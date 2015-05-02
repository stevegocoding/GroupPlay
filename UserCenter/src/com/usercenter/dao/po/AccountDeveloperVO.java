/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.usercenter.dao.po;

import java.util.Date;

/**
 * 
 * 
 * @author wqi
 * @version $Id: AccountDeveloperPO.java, v 0.1 2015-2-26 下午5:29:00 wangq Exp $
 */
public class AccountDeveloperVO {

    // 用户账户
    private long account;

    // 用户名称
    private String accountname;

    // 电话号码
    private String phone;

    // email
    private String email;

    // 权限等级
    private int  authgrade;

    // 状态 
    private int  devstatus;

    // 创建时间
    private Date createtime;

    // 最后修改时间
    private Date lastmodifytime;

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
     * Getter method for property <tt>authgrade</tt>.
     * 
     * @return property value of authgrade
     */
    public int getAuthgrade() {
        return authgrade;
    }

    /**
     * Setter method for property <tt>authgrade</tt>.
     * 
     * @param authgrade value to be assigned to property authgrade
     */
    public void setAuthgrade(int authgrade) {
        this.authgrade = authgrade;
    }


        /**
     * Getter method for property <tt>devstatus</tt>.
     * 
     * @return property value of devstatus
     */
    public int getDevstatus() {
        return devstatus;
    }

    /**
     * Setter method for property <tt>devstatus</tt>.
     * 
     * @param devstatus value to be assigned to property devstatus
     */
    public void setDevstatus(int devstatus) {
        this.devstatus = devstatus;
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

}
