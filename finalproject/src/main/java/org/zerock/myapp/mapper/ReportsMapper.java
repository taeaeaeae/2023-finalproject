package org.zerock.myapp.mapper;

import java.util.ArrayList;

import org.zerock.myapp.domain.ReportsDTO;
import org.zerock.myapp.domain.ReportsVO;

public interface ReportsMapper {

	public abstract Integer PostReportsInsert(ReportsDTO dto);
	
	public abstract ArrayList<ReportsVO> reportList(String uids); 
	
	public abstract boolean dropout(ReportsDTO dto);
	
	public abstract boolean status(ReportsDTO dto);
	
	public abstract boolean dropin(ReportsDTO dto);
	
	public abstract boolean dropinStatus(ReportsDTO dto);
	
} // end interface