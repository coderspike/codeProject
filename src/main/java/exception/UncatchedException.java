package exception;

/**
 * 未捕获异常
 */
public class UncatchedException extends RuntimeException {
    public UncatchedException(String msg) {
        super(msg);//业务异常
    }

    public UncatchedException(Throwable cause) {
        super(cause);
    }

    public UncatchedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UncatchedException() {
        super();
    }
}