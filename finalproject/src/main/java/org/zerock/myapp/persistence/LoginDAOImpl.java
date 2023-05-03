package org.zerock.myapp.persistence;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.LoginVO;
import org.zerock.myapp.exception.DAOException;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO {
	
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public LoginVO selectUser(LoginDTO dto) throws DAOException {
		log.trace("selectUser({}) invoked.", dto);
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try (sqlSession) {
			
			String namespace = "org.zerock.myapp.persistence.LoginDAO";
			String sqlId = "selectUser";
			String mappedStmt = namespace + "." + sqlId;
			
			return sqlSession.<LoginVO>selectOne(mappedStmt, dto);
		} catch(Exception e) {
			throw new DAOException(e);
		}	// try-catch
	
	}	//selectUser

}	// end class