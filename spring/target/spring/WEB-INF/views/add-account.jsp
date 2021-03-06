<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>Add Account</title>
</head>
<body>
	<h1>Add Account</h1>
	<p>Here you can add a new account.</p>
	<form:form method="POST" commandName="account" action="${pageContext.request.contextPath}/account/add">
	<table>
	<tbody>
		<tr>
			<td>Balance: </td>
			<td><form:input path="balance" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="Add" /></td>
		</tr>
	</tbody>
	</table>
	</form:form>
	
	<p><a href="${pageContext.request.contextPath}/account/">Accounts page</a></p>
</body>
</html>