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
    <title>学生管理</title>
</head>
<body>
    <%@ include file="admin_header.jsp" %>
    <div class="container">
        <h3 class="mt-5">学生管理</h3>
        <div class="card bg-light mt-5">
            <div class="card-body">
                <form method="post" action="addStudent" >
                    <div class="row">
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                            <input type="text" class="form-control" placeholder="学号" name="sid">
                        </div>
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                            <input type="text" class="form-control" placeholder="姓名" name="sname">
                        </div>
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                            <input type="text" class="form-control" placeholder="密码" name="spwd">
                        </div>

                        <div class="col-md-2 col-sm-4 col-6 input-group">
							<div class="errorTip">${requestScope.errorCode}</div>
                        </div>
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                            <input type="submit" class="btn bg-success text-white form-control" value="添加">
                        </div>
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                            <input type="submit" class="btn bg-success text-white form-control" value="批量添加">
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="card bg-light mt-5">
            <div class="card-body">
                <form>
                    <div class="row">
                        <div class="input-group col-md-2 col-sm-4 col-6">
                            <input type="text" class="form-control" placeholder="学号">
                        </div>
                        <div class="input-group col-md-2 col-sm-4 col-6">
                            <input type="text" class="form-control" placeholder="姓名">
                        </div>
                        <div class="input-group col-md-2 col-sm-4 col-6">

                        </div>
                        <div class="input-group col-md-2 col-sm-4 col-6">

                        </div>
                        <div class="input-group col-md-2 col-sm-4 col-6">
                            <input type="submit" class="btn bg-success text-white form-control" value="查询">
                        </div>
                    </div>
                </form>

                <table class="table table-hover mt-3">
                    <thead>
                        <tr>
                            <th class="w-25">学号</th>
                            <th class="w-25">姓名</th>
                            <th class="w-25"></th>
                            <th class="w-25">操作类型</th>
                        </tr>
                    </thead>
                    <tbody>
						<c:forEach items="${requestScope.students}" var="student">
							<tr>
                            	<td>${student.sid}</td>
                           		<td>${student.sname}</td>
                           		<td></td>
                            	<td><a href="editStudent?sid=${student.sid}">编辑</a> | <a href="deleteStudent?sid=${student.sid}">删除</a></td>
                        	</tr>
						</c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="container">

    </div>
    <script src="js/jquery-3.4.1.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
</body>
</html>