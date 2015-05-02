/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.usercenter.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

/**
 * 有关字符串处理的工具类。
 * 
 * <p>
 * 这个类中的每个方法都可以“安全”地处理<code>null</code>，而不会抛出<code>NullPointerException</code>。
 * </p>
 *
 * @author wangqi
 * @version $Id: StringUtil.java, v 0.1 2014-5-28 下午3:04:07 wangqi Exp $
 */
public class StringUtil {
    /* ============================================================================ */
    /*  常量和singleton。                                                           */
    /* ============================================================================ */

    /** 空字符串。 */
    public static final String EMPTY_STRING = "";

    /* ============================================================================ */
    /*  判空函数。                                                                  */
    /*                                                                              */
    /*  以下方法用来判定一个字符串是否为：                                          */
    /*  1. null                                                                     */
    /*  2. empty - ""                                                               */
    /*  3. blank - "全部是空白" - 空白由Character.isWhitespace所定义。              */
    /* ============================================================================ */

    /**
     * 检查字符串是否为<code>null</code>或空字符串<code>""</code>。
     * <pre>
     * StringUtil.isEmpty(null)      = true
     * StringUtil.isEmpty("")        = true
     * StringUtil.isEmpty(" ")       = false
     * StringUtil.isEmpty("bob")     = false
     * StringUtil.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果为空, 则返回<code>true</code>
     */
    public static boolean isEmpty(String str) {
        return ((str == null) || (str.length() == 0));
    }

    /**
     * 检查字符串是否不是<code>null</code>和空字符串<code>""</code>。
     * <pre>
     * StringUtil.isEmpty(null)      = false
     * StringUtil.isEmpty("")        = false
     * StringUtil.isEmpty(" ")       = true
     * StringUtil.isEmpty("bob")     = true
     * StringUtil.isEmpty("  bob  ") = true
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果不为空, 则返回<code>true</code>
     */
    public static boolean isNotEmpty(String str) {
        return ((str != null) && (str.length() > 0));
    }

    /**
     * 检查字符串是否是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
     * <pre>
     * StringUtil.isBlank(null)      = true
     * StringUtil.isBlank("")        = true
     * StringUtil.isBlank(" ")       = true
     * StringUtil.isBlank("bob")     = false
     * StringUtil.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果为空白, 则返回<code>true</code>
     */
    public static boolean isBlank(String str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 检查字符串是否不是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
     * <pre>
     * StringUtil.isBlank(null)      = false
     * StringUtil.isBlank("")        = false
     * StringUtil.isBlank(" ")       = false
     * StringUtil.isBlank("bob")     = true
     * StringUtil.isBlank("  bob  ") = true
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果为空白, 则返回<code>true</code>
     */
    public static boolean isNotBlank(String str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    /**
     * 获取子字符串，
     *      beginStr ：  为空从头开始截取
     *      endStr   ： 为空截取到字符串结束
     *      同时存在：必须同时包含beginStr、endStr，并且beginStr 在endStr 前，才进行截取
     * 
     * @param str
     * @param beginStr
     * @param endStr
     * @return
     */
    public static String getSubString(String str, String beginStr, String endStr) {
        return getSubString(str, beginStr, endStr, true);
    }

    /**
     * 获取子字符串，
     *      beginStr ：  为空从头开始截取
     *      endStr   ： 为空截取到字符串结束
     *      同时存在：必须同时包含beginStr、endStr，并且beginStr 在endStr 前，才进行截取
     * 
     * @param str
     * @param beginStr
     * @param endStr
     * @param returnDefault  截取失败是否返回原字符串
     * @return
     */
    public static String getSubString(String str, String beginStr, String endStr, boolean returnDefault) {
        if (StringUtil.isEmpty(str) || (beginStr == null && endStr == null)) {
            return returnDefault ? str : null;
        }
        int begin = 0;
        int end = str.length();
        if (beginStr != null) {
            begin = str.indexOf(beginStr) + beginStr.length();
        }
        if (endStr != null) {
            end = str.lastIndexOf(endStr);
        }
        if (begin != -1 && end != -1 && begin < end) {
            return str.substring(begin, end);

        }
        return returnDefault ? str : null;
    }

    /**
     * 字符串转换为正则表达式，
     *  主要用于特殊字符转换
     *      * . ? + $ ^ [ ] ( ) { } | \ /
     * @param str
     * @return
     */
    public static String convertPattern(String str) {
        if (isBlank(str)) {
            return str;
        }
        return str.replaceAll("[\\* \\. \\+ \\? \\^ \\$ \\[ \\] \\{ \\} \\| \\/ \\\\]", "\\\\$0");
    }

    /**
     * sip-uri 验证
     * 1、是否以sip：开头
     * 2、是否包含@
     * 3、sip：后，@前，不能为空(最少不得少于两位)
     * 4、@后不能为空。
     */
    public static boolean isSipUri(String str) {
        //        String sipuriPattern = "sip:[+a-zA-Z0-9._-]{1,}[a-zA-Z0-9]{1,}@[a-zA-Z0-9][a-zA-Z0-9.:]+[a-zA-Z0-9]";
        //        boolean result = Pattern.matches(sipuriPattern, str);
        //        return result;
        return true;
    }

    /**
     * 校验是否是时间戳
     * 非空，13位数字认为有效
     * 
     * @param str
     * @param allowEmpty 允许为空
     * @return
     */
    public static boolean isTimestamp(String str, boolean allowEmpty) {
        if (allowEmpty && isEmpty(str)) {
            return true;
        }
        if (str.length() != 19 || strToDate(str) == null) {
            return false;
        }

        return true;
    }

    /**
     * 文本转换为日期
     * 
     * @param str
     * @return
     */
    public static Date strToDate(String str) {
        if (isEmpty(str)) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (str.length() != 19) {
                return null;
            }
            return dateFormat.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 日期转换为文本
     * 
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    /**
     * check str 
     * 1.If str is not a json string, return null
     * 2.If str is a json string, return JSONObject
     * @param str
     * @return JSONObject
     */
    public static JSONObject toJsonObject(String str) {
        try {
            JSONObject jsonNode = JSONObject.fromObject(str);
            return jsonNode;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * corporation_id  验证
     * 1、corporation_id 不允许为null 和  "";
     * 2、corporation_id 必须是 1 到 11 数字组成
     */
    public static boolean isCorporationId(String corporation_id) {
        if (isBlank(corporation_id)) {
            return false;
        }
        String sipuriPattern = "^\\d{1,11}$";
        return Pattern.matches(sipuriPattern, corporation_id);
    }

    /**
     * 检查用户名称
     * 
     * @param name
     * @return
     */
    public static boolean checkName(String name) {
        boolean flag = false;
        try {
            String check = "[0-9a-zA-Z]*";
            Pattern regex = Pattern.compile(check);
            flag = name.matches(check);
            Matcher matcher = regex.matcher(name);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }

        return flag;
    }

    /**
     * 检查密码
     * 
     * @param name
     * @return
     */
    public static boolean checkPwd(String pwd) {
        //        String regEx="[^0-9a-zA-Z`~!@#$%^&*()_+=|{}':;',//[//].<>/?]";   

        boolean flag = false;
        try {
            String check = "[0-9a-zA-Z`~!@#$%^&*()_+=|{}':;',//[//].<>/?]*";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(pwd);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }

        return flag;

    }

    /**  
     * 验证邮箱地址是否正确  
     * @param email  
     * @return  
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }

        return flag;
    }

    /**  
     * 验证手机号码  
     * @param mobiles  
     * @return  [0-9]{5,9}  
     */
    public static boolean isMobileNO(String mobiles) {
        boolean flag = false;
        try {
            Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

}
