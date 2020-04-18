<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<!--
파일명 : rent.jsp
작성자명 : 문지훈,황효준,김수민
프로젝트명 : library
기능설명 : 책 대여 페이지
  -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Library.rent</title>
</head>
<body>

	<header>
		<article class="container">
			<div class="col-md-6 col-md-offset-5">
				<br> <br> <b><font size="6" color="gray"><a
						href="main.jsp" style="text-decoration: none;">산학도서관</a></font></b> <br>
				<br>
				<br>
			</div>
		</article>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">책 대여</a>
				</div>
			</div>
		</nav>
	</header>
	<div>
		<table class="table">
			<tr align="center">
				<td>번호</td>
				<td>제목</td>
				<td>저자</td>
				<td>출판사</td>
				<td>장르</td>
				<td>개수</td>
				<td>대여(유,무)</td>
			</tr>
			<c:forEach var="b" items="${book}">

				<tr align="center">

					<td>${b.isbn}</td>
					<td>${b.bname}</td>
					<td>${b.author}</td>
					<td>${b.publisher}</td>
					<td>${b.genre}</td>
					<td>${b.count}</td>
					<td><c:forEach var="r" items="${rentlog}">
							<c:if test="${b.isbn eq r.isbn}">
								<form method="post"
									action="rent.do?isbn=${b.isbn}&mno=${sessionScope.mdto.mno}&reservation=1">
									<input type="submit" class="btn btn-warning" value="반납" />
								</form>
							</c:if>
						</c:forEach> <c:if test="${b.count > 0}">
							<form method="post"
								action="rent.do?isbn=${b.isbn}&mno=${sessionScope.mdto.mno}&reservation=0">
								<input name="de" type="submit" class="btn btn-primary"
									value="대여" />
							</form>
						</c:if> <c:if test="${b.count <= 0}">
							<button class="btn btn-danger">대여 불가</button>
						</c:if></td>
				</tr>

			</c:forEach>
		</table>
	</div>
	<div class="col-md-6 col-md-offset-5">
		<form method="post"
			action="rent.do?sfunc=1&mno=${sessionScope.mdto.mno}">
			<select class="form-control" style="width: 100px; float: left;"
				name="category">
				<option value=0>제목</option>
				<option value=1>저자</option>
				<option value=2>출판사</option>
				<option value=3>장르</option>
			</select> <input class="form-control" style="width: 100px; float: left;"
				type="text" name="search"> <input class="btn btn-success"
				type="submit" value="검색" />
		</form>
	</div>


</body>
</html>