<%-- 
    Document   : upload
    Created on : Sep 11, 2014, 4:50:55 PM
    Author     : aryner
--%>
<%@page import="java.util.*"%>

<h3>Upload pictures to be graded</h3>

<form action='uploadPictures' method="POST" enctype="multipart/form-data">
	Choose which section to add to
	<select name="type" class="btn bigBtn"> 
		<option value="HVF">HVF</option>
<!--		<option value="grading">Grading Chart</option> -->
<!--		<option value="severity">Glaucoma Severity Chart</option> -->
		<option value="FDT">FDT</option>
		<option value="MDT">MDT</option>
		<option value="OCT">OCT</option>
		<option value="stereo">Stereo</option>
		<option value="3Nethra">3Nethra</option> 

	</select>
	<p>Select which pictures to upload</p>
	<input type="file" multiple="multiple" name="pictures">
	<input type="submit" value="Submit" class="btn bigBtn">
</form>

<div class="container">
	<h3>Missing Images</h3>
	<!--
	<div class="fifth-column">
		<h4>HVF</h4>
<%
		ArrayList<String> needPics = (ArrayList)request.getAttribute("hvfNeedPictures");

		for(int i=0; i<needPics.size(); i++) {
			out.print(needPics.get(i)+"<br>");
		}
%>
	</div>
 	-->
</div>