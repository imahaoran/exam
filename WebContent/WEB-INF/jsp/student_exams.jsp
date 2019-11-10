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
    <title>在线考试</title>
</head>

<body>
    <%@ include file="student_header.jsp" %>
    <div class="container">
        <h3 class="mt-5 mb-5">在线考试</h3>
        <div class="card bg-light mt-5">
            <div class="card-body">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th class="w-25">考试名称</th>
                            <th class="w-25">考试时间</th>
                            <th class="w-25">创建人</th>
                            <th class="w-25"></th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${requestScope.exams}" var="exam">
                    		<tr>
                            <td>${exam.ename}</td>
                            <td>${exam.etime}</td>
                           	<td>${exam.teacher.tname}</td>
                            <td>
                                <a class="btn btn-sm bg-success text-white" href="examming?eid=${exam.eid}">进入考试</a>
                                <c:if test="${requestScope.errorEid == exam.eid}">
                        			<span class="errorTip">不可参与</span>
                        		</c:if>
                            </td>
                        	</tr>
                    	</c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>

</html>