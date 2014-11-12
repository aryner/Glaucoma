<%-- 
    Document   : OpHVFtest
    Created on : Oct 1, 2014, 6:52:51 AM
    Author     : alexanderryner
--%>

<%@page import="model.HVFtest"%>
<%@page import="model.Picture"%>
<%@page import="java.util.*"%>

<%
Picture pic = (Picture)request.getAttribute("picture");
ArrayList missingPics = (ArrayList)request.getAttribute("missingPics");

if(pic == null) { 

%>
<h3>You have finished all uploaded HVF files!</h3>
<%
	if(missingPics != null && missingPics.size() > 0) {
%>
<h3>The following HVF PDFs still need to be uploaded</h3>
<%
		for(int i=0; i<missingPics.size(); i++) {
			out.print(missingPics.get(i)+"<br>");
		}
	}

} else {
	HVFtest hvf = (HVFtest)request.getAttribute("hvf");
	boolean reviewed = (hvf.getOpthName() != null && hvf.getOpthName().length() > 0 && !hvf.getOpthName().equals("null")) ? true : false;
	String slash = ""+request.getAttribute("slash");
	String src = "http://localhost:8084/HVF/pdf?type=HVF&name="+pic.getName();
	out.print("<h3 class='picName'>"+pic.getType()+" "+pic.getName()+"</h3>");
%>
<!--pdf of HVFfile -->
<iframe src="<%out.print(src);%>" class="HVFimage"></iframe>

<!--Questions-->
<div class="questions">
<form action="OpReviewHVF" method="POST">
<input type="hidden" name="pictureName" value="<%out.print(pic.getName());%>">
<input type="hidden" name="reviewedAgain" value="<%out.print((reviewed)?"true":"false");%>">

GHT: <br>
<input type="radio" name="ght" value="1"<%if(hvf.getGht()==1) {out.print(" checked");}%>>Within Normal Limits<br>
<input type="radio" name="ght" value="2"<%if(hvf.getGht()==2) {out.print(" checked");}%>>Borderline / General Reduction<br>
<input type="radio" name="ght" value="3"<%if(hvf.getGht()==3) {out.print(" checked");}%>><span style='color:darkorange;'>Outside Normal Limits</span><br>
<input type="radio" name="ght" value="4"<%if(hvf.getGht()==4) {out.print(" checked");}%>>General Reduction of Sensitivity<br>
<input type="radio" name="ght" value="5"<%if(hvf.getGht()==5) {out.print(" checked");}%>>Abnormally High Sensitivity<br>
<input type="radio" name="ght" value="999"<%if(hvf.getGht()==999) {out.print(" checked");}%>>Blank<br><br>

PSD - P-value: <br>
<input type="radio" name="psdp" value="1"<%if(hvf.getPsdp()==1) {out.print(" checked");}%>><span style='color:darkorange'><0.5%<br>
<input type="radio" name="psdp" value="2"<%if(hvf.getPsdp()==2) {out.print(" checked");}%>><1%<br>
<input type="radio" name="psdp" value="3"<%if(hvf.getPsdp()==3) {out.print(" checked");}%>><2%<br>
<input type="radio" name="psdp" value="4"<%if(hvf.getPsdp()==4) {out.print(" checked");}%>><5%</span><br>
<input type="radio" name="psdp" value="5"<%if(hvf.getPsdp()==5) {out.print(" checked");}%>><10%<br>
<input type="radio" name="psdp" value="999"<%if(hvf.getPsdp()==999) {out.print(" checked");}%>>Blank<br><br>

Cluster of >= 3 non-edge points that are all significant<br> at p < 5% with >= 1 point
being significant at p < 1%<br>
<input type="radio" name="cluster" value="1"<%if(hvf.getCluster()==1) {out.print(" checked");}%>><span style='color:darkorange;'>Yes</span>
<input type="radio" name="cluster" value="2"<%if(hvf.getCluster()==2) {out.print(" checked");}%>>No 
<input type="radio" name="cluster" value="999"<%if(hvf.getCluster()==999) {out.print(" checked");}%>>Blank <br><br> 

<div class="boxed" style="width:40%;">
<span class="bold">Glaucoma Present</span> <b>
	<input type="radio" name="glau" value="1"<%if(hvf.getHvf_glau()==1) {out.print(" checked");}%>><span style='color:darkorange;'>Yes </span>
<input type="radio" name="glau" value="2"<%if(hvf.getHvf_glau()==2) {out.print(" checked");}%>>No </b>
<span id="glauMatch1" class="invis error">Glaucoma present, glaucoma severity, and VF loss answers must be consistent</span>
</div>
<br><br>

<%
	String severity = "";
	if(hvf.getMdsign() == 2 || Float.parseFloat(hvf.getMddb()) < 0.001) {
		severity = "class='noGlau borderTop' style='border:solid;border-bottom:none' ";
	}
	else if(Float.parseFloat(hvf.getMddb()) <= 6.) {
		severity = "class='earlyGlau borderTop' style='border:solid;border-bottom:none' ";
	}
	else if(Float.parseFloat(hvf.getMddb()) <= 12.) {
		severity = "class='modGlau borderTop' style='border:solid;border-bottom:none'";
	}
	else if(Float.parseFloat(hvf.getMddb()) <= 20.) {
		severity = "class='advGlau borderTop' style='border:solid;border-bottom:none'";
	}
	else {
		severity = "class='severeGlau borderTop' style='border:solid;border-bottom:none'";
	}
%>
<div id="MD" <%out.print(severity);%>>
MD - Sign <input type="radio" name="mdsign" value="2"<%if(hvf.getMdsign()==2) {out.print(" checked");}%>>+ 
<input type="radio" name="mdsign" value="1"<%if(hvf.getMdsign()==1) {out.print(" checked");}%>>-<br>
MD - dB <input type="text" name="mddb" <%out.print("value='"+hvf.getMddb()+"'");%>><br>
</div>
<%
	severity = "";
	if(hvf.getCentral_0() >= 2) {
		severity = "class='severeGlau' style='border:solid;border-bottom:none' ";
	}
	else if(hvf.getCentral_0() == 1) {
		severity = "class='advGlau' style='border:solid;border-bottom:none' ";
	}
	else if(hvf.getCentral_15() >= 1) {
		severity = "class='modGlau' style='border:solid;border-bottom:none'";
	}
	else {
		severity = "class='noGlau' style='border:solid;border-bottom:none'";
	}

%>
<div id="central" <%out.print(severity);%>>
# of points within central 5 degrees with sensitivity < 15 dB (top left graph) <br>
<input type="radio" name="central_15" value="0"<%if(hvf.getCentral_15()==0) {out.print(" checked");}%>> 0 
<input type="radio" name="central_15" value="1"<%if(hvf.getCentral_15()==1) {out.print(" checked");}%>> 1 
<input type="radio" name="central_15" value="2"<%if(hvf.getCentral_15()==2) {out.print(" checked");}%>> 2 
<input type="radio" name="central_15" value="3"<%if(hvf.getCentral_15()==3) {out.print(" checked");}%>> 3 
<input type="radio" name="central_15" value="4"<%if(hvf.getCentral_15()==4) {out.print(" checked");}%>> 4 <br>
# of points within central 5 degrees with sensitivity < 0 dB (top left graph) <br>
<input type="radio" name="central_0" value="0"<%if(hvf.getCentral_0()==0) {out.print(" checked");}%>> 0 
<input type="radio" name="central_0" value="1"<%if(hvf.getCentral_0()==1) {out.print(" checked");}%>> 1 
<input type="radio" name="central_0" value="2"<%if(hvf.getCentral_0()==2) {out.print(" checked");}%>> 2 
<input type="radio" name="central_0" value="3"<%if(hvf.getCentral_0()==3) {out.print(" checked");}%>> 3 
<input type="radio" name="central_0" value="4"<%if(hvf.getCentral_0()==4) {out.print(" checked");}%>> 4 
</div>

<%
	severity = "";
	if(hvf.getPts_five() >= 56 || hvf.getPts_one() >= 37) {
		severity = "class='severeGlau' style='border:solid;border-bottom:none' ";
	}
	else if(hvf.getPts_five() >= 37 && hvf.getPts_one() >= 19) {
		severity = "class='advGlau' style='border:solid;border-bottom:none' ";
	}
	else if(hvf.getPts_five() >= 19 && hvf.getPts_one() >= 12) {
		severity = "class='modGlau' style='border:solid;border-bottom:none'";
	}
	else {
		severity = "class='noGlau' style='border:solid;border-bottom:none'";
	}

%>
<div id="pts" <%out.print(severity);%>>
# points depressed < 5% level on Pattern Deviation Plot <input type="text" name="pts_five" <%out.print("value='"+hvf.getPts_five()+"'");%>><br>
# points depressed < 1% level on Pattern Deviation Plot <input type="text" name="pts_one" <%out.print("value='"+hvf.getPts_one()+"'");%>><br>
</div>
<!--
# contiguous points < 5% level on Pattern Deviation Plot <input type="text" name="pts_contig" <%out.print("value='"+hvf.getPts_contig()+"'");%>><br><br>
-->

<%
	severity = "";
	int sup = hvf.getSup_hem();
	int inf = hvf.getInf_hem();
	if(sup >= 2 && inf >= 2) {
		severity = "class='severeGlau' style='border:solid;' ";
	}
	else if(sup >= 1 && inf >= 1) {
		severity = "class='advGlau' style='border:solid;' ";
	}
	else if((sup + inf) >= 1) {
		severity = "class='modGlau' style='border:solid;'";
	}
	else {
		severity = "class='noGlau' style='border:solid;'";
	}

%>
<div id="hem" <%out.print(severity);%>>
Superior hemifield: # points <15dB within central 5 degrees of fixation? (top left graph)<br> 
<input type="radio" name="sup_hem" value="0"<%if(hvf.getSup_hem()==0) {out.print(" checked");}%>> 0 
<input type="radio" name="sup_hem" value="1"<%if(hvf.getSup_hem()==1) {out.print(" checked");}%>> 1 
<input type="radio" name="sup_hem" value="2"<%if(hvf.getSup_hem()==2) {out.print(" checked");}%>> 2 <br>
Inferior hemifield: # points <15dB within central 5 degrees of fixation? (top left graph)<br> 
<input type="radio" name="inf_hem" value="0"<%if(hvf.getInf_hem()==0) {out.print(" checked");}%>> 0 
<input type="radio" name="inf_hem" value="1"<%if(hvf.getInf_hem()==1) {out.print(" checked");}%>> 1 
<input type="radio" name="inf_hem" value="2"<%if(hvf.getInf_hem()==2) {out.print(" checked");}%>> 2 <br>
</div><br><br>

<!--
Superiour hemifield: # points with sensitivity >=15dB within 5 degrees of fixation?<br> <input type="text" name="sup_hem2" <%out.print("value='"+hvf.getSup_hem2()+"'");%>><br>
Inferior hemfield: # points with sensitivity >=15dB within 5 degrees of fixation?<br> <input type="text" name="inf_hem2" <%out.print("value='"+hvf.getInf_hem2()+"'");%>><br><br>
-->
<br>
<%
	String chart = (String)request.getAttribute("chart");
	if(chart == null) {
		chart = "";
	}
%>
<input type="hidden" class="severityChart" name="<%out.print(chart);%>">
<input type='button' id="chart" value="See Severity Chart" class="btn" id="chart">
<br><br>
<span class="bold">Categorization of glaucoma severity:</span> <span id="glauMatch2" class="invis error">Glaucoma present, glaucoma severity, and VF loss answers must be consistent</span><br><b>
<div class="severityAnswers noGlau"><input type="radio" name="severe" value="0"<%if(hvf.getSevere()==0) {out.print(" checked");}%>>No glaucoma / minimal defect</div>
<div class="severityAnswers earlyGlau"><input type="radio" name="severe" value="1"<%if(hvf.getSevere()==1) {out.print(" checked");}%>>Early</div>
<div class="severityAnswers modGlau"><input type="radio" name="severe" value="2"<%if(hvf.getSevere()==2) {out.print(" checked");}%>>Moderate</div>
<div class="severityAnswers advGlau"><input type="radio" name="severe" value="3"<%if(hvf.getSevere()==3) {out.print(" checked");}%>>Advanced</div>
<div class="severityAnswers severeGlau"><input type="radio" name="severe" value="4"<%if(hvf.getSevere()==4) {out.print(" checked");}%>>Severe</div>
<div class="severityAnswers endGlau"><input type="radio" name="severe" value="5"<%if(hvf.getSevere()==5) {out.print(" checked");}%>>End stage glaucoma</div></b><br>


False POS Errors (%) <input type="text" name="fp" class="numBox" <%out.print("value='"+hvf.getFp()+"'");%>><br>
False NEG Errors (%) <input type="text" name="fn" class="numBox" <%out.print("value='"+hvf.getFp()+"'");%>><br><br>

<span id='reliable_review'>Test Reliability:</span><br>
<input type="radio" name="reliable_review" value="1"<%if(reviewed && hvf.getReliable_review()==1){out.print(" checked");}%>>Reliable<br>
<input type="radio" name="reliable_review" value="2"<%if(reviewed && hvf.getReliable_review()==2){out.print(" checked");}%>>Unreliable<br><br>

<span id='vf_loss'>Pattern of glaucoma VF loss:</span> <span id="glauMatch3" class="invis error">Glaucoma present, glaucoma severity, and VF loss answers must be consistent</span><br>
<input type="radio" name="vf_loss" value="1"<%if(reviewed && hvf.getVf_loss().equals("1")){out.print(" checked");}%>>glaucoma<br>
<input type="radio" name="vf_loss" value="2"<%if(reviewed && hvf.getVf_loss().equals("2")){out.print(" checked");}%>>neuro<br>
<input type="radio" name="vf_loss" value="3"<%if(reviewed && hvf.getVf_loss().equals("3")){out.print(" checked");}%>>nonspecific change<br>
<input type="radio" name="vf_loss" value="4"<%if(reviewed && hvf.getVf_loss().equals("4")){out.print(" checked");}%>>could not determine (unreliable field)<br>
<input type="radio" name="vf_loss" value="5"<%if(reviewed && hvf.getVf_loss().equals("5")){out.print(" checked");}%>><span id='vf_loss_oth'>other:</span>
<input type="text" name="vf_loss_oth">
<br><br>
<%
String result = "";
String resultString = "00000000";
if(reviewed) {
	resultString = hvf.getVf_defect();
}
%>
<div id="vf_defectInvis">
<span id='vf_defect'>Type of glaucoma VF defect:</span><br>
<% result = resultString.substring(0,1); %>
<input type="checkbox" name="vf_defect1" value="1"<%if(result.equals("2")) { out.print(" checked");}%>>superior arcuate scotoma<br>
<% result = resultString.substring(1,2); %>
<input type="checkbox" name="vf_defect2" value="2"<%if(result.equals("2")) { out.print(" checked");}%>>inferior arcuate scotoma<br>
<% result = resultString.substring(2,3); %>
<input type="checkbox" name="vf_defect3" value="3"<%if(result.equals("2")) { out.print(" checked");}%>>superior nasal step<br>
<% result = resultString.substring(3,4); %>
<input type="checkbox" name="vf_defect4" value="4"<%if(result.equals("2")) { out.print(" checked");}%>>inferior nasal step<br>
<% result = resultString.substring(4,5); %>
<input type="checkbox" name="vf_defect5" value="5"<%if(result.equals("2")) { out.print(" checked");}%>>temporal wedge<br>
<% result = resultString.substring(5,6); %>
<input type="checkbox" name="vf_defect6" value="6"<%if(result.equals("2")) { out.print(" checked");}%>>tunnel vision<br>
<% result = resultString.substring(6,7); %>
<input type="checkbox" name="vf_defect7" value="7"<%if(result.equals("2")) { out.print(" checked");}%>>end stage<br>
<% result = resultString.substring(7,8); %>
<input type="checkbox" name="vf_defect8" value="8"<%if(result.equals("2")) { out.print(" checked");}%>><span id='vf_defect_oth'>other:</span>
<input type="text" name="vf_defect_oth" <%if(reviewed) {out.print("value='"+hvf.getVf_defect_oth()+"'");}%>>
<br><br>
</div>

Notes: <br>
<input type="radio" name="notes" value="1"<%if(hvf.getNotes()==1) {out.print(" checked");}%>>Low test reliability<br>
<input type="radio" name="notes" value="2"<%if(hvf.getNotes()==2) {out.print(" checked");}%>>Excessive High False Positives<br>
<input type="radio" name="notes" value="3"<%if(hvf.getNotes()==3) {out.print(" checked");}%>>Excessive High False Negatives<br>
<input type="radio" name="notes" value="4"<%if(hvf.getNotes()==4) {out.print(" checked");}%>><span id="notes_other">Other:</span>
<input type="text" name="notes_other" value="<%out.print(hvf.getNotes_other());%>"><br>
<input type="radio" name="notes" value="999"<%if(hvf.getNotes()==999) {out.print(" checked");}%>>Blank<br><br>


<input type="submit" value="Submit" class="btn"><br><br>
</form>
</div>
<%
}
%>

<script src="javascripts/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="javascripts/HVFOpthChecks.js" type="text/javascript"></script>
<script src="javascripts/HVFseverityChart.js" type="text/javascript"></script>