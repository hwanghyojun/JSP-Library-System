<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!--
파일명 : main.jsp
작성자명 : 문지훈,황효준,김수민
프로젝트명 : library
기능설명 : 메인화면
  -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body style="background: #D9E5FF;">

	<script>
		$('.carousel').carousel();
		function submitForm2(url) {

			var form1 = document.getElementById("form1");
			form1.setAttribute("action", url);
			form1.submit();

		}
	</script>

	<article class="container">
		<div class="col-md-6 col-md-offset-5">
			<br> <br> <b><font size="6" color="gray">산학도서관</font></b> <br>
		</div>
	</article>




	<div class='row'>
		<div class="col-sm-3" align="center">
			<div class="login alert"
				style="border: 1px solid black; margin: 20px; background: #F6F6F6;">
				<table>
					<c:if test="${admin eq null}">
						<tr>
							<td><a href="login.jsp"
								class="btn btn-lg btn-block btn-success">로그인</a></td>
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td><a href="join.jsp"
								class="btn btn-lg btn-block btn-success">회원가입</a></td>
						</tr>

					</c:if>
					<tr>
						<td>
							<form id="form1" method="post" action="logout.do">
								<c:if test="${admin eq 1}">
    ${sessionScope.adto.aid} 관리자님 반갑습니다.<br>
									<button class="btn btn-primary"
										onclick="submitForm2('member_list.do')">회원목록</button>
									<input type="submit" class="btn btn-primary" value="로그아웃"/>
								</c:if>
								<c:if test="${admin eq 0}">
    ${sessionScope.mdto.mid} 님 반갑습니다.<br>
									<button class="btn btn-primary"
										onclick="submitForm2('member_info.do')">내 정보</button>
									<input type="submit" class="btn btn-primary" value="로그아웃"/>
								</c:if>
							</form>
						</td>
					</tr>
				</table>
			</div>
			<div class="login alert"
				style="border: 1px solid black; margin: 20px; background: #F6F6F6;">
				<table>
					<tr>
						<td>
							<form method="post"
								action="reservation.do?rno=1&mno=${sessionScope.mdto.mno}&ano=${sessionScope.adto.ano}">
								<input class="btn btn-lg btn-info" type="submit" value="자리 예약" />
							</form>
						</td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td><c:if test="${admin ne 1}">
								<form method="post"
									action="rent.do?mno=${sessionScope.mdto.mno}">
									<input class="btn btn-lg btn-info" type="submit" value="도서 대여" />
								</form>
							</c:if> <c:if test="${admin eq 1}">
								<form method="post" action="book_management.do">
									<input class="btn btn-lg btn-info" type="submit" value="도서 관리" />
								</form>
							</c:if></td>
					</tr>
				</table>
			</div>
		</div>
		<div class='col-sm-9' style="padding: 20px" align="right">
			<div id="carousel-example-generic" class="carousel slide">

				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0"
						class="active"></li>
					<li data-target="#carousel-example-generic" data-slide-to="1"></li>
					<li data-target="#carousel-example-generic" data-slide-to="2"></li>
				</ol>

				<div class="carousel-inner">

					<div class="item active" align="center">
						<img src="./images/01.jpg" alt="First slide">
					</div>
					<div class="item" align="center">
						<img src="./images/02.jpg" alt="Second slide">
					</div>
					<div class="item" align="center">
						<img src="./images/03.jpg" alt="Third slide">
					</div>
				</div>
				<!-- Controls -->
				<a class="left carousel-control" href="#carousel-example-generic"
					data-slide="prev"> <span class="icon-prev"></span>
				</a> <a class="right carousel-control" href="#carousel-example-generic"
					data-slide="next"> <span class="icon-next"></span>
				</a>
			</div>
		</div>
	</div>
	<div>
		<nav>
			<div class='row'>
				<div class='col-sm-4'></div>
				<div class='col-sm-4'></div>
				<div class='col-sm-4'></div>
			</div>
		</nav>
	</div>

</body>
</html>