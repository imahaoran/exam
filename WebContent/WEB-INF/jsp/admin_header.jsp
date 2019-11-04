<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header class="bg-dark">
        <div class="bg-success" style="height: 4px"></div>
        <div class="container">
            <nav class="navbar navbar-expand-md bg-dark navbar-dark">
                <a class="navbar-brand" href="#">EXAM</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link ${requestScope.active_0}" href="aMain">首页</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link ${requestScope.active_1}" href="tManager">教师管理</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link ${requestScope.active_2}" href="sManager">学生管理</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link ${requestScope.active_3}" href="eManager">考试管理</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown">
                                	系统管理
                            </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="aConfig">系统配置</a>
                                <a class="dropdown-item" href="aLogout">退出系统</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </header>