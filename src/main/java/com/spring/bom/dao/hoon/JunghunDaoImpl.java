package com.spring.bom.dao.hoon;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.hoon.Junghun;
import com.spring.bom.model.iron.Board;
import com.spring.bom.model.iron.Follow;

@Repository
public class JunghunDaoImpl implements JunghunDao{
	@Autowired
	private SqlSession session;

	@Override
	public List<Junghun> listSearch(Junghun junghun) {
		List<Junghun> list = null;
		try {
			list = session.selectList("searchfame",junghun);
			
			// 인용데이터 처리
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getBbtype().equals("quote")) {
					Board quoteboard = session.selectOne("QuoteBoard", list.get(i).getBbcode());
					System.out.println("QuoteBoard--bdlist.get(i).getBbcode() --> " + list.get(i).getBbcode());
					System.out.println("쿼트문 실행");
					list.get(i).setQ_uimage(quoteboard.getUimage());
					System.out.println("quoteboard.getUimage() -> " + quoteboard.getUimage());
					list.get(i).setQ_nickname(quoteboard.getUnickName());
					list.get(i).setQ_atid(quoteboard.getUatid());
					list.get(i).setQ_content(quoteboard.getBcontent());
					list.get(i).setQ_regdate(quoteboard.getBregDate());

					if (quoteboard.getBattach() != null) {
						System.out.println("quoteboard.getBattach() -> " + quoteboard.getBattach());
						list.get(i).setQ_attachsrc(quoteboard.getBattach().substring(6));
						list.get(i).setQ_attachtype(quoteboard.getBattach().substring(0, 5));
						System.out.println(
								"quoteboard.getBattach().substring(6) -> " + quoteboard.getBattach().substring(6));
						System.out.println("quoteboard.getBattach().substring(0, 5) -> "
								+ quoteboard.getBattach().substring(0, 5));
					}
				}
			}
		}catch(Exception e) {
			System.out.println("listSearch -> " + e.getMessage());
		}
		return list;
	}

	@Override
	public List<Junghun> listUser(Junghun junghun) {
		return session.selectList("searchuser",junghun);
	}

	@Override
	public List<Junghun> listNew(Junghun junghun) {
		return session.selectList("searchnew",junghun);
	}

	@Override
	public List<Junghun> listCount(Junghun junghun) {
		return session.selectList("searchcount",junghun);
	}

	@Override
	public int searchData(Junghun junghun) {
		return session.update("searchData",junghun);
	}

	@Override
	public List<Junghun> listHash(Junghun junghun) {
		return session.selectList("searchHash",junghun);
	}

	@Override
	public List<Junghun> listTrend() {
		return session.selectList("searchTrend");
	}

	@Override
	public List<Junghun> searchkeyword(Junghun junghun) {
		return session.selectList("searchkeyword",junghun);
	}

	@Override
	public int deleterow(int ucode) {
		System.out.println("del dao::1");
		System.out.println("ucode ::"+ucode);
		return session.delete("searchdelete",ucode);
	}

	@Override
	public List<Junghun> searchblock(Junghun junghun) {
		// TODO Auto-generated method stub
		return session.selectList("searchblock",junghun);
	}

	@Override
	public int searchlike(Junghun junghun) {
		return session.update("searchlike",junghun);
	}

	@Override
	public List<Junghun> searchlistall(Junghun junghun) {
		// TODO Auto-generated method stub
		return session.selectList("searchlistall",junghun);
	}
	
	@Override
	public List<Follow> getSuggestFollowList1(int ucode) {
		List<Follow> suggestFlist = session.selectList("followWhoSameTrend",ucode);
		return suggestFlist;
	}
	
	@Override
	public List<Follow> getSuggestFollowList2(int ucode) {
		List<Follow> suggestFlist = session.selectList("followWhoIknow",ucode);
		return suggestFlist;
	}

	@Override
	public List<Junghun> searchbattach(Junghun junghun) {
		// TODO Auto-generated method stub
		return session.selectList("searchbattach",junghun);
	}

	@Override
	public List<Junghun> searchbattachvideo(Junghun junghun) {
		// TODO Auto-generated method stub
		return session.selectList("searchbattachvideo",junghun);
	}

	@Override
	public List<Junghun> searchbattach2(Junghun junghun) {
		// TODO Auto-generated method stub
		return session.selectList("searchbattach2",junghun);
	}

	@Override
	public List<Junghun> searchbattachvideo2(Junghun junghun) {
		// TODO Auto-generated method stub
		return session.selectList("searchbattachvideo2",junghun);
	}


}

