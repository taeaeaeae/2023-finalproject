package org.zerock.myapp.service;


import org.zerock.myapp.domain.CheckIdVO;
import org.zerock.myapp.exception.ServiceException;

public interface CheckService {
	
	// 아이디 중복확인
	public int checkId(CheckIdVO vo) throws ServiceException;

}	// end interface
	