<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <c:choose>
            <c:when test="${poll['new']}">
                <title><spring:message code="poll.addnewpoll" /></title>
            </c:when>
            <c:otherwise>
                <title><spring:message code="poll.updatepoll" /></title>
            </c:otherwise>
        </c:choose>

    </head>
    <body>
        <div id="mainContainer">
            
            <c:choose>
                <c:when test="${poll['new']}">
                    <h2><spring:message code="poll.addnewpoll" /></h2>
                    <spring:url value="/addPoll" var="actionUrl" />
                </c:when>
                <c:otherwise>
                    <h2><spring:message code="poll.updatepoll" /></h2>
                    <spring:url value="/editPoll" var="actionUrl" />
                </c:otherwise>
            </c:choose>

            <form:form id="form" class="form-horizontal" method="post" modelAttribute="poll" action="${actionUrl}">
                <form:hidden path="id" />
                <form:hidden path="uuid" />
                
                <spring:bind path="poll">
                    <c:if test="${status.error}">
                        <div class="alert alert-danger" style="text-align: center;">
                            <form:errors path="" class="control-label" />
                        </div>
                    </c:if>
                </spring:bind>
                
                <spring:bind path="name">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-2 control-label"><spring:message code="name" /></label>
                        <div class="col-sm-9">
                            <spring:message code="name" var="namePlaceholder"/> 
                            <form:input path="name" class="form-control" placeholder='${namePlaceholder}' autocomplete="off" />
                            <form:errors path="name" class="control-label" />
                        </div>
                    </div>
                </spring:bind>
                
                <spring:bind path="expirationDatetime">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-2 control-label"><spring:message code="poll.expirationdatetime" /></label>
                        <div class="col-sm-9">
                            <spring:message code="poll.expirationdatetime" var="expirationDatetimePlaceholder"/> 
                            
                            <form:input path="expirationDatetime" class="form-control" placeholder='${expirationDatetimePlaceholder}' autocomplete="off" />
                            
                            <form:errors path="expirationDatetime" class="control-label" />
                        </div>
                    </div>
                </spring:bind>
                
                <h2><spring:message code="poll.questions" />: <button id="addQuestionBtn" class="btn btn-success btn-action-width" ><spring:message code="poll.addquestion" /></button></h2>
                
                <div id="questionsDiv">
                    <c:if test="${not empty poll.questions}">
                        <c:forEach var="i" begin="0" end="${fn:length(poll.questions) - 1}"  >
                            <div class="questionDiv ${ (i+1) % 2 == 0 ? 'even' : 'odd' }">
                                <div class="form-group">
                                    <c:if test="${poll.questions[i].id ne null}">
                                        <form:hidden path="questions[${i}].id" />
                                    </c:if>
                                    <spring:bind path="questions[${i}].questionText">
                                        <label class="col-sm-2 control-label ">Question <span class="questionNumber">${i + 1}</span></label>

                                    
                                        <div class="col-sm-8 form-group ${status.error ? 'has-error' : ''}">
                                            <form:textarea path="questions[${i}].questionText" class="form-control" />
                                            <form:errors path="questions[${i}].questionText" class="control-label" />
                                        </div>
                                        <div class="col-sm-1 form-group">
                                            <span class="glyphicon glyphicon-remove removeQuestionIcon" />
                                        </div>
                                    </spring:bind>


                                    <div class="col-sm-12 form-group text-center">
                                        <button class="btn btn-success btn-action-width addChoiceBtn" ><spring:message code="poll.addchoice" /></button>
                                    </div>

                                    <c:if test="${not empty poll.questions[i].choices}">
                                        <c:forEach var="j" begin="0" end="${fn:length(poll.questions[i].choices) - 1}" >
                                            <spring:bind path="questions[${i}].choices[${j}].choiceText">    
                                                <div class="choiceDiv form-group ${status.error ? 'has-error' : ''}">
                                                    <c:if test="${poll.questions[i].choices[j].id ne null}">
                                                        <form:hidden path="questions[${i}].choices[${j}].id" />
                                                    </c:if>
                                                    <label class="col-sm-3 control-label">Choice <span class="choiceNumber">${j + 1}</span></label>
                                                    <div class="col-sm-6 ">
                                                        <form:input path="questions[${i}].choices[${j}].choiceText" class="form-control" autocomplete="off" />
                                                        <form:errors path="questions[${i}].choices[${j}].choiceText" class="control-label" />
                                                    </div>
                                                    <div class="col-sm-1 form-group">
                                                        <span class="glyphicon glyphicon-remove removeChoiceIcon" />
                                                    </div>
                                                </div>
                                            </spring:bind>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
                
                
                
                
                
                <script id="questionTemplate" type="questionTemplate">
                    <div class="questionDiv">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Question <span class="questionNumber">replaceMe</span></label>
                            <div class="col-sm-8 form-group">
                                <textarea  class="form-control" cols="100"></textarea>
                            </div>
                            <div class="col-sm-1 form-group">
                                <span class="glyphicon glyphicon-remove removeQuestionIcon" />
                            </div>
                            
                            <div class="col-sm-12 form-group text-center">
                                <button class="btn btn-success btn-action-width addChoiceBtn" ><spring:message code="poll.addchoice" /></button>
                            </div>
                            
                            <div class="choiceDiv form-group">
                                <label class="col-sm-3 control-label">Choice <span class="choiceNumber">replaceMe</span></label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" autocomplete="off" />
                                </div>
                                <div class="col-sm-1 form-group">
                                    <span class="glyphicon glyphicon-remove removeChoiceIcon" />
                                </div>
                            </div>
                            <div class="choiceDiv form-group">
                                <label class="col-sm-3 control-label">Choice <span class="choiceNumber">replaceMe</span></label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" autocomplete="off" />
                                </div>
                                <div class="col-sm-1 form-group">
                                    <span class="glyphicon glyphicon-remove removeChoiceIcon" />
                                </div>
                            </div>
                        </div>
                    </div>
                </script>
                
                <script id="choiceTemplate" type="choiceTemplate">
                    <div class="choiceDiv form-group">
                        <label class="col-sm-3 control-label">Choice <span class="choiceNumber">replaceMe</span></label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" autocomplete="off" />
                        </div>
                        <div class="col-sm-1 form-group">
                            <span class="glyphicon glyphicon-remove removeChoiceIcon" />
                        </div>
                    </div>
                </script>
                
                
                
                <div class="form-group">
                    <div class="col-sm-12 text-center">
                        <button type="button" onclick="document.location.href='<c:url value='/pollList'  />'" class="btn-lg btn-primary "><spring:message code="back" /></button>
                        <c:choose>
                            <c:when test="${poll['new']}">
                                <button type="submit" class="btn-lg btn-primary "><spring:message code="add" /></button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" class="btn-lg btn-primary"><spring:message code="update" /></button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </form:form>    
        </div>
        
        
        <script type="text/javascript">
            $( document ).ready(function() {
                $('#expirationDatetime').datetimepicker({format: 'Y-m-d H:i'});
                
                $("#questionsDiv .questionDiv").each(function(index, value){
                    addClickListeners(value);
                });
                
                $("#addQuestionBtn").click(function(event){
                    event.preventDefault();
                    
                    $("#questionsDiv").append($("#questionTemplate").html());
                    updateQuestionsNumbersAndAttributes();
                    
                    addClickListeners($("#questionsDiv .questionDiv:last-child"));
                });
            });
            
            
            
            function addClickListeners(questionObj){
                $(questionObj).find(".removeQuestionIcon").on('click',function(){
                    $(this).closest(".questionDiv").slideUp("normal", function() { 
                        $(this).remove();
                        updateQuestionsNumbersAndAttributes();
                    });
                });

                $(questionObj).find(".removeChoiceIcon").on('click',function(){
                    $(this).closest(".choiceDiv").remove();
                    updateQuestionsNumbersAndAttributes();
                });

                $(questionObj).find(".addChoiceBtn").on('click',function(event){
                    event.preventDefault();
                    $(this).closest(".questionDiv .form-group:first-child").append($("#choiceTemplate").html());

                    $(this).closest(".questionDiv").find(".removeChoiceIcon:last").on('click', function(){
                        $(this).closest(".choiceDiv").remove();
                        updateQuestionsNumbersAndAttributes();
                    });

                    updateQuestionsNumbersAndAttributes();
                });
            }
            
            
            function updateQuestionsNumbersAndAttributes(){
                $("#questionsDiv .questionDiv").each(function(index, value){
                    $(value).find(".questionNumber").html(index + 1);
                    $(value).find("input[type=hidden]:first-child").attr("id","questions"+index+".id");
                    $(value).find("input[type=hidden]:first-child").attr("name","questions["+index+"].id");
                    
                    $(value).find("textarea:first-child").attr("id","questions"+index+".questionText");
                    $(value).find("textarea:first-child").attr("name","questions["+index+"].questionText");
                    
                    $(value).find(".choiceNumber").each(function(index2, value2){
                        $(value2).html(index2 + 1);
                    });
                    
                    $(value).find(".choiceDiv").each(function(index2, value2){
                        $(value2).find("input[type=hidden]:first-child").attr("id","questions"+index+".choices"+index2+".id");
                        $(value2).find("input[type=hidden]:first-child").attr("name","questions["+index+"].choices["+index2+"].id");
                        
                        $(value2).find("input[type=text]:first-child").attr("id","questions"+index+".choices"+index2+".choiceText");
                        $(value2).find("input[type=text]:first-child").attr("name","questions["+index+"].choices["+index2+"].choiceText");
                    });
                });
                
                $("#questionsDiv .questionDiv:last-child").removeClass("even odd");
                $("#questionsDiv .questionDiv:last-child").addClass( $("#questionsDiv .questionDiv").length % 2 === 0 ? "even" : "odd" );
            }
            
            
            
        </script>
    </body>
</html>
