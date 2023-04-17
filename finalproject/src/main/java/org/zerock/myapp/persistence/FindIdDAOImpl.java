package org.zerock.myapp.persistence;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.FindIdDTO;
import org.zerock.myapp.domain.FindIdVO;
import org.zerock.myapp.exception.DAOException;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@Repository("findIdDAO")
public class FindIdDAOImpl implements FindIdDAO {
	
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public FindIdVO findId(FindIdDTO dto) throws DAOException {
		log.trace("findId({}) invoked.", dto);
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try (sqlSession) {
			
			String namespace = "org.zerock.myapp.persistence.FindIdDAO";
			String sqlId = "findId";
			String mappedStmt = namespace + "." + sqlId;
			
			return sqlSession.<FindIdVO>selectOne(mappedStmt, dto);
		} catch(Exception e) {
			throw new DAOException(e);
		}	// try-catch
		
		
	}	//findId
	

}	// end class
