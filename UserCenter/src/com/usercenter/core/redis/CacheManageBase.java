/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.usercenter.core.redis;

import com.usercenter.core.util.StringUtil;


/**
 * 
 * @author wangq
 * @version $Id: CacheManageFactory.java, v 0.1 2014-11-12 上午9:15:49 wangq Exp $
 */
public class CacheManageBase {

    public static RedisCacheManager redis              = new RedisCacheManager();
    /** 存活时间  null值存活 5秒*/
    public final static long        ALIVE_TIME_NULL    = 5 * 1000;
    /** 存活时间  默认存活5分钟*/
    public final static long        ALIVE_TIME_DEFAULT = 5 * 60 * 1000;

    /**
     * 缓存String类型数据
     * 
     * @param cacheType
     * @param cacheIndex
     * @param value
     * @return
     */
    public boolean setStringCache(String cacheType, String cacheIndex, String value) {
        redis.setString(cacheType + "_" + cacheIndex, value);
        return true;
    }

    /**
     * 获取String缓存
     * 
     * @param cacheType
     * @param cacheIndex
     * @return
     */
    public String getStringCache(String cacheType, String cacheIndex) {
        return redis.getString(cacheType + "_" + cacheIndex);
    }

    /**
     * 存储缓存缓存对象
     * 
     * @param cacheType
     * @param cacheIndex
     * @return
     */
    public boolean setObjCache(String cacheType, String cacheIndex, Object obj) {
        redis.setObject(cacheType + "_" + cacheIndex, obj);
        return true;
    }

    /**
     * 获取缓存对象
     * 
     * @param cacheType
     * @param cacheIndex
     * @return
     */
    public Object getObjCache(String cacheType, String cacheIndex, boolean needReload) {


        if (StringUtil.isBlank(cacheType) || StringUtil.isBlank(cacheIndex)) {
            return null;
        }

        Object obj = redis.getObject(cacheType + "_" + cacheIndex);

        if (obj != null) {
            return obj;
        }

        if (needReload) {
            synchronized (cacheType + "_" + cacheIndex) {
                obj = redis.getObject(cacheType + "_" + cacheIndex);
                if (obj == null) {
                    obj = uploadCache(cacheType, cacheIndex);
                    if (obj != null) {
                        redis.setObject(cacheType + "_" + cacheIndex, obj);
                    }
                }
            }
        }
        return obj;

    }

    /**
     * 时间校验 
     *      可以根据业务在继承类重新
     * 
     * @param cacheType
     * @param cacheIndex
     * @param dto
     * @return
     */
    protected boolean checkAliveTime(String cacheType, String cacheIndex, Object obj) {
        return true;
    }

    /**
     * 加载缓存数据
     * 
     * @param cacheType
     * @param cacheIndex
     * @return
     */
    protected Object uploadCache(String cacheType, String cacheIndex) {
        return null;
    }

}
