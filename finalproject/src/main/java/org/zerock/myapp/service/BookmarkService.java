package org.zerock.myapp.service;

import org.zerock.myapp.domain.BookmarkDTO;
import org.zerock.myapp.domain.BookmarkVO;
import org.zerock.myapp.exception.ServiceException;

public interface BookmarkService {

	public abstract boolean isBookmarked(BookmarkDTO dto) throws ServiceException;
	public abstract boolean register(BookmarkDTO dto) throws ServiceException;
	public abstract boolean remove(BookmarkDTO dto) throws ServiceException;
	
} // end interface