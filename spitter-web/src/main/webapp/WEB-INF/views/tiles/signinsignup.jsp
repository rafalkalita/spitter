<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div>
    <sec:authorize access="!isAuthenticated()">
        <div class="msg">
          <h3>Please Sign In!</h3>
        </div>
        <form id="loginForm" name='loginForm' action="<s:url value='j_spring_security_check' />" method='POST'>
            <fieldset>
                <div>
                    <label for="j_username">Username or Email</label>
                    <input id="email" type="text" name="j_username"/>
                </div>
                <div>
                    <label for="j_password">Password</label>
                    <input id="pass" type="password" name="j_password"/>
                </div>
                <input id="remember_me" type="checkbox" value="1" name="_spring_security_remember_me"/>
                <label for="remember_me">Remember me</label>
                <br /><input id="submit" type="submit" value="Sign In!" name="commit"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </fieldset>
        </form>
        <div class="notify">
            Want an account?<br/>
            <a class="join" href="<s:url value="/spitters?new"/>">Join for Free!</a><br/>
            It's fast and easy!
        </div>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <span>User: <sec:authentication property="principal.username" /></span>
        <br/>
        <form id="logoutForm" name='logoutForm' action="<s:url value='/logout' />" method='POST'>
    	    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    	    <input id="submit" type="submit" value="Logout" name="commit"/>
    	</form>
    </sec:authorize>
</div>