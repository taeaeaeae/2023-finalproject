package org.zerock.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
	
	// Target of LoginInterceptor : /user/loginPost 
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {
		log.trace("======================================================");
		log.trace("preHandle(req,res,handler)");
		log.trace("======================================================");
		
		HttpSession session = req.getSession();
		
		Object auth = session.getAttribute("__AUTH__");
		log.info("\t+ auth: {}", auth);
		
		if(auth == null) {
			
			req.setAttribute("msg", "로그인 후 이용이 가능합니다.");
			req.setAttribute("location", "/user/login");
			req.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(req, res);
			
			return false;
		} 
		
		return true;

	}	//preHandle

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.trace("======================================================");
		log.trace("postHandle(req,res,handler,modelAndView)");
		log.trace("======================================================");
		

	}	//postHandle
	

	
	
} // end class
