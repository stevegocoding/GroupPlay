/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.usercenter.dto;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.usercenter.core.util.StringUtil;
import com.usercenter.dao.po.AccountAppPO;
import com.usercenter.dao.po.AccountDeveloperPO;
import com.usercenter.dao.po.AccountInfoPO;
import com.usercenter.dao.vo.UserInfoVO;
import com.usercenter.manage.handler.impl.UserCacheHandlerImpl;

/**
 * 数据库Po对象到业务对象VO转换类
 * @author wangqi
 * @version $Id: PoVoConvert.java, v 0.1 2014-5-26 上午11:48:32 wangqi Exp $
 */
public class PoVoConvert {
    public static Logger              logger = Logger.getLogger(PoVoConvert.class);

    /**
     * 将Map数据转化为Object对象
     * 
     * @param fieldMap
     * @param object
     * @return
     */
    public static <T> T mapToObject(Map<String, String> fieldMap, T object) {

        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {

            field.setAccessible(true);

            try {
                if (fieldMap.get(field.getName()) != null) {

                    if (field.getType() == Integer.class)
                        field.set(object, Integer.valueOf(fieldMap.get(field.getName())));

                    if (field.getType() == Long.class)
                        field.set(object, Long.valueOf(fieldMap.get(field.getName())));

                    if (field.getType() == String.class)
                        field.set(object, fieldMap.get(field.getName()));

                    if (field.getType() == Date.class) {

                        field.set(object, StringUtil.strToDate(fieldMap.get(field.getName())));
                    }

                } else {
                    field.setAccessible(false);
                    continue;
                }
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            field.setAccessible(false);
        }
        return object;
    }


    /**
     * userInfo 和 accountInfoPO转换
     * 
     * @param userInfo
     * @return
     */
    public static AccountInfoPO UserInofDtoTOInfoPO(UserInofDto userInfo) {
        if (userInfo == null) {
            return null;
        }
        AccountInfoPO accountInfoPO = new AccountInfoPO();
        accountInfoPO.setAccount(userInfo.getAccount());
        accountInfoPO.setAccountname(userInfo.getUsername());
        accountInfoPO.setEmail(userInfo.getEmail());
        accountInfoPO.setPhone(userInfo.getPhone());
        accountInfoPO.setCreatetime(userInfo.getCreatetime());
        accountInfoPO.setLastmodifytime(userInfo.getLastmodifytime());
        accountInfoPO.setPassword(userInfo.getPassword());
        accountInfoPO.setPinpassword(userInfo.getPinpassword());
        accountInfoPO.setNickname(userInfo.getNickname());
        return accountInfoPO;
    }


    /**
     * userInfo 和 accountInfoPO转换
     * 
     * @param userInfo
     * @return
     */
    public static UserInfoVO UserInofPOTOInfoVO(AccountInfoPO userInfo) {
        if (userInfo == null) {
            return null;
        }
        UserInfoVO accountInfoVO = new UserInfoVO();
        accountInfoVO.setAccount(userInfo.getAccount());
        accountInfoVO.setAccountname(userInfo.getAccountname());
        accountInfoVO.setEmail(userInfo.getEmail());
        accountInfoVO.setPhone(userInfo.getPhone());
        accountInfoVO.setCreatetime(userInfo.getCreatetime());
        accountInfoVO.setLastmodifytime(userInfo.getLastmodifytime());
        accountInfoVO.setPassword(userInfo.getPassword());
        accountInfoVO.setPinpassword(userInfo.getPinpassword());
        accountInfoVO.setNickName(userInfo.getNickname());
        return accountInfoVO;
    }

    /**
     * userInfo 转换为AccountAppPO
     * 
     * @param userInfo
     * @return
     */
    public static AccountAppPO UserInofDtoTOAppPO(UserInofDto userInfo) {
        if (userInfo == null) {
            return null;
        }
        AccountAppPO appPo = new AccountAppPO();
        appPo.setAccount(userInfo.getAccount());
        appPo.setAppkey(userInfo.getAppkey());
        appPo.setNickname(userInfo.getNickname());
        appPo.setCreatetime(userInfo.getCreatetime());
        appPo.setLastmodifytime(userInfo.getLastmodifytime());
        return appPo;
    }

    /**
     * userInfo 转换为 AccountDeveloperPO
     * 
     * @param userInfo
     * @return
     */
    public static AccountDeveloperPO UserInofDtoTODevPO(UserInofDto userInfo) {
        if (userInfo == null) {
            return null;
        }
        AccountDeveloperPO appPo = new AccountDeveloperPO();
        appPo.setAccount(userInfo.getAccount());
    
        appPo.setCreatetime(userInfo.getCreatetime());
        appPo.setLastmodifytime(userInfo.getLastmodifytime());
        return appPo;
    }

    public static JSONObject userInfoVO2Json(UserInfoVO infoVO){
        
        JSONObject json = new JSONObject();
        try {
            json.put("account", infoVO.getAccount());
            json.put("username", infoVO.getAccountname());
            json.put("phone", infoVO.getPhone());
            json.put("nickName", infoVO.getNickName());
            json.put("email", infoVO.getEmail());
            json.put("createtime", StringUtil.dateToString(infoVO.getCreatetime()));
            json.put("lastmodifytime", StringUtil.dateToString(infoVO.getLastmodifytime()));
            json.put("isdev",infoVO.getAccountDev() == null ? "false" : "true");
            
            String appkeys = "";
            for (AccountAppPO app : infoVO.getAccountAppList()) {
                appkeys += app.getAppkey() + "|";
            }
            if (!StringUtil.isBlank(appkeys)){
                json.put("appkey", appkeys.substring(0, appkeys.length()-1));
            }
        } catch (JSONException e) {
            logger.error("对象转换异常：" ,e);
            return null;
        }
        return json;
    }
    //    /**
    //     * 数据库VO对象到业务DTO对象的转换
    //     * 
    //     * @param configInfoVo
    //     * @return
    //     */
    //
    //    public static ConfigInfoDto configInfoVo2DtoConvert(ConfigInfoVO configInfoVo) {
    //        if (configInfoVo == null) {
    //            return null;
    //        }
    //        ConfigInfoDto configInfoDto = new ConfigInfoDto();
    //        configInfoDto.setConfig_uuid(configInfoVo.getConfig_uuid());
    //        configInfoDto.setDirection(configInfoVo.getDirection());
    //        configInfoDto.setMsgbiztype(configInfoVo.getMsgbiztype());
    //        configInfoDto.setMsgbiztype_name(configInfoVo.getMsgbiztype_name());
    //        configInfoDto.setBusiness_plat(configInfoVo.getBusiness_plat());
    //        configInfoDto.setUpmsg_service(configInfoVo.getUpmsg_service());
    //        configInfoDto.setDownmsg_service(configInfoVo.getDownmsg_service());
    //        configInfoDto.setCallback_service(configInfoVo.getCallback_service());
    //        configInfoDto.setMax_receiver_num(configInfoVo.getMax_receiver_num());
    //        configInfoDto.setCreatetime(configInfoVo.getCreatetime());
    //        configInfoDto.setLastupdatetime(configInfoVo.getLastupdatetime());
    //
    //        return configInfoDto;
    //    }
    //
    //    /**
    //     * 业务DTO对象到数据库VO对象的转换
    //     * 
    //     * @param configInfoDto
    //     * @return
    //     */
    //    public static ConfigInfoVO configInfoDto2VoConvert(ConfigInfoDto configInfoDto) {
    //        if (configInfoDto == null) {
    //            return null;
    //        }
    //        ConfigInfoVO configInfoVo = new ConfigInfoVO();
    //        configInfoVo.setConfig_uuid(configInfoDto.getConfig_uuid());
    //        configInfoVo.setDirection(configInfoDto.getDirection());
    //        configInfoVo.setMsgbiztype(configInfoDto.getMsgbiztype());
    //        configInfoVo.setMsgbiztype_name(configInfoDto.getMsgbiztype_name());
    //        configInfoVo.setBusiness_plat(configInfoDto.getBusiness_plat());
    //        configInfoVo.setUpmsg_service(configInfoDto.getUpmsg_service());
    //        configInfoVo.setDownmsg_service(configInfoDto.getDownmsg_service());
    //        configInfoVo.setCallback_service(configInfoDto.getCallback_service());
    //        configInfoVo.setMax_receiver_num(configInfoDto.getMax_receiver_num());
    //        configInfoVo.setCreatetime(configInfoDto.getCreatetime());
    //        configInfoVo.setLastupdatetime(configInfoDto.getLastupdatetime());
    //
    //        return configInfoVo;
    //    }
}
