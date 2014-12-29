<%-- 
    Document   : home
    Created on : Aug 25, 2014, 2:47:12 PM
    Author     : aryner
--%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>

<%
if(session.getAttribute("errors") != null) {
	Vector<String> errors = (Vector)session.getAttribute("errors");
	session.removeAttribute("errors");

	out.print("<div class='error'>");
	out.print("<h4>The following files where not uploaded because they did not have a jpg extension or they are already in the program:</h4>");
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
		<a href="/Glaucoma/OpHVFtest" class="btn menuBtn">HVF test</a>
	</div>

<%
	}
	if(access == 0 || access == 1){
%>
	<div class="thinColumn">
		<a href="/Glaucoma/FDTtest" class="btn menuBtn">FDT test</a>
	</div>
<%
	}
	if (access == 0 || (access == 1)) {
%>
	<div class="thinColumn">
		<a href="/Glaucoma/HVFtest" class="btn menuBtn">HVF test</a> 
	</div>
<%
	}
	if (access == 0|| access == 1) {
%>
	<div class="thinColumn">
		<a href="/Glaucoma/MDTtest" class="btn menuBtn">MDT test</a> 
	</div>
<%
	}
	if (access == 0|| access == 1) {
%>
	<div class="thinColumn">
		<a href="/Glaucoma/OCTtest" class="btn menuBtn">OCT test</a> 
	</div> 
<%
	}
	if (access == 0|| access == 1) {
%>
	<div class="thinColumn">
		<a href="/Glaucoma/stereo" class="btn menuBtn">Stereo</a>
	</div>
<%
	}
	if (access == 0|| access == 1) {
%>
	<div class="thinColumn">
		<a href="/Glaucoma/nethra" class="btn menuBtn">3Nethra</a>
	</div>
<%
	} 
	if (access == 0|| access == 1) {
%>
	<div class="thinColumn">
		<a href="/Glaucoma/ipad" class="btn menuBtn">iPad</a>
	</div>
<%
	} 
%>
</div>
<br>
<div class='container'>
	<div class='column'>
		<a href='/Glaucoma/upload' class='btn menuBtn'>Upload Pictures</a>
	</div>
	<div class="thinColumn">
		<a href="/Glaucoma/printCSV" class="btn menuBtn">Get CSV files</a>
	</div>
	<div class="thinColumn">
		<a href="/Glaucoma/uploadData" class="btn menuBtn">Upload Data</a>
	</div>
</div>

<div class='container'>
	<%Vector<String> ungraded = (Vector)request.getAttribute("ungraded");%>
	<div class='fifth-column'><h3>Ungraded (<%out.print(ungraded.size());%>)</h3>
		<%
			Vector<BaseTest> baseTests = (Vector)request.getAttribute("baseTests");
			Object temp = request.getAttribute("userID");
			int userID = (temp != null ? (Integer)temp : -1);

			String name = "";
			boolean match = false;
			int type = 0;
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
				for(int j=0; j<baseTests.size() && !match; j++) {
					switch(baseTests.get(j).getBaseType()) {
						case BaseTest.FDT :
							if(name.substring(0,name.indexOf(" ")).equals("FDT") &&
							   name.substring(name.indexOf(" - ")+3,name.length()).equals(baseTests.get(j).getPictureName())) 
							{
								match = true;
								out.print("<a href='FDTReview?pictureName="+baseTests.get(j).getPictureName()+"'>");
							}
							break;
						case BaseTest.MDT :
							if(name.substring(0,name.indexOf(" ")).equals("MDT") &&
							   name.substring(name.indexOf(" - ")+3,name.length()).equals(baseTests.get(j).getPictureName())) 
							{
								match = true;
								out.print("<a href='MDTReview?pictureName="+baseTests.get(j).getPictureName()+"'>");
							}
							break;
						case BaseTest.OCT :
							if(name.substring(0,name.indexOf(" ")).equals("OCT") &&
							   name.substring(name.indexOf(" - ")+3,name.length()).equals(baseTests.get(j).getPictureName())) 
							{
								match = true;
								out.print("<a href='OCTReview?pictureName="+baseTests.get(j).getPictureName()+"'>");
							}
							break;
						case BaseTest.STEREO :
							if(name.substring(0,name.indexOf(" ")).equals("Stereo") &&
							   name.substring(name.indexOf(" - ")+3,name.length()).equals(baseTests.get(j).getPictureName())) 
							{
								match = true;
								out.print("<a href='stereoReview?pictureName="+baseTests.get(j).getPictureName()+"'>");
							}
							break;
						case BaseTest.NETHRA :
							if(name.substring(0,name.indexOf(" ")).equals("3Nethra") &&
							   name.substring(name.indexOf(" - ")+3,name.length()).equals(baseTests.get(j).getPictureName())) 
							{
								match = true;
								out.print("<a href='nethraReview?pictureName="+baseTests.get(j).getPictureName()+"'>");
							}
							break;
						case BaseTest.IPAD :
							if(name.substring(0,name.indexOf(" ")).equals("iPad") &&
							   name.substring(name.indexOf(" - ")+3, name.length()).equals(baseTests.get(j).getPictureName()))
							{
								match = true;
								out.print("<a href='ipadReview?pictureName="+baseTests.get(j).getPictureName()+"'>");
							}
							break;
					}
				}
				out.print(name.substring(0,name.length()-4)+"<br>");
				if(match) {
					out.print("</a>");
				}
				match = false;
			}
		%>
	</div>
	<%Vector<String> needsAdj = (Vector)request.getAttribute("needsAdj");%>
	<div class='fifth-column'><h3>Needs Adjudication (<%out.print(needsAdj.size());%>)</h3>
		<%
			name = "";
			match = false;
			for(int i=0; i<needsAdj.size(); i++) {
				name = needsAdj.get(i);
				for(int j=0; j<baseTests.size() && !match; j++) {
					switch(baseTests.get(j).getBaseType()) {
						case BaseTest.FDT :
							if(name.substring(0,name.indexOf(" ")).equals("FDT") &&
							   name.substring(name.indexOf(" - ")+3,name.length()).equals(baseTests.get(j).getPictureName())) 
							{
								match = true;
								out.print("<a href='FDTReview?pictureName="+baseTests.get(j).getPictureName()+"'>");
							}
							break;
						case BaseTest.MDT :
							if(name.substring(0,name.indexOf(" ")).equals("MDT") &&
							   name.substring(name.indexOf(" - ")+3,name.length()).equals(baseTests.get(j).getPictureName())) 
							{
								match = true;
								out.print("<a href='MDTReview?pictureName="+baseTests.get(j).getPictureName()+"'>");
							}
							break;
						case BaseTest.OCT :
							if(name.substring(0,name.indexOf(" ")).equals("OCT") &&
							   name.substring(name.indexOf(" - ")+3,name.length()).equals(baseTests.get(j).getPictureName())) 
							{
								match = true;
								out.print("<a href='OCTReview?pictureName="+baseTests.get(j).getPictureName()+"'>");
							}
							break;
						case BaseTest.STEREO :
							if(name.substring(0,name.indexOf(" ")).equals("Stereo") &&
							   name.substring(name.indexOf(" - ")+3,name.length()).equals(baseTests.get(j).getPictureName())) 
							{
								match = true;
								out.print("<a href='stereoReview?pictureName="+baseTests.get(j).getPictureName()+"'>");
							}
							break;
						case BaseTest.NETHRA :
							if(name.substring(0,name.indexOf(" ")).equals("3Nethra") &&
							   name.substring(name.indexOf(" - ")+3,name.length()).equals(baseTests.get(j).getPictureName())) 
							{
								match = true;
								out.print("<a href='nethraReview?pictureName="+baseTests.get(j).getPictureName()+"'>");
							}
							break;
						case BaseTest.IPAD:
							if(name.substring(0,name.indexOf(" ")).equals("iPad") &&
							   name.substring(name.indexOf(" - ")+3,name.length()).equals(baseTests.get(j).getPictureName())) 
							{
								match = true;
								out.print("<a href='ipadReview?pictureName="+baseTests.get(j).getPictureName()+"'>");
							}
							break;
					}
				}
				out.print(name.substring(0,name.length()-4)+"<br>");
				if(match) {
					out.print("</a>");
				}
				match = false;
			}
		%>
	</div>
	<%Vector<String> needsReview = (Vector)request.getAttribute("needsReview");%>
	<div class='fifth-column'><h3>Needs review (<%out.print(needsReview.size());%>)</h3>
		<%
			Vector<HVFtest> adjudicatedBy = (Vector)request.getAttribute("adjudicatedBy");
			name = "";
			for(int i=0; i<needsReview.size(); i++) {
				name = needsReview.get(i);
				boolean link = false;
				if(adjudicatedBy != null) {
					for(int j=0; j<adjudicatedBy.size(); j++) {
						if(adjudicatedBy.get(j).getPictureName().equals(name)) {
							out.print("<a class='opthReviewed' href='HVFtest?pictureName="+name+"'>");
							link = true;
						}
					}
				}
				out.print(name.substring(0,name.length()-4)+"<br>");
				if(link) {
					out.print("</a>");
				}
			}
		%>
	</div>
	<%Vector<String> reviewed = (Vector)request.getAttribute("reviewed");%>
	<%Vector<String> adjudicated = (Vector)request.getAttribute("adjudicated");%>
	<div class='fifth-column'><h3>Reviewed / Adjudicated (<%out.print(adjudicated.size()+reviewed.size());%>)</h3>
		<%
			Vector<HVFtest> reviewedBy = (Vector)request.getAttribute("reviewedBy");
			name = "";
			for(int i=0; i<reviewed.size(); i++) {
				name = reviewed.get(i);
				boolean link = false;
				if(reviewedBy != null) {
					for(int j=0; j<reviewedBy.size(); j++) {
						if(("HVF - "+reviewedBy.get(j).getPictureName()).equals(name)) {
							out.print("<a class='opthReviewed' href='OpHVFtest?pictureName="+reviewedBy.get(j).getPictureName()+"'>");
							link = true;
						}
					}
				}
				if(!link && adjudicatedBy != null) {
					for(int j=0; j<adjudicatedBy.size(); j++) {
						if(("HVF - "+adjudicatedBy.get(j).getPictureName()).equals(name)) {
							out.print("<a class='opthReviewed' href='HVFtest?pictureName="+reviewedBy.get(j).getPictureName()+"'>");
							link = true;
						}
					}
				}
				out.print(name.substring(0,name.length()-4)+"<br>");
				if(link) {
					out.print("</a>");
				}
			}
			name = "";
			match = false;
			for(int i=0; i<adjudicated.size(); i++) {
				name = adjudicated.get(i);
				if(access == 1) {
					for(int j=0; j<baseTests.size() && !match; j++) {
						switch(baseTests.get(j).getBaseType()) {
							case BaseTest.FDT :
								if(name.substring(0,name.indexOf(" ")).equals("FDT") &&
								   name.substring(name.indexOf(" - ")+3,name.length()).equals(baseTests.get(j).getPictureName())) 
								{
									match = true;
									out.print("<a href='FDTReview?pictureName="+baseTests.get(j).getPictureName()+"'>");
								}
								break;
							case BaseTest.MDT :
								if(name.substring(0,name.indexOf(" ")).equals("MDT") &&
								   name.substring(name.indexOf(" - ")+3,name.length()).equals(baseTests.get(j).getPictureName())) 
								{
									match = true;
									out.print("<a href='MDTReview?pictureName="+baseTests.get(j).getPictureName()+"'>");
								}
								break;
							case BaseTest.OCT :
								if(name.substring(0,name.indexOf(" ")).equals("OCT") &&
								   name.substring(name.indexOf(" - ")+3,name.length()).equals(baseTests.get(j).getPictureName())) 
								{
									match = true;
									out.print("<a href='OCTReview?pictureName="+baseTests.get(j).getPictureName()+"'>");
								}
								break;
							case BaseTest.STEREO :
								if(name.substring(0,name.indexOf(" ")).equals("Stereo") &&
								   name.substring(name.indexOf(" - ")+3,name.length()).equals(baseTests.get(j).getPictureName())) 
								{
									match = true;
									out.print("<a href='stereoReview?pictureName="+baseTests.get(j).getPictureName()+"'>");
								}
								break;
							case BaseTest.NETHRA :
								if(name.substring(0,name.indexOf(" ")).equals("3Nethra") &&
								   name.substring(name.indexOf(" - ")+3,name.length()).equals(baseTests.get(j).getPictureName())) 
								{
									match = true;
									out.print("<a href='nethraReview?pictureName="+baseTests.get(j).getPictureName()+"'>");
								}
								break;
							case BaseTest.IPAD:
								if(name.substring(0,name.indexOf(" ")).equals("iPad") &&
								   name.substring(name.indexOf(" - ")+3,name.length()).equals(baseTests.get(j).getPictureName())) 
								{
									match = true;
									out.print("<a href='ipadReview?pictureName="+baseTests.get(j).getPictureName()+"'>");
								}
								break;
						}
					}
				}
				out.print(name.substring(0,name.length()-4)+"<br>");
				if(match) {
					out.print("</a>");
				}
				match = false;
			}
		%>
	</div>
	<!--
	<div class='fifth-column'><h3>Reviewed / Not glaucoma (<%out.print(reviewed.size());%>)</h3>
		<%
//			Vector<HVFtest> reviewedBy = (Vector)request.getAttribute("reviewedBy");
			name = "";
			for(int i=0; i<reviewed.size(); i++) {
				name = reviewed.get(i);
				boolean link = false;
				if(reviewedBy != null) {
					for(int j=0; j<reviewedBy.size(); j++) {
						if(reviewedBy.get(j).getPictureName().equals(name)) {
							out.print("<a class='opthReviewed' href='OpHVFtest?pictureName="+name+"'>");
							link = true;
						}
					}
				}
				if(!link && adjudicatedBy != null) {
					for(int j=0; j<adjudicatedBy.size(); j++) {
						if(adjudicatedBy.get(j).getPictureName().equals(name)) {
							out.print("<a class='opthReviewed' href='HVFtest?pictureName="+name+"'>");
							link = true;
						}
					}
				}
				out.print(name.substring(0,name.length()-4)+"<br>");
				if(link) {
					out.print("</a>");
				}
			}
		%>
	</div>
 -->
</div>