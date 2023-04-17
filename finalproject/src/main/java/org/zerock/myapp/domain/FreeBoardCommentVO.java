package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;

//@Log4j2

@Value
public class FreeBoardCommentVO {

	private String fbcid;
	private String fid;
	private String uids;
	private String content;
	private Timestamp insert_ts;
	private Timestamp update_ts;
	
} // end class