package org.zerock.myapp.mapper;

import org.zerock.myapp.domain.CheckVO;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;

public interface JoinMapper {
	
	// ȸ�� �� ��ȸ
	public abstract UsersVO select(String uids);			
	
	// ȸ�� ���
	public abstract Integer join(UsersDTO dto);				
	
	// ȸ�� ����
	public abstract boolean update(UsersDTO dto);			
	
	// ȸ�� Ż��
	public abstract boolean remove(UsersDTO dto);			
	
	// ���̵� �ߺ�Ȯ��
	public abstract CheckVO checkId(String uids);			
	
	// �г��� �ߺ�Ȯ��
	public abstract CheckVO checkNickName(String nickname); 
	
}	// end interface
