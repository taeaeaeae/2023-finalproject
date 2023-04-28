package org.zerock.myapp.service;

import org.zerock.myapp.domain.FindDTO;
import org.zerock.myapp.domain.FindVO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ServiceException;

public interface FindService {

	public abstract FindVO findId(FindDTO dto) throws ServiceException;
	
	public abstract FindVO findPw(FindDTO dto) throws ServiceException;

	public abstract UsersVO newPassword(FindDTO dto) throws ServiceException;

}	// end interface
