/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2014 All Rights Reserved.
 */
package com.usercenter.manage;

/**
 * 处理结果
 * @author wangqi
 * @version $Id: HandlerResult.java, v 0.1 2014-5-28 上午10:53:03 wangqi Exp $
 */
public class ManageHandlerResult {

    /** 是否成功     */
    private boolean isSuccess = true;

    /** 返回结果    */
    private Object  resultObj;

    /** 状态     */
    private String  status;

    /** 异常码    */
    private String  error_code;

    /** 异常描述    */
    private String  reason;

    /** 业务描述*/
    private String  bizReason;

    /**
     * 构造
     * @param resultObj
     */
    public ManageHandlerResult() {
    }

    /**
     * 构造
     * @param resultObj
     */
    public ManageHandlerResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * 构造
     * @param resultObj
     */
    public ManageHandlerResult(boolean isSuccess, String bizReason) {
        this.isSuccess = isSuccess;
        this.bizReason = bizReason;
    }

    /**
     * 构造
     * @param resultObj
     */
    public ManageHandlerResult(Object resultObj) {
        this.resultObj = resultObj;
    }

    public ManageHandlerResult(String status, String error_code, String reason, String bizReason) {
        this.isSuccess = false;
        this.status = status;
        this.error_code = error_code;
        this.reason = reason;
        this.bizReason = bizReason;
    }

    public ManageHandlerResult(String status, String error_code, String reason) {
        this.isSuccess = false;
        this.status = status;
        this.error_code = error_code;
        this.reason = reason;
    }

    /**
     * Getter method for property <tt>isSuccess</tt>.
     * 
     * @return property value of isSuccess
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * Setter method for property <tt>isSuccess</tt>.
     * 
     * @param isSuccess value to be assigned to property isSuccess
     */
    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * Getter method for property <tt>resultObj</tt>.
     * 
     * @return property value of resultObj
     */
    public Object getResultObj() {
        return resultObj;
    }

    /**
     * Setter method for property <tt>resultObj</tt>.
     * 
     * @param resultObj value to be assigned to property resultObj
     */
    public void setResultObj(Object resultObj) {
        this.resultObj = resultObj;
    }

    /**
     * Getter method for property <tt>status</tt>.
     * 
     * @return property value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter method for property <tt>status</tt>.
     * 
     * @param status value to be assigned to property status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter method for property <tt>error_code</tt>.
     * 
     * @return property value of error_code
     */
    public String getError_code() {
        return error_code;
    }

    /**
     * Setter method for property <tt>error_code</tt>.
     * 
     * @param error_code value to be assigned to property error_code
     */
    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    /**
     * Getter method for property <tt>reason</tt>.
     * 
     * @return property value of reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Setter method for property <tt>reason</tt>.
     * 
     * @param reason value to be assigned to property reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }


    public String getBizReason() {
        return bizReason;
    }

    public void setBizReason(String bizReason) {
        this.bizReason = bizReason;
    }

}
