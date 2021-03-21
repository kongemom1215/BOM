<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery.js"></script>
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
	<link href="../css/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function pushbutton() {
	alert("이메일로 비밀번호가 전송되었습니다");  // n 은 줄을 바꿀때 사용합니다
	}

</script>
</head>
<body>
<div class="wrapper fadeInDown">
  <div id="formContent">
        <form action="emailAu" method="post">
            <h1>비밀번호 찾기</h1>
            <p>
                아래 이메일주소를 입력하시면,<br> 입력하신 이메일로 새암호를 보내드립니다<br>
                
            <div>
                <input type="text" name="uEmail" id = "uEmail" placeholder="'@'포함한 이메일주소를 정확히 입력해주세요.">
                 <div id="Email_check"></div>
                
            </div>
 
            <div>
                <button type="submit" disabled="disabled" id = "findPw_btn" onclick="pushbutton()">비밀번호찾기</button>
            </div>
        </form>
    </div>
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
				$("#Email_check").text("아래 비밀번호 찾기를 눌러주세요 :p");
					$("#reg_submit").attr("disabled", true);
					$("#findPw_btn").attr("disabled", false);
				}
			
			if(data == 0){
					$("#Email_check").text("존재 하지 않는 이메일 입니다 :p");
					$("#Email_check").css("color", "red");
					$("#reg_submit").attr("disabled", true);
					$("#findPw_btn").attr("disabled", true);
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


</script>
</html>