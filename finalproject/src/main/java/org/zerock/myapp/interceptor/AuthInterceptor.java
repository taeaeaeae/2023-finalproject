package org.zerock.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Component

public class AuthInterceptor implements HandlerInterceptor{
	

	@Override
	public boolean preHandle(
			HttpServletRequest req, 
			HttpServletResponse res, 
			Object handler)
			throws Exception {
		log.trace("preHandle(requst, response, {}) invoked.",handler);
		
		HttpSession session = req.getSession(false);
		
		if(session == null) {
			res.sendRedirect("/user/login");
			return false;	
		}	// if
		
		
		Object auth = session.getAttribute("__AUTH__");
		if(auth == null) {	
			res.sendRedirect("/user/login");
			return false;	
		}	// if
			
		
		return true;
		
	}	//preHandle

	
}	// end class
