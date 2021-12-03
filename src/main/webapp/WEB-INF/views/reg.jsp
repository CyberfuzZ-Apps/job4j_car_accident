<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reg</title>
</head>
<body>

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
            <td colspan='2'><input name="submit" type="submit" value="submit"></td>
        </tr>
    </table>
</form>

</body>
</html>
