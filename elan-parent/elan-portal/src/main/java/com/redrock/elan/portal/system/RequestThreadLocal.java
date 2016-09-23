package com.redrock.elan.portal.system;

import javax.servlet.http.HttpServletRequest;

public class RequestThreadLocal {
	private static ThreadLocal<HttpServletRequest> holder = new ThreadLocal<HttpServletRequest>();
	
	public static HttpServletRequest get(){
		return holder.get();
	}
	
	public static void set(HttpServletRequest request) {
		holder.set(request);
	}
}
