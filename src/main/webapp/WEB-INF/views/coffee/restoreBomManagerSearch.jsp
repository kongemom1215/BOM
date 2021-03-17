<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/views/coffee/header.jsp" %>

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
<script type="text/javascript" src="/js/jquery.js"></script>

<script src="/js/bootstrap.bundle.min.js"></script>
<script src="/js/bootstrap.bundle.js"></script>
<style>
.dropdown-toggle.caret-off::after {
	display: none;
}
</style>
<script type="text/javascript">
    //var contextPath='${pageContext.request.contextPath}';

	$(function(){
		<c:forEach var="i" begin = "0" end= "${fn:length(boardUser_infoList)}">
	
			var state = $(".bstate${i}").val();
			// console.log(state);
			//console.log(vUcode);
    		var vBcode${i} = $(".bcode${i}").val();
    		// var vUnickname = $(".unickname${i}").val();

			if(state == '1'){
				$(".btn.btn-primary.float-right.${i}").hide();
			}else if(state == '2'){
				$(".btn.btn-danger.float-right.${i}").hide();
			}
	    	$(".btn.btn-danger.float-right.${i}").click(function(){
				
				/* alert("차단 누름")
				alert("vUcode->"+vUcode);
				alert("vUnickname->"+vUnickname); */
				$.ajax({
    				url:"<%=context%>/coffee/coffeeUpdateBstate",  
    				data:{updateValue : 2, bcode : vBcode${i} },
    				dataType:'text',
    				success:function(data){
    					$(".btn.btn-danger.float-right.${i}").hide();
    					$(".btn.btn-primary.float-right.${i}").show();
    					/* if(data == 1){
    						alert("업데이트 성공");
    					}else{
    						alert("업데이트 실패")
    					} */
    				},
    				error:function(request,status,error){
    				    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
    				}
    				
    			});	 		
		 	});
			﻿﻿﻿﻿﻿
			$(".btn.btn-primary.float-right.${i}").click(function(){
				//alert("복원 누름")
				$.ajax({
    				url:"<%=context%>/coffee/coffeeUpdateBstate",  
    				data:{updateValue : 1, bcode : vBcode${i} },
    				dataType:'text',
    				success:function(data){
    					$(".btn.btn-primary.float-right.${i}").hide();
    					$(".btn.btn-danger.float-right.${i}").show();
    					/* if(data == 1){
    						alert("업데이트 성공");
    					}else{
    						alert("업데이트 실패")
    					} */
    				},
    				error:function(request,status,error){
    				    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
    				}
    			});	 
			});
		</c:forEach>
	})

</script>
</head>

<body>

	<div class="d-flex" id="wrapper">

		<!-- Sidebar -->
		<div class="border-right min-vh-100" id="sidebar-wrapper">
			<div class="sidebar-heading" align="center">
				<img src="/img/logo2.jpg" width="150" height="150">
			</div>
			<div class="list-group list-group-flush overflow-auto vh-100">
				<!-- <a href="#" class="list-group-item list-group-item-action"> <img
					src="/img/home.svg" width="15" height="15"> 타임라인
				</a> <a href="#" class="list-group-item list-group-item-action"> <img
					src="/img/search.svg" width="15" height="15"> 검색하기
				</a> <a href="#" class="list-group-item list-group-item-action"> <img
					src="/img/bell.svg" width="15" height="15"> 알림 <span
					class="badge badge-success">1</span>
				</a> <a href="#" class="list-group-item list-group-item-action"> <img
					src="/img/send.svg" width="15" height="15"> 쪽지
				</a> <a href="#" class="list-group-item list-group-item-action"> <img
					src="/img/bookmark.svg" width="15" height="15"> 북마크
				</a> <a href="#" class="list-group-item list-group-item-action"> <img
					src="/img/user.svg" width="15" height="15"> 프로필
				</a> <a href="#" class="list-group-item list-group-item-action"> <img
					src="/img/more.svg" width="15" height="15"> 더보기 -->
				</a> <a href="/coffee/interceptor/censorBomManagerPage" class="list-group-item list-group-item-action"> <img
					src="/img/coffee/censorBom.svg" width="15" height="15"> 봄 검열
				</a> <a href="/coffee/interceptor/censorMemberManagerPage" class="list-group-item list-group-item-action"> <img
					src="/img/coffee/censorMember.svg" width="15" height="15"> 회원 검열
				</a> <a href="/coffee/interceptor/censorAccusationManagerPage" class="list-group-item list-group-item-action"> <img
					src="/img/coffee/accusation.svg" width="15" height="15"> 신고 게시판
				</a> <!-- <a href="#" class="list-group-item list-group-item-action">
					<button type="button" class="btn btn-outline-success">
						<img src="/img/write.svg" width="15" height="15"> 글 쓰기
					</button>
				</a> -->
				<div class="card">
					<div class="card-body">
						<img src="/img/teemo.jpg" class="rounded-circle" width="50"
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
			<h2>봄 검열 페이지</h2>
			<div class="alert alert-success" role="alert">
				"${search }" 검색 결과 입니다.
			</div>
			
			<div class="container-fluid">
				<p>
				<div class="input-group col-auto">
					<ul class="nav nav-tabs">
					  <li class="nav-item">
					    <a class="nav-link" href="/coffee/interceptor/censorBomManagerPage">차단</a>
					  </li>
					  <li class="nav-item">
					    <a class="nav-link active" aria-current="page" href="/coffee/interceptor/restoreBomManagerPage">복원</a>
					  </li>
					  <li class="nav-item">
					    <a class="nav-link" href="/coffee/interceptor/accusationBomManagerPage">피신고글</a>
					  </li>
					  
					</ul>
					<form class="form-inline mt-2 mt-md-0" action="/coffee/restoreBomManagerSearch">
						<input type="text" class="form-control form-control mr-sm-2 float-right" name="search" id="search">
						<button type="submit" class="btn btn-success float-right">검 색</button>
					</form>
				</div>
				<!--글 정렬-->
				<c:forEach var="list" items="${boardUser_infoList }" varStatus="status">
					
					<input type="hidden" class="bstate${status.index }"      value="${list.bstate}">
					<input type="hidden" class="bcode${status.index }"      value="${list.bcode}">
					<%-- <input type="hidden" class="unickname${status.index }"  value="${list.unickname }"> --%>
					<div class="card">
						<div class="card-body">
							<button type="button" id="btn_ban" class="btn btn-danger float-right ${status.index }">차단</button>
							<button type="button" id="btn_restore" class="btn btn-primary float-right ${status.index }">복원</button>
							<c:choose>
								<c:when test="${not empty list.uimage }">
									<img alt="회원 이미지" src="<%=context %>/profile_image/${list.uimage }" class="rounded-circle" width="50"
									height="50"></c:when>
								<c:otherwise>
									<img src="/img/coffee/user_basic.svg" class="rounded-circle" width="50" height="50">
								</c:otherwise>
							</c:choose>
							<span class="card-title text-dark">${list.unickname }</span> <a
								class="card-subtitle mb-2 text-muted">@${list.uatid }</a> <a
								class="card-subtitle mb-2 text-muted">${list.bregdate }</a> <a href="#"
								class="card-text" style="margin-top: 10px;">${list.bcontent }</a>
								<c:if test="${list.battach!=null }">
								 	<c:if test="${list.battachType=='image'}">
								 		<img class="img-thumnail" width="300" src="<%=context %>/image/${list.battachSrc}"/>
								 	</c:if>
								 	<c:if test="${list.battachType=='video'}">
								 		<video controls width="300">
								 			<source  src="<%=context %>/video/${list.battachSrc}" type="video/mp4">
								 			<source  src="<%=context %>/video/${list.battachSrc}" type="video/webm">
								 			해당 브라우저에는 지원하지 않는 비디오입니다.
								 		</video>
								 	</c:if>
								 </c:if>
							<div align="center">
								<div class="btn-group col-md-12" role="group"
									aria-label="Button group with nested dropdown">
									<button type="button" class="btn btn-secondary btn-light mr-3"
										data-toggle="tooltip" data-placement="top" title="답글">
										<img src="/img/speech-bubble.svg" width="20" height="20">${list.breplycount }
									</button>
									<button type="button" class="btn btn-secondary btn-light mr-3"
										data-toggle="tooltip" data-placement="top" title="스크랩 or 인용">
										<img src="/img/bring.svg" width="20" height="20">${list.bquotecount }
									</button>
									<button type="button" class="btn btn-secondary btn-light mr-3"
										data-toggle="tooltip" data-placement="top" title="좋아요">
										<img src="/img/heart.svg" width="20" height="20">${list.blikecount }
									</button>
									
								</div>
							</div>
							<div>
								<span class="bg-danger p-1 text-light"><img src="/img/coffee/accusation.svg" width="15" height="15">${list.breportcount }</span>
							</div>
						</div>
					</div>
				</c:forEach>
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