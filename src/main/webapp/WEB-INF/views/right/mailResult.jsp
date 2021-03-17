<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	
	/* checknum Form Run */
	function runChecknum(){
		 alert("runChecknum Start..");
         document.getElementById('frm').submit();
	}

 </script>
<title>Insert title here</title>
</head>
<body onload="runChecknum();">
 <c:if test="${check==1}">
	<form action="checknum" method="post" id="frm">
		<script type="text/javascript">
			alert("메일이 전송되었습니다. 인증번호를 입력해주세요.");
		</script>
		<input type="hidden" name="num" value="${num}">
	</form>
</c:if>
 <c:if test="${check!=1}">
 	<script type="text/javascript">
		alert("메일전송이 실패되었습니다..");
		location.href = "/right/doubleSecurity";
	</script>
 </c:if>

</body>
</html>