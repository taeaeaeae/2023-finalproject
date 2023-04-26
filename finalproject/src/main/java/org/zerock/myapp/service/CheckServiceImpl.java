package org.zerock.myapp.service;


import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.CheckVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.persistence.UsersDAO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor

@Log4j2
@Service
public class CheckServiceImpl implements CheckService{
	
	private UsersDAO UsersDAO;
	
	@Override
	public int checkId(CheckVO vo) throws ServiceException {
		log.trace("checkId({}) invoked.", vo);
		
			try {
				int result = UsersDAO.checkId(vo);
				return result;
			} catch(Exception e) {
				throw new ServiceException(e);
			}	// try-catch
		}	// checkId
	
	@Override
	public int checkNickName(CheckVO vo) throws ServiceException {
		log.trace("checkNickName({}) invoked.", vo);
		
			try {
				int result = UsersDAO.checkNickName(vo);
				return result;
			} catch(Exception e) {
				throw new ServiceException(e);
			}	// try-catch
		}

	@Override
	public int passChk(CheckVO vo) throws ServiceException {
		log.trace("passChk({}) invoked.", vo);
		
		try {
			int result = UsersDAO.checkNickName(vo);
			return result;
		} catch(Exception e) {
			throw new ServiceException(e);
		}	// try-catch
	}


	
}	// end class
