/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.usercenter.dao.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author wqi
 * @version $Id: AccountInfoPO.java, v 0.1 2015-2-26 下午5:29:05 wangq Exp $
 */
public class AccountInfoPO implements Serializable {
    
    /**  */
    private static final long serialVersionUID = 7968172837063160405L;
    
    // 用户账户
    private long account;
    
    // 用户名称
    private String accountname;
    
    // 用户真实密码
    private String password;

    // 用户pin密码
    private String pinpassword;

    // 电话号码
    private String phone;
    
    // email
    private String email;
    
    // 手机验证
    private String phoneauth;

    // 邮箱验证
    private String emailauth;

    // 昵称
    private String            nickname;

    // 创建时间
    private Date   createtime;
    
    // 最后修改时间
    private Date   lastmodifytime;

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
     * Getter method for property <tt>accountname</tt>.
     * 
     * @return property value of accountname
     */
    public String getAccountname() {
        return accountname;
    }

    /**
     * Setter method for property <tt>accountname</tt>.
     * 
     * @param accountname value to be assigned to property accountname
     */
    public void setAccountname(String accountname) {
        this.accountname = accountname;
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
     * Getter method for property <tt>phoneauth</tt>.
     * 
     * @return property value of phoneauth
     */
    public String getPhoneauth() {
        return phoneauth;
    }

    /**
     * Setter method for property <tt>phoneauth</tt>.
     * 
     * @param phoneauth value to be assigned to property phoneauth
     */
    public void setPhoneauth(String phoneauth) {
        this.phoneauth = phoneauth;
    }

    /**
     * Getter method for property <tt>emailauth</tt>.
     * 
     * @return property value of emailauth
     */
    public String getEmailauth() {
        return emailauth;
    }

    /**
     * Setter method for property <tt>emailauth</tt>.
     * 
     * @param emailauth value to be assigned to property emailauth
     */
    public void setEmailauth(String emailauth) {
        this.emailauth = emailauth;
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
    
    

}
