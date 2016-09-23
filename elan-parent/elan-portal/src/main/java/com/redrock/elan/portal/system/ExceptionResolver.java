/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 * 创建:lyg 2012-10-30
 */

package com.redrock.elan.portal.system;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class ExceptionResolver extends SimpleMappingExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		logger.error(getStackTraceString(ex, true));

		String viewName = determineViewName(ex, request);
        
		if (viewName != null) {
            if (!(request.getHeader("accept").indexOf("application/json") > -1 || ( request
                    .getHeader("X-Requested-With") != null && request
                    .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1 ))) {
                // 非异步方式返回
                Integer statusCode = determineStatusCode(request, viewName);
                if (statusCode != null) {
                    applyStatusCodeIfPossible(request, response, statusCode);
                }
                // 跳转到提示页面
                return getModelAndView(viewName, ex, request);
            } else {
                // 异步方式返回
                try {
                    PrintWriter writer = response.getWriter();
                    writer.write(ex.getMessage());
                    writer.flush();
                } catch ( Exception e ) {
                	logger.error(e.getMessage(),e);
                }
                // 不进行页面跳转
                return null;
            }
        } else {
            return null;
        }
	}


	private String getStackTraceString(Throwable ex, boolean detal) {
		if (detal == true) {
			StringWriter stringWriter= new StringWriter();
	        PrintWriter writer= new PrintWriter(stringWriter);
	        ex.printStackTrace(writer);
	        StringBuffer buffer= stringWriter.getBuffer();
	        return buffer.toString();
		}
		return ex.getMessage();
	}
}
