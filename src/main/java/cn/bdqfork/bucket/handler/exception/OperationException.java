package cn.bdqfork.bucket.handler.exception;

import cn.bdqfork.bucket.handler.result.ResultCode;

/**
 * 服务器运算异常 code:500
 *
 * Created by bdq .
 */
public class OperationException extends FxException {

    private static final long serialVersionUID = 8889648842441664681L;

    public OperationException(String message){
        super(message, ResultCode.OPERATION_EXCEPTION);
    }
}
