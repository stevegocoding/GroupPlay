/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.usercenter.manage.handler;

import com.usercenter.dto.UserQueryDto;
import com.usercenter.manage.ManageHandlerResult;

/**
 * 
 * @author wangq
 * @version $Id: UserManageServiceImpl.java, v 0.1 2015-2-26 下午4:09:02 wangq Exp $
 */
public interface IUserQueryHandler {


    /**
     * 
     * 
     * @param appkey
     * @return
     */
    public int countUser(String appkey);

    /**
     * 
     * 
     * @param queryDto
     * @return
     */
    public ManageHandlerResult findUsers(UserQueryDto queryDto);

    /**
     * 
     * 
     * @param queryDto
     * @return
     */
    public ManageHandlerResult findUserByName(String username);

    /**
     * 
     * 
     * @param queryDto
     * @return
     */
    public ManageHandlerResult findUserByAccount(String account);

    /**
     * 根据用户名查询电话号码
     * 
     * @param username
     * @return
     */
    public String getphoneByName(String username);

    /**
     * 根据用户账户查询电话号码
     * 
     * @param username
     * @return
     */
    public String getphoneByaccount(String account);

    /**
     * 校验用户名称是否存在
     * 
     * @param username
     * @return
     */
    public boolean isExistUser(String username);

    /**
     * 根据账户获取密码
     * 
     * @param account
     * @return
     */
    public String getPasswordByaccount(String account);



}
