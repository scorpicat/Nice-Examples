<%--
  Created by IntelliJ IDEA.
  User: scorpicat
  Date: 2021/8/3
  Time: 0:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<div align="center">
    <form action="student/addStudent.do">
    <table>
        <tr><td>姓名：</td><td><input type="text" name="name"></td></tr>
        <tr><td>年龄：</td><td><input type="text" name="age"></td></tr>
        <tr><td><input type="submit" value="提交"></td></tr>
    </table>
    </form>
</div>
</body>
</html>
