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

<link href="//cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/landing.css" />">

<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

<title>Invalid transaction</title>
</head>
<header><%@ include file="./navbar.jsp"%></header>
<body class="bg-color1">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-8">
				<h4 class="title">${message}</h4>
				<br>
				<form action="./PortfolioServlet" method="post">
					<input type="submit" value="Return to Portfolio">
				</form>
			</div>
		</div>
	</div>
</body>
<footer><%@ include file="./footer.html"%></footer>
</html>