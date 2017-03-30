<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="mrpoll" /></title>
    </head>
    <body>
        <div id="mainContainer" class="text-center">
            <h2>
                <spring:message code="answerpoll.thanks" />
                <a href="<c:url value='/results/${poll.uuid}' />"><spring:message code="answerpoll.here" /></a>
            </h2>
            <br />
            <button type="button" onclick="document.location.href='<c:url value='/'  />'" class="btn-lg btn-primary "><spring:message code="back" /></button>
        </div>
    </body>
</html>
