package org.zerock.myapp.service;

import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.LoginVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.persistence.LoginDAO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@Service
public class LoginServiceImpl implements LoginService {
	
	private LoginDAO userDAO;
	
	@Override
	public LoginVO login(LoginDTO dto) throws ServiceException {
		log.trace("login({}) invoked.", dto);
		
		try {
			return this.userDAO.selectUser(dto);
		} catch(Exception e) {
			throw new ServiceException(e);
		}	// try-catch
	}	//login
	
}	// end class
