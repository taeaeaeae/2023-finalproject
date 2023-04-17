package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FreeBoardDTO;
import org.zerock.myapp.domain.FreeBoardVO;

public interface BoardMapper {
	
//	@Select("SELECT /*+ index_desc(free_board) */ * FROM free_board")
//	public abstract List<FreeBoardVO> selectAll();				// 1-1. 
	
	@Select("""
			SELECT *
			FROM free_board	
			ORDER BY fid DESC
			OFFSET (#{currPage} - 1) * #{amount} ROWS	
			FETCH NEXT #{amount} ROWS ONLY
			""")
	public abstract List<FreeBoardVO> selectAll(Criteria cri);	// 1-2. 
	public abstract FreeBoardVO select(Integer fid);			// 2.
	public abstract Integer delete(Integer fid);				// 3.
	public abstract Integer insert(FreeBoardDTO dto);			// 4. 
	public abstract Integer update(FreeBoardDTO dto);			// 5. 
	
//	@Select("SELECT count(fid) FROM free_board WHERE fid > 0")
	public abstract Integer selectTotalCount(Criteria cri);					// 6.  
	
	@Update("UPDATE free_board SET view_count = view_count + 1 WHERE fid = #{fid}")	// 7. 
	public abstract Integer viewCountUp(Integer fid);
	
	public abstract Integer prev(Integer fid);
	public abstract Integer next(Integer fid);
	
	// 검색 + 목록 + 페이징
	public abstract List<FreeBoardVO> listPageSearch(Criteria cri);
} // end interface