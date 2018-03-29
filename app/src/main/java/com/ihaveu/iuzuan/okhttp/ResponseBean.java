package com.ihaveu.iuzuan.okhttp;

import java.util.List;

/**
 * User: bkzhou
 * Date: 2018/3/29
 * Description:
 */
public class ResponseBean {

    /**
     * execResult : true
     * execMsg :
     * execData : {}
     * execDatas : []
     * execErrCode : 200
     */

    private boolean execResult;
    private String execMsg;
    private Object execData;
    private String execErrCode;
    private List<?> execDatas;

    public boolean isExecResult() {
        return execResult;
    }

    public void setExecResult(boolean execResult) {
        this.execResult = execResult;
    }

    public String getExecMsg() {
        return execMsg;
    }

    public void setExecMsg(String execMsg) {
        this.execMsg = execMsg;
    }

    public Object getExecData() {
        return execData;
    }

    public void setExecData(Object execData) {
        this.execData = execData;
    }

    public String getExecErrCode() {
        return execErrCode;
    }

    public void setExecErrCode(String execErrCode) {
        this.execErrCode = execErrCode;
    }

    public List<?> getExecDatas() {
        return execDatas;
    }

    public void setExecDatas(List<?> execDatas) {
        this.execDatas = execDatas;
    }

    public static class ExecDataBean {
    }
}
