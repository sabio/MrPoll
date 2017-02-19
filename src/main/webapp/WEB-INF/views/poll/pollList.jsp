<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Poll list</title>
        <script type="text/javascript">
            function changePaginationSize(){
                var pageSize = $("#selectPageSize").val();
                document.location.href = "<c:url value='/pollList'  />?pageSize="+pageSize;
            }
        </script>
    </head>
    <body>

        <div id="mainContainer">

            <c:url var="firstUrl" value="/pollList?pageNumber=1&pageSize=${pageSize}" />
            <c:url var="lastUrl" value="/pollList?pageNumber=${totalPages}&pageSize=${pageSize}" />
            <c:url var="prevUrl" value="/pollList?pageNumber=${currentIndex - 1}&pageSize=${pageSize}" />
            <c:url var="nextUrl" value="/pollList?pageNumber=${currentIndex + 1}&pageSize=${pageSize}" />
            
            <div class="pagination">
                <ul>
                <c:choose>
                    <c:when test="${currentIndex == 1}">
                        <li class="disabled"><a href="#">&lt;&lt;</a></li>
                        <li class="disabled"><a href="#">&lt;</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${firstUrl}">&lt;&lt;</a></li>
                        <li><a href="${prevUrl}">&lt;</a></li>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
                    <c:url var="pageUrl" value="/pollList?pageNumber=${i}&pageSize=${pageSize}" />
                    <c:choose>
                        <c:when test="${i == currentIndex}">
                            <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${currentIndex == totalPages}">
                        <li class="disabled"><a href="#">&gt;</a></li>
                        <li class="disabled"><a href="#">&gt;&gt;</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${nextUrl}">&gt;</a></li>
                        <li><a href="${lastUrl}">&gt;&gt;</a></li>
                    </c:otherwise>
                </c:choose>
                </ul>
                <span class="paginationSize">
                    # entries: 
                    <select id="selectPageSize" onchange="changePaginationSize();">
                        <c:forEach var="i" begin="5" end="20" step="5">
                            <option value="${i}" <c:if test="${pageSize eq i}">selected</c:if> >${i}</option>
                        </c:forEach>
                        
                    </select>
                </span>
                
            </div>

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
