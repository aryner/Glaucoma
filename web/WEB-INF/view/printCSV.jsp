<%-- 
    Document   : printCSV
    Created on : Oct 7, 2014, 4:33:17 PM
    Author     : aryner
--%>
<%@page import="java.util.*"%>

<%
Vector<String> fdt = (Vector)request.getAttribute("fdt");
Vector<String> mdt = (Vector)request.getAttribute("mdt");
Vector<String> oct = (Vector)request.getAttribute("oct");
Vector<String> stereo = (Vector)request.getAttribute("stereo");
Vector<String> nethra = (Vector)request.getAttribute("nethra");
%>

<form action="printCSVs" method="POST">
	<input type ="submit" value="Print CSVs to your Desktop" class="btn">
</form>
<br><br>
<!--
<form action="printFinishedCSVs" method="POST">
	<input type ="submit" value="Print CSVs of finished records to your Desktop" class="btn">
</form>
<br><br>
-->
<%
for(int i=0; i<fdt.size(); i++) {
	out.print(fdt.get(i)+"<br>");
}
%>
<br><br>
<%
for(int i=0; i<mdt.size(); i++) {
	out.print(mdt.get(i)+"<br>");
}
%>
<br><br>
<%
for(int i=0; i<oct.size(); i++) {
	out.print(oct.get(i)+"<br>");
}
%>
<br><br>
<%
for(int i=0; i<stereo.size(); i++) {
	out.print(stereo.get(i)+"<br>");
}
%>
<br><br>
<%
for(int i=0; i<nethra.size(); i++) {
	out.print(nethra.get(i)+"<br>");
}
%>