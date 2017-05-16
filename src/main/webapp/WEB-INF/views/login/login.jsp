<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title><spring:message code="title" /></title>
    </head>
    <body>
        <div id="mainWrapper">
            
            <div class="login-container">
                <div class="title">
                    <spring:message code="title" />
                </div>
                <div class="login-form">
                    <c:url var="loginUrl" value="/login" />
                    <c:url var="availablePolls" value="/availablePolls" />
                    <form id="loginForm" action="${loginUrl}" method="post" class="form-horizontal">
                        <c:if test="${param.error != null}">
                            <div class="alert alert-danger">
                                <p><spring:message code="login.invalidusernamepassword" /></p>
                            </div>
                        </c:if>
                        <c:if test="${param.logout != null}">
                            <div class="alert alert-success">
                                <p><spring:message code="login.loggedout" /></p>
                            </div>
                        </c:if>
                        <div class="input-group input-sm">
                            <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                            <input type="text" class="form-control" id="username" name="username" placeholder="<spring:message code="login.enterusername" />" required>
                        </div>
                        <div class="input-group input-sm">
                            <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
                            <input type="password" class="form-control" id="password" name="password" placeholder="<spring:message code="login.enterpassword" />" required>
                        </div>
                        <div class="input-group input-sm">
                            <div class="checkbox">
                                <label><input type="checkbox" id="rememberme" name="remember-me"><spring:message code="login.rememberme" /></label>  
                            </div>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />

                        <div class="form-actions">
                            <button type="submit" class="btn btn-block btn-primary btn-default"><spring:message code="login" /></button>
                        </div>
                    </form>
                </div>
                <br />
                <div class="text-center">
                    <a href="${availablePolls}"><spring:message code="answerpoll.availablepolls" /></a>
                </div>

            </div>
        </div>
        <script type="text/javascript">
            $( document ).ready(function() {
                $('body').keydown(function(e) {
                    if (e.keyCode === 13) {
                        e.preventDefault();
                        $("#loginForm").submit();
                    }
                });
            });
        </script>
    </body>
</html>