<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath=request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">
    <title>系统配置</title>
</head>
<body>
	 <%@ include file="admin_header.jsp" %>
	 <div class="container">
        <h3 class="mt-5">系统配置</h3>
        <div class="card bg-light mt-5">
            <div class="card-body">
                <form>
                    <div class="form-group">
                        <label>后台任务间隔时间：</label>
                        <input type="text" class="form-control" value="30" style="max-width: 300px;">
                    </div>
                    <div class="form-group">
                        <label>后台任务间隔时间：</label>
                        <input type="text" class="form-control" value="30" style="max-width: 300px;">
                    </div>
                    <div class="form-group">
                        <label>后台任务间隔时间：</label>
                        <input type="text" class="form-control" value="30" style="max-width: 300px;">
                    </div>
                    <div class="form-group">
                        <label>后台任务间隔时间：</label>
                        <input type="text" class="form-control" value="30" style="max-width: 300px;">
                    </div>
                    <div class="form-group">
                        <label>后台任务间隔时间：</label>
                        <input type="text" class="form-control" value="30" style="max-width: 300px;">
                    </div>
                    <input type="submit" class="btn btn-success" value="保存"></input>
                </form>
            </div>
        </div>
    </div>
	 <script src="js/jquery-3.4.1.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
</body>
</html>