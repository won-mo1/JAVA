<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function val(){
		
		if(document.getElementById("id").value==""){
			alert("아이디를 입력해주세요.");
			return false;
		}
	}
</script>
</head>
<body>
	<div>
		<form method="post" onsubmit="return val();">
			<table>
				<tr>
					<td class="td_one">아이디</td>
					<td class="td_two"><input type="text" name="id" id="id"></td>
				</tr>
				
				<tr>
					<td class="td_one">비밀번호</td>
					<td class="td_two"><input type="password" name="pwd" id="pwd"></td>
				</tr>
				
				<tr>
					<td class="td_one">이름</td>
					<td class="td_two"><input type="text" name="name" id="name"></td>
				</tr>
				
				<tr>
					<td class="td_one">연락처</td>
					<td class="td_two"><input type="text" name="pne" id="pne"></td>
				</tr>
			</table>
			<button type="submit">가입</button>
			<button type="button" onclick="location.href='/login.do'">취소</button>
		</form>
	</div>
</body>
</html>