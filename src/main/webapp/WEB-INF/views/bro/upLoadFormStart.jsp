<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
<style>
iframe {
	width: 0px;
	height: 0px;
	border: 20px
}
</style>




</head>
<body>
<%--   <p>  경복궁1 :  <img alt="경복궁1" src="${pageContext.request.contextPath}/upload/3ea46a55-fa04-40a1-9db6-6cf9aad37963_20150702_115327.jpg">
  <p>    kkk  <img src="c:\Upload\kh2.jpg">
 --%>
 <div class="wrapper fadeInDown">
  <div id="formContent">

   <form id="form1" action="uploadForm" method="post" enctype="multipart/form-data" target="myBatisFrame">
		<h2>프로필 이미지 설정</h2>
		<input type="file"   name="uImage"> <p>
	    <input type="hidden" name="path" value="${pageContext.request.contextPath}/resources/image/"> 
		<input type="submit" value="설정" onClick="alert('프로필 이미지 설정이 완료되었습니다')"><p>
		<input type="button" onclick="location.href='interestForm?uCode=${uCode}'" value="다음">
		 
	</form>
	</div>
	</div>



	

</body>
<script>
		function addFilePath(msg) {
			alert(msg);
			document.getElementById("form1").reset();
		}
	</script>
<iframe name="myBatisFrame"></iframe>
</html>

