<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath=request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">
    <title>管理员主页</title>
</head>

<body>
     <%@ include file="admin_header.jsp" %>
     <div class="container">
        <h3 class="mt-5">教师主页</h3>
        <div class="row mt-5">
            <div class="col-md-6 col-lg-4">
                <div class="jumbotron mainblock">
                    <b>考前管理</b>
                    <ul>
                        <li>对教师用户进行增删改查操作</li>
                        <li>对指定教师赋予/撤销管理员权限</li>
                        <li>对教师用户进行其他操作</li>
                    </ul>
                </div>
            </div>
            <div class="col-md-6 col-lg-4">
                <div class="jumbotron mainblock">
                    <b>考中管理</b>
                    <ul>
                        <li>对学生进行增删改查操作</li>
                        <li>支持批量添加学生</li>
                        <li>可以进行模糊查询</li>
                    </ul>
                </div>
            </div>
            <div class="col-sm-6 col-lg-4">
                    <div class="jumbotron mainblock">
                        <b>考后管理</b>
                        <ul>
                           <li>可以更改系统配置</li>
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