package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.NoticeDTO;
import org.zerock.myapp.domain.NoticeVO;

public interface NoticeMapper {
	
	@Select("""
			SELECT *
			FROM notice_board	
			ORDER BY nid DESC
			OFFSET (#{currPage} - 1) * #{amount} ROWS	
			FETCH NEXT #{amount} ROWS ONLY
			""")
	public abstract List<NoticeVO> selectAll(Criteria cri);	// 1. 게시글 가져오기(페이징O)
	public abstract NoticeVO select(Integer nid);			// 2. 게시글 하나 조회
	public abstract Integer delete(Integer nid);			// 3. 게시글 삭제
	public abstract Integer insert(NoticeDTO dto);			// 4. 게시글 생성
	public abstract Integer update(NoticeDTO dto);			// 5. 게시글 수정
	public abstract Integer selectTotalCount(Criteria cri);	// 6. 게시글 전체 개수
	
	@Update("UPDATE notice_board SET view_count = view_count + 1 WHERE nid = #{nid}") // 7. 조회수 증가 mapper
	public abstract Integer viewCountUp(Integer nid);
	
	public abstract Integer prev(Integer nid);	// 8. 이전글
	public abstract Integer next(Integer nid);	// 9. 다음글
	public abstract List<NoticeVO> listPageSearch(Criteria cri); // 10. 검색 + 목록 + 페이징
	
	public abstract Integer top(Integer nid);	// 게시글 상단고정
	
} // end interface