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
	boolean a = false;
	Vector<HVFtest> HVF = null;
	if(((Integer)request.getAttribute("access"))==1) {
		a = true;
		HVF = (Vector)request.getAttribute("pair");
	}
%>
<!--pdf of HVFfile -->
<embed src="<%out.print(src);%>" class="HVFimage">

<!--Questions-->
<div class="questions">
<form action="assignHVF" method="POST">
	<input type="hidden" name="pictureName" value="<%out.print(pic.getName());%>" autocomplete="off">
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
Fixation Monitor: <br>
<input type="radio" name="mon" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>Blind Spot<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="mon" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Gaze/Blind Spot<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="mon" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Gaze Track<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="mon" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>Other : 
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
Fixation Target: <br>
<input type="radio" name="tar" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>Central<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="tar" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Small Diamond<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="tar" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Large Diamond<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="tar" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>Bottom LED<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="tar" value="5"<%if(a && !diff && v1==5){out.print(" checked='true'");}%>>Other : 
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
Fixation Losses <input type="text" name="lossnum" class="numBox" autocomplete="off"<%if(a&&!diff){out.print(" value='"+HVF.get(0).getLossnum()+"'");}%>> 
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
False POS Errors (%) <input type="text" name="fp" class="numBox" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
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
False NEG Errors (%) <input type="text" name="fn" class="numBox" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
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
Test Duration <input type="text" name="dur" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
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
Fovea(dB) <input type="text" name="fov" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
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
Stimulus - Intensity: <br>
<input type="radio" name="stimintens" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>I<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="stimintens" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>II<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="stimintens" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>III<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="stimintens" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>IV<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="stimintens" value="5"<%if(a && !diff && v1==5){out.print(" checked='true'");}%>>V<%if((a&&diff)&&((v1==5)||(v2==5))){%><span class='highlight'>***</span><%}%><br><br>

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
Stimulus - Color: <br>
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
Other: <input type="text" name="stimcol_oth" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
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
Background <input type="text" name="back" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
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
Strategy: <br>
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
Other: <input type="text" name="strategy_oth">
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
Pupil Diameter (mm) <input type="text" name="pup"<%if(a&&!diff){out.print(" value='"+s1+"'");}%> autocomplete="off">
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
Visual Acuity <input type="text" name="vanum" class="numBox" autocomplete="off"<%if(a&&!diff){out.print(" value='"+HVF.get(0).getVanum()+"'");}%>> 
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
RX Sphere - Sign <input type="radio" name="sph_sign" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>+<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%> 
	<input type="radio" name="sph_sign" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>-<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
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
RX Sphere - Number <input type="text" name="sph_num" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
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
RX Cylinder - Sign <input type="radio" name="cyl_sign" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>+<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class="highlight">***</span><%}%> 
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
RX Cylinder - Number <input type="text" name="cyl_num" autocomplete="off"<%if(!diff){out.print(" value='"+s1+"'");}%>>
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
RX Axis <input type="text" name="axis" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
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
GHT: <br>
<input type="radio" name="ght" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>Within Normal Limits<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="ght" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Borderline<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="ght" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Outside Normal Limits<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="ght" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>General Reduction of Sensitivity<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'> ***</span><%}%><br>
	<input type="radio" name="ght" value="5"<%if(a && !diff && v1==5){out.print(" checked='true'");}%>>Abnormally High Sensitivity<%if((a&&diff)&&((v1==5)||(v2==5))){%><span class='highlight'>  ***</span><%}%><br><br>

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
VFI (%) <input type="text" name="vfi" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
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
MD - Sign <input type="radio" name="mdsign" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>+<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'> ***</span><%}%> 
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
MD - dB <input type="text" name="mddb" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
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
MD - P-value: <br>
<input type="radio" name="mdp" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>><0.5%<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="mdp" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>><1%<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="mdp" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>><2%<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="mdp" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>><5%<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="mdp" value="5"<%if(a && !diff && v1==5){out.print(" checked='true'");}%>><10%<%if((a&&diff)&&((v1==5)||(v2==5))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="mdp" value="6"<%if(a && !diff && v1==6){out.print(" checked='true'");}%>>Blank<%if((a&&diff)&&((v1==6)||(v2==6))){%><span class='highlight'> ***</span><%}%><br><br>

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
PSD - Sign <input type="radio" name="psdsign" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>+<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'> ***</span><%}%> 
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
PSD - dB <input type="text" name="psddb" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
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
PSD - P-value: <br>
<input type="radio" name="psdp" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>><0.5%<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="psdp" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>><1%<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="psdp" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>><2%<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="psdp" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>><5%<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="psdp" value="5"<%if(a && !diff && v1==5){out.print(" checked='true'");}%>><10%<%if((a&&diff)&&((v1==5)||(v2==5))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="psdp" value="6"<%if(a && !diff && v1==6){out.print(" checked='true'");}%>>Blank<%if((a&&diff)&&((v1==6)||(v2==6))){%><span class='highlight'> ***</span><%}%><br><br>

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
# of points within central 5 degrees with sensitivity < 15 dB:<br>
<input type="radio" name="central_15" value="0"<%if(a && !diff && v1==0){out.print(" checked='true'");}%>>0<%if((a&&diff)&&((v1==0)||(v2==0))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="central_15" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>1<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="central_15" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>2<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="central_15" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>3<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="central_15" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>4<%if((a&&diff)&&((v1==4)||(v2==5))){%><span class='highlight'> ***</span><%}%><br><br>

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
# of points within central 5 degrees with sensitivity < 0 dB:<br>
<input type="radio" name="central_0" value="0"<%if(a && !diff && v1==0){out.print(" checked='true'");}%>>0<%if((a&&diff)&&((v1==0)||(v2==0))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="central_0" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>1<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="central_0" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>2<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="central_0" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>3<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'> ***</span><%}%><br>
<input type="radio" name="central_0" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>4<%if((a&&diff)&&((v1==5)||(v2==5))){%><span class='highlight'> ***</span><%}%><br><br>

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
Superior hemifield: # points <15dB within 5 degrees of fixations?<br> <input type="text" name="sup_hem" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'>  "+s1+" | "+s2+"</span>");}%>
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
Inferior hemfield: # points <15dB within 5 degrees of fixation?<br> <input type="text" name="inf_hem" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'>  "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	if(a) {
		s1 = HVF.get(0).getSup_hem2()+"";
		s2 = HVF.get(1).getSup_hem2()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
Superiour hemifield: # points with sensitivity >=15dB within 5 degrees of fixation?<br> <input type="text" name="sup_hem2" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'>  "+s1+" | "+s2+"</span>");}%>
<br>

<%
	if(a) {
		s1 = HVF.get(0).getInf_hem2()+"";
		s2 = HVF.get(1).getInf_hem2()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
Inferior hemfield: # points with sensitivity >=15dB within 5 degrees of fixation?<br> <input type="text" name="inf_hem2" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'>  "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	if(a) {
		s1 = HVF.get(0).getPts_five()+"";
		s2 = HVF.get(1).getPts_five()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
# points depressed < 5% level on Pattern Deviation Plot <input type="text" name="pts_five" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'>  "+s1+" | "+s2+"</span>");}%>
<br>

<%
	if(a) {
		s1 = HVF.get(0).getPts_contig()+"";
		s2 = HVF.get(1).getPts_contig()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
# contiguous points < 5% level on Pattern Deviation Plot <input type="text" name="pts_contig" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'>  "+s1+" | "+s2+"</span>");}%>
<br>

<%
	if(a) {
		s1 = HVF.get(0).getPts_one()+"";
		s2 = HVF.get(1).getPts_one()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
# points depressed < 1% level on Pattern Deviation Plot <input type="text" name="pts_one" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
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
Cluster of >= 3 non-edge points that are all significant<br> at p < 5% with >= 1 point being significant at p < 5% <br>with >= 1 point
being significant at p < 1%<br>
<input type="radio" name="cluster" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>Yes<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'> ***</span><%}%> 
<input type="radio" name="cluster" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>No<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'> ***</span><%}%> <br><br> 

<input type="submit" value="Submit" class="btn">
</form>
</div>
<%
}
%>

<script src="javascripts/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="javascripts/HVFChecks.js" type="text/javascript"></script>