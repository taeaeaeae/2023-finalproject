package org.zerock.myapp.persistence;


import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Inject
	private SqlSessionFactory sqlSessionFactory;
	private static String namespace = "org.zerock.myapp.mappers.board";
	
	//계획 총 갯수
	@Override
	public int boardPlanCnt(String searchType, String keyword) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		Map<String, String> data = new HashMap<>();
		
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		
		return sqlSession.selectOne(namespace+".boardPlanCnt", data);
	}

	@Override
	public int boardUserPlanCnt(String uids, String searchType, String keyword) throws Exception {
		Map<String, String> data = new HashMap<>();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		data.put("uids", uids);
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		return sqlSession.selectOne(namespace+".boardPlanCnt", data);
	}
	
	//유저별 계획 총 갯수
	
}
