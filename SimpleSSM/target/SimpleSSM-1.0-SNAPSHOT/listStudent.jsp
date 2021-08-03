<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <title>查询学生信息</title>
    <base href="<%=basePath%>"/>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">

        $(function () {
            //页面进入自动加载数据
            loaderData();
            //手动刷新数据
            $("#btnLoader").click(function () {
                loaderData();
            })
        })
        function loaderData() {
            $.ajax(
                {
                    url:"student/queryStudent.do",
                    type:"get",
                    dataType:"json",
                    success:function (data) {
                        //清空页面的旧数据
                        $("#info").html("");
                        //增加新数据
                        $.each(data,function (i,n) {
                            $("#info").append("<tr>")
                                .append("<td>"+n.id+"</td>")
                                .append("<td>"+n.name+"</td>")
                                .append("<td>"+n.age+"</td>")
                                .append("</tr>");
                        })
                    }
                }
            )
        }
    </script>
</head>
<body>
<div align="center">
    <table>
        <thead>
            <tr>
                <td>学号</td>
                <td>姓名</td>
                <td>年龄</td>
            </tr>
        </thead>
        <tbody id="info">

        </tbody>
    </table>
    <input value="查询数据" type="button" id="btnLoader"/>
</div>
</body>
</html>
