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
            
            <h2>${poll.name}</h2>

            <form:form id="form" class="form-horizontal" method="post" modelAttribute="formResponse" action="${actionUrl}">
                
                <div id="questionsDiv">
                    <c:forEach var="i" begin="0" end="${fn:length(poll.questions) - 1}"  >
                        <div class="questionDiv">
                            <div class="form-group">
                                <label>
                                    <span class="questionNumber">
                                        ${i + 1}.
                                    </span> ${poll.questions[i].questionText}
                                </label>
                                <c:forEach var="j" begin="0" end="${fn:length(poll.questions[i].choices) - 1}" >
                                    <div class="choiceDiv ">
                                        <label>
                                            <form:radiobutton path="responses[${i}].idChoice" value="${poll.questions[i].choices[j].id}"  /> ${poll.questions[i].choices[j].choiceText} 
                                        </label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <br />
                <div class="form-group">
                    <div class="col-sm-12 text-center">
                        <button type="submit" class="btn-lg btn-primary "><spring:message code="submit" /></button>
                    </div>
                </div>
            </form:form>    
        </div>
    </body>
</html>
