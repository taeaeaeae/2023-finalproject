package org.zerock.myapp.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.BookmarkDTO;
import org.zerock.myapp.domain.BookmarkVO;
import org.zerock.myapp.exception.DAOException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@Repository("BookmarkDAO")
public class BookmarkDAOImpl implements BookmarkDAO {
	
	@Inject
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public boolean isBookmarked(BookmarkDTO dto) throws DAOException{
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		log.info("\t+ sqlSession : {}", sqlSession);
				
		try (sqlSession){
			int count = sqlSession.selectOne("isBookmarked", dto);
			log.trace("\t+ count : {}", count);
			
			return count > 0;
		} catch (Exception e) {
			throw new DAOException(e);
		} // try-catch
	} // isBookmarked

} // end class