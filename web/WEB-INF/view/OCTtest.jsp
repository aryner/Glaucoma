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
	int v1 = -1;
	int v2 = -1;
	boolean diff = false;
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
<input type="radio" name="type" value="5"<%if(a && !diff && v1==5){out.print(" checked='true'");}%>><span id='type_oth'>Other : </span>
<%if((a&&diff)&&((v1==5)||(v2==5))){%><span class='highlight'>***</span><%}%>
<%
	String s1 = "";
	String s2 = "";
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
<span id="sig">(OD) Signal Strength (Max 10)</span>
<input type="text" name="sig" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = OCT.get(0).getSig_os()+"";
		s2 = OCT.get(1).getSig_os()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<span id="sig_os">(OS) Signal Strength (Max 10)</span>
<input type="text" name="sig_os" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
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

<table border='1'>
	<tr>
		<td>Name </td>
		<td>OD number/color</td>
		<td>OS number/color</td>
	</tr>
	<tr>
		<td>
<span id="snum">S </span>
		</td>
		<td id='scol'>
<input type="text" name="snum" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
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
<select name='scol' >
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
		</td>
		<td id='scol_os'>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = OCT.get(0).getSnum_os()+"";
		s2 = OCT.get(1).getSnum_os()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<input type="text" name="snum_os" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = OCT.get(0).getScol_os();
		v2 = OCT.get(1).getScol_os();
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
<select name='scol_os'> 
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
		</td>
	</tr>
	<tr>
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
<td>
	<span id="nnum">N </span>
</td>
<td id='ncol'>
<input type="text" name="nnum" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

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
<select name='ncol'>
	<option value='0'></option>
	<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
	<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
	<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
	<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = OCT.get(0).getNnum_os()+"";
		s2 = OCT.get(1).getNnum_os()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<td id='ncol_os'>
<input type="text" name="nnum_os" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = OCT.get(0).getNcol_os();
		v2 = OCT.get(1).getNcol_os();
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
<select name='ncol_os'>
	<option value='0'></option>
	<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
	<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
	<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
	<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>
	</tr>
	<tr>
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
<td>
<span id="inum">I</span>
</td>
<td id='icol'>
<input type="text" name="inum" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
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
<select name='icol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = OCT.get(0).getInum_os()+"";
		s2 = OCT.get(1).getInum_os()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<td id='icol_os'>
<input type="text" name="inum_os" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = OCT.get(0).getIcol_os();
		v2 = OCT.get(1).getIcol_os();
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
<select name='icol_os'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>

	<tr>
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
<td>
<span id="tnum">T</span>
</td>
<td id='tcol'>
<input type="text" name="tnum" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

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
<select name='tcol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = OCT.get(0).getTnum_os()+"";
		s2 = OCT.get(1).getTnum_os()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<td id='tcol_os'>
<input type="text" name="tnum_os" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = OCT.get(0).getTcol_os();
		v2 = OCT.get(1).getTcol_os();
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
<select name='tcol_os'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>

	<tr>

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
<td>
<span id="isnum">Imax/Smax</span>
</td>
<td id='iscol'>
<input type="text" name="isnum" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

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
<select name='iscol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>
<td id='iscol_os'>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = OCT.get(0).getIsnum_os()+"";
		s2 = OCT.get(1).getIsnum_os()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<input type="text" name="isnum_os" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = OCT.get(0).getIscol_os();
		v2 = OCT.get(1).getIscol_os();
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
<select name='iscol_os'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>
	<tr>
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
<td>
<span id="sinum">Smax/Imax</span>
</td>
<td id='sicol'>
<input type="text" name="sinum" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

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
<select name='sicol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = OCT.get(0).getSinum_os()+"";
		s2 = OCT.get(1).getSinum_os()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<td id='sicol_os'>
<input type="text" name="sinum_os" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = OCT.get(0).getSicol_os();
		v2 = OCT.get(1).getSicol_os();
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
<select name='sicol_os'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>
	</tr>
	<tr>
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
<td>
<span id="stnum">Smax/Tavg</span>
</td>
<td id='stcol'>
<input type="text" name="stnum" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

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
<select name='stcol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = OCT.get(0).getStnum_os()+"";
		s2 = OCT.get(1).getStnum_os()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<td id='stcol_os'>
<input type="text" name="stnum_os" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = OCT.get(0).getStcol_os();
		v2 = OCT.get(1).getStcol_os();
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
<select name='stcol_os'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>
	</tr>

	<tr>
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
<td>
<span id="itnum">Imax/Tvag</span>
</td>
<td id='itcol'>
<input type="text" name="itnum" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

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
<select name='itcol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = OCT.get(0).getItnum_os()+"";
		s2 = OCT.get(1).getItnum_os()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<td id='itcol_os'>
<input type="text" name="itnum_os" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = OCT.get(0).getItcol_os();
		v2 = OCT.get(1).getItcol_os();
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
<select name='itcol_os'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>

	</tr>
	<tr>
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
<td>
<span id="snnum">Smax/Navg</span>
</td>
<td id='sncol'>
<input type="text" name="snnum" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

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
<select name='sncol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = OCT.get(0).getSnnum_os()+"";
		s2 = OCT.get(1).getSnnum_os()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<td id='sncol_os'>
<input type="text" name="snnum_os" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = OCT.get(0).getSncol_os();
		v2 = OCT.get(1).getSncol_os();
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
<select name='sncol_os'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>

	</tr>

	<tr>
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
<td>
<span id="mmnum">Max-Min</span>
</td>
<td id='mmcol'>
<input type="text" name="mmnum" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

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
<select name='mmcol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = OCT.get(0).getMmnum_os()+"";
		s2 = OCT.get(1).getMmnum_os()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<td id='mmcol_os'>
<input type="text" name="mmnum_os" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = OCT.get(0).getMmcol_os();
		v2 = OCT.get(1).getMmcol_os();
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
<select name='mmcol_os'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>

	</tr>
	<tr>
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
<td>
<span id="smaxnum">Smax</span>
</td>
<td id='smaxcol'>
<input type="text" name="smaxnum" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

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
<select name='smaxcol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = OCT.get(0).getSmaxnum_os()+"";
		s2 = OCT.get(1).getSmaxnum_os()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<td id='smaxcol_os'>
<input type="text" name="smaxnum_os" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = OCT.get(0).getSmaxcol_os();
		v2 = OCT.get(1).getSmaxcol_os();
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
<select name='smaxcol_os'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>

	</tr>

	<tr>
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
<td>
<span id="imaxnum">Imax</span>
</td>
<td id='imaxcol'>
<input type="text" name="imaxnum" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

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
<select name='imaxcol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = OCT.get(0).getImaxnum_os()+"";
		s2 = OCT.get(1).getImaxnum_os()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<td id='imaxcol_os'>
<input type="text" name="imaxnum_os" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = OCT.get(0).getImaxcol_os();
		v2 = OCT.get(1).getImaxcol_os();
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
<select name='imaxcol_os'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>

	</tr>
	<tr>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = OCT.get(0).getSavgnum()+"";
		s2 = OCT.get(1).getSavgnum()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<td>
<span id="savgnum">Savg</span>
</td>
<td id='savgcol'>
<input type="text" name="savgnum" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = OCT.get(0).getSavgcol();
		v2 = OCT.get(1).getSavgcol();
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
<select name='savgcol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = OCT.get(0).getSavgnum_os()+"";
		s2 = OCT.get(1).getSavgnum_os()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<td id='savgcol_os'>
<input type="text" name="savgnum_os" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = OCT.get(0).getSavgcol_os();
		v2 = OCT.get(1).getSavgcol_os();
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
<select name='savgcol_os'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>

	</tr>
	<tr>
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
<td>
<span id="iavgnum">Iavg</span>
</td>
<td id='iavgcol'>
<input type="text" name="iavgnum" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

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
<select name='iavgcol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = OCT.get(0).getIavgnum_os()+"";
		s2 = OCT.get(1).getIavgnum_os()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<td id='iavgcol_os'>
<input type="text" name="iavgnum_os" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = OCT.get(0).getIavgcol_os();
		v2 = OCT.get(1).getIavgcol_os();
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
<select name='iavgcol_os'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>

	</tr>
	<tr>
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
<td>
<span id="atnum">Avg.Thick</span>
</td>
<td id='atcol'>
<input type="text" name="atnum" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

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
<select name='atcol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = OCT.get(0).getAtnum_os()+"";
		s2 = OCT.get(1).getAtnum_os()+"";
		if(s1.equals(s2)) {
			diff = false;
		}
		else {
			diff = true;
		}
	}
%>
<td id='atcol_os'>
<input type="text" name="atnum_os" class='bigNumBox' autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>

<%
	v1 = -1;
	v2 = -1;
	if(a) {
		v1 = OCT.get(0).getAtcol_os();
		v2 = OCT.get(1).getAtcol_os();
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
<select name='atcol_os'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>***</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>***</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>***</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>***</span><%}%></option>
</select>
</td>
	</tr>
</table>
<br>

<input type="submit" value="Submit" class="btn">

</form>

<%
}
%>

<script src="javascripts/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="javascripts/OCTChecks.js" type="text/javascript"></script>