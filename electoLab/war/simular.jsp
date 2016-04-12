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
</head>

<body>
	<header>
		<%@ include file="templates/header.jsp" %>
	</header>

	<main>
		<div class="container">
			 <%@ include file="templates/main.jsp" %>
			<div class="container-fluid" id="main-content">
				<h1>Resultado de la simulación</h1>
								<HR width=95% align="center" class="btn-info" style="margin-top: 10px;margin-top: 10px;"> 
						<div class="row" style="margin-top: 20px;">
								<div class="col-lg-3" style="margin-top: 7px; margin-left: 12px;"> Sistema de proporcionalidad:</div>
								<div class="dropdown col-lg-2">
								<input type="radio" name="sistema" value="dhont" checked>D'Hondt<br>
  								<input type="radio" name="sistema" value="sainte">Sainte-Lagüe<br>
								</div>
								<div class="col-lg-2" style="margin-top: 7px;"> Circunscripciones:</div>
								<div class="dropdown col-lg-3">
								<input type="radio" name="circunscripciones" value="provincias" checked>Provincias<br>
  								<input type="radio" name="circunscripciones" value="comunidades">Comunidades autónomas<br>
  								<input type="radio" name="circunscripciones" value="spain">España<br>
								</div>
								<div class="col-lg-4 center-block" style="margin-top: 28px; margin-left: 12px;"> % escaños para la mayoria absoluta:</div>
								<div class="form-group col-lg-2 center-block">
									<input type="number" name="mayoria" class="form-control col-lg-2 center-block"  style="margin-top: 18px;" value="50">
								</div>
							</div>
				<div class="row" style="margin-top: 15px;">
					<div class="col-lg-6">
						<button type="button" class="btn btn-info volversimular center-block" data-toggle="modal" data-target="#volversimular">Volver a Simular</button>
					</div>
					<div class="col-lg-6">
						<button type="button" class="btn btn-info center-block" id="botoncrear">Editar Simulación</button>
					</div>
					</div>
				<HR width=95% align="center" class="btn-info" style="margin-top: 10px;margin-top: 10px;"> 
					
				<div class="col-lg-12 center-block" id="editarsimular" style="margin-top: 18px; "></div>
				<div class="container">
				<div class="row" style="margin-top: 3px; margin-top: 5px;">
					<div class="col-lg-5">
						<h3 class="text-center">Mapa de partido ganador</h3>
						<img class="map center-block" src="img/mapa.jpeg" width="350" height="220">
					</div>
					<div class="col-lg-7">
						<h3 class="text-center">Distribución de escaños en el Congreso</h3>
						<img  class="diagram center-block" src="img/diagramaqueso.png" width="350" height="220">
					</div>
				</div>
					<div class="row" style="margin-top: 3px; margin-top: 5px;">
						<div class="col-lg-5">
							<h3 class="text-center">Partido más Votado</h3>
							<img class="center-block" src="img/graf1.jpg" width="240" height="220">
						</div>
						<div class="col-lg-7">
							<h3 class="text-center">Gráfica votos</h3>
							<img  class="diagrama center-block" src="img/graf2.jpeg" width="298" height="220">
						</div>
					</div>
				</div>
				<div class="panel panel-default" style="margin-top: 15px;">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapse3">Resultado simulación</a>
						</h4>
					</div>
					<div id="collapse3" class="panel-collapse collapse">
						<div class="panel-body">Resultado</div>
						<table class="table table-hover" id="partidosTable">
							<thead>
							<tr>
								<th class="col-lg-3">Partido</th>
								<th class="col-lg-3">Votos</th>
								<th class="col-lg-3">Representación</th>
							</tr>
							</thead>
							<tbody>
							<tr>
								<th scope="row"><h5>PartidoXX</h5></th>
								<th> <input type='text' class='form-control' value="1.000.0000"></th>
								<th> <input type='text' class='form-control' value="20"></th>
							</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-lg-12" style="padding-bottom: 40px;">
					<button class="btn btn-info center-block" data-toggle="modal" data-target="#popupInforme">Generar informe</button>
				</div>
				<table class="table table-hover" id="votosTable">
								<tbody>
									<c:forEach items="${votos}" var="voto">
									
										<c:forEach var="i" begin="1" end="${fn:length(voto.provincia)}">
	                                    <tr>	                                    	
	                                    	<th scope="row"><c:out value="${voto.siglas}"/>
										    <th scope="row"><c:out value="${voto.provincia[i-1]}"/> 
										  	<th scope="row"><c:out value="${voto.votos[i-1]}"/>
										</tr>
											</c:forEach>				
									</c:forEach>
								</tbody>
							</table>
			</div>
				<div style="padding: 20px;"></div>
			</div>
		</div>




		<!-- Modal Escenario--> <div class="modal fade" id="popupInforme" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
						<span class="sr-only">Cerrar</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Generar informe de la simulación</h4>
				</div>
				<div id="informe" class="modal-body">
					<div class="text-center">

						<h4>¿Qué tipo de informe desea generar?</h4>
						<button type="button" class="btn btn-default" id="botonInforme" onClick="#" data-dismiss="modal">Resumido</button>
						<button type="button" class="btn btn-default" id="botonInforme" onClick="#" data-dismiss="modal">Detallado</button>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
				</div>
			</div>
		</div>
	</div>
</main>
<footer class="footer">
	<div class="container">
		<p>Desarrollado por Grupo 20. Todos los derechos reservados.</p>
		<%@ include file="templates/footer.html" %>
		
	</div>
</footer>

<script type="text/javascript" src="./js/dropdown.js"></script>
	<script type="text/javascript" src="./js/bootstrap-colorselector.js"></script>
	<script type="text/javascript" src="./js/cambiarsimulacio.js"></script>
	<script>
	$('#votosTable').DataTable( {
        "scrollX": true,
        "ordering": false, 
    } );  
	</script>
</body>
</html>