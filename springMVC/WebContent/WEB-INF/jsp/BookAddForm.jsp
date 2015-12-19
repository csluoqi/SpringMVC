<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String nameSpace = request.getContextPath();%>
<%String projectPath = request.getScheme()+"://"
+request.getServerName()+":"+request.getServerPort()+nameSpace+"/"; 
%>
<c:set value="<%=projectPath %>" var="basePath"/>
<!DOCTYPE HTML>
<html>
<head>
<title>Add Book Form</title>
<style type="text/css">@import url("<c:url value="/css/main.css"/>");</style>
<script type="text/javascript" src="${basePath }js/jquery-1.7.2.js"></script>
<script type="text/javascript">
window.onload = function(){
	//alert("123");
	//var saveUrl = "http://localhost:8080/springMVC/savebook";
	//${basePath}book_save"
	var saveUrl = '${basePath}savebook';
	var i= 0;
	for(;i<5;i++)
		{
		sendSaveRequest(saveUrl);
		}
}

function sendSaveRequest (saveUrl){
	$.post(
			saveUrl,
			{"token":$("#token").val()},
			function()
			{
				return;
			},
			
			"text"
		);	
	
}
</script>
</head>
<body>

<div id="global">
<c:set value='<%=request.getSession().getAttribute("token")%>' var="token" />
<form:form commandName="book" action="${basePath}book_save" method="post">
    <fieldset>
        <legend>Add a book</legend>
        <p>
            <label for="category">Category: </label>
             <form:select id="category" path="category.id" 
                items="${categories}" itemLabel="name" 
                itemValue="id"/>
        </p>
        <p>
            <label for="title">Title: </label>
            <form:input id="title" path="title"/>
        </p>
        <p>
            <label for="author">Author: </label>
            <form:input id="author" path="author"/>
        </p>
        <p>
            <label for="isbn">ISBN: </label>
            <form:input id="isbn" path="isbn"/>
        </p>
        <p>
        <input name="token" id="token" value="${token }"/>
        </p>
        <p id="buttons">
            <input id="reset" type="reset" tabindex="4">
            <input id="submit" type="submit" tabindex="5" 
                value="Add Book">
        </p>
    </fieldset>
</form:form>
</div>
</body>
</html>
