<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Poll list</title>
    </head>
    <body>
        
        <div id="mainContainer">
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <td>Name</td>
                        <td># of Questions</td>
                        <td>Status</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="obj" >
                        <tr>
                            <td>${obj.name}</td>
                            <td>${obj.questions.size()}</td>
                            <td>${obj.expirationDate}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            
            
        </div>
        
        
        
    </body>
</html>
