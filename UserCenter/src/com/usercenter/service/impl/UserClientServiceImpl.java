/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.usercenter.service.impl;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.jersey.api.core.HttpContext;
import com.usercenter.core.util.StringUtil;
import com.usercenter.dto.UserInofDto;
import com.usercenter.manage.ManageHandlerResult;
import com.usercenter.manage.handler.IUserManageHandler;
import com.usercenter.manage.handler.IUserQueryHandler;
import com.usercenter.service.IUserClientService;
import com.usercenter.service.IUserManageService;
import com.usercenter.service.ServiceException;
import com.usercenter.service.ServiceReturn;
import com.usercenter.util.CommonConstants;
import com.usercenter.util.ServiceUtil;

/**
 * 
 * @author wqi
 * @version $Id: UserClientServiceImpl.java, v 0.1 2015-3-14 下午4:45:30 wangq Exp $
 */
@Path("/client")
public class UserClientServiceImpl implements IUserClientService {

    public static Logger       logger = Logger.getLogger(UserClientServiceImpl.class);

    @Autowired
    private IUserManageService userManageService;

    @Autowired
    private IUserQueryHandler  userQueryHandler;

    @Autowired
    private IUserManageHandler userManageHandler;

    /** 
     * @see com.usercenter.service.IUserClientService#createUser(com.sun.jersey.api.core.HttpContext)
     */
    public Response createUser(HttpContext httpContexty) {
        String entity = httpContexty.getRequest().getEntity(String.class);
        UserInofDto dto = (UserInofDto) ServiceUtil.jsonStr2Object(entity, UserInofDto.class, null);

        return userManageService.createUser(dto.getUsername(), dto.getPassword(), dto.getNickname(), dto.getEmail(), dto.getPhone(), false, dto.getAppkey());

    }

    /** 
     * @see com.usercenter.service.IUserClientService#updateUser(com.sun.jersey.api.core.HttpContext)
     */
    public Response updateUser(HttpContext httpContexty) {
        token(httpContexty);
        return userManageService.updateUser(httpContexty);
    }

    /** 
     * @see com.usercenter.service.IUserClientService#updateNickName(com.sun.jersey.api.core.HttpContext)
     */
    public Response updateNickName(HttpContext httpContexty) {
        token(httpContexty);
        String entity = httpContexty.getRequest().getEntity(String.class);
        JSONObject json = null;
        try {
            json = new JSONObject(entity);
            String username = json.getString("username");
            String nickname = json.getString("nickname");
            String appkey = json.getString("appkey");
            return userManageService.updateNickName(username, appkey, nickname);
        } catch (JSONException e) {
            logger.error("", e);
        }
        return null;
    }

    /** 
     * @see com.usercenter.service.IUserClientService#updatepwd(com.sun.jersey.api.core.HttpContext)
     */
    public Response updatepwd(HttpContext httpContexty) {
        token(httpContexty);
        String entity = httpContexty.getRequest().getEntity(String.class);
        JSONObject json = null;
        try {
            json = new JSONObject(entity);
            String username = json.getString("username");
            String newpwd = json.getString("newpwd");
            String appkey = json.getString("appkey");
            String oldpwd = json.getString("oldpwd");
            return userManageService.updatepwd(username, newpwd, oldpwd, appkey);
        } catch (JSONException e) {
            logger.error("", e);
        }

        return null;
    }

    /** 
     * @see com.usercenter.service.IUserClientService#load(com.sun.jersey.api.core.HttpContext)
     */
    public Response load(HttpContext httpContexty) {
        String entity = httpContexty.getRequest().getEntity(String.class);
        String phonecode = httpContexty.getRequest().getHeaderValue("phonecode");
        String appkey = httpContexty.getRequest().getHeaderValue("appkey");
        JSONObject json = null;
        try {
            json = new JSONObject(entity);
            String username = json.getString("username");
            String password = json.getString("password");
            ManageHandlerResult result = userManageHandler.load(username, password, false, appkey, phonecode);

            return ServiceReturn.build(result);

        } catch (JSONException e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 
     * @see com.usercenter.service.IUserClientService#checktoken(com.sun.jersey.api.core.HttpContext)
     */
    public Response checktoken(HttpContext httpContexty) {
        token(httpContexty);
        return null;
    }

    /** 
     * @see com.usercenter.service.IUserClientService#isExistUser(java.lang.String)
     */
    public String isExistUser(String username) {
        if (StringUtil.isBlank(username)) {
            throw new ServiceException(CommonConstants.RET_ERROR, CommonConstants.PARAM_BAD_FORMAT, "用户名为空", "username=" + username);
        }
        boolean exist = userQueryHandler.isExistUser(username);
        return exist ? "true" : "false";
    }

    /**
     *  token校验
     * 
     * @param httpContexty
     * @return
     */
    public boolean token(HttpContext httpContexty) {
        String token = httpContexty.getRequest().getHeaderValue("token");
        String phonecode = httpContexty.getRequest().getHeaderValue("phonecode");

        return true;
    }
}
