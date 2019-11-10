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
    <title>${requestScope.exam.ename}</title>
</head>

<body>
    <%@ include file="teacher_header.jsp" %>
    <div class="container myrelative">
        <div class="return">
            <a href="examManager">
                <i class="fa fa-angle-double-left"></i>
            </a>
        </div>
        <h3 class="mt-5">${requestScope.exam.ename}——状态</h3>
        <div class="card bg-light mt-5">
            <div class="card-body">
            	<form class="form-inline">
                    <textarea class="form-control w-75" rows="1" placeholder="通知信息"></textarea>
                    <input type="submit" class="btn btn-sm bg-success text-white ml-5" value="发送">
                </form>
                <table class="table table-hover mt-3">
                    <thead>
                        <tr>
                            <th class="w-25">学号</th>
                            <th class="w-25">姓名</th>
                            <th class="w-25">状态</th>
                            <th class="w-25">提交试卷</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${requestScope.results}" var="result">
                    	<tr>
                            <td>${result.student.sid}</td>
                            <td>${result.student.sname}</td>
                            <c:if test="${not empty result.ip}"><td class="text-success">在线（${result.ip}）</td></c:if>
                            <c:if test="${empty result.ip}"><td>离线</td></c:if>
                            <td><c:if test="${not empty result.submitfile}"><i class="fa fa-check"></i></c:if></td>
                        </tr>
                    	</c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>

</html>