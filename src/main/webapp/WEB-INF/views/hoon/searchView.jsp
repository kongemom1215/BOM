<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%String context = request.getContextPath();%>
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
 <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script> 
	<!-- <script src="https://code.jquery.com/jquery-3.5.1.js"></script> -->
	
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
</style>
<script type="text/javascript">
	
</script>
</head>

<body style="padding-top: 86px;">

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
				<form class="well form-search" action="searchView" method="get" id="jh_form" >
					<div class="input-group">
						<input type="text" ID="datebox" Class="form-control" name="search"
							data-toggle="dropdown" required="required" placeholder="봄 검색"></input>
				
						<table id="demolist" class="dropdown-menu" style="z-index: 5;">
							<tr>
								<td style="font-weight: normal;">최근</td>
								<td>
									<button type="reset" id="del_ajax" style="font-size: 12px;">전체지우기</button>
								</td>
							</tr>
							<c:forEach var="Junghun" items="${searchkeyword }" begin="0"
								end="10">
								<tr id="searchkeyword">
									<td class="dropdown-li" style="padding: 5px;"><c:choose>
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
					$("#del_ajax").click(function(){
								var ucode = ${loginUser.ucode}
								console.log('ucode !!: ' + ucode);
							$.ajax({
							url:"<%=context%>/deleteRow",
							data : {ucode : ucode},
							dataType : 'text',
							success : function(data) {
							alert(data);
							},
							error:function(request,status,error){
								alert("code-"+request.status+" mas-"+request.responseText+" error-"+error);
							}
						});
					});
			</script>
			<nav
				class="navbar navbar-expand-lg navbar-light bg-light border-bottom fixed-top"
				style="left: 241px; right: 241px; margin-top: 50px;  z-index: 3;  padding-bottom: 0px;">
				<ul class="nav nav-tabs nav-justified col-md-25" id="myTab"
					role="tablist" style="width: 800px;">
					<li class="nav-item mr-5" role="presentation"><a
						class="nav-link active" id="home-tab" data-toggle="tab"
						href="#fame" role="tab" aria-controls="fame" aria-selected="true">인기</a></li>

					<li class="nav-item mr-5" role="presentation"><a
						class="nav-link" id="profile-tab" data-toggle="tab" href="#user"
						role="tab" aria-controls="user" aria-selected="false">사용자</a></li>

					<li class="nav-item mr-5" role="presentation"><a
						class="nav-link" id="contact-tab" data-toggle="tab" href="#new"
						role="tab" aria-controls="new" aria-selected="false">최신글</a></li>

					<li class="nav-item mr-5" role="presentation"><a
						class="nav-link" id="contact-tab" data-toggle="tab"	href="#image" 
						role="tab" aria-controls="image" aria-selected="false">사진</a></li>

					<li class="nav-item mr-5" role="presentation"><a
						class="nav-link" id="contact-tab" data-toggle="tab"	href="#video" 
						role="tab" aria-controls="video" aria-selected="false">동영상</a></li>
				</ul>
			</nav>
			<p>
				<!--글 정렬-->
				
			<div class="tab-content" id="myTabContent">
				<!-- 인기  -->
				<div class="tab-pane fade show active" id="fame" role="tabpanel"
					aria-labelledby="home-tab">
					<c:if test="${listSearch.size() == 0 }">
						" ${search } " 검색된 글이 없습니다
					</c:if>
					<c:forEach var="junghun" items="${listSearch }">
						<div class="card">
							<div class="card-body">
								<button type="button" class="btn btn-light float-right">⋯</button>
								<img src="/img/teemo.jpg" class="rounded-circle" width="50"
									width="50"> <a class="card-title text-dark">${junghun.uNickName}</a>
								<a class="card-subtitle mb-2 text-muted">@${junghun.uatId}</a> <a
									class="card-subtitle mb-2 text-muted" style="font-size: 12px;">${junghun.bregdate }</a>
								<a href="#" class="card-text" style="margin-top: 10px;">${junghun.bcontent }
								</a>
								
								<div align="center">
									<div class="btn-group col-md-12" role="group"
										aria-label="Button group with nested dropdown">
										<button type="button" class="btn btn-secondary mr-3 btn-light"
											data-toggle="tooltip" data-placement="top" title="답글" style="font-size: 12px;">
											<img src="/img/speech-bubble.svg" width="20" height="20">
											&ensp;${junghun.breplycount }
										</button>
										<button type="button" class="btn btn-secondary btn-light mr-3"
											data-toggle="tooltip" data-placement="top" title="스크랩 or 인용" style="font-size: 12px;" >
											<img src="/img/bring.svg" width="20" height="20">
											&ensp;${junghun.bquotecount }
										</button>
										<button type="button" class="btn btn-secondary btn-light mr-3"
											data-toggle="tooltip" data-placement="top" title="좋아요" style="font-size: 12px;">
											<img src="/img/heart.svg" width="20" height="20">
											&ensp;${junghun.blikecount }
										</button>
										<button type="button"
											class="btn btn-secondary btn-light mr-3 dropdown-toggle caret-off"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">
											<img src="/img/share.svg" width="20" height="20">
										</button>
										<div class="dropdown-menu">
											<a class="dropdown-item" href="#">북마크 추가/삭제</a> <a
												class="dropdown-item" href="#">URL담아가기</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<!-- 사용자 -->
				<div class="tab-pane fade" id="user" role="tabpanel"
					aria-labelledby="profile-tab">
					<c:if test="${listUser.size() == 0 }">
						" ${search } " 검색된 사용자가 없습니다
					</c:if>
					<c:forEach var="junghun" items="${listUser }">
						<div class="card rounded">
							<div class="card-hover">
								<div class="card-body">
									<div class="row no-gutters">
										<img class="img-fluid rounded-circle" alt="Profile Picture"
											src="/img/teemo.jpg" style="width: 50px; height: 50px;" />
										<div style="margin-left: 10px; width: 90%">
											<a href="#" class="card-title text-dark"
												style="font-weight: bold;">${junghun.uNickName}</a>
											<button type="button"
												class="btn btn-outline-success btn-sm float-right"
												style="font-size: 0.8rem; float: right;">팔로우</button>
											<h6 class="card-title">@${junghun.uatId }</h6>
											<p>${junghun.uintro }</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<!-- 최신 -->
				<div class="tab-pane fade" id="new" role="tabpanel"
					aria-labelledby="contact-tab">
					<c:if test="${listNew.size() == 0 }">
						" ${search } " 검색된 글이 없습니다
					</c:if>
					<c:forEach var="junghun" items="${listNew }">
						<div class="card">
							<div class="card-body">
								<button type="button" class="btn btn-light float-right">⋯</button>
								<img src="/img/teemo.jpg" class="rounded-circle" width="50"
									width="50"> <a class="card-title text-dark">${junghun.uNickName}</a>
								<a class="card-subtitle mb-2 text-muted">${junghun.uatId}</a> <a
									class="card-subtitle mb-2 text-muted" style="font-size: 12px;">${junghun.bregdate }</a>
								<a href="#" class="card-text" style="margin-top: 10px;">${junghun.bcontent }</a>
								<div align="center">
									<div class="btn-group col-md-12" role="group"
										aria-label="Button group with nested dropdown">
										<button type="button" class="btn btn-secondary mr-3 btn-light"
											data-toggle="tooltip" data-placement="top" title="답글" style="font-size: 12px;">
											<img src="/img/speech-bubble.svg" width="20" height="20">
											&ensp;${junghun.breplycount }
										</button>
										<button type="button" class="btn btn-secondary btn-light mr-3"
											data-toggle="tooltip" data-placement="top" title="스크랩 or 인용" style="font-size: 12px;" >
											<img src="/img/bring.svg" width="20" height="20">
											&ensp;${junghun.bquotecount }
										</button>
										<button type="button" class="btn btn-secondary btn-light mr-3"
											data-toggle="tooltip" data-placement="top" title="좋아요" style="font-size: 12px;">
											<img src="/img/heart.svg" width="20" height="20">
											&ensp;${junghun.blikecount }
										</button>
										<button type="button"
											class="btn btn-secondary btn-light mr-3 dropdown-toggle caret-off"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">
											<img src="/img/share.svg" width="20" height="20">
										</button>
										<div class="dropdown-menu">
											<a class="dropdown-item" href="#">북마크 추가/삭제</a> <a
												class="dropdown-item" href="#">URL담아가기</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<!-- 사진 -->
				<div class="tab-pane fade" id="image" role="tabpanel"
					aria-labelledby="contact-tab">
					<c:if test="${junghun.battach == null}">
						" ${search } " 검색된 사진이 없습니다
					</c:if>
					<c:forEach var="junghun" items="${listSearch }">
						<div class="card">
						<c:if test="${junghun.battachType =='image' && junghun.battach != null}">
							<div class="card-body">
								<button type="button" class="btn btn-light float-right">⋯</button>
								<img src="/img/teemo.jpg" class="rounded-circle" width="50"
									width="50"> <a class="card-title text-dark">${junghun.uNickName}</a>
								<a class="card-subtitle mb-2 text-muted">@${junghun.uatId}</a> <a
									class="card-subtitle mb-2 text-muted" style="font-size: 12px">${junghun.bregdate }</a>
								<p class="card-text" style="margin-top: 10px;">${junghun.bcontent }</p>
									<c:if test="${junghun.battachType=='image' }">
										<img src="/img/media/${junghun.battachSrc }">
									</c:if>
								
								<div align="center">
									<div class="btn-group col-md-12" role="group"
										aria-label="Button group with nested dropdown">
										<button type="button" class="btn btn-secondary mr-3 btn-light"
											data-toggle="tooltip" data-placement="top" title="답글" style="font-size: 12px;">
											<img src="/img/speech-bubble.svg" width="20" height="20">
											&ensp;${junghun.breplycount }
										</button>
										<button type="button" class="btn btn-secondary btn-light mr-3"
											data-toggle="tooltip" data-placement="top" title="스크랩 or 인용" style="font-size: 12px;" >
											<img src="/img/bring.svg" width="20" height="20">
											&ensp;${junghun.bquotecount }
										</button>
										<button type="button" class="btn btn-secondary btn-light mr-3"
											data-toggle="tooltip" data-placement="top" title="좋아요" style="font-size: 12px;">
											<img src="/img/heart.svg" width="20" height="20">
											&ensp;${junghun.blikecount }
										</button>
										<button type="button"
											class="btn btn-secondary btn-light mr-3 dropdown-toggle caret-off"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">
											<img src="/img/share.svg" width="20" height="20">
										</button>
										<div class="dropdown-menu">
											<a class="dropdown-item" href="#">북마크 추가/삭제</a> <a
												class="dropdown-item" href="#">URL담아가기</a>
										</div>
									</div>
								</div>
							</div>
							</c:if>		
						</div>
					</c:forEach>
				</div>
				<!-- 동영상 -->
				<div class="tab-pane fade" id="video" role="tabpanel"
					aria-labelledby="contact-tab">
					<c:if test="${junghun.battach == null}">
						" ${search } " 검색된 동영상이 없습니다
					</c:if>
					<c:forEach var="junghun" items="${listSearch }">
						<div class="card">
						<c:if test="${junghun.battachType =='video' && junghun.battach != null}">
							<div class="card-body">
								<button type="button" class="btn btn-light float-right">⋯</button>
								<img src="/img/teemo.jpg" class="rounded-circle" width="50"
									width="50"> <a class="card-title text-dark">${junghun.uNickName}</a>
								<a class="card-subtitle mb-2 text-muted">@${junghun.uatId}</a> <a
									class="card-subtitle mb-2 text-muted" style="font-size: 12px">${junghun.bregdate }</a>
								<p class="card-text" style="margin-top: 10px;">${junghun.bcontent }</p>
									<c:if test="${junghun.battachType=='video' }">
										<img src="/img/media/${junghun.battachSrc }">
									</c:if>
								
								<div align="center">
									<div class="btn-group col-md-12" role="group"
										aria-label="Button group with nested dropdown">
										<button type="button" class="btn btn-secondary mr-3 btn-light"
											data-toggle="tooltip" data-placement="top" title="답글" style="font-size: 12px;">
											<img src="/img/speech-bubble.svg" width="20" height="20">
											&ensp;${junghun.breplycount }
										</button>
										<button type="button" class="btn btn-secondary btn-light mr-3"
											data-toggle="tooltip" data-placement="top" title="스크랩 or 인용" style="font-size: 12px;" >
											<img src="/img/bring.svg" width="20" height="20">
											&ensp;${junghun.bquotecount }
										</button>
										
										
										<button type="button" class="btn btn-secondary btn-light mr-3" id="searchLikeBtn${status.index }" 
										onclick="searchLikeBtn(${junghun.bcode},${status.index }); return false;"
										data-toggle="tooltip" data-placement="top" title="좋아요" style="font-size: 12px;">
											<img src="/img/heart.svg" width="20" height="20">
											&ensp;${junghun.blikecount }
										</button>
										
										
										<button type="button"
											class="btn btn-secondary btn-light mr-3 dropdown-toggle caret-off"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">
											<img src="/img/share.svg" width="20" height="20">
										</button>
										<div class="dropdown-menu">
											<a class="dropdown-item" href="#">북마크 추가/삭제</a> <a
												class="dropdown-item" href="#">URL담아가기</a>
										</div>
									</div>
								</div>
							</div>
							</c:if>		
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<!-- /#page-content-wrapper -->
<script type="text/javascript">
	function searchLikeBtn(bcode,btnIndex){
		event.stopPropagation();
		var index = btnIndex;
		var bcode = bcode;
		var msg = '게시글['+bcode+']에 좋아요를 눌렀습니다!';
		alert(msg);
		$.ajax({
			url : "<%=context%>/searchlike",
			data:{ bcode: bcode }, 
			dataType:'json',
			success : function(data){
				var str='';
				$('#searchLikeBtn'+index).empty();
				if(data.ltype==0||data.ltype==null)
					str += "<img src='/img/heart.svg' width='20' height='20'> " + data.likeCount
				if(data.ltype==1)
					str+= "<img src='/img/red_heart.svg' width='20' height='20'> "+ data.likeCount
				$('#searchLikeBtn'+index).append(str);
				alert(".ajax searchLikeBtn str->"+str);
			}
		});
	}
</script>
	<!-- 오른쪽 사이드바 -->
	<div class="bg-light border-left" id="sidebar-wrapper2">
		<div class="list-group list-group-flush">
		<!-- 검색창 -->
			<div class="list-group-item list-group-item-action bg-light">
				<div id="drop_the_text">
					<!-- 엔터치면 searchData() 실행 -->
					<form class="well form-search" action="searchView" method="get" id="jh_form" >
					<input class="form-control" id="search" placeholder="봄 검색" name="search"
						onkeypress="if( event.keyCode == 13 ){searchData();}">
						</form>
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
								<div class="card-body" style="font-size: 0.8rem; padding: 10px;">
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
	<!-- 오른쪽 사이드바 끝 -->
	<!-- /#wrapper -->
</body>

</html>