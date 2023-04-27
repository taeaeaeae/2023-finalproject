package org.zerock.myapp.mapper;

import org.zerock.myapp.domain.CheckVO;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;

public interface JoinMapper {
	
	// 회원 상세 조회
	public abstract UsersVO select(String uids);			
	
	// 회원 등록
	public abstract Integer join(UsersDTO dto);				
	
	// 회원 수정
	public abstract boolean update(UsersDTO dto);			
	
	// 회원 탈퇴
	public abstract boolean remove(UsersDTO dto);			
	
	// 아이디 중복확인
	public abstract CheckVO checkId(String uids);			
	
	

	
}	// end interface
