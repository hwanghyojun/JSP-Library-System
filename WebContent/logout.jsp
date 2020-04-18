<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--
파일명 : logout.jsp
작성자명 : 문지훈,황효준,김수민
프로젝트명 : library
기능설명 : 로그아웃 페이지
  -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그아웃 페이지</title>
</head>
<body>

	<%
		String message = "로그아웃 되었습니다.";
		session.invalidate();
	%>


	<script>
		alert("<%=message%>");
		location.href = "main.jsp";
	</script>


</body>
</html>