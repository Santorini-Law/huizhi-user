package com.zhihui.user.dao;

import com.zhihui.user.domain.GrayFeatureDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 灰度
 *
 * @author LDZ
 * @date 2020-02-28 12:51
 */
@Mapper
public interface GrayDAO {

    /**
     * 根据灰度id获取灰度信息
     *
     * @param grayId 灰度id
     * @return 灰度信息
     */
    GrayFeatureDO getGrayFeatureByGrayId(Long grayId);
}
