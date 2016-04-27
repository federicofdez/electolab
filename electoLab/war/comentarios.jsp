<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="templates/head.html"%>
</head>

<body>
	<header>
		<%@ include file="templates/header.jsp"%>
	</header>

	<main>
	<div class="container">
		<%@ include file="templates/main.jsp"%>
		<div class="container-fluid" id="main-content">
			<h1>simulación <c:out value="${escenario.id }"/></h1>
			<HR width=95% align="center" class="btn-info"
				style="margin-top: 10px; margin-top: 10px;">
										<div>
				<h4>Parámetros de la simulación</h4>
				<div class="col-lg-12" style="margin-top: 7px; margin-left: 12px;">
					<h5> Sistema de proporcionalidad: <c:out value="${escenario.sistema }"/></h5> </div>
				<div class="col-lg-12" style="margin-top: 7px; margin-left: 12px;">
					<h5> Circunscripciones: <c:out value="${escenario.circunscripciones }"/> </h5> </div>
				<div class="col-lg-12 center-block"
					style="margin-top: 7px; margin-left: 12px;">
					<h5> % escaños para la mayoria absoluta: <c:out value="${escenario.mayoria_abs }"/> </h5> </div>			
					</div>
			<HR width=95% align="center" class="btn-info"
				style="margin-top: 10px; margin-top: 10px;">
			<div class="col-lg-12 center-block" id="editarsimular" style="margin-top: 18px;"></div>
			<div class="container">
				<div class="row" style="margin-top: 3px; margin-top: 5px;">
					<div class="col-lg-5">
						<h3 class="text-center">Mapa de partido ganador</h3>
						<img class="map center-block" src="img/mapa.jpeg" width="350"
							height="220">
					</div>
					<div class="col-lg-7">
						<h3 class="text-center">Distribución de escaños en el
							Congreso</h3>
						<img class="diagram center-block" src="img/diagramaqueso.png"
							width="350" height="220">
					</div>
				</div>
				<div class="row" style="margin-top: 3px; margin-top: 5px;">
					<div class="col-lg-5">
						<h3 class="text-center">Partido más Votado</h3>
						<img class="center-block" src="img/graf1.jpg" width="240"
							height="220">
					</div>
					<div class="col-lg-7">
						<h3 class="text-center">Gráfica votos</h3>
						<img class="diagrama center-block" src="img/graf2.jpeg"
							width="298" height="220">
					</div>
				</div>
			</div>
			<div class="panel panel-default" style="margin-top: 15px;">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapse3">Resultado simulación</a>
					</h4>
				</div>
				<div id="collapse3" class="panel-collapse collapse">
					<div class="panel-body">Resultado</div>
					<table class="table table-hover" id="partidosTable">
						<thead>
							<tr>
								<th class="col-lg-3">Partido</th>
								<th class="col-lg-3">Circunscripción</th>
								<th class="col-lg-3">Escaños obtenidos</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${resultadosPorCircunscripcion}" var="r">
								<tr>
									<th scope="row"><h5>${ r.partido }</h5></th>
									<th><input type='text' class='form-control'
										value="${ r.circunscripcion }"></th>
									<th><input type='text' class='form-control' value="${r.votos }"></th>
								</tr>
							</c:forEach>
							<c:forEach items="${resultadosCongreso}" var="r">
								<tr>
									<th scope="row"><h5>${ r.partido }</h5></th>
									<th><input type='text' class='form-control'
										value="${ r.circunscripcion }"></th>
									<th><input type='text' class='form-control' value="${r.votos }"></th>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col-lg-12" style="padding-bottom: 40px;">
				<button class="btn btn-info center-block" data-toggle="modal"
					data-target="#popupInforme">Generar informe</button>
			</div>
			<div class="container-fluid" id="main-content">
				<h1>Comentarios de tu simulación</h1>
				<div>
					<table class="table table-hover" id="foroTable">
						<thead>
							<tr>
								<th class="col-lg-3">Usuario</th>
								<th class="col-lg-3">Fecha</th>
								<th class="col-lg-3">Texto</th>
							</tr>
						</thead>
						<tbody>
								<c:forEach items="${escenario.comentarios}" var="comentario">
								<tr>
								<th scope="row"><c:out value="${comentario.usuario}"/></th>
								<td style="color: #A4A4A4;"><c:out value="${comentario.fecha }" /></td>
								<td><c:out value="${comentario.texto}"/></td>
								</tr>
								</c:forEach>
						</tbody>
					</table>
										<form method="post" action="/forosimular">
											<input type="hidden" name="escenarioId" id="escenarioId" value="${escenario.id}"/>
											<textarea rows="2" maxlength="350" name="comentario" class="col-lg-12" required></textarea>
											<input type="submit" value="Añadir comentario"class="btn btn-info center-block" role="button"/>
										</form>				
										</div>
			</div>
		</div>
		</div>
		<div style="padding: 20px;"></div>
	</div>
	</div>




	<!-- Modal Escenario-->
	<div class="modal fade" id="popupInforme" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span> <span class="sr-only">Cerrar</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Generar informe de
						la simulación</h4>
				</div>
				<div id="informe" class="modal-body">
					<div class="text-center">

						<h4>¿Qué tipo de informe desea generar?</h4>
						<button type="button" class="btn btn-default" id="botonInforme"
							onClick="#" data-dismiss="modal">Resumido</button>
						<button type="button" class="btn btn-default" id="botonInforme"
							onClick="#" data-dismiss="modal">Detallado</button>
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
			<%@ include file="templates/footer.html"%>

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