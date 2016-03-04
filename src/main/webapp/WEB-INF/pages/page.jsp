<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="parts/html_head.jsp"/>

<body>

<jsp:include page="parts/navigation.jsp"/>

<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">

        <jsp:include page="parts/main_content.jsp"/>

        <jsp:include page="parts/sidebar.jsp"/>

    </div>

    <hr>

    <jsp:include page="parts/footer.jsp"/>

</div>

<jsp:include page="parts/include_js.jsp"/>

</body>
</html>


