package com.mountainside.hydroppower.base.common.exception;

import java.io.Serializable;

/**
 * @Author : sxj
 * @Date : 2018/11/30 10:47
 * @Version : 1.0
 */
public class BusinessException extends RuntimeException implements Serializable {
    /**
     * serializable
     */
    private static final long serialVersionUID = 1L;

    public BusinessException(String errMsg) {
        super(errMsg);
    }
}
