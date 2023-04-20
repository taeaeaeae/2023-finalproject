package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.FreeBoardCommentDTO;
import org.zerock.myapp.domain.FreeBoardCommentVO;
import org.zerock.myapp.domain.FreeBoardVO;
import org.zerock.myapp.exception.ServiceException;

public interface FreeBoardCommentService {

	public abstract List<FreeBoardCommentVO> getList(Integer fid) throws ServiceException;	// 1. �ڸ�Ʈ ���
	public abstract FreeBoardCommentVO get(Integer fbcid) throws ServiceException;
	public abstract boolean register(FreeBoardCommentDTO dto) throws ServiceException;		// 2. �ڸ�Ʈ ���
	public abstract boolean remove(Integer fbcid) throws ServiceException;					// 3. �ڸ�Ʈ ����
	public abstract boolean modify(FreeBoardCommentDTO dto) throws ServiceException;		// 4. �ڸ�Ʈ ����
	public abstract Integer getTotal() throws ServiceException;								// 5. �Խñ� �� ����		
	
} // end interface