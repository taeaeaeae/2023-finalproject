package org.zerock.myapp.persistence;


import org.zerock.myapp.domain.CheckIdVO;


public interface UsersDAO {
	
	// ���̵� �ߺ�üũ
	public int checkId(CheckIdVO vo) throws Exception;

}
