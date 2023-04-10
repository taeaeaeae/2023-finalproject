package org.zerock.myapp.service;

import org.zerock.myapp.domain.AnswerDTO;
import org.zerock.myapp.domain.AnswerVO;
import org.zerock.myapp.exception.ServiceException;

public interface AnswerService {
	
	public abstract AnswerVO get(Integer qid) throws ServiceException;

	public abstract boolean remove(Integer qid) throws ServiceException;
	
	public abstract boolean register(AnswerDTO dto) throws ServiceException;
	
	public abstract boolean modify(AnswerDTO dto) throws ServiceException;

} // end class
