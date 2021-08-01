<%--
  Created by IntelliJ IDEA.
  User: scorpicat
  Date: 2021/8/1
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>returnValuePage</title>
<%--    ReadMe.txt 5.6.③ 返回值类型为Void，ajax时 起--%>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            //5.5.③
            $("#btn").click(function () {
                $.ajax({
                    url:"return-ajax.do",
                    data:{
                        age:20,
                        name:"张三"
                    },
                    type:"post",
                    dataType:"json",
                    success:function (resp) {
                        <!--resp是服务端返回的json格式的字符串，jQuery会把它转化为json对象-->
                        alert(resp.name+' '+resp.age)
                    }
                })
            })
            //5.5.④
            $("#btn2").click(function () {
                $.ajax({
                    url:"returnObjectJson.do",
                    data:{
                        msg:"请求两个人的信息"
                    },
                    type:"post",
                    dataType:"json",
                    success:function (resp) {
                        <!--resp是服务端返回的json格式的字符串，jQuery会把它转化为json对象-->
                        $.each(resp,function(i,obj){
                            alert(obj.name+' '+obj.age)
                        })
                    }
                })
            })
            $("#btn3").click(function () {
                $.ajax({
                    url:"returnDataString.do",
                    data:{
                        msg:"请求纯字符串数据响应"
                    },
                    type:"post",
                    dataType:"text",//这里不是json，而是text
                    success:function (resp) {
                        alert(resp.toString())
                    }
                })
            })
        })

    </script>
    <%--    ReadMe.txt 5.6.③ 返回值类型为Void，ajax时 止--%>
</head>
<body>
<h3><a href="toNextPage.do">控制器方法的返回值为String示例</a></h3>
<h3>5.6.③ 控制器方法的返回值为Void示例：响应ajax (原始方法)</h3>
<button id="btn">发起ajax请求</button>
<h3>5.6.④ 控制器方法的返回值为Object示例：响应ajax（springmvc配置的进阶方法）</h3>
<button id="btn2">发起ajax请求</button><br/>
<button id="btn3">响应纯字符串数据</button>
</body>
</html>
