<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="poll.polllist" /></title>
    </head>
    <body>
        <div id="mainContainer">
            <c:url var="firstUrl" value="/pollList?pageNumber=1&pageSize=${pageSize}" />
            <c:url var="lastUrl" value="/pollList?pageNumber=${totalPages}&pageSize=${pageSize}" />
            <c:url var="prevUrl" value="/pollList?pageNumber=${currentIndex - 1}&pageSize=${pageSize}" />
            <c:url var="nextUrl" value="/pollList?pageNumber=${currentIndex + 1}&pageSize=${pageSize}" />
            
            <h2><spring:message code="poll.polllist" /></h2>
            
            <c:if test="${not empty msg}">
                <div class="alert alert-${css} alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <strong>${msg}</strong>
                </div>
            </c:if>
            
            <button type="button" id="addButton" class="add-button btn btn-primary btn-action-width" ><spring:message code="poll.addnewpoll" /></button>

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
                    <spring:message code="numentries" />: 
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
                        <td><spring:message code="poll.pollname" /></td>
                        <td><spring:message code="poll.numofquestions" /></td>
                        <td><spring:message code="status" /></td>
                        <td></td>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty list}">
                            <tr>
                                <td colspan="3"><spring:message code="norecordsfound" /></td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${list}" var="obj" >
                                <tr>
                                    <td>${obj.name}</td>
                                    <td>${obj.questions.size()}</td>
                                    <td>${obj.expirationDatetime}</td>
                                    <td>
                                        <a href="<c:url value='/editPoll/${obj.id}' />" class="btn btn-success btn-action-width"><spring:message code="edit" /></a>
                                        <a href="<c:url value='/deletePoll/${obj.id}' />" class="btn btn-danger btn-action-width"><spring:message code="delete" /></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
        <script type="text/javascript">
            $("#selectPageSize").change(function(){
                var pageSize = $(this).val();
                document.location.href = "<c:url value='/pollList'  />?pageSize=" + pageSize;
            });
            
            $("#addButton").click(function () {
                document.location.href = "<c:url value='/addPoll'  />";
            });
        </script>
    </body>
</html>
