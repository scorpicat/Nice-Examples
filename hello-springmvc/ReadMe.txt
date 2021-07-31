实现步骤
1.新建web maven工程
2.加入依赖(参考pom.xml)
    spring-webmvc依赖，间接把spring依赖也加入到项目
    jsp,servlet依赖
3.重点：在web.xml中注册springmvc框架核心DispatchServlet
    1).DispatchServlet 叫做中央调控器，是一个servlet，他的父类继承HttpServlet
    2).DispatchServlet 也叫前端控制器（font controller）
    3).DispatchServlet 负责接受用户提交的请求，调用其他控制器对象，并把请求的处理结果显示给用户
    4).web.xml配置DispatchServlet时要指定Springmvc的配置文件
4.创建一个index.jsp
5.在Resource下创建对应的springmvc配置文件（文件名与步骤3的web.xml中的配置有关）
    1).配置组件扫描器，用于扫描Controller
    2).配置视图解析器
5.创建控制器类（controller包下）
    1).在类的上面加上@Controller注解
    2).在类的方法中加入@RequestMapping注解
6.创建一个作为结果的jsp（也就是视图），作为请求处理的结果
    1).在WEB-INF下创建视图，请阅读ReadMe2.txt

Final.配置好Project Structure中的Modules部分和Facets部分；配置tomcat