<%-- 
    Document   : HVFtest
    Created on : Aug 25, 2014, 4:21:10 PM
    Author     : aryner
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
	int access = (Integer)request.getAttribute("access");

	if(access == 0) {
		int needToPairCount = (Integer)request.getAttribute("needToPairCount");
		if(needToPairCount > 0) {
			out.print(needToPairCount+" still need to be graded by someone else before adjudication");
		} else {
			out.print("HVF is ready for adjudication!");
		}
	}
} else {
	String slash = ""+request.getAttribute("slash");
	String src = "http://localhost:8080/Glaucoma/pdf?type=HVF&name="+pic.getName();
%>
<!--pdf of HVFfile -->
<embed src="<%out.print(src);%>" class="HVFimage">

<!--Questions-->
<div class="questions">
<form action="assignHVF" method="POST">
<input type="hidden" name="pictureID" value="<%out.print(pic.getId());%>">
	
Fixation Monitor: <br>
<input type="radio" name="mon" value="1">Blind Spot<br>
<input type="radio" name="mon" value="2">Gaze/Blind Spot<br>
<input type="radio" name="mon" value="3">Gaze Track<br>
<input type="radio" name="mon" value="4">Other : <input type="text" name="mon_oth2_c74"><br><br>

Fixation Target: <br>
<input type="radio" name="tar" value="1">Central<br>
<input type="radio" name="tar" value="2">Small Diamond<br>
<input type="radio" name="tar" value="3">Large Diamond<br>
<input type="radio" name="tar" value="4">Bottom LED<br>
<input type="radio" name="tar" value="5">Other : <input type="text" name="tar_oth"><br><br>
<!--ints only -->
Fixation Losses <input type="text" name="lossnum" class="numBox"> 
/ <input type="text" name="lossden" class="numBox"><br><br>

False POS Errors (%) <input type="text" name="fp" class="numBox"><br>
False NEG Errors (%) <input type="text" name="fn" class="numBox"><br><br>

Test Duration <input type="text" name="dur"><br><br>

Fovea(dB) <input type="text" name="fov"><br><br>

Stimulus - Intensity: <br>
<input type="radio" name="stimintens" value="1">I<br>
<input type="radio" name="stimintens" value="2">II<br>
<input type="radio" name="stimintens" value="3">III<br>
<input type="radio" name="stimintens" value="4">IV<br>
<input type="radio" name="stimintens" value="5">V<br><br>

Stimulus - Color: <br>
<input type="radio" name="stimcol" value="1">White<br>
<input type="radio" name="stimcol" value="2">Red<br>
<input type="radio" name="stimcol" value="3">Blue<br>
<input type="radio" name="stimcol" value="4">Other: <input type="text" name="stimcol_oth"><br><br>
<!--if color is blue?-->
Background <input type="text" name="back"><br><br>

Strategy: <br>
<input type="radio" name="strategy" value="1">SITA - Standard<br>
<input type="radio" name="strategy" value="2">SITA - Fast<br>
<input type="radio" name="strategy" value="3">Full Threshold<br>
<input type="radio" name="strategy" value="4">FastPac<br>
<input type="radio" name="strategy" value="5">Other: <input type="text" name="strategy_oth"><br><br>

Pupil Diameter (mm) <input type="text" name="pup"><br><br>

Visual Acuity <input type="text" name="vanum" class="numBox"> 
/ <input type="text" name="vaden" class="numBox"><br><br>

RX Sphere - Sign <input type="radio" name="sph_sign" value="2">+ <input type="radio" name="sph_sign" value="1">-<br>
RX Sphere - Number <input type="text" name="sph_num"><br><br>

RX Cylinder - Sign <input type="radio" name="cyl_sign" value="2">+ <input type="radio" name="cyl_sign" value="1">-<br>
RX Cylinder - Number <input type="text" name="cyl_num"><br>
RX Axis <input type="text" name="axis"><br><br>

GHT: <br>
<input type="radio" name="ght" value="1">Within Normal Limits<br>
<input type="radio" name="ght" value="2">Borderline<br>
<input type="radio" name="ght" value="3">Outside Normal Limits<br>
<input type="radio" name="ght" value="4">General Reduction of Sensitivity<br>
<input type="radio" name="ght" value="5">Abnormally High Sensitivity<br><br>

VFI (%) <input type="text" name="vfi"><br><br>

MD - Sign <input type="radio" name="mdsign" value="2">+ <input type="radio" name="mdsign" value="1">-<br>
MD - dB <input type="text" name="mddb"><br>
MD - P-value: <br>
<input type="radio" name="mdp" value="1"><0.5%<br>
<input type="radio" name="mdp" value="2"><1%<br>
<input type="radio" name="mdp" value="3"><2%<br>
<input type="radio" name="mdp" value="4"><5%<br>
<input type="radio" name="mdp" value="5"><10%<br>
<input type="radio" name="mdp" value="6">Blank<br><br>

PSD - Sign <input type="radio" name="psdsign" value="2">+ <input type="radio" name="psdsign" value="1">-<br>
PSD - dB <input type="text" name="psddb"><br>
PSD - P-value: <br>
<input type="radio" name="psdp" value="1"><0.5%<br>
<input type="radio" name="psdp" value="2"><1%<br>
<input type="radio" name="psdp" value="3"><2%<br>
<input type="radio" name="psdp" value="4"><5%<br>
<input type="radio" name="psdp" value="5"><10%<br>
<input type="radio" name="psdp" value="6">Blank<br><br>

# points within central 5 degrees with sensitivity <0dB <input type="text" name="pts2"><br><br>

Superior hemifield: # points <15dB within 5 degrees of fixations?<br> <input type="text" name="sup_hem"><br>
Inferior hemfield: # points <15dB within 5 degrees of fixation?<br> <input type="text" name="inf_hem"><br><br>

Superiour hemifield: # points with sensitivity >=15dB within 5 degrees of fixation?<br> <input type="text" name="sup_hem2"><br>
Inferior hemfield: # points with sensitivity >=15dB within 5 degrees of fixation?<br> <input type="text" name="inf_hem2"><br><br>

# points depressed < 5% level on Pattern Deviation Plot <input type="text" name="pts_five"><br>
# contiguous points < 5% level on Pattern Deviation Plot <input type="text" name="pts_contig"><br>
# points depressed < 1% level on Pattern Deviation Plot <input type="text" name="pts_one"><br><br>

Cluster of >= 3 non-edge points that are all significant<br> at p < 5% with >= 1 point being significant at p < 5% <br>with >= 1 point
being significant at p < 1%<br>
<input type="radio" name="cluster" value="1">Yes 
<input type="radio" name="cluster" value="2">No <br><br> 

<input type="submit" value="Submit" class="btn">
</form>
</div>
<%
}
%>

<script src="javascripts/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="javascripts/HVFChecks.js" type="text/javascript"></script>