package org.zerock.myapp.service;

import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.FindIdDTO;
import org.zerock.myapp.domain.FindIdVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.persistence.FindIdDAO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@Service("findIdService")
public class FindIdServiceImpl implements FindIdService {
	
	private FindIdDAO findIdDAO;

	@Override
	public FindIdVO findId(FindIdDTO dto) throws ServiceException {
		log.trace("findId({}) invoked.", dto);
		
		try {
			return this.findIdDAO.findId(dto);
		} catch(Exception e) {
			throw new ServiceException(e);
		}	// try-catch
	}	//findId

	

}	// end class
