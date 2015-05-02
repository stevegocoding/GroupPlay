/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.usercenter.core.cache;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author wangq
 * @version $Id: CacheManageFactory.java, v 0.1 2014-11-12 上午9:15:49 wangq Exp $
 */
public class CacheManageFactory {

    /** 消息类型注册信息 缓存*/
    public final static String     CACHE_TYPE_ACCOUNT_INFO = "Act_info";

    @Autowired
    private AccountInfoCacheManage accountInfoCacheManage;
    @Autowired
    private CacheManageBase        cacheManageBase;

    /**
     * 获取缓存内容
     * 
     * @param cacheType
     * @param cacheIndex
     * @return
     */
    public Object getCacheObject(String cacheType, String cacheIndex, boolean needReload) {
        CacheManageBase base = getCacheManage(cacheType);
        if (base == null) {
            return null;
        }
        return base.getObjCache(cacheType, cacheIndex, needReload);
    }
    
    /**
     * 获取缓存内容
     * 
     * @param cacheType
     * @param cacheIndex
     * @return
     */
    public Object setCacheObject(String cacheType, String cacheIndex, Object obj) {
        CacheManageBase base = getCacheManage(cacheType);
        if (base == null) {
            return null;
        }
        return base.setObjCache(cacheType, cacheIndex, obj);
    }

    /**
     * 获取String类型缓存数据
     * 
     * @param cacheType
     * @param cacheIndex
     * @return
     */
    public String getCacheString(String cacheType, String cacheIndex) {
        return cacheManageBase.getStringCache(cacheType, cacheIndex);
    }

    /**
     * 缓存String类型数据
     * 
     * @param cacheType
     * @param cacheIndex
     * @param value
     * @return
     */
    public boolean setCacheString(String cacheType, String cacheIndex, String value) {
        return cacheManageBase.setStringCache(cacheType, cacheIndex, value);
    }

    /**
     * 获取
     * 
     * @param cacheType
     * @return
     */
    private CacheManageBase getCacheManage(String cacheType) {
        if (CACHE_TYPE_ACCOUNT_INFO.equalsIgnoreCase(cacheType)) {
            return accountInfoCacheManage;
        }
        return null;
    }
}
