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
   margin-left: 320px;
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
		<form action="javascript:demoFromHTML()">
    		<input type="submit" style="margin-top:10px;" class="btn btn-success center-block botonEliminar "value="Generar informe PDF">
		</form>
			<h1>Informe de la simulación</h1>
			<h3><center><c:out value='${escenario.titulo}'/></center></h3>		
			<HR width=95% align="center" class="btn-info"
				style="margin-top: 10px; margin-top: 10px;">
			<div class="row" align="center" style="margin-top: 15px;margin-left:2%; width:96%">

				<h4 style="margin-left: 5px;"><u>Parámetros de la simulación:</u></h4>
				    <div class="panel panel-default col-lg-4">
      <div class="panel-heading">Sistema de proporcionalidad</div>
      <div class="panel-body"><c:out value="${escenario.sistema }"/></div>
   	 </div>
   	 				    <div class="panel panel-default col-lg-4">
      <div class="panel-heading">Circunscripciones</div>
      <div class="panel-body"><c:out value="${escenario.circunscripciones }"/></div>
   	 </div>
   	 				    <div class="panel panel-default col-lg-4">
      <div class="panel-heading">% escaños para la mayoria absoluta</div>
      <div class="panel-body"><c:out value="${escenario.mayoria_abs }"/></div>
   	 </div>
   	 </div>
			<HR width=95% align="center" class="btn-info"
				style="margin-top: 10px; margin-top: 10px;">

			<div class="col-lg-12 center-block" id="editarsimular"
				style="margin-top: 18px;"></div>
			<div class="container">
					<div class="col-lg-offset-1 col-lg-9">
						<canvas id="piechart" width="900" height="500"></canvas>
						<div class="donut-inner">
    						<h5><span id="numEsc"> </span> escaños</h5>
						</div>
					</div>
				</div>
				<div class="panel-body"><h2><center>Resultados generales</center></h2></div>
					<table class="table table-hover" id="generalesTable">
						<thead>
							<tr>
								<th class="col-lg-3">Partido</th>
								<th class="col-lg-3">Escaños obtenidos</th>
								<th class="col-lg-3">Votos</th>							
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${resultadosCongreso}" var="r">
								<tr>
									<th scope="row"><h5>${ r.partido }</h5></th>
									<th>${r.escanos }</th>
									<th>${r.votos }</th>
									
								</tr>
							</c:forEach>
						</tbody>
					</table>
		</div>
		<div style="padding: 50px;"></div>
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
	<script type='text/javascript' src='./js/jsPDF_debug.js'></script>
		<script src="http://html2canvas.hertzen.com/build/html2canvas.js"></script>
	
	
		
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

	
		
piechart(partidos);
	
	 });
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
    	var siglas, nombre, color, imagen, partido, escanos, votos = "";
    	<c:forEach items="${resultadosCongreso}" var="r">
				partido = "${r.partido}";
				escanos =  "${r.escanos}";
				votos =  "${r.votos}";
				for( var j = 0; j< arrayPartidos.siglas.length; j++){
					if(arrayPartidos.siglas[j] == partido){
						 siglas = arrayPartidos.siglas[j];
						 nombre = arrayPartidos.nombre[j];
						 color = arrayPartidos.color[j];
						 imagen = arrayPartidos.imagen[j];
					}
				}
			if(!partidosCongreso.siglas.includes(siglas) && escanos>0){
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
    	var ctx = document.getElementById("piechart");
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


    function demoFromHTML() {

    	ocultar();
    	//code before the pause

    	var pdf = new jsPDF('p', 'pt', 'a2');
      	 var options = {
      	    pagesplit: true
      	};

      	pdf.addHTML($('body'), 0, 0, options, function(){
      		var titulo = "<c:out value='${escenario.titulo}'/>";
      		if(titulo == "")
      			titulo = "informe";
      	    pdf.save(titulo+".pdf");
      	});
      	
    	setTimeout(function(){
          	mostrar();
    	}, 2000);
    }
    
	$('#generalesTable').DataTable( {
	    "ordering": true, 
        "order": [[ 2, "desc" ]],
        "searching": false,
        "pageLength": 52,
        "paging": false,
	    "language": {
	        "lengthMenu": "Mostrando _MENU_ entradas por página",
	        "zeroRecords": "Niguna entrada coincide con su búsqueda",
	        "info": "Mostrando _PAGE_ de _PAGES_",
	        "infoEmpty": "No existe el comentario",
	        "infoFiltered": "(filtrado de _MAX_ entradas totales)"
	    }
	} ); 
	
	function ocultar(){
		$('.nav-tabs').hide();
		$('.navbar-right').hide();
		$('.botonEliminar').hide();


	}
	
	function mostrar(){
		$('.nav-tabs').show();
		$('.navbar-right').show();
		$('.botonEliminar').show();



	}
</script>
</body>
</html>