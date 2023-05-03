package org.zerock.myapp.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.zerock.myapp.domain.BookmarkVO;
import org.zerock.myapp.domain.ChecklistDTO;
import org.zerock.myapp.domain.ChecklistVO;
import org.zerock.myapp.domain.LikesVO;
import org.zerock.myapp.domain.MycommentVO;
import org.zerock.myapp.domain.MywriteVO;

public interface MypageMapper {
	

	public abstract ArrayList<MywriteVO> mywrite(String uids);
	

	public abstract ArrayList<MycommentVO> mycomment(String uids);
		

	public abstract ArrayList<ChecklistVO> checklist(String uids);
	

	public abstract Integer listadd(ChecklistDTO dto);				
	

	public abstract boolean listupdate(ChecklistDTO dto);			
	

	public abstract boolean listdelete(ChecklistDTO dto);
	

	public abstract ArrayList<LikesVO> likes(String uids);


	public abstract ArrayList<BookmarkVO> bookmark(String uids);
	

	public abstract List<Map<String,Object>> morelist(int startNum);

}	// end interfaces
