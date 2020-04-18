<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<!--
파일명 : member_list.jsp
작성자명 : 문지훈,황효준,김수민
프로젝트명 : library
기능설명 : 관리자 회원관리 페이지
  -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<script>
		function deleteForm(url) {
			var message = confirm("정말로 삭제하시겠습니까?");
			if (message == true) {
				var form1 = document.getElementById("form1");
				form1.setAttribute("action", url);
				form1.submit();
			} else
				return false;

		}
		function updateForm(url) {

			var form1 = document.getElementById("form1");
			form1.setAttribute("action", url);
			form1.submit();

		}
	</script>
	<article class="container">
		<div class="col-md-6 col-md-offset-5">
			<br> <br> <b><font size="6" color="gray"><a
					href="main.jsp" style="text-decoration: none;">산학도서관</a></font></b> <br>
		</div>
	</article>
	<article class="container">
		<form method="post" id="form1">
			<div class="page-header">
				<div class="col-md-6 col-md-offset-3" align='center'>
					<h3>도서 목록</h3>
				</div>
			</div>

			<div id="content">
				<div align="right">
					${sessionScope.adto.aid } 관리자님 반갑습니다. <br> <br>
				</div>
				<c:if test="${book ne null}">

					<div>
						<table id="mytable" class="table table-bordred table-striped"
							align="center">

							<thead>

								<th>번호</th>
								<th>제목</th>
								<th>저자</th>
								<th>출판사</th>
								<th>장르</th>
								<th>개수</th>
								<th>Edit</th>
								<th>Delete</th>
							</thead>

							<tbody>
								<c:forEach var="b" items="${book}">
									<tr>

										<td>${b.isbn}</td>
										<td>${b.bname}</td>
										<td>${b.author}</td>
										<td>${b.publisher}</td>
										<td>${b.genre}</td>
										<td>${b.count}</td>
										<td><p data-placement="top" data-toggle="tooltip"
												title="Edit">
												<button class="btn btn-primary btn-xs" data-title="Edit"
													data-toggle="modal" data-target="#edit"
													onclick="updateForm('book_update.do?isbn=${b.isbn}')">
													<span class="glyphicon glyphicon-pencil"></span>
												</button>
											</p></td>
										<td><p data-placement="top" data-toggle="tooltip"
												title="Delete">
												<button class="btn btn-danger btn-xs" data-title="Delete"
													data-toggle="modal" data-target="#delete"
													onclick="deleteForm('book_delete.do?isbn=${b.isbn}')">
													<span class="glyphicon glyphicon-trash"></span>
												</button>
											</p></td>
									</tr>
								</c:forEach>
						</table>

					</div>

				</c:if>
			</div>
		</form>
	</article>
	<div align='center'>
		<button class="btn btn-warning"
			onclick="location.href = 'book_insert.jsp' ">추가하기</button>
	</div>
	<div class="col-md-6 col-md-offset-5">
		<form method="post" action="book_management.do?sfunc=1">
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