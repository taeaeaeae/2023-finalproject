package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;

@Value
public class MywriteVO {

	private String uids;
	private String title;
	private Timestamp insert_ts;
	private String board_name;
	private Integer bno;
	private String bm;
	
}
