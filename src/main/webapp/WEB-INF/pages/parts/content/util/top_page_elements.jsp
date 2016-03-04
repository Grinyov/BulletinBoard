<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="jumboheader padding-container">
    <!-- search -->
    <form role="form" action="<c:url value="/search"/>" method="get">
        <div class="col-lg-4">
            <div class="input-group">
                <input type="text" class="form-control" name="q" placeholder="${jspProperties['title.search']}..."
                       value="${search}">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="submit">
                        <span class="glyphicon glyphicon-search"></span>
                    </button>
                </span>
            </div>
        </div>
    </form>
    <!-- sort dropdown menu -->
    <div class="dropdown">
        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">
            <c:choose>

                <c:when test="${empty sortByTitle}">
                    ${jspProperties['title.sort']}
                </c:when>

                <c:otherwise>
                    ${sortByTitle}
                </c:otherwise>

            </c:choose>
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation">
                <a role="menuitem" tabindex="-1" href="<c:url value="${sortUrlPrefix}sort=id"/>">
                    ${jspProperties['title.sort_by_id']}
                </a>
            </li>
            <li role="presentation">
                <a role="menuitem" tabindex="-1" href="<c:url value="${sortUrlPrefix}sort=date"/>">
                    ${jspProperties['title.sort_by_date']}
                </a>
            </li>
            <li role="presentation">
                <a role="menuitem" tabindex="-1" href="<c:url value="${sortUrlPrefix}sort=price"/>">
                    ${jspProperties['title.sort_by_price']}
                </a>
            </li>
            <li class="divider"></li>
            <li role="presentation">
                <a role="menuitem" tabindex="-1" href="<c:url value="${sortUrlPrefix}sort"/>">
                    ${jspProperties['title.reset']}
                </a>
            </li>
        </ul>
    </div>

    <!-- sort dropdown menu -->
    <div class="dropdown padding-container">
        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown">
            <c:choose>

                <c:when test="${empty chooseCurrencyTitle}">
                    ${jspProperties['title.choose_currency']}
                </c:when>

                <c:otherwise>
                    ${chooseCurrencyTitle}
                </c:otherwise>

            </c:choose>
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu2">
            <li role="presentation">
                <a role="menuitem" tabindex="-1" href="<c:url value="/currency?value=UAH"/>">
                    ${jspProperties['title.currency_uah']}
                </a>
            </li>
            <li role="presentation">
                <a role="menuitem" tabindex="-1" href="<c:url value="/currency?value=USD"/>">
                    ${jspProperties['title.currency_usd']}
                </a>
            </li>
            <li role="presentation">
                <a role="menuitem" tabindex="-1" href="<c:url value="/currency?value=EUR"/>">
                    ${jspProperties['title.currency_eur']}
                </a>
            </li>
            <li class="divider"></li>
            <li role="presentation">
                <a role="menuitem" tabindex="-1" href="<c:url value="/currency"/>">
                    ${jspProperties['title.reset']}
                </a>
            </li>
        </ul>
    </div>

    <!-- top pagination -->
    <jsp:include page="pagination.jsp"/>

</div>