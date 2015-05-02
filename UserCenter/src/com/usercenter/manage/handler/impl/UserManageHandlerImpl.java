/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.usercenter.manage.handler.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.usercenter.core.util.IdWorker;
import com.usercenter.core.util.StringUtil;
import com.usercenter.dao.AccountAppDaoMapper;
import com.usercenter.dao.AccountDeveloperDaoMapper;
import com.usercenter.dao.AccountInfoDaoMapper;
import com.usercenter.dao.po.AccountAppPO;
import com.usercenter.dao.po.AccountDeveloperPO;
import com.usercenter.dao.po.AccountInfoPO;
import com.usercenter.dao.vo.UserInfoVO;
import com.usercenter.dto.PoVoConvert;
import com.usercenter.dto.UserInofDto;
import com.usercenter.manage.ManageHandlerResult;
import com.usercenter.manage.handler.IUserCacheHandler;
import com.usercenter.manage.handler.IUserManageHandler;
import com.usercenter.manage.handler.IUserQueryHandler;
import com.usercenter.util.CommonConstants;

/**
 * 
 * @author wqi
 * @version $Id: UserManageHandlerImpl.java, v 0.1 2015-2-27 下午2:47:16 wangq Exp $
 */
public class UserManageHandlerImpl implements IUserManageHandler {

    public static Logger              logger = Logger.getLogger(UserManageHandlerImpl.class);

    @Autowired
    private AccountInfoDaoMapper      accountInfoDaoMapper;
    @Autowired
    private AccountAppDaoMapper       accountAppDaoMapper;
    @Autowired
    private AccountDeveloperDaoMapper accountDeveloperDaoMapper;
    @Autowired
    private IUserQueryHandler         userQueryHandler;

    @Autowired
    private IUserCacheHandler         userCacheHandler;

    /** 
     * @see com.usercenter.manage.handler.IUserManageHandler#createUser(com.usercenter.dto.UserInofDto)
     */
    public ManageHandlerResult createUser(UserInofDto userInfo) {

        // 考虑分布式锁的问题

        if (!userCacheHandler.lock(userInfo.getUsername())) {
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.USERNAME_REPETITION, "用户名重复");
        }

        userInfo = adjustUserInofDto(userInfo);
        AccountInfoPO accountInfoPO = PoVoConvert.UserInofDtoTOInfoPO(userInfo);

        try {
            boolean returnV = accountInfoDaoMapper.insert(accountInfoPO);

            if (!returnV) {
                return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.SYS_DB_ERROR, "插入新用户异常");
            }
            AccountAppPO appPo = null;
            AccountDeveloperPO devPo = null;
            List<AccountAppPO> appList = null;
            if (StringUtil.isNotBlank(userInfo.getAppkey())) {
                appPo = PoVoConvert.UserInofDtoTOAppPO(userInfo);
                accountAppDaoMapper.insert(appPo);
                appList = new ArrayList<AccountAppPO>();
                appList.add(appPo);
            }

            if (userInfo.isDeveloper()) {
                devPo = PoVoConvert.UserInofDtoTODevPO(userInfo);
                accountDeveloperDaoMapper.insert(devPo);
            }

            // 刷新 缓存
            UserInfoVO infoVO = new UserInfoVO(accountInfoPO, devPo, appList);
            userCacheHandler.refresh(infoVO);

        } catch (Exception e) {
            logger.error("插入新用户异常", e);
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.SYS_DB_ERROR, "插入新用户异常");
        } finally {
            if (userCacheHandler.unlock(userInfo.getUsername())) {

            }
        }

        return new ManageHandlerResult();
    }

    /** 
     * @see com.usercenter.manage.handler.IUserManageHandler#createUsers(java.util.List)
     */
    public ManageHandlerResult createUsers(List<UserInofDto> userInfoList, String appkey) {

        // #1 参数校验
        if (userInfoList == null || userInfoList.size() == 0) {
            return new ManageHandlerResult(false, "批量新增用户，待增加用户数为0");
        }

        // #2 数据封装
        List<AccountInfoPO> infoList = new ArrayList<AccountInfoPO>(userInfoList.size());
        List<AccountAppPO> appList = new ArrayList<AccountAppPO>();
        for (UserInofDto dto : userInfoList) {
            dto = adjustUserInofDto(dto);
            dto.setAppkey(appkey);

            infoList.add(PoVoConvert.UserInofDtoTOInfoPO(dto));
            appList.add(PoVoConvert.UserInofDtoTOAppPO(dto));
        }
        boolean ret = accountInfoDaoMapper.insertBatch(infoList);
        if (!ret) {
            return new ManageHandlerResult(false, "批量插入失败");
        }
        ret = accountAppDaoMapper.insertBatch(appList);
        return new ManageHandlerResult();
    }

    /**
     * 调整用户信息参数
     * 
     * @param userInfo
     * @return
     */
    private UserInofDto adjustUserInofDto(UserInofDto userInfo) {
        long account = IdWorker.nextId();
        userInfo.setAccount(account);
        userInfo.setCreatetime(new Date());
        userInfo.setLastmodifytime(new Date());
        if (StringUtil.isBlank(userInfo.getNickname())) {
            userInfo.setNickname(userInfo.getUsername());
        }
        // 密码加密 和  pin密码生成
        if (StringUtil.isBlank(userInfo.getPinpassword())) {
            userInfo.setPinpassword(Long.toString(IdWorker.nextId()));
        }
        return userInfo;
    }

    /** 
     * @see com.usercenter.manage.handler.IUserManageHandler#opendeveloper(java.lang.String, java.lang.String)
     */
    public ManageHandlerResult opendeveloper(String username, String password) {

        if (!userCacheHandler.lock(username)) {
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.USERNAME_REPETITION, "用户名重复");
        }
        try {
            // #1 参数校验
            // #2 获取用户信息
            UserInfoVO infoVO = userCacheHandler.findUserByName(username, true);
            // #3 密码校验

            if (password != null && password.equals(infoVO.getPassword())) {

            }
            // #4 检查是否已经存在
            if (infoVO.getAccountDev() != null) {
                return null;
            }
            // #5 封装对象
            AccountDeveloperPO dev = new AccountDeveloperPO();
            dev.setAccount(infoVO.getAccount());
            dev.setAuthgrade(0);
            dev.setCreatetime(new Date());
            dev.setLastmodifytime(new Date());
            dev.setDevstatus(0);

            accountDeveloperDaoMapper.insert(dev);
            infoVO.setAccountDev(dev);
            userCacheHandler.refresh(infoVO);

        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (userCacheHandler.unlock(username)) {

            }
        }

        return new ManageHandlerResult();
    }

    /** 
     * @see com.usercenter.manage.handler.IUserManageHandler#openApplication(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public ManageHandlerResult openApplication(String username, String password, String appkey, String nickname) {

        // 考虑分布式锁的问题

        if (!userCacheHandler.lock(username)) {
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.USERNAME_REPETITION, "用户名重复");
        }
        try {
            // #1 参数校验

            // #2 获取用户信息
            UserInfoVO infoVO = userCacheHandler.findUserByName(username, true);

            // #3 密码校验

            if (password != null && password.equals(infoVO.getPassword())) {

            }

            // #4 检查是否存在 

            // #5 封装
            AccountAppPO appPo = new AccountAppPO();

            appPo.setAccount(infoVO.getAccount());
            appPo.setAppkey(appkey);
            appPo.setNickname(nickname != null ? nickname : infoVO.getNickName());
            appPo.setCreatetime(new Date());
            appPo.setLastmodifytime(new Date());
            appPo.setStatus(1);
            accountAppDaoMapper.insert(appPo);

            // 刷新缓存
            infoVO.addAccountApp(appPo);
            userCacheHandler.refresh(infoVO);
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (userCacheHandler.unlock(username)) {

            }
        }

        return new ManageHandlerResult();
    }

    /** 
     * @see com.usercenter.manage.handler.IUserManageHandler#deleteByName(java.lang.String, java.lang.String)
     */
    public ManageHandlerResult deleteByNames(String usernames, String appkey) {

        // #1 参数校验

        // #2 获取用户信息
        String[] users = usernames.split(";");

        // TODO 当前用户和appkey一一对应，直接都删除
        try {
            for (String user : users) {
//                UserInfoVO info = userCacheHandler.findUserByName(user, false);
                
                if (StringUtil.isBlank(user)){
                    continue;
                }
                
                accountAppDaoMapper.deleteByAccountnameAppkey(user, appkey);
                accountDeveloperDaoMapper.deleteByAccountname(user);
                accountInfoDaoMapper.deleteByName(user);

                userCacheHandler.deleteUserByName(user);
            }
        } catch (Exception e) {
            logger.error("删除用户异常", e);
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.SYS_DB_ERROR, "删除异常");
        }
       

        return new ManageHandlerResult();
    }

    /** 
     * @see com.usercenter.manage.handler.IUserManageHandler#deleteByAcccount(java.lang.String, java.lang.String)
     */
    public ManageHandlerResult deleteByAcccount(String account, String appkey) {

        // #1 参数校验
        long accountLong = Long.parseLong(account);
        accountAppDaoMapper.deleteByAccountAppkey(accountLong, appkey);
        accountDeveloperDaoMapper.deleteByAccount(accountLong);
        accountInfoDaoMapper.deleteByAccount(accountLong);

        // 删除缓存
        userCacheHandler.deleteUserByAccount(account);
        return new ManageHandlerResult();
    }

    /** 
     * @see com.usercenter.manage.handler.IUserManageHandler#deleteDevByName(java.lang.String)
     */
    public ManageHandlerResult deleteDevByName(String username) {

        // #1 参数校验

        // #2 获取用户信息
        accountDeveloperDaoMapper.deleteByAccountname(username);

        UserInfoVO info = userCacheHandler.findUserByName(username, false);
        if (info != null){
            info.setAccountDev(null);
        }
        return new ManageHandlerResult();
    }

    /** 
     * @see com.usercenter.manage.handler.IUserManageHandler#updateUser(com.usercenter.dto.UserInofDto)
     */
    public ManageHandlerResult updateUser(UserInofDto userInfo) {
        AccountInfoPO accountInfoPO = PoVoConvert.UserInofDtoTOInfoPO(userInfo);
        accountInfoPO.setLastmodifytime(new Date());
        boolean ret = accountInfoDaoMapper.update(accountInfoPO);
        if (!ret) {

        }
        if (StringUtil.isNotBlank(userInfo.getAppkey()) && StringUtil.isNotBlank(userInfo.getNickname())) {
            accountAppDaoMapper.updateNickByName(userInfo.getUsername(), userInfo.getAppkey(), userInfo.getNickname());
        }
        userCacheHandler.refresh(userInfo.getUsername(), true);
        return new ManageHandlerResult();
    }

    /** 
     * @see com.usercenter.manage.handler.IUserManageHandler#updateUsers(java.util.List)
     */
    public ManageHandlerResult updateUsers(List<UserInofDto> userInfoList, String appkey) {

        // #2 数据封装
        List<AccountInfoPO> infoList = new ArrayList<AccountInfoPO>(userInfoList.size());
        List<AccountAppPO> appList = new ArrayList<AccountAppPO>();
        for (UserInofDto dto : userInfoList) {
            dto = adjustUserInofDto(dto);
            infoList.add(PoVoConvert.UserInofDtoTOInfoPO(dto));
            appList.add(PoVoConvert.UserInofDtoTOAppPO(dto));
        }
        boolean ret = accountInfoDaoMapper.batchUpdate(infoList);
        if (!ret) {
            return  new ManageHandlerResult(false, "批量修改失败");
        }
        // 刷新数据
        for (UserInofDto dto : userInfoList) {
            userCacheHandler.refresh(dto.getUsername(), false);
        }
        return new ManageHandlerResult();
    }

    /** 
     * @see com.usercenter.manage.handler.IUserManageHandler#updateNickName(java.lang.String, java.lang.String, java.lang.String)
     */
    public ManageHandlerResult updateNickName(String username, String appkey, String nickname) {
        // #1 参数校验

        // #2 获取用户信息
        String account = userCacheHandler.findAccountByName(username, true);

        if (account == null) {
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.USERNAME_ISNOTEXIST, "用户不存在");
        }

        accountAppDaoMapper.updateNickByName(username, appkey, nickname);
        userCacheHandler.refresh(username, true);
        return new ManageHandlerResult();
    }

    /**
     * 
     * @see com.usercenter.manage.handler.IUserManageHandler#updatepwd(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public ManageHandlerResult updatepwd(String username, String newpwd, String oldpwd, String appkey) {

        // #2 获取用户信息
        UserInfoVO infoVO = userCacheHandler.findUserByName(username, true);

        if (infoVO == null || !infoVO.containApp(appkey)) {
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.USERNAME_ISNOTEXIST, "用户不存在");
        }

        // #3 校验老密码
        if (oldpwd == null || !oldpwd.equals(infoVO.getPassword())) {
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.PASSWORD_ERROR, "用户不存在");
        }

        UserInofDto userDto = new UserInofDto();
        userDto.setAccount(infoVO.getAccount());
        userDto.setPassword(newpwd);
        userDto.setPinpassword(newpwd);

        return updateUser(userDto);
    }

    /**
     * 
     * @see com.usercenter.manage.handler.IUserManageHandler#load(java.lang.String, java.lang.String, boolean, java.lang.String)
     */
    public ManageHandlerResult load(String username, String password, boolean opendeveloper, String appkey, String phonecode) {

        // #1 参数校验

        // #2 获取用户信息
        UserInfoVO infoVO = userCacheHandler.findUserByName(username, true);
        if (infoVO == null) {
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.USERNAME_ISNOTEXIST, "用户不存在");
        }

        // #3 密码校验
        if (password != null && (password.equals(infoVO.getPassword()) || password.equals(infoVO.getPinpassword()))) {
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.PASSWORD_ERROR, "用户名或密码为空");
        }

        // 开发者登录
        if (opendeveloper) {
            if (infoVO.getAccountDev() == null) {
                opendeveloper(username, password);
            }
        }

        if (StringUtil.isNotBlank(appkey) && (!infoVO.containApp(appkey))) {
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.USERNAME_APP_ISNOTEXIST, "用户对应appkey不存在！");
        }

        if (!StringUtil.isBlank(phonecode)){
            String token = Long.toString(IdWorker.nextId());

            JSONObject json = new JSONObject();
            json.put("username", username);
            json.put("pinpwd", infoVO.getPinpassword());
            json.put("token", token);

            userCacheHandler.setToken(token, infoVO.getAccount() + "@_@" + username + "@_@" + phonecode + "@_@" + System.currentTimeMillis());
            new ManageHandlerResult(true, json.toString());
        }
       
        return new ManageHandlerResult(true);
    }

    /**
     * 
     * @see com.usercenter.manage.handler.IUserManageHandler#checkToken(java.lang.String, java.lang.String)
     */
    public ManageHandlerResult checkToken(String token, String phonecode) {
        String tokenValue = userCacheHandler.getToken(token);
        if (StringUtil.isBlank(tokenValue)) {
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.TOKEN_EMPTY, "token不存在");
        }

        String[] param = tokenValue.split("@_@");

        if (!phonecode.equals(param[2])) {
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.TOKEN_PHONECODE_ERROR, "phonecode不匹配");
        }

        long now = System.currentTimeMillis();
        long tokentime = Long.parseLong(param[3]);
        // 标准
        long refresh = 12 * 3600 * 1000;
        long valid = 2 * refresh;

        if (now - tokentime > valid) {
            return new ManageHandlerResult(CommonConstants.RET_ERROR, CommonConstants.TOKEN_DISABLED, "token失效");
        }
        if (now - tokentime > refresh) {
            // 刷新时间
            tokenValue = tokenValue.substring(0, tokenValue.lastIndexOf("@_@") + 3) + System.currentTimeMillis();
            userCacheHandler.setToken(token, tokenValue);
        }

        return null;
    }
}
