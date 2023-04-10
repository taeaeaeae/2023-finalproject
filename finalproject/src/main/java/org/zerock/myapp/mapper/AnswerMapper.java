package org.zerock.myapp.mapper;

import org.zerock.myapp.domain.AnswerDTO;
import org.zerock.myapp.domain.AnswerVO;


public interface AnswerMapper {

	public abstract AnswerVO select(Integer qid);		
	public abstract Integer delete(Integer qid);		
	public abstract Integer insert(AnswerDTO dto);		
	public abstract Integer update(AnswerDTO dto);		
	
} // end interface
