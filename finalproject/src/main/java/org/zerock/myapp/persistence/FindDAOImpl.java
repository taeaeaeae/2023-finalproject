package org.zerock.myapp.persistence;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.FindDTO;
import org.zerock.myapp.domain.FindVO;
import org.zerock.myapp.exception.DAOException;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@Repository("findDAO")
public class FindDAOImpl implements FindDAO {
	
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public FindVO findId(FindDTO dto) throws DAOException {
		log.trace("findId({}) invoked.", dto);
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try (sqlSession) {
			
			String namespace = "org.zerock.myapp.persistence.FindDAO";
			String sqlId = "findId";
			String mappedStmt = namespace + "." + sqlId;
			
			return sqlSession.<FindVO>selectOne(mappedStmt, dto);
		} catch(Exception e) {
			throw new DAOException(e);
		}	// try-catch
		
		
	}	//findId
	
	@Override
	public FindVO findPw(FindDTO dto) throws DAOException {
		log.trace("findPw({}) invoked.", dto);
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try (sqlSession) {
			
			String namespace = "org.zerock.myapp.persistence.FindDAO";
			String sqlId = "findPw";
			String mappedStmt = namespace + "." + sqlId;
			
			return sqlSession.<FindVO>selectOne(mappedStmt, dto);
		} catch(Exception e) {
			throw new DAOException(e);
		}	// try-catch

	}	//findPw
	

}	// end class
