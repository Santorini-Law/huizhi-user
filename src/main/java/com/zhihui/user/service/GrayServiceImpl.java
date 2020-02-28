package com.zhihui.user.service;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.zhihui.user.domain.GrayFeatureDO;
import com.zhihui.user.model.GrayFeatureDetail;
import com.zhihui.user.service.api.GrayService;
import com.zhihui.user.service.api.IUserService;
import com.zhihui.user.service.loadingcache.api.ILoadingCacheService;
import com.zhihui.user.vo.GrayCheckRequestVO;
import com.zhihui.user.vo.GrayExecuteRequestVO;
import com.zhihui.user.vo.GrayExecuteResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 灰度相关
 *
 * @author LDZ
 * @date 2020-02-28 12:50
 */
@Service
@Slf4j
public class GrayServiceImpl implements GrayService {

    @Resource
    ApplicationContext context;

    /**
     * 获取灰度信息
     *
     * @param grayId 灰度id
     * @return 灰度信息
     */
    private GrayFeatureDO getGrayFeature(Long grayId) {
        ILoadingCacheService<Long, GrayFeatureDO> grayFeatureCache = context.getBean("GRAY_FEATURE_CACHE", ILoadingCacheService.class);
        return grayFeatureCache.getValue(grayId);
    }

    @Override
    public boolean checkInGray(GrayCheckRequestVO requestVO) {

        if (null == requestVO || null == requestVO.getGrayId() || null == requestVO.getUid()) {
            return false;
        }

        GrayFeatureDO grayFeature = getGrayFeature(requestVO.getGrayId());
        if (grayFeature == null) {
            return false;
        }
        GrayFeatureDetail detail = convertGrayFeatureDetail(grayFeature);
        return checkGrayFeatureDetail(detail, requestVO.getUid());

    }


    @Override
    public GrayExecuteResponseVO executeGrayLogic(GrayExecuteRequestVO request) {
        GrayExecuteResponseVO grayExecuteResponseVO = new GrayExecuteResponseVO();
        boolean gray = checkInGray(request);
        Object result;
        Object object = request.getObject();

        BiFunction<String, Object[], Object> reflectMethod = (methodName, params) -> {
            Class[] classes = Stream.of(params).map(Object::getClass).toArray(Class[]::new);
            Method newLogicMethod;
            try {
                newLogicMethod = object.getClass().getDeclaredMethod(methodName, classes);
                return newLogicMethod.invoke(object, params);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                // ignore
                throw new RuntimeException("invoke method error, method name = {}", e);
            }
        };

        if (gray) {
            result = reflectMethod.apply(request.getNewLogicMethod(), request.getNewParams());
        } else {
            result = reflectMethod.apply(request.getOldLogicMethod(), request.getOldParams());
        }
        grayExecuteResponseVO.setGrayResult(gray);
        grayExecuteResponseVO.setResult(result);
        return grayExecuteResponseVO;
    }

    private static final Function<String, List<String>> SPLIT_TO_LIST = str -> Splitter.on(",").omitEmptyStrings().splitToList(str);

    /**
     * 灰度开关关闭
     */
    private static final int CLOSE_SWITCH = 0;

    /**
     * 灰度关闭 全量true
     */
    private static final int TRUE_SWITCH = 2;

    private GrayFeatureDetail convertGrayFeatureDetail(GrayFeatureDO grayFeature) {

        GrayFeatureDetail grayFeatureDetail = new GrayFeatureDetail();

        grayFeatureDetail.setGrayId(grayFeature.getGrayId());
        grayFeatureDetail.setGrayName(grayFeature.getGrayName());
        grayFeatureDetail.setGraySwitch(grayFeature.getGraySwitch());

        List<Long> whiteList = SPLIT_TO_LIST.apply(grayFeature.getWhiteList()).stream().map(Long::new).collect(Collectors.toList());
        grayFeatureDetail.setWhiteList(whiteList);

        List<Long> blackList = SPLIT_TO_LIST.apply(grayFeature.getBlackList()).stream().map(Long::new).collect(Collectors.toList());
        grayFeatureDetail.setBlackList(blackList);

        String[] grayInterval = grayFeature.getGrayInterval().split(",");
        grayFeatureDetail.setGrayIntervalFloor(Integer.valueOf(grayInterval[0]));
        grayFeatureDetail.setGrayIntervalCell(Integer.valueOf(grayInterval[1]));
        grayFeatureDetail.setCreateTime(grayFeature.getCreateTime());

        return grayFeatureDetail;

    }

    private boolean checkGrayFeatureDetail(GrayFeatureDetail detail, Long uid) {
        if (uid == null) {
            return false;
        }
        // 灰度开关关闭
        if (detail.getGraySwitch() == CLOSE_SWITCH) {
            return false;
        }
        // 灰度全量
        if (detail.getGraySwitch() == TRUE_SWITCH) {
            return true;
        }
        // 黑名单
        if (!CollectionUtils.isEmpty(detail.getBlackList()) && detail.getBlackList().contains(uid)) {
            return false;
        }
        // 白名单
        if (!CollectionUtils.isEmpty(detail.getWhiteList()) && detail.getWhiteList().contains(uid)) {
            return true;
        }

        if (detail.getGrayIntervalCell() != null && detail.getGrayIntervalFloor() != null) {
            long mod = uid % 100;
            return detail.getGrayIntervalFloor() <= mod && mod < detail.getGrayIntervalCell();
        }

        return false;
    }
}
