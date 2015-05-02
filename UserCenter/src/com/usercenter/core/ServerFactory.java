/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.usercenter.core;

/**
 * 
 * @author wqi
 * @version $Id: ServerFactory.java, v 0.1 2014-12-15 下午2:55:31 wqi Exp $
 */
public class ServerFactory {

    public static Object getBean(String bean) {
        return SpringContext.getContext().getBean(bean);
    }
}
