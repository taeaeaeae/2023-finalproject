package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FreeBoardDTO;
import org.zerock.myapp.domain.FreeBoardVO;
import org.zerock.myapp.exception.ServiceException;

public interface FreeBoardService {

	public abstract List<FreeBoardVO> getList(Criteria cri) throws ServiceException;	
	public abstract FreeBoardVO get(Integer fid) throws ServiceException;				
	public abstract	boolean remove(Integer fid) throws ServiceException;				
	public abstract boolean register(FreeBoardDTO dto) throws ServiceException;			
	public abstract boolean modify(FreeBoardDTO dto) throws ServiceException;			
	public abstract Integer getTotalAmount(Criteria cri) throws ServiceException;					
	public abstract Integer getPrevPost(Integer fid) throws ServiceException;		
	public abstract Integer getNextPost(Integer fid) throws ServiceException;
	public abstract List<FreeBoardVO> getListPageSearch(Criteria cri) throws ServiceException;
	public abstract Integer viewCountUp(Integer fid) throws ServiceException;
	
} // end interface