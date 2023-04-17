package org.zerock.myapp.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2
@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {

 
    @Override
    public void sessionCreated(HttpSessionEvent se)  { 
         log.trace("sessionCreated({}) invoked.", se);
         
         HttpSession sess = se.getSession();
         log.trace("\t+ Session ID: {}", sess.getId());
         
    }	//sessionCreated
    
    @Override
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	log.trace("sessionDestroyed({}) invoked.", se);
    	
        HttpSession sess = se.getSession();
        log.trace("\t+ Session ID: {}", sess.getId());
    	
    }	//sessionDestroyed
	
}	//end class
