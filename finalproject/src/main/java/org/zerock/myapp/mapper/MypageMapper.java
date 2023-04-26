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
	
	// 나의 작성글
	public abstract ArrayList<MywriteVO> mywrite(String uids);
	
	// 나의 댓글
	public abstract ArrayList<MycommentVO> mycomment(String uids);
		
	// 체크리스트 조회
	public abstract ArrayList<ChecklistVO> checklist(String uids);
	
	// 체크리스트 추가
	public abstract Integer listadd(ChecklistDTO dto);				
	
	// 체크리스트 수정
	public abstract boolean listupdate(ChecklistDTO dto);			
	
	// 체크리스트 삭제
	public abstract boolean listdelete(ChecklistDTO dto);
	
	// 좋아요 목록
	public abstract ArrayList<LikesVO> likes(String uids);

	// 북마크 목록
	public abstract ArrayList<BookmarkVO> bookmark(String uids);
	
	// 회원 탈퇴 비밀번호 확인
	public abstract CheckVO passChk(String password);	

}	// end interfaces
