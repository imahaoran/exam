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
    <script src="js/jquery-3.4.1.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
    <script>
		function del(ename){
			var x;
			var r=confirm("确认删除"+ename+"?");
			if (r==true){
				
			}
			else{
				
			}
		}
	</script>
    <title>考试管理</title>
</head>
<body>
	<%@ include file="admin_header.jsp" %>
	<div class="container">
        <h3 class="mt-5">考试管理</h3>
        <div class="card bg-light mt-5">
            <div class="card-body">
                <form>
                    <div class="row">
                        <div class="input-group col-md-2 col-sm-4 col-6">
                            <input type="text" class="form-control" placeholder="考试名称">
                        </div>
                        <div class="input-group col-md-2 col-sm-4 col-6">
                            <input type="text" class="form-control" placeholder="出题教师">
                        </div>
                        <div class="input-group col-md-2 col-sm-4 col-6">
                            <input type="text" class="form-control" placeholder="考试ID">
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
                            <th>考试名称</th>
                            <th>考试时间</th>
                            <th>创建人</th>
                            <th>自动开始</th>
                            <th>上传试卷</th>
                            <th>进行中</th>
                            <th>已结束</th>
                            <th>已归档</th>
                            <th>已清理</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.exams}" var="exam">
							<tr>
                            	<td>${exam.ename}</td>
                           		<td>${exam.etime}</td>
                           		<td>${exam.teacher.tname}</td>
                           		<td><c:if test="${exam.eautostart}"><i class="fa fa-check"></i></c:if> </td>
                           		<td><c:if test="${not empty exam.epaper}"><i class="fa fa-check"></i></c:if> </td>
                           		<td><c:if test="${exam.eactive}"><i class="fa fa-check"></i></c:if> </td>
                           		<td><c:if test="${exam.efinish}"><i class="fa fa-check"></i></c:if> </td>
                           		<td><c:if test="${exam.earchive}"><i class="fa fa-check"></i></c:if> </td>
                           		<td><c:if test="${exam.ecleared}"><i class="fa fa-check"></i></c:if> </td>
                            	<td><a href="" onclick="del('${exam.ename}')">删除</a></td>
                        	</tr>
						</c:forEach>
                    </tbody>
                </table>
                <!-- 
                <ul class="pagination">
                    <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
                    <li class="page-item active"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item"><a class="page-link" href="#">Next</a></li>
                </ul>
                 -->
            </div>
        </div>
    </div>
</body>
</html>