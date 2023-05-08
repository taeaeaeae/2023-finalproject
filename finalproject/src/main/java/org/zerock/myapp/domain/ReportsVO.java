package org.zerock.myapp.domain;

import java.util.Date;

import lombok.Value;

@Value
public class ReportsVO {

	private Integer rid;
	private String uids;
	private String target_user;
	private String target_board_id;
	private String reason;
	private String reason_2;
	private Date reported_at;
	private String status;
	private String board_name;
	private Integer bno;
	private String bm;
		
} // end class