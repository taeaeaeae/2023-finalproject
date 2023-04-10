package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class AnswerDTO {
	private Integer qid;
	private String uids;
	private String title;
	private String content;
	private String image;

	
} // end class
