<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="mrpoll" /></title>
    </head>
    <body>
        <div id="mainContainer">
            <h2>${poll.name}</h2>
            
            <div id="questionsDiv">
                <c:forEach items="${questionResults}" var="questionResult" varStatus="varStatus">
                    <div class="questionDiv">
                        <label class="control-label" style="text-align: left">
                            <span class="questionNumber">
                                ${varStatus.count}.
                            </span> ${questionResult.questionText}
                        </label>
                        
                        <c:forEach items="${questionResult.choiceResults}" var="choiceResult">
                            <div class="choiceDiv control-label" style="text-align: left; cursor: none">
                                <strong>${choiceResult.choiceText}</strong> - ${choiceResult.count} <spring:message code="votes" />. 
                                <fmt:formatNumber type="number" maxFractionDigits="2" value="${choiceResult.percent}" />%
                                <div class="bar" style="width: ${choiceResult.percent}%" ></div>
                            </div>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
            
            <div class="text-center">
                <button type="button" onclick="document.location.href='<c:url value='/'  />'" class="btn-lg btn-primary "><spring:message code="back" /></button>
            </div>
            
        </div>
    </body>
</html>
