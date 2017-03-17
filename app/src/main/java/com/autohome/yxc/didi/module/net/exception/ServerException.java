package com.autohome.yxc.didi.module.net.exception;

/**
 * Description: ServerException
 * Creator: yxc
 * date: 2017/03/16 9:14
 */
public class ServerException extends RuntimeException {
    private int code;
    private String msg;

    public ServerException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
