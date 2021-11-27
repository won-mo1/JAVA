<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form method="post" enctype="multipart/form-data">
		<input type="file" name="upload"> <br/>
		<button type="submit">등록</button> <br/>
	</form>
	<br/><br/><hr/>
	<c:forEach items="${fileList}" var="list">
		<a href="/board/downTest.do">${list.ATCH_FILE_NM}</a> <br/>
	</c:forEach>
	
</body>
</html>