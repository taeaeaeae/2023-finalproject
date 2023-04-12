package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.QnaDTO;
import org.zerock.myapp.domain.QnaVO;
import org.zerock.myapp.exception.ServiceException;

public interface QnaService {
	
	public abstract List<QnaVO> getList(Criteria cri) throws ServiceException;
	
	public abstract QnaVO get(Integer qid) throws ServiceException;
	
	public abstract boolean remove(Integer qid) throws ServiceException;
	
	public abstract boolean register(QnaDTO dto) throws ServiceException;
	
	public abstract boolean modify(QnaDTO dto) throws ServiceException;

	public abstract Integer getTotalAmount() throws ServiceException;
	
} // end class
