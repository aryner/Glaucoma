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

if(pic == null) { 

%>
<h3>You have finished all uploaded HVF files!</h3>
<%

} else {
	HVFtest hvf = (HVFtest)request.getAttribute("hvf");
	String slash = ""+request.getAttribute("slash");
	String src = "http://localhost:8080/Glaucoma/pdf?type=HVF&name="+pic.getName();
%>
<!--pdf of HVFfile -->
<embed src="<%out.print(src);%>" class="HVFimage">

<!--Questions-->
<div class="questions">
<form action="OpReviewHVF" method="POST">
<input type="hidden" name="pictureID" value="<%out.print(pic.getId());%>">

False POS Errors (%) <input type="text" name="fp" class="numBox" <%out.print("value='"+hvf.getFp()+"'");%>><br>
False NEG Errors (%) <input type="text" name="fn" class="numBox" <%out.print("value='"+hvf.getFp()+"'");%>><br><br>

GHT: <br>
<input type="radio" name="ght" value="1"<%if(hvf.getGht()==1) {out.print(" checked");}%>>Within Normal Limits<br>
<input type="radio" name="ght" value="2"<%if(hvf.getGht()==2) {out.print(" checked");}%>>Borderline<br>
<input type="radio" name="ght" value="3"<%if(hvf.getGht()==3) {out.print(" checked");}%>>Outside Normal Limits<br>
<input type="radio" name="ght" value="4"<%if(hvf.getGht()==4) {out.print(" checked");}%>>General Reduction of Sensitivity<br>
<input type="radio" name="ght" value="5"<%if(hvf.getGht()==5) {out.print(" checked");}%>>Abnormally High Sensitivity<br><br>

PSD - P-value: <br>
<input type="radio" name="psdp" value="1"<%if(hvf.getPsdp()==1) {out.print(" checked");}%>><0.5%<br>
<input type="radio" name="psdp" value="2"<%if(hvf.getPsdp()==2) {out.print(" checked");}%>><1%<br>
<input type="radio" name="psdp" value="3"<%if(hvf.getPsdp()==3) {out.print(" checked");}%>><2%<br>
<input type="radio" name="psdp" value="4"<%if(hvf.getPsdp()==4) {out.print(" checked");}%>><5%<br>
<input type="radio" name="psdp" value="5"<%if(hvf.getPsdp()==5) {out.print(" checked");}%>><10%<br>
<input type="radio" name="psdp" value="6"<%if(hvf.getPsdp()==6) {out.print(" checked");}%>>Blank<br><br>

Cluster of >= 3 non-edge points that are all significant<br> at p < 5% with >= 1 point being significant at p < 5% <br>with >= 1 point
being significant at p < 1%<br>
<input type="radio" name="cluster" value="1"<%if(hvf.getCluster()==1) {out.print(" checked");}%>>Yes 
<input type="radio" name="cluster" value="2"<%if(hvf.getCluster()==2) {out.print(" checked");}%>>No <br><br> 

Glaucoma Determination  
<input type="radio" name="glau" value="1"<%if(hvf.getHvf_glau()==1) {out.print(" checked");}%>>Yes 
<input type="radio" name="glau" value="2"<%if(hvf.getHvf_glau()==2) {out.print(" checked");}%>>No <br><br>

<%
	String severity = "";
	if(hvf.getMdsign() == 2 || Float.parseFloat(hvf.getMddb()) < 0.001) {
		severity = " style='background:lightblue;' ";
	}
	else if(Float.parseFloat(hvf.getMddb()) <= 6.) {
		severity = " style='background:lightgreen;' ";
	}
	else if(Float.parseFloat(hvf.getMddb()) <= 12.) {
		severity = " style='background:yellow;'";
	}
	else if(Float.parseFloat(hvf.getMddb()) <= 20.) {
		severity = " style='background:orange;'";
	}
	else {
		severity = " style='background:red;'";
	}
%>
<div <%out.print(severity);%>>
MD - Sign <input type="radio" name="mdsign" value="2"<%if(hvf.getMdsign()==2) {out.print(" checked");}%>>+ 
<input type="radio" name="mdsign" value="1"<%if(hvf.getMdsign()==1) {out.print(" checked");}%>>-<br>
MD - dB <input type="text" name="mddb" <%out.print("value='"+hvf.getMddb()+"'");%>><br>
</div>
MD - P-value: <br>
<input type="radio" name="mdp" value="1"<%if(hvf.getMdp()==1) {out.print(" checked");}%>><0.5%<br>
<input type="radio" name="mdp" value="2"<%if(hvf.getMdp()==2) {out.print(" checked");}%>><1%<br>
<input type="radio" name="mdp" value="3"<%if(hvf.getMdp()==3) {out.print(" checked");}%>><2%<br>
<input type="radio" name="mdp" value="4"<%if(hvf.getMdp()==4) {out.print(" checked");}%>><5%<br>
<input type="radio" name="mdp" value="5"<%if(hvf.getMdp()==5) {out.print(" checked");}%>><10%<br>
<input type="radio" name="mdp" value="6"<%if(hvf.getMdp()==6) {out.print(" checked");}%>>Blank<br><br>

<%
	severity = "";
	if(hvf.getCentral_0() >= 2) {
		severity = " style='background:red;' ";
	}
	else if(hvf.getCentral_0() == 1) {
		severity = " style='background:orange;' ";
	}
	else if(hvf.getCentral_15() >= 1) {
		severity = " style='background:yellow;'";
	}
	else {
		severity = " style='background:green;'";
	}

%>
<div <%out.print(severity);%>>
# of points within central 5 degrees with sensitivity < 15 dB <input type="text" name="central_15" <%out.print("value='"+hvf.getCentral_15()+"'");%>><br>
# of points within central 5 degrees with sensitivity < 0 dB <input type="text" name="central_0" <%out.print("value='"+hvf.getCentral_0()+"'");%>><br>
</div>

<%
	severity = "";
	if(hvf.getPts_five() >= 56 || hvf.getPts_one() >= 37) {
		severity = " style='background:red;' ";
	}
	else if(hvf.getPts_five() >= 37 && hvf.getPts_one() >= 19) {
		severity = " style='background:orange;' ";
	}
	else if(hvf.getPts_five() >= 19 && hvf.getPts_one() >= 12) {
		severity = " style='background:yellow;'";
	}
	else {
		severity = " style='background:green;'";
	}

%>
<div <%out.print(severity);%>>
# points depressed < 5% level on Pattern Deviation Plot <input type="text" name="pts_five" <%out.print("value='"+hvf.getPts_five()+"'");%>><br>
# points depressed < 1% level on Pattern Deviation Plot <input type="text" name="pts_one" <%out.print("value='"+hvf.getPts_one()+"'");%>><br>
</div>
# contiguous points < 5% level on Pattern Deviation Plot <input type="text" name="pts_contig" <%out.print("value='"+hvf.getPts_contig()+"'");%>><br><br>

<%
	severity = "";
	int sup = hvf.getSup_hem();
	int inf = hvf.getInf_hem();
	if(sup >= 2 && inf >= 2) {
		severity = " style='background:red;' ";
	}
	else if(sup >= 1 && inf >= 1) {
		severity = " style='background:orange;' ";
	}
	else if((sup + inf) >= 1) {
		severity = " style='background:yellow;'";
	}
	else {
		severity = " style='background:green;'";
	}

%>
<div <%out.print(severity);%>>
Superior hemifield: # points <15dB within 5 degrees of fixations?<br> <input type="text" name="sup_hem" <%out.print("value='"+hvf.getSup_hem()+"'");%>><br>
Inferior hemfield: # points <15dB within 5 degrees of fixation?<br> <input type="text" name="inf_hem" <%out.print("value='"+hvf.getInf_hem()+"'");%>><br><br>
</div>

Superiour hemifield: # points with sensitivity >=15dB within 5 degrees of fixation?<br> <input type="text" name="sup_hem2" <%out.print("value='"+hvf.getSup_hem2()+"'");%>><br>
Inferior hemfield: # points with sensitivity >=15dB within 5 degrees of fixation?<br> <input type="text" name="inf_hem2" <%out.print("value='"+hvf.getInf_hem2()+"'");%>><br><br>

Categorization of glaucoma severity:<br>
<input type="radio" name="severe" value="0"<%if(hvf.getSevere()==0) {out.print(" checked");}%>>no glaucoma / minimal defect<br>
<input type="radio" name="severe" value="1"<%if(hvf.getSevere()==1) {out.print(" checked");}%>>early<br>
<input type="radio" name="severe" value="2"<%if(hvf.getSevere()==2) {out.print(" checked");}%>>moderate<br>
<input type="radio" name="severe" value="3"<%if(hvf.getSevere()==3) {out.print(" checked");}%>>advanced<br>
<input type="radio" name="severe" value="4"<%if(hvf.getSevere()==4) {out.print(" checked");}%>>severe<br>
<input type="radio" name="severe" value="4"<%if(hvf.getSevere()==5) {out.print(" checked");}%>>end stage glaucoma<br><br>


<input type="submit" value="Submit" class="btn">
</form>
</div>
<%
}
%>

<script src="javascripts/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="javascripts/HVFChecks.js" type="text/javascript"></script>