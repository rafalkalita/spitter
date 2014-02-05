<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="msg">
    <h3>Messages:</h3>
    <c:if test="${param.errormessage != null}">
        <p>
            Invalid username and password.
        </p>
    </c:if>

    <c:if test="${param.message != null}">
        <p>
            ${param.message}
        </p>
    </c:if>
</div>