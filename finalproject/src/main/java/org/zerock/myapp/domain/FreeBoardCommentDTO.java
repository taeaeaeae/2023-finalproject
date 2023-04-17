package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Log4j2

@Data
public class FreeBoardCommentDTO {

	private String fbcid;
	private String fid;
	private String uids;
	private String content;

} // end class