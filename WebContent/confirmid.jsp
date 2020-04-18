<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--
파일명 : confirmid.jsp
작성자명 : 문지훈,황효준,김수민
프로젝트명 : library
기능설명 : 아이디 중복체크 새로운 폼
  -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body onload="IdValue()">

	<article class="container">
	<div class="page-header">
		<div class="col-md-6 col-md-offset-3" align='center'>
			<h3>아이디 중복체크</h3>
		</div>
	</div>
	<br>
	<div class="col-sm-6 col-md-offset-3">
		<form id="form1" align='center'>
			<input type="text" name="id" id="id"> <input type="button"
				class="btn" value="중복확인" onclick='submitForm("confirmid1.do");'>
		</form>
		<div class="form-group text-center"></div>
		<br>
		<div align='center'>
			<button id="useBtn" class="btn btn-primary"
				onClick="sendCheckValue()">사용하기</button>
			<button id="cancelBtn" class="btn btn-warning"
				onClick="window.close()">취소</button>
		</div>
	</div>
	</article>


	<script>
		function submitForm(url) {

			var form1 = document.getElementById("form1");
			form1.setAttribute("action", url);
			form1.submit();

		}

		function IdValue() {
			document.getElementById("id").value = opener.document.form.id.value;
		}

		function sendCheckValue() {
			// 중복체크 결과인 idCheck 값을 전달한다.
			opener.document.form.idDuplication.value = "idCheck";
			// 회원가입 화면의 ID입력란에 값을 전달
			opener.document.form.id.value = document.getElementById("id").value;

			if (opener != null) {
				opener.chkForm = null;
				self.close();
			}
		}
	</script>


</body>
</html>