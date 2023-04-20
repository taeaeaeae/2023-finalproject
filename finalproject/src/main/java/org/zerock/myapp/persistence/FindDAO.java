package org.zerock.myapp.persistence;

import org.zerock.myapp.domain.FindDTO;
import org.zerock.myapp.domain.FindVO;
import org.zerock.myapp.exception.DAOException;

public interface FindDAO {

	// ���̵�ã��
	public abstract FindVO findId(FindDTO dto) throws DAOException;
	
	// ��й�ȣã��
	public abstract FindVO findPw(FindDTO dto) throws DAOException;

}	// end interface
