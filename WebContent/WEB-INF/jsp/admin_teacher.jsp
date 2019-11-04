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
    <title>教师管理</title>
</head>

<body>
    <%@ include file="admin_header.jsp" %>
    <div class="container">
        <h3 class="mt-5">教师管理</h3>
        <div class="card bg-light mt-5">
            <div class="card-body">
                <form method="post" action="addTeacher">
                    <div class="row">
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                            <input type="text" class="form-control" placeholder="教师ID" name="tid">
                        </div>
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                            <input type="text" class="form-control" placeholder="姓名" name="tname">
                        </div>
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                            <input type="text" class="form-control" placeholder="密码" name="tpwd">
                        </div>
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                            <div class="input-group" style="padding: 5px;">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="admin" name="tadmin" value="true">
                                    <label class="custom-control-label" for="admin">管理员</label>
                                  </div>
                            </div>
                        </div>
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                            <input type="submit" class="btn bg-success text-white form-control" value="添加">
                        </div>
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                        	<div class="errorTip">${requestScope.errorCode}</div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="card bg-light mt-5">
            <div class="card-body">
                <form>
                    <div class="row">
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                            <input type="text" class="form-control" placeholder="教师ID">
                        </div>
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                            <input type="text" class="form-control" placeholder="姓名">
                        </div>
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                            <div class="input-group" style="padding: 5px;">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="isadmin" name="example1">
                                    <label class="custom-control-label" for="isadmin">管理员</label>
                                  </div>
                            </div>
                        </div>
                        <div class="col-md-2 col-sm-4 col-6 input-group">

                        </div>
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                            <input type="submit" class="btn bg-success text-white form-control" value="查询">
                        </div>
                    </div>
                </form>
                <table class="table table-hover mt-3">
                    <thead>
                        <tr>
                            <th class="w-20">教师ID</th>
                            <th class="w-20">姓名</th>
                            <th class="w-20">管理员</th>
                            <th class="w-20">操作类型</th>
                        </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${requestScope.teachers}" var="teacher">
							<tr>
                            	<td>${teacher.tid}</td>
                           		<td>${teacher.tname}</td>
                           		<td><c:if test="${teacher.tadmin}"><i class="fa fa-check"></i></c:if></td>
                            	<td><a href="editTeacher?tid=${teacher.tid}">编辑</a> | <a href="deleteTeacher?tid=${teacher.tid}">删除</a></td>
                        	</tr>
						</c:forEach>
                    </tbody>
                </table>
                
            </div>
        </div>
    </div>
    <script src="js/jquery-3.4.1.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
</body>
</html>