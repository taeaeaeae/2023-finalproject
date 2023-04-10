package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;


@Value
public class AnswerVO {
	private Integer qid;
	private String uids;
	private String title;
	private String content;
	private String image;
	private Timestamp insert_ts;
	private Timestamp update_ts;
	

} // end class
