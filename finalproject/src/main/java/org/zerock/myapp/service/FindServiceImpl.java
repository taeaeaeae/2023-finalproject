package org.zerock.myapp.service;

import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.FindDTO;
import org.zerock.myapp.domain.FindVO;

import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.persistence.FindDAO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@Service
public class FindServiceImpl implements FindService {
	
	private FindDAO findDAO;

	@Override
	public FindVO findId(FindDTO dto) throws ServiceException {
		log.trace("findId({}) invoked.", dto);
		
		try {
			return this.findDAO.findId(dto);
		} catch(Exception e) {
			throw new ServiceException(e);
		}	// try-catch
	}	//findId
	

	@Override
	public FindVO findPw(FindDTO dto) throws ServiceException {
		log.trace("findPw({}) invoked.", dto);
		
		try {
			return this.findDAO.findPw(dto);
		} catch(Exception e) {
			throw new ServiceException(e);
		}	// try-catch
	}	//findPw


	

}	// end class
