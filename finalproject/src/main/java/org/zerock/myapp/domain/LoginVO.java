package org.zerock.myapp.domain;

import lombok.Value;

@Value
public class LoginVO {
	
	private String uids;
	private String password;
	private String name;
	private String email;
	private String nickname;
	private String phonenumber;
	
	

}	// end class
