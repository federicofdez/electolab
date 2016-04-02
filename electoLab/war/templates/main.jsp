<nav class="navbar">
				<ul class="nav nav-tabs">
					<li><a href="/crear.jsp">Crear escenario</a></li>
					<li><a href="/simular.jsp">Simular escenario</a></li>
					<c:if test="${not empty user}">
						<li><a href="foro.jsp">Foros de discusión</a></li>
					</c:if>
				</ul>
</nav>