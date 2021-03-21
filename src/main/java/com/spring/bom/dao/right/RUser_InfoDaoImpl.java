package com.spring.bom.dao.right;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.right.RBlock;
import com.spring.bom.model.right.RInterest;
import com.spring.bom.model.right.RInterestDetail;
import com.spring.bom.model.right.RUser_Info;

@Repository
public class RUser_InfoDaoImpl implements RUser_InfoDao {
	// Mybatis 세션 
	@Autowired
	private SqlSession session;

	@Override
	public RUser_Info detail(int ucode) {
		System.out.println("User_InfoDaoImpl detail start..");
		
		RUser_Info ui = new RUser_Info();
		try {
			ui = session.selectOne("rightUserSelect", ucode);
			System.out.println("ui.getPwd() -> " + ui.getPwd());
			System.out.println("ui.getUpassword() " + ui.getUpassword());
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl detail Exception->"+e.getMessage());
		}
		return ui;
	}
	@Override
	public int changeinsert(RUser_Info ui) {
		System.out.println("User_InfoDaoImpl changeinsert start..");
		int result1 = 0;
		try {
			result1 = session.insert("rightUserEdit1", ui);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl changeinsert Exception->"+e.getMessage());
		}
		return result1;
	}

	@Override
	public int changeupdate(RUser_Info ui) {
		System.out.println("User_InfoDaoImpl changeupdate start..");
		int result2 = 0;
		try {
			result2 = session.update("rightUserEdit2", ui);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl changeupdate Exception->"+e.getMessage());
		}
		return result2;
	}
	@Override
	public int edit(RUser_Info ui) {
		System.out.println("User_InfoDaoImpl edit start..");
		int result3 = 0;
		try {
			result3 = session.update("rightUserEdit3", ui);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl edit Exception->"+e.getMessage());
		}
		return result3;
	}
	@Override
	public int userIdCheck(String uatid) {
		System.out.println("User_InfoDaoImpl userIdCheck start..");
		int cnt = 0;
		try {
			cnt = session.selectOne("checkId", uatid);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl userIdCheck Exception->"+e.getMessage());
		}
		
		return cnt;
	}
	@Override
	public int changeinsertstate(RUser_Info ui) {
		System.out.println("User_InfoDaoImpl changeinsertstate start..");
		int result = 0;
		try {
			result = session.insert("rightUserDisabl", ui);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl changeinsertstate Exception->"+e.getMessage());
		}
		return result;
	}
	@Override
	public int statedDis(RUser_Info ui) {
		System.out.println("User_InfoDaoImpl statedDis start..");
		int result1 = 0;
		try {
			result1 = session.update("rightUserDisabl1", ui);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl statedDis Exception->"+e.getMessage());
		}
		return result1;
	}
	@Override
	public int changeInfoState(RUser_Info ui) {
		System.out.println("User_InfoDaoImpl changeInfoState start..");
		int result2 = 0;
		try {
			result2 = session.update("rightUserDisabl3", ui);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl changeInfoState Exception->"+e.getMessage());
		}
		return result2;
	}
	@Override
	public int boardBpermission(RUser_Info ui) {
		System.out.println("User_InfoDaoImpl userIdCheck start..");
		int result3 = 0;
		try {
			result3 = session.update("rightUserDisabl3", ui);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl boardBpermission Exception->"+e.getMessage());
		}
		return result3;
	}
	@Override
	public RUser_Info select(String uemail) {
		System.out.println("User_InfoDaoImpl select start..");
		
		RUser_Info ui = new RUser_Info();
		try {
			ui = session.selectOne("rightUserSelectemail", uemail);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl select Exception->"+e.getMessage());
		}
		return ui;
	}
	@Override
	public int updouble(int ucode) {
		System.out.println("User_InfoDaoImpl updouble start..");
		int result = 0;
		try {
			result = session.update("rightupdouble", ucode);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl updouble Exception->"+e.getMessage());
		}
		return result;
	}
	@Override
	public int userpwCheck(String upassword, int ucode) {
		String u_code = String.valueOf(ucode);
		System.out.println("User_InfoDaoImpl userpwCheck start..");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("upassword", upassword);
		map.put("ucode", u_code);
		
		int cnt = 0;
		try {
			cnt = session.selectOne("checkPw", map);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl userpwCheck Exception->"+e.getMessage());
		}
		return cnt;
	}
	//비밀번호 변경
	
	@Override
	public int changePwinsert(int ucode, String pwd) {
		String u_code = String.valueOf(ucode);
		System.out.println("User_InfoDaoImpl userpwCheck start..");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pwd", pwd);
		map.put("ucode", u_code);
		System.out.println("User_InfoDaoImpl changePwinsert start..");
		System.out.println("User_InfoDaoImpl changePwinsert() map -> "+map);
		int result1 = 0;
		try {
			result1 = session.insert("rightPwchange1", map);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl changePwinsert Exception->"+e.getMessage());
		}
		return result1;
	}
	@Override
	public int changePwupdate(int ucode, String pwd) {
		String u_code = String.valueOf(ucode);
		System.out.println("User_InfoDaoImpl changePwupdate start..");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pwd", pwd);
		map.put("ucode", u_code);
		
		int result2 = 0;
		try {
			System.out.println("User_InfoDaoImpl changePwupdate()1 map -> " + map);
			result2 = session.update("rightPwchange2", map);
			System.out.println("User_InfoDaoImpl changePwupdate()2 map -> " + map);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl changePwupdate Exception->"+e.getMessage());
		}
		return result2;
	}
	@Override
	public int editPw(int ucode, String pwd) {
		String u_code = String.valueOf(ucode);
		System.out.println("User_InfoDaoImpl editPw start..");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pwd", pwd);
		map.put("ucode", u_code);
		int result3 = 0;
		try {
			System.out.println("User_InfoDaoImpl editPw() map -> "+map);
			result3 = session.update("rightPwchange3", map);
			System.out.println("User_InfoDaoImpl editPw() map -> "+map);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl editPw Exception->"+e.getMessage());
		}
		return result3;
	}
	//관심사변경
	@Override
	public List<RInterest> itdetail(int ucode) {
		System.out.println("User_InfoDaoImpl List<Interest> itdetail start..");
		List<RInterest> list_choice = new ArrayList<RInterest>();
		try {
			list_choice = session.selectList("rightInterestSelect", ucode);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl List<Interest> itdetail Exception->"+e.getMessage());
		}

		return list_choice;
	}
	@Override
	public List<RInterestDetail> select(RInterestDetail interestd) {
		System.out.println("User_InfoDaoImpl List<InterestDetail> select start..");
		List<RInterestDetail> list = new ArrayList<RInterestDetail>();
		try {
			list = session.selectList("rightInterestSelectAll", interestd);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl List<InterestDetail> select Exception->"+e.getMessage());
		}

		return list;
	}
	@Override
	public int deleteinterest(int ucode) {
		System.out.println("User_InfoDaoImpl deleteinterest start..");
		
		System.out.println("User_InfoDaoImpl deleteinterest start..");
		int result1 = 0;
		try {
			result1 = session.delete("rightInterestdelete", ucode);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl deleteinterest Exception->"+e.getMessage());
		}
		return result1;
	}
	@Override
	public int changeinsert1(RInterest interest) {
		System.out.println("User_InfoDaoImpl changeinsert1 start..");
		
		System.out.println("User_InfoDaoImpl changeinsert1 start..");
		int result2 = 0;
		try {
			result2 = session.insert("rightInterestchange1", interest);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl changeinsert1 Exception->"+e.getMessage());
		}
		return result2;
	}
	@Override
	public int changeinsert2(RInterest interest) {
		System.out.println("User_InfoDaoImpl changeinsert2 start..");
		
		System.out.println("User_InfoDaoImpl changeinsert2 start..");
		int result3 =0;
		try {
			result3 = session.insert("rightInterestchange2", interest);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl changeinsert2 Exception->"+e.getMessage());
		}
		return result3;
	}
	@Override
	public int changeinsert3(RInterest interest) {
		System.out.println("User_InfoDaoImpl changeinsert3 start..");
		int result4 = 0;
		try {
			result4 = session.insert("rightInterestchange3", interest);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl changeinsert3 Exception->"+e.getMessage());
		}
		return result4;
	}

	//차단 block 조회
	@Override
	public List<RBlock> blockListP(int ucode) {
		System.out.println("User_InfoDaoImpl List<Block> blockListP start..");
		List<RBlock> blockListP = new ArrayList<RBlock>();
		try {
			blockListP = session.selectList("rightBlockSelect", ucode);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl List<Block> blockListP Exception->"+e.getMessage());
		}

		return blockListP;
	}
	@Override
	public List<RBlock> blockList(int ucode) {
		System.out.println("User_InfoDaoImpl List<Block> blockList start..");
		List<RBlock> blockList = new ArrayList<RBlock>();
		try {
			blockList = session.selectList("rightBlockSelect2", ucode);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl List<Block> blockList Exception->"+e.getMessage());
		}

		return blockList;
	}
	//차단 해제
	@Override
	public int deleteblock(int blcode) {
		System.out.println("User_InfoDaoImpl deleteblock start..");
		int result = 0;
		try {
			result = session.delete("rightDeleteblock", blcode);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl deleteblock Exception->"+e.getMessage());
		}
		return result;
	}
	//차단 추가
	@Override
	public int addBHash(int ucode, String bhashtag) {
		String u_code = String.valueOf(ucode);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bhashtag", bhashtag);
		map.put("ucode", u_code);

		int result = 0;
		System.out.println("User_InfoDaoImpl addBHash start..");
		try {
			result = session.insert("rightaddBHash", map);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl addBHash Exception->"+e.getMessage());
		}
		return result;
	}
	@Override
	public int addBWord(int ucode, String bword) {
		String u_code = String.valueOf(ucode);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bword", bword);
		map.put("ucode", u_code);
		
		System.out.println("User_InfoDaoImpl addBWord start..");
		int result = 0;
		try {
			result = session.insert("rightaddBWord", map);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl addBWord Exception->"+e.getMessage());
		}
		return result;
	}
	//계정복구
	//change_info에 바뀐 ustate insert
	@Override
	public int updateUstate1(int ucode) {
		int result1 = 0;
		System.out.println("User_InfoDaoImpl updateUstate1 start..");
		try {
			result1 = session.insert("rightupdateUstate1", ucode);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl updateUstate1 Exception->"+e.getMessage());
		}
		return result1;
	}
	//user_info에 바뀐 ustate update
	@Override
	public int updateUstate2(int ucode) {
		int result2 = 0;
		System.out.println("User_InfoDaoImpl updateUstate2 start..");
		try {
			result2 = session.update("rightupdateUstate2", ucode);
		} catch (Exception e) {
			System.out.println("User_InfoDaoImpl updateUstate2 Exception->"+e.getMessage());
		}
		return result2;
	}
}
