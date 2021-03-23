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
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="/js/bootstrap.bundle.min.js"></script>
<script src="/js/bootstrap.bundle.js"></script>
<style>
.dropdown-toggle.caret-off::after {
	display: none;
}
</style>

<!-- id 중복 check -->
		<%  
			request.setCharacterEncoding("UTF-8");
			String context = request.getContextPath();
			System.out.println("context->"+context);
		%>
		<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
		<script type="text/javascript">
		 var contextPath='${pageContext.request.contextPath}';
		 
			// 아이디 유효성 검사(1 = 중복 / 0 != 중복)
		$(document).ready(function(){
			$("#uatid").blur(function(){
				//function idCh(){
					var uatid = $('#uatid').val();
					console.log(uatid);
					$.ajax({
						url : "<%=context%>/right/idCheck",
						data: {uatid : uatid},
						dataType:'text',
						success : function(data){						
							
							if (data==='1'){
									// 1 : 아이디가 중복되는 문구
									$('#id_check').html('사용중인 아이디입니다 :p');
									$('#id_check').css('color', 'red');
									$('#reg_submit').attr('disabled', true);

							}else{
								$('#id_check').html('사용가능한 아이디입니다 :p');
								$('#id_check').css('color', 'green');
								$("#reg_submit").attr("disabled", false);
 									
								}
							}
						});
					});
				});
			</script>
<style>
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
</head>
<script type="text/javascript">
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
</script>
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
				<!-- <a href="#" class="list-group-item list-group-item-action">
					<button type="button" class="btn btn-outline-success">
						<img src="/img/write.svg" width="15" height="15"> 글 쓰기
					</button>
				</a> -->
				<div class="card">
					<div class="card-body">
						<img src="<%=context %>/profile_image/${user.uimage }"
							class="rounded-circle" width="50" width="50"> <a
							class="card-title text-dark" style="font-size: 0.8em">${user.unickName }</a><br>
						<a class="card-subtitle mb-2 text-muted" style="font-size: 0.8em">@${user.uatid }</a>
					</div>
					<button type="button" class="btn btn-success"	onclick="location.href='../coffee/logout'">로그아웃</button>
				</div>
		</div>
	</div>
		<!-- /#sidebar-wrapper -->
		<!-- Page Content -->
		<div id="page-content-wrapper">
				<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
					<button class="btn btn-success" id="menu-toggle" onclick="location.href='updateEv'">←</button>
				</nav>
			<div class="container-fluid">
				<p>
				<div class="card">
					<div align="center">
						<form action="userInfoEdit" method="post">
						<input type="hidden" name="ucode" value="${ui.ucode }">
						<p>
						<h2>계정 정보 수정</h2>
						<div class="card-body">
						<table>
						<!-- 이메일 -->
						<tr><td>
							<label for="uemail">이메일</label></td><td><span class="tab">&#9;&nbsp;&nbsp;&nbsp;</span></td>
							<td><span>${ui.uemail }</span></td>
						</tr>
						<!-- 아이디 -->
						<tr>
						<td>
							<label for="uatid">아이디</label></td><td><span class="tab">&#9;</span></td>
						<td><input type="text" class="form-control" id="uatid" name="uatid" placeholder="ID" value="${ui.uatid }" required="required">
						</td>			<!-- <input type="button" value="중복확인" class="btn btn-outline-success" onclick="idCh()"> -->
									
						<tr><td colspan="3" style="text-align:center;"><div id="id_check"></div><br></td></tr>
						<!-- 생년월일 -->
						<tr>
						<td>
							<label for="user_id">생년월일</label></td><td><span class="tab">&#9;</span></td>
						<td><input type="date"  name="ubirth" required="required" value="${ui.ubirth }"></td>
						</tr>
						<!-- 성별 -->
						<tr>
						<td>
							<label for="ugender">성별</label></td><td><span class="tab">&#9;</span></td>
						<td><select class="form-select" id="inputGroupSelect01" name="ugender">
								<option selected value="${ui.ugender}">
									<c:set var="ugender" scope = "session" value="${ui.ugender}"/>
									<c:choose>
										<c:when test="${ugender eq 'm' }">
											남
										</c:when>
										<c:when test="${ugender eq 'w' }">
											여
										</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
										</option>
										<option value="m">남</option>
										<option value="w">여</option>
								</select>
						</td>
						</tr>
						<!-- 국가 -->
						<tr>
						<td>
							<label for="unation">국가</label></td><td><span class="tab">&#9;</span></td>
						<td><select class="form-select" id="inputGroupSelect01" name="unation">
							    <option selected value="${ui.unation }">${ui.unation }</option>
							    <option value="가나">가나</option>
								<option value="가봉">가봉</option>
								<option value="가이아나">가이아나</option>
								<option value="감비아">감비아</option>
								<option value="건지">건지</option>
								<option value="과들루프">과들루프</option>
								<option value="과테말라">과테말라</option>
								<option value="괌">괌</option>
								<option value="그레나다">그레나다</option>
								<option value="그리스">그리스</option>
								<option value="그린란드">그린란드</option>
								<option value="기니">기니</option>
								<option value="기니비사우">기니비사우</option>
								<option value="나미비아">나미비아</option>
								<option value="나우루">나우루</option>
								<option value="나이지리아">나이지리아</option>
								<option value="남아프리카">남아프리카</option>
								<option value="네덜란드">네덜란드</option>
								<option value="네덜란드령 카리브">네덜란드령 카리브</option>
								<option value="네팔">네팔</option>
								<option value="노르웨이">노르웨이</option>
								<option value="노퍽섬">노퍽섬</option>
								<option value="뉴질랜드">뉴질랜드</option>
								<option value="뉴칼레도니아">뉴칼레도니아</option>
								<option value="니우에">니우에</option>
								<option value="니제르">니제르</option>
								<option value="니카라과">니카라과</option>
								<option value="대만">대만</option>
								<option value="대한민국">대한민국</option>
								<option value="덴마크">덴마크</option>
								<option value="도미니카">도미니카</option>
								<option value="독일">독일</option>
								<option value="동티모르">동티모르</option>
								<option value="라오스">라오스</option>
								<option value="라이베리아">라이베리아</option>
								<option value="라트비아">라트비아</option>
								<option value="러시아">러시아</option>
								<option value="레바논">레바논</option>
								<option value="레소토">레소토</option>
								<option value="루마니아">루마니아</option>
								<option value="룩셈부르크">룩셈부르크</option>
								<option value="르완다">르완다</option>
								<option value="리비아">리비아</option>
								<option value="리유니온">리유니온</option>
								<option value="리투아니아">리투아니아</option>
								<option value="리히텐슈타인">리히텐슈타인</option>
								<option value="마다가스카르">마다가스카르</option>
								<option value="마르티니크">마르티니크</option>
								<option value="마요트">마요트</option>
								<option value="마카오(중국 특별행정구)">마카오(중국 특별행정구)</option>
								<option value="마케도니아">마케도니아</option>
								<option value="말라위">말라위</option>
								<option value="말레이시아">말레이시아</option>
								<option value="말리">말리</option>
								<option value="멕시코">멕시코</option>
								<option value="모나코">모나코</option>
								<option value="모로코">모로코</option>
								<option value="모리셔스">모리셔스</option>
								<option value="모리타니">모리타니</option>
								<option value="모잠비크">모잠비크</option>
								<option value="몬테네그로">몬테네그로</option>
								<option value="몬트세라트">몬트세라트</option>
								<option value="몰도바">몰도바</option>
								<option value="몰디브">몰디브</option>
								<option value="몰타">몰타</option>
								<option value="몽골">몽골</option>
								<option value="미국">미국</option>
								<option value="미크로네시아">미크로네시아</option>
								<option value="바누아투">바누아투</option>
								<option value="바레인">바레인</option>
								<option value="바베이도스">바베이도스</option>
								<option value="바티칸 시국">바티칸 시국</option>
								<option value="바하마">바하마</option>
								<option value="방글라데시">방글라데시</option>
								<option value="버뮤다">버뮤다</option>
								<option value="베냉">베냉</option>
								<option value="베네수엘라">베네수엘라</option>
								<option value="베트남">베트남</option>
								<option value="벨기에">벨기에</option>
								<option value="벨라루스">벨라루스</option>
								<option value="벨리즈">벨리즈</option>
								<option value="보스니아 헤르체고비나">보스니아 헤르체고비나</option>
								<option value="보츠와나">보츠와나</option>
								<option value="볼리비아">볼리비아</option>
								<option value="부룬디">부룬디</option>
								<option value="부르키나파소">부르키나파소</option>
								<option value="부베섬">부베섬</option>
								<option value="부탄">부탄</option>
								<option value="북마리아나제도">북마리아나제도</option>
								<option value="불가리아">불가리아</option>
								<option value="브라질">브라질</option>
								<option value="브루나이">브루나이</option>
								<option value="사모아">사모아</option>
								<option value="사우디아라비아">사우디아라비아</option>
								<option value="산마리노">산마리노</option>
								<option value="상투메 프린시페">상투메 프린시페</option>
								<option value="생마르탱">생마르탱</option>
								<option value="생바르텔레미">생바르텔레미</option>
								<option value="생피에르 미클롱">생피에르 미클롱</option>
								<option value="세네갈">세네갈</option>
								<option value="세르비아">세르비아</option>
								<option value="세이셸">세이셸</option>
								<option value="스리랑카">스리랑카</option>
								<option value="스와질란드">스와질란드</option>
								<option value="스웨덴">스웨덴</option>
								<option value="스위스">스위스</option>
								<option value="스페인">스페인</option>
								<option value="슬로바키아">슬로바키아</option>
								<option value="슬로베니아">슬로베니아</option>
								<option value="시에라리온">시에라리온</option>
								<option value="신트마르턴">신트마르턴</option>
								<option value="싱가포르">싱가포르</option>
								<option value="아랍에미리트">아랍에미리트</option>
								<option value="아루바">아루바</option>
								<option value="아르메니아">아르메니아</option>
								<option value="아르헨티나">아르헨티나</option>
								<option value="아메리칸 사모아">아메리칸 사모아</option>
								<option value="아이슬란드">아이슬란드</option>
								<option value="아이티">아이티</option>
								<option value="아일랜드">아일랜드</option>
								<option value="아제르바이잔">아제르바이잔</option>
								<option value="아프가니스탄">아프가니스탄</option>
								<option value="안도라">안도라</option>
								<option value="알바니아">알바니아</option>
								<option value="알제리">알제리</option>
								<option value="앙골라">앙골라</option>
								<option value="앤티가 바부다">앤티가 바부다</option>
								<option value="앵귈라">앵귈라</option>
								<option value="에리트리아">에리트리아</option>
								<option value="에스토니아">에스토니아</option>
								<option value="에콰도르">에콰도르</option>
								<option value="에티오피아">에티오피아</option>
								<option value="엘살바도르">엘살바도르</option>
								<option value="영국">영국</option>
								<option value="예멘">예멘</option>
								<option value="오만">오만</option>
								<option value="오스트레일리아">오스트레일리아</option>
								<option value="오스트리아">오스트리아</option>
								<option value="요르단">요르단</option>
								<option value="우간다">우간다</option>
								<option value="우루과이">우루과이</option>
								<option value="우즈베키스탄">우즈베키스탄</option>
								<option value="우크라이나">우크라이나</option>
								<option value="이라크">이라크</option>
								<option value="이란">이란</option>
								<option value="이스라엘">이스라엘</option>
								<option value="이집트">이집트</option>
								<option value="이탈리아">이탈리아</option>
								<option value="인도">인도</option>
								<option value="인도네시아">인도네시아</option>
								<option value="일본">일본</option>
								<option value="자메이카">자메이카</option>
								<option value="잠비아">잠비아</option>
								<option value="저지">저지</option>
								<option value="지부티">지부티</option>
								<option value="지브롤터">지브롤터</option>
								<option value="짐바브웨">짐바브웨</option>
								<option value="차드">차드</option>
								<option value="체코">체코</option>
								<option value="칠레">칠레</option>
								<option value="카메룬">카메룬</option>
								<option value="카보베르데">카보베르데</option>
								<option value="카자흐스탄">카자흐스탄</option>
								<option value="카타르">카타르</option>
								<option value="캄보디아">캄보디아</option>
								<option value="캐나다">캐나다</option>
								<option value="케냐">케냐</option>
								<option value="코스타리카">코스타리카</option>
								<option value="코트디부아르">코트디부아르</option>
								<option value="콜롬비아">콜롬비아</option>
								<option value="쿠바">쿠바</option>
								<option value="쿠웨이트">쿠웨이트</option>
								<option value="퀴라소">퀴라소</option>
								<option value="크로아티아">크로아티아</option>
								<option value="타지키스탄">타지키스탄</option>
								<option value="탄자니아">탄자니아</option>
								<option value="태국">태국</option>
								<option value="터키">터키</option>
								<option value="토고">토고</option>
								<option value="토켈라우">토켈라우</option>
								<option value="통가">통가</option>
								<option value="투르크메니스탄">투르크메니스탄</option>
								<option value="투발루">투발루</option>
								<option value="튀니지">튀니지</option>
								<option value="트리니다드 토바고">트리니다드 토바고</option>
								<option value="파나마">파나마</option>
								<option value="파라과이">파라과이</option>
								<option value="파키스탄">파키스탄</option>
								<option value="파푸아뉴기니">파푸아뉴기니</option>
								<option value="팔라우">팔라우</option>
								<option value="페루">페루</option>
								<option value="포르투갈">포르투갈</option>
								<option value="폴란드">폴란드</option>
								<option value="푸에르토리코">푸에르토리코</option>
								<option value="프랑스">프랑스</option>
								<option value="피지">피지</option>
								<option value="핀란드">핀란드</option>
								<option value="필리핀">필리핀</option>
								<option value="홍콩(중국 특별행정구)">홍콩(중국 특별행정구)</option>
								<option value="헝가리">헝가리</option>
  								</select></td>
  						</tr>
						<!-- 공개 범위 -->
						<tr>
						<td>
							<label for="uprivate">공개 범위</label></td><td><span class="tab">&#9;</span></td>
						<td>
							   <select class="form-select" id="inputGroupSelect01" name="uprivate">
								<option selected value="${ui.uprivate}">
							   	  <c:set var="uprivate" scope = "session" value="${ui.uprivate}"/>
								  <c:choose>
									<c:when test="${uprivate eq 0 }">
										전체 공개
									</c:when>
									<c:when test="${uprivate eq 1 }">
										필로워에게만
									</c:when>
									<c:otherwise></c:otherwise>
								  </c:choose>
									 </option>
								    <option value="0">전체 공개</option>
								    <option value="1">필로워에게만</option>
							  </select></td>		
						</tr>
						<!-- 쪽지 범위 -->
						<tr>
						<td><label for="uchat">쪽지 범위</label></td><td><span class="tab">&#9;</span></td>
						<td><select class="form-select" id="inputGroupSelect01" name="uchat">
							    <option selected value="${ui.uchat}">
   	  								<c:set var="uchat" scope = "session" value="${ui.uchat}"/>
									<c:choose>
										<c:when test="${uchat eq 0 }">
											전체
										</c:when>
										<c:when test="${uchat eq 1 }">
											팔로워만
										</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
								 </option>
							    <option value="0">전체</option>
							    <option value="1">팔로워만</option>
							  </select></td>
						</tr>
						<!-- 알림 범위 -->
						<tr>
						<td>
							<label for="ualarm">알림 범위</label></td><td><span class="tab">&#9;</span></td>
						<td><select class="form-select" id="inputGroupSelect01" name="ualarm">
							    <option selected value="${ui.ualarm}">
								   <c:set var="ualarm" scope = "session" value="${ui.ualarm}"/>
								   <c:choose>
									<c:when test="${ualarm eq 0 }">
										전체
									</c:when>
									<c:when test="${ualarm eq 1 }">
										팔로워만
									</c:when>
									<c:otherwise></c:otherwise>
								   </c:choose>
								 </option>
							    <option value="0">전체</option>
							    <option value="1">팔로워만</option>
							  </select></td>
						</tr>
						<!-- 가입 일자 -->
						<tr>
						<td>
							<label for="uregdate">가입 일자</label></td><td><span class="tab">&#9;</span></td>
						<td><span>${ui.uregdate }</span></td>
						</tr>
						</table>
						</div>
						<div class="form-group">
							<input type="reset" value="수정취소" class="btn btn-outline-secondary" style="margin-right: 0.5%;'"> 
							<input type="submit" value="수정완료" class="btn btn-outline-success" id="reg_submit">
						</div>
						</form>
						<p>
					</div>
				</div>
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
								id="writeBtn" data-toggle="modal" data-target="#morebtn">더보기
							</button>
						</c:if>
					</div>
				</div>
				<!-- 사이드바 팔로우 가져가야할 구간 끝 -->

				<div class="list-group-item list-group-item-action bg-light"
					style="padding: 5px;">
					<div class="card bg-light mb-3">
						<div class="card-header">해시태그</div>
						<div class="card-body" style="padding: 5px;">
							<c:forEach var="tag" items="${tag_list}" varStatus="status">
								<c:if test="${status.count <=3 }">
									<div class="card">
										<div class="card-body"
											style="font-size: 0.8rem; padding: 10px;">
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