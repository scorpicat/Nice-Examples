package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;

@Controller
public class MyController {
    @RequestMapping(value = "/some.do")
    //如果想要处理多个请求，参考：@RequestMapping(value = {"/some.do","/other.do"}) 即可。配置的多个请求，都会执行同一个方法，返回同一个视图
    public ModelAndView doSome(){//注解中的请求名与方法名无关
//        ModelAndView mv = new ModelAndView("show.jsp");//这里是直接指定了视图，如果这里没有指定，则使用setViewName()指定
        ModelAndView mv = new ModelAndView();
//        mv.setViewName("/WEB-INF/view/show.jsp");//没有配置视图解析器：需要写视图的完整路径
        mv.setViewName("show");//在myweb-servlet.xml中配置视图解析器后，就可以只写视图名称
        mv.addObject("color","Red");
        return mv;
    }
    //演示多个请求指向一个视图
    @RequestMapping(value = {"/first.do","/second.do","/other.do"})
    public ModelAndView doOther(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("other");
        return mv;
    }

}
