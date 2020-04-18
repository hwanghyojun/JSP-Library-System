<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--
파일명 : confirmid1.jsp
작성자명 : 문지훈,황효준,김수민
프로젝트명 : library
기능설명 : 아이디 중복체크 alert 창
  -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		boolean result = (Boolean) request.getAttribute("result");
		String message;
		if (result) {
			message = "이미 사용중인 ID입니다.";
		} else {
			message = "사용가능한 ID 입니다.";
		}
	%>
	<script>
		alert("<%=message%>");
		location.href = "confirmid.jsp";
	</script>
</body>
</html>