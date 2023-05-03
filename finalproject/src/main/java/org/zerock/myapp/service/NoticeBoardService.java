package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.NoticeDTO;
import org.zerock.myapp.domain.NoticeVO;
import org.zerock.myapp.exception.ServiceException;

public interface NoticeBoardService {

	public abstract List<NoticeVO> getList(Criteria cri) throws ServiceException;	
	public abstract NoticeVO get(Integer nid) throws ServiceException;				
	public abstract	boolean remove(Integer nid) throws ServiceException;				
	public abstract boolean register(NoticeDTO dto) throws ServiceException;			
	public abstract boolean modify(NoticeDTO dto) throws ServiceException;			
	public abstract Integer getTotalAmount(Criteria cri) throws ServiceException;					
	public abstract Integer getPrevPost(Integer nid) throws ServiceException;		
	public abstract Integer getNextPost(Integer nid) throws ServiceException;
	public abstract List<NoticeVO> getListPageSearch(Criteria cri) throws ServiceException;
	public abstract Integer viewCountUp(Integer nid) throws ServiceException;
	
} // end interface