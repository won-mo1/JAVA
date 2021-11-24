<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" enctype="multipart/form-data">
		제목 : <input type="text" name="boardTitle">  <br>
		작성자 : <input type="text" name="writer">		<br>
		내용 : <input type="text" name="content">		<br>
		<input type="file" name="uploadFile" multiple="multiple">	<br>	
		
		<button type="submit">등록</button>
	</form>
	
<!-- 	<form action="/cnu/boardInsert.do" method="post" enctype="multipart/form-data"> -->
<!-- 		<input type="file" multiple="multiple" name="uploadFile"> <button type="submit">등록</button> -->
<!-- 	</form> -->
</body>
</html>