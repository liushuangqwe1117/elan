package com.redrock.elan.mgm.security.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.redrock.elan.mgm.security.service.SecUserService;

/**
 * 初始化系统账户
 * 
 * @author LS
 * 
 */
@Component
@Lazy(false)
public class InitRootUser implements InitializingBean{

	private static Logger _log = LoggerFactory.getLogger(InitRootUser.class);
	
	@Autowired
	private SecUserService secUserService;

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			_log.info("初始化系统账户...");
			secUserService.initSecUserDto();		
		} catch (Exception e) {
			_log.error("初始化系统账户出错",e);
		}
	}
}
