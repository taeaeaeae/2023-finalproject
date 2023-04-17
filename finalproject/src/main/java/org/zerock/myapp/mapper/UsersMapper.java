package org.zerock.myapp.mapper;

import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;

public interface UsersMapper {
	
	public abstract UsersVO select(String uids);	// 회원 상세 조회
	
	public abstract Integer join(UsersDTO dto);		// 회원 등록
	
	public abstract Integer remove(String uids);	// 회원 탈퇴
	
	public abstract Integer update(UsersDTO dto);	// 회원 수정
	
	public int idCheck(String uids);	// 아이디 중복확인
	

}	// end interface
