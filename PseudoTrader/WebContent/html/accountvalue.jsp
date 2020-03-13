<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<h5 class="title">Account Value</h5>
<table class="bg-color2">
	<tr class="stripes">
		<td><strong>Initial Cash: </strong>
			<div class="dollar" style="display: inline-block">${intitialCash}</div></td>
	</tr>
	<tr class="stripes">
		<td><strong>Current Cash: </strong>
			<div class="dollar" style="display: inline-block">${currCash}</div></td>
	</tr>
	<tr class="stripes">
		<td><strong>Stock Value: </strong>
			<div class="dollar" style="display: inline-block">${totalStockValue}</div></td>
	</tr>
	<tr class="stripes">
		<td><strong>Total Value: </strong>
			<div class="dollar" style="display: inline-block"><jsp:text> ${currCash + totalStockValue}</jsp:text></div></td>
	</tr>

</table>