/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.usercenter.core.cache;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import com.usercenter.core.util.StringUtil;

/**
 * 
 * @author wangq
 * @version $Id: CacheManageFactory.java, v 0.1 2014-11-12 上午9:15:49 wangq Exp $
 */
public abstract class CopyOfCacheManageBaseHashmap {

    /** 存活时间  null值存活 5秒*/
    public final static long                                                          ALIVE_TIME_NULL    = 5 * 1000;
    /** 存活时间  默认存活5分钟*/
    public final static long                                                          ALIVE_TIME_DEFAULT = 5 * 60 * 1000;
    private static ConcurrentHashMap<String, ConcurrentHashMap<String, CacheBaseDto>> cacheFactory       = new ConcurrentHashMap<String, ConcurrentHashMap<String, CacheBaseDto>>();

    /**
     * 获取缓存域
     * 
     * @param cacheType
     * @return
     */
    public static ConcurrentHashMap<String, CacheBaseDto> getInstance(String cacheType) {

        if (!cacheFactory.containsKey(cacheType)) {
            synchronized (cacheType) {
                if (!cacheFactory.containsKey(cacheType)) {
                    cacheFactory.put(cacheType, new ConcurrentHashMap<String, CacheBaseDto>());
                }
            }
        }
        return cacheFactory.get(cacheType);
    }

    /**
     * 获取缓存对象
     * 
     * @param cacheType
     * @param cacheIndex
     * @return
     */
    public Object getCache(String cacheType, String cacheIndex) {
        CacheBaseDto dto = getCacheDto(cacheType, cacheIndex);
        return dto == null ? dto : dto.getCache();

    }

    /**
     * 获取缓存对象
     * 
     * @param cacheType
     * @param cacheIndex
     * @return
     */
    public CacheBaseDto getCacheDto(String cacheType, String cacheIndex) {

        if (StringUtil.isBlank(cacheType) || StringUtil.isBlank(cacheIndex)) {
            return null;
        }

        CacheBaseDto dto = getInstance(cacheType).get(cacheIndex);

        // 校验是否需要加载
        boolean needLoad = false;

        if (dto == null) {
            // 缓存不存在时，初始化加载
            needLoad = true;
        } else {
            // 时间校验
            needLoad = !checkAliveTime(cacheType, cacheIndex, dto);
        }

        if (needLoad) {
            synchronized (cacheType + "_" + cacheIndex) {
                dto = getInstance(cacheType).get(cacheIndex);
                if (dto == null) {
                    dto = uploadCache(cacheType, cacheIndex);
                    if (dto != null) {
                        getInstance(cacheType).put(cacheIndex, dto);
                    }
                }
            }
        }
        return dto;

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
    protected boolean checkAliveTime(String cacheType, String cacheIndex, CacheBaseDto dto) {
        return true;
    }

    /**
     * 加载缓存数据
     * 
     * @param cacheType
     * @param cacheIndex
     * @return
     */
    protected CacheBaseDto uploadCache(String cacheType, String cacheIndex) {
        return null;
    }

    /**
     * 缓存对象
     * 
     * @author wangq
     * @version $Id: CacheManageBase.java, v 0.1 2014-11-20 下午4:51:18 wangq Exp $
     */
    class CacheBaseDto {

        /** 缓存对象*/
        private Object cache;

        /** 创建时间*/
        private Date   createDate;

        /** 有效时间 */
        private long   alivetime;

        /**
         * Getter method for property <tt>cache</tt>.
         * 
         * @return property value of cache
         */
        public Object getCache() {
            return cache;
        }

        /**
         * Setter method for property <tt>cache</tt>.
         * 
         * @param cache value to be assigned to property cache
         */
        public void setCache(Object cache) {
            this.cache = cache;
        }

        /**
         * Getter method for property <tt>createDate</tt>.
         * 
         * @return property value of createDate
         */
        public Date getCreateDate() {
            return createDate;
        }

        /**
         * Setter method for property <tt>createDate</tt>.
         * 
         * @param createDate value to be assigned to property createDate
         */
        public void setCreateDate(Date createDate) {
            this.createDate = createDate;
        }

        /**
         * Getter method for property <tt>alivetime</tt>.
         * 
         * @return property value of alivetime
         */
        public long getAlivetime() {
            return alivetime;
        }

        /**
         * Setter method for property <tt>alivetime</tt>.
         * 
         * @param alivetime value to be assigned to property alivetime
         */
        public void setAlivetime(long alivetime) {
            this.alivetime = alivetime;
        }

    }

}
