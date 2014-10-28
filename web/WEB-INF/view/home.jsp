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
	out.print("<h4>The following files where not uploaded because they did not have a pdf extension or they are already in the program:</h4>");
	for(int i=0; i<errors.size(); i++) {
		if (i>0) {out.print(", ");}
		out.print(errors.get(i)+"\n");
	}
	out.print("</div>"); 
} 
	ArrayList<String> needPics = (ArrayList)request.getAttribute("hvfNeedPictures");
	if(needPics != null && needPics.size() > 0) {
		out.print("<h4>The following have records but no pictures, please upload the PDFs that belong to them</h4>");
		for(int i=0; i<needPics.size(); i++) {
			if(i>0) { out.print(", "); }
			out.print(needPics.get(i));
		}
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
	<div class="thinColumn">
		<a href="/HVF/uploadData" class="btn menuBtn">Upload Data</a>
	</div>
</div>

<div class='container'>
	<%Vector<String> ungraded = (Vector)request.getAttribute("ungraded");%>
	<div class='fifth-column'><h3>Ungraded (<%out.print(ungraded.size());%>)</h3>
		<%
			String name = "";
			for(int i=0; i<ungraded.size(); i++) {
				name = ungraded.get(i);
				out.print(name.substring(0,name.length()-4)+"<br>");
			}
		%>
	</div>
	<%Vector<String> gradedOnce = (Vector)request.getAttribute("gradedOnce");%>
	<div class='fifth-column'><h3>Graded once (<%out.print(gradedOnce.size());%>)</h3>
		<%
			name = "";
			for(int i=0; i<gradedOnce.size(); i++) {
				name = gradedOnce.get(i);
				out.print(name.substring(0,name.length()-4)+"<br>");
			}
		%>
	</div>
	<%Vector<String> needsAdj = (Vector)request.getAttribute("needsAdj");%>
	<div class='fifth-column'><h3>Needs Adjudication (<%out.print(needsAdj.size());%>)</h3>
		<%
			name = "";
			for(int i=0; i<needsAdj.size(); i++) {
				name = needsAdj.get(i);
				out.print(name.substring(0,name.length()-4)+"<br>");
			}
		%>
	</div>
	<%Vector<String> needsReview = (Vector)request.getAttribute("needsReview");%>
	<div class='fifth-column'><h3>Needs review (<%out.print(needsReview.size());%>)</h3>
		<%
			name = "";
			for(int i=0; i<needsReview.size(); i++) {
				name = needsReview.get(i);
				out.print(name.substring(0,name.length()-4)+"<br>");
			}
		%>
	</div>
	<%Vector<String> reviewed = (Vector)request.getAttribute("reviewed");%>
	<div class='fifth-column'><h3>Reviewed / Not glaucoma (<%out.print(reviewed.size());%>)</h3>
		<%
			name = "";
			for(int i=0; i<reviewed.size(); i++) {
				name = reviewed.get(i);
				out.print(name.substring(0,name.length()-4)+"<br>");
			}
		%>
	</div>
</div>