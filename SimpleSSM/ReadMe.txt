SSM整合开发
SpringMVC：视图层，界面层，负责请求的接收，显示处理结果的
Spring：业务层，管理service,dao,工具类对象
MyBaits：持久层，访问数据库的

用户发起请求->SpringMVC接收->调用Spring中的Service对象-->访问MyBaits处理数据

SSM整合中有两个容器
1.SpringMVC容器，管理Controller控制器对象的
2.Spring容器，管理Service，dao，工具类对象的

我们要做的是把使用的对象交给合适的容器创建，管理。把Controller还有web开发的相关对象交给SpringMVC容器，这些web用的对象写在springmvc配置文件中

service，dao对象定义在spring配置文件中，让spring管理这些对象

springmvc容器是spring的子容器，类似java中的继承，子可以访问父的内容，所以springmvc中的Controller可以访问service对象

实现步骤：
0.在本项目中数据库选用mysql，库名springdb，表使用student
1.新建maven web项目
2.加入依赖
    springmvc，spring，mybaits三个框架的依赖，jackson依赖，mysql驱动，druid连接池，jsp，servlet依赖
3.写web.xml
    1).注册DispatchServlet，目的：①.创建springmvc容器对象，才能创建Controller类对象。
                                ②.创建的servlet，才能接收用户的请求
    2).注册spring监听器：ContextLoaderListenner，目的：创建spring的容器对象，才能创建service，dao等对象
    3).注册字符集过滤器，解决post请求乱码问题
4.创建包，Controller，service，dao，实体类包名等
5.写springmvc，spring，mybaits配置文件
    1).springmvc配置文件
    2).spring配置文件
    3).mybaits配置文件
    4).数据库的属性配置文件
6.写代码，dao接口和mapper文件，service和实现类，controller，实体类
7.写前端页面