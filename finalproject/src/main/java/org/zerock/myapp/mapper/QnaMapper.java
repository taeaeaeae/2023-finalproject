package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.QnaDTO;
import org.zerock.myapp.domain.QnaVO;


public interface QnaMapper {

	@Select("""
			SELECT * 
			FROM qna_board	
			ORDER BY qid DESC 
			OFFSET (#{currPage} -1) * #{amount} ROWS 
			FETCH NEXT #{amount} ROWS ONLY
			"""	)
	public abstract List<QnaVO> selectAll(Criteria cri);

	public abstract QnaVO select(Integer qid);	
	public abstract Integer delete(Integer qid);		
	public abstract Integer insert(QnaDTO dto);		
	public abstract Integer update(QnaDTO dto);		

	@Select("SELECT count(bno) FROM tbl_board WHERE bno > 0")
	public abstract Integer selectTotalCount();			
} // end interface
