package cn.bdqfork.bucket.handler.exception;

/**
 * 自定义异常类
 *
 * Created by bdq .
 */
public class FxException extends Exception {

    private static final long serialVersionUID = 3918707200183569313L;

    private int code;

    public FxException(String message) {
        super(message);
    }

    public FxException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
