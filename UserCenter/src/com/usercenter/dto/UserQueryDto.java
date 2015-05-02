/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.usercenter.dto;


/**
 * 
 * @author wqi
 * @version $Id: UserInofDto.java, v 0.1 2015-2-27 下午2:36:59 wangq Exp $
 */
public class UserQueryDto {

    // 需要的字段，使用; 分割
    private String  fields;

    // 用户账户
    private long   account;

    // 用户名称
    private String username;

    // 电话号码
    private String phone;

    // email
    private String email;

    // 开发者
    private boolean developer;

    // 应用key
    private String appkey;

    // 昵称
    private String nickname;

    private int     index;

    private int     num;

    private int     direction = 0;

    /**
     * 检查参数
     * 
     * @return
     */
    public boolean check() {
        return true;
    }

    public String paramString() {
        StringBuilder bld = new StringBuilder();
        if (username != null) {
            bld.append("username=" + username);
        }
        if (username != null) {
            bld.append(";password=*******");
        }
        if (username != null) {
            bld.append(";phone=" + phone);
        }
        if (username != null) {
            bld.append(";email=" + email);
        }
        if (username != null) {
            bld.append(";nickname=" + nickname);
        }
        if (username != null) {
            bld.append(";appkey=" + appkey);
        }

        return bld.toString();
    }

    /**
     * Getter method for property <tt>account</tt>.
     * 
     * @return property value of account
     */
    public long getAccount() {
        return account;
    }

    /**
     * Setter method for property <tt>account</tt>.
     * 
     * @param account value to be assigned to property account
     */
    public void setAccount(long account) {
        this.account = account;
    }

    /**
     * Getter method for property <tt>username</tt>.
     * 
     * @return property value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter method for property <tt>username</tt>.
     * 
     * @param username value to be assigned to property username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter method for property <tt>phone</tt>.
     * 
     * @return property value of phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter method for property <tt>phone</tt>.
     * 
     * @param phone value to be assigned to property phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter method for property <tt>email</tt>.
     * 
     * @return property value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for property <tt>email</tt>.
     * 
     * @param email value to be assigned to property email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method for property <tt>developer</tt>.
     * 
     * @return property value of developer
     */
    public boolean isDeveloper() {
        return developer;
    }

    /**
     * Setter method for property <tt>developer</tt>.
     * 
     * @param developer value to be assigned to property developer
     */
    public void setDeveloper(boolean developer) {
        this.developer = developer;
    }

    /**
     * Getter method for property <tt>appkey</tt>.
     * 
     * @return property value of appkey
     */
    public String getAppkey() {
        return appkey;
    }

    /**
     * Setter method for property <tt>appkey</tt>.
     * 
     * @param appkey value to be assigned to property appkey
     */
    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    /**
     * Getter method for property <tt>nickname</tt>.
     * 
     * @return property value of nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Setter method for property <tt>nickname</tt>.
     * 
     * @param nickname value to be assigned to property nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Getter method for property <tt>index</tt>.
     * 
     * @return property value of index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Setter method for property <tt>index</tt>.
     * 
     * @param index value to be assigned to property index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Getter method for property <tt>num</tt>.
     * 
     * @return property value of num
     */
    public int getNum() {
        return num;
    }

    /**
     * Setter method for property <tt>num</tt>.
     * 
     * @param num value to be assigned to property num
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * Getter method for property <tt>direction</tt>.
     * 
     * @return property value of direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Setter method for property <tt>direction</tt>.
     * 
     * @param direction value to be assigned to property direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Getter method for property <tt>fields</tt>.
     * 
     * @return property value of fields
     */
    public String getFields() {
        return fields;
    }

    /**
     * Setter method for property <tt>fields</tt>.
     * 
     * @param fields value to be assigned to property fields
     */
    public void setFields(String fields) {
        this.fields = fields;
    }


}
