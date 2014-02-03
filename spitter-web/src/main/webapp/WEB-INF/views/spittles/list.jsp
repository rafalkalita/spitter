<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="spittlesList">
    <h2>Spittle for ${spitter.username}</h2>

    <script>
        function deleteSpittle(id) {
            if(confirm("Are you sure you want to delete this spittle?")) {
                document["deleteSpittle_" + id].submit();
            }
        }
    </script>


    <c:forEach items="${spittleList}" var="spittle">
        <div class="spittle" name="${spitter.username}">
            <s:url value="/spitters/{spitterName}/spittles" var="spitter_url" >
                <s:param name="spitterName" value="${spitter.username}" />
            </s:url>

            <li>
                <span class="spittleListText">
                    <a class="spitterlink" href="${spitter_url}"><c:out value="${spitter.username}" /></a>
                    <c:out value="${spittle.message}" /><br/>
                    <small class="date"><fmt:formatDate value="${spittle.whenCreated}" pattern="hh:mma MMM d, yyyy" /></small>
                </span>
            </li>
        </div>

        <!-- a button for deleting spittle
        <sf:form method="delete" action="${spittle_url}" name="deleteSpittle_${spittle.id}" cssClass="deleteForm">
            <input type="submit" value="Delete"/>
        </sf:form>
        -->

    </c:forEach>
</div>