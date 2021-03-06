<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div>
<h2>Create a free Spitter account</h2>

<sf:form id="registration" method="POST" modelAttribute="spitter">
    <fieldset>
        <table cellspacing="0">
        <tr>
            <th><sf:label path="fullName">Full name:</sf:label></th>
            <td>
                <sf:input id="fullName" path="fullName" size="15" /><br/>
                <sf:errors path="fullName" cssClass="error" />
            </td>
        </tr>
        <tr>
            <th><sf:label path="username">Username:</sf:label></th>
            <td>
                <sf:input id="username" path="username" size="15" maxlength="15" />
                <small id="username_msg">No spaces, please.</small><br/>
                <sf:errors path="username" cssClass="error" />
            </td>
        </tr>
        <tr>
            <th><sf:label path="password">Password:</sf:label></th>
            <td>
                <sf:password id="password" path="password" size="30" showPassword="true"/>
                <small>6 characters or more (be tricky!)</small><br/>
                <sf:errors path="password" cssClass="error" />
            </td>
        </tr>
        <tr>
            <th></th>
            <td>
                <input name="commit" type="submit" value="I accept. Create my account." />
            </td>
        </tr>
        </table>
    </fieldset>
</sf:form>
</div>
