package com.usercenter.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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
 * 
 * @author wangq
 * @version $Id: IReceiveService.java, v 0.1 2014-11-13 下午4:44:23 wangq Exp $
 */
@Path("/manage")
public interface IUserManageService {

    /**
     * create user  [Post /usercenter/manage/createuser]
     * @param username
     * @param password
     * @param nickname
     * @param email
     * @param phone
     * @param developer
     * @param appkey
     * @return
     */
    @POST
    @Path("createuser")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes("application/x-www-form-urlencoded")
    public Response createUser(@FormParam("username") String username, @FormParam("password") String password, @FormParam("nickname") String nickname,
                               @FormParam("email") String email, @FormParam("phone") String phone, @FormParam("developer") boolean developer,
                               @FormParam("appkey") String appkey);

    /**
     * create users  [Post /usercenter/manage/createusers]
     * 
     *  批量创建用户 
     * @param httpContext
     * @return
     */
    @POST
    @Path("createusers")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response createUsers(@Context HttpContext httpContexty);

    /**
     * delete user by username  [Post /usercenter/manage/deletebynames]
     * 
     * @param username  user1;user2;user3
     * @param appkey
     * @return  成功：success 失败，返回成功个数，失败个数，失败的username
     */
    @POST
    @Path("deletebynames")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response deleteByNames(@FormParam("usernames") String usernames, @FormParam("appkey") String appkey);

    /**
     * delete user by account  [Post /usercenter/manage/deletebyaccount]
     * 
     * @param username
     * @param appkey
     * @return
     */
    @POST
    @Path("deletebyaccount")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response deleteByAcccount(@FormParam("account") String account, @FormParam("appkey") String appkey);

    /**
     * delete dev user by username  [Post /usercenter/manage/deletedevbyname]
     * 
     * @param username
     * @param appkey
     * @return
     */
    @POST
    @Path("deletedevbyname")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response deleteDevByName(@FormParam("username") String username);

    /**
     * update users  [Post /usercenter/manage/updateuser]
     *  {"username":"xxx","appkey":"key1","name":"newname","email":"xxxx","Phone":"xxxx","password":"xxxx", }
     *  批量创建用户 
     * @param httpContext
     * @return
     */
    @POST
    @Path("updateuser")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response updateUser(@Context HttpContext httpContexty);

    /**
     * update users  [Post /usercenter/manage/updateusers]
     * 
     *  批量创建用户 
     * @param httpContext
     * @return
     */
    @POST
    @Path("updateusers")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response updateUsers(@Context HttpContext httpContexty);

    /**
     * update the user appliction nickname  [Post /usercenter/manage/updatenickname]
     * 
     * @param username
     * @param appkey
     * @param appkey
     * @return
     */
    @POST
    @Path("updatenickname")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response updateNickName(@FormParam("username") String username, @FormParam("appkey") String appkey, @FormParam("nickname") String nickname);

    /**
     * update the user appliction nickname  [Post /usercenter/manage/updatepwd]
     * 
     * @param username
     * @param appkey
     * @param appkey
     * @return
     */
    @POST
    @Path("updatepwd")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response updatepwd(@FormParam("username") String username, @FormParam("newpwd") String newpwd, @FormParam("oldpwd") String oldpwd,
                              @FormParam("appkey") String appkey);

    /**
     * send text Msg for IM-AS [Post /usercenter/query/count]
     * 
     * @param appkey
     * @return
     */
    @GET
    @Path("count")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes("application/x-www-form-urlencoded")
    public String count(@QueryParam("appkey") String appkey);

    /**
     * [Post /usercenter/query/findUsers]
     * 
     * @param fields
     * @param query
     * @return
     */
    @GET
    @Path("findusers")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes("application/x-www-form-urlencoded")
    public Response findUsers(@Context HttpContext httpContexty);

    /**
     * [Post /usercenter/query/findUsers]
     * 
     * @param fields
     * @param query
     * @return
     */
    @GET
    @Path("finduserbyname")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes("application/x-www-form-urlencoded")
    public Response findByName(@QueryParam("username") String username);

    /**
     * [Post /usercenter/query/findUsers]
     * 
     * @param fields
     * @param query
     * @return
     */
    @GET
    @Path("finduserbyaccount")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes("application/x-www-form-urlencoded")
    public Response findByAccount(@QueryParam("account") String account);

    /**
     * 
     * 
     * @param username
     * @return
     */
    @GET
    @Path("getphone")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes("application/x-www-form-urlencoded")
    public String getphone(@QueryParam("username") String username);

    @GET
    @Path("isexistuser")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes("application/x-www-form-urlencoded")
    public String isExistUser(@QueryParam("username") String username);

    /**
     * TODO 再考虑 
     * 
     * @param username
     * @return
     */
    @GET
    @Path("getpassword")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes("application/x-www-form-urlencoded")
    public String getPassword(@QueryParam("username") String username);

    /**
     * TODO 再考虑 
     * 
     * @param username
     * @return
     */
    @POST
    @Path("load")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes("application/x-www-form-urlencoded")
    public Response load(@FormParam("username") String username, @FormParam("password") String password, @FormParam("opendeveloper") boolean opendeveloper);

}
