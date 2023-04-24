package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;

@Value
public class MycommentVO {

	private String uids;
	private String content;
	private Timestamp insert_ts;
	
}
