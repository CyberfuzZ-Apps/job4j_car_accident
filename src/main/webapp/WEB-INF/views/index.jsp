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

    <title>Accident</title>
</head>
<body>

<h1 align="center">Происшествия</h1>

<div class="row">
    <ul class="nav">
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/"/>">Главная</a>
        </li>
        <li>
            ${user.username}
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/logout"/>">Выйти</a>
        </li>
    </ul>
</div>

<br>
<a href="<c:url value='/create'/>">
    <button class="btn btn-success">Добавить инцидент</button>
</a>
<br>
<br>
<table class="table table-striped">
    <thead>
    <tr>
        <td></td>
        <td style="font-weight: bolder">Название</td>
        <td style="font-weight: bolder">Тип</td>
        <td style="font-weight: bolder">Статьи</td>
        <td style="font-weight: bolder">Описание</td>
        <td style="font-weight: bolder">Адрес</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${accidents}" var="accident">
        <tr>
            <td>
                <c:if test="${user.authorities == '[ROLE_ADMIN]'}">
                    <a id="edit" href="<c:url value="/edit?id=${accident.id}"/>">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-pencil-square" viewBox="0 0 16 16">
                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                            <path fill-rule="evenodd"
                                  d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                        </svg>
                    </a>
                    <br>
                    <br>
                    <a id="delete" href="<c:url value="/delete?id=${accident.id}"/>">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-trash" viewBox="0 0 16 16">
                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                            <path fill-rule="evenodd"
                                  d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                        </svg>
                    </a>
                </c:if>
            </td>
            <td style="font-weight: bold">
                <c:out value="${accident.name}"/>
            </td>
            <td style="font-style: italic">
                <c:out value="${accident.type.name}"/>
            </td>
            <td>
                <c:forEach items="${accident.rules}" var="rule">
                    <c:out value="${rule.name}"/>
                </c:forEach>
            </td>
            <td>
                <c:out value="${accident.text}"/>
            </td>
            <td>
                <c:out value="${accident.address}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
