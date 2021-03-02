<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<title>Home</title>
		<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<script type="text/javascript">
	$(document).ready(function(){
		$("#logoutBtn").on("click", function(){
			location.href="logout";
		})
		
	})
</script>
<body>
	<form action="login" method="post">
			<div>
				<label for="uEmail"></label>
				<input type="text" id="uEmail" name="uEmail">
			</div>
			<div>
				<label for="uPassword"></label>
				<input type="password" id="uPassword" name="uPassword">
			</div>
			<div>
				<button type="submit">로그인</button>
				<button type="button">회원가입</button>
			</div>
	</form>
</body>
</html>