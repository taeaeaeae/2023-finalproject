package org.zerock.myapp.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.myapp.mapper.MypageMapper;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor

@Service
public class MoreListServiceImpl implements MorelistService {

	@Autowired
	private MypageMapper mapper;
	
	@Autowired
	SqlSessionFactory sqlsessionfactory;
	
	@Override
	public List<Map<String,Object>> morelist(int startNum) throws Exception{
		
		int cnt = 10;
		Map<String,Object> map = new HashMap<>();
		map.put("startNum", startNum);
		map.put("limit", 5);
		map.put("cnt", cnt);
		
		List<Map<String,Object>> list = mapper.morelist(startNum);
				
				return list;
	}
	




}	// end class
