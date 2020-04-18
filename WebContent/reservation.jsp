<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<!--
파일명 : reservation.jsp
작성자명 : 문지훈,황효준,김수민
프로젝트명 : library
기능설명 : 자리 예약 페이지
  -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Library.reservation</title>
</head>
<body>
	<header>
		<article class="container">
			<div class="col-md-6 col-md-offset-5">
				<br> <br> <b><font size="6" color="gray"><a
						href="main.jsp" style="text-decoration: none;">산학도서관</a></font></b> <br>
				<br> <br>
			</div>
		</article>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">남은 자리 : ${fill}/${total} </a>
				</div>
				<form method="post" id="rform">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#" onclick="rform(1,${sessionScope.mdto.mno})">1호실</a></li>
						<li><a href="#" onclick="rform(2,${sessionScope.mdto.mno})">2호실</a></li>
						<li><a href="#" onclick="rform(3,${sessionScope.mdto.mno})">3호실</a></li>
						<li><a href="#" onclick="rform(4,${sessionScope.mdto.mno})">4호실</a></li>
					</ul>
				</form>
			</div>
		</nav>
	</header>
	<div align="center">
		<table>
			<c:forEach var="s" items="${seat}">
				<c:if test="${s.sno mod 20 eq 1 }">
					<tr>
				</c:if>
				<c:if test="${s.sno mod 10 eq 1 }">
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</c:if>
				<c:choose>
					<c:when test="${s.seatcheck eq true}">
						<td><c:choose>
								<c:when test="${admin eq 1 }">
									<button type="button" class="btn btn-primary btn-block btn-lg"
										data-toggle="modal" data-target="#Modal-admin-${s.sno}">${s.sno}</button>
									<div class="modal fade" id="Modal-admin-${s.sno}" role="dialog">
										<div class="modal-dialog modal-sm">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
													<h4 class="modal-title">정비하기.</h4>
												</div>

												<div class="modal-body">
													<p>${s.sno}번자리</p>
												</div>
												<div class="modal-footer">
													<form method="post" id="reform">
														<button type="button" class="btn btn-success"
															data-dismiss="modal"
															onclick="reform(${s.rno},${s.sno},${sessionScope.adto.ano})">정비</button>
														<button type="button" class="btn btn-danger"
															data-dismiss="modal">취소</button>
													</form>
												</div>

											</div>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<c:if test="${overlap eq 0 }">
										<button type="button" class="btn btn-primary btn-block btn-lg"
											data-toggle="modal" data-target="#Modal1-${s.sno}">${s.sno}</button>
									</c:if>
									<c:if test="${overlap eq 1 }">
										<button type="button" class="btn btn-primary btn-block btn-lg"
											data-toggle="modal" data-target="#Modal2-${s.sno}">${s.sno}</button>
									</c:if>
									<!-- Modal -->
									<div class="modal fade" id="Modal1-${s.sno}" role="dialog">
										<div class="modal-dialog modal-sm">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
													<h4 class="modal-title">예약 하시겠습니까?</h4>
												</div>
												<form method="post"
													action="reservation.do?rno=${s.rno}&sno=${s.sno}&mno=${sessionScope.mdto.mno}&reservation=1">
													<div class="modal-body">
														<p>${s.sno}번자리</p>
														<hr>
														<select class="form-control" name="time">
															<option value="1">1 시간</option>
															<option value="2">2 시간</option>
															<option value="3">3 시간</option>
															<option value="4">4 시간</option>
															<option value="5">5 시간</option>
														</select>
													</div>
													<div class="modal-footer">
														<input type="submit" class="btn btn-success" value="확인" />
														<button type="button" class="btn btn-danger"
															data-dismiss="modal">취소</button>
													</div>
												</form>
											</div>
										</div>
									</div>
									<div class="modal fade" id="Modal2-${s.sno}" role="dialog">
										<div class="modal-dialog modal-sm">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
													<h4 class="modal-title">이미 예약하신 자리가 있습니다.</h4>
												</div>

												<div class="modal-body">
													<p>${s.sno}번자리</p>

												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-success"
														data-dismiss="modal">확인</button>
												</div>
											</div>
										</div>
									</div>
								</c:otherwise>
							</c:choose></td>

						<td>&nbsp;</td>
					</c:when>
					<c:otherwise>
						<td><c:if test="${s.ano ne 0 }">
								<c:choose>
									<c:when test="${admin eq 1 }">
										<button type="button"
											class="btn btn-secondary btn-block btn-lg"
											data-toggle="modal" data-target="#Modal-end-${s.sno}">${s.sno}</button>
									</c:when>
									<c:otherwise>
										<button type="button"
											class="btn btn-secondary btn-block btn-lg">${s.sno}</button>
									</c:otherwise>
								</c:choose>
							</c:if> <c:if test="${s.mno ne sessionScope.mdto.mno and s.ano eq 0  }">
								<button type="button" class="btn btn-danger btn-block btn-lg"
									data-toggle="modal" data-target="#Modal3-${s.sno}">${s.sno}</button>
							</c:if> <c:if test="${s.mno eq sessionScope.mdto.mno}">
								<button type="button" class="btn btn-warning btn-block btn-lg"
									data-toggle="modal" data-target="#Modal4-${s.sno}">${s.sno}</button>
							</c:if>
							<div class="modal fade" id="Modal3-${s.sno}" role="dialog">
								<div class="modal-dialog modal-sm">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">예약된 자리 입니다.</h4>
										</div>
										<div class="modal-body">
											<p>${s.sno}번자리</p>
											<hr>
											<p>시작 시간 : ${s.starttime}</p>
											<hr>
											<p>종료 예상 시간 : ${s.endtime}</p>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-success"
												data-dismiss="modal">확인</button>
										</div>
									</div>
								</div>
							</div>
							<div class="modal fade" id="Modal4-${s.sno}" role="dialog">
								<div class="modal-dialog modal-sm">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">나의 자리</h4>
										</div>
										<form method="post"
											action="reservation.do?rno=${s.rno}&sno=${s.sno}&mno=${sessionScope.mdto.mno}&reservation=2">
											<div class="modal-body">
												<p>${s.sno}번자리</p>
												<hr>
												<p>시작 시간 : ${s.starttime}</p>
												<hr>
												<p>종료 예상 시간 : ${s.endtime}</p>
											</div>

											<div class="modal-footer">
												<button type="button" class="btn btn-success"
													data-dismiss="modal">확인</button>
												<input type="submit" class="btn btn-danger" value="예약종료" />
											</div>
										</form>
									</div>
								</div>
							</div>
							<div class="modal fade" id="Modal-end-${s.sno}" role="dialog">
								<div class="modal-dialog modal-sm">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">관리자</h4>
										</div>

										<div class="modal-body">
											<p>${s.sno}번자리</p>
										</div>
										<div class="modal-footer">
											<form method="post" id="erform">
												<button type="button" class="btn btn-success"
													data-dismiss="modal">확인</button>
												<button type="button" class="btn btn-danger"
													data-dismiss="modal"
													onclick="erform(${s.rno},${s.sno},${sessionScope.adto.ano})">정비종료</button>
											</form>
										</div>

									</div>
								</div>
							</div></td>
						<td>&nbsp;</td>
					</c:otherwise>
				</c:choose>

				<c:if test="${s.sno mod 20 eq 0}">
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
				</c:if>

			</c:forEach>

		</table>
	</div>

	<script>
		function rform(rno,mno) {
			rform = document.getElementById('rform');
			rform.setAttribute("action", "reservation.do?rno=" + rno+"&mno="+mno);
			rform.submit();
		}
		function reform(rno,sno,ano) {
			reform = document.getElementById('reform');
			reform.setAttribute("action", "reservation.do?rno=" + rno+"&sno="+sno+"&ano="+ano+"&reservation=1");
			reform.submit();
		}
		function erform(rno,sno,ano) {
			erform = document.getElementById('erform');
			erform.setAttribute("action", "reservation.do?rno=" + rno+"&sno="+sno+"&ano="+ano+"&reservation=2");
			erform.submit();
		}
	</script>
</body>
</html>