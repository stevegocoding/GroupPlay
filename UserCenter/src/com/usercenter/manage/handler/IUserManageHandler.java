/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.usercenter.manage.handler;

import java.util.List;

import com.usercenter.dto.UserInofDto;
import com.usercenter.manage.ManageHandlerResult;

/**
 * 
 * @author wangq
 * @version $Id: UserManageServiceImpl.java, v 0.1 2015-2-26 下午4:09:02 wangq Exp $
 */
public interface IUserManageHandler {

    /**
     * 创建用户
     * 
     * @param userInfo
     * @return
     */
    public ManageHandlerResult createUser(UserInofDto userInfo);

    /**
     * 创建用户
     * 
     * @param userInfoList
     * @return
     */
    public ManageHandlerResult createUsers(List<UserInofDto> userInfoList, String appkey);

    /** 
     * 用户开通开发者
     * 
     * @param username
     * @param password
     * @return
     */
    public ManageHandlerResult opendeveloper(String username, String password);

    /**
     * 用户开通appkey应用
     * 
     * @param username
     * @param password
     * @param appkey
     * @param nickname
     * @return
     */
    public ManageHandlerResult openApplication(String username, String password, String appkey, String nickname);

    /** 
     * 根据userName 和appkey 删除应用
     * 
     * @param usernames
     * @param appkey
     * @return
     */
    public ManageHandlerResult deleteByNames(String usernames, String appkey);

    /** 
     * 根据account 和 appkey 删除应用
     * 
     * @param account
     * @param appkey
     * @return
     */
    public ManageHandlerResult deleteByAcccount(String account, String appkey);

    /**
     * 删除用户
     * 
     * @param username
     * @return
     */
    public ManageHandlerResult deleteDevByName(String username);

    /**
     * 修改用户
     * 
     * @param userInfo
     * @return
     */
    public ManageHandlerResult updateUser(UserInofDto userInfo);

    /**
     * 批量修改用户
     * 
     * @param userInfoList
     * @return
     */
    public ManageHandlerResult updateUsers(List<UserInofDto> userInfoList, String appkey);

    /**
     * 修改用户应用昵称
     * 
     * @param username
     * @param appkey
     * @param nickname
     * @return
     */
    public ManageHandlerResult updateNickName(String username, String appkey, String nickname);

    /**
     * 修改用户应用昵称
     * 
     * @param username
     * @param appkey
     * @param nickname
     * @return
     */
    public ManageHandlerResult updatepwd(String username, String newpwd, String oldpwd, String appkey);

    /**
     * 登陆处理
     * 
     * @param username
     * @param password
     * @param opendeveloper
     * @param appkey
     * @return
     */
    public ManageHandlerResult load(String username, String password, boolean opendeveloper, String appkey, String phonecode);

    /**
     * 检查token
     * 
     * @param token
     * @param phonecode
     * @return
     */
    public ManageHandlerResult checkToken(String token, String phonecode);

}
