package org.zerock.myapp.service;

import org.zerock.myapp.domain.FindPwDTO;
import org.zerock.myapp.domain.FindPwVO;
import org.zerock.myapp.exception.ServiceException;

public interface FindPwService {

	// ��й�ȣ ã��
	public abstract FindPwVO findPw(FindPwDTO dto) throws ServiceException;
	
}	// end interface
