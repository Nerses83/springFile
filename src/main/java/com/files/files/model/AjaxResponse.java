package com.files.files.model;

/**
 * Created with IntelliJ IDEA.
 * User: Nerses
 * Date: 2/18/18.
 * Time: 12:52 AM.
 * To change this template use File | Settings | File Templates.
 */
public class AjaxResponse<T> {
    private String msg;
    private T body;

    public AjaxResponse(String msg, T body) {
        this.msg = msg;
        this.body = body;
    }

    public AjaxResponse() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
