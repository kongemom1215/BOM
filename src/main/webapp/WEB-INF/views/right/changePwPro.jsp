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
 <c:if test="${ui.uidentify==0}">
	<form action="changePw1" method="post" id="frm">
		<input type="hidden" name="pwd" value="${pwd}">
	</form>
</c:if>
 <c:if test="${ui.uidentify==1}">
 	<form action="changePw2Mail" method="post" id="frm">
		<input type="hidden" name="pwd" value="${pwd}">
	</form>
 </c:if>

</body>
</html>