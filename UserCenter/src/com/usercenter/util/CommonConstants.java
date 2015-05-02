package com.usercenter.util;

public class CommonConstants {

    /**
     * Common constants
     */
    public static final String  RET_INFO                = "info";
    public static final String  RET_SUCCESS             = "success";
    public static final String  RET_ERROR               = "error";
    public static final String  RET_FAILED              = "failed";

    // System error
    public static final String  SUCCESS                 = "000000";
    // 100000  未知错误
    public static final String  SYS_ERROR_UNKNOWN       = "100000";
    // 100001  系统忙
    public static final String  SYS_BUSY                = "100001";
    // 100002  操作超时
    public static final String  SYS_TIME_OUT            = "100002";
    // 100003  网络异常
    public static final String  SYS_NETWORK_ERROR       = "100003";
    // 100004  数据库操作异常
    public static final String  SYS_DB_ERROR            = "100004";

    // Parameters error
    //  200000  未知错误
    public static final String  PARAM_ERROR_UNKNOWN     = "200000";
    //  200001  必选参数为空
    public static final String  PARAM_LACK              = "200001";
    //  200002  参数格式错误
    public static final String  PARAM_BAD_FORMAT        = "200002";
    //  200003  参数长度超出范围
    public static final String  PARAM_OVERLENGTH        = "200003";
    //  200004  所有输入参数都为空
    public static final String  PARAM_EMPTY             = "200004";
    //  200005  消息版本号非法
    public static final String  PARAM_BAD_MSGVERSION    = "200005";
    //  200008  消息名称错误
    public static final String  PARAM_BAD_MSGNAME       = "200008";
    //  200009  消息解析失败
    public static final String  PARAM_BAD_MSGFORMAT     = "200009";

    // Username error
    //  300000  已存在
    public static final String  USERNAME_ISEXIST        = "300000";
    //  300001  参数格式错误
    public static final String  USERNAME_BAD_FORMAT     = "300001";
    //  300002  用户名重复
    public static final String  USERNAME_REPETITION     = "300002";
    //  300003  参数长度超出范围
    public static final String  USERNAME_OVERLENGTH     = "300003";
    //  300004  用户名参数都为空
    public static final String  USERNAME_EMPTY          = "300004";
    //  300005  用户不存在
    public static final String  USERNAME_ISNOTEXIST     = "300005";
    //  300006  用户对应app不存在
    public static final String  USERNAME_APP_ISNOTEXIST = "300006";

    // passwored error
    //  400001  参数格式错误
    public static final String  PASSWORD_BAD_FORMAT     = "400001";
    //  400002  参数长度超出范围
    public static final String  PASSWORD_OVERLENGTH     = "400002";
    //  400003 密码为空
    public static final String  PASSWORD_EMPTY          = "400003";
    //  400004 密码错误
    public static final String  PASSWORD_ERROR          = "400004";

    // token error
    //  500001  参数格式错误
    public static final String  TOKEN_BAD_FORMAT        = "500001";
    //  500002  token失效
    public static final String  TOKEN_DISABLED          = "500002";
    //  500003 token参数都为空
    public static final String  TOKEN_EMPTY             = "500003";
    //  500003 token参数都为空
    public static final String  TOKEN_PHONECODE_EMPTY   = "500004";
    //  500003 token参数都为空
    public static final String  TOKEN_PHONECODE_ERROR   = "500005";

    /**
    * 通用分隔符
    */
    public static final String  LIST_SEPERATOR          = ";";

    public static final String  CONTENT_TYPE_XML        = "text/xml";

    public static final Integer MAX_RECEIVER_NUM        = 100;

}
