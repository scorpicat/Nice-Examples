<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>重定向与请求转发页面</title>
</head>
<body>
<h2>Springmvc中的转发与重定向</h2>
在springmvc中，要实现重定向与请求转发，可以在controller中设置视图时使用redirect与forward来实现。<br/>
如：mv.setViewName("forward:/hello.jsp")
<h3>请求转发</h3>
请求转发使用forward:/资源路径。请求转发后地址的仍是访问时的地址，是一次请求
<p><a href="aForwardBefore.do?name=xiaoming&age=9">点击请求转发到forwardAfter页面，请求地址：aForwardBefore.do?name=xiaoming&age=9</a></p>
<h3>请求重定向</h3>
请求重定向使用redirect:/资源路径。重定向后地址的已经变成重定向指定的地址，是两次请求。
<div>
    <form action="redirectA.do">
        <p>姓名：<input type="text" name="name"> </p>
        <p>年龄：<input type="text" name="age"> </p>
        <input type="submit" value="从redirectA.do重定向到另个redirectB.jsp页面"></input>
    </form>
    <p>请按下F12查看请求次数</p>
</div>
<h3>重定向与请求转发的区别</h3>
<p>重定向在不处理的情况下(controller中处理)是不会把原页面的请求参数带过去的</p>
<p>而请求转发会将请求参数一起带过去</p>
<p>重定向后地址栏的地址会变成重定向指定的新地址</p>
<p>重定向时的网址可以是任何网址,转发的网址必须是本站点的网址</p>
<h3>什么时候用重定向？什么时候用请求转发？</h3>
<p>当前后两个页面有数据传递时，例如查询了数据需要在页面显示时，用请求转发</p>
<p>当没有数据传递，例如做了更新操作跳转到其他页面，就用重定向。</p>
</body>
</html>
