package cn.bdqfork.bucket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bdqfork.bucket.handler.result.CommonResult;

/**
 * 统一异常捕获处理类
 * <p>
 * Created by bdq .
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 异常处理机制
     *
     * @param e Exception
     * @return CommonResult
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResult<Object> UnknowErrorHandler(Exception e) {
        CommonResult<Object> response = new CommonResult<>();
        response.setMessage(e.getMessage());
        response.setCode(500);
        response.setData(null);
        log.error(String.format("GlobalException Code：%s ,message:", 500), e);
        return response;
    }

}
