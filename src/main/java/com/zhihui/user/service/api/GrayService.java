package com.zhihui.user.service.api;

import com.zhihui.user.vo.GrayCheckRequestVO;
import com.zhihui.user.vo.GrayExecuteRequestVO;
import com.zhihui.user.vo.GrayExecuteResponseVO;

/**
 * 灰度相关
 *
 * @author LDZ
 * @date 2020-02-28 12:48
 */
public interface GrayService {


    /**
     * 获取灰度结果
     *
     * @param requestVO 请求
     * @return 灰度结果
     */
    boolean checkInGray(GrayCheckRequestVO requestVO);

    /**
     * 直接执行灰度 想要执行的方法
     *
     * @param requestVO 请求
     * @return 灰度结果 执行结果
     */
    GrayExecuteResponseVO executeGrayLogic(GrayExecuteRequestVO requestVO);
}
