package com.ihaveu.service;

import java.io.IOException;

/**
 * User: bkzhou
 * Date: 2018/3/26
 * Description:
 */
public abstract class   MoocResponse<T> {
    public abstract void sucess(MoocRequest request, T data) throws IOException;

    public abstract void fail(int errorcode, String errorMsg);
}
