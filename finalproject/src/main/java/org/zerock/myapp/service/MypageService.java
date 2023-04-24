package org.zerock.myapp.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.ChecklistDTO;
import org.zerock.myapp.domain.MycommentVO;
import org.zerock.myapp.domain.MywriteVO;
import org.zerock.myapp.exception.ServiceException;

@Service
public interface MypageService {
	
	public ArrayList<MywriteVO> mywrite(String uids);
	
	public ArrayList<MycommentVO> mycomment(String uids);
	
	// üũ����Ʈ
	public abstract ArrayList<ChecklistDTO> checklist(String uids);
		
	// ȸ������
	public abstract boolean listadd(ChecklistDTO dto) throws ServiceException;
		
	// ȸ������
	public abstract boolean listupdate(ChecklistDTO dto) throws ServiceException;
		
	// ȸ��Ż��
	public abstract boolean listdelete(ChecklistDTO dto) throws ServiceException;
		

	
	

}	// end interface
