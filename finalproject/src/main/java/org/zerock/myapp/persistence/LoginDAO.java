package org.zerock.myapp.persistence;

import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.LoginVO;
import org.zerock.myapp.exception.DAOException;

public interface LoginDAO {

	public abstract LoginVO selectUser(LoginDTO dto) throws DAOException;

}	// end interface
