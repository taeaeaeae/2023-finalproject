package org.zerock.myapp.mapper;

import org.zerock.myapp.domain.BookmarkDTO;

public interface BookmarkMapper {

	public abstract Integer isBookmarked(BookmarkDTO dto);
	public abstract Integer addBookmark(BookmarkDTO dto); 
	public abstract Integer removeBookmark(BookmarkDTO dto);
	
} // end interface