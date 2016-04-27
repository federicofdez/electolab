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
	<%@ include file="templates/head.html" %>
</head>
<body>
	<header>
		<%@ include file="templates/header.jsp" %>
	</header>
	<main>
		<div class="container">
		<%@ include file="templates/main.jsp"%>
			<div class="container-fluid" id="main-content">
				<h1>Foros de tu grupo</h1>
				<div>
					<table class="table table-hover" id="foroTable">
						<thead>
							<tr>
								<th class="col-lg-4" >Tema</th>
								<th class="col-lg-4">Autor</th>
								<th class="col-lg-4">Fecha</th>
							</tr>
						</thead>
						<tbody>
								<c:forEach items="${escenarios}" var="escenario">
									<tr>
										<form method="get" action="/forosimular">
											<input type="hidden" name="escenarioId" id="escenarioId" value="${escenario.id}"/>
											<th scope="row"><h5><input type="submit" value="${escenario.id}"/></h5></th>
											<td><c:out value="${escenario.usuario}"/></td>
											<td style="color: #A4A4A4;">00/00/0000</td>
										</form>
									</tr>
								</c:forEach>
						</tbody>
					</table>
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
	<script>	
	$('#foroTable').DataTable( {
        "ordering": true, 
    } );  </script>
</body>
</html>
