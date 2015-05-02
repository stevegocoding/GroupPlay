/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.usercenter.manage.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.usercenter.core.cache.RedisCacheManager;
import com.usercenter.core.util.StringUtil;
import com.usercenter.dao.AccountAppDaoMapper;
import com.usercenter.dao.AccountDeveloperDaoMapper;
import com.usercenter.dao.AccountInfoDaoMapper;
import com.usercenter.dao.po.AccountAppPO;
import com.usercenter.dao.po.AccountDeveloperPO;
import com.usercenter.dao.po.AccountInfoPO;
import com.usercenter.dao.vo.UserInfoVO;
import com.usercenter.manage.handler.IUserCacheHandler;

/**
 * 
 * @author wqi
 * @version $Id: UserCacheHandlerImpl.java, v 0.1 2015-3-20 下午5:04:19 wangq Exp $
 */
public class UserCacheHandlerImpl implements IUserCacheHandler {

    public static Logger              logger = Logger.getLogger(UserCacheHandlerImpl.class);

    public static String              USER_CACHE_NAME_ACCOUNT_KEY = "uc_cache_name2account_";
    public static String              USER_CACHE_ACCOUNT_USER_KEY = "uc_cache_account2user_";
    public static String              USER_CACHE_ACCOUNT_TOKEN_KEY = "uc_cache_token_account_";

    @Autowired
    private AccountInfoDaoMapper      accountInfoDaoMapper;
    @Autowired
    private AccountAppDaoMapper       accountAppDaoMapper;
    @Autowired
    private AccountDeveloperDaoMapper accountDeveloperDaoMapper;
    /** 
     * @see com.usercenter.manage.handler.IUserCacheHandler#refresh(com.usercenter.dao.vo.UserInfoVO)
     */
    public boolean refresh(UserInfoVO infoVO) {

        // 检查用户信息是否存在
        if (infoVO == null) {
            return false;
        }
        logger.info("用户缓存信息刷新：userName=" + infoVO.getAccountname());
        // 存储用户名称到account的缓存
        RedisCacheManager.getInstance().hset(USER_CACHE_NAME_ACCOUNT_KEY, infoVO.getAccountname(), Long.toString(infoVO.getAccount()));
        // 存储account到对象的缓存
        RedisCacheManager.getInstance().hsetobj(USER_CACHE_ACCOUNT_USER_KEY, Long.toString(infoVO.getAccount()), infoVO);
        return true;
    }

    /**
     * 
     * @see com.usercenter.manage.handler.IUserCacheHandler#refresh(java.lang.String)
     */
    public boolean refresh(String account) {
        if (StringUtil.isBlank(account)) {
            return false;
        }
        UserInfoVO vo = cacheLoad(account);
        return vo == null ? false : true;
    }

    /**
     * 
     * @see com.usercenter.manage.handler.IUserCacheHandler#refreshByName(java.lang.String)
     */
    public boolean refresh(String userName, boolean isload) {
        if (StringUtil.isBlank(userName)) {
            return false;
        }
        return refresh(findAccountByName(userName, isload));
    }

    /**
     * 加载缓存
     * 
     * @param account
     * @return
     */
    private UserInfoVO cacheLoad(String account) {

        long accountcode = Long.parseLong(account);
        AccountInfoPO infoPo = accountInfoDaoMapper.selectByAccount(accountcode);
        UserInfoVO infoVO = null;

        if (infoPo != null) {
            AccountDeveloperPO devPO = accountDeveloperDaoMapper.selectByAccount(accountcode);
            List<AccountAppPO> appList = accountAppDaoMapper.selectByAccount(accountcode);
            infoVO = new UserInfoVO(infoPo, devPO, appList);

            // 刷新缓存
            refresh(infoVO);
        }

        return infoVO;
    }
    /** 
     * @see com.usercenter.manage.handler.IUserCacheHandler#findUserByName(java.lang.String)
     */
    public UserInfoVO findUserByName(String username, boolean isload) {

        // 查看用户是否存在
        String account = findAccountByName(username, isload);
        return findUserByAccount(account, isload);
    }

    /**
     * 
     * @see com.usercenter.manage.handler.IUserCacheHandler#findAccountByName(java.lang.String, boolean)
     */
    public String findAccountByName(String username, boolean isload) {

        if (StringUtil.isBlank(username)) {
            return null;
        }

        String account = RedisCacheManager.getInstance().hget(USER_CACHE_NAME_ACCOUNT_KEY, username);
        // 不存在重新加载
        if (StringUtil.isNotBlank(account) && isload) {
            long at = accountInfoDaoMapper.selectByAccountbyName(username);
            if (at != 0) {
                account = Long.toString(at);
                RedisCacheManager.getInstance().hset(USER_CACHE_NAME_ACCOUNT_KEY, username, account);
            }
        }
        return account;
    }

    /** 
     * @see com.usercenter.manage.handler.IUserCacheHandler#findUserByAccount(java.lang.String)
     */
    public UserInfoVO findUserByAccount(String account, boolean isload) {
        if (StringUtil.isBlank(account)) {
            return null;
        }
        // 查询缓存 
        UserInfoVO infoVO = (UserInfoVO) RedisCacheManager.getInstance().hgetobj(USER_CACHE_ACCOUNT_USER_KEY, account);

        // 刷新缓存 
        if (infoVO == null && isload) {
            infoVO = cacheLoad(account);
        }

        return infoVO;
    }

    /**
     * 
     * @see com.usercenter.manage.handler.IUserCacheHandler#findUserByAccounts(java.lang.String[])
     */
    public ArrayList<UserInfoVO> findUserByAccounts(String[] accounts) {
        if (accounts == null) {
            return null;
        }
        ArrayList<UserInfoVO> userList = new ArrayList<UserInfoVO>();
        List<Object> list = RedisCacheManager.getInstance().hgetmobj(USER_CACHE_ACCOUNT_USER_KEY, accounts);
        for (Object object : list) {
            userList.add((UserInfoVO) object);
        }
        return userList;
    }

    /**
     * 
     * @see com.usercenter.manage.handler.IUserCacheHandler#deleteUserByName(java.lang.String)
     */
    public boolean deleteUserByName(String username) {
        // 查看用户是否存在
        String account = findAccountByName(username, false);
        return deleteUserByAccount(account);
    }

    /**
     * 
     * @see com.usercenter.manage.handler.IUserCacheHandler#deleteUserByAccount(java.lang.String)
     */
    public boolean deleteUserByAccount(String account) {

        if (StringUtil.isBlank(account)) {
            return true;
        }

        UserInfoVO vo = findUserByAccount(account, false);

        if (vo != null) {
            // 清除缓存。
            RedisCacheManager.getInstance().hdel(USER_CACHE_NAME_ACCOUNT_KEY, vo.getAccountname());
            RedisCacheManager.getInstance().hdelobj(USER_CACHE_ACCOUNT_USER_KEY, account);
            return true;
        }

        return false;
    }

    /**
     * 
     * @see com.usercenter.manage.handler.IUserCacheHandler#isExistUser(java.lang.String)
     */
    public boolean isExistUser(String username) {

        // 校验缓存中是否存在，
        String account = findAccountByName(username, false);
        return StringUtil.isNotBlank(account);
    }

    /**
     * 
     * @see com.usercenter.manage.handler.IUserCacheHandler#lock(java.lang.String)
     */
    public boolean lock(String username) {
        // 校验是否已经加锁
        synchronized (username) {

        }
        return true;
    }

    /**
     * 
     * @see com.usercenter.manage.handler.IUserCacheHandler#unlock(java.lang.String)
     */
    public boolean unlock(String username) {

        return true;
    }

    /**
     * 
     * @see com.usercenter.manage.handler.IUserCacheHandler#setToken(java.lang.String, java.lang.String)
     */
    public boolean setToken(String token, String value) {
        return RedisCacheManager.getInstance().hset(USER_CACHE_ACCOUNT_TOKEN_KEY, token, value);
    }

    /**
     * 
     * @see com.usercenter.manage.handler.IUserCacheHandler#getToken(java.lang.String)
     */
    public String getToken(String token) {
        return RedisCacheManager.getInstance().hget(USER_CACHE_ACCOUNT_TOKEN_KEY, token);
    }

    /**
     * 
     * @see com.usercenter.manage.handler.IUserCacheHandler#delToken(java.lang.String)
     */
    public boolean delToken(String token) {

        return RedisCacheManager.getInstance().hdel(USER_CACHE_ACCOUNT_TOKEN_KEY, token);
    }

}
