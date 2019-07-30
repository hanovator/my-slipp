package net.slipp.web;

import javax.servlet.http.HttpSession;

import net.slipp.domain.UserVo;

public class HttpSessionUtils {
	public static final String USER_SESSION_KEY = "sessionUser";
	
	public static boolean isLoginUser(HttpSession session){
		Object sessionUser = session.getAttribute(USER_SESSION_KEY);
		if(sessionUser == null) {
			return false;
		}
		return true;
	}
	
	public static UserVo getUserFromSession(HttpSession session){
		if(!isLoginUser(session)){
			return null;
		}
		return (UserVo)session.getAttribute(USER_SESSION_KEY);
	}
	
}
