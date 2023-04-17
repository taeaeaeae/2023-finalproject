package org.zerock.myapp.persistence;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.FindPwDTO;
import org.zerock.myapp.domain.FindPwVO;
import org.zerock.myapp.exception.DAOException;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@Repository("findPwDAO")
public class FindPwDAOImpl implements FindPwDAO {
	
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public FindPwVO findPw(FindPwDTO dto) throws DAOException {
		log.trace("findPw({}) invoked.", dto);
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try (sqlSession) {
			
			String namespace = "org.zerock.myapp.persistence.FindPwDAO";
			String sqlId = "findPw";
			String mappedStmt = namespace + "." + sqlId;
			
			return sqlSession.<FindPwVO>selectOne(mappedStmt, dto);
		} catch(Exception e) {
			throw new DAOException(e);
		}	// try-catch
		
		
	}	//findPw
	

}	// end class
