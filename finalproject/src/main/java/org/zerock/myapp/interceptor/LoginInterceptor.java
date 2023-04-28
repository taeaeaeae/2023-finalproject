package org.zerock.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.myapp.domain.LoginVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Component
public class LoginInterceptor implements HandlerInterceptor {
	
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
		
		if(auth != null) {
			session.removeAttribute("__AUTH__");	
			log.info("\t+ 세션영역에서 인증객체(__AUTH__) 삭제 완료");
		}	// if
		
		return true;
	}	//preHandle

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.trace("======================================================");
		log.trace("postHandle(req,res,handler,modelAndView)");
		log.trace("======================================================");
		
		log.info("\t+ 1. modelAndView: {}", modelAndView);
		
		ModelMap modelMap = modelAndView.getModelMap();
		
		LoginVO userVO = (LoginVO) modelMap.get("__AUTH__");
		log.info("\t+ 2. userVO: {}", userVO);
		
		
		if(userVO != null) {	
			HttpSession session = req.getSession();				
			session.setAttribute("__AUTH__", userVO);	
			log.info("\t+ 3. 세션영역에 새로운 인증객체 저장 성공");
		}	// if
	}	//postHandle
	
	

	
	
} // end class
