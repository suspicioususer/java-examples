<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>Register</title>
</head>
<body>
	<h1>Register</h1>
	<p>Here you can register.</p>
	<form:form method="POST" commandName="user" action="${pageContext.request.contextPath}/login/pRegister.html">
	<table>
	<tbody>
		<tr>
			<td>Username: </td>
			<td><form:input path="userName" /></td>
		</tr>
		<tr>
			<td>Password: </td>
			<td><form:password path="password" /></td>
		</tr>
		<tr>
			<a href="${pageContext.request.contextPath}/login/">Main Page</a><br /> 
			<td><input type="submit" value="Register" /></td>
		</tr>
	</tbody>
	</table>
	</form:form>

</body>
</html>