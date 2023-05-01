package org.zerock.myapp.persistence;

import org.zerock.myapp.domain.BookmarkDTO;
import org.zerock.myapp.exception.DAOException;

public interface BookmarkDAO {
	
	public abstract boolean isBookmarked(BookmarkDTO dto) throws DAOException;

} // end class