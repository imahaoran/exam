<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header class="bg-dark">
        <div class="bg-success" style="height: 4px"></div>
        <div class="container">
            <nav class="navbar navbar-expand-md bg-dark navbar-dark">
                <a class="navbar-brand" style="padding-top: 11px;padding-bottom: 12px;" href="#">EXAM</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link ${requestScope.active_0}" href="sMain">首页</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link ${requestScope.active_1}" href="exams">在线考试</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link ${requestScope.active_2}" href="grades">成绩查询</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a href="" class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown">
                                	系统管理
                            </a>
                            <div class="dropdown-menu">
                              <a class="dropdown-item" href="sPwd">修改密码</a>
                              <a class="dropdown-item" href="sLogout">退出登录</a>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="me border border-top-0">
                    ${sessionScope.student.sname}
                </div>
            </nav>
        </div>
</header>