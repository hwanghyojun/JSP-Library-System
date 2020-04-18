<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--
파일명 : member_update_result.jsp
작성자명 : 문지훈,황효준,김수민
프로젝트명 : library
기능설명 : 회원수정 결과
  -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int result = Integer.parseInt((String) request.getAttribute("result"));
		String message = "수정 실패하였습니다";
		if (result > 0) {
			message = "수정되었습니다. 다시 로그인 하세요.";
		}
	%>
	<script>
	if (<%=result%> >0){
		alert("<%=message%>");
			location.href = "login.jsp";
			session.invalidate();
		}else{
			alert("<%=message%>");
			window.history.back();
		}
	</script>
</body>
</html>