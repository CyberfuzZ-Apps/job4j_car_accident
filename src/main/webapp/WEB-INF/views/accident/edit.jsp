<%--
  User: Евгений Зайцев
  Date: 29.11.2021
  Time: 13:32
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>

<form  action="<c:url value='/save?id=${accident.id}'/>" method='POST'>
    <table style="font-size: larger">
        <tr>
            <td>Название:</td>
            <td>
                <input type='text' name='name' placeholder="Введите название"
                       value="${accident.name}" required>
            </td>
        </tr>
        <tr>
            <td>Описание:</td>
            <td>
                <textarea name="text" rows="5" placeholder="Подробное описание инцидента" required>
                    <c:out value="${accident.text}"/>
                </textarea>
            </td>
        </tr>
        <tr>
            <td>Адрес:</td>
            <td><input type='text' name='address' placeholder="Адрес инцидента"
                       value="${accident.address}" required></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить"/></td>
        </tr>
    </table>
</form>

</body>
</html>