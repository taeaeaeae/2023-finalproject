package org.zerock.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.ReportsDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.ReportsMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2

@NoArgsConstructor
@Service
public class ReportsServiceImpl implements ReportsService {

	@Setter(onMethod_ = {@Autowired})
	private ReportsMapper mapper;
	
	@Override
	public boolean InsertPostReports(ReportsDTO dto) throws ServiceException {
		log.trace("InsertPostReports({}) invoked.", dto);
		
		try {
			return this.mapper.PostReportsInsert(dto) == 1;
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // InsertPostReports

} // end class