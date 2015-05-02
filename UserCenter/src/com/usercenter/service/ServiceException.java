package com.usercenter.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.usercenter.core.util.StringUtil;
import com.usercenter.manage.ManageHandlerResult;

/**
 * 消息处理异常
 * 
 * @author wangq
 * @version $Id: CommonException.java, v 0.1 2014-11-11 下午3:23:13 wangq Exp $
 */
public class ServiceException extends WebApplicationException {
    public static Logger      logger           = Logger.getLogger(ServiceReturn.class);
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ServiceException() {

    }

    public ServiceException(String status, String error_code, String reason) {
        super(Response.ok().entity(createRetMsg(status, error_code, reason, null)).build());
    }

    public ServiceException(String status, String error_code, String reason, String bizReason) {
        super(Response.ok().entity(createRetMsg(status, error_code, reason, bizReason)).build());
    }

    public ServiceException(ManageHandlerResult result) {
        this(result.getError_code(), result.getStatus(), result.getReason(), result.getBizReason());
    }

    public static String createRetMsg(String status, String error_code, String reason,
                                      String bizReason) {
        Map<String, String> map = new HashMap<String, String>(4);
        map.put("status", status);
        map.put("error_code", error_code);
        map.put("reason", reason);
        if (StringUtil.isNotEmpty(bizReason)) {
            map.put("bizReason", bizReason);
        }
        String ret = JSONObject.fromObject(map).toString();
        logger.warn("调用异常：" + ret);
        return ret;
    }
}
