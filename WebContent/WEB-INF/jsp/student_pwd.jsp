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
    <link rel="stylesheet" href="css/font-awesome.css">
    <script src="js/jquery-3.4.1.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
    <title>上机考试管理系统</title>
</head>

<body>
    <%@ include file="student_header.jsp" %>
    <div class="container myrelative">
            <div class="return">
                <a href="sMain">
                    <i class="fa fa-angle-double-left"></i>
                </a>
            </div>
            <h3 class="mt-5">修改密码</h3>
            <div class="card bg-light mt-5">
                <div class="card-body">
                    <form style="max-width: 300px;" method="post" action="sUpdatePwd">
                    	<div class="errorTip">${requestScope.errorCode}</div>
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="原始密码" name="oldpwd">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="新密码" name="newpwd1">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="新密码" name="newpwd2">
                        </div>
                        <input type="submit" class="btn btn-success btn-sm" value="修改"></input>
                    </form>
                </div>
            </div>
        </div>
</body>

</html>