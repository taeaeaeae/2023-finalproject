package org.zerock.myapp.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2

@Data
public class ReportsDTO {

	private Integer rid;
	private String uids;
	private String target_board_name;
	private Integer target_board_id;
	private String reason;
	private String status;
	private Integer target_type;
	private String target_user;
	private Integer target_comment_id;
	private Integer fid;
	
} // end class