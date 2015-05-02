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
 * @version $Id: AccountAppPO.java, v 0.1 2015-2-26 下午5:28:55 wangq Exp $
 */
public class AccountAppPO implements Serializable {

    /**  */
    private static final long serialVersionUID = -3324248038527502743L;

    private int    id;

    // 用户账户
    private long account;

    // 应用key
    private String appkey;

    // 用户状态
    private int  status;

    // 昵称
    private String nickname;

    // 创建时间
    private Date createtime;

    // 最后修改时间
    private Date lastmodifytime;

    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     */
    public void setId(int id) {
        this.id = id;
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
     * Getter method for property <tt>status</tt>.
     * 
     * @return property value of status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Setter method for property <tt>status</tt>.
     * 
     * @param status value to be assigned to property status
     */
    public void setStatus(int status) {
        this.status = status;
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
