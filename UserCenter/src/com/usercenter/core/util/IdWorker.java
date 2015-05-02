/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.usercenter.core.util;

import java.util.Date;

/**
 * 分布式Id发放
 * 
 * @author wqi
 * @version $Id: IDWorker.java, v 0.1 2015-2-28 下午2:46:17 wangq Exp $
 */
public class IdWorker {

    private static IdWorker   idWorker;
    private final long        workerId;
    private final static long twepoch            = 1361753741828L;

    //机器标识位数
    private final static long workerIdBits       = 7L;
    //毫秒内自增位
    private final static long sequenceBits       = 11L;
    //机器ID偏左移位
    private final static long workerIdShift      = sequenceBits;
    //时间毫秒左移位
    private final static long timestampLeftShift = sequenceBits + workerIdBits;
    //机器ID最大值
    public final static long  maxWorkerId        = -1L ^ -1L << workerIdBits;
    //毫秒内序列最大值
    public final static long  sequenceMask       = -1L ^ -1L << sequenceBits;
    // 最新时间
    private long              lastTimestamp      = -1L;
    // 序列
    private long              sequence           = 0L;

    /**
     * workerId 设置为 0-128   
     */
    private static void getInstance() {
        if (idWorker == null) {
            synchronized ("idWorker") {
                if (idWorker == null) {
                    idWorker = new IdWorker(10);
                }
            }
        }
    }

    private IdWorker(final long workerId) {
        super();
        if (workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", IdWorker.maxWorkerId));
        }
        if (workerId > IdWorker.maxWorkerId) {
            this.workerId = workerId % IdWorker.maxWorkerId;
        } else {
            this.workerId = workerId;
        }

    }

    public static synchronized long nextId() {
        getInstance();

        long timestamp = IdWorker.timeGen();
        if (idWorker.lastTimestamp == timestamp) {
            idWorker.sequence = (idWorker.sequence + 1) & IdWorker.sequenceMask;
            if (idWorker.sequence == 0) {
                //                System.out.println("###########" + sequenceMask);
                timestamp = IdWorker.tilNextMillis(idWorker.lastTimestamp);
            }
        } else {
            idWorker.sequence = 0;
        }
        if (timestamp < idWorker.lastTimestamp) {
            try {
                throw new Exception(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", idWorker.lastTimestamp - timestamp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        idWorker.lastTimestamp = timestamp;
        long nextId = ((timestamp - twepoch << timestampLeftShift)) | (idWorker.workerId << IdWorker.workerIdShift) | (idWorker.sequence);
        return nextId;
    }

    /**
     * guid还原为时间戳
     * 
     * @param guid
     * @return
     */
    public static long restore(long guid) {
        return (guid >> timestampLeftShift) + twepoch;
    }


    /**
     * 跟新时间
     * 
     * @param lastTimestamp
     * @return
     */
    private static long tilNextMillis(final long lastTimestamp) {
        long timestamp = IdWorker.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = IdWorker.timeGen();
        }
        return timestamp;
    }


    /**
     * 获取系统时间
     * 
     * @return
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        long id = IdWorker.nextId();
        long i = IdWorker.restore(id);
        System.out.println(i);
        Date d = new Date(i);

        System.out.println(d);
        //        long start = System.currentTimeMillis();
        //        for (int i = 0; i < end; i++) {
        //            worker2.nextId();
        //        }
        //        long total = (System.currentTimeMillis() - start);
        //        System.out.println(onetime + "平均耗时：" + (System.currentTimeMillis() - start) / time + " total :" + total);
    }

}
