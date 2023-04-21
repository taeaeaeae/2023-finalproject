package org.zerock.myapp.mapper;

import java.util.ArrayList;

import org.zerock.myapp.domain.MywriteVO;

public interface MypageMapper {
	
	public abstract ArrayList<MywriteVO> mywrite(String uids);

}	// end interface
