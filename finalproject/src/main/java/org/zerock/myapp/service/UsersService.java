package org.zerock.myapp.service;


import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ServiceException;

public interface UsersService {
	
	// 회원 상세조회 
	public abstract UsersVO select(String uids) throws ServiceException;

	// 회원가입
	public abstract boolean join(UsersDTO dto) throws ServiceException;
	
	// 회원수정
	public abstract boolean update(UsersDTO dto) throws ServiceException;
	
	// 회원탈퇴
	public abstract boolean remove(String uids) throws ServiceException;
	


	






	

}	// end interface
	