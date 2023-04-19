package org.zerock.myapp.domain;

import lombok.Value;

@Value
public class FindVO {
	
	private String uids;
	private String name;
	private String phonenumber;
	
	private String password;
	private String email;
	
}	// end class
