<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"/ -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>
<!-- Bootstrap -->
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
<link href="../css/joinForm.css" rel="stylesheet" type="text/css">
</head>
<script type="text/javascript">
	$(function() {
		$(".alert-success").hide();
		$(".alert-different").hide();
		$("input").keyup(function() {
			var user_password = $("#user_password").val();
			var user_password1 = $("#user_password1").val();
			if (user_password != "" || user_password1 != "") {
				if (user_password == user_password1) {
					$(".alert-success").show();
					$(".alert-different").hide();
					$("#submit").removeAttr("disabled");

				} else {
					$(".alert-success").hide();
					$(".alert-different").show();
					$("#submit").attr("disabled", "disabled");

				}
			}

		});
	});
</script>
<body>
	<div class="wrapper fadeInDown">
		<div id="formContent">

			<!-- 좌우측의 공간 확보 -->
			<!-- 헤더 들어가는 부분 -->


			<!--// 헤더 들어가는 부분 -->
			<!-- 모달창 -->
			<div class="modal fade" id="defaultModal">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">×</button>
							<h4 class="modal-title">알림</h4>
						</div>
						<div class="modal-body">
							<p class="modal-contents"></p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">닫기</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!--// 모달창 -->
			<hr />
			<!-- 본문 들어가는 부분 -->



			<form class="form-horizontal" role="form" method="post"
				action="joinForm">

				<div class="form-group" id="divId">
					<label for="inputId" class="col-lg-12 control-label">이메일</label>
					<div class="col-lg-12">
						<input type="text" class="form-control onlyAlphabetAndNumber"
							name="uEmail" id="uEmail" data-rule-required="true"
							placeholder="@을 포함한 정확한 이메일주소를 입력하여주세요" maxlength="30">
						<div id="Email_check"></div>
					</div>
				</div>
				<div class="form-group" id="divPassword">
					<label for="inputPassword" class="col-lg-4 control-label">비밀번호</label>
					<div class="col-lg-12">
						<input type="password" class="form-control" name="uPassword"
							id="user_password" name="excludeHangul" data-rule-required="true"
							placeholder="패스워드" maxlength="30">
					</div>
				</div>
				<div class="form-group" id="divPasswordCheck">
					<label for="inputPasswordCheck" class="col-lg-6 control-label">비밀번호
						확인</label>
					<div class="col-lg-12">
						<input type="password" class="form-control" id="user_password1"
							data-rule-required="true" placeholder="패스워드 확인" maxlength="30">
						<div class="alert-success">비밀번호가 일치합니다.</div>
						<div class="alert-different">비밀번호가 불일치합니다.</div>
					</div>
				</div>
				<div class="form-group" id="divName">
					<label for="inputName" class="col-lg-2 control-label">아이디</label>
					<div class="col-lg-12">
						<input type="text" class="form-control onlyHangul" name="uAtid"
							id="uAtid" data-rule-required="true" placeholder="영문만 입력 가능합니다."
							maxlength="15">
						<div id="id_check"></div>

					</div>
				</div>

				<div class="form-group" id="divNickname">
					<label for="inputNickname" class="col-lg-2 control-label">닉네임</label>
					<div class="col-lg-12">
						<input type="text" class="form-control" id="nickname"
							name="uNickname" data-rule-required="true" placeholder="별명"
							maxlength="15">
					</div>
				</div>

				<div class="form-group" id="divEmail">
					<label for="inputEmail" class="col-lg-4 control-label">생년월일</label>
					<div class="col-lg-12">
						<input type="Date" name="uBirth" class="form-control"
							data-rule-required="true" placeholder="생년월일" maxlength="40">
					</div>
				</div>
				<div class="form-group" id="divPhoneNumber">
					<label for="inputPhoneNumber" class="col-lg-2 control-label">국가</label>
					<div class="col-lg-12">
						<select name="uNation" class="form-control">
							<option value="가나">가나</option>
                        <option value="가봉">가봉</option>
                        <option value="가이아나">가이아나</option>
                        <option value="감비아">감비아</option>
                        <option value="건지">건지</option>
                        <option value="과들루프">과들루프</option>
                        <option value="과테말라">과테말라</option>
                        <option value="괌">괌</option>
                        <option value="그레나다">그레나다</option>
                        <option value="그리스">그리스</option>
                        <option value="그린란드">그린란드</option>
                        <option value="기니">기니</option>
                        <option value="기니비사우">기니비사우</option>
                        <option value="나미비아">나미비아</option>
                        <option value="나우루">나우루</option>
                        <option value="나이지리아">나이지리아</option>
                        <option value="남아프리카">남아프리카</option>
                        <option value="네덜란드">네덜란드</option>
                        <option value="네덜란드령 카리브">네덜란드령 카리브</option>
                        <option value="네팔">네팔</option>
                        <option value="노르웨이">노르웨이</option>
                        <option value="노퍽섬">노퍽섬</option>
                        <option value="뉴질랜드">뉴질랜드</option>
                        <option value="뉴칼레도니아">뉴칼레도니아</option>
                        <option value="니우에">니우에</option>
                        <option value="니제르">니제르</option>
                        <option value="니카라과">니카라과</option>
                        <option value="대만">대만</option>
                        <option value="대한민국">대한민국</option>
                        <option value="덴마크">덴마크</option>
                        <option value="도미니카">도미니카</option>
                        <option value="독일">독일</option>
                        <option value="동티모르">동티모르</option>
                        <option value="라오스">라오스</option>
                        <option value="라이베리아">라이베리아</option>
                        <option value="라트비아">라트비아</option>
                        <option value="러시아">러시아</option>
                        <option value="레바논">레바논</option>
                        <option value="레소토">레소토</option>
                        <option value="루마니아">루마니아</option>
                        <option value="룩셈부르크">룩셈부르크</option>
                        <option value="르완다">르완다</option>
                        <option value="리비아">리비아</option>
                        <option value="리유니온">리유니온</option>
                        <option value="리투아니아">리투아니아</option>
                        <option value="리히텐슈타인">리히텐슈타인</option>
                        <option value="마다가스카르">마다가스카르</option>
                        <option value="마르티니크">마르티니크</option>
                        <option value="마요트">마요트</option>
                        <option value="마카오(중국 특별행정구)">마카오(중국 특별행정구)</option>
                        <option value="마케도니아">마케도니아</option>
                        <option value="말라위">말라위</option>
                        <option value="말레이시아">말레이시아</option>
                        <option value="말리">말리</option>
                        <option value="멕시코">멕시코</option>
                        <option value="모나코">모나코</option>
                        <option value="모로코">모로코</option>
                        <option value="모리셔스">모리셔스</option>
                        <option value="모리타니">모리타니</option>
                        <option value="모잠비크">모잠비크</option>
                        <option value="몬테네그로">몬테네그로</option>
                        <option value="몬트세라트">몬트세라트</option>
                        <option value="몰도바">몰도바</option>
                        <option value="몰디브">몰디브</option>
                        <option value="몰타">몰타</option>
                        <option value="몽골">몽골</option>
                        <option value="미국">미국</option>
                        <option value="미크로네시아">미크로네시아</option>
                        <option value="바누아투">바누아투</option>
                        <option value="바레인">바레인</option>
                        <option value="바베이도스">바베이도스</option>
                        <option value="바티칸 시국">바티칸 시국</option>
                        <option value="바하마">바하마</option>
                        <option value="방글라데시">방글라데시</option>
                        <option value="버뮤다">버뮤다</option>
                        <option value="베냉">베냉</option>
                        <option value="베네수엘라">베네수엘라</option>
                        <option value="베트남">베트남</option>
                        <option value="벨기에">벨기에</option>
                        <option value="벨라루스">벨라루스</option>
                        <option value="벨리즈">벨리즈</option>
                        <option value="보스니아 헤르체고비나">보스니아 헤르체고비나</option>
                        <option value="보츠와나">보츠와나</option>
                        <option value="볼리비아">볼리비아</option>
                        <option value="부룬디">부룬디</option>
                        <option value="부르키나파소">부르키나파소</option>
                        <option value="부베섬">부베섬</option>
                        <option value="부탄">부탄</option>
                        <option value="북마리아나제도">북마리아나제도</option>
                        <option value="불가리아">불가리아</option>
                        <option value="브라질">브라질</option>
                        <option value="브루나이">브루나이</option>
                        <option value="사모아">사모아</option>
                        <option value="사우디아라비아">사우디아라비아</option>
                        <option value="산마리노">산마리노</option>
                        <option value="상투메 프린시페">상투메 프린시페</option>
                        <option value="생마르탱">생마르탱</option>
                        <option value="생바르텔레미">생바르텔레미</option>
                        <option value="생피에르 미클롱">생피에르 미클롱</option>
                        <option value="세네갈">세네갈</option>
                        <option value="세르비아">세르비아</option>
                        <option value="세이셸">세이셸</option>
                        <option value="스리랑카">스리랑카</option>
                        <option value="스와질란드">스와질란드</option>
                        <option value="스웨덴">스웨덴</option>
                        <option value="스위스">스위스</option>
                        <option value="스페인">스페인</option>
                        <option value="슬로바키아">슬로바키아</option>
                        <option value="슬로베니아">슬로베니아</option>
                        <option value="시에라리온">시에라리온</option>
                        <option value="신트마르턴">신트마르턴</option>
                        <option value="싱가포르">싱가포르</option>
                        <option value="아랍에미리트">아랍에미리트</option>
                        <option value="아루바">아루바</option>
                        <option value="아르메니아">아르메니아</option>
                        <option value="아르헨티나">아르헨티나</option>
                        <option value="아메리칸 사모아">아메리칸 사모아</option>
                        <option value="아이슬란드">아이슬란드</option>
                        <option value="아이티">아이티</option>
                        <option value="아일랜드">아일랜드</option>
                        <option value="아제르바이잔">아제르바이잔</option>
                        <option value="아프가니스탄">아프가니스탄</option>
                        <option value="안도라">안도라</option>
                        <option value="알바니아">알바니아</option>
                        <option value="알제리">알제리</option>
                        <option value="앙골라">앙골라</option>
                        <option value="앤티가 바부다">앤티가 바부다</option>
                        <option value="앵귈라">앵귈라</option>
                        <option value="에리트리아">에리트리아</option>
                        <option value="에스토니아">에스토니아</option>
                        <option value="에콰도르">에콰도르</option>
                        <option value="에티오피아">에티오피아</option>
                        <option value="엘살바도르">엘살바도르</option>
                        <option value="영국">영국</option>
                        <option value="예멘">예멘</option>
                        <option value="오만">오만</option>
                        <option value="오스트레일리아">오스트레일리아</option>
                        <option value="오스트리아">오스트리아</option>
                        <option value="요르단">요르단</option>
                        <option value="우간다">우간다</option>
                        <option value="우루과이">우루과이</option>
                        <option value="우즈베키스탄">우즈베키스탄</option>
                        <option value="우크라이나">우크라이나</option>
                        <option value="이라크">이라크</option>
                        <option value="이란">이란</option>
                        <option value="이스라엘">이스라엘</option>
                        <option value="이집트">이집트</option>
                        <option value="이탈리아">이탈리아</option>
                        <option value="인도">인도</option>
                        <option value="인도네시아">인도네시아</option>
                        <option value="일본">일본</option>
                        <option value="자메이카">자메이카</option>
                        <option value="잠비아">잠비아</option>
                        <option value="저지">저지</option>
                        <option value="지부티">지부티</option>
                        <option value="지브롤터">지브롤터</option>
                        <option value="짐바브웨">짐바브웨</option>
                        <option value="차드">차드</option>
                        <option value="체코">체코</option>
                        <option value="칠레">칠레</option>
                        <option value="카메룬">카메룬</option>
                        <option value="카보베르데">카보베르데</option>
                        <option value="카자흐스탄">카자흐스탄</option>
                        <option value="카타르">카타르</option>
                        <option value="캄보디아">캄보디아</option>
                        <option value="캐나다">캐나다</option>
                        <option value="케냐">케냐</option>
                        <option value="코스타리카">코스타리카</option>
                        <option value="코트디부아르">코트디부아르</option>
                        <option value="콜롬비아">콜롬비아</option>
                        <option value="쿠바">쿠바</option>
                        <option value="쿠웨이트">쿠웨이트</option>
                        <option value="퀴라소">퀴라소</option>
                        <option value="크로아티아">크로아티아</option>
                        <option value="타지키스탄">타지키스탄</option>
                        <option value="탄자니아">탄자니아</option>
                        <option value="태국">태국</option>
                        <option value="터키">터키</option>
                        <option value="토고">토고</option>
                        <option value="토켈라우">토켈라우</option>
                        <option value="통가">통가</option>
                        <option value="투르크메니스탄">투르크메니스탄</option>
                        <option value="투발루">투발루</option>
                        <option value="튀니지">튀니지</option>
                        <option value="트리니다드 토바고">트리니다드 토바고</option>
                        <option value="파나마">파나마</option>
                        <option value="파라과이">파라과이</option>
                        <option value="파키스탄">파키스탄</option>
                        <option value="파푸아뉴기니">파푸아뉴기니</option>
                        <option value="팔라우">팔라우</option>
                        <option value="페루">페루</option>
                        <option value="포르투갈">포르투갈</option>
                        <option value="폴란드">폴란드</option>
                        <option value="푸에르토리코">푸에르토리코</option>
                        <option value="프랑스">프랑스</option>
                        <option value="피지">피지</option>
                        <option value="핀란드">핀란드</option>
                        <option value="필리핀">필리핀</option>
                        <option value="홍콩(중국 특별행정구)">홍콩(중국 특별행정구)</option>
                        <option value="헝가리">헝가리</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPhoneNumber" class="col-lg-2 control-label">성별</label>
					<div class="col-lg-12">
						<select class="form-control" id="gender" name="uGender">
							<option value="mail">남</option>
							<option value="femail">여</option>
						</select>
					</div>
				</div>


				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-12">
						<input type="submit" value="다음">
					</div>
				</div>

			</form>

		</div>
	</div>
</body>
<script type="text/javascript">
	$("#uEmail").blur(
			function() {

				// id = "id_reg" / name = "userId"
				var uEmail = $('#uEmail').val();
				$.ajax({
					url : '/checkEmail?uEmail=' + uEmail,
					type : 'get',
					success : function(data) {
						console.log("1 = 중복o / 0 = 중복x : " + data);

						if (data == 1) {
							// 1 : 아이디가 중복되는 문구
							$("#Email_check").text("사용중인 이메일입니다 :p");
							$("#Email_check").css("color", "red");
							$("#reg_submit").attr("disabled", true);
						}
						if (data == 0) {
							$("#Email_check").text("사용가능한 이메일입니다 :p");
							$("#Email_check").css("color", "red");
							$("#reg_submit").attr("disabled", true);

						}

						else {

							if (idJ.test(uEmail)) {
								// 0 : 아이디 길이 / 문자열 검사
								$("#Email_check").text("");
								$("#reg_submit").attr("disabled", false);

							} else if (uEmail == " ") {

								$('#Email_check').text('아이디를 입력해주세요 :)');
								$('#Email_check').css('color', 'red');
								$("#reg_submit").attr("disabled", true);

							} else {

								$('#uEmail_check').text(
										"아이디는 소문자와 숫자 4~12자리만 가능합니다 :) :)");
								$('#uEmail_check').css('color', 'red');
								$("#reg_submit").attr("disabled", true);
							}

						}
					},
					error : function() {
						console.log("실패");
					}
				});
			});
	$("#uAtid").blur(
			function() {

				// id = "id_reg" / name = "userId"
				var uAtid = $('#uAtid').val();
				$.ajax({
					url : '/checkAtid?uAtid=' + uAtid,
					type : 'get',
					success : function(data) {
						console.log("1 = 중복o / 0 = 중복x : " + data);

						if (data == 1) {
							// 1 : 아이디가 중복되는 문구
							$("#id_check").text("사용중인 아이디입니다 :p");
							$("#id_check").css("color", "red");
							$("#reg_submit").attr("disabled", true);
						}
						if (data == 0) {
							$("#id_check").text("사용가능한 아이디입니다 :p");
							$("#id_check").css("color", "red");
							$("#reg_submit").attr("disabled", true);

						}

						else {

							if (idJ.test(uAtid)) {
								// 0 : 아이디 길이 / 문자열 검사
								$("#id_check").text("");
								$("#reg_submit").attr("disabled", false);

							} else if (uAtid == " ") {

								$('#id_check').text('아이디를 입력해주세요 :)');
								$('#id_check').css('color', 'red');
								$("#reg_submit").attr("disabled", true);

							} else {

								$('#id_check').text(
										"아이디는 소문자와 숫자 4~12자리만 가능합니다 :) :)");
								$('#id_check').css('color', 'red');
								$("#reg_submit").attr("disabled", true);
							}

						}
					},
					error : function() {
						console.log("실패");
					}
				});
			});
</script>
</html>


