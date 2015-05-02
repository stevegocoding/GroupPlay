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
 * @version $Id: AccountDeveloperPO.java, v 0.1 2015-2-26 下午5:29:00 wangq Exp $
 */
public class AccountDeveloperPO implements Serializable {


    /**  */
    private static final long serialVersionUID = 4784894644123606362L;

    // 用户账户
    private long account;

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

}
