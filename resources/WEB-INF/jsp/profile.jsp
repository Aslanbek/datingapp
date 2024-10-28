<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<%@ include file="header.html"%>
<table>
    <tr>
        <td><h3>email</h3></td>
        <td><h3>${requestScope.profile.email}</h3></td>
    </tr>
    <tr>
        <td><h3>name</h3></td>
        <td><h3>${requestScope.profile.name}</h3></td>
    </tr>
    <tr>
        <td><h3>surname</h3></td>
        <td><h3>${requestScope.profile.surname}</h3></td>
    </tr>
    <tr>
        <td><h3>About</h3></td>
        <td><h3>${requestScope.profile.about}</h3></td>
    </tr>
</table>
<%@ include file="footer.html"%>
</body>
</html>