/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.usercenter.dao.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.usercenter.dao.po.AccountAppPO;
import com.usercenter.dao.po.AccountDeveloperPO;
import com.usercenter.dao.po.AccountInfoPO;

/**
 * 用户信息
 * @author wangq
 * @version $Id: UserInfoVO.java, v 0.1 2015-2-4 下午8:50:25 wangq Exp $
 */
public class UserInfoVO implements Serializable {

    /**  */
    private static final long  serialVersionUID = 827627798695543731L;

    // 用户主键
    private long                    id;

    // 用户账户
    private long                    account;

    // 用户名称
    private String                  accountname;

    // 用户真实密码
    private String                  password;

    // 用户pin密码
    private String                  pinpassword;

    // 电话号码
    private String                  phone;

    // 昵称
    private String                  nickName;

    // email
    private String                  email;

    // 创建时间
    private Date                    createtime;

    // 最后修改时间
    private Date                    lastmodifytime;

    // 开发者状态
    private AccountDeveloperPO        accountDev;

    // 应用列表
    private List<AccountAppPO> accountAppList;


    public UserInfoVO() {
        super();
    }

    /**
     * 构造函数
     * @param infovo
     * @param accountDev
     * @param accountAppList
     */
    public UserInfoVO(AccountInfoPO infovo, AccountDeveloperPO accountDev, List<AccountAppPO> accountAppList) {
        this.account = infovo.getAccount();
        this.accountname = infovo.getAccountname();
        this.password = infovo.getPassword();
        this.pinpassword = infovo.getPinpassword();
        this.phone = infovo.getPhone();
        this.email = infovo.getEmail();
        this.nickName = infovo.getNickname();
        this.createtime = infovo.getCreatetime();
        this.lastmodifytime = infovo.getLastmodifytime();
        this.accountDev = accountDev;
        this.accountAppList = accountAppList;
    }

    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     */
    public void setId(long id) {
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
     * Getter method for property <tt>accountDev</tt>.
     * 
     * @return property value of accountDev
     */
    public AccountDeveloperPO getAccountDev() {
        return accountDev;
    }

    /**
     * Setter method for property <tt>accountDev</tt>.
     * 
     * @param accountDev value to be assigned to property accountDev
     */
    public void setAccountDev(AccountDeveloperPO accountDev) {
        this.accountDev = accountDev;
    }


    /**
     * Getter method for property <tt>accountAppList</tt>.
     * 
     * @return property value of accountAppList
     */
    public List<AccountAppPO> getAccountAppList() {
        return accountAppList;
    }

    /**
     * Setter method for property <tt>accountAppList</tt>.
     * 
     * @param accountAppList value to be assigned to property accountAppList
     */
    public void setAccountAppList(List<AccountAppPO> accountAppList) {
        this.accountAppList = accountAppList;
    }



    /**
     * 增加应用
     * 
     * @param appPO
     * @return
     */
    public boolean addAccountApp(AccountAppPO appPO) {
        if (accountAppList == null) {
            accountAppList = new ArrayList<AccountAppPO>(5);
        }
        accountAppList.add(appPO);
        return true;
    }

    /**
     * 删除应用
     * 
     * @param appPO
     * @return
     */
    public boolean delAccountApp(AccountAppPO appPO) {
        if (accountAppList != null) {
            accountAppList.remove(appPO);
        }
        return true;
    }

    /**
     * 判断是否包含appkey
     * 
     * @param appkey
     * @return
     */
    public boolean containApp(String appkey) {
        if (accountAppList != null) {
            for (AccountAppPO appPO : accountAppList) {
                if (appPO.getAppkey().equals(appkey)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Getter method for property <tt>nickName</tt>.
     * 
     * @return property value of nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Setter method for property <tt>nickName</tt>.
     * 
     * @param nickName value to be assigned to property nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}
