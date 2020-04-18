<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--
파일명 : login.jsp
작성자명 : 문지훈,황효준,김수민
프로젝트명 : library
기능설명 : 로그인 페이지
  -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>


<title>Insert title here</title>
</head>
<body>
	<script>
		function go() {
			if (userInfo.id.value == "") {
				alert("아이디를 입력해주세요")
				return false;
			} else if (userInfo.pw.value == "") {
				alert("비밀번호를 입력해주세요");
				return false;
			}
		}
	</script>
	<article class="container">
	<div class="col-md-6 col-md-offset-5">
		<br> <br> <b><font size="6" color="gray"><a
				href="main.jsp" style="text-decoration: none;">산학도서관</a></font></b> <br> <br>
		<br>
	</div>
	</article>




	<article class="container">
	<div class="page-header">
		<div class="col-md-6 col-md-offset-3">
			<h3>로그인</h3>
			<div align=right>
				<button class="btn btn-success"
					onclick="window.location.href='adminlogin.jsp'" />
				관리자로그인
				</button>
			</div>
		</div>
	</div>

	<!-- 입력한 값을 전송하기 위해 form 태그를 사용한다 --> <!-- 값(파라미터) 전송은 POST 방식, 전송할 페이지는 JoinPro.jsp -->
	<div class="col-sm-6 col-md-offset-3">
		<form method="post" action="login.do" name="userInfo"
			onsubmit="return go()">
			<div class="form-group">

				<label for="inputId">아이디</label> <input type="text" name="id"
					class="form-control" maxlength="50">
			</div>
			<div class="form-group">

				<label for="inputPw">비밀번호</label> <input type="password" name="pw"
					class="form-control" maxlength="50">
			</div>
			<div class="form-group text-center">
				<button type="submit" class="btn btn-primary">로그인</button>

				<a class="btn btn-warning" href="main.jsp">이전</a>
			</div>
		</form>
	</div>




	</article>
	<%
		// 아이디, 비밀번호가 틀릴경우 화면에 메시지 표시
		String loginMsg = (String) request.getAttribute("fail");

		if (loginMsg != null && loginMsg.equals("0")) {
			out.println("<br>");
			out.println("<font color='red' size='3'>비밀번호를 확인해 주세요.</font>");
		} else if (loginMsg != null && loginMsg.equals("-1")) {
			out.println("<br>");
			out.println("<font color='red' size='3'>아이디를 확인해 주세요.</font>");
		}
	%>

</body>
</html>