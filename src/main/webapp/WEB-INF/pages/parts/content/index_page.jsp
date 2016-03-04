<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="util/top_page_elements.jsp"/>

<c:choose>

    <c:when test="${error == 'true'}">

        <div class="padding-container clearfix">
            <div class="bs-callout bs-callout-danger margin-fix">
                <h4>${jspProperties['error.no_annc']}</h4>
            </div>
        </div>

    </c:when>

    <c:otherwise>
        <form role="form" action="<c:url value="/delete"/>" method="post"
              onsubmit="return confirm('${jspProperties['title.confirmation']}');">

            <div class="announcements-list">
                <div class="padding-container">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <td class="td-checkbox">
                                <div class="custom_checkbox"><input type="checkbox" id="checkall"/></div>
                            </td>
                            <td class="col-md-5"><b>${jspProperties['title.short_desc']}</b></td>
                            <td class="col-md-3"><b>${jspProperties['title.info']}</b></td>
                            <td class="col-md-2 text-right"><b>${jspProperties['title.price']}</b></td>
                        </tr>
                        </thead>

                        <!-- top(paid) announcements -->
                        <c:forEach items="${top_annc}" var="ann">
                            <tr class="warning">

                                <td class="td-checkbox">
                                    <div class="custom_checkbox"><input type="checkbox" name="checks"
                                                                        value="${ann.id}"/></div>
                                </td>

                                <td class="col-md-5">
                                    <p>${ann.shortDesc}&nbsp;
                                        <span class="label label-default">${jspProperties['title.top_flag']}</span>
                                    </p>

                                    <p><strong>${jspProperties['title.category']}:</strong>&nbsp;${ann.category.name}
                                    </p>

                                    <p>
                                        <a class="btn btn-default" href="<c:url value="?photo=${ann.id}"/>"
                                           role="button"
                                           disabled>
                                                ${jspProperties['btn.photo']}&nbsp;&raquo;
                                        </a>&nbsp;
                                        <a class="btn btn-danger" href="<c:url value="/delete?id=${ann.id}"/>"
                                           role="button"
                                           onclick="return confirm('${jspProperties['title.confirmation']}');">
                                                ${jspProperties['btn.delete']}
                                        </a>

                                    </p>
                                </td>
                                <td class="col-md-3">
                                    <p>
                                        <strong>${jspProperties['title.posted']}:</strong>&nbsp;${ann.contact.name}&nbsp;${ann.contact.surname}
                                    </p>

                                    <p>
                                        <strong>${jspProperties['title.phone']}:&nbsp;</strong>${ann.contact.phone}<br/>
                                        <c:if test="${!empty ann.contact.email}">
                                            <strong>${jspProperties['title.email']}:&nbsp;</strong>
                                            <a href="mailto:${ann.contact.email}">${ann.contact.email}</a>
                                        </c:if>
                                    </p>

                                    <p>${jspProperties['title.added']}: ${ann.timeString}</p>
                                </td>
                                <td class="col-md-2 text-right">
                                    <strong>${unit}&nbsp;
                                        <fmt:formatNumber type="number" maxFractionDigits="0"
                                                          value="${ann.price * currency}"/>&nbsp;
                                    </strong>
                                </td>
                            </tr>
                        </c:forEach>
                        <!-- /top(paid) announcements -->
                    </table>
                </div>

                <!-- regular (free) announcements -->
                <div class="padding-container">
                    <table class="table table-hover">
                        <c:forEach items="${regular_annc}" var="ann">
                            <tr>

                                <td class="td-checkbox">
                                    <div class="custom_checkbox"><input type="checkbox" name="checks"
                                                                        value="${ann.id}"/></div>
                                </td>

                                <td class="col-md-5">
                                    <p>${ann.shortDesc}</p>

                                    <p><strong>${jspProperties['title.category']}:</strong>&nbsp;${ann.category.name}
                                    </p>

                                    <p>
                                        <a class="btn btn-default" href="<c:url value="?photo=${ann.id}"/>"
                                           role="button"
                                           disabled>
                                                ${jspProperties['btn.photo']}&nbsp;&raquo;
                                        </a>&nbsp;
                                        <a class="btn btn-danger" href="<c:url value="/delete?id=${ann.id}"/>"
                                           role="button"
                                           onclick="return confirm('${jspProperties['title.confirmation']}');">
                                                ${jspProperties['btn.delete']}
                                        </a>
                                    </p>
                                </td>
                                <td class="col-md-3">
                                    <p>
                                        <strong>${jspProperties['title.posted']}:</strong>&nbsp;${ann.contact.name}&nbsp;${ann.contact.surname}
                                    </p>

                                    <p>
                                        <strong>${jspProperties['title.phone']}:&nbsp;</strong>${ann.contact.phone}<br/>
                                        <c:if test="${!empty ann.contact.email}">
                                            <strong>${jspProperties['title.email']}:&nbsp;</strong>
                                            <a href="mailto:${ann.contact.email}">${ann.contact.email}</a>
                                        </c:if>
                                    </p>

                                    <p>${jspProperties['title.added']}: ${ann.timeString}</p>
                                </td>
                                <td class="col-md-2 text-right">
                                    <strong>${unit}&nbsp;
                                        <fmt:formatNumber type="number" maxFractionDigits="0"
                                                          value="${ann.price * currency}"/>&nbsp;
                                    </strong>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>

            <jsp:include page="util/bottom_fixed_notification.jsp"/>

        </form>

        <!-- bottom pagination -->
        <div class="padding-container">
            <jsp:include page="util/pagination.jsp"/>
        </div>

    </c:otherwise>

</c:choose>
