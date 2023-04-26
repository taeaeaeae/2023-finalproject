package org.zerock.myapp.util;
/* 조회수 중복 방지 기능 */

import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.FreeBoardVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.BoardMapper;
import org.zerock.myapp.service.FreeBoardService;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString

@Log4j2
public class ViewCounting {
	
	public void checkVisited(Integer fid, HttpServletRequest req, HttpServletResponse res) throws ServiceException {
		
		Cookie oldCookie = null;
		Cookie[] cookies = req.getCookies();
		FreeBoardService service = null;
		
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("viewCountUp")) {
					oldCookie = cookie;
				} // if
			} // for
		} // if
		log.info("\t cookies : {}, oldCookie : {}", Arrays.deepToString(cookies), oldCookie);
		
		if(oldCookie != null) {
			if(!oldCookie.getValue().contains("[" + fid.toString() + "]")) {

				service.viewCountUp(fid);
				oldCookie.setValue(oldCookie.getValue() + "_[" + fid + "]");
				oldCookie.setPath("/");
				oldCookie.setMaxAge(60 * 60 * 24);
				res.addCookie(oldCookie);
			} // if
		} else {
			service.viewCountUp(fid);
			Cookie newCookie = new Cookie("viewCountUp", "[" + fid + "]");
			newCookie.setPath("/");
			newCookie.setMaxAge(60 * 60 * 24);
			res.addCookie(newCookie);
		} // if-else
	} // checkVisited

} // end class