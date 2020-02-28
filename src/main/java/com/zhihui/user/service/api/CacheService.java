package com.zhihui.user.service.api;

/**
 * @author LDZ
 * @date 2018/9/28 上午10:59
 */
public interface CacheService {

    /**
     * 存数据
     *
     * @param key
     * @param value
     * @param <T>
     */
    <T> void saveValue(String key, T value);

    <T> void saveValue(String key, T value, Long seconds);

    /**
     * @param key
     * @param <T>
     * @return
     */
    <T> T getValue(String key);


    // 存储老拉新小程序二维码 redis start

    /**
     * 存小程序菊花码 1天
     *
     * @param key
     * @param value
     */
    void saveInvitationCodeImage(String key, String value);

    /**
     * 取小程序菊花码
     *
     * @param key
     * @return
     */
    String getInvitationCodeImage(String key);


    /**
     * 存储设备端属性
     *
     * @param uid    用户uid
     * @param device 设备
     */
    void saveUserDeviceType(Long uid, String device);

    /**
     * 获取用户端属性
     *
     * @param uid 用户uid
     * @return 设备
     */
    String getUserDeviceType(Long uid);
// 存储老拉新小程序二维码 redis end
}