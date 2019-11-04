<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String basePath=request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">
    <title>教师主页</title>
</head>

<body>
    <%@ include file="teacher_header.jsp" %>
    <div class="container">
        <h3 class="mt-5">教师主页</h3>
        <div class="row mt-5">
            <div class="col-md-6 col-lg-4">
                <div class="jumbotron mainblock">
                    <b>考前管理</b>
                    <ul>
                        <li>创建一场考试</li>
                        <li>编辑考试信息</li>
                        <li>添加考试学生名单</li>
                    </ul>
                </div>
            </div>
            <div class="col-md-6 col-lg-4">
                <div class="jumbotron mainblock">
                    <b>考中管理</b>
                    <ul>
                        <li>查看考试进行状态</li>
                        <li>添加考试学生</li>
                        <li>发布临时通知</li>
                    </ul>
                </div>
            </div>
            <div class="col-sm-6 col-lg-4">
                    <div class="jumbotron mainblock">
                        <b>考后管理</b>
                        <ul>
                            <li>修改密码</li>
                            <li>清除考试</li>
                            <li>退出系统</li>
                        </ul>
                    </div>
                </div>
        </div>
    </div>
    <script src="js/jquery-3.4.1.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
</body>

</html>