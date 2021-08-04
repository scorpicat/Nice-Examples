package interception;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class MyInterception implements HandlerInterceptor {
    /**
     * 预处理方法
     * 特点：在controller的方法之前执行的
     * 在preHandle中，可以获取用户请求的数据，验证用户是否有权限访问某个链接地址等
     * 如果验证失败，可以截断请求，请求不被处理。
     * 如果验证成功，可以放行请求，请求被执行。
     * @param request
     * @param response
     * @param handler 被拦截的控制器对象，也就是controller包下的类
     * @return true:通过，false：拒绝
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterception--prehandle");
        //模拟登陆拦截：从session中获取用户信息，如果为空，则拦截
//        Object object = request.getSession().getAttribute("userinfo");
//        if(object != null){
//            return true;
//        }else{
//            return false;
//        }
        return true;
    }

    /**
     * 后处理方法
     * 特点：在处理器方法之后执行的
     * 能够获取到处理器方法返回的ModelAndView，可以修改返回值中的数据和视图，可以影响到最后的执行结果
     * 主要是用来对执行结果做修正
     * @param request
     * @param response
     * @param handler 被拦截的controller对象
     * @param modelAndView 控制器方法返回的ModelAndView
     * @throws Exception
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterception--postHandle");
        //可以对返回的ModelAndView进行加工
        if(modelAndView != null){
            modelAndView.addObject("date",new Date().getDate());
            modelAndView.addObject("msg","helloInteception");
        }
    }

    /**
     * 请求执行完成后执行的方法
     * 一般用来做资源回收工作
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterception--afterCompletion");
    }

}
