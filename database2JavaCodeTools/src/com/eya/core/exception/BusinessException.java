/**
 * Eya.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.eya.core.exception;

import com.eya.core.enums.ErrorCode;


/**
 * 自定义异常
 * @author Luolin
 * @version $Id: PacsBusinessException.java, v 0.1 Sep 9, 2014 5:01:18 PM Luolin Exp $
 */
public class BusinessException extends RuntimeException {

    /** 序列号 */
    private static final long serialVersionUID = -1003683355305462875L;
    /** 错误码 */
    private ErrorCode         errorCode;

    /**
     * 构造方法
     * @param message 错误消息
     * @param errorCode 错误码
     */
    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * 构造方法
     * @param message 错误消息
     * @param errorCode 错误码
     * @param ex {@link Throwable}
     */
    public BusinessException(ErrorCode errorCode, String message, Throwable ex) {
        super(message, ex);
        this.errorCode = errorCode;
    }

    /**
     * Getter method for property <tt>errorCode</tt>.
     * 
     * @return property value of errorCode
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    /**
     * Setter method for property <tt>errorCode</tt>.
     * 
     * @param errorCode value to be assigned to property errorCode
     */
    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
