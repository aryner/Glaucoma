<%-- 
    Document   : register
    Created on : Jun 13, 2014, 2:20:07 PM
    Author     : alexanderryner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2>Register</h2>

<p><a href="index.jsp">Already registered?</a></p>

	<%
		if(session.getAttribute("error") != null) {
			out.print("<p style='color:red'>" + session.getAttribute("error") + "</p>");
			session.removeAttribute("error");
		}
	%>

<form action="createUser" method="Post">
	<p>
		User name: <input type="text" name="userName">
	</p>

	<p>
		Password: <input type="password" name="password">
	</p>
	<p>
		Repeat password: <input type="password" name="rePassword">
	</p>
	<p>
		Select which type of grader you are:
		<select name="graderType" class="btn bigBtn">
			<option value="0">Initial Grader</option>
			<option value="1">Adjudicator</option>
			<option value="2">Ophthalmologist</option>
		</select>
	</p>
<!--
	<p id="opthChosen" class="invis">
		If you had an old ID enter it here, if not leave it blank: <input type="text" name="oldID">
	</p>
 -->
	<p>
		<input type="submit" value="submit" class="btn">
	</p>
</form>

<script src="javascripts/jquery-1.11.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="javascripts/register.js"></script>