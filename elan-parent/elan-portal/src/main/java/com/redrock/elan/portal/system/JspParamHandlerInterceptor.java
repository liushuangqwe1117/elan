package com.redrock.elan.portal.system;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 用于切换语言，POST提交值丢失的问题
 * @author luohui
 *
 */
public class JspParamHandlerInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger = LoggerFactory.getLogger(JspParamHandlerInterceptor.class);
	
	private static final String METHOD = "post";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 获取请求的方式
		String method = request.getMethod();
		StringBuilder sb = new StringBuilder();
		if(METHOD.equalsIgnoreCase(method)){//只处理POST提交的参数
			Enumeration<?> enums = request.getParameterNames();
			while(enums.hasMoreElements()){
				String key = (String)enums.nextElement();
				String[] valueArr = request.getParameterValues(key);
				if(valueArr != null && valueArr.length > 0){
					for(int i=0;i<valueArr.length;i++){
						if(StringUtils.isNotBlank(valueArr[i])){
							sb.append("&" + key + "=" + valueArr[i]);
						}
					}
				}
			}
			try {
				request.setAttribute("postSubmitParam", new String(sb.toString().getBytes(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				logger.error("",e);
			}
		}
		super.postHandle(request, response, handler, modelAndView);
	}
}
