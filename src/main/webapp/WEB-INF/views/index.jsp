<%--
  User: Евгений Зайцев
  Date: 26.11.2021
  Time: 13:45
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>

    <style>
        tbody {
            counter-reset: heading;
        }

        th:before {
            counter-increment: heading;
            content: counter(heading);
        }
    </style>

    <title>Accident</title>
</head>
<body>

<h1 align="center">Происшествия</h1>

<br>
<br>

<table class="table">
    <thead>
    <tr>
        <td style="font-weight: bold">#</td>
        <td style="font-weight: bold">Заголовок</td>
        <td style="font-weight: bold">Описание</td>
        <td style="font-weight: bold">Адрес</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${accidentMap}" var="accident">
        <tr>
            <th scope="row"></th>
            <td>
                <c:out value="${accident.value.name}"/>
            </td>
            <td>
                <c:out value="${accident.value.text}"/>
            </td>
            <td>
                <c:out value="${accident.value.address}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
