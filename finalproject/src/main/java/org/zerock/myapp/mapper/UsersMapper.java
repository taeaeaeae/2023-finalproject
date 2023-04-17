package org.zerock.myapp.mapper;

import org.zerock.myapp.domain.CheckIdVO;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;

public interface UsersMapper {
	
	public abstract UsersVO select(String uids);	// ȸ�� �� ��ȸ
	
	public abstract Integer join(UsersDTO dto);		// ȸ�� ���
	
	public abstract Integer remove(String uids);	// ȸ�� Ż��
	
	public abstract Integer update(UsersDTO dto);	// ȸ�� ����
	
	public abstract CheckIdVO checkId(String uids);
}	// end interface
