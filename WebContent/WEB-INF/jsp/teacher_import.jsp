<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <link rel="stylesheet" href="css/font-awesome.css">
    <script src="js/jquery-3.4.1.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
    <title>上机考试管理系统</title>
</head>

<body>
    <%@ include file="teacher_header.jsp" %>
    <div class="container myrelative">
        <div class="return">
            <a href="editExam?eid=${requestScope.exam.eid}">
                <i class="fa fa-angle-double-left"></i>
            </a>
        </div>
        <h3 class="mt-5">${requestScope.exam.ename}——学生名单</h3>
        <div class="card bg-light mt-5">
            <div class="card-body">
                <h6 class="mb-3">批量导入：</h6>
                <form method="post" action="upLoadSExcel2?eid=${requestScope.exam.eid}" enctype="multipart/form-data">
                    <div class="row">
                        <div class="input-group col-8">
                            <input style="width: auto;" type="file" id="myFile" name="file">
                        </div>
                        <div class="input-group col-2">
                            <input type="submit" class="btn btn-sm bg-success text-white form-control" value="提交">
                        </div>
                        <div class="input-group col-2">
                            <div class="errorTip">${requestScope.errorCode2}</div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="card bg-light mt-5">
            <div class="card-body">
                <h6 class="mb-3">单个导入：</h6>
                <form method="post" action="addResult?eid=${requestScope.exam.eid}">
                    <div class="row">
                        <div class="input-group col-md-2 col-sm-4 col-6">
                            <input type="text" class="form-control" name="sid" placeholder="学号">
                        </div>
                        <div class="input-group col-md-2 col-sm-4 col-6">
                            <input type="text" class="form-control" name="sname" placeholder="姓名">
                        </div>
                        <div class="input-group col-md-2 col-sm-4 col-6">

                        </div>
                        <div class="input-group col-md-2 col-sm-4 col-6">
							<div class="errorTip">${requestScope.errorCode}</div>
                        </div>
                        <div class="input-group col-md-2 col-sm-4 col-6">
                            <input type="submit" class="btn bg-success text-white form-control" value="添加">
                        </div>
                    </div>
                </form>

                <table class="table table-hover mt-3">
                    <thead>
                        <tr>
                            <th class="w-25">学号</th>
                            <th class="w-50">姓名</th>
                            <th class="w-25"></th>
                        </tr>
                    </thead>
                    <tbody>
	                    <c:forEach items="${requestScope.results}" var="result">
	                    	<tr>
                            	<td>${result.student.sid}</td>
                           		<td>${result.student.sname}</td>
                           		<td><a href="deleteResult?rid=${result.rid}&&eid=${requestScope.exam.eid}">删除</a></td>
                        	</tr>
	                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>

</html>