<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="sidebar-offcanvas" id="sidebar" role="navigation">

    <a class="btn btn-primary btn-lg create-ann-btn" role="button" href="<c:url value="/new_advert"/>">
        <span class="glyphicon glyphicon-pencil" style="width: 33px; margin-left: -30px;"></span>
        ${jspProperties['title.create_announcement']}
    </a>

    <div class="list-group" style="margin-top: 10px">
        <c:set var="currentCategoryId" value="${currentCategoryId}"/>
        <c:forEach items="${categories}" var="cat">
            <a href="<c:url value="${categoryUrlPrefix}category=${cat.categoryId}"/>"
               class="list-group-item ${cat.categoryId == currentCategoryId ? 'active' : ''}">${cat.name}</a>
        </c:forEach>
    </div>

    <c:if test="${!empty top_ann}">
        <div class="thumbnail">
            <div class="pic-thumb-sidebar">
                <!-- img data-src="holder.js/242x200" alt="thumb"/> -->
                <img src="<c:url value="/resources/img/pic_thumb.png"/>" style="width:120px; height: 100px;"
                     alt="thumb"/>
                <img src="<c:url value="/resources/img/pic_thumb.png"/>" style="width:120px; height: 100px;"
                     alt="thumb"/>
            </div>

            <div class="caption">

                <p>
                    <strong>${jspProperties['title.category']}:</strong>&nbsp;${top_ann.category.name}</p>

                <p>
                    <strong>${jspProperties['title.posted']}:</strong>&nbsp;${top_ann.contact.name}&nbsp;${top_ann.contact.surname}
                </p>

                <p>
                    <strong>${jspProperties['title.phone']}:</strong>&nbsp;${top_ann.contact.phone}<br/>
                    <c:if test="${!empty top_ann.contact.email}">
                        <strong>${jspProperties['title.email']}:</strong>&nbsp;
                        <a href="mailto:${top_ann.contact.email}">${top_ann.contact.email}</a>
                    </c:if>
                </p>

                <p>${jspProperties['title.added']}: ${top_ann.timeString}</p>


                <p>${top_ann.shortDesc}</p>

                <p>
                    <strong>${jspProperties['title.price']}:</strong>&nbsp;
                        ${unit}&nbsp;
                    <fmt:formatNumber type="number" maxFractionDigits="0"
                                      value="${top_ann.price * currency}"/>&nbsp;
                </p>
            </div>
        </div>
    </c:if>

</div>
