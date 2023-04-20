package org.zerock.myapp.persistence;


import org.zerock.myapp.domain.CheckVO;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.DAOException;


public interface UsersDAO {
	
	// 아이디 중복체크
	public int checkId(CheckVO vo) throws Exception;
	
	// 닉네임 중복체크
	public int checkNickName(CheckVO vo) throws Exception;
	
	// 회원정보 수정
	public abstract UsersVO update(UsersDTO dto) throws DAOException;
	
	// 회원정보 조회
	public abstract UsersVO select(String uids) throws DAOException;
	
	// 회원탈퇴
	public abstract UsersVO remove(String uids) throws DAOException;
	

}
