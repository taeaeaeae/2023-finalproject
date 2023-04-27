package org.zerock.myapp.domain;

import lombok.Value;

@Value
public class LoginVO {
	
	private String uids;
	private String password;
	private String is_delete;

}	// end class
