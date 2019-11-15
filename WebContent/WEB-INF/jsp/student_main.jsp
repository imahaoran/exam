<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String basePath=request.getContextPath()+"/"; %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">
    <script src="js/jquery-3.4.1.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
    <title>上机考试管理系统</title>
</head>

<body>
    <%@ include file="student_header.jsp" %>
    <div class="container">
        <h3 class="mt-5">学生主页</h3>
        <div class="row mt-5">
            <div class="col-md-6 col-lg-4">
                <div class="jumbotron mainblock">
                    <b>在线考试</b>
                    <ul>
                        <li>查看已开放考试</li>
                        <li>参加考试</li>
                    </ul>
                </div>
            </div>
            <div class="col-md-6 col-lg-4">
                <div class="jumbotron mainblock">
                    <b>成绩查询</b>
                    <ul>
                        <li>查看过往考试</li>
                        <li>查看考试成绩</li>
                    </ul>
                </div>
            </div>
            <div class="col-sm-6 col-lg-4">
                    <div class="jumbotron mainblock">
                        <b>修改密码</b>
                        <ul>
                            <li>修改密码</li>
                            <li>退出系统</li>
                        </ul>
                    </div>
                </div>
        </div>
    </div>
</body>

</html>