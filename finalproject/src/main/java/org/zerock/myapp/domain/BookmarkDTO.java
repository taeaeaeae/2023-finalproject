package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class BookmarkDTO {
	
	private Integer fbbmid;		// free_board용
	private Integer pbmid;		// plan_board용
	private String uids;
	private Integer fid;
	private Integer pid;
	private boolean bookmarked;
	
}	// end class
