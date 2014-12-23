<%-- 
    Document   : OCTReview
    Created on : Dec 22, 2014, 5:03:50 PM
    Author     : aryner
--%>

<%@page import="model.OCTtest"%>
<%@page import="model.Picture"%>
<%@page import="java.util.*"%>

<%
Picture pic = (Picture)request.getAttribute("picture");
ArrayList<String> missingPics = (ArrayList)request.getAttribute("missingPics");

if(pic == null) { 

%>
<h3>You have finished all uploaded OCT files!</h3>
<%
	int access = (Integer)request.getAttribute("access");

	if(access == 0) {
		int needToPairCount = (Integer)request.getAttribute("needToPairCount");
		if(needToPairCount > 0) {
			out.print(needToPairCount+" still need to be graded by someone else before adjudication");
		} else {
			out.print("OCT is ready for adjudication!");
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
	String src = "http://localhost:8084/Glaucoma/pdf?type=OCT&name="+pic.getName();
	boolean a = true;
	OCTtest oct = (OCTtest)request.getAttribute("oct");
	out.print("<h3 class='picName'>"+pic.getType()+" "+pic.getName()+"</h3>");
%>
<iframe src="<%out.print(src);%>" class="HVFimage"></iframe>

<!--Questions-->
<div class="questions">
<form action="updateOCT" method="POST">
	<input type="hidden" name="pictureName" value="<%out.print(pic.getName());%>" autocomplete="off">
	<input type="hidden" name="alreadyConfirmed" value="<%out.print((request.getAttribute("confirmed")!=null)?"true":"false");%>">
<%
	int v1 = -1;
	int v2 = -1;
	boolean diff = false;
	if(a) {
		v1 = oct.getType();
		v2 = oct.getType();
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
<span id='type'>Scan Type</span><br>
<input type="radio" name="type" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>Fast RNFL thickness(3.4)<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="type" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>RNFL thickness(3.4)<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="type" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>RNFL thickness(2.27 x disk)<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="type" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>RNFL map<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="type" value="5"<%if(a && !diff && v1==5){out.print(" checked='true'");}%>><span id='type_oth'>Other : </span>
<%if((a&&diff)&&((v1==5)||(v2==5))){%><span class='highlight'>***</span><%}%>
<%
	String s1 = "";
	String s2 = "";
	if(a) {
		s1 = oct.getType_oth()+"";
		s2 = oct.getType_oth()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<input type="text" name="type_oth" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getLength()+"";
		s2 = oct.getLength()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="length">Scan Length(mm): </span>
<input type="text" name="length" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>


<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getSnum()+"";
		s2 = oct.getSnum()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="savg_snum" class="error hidden">S-Number and Savg - Number must match</span><br>
<span id="snum">S - Number: </span>
<input type="text" name="snum" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = oct.getScol();
		v2 = oct.getScol();
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
<span id='scol'>S - Color: </span><br>
<input type="radio" name="scol" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="scol" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="scol" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="scol" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getNnum()+"";
		s2 = oct.getNnum()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="nnum">N - Number: </span>
<input type="text" name="nnum" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = oct.getNcol();
		v2 = oct.getNcol();
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
<span id='ncol'>N - Color: </span><br>
<input type="radio" name="ncol" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="ncol" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="ncol" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="ncol" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getInum()+"";
		s2 = oct.getInum()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="iavg_inum" class="error hidden">I-Number and Iavg-Number must match</span><br>
<span id="inum">I - Number: </span>
<input type="text" name="inum" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = oct.getIcol();
		v2 = oct.getIcol();
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
<span id='icol'>I - Color: </span><br>
<input type="radio" name="icol" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="icol" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="icol" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="icol" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getTnum()+"";
		s2 = oct.getTnum()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="tnum">T - Number: </span>
<input type="text" name="tnum" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = oct.getTcol();
		v2 = oct.getTcol();
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
<span id='tcol'>T - Color: </span><br>
<input type="radio" name="tcol" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="tcol" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="tcol" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="tcol" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getSig()+"";
		s2 = oct.getSig()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="sig">Signal Strength (Max 10)</span>
<input type="text" name="sig" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getIsnum()+"";
		s2 = oct.getIsnum()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="isnum">Imax/Smax - Number: </span>
<input type="text" name="isnum" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = oct.getIscol();
		v2 = oct.getIscol();
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
<span id='iscol'>Imax/Smax - Color: </span><br>
<input type="radio" name="iscol" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="iscol" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="iscol" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="iscol" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getSinum()+"";
		s2 = oct.getSinum()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="sinum">Smax/Imax - Number: </span>
<input type="text" name="sinum" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = oct.getSicol();
		v2 = oct.getSicol();
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
<span id='sicol'>Smax/Imax - Color: </span><br>
<input type="radio" name="sicol" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="sicol" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="sicol" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="sicol" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getStnum()+"";
		s2 = oct.getStnum()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="stnum">Smax/Tavg - Number: </span>
<input type="text" name="stnum" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = oct.getStcol();
		v2 = oct.getStcol();
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
<span id='stcol'>Smax/Tavg - Color: </span><br>
<input type="radio" name="stcol" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="stcol" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="stcol" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="stcol" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getItnum()+"";
		s2 = oct.getItnum()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="itnum">Imax/Tvag - Number: </span>
<input type="text" name="itnum" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = oct.getItcol();
		v2 = oct.getItcol();
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
<span id='itcol'>Imax/Tvag - Color: </span><br>
<input type="radio" name="itcol" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="itcol" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="itcol" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="itcol" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getSnnum()+"";
		s2 = oct.getSnnum()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="snnum">Smax/Navg - Number: </span>
<input type="text" name="snnum" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = oct.getSncol();
		v2 = oct.getSncol();
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
<span id='sncol'>Smax/Navg - Color: </span><br>
<input type="radio" name="sncol" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="sncol" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="sncol" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="sncol" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getMmnum()+"";
		s2 = oct.getMmnum()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="mmnum">Max-Min - Number: </span>
<input type="text" name="mmnum" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = oct.getMmcol();
		v2 = oct.getMmcol();
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
<span id='mmcol'>Max-min - Color: </span><br>
<input type="radio" name="mmcol" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="mmcol" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="mmcol" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="mmcol" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getSmaxnum()+"";
		s2 = oct.getSmaxnum()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="smaxnum">Smax - Number: </span>
<input type="text" name="smaxnum" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = oct.getSmaxcol();
		v2 = oct.getSmaxcol();
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
<span id='smaxcol'>Smax - Color</span><br>
<input type="radio" name="smaxcol" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="smaxcol" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="smaxcol" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="smaxcol" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getImaxnum()+"";
		s2 = oct.getImaxnum()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="imaxnum">Imax - Number: </span>
<input type="text" name="imaxnum" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = oct.getImaxcol();
		v2 = oct.getImaxcol();
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
<span id='imaxcol'>Imax - Color: </span><br>
<input type="radio" name="imaxcol" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="imaxcol" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="imaxcol" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="imaxcol" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getSavgnum()+"";
		s2 = oct.getSavgnum()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="savg_snum" class="error hidden">S-Number and Savg - Number must match</span><br>
<span id="savgnum">Savg - Number: </span>
<input type="text" name="savgnum" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = oct.getSavgcol();
		v2 = oct.getSavgcol();
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
<span id='savgcol'>Savg - Color: </span><br>
<input type="radio" name="savgcol" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="savgcol" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="savgcol" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="savgcol" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getIavgnum()+"";
		s2 = oct.getIavgnum()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="iavg_inum" class="error hidden">I-Number and Iavg-Number must match</span><br>
<span id="iavgnum">Iavg - Number: </span>
<input type="text" name="iavgnum" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = oct.getIavgcol();
		v2 = oct.getIavgcol();
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
<span id='iavgcol'>Iavg - Color: </span><br>
<input type="radio" name="iavgcol" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="iavgcol" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="iavgcol" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="iavgcol" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%>
<br><br>

<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getAtnum()+"";
		s2 = oct.getAtnum()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="atnum">Avg. Thick - Number: </span>
<input type="text" name="atnum" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = oct.getAtcol();
		v2 = oct.getAtcol();
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
<span id='atcol'>Avg. Thick - Color: </span><br>
<input type="radio" name="atcol" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="atcol" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="atcol" value="3"<%if(a && !diff && v1==3){out.print(" checked='true'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="atcol" value="4"<%if(a && !diff && v1==4){out.print(" checked='true'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%>
<br><br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = Integer.parseInt(oct.getEye());
		v2 = Integer.parseInt(oct.getEye());
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
<span id='eye'>Eye : </span><br>
<input type="radio" name="eye" value="1"<%if(a && !diff && v1==1){out.print(" checked='true'");}%>>OD<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%><br>
<input type="radio" name="eye" value="2"<%if(a && !diff && v1==2){out.print(" checked='true'");}%>>OS<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%>
<br><br>

<input type="submit" value="Submit" class="btn">

</form>

<%
}
%>

<script src="javascripts/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="javascripts/OCTChecks.js" type="text/javascript"></script>