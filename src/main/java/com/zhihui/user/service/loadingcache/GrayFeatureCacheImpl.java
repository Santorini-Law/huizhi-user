package com.zhihui.user.service.loadingcache;

import com.github.benmanes.caffeine.cache.Cache;
import com.zhihui.user.dao.GrayDAO;
import com.zhihui.user.domain.GrayFeatureDO;
import com.zhihui.user.service.loadingcache.api.ILoadingCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LDZ
 * @date 2020-02-28 14:24
 */
@Service("GRAY_FEATURE_CACHE")
@Slf4j
public class GrayFeatureCacheImpl implements ILoadingCacheService<Long, GrayFeatureDO> {


    @Resource
    @Qualifier("grayFeatureCache")
    Cache<Long, GrayFeatureDO> grayFeatureCache;

    @Resource
    GrayDAO grayDAO;

    @Override
    public GrayFeatureDO getValue(Long grayId) {
        return grayFeatureCache.get(grayId, key -> grayDAO.getGrayFeatureByGrayId(key));
    }

    @Override
    public void clear() {

    }

    @Override
    public void saveValue(Long integer, GrayFeatureDO grayFeatureDO) {

    }

    @Override
    public long size() {
        return 0;
    }
}
