<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0);
//prevents caching at the proxy server
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="templates/head.html" %>
	<link rel="stylesheet" type="text/css" href="./css/bootstrap-colorpicker.min.css" />
	<script src="./js/bootstrap-colorpicker.min.js"></script>
	
</head>

<body>
	<header>
		<%@ include file="templates/header.jsp" %>
	</header>

	<main>
		<div class="container">
			 <%@ include file="templates/main.jsp" %>
		<form action="/crear" method="post">
		  	<input name="usuario" type="hidden" value="<c:out value='${user}'/>" />
			<div class="container-fluid" id="main-content">
				<h2>Introduzca los parámetros del escenario a simular</h2>
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Partidos políticos</a>
							</h4>
						</div>
					
						<div id="collapse1" class="panel-collapse collapse in">
							<div class="panel-body">Introduzca los partidos políticos que se presentan a la elecciones</div>
							<div>
								<table class="table table-hover" id="partidosTable">
									<thead>
										<tr>
											<th class="col-lg-3">Siglas</th>
											<th class="col-lg-3">Nombre</th>
											<th class="col-lg-3">Color</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${escenario.partidos}" var="partido">
	                                    <tr>
											<th scope="row"> <img src="${partido.imagen}" style="width: 40px;"> <c:out value="${partido.siglas}"/></th>
											<td><c:out value="${partido.nombre}"/></td>
											<td>
											<div  id="color${partido.color}" class="input-group colorpicker-component">
    											<input disabled type="text" value="#${partido.color }" class="form-control" name="color${partido.siglas}" />
   												<span class="input-group-addon"><i></i></span>
											</div>	
											</td>											
										</tr>
										<script>
									    $(function() {
									        $("#color${partido.color}").colorpicker();
									    });
									    </script>
									</c:forEach>
									</tbody>
								</table>
								<!-- Boton comentado de momento -->
								<!--<button onclick="addPartido()" type="button" class="btn btn-default center-block">Añadir partido</button>-->
								<div style="padding: 5px;"></div>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion" href="#collapse2">Sistema de proporcionalidad y circuscripciones</a>
							</h4>
						</div>
						<div id="collapse2" class="panel-collapse collapse">
							<div class="panel-body">Configure el formato de sistema electoral:</div>
							<div class="row">
								<div class="col-lg-3" style="margin-top: 7px; margin-left: 12px;"> Sistema de proporcionalidad:</div>
								<div class="dropdown col-lg-2">
								<input type="radio" name="sistema" value="DHONDT"<c:if test="${escenario.sistema == 'DHONDT'}"> checked </c:if> >D'Hondt<br>
  								<input type="radio" name="sistema" value="SAINTE"<c:if test="${escenario.sistema == 'SAINTE'}"> checked </c:if>>Sainte-Lagüe<br>
								</div>
								<div class="col-lg-2" style="margin-top: 7px;"> Circunscripciones:</div>
								<div class="dropdown col-lg-3">
								<input type="radio" name="circunscripciones" value="PROVINCIAS"<c:if test ="${escenario.circunscripciones == 'PROVINCIAS'}"> checked </c:if>>Provincias<br>
  								<input type="radio" name="circunscripciones" value="COMUNIDADES"<c:if test ="${escenario.circunscripciones == 'COMUNIDADES'}"> checked </c:if>>Comunidades autónomas<br>
  								<input type="radio" name="circunscripciones" value="PAIS"<c:if test ="${escenario.circunscripciones == 'PAIS'}"> checked </c:if>>España<br>
								</div>
								<div class="col-lg-4 center-block"  style="margin-top: 28px; margin-left: 12px;"> % escaños para la mayoria absoluta:</div>
								<div class="form-group col-lg-2 center-block">
									<input type="number" name="mayoria" class="form-control col-lg-2 center-block"  style="margin-top: 18px;" min="0" max="100" value="${escenario.mayoria_abs }">
								</div>
							</div>
								
							</div>
						</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion" href="#collapse3">Reparto de votos y escaños</a>
							</h4>
						</div>
						<div id="collapse3" class="panel-collapse collapse">
							<div class="panel-body"><p>En este apartado puede introducir los votos que obtiene cada partido en cada provincia. Utilice valores porcentuales (0-100), o -1 si el partido no se presenta en la provincia.</p>
							<p>En la última columna puede modificar los escaños que posee cada provincia.</p></div>
							<table class="table table-hover" id="votosTable">
								<thead>
									<tr>
										<th style="min-width:100px;">Circunscripción<span> </span><a href="#" data-toggle="tooltip" data-placement="right" title="34.631.784 electores"><i class="glyphicon glyphicon-info-sign"></i></a></th>
										<c:forEach items="${escenario.partidos}" var="partido">
											<th style="min-width:100px;"><c:out value="${partido.siglas}"/></th>
										</c:forEach>
										<th style="min-width:100px;">Escaños</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${escenario.provincias}" var="provincia">
	                                    <tr>
											<th scope="row">${provincia.nombre}<span> </span><a href="#" data-toggle="tooltip" data-placement="right" title="${provincia.electores} electores"><i class="glyphicon glyphicon-info-sign"></i></a>
	 										<c:forEach items="${escenario.partidos}" var="partido">
	 										<% request.setAttribute("relleno", false); %>
	 										<c:forEach items="${escenario.votos}" var="voto">
	 											<c:if test="${relleno == false and provincia.id == voto.circunscripcion and partido.siglas == voto.partido}">
	 												<th> <input type='number' class='form-control'name="${voto.partido}:${provincia.id}" placeholder='0%' min="-1" max="100" value="${voto.votos}"></th>
	 												<% request.setAttribute("relleno", true); %>
												</c:if>
											</c:forEach>
											<c:if test="${relleno == false}" >
													<th> <input type='number' class='form-control'name="${partido.siglas}:${provincia.id}" placeholder='0%' min="-1" max="100" value="-1"></th>
											</c:if>
											</c:forEach>
											<th class='form-group'><input type="number" name="escaños ${provincia.id}" class="form-control"max="350" min="0" value="${provincia.escanos}"></th>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<table class="table table-hover" id="partidosTable" style="margin-top: 30px;">
								<thead> <tr>
									<th class="col-lg-3 center-block">Nombre de simulación</th>
									<th class="col-lg-3 center-block">Total de Votos</th>
									<th class="col-lg-3 center-block">Total de Circunscripciones</th>
									<th class="col-lg-3 center-block">Total de Escaños</th>
								</tr>
								</thead>
								<tbody>
								<tr>
									<th><input type='text' class='form-control' name="titulo"  ></th>
									<th></th>
									<th></th>
									<th>350</th>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<input type="hidden" name="usuario" value="${user }]">
				<input type="submit" value="Simular escenario"class="btn btn-info center-block" role="button"/>
				<div style="padding: 40px;"></div>
			</div>
		</form>	
		</div>
	</main>

	<footer class="footer">
		<div class="container">
			<p>Desarrollado por Grupo 20. Todos los derechos reservados.</p>
		</div>
				<%@ include file="templates/footer.html" %>
		
	</footer>	
	<script>

	//DataTable
	var oTable = $('#votosTable');
	oTable.DataTable( {
        "scrollX": true,
        "ordering": false, 
        "searching": false,
        "scrollY":        "400px",
        "scrollCollapse": true,
        "pageLength": 52,
        "paging": false,
        "orderClasses": false,
        "language": {
            "zeroRecords": "No se ha encontrado nada - sorry",
            "info": "Mostrando _PAGE_ de _PAGES_",
            "infoEmpty": "No existe el escenario",
        }

    } );
	
	//Etiquetas de electores
    $('[data-toggle="tooltip"]').tooltip(); 




	
	
</script>

</body>
</html>