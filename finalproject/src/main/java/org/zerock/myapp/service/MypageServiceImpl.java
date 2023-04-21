package org.zerock.myapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.MywriteVO;
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
	}	// arrayList

}	// end class
