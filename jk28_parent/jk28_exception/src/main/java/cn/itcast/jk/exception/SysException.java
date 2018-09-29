package cn.itcast.jk.exception;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/25
 * @description:
 */
public class SysException extends Exception {

    private String message;

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public SysException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
