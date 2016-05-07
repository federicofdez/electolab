		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/">
						<img src="img/logo_electolab.png" alt="Logo" />
						<span>ElectoLab</span>
					</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right">
						<c:if test="${empty user}">
							<li><span class="glyphicon glyphicon-user"></span><span><a href="registrar.jsp">Registrarse</a></span></li>
						</c:if>
						<li><span class="glyphicon glyphicon-log-in" style="margin-left:5px;"> </span> <c:out value="${user}"/><span><a href='<c:url value="${url}"/>'> <c:out value="${urlLinktext}"/></a></span></li>
					</ul>
				</div><!-- /.navbar-collapse -->
			</div><!-- /.container-fluid -->
		</nav>



