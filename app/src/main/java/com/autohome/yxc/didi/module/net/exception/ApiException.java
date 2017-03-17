package com.autohome.yxc.didi.module.net.exception;

/**
 * Description: 自定义异常类
 * Creator: yxc
 * date: 2017/03/16 9:14
 */
public class ApiException extends Exception {
    private int code;
    private String msg;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
        this.msg = "请求失败";
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
}
