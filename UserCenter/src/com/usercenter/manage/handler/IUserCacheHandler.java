/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.usercenter.manage.handler;

import java.util.ArrayList;

import com.usercenter.dao.vo.UserInfoVO;

/**
 * 
 * @author wangq
 * @version $Id: UserManageServiceImpl.java, v 0.1 2015-2-26 下午4:09:02 wangq Exp $
 */
public interface IUserCacheHandler {


    /**
     * 刷新缓存
     * 
     * @param infoVO
     * @return
     */
    public boolean refresh(UserInfoVO infoVO);


    /**
     * 刷新缓存
     * 
     * @param infoVO
     * @return
     */
    public boolean refresh(String account);

    /**
     * 刷新缓存
     * 
     * @param infoVO
     * @return
     */
    public boolean refresh(String userName, boolean isload);

    /**
     * 根据name获取用户信息
     * 
     * @param queryDto
     * @return
     */
    public UserInfoVO findUserByName(String username, boolean isload);

    /**
     * 根据account获取用户信息
     * 
     * @param queryDto
     * @return
     */
    public UserInfoVO findUserByAccount(String account, boolean isload);

    /**
     * 
     * 
     * @param accounts
     * @return
     */
    public ArrayList<UserInfoVO> findUserByAccounts(String[] accounts);

    /**
     * 根据用户名查询account
     * 
     * @param username
     * @param isload
     * @return
     */
    public String findAccountByName(String username, boolean isload);

    /**
     * 删除
     * 
     * @param username
     * @return
     */
    public boolean deleteUserByName(String username);

    /**
     * 删除
     * 
     * @param account
     * @return
     */
    public boolean deleteUserByAccount(String account);

    /**
     * 检查用户是否存在
     * 
     * @param username
     * @return
     */
    public boolean isExistUser(String username);


    /**
     * 加锁
     * 
     * @param username
     * @return
     */
    public boolean lock(String username);

    /**
     * 解锁
     * 
     * @param username
     * @return
     */
    public boolean unlock(String username);

    /**
     * 设置token
     * 
     * @param token
     * @param value
     * @return
     */
    public boolean setToken(String token, String value);

    /**
     * 获取token
     * 
     * @param token
     * @return
     */
    public String getToken(String token);

    /**
     * 删除token
     * 
     * @param token
     * @return
     */
    public boolean delToken(String token);
}
