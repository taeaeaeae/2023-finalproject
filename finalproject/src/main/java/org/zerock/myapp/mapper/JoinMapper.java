package org.zerock.myapp.mapper;

import org.zerock.myapp.domain.CheckVO;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;

public interface JoinMapper {
	

	public abstract UsersVO select(String uids);			
	

	public abstract Integer join(UsersDTO dto);				
	

	public abstract boolean update(UsersDTO dto);			
	

	public abstract boolean remove(UsersDTO dto);			
	

	public abstract CheckVO checkId(String uids);			
	

	

	
}	// end interface
