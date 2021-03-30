# 📑프로젝트 소개 
## (BOM 브랜치에 코드 업데이트 완료(2021-03-27)) [![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fkongemom1215%2FBOM&count_bg=%2379C83D&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)



_BOM은 **소셜 네트워크 서비스를 제공하는 사이트**입니다.
실시간 커뮤니케이션을 할 수 있으며, 일종의 채팅 프로그램을 갖고 있습니다.
(트위터를 참조하여 개발한 사이트입니다.)_

> 개발 기간 : 2021.02.18~2021.03.22

> 개발 인원 : 8명 

## 🏷목차
[1. 역할](#역할)

[2. 개발환경/사용언어](#개발환경사용언어)

[3. 프로젝트 제공 기능](#프로젝트-제공-기능)

[4. 시스템 프로세스](#시스템-프로세스)

[5. ERD](#ERD)

[6. 화면 구현](#화면)

## 👩‍💻역할
+ 🍀**임주혜(GOD) - 템플릿, 글쓰기 기능**
+ 서정철(iron) - 타임라인, 프로필 기능
+ 배기웅(man) - 실시간 채팅, 알림
+ 이정윤(right) - 사용자 설정, 통계
+ 임경빈(bean) - 관리자 기능
+ 홍진형(bro) - 로그인/회원가입
+ 박정훈(hoon) - 검색기능
+ 김예린(yeah) - 북마크 기능

## 개발환경/사용언어
- 개발 환경 : STS(eclipse), SQL Developer 
- OS 및 DB : Windows10, Oracle 
- 사용 언어 : JAVA, CSS, HTML5, Javascript, JQuery, Ajax, MyBatis, Spring, Bootstrap 4


## 프로젝트 제공 기능
[1. 템플릿 및 글쓰기 by 임주혜](#화면)
  + [템플릿](#템플릿)
    - bootstrap 4.5를 이용한 사이트 디자인 구축
  + [글쓰기](#글쓰기-페이지)
    - 해시태그(#) 기능
    - 사용자에게 멘션(@) 기능
    - 미디어(사진, 동영상) 첨부 기능
    - 투표 기능
    - 글 등록 예약시간 설정 기능
    - 글 저장 기능
    - 답글, 인용 기능
  + [글출력](#글출력-페이지)
    - 좋아요 
  + [프로필 UI 수정](#프로필)
    - 프로필 디자인 
2. 로그인, 회원가입
  - 회원가입 시  이메일 인증 구현
  - 로그인 차단 계정 활성화 
3. 타임라인
  - 게시글 조회
4. 쪽지
  - 방생성 , 최근메세지 , 실시간 대화 구현
  - 팔로우 ,  팔로잉 구현
  - 온라인 유저 조회
5. 북마크
  - 모든 글 북마크 지정 가능 
  - 최신 북마크 순으로 정렬
6. 프로필
7. 검색
  - 해시태그 , 검색어 입력시 인기 , 사용자 , 최신글 , 사진 , 동영상출력
  - 검색기록 구현하여 기록관리
8. 관리자
  - 모든 회원 및 글 차단 및 삭제 가능
  - 회원 및 글 검색 기능
  - 기타 , 글 삭제 로그아웃 구현 
9. 사용자 설정
  - 계정 정보 수정(2단계인증, 비밀번호 변경, 관심사 관리, 차단 관리, 탈퇴 및 복구)

## 시스템 프로세스
![image](https://user-images.githubusercontent.com/72897088/112763642-467f8100-9040-11eb-92e2-36ec922c6ea1.png)

## ERD
![image](https://user-images.githubusercontent.com/72897088/112763654-5008e900-9040-11eb-952b-90bce3ce9421.png)

## 화면
### 템플릿
![image](https://user-images.githubusercontent.com/72897088/109418489-f8a93600-7a0b-11eb-937f-4dbef746b1cf.png)
```
 - Bootstrap 4.5를 이용한 반응형 웹 사이트 디자인 구축
 - Bootstrap 에서 정의된 다양한 클래스(컴포넌트)들을 HTML에 적용하고
 부가적인 속성이나 레이아웃과 관련된 속성들을 정의한 클래스를 조합
```
![ezgif com-gif-maker (12)](https://user-images.githubusercontent.com/72897088/109418455-d2839600-7a0b-11eb-90a8-8df8133780cf.gif)

### 회원가입
![image](https://user-images.githubusercontent.com/72897088/112764286-c9a1d680-9042-11eb-882e-4a87ff77607f.png)
```
회원가입
 ˙ 회원가입시 입력한 이메일로 이메일 인증번호 전송
 ˙ 인증번호 부적합시 다음으로 넘어가는 버튼 생성
```

### 로그인
![로긴](https://user-images.githubusercontent.com/72897088/111977449-1585e480-8b46-11eb-8173-1664c447e1ff.gif)
```
로그인시 해당 회원의 타임라인으로 이동
```

### 글쓰기 페이지
**1. 일반 글 등록**

![일반 글 등록](https://user-images.githubusercontent.com/72897088/111977968-bb395380-8b46-11eb-93f0-5b53dc7753eb.gif)
```
일반 글 등록
 ˙ bootstrap의 modal을 이용하여 글쓰기창 띄움

 ˙ 입력창은 div의 contenteditable 속성을 주어 편집 기능을 줌

 ˙ jQuery의 keyup() 이벤트를 활용하여 글 내용 입력 시 등록 버튼 활성화
 
 ˙ window.onload 메소드를 활용하여 임시저장글 수를 준비시켜놓음
 
 ˙ 내용입력 -> 등록 -> 타임라인(최신글순) 출력

```

**2. 해시태그 구현**

![해시태그 (2)](https://user-images.githubusercontent.com/72897088/111984150-597ce780-8b4e-11eb-92a1-cbf6d0643e56.gif)
```
 ˙ (# + 단어) 입력시 해시태그 취급하여 <a>태그 처리가 된 상태로 저장
 ˙ indexOf을 이용하여 #태그 확인(단어별로)
 ˙ 해시태그 등록 시 해당 해시태그 수를 count하여
      DB저장
```

**3. 멘션 구현**

![멘션 (2)](https://user-images.githubusercontent.com/72897088/111980906-4bc56300-8b4a-11eb-9ee1-87f6d92242a6.gif)
```
 ˙ [방법1] (@+id) 입력시 멘션 취급하여 <a>태그 처리가 된 상태로 저장 (indexOf을 이용하여 @확인)
 ˙ [방법2] “받는 사람“ 버튼 클릭시 자신의 팔로워 목록[AJAX]에서 선택하거나 검색[Rest Api]을 통해 ID나 닉네임이 검색값에 해당하는     
    회원 목록에서 선택할 수 있음. 
   선택 시 입력창에 @ID 추가됨(멘션 클릭 시 해당하는 회원 프로필 페이지로 이동)
```

**4. 답글 구현**

![답글](https://user-images.githubusercontent.com/72897088/111979902-f9377700-8b48-11eb-944e-d772994884f8.gif)
```
 ˙ 말풍선 클릭 시(onclick을 통해 jQuery로 전송 상대 출력) 해당 글 회원에게 답글 작성
 ˙ 글 등록 시 @ID 와 함께 등록
 ˙ 원글 클릭 시 답글 조회 가능
```

**5. 인용 구현**

![이뇽이뇽](https://user-images.githubusercontent.com/72897088/112128389-bc7f8480-8c09-11eb-87c3-51c5ea98ac08.gif)
```
 ˙ 인용 버튼 클릭 시(onclick) 해당 글 정보를 
     입력폼에 띄움(jQuery)
 ˙ 인용 글 등록시 인용 수가 올라감
```

**6. 미디어 등록***

![이미지 등록](https://user-images.githubusercontent.com/72897088/111981491-1bca8f80-8b4b-11eb-9506-6d2f0b17e099.gif)
```
[이미지 등록]
미디어 버튼 클릭시 이미지or동영상 선택 가능
해당 미디어와 글 등록 가능
˙ jQuery change 메소드를 통하여 
     1) size 체크 : 미디어 등록 시 이미지는 10MB, 동영상은 100MB가 넘으면 경고 창 표시
     2) 이미지 읽기 : 용량초과가 아니면 FileReader 객체를 통하여 파일을 읽고 onload 이벤트 핸들러로
    <img> 태그의 src에 삽입
```

![동영상 등록](https://user-images.githubusercontent.com/72897088/112763969-9ca0f400-9041-11eb-8ad6-7d3e614616ab.gif)
```
[동영상 등록]
     3) 동영상 읽기 : <input type=“file”>을 getElementById().files[0]로 받아오고,  URL.createObjectURL를
     통해 문서 내에서만 유효한 객체 URL을 생성하고, 해당 url을 <video>태그의 src에 삽입
```

![미디어 삭제](https://user-images.githubusercontent.com/72897088/111981502-1ec58000-8b4b-11eb-814c-d47b3243be42.gif)
```
미디어삭제 -> 삽입한 미디어를 삭제(jQuery)
```

**7. 투표 등록**

![투표](https://user-images.githubusercontent.com/72897088/111981929-b034f200-8b4b-11eb-91f9-58ccefe566b4.gif)
```
> 최대 4가지 선택지 선택 가능(2개 필수)
> 마감 시간 설정 가능
> 중복투표 설정 가능
> [JQuery]투표 삭제 버튼 클릭시 삭제
(투표 출력은 개발중)
```

**8. 예약 기능**

![예약](https://user-images.githubusercontent.com/72897088/111982417-49640880-8b4c-11eb-8c82-6c06c2801d7f.gif)
```
 ˙ 예약버튼 클릭 시 현재 시간을 <select><option>에 적용(javascript의 Date 객체이용)
 ˙ 예약 시간을 선택 후 글을 등록하면 해당 시간에 글이 올라감
 ˙ 달력에 맞지 않는 날짜 선택 시 [ex)2월 30일] 경고창 띄움(jQuery)
 ˙ window.onload를 통해 [AJAX]에서 "임시저장한 글" 클릭 시 예약 목록 출력
```

**9. 임시저장 기능**

![임시저장](https://user-images.githubusercontent.com/72897088/111982945-eb83f080-8b4c-11eb-95be-5468de95eadc.gif)
```
 ˙ 글 내용이 있는 상태에서 종료 시에 임시저장을 묻는 창 띄움
 ˙ 저장을 하면 저장목록에서 다시 불러와서 글 등록 가능
 ˙ window.onload를 통해 [AJAX]에서 "임시저장한 글" 클릭 시 임시저장 목록 출력
```

**10. 예약, 저장 글 삭제**

![저장예약삭제](https://user-images.githubusercontent.com/72897088/111983495-88df2480-8b4d-11eb-90bf-2c0a6795cb81.gif)
```
 ˙ [AJAX] 목록에서 수정 버튼 클릭시 checkbox가 뜨고(jQuery) 선택 시 삭제 가능
```

### 글출력 페이지

** 좋아요 기능**

![좋아요](https://user-images.githubusercontent.com/72897088/112120016-38c19a00-8c01-11eb-871f-1982a11d73b8.gif)
```
 ˙ [AJAX] ♡ 클릭 시 onclick을 통해 ajax 호출
     좋아요 count 증가, 좋아요 jQuery 반영 
```

### 타임라인
![image](https://user-images.githubusercontent.com/72897088/112764103-1e911d00-9042-11eb-8c8c-9725785bfa48.png)
```
로그인 유저의 글 & 팔로잉 유저의 글을 최신순으로 표시
˙ 복잡한 Outter JOIN 연산으로 인한 중복튜플 방지를 위해 TABLE1  “UNION”  TABLE2 사용
각각 글 작성시간에 대해 대략적인 단위 표시
˙ ex) n초 전, n분 전, n시간 전, n일 전, n달 전, n 년전
  SQL > case when절, trunc(날짜연산) 처리
˙ 페이지에 노출된 @id 클릭 시, 해당 @id 유저의 프로필로 이동
˙ 팔로우 추천을 통해 유저를 팔로우 하는 사람을 알 수 있음.
˙ 사이트 내 해시태그 순위를 확인
```

### 프로필
![image](https://user-images.githubusercontent.com/72897088/112764201-6e6fe400-9042-11eb-942f-fe839e2dbdab.png)
```
왼쪽 사이드바 프로필 탭 클릭
 ˙ 현재 로그인 중인 유저의 프로필로 이동
사용자 프로필
 ˙ 닉네임, @아이디, 이미지, 위치 정보 수정 구현
프로필 유저
 ˙ [AJAX]팔로워&팔로잉 탭 지원
```


### 검색
![image](https://user-images.githubusercontent.com/72897088/112764326-ef2ee000-9042-11eb-8380-3937012d7fc0.png)
```
검색창
 ˙ 검색 이용 시 검색기록이 남음 (DB에 저장)
 ˙ 전체 지우기 이용해 검색기록 삭제 할 수 있음
 ˙ 검색 기록 남으면 클릭 시 해당 검색어로 바로 검색가능
  그 후 검색한 단어는 최신순으로 정렬
```

#### 검색 결과 창
![image](https://user-images.githubusercontent.com/72897088/112764365-1ab1ca80-9043-11eb-8267-60f9c47f268c.png)
```
검색어 입력
˙ 원하는 검색어 입력 시 인기 , 사용자 , 최신 사진 , 동영상 출력 가능
```

### 쪽지
![image](https://user-images.githubusercontent.com/72897088/112764379-374e0280-9043-11eb-861e-5d1f2d7910dc.png)
```
온라인 유저 조회
 ˙ ‘봄’ 사이트 전체 온라인 유저 조회(로그인 시 유저를 온라인 처리함)
팔로우 추천
 ˙ 상대방이 나를 팔로우 하였을시 팔로우 추천 랜덤 조회
 ˙ 더보기 클릭시 상세내용 
 ˙ 더보기창 과 팔로우 추천에서 팔로워,언팔 기능
```

![image](https://user-images.githubusercontent.com/72897088/112764401-52207700-9043-11eb-9e9c-f0a9d084023f.png)
```
실시간 대화
 ˙ HTML5 WebSocket을 활용하여 실시간 대화구현 
 ˙ 메세지 내용이 길어지면 ……… 처리
```

### 북마크
![image](https://user-images.githubusercontent.com/72897088/112764441-78dead80-9043-11eb-92b5-7c4b4e9a3a8a.png)
![image](https://user-images.githubusercontent.com/72897088/112764448-8431d900-9043-11eb-9c4e-6649f8eec7ea.png)
```
북마크 글 정렬
 ˙ 게시글 북마크 설정 시 북마크 페이지로 이동
 ˙ 최신 북마크 순으로 정렬
```

