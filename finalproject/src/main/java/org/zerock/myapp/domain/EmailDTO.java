package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class EmailDTO {
    private String senderName;   
    private String senderMail;   
    private String receiveMail;  
    private String subject;           
    private String message;  
}//end class
