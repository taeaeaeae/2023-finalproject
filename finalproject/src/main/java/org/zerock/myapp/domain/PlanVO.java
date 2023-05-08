package org.zerock.myapp.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PlanVO {
  
	private int pid;			
	private String uids;		
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regDate;		
	private String planTitle;
	//Date type mismatch 에러 해결
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	private int planTotalDay;
	//쿼리로 조인할 VO
	private List<ScheduleVO> scheduleVOList;
	
	
	
	
}
