package org.zerock.myapp.service;


import org.zerock.myapp.domain.CheckVO;
import org.zerock.myapp.exception.ServiceException;

public interface CheckService {
	
	// ���̵� �ߺ�Ȯ��
	public int checkId(CheckVO vo) throws ServiceException;
	
	// �г��� �ߺ�Ȯ��
	public int checkNickName(CheckVO vo) throws ServiceException;
	
	public int passChk(CheckVO vo) throws ServiceException;

}	// end interface
	