package org.zerock.myapp.persistence;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.CheckVO;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.DAOException;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@AllArgsConstructor
@Log4j2

@Repository("UsersDAO")
public class UsersDAOImpl implements UsersDAO {
	
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public int checkId(CheckVO vo) throws Exception {
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		int result = sqlSession.selectOne("checkId", vo);
		return result;
	}	// checkId
	
	@Override
	public int checkNickName(CheckVO vo) throws Exception {
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		int result = sqlSession.selectOne("checkNickName", vo);
		return result;
	}	// checkNickName

	@Override
	public UsersVO update(UsersDTO dto) throws DAOException {
		log.trace("update({}) invoked.", dto);
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try (sqlSession) {
			
			String namespace = "org.zerock.myapp.persistence.UsersDAO";
			String sqlId = "update";
			String mappedStmt = namespace + "." + sqlId;
			
			return sqlSession.<UsersVO>selectOne(mappedStmt, dto);
		} catch(Exception e) {
			throw new DAOException(e);
		}	// try-catch
		
		
	}

	@Override
	public UsersVO select(String uids) throws DAOException{
		log.trace("select({}) invoked.", uids);
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try (sqlSession) {
			
			String namespace = "org.zerock.myapp.persistence.UsersDAO";
			String sqlId = "select";
			String mappedStmt = namespace + "." + sqlId;
			
			return sqlSession.<UsersVO>selectOne(mappedStmt, uids);
		} catch(Exception e) {
			throw new DAOException(e);
		}	// try-catch
	}	//select

	
	
}	// end class

	

