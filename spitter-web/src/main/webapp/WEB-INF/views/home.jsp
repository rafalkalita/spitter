<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body id="spitter">
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
                        <small><fmt:formatDate value="${spittle.whenCreated}" pattern="hh:mma MMM d, yyyy" /></small>
                    </span>
                </li>
            </div>
        </c:forEach>
    </body>
</html>
