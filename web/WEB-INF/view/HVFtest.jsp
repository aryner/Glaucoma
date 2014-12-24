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
ArrayList<String> missingPics = (ArrayList)request.getAttribute("missingPics");

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
	if(missingPics != null && missingPics.size() > 0) {
%>
<h3>The following HVF PDFs still need to be uploaded</h3>
<%
		for(int i=0; i<missingPics.size(); i++) {
			out.print(missingPics.get(i)+"<br>");
		}
	}
} else {
	String slash = ""+request.getAttribute("slash");
	String src = "http://localhost:8084/HVF/pdf?type=HVF&name="+pic.getName();
	boolean a = false;
	Vector<HVFtest> HVF = null;
	if(((Integer)request.getAttribute("access"))==1) {
		a = true;
		HVF = (Vector)request.getAttribute("pair");
	}
	String name = (String)request.getAttribute("gradingChart");
	if (name == null) {
		name = "";
	}
	out.print("<h3 class='picName'>"+pic.getType()+" "+pic.getName()+"</h3>");
%>
<!--pdf of HVFfile -->
<iframe src="<%out.print(src);%>" class="HVFimage"></iframe>

<!--Questions-->
<div class="questions">
<form action="assignHVF" method="POST">
	<input type="hidden" class="gradingChart" name="<%out.print(name);%>">
	<input type="hidden" name="pictureName" value="<%out.print(pic.getName());%>" autocomplete="off">
	<input type="hidden" name="alreadyConfirmed" value="<%out.print((request.getAttribute("confirmed")!=null)?"true":"false");%>">
<%
	int v1 = -1;
	int v2 = -1;
	boolean diff = false;
	if(a) {
		v1 = HVF.get(0).getMon();
		v2 = HVF.get(1).getMon();
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
<span id='mon'>Fixation Monitor: </span><br>
<input type="radio" name="mon" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>Blind Spot<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="mon" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Gaze/Blind Spot<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="mon" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Gaze Track<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="mon" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>><span id='mon_oth2_c74'>Other : </span>
<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%>
<%
	String s1 = "";
	String s2 = "";
	if(a) {
		s1 = HVF.get(0).getMon_oth2_c74()+"";
		s2 = HVF.get(1).getMon_oth2_c74()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<input type="text" name="mon_oth2_c74" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = HVF.get(0).getTar();
		v2 = HVF.get(1).getTar();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='tar'>Fixation Target:</span> <br>
<input type="radio" name="tar" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>Central<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="tar" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Small Diamond<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="tar" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Large Diamond<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="tar" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>Bottom LED<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="tar" value="5"<%if(a && !diff && v1==5){out.print(" checked='true'");}%>><span id='tar_oth'>Other : </span>
<%if((a&&diff)&&((v1==5)||(v2==5))){%><span class='highlight'>***</span><%}%>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = HVF.get(0).getTar_oth()+"";
		s2 = HVF.get(1).getTar_oth()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<input type="text" name="tar_oth" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>
<!--ints only -->
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = HVF.get(0).getLossnum()+"/"+HVF.get(0).getLossden();
		s2 = HVF.get(1).getLossnum()+"/"+HVF.get(1).getLossden();
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='lossnum'>Fixation Losses  </span>
<input type="text" name="lossnum" class="numBox" autocomplete="off"<%if(a&&!diff){out.print(" value='"+HVF.get(0).getLossnum()+"'");}%>>
/ <input type="text" name="lossden" class="numBox" autocomplete="off"<%if(a&&!diff){out.print(" value='"+HVF.get(0).getLossden()+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	if(a) {
		s1 = HVF.get(0).getFp()+"";
		s2 = HVF.get(1).getFp()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='fp'>False POS Errors (%) </span>
<input type="text" name="fp" class="numBox" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>
<%
	if(a) {
		s1 = HVF.get(0).getFn()+"";
		s2 = HVF.get(1).getFn()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='fn'>False NEG Errors (%) </span>
<input type="text" name="fn" class="numBox" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	if(a) {
		s1 = HVF.get(0).getDur()+"";
		s2 = HVF.get(1).getDur()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='dur'>Test Duration </span>
<input type="text" name="dur" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	if(a) {
		s1 = HVF.get(0).getFov()+"";
		s2 = HVF.get(1).getFov()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='fov'>Fovea(dB) </span>
<input type="text" name="fov" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = HVF.get(0).getStimintens();
		v2 = HVF.get(1).getStimintens();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='stimintens'>Stimulus - Intensity:</span> <br>
<input type="radio" name="stimintens" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>I<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="stimintens" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>II<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="stimintens" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>III<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="stimintens" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>IV<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="stimintens" value="5"<%if(a && !diff && v1==5){out.print(" checked='true'");}%>>V<%if((a&&diff)&&((v1==5)||(v2==5))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="stimintens" value="999"<%if(a && !diff && v1==999){out.print(" checked='true'");}%>>Blank<%if((a&&diff)&&((v1==999)||(v2==999))){%><span class='highlight'>***</span><%}%><br>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = HVF.get(0).getStimcol();
		v2 = HVF.get(1).getStimcol();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='stimcol'>Stimulus - Color: </span><br>
<input type="radio" name="stimcol" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="stimcol" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Red<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
	<input type="radio" name="stimcol" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Blue<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="stimcol" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>
<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%>
<%
	if(a) {
		s1 = HVF.get(0).getStimcol_oth()+"";
		s2 = HVF.get(1).getStimcol_oth()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='stimcol_oth'>Other: </span>
<input type="text" name="stimcol_oth" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>
<!--if color is blue?-->
<%
	if(a) {
		s1 = HVF.get(0).getBack()+"";
		s2 = HVF.get(1).getBack()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='back'>Background </span>
<input type="text" name="back" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = HVF.get(0).getStrategy();
		v2 = HVF.get(1).getStrategy();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='strategy'>Strategy: </span><br>
<input type="radio" name="strategy" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>SITA - Standard<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="strategy" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>SITA - Fast<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="strategy" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Full Threshold<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="strategy" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>FastPac<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="strategy" value="5"<%if(a && !diff && v1==5){out.print(" checked='true'");}%>>
<%if((a&&diff)&&((v1==5)||(v2==5))){%><span class='highlight'>***</span><%}%>
<%
	if(a) {
		s1 = HVF.get(0).getStrategy_oth()+"";
		s2 = HVF.get(1).getStrategy_oth()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='strategy_oth'>Other: </span>
<input type="text" name="strategy_oth">
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	if(a) {
		s1 = HVF.get(0).getLossnum()+"";
		s2 = HVF.get(1).getLossnum()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='pup'>Pupil Diameter (mm) </span>
<input type="text" name="pup"<%if(a&&!diff){out.print(" value='"+s1+"'");}%> autocomplete="off">
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	if(a) {
		s1 = HVF.get(0).getVanum()+"/"+HVF.get(0).getVaden();
		s2 = HVF.get(1).getVanum()+"/"+HVF.get(1).getVaden();
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='vanum'>Visual Acuity </span>
<input type="text" name="vanum" class="numBox" autocomplete="off"<%if(a&&!diff){out.print(" value='"+HVF.get(0).getVanum()+"'");}%>> 
/ <input type="text" name="vaden" class="numBox" autocomplete="off"<%if(a&&!diff){out.print(" value='"+HVF.get(0).getVanum()+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = HVF.get(0).getSph_sign();
		v2 = HVF.get(1).getSph_sign();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<br> <input type="button" id="gradingChart" value="See Grading Chart" class="btn"><br><br>
<span id='sph_sign'>RX Sphere - Sign </span>
<input type="radio" name="sph_sign" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>+<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%> 
	<input type="radio" name="sph_sign" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>-<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%>
<br>

<%
	if(a) {
		s1 = HVF.get(0).getSph_num()+"";
		s2 = HVF.get(1).getSph_num()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='sph_num'>RX Sphere - Number </span>
<input type="text" name="sph_num" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = HVF.get(0).getCyl_sign();
		v2 = HVF.get(1).getCyl_sign();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='cyl_sign'>RX Cylinder - Sign </span>
<input type="radio" name="cyl_sign" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>+<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class="highlight">***</span><%}%> 
<input type="radio" name="cyl_sign" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>-<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class="highlight">***</span><%}%>
<br>

<%
	if(a) {
		s1 = HVF.get(0).getCyl_num()+"";
		s2 = HVF.get(1).getCyl_num()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='cyl_num'>RX Cylinder - Number </span>
<input type="text" name="cyl_num" autocomplete="off"<%if(!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	if(a) {
		s1 = HVF.get(0).getAxis()+"";
		s2 = HVF.get(1).getAxis()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='axis'>RX Axis </span>
<input type="text" name="axis" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = HVF.get(0).getGht();
		v2 = HVF.get(1).getGht();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='ght'>GHT:</span> <br>
<input type="radio" name="ght" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>Within Normal Limits<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="ght" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Borderline / General Reduction<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="ght" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Outside Normal Limits<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="ght" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>General Reduction of Sensitivity<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="ght" value="5"<%if(a && !diff && v1==5){out.print(" checked='true'");}%>>Abnormally High Sensitivity<%if((a&&diff)&&((v1==5)||(v2==5))){%><span class='highlight'>  ***</span><%}%><br>
<input type="radio" name="ght" value="999"<%if(a && !diff && v1==999){out.print(" checked='true'");}%>>Blank<%if((a&&diff)&&((v1==999)||(v2==999))){%><span class='highlight'>  ***</span><%}%><br>
<br>

<%
	if(a) {
		s1 = HVF.get(0).getVfi()+"";
		s2 = HVF.get(1).getVfi()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='vfi'>VFI (%) </span>
<input type="text" name="vfi" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'>  "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = HVF.get(0).getMdsign();
		v2 = HVF.get(1).getMdsign();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='mdsign'>MD - Sign </span>
<input type="radio" name="mdsign" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>+<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'> ***</span><%}%> 
<input type="radio" name="mdsign" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>-<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'> ***</span><%}%><br>

<%
	if(a) {
		s1 = HVF.get(0).getMddb()+"";
		s2 = HVF.get(1).getMddb()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='mddb'>MD - dB </span><input type="text" name="mddb" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'>  "+s1+" | "+s2+"</span>");}%>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = HVF.get(0).getMdp();
		v2 = HVF.get(1).getMdp();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='mdp'>MD - P-value: </span><br>
<input type="radio" name="mdp" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>><0.5%<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="mdp" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>><1%<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="mdp" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>><2%<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="mdp" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>><5%<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="mdp" value="5"<%if(a && !diff && v1==5){out.print(" checked='true'");}%>><10%<%if((a&&diff)&&((v1==5)||(v2==5))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="mdp" value="999"<%if(a && !diff && v1==999){out.print(" checked='true'");}%>>Blank<%if((a&&diff)&&((v1==999)||(v2==999))){%><span class='highlight'> ***</span><%}%><br>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = HVF.get(0).getPsdsign();
		v2 = HVF.get(1).getPsdsign();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='psdsign'>PSD - Sign </span><input type="radio" name="psdsign" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>+<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'> ***</span><%}%> 
<input type="radio" name="psdsign" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>-<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'> ***</span><%}%><br>

<%
	if(a) {
		s1 = HVF.get(0).getPsddb()+"";
		s2 = HVF.get(1).getPsddb()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='psddb'>PSD - dB </span><input type="text" name="psddb" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'>  "+s1+" | "+s2+"</span>");}%>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = HVF.get(0).getPsdp();
		v2 = HVF.get(1).getPsdp();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='psdp'>PSD - P-value: </span><br>
<input type="radio" name="psdp" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>><0.5%<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="psdp" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>><1%<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="psdp" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>><2%<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="psdp" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>><5%<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="psdp" value="5"<%if(a && !diff && v1==5){out.print(" checked='true'");}%>><10%<%if((a&&diff)&&((v1==5)||(v2==5))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="psdp" value="999"<%if(a && !diff && v1==999){out.print(" checked='true'");}%>>Blank<%if((a&&diff)&&((v1==999)||(v2==999))){%><span class='highlight'> ***</span><%}%><br><br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = HVF.get(0).getCentral_15();
		v2 = HVF.get(1).getCentral_15();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<br><b>Central 5% of fixation = the 4 points directly surrounding central fixation</b><br><br>
<span id='central_15'># of points within central 5 degrees with sensitivity < 15 dB (top left graph):</span><br>
<input type="radio" name="central_15" value="0"<%if(a && !diff && v1==0){out.print(" checked='true'");}%>>0<%if((a&&diff)&&((v1==0)||(v2==0))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="central_15" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>1<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="central_15" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>2<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="central_15" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>3<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="central_15" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>4<%if((a&&diff)&&((v1==4)||(v2==5))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="central_15" value="999"<%if(a && !diff && v1==999){out.print(" checked='true'");}%>>Blank<%if((a&&diff)&&((v1==999)||(v2==999))){%><span class='highlight'> ***</span><%}%><br>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = HVF.get(0).getCentral_0();
		v2 = HVF.get(1).getCentral_0();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='central_0'># of points within central 5 degrees with sensitivity < 0 dB (top left graph):</span><br>
<input type="radio" name="central_0" value="0"<%if(a && !diff && v1==0){out.print(" checked='true'");}%>>0<%if((a&&diff)&&((v1==0)||(v2==0))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="central_0" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>1<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="central_0" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>2<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="central_0" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>3<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="central_0" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>4<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="central_0" value="999"<%if(a && !diff && v1==999){out.print(" checked='true'");}%>>Blank<%if((a&&diff)&&((v1==999)||(v2==999))){%><span class='highlight'> ***</span><%}%><br>
<br>

<%
	if(a) {
		s1 = HVF.get(0).getSup_hem()+"";
		s2 = HVF.get(1).getSup_hem()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='sup_hem'>Superior hemifield: # points <15dB within central 5 degrees of fixation? (top left graph) </span><br>
<input type="radio" name="sup_hem" value="0"<%if(a && !diff && s1.equals("0")){out.print(" checked='true'");}%>>0<%if((a&&diff)&&((s1.equals("0"))||(s2.equals("0")))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="sup_hem" value="1"<%if(a && !diff && s1.equals("1")){out.print(" checked='true'");}%>>1<%if((a&&diff)&&((s1.equals("1"))||(s2.equals("1")))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="sup_hem" value="2"<%if(a && !diff && s1.equals("2")){out.print(" checked='true'");}%>>2<%if((a&&diff)&&((s1.equals("2"))||(s2.equals("2")))){%><span class='highlight'> ***</span><%}%><br>
<br>

<%
	if(a) {
		s1 = HVF.get(0).getInf_hem()+"";
		s2 = HVF.get(1).getInf_hem()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='inf_hem'>Inferior hemifield: # points <15dB within central 5 degrees of fixation? (top left graph) </span><br> 
<input type="radio" name="inf_hem" value="0"<%if(a && !diff && s1.equals("0")){out.print(" checked='true'");}%>>0<%if((a&&diff)&&((s1.equals("0"))||(s2.equals("0")))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="inf_hem" value="1"<%if(a && !diff && s1.equals("1")){out.print(" checked='true'");}%>>1<%if((a&&diff)&&((s1.equals("1"))||(s2.equals("1")))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="inf_hem" value="2"<%if(a && !diff && s1.equals("2")){out.print(" checked='true'");}%>>2<%if((a&&diff)&&((s1.equals("2"))||(s2.equals("2")))){%><span class='highlight'> ***</span><%}%><br>
<br>

<%
	if(a) {
		s1 = HVF.get(0).getPts_five_top()+"";
		s2 = HVF.get(1).getPts_five_top()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='pts_five'># points depressed < 5% level on Pattern Deviation Plot</span><br>
Top <input type="text" name="pts_five_top" class='numBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>> 
<%if(a&&diff){out.print("<span class='highlight'>  "+s1+" | "+s2+"</span>");}%>
<%
	if(a) {
		s1 = HVF.get(0).getPts_five_bot()+"";
		s2 = HVF.get(1).getPts_five_bot()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
 Bot <input type="text" name="pts_five_bot" class='numBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'>  "+s1+" | "+s2+"</span>");}%><br>
<br>

<%
	if(a) {
		s1 = HVF.get(0).getPts_one_top()+"";
		s2 = HVF.get(1).getPts_one_top()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='pts_one'># points depressed < 1% level on Pattern Deviation Plot </span><br>
Top <input type="text" name="pts_one_top" class='numBox' autocomplete="off" <%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'>  "+s1+" | "+s2+"</span>");}%>
<%
	if(a) {
		s1 = HVF.get(0).getPts_one_bot()+"";
		s2 = HVF.get(1).getPts_one_bot()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
Bot <input type="text" name="pts_one_bot" class='numBox' autocomplete="off" <%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'>  "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = HVF.get(0).getCluster();
		v2 = HVF.get(1).getCluster();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='cluster'>Cluster of >= 3 points that are all significant<br> at p < 5% with >= 1 point
	being significant at p < 1%</span><br>
<input type="radio" name="cluster" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>Yes<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'> ***</span><%}%> 
<input type="radio" name="cluster" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>No<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'> ***</span><%}%>
<input type="radio" name="cluster" value="999"<%if(a && !diff && v1==999){out.print(" checked='true'");}%>>Blank<%if((a&&diff)&&((v1==999)||(v2==999))){%><span class='highlight'> ***</span><%}%> <br>
<br> 
<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = HVF.get(0).getNotes();
		v2 = HVF.get(1).getNotes();
		if(v1 == v2) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='notes'>Notes: </span><br>
<input type="radio" name="notes" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>Low test reliability<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="notes" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Excessive High False Positives<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="notes" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Excessive High False Negatives<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="notes" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%><%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'> ***</span><%}%>>
<%
	if(a) {
		s1 = HVF.get(0).getNotes_other()+"";
		s2 = HVF.get(1).getNotes_other()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='notes_other'>Other: </span>
<input type="text" name="notes_other">
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>
<input type="radio" name="notes" value="999"<%if(a && !diff && v1==999){out.print(" checked='true'");}%>>Blank<%if((a&&diff)&&((v1==999)||(v2==999))){%><span class='highlight'> ***</span><%}%><br>
<br>

<input type="submit" value="Submit" class="btn">
</form>
</div>
<%
}
%>

<script src="javascripts/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="javascripts/HVFChecks.js" type="text/javascript"></script>
<script src="javascripts/HVFgradingChart.js" type="text/javascript"></script>