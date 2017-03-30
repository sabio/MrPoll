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
        <div id="mainContainer">
            
            <h2><spring:message code="answerpoll.availablepolls" /></h2>
            
            <table class="table table-bordered table-striped">
                <thead>
                    <td><spring:message code="poll.pollname" /></td>
                    <td><spring:message code="poll.expirationdatetime" /></td>
                    <td></td>
                </thead>
                <tbody>
                    <c:forEach items="${polls}" var="p">
                        <tr>
                            <td>${p.name}</td>
                            <td>${p.expirationDatetime}</td>
                            <td> <a href="<c:url value='/answerPoll/${p.uuid}' />" class="btn btn-success btn-action-width"><spring:message code="answerpoll.answerthispoll" /></a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            
            
            <div class="text-center">
                <button type="button" onclick="document.location.href='<c:url value='/'  />'" class="btn-lg btn-primary "><spring:message code="back" /></button>
            </div>
            
        </div>
    </body>
</html>
