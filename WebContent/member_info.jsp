<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.silla.library.member.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<!--
파일명 : member_info.jsp
작성자명 : 문지훈,황효준,김수민
프로젝트명 : library
기능설명 : 회원정보 수정 페이지
  -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>

<title>내 정보</title>
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
		function checkValue() {
			var pw = document.getElementById("pw").value;
			var pwok = document.getElementById("pwcheck").value;
			if (pw != pwok) {
				alert("비밀번호가 일치하지 않습니다. 다시 입력해 주세요.");
				document.getElementById("pw").value = "";
				document.getElementById("pwcheck").value = "";
				return false;
			}
		}
		function submitForm3(url) {

			var form1 = document.getElementById("form1");
			form1.setAttribute("action", url);
			form1.submit();

		}
	</script>
	<article class="container">
		<div class="col-md-6 col-md-offset-5">
			<br> <br> <b><font size="6" color="gray"><a
					href="main.jsp" style="text-decoration: none;">산학도서관</a></font></b> <br>
			<br> <br>
		</div>
	</article>
	<article class="container">
		<div class="page-header">
			<div class="col-md-6 col-md-offset-3" align='center'>
				<h3>회원정보수정</h3>
			</div>
		</div>


		<c:if test="${admin eq 1}">
    			${sessionScope.adto.aid } 관리자님 반갑습니다. <br>
			<br>

			<div class="col-sm-6 col-md-offset-3">
				<form method="post" action="member_list.do?update=1"
					onsubmit="return checkValue()" id="form1">
					<div class="form-group">
						<label for="inputName">아이디</label> <input type="text" name="id"
							class="form-control" id="userId" value="${member.mid }" readonly>

					</div>
					<div class="form-group">
						<label for="inputPassword">비밀번호</label> <input type="text"
							class="form-control" id="pw" name="pw" maxlength="50"
							value="${member.mpw}" required placeholder="비밀번호를 입력해주세요">
					</div>
					<div class="form-group">
						<label for="inputPasswordCheck">비밀번호 확인</label> <input type="text"
							class="form-control" id="pwcheck" name="pwcheck" maxlength="50"
							required placeholder="비밀번호 확인을 위해 다시한번 입력 해 주세요">
					</div>
					<div class="form-group">
						<label for="inputName">이름</label> <input type="text"
							class="form-control" name="name" maxlength="50"
							value="${member.mname}" required>
					</div>
					<div class="form-group">
						<label for="inputBirth">생년월일</label> <input type="text"
							class="form-control" id="birth" name="birth" maxlength="6"
							placeholder="(6자)" size="6" value="${member.mbirth}" required>
					</div>

					<div class="form-group">
						<label for="InputEmail">이메일 주소</label> <input type="text"
							class="form-control" name="mail" maxlength="50"
							value="${member.memail}" required>
					</div>
					<div class="form-group">
						<label for="inputMobile">휴대폰 번호</label> <input type="text"
							class="form-control" name="tel" value="${member.mtel}" required>
					</div>
					<br>

					<div align='center'>
						<button type="submit" class="btn btn-success">수정하기</button>
						<button class="btn btn-warning" onclick="window.history.back()">뒤로가기</button>
					</div>

				</form>
			</div>

		</c:if>
		<c:if test="${admin eq 0}">
    			${sessionScope.mdto.mid } 님 반갑습니다. <br>
			<br>

			<div class="col-sm-6 col-md-offset-3">
				<form method="post" action="member_update.do"
					onsubmit="return checkValue()" id="form1">
					<div class="form-group">
						<label for="inputName">아이디</label> <input type="text" name="id"
							class="form-control" id="userId" value="${member.mid }" readonly>

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
							class="form-control" name="name" maxlength="50"
							value="${member.mname}" required>
					</div>
					<div class="form-group">
						<label for="inputBirth">생년월일</label> <input type="text"
							class="form-control" id="birth" name="birth" maxlength="6"
							placeholder="(6자)" size="6" value="${member.mbirth}" required>
					</div>

					<div class="form-group">
						<label for="InputEmail">이메일 주소</label> <input type="text"
							class="form-control" name="mail" maxlength="50"
							value="${member.memail}" required>
					</div>
					<div class="form-group">
						<label for="inputMobile">휴대폰 번호</label> <input type="text"
							class="form-control" name="tel" value="${member.mtel}" required>
					</div>
					<br>

					<div align='center'>
						<button type="submit" class="btn btn-success">수정하기</button>
						<button class="btn btn-primary"
							onclick="submitForm('member_delete.do')">회원탈퇴</button>
						<button class="btn btn-warning" onclick="window.history.back()">뒤로가기</button>
					</div>

				</form>
			</div>
		</c:if>


	</article>

</body>
</html>