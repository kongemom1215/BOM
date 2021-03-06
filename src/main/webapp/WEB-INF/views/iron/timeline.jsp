<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% 
	String context = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">

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
<!-- <script src="/js/jquery.min.js"></script> -->
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

#bearsize {
	width: 550px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	text-align: left;
}

.dropdown-toggle.caret-off::after {
	display: none;
}

a:hover {
	text-decoration: none;
	cursor: pointer;
}

.card-text:hover {
	color: black;
	text-decoration: none;
}
</style>
<style>
.dropdown-toggle.caret-off::after {
	display: none;
	resize: none;
}

div#writeTextarea {
	min-height: 50px;
	border: none;
	resize: none;
}

div#writeTextarea:focus {
	outline: none;
	box-shadow: none;
	-webkit-box-shadow: none;
}

select.custom-select {
	margin-top: 10px;
}

select.custom-select:focus {
	outline: none;
	box-shadow: none;
	-webkit-box-shadow: none;
}
/*placeholder 설정하기*/
[contenteditable=true]:empty:before {
	content: attr(placeholder);
	display: block; /* For Firefox */
}

input[type="file"] {
	position: absolute;
	width: 1px;
	height: 1px;
	padding: 0;
	margin: -1px;
	overflow: hidden;
	clip: rect(0, 0, 0, 0);
	border: 0;
}

label {
	margin: 0px;
}

.nav-tabs .nav-link:not(.active) {
	border-color: transparent !important;
	color: #28a745;
}
</style>
<!-- Like Ajax Fuction -->
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	
	function goProfile(){
		event.stopPropagation();
		location.href = "../iron/profile?uatid="+${user.uatid};
	}
	
	window.onload = function(){	//주혜
		clickWriteBtn();
		clickSaveBtn();
		getFollower('<%=context %>');
	}
	
	function goSingleBoard(bbcode,bindex){
		alert(bbcode+'로 이동합니다.');
		location.href = 'singleBoard?bcode='+bbcode;
	}
	
	function clickLikeBtn(bcode,index, user){
		event.stopPropagation();
		$.ajax({
			url : "<%=context%>/god/AjaxLikeAction",
			data:{ bcode: bcode, ucode:user}, 
			dataType:'json',
			success : function(data){
				if(data.ltype==0){
					$("#noheart"+index).css("display","none");
					$("#doheart"+index).css("display","block");
				}
				else if(data.ltype==1){
					$("#noheart"+index).css("display","block");
					$("#doheart"+index).css("display","none");
				}
				$("#likecount"+index).text(data.likeCount);
			}
		});
	}
	
	function doBookmark(bbcode,bindex){
		event.stopPropagation();
		var index = bindex;
		var bcode = bbcode;
		var msg = '북마크를 추가하시겠습니까?';
		if (confirm(msg) == true){    //확인
			location.href="doBookmark?bcode="+bcode;
		}
		else{   //취소
		    return;
		}
	}
	
	//팔로우 추천 가져가야할 
	//팔로우 추천 더보기 닫기 기능
	function closemodal(){
		location.reload();
	}
	//팔로우 하는 로직
	function followchk(number){
		//name 에 k + number 쓰는 태그를찾아서 text변경
		var textareaVal = $("button[name=k"+number+"]").text();
		console.log("textareaVal + textareaVal" + textareaVal)
		var msg = { uopcode :number};
		$.ajax({
			url: '<%=context%>/bear/followchk',
			data: msg,
			type: "post",
			success: function (res) {
				console.log("저장성공 - > " +res)
				if(res == "1"){
					console.log("저장성공")
					  $("button[name=k"+number+"]").text("팔로잉");
					  $("button[name=k"+number+"]").attr("class","btn btn-success btn-sm float-right");
					  $("button[name=k"+number+"]").attr("onclick","unfollow("+number+")");
				}else 
					{console.log("저장실패")}
			}
		});	 
	}
	//언팔로우 
	function unfollow(number){
		console.log("언팔로우 시작  number -> " + number);
		var msg = {fopcode  : number};
		$.ajax({
			url: '<%=context%>/bear/unfollow',
			data: msg,
			type: "post",
			success: function (res){
				if(res == 1 ){
					console.log("저장성공 - > " +res);
					 $("button[name=k"+number+"]").text("언팔함");
					  $("button[name=k"+number+"]").attr("class","btn btn-danger btn-sm float-right");
					  $("button[name=k"+number+"]").attr("onclick","followchk("+number+")");					
				}else{					
					alert("삭제하지못했습니다.");
				}				
			}			
		});
	}
	
	//경빈
	//글 삭제 하는 로직
	function deleteBom(loginUcode, bUcode, sIndex){
		event.stopPropagation();
		/* alert('loginUcode->'+loginUcode);
		alert('bUcode->'+bUcode);
		alert('sIndex->'+sIndex); */
		if(loginUcode == bUcode){
			if (confirm("정말 삭제하시겠습니까?") == true){    //확인
				$('.coffeeDeleteBom'+sIndex).submit();
			}else{   //취소
			    return;
			} 
		}else{
			alert("타인의 글은 삭제할 수 없습니다.");
			return;
		} 
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
				</a>
				<!-- 
				<a href="alarm" class="list-group-item list-group-item-action">
					<img src="/img/bell.svg" width="15" height="15"> 알림 <span
					class="badge badge-success">1</span>
				</a>
				 -->
				<!-- bear1 -->
				<a href="/bear/chat" class="list-group-item list-group-item-action">
					<img src="/img/send.svg" width="15" height="15"> 쪽지
				</a> <a href="/yeah/bookmark"
					class="list-group-item list-group-item-action"> <img
					src="/img/bookmark.svg" width="15" height="15"> 북마크
				</a> <a href="/iron/profile?uatid=${user.uatid }"
					class="list-group-item list-group-item-action"> <img
					src="/img/user.svg" width="15" height="15"> 프로필
				</a> <a href="/right/moreSee"
					class="list-group-item list-group-item-action"> <img
					src="/img/more.svg" width="15" height="15"> 더보기
				</a>

				<div class="list-group-item list-group-item-action">
					<!--주혜 -->
					<button type="button" class="btn btn-outline-success" id="writeBtn"
						data-toggle="modal" data-target="#writeForm">
						<img src="/img/write.svg" width="15" height="15"> 글 쓰기
					</button>
				</div>

				<div class="card">
					<div class="card-body">
						<div class="form-row">
							<img src="<%=context %>/profile_image/${user.uimage}"
								alt="no_image" class="rounded-circle" width="50">
							<div class="form-col ml-2">
								<a class="card-title text-dark" style="font-size: 0.8em">${user.unickName }</a><br>
								<a class="card-subtitle mb-2 text-muted"
									style="font-size: 0.8em">@${user.uatid }</a>
							</div>
						</div>
					</div>
					<button type="button" class="btn btn-success"
						onclick="location.href='../coffee/logout'">로그아웃</button>
				</div>
			</div>
		</div>

		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">

			<nav
				class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
				<button class="btn btn-success" id="menu-toggle"
					onclick="history.back(); return false;">←</button>
			</nav>
			<!--
			<div class="alert alert-success" role="alert">
				<a href="#" class="alert-link">좋아요</a>를 누르셨습니다.
			</div>
			-->
			<div class="container-fluid">
				<p>
					<!-- 기본기능 완성 후 업그레이드 후보
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
				 -->
					<!--글 정렬-->

					<c:forEach var="tl_element" items="${tl_list }" varStatus="status">
						<div class="card">
							<!-- 경빈 part -->
							<%-- <div class="card-body" id="singleBoard"
								onclick="goSingleBoard(${tl_element.bcode},${status.index });"> --%>
							<div class="card-body" id="singleBoard"
								onclick="goSingleBoard(${reply.bcode},${status.index });">
								<form action="/coffee/deleteBom_timeline"
									class="coffeeDeleteBom${status.index }" method="post">
									<input type="hidden" name="coffeeBoardBcode"
										value="${tl_element.bcode }"> <input type="hidden"
										name="coffeeBoardUatid" value="${tl_element.uatid }">
									<button type="button" class="btn btn-light float-right"
										onclick="deleteBom(${user.ucode}, ${tl_element.ucode }, ${status.index })">
										<img src="/img/coffee/trash.svg" width="15" height="15">
									</button>
									<%-- <button type="button"
											class="btn btn-light dropdown-toggle caret-off float-right">⋯</button>
										
										<div class="dropdown-menu">
											<c:if test="${tl_element.ucode == user.ucode }">
												
											</c:if>
										</div> --%>
								</form>
								<!-- 경빈 part 끝 -->
								<img src="<%=context %>/profile_image/${tl_element.uimage }"
									alt="no_image" class="rounded-circle" width="50" width="50">
								<a class="card-title text-dark">${tl_element.unickName }</a> <a
									class="card-subtitle mb-2 text-muted">@${tl_element.uatid }</a>
								<a class="card-subtitle mb-2 text-muted">${tl_element.bregDate }</a>
								<p class="card-text" style="margin-top: 10px;"
									onclick="goSingleBoard(${tl_element.bcode}, ${tl_element_ucode});">${tl_element.bcontent }

									<!-- 우선 인용문이 있을 때-->
									<c:if test="${tl_element.btype=='quote' }">
										<div class="col-12 float-left" id="QuoteArea"
											style="font-size: 0.8em;">
											<div class='card'>
												<div class='card-body'>
													<img id="quote_profile"
														src="<%=context %>/profile_image/${tl_element.q_uimage}"
														alt='no_image' class='rounded-circle' width='30'> <a
														class='card-title text-dark' id="quote_nickname">${tl_element.q_nickname}</a>
													<a class='card-subtitle mb-2 text-muted' id="quote_atid">${tl_element.q_atid}</a>
													<a class='card-subtitle mb-2 text-muted' id="quote_regdate">${tl_element.q_regdate}</a>
													<p class='card-text mt-2 mb-0' style="height: 100%;"
														id="quote_content">${tl_element.q_content}</p>
													<c:if test="${tl_element.q_attach != ''}">
														<div class="quote_file mt-2">
															<c:if test="${tl_element.q_attachtype eq 'image'}">
																<img id="quote_img"
																	src="<%=context %>/image/${tl_element.q_attachsrc }"
																	class="img-fluid" />
															</c:if>
															<c:if test="${tl_element.q_attachtype eq 'video'}">
																<div id="show_quote_video"
																	class="embed-responsive embed-responsive-16by9">
																	<video controls id="quote_video"
																		src="<%=context %>/video/${tl_element.q_attachsrc }">
																	</video>
																</div>
															</c:if>
														</div>
													</c:if>

												</div>
											</div>
										</div>
									</c:if>

								</p>
								<c:if test="${tl_element.battach!=null }">
									<c:if test="${tl_element.battachType=='image'}">
										<img class="img-fluid"
											src="<%=context %>/image/${tl_element.battachSrc}" />
									</c:if>
									<c:if test="${tl_element.battachType=='video'}">
										<div class="embed-responsive embed-responsive-16by9">
											<video controls>
												<source src="<%=context %>/video/${tl_element.battachSrc}"
													type="video/mp4">
												<source src="<%=context %>/video/${tl_element.battachSrc}"
													type="video/webm">
												해당 브라우저에는 지원하지 않는 비디오입니다.
											</video>
										</div>
									</c:if>
								</c:if>
								<div align="center">
									<div class="btn-group col-md-12" role="group"
										aria-label="Button group with nested dropdown">
										<!-- 답글 -->
										<button type="button"
											class="replySetting btn btn-secondary mr-3 btn-light"
											data-toggle="modal" data-target="#writeForm"
											onclick="reply_click('${tl_element.bcode}','${tl_element.uatid }');">
											<img src="/img/speech-bubble.svg" width="20" height="20">
											<c:if test="${tl_element.breplyCount ne 0}">
												${tl_element.breplyCount }
											</c:if>
										</button>

										<!-- 인용 -->
										<button type="button"
											onclick="scrap_click('${tl_element.bcode}','${status.index }','${tl_element.unickName }','${tl_element.uatid }','<%=context %>/profile_image/${tl_element.uimage }','${tl_element.battachType}','${tl_element.battachSrc}','<%=context %>');"
											class="scrapSetting btn btn-secondary mr-3 btn-light"
											data-toggle="modal" data-target="#writeForm">
											<input type="hidden" value="${tl_element.bcontent}"
												id="tagContent${status.index}"> <img
												src="/img/bring.svg" width="20" height="20">
											<c:if test="${tl_element.bquoteCount ne 0}">
												${tl_element.bquoteCount }
											</c:if>
										</button>

										<!--좋아요 버튼 -->
										<button id="likeBtn${status.index }" type="button"
											class="btn btn-secondary btn-light mr-3"
											data-toggle="tooltip" data-placement="top" title="좋아요"
											onclick="clickLikeBtn(${tl_element.bcode},${status.index }, ${user.ucode});">
											<div class="form-row justify-content-center text-center">
												<img src="/img/heart.svg" width="20" height="20"
													id="noheart${status.index }" style="display: none;">
												<img src="/img/red_heart.svg" width="20" height="20"
													id="doheart${status.index }" style="display: none;">
												<span id="likecount${status.index }" class="ml-1">${tl_element.blikeCount }</span>
												<c:if
													test="${tl_element.ltype == 0 || tl_element.ltype == null }">
													<script type="text/javascript">
													$("#noheart"+${status.index }).css("display","block");
													$("#doheart"+${status.index }).css("display","none");
												</script>
												</c:if>
												<c:if test="${tl_element.ltype == 1 }">
													<script type="text/javascript">
													$("#noheart"+${status.index }).css("display","none");
													$("#doheart"+${status.index }).css("display","block"); 
												</script>
												</c:if>
											</div>
										</button>

										<button type="button" class="btn btn-secondary btn-light mr-3"
											aria-expanded="false" id="boardOption${status.index }"
											onclick="doBookmark(${tl_element.bcode},${status.index });">
											<img src="/img/share.svg" width="20" height="20">
										</button>
										<!-- ajax -->
										<div class="dropdown-menu"
											id="boardDropdownOption${status.index }">
											<%-- 
										<c:if test="${tl_element.bbtype==1 }">
											<a class="dropdown-item" href="#">북마크 삭제</a>
										</c:if>
										<c:if test="${tl_element.bbtype==0 || tl_element.bbtype==null}">
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
		<!-- 사이드바 팔로우 가져가야할 구간 시작 -->
		<div class="bg-light border-left" id="sidebar-wrapper2">
			<div class="list-group list-group-flush">
				<!-- 사이드바검색 시작-->
				<div class="list-group-item list-group-item-action bg-light">
					<div id="drop_the_text">
						<!-- 엔터치면 searchData() 실행 -->
						<form class="well form-search" action="/hoon/searchView"
							method="get" id="jh_form">
							<input class="form-control" id="search" placeholder="봄 검색"
								name="search"
								onkeypress="if( event.keyCode == 13 ){searchData();}">
						</form>
					</div>
				</div>
				<!-- 사이드바검색 끝-->
				<div class="list-group-item list-group-item-action bg-light"
					style="padding: 5px;">
					<div class="card bg-light mb-3">
						<div class="card-header">팔로우 추천</div>
						<div class="card-body" style="padding: 5px;">
							<c:if test="${suggestFlist2_size>0 }">
								<c:forEach var="justFollowMe" items="${suggestFlist2 }"
									begin="0" end="2">
									<div class="card">
										<div class="card-body"
											style="font-size: 0.8rem; padding: 10px;">
											<img src="<%=context %>/profile_image/${justFollowMe.uimage}"
												class="rounded-circle" width="20" height="20"> <a
												class="card-title text-dark">${justFollowMe.unickName}</a> <a
												class="card-subtitle mb-2 text-muted"
												href="/iron/profile?uatid=${justFollowMe.uatid}">@${justFollowMe.uatid}</a>
											<button type="button"
												class="btn btn-outline-success btn-sm float-right"
												style="font-size: 0.8rem;"
												onclick="followchk(${justFollowMe.uucode})"
												name=k${justFollowMe.uucode}>팔로우</button>
										</div>
									</div>
								</c:forEach>
							</c:if>
							<%--
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
							</c:if> --%>
						</div>
						<c:if test="${suggestFlist2_size>0 }">
							<button type="button" class="btn btn-outline-success"
								data-toggle="modal" data-target="#morebtn">더보기</button>
						</c:if>
					</div>
				</div>
				<!-- 사이드바 팔로우 가져가야할 구간 끝 -->

				<div class="list-group-item list-group-item-action bg-light"
					style="padding: 5px;">
					<div class="card bg-light mb-3">
						<div class="card-header">해시태그 순위</div>
						<div class="card-body" style="padding: 5px;">
							<c:forEach var="tag" items="${tag_list}" varStatus="status">
								<c:if test="${status.count <=3 }">
									<div class="card">
										<div class="card-body"
											style="font-size: 0.8rem; padding: 10px;">
											${tag.hrank}위
											<div>
												<a href="/hoon/searchView?search=%23${tag.hname}">#${tag.hname}</a>
												<span class="float-right">${tag.hcount } 봄</span>
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

	<!--GOD 글쓰기 팝업 시작-->
	<form action="../god/write" method="post" enctype="Multipart/form-data"
		onsubmit="return checkWrite()">
		<!--실제 값을 보내는곳 -->
		<input type="hidden" name="ucode" value="${user.getUcode() }">
		<input type="hidden" name="savebcode"> <input type="hidden"
			name="bcontent"> <input type="hidden" name="bregdate">
		<input type="hidden" name="btype"> <input type="hidden"
			name="banchor" value="0"> <input type="hidden" name="image">
		<input type="hidden" name="video"> <input type="hidden"
			name="vote"> <input type="hidden" name="save"> <input
			type="hidden" name="bsaveorrsvd"> <input type="hidden"
			name="bbcode"> <input type="hidden" name="hashtags">
		<!--실제 값을 보내는곳 끝 -->
		<div class="modal fade" id="writeForm" data-backdrop="static"
			data-keyboard="false" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-scrollable">
				<div class="modal-content">
					<div class="modal-header">
						<fieldset class="w-100">
							<button type="button" id="selectToAtid" class="btn btn-light"
								data-toggle="modal" data-target="#towhom">받는 사람</button>
							<button type="button" id="realCloseWrite" class="close"
								data-dismiss="modal" style="display: none;"></button>
							<button type="button" id="closeWrite" class="close"
								style="float: right;" data-toggle="modal"
								data-target="#saveModal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<a style="float: right; color: black; text-decoration: none;"
								data-toggle="modal" data-target="#tempForm">임시 저장한 봄 <span
								class="badge badge-success" id="sumNum"></span>
							</a>
						</fieldset>
					</div>
					<div class="modal-body col-12">
						<!-- 인용부분 -->
						<div class="col-12 float-left" id="QuoteArea2"
							style="display: none; font-size: 0.8em;">
							<div class='card'>
								<div class='card-body'>
									<img id="quote_profile2" src="" alt='no_image'
										class='rounded-circle' width='30'> <a
										class='card-title text-dark' id="quote_nickname2"></a> <a
										class='card-subtitle mb-2 text-muted' id="quote_atid2"></a>
									<div class='card-text mt-2 mb-0' style="height: 100%;"
										id="quote_content2"></div>
									<div class="quote_file2 mt-2" style="display: none;">
										<img id="quote_img2" src="<%=context %>" class="img-fluid" />
										<div id="show_quote_video2"
											class="embed-responsive embed-responsive-16by9">
											<video controls id="quote_video2" src="<%=context %>">
											</video>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!--인용부분 끝 -->
						<!--답글 부분-->
						<div id="ReplyArea" style="display: none; font-size: 0.8em;">
							<a id="toreply" style="color: #B2B2B2"></a>에게 보내는 답글
						</div>
						<!--답글부분 끝-->
						<img src="<%=context %>/profile_image/${user.uimage}"
							class="rounded-circle col-1 float-left mt-3"
							style="padding: 0px;" width="50" width="50">
						<div contentEditable="true" id="writeTextarea"
							class="col-11 float-left mt-3" placeholder="당신의 이야기를 들려주세요."
							autofocus></div>
						<!--투표-->
						<div class="card" id="vote_form" style="display: none;">
							<ul class="list-group list-group-flush">
								<li class="list-group-item">
									<div class="form-group">
										<input type="text" name="vselect1" class="form-control"
											placeholder="선택1">
									</div>
									<div class="form-group">
										<input type="text" name="vselect2" class="form-control"
											placeholder="선택2">
									</div>
									<div class="form-group" id="pick3" style="display: none;">
										<input type="text" name="vselect3" class="form-control"
											placeholder="선택3(선택 사항)">
									</div>
									<div class="form-group" id="pick4" style="display: none;">
										<input type="text" name="vselect4" class="form-control"
											placeholder="선택4(선택 사항)">
									</div>
									<button type="button" id="plusSelect"
										class="btn btn-outline-secondary btn-sm">+</button>
								</li>
								<li class="list-group-item">
									<!--복수선택 on / null 로 전달 -->
									<div class="form-check">
										<input class="form-check-input" type="checkbox"
											id="multipleChk" name="multipleChk"> <label
											class="form-check-label" for="multipleChk"> 중복 투표 </label>
									</div>
								</li>
								<li class="list-group-item">
									<h5 class="card-title">투표 기간</h5>
									<div class="form-row">
										<label for="day" class="col-md-3 mr-5 ml-2">일</label> <label
											for="hour" class="col-md-3 mr-5">시</label> <label for="min"
											class="col-md-3">분</label>
									</div>
									<div class="form-row">
										<select id="date" name="date"
											class="form-control col-md-3 mr-5 ml-2" required="required">
											<c:forEach var="i" begin="0" end="7" step="1">
												<c:if test="${i eq '1'}">
													<option selected="selected">${i}</option>
												</c:if>
												<c:if test="${i ne '1'}">
													<option>${i}</option>
												</c:if>
											</c:forEach>
										</select> <select name="hour" id="hour"
											class="form-control col-md-3 mr-5" required="required">
											<c:forEach var="i" begin="0" end="23" step="1">
												<option>${i}</option>
											</c:forEach>
										</select> <select name="min" id="min" class="form-control col-md-3"
											required="required">
											<c:forEach var="i" begin="0" end="59" step="1">
												<option>${i}</option>
											</c:forEach>
										</select>
									</div>
								</li>
							</ul>
						</div>
						<!--파일첨부 -->
						<div class="select_file">
							<img id="image" src="" class="img-fluid" />
							<div class="embed-responsive embed-responsive-16by9"
								style="display: none;">
								<video id="video">
								</video>
							</div>
						</div>
						<!--예약시간표시 -->
						<div id="setTime" class="text-success" style="font-size: 0.8em">

						</div>
						<!--답글권한 select-->
						<select name="bpermission" class="custom-select custom-select-sm">
							<option value="All" selected>모든 사람이 답글 권한을 가집니다</option>
							<option value="Follower">내가 팔로우하는 사람들만 답글 권한을 가집니다</option>
							<option value="Nobody">아무도 답글 권한이 없습니다</option>
						</select>
					</div>
					<div class="modal-footer">
						<fieldset class="w-100">
							<span class="btn-group" role="group" aria-label="Basic example">
								<button type="button" id="mediaDelete" class="btn btn-danger"
									style="display: none;">미디어 삭제</button> <label id="media"
								class="btn btn-outline-secondary rounded-left" for="media_file">미디어</label>
								<input name="attach" type="file" class="custom-file-input"
								accept="video/*, image/*" id="media_file">
								<button type="button" id="displayVote"
									class="btn btn-outline-secondary">투표</button>
								<button type="button" id="voteDelete" class="btn btn-danger"
									style="display: none;">투표 삭제</button>
								<button type="button" class="btn btn-outline-secondary"
									id="reserveBtn" data-toggle="modal" data-target="#reserveForm">예약하기</button>
							</span>
							<button type="submit" id="writeSubmit"
								class="btn btn-success float-right" disabled>봄</button>
						</fieldset>
					</div>
				</div>
			</div>
		</div>
		<!--GOD 글쓰기 팝업 끝-->
		<!--GOD 예약 창 시작-->
		<div class="modal" id="reserveForm" data-backdrop="static"
			data-keyboard="false" tabindex="-1"
			aria-labelledby="exampleModalLabel2" aria-hidden="true"
			backdrop="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">봄 예약하기</h5>
						<div class="form-row">
							<button type="button" id="reserveDelete"
								class="btn btn-light btn-sm mr-1" style="display: none;">지우기</button>
							<button type="button" id="reserveChk"
								class="btn btn-success btn-sm float-right">예약</button>
						</div>
					</div>
					<div id="checkTime" class="alert alert-danger" role="alert">
						날짜가 맞지 않습니다. 다시 확인해 주세요.</div>
					<div class="modal-body col-12">
						<h5 class="card-title">
							날짜 <span id="alertTime"></span>
						</h5>
						<div class="form-row">
							<label for="year" class="col-md-3 mr-5 ml-2">년</label> <label
								for="month" class="col-md-3 mr-5">월</label> <label for="day2"
								class="col-md-3">일</label>
						</div>
						<div class="form-row">
							<!--올해 계산 -->
							<%
								SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
								Date d = new Date();

								int year = Integer.parseInt(sdf1.format(d));
							%>
							<select name="year" id="year"
								class="form-control col-md-3 mr-5 ml-2" required="required">
								<option value="<%=year%>년" selected="selected"><%=year%></option>
								<option value="<%=year + 1%>년"><%=year + 1%></option>
								<option value="<%=year + 2%>년"><%=year + 2%></option>
							</select> <select name="month" id="month"
								class="form-control col-md-3 mr-5" required="required">
								<option value="01월">1</option>
								<option value="02월">2</option>
								<option value="03월">3</option>
								<option value="04월">4</option>
								<option value="05월">5</option>
								<option value="06월">6</option>
								<option value="07월">7</option>
								<option value="08월">8</option>
								<option value="09월">9</option>
								<option value="10월">10</option>
								<option value="11월">11</option>
								<option value="12월">12</option>
							</select> <select name="day" id="day2" class="form-control col-md-3"
								required="required">
								<option value="01일">1</option>
								<option value="02일">2</option>
								<option value="03일">3</option>
								<option value="04일">4</option>
								<option value="05일">5</option>
								<option value="06일">6</option>
								<option value="07일">7</option>
								<option value="08일">8</option>
								<option value="09일">9</option>
								<option value="10일">10</option>
								<option value="11일">11</option>
								<option value="12일">12</option>
								<option value="13일">13</option>
								<option value="14일">14</option>
								<option value="15일">15</option>
								<option value="16일">16</option>
								<option value="17일">17</option>
								<option value="18일">18</option>
								<option value="19일">19</option>
								<option value="20일">20</option>
								<option value="21일">21</option>
								<option value="22일">22</option>
								<option value="23일">23</option>
								<option value="24일">24</option>
								<option value="25일">25</option>
								<option value="26일">26</option>
								<option value="27일">27</option>
								<option value="28일">28</option>
								<option value="29일">29</option>
								<option value="30일">30</option>
								<option value="31일">31</option>
							</select>
						</div>
						<div class="form-row mt-1">
							<label for="hours" class="col-md-3 mr-5 ml-2">시간</label> <label
								for="minute" class="col-md-3">분</label>
						</div>
						<div class="form-row">
							<select name="hours" id="hours"
								class="form-control col-md-3 mr-5 ml-2" required="required">
								<option value="00시">0</option>
								<option value="01시">1</option>
								<option value="02시">2</option>
								<option value="03시">3</option>
								<option value="04시">4</option>
								<option value="05시">5</option>
								<option value="06시">6</option>
								<option value="07시">7</option>
								<option value="08시">8</option>
								<option value="09시">9</option>
								<option value="10시">10</option>
								<option value="11시">11</option>
								<option value="12시">12</option>
								<option value="13시">13</option>
								<option value="14시">14</option>
								<option value="15시">15</option>
								<option value="16시">16</option>
								<option value="17시">17</option>
								<option value="18시">18</option>
								<option value="19시">19</option>
								<option value="20시">20</option>
								<option value="21시">21</option>
								<option value="22시">22</option>
								<option value="23시">23</option>
							</select> <select name="minute" id="minute" class="form-control col-md-3"
								required="required">
								<option value="00분">0</option>
								<option value="01분">1</option>
								<option value="02분">2</option>
								<option value="03분">3</option>
								<option value="04분">4</option>
								<option value="05분">5</option>
								<option value="06분">6</option>
								<option value="07분">7</option>
								<option value="08분">8</option>
								<option value="09분">9</option>
								<option value="10분">10</option>
								<option value="11분">11</option>
								<option value="12분">12</option>
								<option value="13분">13</option>
								<option value="14분">14</option>
								<option value="15분">15</option>
								<option value="16분">16</option>
								<option value="17분">17</option>
								<option value="18분">18</option>
								<option value="19분">19</option>
								<option value="20분">20</option>
								<option value="21분">21</option>
								<option value="22분">22</option>
								<option value="23분">23</option>
								<option value="24분">24</option>
								<option value="25분">25</option>
								<option value="26분">26</option>
								<option value="27분">27</option>
								<option value="28분">28</option>
								<option value="29분">29</option>
								<option value="30분">30</option>
								<option value="31분">31</option>
								<option value="32분">32</option>
								<option value="33분">33</option>
								<option value="34분">34</option>
								<option value="35분">35</option>
								<option value="36분">36</option>
								<option value="37분">37</option>
								<option value="38분">38</option>
								<option value="39분">39</option>
								<option value="40분">40</option>
								<option value="41분">41</option>
								<option value="42분">42</option>
								<option value="43분">43</option>
								<option value="44분">44</option>
								<option value="45분">45</option>
								<option value="46분">46</option>
								<option value="47분">47</option>
								<option value="48분">48</option>
								<option value="49분">49</option>
								<option value="50분">50</option>
								<option value="51분">51</option>
								<option value="52분">52</option>
								<option value="53분">53</option>
								<option value="54분">54</option>
								<option value="55분">55</option>
								<option value="56분">56</option>
								<option value="57분">57</option>
								<option value="58분">58</option>
								<option value="59분">59</option>
							</select>
						</div>
					</div>
					<div class="modal-footer">

						<button type="button" class="close" id="reserveClose"
							data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
				</div>
			</div>
		</div>
		<!--GOD 예약 창 끝-->
		<!--GOD 임시저장 창 시작-->
		<div class="modal" id="tempForm" data-backdrop="static"
			data-keyboard="false" tabindex="-1"
			aria-labelledby="exampleModalLabel2" aria-hidden="true"
			backdrop="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">임시 저장한 봄</h5>
						<button type="button" id="editSave"
							class="btn btn-success btn-sm float-right">수정</button>
					</div>
					<div class="modal-body col-12">
						<ul class="nav nav-tabs" id="myTab" role="tablist">
							<li class="nav-item" role="presentation"><a
								class="nav-link active" id="save-tab" data-toggle="tab"
								href="#save">저장 <span class="badge badge-success"
									id="saveNum"></span>
							</a></li>
							<li class="nav-item" role="presentation"><a class="nav-link"
								id="reserve-tab" data-toggle="tab" href="#reserve">예약 <span
									class="badge badge-success" id="reserveNum"></span>
							</a></li>
						</ul>
						<div class="tab-content" id="myTabContent">
							<div class="tab-pane fade show active" id="save">
								<ul class="list-group" id="saveList">
								</ul>
							</div>
							<div class="tab-pane fade" id="reserve">
								<ul class="list-group" id="reserveList">
								</ul>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<div id="editBtnGroup" class="mr-auto" style="display: none;">
							<button type="button" id="cancelEditSave"
								class="btn btn-secondary btn-sm">취소</button>
							<button type="button" id="deleteEditSave"
								class="btn btn-danger btn-sm">삭제</button>
						</div>
						<button type="button" class="close" id="saveClose"
							data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
				</div>
			</div>
		</div>
		<!--GOD 임시저장 창 끝-->
		<!--GOD 종료 전 저장 묻는 팝업 -->
		<div class="modal fade" id="saveModal" tabindex="-1"
			aria-labelledby="saveModal" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						봄을 저장하시겠습니까?
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body text-center">
						이 내용을 저장하시면 <br> 다음에 이어서 작성하실 수 있습니다.
					</div>
					<div class="modal-footer">
						<button type="button" id="notsaveBtn" class="btn btn-secondary">
							아뇨 괜찮습니다</button>
						<button type="submit" id="saveBtn" class="btn btn-success">저장</button>
					</div>
				</div>
			</div>
		</div>
		<!--GOD 저장 팝업 끝-->
	</form>
	<!--답글보낼 사람 선택하는 MODAL-->
	<div class="modal fade" id="towhom" tabindex="-1">
		<div class="modal-dialog modal-sm modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="form-row col-12">
						<input name="search_user" class="form-control col-8 mr-1 ml-2"
							type="search" placeholder="사용자 입력">
						<button onclick="search_user('<%=context %>');"
							class="btn btn-outline-success col-3 float-right ml-1"
							type="button">검색</button>
					</div>
					<ul id="followlist" class="list-group list-group-flush">
						<!-- <li class="list-group-item list-group-item-action">Cras justo odio</li> -->
					</ul>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-outline-secondary"
						data-dismiss="modal">취소</button>
					<button id="selectSendUser" type="button"
						class="btn btn-sm btn-outline-success">선택</button>
				</div>
			</div>
		</div>
	</div>

	<!--GOD 글쓰기 폼 기능-->
	<script type="text/javascript">
		/*예약을 해뒀는지(현재시간으로 안바뀌게)*/
		var reserve=0; //0 안함 1 함 -> bregdate 설정
		var btype=0; // 0 = noraml, 1=reply, 2=scrap
		var bvote=0; //0 안함 1 함
		var imgorvideo=0; //0이면 없는거 1이면 image 2이면 video
		var save=0; //0이면 save x, 1이면 save
		
		<!--글 쓰기전에 값 input에 넣기--> 
		function checkWrite(){
			//bcontent 넣기
			var write;
			if(btype==1){
				write=$("#toreply").text()+" ";
				write+=$("#writeTextarea").html();
			}
			else
				write=$("#writeTextarea").html();
			
			write=write.replace("&nbsp;", " ");
			var splitedWrite=write.split(' ');
			var linkedContent = '';
			var hash='';
			for(var word in splitedWrite){
				word = splitedWrite[word];
				//해시태그처리
				if(word.indexOf('#')==0){
					hash+=word;
					word="<a href='../hoon/searchView?search=%23"+word.substring(1, word.length)+"'>"+word+"</a>";
				}
				//어노테이션처리
				if(word.indexOf('@')==0){
					word="<a href='../iron/profile?uatid="+word.substring(1, word.length)+"'>"+word+"</a>";
				}
				linkedContent += word+' ';
			}
			$("input[name=hashtags]").attr("value", hash);
			write=linkedContent;
			
			$('input[name=bcontent]').attr('value', write);
			//bregdate 넣기
			if(reserve==0)
				$('input[name=bregdate]').attr('value','0');
			else{
				$('input[name=bregdate]').attr('value','1');
				$('input[name=bsaveorrsvd]').attr('value','1')
			}
			//저장글인지
			if(save==1){
				$('input[name=bregdate]').attr('value','0');
				$('input[name=bsaveorrsvd]').attr('value','0')
			}
			//일반글이면(예약,저장아니면)
			if(reserve==0 && save==0)
				$('input[name=bsaveorrsvd]').attr('value','2')
			
			//btype 넣기
			if(btype==0)
				$('input[name=btype]').attr('value','normal');
			else if(btype==1)
				$('input[name=btype]').attr('value','reply');
			else if(btype==2)
				$('input[name=btype]').attr('value','quote');
			
			//video인지 image인지 구분
			if(imgorvideo==1)
				$('input[name=image]').attr('value','1');
			else if(imgorvideo==2)
				$('input[name=video]').attr('value','1');
			
			//투표가있는지
			if(bvote==0)
				$('input[name=vote]').attr('value','0');
			else if(bvote==1)
				$('input[name=vote]').attr('value','1');
			
			if(!$("input[name=bbcode]").val())
				$("input[name=bbcode]").attr('value', 0);
			return true;
		}
		
		//사용자 검색 후 bcontent에 값 추가
		$("#selectSendUser").click(function(){
			var selectUser=$("input:radio[name=clickuatid]:checked").val();
			if(!(selectUser==null || selectUser=="")){
				$("#writeTextarea").append("@"+selectUser+" ");
			}
			$("#towhom .close").click();
		});
		
		//사용자 검색 아작스
		function search_user(ctx){
			$("#followlist").html("");
			var search_value=$("input[name=search_user]").val();
			if(search_value==null || search_value==""){
				var str="";
				$.ajax({
					url:"<%=context%>/god/getFollowerList",
					data:{ucode:${user.getUcode()}}, 
					dataType:'json',
					async:false,
					success:function(list){
						for(var i=0; i<list.length; i++){
							str+="<li class='list-group-item list-group-item-action'>"
							+"<img alt='no_image' src='"+ctx+"/profile_image/"+list[i].uimage+"' class='rounded-circle' width='30'>"
							+"<span class='card-title text-dark ml-1' style='font-size:0.8em;'>"+list[i].unickname+"</span>" 
							+"<span class='card-subtitle mb-2 text-muted ml-1' style='font-size:0.8em;'>@"+list[i].uatid+"</span>"
							+"<input type='radio' class='float-right mt-2' name='clickuatid' value='"+list[i].uatid+"'>"
							+"</li>";
						}
					
					$("#followlist").append(str);
					},
				 error : function(request,status,error) {
			        alert("message:"+request.responseText+"\n"+"Error -> "+error);
				}});
			}
			else{
				var str="";
				$.ajax({
					url:"<%=context%>/god/getSearchUser",
					data:{search_value:search_value},
					dataType:'json',
					async:false,
					success:function(list){
						for(var i=0; i<list.length; i++){
							str+="<li class='list-group-item list-group-item-action'>"
							+"<img alt='no_image' src='"+ctx+"/profile_image/"+list[i].uimage+"' class='rounded-circle' width='30'>"
							+"<span class='card-title text-dark ml-1' style='font-size:0.8em;'>"+list[i].unickname+"</span>" 
							+"<span class='card-subtitle mb-2 text-muted ml-1' style='font-size:0.8em;'>@"+list[i].uatid+"</span>"
							+"<input type='radio' class='float-right mt-2' name='clickuatid' value='"+list[i].uatid+"'>"
							+"</li>";
						}
					$("#followlist").append(str);
					},
				 error : function(request,status,error) {
			        alert("message:"+request.responseText+"\n"+"Error -> "+error);
				}});
			}
		}
		
		//페이지 로드 시 팔로워 목록 가져오기
		function getFollower(ctx){
				var str="";
				$.ajax({
					url:"<%=context%>/god/getFollowerList",
				data:{ucode:${user.getUcode()}},
				dataType:'json',
				async:false,
				success:function(list){
					for(var i=0; i<list.length; i++){
						str+="<li class='list-group-item list-group-item-action'>"
						+"<img alt='no_image' src='"+ctx+"/profile_image/"+list[i].uimage+"' class='rounded-circle' width='30'>"
						+"<span class='card-title text-dark ml-1' style='font-size:0.8em;'>"+list[i].unickname+"</span>" 
						+"<span class='card-subtitle mb-2 text-muted ml-1' style='font-size:0.8em;'>@"+list[i].uatid+"</span>"
						+"<input type='radio' class='float-right mt-2' name='clickuatid' value='"+list[i].uatid+"'>"
						+"</li>";
					}
						
					$("#followlist").append(str);
				},
				 error : function(request,status,error) {
				        alert("message:"+request.responseText+"\n"+"Error -> "+error);
				}
			});
		}
		
		
		//클릭시 답글은 반응안하게
		$('.replySetting').click(function(e){
			event.stopPropagation();
			$("#writeBtn").click();
			btype=1;
		});
		
		//reply시 bbcode 세팅
		function reply_click(code, atid){
			$("input[name=bbcode]").attr("value",code);
			$('#ReplyArea').css("display","block");
			$('#toreply').text("@"+atid);
		}

		//클릭시 스크랩 반응안하게
		$('.scrapSetting').click(function(e){
			e.stopPropagation();
			$("#writeBtn").click();
			btype=2;
		});
		  
		//인용시 bbcode와 인용디스플레이 세팅
		function scrap_click(code, index,nickname, atid, profile, type, src, context){
			var str="";
			$("input[name=bbcode]").attr("value", code);
			$("#QuoteArea2").css("display","block");
			$("#quote_nickname2").text(nickname); 
			var content = $("input#tagContent"+index).attr('value');
			$("#quote_content2").html(content);
			$("#quote_atid2").text("@"+atid); 
			$("#quote_profile2").attr("src", profile);
			if(type=='image'){
				$(".quote_file2").css("display","block");
				$("#show_quote_video2").css("display","none");
				var img=context+"/image/"+src;
				$("#quote_img2").attr("src", img);
			}
			else if(type=='video'){
				$(".quote_file2").css("display","block");
				var video=context+"/video/"+src;
				$("#quote_video2").attr("src", video);
			}
			//투표버튼은 비활성화
			$("#displayVote").attr("disabled","disabled");
		}  
		
		<!--글쓰면 글등록 버튼 활성화 --> 
		$("#writeTextarea").on("propertychange change keyup paste input",
				function(event) {
					$('#writeSubmit').attr('disabled', false);
					var write=$("#writeTextarea").html();
					if(write=='' ||write.trim()==''){
						$('#writeSubmit').attr('disabled', true);
					}
		});
		
		/*저장 목록창에서 수정창 누르면 checkbox띄우기*/
		$("#editSave").click(function(){
			$("input[name=checkedbcode]").css("display","block");
			$("#editBtnGroup").css("display","block");
			$("#editSave").css("display","none");
		});
		
		/*저장 창에서 수정->취소 누를시*/
		$("#cancelEditSave").click(function(){
			$("input[name=checkedbcode]").css("display","none");
			$("#editBtnGroup").css("display","none");
			$("#editSave").css("display","block");
		});
		
		/*저장 창에서 수정->삭제 누를 시*/
		$("#deleteEditSave").click(function(){
			var valueArr=new Array();
			var list=$("input[name=checkedbcode]");
			for(var i=0; i<list.length; i++){
				if(list[i].checked){
					valueArr.push(list[i].value);
				}
			}
			if(valueArr.length==0){
				alert("선택된 글이 없습니다.");
			}
			else{
				$.ajax({
					url:"<%=context%>/god/deleteSaveWrite",
					type:'POST',
					traditional : true,
					data:{bcodes:valueArr},
					dataType:'json',
					success:function(data){
						if(data>=1){
							clickWriteBtn();
							clickSaveBtn();
						}
					},
					error:function(request, status, error){
						alert("code = " + request.status + " message = "
								+ request.responseText + " error = "
								+ error);
					}
				});
			}
			$("input[name=checkedbcode]").css("display","none");
			$("#editBtnGroup").css("display","none");
			$("#editSave").css("display","block");
		});
		
		/* 글쓰기 버튼 누르면 임시저장 글 수(저장, 예약) 가져오기 */
		function clickWriteBtn(){
			var sumNum=0;
			
			$.ajax({
				url:"<%=context%>/god/getReserveNum",
			data:{ucode:${user.getUcode()}},  
			dataType:'text',
			async:false,
			success:function(data){
				$('#reserveNum').html(data);
				sumNum=parseInt(data);
			}
		});
		
		$.ajax({
			url:"<%=context%>/god/getSaveNum",
				data:{ucode:${user.getUcode()}},
				dataType:'text',
				async:false,
				success:function(data){
					$('#saveNum').html(data);
					sumNum+=parseInt(data);
				}
			});
			
			$('#sumNum').html(sumNum);
		}
		
		/*저장,예약 글 불러오기*/
		function clickSaveBtn(){
			$("#saveList").html("");
			var str="";
			$.ajax({
				url:"<%=context%>/god/getSaveList",
			data:{ucode:${user.getUcode()}},
			dataType:'json',
			async:false,
			success:function(list){
				for(var i=0; i<list.length; i++){
					str+="<li class='list-group-item list-group-item-action'><div class='form-row'>"
					+"<input type='checkbox' class='mr-1 mt-1' name='checkedbcode' value='"+list[i].bcode+"' style='display:none;''>"
					+"<a href='javascript:callSave("+list[i].bcode+")' style='color:black;'>"
					+list[i].bcontent
					+"</a></div>"
					+"</li>";
				}
					
				$("#saveList").append(str);
			},
			 error : function(request,status,error) {
			        alert("message:"+request.responseText+"\n"+"Error -> "+error);
			}
		});
		
		$("#reserveList").html("");
		str="";
		$.ajax({
			url:"<%=context%>/god/getReserveList", 
				data:{ucode:${user.getUcode()}},  
				dataType:'json',
				async:false,
				success:function(list){
					for(var i=0; i<list.length; i++){
						str+="<li class='list-group-item list-group-item-action'><div class='form-row'>"
							+"<input type='checkbox' class='mr-1 mt-1' name='checkedbcode' value='"+list[i].bcode+"' style='display:none;''>"
							+"<a href='javascript:callSave("+list[i].bcode+")' style='color:black;'>"
							+list[i].bcontent
							+"</a></div>"
							+"</li>";
					} 
					$("#reserveList").append(str);
				}, 
				 error : function(request,status,error) {
				        alert("message:"+request.responseText+"\n"+"Error -> "+error);
				}
			});
		}
		
		/*저장글 writeForm에 적용*/
		function callSave(code){
			//writeForm 초기화부터
			reserve=0;
			btype=0;
		 	bvote=0;
			imgorvideo=0;
			save=0;
			$("#writeTextarea").html(""); //글내용 초기화
			$(".img-fluid").attr("src",""); //이미지 초기화
			$(".embed-responsive video").attr("src",""); //동영상 초기화
			$('.embed-responsive').css("display","none"); //동영상 none
			jQuery("#media").css("display","block"); //미디어선택 버튼 활성화
			jQuery("#mediaDelete").css("display","none"); //미디어 삭제 버튼 안보이게
			//예약창 닫고
			$("#tempForm .close").click();
			//아작스로 글불러오기
			$.ajax({
				url:"<%=context%>/god/callSaveBoard",
				data:{bcode: code},
				dataType:'json',
				async:false,
				success:function(data){
					$("input[name=ucode]").attr('value', data.ucode);
					$("input[name=savebcode]").attr('value', data.bcode);
					$("#writeTextarea").html(data.bcontent);
					//답글일경우
					if(data.btype == "reply"){
						btype=1;
						$("input[name=bbcode]").attr("value",data.bbcode);
						$('#ReplyArea').css("display","block");
						$('#toreply').text("@"+data.touatid);
					}
					//인용글일경우
					if(data.btype == "quote"){
						btype=2;
						$("input[name=bbcode]").attr("value",data.bbcode);
						$("#QuoteArea2").css("display","block");
						$("#quote_nickname2").text(data.qnickname); 
						$("#quote_content2").html(data.qcontent);
						$("#quote_atid2").text("@"+data.qatid); 
						$("#quote_profile2").attr("src", data.qprofileimage);
						if((data.qattach).substring(0,5) == "image"){
							$(".quote_file2").css("display","block");
							$("#show_quote_video2").css("display","none");
							$("img#quote_img2").attr("src", $("img#quote_img2").attr('src')+"/"+data.qattach);
						}
						else if((data.qattach).substring(0,5) == 'video'){
							$(".quote_file2").css("display","block");
							$("#show_quote_video2").css("display","block");
							$("video#quote_video2").attr("src", $("video#quote_video").attr('src')+"/"+data.qattach);
						}
						
						//투표버튼은 비활성화
						$("#displayVote").attr("disabled","disabled");
					}
					//예약글일경우
					if(data.bregdate!=null){
						reserve=1;
						var str="";
						var rsvdyear=(data.bregdate).substring(0,2);
						var rsvdmonth=(data.bregdate).substring(3,5);
						var rsvdday=(data.bregdate).substring(6,8);
						var rsvdhour=(data.bregdate).substring(9,11);
						var rsvdmin=(data.bregdate).substring(12,14);
						str="20"+rsvdyear+"년  "+
							rsvdmonth+"월 "+
							rsvdday+"일 "+
							rsvdhour+"시 "+
							rsvdmin+"분에 글 예약";
						$('#setTime').text(str);
						//예약창 selected 시켜놓기
						$("#year").val("20"+rsvdyear+"년").attr("selected",true);
						$("#month").val(rsvdmonth+"월").attr("selected",true);
						$("#day2").val(rsvdday+"일").attr("selected",true);
						$("#hours").val(rsvdhour+"시").attr("selected",true);
						$("#minute").val(rsvdmin+"분").attr("selected",true);
					}
					//저장글일경우
					else{
						$('#setTime').text("");
					}
					//attach가 이미지일때
					if((data.battach).substring(0,5) == "image"){
						$('.img-fluid').css("display","");
						$('.embed-responsive').css("display","none");
						$(".select_file img").attr("src", "${context}/"+data.battach);
						imgorvideo=1;
						jQuery("#mediaDelete").css("display","block");
						jQuery("#media").css("display","none");
					}
					//attach가 동영상일때
					else if((data.battach).substring(0,5) == "video"){
						$('.img-fluid').css("display","none");
						$('.embed-responsive').css("display","");
						var video = document.getElementById("video");
						video.setAttribute("src", "${context}/"+data.battach); 
						video.play(); 
						imgorvideo=2;
						jQuery("#mediaDelete").css("display","block");
						jQuery("#media").css("display","none");
					}
				},
			error : function(request,status,error) {
			    alert("message:"+request.responseText+"\n"+"Error -> "+error);
			}});
		}
		
		/*글쓰기에서 닫기눌렀을때 글이없으면 저장창 안띄우고 닫기*/
		jQuery("#closeWrite").click(function(){
			if(bvote==1){
				location.href="../iron/timeline";
			}
			else{
				var write=$("#writeTextarea").html();
				if(write=='' ||write.trim()==''){
					$("#saveModal .close").click();
					$("#realCloseWrite").click();
					//그리고 메인글로 돌아가
					location.href="/iron/timeline";
				}
			}
		});
		
		/*마지막 저장창에서 저장안해 클릭*/
		jQuery("#notsaveBtn").click(function(){
			//$("#writeForm .close").click();
			$("#saveModal .close").click();
			$("#realCloseWrite").click();
			//그리고 메인글로 돌아가
			location.href="/iron/timeline";
		});
		
		/*마지막 저장창에서 저장해 클릭*/
		jQuery("#saveBtn").click(function(){
			save=1;
		});
		
		<!--예약하기 버튼(월-일맞춰서 경고창띄우기)-->
		jQuery("#reserveChk").click(function(){
			var year=$("#year option:selected").val();
			var month=$("#month option:selected").val();
			var day=$("#day2 option:selected").val()
			var hours=$("#hours option:selected").val();
			var minute=$("#minute option:selected").val();
			if(month=="02월"&&(day=="29일"||day=="30일"||day=="31일"))
				$('#checkTime').show();
			else if((month=="04월"||month=="06월"||month=="09월"||month=="11월")&&day=="31일")
				$('#checkTime').show();
			else{
				reserve=1;
				$('#checkTime').hide(); //알림창 끄고
				$("#reserveForm .close").click(); // 예약창 닫아주고
				$('#setTime').text(year+" "+month+" "+day+" "+hours+" "+minute+"에 글 예약");
				//예약 지우기 버튼 활성화
				$("#reserveDelete").css("display","block");
				//투표 비활성화
				$("#displayVote").attr("disabled","disabled");
				bvote=0;
			}
		 });
		 
		<!--예약창 시간 가져오기-->
		jQuery('#reserveBtn').click(function () { 
			if(reserve==0){
				var timer=new Date();
				var y=timer.getFullYear();
				var m=timer.getMonth()+1;
				var d=timer.getDate();
				var h=timer.getHours();
				var m2=timer.getMinutes();
				if(m<10)
					$("#month").val("0"+m+"월").attr("selected",true);
				else
					$("#month").val(m+"월").attr("selected",true);
				if(d<10)
					$("#day2").val("0"+d+"일").attr("selected",true);
				else
					$("#day2").val(d+"일").attr("selected",true);
				if(h<10)
					$("#hours").val("0"+h+"시").attr("selected",true);
				else
					$("#hours").val(h+"시").attr("selected",true);
				if(m2<10)
					$("#minute").val("0"+m2+"분").attr("selected",true);
				else
					$("#minute").val(m2+"분").attr("selected",true);
				$('#checkTime').hide();
			}
		}); 
		
		<!--예약 시간 지우기-->
		jQuery("#reserveDelete").click(function(){
			$("#reserveDelete").css("display","none");
			$('#setTime').text("");
			$("#reserveForm .close").click(); 
			//투표 활성화
			$("#displayVote").removeAttr("disabled");
			reserve=0;
		});
		
		<!-- 투표 삭제 -->
		jQuery('#voteDelete').click(function () {   
			$('#vote_form').css("display","none");  
			$("#displayVote").css("display","block");
		    $("#voteDelete").css("display","none");
			$("[name=vselect1]").attr("required" , false);
	        $("[name=vselect2]").attr("required" , false);
	        //예약버튼 활성화
	        $("#reserveBtn").attr("disabled",false);
	        //미디어버튼 활성화
	        $("#media").attr("disabled",false);
			bvote=0;
		}); 
		
		<!-- 투표 보이기 -->
		jQuery('#displayVote').click(function () {  
		    if($("#vote_form").css("display") == "none"){   
		        $('#vote_form').css("display","");
		        $("[name=vselect1]").attr("required" , true);
		        $("[name=vselect2]").attr("required" , true);
		        $("#displayVote").css("display","none");
		        $("#voteDelete").css("display","block");
		        //예약버튼 비활성화
		        $("#reserveBtn").attr("disabled",true);
		        $('#setTime').text("");
		        $("#reserveDelete").css("display","none");
		        reserve=0;
		        //미디어 버튼 비활성화
		        $("#media").attr("disabled","disabled");
		    } 
		    bvote=1;
		});  
		
		<!--투표 항목 늘리기-->
		jQuery('#plusSelect').click(function () {  
		    if($("#pick3").css("display") == "none"){   
		        $('#pick3').css("display","");
		    } 
		    else {  
		        $('#pick4').css("display","");
		        $('#plusSelect').css("display","none");
		    }  
		});  
		
		<!--파일 처리(이미지 or 동영상 선택)--> 
		$("#media_file").change(function(){
			if(this.files && this.files[0] && this.files[0].name.match(/\.(jpg|jpeg|png|gif)$/)){
				$(".embed-responsive video").attr("src","");
				if(this.files[0].size>10485760) {
		            alert('File size is larger than 10MB!');
		        }
				else{
					$('.img-fluid').css("display","");
					$('.embed-responsive').css("display","none");
					var reader=new FileReader;
					reader.onload=function(data){
						$(".select_file img").attr("src", data.target.result);
					}
					reader.readAsDataURL(this.files[0]);
					imgorvideo=1;
					jQuery("#mediaDelete").css("display","block");
					jQuery("#media").css("display","none");
					$("#displayVote").attr("disabled","disabled");
				}
			}
			else if(this.files && this.files[0] && this.files[0].name.match(/\.(avi|mpg|mpeg|mp4)$/)){
				$(".img-fluid").attr("src","");
				if(this.files[0].size>104857600) {
		            alert('File size is larger than 100MB!');
		        }
				else{
					$('.img-fluid').css("display","none");
					$('.embed-responsive').css("display","");
					var inputFile = document.getElementById("media_file"); 
					var video = document.getElementById("video"); 
					var files = inputFile.files; 
					var videourl = URL.createObjectURL(inputFile.files[0]); 
					video.setAttribute("src", videourl); 
					video.play(); 
					imgorvideo=2;
					jQuery("#mediaDelete").css("display","block");
					jQuery("#media").css("display","none");
					$("#displayVote").attr("disabled","disabled");
				}
			}
		}); 
		<!--미디어 삭제 버튼 누르면-->
		jQuery('#mediaDelete').click(function (){
			if(imgorvideo==1){
				$(".img-fluid").attr("src","");
			}
			else if(imgorvideo==2){
				$(".embed-responsive video").attr("src","");
				$('.embed-responsive').css("display","none");
			}
			jQuery("#media").css("display","block");
			jQuery("#mediaDelete").css("display","none");
			$("#displayVote").attr("disabled",false);
			imgorvideo=0;
		});
		</script>
	<!--GOD 글쓰기 기능 끝-->

	<!--BEAR 더보기 창  -->
	<div class="modal fade" id="morebtn" data-backdrop="static"
		data-keyboard="false" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<div class="modal-body col-12">
						<div class="card-header">
							<h4 style="text-align: center;">
								팔로우 추천
								<button style="float: right;" onclick="closemodal()">x</button>
							</h4>
							<div class="card-body" style="padding: 5px;">
								<div class="card">
									<div class="card-body"
										style="font-size: 0.8rem; padding: 10px;">
										<c:forEach var="justFollowMe1" items="${suggestFlist2 }">
											<div class="card">
												<div class="card-body"
													style="font-size: 0.8rem; padding: 10px;">
													<img
														src="<%=context %>/profile_image/${justFollowMe1.uimage}"
														class="rounded-circle" width="40" height="40"> <a
														class="card-title text-dark">${justFollowMe1.unickName}</a>
													<a class="card-subtitle mb-2 text-muted">@${justFollowMe1.uatid}</a>
													<c:if test="${justFollowMe1.uonline eq 1 }">
														<img src="<%=context%>/image/online.png" width="20"
															height="20">
													</c:if>
													<div>
														<button type="button"
															class="btn btn-outline-success btn-sm float-right"
															style="font-size: 1.2rem;"
															onclick="followchk(${justFollowMe1.uucode})"
															name="k${justFollowMe1.uucode}">팔로우</button>

													</div>
													<h3 id="bearsize" style="padding-left: 40px">&nbsp&nbsp${justFollowMe1.uintro}</h3>

												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /#wrapper -->
</body>

</html>