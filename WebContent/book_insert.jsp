<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--
파일명 : join.jsp
작성자명 : 문지훈,황효준,김수민
프로젝트명 : library
기능설명 : 회원가입 페이지
  -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>

<body>


	<article class="container">
	<div class="col-md-6 col-md-offset-5">
		<br> <br> <b><font size="6" color="gray"><a
				href="main.jsp" style="text-decoration: none;">산학도서관</a></font></b> <br> <br>
		<br>
	</div>
	</article>



	<!-- 입력한 값을 전송하기 위해 form 태그를 사용한다 -->
	<!-- 값(파라미터) 전송은 POST 방식, 전송할 페이지는 JoinPro.jsp -->
	<article class="container">
	<div class="page-header">
		<div class="col-md-6 col-md-offset-3" align='center'>
			<h3>도서 추가</h3>
		</div>
	</div>
	<div class="col-sm-6 col-md-offset-3">
		<form method="post" action="book_management.do?insert=1"
			onsubmit="return checkValue()" id="form1" name="form">
			<div class="form-group">
				<label>번호</label> <input type="text" name="isbn"
					class="form-control" required>
			</div>
			<div class="form-group">
				<label>제목</label> <input type="text" name="bname"
					class="form-control" required>
			</div>
			<div class="form-group">
				<label>저자</label> <input type="text" name="author"
					class="form-control" required>
			</div>
			<div class="form-group">
				<label>출판사</label> <input type="text" name="publisher"
					class="form-control" required>
			</div>
			<div class="form-group">
				<label>장르</label> <input type="text" name="genre"
					class="form-control" required>
			</div>
			<div class="form-group">
				<label>개수</label> <input type="text" name="count"
					class="form-control" required>
			</div>

			<div class="form-group text-center">
				<button type="submit" id="join-submit" class="btn btn-primary">도서
					추가</button>
				<button type="reset" class="btn btn-warning">취소</button>
				<button type="button" class="btn btn-success"
					onclick="window.history.back()">이전</button>
			</div>
		</form>
	</div>

	</article>
	</div>

</body>
</html>