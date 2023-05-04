package org.zerock.myapp.domain;

import lombok.Data;


@Data
public class NoticeDTO {
	
	private Integer nid;
	private String uids;
	private String title;
	private String content;
	private String image;
	private Integer view_count;
	private Integer top;
	

} // end class
