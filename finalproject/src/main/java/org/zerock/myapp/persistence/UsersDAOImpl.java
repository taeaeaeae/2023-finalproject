package org.zerock.myapp.persistence;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.CheckIdVO;

import lombok.AllArgsConstructor;


@AllArgsConstructor

@Repository("UsersDAO")
public class UsersDAOImpl implements UsersDAO {
	
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public int checkId(CheckIdVO vo) throws Exception {
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		int result = sqlSession.selectOne("checkId", vo);
		return result;
	}

	

}	// end class
