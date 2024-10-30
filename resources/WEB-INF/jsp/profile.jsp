<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<%@ include file="header.html"%>

<form method="post" action="/profile">
    <c:if test="${requestScope.profile.id!=null}">
        <input type="hidden" name="_method" value="PUT">
    </c:if>
    <table>
        <input type="text" name="id" hidden value="${requestScope.profile.id}">
        <tr>
            <td><h3>email</h3></td>
            <td><h3><input type="email" name="email" value="${requestScope.profile.email}"></h3></td>
        </tr>
        <tr>
            <td><h3>name</h3></td>
            <td><h3><input type="text" name="name" value="${requestScope.profile.name}"></h3></td>
        </tr>
        <tr>
            <td><h3>surname</h3></td>
            <td><h3><input type="text" name="surname" value="${requestScope.profile.surname}"></h3></td>
        </tr>
        <tr>
            <td><h3>About</h3></td>
            <td><h3><input type="text" name="about" value="${requestScope.profile.about}"></h3></td>
        </tr>
        <tr>
            <td><h3>Gender</h3></td>
            <td>
                <h3>
                    <select name="gender">
                        <option value="${requestScope.profile.gender}" selected hidden>${requestScope.profile.gender}
                        </option>
                        <c:forEach var="gender" items="${applicationScope.genders}">
                            <option value="${gender}">${gender}</option>
                        </c:forEach>
                    </select>
                </h3>
            </td>
        </tr>
    </table>
    <button type="submit">save</button>
</form>
<c:if test="${requestScope.profile.id!=null}">
    <form method="post" action="/profile">
        <input type="hidden" name="_method" value="DELETE">
        <input type="hidden" name="id" value="${requestScope.profile.id}">
        <button type="submit">delete</button>
    </form>

</c:if>

<%@ include file="footer.html"%>
</body>
</html>