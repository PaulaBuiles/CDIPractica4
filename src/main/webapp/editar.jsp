<%--
  Created by IntelliJ IDEA.
  User: Paula
  Date: 30/04/2023
  Time: 11:41 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Editar Base de Datos</title>
  <style>
    header {
      background-color: #ff69b4; /* rosa */
      color: white;
      padding: 10px;
      text-align: center;
    }

    .form-container {
      width: 50%;
      margin: 50px auto;
      background-color: #fdfdfd; /* blanco */
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* sombra */
    }

    input[type=text],
    select {
      width: 100%;
      padding: 12px 20px;
      margin: 8px 0;
      box-sizing: border-box;
      border: none; /* sin borde */
      border-radius: 4px;
      background-color: #f2f2f2; /* gris claro */
      font-size: 16px;
      color: #333; /* negro */
    }

    input[type=submit] {
      background-color: #ff69b4; /* rosa */
      color: white;
      padding: 12px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      float: right;
      font-size: 16px;
      text-transform: uppercase; /* mayúsculas */
      letter-spacing: 1px; /* espacio entre letras */
    }

    input[type=submit]:hover {
      background-color: #ff5a9e; /* rosa más oscuro */
    }

  </style>
</head>
<body>
<header>
  <h1>Editar Base de Datos</h1>
</header>
<div class="form-container">
  <form action="${pageContext.request.contextPath}/editar-BD" method="get">
    <label for="id">Ingresa la ID:</label>
    <input type="text" id="id" name="id" placeholder="Ingresar ID">
    <br>
    <label for="operation">Elige la Accion:</label>
    <select id="operation" name="operation">
      <option value="buscar">Buscar</option>
      <option value="eliminar">Eliminar</option>
    </select>
    <br>
    <input type="submit" value="Aceptar">
  </form>
</div>
</body>
</html>
