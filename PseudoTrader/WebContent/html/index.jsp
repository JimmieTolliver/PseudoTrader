<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>pseudoTrader</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/landing.css">

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body id="bgImage">
	<h1 id="trademark">pseudoTrader</h1>
	<div class="container">
		<div class="d-flex justify-content-center h-100">
			<div class="card">
				<div class="card-header">
					<h3>Sign In</h3>
					<h6>${message }</h6>
					<div class="d-flex justify-content-end social_icon">
						<span><i class="fab fa-facebook fa-xs"></i></span> <span><i
							class="fab fa-twitter fa-xs"></i></span> <span><i
							class="fab fa-instagram fa-xs"></i></span>
					</div>
				</div>
				<div class="card-body">
					<form action="${pageContext.request.contextPath}/AuthServlet" method="post">
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<input type="text" class="form-control" name="username"
								placeholder="username" required>

						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input type="password" class="form-control" name="password"
								placeholder="password" required>
						</div>
						<div class="row align-items-center remember">
							<input type="checkbox">Remember Me
						</div>
						<div class="form-group">
							<input type="submit" value="Login"
								class="btn float-right login_btn">
						</div>
					</form>
				</div>
				<div class="card-footer">
					<div class="d-flex justify-content-center links">
						Don't have an account?<a id="signup" href="${pageContext.request.contextPath}/html/newuser.jsp">Sign Up</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<footer style="color: white"><%@ include file="./footer.html"%></footer>
</html>