package com.usercenter.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.usercenter.core.util.StringUtil;
import com.usercenter.manage.ManageHandlerResult;
import com.usercenter.util.CommonConstants;

/**
 * 调用返回
 * 
 * @author wangq
 * @version $Id: CommonException.java, v 0.1 2014-11-11 下午3:23:13 wangq Exp $
 */
public class ServiceReturn {
    public static Logger      logger           = Logger.getLogger(ServiceReturn.class);
    /**
     * 
     */
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 141345641231423113L;

    /**
     * 根据处理结果构造返回值
     * 
     * @param result
     * @return
     */
    public static Response build(ManageHandlerResult result) {

        if (result.isSuccess()) {
            String ret = createRetMsg(result);
            if (logger.isInfoEnabled()) {
                logger.info("调用成功：" + ret);
            }
            return Response.ok().entity(ret).build();
        }
        throw new ServiceException(result.getError_code(), result.getStatus(), result.getReason(), result.getBizReason());
    }

    /**
     * 构造返回结果体
     * 
     * @param entity
     * @return
     */
    public static Response entity(String entity) {

        return Response.ok().entity(entity).build();
    }

    /**
     * 璁剧疆杩斿洖缁撴灉
     * 
     * @param serverid
     * @return
     */
    private static String createRetMsg(ManageHandlerResult result) {

        Map<String, String> map = new HashMap<String, String>(2);
        map.put("status", CommonConstants.RET_SUCCESS);
        if (StringUtil.isNotEmpty(result.getBizReason())) {
            map.put("bizReason", result.getBizReason());
        }
        return JSONObject.fromObject(map).toString();

    }
}
