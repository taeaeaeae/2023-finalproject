package org.zerock.myapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.ChecklistDTO;
import org.zerock.myapp.domain.MycommentVO;
import org.zerock.myapp.domain.MywriteVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.MypageMapper;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor

@Service
public class MypageServiceImpl implements MypageService {

	@Autowired
	private MypageMapper mapper;
	
	@Override
	public ArrayList<MywriteVO> mywrite(String uids) {
		
		return mapper.mywrite(uids);
	}	// mywrite
	
	@Override
	public ArrayList<MycommentVO> mycomment(String uids) {
		
		return mapper.mycomment(uids);
	}

	@Override
	public ArrayList<ChecklistDTO> checklist(String uids) {

		return mapper.checklist(uids);
	}

	@Override
	public boolean listadd(ChecklistDTO dto) throws ServiceException {
		return this.mapper.listadd(dto) == 1;
	}

	@Override
	public boolean listupdate(ChecklistDTO dto) throws ServiceException {
		return this.mapper.listupdate(dto);
	}

	@Override
	public boolean listdelete(ChecklistDTO dto) throws ServiceException {
		return this.mapper.listdelete(dto);
	}




}	// end class
