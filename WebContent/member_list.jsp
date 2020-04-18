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
		function submitForm(url) {
			var message = confirm("정말로 삭제하시겠습니까?");
			if (message == true) {
				var form1 = document.getElementById("form1");
				form1.setAttribute("action", url);
				form1.submit();
			} else
				return false;
		}
		function submitForm2(url) {

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
	<form method="post" id="form1">
		<div class="page-header">
			<div class="col-md-6 col-md-offset-3" align='center'>
				<h3>회원목록</h3>
			</div>
		</div>

		<div id="content">
			<div align="right">
				${sessionScope.adto.aid } 관리자님 반갑습니다. <br> <br>
			</div>
			<c:if test="${requestScope.memberList ne null}">

				<div>
					<table id="mytable" class="table table-bordred table-striped"
						align="center">

						<thead>


							<th>아이디</th>
							<th>이름</th>
							<th>생년월일</th>
							<th>이메일</th>
							<th>전화번호</th>
							<th>Edit</th>
							<th>Delete</th>
						</thead>

						<tbody>
							<c:forEach var="member" items="${requestScope.memberList }">
								<tr>

									<td>${member.mid }</td>
									<td>${member.mname}</td>
									<td>${member.mbirth}</td>
									<td>${member.memail}</td>
									<td>${member.mtel}</td>

									<td><p data-placement="top" data-toggle="tooltip"
											title="Edit">
											<button type="submit" class="btn btn-primary btn-xs"
												data-title="Edit" data-toggle="modal" data-target="#edit"
												onclick="submitForm2('admin_member_update.do?id=${member.mid}')">
												<span class="glyphicon glyphicon-pencil"></span>
											</button>
										</p></td>
									<td><p data-placement="top" data-toggle="tooltip"
											title="Delete">
											<button class="btn btn-danger btn-xs" data-title="Delete"
												data-toggle="modal" data-target="#delete"
												onclick="submitForm('member_delete.do?id=${member.mid}')">
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
	<div align='center'>
		<button class="btn btn-warning"
			onclick="window.location.href='main.jsp'">뒤로가기</button>
	</div>

</body>
</html>