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
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/landing.css" />">

<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>



<title>Stock Research</title>
</head>
<header><%@ include file="./navbar.jsp"%></header>
<body class="bg-color1">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h4 class="title">Stock Quote</h4>
				<br>
				<form action="../QuoteServlet" method="get">
					Ticker Symbol:<br> <input type="text" id="stockName"
						name="ticker"> <br> <br> <input type="submit"
						value="Submit">
				</form>

			</div>
		</div>
	</div>
</body>
<footer><%@ include file="./footer.html"%></footer>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<!--  Autocomplete tickers  -->
<script>
	var xhReq = new XMLHttpRequest();
	xhReq.open("GET", "../data/tickers.json", false);
	xhReq.send(null);
	var mydata = JSON.parse(xhReq.responseText);

	$('#stockName').autocomplete({
		source : function(request, response) {
			response($.map(mydata, function(obj, key) {

				var name = obj.Symbol.toUpperCase();

				if (name.indexOf(request.term.toUpperCase()) != -1) {
					return {
						label : obj.Symbol + " (" + obj[`Company Name`] + ")", // Label for Display
						value : obj.Symbol
					// Value
					}
				} else {
					return null;
				}
			}));
		},
		focus : function(event, ui) {
			event.preventDefault();
		},
		// Once a value in the drop down list is selected, do the following:
		select : function(event, ui) {
			event.preventDefault();
			// place the person.given_name value into the textfield called 'select_origin'...
			$('#stockName').val(ui.item.label);
			// ... any other tasks (like setting Hidden Fields) go here...
		}
	});
</script>
</html>