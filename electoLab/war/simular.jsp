<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0);
//prevents caching at the proxy server
%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="templates/head.html"%>
	<link rel="stylesheet" type="text/css" href="./css/leaflet.css" />
<style>

#map {
  width: 95%;
  height: 400px;
}
#graphicbar{
	width: 50%;
	heigth: 20px;
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
		<%@ include file="templates/header.jsp"%>
	</header>

	<main>
	<div class="container">
		<%@ include file="templates/main.jsp"%>
		<div class="container-fluid" id="main-content">
			<h1>Resultado de la simulación</h1>
			<HR width=95% align="center" class="btn-info"
				style="margin-top: 10px; margin-top: 10px;">
			<div class="row" style="margin-top: 20px;">
				<div class="col-lg-3" style="margin-top: 7px; margin-left: 12px;">
					Sistema de proporcionalidad:</div>
				<div class="dropdown col-lg-2">
					<input type="radio" name="sistema" value="dhont" checked>D'Hondt<br>
					<input type="radio" name="sistema" value="sainte">Sainte-Lagüe<br>
				</div>
				<div class="col-lg-2" style="margin-top: 7px;">
					Circunscripciones:</div>
				<div class="dropdown col-lg-3">
					<input type="radio" name="circunscripciones" value="provincias"
						checked>Provincias<br> <input type="radio"
						name="circunscripciones" value="comunidades">Comunidades
					autónomas<br> <input type="radio" name="circunscripciones"
						value="spain">España<br>
				</div>
				<div class="col-lg-4 center-block"
					style="margin-top: 28px; margin-left: 12px;" min="0" max="100">
					% escaños para la mayoria absoluta:</div>
				<div class="form-group col-lg-2 center-block">
					<input type="number" name="mayoria"
						class="form-control col-lg-2 center-block"
						style="margin-top: 18px;" value="50">
				</div>
			</div>
			<div class="row" style="margin-top: 15px;">
				<div class="col-lg-6">
					<button type="button"
						class="btn btn-info volversimular center-block"
						data-toggle="modal" data-target="#volversimular">Volver a
						Simular</button>
				</div>
				<div class="col-lg-6">
					<button type="button" class="btn btn-info center-block"
						id="botoncrear">Editar Simulación</button>
				</div>
			</div>
			<HR width=95% align="center" class="btn-info"
				style="margin-top: 10px; margin-top: 10px;">

			<div class="col-lg-12 center-block" id="editarsimular"
				style="margin-top: 18px;"></div>
			<div class="container">
				<div class="row" style="margin-top: 3px; margin-top: 5px;">
					<div class="col-lg-5">
						<h3 class="text-center">Mapa de partido ganador</h3>
						<div id="map" style=" border: 1px solid #AAA;"></div>

					</div>
					<div class="col-lg-7">
						<h3 class="text-center">Distribución de escaños en el
							Congreso</h3>
						<canvas id="piechart" width="900" height="500"></canvas>
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
									<th>${ r.circunscripcion }</th>
									<th>${r.escanos }</th>
								</tr>
							</c:forEach>
							<c:forEach items="${resultadosCongreso}" var="r">
								<tr>
									<th scope="row"><h5>${ r.partido }</h5></th>
									<th>${ r.circunscripcion }</th>
									<th>${r.escanos }</th>
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
	
	
	<!-- Modal Votos-->
	<div class="modal fade" id="popupVotos" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span> <span class="sr-only">Cerrar</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Votos en la provincia:<span id="prov"> </span> </h4>
				</div>
				<div class="modal-body">
					<div class="text-center" >
						<canvas id="graphicbar"></canvas>
					
						<table class="table table-hover" id="partidosTable">
						<thead>
							<tr>
								<th class="col-lg-3">Partido</th>
								<th class="col-lg-3">Escaños obtenidos</th>
							</tr>
						</thead>
						<tbody id="votos">
							
						</tbody>
					</table>
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
	<script type='text/javascript' src="./js/topojson.v1.min.js"></script>
	<script type='text/javascript'  src="./js/d3.v3.min.js"></script>
	<script type='text/javascript' src='./js/leaflet.js'></script>	
	<script class="mapScript">
	$(document).ready(function(){
		var map = new L.Map("map", {center: [39.9855, -3.7353], zoom: 5});
		var Esri_WorldGrayCanvas = L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/Canvas/World_Light_Gray_Base/MapServer/tile/{z}/{y}/{x}.png', {
		attribution: 'Tiles &copy; Esri &mdash; Esri, DeLorme, NAVTEQ',
		maxZoom: 16
		}).addTo(map);

		var svg = d3.select(map.getPanes().overlayPane).append("svg"),
		g = svg.append("g").attr("class", "leaflet-zoom-hide");
		
		d3.json("./json/states_esp.topo.json", function(error, collection) {
		if (error) throw error;

		var states = topojson.feature(collection, collection.objects.states);

		var transform = d3.geo.transform({point: projectPoint}),
		path = d3.geo.path().projection(transform);


		var feature = g.selectAll("path")
		.data(states.features)
		.enter().append("path")
		.style({
		"fill-opacity" : 0.4,
		"stroke" : "#fff",
		"stroke-width" : "1px"
		})
		.style("fill", function (d) {
			<c:forEach items="${colores}" var="color">
			if(d.id == "${color.key}"){
				var value = "${color.value}";
				return (d.color = value);
			}
			</c:forEach>
		})		

		.on('click', function (d) {
			$('#popupVotos').modal('show');
			$('#votos').html("");
			$('#prov').html("");
			var id = d.id;
			var name = d.properties.name
	    	var resultados = [];
	    	var i = 0;

			<c:forEach items="${resultadosPorCircunscripcion}" var="r">
				if(id == "${r.circunscripcion}"){
					$('#prov').html(" " + name);
					$('#votos').append("<tr><th scope='row'><h4> ${ r.partido } </h4></th><th><h5> ${r.escanos }  </h5></th></tr>");			    	
					resultados[i] = ${r.escanos};						
						i++;
				}
			</c:forEach>
			graphicbar(resultados);

		})
		.on('mouseover', function (d, i) {
				d3.select(this).style({
					"fill-opacity" : .7
					})
			})
		.on('mouseout', function (d, i) {
					d3.selectAll('path').style({
						"fill-opacity" : .2
					})
			});
		map.on("viewreset", reset);
		reset();

		// Reposition the SVG to cover the features.
		function reset() {
		var bounds = path.bounds(states),
		topLeft = bounds[0],
		bottomRight = bounds[1];

		svg.attr("width", bottomRight[0] - topLeft[0])
		.attr("height", bottomRight[1] - topLeft[1])
		.style("left", topLeft[0] + "px")
		.style("top", topLeft[1] + "px");

		g.attr("transform", "translate(" + -topLeft[0] + "," + -topLeft[1] + ")");

		feature.attr("d", path);
		}

		// Use Leaflet to implement a D3 geometric transformation.
		function projectPoint(x, y) {
		var point = map.latLngToLayerPoint(new L.LatLng(y, x));
		this.stream.point(point.x, point.y);
		}

		});
	
	<c:if test ="${escenario.circunscripciones == 'COMUNIDADES'}">
	d3.json("./json/communities_esp.topo.json", function(error, collection) {
		if (error) throw error;


		var transform = d3.geo.transform({point: projectPoint}),
		path = d3.geo.path().projection(transform);


		var feature = g.selectAll("path")
		.data(collection.features)
		.enter().append("path")
		.style({
		"fill-opacity" : 0.4,
		"stroke" : "#fff",
		"stroke-width" : "1px"
		})

		.on('mouseover', function (d, i) {
				d3.select(this).style({
					"fill-opacity" : .7
					})
			})
		.on('mouseout', function (d, i) {
					d3.selectAll('path').style({
						"fill-opacity" : .2
					})
			});
		map.on("viewreset", reset);
		reset();

		// Reposition the SVG to cover the features.
		function reset() {
		var bounds = path.bounds(collection),
		topLeft = bounds[0],
		bottomRight = bounds[1];

		svg.attr("width", bottomRight[0] - topLeft[0])
		.attr("height", bottomRight[1] - topLeft[1])
		.style("left", topLeft[0] + "px")
		.style("top", topLeft[1] + "px");

		g.attr("transform", "translate(" + -topLeft[0] + "," + -topLeft[1] + ")");

		feature.attr("d", path);
		}

		// Use Leaflet to implement a D3 geometric transformation.
		function projectPoint(x, y) {
		var point = map.latLngToLayerPoint(new L.LatLng(y, x));
		this.stream.point(point.x, point.y);
		}
		});
		 });
</c:if>
	 });

	</script>	

	
	<script>
	$('#votosTable').DataTable( {
        "scrollX": true,
        "ordering": false, 
    } );  
	</script>
	<script src="./js/Chart.js"></script>
	
	<script type="text/javascript">
				
		function graphicbar(resultados) {
    	var ctx = document.getElementById("graphicbar").getContext("2d"); 
    	var resultados = resultados;
		var data = {
			    labels: [
			        "PP",
			        "DL",
			        "FAC",
			        "ERC-CATS",
			        "C's",
			        "IU-UPeC",
			        "PODEMOS",
			        "En MArea-ANOVEA-EU",
			        "UPN",
			        "COMPROMIS",
			        "NC",
			        "EAJ-PNV",
			        "EN COMÚ",
			        "CCa-PNC",
			        "PSOE"
			    ],
			    datasets: [
			        {			   
			        	
			        	label: "Escaños ",
			        	data: resultados,
			            backgroundColor: [
			                "#02cff7",
			                "#0f178a",
			                "#626262",
			                "#ffcc00",
			                "#f7771b",
			                "#10812d",
			                "#742da1",
			                "#c920dd",
			                "#17626a",
			                "#4e05d3",
			                "#a4ff00",
			                "#54a106",
			                "#998eef",
			                "#dae705",
			                "#ff0000"
			            ],
			            hoverBackgroundColor: [
			                "#36A2EB",
			                "#FFCE56",
			                "#FF6384",
			                "#36A2EB",
			                "#FFCE56",
			                "#FF6384",
			                "#36A2EB",
			                "#FFCE56",
			                "#FF6384",
			                "#36A2EB",
			                "#FFCE56",
			                "#FF6384",
			                "#36A2EB",
			                "#FFCE56",
			                "#FFCE56"
			            ]			        	
			        }]
			};
    	var myBarChart = new Chart(ctx,{
    	    type: 'bar',
    	    data: data,
    	    display:false,
    	    scaleLabel:{
    	    	display:false
    	    }
    	});
		};
    </script>
    <script>
    $(document).ready(function() {
    	var ctx = document.getElementById("piechart").getContext("2d");
    	var resultados = [];
    	
    	var i = 0;
    	<c:forEach items="${resultadosCongreso}" var="r">
			resultados[i] = ${r.escanos};
			
			i++;
		</c:forEach>
		var data = {
			    labels: [ 
			        "PP",
			        "DL",
			        "FAC",
			        "ERC-CATS",
			        "C's",
			        "IU-UPeC",
			        "PODEMOS",
			        "En MArea-ANOVEA-EU",
			        "UPN",
			        "COMPROMIS",
			        "NC",
			        "EAJ-PNV",
			        "EN COMÚ",
			        "CCa-PNC",
			        "PSOE"
			    ],
			    datasets: [
			        {
			            data: resultados,
			            backgroundColor: [
			                "#02cff7",
			                "#0f178a",
			                "#626262",
			                "#ffcc00",
			                "#f7771b",
			                "#10812d",
			                "#742da1",
			                "#c920dd",
			                "#17626a",
			                "#4e05d3",
			                "#a4ff00",
			                "#54a106",
			                "#998eef",
			                "#dae705",
			                "#ff0000"
			            ],
			            hoverBackgroundColor: [
			                "#36A2EB",
			                "#FFCE56",
			                "#FF6384",
			                "#36A2EB",
			                "#FFCE56",
			                "#FF6384",
			                "#36A2EB",
			                "#FFCE56",
			                "#FF6384",
			                "#36A2EB",
			                "#FFCE56",
			                "#FF6384",
			                "#36A2EB",
			                "#FFCE56",
			                "#FFCE56"
			            ]			        	
			        }]
			};
		var options = {
				rotation : -1*Math.PI,
				circumference : Math.PI,
				animateScale : true
		}
    	var myPieChart = new Chart(ctx,{
    	    type: 'doughnut',
    	    data: data,
    		options: options
    	});
      });
    </script>
</body>
</html>