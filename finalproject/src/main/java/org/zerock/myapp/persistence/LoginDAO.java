package org.zerock.myapp.persistence;

import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.LoginVO;
import org.zerock.myapp.exception.DAOException;

public interface LoginDAO {

	// 로그인 창에서 아이디/ 암호 여부에 따라 매칭되는 회원정보를 조회/반환
	public abstract LoginVO selectUser(LoginDTO dto) throws DAOException;
	
}	// end interface
