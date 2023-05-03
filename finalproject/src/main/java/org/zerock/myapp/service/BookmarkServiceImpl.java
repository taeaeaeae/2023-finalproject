package org.zerock.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.BookmarkDTO;
import org.zerock.myapp.domain.BookmarkVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.BookmarkMapper;
import org.zerock.myapp.persistence.BookmarkDAO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString
@NoArgsConstructor
@Log4j2

@Service
public class BookmarkServiceImpl implements BookmarkService {

	@Setter(onMethod_ = {@Autowired})
	private BookmarkMapper mapper;
	
	@Setter(onMethod_ = {@Autowired})
	private BookmarkDAO dao;
	
	@Override
	public boolean isBookmarked(BookmarkDTO dto) throws ServiceException {
		log.trace("isBookmarked({}) invoked.", dto);
		
		try {
			if (this.mapper.isBookmarked(dto) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // isBookmarked 
	
	@Override
	public boolean register(BookmarkDTO dto) throws ServiceException {
		log.trace("register({}) invoked.", dto);
		
		try {
			return (this.mapper.addBookmark(dto) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // register

	@Override
	public boolean remove(BookmarkDTO dto) throws ServiceException {
		log.trace("remove({}) invoked.", dto);
		
		try {
			return (this.mapper.removeBookmark(dto) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // remove

} // end class