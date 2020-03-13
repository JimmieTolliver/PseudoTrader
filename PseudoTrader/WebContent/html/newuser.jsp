<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/landing.css" />">

<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<title>Create pseudoTrader Account</title>
</head>
<body class="bg-color1">

	<div class="container-fluid">
		<h4 class="title">Create New Account</h4>
		<h6>${message }</h6>
		<div class="row">
			<div class="col-md-12">

				<form action="${pageContext.request.contextPath}/AccountServlet"
					method="post">


					<label class="userinfo" for="fname">first name:</label><br>
					<input type="text" id="fname" name="fname" placeholder="first name"><br>
					
					<label class="userinfo" for="lname">last name:</label><br> 						
					<input type="text" id="lname" name="lname" placeholder="last name"><br>
					
					<label class="userinfo" for="email">email:</label><br>	
					<input type="email" id="email" name="email" placeholder="email"><br>
					
					<label class="userinfo" for="username">username:</label><br>	
					<input type="text" id="username" name="username" placeholder="username"><br>
					
					<label class="userinfo" for="password">password:</label><br>
					<input type="password" id="password1" name="password1" placeholder="password"><br>
					
					<label class="userinfo" for="password">password:</label><br>
					<input type="password" id="password2" name="password2" placeholder="password"><br>
					

					<br> <input type="submit" value="Submit">
				</form>
			</div>
		</div>
	</div>

</body>
</html>