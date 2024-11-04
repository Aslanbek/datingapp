<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>Charm Registration</title>
    <%@ include file="style.html" %>
</head>
<body>
<%@ include file="header.jsp" %>
<div>
    <form method="post" action="/registration">
        <table>
            <tr>
                <td><h3>${wordBundle.getWord("email")}</h3></td>
                <td><input type="email" name="email" placeholder="user@charm.ru"></td>
            </tr>
            <tr>
                <td><h3>${wordBundle.getWord("password")}</h3></td>
                <td><input type="password" name="password"></td>
            </tr>
        </table>
        <button type="submit">${wordBundle.getWord("save")}</button>
    </form>
    <div style="color: red">
        <c:forEach var="error" items="${errors}">
            <p>${wordBundle.getWord(error)}</p>
        </c:forEach>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>