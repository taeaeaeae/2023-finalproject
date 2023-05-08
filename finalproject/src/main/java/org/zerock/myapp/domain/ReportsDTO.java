package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class ReportsDTO {

	private String rid;
	private String uids;
	private String target_board_name;
	private String reason;
	private String reason_2;
	private String target_user;
	private Integer fid;
	private Integer status;
		
} // end class