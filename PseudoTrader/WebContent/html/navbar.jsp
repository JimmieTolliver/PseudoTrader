<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>


<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<nav class="navbar navbar-expand-lg navbar-light navbar-color">

				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="navbar-toggler-icon"></span>
				</button>
				<a class="navbar-brand" id="navmark"
					href="${pageContext.request.contextPath}/PortfolioServlet">pseudoTrader</a>
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="navbar-nav">

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" id="navbarDropdownMenuLink"
							data-toggle="dropdown">Account</a>
							<div class="dropdown-menu"
								aria-labelledby="navbarDropdownMenuLink">
								<a class="dropdown-item" href="./html/research.jsp">Quotes</a> <a
									class="dropdown-item"
									href="${pageContext.request.contextPath}/TransactionServlet">Transactions</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="./html/updateaccount.jsp">Update
									Account</a>
							</div></li>
					</ul>

					<ul class="navbar-nav ml-md-auto">
						<li class="nav-item active"><a class="nav-link"
							href="${pageContext.request.contextPath}/LogoutServlet">Logout
								<span class="sr-only">(current)</span>
						</a></li>

					</ul>
				</div>
			</nav>
		</div>
	</div>
</div>

