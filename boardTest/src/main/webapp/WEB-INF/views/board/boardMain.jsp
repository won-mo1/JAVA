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
	<br>
	<br>
	<div style="float: left;">
		<button onclick="location.href='boardInsert.do'">등록하기</button>
	</div>
	<div style="float: right;">
		<form>
			<select name="searchFlag">
				<c:choose>
					<c:when test="${pagingVO.searchFlag == 'WRITER'}">
						<option value="BOARD_TITLE">제목</option>
						<option value="WRITER" selected="selected">작성자</option>
					</c:when>
					<c:otherwise>
						<option value="BOARD_TITLE">제목</option>
						<option value="WRITER">작성자</option>
					</c:otherwise>
				</c:choose>
				
			</select>
			<input type="text" name="searchKeyword" value="${pagingVO.searchKeyword}"><button type="submit">검색</button>
		</form>
	</div>
	<br>
	<br>
	<table border="1" style="width: 100%; text-align: center;">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${boardList }" var="boardList">
			<tr>
				<td>${boardList.boardNo }</td>
				<td><a href="#">${boardList.boardTitle }</a></td>
				<td>${boardList.writer }</td>
				<td>
					<fmt:formatDate value="${boardList.regdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>${boardList.hit }</td>
			</tr>
		</c:forEach>
	</table>

	<br>
	
	<table style="width: 30%; margin: 0 auto; text-align: center; cursor: pointer;" border="1">
		<tr>
			<c:if test="${pagingVO.startPage != 1}">
				<td onclick="location.href='/board/main.do?nowPage=${status.index-5}'">&lt;&lt;이전</td>
			</c:if>
			<c:forEach begin="${pagingVO.startPage}" end="${pagingVO.endPage}" varStatus="status">
				<td onclick="location.href='/board/main.do?nowPage=${status.index}&searchFlag=${pagingVO.searchFlag}&searchKeyword=${pagingVO.searchKeyword}'">${status.index}</td>
			</c:forEach>
			
			<c:if test="${pagingVO.endPage != pagingVO.lastPage}">
				<td onclick="location.href='/board/main.do?nowPage=${status.index+5}'">다음&gt;&gt;</td>
			</c:if>
		</tr>
	</table>	
</body>
</html>