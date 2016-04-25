<nav class="navbar">
				<ul class="nav nav-tabs">
					<li><a href="/crear">Crear escenario</a></li>
					<li><a href="/simular">Simular escenario</a></li>
					<c:if test="${not empty user}">
						<li><a href="/foro">Foros de discusión</a></li>
					</c:if>
				</ul>
</nav>