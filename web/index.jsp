<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>

<h2>Log in</h2>

<%
	if(session.getAttribute("error") != null){
		out.print("<p style='color:red;'>Incorrect password or user name");
		session.removeAttribute("error");
	}
	if(session.getAttribute("userName") != null) {
		response.sendRedirect("/GlaucomGrader/home"); 
	}
%>

<form action="login" method="POST">
	<p>User name: 
		<input type="text" name="userName">
	</p>
	<p>Password: 
		<input type="password" name="password">
	</p>
	<p>
		<input type="submit" value="Log in" class="btn">
	</p>
</form>

<a href="register">Register</a>