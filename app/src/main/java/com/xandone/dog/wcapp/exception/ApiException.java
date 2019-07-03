package com.xandone.dog.wcapp.exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;

import org.json.JSONException;

import java.io.NotSerializableException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * author: xandone
 * created on: 2019/3/29 14:08
 */

public class ApiException extends Exception {
    private int code;
    private String message;

    public ApiException(String msg) {
        super(msg);
    }

    public ApiException(String msg, int code) {
        super(msg);
        this.code = code;
    }

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
        this.message = throwable.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ApiException(httpException, httpException.code());
            ex.message = "数据加载失败";
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof JsonSerializer
                || e instanceof NotSerializableException
                || e instanceof ParseException) {
            ex = new ApiException(e, Error.PARSE_ERROR);
            ex.message = "数据解析出错";
            return ex;
        } else if (e instanceof ClassCastException) {
            ex = new ApiException(e, Error.CAST_ERROR);
            ex.message = "类型转换错误";
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ApiException(e, Error.NETWORD_ERROR);
            ex.message = "服务器连接失败";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ApiException(e, Error.SSL_ERROR);
            ex.message = "证书验证失败";
            return ex;
        } else if (e instanceof java.net.SocketTimeoutException) {
            ex = new ApiException(e, Error.TIMEOUT_ERROR);
            ex.message = "网络连接超时";
            return ex;
        } else if (e instanceof UnknownHostException) {
            ex = new ApiException(e, Error.UNKNOWNHOST_ERROR);
            ex.message = "无法解析该域名";
            return ex;
        } else if (e instanceof NullPointerException) {
            ex = new ApiException(e, Error.NULLPOINTER_EXCEPTION);
            ex.message = "NullPointerException";
            return ex;
        } else {
            ex = new ApiException(e, Error.UNKNOWN);
            ex.message = "未知错误：" + e.getMessage();
            return ex;
        }
    }

    public static class Error {
        /**
         * 未知错误
         */
        private static final int UNKNOWN = 2500;
        /**
         * 解析错误
         */
        private static final int PARSE_ERROR = UNKNOWN + 1;
        /**
         * 网络错误
         */
        private static final int NETWORD_ERROR = PARSE_ERROR + 1;
        /**
         * 协议出错
         */
        private static final int HTTP_ERROR = NETWORD_ERROR + 1;
        /**
         * 证书出错
         */
        private static final int SSL_ERROR = HTTP_ERROR + 1;
        /**
         * 连接超时
         */
        private static final int TIMEOUT_ERROR = SSL_ERROR + 1;
        /**
         * 调用错误
         */
        private static final int INVOKE_ERROR = TIMEOUT_ERROR + 1;
        /**
         * 类转换错误
         */
        private static final int CAST_ERROR = INVOKE_ERROR + 1;
        /**
         * 请求取消
         */
        private static final int REQUEST_CANCEL = CAST_ERROR + 1;
        /**
         * 未知主机
         */
        private static final int UNKNOWNHOST_ERROR = REQUEST_CANCEL + 1;
        /**
         * 空指针
         */
        private static final int NULLPOINTER_EXCEPTION = UNKNOWNHOST_ERROR + 1;
    }
}
