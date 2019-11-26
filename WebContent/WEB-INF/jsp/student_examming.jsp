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
    <link rel="stylesheet" href="css/font-awesome.css">
    <script src="js/jquery-3.4.1.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
    <title>上机考试管理系统</title>
    <script>
    $(document).ready(function(){
    	var getting = {
    			type:"get",
    	        url:"getInfo?eid=${requestScope.result.exam.eid}",
    	        success:function(res) {
    	          $('#info').html(res);
    	          setTimeout(function(){$.ajax(getting);},5000);
    	          var obj = document.getElementById("info");
                  obj.scrollTop = obj.scrollHeight;
    	        }
    	};
    	$.ajax(getting)
    });
            
   	</script>
</head>

<body>
    <%@ include file="student_header.jsp" %>
    <div class="container myrelative">
        <div class="return">
            <a href="exams">
                <i class="fa fa-angle-double-left"></i>
            </a>
        </div>
        <h3 class="mt-5 mb-5">${requestScope.result.exam.ename}</h3>
        <div class="card bg-light mt-5">
            <div class="card-body">
                <div class="form-group mt-3">
                    <textarea class="form-control" rows="5" id="info" readonly="readonly" 
                    onpropertychange="this.scrollTop = this.scrollHeight"
                    οnfοcus="this.scrollTop = this.scrollHeight" 
                    placeholder="当前没有通知"></textarea>
                </div>
                <div class="row">
                    <div class="col-6">
                        <a href="download?eid=${requestScope.result.exam.eid}" class="btn btn-sm bg-success text-white">试卷下载</a>
                    </div>
                    <div class="col-6">
                        <form style="padding-left: 6px" action="upload?eid=${requestScope.result.exam.eid}" enctype="multipart/form-data" method="post">
                            <input type="file" style="position: relative;top: 2px;" name="file">
                            <input type="submit" class="d-inline-block btn btn-sm bg-success text-white float-right" value="答案上传">
                        </form>
                      	<c:if test="${not empty requestScope.errorCodeup}">
                      		<hr class="mt-4">
                        	<span class="errorTip">${requestScope.errorCodeup}</span>
                        </c:if>
                        <c:if test="${not empty requestScope.result.submitfile}">
                        	<hr class="mt-4">
                        	<span class="successTip">已上传：${requestScope.result.submitfile}</span>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>