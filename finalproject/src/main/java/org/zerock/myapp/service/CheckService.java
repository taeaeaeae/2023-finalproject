package org.zerock.myapp.service;


import org.zerock.myapp.domain.CheckVO;
import org.zerock.myapp.exception.ServiceException;

public interface CheckService {
	
	public int checkId(CheckVO vo) throws ServiceException;
	
	public int checkEmail(CheckVO vo) throws ServiceException;

}	// end interface
	