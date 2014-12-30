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
<%if((a&&diff)&&((v1==5)||(v2==5))){%><span class='highlight'>*</span><%}%>
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
<span id="sig">(OD) Signal Strength (Max 10)</span>
<input type="text" name="sig" autocomplete="off"<%if(a&&!diff){out.print(" value='"+s1+"'");}%>>
<%if(a&&diff){out.print("<span class='highlight'> "+s1+" | "+s2+"</span>");}%>
<br><br>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getSig_os()+"";
		s2 = oct.getSig_os()+"";
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
<select name='scol' >
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
		</td>
		<td id='scol_os'>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getSnum_os()+"";
		s2 = oct.getSnum_os()+"";
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
		v1 = oct.getScol_os();
		v2 = oct.getScol_os();
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
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
		</td>
	</tr>
	<tr>
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
<select name='ncol'>
	<option value='0'></option>
	<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
	<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
	<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
	<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getNnum_os()+"";
		s2 = oct.getNnum_os()+"";
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
		v1 = oct.getNcol_os();
		v2 = oct.getNcol_os();
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
	<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
	<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
	<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
	<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>
	</tr>
	<tr>
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
<select name='icol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getInum_os()+"";
		s2 = oct.getInum_os()+"";
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
		v1 = oct.getIcol_os();
		v2 = oct.getIcol_os();
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
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>

	<tr>
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
<select name='tcol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getTnum_os()+"";
		s2 = oct.getTnum_os()+"";
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
		v1 = oct.getTcol_os();
		v2 = oct.getTcol_os();
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
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>

	<tr>

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
<select name='iscol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>
<td id='iscol_os'>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getIsnum_os()+"";
		s2 = oct.getIsnum_os()+"";
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
		v1 = oct.getIscol_os();
		v2 = oct.getIscol_os();
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
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>
	<tr>
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
<select name='sicol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getSinum_os()+"";
		s2 = oct.getSinum_os()+"";
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
		v1 = oct.getSicol_os();
		v2 = oct.getSicol_os();
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
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>
	</tr>
	<tr>
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
<select name='stcol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getStnum_os()+"";
		s2 = oct.getStnum_os()+"";
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
		v1 = oct.getStcol_os();
		v2 = oct.getStcol_os();
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
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>
	</tr>

	<tr>
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
<select name='itcol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getItnum_os()+"";
		s2 = oct.getItnum_os()+"";
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
		v1 = oct.getItcol_os();
		v2 = oct.getItcol_os();
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
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>

	</tr>
	<tr>
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
<select name='sncol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getSnnum_os()+"";
		s2 = oct.getSnnum_os()+"";
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
		v1 = oct.getSncol_os();
		v2 = oct.getSncol_os();
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
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>

	</tr>

	<tr>
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
<select name='mmcol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getMmnum_os()+"";
		s2 = oct.getMmnum_os()+"";
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
		v1 = oct.getMmcol_os();
		v2 = oct.getMmcol_os();
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
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>

	</tr>
	<tr>
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
<select name='smaxcol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getSmaxnum_os()+"";
		s2 = oct.getSmaxnum_os()+"";
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
		v1 = oct.getSmaxcol_os();
		v2 = oct.getSmaxcol_os();
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
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>

	</tr>

	<tr>
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
<select name='imaxcol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getImaxnum_os()+"";
		s2 = oct.getImaxnum_os()+"";
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
		v1 = oct.getImaxcol_os();
		v2 = oct.getImaxcol_os();
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
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>

	</tr>
	<tr>
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
<select name='savgcol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getSavgnum_os()+"";
		s2 = oct.getSavgnum_os()+"";
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
		v1 = oct.getSavgcol_os();
		v2 = oct.getSavgcol_os();
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
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>

	</tr>
	<tr>
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
<select name='iavgcol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getIavgnum_os()+"";
		s2 = oct.getIavgnum_os()+"";
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
		v1 = oct.getIavgcol_os();
		v2 = oct.getIavgcol_os();
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
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>

	</tr>
	<tr>
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
<select name='atcol'>
<option value='0'></option>
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>
<%
	s1 = "";
	s2 = "";
	if(a) {
		s1 = oct.getAtnum_os()+"";
		s2 = oct.getAtnum_os()+"";
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
		v1 = oct.getAtcol_os();
		v2 = oct.getAtcol_os();
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
<option value="1"<%if(a && !diff && v1==1){out.print(" selected='selected'");}%>>White<%if((a&&diff)&&((v1==1)||(v2==1))){%><span class='highlight'>*</span><%}%></option>
<option value="2"<%if(a && !diff && v1==2){out.print(" selected='selected'");}%>>Green<%if((a&&diff)&&((v1==2)||(v2==2))){%><span class='highlight'>*</span><%}%></option>
<option value="3"<%if(a && !diff && v1==3){out.print(" selected='selected'");}%>>Yellow<%if((a&&diff)&&((v1==3)||(v2==3))){%><span class='highlight'>*</span><%}%></option>
<option value="4"<%if(a && !diff && v1==4){out.print(" selected='selected'");}%>>Red<%if((a&&diff)&&((v1==4)||(v2==4))){%><span class='highlight'>*</span><%}%></option>
</select>
</td>
	</tr>
</table>
<br>

<span id='savg_snum' class="error hidden">OD Savg and S numbers must match<br></span>
<span id='iavg_inum' class="error hidden">OD Iavg and I numbers must match<br></span>
<span id='savg_snum_os' class="error hidden">OS Savg and S numbers must match<br></span>
<span id='iavg_inum_os' class="error hidden">OS Iavg and I numbers must match<br></span>
<br>

<input type="submit" value="Submit" class="btn">

</form>

<%
}
%>

<script src="javascripts/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="javascripts/OCTChecks.js" type="text/javascript"></script>