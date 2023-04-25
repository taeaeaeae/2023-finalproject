package org.zerock.myapp.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.BookmarkVO;
import org.zerock.myapp.domain.ChecklistDTO;
import org.zerock.myapp.domain.ChecklistVO;
import org.zerock.myapp.domain.LikesVO;
import org.zerock.myapp.domain.MycommentVO;
import org.zerock.myapp.domain.MywriteVO;
import org.zerock.myapp.exception.ServiceException;

@Service
public interface MypageService {
	
	// 나의 작성글
	public ArrayList<MywriteVO> mywrite(String uids);
	
	// 나의 댓글
	public ArrayList<MycommentVO> mycomment(String uids);
	
	// 체크리스트
	public abstract ArrayList<ChecklistVO> checklist(String uids);
		
	// 체크리스트 추가
	public abstract boolean listadd(ChecklistDTO dto) throws ServiceException;
		
	// 체크리스트 수정
	public abstract boolean listupdate(ChecklistDTO dto) throws ServiceException;
		
	// 체크리스트 삭제
	public abstract boolean listdelete(ChecklistDTO dto) throws ServiceException;
	
	// 좋아요 목록
	public abstract ArrayList<LikesVO> likes(String uids);
	
	// 북마크 목록
	public abstract ArrayList<BookmarkVO> bookmark(String uids);
	


}	// end interface
