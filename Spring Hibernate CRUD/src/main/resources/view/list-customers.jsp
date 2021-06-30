<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>List Customers</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>CRM</h2>
        </div>
    </div>

    <div id="container">
        <div id="content">
            <input type="button"
                   value="Add Customer"
                   onclick="window.location.href='add'; return false;"
                   class="add-button" />

            <table>
                <tr>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>E-mail</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="customer" items="${customers}">
                    <tr>
                        <td><a href="add?customerId=${customer.id}">${customer.firstName}</a></td>
                        <td>${customer.lastName}</td>
                        <td>${customer.email}</td>
                        <td><a href="delete?customerId=${customer.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
