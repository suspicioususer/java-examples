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
	
	<html:form action="/main">
	<html:hidden property="page" value="2"/>
	
	<logic:present name="dataList">
		<logic:iterate name="dataList" id="valueModel">
			<bean:define id="userModel" name="valueModel" property="key" />
			<ul>
				<li>
					<table border="1">
						<tr>
							<th><bean:write name="userModel" property="ID" /></th>
							<th><bean:write name="userModel" property="userName" /></th>
							<th><bean:write name="userModel" property="password" /></th>
						</tr>
					</table>
				</li>
				<bean:define id="accountList" name="valueModel" property="value" />
				<logic:iterate name="accountList" id="accountModel">
					<ul>
						<li>
							<table style="table-layout: fixed; width:200px" border="1">
								<tr>
									<td style="overflow: hidden; text-align:center">
										<bean:write name="accountModel" property="ID" />
									</td>
									<td style="overflow: hidden; text-align:center">
										<bean:write name="accountModel" property="balance" />
									</td>
								</tr>
							</table>
						</li>
					</ul>
				</logic:iterate>
			</ul>
		</logic:iterate>
	</logic:present>
	<logic:notPresent name="dataList">
		<h3>Data not found!</h3>
	</logic:notPresent>
	
	<html:submit value="Change Page"/>
</html:form>
</body>
</html>