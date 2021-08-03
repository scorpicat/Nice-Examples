<%--
  Created by IntelliJ IDEA.
  User: scorpicat
  Date: 2021/7/31
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>helloPage</title>
</head>
<body>
<h2>springmvc</h2>
<p>整理自<a href="https://www.bilibili.com/video/BV1sk4y167pD?p=40&spm_id_from=pageDriver">点击跳转视频页面</a></p>
<p>请阅读项目中的ReadMe.txt</p>
1.新建web maven工程<br/>
2.加入核心依赖(参考pom.xml)<br/>
&nbsp;&nbsp;spring-webmvc依赖，间接把spring依赖也加入到项目<br/>
&nbsp;&nbsp;servlet依赖<br/>
3.重点：在web.xml中注册springmvc框架核心DispatchServlet<br/>
&nbsp;&nbsp;1).DispatchServlet 叫做中央调控器，是一个servlet，他的父类继承HttpServlet<br/>
&nbsp;&nbsp;2).DispatchServlet 也叫前端控制器（font controller）<br/>
&nbsp;&nbsp;3).DispatchServlet 负责接受用户提交的请求，调用其他控制器对象，并把请求的处理结果显示给用户<br/>
&nbsp;&nbsp;4).web.xml配置DispatchServlet时要指定Springmvc的配置文件<br/>
&nbsp;&nbsp;在配置DispatchServlet的url-pattern是要注意的点：url-pattern有两种配置方式<br/>
&nbsp;&nbsp;&nbsp;&nbsp;①.使用*.xx 后缀类型配置，如 *.do<br/>
&nbsp;&nbsp;&nbsp;&nbsp;②.直接使用“/”配置，表示所有/开头的请求都交给DisPatchServlet来处理，这会导致tomcat中的默认default servlet（在tomcat的web.xml中可以看到）失效，而这个默认servlet
能够处理静态资源（即能访问），而DispatchServlet是不能够处理静态资源的，因为controller中没有对应的控制器去处理，所以会导致访问静态资源失效。<br/>
&nbsp;&nbsp;&nbsp;&nbsp;要解决这个问题有两种方式：<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I.在springmvc配置文件中配置&lt;mvc:default-servlet-handler/&gt;，配置后springmvc框架会创建一个DefaultServletHpptRequestHandler对象，这个对象会把
请求转发给tomcat的default这个servlet处理。同时还要配置&lt;mvc:annotation-driven/&gt;<br/>(不推荐，因为这是依靠tomcat的web.xml中配置的default servelet)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;II.使用springmvc框架自己的处理器，在springmvc配置文件中配置如&lt;mvc:resources mapping="/static/**" location="/static/"/&gt;<br/>
<p><a href="static/html/index.html">静态资源访问示例</a></p>
4.创建一个index.jsp<br/>
5.创建控制器（controller包下）<br/>
&nbsp;&nbsp;1).在类的上面加上@Controller注解<br/>
&nbsp;&nbsp;2).在类的方法中加入@RequestMapping注解<br/>
&nbsp;&nbsp;3).在类的上面加上@RequestMapping,如果有需要的话(如果加了，那么index.jsp中访问的路径要对应改变)<br/>
<p><a href="user/some.do">发起一个some.do请求</a></p>
<p><a href="user/first.do">发起一个first.do请求</a></p>
<p><a href="user/other.do">发起一个other.do请求</a></p>
&nbsp;&nbsp;4).指定方法的请求method属性（post、get等）<br/>
&nbsp;&nbsp;5).控制器方法参数的接收<br/>
&nbsp;&nbsp;&nbsp;&nbsp;①逐个接收:见MyController下的showReceiveParameter()方法和doSome(HttpServletRequest request, HttpServletResponse response)方法<br/>
&nbsp;&nbsp;&nbsp;&nbsp;②对象接收：创建一个实体类，如vo包下的Person类，为他设置好属性，属性名需要与前端表格中的字段名对应。然后设置他的控制器处理器方法（MyController中的receiveParamObjectWay）<br/>
<p><a href="user/receive.do">请求参数接收示例</a></p>
&nbsp;&nbsp;6).控制器方法的返回值类型<br/>
&nbsp;&nbsp;&nbsp;&nbsp;①ModelAndView 即返回视图又返回数据（见5.5）<br/>
&nbsp;&nbsp;&nbsp;&nbsp;②String 返回值类型为String,且没有加@ResponseBody注解的，表示只返回视图（相当于forward跳转一个页面）（见MyController的toNextPage()）<br/>
&nbsp;&nbsp;&nbsp;&nbsp;③Void 只返回数据，Ajax、JSON (了解即可)<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;配置jackson依赖。导入jQuery.js及编写ajax脚本（见returnValuePage.jsp的head标签中的script部分）。<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;方法见doReturnVoidAjax()。<br/>
&nbsp;&nbsp;&nbsp;&nbsp;④Object 只返回数据，Object是对象，对象有属性，属性就是数据。控制器方法返回Object，可以转为Json格式，响应ajax的请求。<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;加jackson依赖<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;springmvc的配置文件加入&lt;mvc:annotation-driven/&gt; (http://www.springframework.org/schema/mvc")注解驱动。这个驱动的作用是将java对象转化为json、xml等数据格式。<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在处理器的方法上加上@ResponseBody注解。这个注解时springmvc将数据响应出去<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;若返回值类型为String且有@ResponseBody注解的表示返回字符串数据，而不是视图。此时返回的数据会出现中文乱码，需要在@RequestMapping注解中配置produces = "text/plain;charset=utf-8"<br/>
<p><a href="user/returnValue.do">控制器方法返回值示例</a></p>
6.在Resource下创建对应的springmvc配置文件（文件名与步骤3的web.xml中的配置有关）<br/>
&nbsp;&nbsp;1).配置组件扫描器，用于扫描Controller<br/>
&nbsp;&nbsp;2).配置视图解析器<br/>
7.创建需要的jsp（也就是视图），作为请求处理的结果<br/>
&nbsp;&nbsp;1).在WEB-INF下创建视图，请阅读ReadMe2.txt<br/>
8.在web.xml中配置过滤器（解决post请求的乱码问题）<br/>
&nbsp;&nbsp;1).get请求方式是没有中文乱码问题的，但是post请求方式会有<br/>

Finally.配置好Project Structure中的Modules部分和Facets部分；配置tomcat<br/>

<h2>其他知识点</h2>
<h3>1.<a href="user/forwardAndRedirect.do">Springmvc中的重定向与转发</a></h3>





</body>
</html>
