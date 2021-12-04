<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create</title>
</head>
<body>

<h1>Новое происшествие</h1>

<div class="row">
    <a class="nav-link" href="<c:url value="/"/>">Главная</a>
    &nbsp;&nbsp;
    ${user.username}

    <a class="nav-link" href="<c:url value="/logout"/>">Выйти</a>
</div>
<br>
<br>
<form action="<c:url value='/save'/>" method='POST'>
    <table style="font-size: larger">
        <tr>
            <td>Название:</td>
            <td>
                <input type='text' name='name' placeholder="Введите название" required>
            </td>
        </tr>
        <tr>
            <td>Тип:</td>
            <td>
                <select name="type.id" required>
                    <c:forEach var="type" items="${types}">
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
        </tr>
        <tr>
            <td>Статьи:</td>
            <td>
                <select name="rIds" multiple required>
                    <c:forEach var="rule" items="${rules}">
                        <option value="${rule.id}">${rule.name}</option>
                    </c:forEach>
                </select>
        </tr>
        <tr>
            <td>Описание:</td>
            <td>
                <textarea name="text" rows="5" placeholder="Подробное описание инцидента" required></textarea>
            </td>
        </tr>
        <tr>
            <td>Адрес:</td>
            <td><input type='text' name='address' placeholder="Адрес инцидента" required></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить"/></td>
        </tr>
    </table>
</form>

</body>
</html>
