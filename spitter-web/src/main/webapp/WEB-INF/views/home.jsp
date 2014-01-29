<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
    <h1>A global community of people who are spitting out their thoughts!</h1>
    <h3>Look at what these people are spitting right now...</h3>

    <c:forEach var="spittle" items="${spittles}">

        <div class="spittle" name="${spittle.spitter.username}">
            <s:url value="/spitters/{spitterName}" var="spitter_url" >
                <s:param name="spitterName" value="${spittle.spitter.username}" />
            </s:url>

            <li>
                <span class="spittleListText">
                    <a href="${spitter_url}"><c:out value="${spittle.spitter.username}" /></a>
                    <c:out value="${spittle.message}" /><br/>
                    <small class="date"><fmt:formatDate value="${spittle.whenCreated}" pattern="hh:mma MMM d, yyyy" /></small>
                </span>
            </li>
        </div>
    </c:forEach>
</div>
