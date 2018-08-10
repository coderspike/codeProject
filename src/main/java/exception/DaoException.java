package exception;

/**
 * 数据库异常 Dao层异常
 */
public class DaoException extends UncatchedException {
    public DaoException(String msg) {
        super(msg);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DaoException() {
        super("数据库操作异常！");
    }
}