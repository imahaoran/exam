<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <h3 class="mt-5">成绩查询</h3>
        <div class="card bg-light mt-5">
            <div class="card-body">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th class="w-25">考试名称</th>
                            <th class="w-25">考试时间</th>
                            <th class="w-25">创建人</th>
                            <th class="w-25">成绩</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${requestScope.results}" var="result">
                    		<tr>
                            <td>${result.exam.ename}</td>
                            <td>${result.exam.etime}</td>
                            <td>${result.student.sname}</td>
                            <td>${result.grade}</td>
                        	</tr>
                    	</c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>

</html>