<%-- 
    Document   : printCSV
    Created on : Oct 7, 2014, 4:33:17 PM
    Author     : aryner
--%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>

<%
Vector<String> hvf = (Vector)request.getAttribute("hvf");
Vector<String> fdt = (Vector)request.getAttribute("fdt");
Vector<String> mdt = (Vector)request.getAttribute("mdt");
Vector<String> oct = (Vector)request.getAttribute("oct");
Vector<String> stereo = (Vector)request.getAttribute("stereo");
Vector<String> nethra = (Vector)request.getAttribute("nethra");
Vector<String> iPad = (Vector)request.getAttribute("iPad");
%>
<div class='container'>
<div class='wide-column'>
<form action="printCSVs" method="POST">
	<input type='hidden' name='type' value='<%out.print(BaseTest.HVF);%>'>
	<input type ="submit" value="Print the HVF CSV to your Desktop" class="btn">
</form>
</div>
<div class='wide-column'>
<form action="printCSVs" method="POST">
	<input type='hidden' name='type' value='<%out.print(BaseTest.FDT);%>'>
	<input type ="submit" value="Print the FDT CSV to your Desktop" class="btn">
</form>
</div>
<div class='wide-column'>
<form action="printCSVs" method="POST">
	<input type='hidden' name='type' value='<%out.print(BaseTest.MDT);%>'>
	<input type ="submit" value="Print the MDT CSV to your Desktop" class="btn">
</form>
</div>
	<div class='spacer'></div>
<div class='wide-column'>
<form action="printCSVs" method="POST">
	<input type='hidden' name='type' value='<%out.print(BaseTest.OCT);%>'>
	<input type ="submit" value="Print the OCT CSV to your Desktop" class="btn">
</form>
</div>
<div class='wide-column'>
<form action="printCSVs" method="POST">
	<input type='hidden' name='type' value='<%out.print(BaseTest.STEREO);%>'>
	<input type ="submit" value="Print the stereo CSV to your Desktop" class="btn">
</form>
</div>
<div class='wide-column'>
<form action="printCSVs" method="POST">
	<input type='hidden' name='type' value='<%out.print(BaseTest.NETHRA);%>'>
	<input type ="submit" value="Print the 3Nethra CSV to your Desktop" class="btn">
</form>
</div>
<div class='wide-column'>
<form action="printCSVs" method="POST">
	<input type='hidden' name='type' value='<%out.print(BaseTest.IPAD);%>'>
	<input type ="submit" value="Print the iPad CSV to your Desktop" class="btn">
</form>
</div>
</div>
<br><br>
<%
for(int i=0; i<hvf.size(); i++) {
	out.print(hvf.get(i)+"<br>");
}
%>
<br><br>
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
<br><br>
<%
for(int i=0; i<iPad.size(); i++) {
	out.print(iPad.get(i)+"<br>");
}
%>