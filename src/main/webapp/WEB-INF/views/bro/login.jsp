<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="../css/login.css" rel="stylesheet" type="text/css">

</head>
<c:set var="uState" value="${uState}"></c:set>
<script type="text/javascript">
	function state() {
		var uStateNum = "${uState}";
		if (uStateNum == 0) {
			alert("탈퇴된 계정입니다");
			return false;
		} else if (uStateNum == 2) {
			alert("관리자에 의하여 정지된 계정입니다 고객센터 에 문의해주세요.");
			return false;
		} else {
			alert("환영합니다");
			return true;

		}
	}
</script>
<body>
	<c:set var="uState" value="${uState}"></c:set>


	<div class="wrapper fadeInDown">
		<div id="formContent">
			<!-- Tabs Titles -->

			<!-- Login Form -->
			<form action="login" method="post">
				<input type="email" id="uEmail" class="fadeIn second" name="uEmail"
					placeholder="login"> <input type="password" id="uPassword"
					class="fadeIn third" name="uPassword" placeholder="password">
				<input type="submit" class="fadeIn fourth" value="Log In">


			</form>


			<div id="formFooter">
				<a class="underlineHover" href="join">회원가입</a>
				<p>
					<a class="underlineHover" href="email">비밀번호찾기</a>
			</div>

		</div>
	</div>

</body>
</html>