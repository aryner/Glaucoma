<%-- 
    Document   : stereoReview
    Created on : Dec 22, 2014, 5:10:10 PM
    Author     : aryner
--%>

<%@page import="model.Photos"%>
<%@page import="model.Picture"%>
<%@page import="java.util.*"%>

<%
Picture pic = (Picture)request.getAttribute("picture");
ArrayList<String> missingPics = (ArrayList)request.getAttribute("missingPics");

if(pic == null) { 

%>
<h3>You have finished all uploaded Stereo files!</h3>
<%
	int access = (Integer)request.getAttribute("access");

	if(access == 0) {
		int needToPairCount = (Integer)request.getAttribute("needToPairCount");
		if(needToPairCount > 0) {
			out.print(needToPairCount+" still need to be graded by someone else before adjudication");
		} else {
			out.print("Stereo is ready for adjudication!");
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
	String src = "http://localhost:8084/Glaucoma/pdf?type=stereo&name="+pic.getName();
	boolean a = true;
	Photos stereo = (Photos)request.getAttribute("stereo");
	out.print("<h3 class='picName'>"+pic.getType()+" "+pic.getName()+"</h3>");
%>
<iframe src="<%out.print(src);%>" class="stereoImage"></iframe>

<!--Questions-->
<div class="stereoQuestions">
<form action="updateStereo" method="POST">
	<input type="hidden" name="pictureName" value="<%out.print(pic.getName());%>" autocomplete="off">
	<input type="hidden" name="alreadyConfirmed" value="<%out.print((request.getAttribute("confirmed")!=null)?"true":"false");%>">
	<input type="hidden" name="type" value="<%out.print(Photos.STEREO);%>">

<%
	int v1 = -1;
	int v2 = -1;
	boolean diff = false;
	if(a) {
		v1 = stereo.getQual();
		v2 = stereo.getQual();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
		if(diff) {

		}
	}
%>
<span id='qual'>Photo Quality</span><br>
<input type="radio" name="qual" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>good<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="qual" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>acceptable<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="qual" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>poor<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<br>

<%
	String s1 = "";
	String s2 = "";
	if(a) {
		s1 = stereo.getCdr()+"";
		s2 = stereo.getCdr()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="cdr">Vertical CDR</span>
<input type="text" name="cdr" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = stereo.getNotch();
		v2 = stereo.getNotch();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
		if(diff) {

		}
	}
%>
<span id='notch'>Notch </span><br>
<input type="radio" name="notch" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>Yes <%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%>
<input type="radio" name="notch" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>No <%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%>
<br>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = stereo.getNotch_hrs_one()+"";
		s2 = stereo.getNotch_hrs_one()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="notch_hrs_one">Notch clock hours 1</span>
<input type="text" name="notch_hrs_one" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = stereo.getNotch_hrs_two()+"";
		s2 = stereo.getNotch_hrs_two()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="notch_hrs_two">Notch clock hours 2</span>
<input type="text" name="notch_hrs_two" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = stereo.getErosion();
		v2 = stereo.getErosion();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
		if(diff) {

		}
	}
%>
<span id='erosion'>Erosion </span><br>
<input type="radio" name="erosion" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>Yes <%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%>
<input type="radio" name="erosion" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>No <%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%>
<br>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = stereo.getEros_hrs_one()+"";
		s2 = stereo.getEros_hrs_one()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="eros_hrs_one">Erosion clock hours 1</span>
<input type="text" name="eros_hrs_one" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = stereo.getEros_hrs_two()+"";
		s2 = stereo.getEros_hrs_two()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="eros_hrs_two">Erosion clock hours 2</span>
<input type="text" name="eros_hrs_two" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = stereo.getDisc();
		v2 = stereo.getDisc();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
		if(diff) {

		}
	}
%>
<span id='disc'>Disc heme</span><br>
<input type="radio" name="disc" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>Yes <%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%>
<input type="radio" name="disc" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>No <%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%>
<br>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = stereo.getDisc_hrs_one()+"";
		s2 = stereo.getDisc_hrs_one()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="disc_hrs_one">Disc heme clock hours 1</span>
<input type="text" name="disc_hrs_one" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = stereo.getDisc_hrs_two()+"";
		s2 = stereo.getDisc_hrs_two()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="disc_hrs_two">Disc heme clock hours 2</span>
<input type="text" name="disc_hrs_two" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = stereo.getRnfl();
		v2 = stereo.getRnfl();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
		if(diff) {

		}
	}
%>
<span id='rnfl'>RNFL defect</span><br>
<input type="radio" name="rnfl" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>Yes <%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%>
<input type="radio" name="rnfl" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>No <%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%>
<br>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = stereo.getRnfl_hrs_one()+"";
		s2 = stereo.getRnfl_hrs_one()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="rnfl_hrs_one">RNFL defect clock hours 1</span>
<input type="text" name="rnfl_hrs_one" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = stereo.getRnfl_hrs_two()+"";
		s2 = stereo.getRnfl_hrs_two()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="rnfl_hrs_two">RNFL defect clock hours 2</span>
<input type="text" name="rnfl_hrs_two" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = stereo.getGlau();
		v2 = stereo.getGlau();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
		if(diff) {

		}
	}
%>
<span id='glau'>Glaucomatous optic nerve</span><br>
<input type="radio" name="glau" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>Definite glaucomatous nerve<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="glau" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Probable glaucomatous nerve<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="glau" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Possible glaucomatous nerve<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="glau" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>Not a glaucomatous nerve<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="glau" value="5"<%if(a && !diff && v1==5){out.print(" checked='true'");}%>>Indeterminate<%if((a&&diff)&&((v1==5)||(v2==5))){%><span class='highlight'>***</span><%}%><br>
<br>

<input type="submit" value="Submit" class="btn">

</form>

<%
}
%>

<script src="javascripts/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="javascripts/photosChecks.js" type="text/javascript"></script>