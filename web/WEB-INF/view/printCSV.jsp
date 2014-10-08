<%-- 
    Document   : printCSV
    Created on : Oct 7, 2014, 4:33:17 PM
    Author     : aryner
--%>
<%@page import="java.util.*"%>

<%
Vector<String> pics = (Vector)request.getAttribute("pics");
Vector<String> hvf = (Vector)request.getAttribute("hvf");
%>

<form action="printCSVs" method="POST">
	<input type ="submit" value="Print CSVs to your Desktop" class="btn">
</form>
<br><br>
<%
for(int i=0; i<pics.size(); i++) {
	out.print(pics.get(i)+"<br>");
}
out.print("<br><br>");
for(int i=0; i<hvf.size(); i++) {
	out.print(hvf.get(i)+"<br>");
}
%>