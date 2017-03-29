/**
 * Eya.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.eya.core.enums;

/**
 * 错误码枚举
 * @author Luolin
 * @version $Id: ErrorCode.java, v 0.1 Sep 9, 2014 5:02:10 PM Luolin Exp $
 */
public enum ErrorCode {
    /** 系统异常 */
    SYSTEM_ERROR("SYSTEM_ERROR", "系统异常"),
    /** 空参数异常 */
    NULL_ARGUMENT("NULL_ARGUMENT", "空参数异常"),
    /** 错误参数异常 */
    WRONG_PARAM("WRONG_PARAM", "错误参数异常");

    /** 错误编码 */
    private String code;
    /** 错误描述 */
    private String desc;

    private ErrorCode() {
    }

    /**
     * @param code 错误码
     * @param desc 错误描述
     */
    private ErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * Getter method for property <tt>code</tt>.
     * 
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter method for property <tt>code</tt>.
     * 
     * @param code value to be assigned to property code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter method for property <tt>desc</tt>.
     * 
     * @return property value of desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter method for property <tt>desc</tt>.
     * 
     * @param desc value to be assigned to property desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

}
