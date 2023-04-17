package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.FreeBoardCommentDTO;
import org.zerock.myapp.domain.FreeBoardCommentVO;

public interface CommentMapper {
	
	public abstract List<FreeBoardCommentVO> selectAll(Integer fid);	// 댓글 전체 조회
	public abstract FreeBoardCommentVO select(Integer fbcid);
	public abstract Integer insert(FreeBoardCommentDTO dto);
	public abstract Integer update(FreeBoardCommentDTO dto);
	public abstract Integer delete(Integer fbcid);
	
	@Select("SELECT count(fid) FROM free_board WHERE fid > 0")
	public abstract Integer selectTotal();
	
} // end interface