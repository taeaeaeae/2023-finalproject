package org.zerock.myapp.service;


import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ServiceException;

public interface UsersService {
	
	// ȸ�� ����ȸ 
	public abstract UsersVO select(String uids) throws ServiceException;

	// ȸ������
	public abstract boolean join(UsersDTO dto) throws ServiceException;
	
	// ȸ������
	public abstract boolean update(UsersDTO dto) throws ServiceException;
	
	// ȸ��Ż��
	public abstract boolean remove(String uids) throws ServiceException;
	


	






	

}	// end interface
	