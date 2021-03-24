package com.spring.bom.dao.god;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.god.JHBoard;
import com.spring.bom.model.god.JHLike;
import com.spring.bom.model.god.JHUser_info;

@Repository
public class JHUserDaoImpl implements JHUserDao {
	@Autowired
	private SqlSession session;

	@Override
	public String getReserveNum(int ucode) {
		String reserveNum=null;
		try {
			reserveNum=session.selectOne("JHgetReserveNum",ucode);
		} catch (Exception e) {
			System.out.println("GOD UserDaoImpl getReserveNum-> "+e.getMessage());
		}
		return reserveNum;
	}

	@Override
	public String getSaveNum(int ucode) {
		String saveNum=null;
		try {
			saveNum=session.selectOne("JHgetSaveNum",ucode);
		} catch (Exception e) {
			System.out.println("GOD UserDaoImpl getSaveNum -> "+e.getMessage());
		}
		return saveNum;
	}
	
	@Override
	public List<JHBoard> getReserveList(int ucode) {
		List<JHBoard> list = null;
		try {
			list=session.selectList("JHgetReserveList",ucode);
		} catch (Exception e) {
			System.out.println("GOD UserDaoImpl getReserveList ->"+e.getMessage());
		}
		return list;
	}

	@Override
	public List<JHBoard> getSaveList(int ucode) {
		List<JHBoard> list = null;
		try {
			list=session.selectList("JHgetSaveList",ucode);
		} catch (Exception e) {
			System.out.println("GOD UserDaoImpl getSaveList -> "+e.getMessage());
		}
		return list;
	}

	@Override
	public List<JHUser_info> getFollowerList(int ucode) {
		List<JHUser_info> followList=null;
		try {
			followList=session.selectList("JHgetfollowlist", ucode);
		} catch (Exception e) {
			System.out.println("GOD UserDaoImpl getFollowerList -> "+e.getMessage());
		}
		return followList;
	}

	@Override
	public List<JHUser_info> getSearchList(String search_value) {
		List<JHUser_info> userList=null;
		try {
			userList=session.selectList("JHgetSearchUserlist", search_value);
		} catch (Exception e) {
			System.out.println("GOD UserDaoImpl getSearchList -> "+e.getMessage());
		}
		return userList;
	}

	@Override
	public JHLike getUserLike(int bcode, int ucode) {
		JHLike jhlike=new JHLike();
		try {
			jhlike.setUcode(ucode);
			jhlike.setBcode(bcode);
			int exist=session.selectOne("JHgetUserLike", jhlike);
			
			//행이 있는지
			if(exist==1) {
				jhlike=session.selectOne("JHgetLike", jhlike);
				//있다
				//좋아요를 안했는지
				if(jhlike.getLtype().equals("0")) {
					//안했다면
					session.update("GODUpLikeCount", bcode); //좋아요 수 올리고
					session.update("JHdoLike", jhlike); //like_bookmark도 update
				}
				else {
					//좋아요를 했다면
					session.update("JHDownLikeCount", bcode); //좋아요 수 내리고
					session.update("JHnoLike", jhlike); //like_bookmark에서 업데이트하고
					if(jhlike.getLtype().equals("0") && jhlike.getBbtype().equals("0"))
						session.delete("JHDeleteLike", jhlike);
				}
			}
			//행이 없다면
			else {
				session.update("GODUpLikeCount", bcode); //좋아요 수 올리고
				session.insert("JHInsertLike", jhlike);//좋아요 추가
			}
			//좋아요 수 조회
			jhlike.setLikeCount(session.selectOne("JHgetLikeCount",bcode));
		} catch (Exception e) {
			System.out.println("GOD UserDaoImpl getUserLike -> "+e.getMessage());
		}
		return jhlike;
	}

}
