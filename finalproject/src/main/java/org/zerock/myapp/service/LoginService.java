package org.zerock.myapp.service;

import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.LoginVO;
import org.zerock.myapp.exception.ServiceException;

public interface LoginService {
	
	public abstract LoginVO login(LoginDTO dto) throws ServiceException;

}	// end interface
