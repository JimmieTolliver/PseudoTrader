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
<title>PseudoTrade Quotes</title>

</head>
<header><%@ include file="./navbar.jsp"%></header>
<body class="bg-color1">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h4 class="title">${name}(${ticker })</h4>
				<div style="font-size: 1.5em">
					<strong>$${value} <span id="marketChange"
						style="font-size: .8em">${marketChange }
							(${marketChangePercent })</span></strong>
				</div>
				<table class="bg-color2">
					<tr class="stripes">
						<td>Previous Close: $${prevClose }</td>
						<td>Market Cap: ${marketCap }</td>
					</tr>
					<tr class="stripes">
						<td>Open: $${marketOpen }</td>
						<td>Beta (5Y Monthly): ${beta }</td>
					</tr>
					<tr class="stripes">
						<td>Day's Range: $${dayLow } - $${dayHigh }</td>
						<td>PE Ratio (TTM): ${pe }</td>
					</tr>
					<tr class="stripes">
						<td style="padding-right: 15px">52 Week Range: $${yearLow } -
							$${yearHigh }</td>
						<td>EPS (TTM): ${eps }</td>
					</tr>
					<tr class="stripes">
						<td>Volume: ${volume }</td>
						<td>Earnings Date: ${earningsDate }</td>
					</tr>
					<tr class="stripes">
						<td>Avg. Volume ${avgDailyVol }</td>
						<td>1y Target Est: $${targetPrice }</td>
					</tr>
				</table>
				<br>
				<form action="./TradeServlet" method="post">
					<button type="submit" name="trade" value="${ticker}">Buy</button>
				</form>

				<form class="userinfo" action="./html/research.jsp">
					<input type="submit" value="New Quote">
				</form>
			</div>
		</div>
	</div>
</body>
<footer><%@ include file="./footer.html"%></footer>
<script src="${pageContext.request.contextPath}/scripts/fontColor.js"></script>
</html>