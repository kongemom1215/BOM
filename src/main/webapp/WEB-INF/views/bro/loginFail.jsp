<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${uState==0 }">
		<script type="text/javascript">
			alert("탈퇴된 계정입니다");
			location.href = "index";
		</script>
	</c:if>
	<c:if test="${uState==1}">
		<script type="text/javascript">
			alert("아이디,비밀번호가 틀립니다.");
			location.href = "index";
		</script>

	</c:if>
	<c:if test="${uState==2 }">
		<script type="text/javascript">
			alert("정지된 계정입니다 고객센터에 문의해주세요");
			location.href = "index";
		</script>

	</c:if>

	<c:if test="${uLoginCount >= 5 }">
		<script type="text/javascript">
			alert("비밀번호가 5회이상 잘못입력되었습니다 비밀번호를 변갱해주세요");
			location.href = "index";
		</script>

	</c:if>


</body>
</html>