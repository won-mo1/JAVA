<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style type="text/css">
	table tr td {
		padding: 15px;
	}
	table tr th{
		padding: 15px;
	}
	table{
		border-collapse: collapse;
	}
	#filetr{
		cursor: pointer;
	}
	#filetr:hover {
		text-decoration: underline;
	}
	.cmtTr{
		border-bottom: 1px solid black;
	}
</style>

<script type="text/javascript">
	function fileDown(atchFileNo, sn){
		
	}
	
	function comment(){
		
		$.ajax({
			
			url : "/board/commentInsert.do"
			, type : "post"
			, data : $("#frm").serialize()
			, dataType: 'json'
			, success : function(data){
				var comtNo = "'" + data.commentNo + "'"
				console.log(data);
				var str = '<tr id="'+data.commentNo+'" class="cmtTr">'
								+'<td>'+ data.writer +'</td>'
								+'<td>'+ data.content +'</td>'
								+'<td><button onclick="cmtDelete('+comtNo+')">삭제</button></td>'
								+'<td>'+ data.regdate +'</td>'
						 +'</tr>';
				$("#cmtTable").append(str);
				$(".cmtInput").val("");
			}
			
		});
	}
	
	function cmtDelete(cmtNo){
		var con = confirm("삭제하시겠습니까?");
		if(con){
			$.ajax({
				url : "/board/cmtDelete.do"
				, type : "post"
				, data : { cmtNo : cmtNo }
				, success : function(data){
					
					if(data=="ok"){
						$("#"+cmtNo).remove();
					}
					
				}
			});
		}
	}
	
</script>

</head>
<body>
	<br>
	<br>
	<div style="margin: 0 auto; width: 20%; text-align: center;">
		<button>수정</button>
		<button>삭제</button>
	</div>
	<br>
	<table border="1" style="margin: 0 auto; text-align: center;">
		<tr>
			<th colspan="2">제목</th>
			<td colspan="2">${boardVO.boardTitle }</td>
		</tr>
		<tr>
			<th colspan="2">작성자</th>
			<td colspan="2">${boardVO.writer }</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${boardVO.hit }</td>
			<th>작성일</th>
			<td><fmt:formatDate value="${boardVO.regdate }" pattern="yyyy-MM-dd"/></td>
		</tr>
		<tr>
			<th colspan="2">내용</th>
			<td colspan="2">${boardVO.content }</td>
		</tr>
		<tr>
			<th colspan="2">첨부파일</th>
			<td colspan="2">
				<c:forEach var="file" items="${fileList}">
					<p  id="filetr" onclick="fileDown('${file.atchFileNo}', '${file.sn}')">
						${file.orignAtchFileNm}
					</p>
				</c:forEach>
			</td>
		</tr>
	</table>

	
	<div style="width: 40%; margin: 0 auto;">
		<hr />
		댓글
		<hr>
		
		<table style="width: 100%; text-align: center;" id="cmtTable">
			<tr style="border-bottom: 1px solid black;">
				<th style="width: 15%;">작성자</th>
				<th style="width: 60%;">내용</th>
				<th style="width: 10%;"></th>
				<th style="width: 15%;">작성일</th>
			</tr>
			<c:forEach items="${commentList}" var="list">
				<tr id="${list.COMMENT_NO}" class="cmtTr">
					<td>${list.WRITER}</td>
					<td>${list.CONTENT}</td>
					<td><button onclick="cmtDelete('${list.COMMENT_NO}')">삭제</button></td>
					<td>
						<fmt:formatDate value="${list.REGDATE}" pattern="yyyy-MM-dd hh:mm:ss"/>
					</td>
				</tr>
			</c:forEach>
		</table>
		<form id="frm">
			<input type="hidden" name="boardNo" value="${boardVO.boardNo }">
			<table style="width: 100%; text-align: center;">
				<tr>
					<th style="width: 15%;"><input type="text" name="writer" placeholder="작성자입력" style="width: 100%;" class="cmtInput"></th>
					<th style="width: 70%;"><input type="text" name="content" placeholder="내용입력" style="width: 100%;" class="cmtInput"></th>
					<th style="width: 15%;">
						<button type="button" onclick="comment()">댓글등록</button>
					</th>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>