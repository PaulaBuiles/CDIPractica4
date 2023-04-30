<%@ page import="cue.clothingjakarta.clothingjakarta.model.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.cdicontextos.model.Product" %><%--
  Created by IntelliJ IDEA.
  User: Paula
  Date: 6/03/2023
  Time: 7:26 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List employees</title>

</head>
<body>
<h1>List employees</h1>
<%
    List<Product> dBClients = (List)request.getSession().getAttribute("dBClients");
    int cont=1;
    for (Client client : dBClients){

%>
<p><b>User: <%=cont%></b></p>
<p>Nombre: <%=client.getName()%></p>
<p>Usuario: <%=client.getUsername()%></p>
<p>Password: <%=client.getPassword()%></p>
<p>ConfirmPassword: <%=client.getConfirm_password()%></p>
<p>Email: <%=client.getEmail()%></p>
<p>Direccion: <%=client.getAddress()%></p>
<p>------------------------</p>
<%cont=cont+1;%>

<%}%>
</body>
</html>
