<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reg</title>
</head>
<body>

<h1>Регистрация нового пользователя</h1>

<c:if test="${not empty errorMessage}">
    <div style="color:red; font-weight: bold; margin: 30px 0px;">
            ${errorMessage}
    </div>
</c:if>

<form name='login' action="<c:url value='/reg'/>" method='POST'>
    <table>
        <tr>
            <td>Имя пользователя:</td>
            <td><input type='text' name='username' placeholder="Имя пользователя" required></td>
        </tr>
        <tr>
            <td>Пароль:</td>
            <td><input type='password' name='password' placeholder="Пароль" required></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить"></td>
        </tr>
    </table>
</form>

<div>Уже есть аккаунт? ->
    <a href="<c:url value='/login'/>">Войти</a>
</div>
</body>
</html>
