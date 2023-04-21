package org.zerock.myapp.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.MywriteVO;

@Service
public interface MypageService {
	
	public ArrayList<MywriteVO> mywrite(String uids);


}	// end interface
