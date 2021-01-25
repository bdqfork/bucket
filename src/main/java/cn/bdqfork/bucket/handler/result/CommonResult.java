package cn.bdqfork.bucket.handler.result;

/**
 * CommonResult 返回结果处理
 * 
 * @version 2021/1/25
 */
public class CommonResult<T> {

    /**
     * 检索正确状态码
     */
    private int code = ResultCode.SUCCESS;

    /**
     * 状态描述
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public CommonResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private CommonResult(T data) {
        this.data = data;
    }

    /**
     * 空方法构造
     */
    public CommonResult() {
    }

    /**
     * 构建返回值方法
     *
     * @param data 数据
     * @return CommonResult
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(data);
    }

    /**
     * 构建返回值方法
     *
     * @return CommonResult
     */
    public static <T> CommonResult<T> success() {
        return new CommonResult<>();
    }

    /**
     * 构建返回值方法
     * 
     * @param code    状态码
     * @param message 描述
     * @return CommonResult
     */
    public static <T> CommonResult<T> error(int code, String message) {
        return new CommonResult<>(code, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
