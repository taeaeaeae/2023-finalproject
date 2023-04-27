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
	

	public ArrayList<MywriteVO> mywrite(String uids);

	
	public ArrayList<MycommentVO> mycomment(String uids);
	
	
	public abstract ArrayList<ChecklistVO> checklist(String uids);
		

	public abstract boolean listadd(ChecklistDTO dto) throws ServiceException;
		

	public abstract boolean listupdate(ChecklistDTO dto) throws ServiceException;
		

	public abstract boolean listdelete(ChecklistDTO dto) throws ServiceException;
	

	public abstract ArrayList<LikesVO> likes(String uids);
	

	public abstract ArrayList<BookmarkVO> bookmark(String uids);

	

}	// end interface
