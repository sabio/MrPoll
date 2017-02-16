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
            
            <c:forEach items="list" var="obj" >
                ${obj.id} - ${obj.name} <br />
            </c:forEach>
            
        </div>
        
        
        
    </body>
</html>
