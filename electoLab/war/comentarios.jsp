<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<% if(session.getAttribute("user") == "" ) {response.sendRedirect((String) session.getAttribute("url"));} 
	else if(session.getAttribute("escenario") == null) {response.sendRedirect("/registrar.jsp");}
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
			<h1>Simulación: <c:out value="${escenario.titulo }"/></h1>
			<HR width=95% align="center" class="btn-info"
				style="margin-top: 10px;">
				<div class="row" align="center" style="margin-top: 15px;margin-left:2%; width:96%">

				<h4 style="margin-left: 5px;"><u>Parámetros de la simulación:</u></h4>
				    <div class="panel panel-default col-lg-4">
      <div class="panel-heading">Sistema de proporcionalidad</div>
      <div class="panel-body" id="sistemaProp"><c:out value="${escenario.sistema }"/></div>
   	 </div>
   	 				    <div class="panel panel-default col-lg-4">
      <div class="panel-heading">Circunscripciones</div>
      <div class="panel-body" id="circunscripcionElegida"><c:out value="${escenario.circunscripciones }"/></div>
   	 </div>
   	 				    <div class="panel panel-default col-lg-4">
      <div class="panel-heading">% escaños para la mayoria absoluta</div>
      <div class="panel-body"><c:out value="${escenario.mayoria_abs }"/></div>
   	 </div>
   	 </div>
							<HR width=95% align="center" class="btn-info"
				style="margin-top: 10px;">
			<div class="col-lg-12 center-block" id="editarsimular" style="margin-top: 18px;"></div>
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
						<div class="donut-inner">
    						<h5><span id="numEsc"> </span> escaños</h5>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-12" style="padding-bottom: 40px;">
					<form action="/informe" method="get">
						<input type="hidden" name="escenarioId" value="${escenario.id}" />
					<button class="btn btn-info center-block" style="margin-top: 40px;" >Generar informe</button>
					</form>
			</div>
						<HR width=95% align="center" class="btn-info"
				style="margin-top: 120px;">
			<div class="container-fluid" id="main-content">
				<h1>Comentarios de  simulación</h1>
				<div>
					<table class="table table-hover" id="comentarioTable">
						<thead>
							<tr>
								<th class="col-lg-3">Usuario</th>
								<th class="col-lg-3">Fecha</th>
								<th class="col-lg-3">Texto</th>
							</tr>
						</thead>
						<tbody>
								<c:forEach items="${comentarios}" var="comentario">
								<tr <c:if test="${comentario.usuario == user}"> class="info" </c:if> >
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
						<table class="table table-hover" id="provinciaTable">
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
	<script type='text/javascript' src="./js/topojson.v1.min.js"></script>
	<script type='text/javascript'  src="./js/d3.v3.min.js"></script>
	<script type='text/javascript' src='./js/leaflet.js'></script>
	<script src="./js/Chart.js"></script>

		</div>
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
					var partidosColores = order(partidosColores,"votos");
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
	


	$('#comentarioTable').DataTable( {
	    "ordering": true, 
        "order": [[ 1, "desc" ]],
	    "language": {
	        "lengthMenu": "Mostrando _MENU_ entradas por página",
	        "zeroRecords": "Niguna entrada coincide con su búsqueda",
	        "info": "Mostrando _PAGE_ de _PAGES_",
	        "infoEmpty": "No existe el comentario",
	        "infoFiltered": "(filtrado de _MAX_ entradas totales)"
	    }
	} ); 
	</script>	
	<script type="text/javascript">
		//Funciones de graficas
		
		function graphicbar(arraysPartidos) {
    	var ctx = document.getElementById("graphicbar").getContext("2d"); 
		arraysPartidos = order(arraysPartidos,"votos");
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
		var partidosCongreso = order(partidosCongreso,"escanos");
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

	function order(arraysPartidos, parametro){
    	var sortable = [];
			for (var i = 0 ; i < arraysPartidos.siglas.length ; i++){
		      sortable.push([arraysPartidos.siglas[i],arraysPartidos.nombre[i],arraysPartidos.color[i],arraysPartidos.imagen[i], arraysPartidos.resultados[i], arraysPartidos.votos[i]]);
			}
			if(parametro == "votos"){
			      sortable.sort(function(a, b) {return a[5] - b[5]}).reverse();
			} 
			else {
			      sortable.sort(function(a, b) {return a[4] - b[4]}).reverse();
			}
		
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

	</script>
</body>
</html>