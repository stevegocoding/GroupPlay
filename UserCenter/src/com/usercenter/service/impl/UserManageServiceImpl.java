/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.usercenter.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.jersey.api.core.HttpContext;
import com.usercenter.core.util.StringUtil;
import com.usercenter.dto.UserInofDto;
import com.usercenter.dto.UserQueryDto;
import com.usercenter.manage.ManageHandlerResult;
import com.usercenter.manage.handler.IUserManageHandler;
import com.usercenter.manage.handler.IUserQueryHandler;
import com.usercenter.service.IUserManageService;
import com.usercenter.service.ServiceException;
import com.usercenter.service.ServiceReturn;
import com.usercenter.util.CommonConstants;
import com.usercenter.util.ServiceUtil;

/**
 * 
 * @author wangq
 * @version $Id: UserManageServiceImpl.java, v 0.1 2015-2-26 下午4:09:02 wangq Exp $
 */
@Path("/manage")
public class UserManageServiceImpl implements IUserManageService {

    public static Logger       logger = Logger.getLogger(UserManageServiceImpl.class);

    @Autowired
    private IUserManageHandler userManageHandler;

    @Autowired
    private IUserQueryHandler  userQueryHandler;

    /** 
     * @see com.usercenter.service.IUserManageService#createUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String)
     */
    public Response createUser(String username, String password, String nickname, String email, String phone, boolean developer, String appkey) {

        // #1 校验参数
        if (StringUtil.isBlank(username) || StringUtil.isBlank(password)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_BAD_FORMAT, "用户名或密码为空", "username=" + username + ";password="
                                                                                                                + password);
        }

        // #2 校验用户是否存在 
        if (userQueryHandler.isExistUser(username)) {
            throw new ServiceException(CommonConstants.RET_FAILED, CommonConstants.PARAM_BAD_FORMAT, "用户已存在 ");
        }

        // #3 封装对象
        UserInofDto userDto = new UserInofDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setNickname(nickname);
        userDto.setPhone(phone);
        userDto.setEmail(email);
        userDto.setDeveloper(developer);
        userDto.setAppkey(appkey);

        String errorCode = checkFormat(userDto);
        if (errorCode != null) {
            throw new ServiceException(CommonConstants.RET_ERROR, errorCode, "参数格式异常", userDto.paramString());
        }

        // #4 调用handler处理
        ManageHandlerResult result = userManageHandler.createUser(userDto);

        logger.info("新增成功：" + userDto.paramString());

        // #5 整理返回结果
        return ServiceReturn.build(result);
    }

    /** 
     * @see com.usercenter.service.IUserManageService#createUsers(com.sun.jersey.api.core.HttpContext)
     */
    public Response createUsers(HttpContext httpContexty) {
        String entity = httpContexty.getRequest().getEntity(String.class);
        String appkey = httpContexty.getRequest().getHeaderValue("appkey");
        if (StringUtil.isBlank(appkey)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_BAD_FORMAT, "appkey为空");
        }

        Object[] dtos = ServiceUtil.getDTOArray2(entity, UserInofDto.class, null);

        if (dtos == null || dtos.length == 0) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_BAD_FORMAT, "entity结构异常", entity);
        }

        //记录因为重复，而添加失败的
        JSONArray jsonArray = new JSONArray();

        // 校驗
        List<UserInofDto> dtoList = new ArrayList<UserInofDto>();
        HashSet<String> nameSet = new HashSet<String>();
        for (Object obj : dtos) {
            UserInofDto dto = (UserInofDto) obj;
            if (!checkBatchCreateParam(nameSet, jsonArray, dto)) {
                continue;
            }
            dtoList.add(dto);
        }

        ManageHandlerResult result = userManageHandler.createUsers(dtoList, appkey);

        JSONObject json = new JSONObject();
        json.put("successusers", dtoList.size());
        if (jsonArray.size() > 0) {
            json.put("errorusers", jsonArray);
        }
        return Response.ok().entity(json.toString()).build();
    }

    /** 
     * @see com.usercenter.service.IUserManageService#deleteByName(java.lang.String, java.lang.String)
     */
    public Response deleteByNames(String usernames, String appkey) {

        if (StringUtil.isBlank(appkey) || StringUtil.isBlank(usernames)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_BAD_FORMAT, "appkey/username为空", "username=" + usernames + ";appkey="
                                                                                                                         + appkey);
        }
        ManageHandlerResult result = userManageHandler.deleteByNames(usernames, appkey);
        return ServiceReturn.build(result);
    }

    /** 
     * @see com.usercenter.service.IUserManageService#deleteByAcccount(java.lang.String, java.lang.String)
     */
    public Response deleteByAcccount(String account, String appkey) {
        if (StringUtil.isBlank(appkey) || StringUtil.isBlank(account)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_BAD_FORMAT, "appkey/account为空", "account=" + account + ";appkey="
                                                                                                                        + appkey);
        }
        ManageHandlerResult result = userManageHandler.deleteByAcccount(account, appkey);
        return ServiceReturn.build(result);
    }

    /** 
     * @see com.usercenter.service.IUserManageService#deleteDevByName(java.lang.String)
     */
    public Response deleteDevByName(String username) {
        if (StringUtil.isBlank(username)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_BAD_FORMAT, "username为空", "username=" + username);
        }
        ManageHandlerResult result = userManageHandler.deleteDevByName(username);
        return ServiceReturn.build(result);

    }

    /** 
     * @see com.usercenter.service.IUserManageService#updateUser(com.sun.jersey.api.core.HttpContext)
     */
    public Response updateUser(HttpContext httpContexty) {
        String entity = httpContexty.getRequest().getEntity(String.class);
        UserInofDto userDto = (UserInofDto) ServiceUtil.jsonStr2Object(entity, UserInofDto.class, null);

        if (StringUtil.isBlank(userDto.getUsername())) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_BAD_FORMAT, "userName为空", "entity=" + entity);
        }

        String errorCode = checkFormat(userDto);
        if (errorCode != null) {
            throw new ServiceException(CommonConstants.RET_ERROR, errorCode, "参数格式异常", userDto.paramString());
        }

        ManageHandlerResult result = userManageHandler.updateUser(userDto);

        return ServiceReturn.build(result);
    }

    /** 
     * @see com.usercenter.service.IUserManageService#updateUsers(com.sun.jersey.api.core.HttpContext)
     */
    public Response updateUsers(HttpContext httpContexty) {
        String entity = httpContexty.getRequest().getEntity(String.class);
        // 校验appkey 是否存在
        String appkey = httpContexty.getRequest().getHeaderValue("appkey");
        if (StringUtil.isBlank(appkey)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_BAD_FORMAT, "appkey为空");
        }

        // 获取要修改的用户
        Object[] dtos = ServiceUtil.getDTOArray2(entity, UserInofDto.class, null);
        if (dtos == null || dtos.length == 0) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_BAD_FORMAT, "entity结构异常", entity);
        }

        // 记录异常情况
        JSONArray jsonArray = new JSONArray();

        // 校验
        List<UserInofDto> dtoList = new ArrayList<UserInofDto>(dtos.length);
        HashSet<String> nameSet = new HashSet<String>();
        for (Object obj : dtos) {
            UserInofDto dto = (UserInofDto) obj;
            if (!checkBatchUpdateParam(nameSet, jsonArray, dto)) {
                continue;
            }
            dtoList.add(dto);
        }

        ManageHandlerResult result = userManageHandler.updateUsers(dtoList, appkey);

        JSONObject json = new JSONObject();
        json.put("successusers", dtoList.size());
        if (jsonArray.size() > 0) {
            json.put("errorusers", jsonArray);
        }
        // 返回成功多少个

        return Response.ok().entity(json.toString()).build();
    }

    /** 
     * @see com.usercenter.service.IUserManageService#updateNickName(java.lang.String, java.lang.String, java.lang.String)
     */
    public Response updateNickName(String username, String appkey, String nickname) {
        if (StringUtil.isBlank(username) || StringUtil.isBlank(appkey) || StringUtil.isBlank(nickname)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_BAD_FORMAT, "参数为空", "username=" + username + "appkey=" + appkey
                                                                                                            + "nickname=" + nickname);
        }
        ManageHandlerResult result = userManageHandler.updateNickName(username, appkey, nickname);
        return ServiceReturn.build(result);
    }

    /**
     * 
     * @see com.usercenter.service.IUserManageService#updatepwd(java.lang.String, java.lang.String, java.lang.String)
     */
    public Response updatepwd(String username, String newpwd, String oldpwd, String appkey) {
        if (StringUtil.isBlank(username) || StringUtil.isBlank(appkey)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_BAD_FORMAT, "参数为空", "username=" + username + "appkey=" + appkey);
        }
        if (StringUtil.isBlank(oldpwd) || StringUtil.isBlank(newpwd)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_BAD_FORMAT, "密码不能为空", "username=" + username + "appkey=" + appkey);
        }

        ManageHandlerResult result = userManageHandler.updatepwd(username, newpwd, oldpwd, appkey);
        return ServiceReturn.build(result);
    }

    /**
     * 
     * @see com.usercenter.service.IUserManageService#count(java.lang.String)
     */
    public String count(String appkey) {
        return Integer.toString(userQueryHandler.countUser(appkey));
    }

    /**
     * 
     * @see com.usercenter.service.IUserManageService#findUsers(java.util.Set, java.lang.String)
     */
    public Response findUsers(@Context HttpContext httpContexty) {

        String entity = httpContexty.getRequest().getEntity(String.class);
        UserQueryDto queryDto = (UserQueryDto) ServiceUtil.jsonStr2Object(entity, UserQueryDto.class, null);

        if (!queryDto.check()) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_BAD_FORMAT, "参数异常", "entity=" + entity);
        }

        return null;
    }

    /**
     * 
     * @see com.usercenter.service.IUserManageService#findByName(java.lang.String)
     */
    public Response findByName(String username) {
        if (StringUtil.isBlank(username)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_BAD_FORMAT, "参数为空", "username=" + username);
        }
        ManageHandlerResult result = userQueryHandler.findUserByName(username);
        return Response.ok().entity(result.getResultObj()).build();
    }

    /**
     * 
     * @see com.usercenter.service.IUserManageService#findByAccount(java.lang.String)
     */
    public Response findByAccount(String account) {
        if (StringUtil.isBlank(account)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_BAD_FORMAT, "参数为空", "account=" + account);
        }
        ManageHandlerResult result = userQueryHandler.findUserByAccount(account);
        return Response.ok().entity(result.getResultObj()).build();
    }

    /**
     * 
     * @see com.usercenter.service.IUserManageService#getphone(java.lang.String)
     */
    public String getphone(String username) {
        if (StringUtil.isBlank(username)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_BAD_FORMAT, "username为空");
        }
        return userQueryHandler.getphoneByName(username);
    }

    /**
     * 
     * @see com.usercenter.service.IUserManageService#isExistUser(java.lang.String)
     */
    public String isExistUser(String username) {

        if (StringUtil.isBlank(username)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_BAD_FORMAT, "username为空");
        }
        return userQueryHandler.isExistUser(username) ? "true" : "false";
    }

    /**
     * TODO 待定
     * @see com.usercenter.service.IUserManageService#getPassword(java.lang.String)
     */
    public String getPassword(String username) {
        if (StringUtil.isBlank(username)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_BAD_FORMAT, "username为空");
        }

        String pwd = userQueryHandler.getPasswordByaccount(username);
        return pwd;
    }

    /**
     * 
     * @see com.usercenter.service.IUserManageService#load(java.lang.String, java.lang.String)
     */
    public Response load(String username, String password, boolean opendeveloper) {
        if (StringUtil.isBlank(username) || StringUtil.isBlank(password)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_BAD_FORMAT, "username为空");
        }
        ManageHandlerResult result = userManageHandler.load(username, password, opendeveloper, null, null);
        return ServiceReturn.build(result);
    }

    /**
     * 检查批量新增的参数
     * 
     * @param nameSet
     * @param jsonArray
     * @param userDto
     * @return
     */
    private boolean checkBatchCreateParam(HashSet<String> nameSet, JSONArray jsonArray, UserInofDto userDto) {

        String userName = userDto.getUsername();

        JSONObject json = ServiceUtil.object2Json(userDto);

        // 参数校验
        if (StringUtil.isBlank(userDto.getPassword()) || StringUtil.isBlank(userName)) {
            json.put("error", CommonConstants.PARAM_BAD_FORMAT);
            jsonArray.add(json);
            return false;
        }

        //批处理添加，排除添加列表中重复用户名
        if (nameSet.contains(userName)) {
            json.put("error", CommonConstants.USERNAME_REPETITION);
            jsonArray.add(json);
            return false;
        }
        nameSet.add(userName);

        if (userQueryHandler.isExistUser(userName)) {
            json.put("error", CommonConstants.USERNAME_ISEXIST);
            jsonArray.add(json);
            return false;
        }

        String errorCode = checkFormat(userDto);
        if (errorCode != null) {
            json.put("error", errorCode);
            jsonArray.add(json);
            return false;
        }
        return true;
    }

    /**
     * 检查批量修改的参数
     * 
     * @param nameSet
     * @param jsonArray
     * @param userDto
     * @return
     */
    private boolean checkBatchUpdateParam(HashSet<String> nameSet, JSONArray jsonArray, UserInofDto userDto) {

        String userName = userDto.getUsername();

        JSONObject json = ServiceUtil.object2Json(userDto);

        // 参数校验
        if (StringUtil.isBlank(userName)) {
            json.put("error", CommonConstants.USERNAME_EMPTY);
            jsonArray.add(json);
            return false;
        }

        //批处理添加，排除添加列表中重复用户名
        if (nameSet.contains(userName)) {
            json.put("error", CommonConstants.USERNAME_REPETITION);
            jsonArray.add(json);
            return false;
        }
        nameSet.add(userName);

        if (!userQueryHandler.isExistUser(userName)) {
            json.put("error", CommonConstants.USERNAME_ISNOTEXIST);
            jsonArray.add(json);
            return false;
        }

        String errorCode = checkFormat(userDto);
        if (errorCode != null) {
            json.put("error", errorCode);
            jsonArray.add(json);
            return false;
        }
        return true;
    }

    /**
     * 校验格式
     * 
     * @param userDto
     * @return
     */
    private String checkFormat(UserInofDto userDto) {
        if (userDto.getUsername() != null) {
            if (!StringUtil.checkName(userDto.getUsername())) {
                return CommonConstants.USERNAME_BAD_FORMAT;
            }
            if (userDto.getUsername().length() > 50) {
                return CommonConstants.USERNAME_OVERLENGTH;
            }
        }

        if (userDto.getPassword() != null) {
            if (!StringUtil.checkPwd(userDto.getPassword())) {
                return CommonConstants.PASSWORD_BAD_FORMAT;
            }

            if (userDto.getPassword().length() > 32 || userDto.getPassword().length() < 6) {
                return CommonConstants.PASSWORD_OVERLENGTH;
            }
        }

        if (StringUtil.isNotBlank(userDto.getEmail()) && !StringUtil.checkEmail(userDto.getEmail())) {
            return CommonConstants.PARAM_BAD_FORMAT;
        }
        return null;
    }

}
