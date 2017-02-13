<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
        <title>Login page</title>
        <link href="<c:url value='/static/css/login.css' />" rel="stylesheet"></link>
        <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
        <sitemesh:write property='head'/>
    </head>
    <body>
        <sitemesh:write property='body'/>
    </body>
</html>
