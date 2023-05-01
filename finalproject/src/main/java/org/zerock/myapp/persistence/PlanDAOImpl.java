package org.zerock.myapp.persistence;

import java.util.HashMap;
import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.zerock.myapp.controller.PlanController;
import org.zerock.myapp.domain.PlanVO;
import org.zerock.myapp.domain.ScheduleVO;

import lombok.extern.log4j.Log4j2;

@Log4j2

@Repository
public class PlanDAOImpl implements PlanDAO {
	
	
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	private static String namespace = "org.zerock.myapp.mappers.plan";
	
	//怨꾪쉷 �꽕�젙 異붽�
	@Override
	public void planAdd(PlanVO vo) throws Exception {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		sqlSession.insert(namespace+".planAdd", vo);
	}
	
	//PlanNo 媛��졇�삤湲�
	@Override
	public int planNoCheck(PlanVO vo) throws Exception {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		int result = sqlSession.selectOne(namespace+".planNoGet", vo);
		return result;
	}

	//怨꾪쉷 �씪�젙 媛쒕퀎 異붽�
	@Override
	public void planSchAdd(ScheduleVO vo) throws Exception {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		sqlSession.insert(namespace+".planSchAdd", vo);
	}
	
	//寃뚯떆�뙋�슜 怨꾪쉷 紐⑸줉 諛� �럹�씠吏�
	@Override
	public List<PlanVO> planList(HashMap<String, Object> data) throws Exception {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		return sqlSession.selectList(namespace + ".planList", data);
	}
	
	//怨꾪쉷�씪�젙 紐⑸줉
	@Override
	public List<ScheduleVO> planSchList(int pid) throws Exception {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		return sqlSession.selectList(namespace + ".planSchList", pid);
	}
	
	//�쑀��蹂� 怨꾪쉷 紐⑸줉
	@Override
	public List<PlanVO> planListForUser(HashMap<String, Object> data) throws Exception{
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		return sqlSession.selectList(namespace + ".planListForUser", data);
	}
	
	//怨꾪쉷 議고쉶
	@Override
	public PlanVO planView(PlanVO vo) throws Exception {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		return sqlSession.selectOne(namespace + ".planView", vo);
	}
	
	//怨꾪쉷 �닔�젙
	@Override
	public void viewPlanModify(PlanVO vo) {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		sqlSession.update(namespace + ".viewPlanModify", vo);
	}
	
	//怨꾪쉷 �궘�젣
	@Override
	public void viewPlanDelete(PlanVO vo) throws Exception {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		log.info("view Plan Delete"+vo.toString());
		sqlSession.delete(namespace + ".viewPlanDelete", vo);
	}
	
	
	//view �궘�젣 異붽� �닔�젙 �셿猷뚮쾭�듉
	@Override
	public void viewSchDelete(HashMap<String,Object> map) throws Exception {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		sqlSession.delete(namespace+".viewSchDelete", map);
	}
	
	
	
	
}
