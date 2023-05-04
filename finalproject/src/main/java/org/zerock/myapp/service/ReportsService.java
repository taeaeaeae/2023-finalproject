package org.zerock.myapp.service;

import java.util.ArrayList;

import org.zerock.myapp.domain.ReportsDTO;
import org.zerock.myapp.domain.ReportsVO;
import org.zerock.myapp.exception.ServiceException;

public interface ReportsService {

	public abstract boolean InsertPostReports(ReportsDTO dto) throws ServiceException;
	
	public abstract ArrayList<ReportsVO> reportList(String uids) throws ServiceException;
	
	public abstract boolean dropout(ReportsDTO dto) throws ServiceException;
	
	public abstract boolean status(ReportsDTO dto) throws ServiceException;
	
} // end class