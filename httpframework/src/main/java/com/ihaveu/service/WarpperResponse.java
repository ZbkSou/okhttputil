package com.ihaveu.service;

import com.ihaveu.service.convert.Convert;
import com.ihaveu.service.convert.JsonConvert;

import java.util.List;

/**
 * User: bkzhou
 * Date: 2018/3/26
 * Description:
 */
public class WarpperResponse extends MoocResponse<String>{
    private MoocResponse moocResponse;

    private  List<Convert> mConverts;
    public WarpperResponse(MoocResponse moocResponse,List<Convert> sConverts) {
        this.moocResponse = moocResponse;
        mConverts = sConverts;
    }

    @Override
    void sucess(MoocRequest request, String data) {

        moocResponse.sucess(request,data);
    }

    @Override
    void fail(int errorcode, String errorMsg) {
        moocResponse.fail(errorcode,errorMsg);
    }
}
