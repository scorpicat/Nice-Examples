<%--
  Created by IntelliJ IDEA.
  User: scorpicat
  Date: 2021/7/31
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>receiveRequest</title>
</head>
<body>
<p>
    本页案例中会把请求参数接收后又作为相应数据原样返回
</p>
<h2>5.5.① 请求参数逐个接收示例:get类型</h2>
<form action="showReceive.do" method="get">
    姓名：<input type="text" name="rname"><br/>
    年龄：<input type="number" name="rage"><br/>
    <input type="submit" value="提交">
</form>
<h2>5.5.① 请求参数逐个接收示例:post类型</h2>
<form action="showReceivePost.do" method="post">
    姓名：<input type="text" name="rname"><br/>
    年龄：<input type="number" name="rage"><br/>
    <input type="submit" value="提交">
</form><h2>5.5.② 请求参数对象接收示例:post类型</h2>
<form action="receiveParamObjectWay.do" method="post">
    姓名：<input type="text" name="name"><br/>
    年龄：<input type="number" name="age"><br/>
    <input type="submit" value="提交">
</form>
</body>
</html>
