<%-- 
    Document   : ipad
    Created on : Dec 29, 2014, 10:33:23 AM
    Author     : aryner
--%>

<%@page import="model.IPad"%>
<%@page import="model.Picture"%>
<%@page import="java.util.*"%>

<%
Picture pic = (Picture)request.getAttribute("picture");
ArrayList<String> missingPics = (ArrayList)request.getAttribute("missingPics");

if(pic == null) { 

%>
<h3>You have finished all uploaded iPad files!</h3>
<%
	int access = (Integer)request.getAttribute("access");

	if(access == 0) {
		int needToPairCount = (Integer)request.getAttribute("needToPairCount");
		if(needToPairCount > 0) {
			out.print(needToPairCount+" still need to be graded by someone else before adjudication");
		} else {
			out.print("iPad is ready for adjudication!");
		}
	}
	if(missingPics != null && missingPics.size() > 0) {
%>
<h3>The following images still need to be uploaded</h3>
<%
		for(int i=0; i<missingPics.size(); i++) {
			out.print(missingPics.get(i)+"<br>");
		}
	}
} else {
	String slash = ""+request.getAttribute("slash");
	String src = "http://localhost:8084/Glaucoma/pdf?type=iPad&name="+pic.getName();
	boolean a = false;
	Vector<IPad> iPad = null;
	if(((Integer)request.getAttribute("access"))==1) {
		a = true;
		iPad = (Vector)request.getAttribute("pair");
	}
	out.print("<h3 class='picName'>"+pic.getType()+" "+pic.getName()+"</h3>");
%>
<iframe src="<%out.print(src);%>" class="HVFimage"></iframe>

<!--Questions-->
<div class="questions">
<form action="assignIPad" method="POST">
	<input type="hidden" name="pictureName" value="<%out.print(pic.getName());%>" autocomplete="off">
	<input type="hidden" name="alreadyConfirmed" value="<%out.print((request.getAttribute("confirmed")!=null)?"true":"false");%>">
<%
	String s1 = "";
	String s2 = "";
	boolean diff = false;
	if(a) {
		s1 = iPad.get(0).getFp()+"";
		s2 = iPad.get(1).getFp()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="fp">False positives</span>
<input type="text" name="fp" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = iPad.get(0).getFn()+"";
		s2 = iPad.get(1).getFn()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="fn">False negatives</span>
<input type="text" name="fn" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = iPad.get(0).getSup_hem()+"";
		s2 = iPad.get(1).getSup_hem()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="sup_hem"># points in superior hemifield</span>
<input type="text" name="sup_hem" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = iPad.get(0).getInf_hem()+"";
		s2 = iPad.get(1).getInf_hem()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="sup_inf"># points in inferior hemifield</span>
<input type="text" name="sup_inf" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<input type="submit" value="Submit" class="btn">

</form>

<%
}
%>

<script src="javascripts/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="javascripts/iPadChecks.js" type="text/javascript"></script>