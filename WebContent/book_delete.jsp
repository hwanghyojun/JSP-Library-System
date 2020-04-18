<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--
파일명 : member_delete.jsp
작성자명 : 문지훈,황효준,김수민
프로젝트명 : library
기능설명 : 회원삭제 페이지
  -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="form1" method="post" action="book_management.do"></form>
	<%
		int result = Integer.parseInt((String) request.getAttribute("result"));
		String message = "삭제 실패 하였습니다. 도서를 대여한 회원이 있습니다.";

		if (result > 0) {
			message = "삭제되었습니다.";
		}
	%>
	<script>
	if (<%=result%> >0){
		
			alert("<%=message%>");
			var form = document.getElementById("form1");
			form.submit();
		
		}else{
			alert("<%=message%>");
			window.history.back();
		}
	</script>
</body>
</html>