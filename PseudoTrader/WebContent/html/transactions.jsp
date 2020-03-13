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

<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!--  Format dollar values  -->
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
</head>
<header><%@ include file="./navbar.jsp"%></header>
<body class="bg-color1">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-8">

				<h4 class="title">${firstName}'s Transaction History</h4>
				<form action="./TradeServlet" method="post">
					<table class="bg-color2">
						<tr>
							<th class="col-pad">Date</th>
							<th class="col-pad">Ticker</th>
							<th class="col-pad">Name</th>
							<th class="col-pad">Shares</th>
							<th class="col-pad">Type</th>
							<th class="col-pad">Price</th>
							<th class="col-pad">Amount</th>
						</tr>
						<c:forEach items="${transactions}" var="transaction">
							<tr class="stripes">
								<td class="col-pad">${transaction.getDate()}</td>
								<td class="col-pad">${transaction.getStock().getTicker()}</td>
								<td class="col-pad">${transaction.getStock().getName()}</td>
								<td class="col-pad">${transaction.getTradeShares()}</td>
								<td class="col-pad"><c:out
										value="${transaction.getBuy() eq 'true' ? 'buy': 'sell'}" /></td>
								<td class="col-pad"><div class="dollar">${transaction.getTradePrice()}</div></td>
								<td class="col-pad"><div class="dollar">${transaction.getTradeShares() * transaction.getTradePrice()}</div></td>
							</tr>
						</c:forEach>
					</table>
				</form>
			</div>
			<div class="col-md-4">
				<%@ include file="./accountvalue.jsp"%>
			</div>
		</div>
	</div>
</body>
<footer><%@ include file="./footer.html"%></footer>
</html>