<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spitter</title>
        <link href="<s:url value="/resources" />/css/reset.css" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/resources" />/css/logobar.css" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/resources" />/css/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body id="spitter">

        <div id="logobar">
            <div id="sitename">Spitter</div>
            <div id="logo"></div>
        </div>
        <div id="container">

            <div id="top">
                <t:insertAttribute name="top" />
            </div>

            <div id="content">
                <t:insertAttribute name="content" />
            </div>
        </div>
    </body>
</html>
