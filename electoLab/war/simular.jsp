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

.donut-inner {
   margin-top: -40px;
   margin-bottom: 20px;
   margin-left: 245px;
}
.donut-inner h5 {
   margin-bottom: 5px;
   margin-top: 0;
   font-size: 30px;
   
}

.hidden {
    display: none;
 }
div.tooltip {
   color: #ffffff;
   background-color: #808080;
   padding: .5em;
   text-shadow: #f5f5f5 0 1px 0;
   border-radius: 2px;
   opacity: 0.9;
   position: absolute;
   right: 0px;
   
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
			<h3><c:out value='${escenario.titulo}'/></h3>		
			<HR width=95% align="center" class="btn-info"
				style="margin-top: 10px; margin-top: 10px;">
			<form action="/simular" method="get">
			<div class="row" style="margin-top: 20px;">
				<div class="col-lg-3" style="margin-top: 7px; margin-left: 12px;">
					Sistema de proporcionalidad:</div>
				<div class="dropdown col-lg-2">
					<input type="radio" name="sistema" value="DHONDT"<c:if test="${escenario.sistema == 'DHONDT'}"> checked </c:if> >D'Hondt<br>
  					<input type="radio" name="sistema" value="SAINTE"<c:if test="${escenario.sistema == 'SAINTE'}"> checked </c:if>>Sainte-Lagüe<br>
				</div>
				<div class="col-lg-2" style="margin-top: 7px;">
					Circunscripciones:</div>
				<div class="dropdown col-lg-3">
					<input type="radio" name="circunscripciones" value="PROVINCIAS"<c:if test ="${escenario.circunscripciones == 'PROVINCIAS'}"> checked </c:if>>Provincias<br>
  					<input type="radio" name="circunscripciones" value="COMUNIDADES"<c:if test ="${escenario.circunscripciones == 'COMUNIDADES'}"> checked </c:if>>Comunidades autónomas<br>
    				<input type="radio" name="circunscripciones" value="PAIS"<c:if test ="${escenario.circunscripciones == 'PAIS'}"> checked </c:if>>España<br>
				</div>
				<div class="col-lg-4 center-block"
					style="margin-top: 28px; margin-left: 12px;" min="0" max="100">
					% escaños para la mayoria absoluta:</div>
				<div class="form-group col-lg-2 center-block">
					<input type="number" name="mayoria" class="form-control col-lg-2 center-block"  style="margin-top: 18px;" min="0" max="100" value="${escenario.mayoria_abs }">

				</div>
			</div>
			<div class="row" style="margin-top: 15px;">
				<div class="col-lg-6">
						<input type="hidden" name="escenario" value="${escenarioKey}" />
						<button type="submit" class="btn btn-info volversimular center-block">Volver a
						Simular</button>
				</div>
				</form>
				<div class="col-lg-6">
					<form action="/crear" method="get">
						<input type="hidden" name="escenario" value="${escenarioKey}" />
						<button type="submit" class="btn btn-info center-block"
						id="botoncrear" data-loading-text="Cargando...">Editar Simulación</button>
					</form>
				</div>
			</div>
			<HR width=95% align="center" class="btn-info"
				style="margin-top: 10px; margin-top: 10px;">

			<div class="col-lg-12 center-block" id="editarsimular"
				style="margin-top: 18px;"></div>
			<div class="container">
				<div class="row" style="margin-top: 3px; margin-top: 5px;">
					<div class="col-lg-5 ">
						<div id="map" style=" border: 1px solid #AAA;">						<div id="mapLabel"></div>
						</div>

					</div>
					<div class="col-lg-7">
						<canvas id="piechart" width="900" height="500"></canvas>
						<div class="donut-inner">
    						<h5><span id="numEsc"> </span> escaños</h5>
						</div>
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
			<c:if test="${not empty user}">
			<div class="col-lg-12" style="padding-bottom: 40px;">
				<div class="col-lg-6">
					<form action="/guardasimulacion" method="post">
						<input type="hidden" name="escenario" value="${escenarioKey}" />
						<button type="submit" class="btn btn-success center-block">Guardar simulación</button>
					</form>
				</div>
				<div class="col-lg-6">
					<button class="btn btn-info center-block" data-toggle="modal"
					data-target="#popupInforme">Generar informe</button>
				</div>
			</div>
			</c:if>
				
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
					<h4 class="modal-title" id="myModalLabel">Votos en la circunscripción:<span id="prov"> </span> </h4>
				</div>
				<div class="modal-body">
					<div class="text-center" >
						<div id="canvasWrapper">
						<canvas id="graphicbar"></canvas>
						</div>
						<table class="table table-hover" id="partidosTable">
						<thead>
							<tr>
								<th class="col-lg-3">Partido</th>
								<th class="col-lg-3">Escaños obtenidos</th>
								<th class="col-lg-3">Votos obtenidos</th>
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
	<script type='text/javascript' src="./js/topojson.v1.min.js"></script>
	<script type='text/javascript'  src="./js/d3.v3.min.js"></script>
	<script type='text/javascript' src='./js/leaflet.js'></script>
	<script src="./js/Chart.js"></script>
		
	</footer>
	<script class="mapScript">
	$(document).ready(function(){
		
    	var partidos = {
    			siglas: [],
    			nombre: [],
    			color: [],
    			imagen: [],
    	};
		<c:forEach items="${escenario.partidos}" var="partido" varStatus="status">
			partidos.siglas[${status.count}] = String("<c:out value='${partido.siglas}'/>");
			partidos.nombre[${status.count}] = String("<c:out value='${partido.nombre}'/>");
			partidos.color[${status.count}]  = "#"+String("<c:out value='${partido.color}'/>");
			partidos.imagen[${status.count}] = String("<c:out value='${partido.imagen}'/>");
		</c:forEach>
		//sobra un elemento
		partidos.siglas.splice(0,1);
		partidos.nombre.splice(0,1);
		partidos.color.splice(0,1);
		partidos.imagen.splice(0,1);

	
		var map = new L.Map("map", {center: [39.9855, -3.7353], zoom: 5});
		var Esri_WorldGrayCanvas = L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/Canvas/World_Light_Gray_Base/MapServer/tile/{z}/{y}/{x}.png', {
		attribution: 'Tiles &copy; Esri &mdash; Esri, DeLorme, NAVTEQ',
		maxZoom: 8,
		minZoom: 4
		}).addTo(map);

		var svg = d3.select(map.getPanes().overlayPane).append("svg"),
		g = svg.append("g").attr("class", "leaflet-zoom-hide");
		
        var tooltip = d3.select('#mapLabel')
        .attr('class', 'hidden tooltip');
		
		<c:choose>
	   		 <c:when test="${escenario.circunscripciones == 'PROVINCIAS'}">
				var path = "./json/states_esp.topo.json";
			</c:when>
  		 <c:when test="${escenario.circunscripciones == 'COMUNIDADES'}">
 			var path = "./json/communities_esp.topo.json";
		</c:when>
 		 <c:when test="${escenario.circunscripciones == 'PAIS'}">
 			var path = "./json/spain.json";
		</c:when>
	    <c:otherwise>
			var path = "./json/states_esp.topo.json";
   		 </c:otherwise>
   		 </c:choose>
   		 
	d3.json(path, function(error, collection) {
		if (error) throw error;

		var states = collection;
	<c:if test ="${escenario.circunscripciones == 'PROVINCIAS'}">
		var states = topojson.feature(collection, collection.objects.states);
	</c:if>

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
	    	var partidosColores = {
	    			siglas: [],
	    			nombre: [],
	    			color: [],
	    			imagen: [],
	    			resultados: [],
	    			votos: []
	    	};
			var id = d.id;

	    	<c:forEach items="${resultadosPorCircunscripcion}" var="r">
				if(id == "${r.circunscripcion}"){
					var partido = "${r.partido}";
					var escanos =  "${r.escanos}";
					var votos =  "${r.votos}";

					for( var j = 0; j< partidos.siglas.length; j++){
						if(partidos.siglas[j] == partido){
							 siglas = partidos.siglas[j];
							 nombre = partidos.nombre[j];
							 color = partidos.color[j];
							 imagen = partidos.imagen[j];
						}
					}
					if(!partidosColores.siglas.includes(siglas)){
						if(siglas != null){
						partidosColores.siglas.push(siglas);
						partidosColores.nombre.push(nombre);
						partidosColores.color.push(color);
						partidosColores.imagen.push(imagen);
						partidosColores.resultados.push(escanos);
						partidosColores.votos.push(votos);
						}
					}
				
				}
					</c:forEach>
					var partidosColores = order(partidosColores);
						return (d.color = partidosColores.color[0]);
				
		})		

		.on('click', function (d) {
			$('#popupVotos').modal('show');
			$('#canvasWrapper').html("");
			$('#canvasWrapper').html("<canvas id='graphicbar'></canvas>");
			$('#votos').html("");
			$('#prov').html("");
			var id = d.id;
			var name = d.properties.name
	    	var partidosProvincia = {
	    			siglas: [],
	    			nombre: [],
	    			color: [],
	    			imagen: [],
	    			resultados: [],
	    			votos: []
	    	};
	    	var siglas,nombre,color,imagen = "";
	    	<c:forEach items="${resultadosPorCircunscripcion}" var="r">
				if(id == "${r.circunscripcion}"){
					var partido = "${r.partido}";
					var escanos =  "${r.escanos}";
					var votos = "${r.votos}";
					$('#prov').html(" " + name);
					$('#votos').append("<tr><th scope='row'><h4> ${ r.partido } </h4></th><th><h5> ${r.escanos }  </h5></th><th><h5> ${r.votos}  </h5></th></tr>");			    	
					for( var j = 0; j< partidos.siglas.length; j++){
						if(partidos.siglas[j] == partido){
							 siglas = partidos.siglas[j];
							 nombre = partidos.nombre[j];
							 color = partidos.color[j];
							 imagen = partidos.imagen[j];
						}
					}
				}
				if(!partidosProvincia.siglas.includes(siglas)){
				if(siglas != null){
					partidosProvincia.siglas.push(siglas);
					partidosProvincia.nombre.push(nombre);
					partidosProvincia.color.push(color);
					partidosProvincia.imagen.push(imagen);
					partidosProvincia.resultados.push(escanos);
					partidosProvincia.votos.push(votos);
				}
				}
			</c:forEach>
			graphicbar(partidosProvincia);

		})
		.on('mouseover', function (d, i) {
				d3.select(this).style({
					"fill-opacity" : .7
					});
	                var mouse = d3.mouse(svg.node()).map(function(d) {
                        return parseInt(d);
                    });
                    tooltip.classed('hidden', false)
                        .html(d.properties.name);

			})
		.on('mouseout', function (d, i) {
					d3.selectAll('path').style({
						"fill-opacity" : .2
					});
                    tooltip.classed('hidden', true);

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
		
piechart(partidos);
	
	 });
	</script>	
	<script type="text/javascript">
		//Funciones de graficas
		
		function graphicbar(arraysPartidos) {
    	var ctx = document.getElementById("graphicbar").getContext("2d"); 
		arraysPartidos = order(arraysPartidos);
		var data = {
			    labels: arraysPartidos.siglas,
			    datasets: [
			        {			   
			        	
			        	label: "Escaños ",
			        	data: arraysPartidos.resultados,
			            backgroundColor: arraysPartidos.color,
			            hoverBackgroundColor: arraysPartidos.color,
			        }]
			};
		
    	var myBarChart = new Chart(ctx,{
    	    type: 'bar',
    	    data: data,
    	    display:false,
    	    scaleLabel:{
    	    	display:false
    	    },
    	    options: {
    	        scales: {
    	            yAxes: [{
    	                ticks: {
    	                    beginAtZero:true,
    	                }
    	            }]
    	        }
    	    }
    	});
		};

	function piechart(arrayPartidos) {
    	var partidosCongreso = {
    			siglas: [],
    			nombre: [],
    			color: [],
    			imagen: [],
    			resultados:[],
    			votos:[]
    	};
    	var siglas,nombre,color,imagen = "";
    	<c:forEach items="${resultadosCongreso}" var="r">
				var partido = "${r.partido}";
				var escanos =  "${r.escanos}";
				var votos =  "${r.votos}";
				for( var j = 0; j< arrayPartidos.siglas.length; j++){
					if(arrayPartidos.siglas[j] == partido){
						 siglas = arrayPartidos.siglas[j];
						 nombre = arrayPartidos.nombre[j];
						 color = arrayPartidos.color[j];
						 imagen = arrayPartidos.imagen[j];
					}
				}
			if(!partidosCongreso.siglas.includes(siglas)){
				if(siglas != null){
					partidosCongreso.siglas.push(siglas);
					partidosCongreso.nombre.push(nombre);
					partidosCongreso.color.push(color);
					partidosCongreso.imagen.push(imagen);
					partidosCongreso.resultados.push(escanos);
					partidosCongreso.votos.push(votos);

				}
			}
		</c:forEach>
		function getSum(total, num) {
		    return parseInt(total) + parseInt(num);
		}
		document.getElementById("numEsc").innerHTML = partidosCongreso.resultados.reduce(getSum);
		var partidosCongreso = order(partidosCongreso);
    	var ctx = document.getElementById("piechart").getContext("2d");
		var data = {
			    labels: partidosCongreso.siglas,
			    datasets: [
			        {
			            data: partidosCongreso.resultados,
			            backgroundColor: partidosCongreso.color,
			            hoverBackgroundColor: partidosCongreso.color			        	
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
	}

	function order(arraysPartidos){
    	var sortable = [];
			for (var i = 0 ; i < arraysPartidos.siglas.length ; i++){
		      sortable.push([arraysPartidos.siglas[i],arraysPartidos.nombre[i],arraysPartidos.color[i],arraysPartidos.imagen[i], arraysPartidos.resultados[i], arraysPartidos.votos[i]]);
			}
		      sortable.sort(function(a, b) {return a[5] - b[5]}).reverse();
		
		partidosOrder = {
    			siglas: [],
    			nombre: [],
    			color: [],
    			imagen: [],
    			resultados:[],
    			votos:[]
    	};
		for (var i = 0; i< arraysPartidos.siglas.length; i++){
			partidosOrder.siglas[i] = sortable[i][0];
			partidosOrder.nombre[i] = sortable[i][1];
			partidosOrder.color[i] = sortable[i][2];
			partidosOrder.imagen[i] = sortable[i][3];
			partidosOrder.resultados[i] = sortable[i][4];	
			partidosOrder.votos[i] = sortable[i][5];		

		}
		return partidosOrder;
	}

		</script>
		<script type="text/javascript">
		$( "button[data-loading-text]" ).on("click", function() {
			$btn = $(this);
		    $btn.button('loading');
		    // simulating a timeout
		    setTimeout(function () {
		        $btn.button('reset');
		    }, 40000);
		});
		</script>
</body>
</html>