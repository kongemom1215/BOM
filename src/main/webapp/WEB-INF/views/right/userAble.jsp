<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${result2 > 0 }">
	<script type="text/javascript">
		alert("복구 되었습니다! 다시 봄에 오신걸 환영합니다 :) 다시 로그인 해주세요.");
		location.href = "/bro/index";
	</script>
</c:if>
<c:if test="${result2 ==0 }">
	<script type="text/javascript">
		alert("실패");
		location.href = "/right/UserdisabledPage";
	</script>
</c:if>
</body>
</html>