<%-- 
    Document   : home
    Created on : Aug 25, 2014, 2:47:12 PM
    Author     : aryner
--%>
<%@page import="java.util.*"%>

<%
if(session.getAttribute("errors") != null) {
	Vector<String> errors = (Vector)session.getAttribute("errors");
	session.removeAttribute("errors");

	out.print("<div class='error'>");
	out.print("<h4>The following files where not uploaded because they did not have a pdf extension:</h4>");
	for(int i=0; i<errors.size(); i++) {
		if (i>0) {out.print(", ");}
		out.print(errors.get(i)+"\n");
	}
	out.print("</div>");
}
%> 

<h2>Home</h2>
<div class='wideContainer'>
	<div class="thinColumn">
		<a href="/Glaucoma/FDTtest" class="btn menuBtn">FDT test</a>
	</div>
	<div class="thinColumn">
		<a href="/Glaucoma/HVFtest" class="btn menuBtn">HVF test</a> 
	</div>
	<div class="thinColumn">
		<a href="/Glaucoma/MDTtest" class="btn menuBtn">MDT test</a> 
	</div>
	<div class="thinColumn">
		<a href="/Glaucoma/OCTtest" class="btn menuBtn">OCT test</a> 
	</div> 
	<div class="thinColumn">
		<a href="/Glaucoma/stereo" class="btn menuBtn">Stereo</a>
	</div>
	<div class="thinColumn">
		<a href="/Glaucoma/nethra" class="btn menuBtn">3Nethra</a>
	</div>
</div>
<br>
<div class='container'>
	<div class='column'>
		<a href='/Glaucoma/upload' class='btn menuBtn'>Upload Pictures</a>
	</div>
</div>
