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
    <title>考试编辑</title>
</head>

<body>
    <%@ include file="teacher_header.jsp" %>
    <div class="container myrelative">
        <div class="return">
            <a href="examManager">
                <i class="fa fa-angle-double-left"></i>
            </a>
        </div>
        <h3 class="mt-5">${requestScope.exam.ename}——编辑</h3>
        <div class="card bg-light mt-5">
            <div class="card-body">
                <h6 class="mb-3">基本信息：</h6>
                <form method="post" action="updateExam">
                    <div class="row">
                    	<input type="hidden" name="eid" value="${requestScope.exam.eid}">
                        <div class="input-group col-md-2 col-sm-4 col-6">
                            <input type="text" class="form-control" name="ename" value="${requestScope.exam.ename}">
                        </div>
                        <div class="input-group col-md-2 col-sm-4 col-6">
                            <input type="text" class="form-control datetimepicker-input" id="datetimepicker1"
                                data-toggle="datetimepicker" data-target="#datetimepicker1" name="etime" value="${requestScope.exam.etime}" />
                            <script type="text/javascript">
                                $(function () {
                                    $('#datetimepicker1').datetimepicker({
                                        format: 'YYYY-MM-DD HH:mm'
                                    });
                                });
                            </script>
                        </div>
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                            <div class="input-group" style="padding: 5px;">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="auto" name="eautostart" value="true" <c:if test="${requestScope.exam.eautostart}">checked="checked"</c:if>>
                                    <label class="custom-control-label" for="auto">自动开始</label>
                                </div>
                            </div>
                        </div>
                        <div class="input-group col-md-2 col-sm-4 col-6">
                            <input type="submit" class="btn bg-success text-white form-control" value="保存">
                        </div>
                        <div class="col-md-2 col-sm-4 col-6 input-group">
                        	<c:if test="${not empty requestScope.errorCode}">
                        		<div class="errorTip">${requestScope.errorCode}</div>
                        	</c:if>
                        	<c:if test="${not empty requestScope.successCode}">
                        		<div class="successTip">${requestScope.successCode}</div>
                        	</c:if>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="card bg-light mt-5">
            <div class="card-body">
                <h6>上传试卷：</h6>
                <form action="upLoadPaper?eid=${requestScope.exam.eid}" enctype="multipart/form-data" method="post">
                    <c:if test="${not empty requestScope.exam.epaper}">
                        <div class="mt-3">
                        	已上传：${requestScope.exam.epaper}
                    	</div>
                    </c:if>
                    <div class="mt-3">
                        <input style="position: relative;top: 2px;" type="file" name="file">
                        <input type="submit" class="btn btn-sm bg-success text-white" value="上传">
                        <c:if test="${not empty requestScope.errorCodeup}">
                        	<span class="errorTip">${requestScope.errorCodeup}</span>
                        </c:if>
                        <c:if test="${not empty requestScope.successCodeup}">
                        	<span class="successTip">${requestScope.successCodeup}</span>
                        </c:if>
                    </div>
                </form>

            </div>
        </div>
        <div class="card bg-light mt-5">
            <div class="card-body">
                <h6>导入学生名单：</h6>
                <a href="importStudent?eid=${requestScope.exam.eid}" class="btn btn-sm bg-success text-white mt-1">继续导入</a>
            </div>
        </div>
        <div class="card bg-light mt-5">
            <div class="card-body">
                <h6>开启考试：</h6>
                <a href="examManager" class="btn btn-sm bg-success text-white mt-1">暂不开启</a>
                <a href="examStart?eid=${requestScope.exam.eid}" class="btn btn-sm bg-success text-white mt-1">立即开启</a>
            </div>
        </div>
        <div class="card bg-light mt-5">
            <div class="card-body">
                <h6 class="mb-1">删除考试：</h6>
                <a href="deleteExam?eid=${requestScope.exam.eid}" class="btn btn-sm bg-danger text-white">删除考试</a><span class="errorTip">${requestScope.errorCodeDel}</span>
            </div>
        </div>
    </div>
</body>

</html>