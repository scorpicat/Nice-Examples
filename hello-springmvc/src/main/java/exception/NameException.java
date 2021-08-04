package exception;

/**
 * 处理name的自定义异常
 */
public class NameException extends MyUserException{
    public NameException() {
        super();
    }

    public NameException(String message) {
        super(message);
    }
}
