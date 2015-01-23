<%-- 
    Document   : deleteRecords
    Created on : Jan 22, 2015, 2:18:27 PM
    Author     : aryner
--%>
<%@page import="java.util.*"%>

<h3>Check the boxes next to the records you wish to delete</h3>

<div class="container">
<%
Vector<String> ungraded = (Vector)request.getAttribute("ungraded");
Vector<String> gradedOnce = (Vector)request.getAttribute("gradedOnce");
Vector<String> needsAdj = (Vector)request.getAttribute("needsAdj");
Vector<String> needsReview = (Vector)request.getAttribute("needsReview");
Vector<String> reviewed = (Vector)request.getAttribute("reviewed");
Vector<String> adjudicated = (Vector)request.getAttribute("adjudicated");
reviewed.addAll(adjudicated);
%>
<form action="removeRecords" method="POST">
	<input type="submit" value="Delete selected records" class="btn">
	<div class="wideContainer">
	<div class="fifth-column"><h3>Ungraded</h3>
	<%
		for(String record : ungraded) {
			out.print("<input type='checkbox' name='"+record+"' value='1'>"+record+"<br>");
		}
	%>
	</div>
	<div class="fifth-column"><h3>Graded Once</h3>
	<%
		for(String record : gradedOnce) {
			out.print("<input type='checkbox' name='"+record+"' value='1'>"+record+"<br>");
		}
	%>
	</div>
	<div class="fifth-column"><h3>Needs Adjudication</h3>
	<%
		for(String record : needsAdj) {
			out.print("<input type='checkbox' name='"+record+"' value='1'>"+record+"<br>");
		}
	%>
	</div>
	<div class="fifth-column"><h3>Needs Review</h3>
	<%
		for(String record : needsReview) {
			out.print("<input type='checkbox' name='"+record+"' value='1'>"+record+"<br>");
		}
	%>
	</div>
	<div class="fifth-column"><h3>Reviewed/Adjudicated</h3>
	<%
		for(String record : reviewed) {
			out.print("<input type='checkbox' name='"+record+"' value='1'>"+record+"<br>");
		}
	%>
	</div>
	</div>
	<br>
</form>
</div>