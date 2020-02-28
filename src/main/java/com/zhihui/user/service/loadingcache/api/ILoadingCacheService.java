package com.zhihui.user.service.loadingcache.api;

/**
 * loading cache
 *
 * @author LDZ
 * @date 2020-02-28 14:26
 */
public interface ILoadingCacheService<K, V> {

    /**
     * 获取缓存数据
     *
     * @param k key
     * @return value
     */
    V getValue(K k);

    /**
     * 清空缓存
     */
    void clear();

    /**
     * 缓存数据
     *
     * @param k key
     * @param v value
     */
    void saveValue(K k, V v);

    /**
     * 获取缓存数量
     *
     * @return 缓存数量
     */
    long size();
}
