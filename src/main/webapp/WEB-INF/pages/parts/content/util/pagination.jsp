<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="pagination">
    <c:if test="${pagesCount > 1}">
        <c:set var="currentPageId" value="${currentPageId}"/>

        <c:if test="${pageBackward}">
            <li><a href="<c:url value="${pageUrlPrefix}page=${currentPageId - 1}"/>" title="backward">&laquo;</a></li>
        </c:if>

        <c:forEach var="i" begin="1" end="${pagesCount}">
            <li ${i == currentPageId ? 'class = "active"' : ''}>
                <a href="<c:url value="${pageUrlPrefix}page=${i}"/>" title="to page ${i}">
                        ${i}
                    <span class="sr-only">(current)</span>
                </a>
            </li>
        </c:forEach>

        <c:if test="${pageForward}">
            <li><a href="<c:url value="${pageUrlPrefix}page=${currentPageId + 1}"/>" title="forward">&raquo;</a></li>
        </c:if>
    </c:if>
</ul>