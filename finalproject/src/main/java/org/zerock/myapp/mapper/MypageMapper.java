package org.zerock.myapp.mapper;

import java.util.ArrayList;

import org.zerock.myapp.domain.BookmarkVO;
import org.zerock.myapp.domain.CheckVO;
import org.zerock.myapp.domain.ChecklistDTO;
import org.zerock.myapp.domain.ChecklistVO;
import org.zerock.myapp.domain.LikesVO;
import org.zerock.myapp.domain.MycommentVO;
import org.zerock.myapp.domain.MywriteVO;

public interface MypageMapper {
	
	// ���� �ۼ���
	public abstract ArrayList<MywriteVO> mywrite(String uids);
	
	// ���� ���
	public abstract ArrayList<MycommentVO> mycomment(String uids);
		
	// üũ����Ʈ ��ȸ
	public abstract ArrayList<ChecklistVO> checklist(String uids);
	
	// üũ����Ʈ �߰�
	public abstract Integer listadd(ChecklistDTO dto);				
	
	// üũ����Ʈ ����
	public abstract boolean listupdate(ChecklistDTO dto);			
	
	// üũ����Ʈ ����
	public abstract boolean listdelete(ChecklistDTO dto);
	
	// ���ƿ� ���
	public abstract ArrayList<LikesVO> likes(String uids);

	// �ϸ�ũ ���
	public abstract ArrayList<BookmarkVO> bookmark(String uids);
	
	// ȸ�� Ż�� ��й�ȣ Ȯ��
	public abstract CheckVO passChk(String password);	

}	// end interfaces
