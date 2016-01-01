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
<title>bookList</title>
</head>
<body>
<h2>Book List</h2>
<a href="#">Add Book</a>
<table>
<tr>
<th>Name</th>
<th>Author</th>
<th>Price</th>
<th>Info</th>
<th>Amount</th>
<th>Operating</th>
<th></th>
</tr>

<c:forEach items="${bookList}" var="book">
	<tr>
		<td>${book.name}</td>
		<td>${book.author}</td>
		<td>${book.price}</td>
		<td>${book.info}</td>
		<td>${book.amount}</td>
		<td><a href="#">delete</a></td>
		<td><a href="#">update</a></td>
	</tr>
</c:forEach>
</table>


</body>
</html>