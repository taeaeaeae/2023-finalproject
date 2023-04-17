package org.zerock.myapp.service;

import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.FindPwDTO;
import org.zerock.myapp.domain.FindPwVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.persistence.FindPwDAO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@Service
public class FindPwServiceImpl implements FindPwService {
	
	private FindPwDAO findPwDAO;

	@Override
	public FindPwVO findPw(FindPwDTO dto) throws ServiceException {
		log.trace("findPw({}) invoked.", dto);
		
		try {
			return this.findPwDAO.findPw(dto);
		} catch(Exception e) {
			throw new ServiceException(e);
		}	// try-catch
	}	//findPw

	

}	// end class
