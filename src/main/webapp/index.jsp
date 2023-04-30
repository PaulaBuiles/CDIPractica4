<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Bienvenido a la base de datos" %>
</h1>
<br/>
<form action="${pageContext.request.contextPath}/editar.jsp">
    <h2>Editar Base de Datos</h2>
    <div class="clearfix">
        <button value="list" type="submit">Ir a editar</button></div>
    <%--<input type="submit" value="Ir a editar">--%>
</form>
</body>
</html>