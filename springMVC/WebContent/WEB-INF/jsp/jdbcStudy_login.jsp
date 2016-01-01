<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String nameSpace = request.getContextPath();%>
<%String projectPath = request.getScheme()+"://"
+request.getServerName()+":"+request.getServerPort()+nameSpace+"/"; 
%>
<c:set value="<%=projectPath %>" var="basePath"/>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
<form:form commandName="user" action="${basePath }loginAction" method="post">
	<%--userName --%>
	<p>
		<label for="name">userName</label>
		<form:input id="name" path="name"/>
	</p>
	<%--password --%>
	<p>
		<label for="password">userName</label>
		<form:input id="password" path="password"/>
	</p>
	<p>
		<input id="submit" type="submit" value="Login">
	</p>
</form:form>



</body>
</html>