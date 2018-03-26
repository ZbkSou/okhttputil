package com.ihaveu.http;

/**
 * User: bkzhou
 * Date: 2018/3/14
 * Description:定义请求状态
 */
public enum HttpStatus {

    /**
     * https://tools.ietf.org/html/rfc7231
     * Informational 1xx .........................................50
     * 6.2.1. 100 Continue .......................................50
     * 6.2.2. 101 Switching Protocols ............................50
     */
    Continue(100,"Continue"),
    SWITCHING_PROTOCOLS(101,"Switching Protocols"),

    /**
     *  6.3. Successful 2xx ............................................51
     6.3.1. 200 OK .............................................51
     6.3.2. 201 Created ........................................52
     6.3.3. 202 Accepted .......................................52
     6.3.4. 203 Non-Authoritative Information ..................52
     6.3.5. 204 No Content .....................................53
     6.3.6. 205 Reset Content ..................................53
     */
    OK(200,"OK"),
    Created(201,"Created"),
    Accepted(202,"Accepted"),
    NON_AUTHORITATIVE_INFORMATION(203,"Non-Authoritative Information"),
    No_Content(204,"No Content"),
    Reset_Content(205,"CReset Contentontinue"),
    /**
     *  6.4. Redirection 3xx ...........................................54
     6.4.1. 300 Multiple Choices ...............................55
     6.4.2. 301 Moved Permanently ..............................56
     6.4.3. 302 Found ..........................................56
     6.4.4. 303 See Other ......................................57
     6.4.5. 305 Use Proxy ......................................58
     6.4.6. 306 (Unused) .......................................58
     6.4.7. 307 Temporary Redirect .............................58
     */
    Multiple_Choices(300,"Multiple Choices"),
    Moved_Permanently(301,"Moved Permanently"),
    Found(302,"Found"),
    See_Other(303,"See Other"),
    Use_Proxy(305,"Use Proxy"),
    Unused(306,"(Unused)"),
    Temporary_Redirect(307,"Temporary Redirect"),
    /**
     * Client Error 4xx ..........................................58
     6.5.1. 400 Bad Request ....................................58
     6.5.2. 402 Payment Required ...............................59
     6.5.3. 403 Forbidden ......................................59
     6.5.4. 404 Not Found ......................................59
     6.5.5. 405 Method Not Allowed .............................59
     6.5.6. 406 Not Acceptable .................................60
     6.5.7. 408 Request Timeout ................................60
     6.5.8. 409 Conflict .......................................60
     6.5.9. 410 Gone ...........................................60
     6.5.10. 411 Length Required ...............................61
     6.5.11. 413 Payload Too Large .............................61
     6.5.12. 414 URI Too Long ..................................61
     6.5.13. 415 Unsupported Media Type ........................62
     6.5.14. 417 Expectation Failed ............................62
     6.5.15. 426 Upgrade Required ..............................62
     */
    Bad_Request(400,"Bad Request"),
    Payment_Required(402,"Payment Required"),
    Forbidden(403,"Forbidden"),
    Not_Found(404,"Not Found"),
    Method_Not_Allowed(405,"Method Not Allowed"),
    Not_Acceptable(406,"Not Acceptable"),
    Request_Timeout(408,"Request Timeout"),
    Conflict(409,"Conflict"),
    Gone(410,"Gone"),
    Length_Required(411,"Length Required"),
    Payload_Too_Large(413,"Payload Too Large"),
    URI_Too_Long(414,"URI Too Long"),
    Unsupported_Media_Type(415,"Unsupported Media Type"),
    Expectation_Failed(417,"Expectation Failed"),
    Upgrade_Required(426,"Upgrade Required"),
    /**
     *6.6. Server Error 5xx ..........................................62
     * 6.6.1. 500 Internal Server Error ..........................63
     6.6.2. 501 Not Implemented ................................63
     6.6.3. 502 Bad Gateway ....................................63
     6.6.4. 503 Service Unavailable ............................63
     6.6.5. 504 Gateway Timeout ................................63
     6.6.6. 505 HTTP Version Not Supported .....................64
     */
    Internal_Server_Error(500,"Internal Server Error"),
    Not_Implemented(501,"Not Implemented"),
    Bad_Gateway(502,"Bad Gateway"),
    Service_Unavailable(503,"Service Unavailable"),
    Gateway_Timeout(504,"Gateway Timeout"),
    HTTP_Version_Not_Supported(505,"HTTP Version Not Supported");

    private int status;
    private String mMessage;
    private HttpStatus(int code,String message){
        status = code;
        mMessage = message;
    }

    /**
     * 返回状态码对用 message
     * @param value  200
     * @return   HttpStatus
     */
    public static HttpStatus getValue(int value){
        for(HttpStatus httpStatus:values()){
            if(value==httpStatus.status){
                return httpStatus;
            }
        }
        return null;
    }

    /**
     * 判断是否请求成功
     * @return
     */
    public boolean isSuccess(){
        int value = status/100;
        if(value==2){
            return true;
        }
        return false;
    }
}
