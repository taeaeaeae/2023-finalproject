package org.zerock.myapp.domain;

import lombok.Value;

@Value
public class UsersVO {

	private String uids;
	private String password;
	private String name;
	private String phonenumber;
	private String email;
	private Integer is_delete;
	private String reason;
	
	
}	// end class
