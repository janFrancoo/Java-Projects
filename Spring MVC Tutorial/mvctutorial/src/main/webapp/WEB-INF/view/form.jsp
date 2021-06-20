<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Form</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <form:form action="process-form" modelAttribute="student">
        <form:input path="firstName" placeholder="Enter first name" />
        <form:errors path="firstName" cssClass="error" /> 
        <br />

        <form:input path="lastName" placeholder="Enter last name" />
        <br />

        <form:input path="age" placeholder="Enter your age" />
        <form:errors path="age" cssClass="error" /> 
        <br />

        <form:input path="studentNo" placeholder="Enter your student no" />
        <form:errors path="studentNo" cssClass="error" /> 
        <br />

        <form:select path="country">
            <!--
            <form:option value="Brazil" label="Brazil" />
            <form:option value="England" label="England" />
            <form:option value="Turkey" label="Turkey" /> -->
            <form:options items="${student.countryOptions}" />
        </form:select>
        <br />

        Fav lang: <br />
        Java <form:radiobutton path="favoriteLanguage" value="Java" />
        JS <form:radiobutton path="favoriteLanguage" value="JS" />
        C# <form:radiobutton path="favoriteLanguage" value="C#" />
        <br />

        OS: <br />
        Windows <form:checkbox path="operatingSystems" value="Windows" />
        Linux <form:checkbox path="operatingSystems" value="Linux" />
        MAC <form:checkbox path="operatingSystems" value="MAC" />

        <input type="submit" value="Submit" />
    </form:form>
</body>
</html>
