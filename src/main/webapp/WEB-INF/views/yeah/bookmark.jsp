<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
	String context = request.getContextPath();
%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>BOM</title>

<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/css/simple-sidebar.css" rel="stylesheet">

<!-- Bootstrap core JavaScript -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<!-- script src="/js/jquery.min.js"></script> -->
<script src="/js/jquery.js"></script>
<script src="/js/bootstrap.bundle.min.js"></script>
<script src="/js/bootstrap.bundle.js"></script>
<style>
.dropdown-toggle.caret-off::after {
	display: none;
}

.dropdown {
	align: right;
}
</style>
</head>


  
<body>

	<div class="d-flex" id="wrapper">

		<!-- Sidebar -->
		<div class="border-right sidebar-fixed-top" id="sidebar-wrapper">
			<div class="sidebar-heading" align="center">
				<img src="/img/logo2.jpg" width="150" height="150">
			</div>
			<div class="list-group list-group-flush">
				<a href="/iron/timeline"
					class="list-group-item list-group-item-action"> <img
					src="/img/home.svg" width="15" height="15"> 타임라인
				</a> <a href="/hoon/explore"
					class="list-group-item list-group-item-action"> <img
					src="/img/search.svg" width="15" height="15"> 검색하기
				</a> <a href="alarm" class="list-group-item list-group-item-action">
					<img src="/img/bell.svg" width="15" height="15"> 알림 <span
					class="badge badge-success">1</span>
				</a>
				<!-- bear1 -->
				<a href="/bear/chat" class="list-group-item list-group-item-action">
					<img src="/img/send.svg" width="15" height="15"> 쪽지
				</a> <a href="/yeah/bookmark" class="list-group-item list-group-item-action">
					<img src="/img/bookmark.svg" width="15" height="15"> 북마크
				</a> <a href="/iron/profile?uatid=${user.uatid }"
					class="list-group-item list-group-item-action"> <img
					src="/img/user.svg" width="15" height="15"> 프로필
				</a> <a href="/right/moreSee"
					class="list-group-item list-group-item-action"> <img
					src="/img/more.svg" width="15" height="15"> 더보기
				</a> <a href="#" class="list-group-item list-group-item-action">
					<button type="button" class="btn btn-outline-success">
						<img src="/img/write.svg" width="15" height="15"> 글 쓰기
					</button>
				</a>
				<div class="card">
					<div class="card-body" style="font-size: 0.8rem; padding: 10px;">
						<img src="/img/profile/${user.uimage}" class="rounded-circle"
							width="20"> <a class="card-title text-dark">${user.unickName }</a>
						<a class="card-subtitle mb-2 text-muted">@${user.uatid }</a>
					</div>
					<button type="button" class="btn btn-success">로그아웃</button>
				</div>
			</div>
		</div>



		<div id="page-content-wrapper">


			<!-- <div class="card">
					<div class="card-header">글쓰기</div>
					<div class="card-body">
						<div class="form-group">
							<textarea class="form-control" id="exampleFormControlTextarea1"
								rows="3"></textarea>
						</div>
						<div class="btn-group" role="group" aria-label="Basic example">
							<button type="button" class="btn btn-outline-secondary">미디어</button>
							<button type="button" class="btn btn-outline-secondary">GIF</button>
							<button type="button" class="btn btn-outline-secondary">투표</button>
							<button type="button" class="btn btn-outline-secondary">예약하기</button>
						</div>
						<button type="submit" class="btn btn-success float-right">등록</button>
					</div>
				</div> -->

			<!-- 북 마크  -->
			<div class="container-fluid">
				<p>
				<div class="card">
					<div class="card-header" style="font-weight: bold;">북마크</div>
					<div class="dropdown">
						<button
							class="btn btn-secondary btn-light mr-3 dropdown-toggle caret-off btn btn-light float-left"
							type="button" id="dropdownMenu2" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">...</button>


						<div class="dropdown-menu" aria-labelledby="dropdownMenu2">
							<form action="deleteAll">
								<input type="hidden" name="ucode" value="${ucode }">
								<button type="submit" class="dropdown-item">모든 북마크 지우기</button>
							</form>
						</div>
					</div>

					<!-- 북마크 글 정렬  -->
					<c:forEach var="bookmark" items="${ubmBoardList}">
						<div class="card">
							<div class="card-body">
								<!-- <button type="button" class="btn btn-light float-right">⋯</button> -->
								<img src="/img/profile/${bookmark.uimage }"
									class="rounded-circle" width="50"> <a
									class="card-title text-dark">${bookmark.unickname }</a> <a
									class="card-subtitle mb-2 text-muted">@${bookmark.uatid }</a> <a
									class="card-subtitle mb-2 text-muted">${bookmark.bdate }</a>


								<c:if test="${bookmark.btype == 'quote' }">
									<div class="col-12 float-left" id="QuoteArea"
										style="font-size: 0.8em;">
										<div class='card'>
											<div class='card-body'>
												<img id="quote_profile"
													src="<%=context %>/profile_image/${bookmark.q_uimage}"
													alt='no_image' class='rounded-circle' width='30'> <a
													class='card-title text-dark' id="quote_nickname">${bookmark.q_nickname}</a>
												<a class='card-subtitle mb-2 text-muted' id="quote_atid">${bookmark.q_atid}</a>
												<a class='card-subtitle mb-2 text-muted' id="quote_regdate">${bookmark.q_regdate}</a>
												<p class='card-text mt-2 mb-0' style="height: 100%;"
													id="quote_content">${bookmark.q_content}</p>
												<c:if test="${bookmark.q_attach != ''}">
													<div class="quote_file mt-2">
														<c:if test="${bookmark.q_attachtype eq 'image'}">
															<img id="quote_img"
																src="<%=context %>/image/${bookmark.q_attachsrc }"
																class="img-fluid" />
														</c:if>
														<c:if test="${bookmark.q_attachtype eq 'video'}">
															<div id="show_quote_video"
																class="embed-responsive embed-responsive-16by9">
																<video controls id="quote_video"
																	src="<%=context %>/video/${bookmark.q_attachsrc }">
																</video>
															</div>
														</c:if>
													</div>
												</c:if>

											</div>
										</div>
									</div>
								</c:if>

								<p class="card-text" style="margin-top: 10px;">${bookmark.bcontent }</p>

								<c:if test="${bookmark.battachType == 'image' }">
									<img src="/img/media/${bookmark.battachSrc }" width="200">
								</c:if>

								<c:if test="${bookmark.battachType == 'video' }">
									<video controls width="300">
										<source src="/img/media/${bookmark.battachSrc }"
											type="video/mp4" />
										<source src="/img/media/${bookmark.battachSrc }"
											type="video/webm">
									</video>
								</c:if>
							</div>

							<div align="center">
								<div class="btn-group col-md-12" role="group"
									aria-label="Button group with nested dropdown">
									<button type="button" class="btn btn-secondary mr-3 btn-light"
										data-toggle="tooltip" data-placement="top" title="답글 ">
										<img src="/img/speech-bubble.svg" width="20" height="20">
									</button>
									<button type="button" class="btn btn-secondary btn-light mr-3"
										data-toggle="tooltip" data-placement="top" title="스크랩 or 인용">
										<img src="/img/bring.svg" width="20" height="20">
									</button>



									<button type="button"
										class="btn btn-secondary btn-light mr-3 dropdown-toggle caret-off"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false">
										<img src="/img/share.svg" width="20" height="20">
									</button>

									<!-- 북마크 삭제 -->
									<div class="dropdown-menu">
										<form action="delete">
											<input type="hidden" name="ucode" value="${bookmark.ucode }">
											<input type="hidden" name="bcode" value="${bookmark.bcode }">
											<button type="submit" class="dropdown-item">${bookmark.ucode }북마크
												삭제${bookmark.bcode }</button>
										</form>

										<form>
											<button class="dropdown-item">URL담아가기</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>



		<!-- /#page-content-wrapper -->








		<!-- 오른쪽 사이드바 -->
		<div class="bg-light border-left" id="sidebar-wrapper2">
			<div class="list-group list-group-flush">
				<div class="list-group-item list-group-item-action bg-light">
					<div id="drop_the_text">
						<!-- 엔터치면 searchData() 실행 -->
						<input class="form-control" id="search" placeholder="봄 검색"
							onkeypress="if( event.keyCode == 13 ){searchData();}">
					</div>
				</div>
				<div class="list-group-item list-group-item-action bg-light"
					style="padding: 5px;">
					<div class="card bg-light mb-3">
						<div class="card-header">팔로우 추천</div>
						<div class="card-body" style="padding: 5px;">
							<div class="card">
								<div class="card-body" style="font-size: 0.8rem; padding: 10px;">
									<img src="/img/teemo.jpg" class="rounded-circle" width="20"
										height="20"> <a class="card-title text-dark">닉네임</a> <a
										class="card-subtitle mb-2 text-muted">@atid</a>
									<button type="button"
										class="btn btn-outline-success btn-sm float-right"
										style="font-size: 0.8rem;">팔로우</button>
								</div>
							</div>
							<div class="card">
								<div class="card-body" style="font-size: 0.8rem; padding: 10px;">
									<img src="/img/teemo.jpg" class="rounded-circle" width="20"
										height="20"> <a class="card-title text-dark">닉네임</a> <a
										class="card-subtitle mb-2 text-muted">@atid</a>
									<button type="button"
										class="btn btn-outline-success btn-sm float-right"
										style="font-size: 0.8rem;">팔로우</button>
								</div>
							</div>
							<div class="card">
								<div class="card-body" style="font-size: 0.8rem; padding: 10px;">
									<img src="/img/teemo.jpg" class="rounded-circle" width="20"
										height="20"> <a class="card-title text-dark">닉네임</a> <a
										class="card-subtitle mb-2 text-muted">@atid</a>
									<button type="button"
										class="btn btn-outline-success btn-sm float-right"
										style="font-size: 0.8rem;">팔로우</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="list-group-item list-group-item-action bg-light"
					style="padding: 5px;">
					<div class="card bg-light mb-3">
						<div class="card-header">실시간 트랜드</div>
						<div class="card-body" style="padding: 5px;">
							<div class="card">
								<div class="card-body" style="font-size: 0.8rem; padding: 10px;">
									1위
									<div>
										<a href="#">#사랑해티모</a> <span class="float-right">11,333
											봄</span>
									</div>
								</div>
							</div>
							<div class="card">
								<div class="card-body" style="font-size: 0.8rem; padding: 10px;">
									2위
									<div>
										<a href="#">#티세구</a> <span class="float-right">2,301 봄</span>
									</div>
								</div>
							</div>
							<div class="card">
								<div class="card-body" style="font-size: 0.8rem; padding: 10px;">
									3위
									<div>
										<a href="#">#롤하고싶다</a> <span class="float-right">1,300
											봄</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="list-group-item list-group-item-action bg-light"
					style="padding: 5px; font-size: 0.8rem;">
					<div class="card">
						<div class="card-body">
							<a href="#">이용약관</a> <a href="#">개인정보처리방침</a> <a href="#">운영정책</a>
							Copyright © Bom Corp. All rights reserved.
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 오른쪽 사이드바 끝 -->
	<!-- /#wrapper -->
</body>

</html>

