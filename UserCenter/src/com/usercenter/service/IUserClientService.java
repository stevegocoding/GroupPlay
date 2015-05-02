/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.usercenter.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.core.HttpContext;

/**
 * 
 * @author wqi
 * @version $Id: IUserClientService.java, v 0.1 2015-3-14 下午4:11:48 wangq Exp $
 */
@Path("/client")
public interface IUserClientService {

    /**
     * create user  [Post /usercenter/client/createuser]
     *  创建用户
     * @param httpContext  
     *          entity: {"username":"xxx","appkey":"key1","nickname":"nickname","email":"xxxx","Phone":"xxxx","password":"xxxx", }
     * @return
     */
    @POST
    @Path("createuser")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response createUser(@Context HttpContext httpContexty);

    /**
     * update user  [Post /usercenter/client/updateuser]
     *         Header:token 
     *         Header:
     *         entity: {"username":"xxx","appkey":"key1","name":"newname","email":"xxxx","Phone":"xxxx","password":"xxxx", }
     *  批量创建用户 
     * @param httpContext
     * @return
     */
    @POST
    @Path("updateuser")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response updateUser(@Context HttpContext httpContexty);

    /**
     * update the user appliction nickname  [Post /usercenter/client/updatenickname]
     * 
     * @param username
     * @param appkey
     * @param appkey
     * @return
     */
    @POST
    @Path("updatenickname")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response updateNickName(@Context HttpContext httpContexty);

    /**
     * update the user appliction nickname  [Post /usercenter/client/updatepwd]
     * 
     * @param username
     * @param appkey
     * @param appkey
     * @return
     */
    @POST
    @Path("updatepwd")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response updatepwd(@Context HttpContext httpContexty);

    /**
     *               [Post /usercenter/client/load]
     * 
     * @param httpContexty
     * @return
     */
    @POST
    @Path("load")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response load(@Context HttpContext httpContexty);

    /**
     *               [Post /usercenter/client/checktoken]
     * 
     * @param httpContexty
     * @return
     */
    @POST
    @Path("checktoken")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response checktoken(@Context HttpContext httpContexty);

    /**
     *           [Post /usercenter/client/createuser]
     * 
     * @param username
     * @return
     */
    @GET
    @Path("isexistuser")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public String isExistUser(@QueryParam("username") String username);

}
