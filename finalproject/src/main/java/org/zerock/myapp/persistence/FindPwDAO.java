package org.zerock.myapp.persistence;

import org.zerock.myapp.domain.FindPwDTO;
import org.zerock.myapp.domain.FindPwVO;
import org.zerock.myapp.exception.DAOException;

public interface FindPwDAO {

	public abstract FindPwVO findPw(FindPwDTO dto) throws DAOException;

	
}	// end interface
