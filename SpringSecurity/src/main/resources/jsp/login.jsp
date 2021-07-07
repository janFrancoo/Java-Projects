<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
        <c:if test="${param.error != null}">
            <i>Invalid username/pwd</i>
        </c:if>
        <c:if test="${param.logout != null}">
            <i>Logged out</i>
        </c:if>
        <p>Username: <input type="text" name="username" /></p>
        <p>Password: <input type="password" name="password" /></p>
        <input type="submit" value="Login" />
    </form:form>
</body>
</html>
