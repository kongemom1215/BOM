<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
String context = request.getContextPath();
%>
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

.container {
	max-width: 1170px;
	margin: auto;
}

img {
	max-width: 100%;
}

.inbox_people {
	background: #f8f8f8 none repeat scroll 0 0;
	float: left;
	overflow: hidden;
	width: 40%;
	border-right: 1px solid #c4c4c4;
}

.inbox_msg {
	border: 1px solid #c4c4c4;
	clear: both;
	overflow: hidden;
}

.top_spac {
	margin: 20px 0 0;
}

.recent_heading {
	float: left;
	width: 40%;
}

.srch_bar {
	display: inline-block;
	text-align: right;
	width: 60%;
	padding:
}

.headind_srch {
	padding: 10px 29px 10px 20px;
	overflow: hidden;
	border-bottom: 1px solid #c4c4c4;
}

.recent_heading h4 {
	color: #05728f;
	font-size: 21px;
	margin: auto;
}

.srch_bar input {
	border: 1px solid #cdcdcd;
	border-width: 0 0 1px 0;
	width: 80%;
	padding: 2px 0 4px 6px;
	background: none;
}

.srch_bar .input-group-addon button {
	background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
	border: medium none;
	padding: 0;
	color: #707070;
	font-size: 18px;
}

.srch_bar .input-group-addon {
	margin: 0 0 0 -27px;
}

.chat_ib h5 {
	font-size: 15px;
	color: #464646;
	margin: 0 0 8px 0;
}

.chat_ib h5 span {
	font-size: 13px;
	float: right;
}

.chat_ib p {
	font-size: 14px;
	color: #989898;
	margin: auto
}

.chat_img {
	float: left;
	width: 11%;
}

.chat_ib {
	float: left;
	padding: 0 0 0 20px;
	width: 88%;
}

.chat_people {
	overflow: hidden;
	clear: both;
}

.chat_list {
	border-bottom: 1px solid #c4c4c4;
	margin: 0;
	padding: 18px 16px 10px;
}

.inbox_chat {
	height: 550px;
	overflow-y: scroll;
}

.active_chat {
	background: #ebebeb;
}

.incoming_msg_img {
	display: inline-block;
	width: 6%;
}

.received_msg {
	display: inline-block;
	padding: 0 0 0 10px;
	vertical-align: top;
	width: 92%;
}

.received_withd_msg p {
	background: #ebebeb none repeat scroll 0 0;
	border-radius: 3px;
	color: #646464;
	font-size: 14px;
	margin: 0;
	padding: 5px 10px 5px 12px;
	width: 100%;
}

.time_date {
	color: #747474;
	display: block;
	font-size: 12px;
	margin: 8px 0 0;
}

.received_withd_msg {
	width: 57%;
}

.mesgs {
	float: left;
	padding: 30px 15px 0 25px;
	width: 60%;
}

.sent_msg p {
	background: #05728f none repeat scroll 0 0;
	border-radius: 3px;
	font-size: 14px;
	margin: 0;
	color: #fff;
	padding: 5px 10px 5px 12px;
	width: 100%;
}

.outgoing_msg {
	overflow: hidden;
	margin: 26px 0 26px;
}

.sent_msg {
	float: right;
	width: 46%;
}

.input_msg_write input {
	background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
	border: medium none;
	color: #4c4c4c;
	font-size: 15px;
	min-height: 48px;
	width: 100%;
}

.type_msg {
	border-top: 1px solid #c4c4c4;
	position: relative;
}

.msg_send_btn {
	background: #05728f none repeat scroll 0 0;
	border: medium none;
	border-radius: 50%;
	color: #fff;
	cursor: pointer;
	font-size: 17px;
	height: 33px;
	position: absolute;
	right: 0;
	top: 11px;
	width: 33px;
}

.messaging {
	padding: 0 0 50px 0;
}

.msg_history {
	height: 516px;
	overflow-y: auto;
}

#msgsize {
	height: 20px;
	width: 440px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	text-align: left;
}

#k123 {
	float: right;
}

.chat_list:hover {
	background: #CBEBAD;
}

.inbox_chat {
	background: white;
}

#bearsize {
	width: 550px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	text-align: left;
}
</style>
</head>

<script type="text/javascript">
	var ws;

	window.onload = function(){
		getRoom();
		//notice();
		
		
	}
	
	//알림메세지 10초단위로 확인하기 sessionId는 세션에저장되어있는 ucode를 넣습니다.
	
	function notice(){
		var sessionId = ${ucode};
		$.ajax({
			
			url: "<%=context%>/bear/notice",
			data: {ucode :  sessionId},
			dataType : 'text',
			success:function (data){
			
				$("#notice").append(data);
			}
			
			
		});
		
	}
	
	//-----------------------------------------------
	
	
	
	
	
	
	//socket 구간
	function wsOpen(number){
		//웹소켓 전송시 현재 방의 번호를 핸들러로 넘겨서 보낸다.
		ws = new WebSocket("ws://" + location.host + "/chating/"+number);
		wsEvt(number);
	}
	
	function wsEvt(number) {
		var roomnumber1 = number;
		ws.onopen = function(data){
			//소켓이 열리면 동작
		
			console.log("소켓이 열립니다 방번호 ccode -> " + number);
		}
		
		ws.onmessage = function(data) {
			//메시지를 받으면 동작
			console.log("메세지를받아서 동작하겠습니다.")
			var msg = data.data;
			console.log(msg)
			if(msg != null && msg.trim() != ''){
				var d = JSON.parse(msg);
				console.log(d);
				if(d.type == "getId"){
					var si = d.sessionId != null ? d.sessionId : "";
					if(si != ''){
						//$("#sessionId").val(si); 
					}
				}else if(d.type == "message"){
					console.log("message뿌립니다")
					if(d.sessionId == $("#sessionId").val()){
						
						$("#messagelist").append("<div class="+"outgoing_msg"+">"+
				        		  "<div class="+"sent_msg"+">"+
				         		   "<p>"+d.msg+"</p>"+
				          			 "</div></div>");	
					}else{
					
						$("#messagelist").append("<div class="+"incoming_msg"+">"+
 								 "<div class="+"received_withd_msg"+">"+
 									"<p>"+d.msg+ "</p>"+
    								"</div></div>");	
					}
						
				}else{
					console.warn("unknown type!");
				}
			}
		}

		
	
	}	
	function send(number) {
		var kk='${ucode}';
		var option ={
			type: "message",
			roomNumber: number,
			sessionId : kk,
			userName : $("#userName").val(),
			msg : $("#chatting").val()
		}
		console.log(option);
		ws.send(JSON.stringify(option))
		$('#chatting').val("");
	}
		
		
		
		
	
	
	
	function getRoom(){
		//$("#sessionId").val()
		//var timerID;
		var msg = {httpsession : $("#sessionId").val() };
		console.log(msg)
		$.ajax({
			url: '<%=context%>/bear/getRoom',
			data: msg,
			type: "get",

			success: function (res) {
				
				createChatingRoom(res);
			}
		
		
	

	});
	}
	
	function selectcode(){
			console.log('createroom 시작 roomname val -> ' + $('#roomName').val())
			var str = $('#roomName').val();
			console.log("상대방id - > " + str)
			var stt = str.substring(1);
			console.log("@ 자른 상대방 id2 - > " + stt)
			
			$.ajax({
				   
				url: "<%=context%>/bear/selectcode",
				data: {uatid : stt},
				dataType: 'text',
				success: function (data){
					console.log('@아이디로 회원 코드값은 : '+ data)
					var msg = {uopcode :  data};
					console.log('@아이디로 회원 msg : '+ msg)
					
					$.ajax({
						url : '<%=context%>/bear/createRoom',
						data : msg,
						type : "post",
							success: function (kkk) {
								
								if(kkk == 0 ){
									alert("방이 존재합니다")
									
									}
								else{
									alert($('#roomName').val() + "님 과 채팅방이생성되었습니다." )
									 location.reload();
								}
								
								
							}
						
						
					});$("#roomName").empty();
					
					
					
					
						
					
		
				}});
		}
			

	function enterkey(number) {
        if (window.event.keyCode == 13) {
 
             // 엔터키가 눌렸을 때 실행할 내용
             console.log("number - > " + number)
             send(number);
        }
}
 



	
	function clear(){
		$("#messagelist").empty();
		$("#messagetest").empty();
		$(".chat_list").css({'background-color' : 'white'});
		
	}

	function goRoom(number){
		console.log("goRoom 참여 하기 ")
	
		
		var msg = {roomnumber : number};
		wsOpen(number);
		clear();
		
		
		var kdsf = "<input onkeyup="+"enterkey("+number+")"+" type="+"'text'"+" class="+"'write_msg'"+" placeholder="+"'새 쪽지 작성하기'"+" id="+"'chatting'"+" />"+
	    "<button class="+"'msg_send_btn'"+" type="+"button"+" onclick="+"send("+number+")><i class="+"'fa fa-paper-plane-o'"+" aria-hidden="+"true"+"></i></button>";
	    
	    
		$.ajax({
			url: "<%=context%>/bear/moveChating",
			data: msg,
			type: "post",
			success: function (res) {
				console.log(res)
				var tag = "";
				var id = ${ucode};
				res.forEach(function(d){
					var ucode = d.ucode;//회원코드
					var roomNumber = d.ccode;//방번호
					var msg = d.cdmessage//메세지내용
					var cdtime = d.cdtime//메세지 보낸시간
					  //내가보낸 메세지 오른쪽에 붙힘
					  
					  
		if(msg == "채팅방생성되었습니다."){
			tag = "<div style="+"'text-align : center;'"+">채팅방생성되었습니다.</div><hr>";	
			
	  }else{
		 
			
				if(ucode == id ){
				tag += 	"<div class="+"outgoing_msg"+">"+
		        		  "<div class="+"sent_msg"+">"+
		         		   "<p>"+msg+"</p>"+
		          			 "<span class="+"time_date"+">"+ cdtime +"</span> </div></div>";
				}else{	
					//상대회원 왼쪽에붙힘
					tag+=  "<div class="+"incoming_msg"+">"+
		           			 "<div class="+"received_withd_msg"+">"+
		           				"<p>"+msg+ "</p>"+
		              				"<span class="+"time_date"+">"+ cdtime +"</span></div></div>";
					
				}}
					
					 
			  }); $("#messagelist").append(tag);
			  $("#messagetest").append(kdsf);
			  document.getElementById(number).style.backgroundColor ="#CBEBAD";
				
			}
			}); 
	}

	function createChatingRoom(res){
		var context = "<%=context%>";
		
		if (res == "") {
			
		 	console.log("채팅방 리스트가 비어있습니다 .")
			var tag = "<h1>텅</h1>";
			
			$("#chatingpage").css({
				"text-align": "center",
				"padding-top": "100px"	
			});
			$("#chatingpage").append(tag);
			 
		}
		else{
			//비어있다가 새로 방만들면 기존에있는 css와 내용없앨려고 만듬 
			$("#chatingpage").empty();
			$("#chatingpage").css({
				"text-align" : "",
				"padding-top": ""});
			// ---------------------------------------
			console.log("채팅방 리스트 값은 -> " +res)
			var tag = ""; 
			  
			res.forEach(function(d){
				
		
				
				var uopcode = d.uopcode ;
				var roomNumber = d.ccode;
				var msg = d.cdmessage;
				var cdtime = d.cdtime;
				var uimage = d.uimage;
				var uatid = d.uatid;
				
				    
				    
				tag +=    "<div class=chat_list  id="+roomNumber+" >" +
		         		 "<div class=chat_people>" +
		         		"<div class="+"chat_img"+"><img src="+context+"/profile_image/"+uimage+" class="+"rounded-circle"+" width="+"100"+" height="+"50"+"></div>"+
		          			 	 "<div class=chat_ib>"+
		            			  "<h5><b>" +uatid+ "</b><span class=chat_date><b>" +cdtime+ "</b></span></h5>"+
		             				 "<h5"+" id='msgsize'>"+ msg+"<span id = 'k123'>"+"</span></h5><div class="+"'boxsize'"+"  align="+"'right'"+"><button type='button' class="+"'btn btn-success'"+" onclick='goRoom(\""+roomNumber+"\")'>참여</button>"+"</div> "+
		           				 "</div>"+
		        		      "</div>"+
		                  "</div>";
						console.log("createChatingroom d 의 값 -> "+ d + " idx 의 값 -> ");
						
					//<div class="chat_img"> <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="sunil"> </div> 프로필사진 넣을때 people 다음으로넣기
			});
			$("#chatingpage").append(tag);
			
		}

		
	}

	function commonAjax(url, parameter, type, calbak, contentType){
		console.log("commonAjax실행")
		$.ajax({
			url: url,
			data: parameter,
			type: type,

			success: function (res) {
				console.log("url: " + url +"data: " + data +"type: " + type )
				calbak(res);
			},
			error : function(err){
				console.log('error');
				calbak(err);
			}
		});
	}
	
	//팔로우 추천 더보기 닫기 기능
	function closemodal(){
		location.href="../bear/chat";
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
					  
				
			
				}else 
					{console.log("저장실패")}
					
				 
			}
	});	 
	}
</script>



<body>
	<input type="hidden" value="${chainglist.ccode }" id="test1">
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
				<div class="card">
					<div class="card-body">
						<img src="<%=context %>/profile_image/${user.uimage}"
							class="rounded-circle" width="50" width="50"> <a
							class="card-title text-dark">${user.unickName }</a> <a
							class="card-subtitle mb-2 text-muted"> @${user.uatid } </a>
					</div>
					<button type="button" class="btn btn-success">로그아웃</button>
					<input type="hidden" id="sessionId" value="${ucode }">
				</div>
			</div>
		</div>

		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->


		<link
			href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
			type="text/css" rel="stylesheet">
		<div id="page-content-wrapper" style="padding: 10px">

			<br>
			<h3 class=" text-center">쪽지방</h3>
			<div class="messaging">
				<div class="inbox_msg">
					<div class="inbox_people">
						<div class="headind_srch">
							<div class="recent_heading">
								<h4>방생성</h4>
							</div>
							<div class="srch_bar">
								<div class="stylish-input-group">
									<input type="text" class="search-bar" placeholder="@아이디"
										id="roomName"> <span class="input-group-addon">
										<button type="button" onclick="selectcode()">
											<i class="fa fa-search" aria-hidden="true"></i>
										</button>
									</span>
								</div>
							</div>
						</div>



						<!-- 채팅방 목록 -->
						<div class="inbox_chat" id="chatingpage"></div>
						<!-- 채팅방 목록 끝 -->
					</div>

					<!-- 메세지보내는페이지 -->
					<div class="mesgs">
						<div class="msg_history" id="messagelist">
							<div id="nullmsg" style="text-align: center;">상대방과 대화를
								해보세요.</div>

							<!-- <div class="incoming_msg_img"> </div> 이미지넣을곳-->

						</div>
						<div class="type_msg">
							<div class="input_msg_write" id="messagetest">
								<input type="text" class="write_msg"
									placeholder="Type a message" />
								<button class="msg_send_btn" type="button">
									<i class="fa fa-paper-plane-o" aria-hidden="true"></i>
								</button>
							</div>
						</div>
					</div>
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
							<c:if test="${suggestFlist2_size>0 }">
								<c:forEach var="justFollowMe" items="${suggestFlist2 }"
									begin="0" end="2">
									<div class="card">
										<div class="card-body"
											style="font-size: 0.8rem; padding: 10px;">
											<img src="<%=context %>/profile_image/${justFollowMe.uimage}"
												class="rounded-circle" width="20" height="20"> <a
												class="card-title text-dark">${justFollowMe.unickName}</a> <a
												class="card-subtitle mb-2 text-muted">@${justFollowMe.uatid}</a>
											<button type="button"
												class="btn btn-outline-success btn-sm float-right"
												style="font-size: 0.8rem;"
												onclick="followchk(${justFollowMe.uucode})"
												name=k${justFollowMe.uucode}>팔로우</button>


										</div>
									</div>
								</c:forEach>
							</c:if>
							<!-- 팔로우하는 유저가 없을 경우 관심항목이 비슷한 사람을 추천 -->
							<c:if test="${suggestFlist2_size<1 }">
								<c:forEach var="justFollowMe" items="${suggestFlist2 }">
									<div class="card">
										<div class="card-body"
											style="font-size: 0.8rem; padding: 10px;">
											<img
												src="${resourcePath }/profile_image/${justFollowMe.uimage}"
												class="rounded-circle" width="20" height="20"> <a
												class="card-title text-dark">${justFollowMe.unickName}</a> <a
												class="card-subtitle mb-2 text-muted">@${justFollowMe.uatid}</a>
											<button type="button"
												class="btn btn-outline-success btn-sm float-right"
												style="font-size: 0.8rem;">팔로우</button>
										</div>
									</div>
								</c:forEach>
							</c:if>
						</div>
						<c:if test="${suggestFlist2_size>0 }">
							<button type="button" class="btn btn-outline-success"
								id="writeBtn" data-toggle="modal" data-target="#morebtn">더보기
							</button>
						</c:if>
					</div>
				</div>

				<div class="list-group-item list-group-item-action bg-light"
					style="padding: 5px;">
					<div class="card bg-light mb-3">
						<div class="card-header">실시간 들어온 유저</div>
						<div class="card-body" style="padding: 5px;">

							<c:forEach var="user" items="${useronline}">
								<div class="card">
									<div class="card-body"
										style="font-size: 0.8rem; padding: 10px;">
										<img src="/img/teemo.jpg" class="rounded-circle" width="20"
											height="20"> <a class="card-title text-dark">${user.unickname}</a>
										<a class="card-subtitle mb-2 text-muted"> ${user.uatid}</a> <img
											src="/img/online.svg" style="float: right" width="50"
											height="20">
									</div>
								</div>
							</c:forEach>

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



</body>

</html>