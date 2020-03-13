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

<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script src="//cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script>
	$(document).ready(function() {
		$("div.dollar").each(function() {
			$(this).html(parseFloat($(this).text()).toLocaleString('en-US', {
				style : 'currency',
				currency : 'USD'
			}));
		})
	})
</script>
<title>Trade ${ticker}</title>
</head>
<header><%@ include file="./navbar.jsp"%></header>
<body class="bg-color1">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-8 title">

				<form action="./QuickTradeServlet" method="post">
					<table>
						<tr>
							<th style="padding-right: 15px">Ticker</th>
							<th style="padding-right: 15px">Name</th>
							<th style="padding-right: 15px">Current Price</th>
							<th style="padding-right: 15px">Buy</th>
							<th style="padding-right: 15px">Sell</th>
							<th style="padding-right: 15px">Qty</th>

						</tr>
						<tr>
							<td>${ticker}</td>
							<td style="padding-right: 10px">${stockName}</td>
							<td><div class="dollar">${currPrice}</div></td>
							<td><input type="radio" name="trade"
								value="<c:out value="${ticker}_${currPrice}_buy"/>"></td>
							<td><input type="radio" name="trade"
								value="${ticker}_${currPrice}_sell"></td>
							<td style="padding-right: 15px"><input type="text" id="qty"
								name="qty" value=0><br></td>

						</tr>
					</table>
					<br> <input type="reset" value="Clear"> <input
						type="submit" value="Submit">

				</form>
			</div>
		</div>
	</div>
</body>
<footer><%@ include file="./footer.html"%></footer>
</html>