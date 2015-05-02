/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.usercenter.manage.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.usercenter.dao.AccountAppDaoMapper;
import com.usercenter.dao.AccountDeveloperDaoMapper;
import com.usercenter.dao.AccountInfoDaoMapper;
import com.usercenter.dao.vo.UserInfoVO;
import com.usercenter.dto.PoVoConvert;
import com.usercenter.dto.UserQueryDto;
import com.usercenter.manage.ManageHandlerResult;
import com.usercenter.manage.handler.IUserCacheHandler;
import com.usercenter.manage.handler.IUserQueryHandler;
import com.usercenter.util.CommonConstants;

/**
 * 
 * @author wqi
 * @version $Id: UserQueryHandlerImpl.java, v 0.1 2015-3-16 下午2:04:10 wangq Exp $
 */
public class UserQueryHandlerImpl implements IUserQueryHandler {

    public static Logger              logger = Logger.getLogger(UserQueryHandlerImpl.class);

    @Autowired
    private AccountInfoDaoMapper      accountInfoDaoMapper;
    @Autowired
    private AccountAppDaoMapper       accountAppDaoMapper;
    @Autowired
    private AccountDeveloperDaoMapper accountDeveloperDaoMapper;

    @Autowired
    private IUserCacheHandler         userCacheHandler;

    /**
     * 
     * @see com.usercenter.manage.handler.IUserQueryHandler#countUser(java.lang.String)
     */
    public int countUser(String appkey) {

        return accountAppDaoMapper.getSizeByappkey(appkey);
    }

    /**
     * 
     * 
     * @param queryDto
     * @return
     */
    public ManageHandlerResult findUsers(UserQueryDto queryDto) {

        // 查询所有的account
        List<String> accountList = new ArrayList<String>();
        
        
        ArrayList<UserInfoVO> infoVoList = userCacheHandler.findUserByAccounts(accountList.toArray(new String[0]));
        return new ManageHandlerResult(infoVoList);
    }

    /**
     * 
     * 
     * @param queryDto
     * @return
     */
    public ManageHandlerResult findUserByName(String username) {
        logger.info("findUserByName " + " username=" + username);
        return findUser(userCacheHandler.findUserByName(username, true));
    }

    /**
     * 
     * 
     * @param queryDto
     * @return
     */
    public ManageHandlerResult findUserByAccount(String account) {
        logger.info("findUserByAccount " + " account=" + account);
        return findUser(userCacheHandler.findUserByAccount(account, true));
    }

    /**
     * 获取用户信息
     * 
     * @param infoVO
     * @return
     */
    private ManageHandlerResult findUser(UserInfoVO infoVO) {
        if (infoVO == null) {
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.USERNAME_ISNOTEXIST, "用户不存在");
        }
        JSONObject json = PoVoConvert.userInfoVO2Json(infoVO);
        return new ManageHandlerResult(json.toString());
    }

    /**
     * 
     * @see com.usercenter.manage.handler.IUserQueryHandler#getphoneByName(java.lang.String)
     */
    public String getphoneByName(String username) {
        return getPhone(userCacheHandler.findUserByName(username, true));
    }

    /**
     * 
     * @see com.usercenter.manage.handler.IUserQueryHandler#getphoneByaccount(java.lang.String)
     */
    public String getphoneByaccount(String account) {
        return getPhone(userCacheHandler.findUserByAccount(account, true));

    }

    /**
     * 获取手机号
     * 
     * @param infoVO
     * @return
     */
    private String getPhone(UserInfoVO infoVO) {
        return infoVO != null ? infoVO.getPhone() : null;
    }

    /**
     * 
     * @see com.usercenter.manage.handler.IUserQueryHandler#isExistUser(java.lang.String)
     */
    public boolean isExistUser(String username) {
        // 查询用户是否存在
        return userCacheHandler.isExistUser(username);
    }

    /**
     * 获取密码
     * @see com.usercenter.manage.handler.IUserQueryHandler#getPasswordByaccount(java.lang.String)
     */
    public String getPasswordByaccount(String account) {
        UserInfoVO infoVO = userCacheHandler.findUserByAccount(account, true);
        return infoVO.getPassword();
    }

}
