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
<title>Update Account</title>
</head>
<header><%@ include file="./navbar.jsp"%></header>
<body class="bg-color1">

	<div class="container-fluid">
		<h4 class="title">Update Password</h4>
		<h6>${message }</h6>
		<br>
		<div class="row">
			<div class="col-md-12">
				<form
					action="${pageContext.request.contextPath}/UpdateAccountServlet"
					method="post">

					<label for="password">New Password:</label><br> <input
						type="password" id="password1" name="password1" value=""><br>
					<br> <label for="password">New Password:</label><br> <input
						type="password" id="password2" name="password2" value=""><br>


					<br> <input type="submit" value="Submit">
				</form>
			</div>
		</div>
	</div>

</body>
<footer><%@ include file="./footer.html"%></footer>
</html>