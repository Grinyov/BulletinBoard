<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form role="form" class="form-horizontal" action="<c:url value="/new_advert"/>" method="post">

    <c:if test="${!empty errors}">

        <div class="margin-container clearfix">
            <div class="bs-callout bs-callout-danger">
                <h4>${jspProperties['error.new_annc_detected']}</h4>
                <c:forEach items="${errors}" var="err">
                    <p>${err}</p>
                </c:forEach>
            </div>
        </div>

    </c:if>

    <div class="col-lg-3 margin-container">
        <select name="category" class="form-control col-xs-5" required="required">
            <c:forEach items="${categories}" var="cat">
                <option value="${cat.categoryId}">${cat.name}</option>
            </c:forEach>
        </select>
    </div>

    <div class="col-lg-3 margin-container">
        <label class="checkbox-inline">
            <input type="checkbox" name="top" value="yes" ${checked}>${jspProperties['title.top']}
        </label>
    </div>

    <div class="gap">&nbsp;</div>

    <div class="col-lg-7 margin-container clearfix">
        <p><b>${jspProperties['title.short_desc']}:&nbsp;</b></p>
        <textarea name="shortDesc" class="form-control" style="height: 350px;"
                  required="required">${shortDesc}</textarea>
    </div>

    <div class="col-lg-5 margin-container">
        <p><b>${jspProperties['title.name']}:&nbsp;</b></p>
        <input type="text" name="name" class="form-control" value="${name}"
               required="required">
    </div>

    <div class="col-lg-5 margin-container">
        <p><b>${jspProperties['title.surname']}:&nbsp;</b></p>
        <input type="text" name="surname" class="form-control" value="${surname}">
    </div>


    <div class="col-lg-5 margin-container">
        <p><b>${jspProperties['title.city']}:&nbsp;</b></p>
        <input type="text" name="city" class="form-control" value="${city}">
    </div>

    <div class="col-lg-5 margin-container">
        <p><b>${jspProperties['title.phone']}:&nbsp;</b></p>
        <input type="text" name="phone" class="form-control" value="${phone}"
               required="required">
    </div>

    <div class="col-lg-5 margin-container">
        <p><b>${jspProperties['title.email']}:&nbsp;</b></p>
        <input type="text" name="email" class="form-control" value="${email}">
    </div>

    <div class="gap">&nbsp;</div>

    <div class="col-lg-3 margin-container clearfix">
        <p><b>${jspProperties['title.price']}&nbsp(&#36;)&nbsp;:</b></p>
        <input type="text" name="price" class="form-control" value="${price}"
               required="required">
    </div>

    <div class="gap">&nbsp;</div>
    <div class="gap">&nbsp;</div>

    <div class="col-lg-12 clearfix">
        <input type="submit" class="btn btn-primary btn-lg" value="${jspProperties['title.create_announcement']}">
    </div>

</form>