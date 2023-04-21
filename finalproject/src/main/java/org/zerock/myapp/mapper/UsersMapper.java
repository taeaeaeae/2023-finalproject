package org.zerock.myapp.mapper;

import org.zerock.myapp.domain.CheckVO;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;

public interface UsersMapper {
	
	public abstract UsersVO select(String uids);			// ȸ�� �� ��ȸ
	
	public abstract Integer join(UsersDTO dto);				// ȸ�� ���
	
	public abstract boolean update(UsersDTO dto);			// ȸ�� ����
	
	public abstract boolean remove(UsersDTO dto);			// ȸ�� Ż��
	
	public abstract CheckVO checkId(String uids);			// ���̵� �ߺ�Ȯ��
	
	public abstract CheckVO checkNickName(String nickname); // �г��� �ߺ�Ȯ��
	
}	// end interface
