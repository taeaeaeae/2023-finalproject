package org.zerock.myapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.BookmarkVO;
import org.zerock.myapp.domain.ChecklistDTO;
import org.zerock.myapp.domain.ChecklistVO;
import org.zerock.myapp.domain.LikesVO;
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
	}	//mycomment

	@Override
	public ArrayList<ChecklistVO> checklist(String uids) {
		return mapper.checklist(uids);
	} //checklist

	@Override
	public boolean listadd(ChecklistDTO dto) throws ServiceException {
		return this.mapper.listadd(dto) == 1;
	} //listadd

	@Override
	public boolean listupdate(ChecklistDTO dto) throws ServiceException {
		return this.mapper.listupdate(dto);
	} //listupdate

	@Override
	public boolean listdelete(ChecklistDTO dto) throws ServiceException {
		return this.mapper.listdelete(dto);
	} //listdelete

	@Override
	public ArrayList<LikesVO> likes(String uids) {
		
		return this.mapper.likes(uids);
	} //likes

	@Override
	public ArrayList<BookmarkVO> bookmark(String uids) {
		return this.mapper.bookmark(uids);
	} //bookmark

	



}	// end class
