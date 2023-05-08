package org.zerock.myapp.service;


import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ServiceException;

public interface UsersService {
	
	public abstract UsersVO select(String uids) throws ServiceException;
	
	public abstract UsersVO selectEmail(String email) throws ServiceException;

	public abstract boolean join(UsersDTO dto) throws ServiceException;
	
	public abstract boolean update(UsersDTO dto) throws ServiceException;
	
	public abstract boolean remove(UsersDTO dto) throws ServiceException;


}	// end interface
	