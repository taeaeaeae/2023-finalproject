package org.zerock.myapp.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2

@Data
public class ReportsDTO {

	private String uids;
	private String target_board_name;
	private String reason;
	private String reason_2;
	private String target_user;
	private Integer fid;
	
	
} // end class