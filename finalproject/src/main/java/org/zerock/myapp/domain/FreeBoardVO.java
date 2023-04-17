package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Log4j2

@Value
public class FreeBoardVO {

	private Integer fid;
	private String uids;
	private String title;
	private String content;
	private String image;
	private Integer view_count;
	private Timestamp insert_ts;
	private Timestamp update_ts;
	
} // end class