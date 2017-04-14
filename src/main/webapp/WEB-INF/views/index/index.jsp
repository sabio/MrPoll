<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="poll.polllist" /></title>
    </head>
    <body>
        <div id="mainContainer" >
            <h2><spring:message code="hello" /> <sec:authentication property="principal.username" /></h2>
            <div class="text-center">
                <h3><spring:message code="welcomemessage" /></h3>
                <br />
                <div style="max-width: 500px; margin: auto">
                    <spring:message code="indexInstructions" />
                </div>
            </div>

        </div>
    </body>
</html>
