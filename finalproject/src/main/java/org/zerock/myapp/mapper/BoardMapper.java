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
//	public abstract List<FreeBoardVO> selectAll();				// 1-1. 게시글 가져오기(페이징X)
	
	@Select("""
			SELECT *
			FROM free_board	
			ORDER BY fid DESC
			OFFSET (#{currPage} - 1) * #{amount} ROWS	
			FETCH NEXT #{amount} ROWS ONLY
			""")
	public abstract List<FreeBoardVO> selectAll(Criteria cri);	// 1-2. 게시글 가져오기(페이징O)
	public abstract FreeBoardVO select(Integer fid);			// 2. 게시글 하나 조회
	public abstract Integer delete(Integer fid);				// 3. 게시글 삭제
	public abstract Integer insert(FreeBoardDTO dto);			// 4. 게시글 생성
	public abstract Integer update(FreeBoardDTO dto);			// 5. 게시글 수정
	
//	@Select("SELECT count(fid) FROM free_board WHERE fid > 0")
	public abstract Integer selectTotalCount(Criteria cri);		// 6. 게시글 전체 개수
	
	@Update("UPDATE free_board SET view_count = view_count + 1 WHERE fid = #{fid}") // 7. 조회수 증가 mapper
	public abstract Integer viewCountUp(Integer fid);
	
	public abstract Integer prev(Integer fid);	// 8. 이전글
	public abstract Integer next(Integer fid);	// 9. 다음글
	
	// 검색 + 목록 + 페이징
	public abstract List<FreeBoardVO> listPageSearch(Criteria cri); // 10. 검색 + 목록 + 페이징
} // end interface