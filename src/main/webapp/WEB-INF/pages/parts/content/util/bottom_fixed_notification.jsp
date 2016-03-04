<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="bottom-fixed-notification" class="hidden">
    <div class="fixed-bottom-btns">
        <a class="btn btn-default" id="cancel_checkboxes" href="javascript:;" role="button">
            ${jspProperties['btn.delete_cancel']}
        </a>&nbsp;
        <input type="submit" class="btn btn-danger" id="submit_checkboxes" value="${jspProperties['btn.delete']}">
    </div>
</div>