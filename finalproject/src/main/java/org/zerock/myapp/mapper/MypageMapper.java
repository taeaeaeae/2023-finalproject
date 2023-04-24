package org.zerock.myapp.mapper;

import java.util.ArrayList;

import org.zerock.myapp.domain.ChecklistDTO;
import org.zerock.myapp.domain.ChecklistVO;
import org.zerock.myapp.domain.MycommentVO;
import org.zerock.myapp.domain.MywriteVO;

public interface MypageMapper {
	
	public abstract ArrayList<MywriteVO> mywrite(String uids);
	
	public abstract ArrayList<MycommentVO> mycomment(String uids);
		
	// 체크리스트
	public abstract ArrayList<ChecklistVO> checklist(String uids);
		
	public abstract Integer listadd(ChecklistDTO dto);				
	
	public abstract boolean listupdate(ChecklistDTO dto);			
	
	public abstract boolean listdelete(ChecklistDTO dto);			
	

}	// end interfaces
