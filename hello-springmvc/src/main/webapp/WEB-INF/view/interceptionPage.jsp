<%--
  Created by IntelliJ IDEA.
  User: scorpicat
  Date: 2021/8/4
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>interceptionPage</title>
</head>
<body>
<h3>Spring拦截器</h3>
<h4>什么是拦截器？</h4>
&nbsp;&nbsp;1).拦截器是springmvc中的一种对象，需要实现HandlerInterceptor接口<br/>
&nbsp;&nbsp;2).拦截器和过滤器类似，但是功能侧重点不同。过滤器用来过滤请求参数，设置编码字符集等工作。<br/>
&nbsp;&nbsp;拦截器用来拦截用户的请求，做请求判断处理的。<br/>
&nbsp;&nbsp;3).拦截器是全局的，可以对多个controller做拦截。<br/>
&nbsp;&nbsp;一个项目中可以有0个或多个拦截器，他们都对用户的请求做拦截。<br/>
&nbsp;&nbsp;4).拦截器常用在:用户登录处理，权限检查，记录日志<br/>
&nbsp;&nbsp;5).拦截器的执行时间：<br/>
&nbsp;&nbsp;&nbsp;&nbsp;①.在请求处理之前，也就是controller的方法被执行之前被拦截<br/>
&nbsp;&nbsp;&nbsp;&nbsp;②.在控制器的方法执行之后也会执行拦截器<br/>
&nbsp;&nbsp;&nbsp;&nbsp;③.在请求处理后也会执行拦截器<br/>
<h4>拦截器与过滤器的区别</h4>
&nbsp;&nbsp;1.过滤器是servlet的对象，拦截器是springmvc的对象<br/>
&nbsp;&nbsp;2.过滤器是实现Filter接口的对象，而拦截器是实现HandleInterception的对象<br/>
&nbsp;&nbsp;3.过滤器是用来设置Request、response的参数属性的，侧重对数据过滤的。<br/>
&nbsp;&nbsp;拦截器则是用来验证请求的，可以截断请求<br/>
&nbsp;&nbsp;4.过滤器在拦截器前执行<br/>
&nbsp;&nbsp;5.过滤器只有一个执行时间点，拦截器有三个时间点<br/>
&nbsp;&nbsp;6.过滤器可以处理jsp,html.js等对象<br/>
&nbsp;&nbsp;拦截器拦截的是controller对象，如果请求不被DispatchServlet接收，就不会触发拦截器<br/>
<h4>实现步骤</h4>
&nbsp;&nbsp;1).创建一个类作为拦截器使用<br/>
&nbsp;&nbsp;该类实现HandlerInterception接口<br/>
&nbsp;&nbsp;实现接口中的三个方法<br/>
&nbsp;&nbsp;&nbsp;&nbsp;preHandle()：请求拦截过滤<br/>
&nbsp;&nbsp;&nbsp;&nbsp;postHandle()：请求结果修正处理<br/>
&nbsp;&nbsp;&nbsp;&nbsp;afterCompletion()：资源回收工作<br/>
&nbsp;&nbsp;2).springmvc的配置文件中，声明拦截器，并指定拦截器的请求uri地址<br/>
<h4>演示</h4>
<p>springmvc配置文件中如下配置，表示对所有/user/下的请求都进行拦截，根据我们配置的拦截器处理方法，会在控制台输出对应的方法名</p>
<img src="/hello_springmvc_war_exploded/static/imgs/lanjieqi.png">
<p>为了看清拦截器方法的执行顺序，我们专门写一个控制器中的方法用来输出控制器方法的执行</p>
<p><a href="doInteception.do">点击我后去控制台看输出结果</a></p>
<p>输出结果，可以看到各方法的执行顺序：</p>
MyInterception--prehandle<br/>
MyController--doInterception()方法<br/>
MyInterception--postHandle<br/>
MyInterception--afterCompletion<br/>
<p></p>
</body>
</html>
