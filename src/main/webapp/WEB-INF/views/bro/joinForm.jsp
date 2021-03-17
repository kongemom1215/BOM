<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"/ -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>
<!-- Bootstrap -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) -->
<script src="http://code.jquery.com/jquery.js"></script>
<!-- 모든 합쳐진 플러그인을 포함하거나 (아래) 필요한 각각의 파일들을 포함하세요 -->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<!-- Respond.js 으로 IE8 에서 반응형 기능을 활성화하세요 (https://github.com/scottjehl/Respond) -->
<script
	src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
</head>
<script type="text/javascript">
    $(function() {
   	 $(".alert-success").hide();
   	 $(".alert-different").hide();
   	 $("input").keyup(function(){
   		 var user_password = $("#user_password").val();
   	     var user_password1 = $("#user_password1").val();
   	     if(user_password != "" || user_password1 != "") {
   	    	 if(user_password == user_password1) {
   	    		  $(".alert-success").show();
   	    		  $(".alert-different").hide();
   	    		  $("#submit").removeAttr("disabled");
   	    		 
   	    	 }else {
   	    		 $(".alert-success").hide();
   	    		 $(".alert-different").show();
   	    		 $("#submit").attr("disabled" , "disabled");
   	    		 
   	    	 }
   	     }
   	     
   	 }); 
     }); 
    
    
    
    </script>
<body>
	<div class="container">
		<!-- 좌우측의 공간 확보 -->
		<!-- 헤더 들어가는 부분 -->


		<!--// 헤더 들어가는 부분 -->
		<!-- 모달창 -->
		<div class="modal fade" id="defaultModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title">알림</h4>
					</div>
					<div class="modal-body">
						<p class="modal-contents"></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
		<!--// 모달창 -->
		<hr />
		<!-- 본문 들어가는 부분 -->



		<form class="form-horizontal" role="form" method="post" action="joinForm">

			<div class="form-group" id="divId">
				<label for="inputId" class="col-lg-2 control-label">이메일</label>
				<div class="col-lg-10">
					<input type="email" class="form-control onlyAlphabetAndNumber"
						name="uEmail" id="uEmail" data-rule-required="true"
						placeholder="30자이내의 알파벳, 언더스코어(_), 숫자만 입력 가능합니다." maxlength="30" required="required">
					<div id="Email_check"></div>
				</div>
			</div>
			<div class="form-group" id="divPassword">
				<label for="inputPassword" class="col-lg-2 control-label">비밀번호</label>
				<div class="col-lg-10">
					<input type="password" class="form-control" name="uPassword"
						id="user_password" name="excludeHangul" data-rule-required="true"
						placeholder="패스워드" maxlength="30">
				</div>
			</div>
			<div class="form-group" id="divPasswordCheck">
				<label for="inputPasswordCheck" class="col-lg-2 control-label">비밀번호
					확인</label>
				<div class="col-lg-10">
					<input type="password" class="form-control" id="user_password1"
						data-rule-required="true" placeholder="패스워드 확인" maxlength="30">
					<div class="alert-success">비밀번호가 일치합니다.</div>
					<div class="alert-different">비밀번호가 불일치합니다.</div>
				</div>
			</div>
			<div class="form-group" id="divName">
				<label for="inputName" class="col-lg-2 control-label">아이디</label>
				<div class="col-lg-10">
					<input type="text" class="form-control onlyHangul" name="uAtid"
						id="uAtid" data-rule-required="true" placeholder="한글만 입력 가능합니다."
						maxlength="15">
					<div id="id_check"></div>
					
				</div>
			</div>

			<div class="form-group" id="divNickname">
				<label for="inputNickname" class="col-lg-2 control-label">닉네임</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" id="nickname"
						name="uNickname" data-rule-required="true" placeholder="별명"
						maxlength="15">
				</div>
			</div>

			<div class="form-group" id="divEmail">
				<label for="inputEmail" class="col-lg-2 control-label">생년월일</label>
				<div class="col-lg-10">
					<input type="Date"  name="uBirth" class="form-control" 
						data-rule-required="true" placeholder="이메일" maxlength="40">
				</div>
			</div>
			<div class="form-group" id="divPhoneNumber">
				<label for="inputPhoneNumber" class="col-lg-2 control-label">국가</label>
				<div class="col-lg-10">
					<select name="uNation" class="form-control">
						<option value="kr">Korea</option>
						<option value="ad">Andorra</option>
						<option value="dz">Algeria</option>
						<option value="ao">Angola</option>
						<option value="ai">Anguilla</option>
						<option value="ag">Antigua &amp; Barbuda</option>
						<option value="ar">Argentina</option>
						<option value="am">Armenia</option>
						<option value="aw">Aruba</option>
						<option value="au">Australia</option>
						<option value="at">Austria</option>
						<option value="us">United States</option>
						<option value="at">Austria</option>
						<option value="br">Brazil</option>
						<option value="ca">Canada</option>
						<option value="uy">Uruguay</option>
						<option value="ar">Argentina</option>
						<option value="de">Germany</option>
						<option value="gb">United Kingdom</option>
						<option value="fr">France</option>
						<option value="vi">Dominican Republic &amp; Virgin
							Islands, US</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="inputPhoneNumber" class="col-lg-2 control-label">성별</label>
				<div class="col-lg-10">
					<select class="form-control" id="gender" name="uGender">
						<option value="m">남</option>
						<option value="w">여</option>
					</select>
				</div>
			</div>

			
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<input type="submit" class="btn btn-primary" value="다음">
				</div>
			</div>

		</form>
	</div>
</body>
<script type="text/javascript">
                $("#uEmail").blur(function() {

                	// id = "id_reg" / name = "userId"
                	var uEmail = $('#uEmail').val();
                	$.ajax({
                		url : '/checkEmail?uEmail='+ uEmail,
                		type : 'get',
                		success : function(data) {
                			console.log("1 = 중복o / 0 = 중복x : "+ data);							
                			
                			if (data == 1) {
                					// 1 : 아이디가 중복되는 문구
                					$("#Email_check").text("사용중인 아이디입니다 :p");
                					$("#Email_check").css("color", "red");
                					$("#reg_submit").attr("disabled", true);
                				}
                			if(data == 0){
                					$("#Email_check").text("사용가능한 아이디입니다 :p");
                					$("#Email_check").css("color", "red");
                					$("#reg_submit").attr("disabled", true);
                					
                				}
                			
                			
                			else {
                					
                					if(idJ.test(uEmail)){
                						// 0 : 아이디 길이 / 문자열 검사
                						$("#Email_check").text("");
                						$("#reg_submit").attr("disabled", false);
                			
                					} else if(uEmail == ""){
                						
                						$('#Email_check').text('아이디를 입력해주세요 :)');
                						$('#Email_check').css('color', 'red');
                						$("#reg_submit").attr("disabled", true);				
                						
                					} else {
                						
                						$('#uEmail_check').text("아이디는 소문자와 숫자 4~12자리만 가능합니다 :) :)");
                						$('#uEmail_check').css('color', 'red');
                						$("#reg_submit").attr("disabled", true);
                					}
                				
                					
                				}
                			}, error : function() {
                					console.log("실패");
                			}
                		});
                	});  
                $("#uAtid").blur(function() {
                	    

                		// id = "id_reg" / name = "userId"
                		var uAtid = $('#uAtid').val();
                		$.ajax({
                			url : '/checkAtid?uAtid='+ uAtid,
                			type : 'get',
                			success : function(data) {
                				console.log("1 = 중복o / 0 = 중복x : "+ data);							
                				
                				if (data == 1) {
                						// 1 : 아이디가 중복되는 문구
                						$("#id_check").text("사용중인 아이디입니다 :p");
                						$("#id_check").css("color", "red");
                						$("#reg_submit").attr("disabled", true);
                					}
                				if(data == 0){
                						$("#id_check").text("사용가능한 아이디입니다 :p");
                						$("#id_check").css("color", "red");
                						$("#reg_submit").attr("disabled", true);
                						
                					}
                				
                				
                				else {
                						
                						if(idJ.test(uAtid)){
                							// 0 : 아이디 길이 / 문자열 검사
                							$("#id_check").text("");
                							$("#reg_submit").attr("disabled", false);
                				
                						} else if(uAtid == ""){
                							
                							$('#id_check').text('아이디를 입력해주세요 :)');
                							$('#id_check').css('color', 'red');
                							$("#reg_submit").attr("disabled", true);				
                							
                						} else {
                							
                							$('#id_check').text("아이디는 소문자와 숫자 4~12자리만 가능합니다 :) :)");
                							$('#id_check').css('color', 'red');
                							$("#reg_submit").attr("disabled", true);
                						}
                					
                						
                					}
                				}, error : function() {
                						console.log("실패");
                				}
                			});
                		});
                
                
                
                </script>
</html>


