<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
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
<body>
	<div class="wrapper fadeInDown">
		<div id="formContent">
			<c:set var="tempPassword" value="${tempPassword}"></c:set>
			<c:if test="${check==1}">

				<div align="center">
					<h2>이메일로전송된 인증번호를 입력해주세요</h2>
					<p>
					<div class="form-group" id="divId">
						<label for="inputId" class="col-lg-2 control-label"></label>
						<div class="col-lg-10">
							<input type="text" class="form-control" id="temp" maxlength="30">
							<div id="temp_check"></div>
						</div>
					</div>




					<%--   <c:if test="${tempPassword==temp }"></c:if> --%>

					<button type="button" id="email_btn" disabled="true"
						onclick="location.href='upLoadFormStart'">다음</button>
				</div>



			</c:if>


			<c:if test="${check!=1}">메일전송이 실패되었습니다</c:if>
			<c:if test="${check==null}">
			</c:if>
		</div>

	</div>
</body>
<c:set var="tempPassword" value="${tempPassword}"></c:set>
<script type="text/javascript">

$("#temp").blur(function() {

	// id = "id_reg" / name = "userId"
	// 입력 인증번호
	var temp = $('#temp').val();
	var ContPassword = ${tempPassword};
	
			if (ContPassword == temp) {
					
					$("#temp_check").text("인증번호가 일치합니다.");
					$("#temp_check").css("color", "red");
					$("#reg_submit").attr("disabled", true);
					$("#email_btn").attr("disabled", false);
				}
			else{
					$("#temp_check").text("인증번호가 다릅니다.");
					$("#temp_check").css("color", "red");
					$("#reg_submit").attr("disabled", true);
					$("#email_btn").attr("disabled", true);
				} 
		}
			
);
	 

</script>
</html>