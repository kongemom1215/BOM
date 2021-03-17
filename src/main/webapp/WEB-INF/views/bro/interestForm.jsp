<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
var maxChecked = 3;   //선택가능한 체크박스 갯수
var totalChecked = 0; // 설정 끝

function CountChecked(field) {
    if (field.checked) // input check 박스가 체크되면 
        totalChecked += 1; // totalChecked 증가
    else // 체크가 아니면
        totalChecked -= 1; // totalChecked 감소

    if (totalChecked > maxChecked) { // totalchecked 수가 maxchecked수보다 크다면
        alert ("최대 3개 까지만 가능합니다."); // 팝업창 띄움
    field.checked = false;
    totalChecked -= 1;
    }
    
}
function fchk() {
    var chk_obj = document.getElementsByName("iCode");
    var chk_leng = chk_obj.length;
    var checked = 0;
    for (i=0; i < chk_leng; i++) {
        if (chk_obj[i].checked == true) {
            checked += 1;
        }
    }
    if (checked < 3 ) {
        alert("항목을 3개 선택해주세요");
        return false;
    }
}






</script>



</head>

<body>
 <h1>관심사 설정</h1>
 <div class = agree>
  <div class = all_agree>
 <form action="interestAction" method="get" onsubmit="return fchk();">
 <input type="checkbox" name="iCode"  onclick="CountChecked(this)" value ="1">게임
 <input type="checkbox" name="iCode" onclick="CountChecked(this)" value="2">스포츠
 <input type="checkbox" name="iCode" onclick="CountChecked(this)" value="3">정치
 <input type="checkbox" name="iCode" onclick="CountChecked(this)" value="4">경제
 <input type="checkbox" name="iCode" onclick="CountChecked(this)" value="5">주식<p>
 <input type="checkbox" name="iCode" onclick="CountChecked(this)" value="6">TV
 <input type="checkbox" name="iCode" onclick="CountChecked(this)" value="7">운동
 <input type="checkbox" name="iCode" onclick="CountChecked(this)" value="8">패션/뷰티
 <input type="checkbox" name="iCode" onclick="CountChecked(this)" value="9">자동차
 <input type="checkbox" name="iCode" onclick="CountChecked(this)" value="10">IT<p>
 <input type="checkbox" name="iCode" onclick="CountChecked(this)" value="11">건강
 <input type="checkbox" name="iCode" onclick="CountChecked(this)" value="12">책/문화
 <input type="checkbox" name="iCode" onclick="CountChecked(this)" value="13">노래
 <input type="checkbox" name="iCode" onclick="CountChecked(this)" value="14">동물
 <input type="checkbox" name="iCode" onclick="CountChecked(this)" value="15">식물<p>
 <input type="checkbox" name="iCode" onclick="CountChecked(this)" value="16">여행
 <input type="checkbox" name="iCode" onclick="CountChecked(this)" value="17">공연/전시
 <input type="checkbox" name="iCode" onclick="CountChecked(this)" value="18">과학/미스테리
 <input type="checkbox" name="iCode" onclick="CountChecked(this)" value="19">영화
 <input type="checkbox" name="iCode" onclick="CountChecked(this)" value="20">연애/결혼<p>
 
 
 <input type="submit" value="완료">
 </form>
 </div>
 </div>
</body>
</html>