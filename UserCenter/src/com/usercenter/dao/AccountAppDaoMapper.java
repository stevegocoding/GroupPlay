/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.usercenter.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.usercenter.dao.po.AccountAppPO;

/**
 * 
 * 
 * @author wqi
 * @version $Id: AccountAppDaoMapper.java, v 0.1 2015-2-26 下午5:28:47 wangq Exp $
 */
public interface AccountAppDaoMapper {

    /**
     * 新增记录
     * 
     * @param configinfo
     * @return
     */
    public boolean insert(AccountAppPO accountapp);

    /**
     * 批量新增
     * 
     * @param accountInfoList
     * @return
     */
    public boolean insertBatch(List<AccountAppPO> accountAppList);

    /**
     * 删除记录
     * 
     * @param account
     * @return
     */
    public boolean deleteByAccount(long account);


    /**
     * 删除记录
     * 
     * @param account
     * @return
     */
    public boolean deleteByAppkey(String appkey);

    /**
     * 删除记录
     * 
     * @param account
     * @return
     */
    public boolean deleteByAccountAppkey(@Param("account") long account, @Param("appkey") String appkey);

    /**
     * 删除记录
     * 
     * @param account
     * @return
     */
    public boolean deleteByAccountnameAppkey(@Param("accountname") String accountname, @Param("appkey") String appkey);

    /**
     * 修改昵称
     * 
     * @param account
     * @param appkey
     * @param nickname
     * @return
     */
    public boolean updateNickByAccount(@Param("account") long account, @Param("appkey") String appkey, @Param("nickname") String nickname);

    /**
     * 修改昵称
     * 
     * @param account
     * @param appkey
     * @param nickname
     * @return
     */
    public boolean updateNickByName(@Param("accountname") String accountname, @Param("appkey") String appkey, @Param("nickname") String nickname);


    /**
     * 查询记录
     * 
     * @param config_uuid
     * @return
     */
    public AccountAppPO selectByAccountAppkey(@Param("account") long account, @Param("appkey") String appkey);

    /**
     * 根据account获取记录
     * 
     * @param config_uuid
     */
    public List<AccountAppPO> selectByAccount(@Param("account") long account);

    /**
     * 根据appkey获取记录
     * @param appkey
     * @param begin
     * @param size
     * @return
     */

    public List<AccountAppPO> getListByappkey(@Param("appkey") String appkey,
                                                       @Param("begin") Integer begin,
                                                       @Param("size") Integer size);

    /**
     * 获得当前页码指定数量的消息业务信息
     * 
     * @param begin
     * @param size
     * @return
     */
    public List<AccountAppPO> getList(@Param("begin") Integer begin, @Param("size") Integer size);

    /**
     * 获得所有数据总数
     * 
     * @return
     */
    public Integer getSize();

    /**
     * 获得所有数据总数
     * 
     * @return
     */
    public Integer getSizeByappkey(String appkey);

}
