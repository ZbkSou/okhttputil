package com.ihaveu.service;

import com.ihaveu.service.convert.Convert;
import com.ihaveu.service.convert.JsonConvert;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
    public void sucess(MoocRequest request, String data) throws IOException {

        for(Convert convert : mConverts){

            if(convert.isCanParse(request.getContentType())){
                Object object =  convert.pares(data, getType());
                moocResponse.sucess(request,object);
            }
        }
        return;

    }

    public Type getType(){
        Type type = moocResponse.getClass().getGenericSuperclass();
       Type[] paramType =  ((ParameterizedType)type).getActualTypeArguments();
        return paramType[0];
    }

    @Override
    public void fail(int errorcode, String errorMsg) {
        moocResponse.fail(errorcode,errorMsg);
    }
}
