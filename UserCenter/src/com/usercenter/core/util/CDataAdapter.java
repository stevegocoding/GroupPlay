/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.usercenter.core.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 * 
 * @author wangq
 * @version $Id: CDataAdapter.java, v 0.1 2014-11-13 上午10:39:57 wangq Exp $
 */
public class CDataAdapter extends XmlAdapter<String, String> {

    @Override
    public String marshal(String str) throws Exception {
        if (str.startsWith("<![CDATA[") && str.endsWith("]]")) {
            return str;
        }
        return "<![CDATA[" + str + "]]>";
    }

    @Override
    public String unmarshal(String str) throws Exception {

        return StringUtil.getSubString(str, "<![CDATA[", "]]>");
    }

}
