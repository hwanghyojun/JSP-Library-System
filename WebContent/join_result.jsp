<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--
파일명 : join_result.jsp
작성자명 : 문지훈,황효준,김수민
프로젝트명 : library
기능설명 : 회원가입 결과
  -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="library_style.css">
</head>
<body>
	<%
		int result = Integer.parseInt((String) request.getAttribute("result"));
	%>
	<c:if test="${sessionScope.ID eq null and sessionScope.ID ne 'admin'}">
		<div id="wrap">
			<br>
			<br> <b><font size="6" color="gray">산학도서관</font></b> <br>
			<br>
			<br>
		</div>
		<c:if test="${result > 0}">
			<script language=javascript>
         self.window.alert("회원가입 되었습니다..");
         location.href = "login.jsp";
      </script>
		</c:if>
		<c:if test="${result <= 0}">
			<script language=javascript>
         self.window.alert("회원가입 실패..");
         location.href = "join.jsp";
      </script>
		</c:if>
		</div>
	</c:if>
	<c:if test="${sessionScope.ID ne null and sessionScope.ID eq 'admin'}">


		<%
			if (result > 0) {

					String message = "추가에 실패하였습니다";
					if (result > 0) {
						message = "정상적으로 추가되었습니다";
					}
		%>
		<script>
		alert("<%=message%>
			");
			location.href = "list";
		</script>
		<%
			} else {
		%>
		<div>
			<h2>회원추가 결과</h2>
			<h3>회원추가에 실패하였습니다.</h3>
			<input type="button" value="이전 페이지로 가기"
				onclick="window.history.back();" />
		</div>
		<%
			}
		%>
	</c:if>
</body>
</html>