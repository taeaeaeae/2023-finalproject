package org.zerock.myapp.service;

import java.util.Objects;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.EmailDTO;
import org.zerock.myapp.exception.ServiceException;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class EmailServiceImpl implements EmailService, InitializingBean {
 
    @Autowired
    JavaMailSender mailSender; 
    
	@Override
	public void afterPropertiesSet() throws ServiceException{	
		log.trace("afterPropertiesSet() invoked.");
		
		try {
			Objects.requireNonNull(this.mailSender);
			log.info("\t+ this.mapper: {}", this.mailSender);
		}catch (Exception e) {
			throw new ServiceException(e);
		}	//try-catch
	}// afterPropertiesSet
 
    @Override
    public void sendMail(EmailDTO dto) throws ServiceException {
        try {
            MimeMessage msg = mailSender.createMimeMessage();
 
            msg.addRecipient(RecipientType.TO, new InternetAddress(dto.getReceiveMail()));
 
            msg.addFrom(new InternetAddress[] { new InternetAddress(dto.getSenderMail(), dto.getSenderName()) });
 
            msg.setSubject(dto.getSubject(), "utf-8");
            msg.setText(dto.getMessage(), "utf-8");
 

            mailSender.send(msg);
            
        } catch (Exception e) {
			throw new ServiceException(e);
        }//tc
        
    }//sendMail

}//end class
