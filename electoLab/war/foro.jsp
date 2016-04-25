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

			<nav class="navbar">
				<ul class="nav nav-tabs">
					<li ><a href="crear.html">Crear escenario</a></li>
					<li><a href="simular.html">Simular escenario</a></li>
					<li class="active"><a href="foro.html" class="active">Foros de discusión</a></li>
				</ul>
			</nav>
			<div class="container-fluid" id="main-content">
				<h1>Foros de tu grupo</h1>
				<div class="col-lg-9 col-centered">
					<table class="table table-hover" id="foroTable">
						<thead>
							<tr>
								<th class="col-lg-3">Tema</th>
								<th class="col-lg-3">Autor</th>
								<th class="col-lg-3">Fecha</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row"><h5><a href="ejemplosimu.html">Simulación 1</a></h5></th>
								<td>Fede</td>
								<td style="color: #A4A4A4;">00/00/0000</td>
							</tr>
							<tr>
								<th scope="row"><h5><a href="ejemplosimu.html">Simulación 2</a></h5></th>
								<td>Rodrigo</td>
								<td style="color: #A4A4A4;">00/00/0000</td>
							</tr>
							<tr>
								<th scope="row"><h5><a href="ejemplosimu.html">Simulación 3</a></h5></th>
								<td>Arturo</td>
								<td style="color: #A4A4A4;">00/00/0000</td>
							</tr>
							<tr>
								<th scope="row"><h5><a href="ejemplosimu.html">Simulación 4</a></h5></th>
								<td>Carlos</td>
								<td style="color: #A4A4A4;">00/00/0000</td>
							</tr>
							<tr>
								<th scope="row"><h5><a href="ejemplosimu.html">Simulación 5</a></h5></th>
								<td>Sergio</td>
								<td style="color: #A4A4A4;">00/00/0000</td>
							</tr>

						</tbody>
					</table>
					<div class="col-lg-12" style="margin-left: 280px;">
					<ul class="pagination">
						<li class="center-block"><a href="#"><<</a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">>></a></li>
					</ul>
					</div>
				</div>
			</div>
		</div>
	</main>
	<footer class="footer">
		<div class="container">
			<p>Desarrollado por Grupo 20. Todos los derechos reservados.</p>
		</div>
				<%@ include file="templates/footer.html" %>
		
	</footer>
	<script type="text/javascript" src="./js/jquery.min.js"></script>
	<script type="text/javascript" src="./js/bootstrap.min.js"></script>
	<script type="text/javascript" src="./js/dropdown.js"></script>
</body>
</html>
