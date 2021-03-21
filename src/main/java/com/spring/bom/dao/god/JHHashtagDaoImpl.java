package com.spring.bom.dao.god;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bom.model.god.JHBoard;
import com.spring.bom.model.god.JHHashtag;

@Repository
public class JHHashtagDaoImpl implements JHHashtagDao {
	@Autowired
	private SqlSession session;

	public int insertHash(ArrayList<String> hashlist, JHHashtag hashtag) {
		int result=0;
		try {
			System.out.println("hashlist -> "+hashlist);
			for(String hash : hashlist) {
				System.out.println("hash -> "+hash);
				hashtag.setHname(hash);
				result=session.insert("JHinsertHash", hashtag);
			}
			
		} catch (Exception e) {
			System.out.println("[GOD] BoardDaoImpl insertHash ->"+e.getMessage());
		}
		
		return result;
	}
}
