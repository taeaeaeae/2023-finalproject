package org.zerock.myapp.persistence;

import org.zerock.myapp.domain.FindDTO;
import org.zerock.myapp.domain.FindVO;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.DAOException;

public interface FindDAO {


	public abstract FindVO findId(FindDTO dto) throws DAOException;
	
	public abstract FindVO findPw(FindDTO dto) throws DAOException;

	public abstract UsersVO newPassword(FindDTO dto) throws DAOException;


}	// end interface
