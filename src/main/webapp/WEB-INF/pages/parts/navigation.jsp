<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
    <div class="container">
        <div class="currency-bar">
            <div class="col-lg-2">1 USD</div>
            <div class="col-lg-1">=</div>
            <div class="col-lg-2" style="text-align:left;">${usduah_currency}&nbsp;</div>
            <div class="col-lg-1">UAH</div>
            <div class="col-lg-2 clearfix">1 EUR</div>
            <div class="col-lg-1">=</div>
            <div class="col-lg-2" style="text-align:left;">${euruah_currency}&nbsp;</div>
            <div class="col-lg-1">UAH</div>
        </div>
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/"/>">${jspProperties['project.name']}</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="${index_active}">
                    <a href="<c:url value="/"/>">
                        <span class="glyphicon glyphicon-home"></span>&nbsp;
                        ${jspProperties['page_title.index']}
                    </a>
                </li>
                <li class="${newann_active}">
                    <a href="<c:url value="/new_advert"/>">
                        <span class="glyphicon glyphicon glyphicon-pencil"></span>&nbsp;
                        ${jspProperties['title.create_announcement']}
                    </a>
                </li>
                <li class="${about_active}">
                    <a href="<c:url value="/about"/>">
                        <span class="glyphicon glyphicon-info-sign"></span>&nbsp;
                        ${jspProperties['page_title.about']}
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>