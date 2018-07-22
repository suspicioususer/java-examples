<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html>
<head>
</head>
<body>
	<h1>
		<bean:write name="mainForm" property="message" />
	</h1>

	<logic:present name="userList">
		<table>
			<logic:iterate name="userList" id="model">
				<tr>
					<td>
						<bean:write name="model" property="ID" />
					</td>
					<td>
						<bean:write name="model" property="userName"/>
					</td>
					<td>
						<bean:write name="model" property="password"/>
					</td>
				</tr>
			</logic:iterate>
		</table>
	</logic:present>
	<logic:notPresent name="userList">
		<h3>Data not found!</h3>
	</logic:notPresent>

</body>
</html>