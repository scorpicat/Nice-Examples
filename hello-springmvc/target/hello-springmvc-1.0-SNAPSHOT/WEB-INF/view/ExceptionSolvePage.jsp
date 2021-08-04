<%--
  Created by IntelliJ IDEA.
  User: scorpicat
  Date: 2021/8/3
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<h3>SpringMVC全局异常处理</h3>
<p>springmvc采用统一的异常处理方式。</p>
步骤1.创建异常类，本项目中异常类在exception包下<br/>
步骤2.创建异常处理器，本项目中创建在handler包下。为异常处理器的类加上@ControllerAdvice标签，表示他是一个控制器增强器。<br/>
在该类下创建异常处理方法，方法的返回值类型和控制器中的方法一样可以有多种，如String ，void，ModelAndView等。<br/>
方法上要声明注解@ExceptionHandler，value值为要处理的异常类.class。如果没有指定value，则该方法为通用异常处理方法<br/>
步骤3.在controller中抛出异常，见toExceptionSolvePage()方法<br/>
步骤4.在springmvc中配置组件扫描器，扫描@ControllerAdvice声明的异常处理器<br/>
<form action="doException.do">
    <p>姓名：<input name="name" type="text"> </p>
    <p>年龄：<input name="age" type="number"> </p>
    <input type="submit" value="异常测试">
</form>
</body>
</html>
