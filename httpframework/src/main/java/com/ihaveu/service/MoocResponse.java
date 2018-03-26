package com.ihaveu.service;

/**
 * User: bkzhou
 * Date: 2018/3/26
 * Description:
 */
public abstract class   MoocResponse<T> {
    abstract void sucess(MoocRequest request,T data);

    abstract void fail(int errorcode ,String errorMsg);
}
