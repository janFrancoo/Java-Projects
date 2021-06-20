<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Process Form</title>
</head>
<body>
    <h3>Name: ${student.firstName} ${student.lastName}</h3>
    <br />
    <h3>Age: ${student.age}</h3>
    <br />
    <h3>Student no: ${student.studentNo}</h3>
    <br />
    <h3>Country: ${student.country}</h3>
    <br />
    <h3>Favorite lang: ${student.favoriteLanguage}</h3>
    <br />
    Operating Systems:
    <br />
    <ul>
        <c:forEach var="os" items="${student.operatingSystems}">
            <li>${os}</li>
        </c:forEach>
    </ul>
    <img src="${pageContext.request.contextPath}/resources/img/fish.png" />
</body>
</html>
