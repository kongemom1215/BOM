<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
function pushbutton() {
	alert("이메일로 비밀번호가 전송되었습니다");  // n 은 줄을 바꿀때 사용합니다
	}

</script>
</head>
<body>
<div>
        <form action="emailAu" method="post">
            <h2>비밀번호 찾기</h2>
            <p>
                아래 이메일주소를 입력하시면,<br> 입력하신 이메일로 새암호를 보내드립니다<br>
                <br>
            <div>
                <input type="text" name="uEmail" id = "uEmail" placeholder="이메일주소">
                 <div id="Email_check"></div>
                <p>'@'포함한 이메일주소를 정확히 입력해주세요.</p>
            </div>
 
            <div>
                <button type="submit" disabled="disabled" id = "findPw_btn" onclick="pushbutton()">비밀번호찾기</button>
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