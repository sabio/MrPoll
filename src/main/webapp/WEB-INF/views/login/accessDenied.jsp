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
            <span><spring:message code="dear" /> <strong>${loggedinuser}</strong> <spring:message code="notallowedtoseethispage" />  </span> <span class="floatRight"><a href="<c:url value="/logout" />"><spring:message code="logout" /></a></span>
        </div>
    </body>
</html>
