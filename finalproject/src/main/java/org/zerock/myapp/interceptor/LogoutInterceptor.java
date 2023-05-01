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
public class LogoutInterceptor implements HandlerInterceptor {
	
	// Target of LoginInterceptor : /user/logout 
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {
		log.trace("======================================================");
		log.trace("preHandle(req,res,handler)");
		log.trace("======================================================");
		
		
		HttpSession session = req.getSession(false);	
		session.invalidate();
		log.info("\t+ ¼¼¼Ç°´Ã¼ ÆÄ±«¿Ï·á");
		
		res.sendRedirect("/user/login");
		
		return false;	
	}	//preHandle


	
} // end class