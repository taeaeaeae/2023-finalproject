package org.zerock.myapp.persistence;

import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.LoginVO;
import org.zerock.myapp.exception.DAOException;

public interface LoginDAO {

	// �α��� â���� ���̵�/ ��ȣ ���ο� ���� ��Ī�Ǵ� ȸ�������� ��ȸ/��ȯ
	public abstract LoginVO selectUser(LoginDTO dto) throws DAOException;
	
}	// end interface
