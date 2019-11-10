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
    <link rel="stylesheet" href="css/tempusdominus-bootstrap-4.min.css">
    <script src="js/jquery-3.4.1.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
    <script src="js/moment.min.js"></script>
    <script src="js/tempusdominus-bootstrap-4.min.js"></script>
    <title>考试管理</title>
</head>

<body>
    <%@ include file="teacher_header.jsp" %>
    <div class="container">
        <h3 class="mt-5">考试管理</h3>
        <div class="card bg-light mt-5">
            <div class="card-body">
                <form method="post" action="addExam">
                    <div class="row">
                        <div class="input-group col-md-2 col-sm-4 col-6">
                            <input type="text" class="form-control" name="examname" placeholder="考试名称">
                        </div>
                        <div class="input-group col-md-2 col-sm-4 col-6">
                            <input type="text" class="form-control datetimepicker-input" name="examtime" id="datetimepicker1"
                                data-toggle="datetimepicker" data-target="#datetimepicker1" placeholder="考试时间" />
                            <script type="text/javascript">
                                $(function () {
                                    $('#datetimepicker1').datetimepicker({
                                        format: 'YYYY-MM-DD HH:mm'
                                    });
                                });
                            </script>
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
                            <th>考试名称</th>
                            <th>考试时间</th>
                            <th>创建人</th>
                            <th>自动开始</th>
                            <th>上传试卷</th>
                            <th>进行中</th>
                            <th>已结束</th>
                            <th>已归档</th>
                            <th>已清理</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${requestScope.exams}" var="exam">
							<tr>
                            	<td>${exam.ename}</td>
                           		<td>${exam.etime}</td>
                           		<td>${sessionScope.teacher.tname}</td>
                           		<td><c:if test="${exam.eautostart}"><i class="fa fa-check"></i></c:if></td>
                           		<td><c:if test="${not empty exam.epaper}"><i class="fa fa-check"></i></c:if></td>
                           		<td><c:if test="${exam.eactive}"><i class="fa fa-check"></i></c:if></td>
                           		<td><c:if test="${exam.efinish}"><i class="fa fa-check"></i></c:if></td>
                           		<td><c:if test="${exam.earchive}"><i class="fa fa-check"></i></c:if></td>
                           		<td><c:if test="${exam.ecleared}"><i class="fa fa-check"></i></c:if></td>
                            	<td>
                            		<c:choose>
                            			<c:when test="${empty exam.epaper}">
                            				<a class="btn btn-sm bg-secondary text-white" href="editExam?eid=${exam.eid}">编辑</a>
                                			<a class="btn btn-sm bg-success text-light disabled" href="examStart?eid=${exam.eid}">开启</a>
                            			</c:when>
                            			<c:when test="${not empty exam.epaper and not exam.eactive and not exam.efinish}">
                            				<a class="btn btn-sm bg-secondary text-white" href="editExam?eid=${exam.eid}">编辑</a>
                                			<a class="btn btn-sm bg-success text-light" href="examStart?eid=${exam.eid}">开启</a>
                            			</c:when>
                            			<c:when test="${exam.eactive and not exam.efinish}">
                            				<a class="btn btn-sm bg-secondary text-white" href="examStatus?eid=${exam.eid}">详情</a>
                                			<a class="btn btn-sm bg-danger text-light" href="examFinish?eid=${exam.eid}">结束</a>
                            			</c:when>
                            			<c:when test="${exam.efinish and not exam.earchive}">
                            				<a class="btn btn-sm bg-secondary text-white" href="teacher_exam_status.html">打包</a>
                                			<a class="btn btn-sm bg-secondary text-light" href="teacher_grade.html">成绩</a>
                                			<a class="btn btn-sm bg-warning text-light disabled" href="">清理</a>
                            			</c:when>
                            			<c:when test="${exam.earchive and not exam.ecleared}">
                            				<a class="btn btn-sm bg-secondary text-white" href="teacher_exam_status.html">打包</a>
                                			<a class="btn btn-sm bg-secondary text-light" href="teacher_grade.html">成绩</a>
                                			<a class="btn btn-sm bg-warning text-light" href="">清理</a>
                            			</c:when>
                            			<c:when test="${exam.ecleared}">
                            				<a class="btn btn-sm badge-danger text-light" href="deleteExam?eid=${exam.eid}">删除</a>
                            			</c:when>
                            		</c:choose>
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