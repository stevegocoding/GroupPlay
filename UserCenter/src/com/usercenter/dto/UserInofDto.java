/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.usercenter.dto;

import java.util.Date;

/**
 * 
 * @author wqi
 * @version $Id: UserInofDto.java, v 0.1 2015-2-27 下午2:36:59 wangq Exp $
 */
public class UserInofDto {
    // 用户账户
    private long   account;

    // 用户名称
    private String username;

    // 用户真实密码
    private String password;

    // 用户pin密码
    private String pinpassword;

    // 电话号码
    private String phone;

    // email
    private String email;

    // 创建时间
    private Date   createtime;

    // 最后修改时间
    private Date   lastmodifytime;

    private boolean developer;

    // 应用key
    private String appkey;

    // 昵称
    private String nickname;

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
     * Getter method for property <tt>password</tt>.
     * 
     * @return property value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for property <tt>password</tt>.
     * 
     * @param password value to be assigned to property password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method for property <tt>pinpassword</tt>.
     * 
     * @return property value of pinpassword
     */
    public String getPinpassword() {
        return pinpassword;
    }

    /**
     * Setter method for property <tt>pinpassword</tt>.
     * 
     * @param pinpassword value to be assigned to property pinpassword
     */
    public void setPinpassword(String pinpassword) {
        this.pinpassword = pinpassword;
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
     * Getter method for property <tt>createtime</tt>.
     * 
     * @return property value of createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * Setter method for property <tt>createtime</tt>.
     * 
     * @param createtime value to be assigned to property createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * Getter method for property <tt>lastmodifytime</tt>.
     * 
     * @return property value of lastmodifytime
     */
    public Date getLastmodifytime() {
        return lastmodifytime;
    }

    /**
     * Setter method for property <tt>lastmodifytime</tt>.
     * 
     * @param lastmodifytime value to be assigned to property lastmodifytime
     */
    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
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

}
