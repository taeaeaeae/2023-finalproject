package org.zerock.myapp.persistence;

import org.zerock.myapp.domain.FindDTO;
import org.zerock.myapp.domain.FindVO;
import org.zerock.myapp.exception.DAOException;

public interface FindDAO {

	// 아이디찾기
	public abstract FindVO findId(FindDTO dto) throws DAOException;
	
	// 비밀번호찾기
	public abstract FindVO findPw(FindDTO dto) throws DAOException;

}	// end interface
