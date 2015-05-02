/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.usercenter.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.usercenter.dao.po.AccountDeveloperPO;
import com.usercenter.dao.vo.AccountDeveloperVO;

/**
 * 
 * 
 * @author wqi
 * @version $Id: AccountDeveloperDaoMapper.java, v 0.1 2015-2-26 下午5:29:34 wangq Exp $
 */
public interface AccountDeveloperDaoMapper {

    /**
     * 新增记录
     * 
     * @param configinfo
     * @return
     */
    public boolean insert(AccountDeveloperPO accountDeveloperPO);

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
    public boolean deleteByAccountname(String accountname);


    /**
     * 修过记录
     * 
     * @param AccountDeveloperVO
     * @return
     */
    public boolean update(@Param("accountdeveloper") AccountDeveloperPO accountDeveloper);

    /**
     * 根据account选择开发者对象
     * 
     * @param config_uuid
     */
    public AccountDeveloperPO selectByAccount(long account);


    /**
     * 根据account获取开发者信息
     * 
     * @param config_uuid
     */
    public AccountDeveloperVO getByAccount(long account);


    /**
     * 获得当前页码指定数量的消息业务信息
     * 
     * @param begin
     * @param size
     * @return
     */
    public List<AccountDeveloperVO> getList(@Param("begin") Integer begin, @Param("size") Integer size);

    /**
     * 获得所有数据总数
     * 
     * @return
     */
    public Integer getSize();


}
