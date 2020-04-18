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
	<form id="form1" method="post" action="member_list.do"></form>
	<%
		int result = Integer.parseInt((String) request.getAttribute("result"));
		int admin = (int)session.getAttribute("admin");
		String message = "삭제 실패 하였습니다. 예약된 자리나 도서를 확인해주세요";

		if (result > 0) {
			message = "삭제되었습니다. 로그인 화면으로 돌아갑니다.";
		}
	%>
	<script>
	if (<%=result%> >0){
		if(<%=admin%> == 1){
			alert("삭제되었습니다.");
			var form = document.getElementById("form1");
			form.submit();
		}else{
			alert("<%=message%>");
			location.href = "login.jsp";
			session.invalidate();
		}
		
		}else{
			alert("<%=message%>");
			window.history.back();
		}
	</script>
</body>
</html>