
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<% if(session.getAttribute("user") == "" ) {response.sendRedirect((String) session.getAttribute("url"));} 
	else if(session.getAttribute("escenarios") == null) {response.sendRedirect("/registrar.jsp");}
	%>
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
								<th class="col-lg-3" >Tema</th>
								<th class="col-lg-3">Autor</th>
								<th class="col-lg-3">Fecha</th>
								<th class="col-lg-3"></th>
							</tr>
						</thead>
						<tbody>
								<c:forEach items="${escenarios}" var="escenario">
									<tr <c:if test="${escenario.usuario == user}"> class="info" </c:if> >									
										<form method="get" action="/forosimular">
											<input type="hidden" name="escenarioId" id="escenarioId" value="${escenario.id}"/>
											<th scope="row"><h5><input type="submit" value="${escenario.titulo}"/></h5></th>
											<td><c:out value="${escenario.usuario}"/></td>
											<td style="color: #A4A4A4;"><c:out value="${escenario.fecha}"/></td>
											<td>
										</form>
										<c:if test="${escenario.usuario == user}">
										<form method="post" action="/borrasimulacion">
										<input type="hidden" name="escenarioId" id="escenarioId" value="${escenario.id}"/>
										<input type="submit" class="btn btn-default" data-toggle="confirmation" data-placement="bottom" value="Borrar escenario"></n>
										</form>
										</c:if>
										</td>
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
	<script type="text/javascript" src="./js/bootstrap-confirmation.js"></script>	
	</footer>
	<script>	
	//Datatable
	$('#foroTable').DataTable( {
        "ordering": true, 
        "language": {
            "lengthMenu": "Mostrando _MENU_ entradas por página",
            "zeroRecords": "Niguna entrada coincide con su búsqueda",
            "info": "Mostrando _PAGE_ de _PAGES_",
            "infoEmpty": "No existe el escenario",
            "infoFiltered": "(filtrado de _MAX_ entradas totales)"
        }
    } ); 
	
	//Etiqueta de confirmacion de borrado
	$('[data-toggle="confirmation"]').confirmation({ btnOkLabel: "Sí", btnCancelLabel: "No", title:"¿Confirmar borrado de escenario?", btnOkClass:"btn btn-info", btnCancelClass:"btn btn-danger", animation:"true"});
	
	</script>
</body>
</html>
