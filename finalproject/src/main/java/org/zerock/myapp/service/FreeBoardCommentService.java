package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.FreeBoardCommentDTO;
import org.zerock.myapp.domain.FreeBoardCommentVO;
import org.zerock.myapp.domain.FreeBoardVO;
import org.zerock.myapp.exception.ServiceException;

public interface FreeBoardCommentService {

	public abstract List<FreeBoardCommentVO> getList(Integer fid) throws ServiceException;	// 1. 코멘트 목록
	public abstract FreeBoardCommentVO get(Integer fbcid) throws ServiceException;
	public abstract boolean register(FreeBoardCommentDTO dto) throws ServiceException;		// 2. 코멘트 등록
	public abstract boolean remove(Integer fbcid) throws ServiceException;					// 3. 코멘트 삭제
	public abstract boolean modify(FreeBoardCommentDTO dto) throws ServiceException;		// 4. 코멘트 수정
	public abstract Integer getTotal() throws ServiceException;								// 5. 게시글 총 개수		
	
} // end interface