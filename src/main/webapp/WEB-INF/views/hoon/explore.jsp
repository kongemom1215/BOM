<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
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

<!-- junghun style -->
<link href="/css/junghun.css" rel="stylesheet">

<!-- Bootstrap core JavaScript -->
<!-- <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script> -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.bundle.min.js"></script>
<script src="/js/bootstrap.bundle.js"></script>
<style>
.dropdown-toggle.caret-off::after {
	display: none;
}
</style>

</head>

<body class="pt-5">

	<div class="d-flex" id="wrapper">

		<!-- Sidebar -->
		<div class="border-right sidebar-fixed-top" id="sidebar-wrapper">
			<div class="sidebar-heading" align="center">
				<img src="/img/logo2.jpg" width="150" height="150">
			</div>
			<div class="list-group list-group-flush">
				<a href="/iron/timeline" class="list-group-item list-group-item-action"> <img
					src="/img/home.svg" width="15" height="15"> 타임라인
				</a> <a href="explore" class="list-group-item list-group-item-action">
					<img src="/img/search.svg" width="15" height="15"> 검색하기
				</a> <a href="#" class="list-group-item list-group-item-action"> <img
					src="/img/bell.svg" width="15" height="15"> 알림 <span
					class="badge badge-success">1</span>
				</a> <a href="/bear/chat" class="list-group-item list-group-item-action"> <img
					src="/img/send.svg" width="15" height="15"> 쪽지
				</a> <a href="#" class="list-group-item list-group-item-action"> <img
					src="/img/bookmark.svg" width="15" height="15"> 북마크
				</a> <a href="#" class="list-group-item list-group-item-action"> <img
					src="/img/user.svg" width="15" height="15"> 프로필
				</a> <a href="#" class="list-group-item list-group-item-action"> <img
					src="/img/more.svg" width="15" height="15"> 더보기
				</a> <a href="#" class="list-group-item list-group-item-action">
					<button type="button" class="btn btn-outline-success">
						<img src="/img/write.svg" width="15" height="15"> 글 쓰기
					</button>
				</a>
				<div class="card">
					<div class="card-body">
						<div class="form-row">
							<img src="/img/teemo.jpg" class="rounded-circle" width="50"
								width="50">
							<div class="form-col ml-2">
								<a class="card-title text-dark" style="font-size: 0.8em">${login.uNickname}</a><br>
								<a class="card-subtitle mb-2 text-muted"
									style="font-size: 0.8em">@${login.uAtid}</a>
							</div>
						</div>
					</div>
					<button type="button" class="btn btn-success">로그아웃</button>
				</div>
			</div>
		</div>

		<!-- /#sidebar-wrapper -->
		<!-- Page Content -->
		<div id="page-content-wrapper">
			<nav
				class="navbar navbar-expand-lg navbar-light bg-light border-bottom fixed-top"
				style="left: 241px; right: 241px; z-index: 5;">
				<form class="well form-search" action="searchView" method="get" id="jh_form">
					<div class="input-group">
						<input type="text" ID="datebox" Class="form-control" name="search"
							data-toggle="dropdown" required="required" placeholder="봄 검색" style="width: 475px;"></input>
				
						<table id="demolist" class="dropdown-menu" style="z-index: 5; width: 475px;">
							<tr>
								<td style="font-weight: normal; padding-bottom: 15px;">최근
									<button type="reset" id="del_ajax" style="font-size: 12px; float: right">전체지우기</button>
								</td>
							</tr>
							<c:forEach var="Junghun" items="${searchkeyword }" begin="0"
								end="10">
								<tr id="searchkeyword">
									<td class="dropdown-li" style="padding: 5px;
									border: 1px solid; border-collapse:collapse ; width: 470px;"><c:choose>
											<c:when test="${Junghun.search.contains('#')}">
												${Junghun.search }
											</c:when>
											<c:otherwise>
												<a id="row" href="searchView?search=${Junghun.search }">${Junghun.search }</a>
											</c:otherwise>
										</c:choose></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</form>
			</nav>
			<script type="text/javascript">
			<%String context = request.getContextPath();%>
					$("#del_ajax").click(function(){
								var ucode = ${loginUser.ucode}
								console.log('ucode !!: ' + ucode);
							$.ajax({
							url:"<%=context%>/deleteRow",
							data : {ucode : ucode},
							dataType : 'text',
							success : function(data) {
							alret(data);
							},
							error:function(request,status,error){
								alert("code-"+request.status+" mas-"+request.responseText+" error-"+error);
							}
						});
					});
					
			</script>
			<div class="container-fluid">
				<!--글 정렬-->
				<div class="panel panel-default">
					<!-- Table -->
					<table class="table">
						<tr>
							<td class="table-title">-</td>
							<td class="table-title">-</td>
						</tr>
						<c:forEach var="Junghun" items="${listCount }" varStatus="status"
							begin="0" end="2">
							<tr>
								<td>${status.count }.
									${Junghun.search }
								</td>
								<td>${status.count }.
									${Junghun.search }
								</td>
							</tr>
						</c:forEach>
					</table>
					<table class="table">
						<tr>
							<td class="table-title">트랜드 추천</td>
						</tr>
						<c:forEach var="Junghun" items="${listHash }">
							<tr>
								<td><a class="table-content"
									href="searchView?search=${Junghun.search }"
									style="text-align: center;">${Junghun.search }<br> <c:choose>
											<c:when test="${Junghun.scount > 4999 }">
												<fmt:formatNumber value="${Junghun.scount }"
													groupingUsed="true"></fmt:formatNumber> 봄</c:when>
											<c:when test="${Junghun.scount <4999}">
											</c:when>
										</c:choose>
								</a></td>
							</tr>
						</c:forEach>
					</table>
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
							<div class="card-hover">
								<c:forEach var="Junghun" items="${listTrend }" begin="0" end="2"
									varStatus="status">
									<div class="card-body"
										style="font-size: 0.8rem; padding: 10px;">
										${status.count }위
										<div>
											${Junghun.search }
											<span class="float-right"><fmt:formatNumber
													value="${Junghun.scount }" groupingUsed="true"></fmt:formatNumber>
												봄</span>
										</div>
									</div>
								</c:forEach>
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