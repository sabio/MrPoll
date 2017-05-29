<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="user.userlist" /></title>
    </head>
    <body>
        <div id="mainContainer">
            <span><spring:message code="poll.notFound" /> </span> <span><a href="<c:url value="/pollList" />"><spring:message code="poll.polllist" /></a></span>
        </div>
    </body>
</html>
