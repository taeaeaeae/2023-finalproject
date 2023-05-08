package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class ScheduleVO {

	private int rowNo;
	private int pid;
	private String uids;
	private String descript;
	private String place;
	private String addr;
	private int planDay;
	private int startTime;
	private double longitude;
	private double latitude;
	
	
	
	
}
