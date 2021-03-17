<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
iframe {
	width: 0px;
	height: 0px;
	border: 0px
}
</style>
</head>
<body>
<%--   <p>  경복궁1 :  <img alt="경복궁1" src="${pageContext.request.contextPath}/upload/3ea46a55-fa04-40a1-9db6-6cf9aad37963_20150702_115327.jpg">
  <p>    kkk  <img src="c:\Upload\kh2.jpg">
 --%>
 <div class="form-group" id="divId">	
   <form id="form1" action="uploadForm" method="post" enctype="multipart/form-data" target="myBatisFrame">
		<label for="inputId" class="col-lg-2 control-label">프로필 이미지설정</label><p>
		<input type="file" class="form-control"  name="uImage"> <p>
	    <input type="hidden" name="path" value="${pageContext.request.contextPath}/resources/image/"> 
		<input type="submit"><p>
		<button type="button" onclick="location.href='interestForm?uCode=${uCode}'">다음</button>
		
	</form>
	</div>

	<iframe name="myBatisFrame"></iframe>

	<script>
		function addFilePath(msg) {
			alert(msg);
			document.getElementById("form1").reset();
		}
	</script>

</body>
</html>

