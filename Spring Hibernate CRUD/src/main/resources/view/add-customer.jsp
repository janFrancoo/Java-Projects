<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Customer</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/add-customer.css">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>CRM</h2>
        </div>
    </div>

    <div id="container">
        <h3>Add Customer</h3>
        <form:form modelAttribute="customer" method="POST">
            <form:hidden path="id" />

            <table>
                <tbody>
                    <tr>
                        <td><label>First name: </label></td>
                        <td><form:input path="firstName" /></td>
                    </tr>
                    <tr>
                        <td><label>Last name: </label></td>
                        <td><form:input path="lastName" /></td>
                    </tr>
                    <tr>
                        <td><label>E-mail: </label></td>
                        <td><form:input path="email" /></td>
                    </tr>
                    <tr>
                        <td><label></label></td>
                        <td>
                            <button type="submit" formaction="/springcrud/add">Save</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form:form>
    </div>
</body>
</html>