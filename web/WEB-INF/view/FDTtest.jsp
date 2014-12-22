<%-- 
    Document   : FDTtest
    Created on : Aug 25, 2014, 4:20:22 PM
    Author     : aryner
--%>

<%@page import="model.FDTtest"%>
<%@page import="model.Picture"%>
<%@page import="java.util.*"%>

<%
Picture pic = (Picture)request.getAttribute("picture");
ArrayList<String> missingPics = (ArrayList)request.getAttribute("missingPics");

if(pic == null) { 

%>
<h3>You have finished all uploaded FDT files!</h3>
<%
	int access = (Integer)request.getAttribute("access");

	if(access == 0) {
		int needToPairCount = (Integer)request.getAttribute("needToPairCount");
		if(needToPairCount > 0) {
			out.print(needToPairCount+" still need to be graded by someone else before adjudication");
		} else {
			out.print("FDT is ready for adjudication!");
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
	String src = "http://localhost:8080/Glaucoma/pdf?type=FDT&name="+pic.getName();
	boolean a = false;
	Vector<FDTtest> FDT = null;
	if(((Integer)request.getAttribute("access"))==1) {
		a = true;
		FDT = (Vector)request.getAttribute("pair");
	}
	out.print("<h3 class='picName'>"+pic.getType()+" "+pic.getName()+"</h3>");
%>
<iframe src="<%out.print(src);%>" class="HVFimage"></iframe>

<!--Questions-->
<div class="questions">
<form action="assignFDT" method="POST">
	<input type="hidden" name="pictureName" value="<%out.print(pic.getName());%>" autocomplete="off">
	<input type="hidden" name="alreadyConfirmed" value="<%out.print((request.getAttribute("confirmed")!=null)?"true":"false");%>">
<%
	String s1 = "";
	String s2 = "";
	boolean diff = false;
	if(a) {
		s1 = FDT.get(0).getDur()+"";
		s2 = FDT.get(1).getDur()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="dur">Test Duration: </span>
<input type="text" name="dur" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	int v1 = -1;
	int v2 = -1;
	if(a) {
		v1 = FDT.get(0).getTarg();
		v2 = FDT.get(1).getTarg();
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
<span id='targ'>Fixation Target: </span><br>
<input type="radio" name="targ" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>Central<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="targ" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>><span id='targ_oth'>Other : </span>
<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = FDT.get(0).getTarg_oth()+"";
		s2 = FDT.get(1).getTarg_oth()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<input type="text" name="targ_oth" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = FDT.get(0).getFixerr_num()+"/"+FDT.get(0).getFixerr_den();
		s2 = FDT.get(1).getFixerr_num()+"/"+FDT.get(1).getFixerr_den();
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='fixerr'>Fixation Errs: </span>
<input type="text" name="fixerr_num" class="numBox" autocomplete="off"<%if(a&&!diff){out.print(" value='"+FDT.get(0).getFixerr_num()+"'");}%>>
/ <input type="text" name="fixerr_den" class="numBox" autocomplete="off"<%if(a&&!diff){out.print(" value='"+FDT.get(0).getFixerr_den()+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = FDT.get(0).getFp_num()+"/"+FDT.get(0).getFp_den();
		s2 = FDT.get(1).getFp_num()+"/"+FDT.get(1).getFp_den();
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='fp'>False Pos Errs</span>
<input type="text" name="fp_num" class="numBox" autocomplete="off"<%if(a&&!diff){out.print(" value='"+FDT.get(0).getFp_num()+"'");}%>>
/ <input type="text" name="fp_den" class="numBox" autocomplete="off"<%if(a&&!diff){out.print(" value='"+FDT.get(0).getFp_den()+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = FDT.get(0).getFn_num()+"/"+FDT.get(0).getFn_den();
		s2 = FDT.get(1).getFn_num()+"/"+FDT.get(1).getFn_den();
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='fn'>False Neg Errs</span>
<input type="text" name="fn_num" class="numBox" autocomplete="off"<%if(a&&!diff){out.print(" value='"+FDT.get(0).getFn_num()+"'");}%>>
/ <input type="text" name="fn_den" class="numBox" autocomplete="off"<%if(a&&!diff){out.print(" value='"+FDT.get(0).getFn_den()+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = FDT.get(0).getTest();
		v2 = FDT.get(1).getTest();
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
<span id='test'>Test Algorithm</span><br>
<input type="radio" name="test" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>24-2-FDT Threshold<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="test" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>><span id='targ_oth'>Other : </span>
<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = FDT.get(0).getTest_oth()+"";
		s2 = FDT.get(1).getTest_oth()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<input type="text" name="test_oth" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = FDT.get(0).getSpeed();
		v2 = FDT.get(1).getSpeed();
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
<span id='speed'>Test Speed</span><br>
<input type="radio" name="speed" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>Normal<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="speed" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Slow<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="speed" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>><span id='speed_oth'>Other : </span>
<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = FDT.get(0).getSpeed_oth()+"";
		s2 = FDT.get(1).getSpeed_oth()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<input type="text" name="speed_oth" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = FDT.get(0).getPupil()+"";
		s2 = FDT.get(1).getPupil()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="pupil">Pupil Diameter: </span>
<input type="text" name="pupil" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = FDT.get(0).getVa_num()+"/"+FDT.get(0).getVa_den();
		s2 = FDT.get(1).getVa_num()+"/"+FDT.get(1).getVa_den();
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id='va'>Visual Acuity</span>
<input type="text" name="va_num" class="numBox" autocomplete="off"<%if(a&&!diff){out.print(" value='"+FDT.get(0).getVa_num()+"'");}%>>
/ <input type="text" name="va_den" class="numBox" autocomplete="off"<%if(a&&!diff){out.print(" value='"+FDT.get(0).getVa_den()+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = FDT.get(0).getMdsign();
		v2 = FDT.get(1).getMdsign();
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
<span id='mdsign'>MD - Sign: </span>
<input type="radio" name="mdsign" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>+<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%>
<input type="radio" name="mdsign" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>-<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = FDT.get(0).getMddb()+"";
		s2 = FDT.get(1).getMddb()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="mddb">MD - db: </span>
<input type="text" name="mddb" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = FDT.get(0).getMdp()+"";
		s2 = FDT.get(1).getMdp()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="mdp">MD - P-value(%): </span>
<input type="text" name="mdp" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = FDT.get(0).getPsdsign();
		v2 = FDT.get(1).getPsdsign();
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
<span id='psdsign'>PSD - Sign: </span>
<input type="radio" name="psdsign" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>+<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%>
<input type="radio" name="psdsign" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>-<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = FDT.get(0).getPsdb()+"";
		s2 = FDT.get(1).getPsdb()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="psdb">PSD - db: </span>
<input type="text" name="psdb" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = FDT.get(0).getPsdp()+"";
		s2 = FDT.get(1).getPsdp()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="psdp">PSD - P-value(%): </span>
<input type="text" name="psdp" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = FDT.get(0).getLu_one()+"";
		s2 = FDT.get(1).getLu_one()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="lu_one">Left upper quadrant # <1%: </span>
<input type="text" name="lu_one" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = FDT.get(0).getLu_five()+"";
		s2 = FDT.get(1).getLu_five()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="lu_five">Left upper quadrant # <5%: </span>
<input type="text" name="lu_five" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = FDT.get(0).getRu_one()+"";
		s2 = FDT.get(1).getRu_one()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="ru_one">Right upper quadrant # <1%: </span>
<input type="text" name="ru_one" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = FDT.get(0).getRu_five()+"";
		s2 = FDT.get(1).getRu_five()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="ru_five">Right upper quadrant # <5%: </span>
<input type="text" name="ru_five" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = FDT.get(0).getLl_one()+"";
		s2 = FDT.get(1).getLl_one()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="ll_one">Left lower quadrant # <1%: </span>
<input type="text" name="ll_one" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = FDT.get(0).getLl_five()+"";
		s2 = FDT.get(1).getLl_five()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="ll_five">Left lower quadrant # <5%: </span>
<input type="text" name="ll_five" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = FDT.get(0).getRl_one()+"";
		s2 = FDT.get(1).getRl_one()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="rl_one">Right lower quadrant # <1%: </span>
<input type="text" name="rl_one" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = FDT.get(0).getRl_five()+"";
		s2 = FDT.get(1).getRl_five()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="rl_five">Right lower quadrant # <5%: </span>
<input type="text" name="rl_five" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<input type="submit" value="Submit" class="btn">

</form>

<%
}
%>

<script src="javascripts/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="javascripts/FDTChecks.js" type="text/javascript"></script>