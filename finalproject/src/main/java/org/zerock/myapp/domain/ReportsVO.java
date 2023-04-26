package org.zerock.myapp.domain;

import java.security.Timestamp;

import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Log4j2

@Value
public class ReportsVO {

	private Integer rid;
	private String uids;
	private String target_board_name;
	private Integer target_board_id;
	private String reason;
	private Timestamp reported_at;
	private String status;
	private Integer target_type;
	private String target_user;
	private Integer target_comment_id;
	private Integer fid;
	
} // end class