<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*,com.opensymphony.xwork2.util.*"%> 
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>地震信息</title>
</head>
<body>
	<table>
	<tr>
		<td align="center">
			<img src="<s:property value="tabMapsDetail.imageName"/>"/>
		</td>
	</tr>
	<tr>
		<td align="left">
			<font size="10">
				<B>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<s:property value="tabMapsDetail.time"/>&nbsp;
				<s:property value="tabMapsDetail.address"/>(
					<s:property value="tabMapsDetail.longitude"/>,
					<s:property value="tabMapsDetail.latitude"/>
					)震级：<s:property value="tabMapsDetail.magnitude"/>
					级深度：<s:property value="tabMapsDetail.depth"/>公里  
				</B> 
			</font>
		</td>
	</tr>
	</table>
</body>
</html>