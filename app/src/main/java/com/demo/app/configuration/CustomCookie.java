package com.demo.app.configuration;

import javax.servlet.http.HttpServletResponse;

public class CustomCookie {
	public static void setCookieOption(String sesionId,HttpServletResponse response) {
		response.setHeader("SET_COOKIE", "JESSONID"+sesionId+";secure;HttpOnly: SameSite=Strict");
		
	}

}
