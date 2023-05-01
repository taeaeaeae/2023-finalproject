package org.zerock.myapp.persistence;


import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("BoardDAO")
public class BoardDAOImpl implements BoardDAO{
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	private static String namespace = "org.zerock.myapp.mappers.board";
	
	//怨꾪쉷 珥� 媛��닔
	@Override
	public int boardPlanCnt(String searchType, String keyword) throws Exception {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		Map<String, String> data = new HashMap<>();
		
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		
		return sqlSession.selectOne(namespace+".boardPlanCnt", data);
	}

	@Override
	public int boardUserPlanCnt(String uids, String searchType, String keyword) throws Exception {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		Map<String, String> data = new HashMap<>();
		
		data.put("uids", uids);
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		return sqlSession.selectOne(namespace+".boardPlanCnt", data);
	}
	
	//�쑀��蹂� 怨꾪쉷 珥� 媛��닔
	
}
