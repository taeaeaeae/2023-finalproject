package org.zerock.myapp.persistence;


import org.zerock.myapp.domain.CheckVO;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.DAOException;


public interface UsersDAO {
	
	public int checkId(CheckVO vo) throws Exception;
	
	public int checkEmail(CheckVO vo) throws Exception;
	
	public abstract UsersVO update(UsersDTO dto) throws DAOException;
	
	public abstract UsersVO select(String uids) throws DAOException;

	public abstract UsersVO remove(String uids, String reason) throws DAOException;
	
}	// end class
