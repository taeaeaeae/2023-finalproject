package org.zerock.myapp.persistence;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

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
	
	
	
	@Inject
	private SqlSessionFactory sqlSessionFactory;
	private static String namespace = "org.zerock.myapp.mappers.plan";
	
	//계획 설정 추가
	@Override
	public void planAdd(PlanVO vo) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		sqlSession.insert(namespace+".planAdd", vo);
	}
	
	//pid 가져오기
	@Override
	public int pidCheck(PlanVO vo) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int result = sqlSession.selectOne(namespace+".pidGet", vo);
		return result;
	}

	//계획 일정 개별 추가
	@Override
	public void planSchAdd(ScheduleVO vo) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		sqlSession.insert(namespace+".planSchAdd", vo);
	}
	
	//게시판용 계획 목록 및 페이징
	@Override
	public List<PlanVO> planList(HashMap<String, Object> data) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		return sqlSession.selectList(namespace + ".planList", data);
	}
	
	//계획일정 목록
	@Override
	public List<ScheduleVO> planSchList(int pid) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		return sqlSession.selectList(namespace + ".planSchList", pid);
	}
	
	//유저별 계획 목록
	@Override
	public List<PlanVO> planListForUser(HashMap<String, Object> data) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		return sqlSession.selectList(namespace + ".planListForUser", data);
	}
	
	//계획 조회
	@Override
	public PlanVO planView(PlanVO vo) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		return sqlSession.selectOne(namespace + ".planView", vo);
	}
	
	//계획 수정
	@Override
	public void viewPlanModify(PlanVO vo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		sqlSession.update(namespace + ".viewPlanModify", vo);
	}
	
	//계획 삭제
	@Override
	public void viewPlanDelete(PlanVO vo) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		log.info("view Plan Delete"+vo.toString());
		sqlSession.delete(namespace + ".viewPlanDelete", vo);
	}
	
	
	//view 삭제 추가 수정 완료버튼
	@Override
	public void viewSchDelete(HashMap<String,Object> map) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		sqlSession.delete(namespace+".viewSchDelete", map);
	}
	
	
	
	
}
