<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<% if(session.getAttribute("user") != "" ) {response.sendRedirect((String) session.getAttribute("url"));} 
	%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="templates/head.html" %>
</head>
<body>
  <header>
		<%@ include file="templates/header.jsp" %>
  </header>

<div class="container">
  <h2><p class="text-primary"><span class="glyphicon glyphicon-user"></span> REGISTRO DE USUARIO</span></p></h2>
  <form role="form-inline" action="/registrar" method="post">
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" class="form-control" name="email" id="email" placeholder="Introduzca su email">
      <label for="email">Grupo:</label>
      <input required type="text" class="form-control" name="grupo" id="grupo" placeholder="Grupo de usuarios al que pertenece">
      <label for="email">Contraseña del grupo:</label>      
      <input required type="password" class="form-control" name="password" id="password" placeholder="Contraseña del grupo">
      
    </div>
    <button type="submit" class="btn btn-primary">Registrarse</button>
    <p> Debe introducir su correo y el nobmre del grupo de usuarios que han contratado junto a la contraseña
    proporcionada para escribir en foros y guardar sus simulaciones.</p>
   	<c:if test="${not empty error}">
		<p style="color: red;">${error}</p>
	</c:if>
  </form>
</div>


</body> 
</html>