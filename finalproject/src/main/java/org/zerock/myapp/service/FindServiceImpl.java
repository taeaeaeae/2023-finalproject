package org.zerock.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.FindDTO;
import org.zerock.myapp.domain.FindVO;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.AnswerMapper;
import org.zerock.myapp.mapper.JoinMapper;
import org.zerock.myapp.persistence.FindDAO;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@Service
public class FindServiceImpl implements FindService {
	
	private FindDAO findDAO;
	
	@Setter(onMethod_= { @Autowired })
	private JoinMapper joinMapper;

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
	}


	@Override
	public UsersVO newPassword(FindDTO dto) throws ServiceException {
		try {
			return this.findDAO.newPassword(dto);
		} catch(Exception e) {
			throw new ServiceException(e);
		}	// try-catch
	}	//findPw



}	// end class
