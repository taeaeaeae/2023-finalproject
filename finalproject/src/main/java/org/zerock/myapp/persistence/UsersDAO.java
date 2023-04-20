package org.zerock.myapp.persistence;


import org.zerock.myapp.domain.CheckVO;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.DAOException;


public interface UsersDAO {
	
	// ���̵� �ߺ�üũ
	public int checkId(CheckVO vo) throws Exception;
	
	// �г��� �ߺ�üũ
	public int checkNickName(CheckVO vo) throws Exception;
	
	// ȸ������ ����
	public abstract UsersVO update(UsersDTO dto) throws DAOException;
	
	// ȸ������ ��ȸ
	public abstract UsersVO select(String uids) throws DAOException;
	
	// ȸ��Ż��
	public abstract UsersVO remove(String uids) throws DAOException;
	

}
