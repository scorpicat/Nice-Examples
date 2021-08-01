实现步骤
1.新建web maven工程
2.加入核心依赖(参考pom.xml)
    spring-webmvc依赖，间接把spring依赖也加入到项目
    servlet依赖
3.重点：在web.xml中注册springmvc框架核心DispatchServlet
    1).DispatchServlet 叫做中央调控器，是一个servlet，他的父类继承HttpServlet
    2).DispatchServlet 也叫前端控制器（font controller）
    3).DispatchServlet 负责接受用户提交的请求，调用其他控制器对象，并把请求的处理结果显示给用户
    4).web.xml配置DispatchServlet时要指定Springmvc的配置文件
        在配置DispatchServlet的url-pattern是要注意的点：url-pattern有两种配置方式
            ①.使用*.xx 后缀类型配置，如 *.do。
            ②.直接使用“/”配置，表示所有/开头的请求都交给DisPatchServlet来处理，这会导致tomcat中的默认default servlet（在tomcat的web.xml中可以看到）失效，而这个默认servlet正是处理
            能够处理静态资源（即能访问），而DispatchServlet是不能够处理静态资源的，因为controller中没有对应的控制器去处理，所以会导致访问静态资源失效。
            要解决这个问题有两种方式：
                I.在springmvc配置文件中配置<mvc:default-servlet-handler/>，配置后springmvc框架会创建一个DefaultServletHpptRequestHandler对象，这个对象会把
                请求转发给tomcat的default这个servlet处理。同时还要配置<mvc:annotation-driven/>
                II.使用springmvc框架自己的处理器，在springmvc配置文件中配置如<mvc:resources mapping="/static/**" location="/static/"/>

4.创建一个index.jsp
5.创建控制器（controller包下）
    1).在类的上面加上@Controller注解
    2).在类的方法中加入@RequestMapping注解
    3).在类的上面加上@RequestMapping,如果有需要的话(如果加了，那么index.jsp中访问的路径要对应改变)
    4).指定方法的请求method属性（post、get等）
    5).控制器方法参数的接收
        ①逐个接收:见MyController下的showReceiveParameter()方法和doSome(HttpServletRequest request, HttpServletResponse response)方法
        ②对象接收：创建一个实体类，如vo包下的Person类，为他设置好属性，属性名需要与前端表格中的字段名对应。然后设置他的控制器处理器方法（MyController中的receiveParamObjectWay）
    6).控制器方法的返回值类型
        ①ModelAndView 即返回视图又返回数据（见5.5）
        ②String 返回值类型为String,且没有加@ResponseBody注解的，表示只返回视图（相当于forward跳转一个页面）（见MyController的toNextPage()）
        ③Void 只返回数据，Ajax、JSON (了解即可)
            配置jackson依赖。导入jQuery.js及编写ajax脚本（见returnValuePage.jsp的<head>标签中的script部分）。
            方法见doReturnVoidAjax()。
        ④Object 只返回数据，Object是对象，对象有属性，属性就是数据。控制器方法返回Object，可以转为Json格式，响应ajax的请求。
            加jackson依赖
            springmvc的配置文件加入<mvc:annotation-driven/> (http://www.springframework.org/schema/mvc")注解驱动。这个驱动的作用是将java对象转化为json、xml等数据格式。
            在处理器的方法上加上@ResponseBody注解。这个注解时springmvc将数据响应出去
            若返回值类型为String且有@ResponseBody注解的表示返回字符串数据，而不是视图。此时返回的数据会出现中文乱码，需要在@RequestMapping注解中配置produces = "text/plain;charset=utf-8"
6.在Resource下创建对应的springmvc配置文件（文件名与步骤3的web.xml中的配置有关）
    1).配置组件扫描器，用于扫描Controller
    2).配置视图解析器
7.创建需要的jsp（也就是视图），作为请求处理的结果
    1).在WEB-INF下创建视图，请阅读ReadMe2.txt
8.在web.xml中配置过滤器（解决post请求的乱码问题）
    1).get请求方式是没有中文乱码问题的，但是post请求方式会有

Finally.配置好Project Structure中的Modules部分和Facets部分；配置tomcat