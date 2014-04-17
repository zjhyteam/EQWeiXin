<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="java.util.List" %>
<%@ page import="org.earthQuake.course.common.FileHelper" %>
<%@ page import="org.springframework.context.support.FileSystemXmlApplicationContext" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.earthQuake.course.common.PubCode" %>
<%@ page import="org.earthQuake.course.service.CommonService" %>
<%@ page import="java.util.Map" %>
<%@ page import="net.sf.json.JSONObject" %>
<%--
  Created by IntelliJ IDEA.
  User: jack
  Date: 13-8-19
  Time: 上午10:20
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java"  contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
	ApplicationContext act=new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
	CommonService commonService=(CommonService)act.getBean("commonService");
    DiskFileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);
    List<FileItem> fileitems = upload.parseRequest(request);
    FileHelper fh=new FileHelper();
    ServletContext context = request.getServletContext();
    String filedir = commonService.getCodeValue(PubCode.knowledgeImage);
    //String filedir = "images/groupsend"; 
	String filepath=context.getRealPath(filedir);
    Map<String,Object> result=fh.saveUploadFile(fileitems, filepath);
    result.put("filepath",filedir + result.get("filepath"));
    out.print(JSONObject.fromObject(result).toString());

%>