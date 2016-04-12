<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
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
    </div>
    <button type="submit" class="btn btn-primary">Registrarse</button>
    <p> Recibirá un correo, con la contraseña asignada a su usuario para comenzar a escribir en foros y guardar sus simulaciones.
  </form>
</div>


</body> 
</html>