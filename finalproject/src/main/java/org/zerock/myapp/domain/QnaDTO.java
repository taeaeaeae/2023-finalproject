package org.zerock.myapp.domain;

import lombok.Data;


@Data
public class QnaDTO {
	
	private Integer qid;
	private String uids;
	private String title;
	private String content;
	private String image;
	private boolean openy_n;

} // end class
