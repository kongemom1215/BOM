<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% String context = request.getContextPath(); %>
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
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.bundle.min.js"></script>
<script src="/js/bootstrap.bundle.js"></script>
<style>
.dropdown-toggle.caret-off::after {
	display: none;
}
.card-body:hover{
	background: #CBEBAD;
}
</style>
<script type="text/javascript">

function goProfile(){
	location.href = "../iron/profile?uatid="+${loginUser.uatid};
}

function goSingleBoard(bbcode,bindex){
	alert(bbcode+'로 이동합니다.');
	location.href = 'singleBoard?bcode='+bbcode;
}
function clickLikeBtn1(bbcode){
	event.stopPropagation();
	var bcode = bbcode;
	var msg = '게시글['+bcode+']에 좋아요를 눌렀습니다!';
	alert(msg);
	$.ajax({
		url : "<%=context%>/iron/AjaxLikeAction",
		data:{ bcode: bcode }, 
		dataType:'json',
		success : function(data){
			var str='';
			$('#likeBtn').empty();
			if(data.ltype==0||data.ltype==null)
				str += "<img src='/img/heart.svg' width='20' height='20'> " + data.likeCount
			if(data.ltype==1)
				str+= "<img src='/img/red_heart.svg' width='20' height='20'> "+ data.likeCount
			$('#likeBtn').append(str);
			alert(".ajax clickLikeBtn str->"+str);
		}
	});
}

function clickLikeBtn2(bbcode, bindex){
	event.stopPropagation();
	var index = bindex;
	var bcode = bbcode;
	var msg = '게시글['+bcode+']에 좋아요를 눌렀습니다!';
	alert(msg);
	$.ajax({
		url : "<%=context%>/iron/AjaxLikeAction",
		data:{ bcode: bcode }, 
		dataType:'json',
		success : function(data){
			var str='';
			$('#likeBtn2'+index).empty();
			if(data.ltype==0||data.ltype==null)
				str += "<img src='/img/heart.svg' width='20' height='20'> " + data.likeCount
			if(data.ltype==1)
				str+= "<img src='/img/red_heart.svg' width='20' height='20'> "+ data.likeCount
			$('#likeBtn2'+index).append(str);
			alert(".ajax clickLikeBtn2 str->"+str);
		}
	});
}

function viewBoardOptions(bbcode,bindex){
	event.stopPropagation();
	var index = bindex;
	var bcode = bbcode;
	var msg = '게시글['+bcode+']의 옵션을 눌렀습니다!';
	alert(msg);
	$.ajax({
		url : "<%=context%>/iron/AjaxViewBoardOptions",
		data:{ bcode: bcode }, 
		dataType:'json',
		success : function(data){
			var str='';
			$('#boardDropdownOption').empty();
			if(data.bbtype==0||data.bbtype==null)
				str += "<a class='dropdown-item' onclick=bookmarkAction();> 북마크추가</a>"
			if(data.bbtype==1)
				str += "<a class='dropdown-item' onclick=bookmarkAction();> 북마크 삭제</a>"
			$('#boardDropdownOption').append(str+"<a class='dropdown-item' href='#'>URL담아가기</a>");
			alert(".ajax viewBoardOptions str->"+str);
		}
	});
}
</script>
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
				</a>
				
				<a href="bom" class="list-group-item list-group-item-action">
					<button type="button" class="btn btn-outline-success">
						<img src="/img/write.svg" width="15" height="15"> 글 쓰기
					</button>
				</a>
				
				
				<div class="card">
					<div class="card-body">
						<img src="<%=context %>/profile_image/${user.uimage}" alt="no_image" class="rounded-circle" width="50"
							width="50"> <a class="card-title text-dark">${user.unickName }</a> <a
							class="card-subtitle mb-2 text-muted">@${user.uatid }</a>
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
				<button class="btn btn-success" id="menu-toggle" onclick="history.back()">←</button>
			</nav>
						
			<div class="container-fluid">
				<!-- 단일 게시글 내용 출력 -->
				<div class="card">
					<div class="card-body">
						<button type="button" class="btn btn-light float-right dropdown-toggle caret-off">⋯</button>
							<div class="dropdown-menu">
									<%-- <c:if test="${tl_element.loginUcode == user.ucode }">
											<a class="dropdown-item" href="#">봄 삭제</a>
									</c:if> --%>
									<a class="dropdown-item" href="#">봄 신고</a>
									<a class="dropdown-item" href="#">봄 분석</a>
							</div>
						<img src="<%=context %>/profile_image/${board.uimage }" alt="no_image" class="rounded-circle" width="50"
							width="50"> <a class="card-title text-dark">${board.unickName }</a>
						 <a class="card-subtitle mb-2 text-muted">@${board.uatid }</a>		
						 <a	class="card-subtitle mb-2 text-muted">${board.bregDate }</a>
							 <p class="card-text" style="margin-top: 10px;">${board.bcontent }</p>
							 <c:if test="${board.battach!=null }">
							 	<c:if test="${board.battachType=='image'}">
							 		<img class="img-thumnail" width="300" src="<%=context %>/image/${board.battachSrc}"/>
							 	</c:if>
							 	<c:if test="${board.battachType=='video'}">
							 		<video controls width="300">
							 			<source  src="<%=context %>/video/${board.battachSrc}" type="video/mp4">
							 			<source  src="<%=context %>/video/${board.battachSrc}" type="video/webm">
							 			해당 브라우저에는 지원하지 않는 비디오입니다.
							 		</video>
							 	</c:if>
							 </c:if>
						<div align="center">
							<div class="btn-group col-md-12" role="group"
								aria-label="Button group with nested dropdown">
								<button type="button" class="btn btn-secondary mr-3 btn-light"
									data-toggle="tooltip" data-placement="top" title="답글 ">
									<img src="/img/speech-bubble.svg" width="20" height="20"> ${board.breplyCount }
								</button>
								<button type="button" class="btn btn-secondary btn-light mr-3"
									data-toggle="tooltip" data-placement="top" title="스크랩 or 인용">
									<img src="/img/bring.svg" width="20" height="20"> ${board.bquoteCount }
								</button>
								<button id="likeBtn" type="button" class="btn btn-secondary btn-light mr-3"
									data-toggle="tooltip" data-placement="top" title="좋아요" onclick="clickLikeBtn1(${board.bcode});">
									<c:if test="${board.ltype == 0 || board.ltype == null }">
										<img src="/img/heart.svg" width="20" height="20"> ${board.blikeCount }
									</c:if>
									<c:if test="${board.ltype == 1 }">
										<img src="/img/red_heart.svg" width="20" height="20"> ${board.blikeCount }
									</c:if>
								</button>
								<button type="button"
									class="btn btn-secondary btn-light mr-3 dropdown-toggle caret-off"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">
									<img src="/img/share.svg" width="20" height="20">
								</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">북마크 추가/삭제</a>
									<a class="dropdown-item" href="#">URL담아가기</a>
								</div>
							</div>
						</div>
						
					</div>
				</div>
				
				<!-- 단일 게시글 댓글 내용 출력 -->
				<c:forEach var="reply" items="${replylist }" varStatus="status">
					<div class="card">
						<div class="card-body" id="singleBoard" onclick="goSingleBoard(${reply.bcode},${status.index });">
							<button type="button" class="btn btn-light dropdown-toggle caret-off float-right">⋯</button>
							<div class="dropdown-menu">
										<%-- <c:if test="${tl_element.loginUcode == user.ucode }">
												<a class="dropdown-item" href="#">봄 삭제</a>
										</c:if> --%>
										<a class="dropdown-item" href="#">봄 신고</a>
										<a class="dropdown-item" href="#">봄 분석</a>
							</div>
							<img src="<%=context %>/profile_image/${reply.uimage }" alt="no_image" class="rounded-circle" width="50"
								width="50"> <a class="card-title text-dark">${reply.unickName }</a>
								 <a class="card-subtitle mb-2 text-muted">@${reply.uatid }</a>		
								 <a	class="card-subtitle mb-2 text-muted">${reply.bregDate }</a>
								 <p class="card-text" style="margin-top:10px;" onclick="goSingleBoard(${reply.bcode}, ${reply_ucode});">${reply.bcontent }</p>
								 <c:if test="${reply.battach!=null }">
								 	<c:if test="${reply.battachType=='image'}">
								 		<img class="img-thumnail" width="300" src="<%=context %>/image/${reply.battachSrc}"/>
								 	</c:if>
								 	<c:if test="${reply.battachType=='video'}">
								 		<video controls width="300">
								 			<source  src="<%=context %>/video/${reply.battachSrc}" type="video/mp4">
								 			<source  src="<%=context %>/video/${reply.battachSrc}" type="video/webm">
								 			해당 브라우저에는 지원하지 않는 비디오입니다.
								 		</video>
								 	</c:if>
								 </c:if>
							<div align="center">
								<div class="btn-group col-md-12" role="group"
									aria-label="Button group with nested dropdown">
									<button type="button" class="btn btn-secondary mr-3 btn-light"
										data-toggle="tooltip" data-placement="top" title="답글 ">
										<img src="/img/speech-bubble.svg" width="20" height="20"> ${reply.breplyCount }
									</button>
									<button type="button" class="btn btn-secondary btn-light mr-3"
										data-toggle="tooltip" data-placement="top" title="스크랩 or 인용">
										<img src="/img/bring.svg" width="20" height="20"> ${reply.bquoteCount }
									</button>
									
									
									<button id="likeBtn2${status.index }" type="button" class="btn btn-secondary btn-light mr-3"
										data-toggle="tooltip" data-placement="top" title="좋아요" onclick="clickLikeBtn2(${reply.bcode},${status.index }); return false;">
										
										<c:if test="${reply.ltype == 0 || reply.ltype == null }">
											<img src="/img/heart.svg" width="20" height="20"> ${reply.blikeCount }
										</c:if>
										<c:if test="${reply.ltype == 1 }">
											<img src="/img/red_heart.svg" width="20" height="20"> ${reply.blikeCount }
										</c:if>
										
									</button>
									
									<button type="button"
										class="btn btn-secondary btn-light mr-3 dropdown-toggle caret-off"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false" id="boardOption${status.index }" onclick="viewBoardOptions(${bcode},${status.index }); return false;">
										<img src="/img/share.svg" width="20" height="20">
									</button>
									<!-- ajax -->
									<div class="dropdown-menu" id="boardDropdownOption${status.index }">
										<%-- 
										<c:if test="${reply.bbtype==1 }">
											<a class="dropdown-item" href="#">북마크 삭제</a>
										</c:if>
										<c:if test="${reply.bbtype==0 || reply.bbtype==null}">
											<a class="dropdown-item" href="#">북마크 추가</a>
										</c:if>
											<a class="dropdown-item" href="#">URL담아가기</a>
										 --%>	
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
		<div class="bg-light border-left" id="sidebar-wrapper2">
			<div class="list-group list-group-flush">
				<div class="list-group-item list-group-item-action bg-light">
					<div id="drop_the_text">
						<!-- 엔터치면 searchData() 실행 -->
						<input  class="form-control" id="search" placeholder="봄 검색"
							onkeypress="if( event.keyCode == 13 ){searchData();}">
					</div>
				</div>
				<div class="list-group-item list-group-item-action bg-light"
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
											<img src="/img/profile_image/${justFollowMe.uimage}" class="rounded-circle" width="20"
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
				</div>
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