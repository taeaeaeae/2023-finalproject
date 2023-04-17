package org.zerock.myapp.persistence;

import org.zerock.myapp.domain.FindIdDTO;
import org.zerock.myapp.domain.FindIdVO;
import org.zerock.myapp.exception.DAOException;

public interface FindIdDAO {

	public abstract FindIdVO findId(FindIdDTO dto) throws DAOException;

}	// end interface
