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
	int HVFACount= 0;
	int access = (Integer)request.getAttribute("access");
	if(access > 0) {
		HVFACount = (Integer)request.getAttribute("HVFACount");
	}
%> 

<h2>Home</h2>
<div class='wideContainer'>
<%
	if(access == 2){
%>
	<div class="thinColumn">
		<a href="/HVF/OpHVFtest" class="btn menuBtn">HVF test</a>
	</div>

<%
	}
	if(access == 0 ){
%>
<!--
	<div class="thinColumn">
		<a href="/Glaucoma/FDTtest" class="btn menuBtn">FDT test</a>
	</div>
-->
<%
	}
	if (access == 0 || (access == 1)) {
%>
	<div class="thinColumn">
		<a href="/HVF/HVFtest" class="btn menuBtn">HVF test</a> 
	</div>
<%
	}
	if (access == 0) {
%>
<!--
	<div class="thinColumn">
		<a href="/Glaucoma/MDTtest" class="btn menuBtn">MDT test</a> 
	</div>
-->
<%
	}
	if (access == 0) {
%>
<!--
	<div class="thinColumn">
		<a href="/Glaucoma/OCTtest" class="btn menuBtn">OCT test</a> 
	</div> 
-->
<%
	}
	if (access == 0) {
%>
<!--
	<div class="thinColumn">
		<a href="/Glaucoma/stereo" class="btn menuBtn">Stereo</a>
	</div>
-->
<%
	}
	if (access == 0) {
%>
<!--
	<div class="thinColumn">
		<a href="/Glaucoma/nethra" class="btn menuBtn">3Nethra</a>
	</div>
-->
<%
	} 
%>
</div>
<br>
<div class='container'>
	<div class='column'>
		<a href='/HVF/upload' class='btn menuBtn'>Upload Pictures</a>
	</div>
	<div class="thinColumn">
		<a href="/HVF/printCSV" class="btn menuBtn">Get CSV files</a>
	</div>
</div>

<div class='container'>
	<div class='fifth-column'><h3>Ungraded</h3>
		<%
			Vector<String> ungraded = (Vector)request.getAttribute("ungraded");
			String name = "";
			for(int i=0; i<ungraded.size(); i++) {
				name = ungraded.get(i);
				out.print(name.substring(0,name.length()-4)+"<br>");
			}
		%>
	</div>
	<div class='fifth-column'><h3>Graded once</h3>
		<%
			Vector<String> gradedOnce = (Vector)request.getAttribute("gradedOnce");
			name = "";
			for(int i=0; i<gradedOnce.size(); i++) {
				name = gradedOnce.get(i);
				out.print(name.substring(0,name.length()-4)+"<br>");
System.out.println("1");
			}
		%>
	</div>
	<div class='fifth-column'><h3>Needs Adjudication</h3>
		<%
			Vector<String> needsAdj = (Vector)request.getAttribute("needsAdj");
			name = "";
			for(int i=0; i<needsAdj.size(); i++) {
				name = needsAdj.get(i);
				out.print(name.substring(0,name.length()-4)+"<br>");
System.out.println("2");
			}
		%>
	</div>
	<div class='fifth-column'><h3>Needs review</h3>
		<%
			Vector<String> needsReview = (Vector)request.getAttribute("needsReview");
			name = "";
			for(int i=0; i<needsReview.size(); i++) {
				name = needsReview.get(i);
				out.print(name.substring(0,name.length()-4)+"<br>");
System.out.println("3");
			}
		%>
	</div>
	<div class='fifth-column'><h3>Reviewed / Not glaucoma</h3>
		<%
			Vector<String> reviewed = (Vector)request.getAttribute("reviewed");
			name = "";
			for(int i=0; i<reviewed.size(); i++) {
				name = reviewed.get(i);
				out.print(name.substring(0,name.length()-4)+"<br>");
System.out.println("4");
			}
		%>
	</div>
</div>