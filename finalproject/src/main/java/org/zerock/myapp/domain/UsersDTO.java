package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class UsersDTO {

	private String uids;
	private String password;
	private String name;
	private String nickname;
	private String phonenumber;
	private String email;
	private Integer is_delete;
	private String reason;
	
}	// end class
