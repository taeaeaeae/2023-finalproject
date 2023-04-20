package org.zerock.myapp.service;

import org.zerock.myapp.domain.FindDTO;
import org.zerock.myapp.domain.FindVO;
import org.zerock.myapp.exception.ServiceException;

public interface FindService {

	// 아이디 찾기
	public abstract FindVO findId(FindDTO dto) throws ServiceException;
	
	// 비밀번호 찾기
	public abstract FindVO findPw(FindDTO dto) throws ServiceException;

	
}	// end interface
