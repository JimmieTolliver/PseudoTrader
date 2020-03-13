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

<title>Welcome ${firstName }</title>
</head>
<body class="bg-color1">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-8 title">
				Congratulations ${firstName } you just created a psuedoTrader
				account!

				<form action="${pageContext.request.contextPath}/html/index.jsp"
					method="post">
					<input type="submit" value="Login">
				</form>
			</div>
		</div>
	</div>
</body>
</html>