<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*,com.opensymphony.xwork2.util.*"%> 
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>地震科普知识</title>
</head>
<body>
	<table>
	<tr>
		<td align = "center" width="500">
			<img src="<s:property value="knowledge.imageName"/>" width="1000"/>
		</td>
	</tr>
	<tr>
		<td align = "center">
			<font size="10">
			<B>
				<s:property value="knowledge.title"/>
			</B>
			</font>
		</td>
	</tr>
	<tr>
		<td align="left">
			<font size="10">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<s:property value="knowledge.summary"/>
				</br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<font size="6">
				<s:property value="knowledge.content" escape="false" />
				</font>
			</font>
		</td>
	</tr>
	</table>
</body>
</html>