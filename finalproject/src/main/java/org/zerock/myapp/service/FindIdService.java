package org.zerock.myapp.service;

import org.zerock.myapp.domain.FindIdDTO;
import org.zerock.myapp.domain.FindIdVO;
import org.zerock.myapp.exception.ServiceException;

public interface FindIdService {

	// ���̵� ã��
	public abstract FindIdVO findId(FindIdDTO dto) throws ServiceException;

	
}	// end interface
