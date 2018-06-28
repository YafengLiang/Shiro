package com.voicecodes.domain.common;

/**
 * @Author: YafengLiang
 * @Description:
 * @Date: Created in  10:39 2018/6/20
 */
public class ServiceException extends RuntimeException {
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ServiceException(String message, int code) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message) {
        super(message);
    }
}
