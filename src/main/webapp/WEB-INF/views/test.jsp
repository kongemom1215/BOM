<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Simple Sidebar - Start Bootstrap Template</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/simple-sidebar.css" rel="stylesheet">

</head>

<body>

	<div class="d-flex" id="wrapper">

		<!-- Sidebar -->
		<div class="bg-white border-right sidebar-fixed-top"
			id="sidebar-wrapper">
			<div class="sidebar-heading" align="center">
				<img src="img/logo2.jpg" width="150" height="150">
			</div>
			<div class="list-group list-group-flush">
				<a href="#" class="list-group-item list-group-item-action bg-white">
					<img src="img/home.svg" width="15" height="15"> 타임라인
				</a> <a href="#" class="list-group-item list-group-item-action bg-white">
					<img src="img/search.svg" width="15" height="15"> 검색하기
				</a> <a href="#" class="list-group-item list-group-item-action bg-white">
					<img src="img/bell.svg" width="15" height="15"> 알림 <span
					class="badge badge-success">1</span>
				</a> <a href="#" class="list-group-item list-group-item-action bg-white">
					<img src="img/send.svg" width="15" height="15"> 쪽지
				</a> <a href="#" class="list-group-item list-group-item-action bg-white">
					<img src="img/bookmark.svg" width="15" height="15"> 북마크
				</a> <a href="#" class="list-group-item list-group-item-action bg-white">
					<img src="img/user.svg" width="15" height="15"> 프로필
				</a> <a href="#" class="list-group-item list-group-item-action bg-white">
					<img src="img/more.svg" width="15" height="15"> 더보기
				</a> <a href="#" class="list-group-item list-group-item-action bg-white">
					<button type="button" class="btn btn-outline-success">
						<img src="img/write.svg" width="15" height="15"> 글 쓰기
					</button>
				</a>
				<div class="card">
					<div class="card-body">
						<img src="img/teemo.jpg" class="rounded-circle" width="50"
							width="50"> <a class="card-title text-dark">닉네임</a> <a
							class="card-subtitle mb-2 text-muted">@atid</a>
					</div>
					<button type="button" class="btn btn-success">로그아웃</button>
				</div>
			</div>
		</div>

		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">
			<nav
				class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
				<button class="btn btn-success" id="menu-toggle">←</button>
			</nav>
			<div class="alert alert-success" role="alert">
				<a href="#" class="alert-link">좋아요</a>를 누르셨습니다.
			</div>
			<div class="container-fluid">
				<p>
				<div class="card">
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
				</div>
				<!--글 정렬-->
				<c:forEach begin="1" end="10" step="1">
					<div class="card">
						<div class="card-body">
							<button type="button" class="btn btn-light float-right">⋯</button>
							<img src="img/teemo.jpg" class="rounded-circle" width="50"
								width="50"> <a class="card-title text-dark">닉네임</a> <a
								class="card-subtitle mb-2 text-muted">@atid</a> <a
								class="card-subtitle mb-2 text-muted">작성시간</a>
							<p class="card-text" style="margin-top: 10px;">글내용 블라블라</p>
							<div align="center">
								<div class="btn-group col-md-12" role="group"
									aria-label="Button group with nested dropdown">
									<button type="button" class="btn btn-secondary mr-3 btn-light"
										data-toggle="tooltip" data-placement="top" title="답글">
										<img src="img/speech-bubble.svg" width="20" height="20">
									</button>
									<button type="button" class="btn btn-secondary btn-light mr-3"
										data-toggle="tooltip" data-placement="top" title="스크랩 or 인용">
										<img src="img/bring.svg" width="20" height="20">
									</button>
									<button type="button" class="btn btn-secondary btn-light mr-3"
										data-toggle="tooltip" data-placement="top" title="좋아요">
										<img src="img/heart.svg" width="20" height="20">
									</button>
									<div class="dropdown">
										<button type="button"
											class="btn btn-secondary dropdown-toggle btn-light mr-3 "
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="true">
											<img src="img/share.svg" width="20" height="20">
										</button>
										<div class="dropdown-menu">
											<a class="dropdown-item" href="#">Dropdown link</a> <a
												class="dropdown-item" href="#">Dropdown link</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<!-- /#page-content-wrapper -->

		<!-- 오른쪽 사이드바 -->
		<div class="bg-light border-right" id="sidebar-wrapper2">
			<div class="list-group list-group-flush">
				<div class="list-group-item list-group-item-action bg-light">
					검색칸 <input class="form-control mr-sm-2" type="text"
						placeholder="Search" aria-label="Search">
				</div>
				<div class="list-group-item list-group-item-action bg-light">팔로우
					추천</div>
				<div class="list-group-item list-group-item-action bg-light">footer</div>
			</div>
		</div>
	</div>

	<!-- /#wrapper -->

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>

</html>