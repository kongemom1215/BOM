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
<script src="/js/jquery.js"></script>
<script src="/js/bootstrap.bundle.min.js"></script>
<script src="/js/bootstrap.bundle.js"></script>
<style>
@font-face {
   font-family: 'GmarketSansLight';
   src:
      url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansLight.woff')
      format('woff');
   font-weight: normal;
   font-style: normal;
}

body {
   font-family: GmarketSansLight;
}
.dropdown-toggle.caret-off::after {
	display: none;
}
</style>
<script type="text/javascript">
    //var contextPath='${pageContext.request.contextPath}';

	$(function(){
		<c:forEach var="i" begin = "0" end= "${fn:length(user_infoList)}">
	
			var state = $(".ustate${i}").val();
			// console.log(state);
			//console.log(vUcode);
    		var vUcode${i} = $(".ucode${i}").val();
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
    				url:"<%=context%>/coffee/coffeeUpdateUstate",  
    				data:{updateValue : 2, ucode : vUcode${i} },
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
    				url:"<%=context%>/coffee/coffeeUpdateUstate",  
    				data:{updateValue : 1, ucode : vUcode${i} },
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
					src="/img/more.svg" width="15" height="15"> 더보기 </a>-->
				<a href="/coffee/interceptor/censorBomManagerPage" class="list-group-item list-group-item-action"> <img
					src="/img/coffee/censorBom.svg" width="15" height="15"> 봄 검열
				</a> <a href="/coffee/interceptor/censorMemberManagerPage" class="list-group-item list-group-item-action"> <img
					src="/img/coffee/censorMember.svg" width="15" height="15"> 회원 검열
				</a><!--  <a href="/coffee/interceptor/censorAccusationManagerPage" class="list-group-item list-group-item-action"> <img
					src="/img/coffee/accusation.svg" width="15" height="15"> 신고 게시판
				</a>  --><!-- <a href="#" class="list-group-item list-group-item-action">
					<button type="button" class="btn btn-outline-success">
						<img src="/img/write.svg" width="15" height="15"> 글 쓰기
					</button>
				</a> -->
				<div class="card">
					<div class="card-body">
						<c:choose>
							<c:when test="${not empty user.uimage }">
								<img alt="회원 이미지" src="<%=context %>/profile_image/${user.uimage }" class="rounded-circle" width="50"
								height="50"></c:when>
							<c:otherwise>
								<img src="/img/coffee/user_basic.svg" class="rounded-circle" width="50" height="50">
							</c:otherwise>
						</c:choose> <a class="card-title text-dark">${user.unickName }</a> <a
						class="card-subtitle mb-2 text-muted">@${user.uatid }</a>
					</div>
					<button type="button" class="btn btn-success" onclick = "alert('로그아웃 되었습니다'); location.href = '/coffee/logout'; ">로그아웃</button>
				</div>
			</div>
		</div>

		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">
			<nav
				class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
				<button class="btn btn-success" id="menu-toggle" onclick="history.back(); return false;">←</button>
			</nav>
			<h2>회원 검열 페이지</h2>
			<div class="alert alert-success" role="alert">
				"${search }" 검색 결과 입니다.
			</div>
			
			<div class="container-fluid">
				<p>
				<div class="input-group col-auto">
					<ul class="nav nav-tabs">
					  <li class="nav-item">
					    <a class="nav-link" href="/coffee/interceptor/censorMemberManagerPage">탈퇴</a>
					  </li>
					  <li class="nav-item">
					    <a class="nav-link" href="/coffee/interceptor/restoreMemberManagerPage">복원</a>
					  </li>
					  <li class="nav-item">
					    <a class="nav-link active" href="/coffee/interceptor/accusationMemberManagerPage">피신고자</a>
					  </li>
					  
					</ul>
					<form class="form-inline mt-2 mt-md-0" action="/coffee/accusationMemberManagerSearch">
						<input type="text" class="form-control form-control mr-sm-2 float-right" name="search" id="search">
						<button type="submit" class="btn btn-success float-right">검 색</button>
					</form>
				</div>
				<!--글 정렬-->
				<c:forEach var="list" items="${user_infoList }" varStatus="status">
					
					<input type="hidden" class="ustate${status.index }"      value="${list.ustate}">
					<input type="hidden" class="ucode${status.index }"      value="${list.ucode}">
					<%-- <input type="hidden" class="unickname${status.index }"  value="${list.unickname }"> --%>
					<div class="card">
						<div class="card-body">
							<div align="center"><c:choose>
								<c:when test="${not empty list.ubg }">
									<img src="<%=context %>/profile_image/${list.ubg }" style="width: auto;
    							height: 200px; object-fit:contain;">
								</c:when>
								<c:otherwise>
									<img src="/img/coffee/news_img_02_m.jpg" style="width: auto;
    								height: 200px; object-fit:contain;">
    							</c:otherwise>
    							</c:choose>
							</div>
							<span><button type="button" class="btn btn-danger float-right ${status.index }">탈퇴</button></span>
							<span><button type="button" class="btn btn-primary float-right ${status.index }">복원</button></span>
							
							<c:choose>
								<c:when test="${not empty list.uimage }">
								<img alt="회원 이미지" src="<%=context %>/profile_image/${list.uimage }" class="rounded-circle" width="100"
								height="100"></c:when>
								<c:otherwise>
								<img src="/img/coffee/user_basic.svg" class="rounded-circle" width="100" height="100">
								</c:otherwise>
							</c:choose>
							<span class="card-title text-dark">${list.unickname }</span> <a
								class="card-subtitle mb-2 text-muted">@${list.uatid }</a> 
								<a class="card-text" style="margin-top: 10px;">${list.uintro } 
								</a>
							<div><c:if test="${not empty list.unation }"><img alt="국적" src="/img/coffee/flags.svg" width="15" height="15">${list.unation }</c:if> 
							<c:if test="${not empty list.uloc }"><img alt="위치" src="/img/coffee/placeholder.svg" width="15" height="15">${list.uloc }</c:if>
							</div>
							<div> 
							<c:if test="${not empty list.uprofilelink }"><img src="/img/coffee/link.svg" width="15" height="15"><a href="#">${list.uprofilelink }</a></c:if>
							<img src="/img/coffee/calendar-interface-symbol-tool.svg" width="15" height="15">${list.uregdate }
							</div>
							<div>${list.ufollowing } Following ${list.ufollower } Follower</div>
							<span class="bg-danger p-1 text-light"><img src="/img/coffee/accusation.svg" width="15" height="15">${list.ureportcount }</span>
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
					<!-- <div id="drop_the_text">
						엔터치면 searchData() 실행
						<input  class="form-control" id="search" placeholder="봄 검색"
							onkeypress="if( event.keyCode == 13 ){searchData();}">
					</div> -->
				</div>
				<%-- <div class="list-group-item list-group-item-action bg-light"
					style="padding: 5px;">
					<div class="card bg-light mb-3">
						<div class="card-header">팔로우 추천</div>
						<div class="card-body" style="padding: 5px;">
							<c:if test="${suggestFlist2_size>0 }">
								<c:forEach var="justFollowMe" items="${suggestFlist2 }">
									<div class="card">
										<div class="card-body" style="font-size: 0.8rem; padding: 10px;">
											<img src="<%=context %>/profile_image/${justFollowMe.uimage}" class="rounded-circle" width="20"
												height="20">
												<a class="card-title text-dark">${justFollowMe.unickName}</a>
												<a class="card-subtitle mb-2 text-muted">@${justFollowMe.uatid}</a>
											<button type="button"
												class="btn btn-outline-success btn-sm float-right"
												style="font-size: 0.8rem;">팔로우</button>
										</div>
									</div>
								</c:forEach>
							</c:if>
							<!-- 팔로우하는 유저가 없을 경우 관심항목이 비슷한 사람을 추천 -->
							<c:if test="${suggestFlist2_size<1 }">
								<c:forEach var="justFollowMe" items="${suggestFlist2 }">
									<div class="card">
										<div class="card-body" style="font-size: 0.8rem; padding: 10px;">
											<img src="${resourcePath }/profile_image/${justFollowMe.uimage}" class="rounded-circle" width="20"
												height="20">
												<a class="card-title text-dark">${justFollowMe.unickName}</a>
												<a class="card-subtitle mb-2 text-muted">@${justFollowMe.uatid}</a>
											<button type="button"
												class="btn btn-outline-success btn-sm float-right"
												style="font-size: 0.8rem;">팔로우</button>
										</div>
									</div>
								</c:forEach>
							</c:if>
						</div>
					</div>
				</div> --%>
				<div class="list-group-item list-group-item-action bg-light"
					style="padding: 5px;">
					<div class="card bg-light mb-3">
						<div class="card-header">실시간 해시태그</div>
						<div class="card-body" style="padding: 5px;">
							<c:forEach var="tag" items="${tag_list}" varStatus="status">
								<c:if test="${status.count <=3 }">
									<div class="card">
										<div class="card-body" style="font-size: 0.8rem; padding: 10px;">
											${tag.hrank}위
											<div>
												<a href="#">#${tag.hname}</a> <span class="float-right">${tag.hcount }
													봄</span>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
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