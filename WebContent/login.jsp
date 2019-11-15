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
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <title>登录</title>
</head>
<body>
    <div class="login-page">
        <div class="container d-flex align-items-center">
            <div class="form-holder">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="info mycolor d-flex align-items-center">
                            <div class="content">
                                <h1 class="text-white">欢迎登录</h1>
                                <p class="text-white">河南大学上机考试管理系统</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form">
                        <c:if test="${not empty errorCode}">
                        	<div style="position: absolute;left:0px;top:0px;background-color: red;color: white;width: 100%;text-align: center;"><c:out value="${errorCode}"/></div>
                        </c:if>
                            <ul class="nav nav-tabs">
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="tab" href="#tab1">学生登录</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#tab2">教师登录</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#tab3">管理员登录</a>
                                </li>
                            </ul>
                            <div class="">

                            </div>
                            <div class="tab-content allheight d-flex align-items-center">
                                <div class="tab-pane active" style="width: 100%" id="tab1">
                                    <form method="POST" action="sLogin" id="loginFrom">
                                        <div class="form-group">
                                            <input id="userName" type="text" class="form-control" name="username"
                                                placeholder="学生：学号">
                                        </div>
                                        <div class="form-group">
                                            <input id="userPwd" type="password" class="form-control" name="password"
                                                placeholder="学生：密码">
                                        </div>
                                        <div class="form-group d-flex align-items-center justify-content-between">
                                            <button type="submit" class="btn btn-dark">登录</button>
                                        </div>
                                    </form>
                                </div>
                                <div class="tab-pane" style="width: 100%" id="tab2">
                                    <form method="POST" action="tLogin" id="loginFrom">
                                        <div class="form-group">
                                            <input id="userName" type="text" class="form-control" name="username"
                                                placeholder="教师：姓名">
                                        </div>
                                        <div class="form-group">
                                            <input id="userPwd" type="password" class="form-control" name="password"
                                                placeholder="教师：密码">
                                        </div>
                                        <div class="form-group d-flex align-items-center justify-content-between">
                                            <button type="submit" class="btn btn-dark">登录</button>
                                        </div>
                                    </form>
                                </div>
                                <div class="tab-pane" style="width: 100%" id="tab3">
                                    <form method="POST" action="aLogin" id="loginFrom">
                                        <div class="form-group">
                                            <input id="userName" type="text" class="form-control" name="username"
                                                placeholder="管理员：姓名">
                                        </div>
                                        <div class="form-group">
                                            <input id="userPwd" type="password" class="form-control" name="password"
                                                placeholder="管理员：密码">
                                        </div>
                                        <div class="form-group d-flex align-items-center justify-content-between">
                                            <button type="submit" class="btn btn-dark">登录</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="js/jquery-3.4.1.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
</body>
</html>