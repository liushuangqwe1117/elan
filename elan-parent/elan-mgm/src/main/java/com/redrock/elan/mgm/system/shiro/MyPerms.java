package com.redrock.elan.mgm.system.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.util.RegExPatternMatcher;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyPerms extends PermissionsAuthorizationFilter {

	private static final Logger log = LoggerFactory.getLogger(MyPerms.class);

	/** 正式表达式匹配 */
	protected boolean regexExpMatcher = false;

	public MyPerms() {
		this(true);
	}

	public MyPerms(boolean regexExp) {
		super();

		this.regexExpMatcher = regexExp;
		if (regexExp) {
			pathMatcher = new RegExPatternMatcher();
		}
	}

	protected boolean pathsMatch(String pattern, ServletRequest request) {
		String requestURI = getPathWithinApplication(request);
		if (request instanceof HttpServletRequest) {
			String queryString = ((HttpServletRequest) request)
					.getQueryString();
			if (regexExpMatcher && !StringUtils.isEmpty(queryString))
				requestURI += ("?" + queryString);
		}

		String regex = pattern;
		if (regexExpMatcher)
			regex = MyPathMatchingFilterChainResolver.replacePattern(pattern);

		if (log.isTraceEnabled()) {
			log.trace((regexExpMatcher ? "正则" : "ANT") + "规则[" + regex
					+ "]与当前请求地址[" + requestURI + "]...");
		}

		return pathsMatch(regex, requestURI);
	}

	@Override
	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {
		return false;
	}

}
