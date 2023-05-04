package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;


@Value
public class NoticeVO {

	private Integer nid;
	private String uids;
	private String title;
	private String content;
	private String image;
	private Timestamp insert_ts;
	private Timestamp update_ts;
	private Integer view_count;
	private Integer top;
	
} // end class
