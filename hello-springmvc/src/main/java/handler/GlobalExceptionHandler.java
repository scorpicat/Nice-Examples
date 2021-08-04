package handler;

import exception.AgeException;
import exception.NameException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ControllerAdvice：控制器增强（给控制器加功能的，在这里是给控制器加异常处理功能）
 * 它需要springmvc配置文件中配置组件扫描器，指定位置为当前位置
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    //异常处理类可以定义方法来处理异常。方法的返回值类型和控制器中的方法一样可以有多种，如String ，void，ModelAndView等
    //方法上药声明注解@ExceptionHandler，value值为要处理的异常.class。如果没有指定value，则该方法为通用异常处理方法
    @ExceptionHandler(value = NameException.class)
    public ModelAndView doNameException(Exception exception){
        /*
        通常情况下，异常的处理方式为：
        1.把异常记录下来，记录到数据库、日志文件
            记录异常发生的时间 ，那个方法产生的，异常的内容
        2.发送通知，把异常的信息通过邮件等渠道发送出去
        3.给用户友好的提示
         */
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception",exception);
        mv.addObject("msg","名字不可以是三个字以上的！");
        mv.setViewName("defaultException");
        return mv;
    }
    @ExceptionHandler(value = AgeException.class)
    public ModelAndView doAgeException(Exception exception){
        /*
        通常情况下，异常的处理方式为：
        1.把异常记录下来，记录到数据库、日志文件
            记录异常发生的时间 ，那个方法产生的，异常的内容
        2.发送通知，把异常的信息通过邮件等渠道发送出去
        3.给用户友好的提示
         */
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception",exception);
        mv.addObject("msg","年龄不能大于200岁！");
        mv.setViewName("defaultException");
        return mv;
    }

    /**
     * 这是一个通用异常处理方法
     * @return
     */
    @ExceptionHandler
    public ModelAndView otherException(Exception exception){
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception",exception);
        mv.addObject("msg","默认异常");
        mv.setViewName("defaultException");
        return mv;
    }

}
