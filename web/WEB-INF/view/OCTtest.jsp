<%-- 
    Document   : OCTtest
    Created on : Aug 25, 2014, 4:20:54 PM
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
	boolean a = false;
	Vector<OCTtest> OCT = null;
	if(((Integer)request.getAttribute("access"))==1) {
		a = true;
		OCT = (Vector)request.getAttribute("pair");
	}
	out.print("<h3 class='picName'>"+pic.getType()+" "+pic.getName()+"</h3>");
%>
<iframe src="<%out.print(src);%>" class="HVFimage"></iframe>

<!--Questions-->
<div class="questions">
<form action="assignOCT" method="POST">
	<input type="hidden" name="pictureName" value="<%out.print(pic.getName());%>" autocomplete="off">
	<input type="hidden" name="alreadyConfirmed" value="<%out.print((request.getAttribute("confirmed")!=null)?"true":"false");%>">
<%
	String s1 = "";
	String s2 = "";
	boolean diff = false;
	if(a) {
		s1 = OCT.get(0).getLength()+"";
		s2 = OCT.get(1).getLength()+"";
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
	int v1 = -1;
	int v2 = -1;
	if(a) {
		v1 = OCT.get(0).getType();
		v2 = OCT.get(1).getType();
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
<input type="radio" name="type" value="5"<%if(a && !diff && v1==5){out.print(" checked='true'");}%>><span id='targ_oth'>Other : </span>
<%if((a&&diff)&&((v1==5)||(v2==5))){%><span class='highlight'>***</span><%}%>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = OCT.get(0).getType_oth()+"";
		s2 = OCT.get(1).getType_oth()+"";
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
		s1 = OCT.get(0).getSnum()+"";
		s2 = OCT.get(1).getSnum()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="snum">S - Number: </span>
<input type="text" name="snum" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = OCT.get(0).getScol();
		v2 = OCT.get(1).getScol();
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
		s1 = OCT.get(0).getNnum()+"";
		s2 = OCT.get(1).getNnum()+"";
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
		v1 = OCT.get(0).getNcol();
		v2 = OCT.get(1).getNcol();
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
		s1 = OCT.get(0).getInum()+"";
		s2 = OCT.get(1).getInum()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="inum">I - Number: </span>
<input type="text" name="inum" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = OCT.get(0).getIcol();
		v2 = OCT.get(1).getIcol();
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
		s1 = OCT.get(0).getTnum()+"";
		s2 = OCT.get(1).getTnum()+"";
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
		v1 = OCT.get(0).getTcol();
		v2 = OCT.get(1).getTcol();
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
		s1 = OCT.get(0).getSig()+"";
		s2 = OCT.get(1).getSig()+"";
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
		s1 = OCT.get(0).getIsnum()+"";
		s2 = OCT.get(1).getIsnum()+"";
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
		v1 = OCT.get(0).getIscol();
		v2 = OCT.get(1).getIscol();
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
		s1 = OCT.get(0).getSinum()+"";
		s2 = OCT.get(1).getSinum()+"";
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
		v1 = OCT.get(0).getSicol();
		v2 = OCT.get(1).getSicol();
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
		s1 = OCT.get(0).getStnum()+"";
		s2 = OCT.get(1).getStnum()+"";
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
		v1 = OCT.get(0).getStcol();
		v2 = OCT.get(1).getStcol();
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
		s1 = OCT.get(0).getItnum()+"";
		s2 = OCT.get(1).getItnum()+"";
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
		v1 = OCT.get(0).getItcol();
		v2 = OCT.get(1).getItcol();
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
		s1 = OCT.get(0).getSnnum()+"";
		s2 = OCT.get(1).getSnnum()+"";
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
		v1 = OCT.get(0).getSncol();
		v2 = OCT.get(1).getSncol();
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
		s1 = OCT.get(0).getMmnum()+"";
		s2 = OCT.get(1).getMmnum()+"";
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
		v1 = OCT.get(0).getMmcol();
		v2 = OCT.get(1).getMmcol();
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
		s1 = OCT.get(0).getSmaxnum()+"";
		s2 = OCT.get(1).getSmaxnum()+"";
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
		v1 = OCT.get(0).getSmaxcol();
		v2 = OCT.get(1).getSmaxcol();
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
		s1 = OCT.get(0).getImaxnum()+"";
		s2 = OCT.get(1).getImaxnum()+"";
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
		v1 = OCT.get(0).getImaxcol();
		v2 = OCT.get(1).getImaxcol();
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
		s1 = OCT.get(0).getIavgnum()+"";
		s2 = OCT.get(1).getIavgnum()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="iavgnum">Iavg - Number: </span>
<input type="text" name="iavgnum" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = OCT.get(0).getIavgcol();
		v2 = OCT.get(1).getIavgcol();
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
		s1 = OCT.get(0).getAtnum()+"";
		s2 = OCT.get(1).getAtnum()+"";
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
		v1 = OCT.get(0).getAtcol();
		v2 = OCT.get(1).getAtcol();
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

<input type="submit" value="Submit" class="btn">

</form>

<%
}
%>

<script src="javascripts/jquery-1.11.1.min.js" type="text/javascript"></script>