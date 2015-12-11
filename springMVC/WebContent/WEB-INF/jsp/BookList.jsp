<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String nameSpace = request.getContextPath();%>
<%String projectPath = request.getScheme()+"://"
+request.getServerName()+":"+request.getServerPort()+nameSpace+"/"; 
%>

<!DOCTYPE HTML>
<html>
<head>
<title>Book List</title>
<style type="text/css">@import url("<c:url value="/css/main.css"/>");</style>
</head>
<body>

<div id="global">
<%=projectPath %>
<c:set value="<%=projectPath %>" var="basePath"/>
<h1>Book List</h1>
<a href="<c:url value="${basePath}book_input"/>">Add Book</a>
<table>
<tr>
    <th>Category</th>
    <th>Title</th>
    <th>ISBN</th>
    <th>Author</th>
    <th>&nbsp;</th>
</tr>
<c:forEach items="${books}" var="book">
    <tr>
        <td>${book.category.name}</td>
        <td>${book.title}</td>
        <td>${book.isbn}</td>
        <td>${book.author}</td>
        <td><a href="book_edit/${book.id}">Edit</a></td>
    </tr>
</c:forEach>
</table>
</div>
</body>
</html>
