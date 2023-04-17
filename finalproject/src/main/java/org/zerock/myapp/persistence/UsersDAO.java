package org.zerock.myapp.persistence;


import org.zerock.myapp.domain.CheckIdVO;


public interface UsersDAO {
	
	// 아이디 중복체크
	public int checkId(CheckIdVO vo) throws Exception;

}
