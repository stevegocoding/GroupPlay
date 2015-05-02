/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.usercenter.core.cache;

import java.util.Date;

import com.usercenter.dao.po.AccountInfoPO;

/**
 * 
 * @author wqi
 * @version $Id: Test.java, v 0.1 2015-2-27 下午5:03:32 wangq Exp $
 */
public class Test {

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {



        AccountInfoPO infoPO = new AccountInfoPO();
        infoPO.setAccount(9374353L);
        infoPO.setAccountname("ASDFASDFASDFS");
        infoPO.setEmail("W34234234@ASDFASDFSD.COM");
        infoPO.setPhone("343534532452345");
        infoPO.setLastmodifytime(new Date());
        infoPO.setCreatetime(new Date());
        infoPO.setPassword("asfdaswerwef");
        infoPO.setPinpassword("dfasdfasdfasdf");
        infoPO.setEmailauth("1");

        long total = 0;
        for (int j = 0; j < 1000; j++) {

        try {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                    infoPO.setAccount(i);
                    byte[] b = RedisCacheManager.serialize(infoPO);
                    infoPO = (AccountInfoPO) RedisCacheManager.deserialize(b);
            }
                long time = (System.currentTimeMillis() - start);
                total += time;
                System.out.println("time: " + time);
        } catch (Exception e) {
        }

        }
        System.out.println("serialize: avg: 235  avg: 742  avg: 690 avg: 574  " + "avg: " + (total / 100));

    }

}
