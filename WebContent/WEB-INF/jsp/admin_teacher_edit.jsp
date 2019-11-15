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
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/font-awesome.css">
    <title>上机考试管理系统</title>
</head>

<body>
    <%@ include file="admin_header.jsp" %>
    <div class="container myrelative">
    	<div class="return">
            <a href="tManager">
                <i class="fa fa-angle-double-left"></i>
            </a>
        </div>
        <h3 class="mt-5">教师编辑</h3>
        <div class="card bg-light mt-5">
            <div class="card-body">
                <form method="post" action="updateTeacher">
                    <div class="row">
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                            <input type="text" class="form-control" name="tid" value="${requestScope.teacher.tid}" readonly unselectable="on">
                        </div>
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                            <input type="text" class="form-control" name="tname" value="${requestScope.teacher.tname}">
                        </div>
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                            <input type="text" class="form-control" name="tpwd" value="${requestScope.teacher.tpwd}">
                        </div>
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                            <div class="input-group" style="padding: 5px;">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="admin" name="tadmin" value="true" <c:if test="${teacher.tadmin}">checked="checked"</c:if>>
                                    <label class="custom-control-label" for="admin">管理员</label>
                                  </div>
                            </div>
                        </div>
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                            <input type="submit" class="btn bg-success text-white form-control" value="保存">
                        </div>
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                        	<div class="errorTip">${requestScope.errorCode}</div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="js/jquery-3.4.1.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
</body>
</html>