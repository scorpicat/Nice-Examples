package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import exception.AgeException;
import exception.MyUserException;
import exception.NameException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import vo.Person;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")//表示该类只处理/user下的请求
public class MyController {
    @RequestMapping(value = "/some.do",method = RequestMethod.GET)//ReadMe.txt:5.4 指定请求的method属性
    //如果想要处理多个请求，参考：@RequestMapping(value = {"/some.do","/other.do"}) 即可。配置的多个请求，都会执行同一个方法，返回同一个视图
    public ModelAndView doSome(HttpServletRequest request, HttpServletResponse response){//注解中的请求名与方法名无关
//        ModelAndView mv = new ModelAndView("show.jsp");//这里是直接指定了视图，如果这里没有指定，则使用setViewName()指定
        ModelAndView mv = new ModelAndView();
//        mv.setViewName("/WEB-INF/view/show.jsp");//没有配置视图解析器：需要写视图的完整路径
        mv.setViewName("show");//在springmvc-config.xml中配置视图解析器后，就可以只写视图名称
        mv.addObject("color","Red");
        mv.addObject("name",request.getParameter("name"));//获取请求中的参数
        return mv;
    }
    //演示多个请求指向一个视图
    @RequestMapping(value = {"/first.do","/other.do"})
    public ModelAndView doOther(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("other");
        return mv;
    }
    @RequestMapping("/receive.do")
    public ModelAndView receiveParameterPage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("receiveRequest");
        return  mv;
    }
    //ReadMe.txt 5.5.①逐个接收参数起
    @RequestMapping(value = "/showReceive.do",method = RequestMethod.GET)
    public ModelAndView showReceiveParameter(@RequestParam(value = "rname",required =true) String name,@RequestParam(value = "rage",required =false) Integer age){//逐个接收请求中的参数，数据类型推荐使用封装类
        ModelAndView mv = new ModelAndView();
        mv.setViewName("showReceive");
        //为演示需要我把请求的参数又送回去
        mv.addObject("name",name);
        mv.addObject("age",age);
        return  mv;
    }

    /**
     *
     * @param name 使用@RequestParam注解来映射前端表格中的字段与controller方法中的参数
     *              required = true 表示这个参数不能为空
     * @param age
     * @return
     */
    @RequestMapping(value = "/showReceivePost.do",method = RequestMethod.POST)
    public ModelAndView showReceiveParameterPost(@RequestParam(value = "rname",required =true) String name, @RequestParam(value = "rage",required =false) Integer age){//逐个接收请求中的参数，数据类型推荐使用封装类
        ModelAndView mv = new ModelAndView();
        mv.setViewName("showReceive");
        mv.addObject("name",name);
        mv.addObject("age",age);
        return  mv;
    }
    //ReadMe.txt 5.5.①逐个接收参数止

    //ReadMe.txt 5.5.②对象接收参数起

    /**
     *
     * @param person Person对象中的属性必须要包含请求中的参数名，并且名称一致才能接收到，接收实际上是框架调用了对象的set方法赋值
     * @return
     */
    @RequestMapping(value = "/receiveParamObjectWay.do",method = RequestMethod.POST)
    public ModelAndView receiveParamObjectWay(Person person){
        System.out.println(person.getAge()+" "+person.getName());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("showReceive");
        mv.addObject("name",person.getName());
        mv.addObject("age",person.getAge());
        return mv;
    }

    //ReadMe.txt 5.5.②对象接收参数止

    //ReadMe.txt 5.6.② 返回值为String 起
    //跳转到控制器返回值类型示例专用页面
    @RequestMapping("returnValue.do")
    public String returnValuePage(){//也可以加入参
        return "returnValuePage";
    }

    @RequestMapping("toNextPage.do")
    public String toNextPage(){//也可以加入参数
        return "nextPage";
    }
    //ReadMe.txt 5.6.② 返回值为String 止

    //ReadMe.txt 5.6.③ 返回值类型为Void，响应ajax 起
    @RequestMapping("/return-ajax.do")
    public void doReturnVoidAjax(HttpServletResponse resp,String name,Integer age) throws IOException {
        Person p = new Person();
        p.setName(name);
        p.setAge(age);

        //把结果的对象转化为json格式
        String json = "";
        if(p != null){
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(p);
            System.out.println("Person转化为json:"+json);
        }

        //输出数据，响应ajax的请求
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.println(json);
        pw.flush();
        pw.close();
    }
    //ReadMe.txt 5.6.③ 返回值类型为Void，响应ajax 止

    //ReadMe.txt 5.6.④ 返回值类型为Object时，响应ajax 起
    @RequestMapping("/returnObjectJson.do")
    @ResponseBody
    public List<Person> doReturnVoidAjax(String msg){
        System.out.println("发过来的请求："+msg);
        List<Person> list = new ArrayList<Person>();
        Person p = new Person();
        Person p1 = new Person();
        p.setName("吴签");
        p.setAge(30);
        p1.setName("五千");
        p1.setAge(22);
        list.add(p);
        list.add(p1);
        //把结果的对象转化为json格式 已经通过<mvc:annotation-driven>被springmvc自动实现了
        //输出数据，响应ajax的请求 已经被springmvc框架的@ResponseBody注解自动实现了
        return list;
    }

    /**
     * 若返回值类型为String且有@ResponseBody注解的表示返回字符串数据，而不是视图。
     * 此时返回的数据会出现中文乱码，需要在@RequestMapping注解中配置produces = "text/plain;charset=utf-8"
     * @param name
     * @param age
     * @return
     */
    @RequestMapping(value = "/returnDataString.do",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String returnDataString(String name ,String age){

        return "这句话是当做数据响应的，而不是视图处理";
    }
    //ReadMe.txt 5.6.④ 返回值类型为Object时，响应ajax 止

    @RequestMapping("/forwardAndRedirect.do")
    public String toforwardAndRedirect(){
        return "forwardAndRedirect";
    }

    @RequestMapping("/aForwardBefore.do")
    public ModelAndView toFowardAfter(String name,String age){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("forward:/aForwardAfter.jsp");
        mv.addObject("name",name);
        mv.addObject("age",age);
        return mv;
    }
    @RequestMapping("/redirectA.do")
    public ModelAndView toRedirectB(String name,String age){
        ModelAndView mv = new ModelAndView();
        //在这里相当于给redirectB.jsp的请求带去新参数（也就是说重定向在不处理的情况下是不会把原页面的请求参数带过去的），但是不能直接访问到，在jsp中，要用${param}来访问
        mv.addObject("myname",name);
        mv.addObject("myage",age);
        mv.setViewName("redirect:/redirectB.jsp");
        return mv;
    }
    @RequestMapping("/ExceptionSolvePage.do")
    public String toExceptionSolvePage(){
        return "ExceptionSolvePage";
    }

    @RequestMapping("/doException.do")
    public ModelAndView doExceptionTest(Person p) throws MyUserException {
        if(p.getName().length()>3){
            throw new NameException("报了个姓名异常");
        }
        if(p.getAge()>200){
            throw new AgeException("报了个年龄异常");
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("showPersonInfo");
        mv.addObject("age",p.getAge());
        mv.addObject("name",p.getName());
        return mv;
    }

    //拦截器
    @RequestMapping("/InterceptionPage.do")
    public String toInterceptionPage(){
        return "interceptionPage";
    }
    @RequestMapping("/doInteception.do")
    public ModelAndView doInterception(){
        System.out.println("MyController--doInterception()方法");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("showInterception");
        mv.addObject("name","zhangsan");
        return mv;
    }
    //springmvc流程页面
    @RequestMapping("/springmvcProcessPage.do")
    public String tospringmvcProcessPage(){
        return "springmvcProcessPage";
    }

}
