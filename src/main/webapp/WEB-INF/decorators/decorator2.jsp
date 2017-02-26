<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
        <link href="<c:url value='/static/css/site.css' />" rel="stylesheet"></link>
        <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet" />
        <script type="text/javascript" src="<c:url value='/static/js/jquery.js' />" ></script>
        <script type="text/javascript" src="<c:url value='/static/js/bootstrap.js' />" ></script>
        <sitemesh:write property='head' />
    </head>
<body>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<c:url value='/' />">Mr. Poll</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="<c:url value='/' />">Home</a></li>
                    <li><a href="#about">My info</a></li>
                    <li><a href="<c:url value='/pollList' />">My polls</a></li>
                    <li><a href="#contact">Reports</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="../navbar/">About</a></li>
                    <li><a href="<c:url value='/logout' />"><spring:message code="logout" /></a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div id="mainWrapper">
        <sitemesh:write property='body'/>
    </div>
</body>
</html>
