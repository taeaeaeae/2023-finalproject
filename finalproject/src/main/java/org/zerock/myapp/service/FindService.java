package org.zerock.myapp.service;

import org.zerock.myapp.domain.FindDTO;
import org.zerock.myapp.domain.FindVO;
import org.zerock.myapp.exception.ServiceException;

public interface FindService {

	// ���̵� ã��
	public abstract FindVO findId(FindDTO dto) throws ServiceException;
	
	// ��й�ȣ ã��
	public abstract FindVO findPw(FindDTO dto) throws ServiceException;

	
}	// end interface
