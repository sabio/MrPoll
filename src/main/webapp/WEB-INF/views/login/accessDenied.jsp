<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title><spring:message code="title" /></title>
    </head>
    <body>
        <div id="mainWrapper">
            <div class="login-container">
                <div class="title">
                    <spring:message code="title" />
                </div>
                <div class="login-form">
                    <span>Dear <strong>${loggedinuser}</strong>, You are not authorized to access this page.</span> <span class="floatRight"><a href="<c:url value="/logout" />">Logout</a></span>
                </div>
            </div>
        </div>
    </body>
</html>