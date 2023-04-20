package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2

@Data
public class FreeBoardDTO {

	private Integer fid;
	private String uids;
	private String title;
	private String content;
	private String image;
	private Integer view_count;
	
} // end class