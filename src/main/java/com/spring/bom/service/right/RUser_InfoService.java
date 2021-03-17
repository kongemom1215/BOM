package com.spring.bom.service.right;

import java.util.List;

import com.spring.bom.model.right.RBlock;
import com.spring.bom.model.right.RInterest;
import com.spring.bom.model.right.RInterestDetail;
import com.spring.bom.model.right.RUser_Info;

public interface RUser_InfoService {
	//login 후 ucode 검색
	RUser_Info 		select(String uemail);
	//회원 정보 수정
	RUser_Info		detail(int ucode);
	int				edit(RUser_Info ui);
	int				changeinsert(RUser_Info ui);
	int				changeupdate(RUser_Info ui); 
	int 			userIdCheck(String uatid); 
	int	 			statedDis(RUser_Info ui);
	int 			changeInfoState(RUser_Info ui);
	int 			boardBpermission(RUser_Info ui); 
	int 			changeinsertstate(RUser_Info ui); 
	//2단계인증 상태 업데
	int 			updouble(int ucode);
	int 			userpwCheck(String upassword, int ucode);
	//비밀번호 변경
	int 			changePwinsert(int ucode, String pwd); 	
	int		 		changePwupdate(int ucode, String pwd);
	int 			editPw(int ucode, String pwd); 
	//관심사 변경
	//체크된 관심사 조회
	List<RInterest> 	itdetail(int ucode);
	//관심사 전체 조회
	List<RInterestDetail>  select(RInterestDetail interestd); 
	int 			deleteinterest(int ucode);
	int 			changeinsert1(RInterest interest);
	int 			changeinsert2(RInterest interest);
	int 			changeinsert3(RInterest interest);
	//차단 block 조회
	List<RBlock> 	blockList(int ucode); 
	List<RBlock> 	blockListP(int ucode); 
	//차단 해제
	int 			deleteblock(int blcode); 
	//차단 추가
	int				addBHash(int ucode, String bhashtag);
	int 			addBWord(int ucode, String bword);
	//계정 복구
	int 			updateUstate1(int ucode); 
	int 			updateUstate2(int ucode); 
}
