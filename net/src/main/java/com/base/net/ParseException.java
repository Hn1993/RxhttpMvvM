package com.base.net;


import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import kotlinx.coroutines.TimeoutCancellationException;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import rxhttp.wrapper.annotations.NonNull;
import rxhttp.wrapper.annotations.Nullable;
import rxhttp.wrapper.exception.HttpStatusCodeException;

/**
 * User: ljx
 * Date: 2018/10/23
 * Time: 22:29
 *
 * @author an huang
 */
public class ParseException extends Exception {

    private final String errorCode;
    //请求方法，Get/Post等
    private final String requestMethod;
    //请求Url及查询参数
    private final HttpUrl httpUrl;
    //响应头
    private final Headers responseHeaders;

    public ParseException(@NonNull String code, String message, Response response) {
        super(message);
        errorCode = code;


        Request request = response.request();
        requestMethod = request.method();
        httpUrl = request.url();
        responseHeaders = response.headers();

        getErrorMsg(this);
    }


    private String getErrorMsg(Exception exception) {
        String s = "";
        //网络异常
        if (exception instanceof UnknownHostException) {
            s = "网络连接不可用，请稍后重试！";
        } else if (
                //okhttp全局设置超时
                exception instanceof SocketTimeoutException
                        //rxjava中的timeout方法超时
                        || exception instanceof TimeoutException
                        //协程超时
                        || exception instanceof TimeoutCancellationException
        ) {
            s = "连接超时,请稍后再试";
        } else if (exception instanceof ConnectException) {
            s = "网络不给力，请稍候重试！";
        } else if (exception instanceof HttpStatusCodeException) {
            //请求失败异常
            s = "Http状态码异常";
        } else if (exception instanceof JsonSyntaxException) {
            //请求成功，但Json语法异常,导致解析失败
            s = "数据解析失败,请检查数据是否正确";
        } else if (exception instanceof ParseException) {
            // ParseException异常表明请求成功，但是数据不正确
            // 根据 code 码来写错误提示
            codeToErrorMsg(Integer.parseInt(((ParseException) exception).errorCode));
        } else {
            s = exception.toString();
        }

        return s;
    }

    /**
     * 自定义服务器错误码
     * @param code
     * @return
     */
    private String codeToErrorMsg(int code){
        String s = "";
        switch (code){
            case 500:
                s = "服务器内部错误";
                break;
            default:
                break;
        }
        return s;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestUrl() {
        return httpUrl.toString();
    }

    public HttpUrl getHttpUrl() {
        return httpUrl;
    }

    public Headers getResponseHeaders() {
        return responseHeaders;
    }

    @Nullable
    @Override
    public String getLocalizedMessage() {
        return errorCode;
    }

    @Override
    public String toString() {
        return getClass().getName() + ":" +
                "\n" + requestMethod + " " + httpUrl +
                "\n\nCode=" + errorCode + " message=" + getMessage() +
                "\n" + responseHeaders;
    }
}
