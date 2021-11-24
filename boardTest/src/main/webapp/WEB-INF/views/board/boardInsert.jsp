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
	<br />
	<br />
	<form method="post" enctype="multipart/form-data">
		<table style="margin: 0 auto;">
			<tr>
				<th>제목</th>
				<td><input type="text" name="boardTitle"></td>
			</tr>
			
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer"></td>
			</tr>
			
			<tr>
				<th>내용</th>
				<td><input type="text" name="content"></td>
			</tr>
			
			<tr>
				<th>첨부파일</th>
				<td><input type="file" name="atchFile" multiple="multiple"></td>
			</tr>
			
			<tr>
				<td>
				</td>
	
				<td>
					<br/>
					<button type="submit">등록</button>
					<button type="button">목록</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>