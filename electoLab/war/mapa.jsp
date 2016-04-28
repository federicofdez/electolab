<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="templates/head.html" %>
	<link rel="stylesheet" type="text/css" href="./css/leaflet.css" />
		    
<style>
#map {
  width: 70%;
  height: 500px;
}

svg {
  position: relative;
}

path {
  fill: #000;
  fill-opacity: .2;
  stroke: #fff;
  stroke-width: 1.5px;
}

path:hover {
  fill: blue;
  fill-opacity: .7;
}
</style>	
</head>

<body>
	<header>
		<%@ include file="templates/header.jsp" %>
	</header>

	<main>
		<div class="container">
			 <%@ include file="templates/main.jsp" %>
			<div class="container-fluid" id="main-content">
				<h2>Prueba del mapa</h2>
				      <div id="map" style=" border: 1px solid #AAA;"></div>
				<div style="padding: 40px;"></div>
			</div>
		</div>
	</main>

	<footer class="footer">
		<div class="container">
			<p>Desarrollado por Grupo 20. Todos los derechos reservados.</p>
		</div>
				<%@ include file="templates/footer.html" %>
				<script type='text/javascript' src="./js/topojson.v1.min.js"></script>
			   <script type='text/javascript'  src="./js/d3.v3.min.js"></script>
			   <script type='text/javascript' src='./js/leaflet.js'></script>	
			   <script type='text/javascript' src='./js/map.js'></script>	
			   
	</footer>

</body>
</html>