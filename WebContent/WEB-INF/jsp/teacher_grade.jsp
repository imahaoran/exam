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
            <a href="examManager">
                <i class="fa fa-angle-double-left"></i>
            </a>
        </div>
        <h3 class="mt-5">${requestScope.exam.ename}——录入成绩</h3>
        <div class="card bg-light mt-5">
            <div class="card-body">
                <form method="post" action="importGrade">
                	<input type="hidden" value="${requestScope.exam.eid}" name="eid"/>
                    <table class="table table-hover mt-3">
                        <thead>
                            <tr>
                                <th class="w-25">学号</th>
                                <th class="w-25">姓名</th>
                                <th class="w-25">成绩</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.results}" var="result">
                    		<tr>
                            	<td>${result.student.sid}</td>
                            	<td>${result.student.sname}</td>
                           	 	<td><input type="text" class="form-control" value="${result.grade}" name="${result.rid}" 
    									onkeyup="if(isNaN(value)){execCommand('undo');alert('只能输入数字 !');}" 
    									onafterpaste="if(isNaN(value))execCommand('undo')" >
    							</td>
                        	</tr>
                    		</c:forEach>
                        </tbody>
                    </table>
                    <input type="submit" class="btn btn-sm bg-success text-white" value="保存">
                    <c:if test="${not empty requestScope.errorCode}">
                        <div class="errorTip">${requestScope.errorCode}</div>
                    </c:if>
                    <c:if test="${not empty requestScope.successCode}">
                        <div class="successTip">${requestScope.successCode}</div>
                    </c:if>
                </form>

            </div>
        </div>
    </div>
</body>

</html>