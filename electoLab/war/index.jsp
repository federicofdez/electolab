<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="templates/head.html" %>
</head>
<body>
	<header>
		<%@ include file="templates/header.html" %>
	</header>
	<main>        
	<div class="container">
        <%@ include file="templates/main.html" %>
			<div class="container-fluid" id="main-content">
                <div class="panel panel-info col-lg-12" style="min-height:150px; text-align:center">
  <div class="panel-heading">
      <i class="
glyphicon glyphicon-envelope "></i>
    <h3 class="panel-title">     ¿Qué es ElectoLab?
</h3>
  </div>
  <div class="panel-body">
		ElectoLab es una aplicación que le permitirá realizar simulaciones electorales en la cual usted será el que ponga las normas. 
  </div>
</div>
                <div class="panel panel-info col-lg-5 col-lg-offset-1" style="min-height:180px; text-align:center">
  <div class="panel-heading">
      <i class="
glyphicon glyphicon-list-alt"></i>
      
    <h3 class="panel-title">    ¿Qué puedo elegir con ElectoLab?
</h3>
  </div>
  <div class="panel-body">
Elija los partidos, las circunscripciones en las que se presentan y los votos que obtienen en ellas, el sistema de proporcionalidad y los escaños asignados a cada circunscripción.  </div>
</div>
                <div class="panel panel-info col-lg-5" style="min-height:180px; text-align:center">
  <div class="panel-heading">
       <i class="
glyphicon glyphicon-signal"></i>
    <h3 class="panel-title">    ¿Cómo verá los resultados?
</h3>
  </div>
  <div class="panel-body">
Con los datos que usted que haya introducido nosotros generaremos mapas con distribuciones de votos y escaños, y un gráfico representando cómo quedará el Congreso.   </div>
</div>
                <div class="panel panel-info col-lg-5 col-lg-offset-1" style="min-height:180px ; text-align:center">
  <div class="panel-heading">
       <i class="
glyphicon glyphicon-download"></i>
    <h3 class="panel-title">    ¿Podré exportar esos resultados?
</h3>
  </div>
  <div class="panel-body">
Con tan solo un click podrá generar un informe con la simulación realizada.  </div>
</div>
                <div class="panel panel-info col-lg-5" style="min-height:180px; text-align:center">
  <div class="panel-heading">
       <i class="
glyphicon glyphicon-user "></i>
    <h3 class="panel-title">  ¿Qué más me puede ofrecer ElectoLab?
</h3>
  </div>
  <div class="panel-body">
		Regí­strese con su correo y su grupo de trabajo para poder comentar las simulaciones que haga con su grupo.
  </div>
</div>
                
<div class="buttons text-center col-lg-12">
    <a href="/crear"> <button type="submit" class="btn btn-info">Crear escenario nuevo</button> </a>
    <a href="/crear"><button type="submit" class="btn btn-info">Cargar escenario previo</button> </a>
    <div style="padding: 10px;"></div>
    </div>
			</div>
         <div class= "license col-lg-6"> <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/"><img alt="Licencia de Creative Commons" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png" /></a><br /><span xmlns:dct="http://purl.org/dc/terms/" href="http://purl.org/dc/dcmitype/Dataset" property="dct:title" rel="dct:type">ElectoLab</span> by <span xmlns:cc="http://creativecommons.org/ns#" property="cc:attributionName">Rodrigo Barbado, Federico FernÃ¡ndez, Sergio GarcÃ­a, Arturo PavÃ³n, Carlos Vega</span> is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/">Creative Commons Reconocimiento-NoComercial-CompartirIgual 4.0 Internacional License</a>.</div>
		</div>
    				<div style="padding: 40px;"></div>


	</main>
    
	<footer class="footer">
		<div class="container">
			<p>Desarrollado por Grupo 20. Todos los derechos reservados.</p>
		</div>
		<%@ include file="templates/footer.html" %>
	</footer>
</body>
</html>