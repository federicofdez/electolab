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
#map2 {
  width: 70%;
  height: 500px;
}
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
				      <div id="map2" style=" border: 1px solid #AAA;"></div>
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
<script>
$(document).ready(function(){
	var map = new L.Map("map", {center: [39.9855, -3.7353], zoom: 5});
	var Esri_WorldGrayCanvas = L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/Canvas/World_Light_Gray_Base/MapServer/tile/{z}/{y}/{x}.png', {
	attribution: 'Tiles &copy; Esri &mdash; Esri, DeLorme, NAVTEQ',
	maxZoom: 16
	}).addTo(map);

	var svg = d3.select(map.getPanes().overlayPane).append("svg"),
	g = svg.append("g").attr("class", "leaflet-zoom-hide");

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
			.style("fill", function (d) {
				return (d.color = "red");
		})		

		.on('click', function (d) {
			alert(d.properties.name)
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


	var map2 = new L.Map("map2", {center: [39.9855, -3.7353], zoom: 5});
	var Esri_WorldGrayCanvas = L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/Canvas/World_Light_Gray_Base/MapServer/tile/{z}/{y}/{x}.png', {
	attribution: 'Tiles &copy; Esri &mdash; Esri, DeLorme, NAVTEQ',
	maxZoom: 16
	}).addTo(map2);

	var svg = d3.select(map2.getPanes().overlayPane).append("svg"),
	g = svg.append("g").attr("class", "leaflet-zoom-hide");

	d3.json("./json/spain.json", function(error, collection) {
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
			.style("fill", function (d) {
				return (d.color = "red");
		})		

		.on('click', function (d) {
			alert(d.properties.name)
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
	map2.on("viewreset", reset);
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
	var point = map2.latLngToLayerPoint(new L.LatLng(y, x));
	this.stream.point(point.x, point.y);
	}

	});

</script>
</body>
</html>