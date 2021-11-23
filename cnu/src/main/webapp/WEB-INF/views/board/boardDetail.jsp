<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
	table{
		width: 70%;
		margin: 0 auto;
		text-align: center;
	}
</style>
<script type="text/javascript">
	// 작성자 : ${sessionScope.memVo.name} 
	// 내용 : $("#comment").val();
	function comment(){
		
		$.ajax({  
			type : "post"
			,url : "/cnu/commentInsert.do"
			,data : { comment : $("#comment").val() 
					, writer : "${sessionScope.memVo.name}"
					, boardNo : "${boardVo.board_No}" } 
			,success : function(data){
				//성공했을때 실행할 함수
				if(data=="ok"){
					
					var str = "<tr>"
								+"<td>${sessionScope.memVo.name}</td>"
								+"<td>"+$("#comment").val()+"</td>"
								+"<td>날짜</td>"
								+"<td>삭제</td>"
								+"</tr>";
					$("#commentTable").append(str);
					
					$("#comment").val("");
					
				}
			}
			,error : function(xhr){
				//에러날때 실행할 함수
			}
		});
		
	}
	
	function commentDelete(cmtNo){
		var delConfirm = confirm('삭제하시겠습니까?');
		if(delConfirm){
			$.ajax({
				url : "/cnu/commentDelete.do"
				, type : "post"
				, data : { commentNo : cmtNo }
				, success : function(data){
					if(data=="ok"){
						location.reload();
					}
				}
				
			});	
		}
		
	}
</script>
</head>
<body>
	<!-- model로 뿌려준것은 el 표기법을 사용해서 쉽게뿌리면됨 -->
	<table border="1">
		<tr>
			<th>글 번호</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<tr>
			<td>${boardVo.board_No}</td>
			<td>${boardVo.writer}</td>
			<td>${boardVo.regdate}</td>
			<td>${boardVo.hit}</td>
		</tr>
		
		<tr>
			<td colspan="4">=========================내용=========================</td>
		</tr>
		
		<tr>
			<td colspan="4">${boardVo.content}</td>
		</tr>
	</table>
	<!-- 일케하면 상세조회 완료 수정은 담에 같이하고 삭제 혼자해봄 -->
	<br/>
	<br/>
	<hr>
	<br/>
	<br/>
	<table border="1" id="commentTable">
		<tr>
			<th>작성자</th>
			<th>댓글내용</th>
			<th>작성일</th>
			<th>삭제</th>
		</tr>
		<c:forEach items="${commentList}" var="commentList">
			<tr>
				<td>${commentList.WRITER }</td>
				<td>${commentList.CONTENT }</td>
				<td>${commentList.REGDATE }</td>
				<td><button onclick="commentDelete('${commentList.COMMENT_NO}')">삭제</button></td>
			</tr>
		</c:forEach>
	</table>
	
	<table border="1">
		<tr>
			<td>
				<input type="text" style="width: 90%;" id="comment">
			</td>
			<td>
				<button type="button" onclick="comment()">등록</button>
			</td>
		</tr>
	</table>
</body>
</html>