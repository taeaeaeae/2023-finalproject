package org.zerock.myapp.service;

import org.zerock.myapp.domain.ReportsDTO;
import org.zerock.myapp.exception.ServiceException;

public interface ReportsService {

	public abstract boolean InsertPostReports(ReportsDTO dto) throws ServiceException;
	
} // end class