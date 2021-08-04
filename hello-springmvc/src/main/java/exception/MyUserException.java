package exception;

/**
 * 自定义异常类MyUserException
 *
 */
public class MyUserException extends Exception{
    public MyUserException(){
        super();
    }

    public  MyUserException(String message){
        super(message);
    }
}
