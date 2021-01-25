package cn.bdqfork.bucket.handler.result;

/**
 * 响应码枚举类
 *
 * Created by bdq .
 */
public interface ResultCode {

    int SUCCESS = 200;

    /**
     * 认证信息异常
     */
    int AUTHORITY_EXCEPTION = 401;

    /**
     * 服务器运算出错
     */
    int OPERATION_EXCEPTION = 500;

}
