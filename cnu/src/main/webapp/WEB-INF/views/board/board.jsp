<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	div {
		width: 70%;
		margin: 0 auto;
	}
	.header{
		text-align: right;
	}
	.body{
		margin-top: 40px;
	}
	.body table{
		width: 100%;
		text-align: center;
	}
	.paging_div{
		text-align: center;
	}
	.paging_div ul li{
		list-style: none;
		float: left;
		margin-left: 20px;
	}
</style>

</head>
<body>
	
	<div class="header">
		${sessionScope.memVo.name} 님 
		<button onclick="location.href='/cnu/logout.do'">로그아웃</button>
	</div>
	
<!-- 	이제 리퀘스트에 boardList가 담아져서 넘어왔음  -->
<!-- 	이 배열을 C태그를 사용해서 뿌려줄거임 
		<table> 안에 <tr>을 반복해주면됨
		-->
	<div class="body">
		
		<div style="float: left;">
			<button onclick="location.href='/cnu/boardInsert.do'">등록하기</button>
		</div>
	
	
		<div style="float: right;">
			<form>
				<select name="search">
					<option value="BOARD_TITLE">제목</option>
					<option value="WRITER">작성자</option>
				</select>
				<input type="text" name="searchKeyword" placeholder="검색어입력">
				<button type="submit">검색</button>
			</form>
		</div>
	
		<table border="1">
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<!--
				c태그 forEach문을 써서 var: 이름은 boardList로 선언하고 재료는 컨트롤러에서보낸 boardList 
				그러면 저배열의 길이만큼 tr이 반복됨. 
				<td> 내용 입력방법은 글번호면 : boardList.board_no 
									   var이름선언한것.매핑된 vo의 변수이름
			-->
			<c:forEach var="boardList" items="${boardList }">
				<tr>
					<td>${boardList.board_No}</td>
					<td>
<%-- 					<a href="/boardDetail.do?boardNo=${boardList.board_No}">${boardList.board_Title}</a> --%>
						<a href="/cnu/${boardList.board_No}/boardDetail.do">${boardList.board_Title}</a>
					</td>
					<td>${boardList.writer}</td>
					<td>${boardList.regdate}</td> 
					<!--
						현재 날짜가  Thu Nov 11 00:16:39 KST 2021 이렇게 출력이되는데
						el날짜 포맷하는 방법이있음 찾아서 2021.11.11   이런식으로 출력해보삼 
					-->
					<td>${boardList.hit}</td>
				</tr>
			</c:forEach>
			
			<!-- 
				이제 데이터가져온 리스트가 띄워졌을거임 그러면 상세조회를 해야하는데 
				제목을 클릭해서 이동을 하겠음 제목에 a태그를 걸어주고 주소를 boardDetail.do?boardNo=글번호  << 이런식으로 해줄거임	
				1.일단 상세조회 화면을 먼저 생성 boardDetail.jsp 로 생성
				여기중요
				2.글제목에 a태그 달아줌 링크는 boardDetail.do?boardNo=글번호 이런식으로 ( 위에 a태그있는데 태그위치보면서 천천히 분석해보셈 )
				3.BoardController에서 상세조회하는 메서드 생성 ( url은 boardDetail.do ) Controller로 이동
			-->
		</table>
		
		<div class="paging_div">
			<c:if test="${pagingVO.startPage != 1}">
				<a href="/cnu/board.do?nowPage=${pagingVO.endPage - 3}&search=${search}&searchKeyword=${searchKeyword}">이전</a>
			</c:if>
			
			<ul>
				<c:forEach var="page" begin="${pagingVO.startPage}" end="${pagingVO.endPage}" varStatus="status">
					<li><a href="/cnu/board.do?nowPage=${status.index}&search=${search}&searchKeyword=${searchKeyword}">${status.index}</a></li>
				</c:forEach>
			</ul>
			
			<c:if test="${pagingVO.endPage != pagingVO.lastPage}">
				<a href="/cnu/board.do?nowPage=${pagingVO.startPage + 3}&search=${search}&searchKeyword=${searchKeyword}">다음</a>
			</c:if>
		</div>
	</div>	
</body>
</html>















