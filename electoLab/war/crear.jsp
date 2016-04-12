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
		<form action="/simular" method="post">
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
											<th class="col-lg-3">Provincias</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${partidos}" var="partido">
	                                    <tr>
											<th scope="row"> <img src="${partido.imagen}" style="height: 40px;"> <c:out value="${partido.siglas}"/></th>
											<td><c:out value="${partido.nombre}"/></td>
											<td>
												<select class="colorselector" id="color${partido.color}">
													<option value="1" data-color="#02CFF7">Azul claro</option>
													<option value="2" data-color="#FF0000">Rojo</option>
													<option value="3" data-color="#742DA1">Morado</option>
													<option value="4" data-color="#F7771B">Naranja</option>
													<option value="5" data-color="#FFCC00">Amarillo</option>
													<option value="6" data-color="#0F178A">Azul oscuro</option>
													<option value="7" data-color="#C2F095">Verde claro</option>
													<option value="8" data-color="#54A106">Verde oscuro</option>
													<option value="9" data-color="#F7199B">Rosa</option>
													<option value="10" data-color="#151515">Negro</option>
													<option value="11" data-color="#FFFFFF">Blanco</option>
													<option value="12" data-color="#A19D9F">Gris</option>
													<option value="13" data-color="#2CF1F5">Cian</option>
													<option value="14" data-color="#151515">Negro</option>
													<option value="15" data-color="#151515">Negro</option>
													<option value="16" data-color="#151515">Negro</option>
													<option value="17" data-color="#151515">Negro</option>
													<option value="18" data-color="#151515">Negro</option>
													<option value="19" data-color="#151515">Negro</option>
													<option value="20" data-color="#151515">Negro</option>		
												</select>
											</td>											
											<td><h5><c:out value="${partido.provincia}"/></h5></td>

										</tr>
										<script>
										var val = ${partido.color};
										$('#color'+${partido.color}).val(val.toString());</script>
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
								
							</div>
						</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion" href="#collapse3">Reparto de votos y escaños</a>
							</h4>
						</div>
						<div id="collapse3" class="panel-collapse collapse">
							<div class="panel-body">Introduzca los votos de cada partido por circunscripción:</div>
							<table class="table table-hover" id="votosTable">
								<thead>
									<tr>
										<th style="min-width:100px;"><a href="#" data-toggle="tooltip" data-placement="right" title="34.631.784
 electores">Circunscripción</a></th>
										<c:forEach items="${partidos}" var="partido">
											<th style="min-width:100px;"><c:out value="${partido.siglas}"/></th>
										</c:forEach>
										<th style="min-width:100px;">Votos a repartir</th>
										<th style="min-width:100px;">Escaños</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${provincias}" var="provincia">
	                                    <tr>
											<th scope="row"><a href="#" data-toggle="tooltip" data-placement="right" title="<c:out value="${provincia.electores}"/>
	 											electores"><c:out value="${provincia.nombre}"/></a>
	 										<c:forEach items="${partidos}" var="partido">
												<th> <input type='number' class='form-control'name="${partido.siglas}${provincia.identificador}" placeholder='0%' min-value=0 max-value=100></th>
											</c:forEach>
											<th class='form-group'><input type="number" name="votos ${provincia.identificador}" class="form-control"max="350" min="0" min-value=0 placeholder="0"></th>	
											<th class='form-group'><input type="number" name="escaños ${provincia.identificador}" class="form-control"max="350" min="0" value="${provincia.escanos}"></th>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<table class="table table-hover" id="partidosTable" style="margin-top: 30px;">
								<thead> <tr>
									<th class="col-lg-3 center-block">Computación total</th>
									<th class="col-lg-3 center-block">Total de Votos</th>
									<th class="col-lg-3 center-block">Total de Circunscripciones</th>
									<th class="col-lg-3 center-block">Total de Escaños</th>
								</tr>
								</thead>
								<tbody>
								<tr>
									<th scope="row"><h5>SimulacioXX</h5></th>
									<th><input type='text' class='form-control' name="votos_totales" placeholder="1.000.000"></th>
									<th> <input type='text' class='form-control' name="total_circuns" placeholder="52"><c:out value="${total_votos}"/></th>
									<th> <input type='text' class='form-control '  name="total_escaños" placeholder="340"></th>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<input type="submit" value="Submit" class="btn btn-info center-block" role="button">Simular escenario/>
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

	<script type="text/javascript" src="./js/dropdown.js"></script>
	<script src="./js/bootstrap-colorselector.js"></script>


	<script>
	$('.colorselector').colorselector();
	function addPartido() {
		//var table = document.getElementById("partidosTable");
		//var row = table.insertRow();
		//var cell1 = row.insertCell(0);
		//var cell2 = row.insertCell(1);
		//var cell3 = row.insertCell(2);
		//var cell4 = row.insertCell(3);
		//cell1.innerHTML = "<input type='text' class='form-control' placeholder='Siglas'>";
		//cell2.innerHTML = "<input type='text' class='form-control' placeholder='Nombre'>";
		//cell3.innerHTML = "<input type='text' class='form-control' placeholder='Color'>";
		//cell4.innerHTML = " <div class='dropdown'><button class='btn btn-default dropdown-toggle numCA' type='button' data-toggle='dropdown'>Todas<span class='caret'></span></button><ul class='dropdown-menu'><li><a class='madrid' href='#'>Madrid</a></li><li><a class='barcelona' href='#'>Barcelona</a></li></ul></div>";
		// $('.p1').removeClass("hidden");
	}
	$('#votosTable').DataTable( {
        "scrollX": true,
        "ordering": false, 
    } );  
    $('[data-toggle="tooltip"]').tooltip(); 

    
</script>

</body>
</html>