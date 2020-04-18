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
	<script>
		function checkValue() {
			var pw = document.getElementById("pw").value;
			var pwok = document.getElementById("pwcheck").value;
			var birth = document.getElementById("birth").value;
			var tel = document.getElementById("tel").value;
			if (pw != pwok) {
				alert("비밀번호가 일치하지 않습니다. 다시 입력해 주세요.");
				document.getElementById("pw").value = "";
				document.getElementById("pwcheck").value = "";
				return false;
			} else if (isNaN(birth)) {
				alert("생년월일은 숫자만 입력가능합니다.");
				document.getElementById("birth").value = "";
				return false;
			} else if (isNaN(tel)) {
				alert("휴대폰번호는 숫자만 입력가능합니다.");
				document.getElementById("tel").value = "";
				return false;
			}

			else if (document.getElementById("idDuplication").value == "idUncheck") {

				alert("아이디 중복체크를 하세요.");
				return false;

			}
		}
		function openIdChk() {

			window.name = "parentForm";
			window.open("confirmid.jsp", "chkForm",
					"width=600, height=300, resizable = no, scrollbars = no");
		}
		function inputIdChk() {
			document.form.idDuplication.value = "idUncheck";
		}
	</script>
	<c:if test="${sessionScope.ID eq null and sessionScope.ID ne 'admin'}">
		<article class="container">
		<div class="col-md-6 col-md-offset-5">
			<br> <br> <b><font size="6" color="gray"><a
					href="main.jsp" style="text-decoration: none;">산학도서관</a></font></b> <br>
			<br> <br>
		</div>
		</article>



		<!-- 입력한 값을 전송하기 위해 form 태그를 사용한다 -->
		<!-- 값(파라미터) 전송은 POST 방식, 전송할 페이지는 JoinPro.jsp -->
		<article class="container">
		<div class="page-header">
			<div class="col-md-6 col-md-offset-3" align='center'>
				<h3>회원가입</h3>
			</div>
		</div>
		<div class="col-sm-6 col-md-offset-3">
			<form method="post" action="join.do" onsubmit="return checkValue()"
				id="form1" name="form">
				<div class="form-group">
					<label for="inputName">아이디</label> <input type="text" name="id"
						class="form-control" id="userId" onkeydown="inputIdChk()"
						placeholder="이름을 입력해 주세요" required> <input type="button"
						class="btn" value="중복확인" onclick="openIdChk()"> <input
						type="hidden" id="idDuplication" name="idCheck" value="idUncheck" />
				</div>
				<div class="form-group">
					<label for="inputPassword">비밀번호</label> <input type="password"
						class="form-control" id="pw" name="pw" maxlength="50" required
						placeholder="비밀번호를 입력해주세요">
				</div>
				<div class="form-group">
					<label for="inputPasswordCheck">비밀번호 확인</label> <input
						type="password" class="form-control" id="pwcheck" name="pwcheck"
						maxlength="50" required placeholder="비밀번호 확인을 위해 다시한번 입력 해 주세요">
				</div>
				<div class="form-group">
					<label for="inputName">이름</label> <input type="text"
						class="form-control" name="name" maxlength="50" required
						placeholder="이름을 입력해 주세요">
				</div>
				<div class="form-group">
					<label for="inputBirth">생년월일</label> <input type="text"
						class="form-control" id="birth" name="birth" maxlength="6"
						placeholder="(6자)" size="6" required>
				</div>

				<div class="form-group">
					<label for="InputEmail">이메일 주소</label> <input type="text"
						class="form-control" name="mail" maxlength="50" required
						placeholder="이메일 주소를 입력해주세요">
				</div>
				<div class="form-group">
					<label for="inputMobile">휴대폰 번호</label> <input type="text"
						class="form-control" name="tel" id="tel" required
						placeholder="휴대폰번호를 입력해 주세요 '-'는 빼고 입력!">
				</div>


				<div class="form-group text-center">
					<button type="submit" id="join-submit" class="btn btn-primary">회원가입</button>
					<button type="reset" class="btn btn-warning">취소</button>
					<button class="btn btn-success"
						onclick="window.location.href='main.jsp'" />
					이전
					</button>
				</div>
			</form>
		</div>

		</article>
		</div>

	</c:if>
</body>
</html>