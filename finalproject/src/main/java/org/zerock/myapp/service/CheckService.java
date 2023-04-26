package org.zerock.myapp.service;


import org.zerock.myapp.domain.CheckVO;
import org.zerock.myapp.exception.ServiceException;

public interface CheckService {
	
	// 아이디 중복확인
	public int checkId(CheckVO vo) throws ServiceException;
	
	// 닉네임 중복확인
	public int checkNickName(CheckVO vo) throws ServiceException;
	
	public int passChk(CheckVO vo) throws ServiceException;

}	// end interface
	