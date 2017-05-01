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
        <div id="mainContainer" style="overflow: scroll; height: 90%">
            <span><spring:message code="generalerrormessage" /> </span>
            <br />
            Failed URL: ${url} <br />
            Exception:  ${exception.message} <br />
            <div style="color: red">
                <c:forEach items="${exception.stackTrace}" var="stackTrace">
                    ${stackTrace} 
                     <br />
                </c:forEach>
            </div>
        </div>
    </body>
</html>