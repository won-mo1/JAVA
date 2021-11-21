<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.div_wrap{
		margin: 0 auto;
		width: 300px;
		text-align: center;
		margin-top: 20%;
	}
	.div_wrap button{
		margin-top: 10px;
		width: 150px;
	}
	.div_wrap input{
		width: 300px;
		height: 30px;
		margin-top: 10px;
	}
</style>

<script type="text/javascript">
	if('${requestScope.result}'=='idFail'){
		alert("아이디를 확인하세요.");
	}
	
	if('${requestScope.result}'=='pwdFail'){
		alert("비밀번호를 확인하세요.");
	}
</script>

</head>
<body>
	<div class="div_wrap">
		<form method="post">
			<input type="text" placeholder="아이디를 입력하세요." name="id" id="id"> <br />
			<input type="password" placeholder="비밀번호를 입력하세요." name="pwd" id="pwd"> <br />
			<button type="submit">로그인</button> <br />
		</form>
		<button type="button" onclick="location.href='/join.do'">회원가입</button>
	</div>
</body>
</html>





