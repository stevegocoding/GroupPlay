/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.usercenter.core.cache;

import javax.annotation.Resource;

import com.usercenter.dao.AccountInfoDaoMapper;
import com.usercenter.dao.po.AccountInfoPO;

/**
 * 
 * @author wangq
 * @version $Id: ConfigInfoCacheManage.java, v 0.1 2014-11-12 下午3:46:39 wangq Exp $
 */
public class AccountInfoCacheManage extends CacheManageBase {

    private AccountInfoDaoMapper accountInfoDaoMapper;

    @Resource
    public void setConfigInfoDaoMapper(AccountInfoDaoMapper accountInfoDaoMapper) {
        this.accountInfoDaoMapper = accountInfoDaoMapper;

    }

    /** 
     * @see com.msggw.manage.cache.CacheManageBase#uploadCache(java.lang.String, java.lang.String)
     */
    @Override
    public Object uploadCache(String cacheType, String cacheIndex) {

        AccountInfoPO infoPO = accountInfoDaoMapper.selectByAccountName(cacheIndex);
        if (infoPO == null) {
            return null;
        }
        return infoPO;
    }
}
