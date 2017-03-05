<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <c:choose>
            <c:when test="${userForm['new']}">
                <title><spring:message code="user.addnewuser" /></title>
            </c:when>
            <c:otherwise>
                <title><spring:message code="user.updateuser" /></title>
            </c:otherwise>
        </c:choose>

    </head>
    <body>
        <div id="mainContainer">

            <c:choose>
                <c:when test="${userForm['new']}">
                    <h2><spring:message code="user.addnewuser" /></h2>
                </c:when>
                <c:otherwise>
                    <h2><spring:message code="user.updateuser" /></h2>
                </c:otherwise>
            </c:choose>

            <form:form id="form" class="form-horizontal" method="post" modelAttribute="formUser">
                <!--
                <spring:bind path="*">
                    <c:if test="${status.error}">
                        <div class="alert alert-danger" style="text-align: center;">
                            <spring:message code="formHasErrors" />
                        </div>
                    </c:if>
                </spring:bind>
                -->
                
                
                <form:errors path="" element="div" cssClass="alert alert-warning" cssStyle="text-align: center;"  />

                <spring:bind path="email">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-2 control-label"><spring:message code="email" /></label>
                        <div class="col-sm-9">
                            <spring:message code="email" var="emailPlaceholder"/> 
                            <form:input path="email" class="form-control" placeholder='${emailPlaceholder}' />
                            <form:errors path="email" class="control-label" />
                        </div>
                    </div>
                </spring:bind>

                <spring:bind path="username">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-2 control-label"><spring:message code="username" /></label>
                        <div class="col-sm-9">
                            <spring:message code="username" var="usernamePlaceholder"/> 
                            <form:input path="username" class="form-control" placeholder='${usernamePlaceholder}' />
                            <form:errors path="username" class="control-label" />
                        </div>
                    </div>
                </spring:bind>

                <spring:bind path="password">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-2 control-label"><spring:message code="password" /></label>
                        <div class="col-sm-9">
                            <spring:message code="password" var="passwordPlaceholder"/> 
                            <form:password path="password" class="form-control" placeholder='${passwordPlaceholder}' />
                            <form:errors path="password" class="control-label" />
                        </div>
                    </div>
                </spring:bind>

                <spring:bind path="confirmPassword">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-2 control-label"><spring:message code="user.confirmPassword" /></label>
                        <div class="col-sm-9">
                            <spring:message code="user.confirmPassword" var="confirmPasswordPlaceholder"/> 
                            <form:password path="confirmPassword" class="form-control" placeholder='${confirmPasswordPlaceholder}' />
                        </div>
                    </div>
                </spring:bind>

                <div class="form-group">
                    <label class="col-sm-2 control-label"><spring:message code="user.enabled" /></label>
                    <div class="col-sm-9">
                        <spring:message code="user.enabled" var="enabledPlaceholder" />
                        <form:checkbox path="enabled" class="checkbox checkbox-primary" placeholder='${enabledPlaceholder}'  />
                    </div>
                </div>    


                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-9">
                        <c:choose>
                            <c:when test="${formUser['new']}">
                                <button type="submit" class="btn-lg btn-primary pull-right"><spring:message code="add" /></button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" class="btn-lg btn-primary pull-right"><spring:message code="update" /></button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </form:form>

        </div>
    </body>
</html>
