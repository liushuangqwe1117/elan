package com.redrock.elan.mgm.system.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redrock.elan.common.project.mgm.exception.EMgmErrorCode;
import com.redrock.elan.common.project.mgm.exception.MgmCheckedException;
import com.redrock.elan.common.project.mgm.exception.MgmUncheckedException;

public class ExceptionAopInterceptor {

	private static Logger _log = LoggerFactory
			.getLogger(ExceptionAopInterceptor.class);

	public Object around(JoinPoint joinPoint) throws Throwable {
		try {
			return ((ProceedingJoinPoint) joinPoint).proceed();
		} catch (Throwable e) {
			_log.error(e.getMessage(), e);

			MgmCheckedException checkedEx = null;
			if (e instanceof MgmUncheckedException) {
				MgmUncheckedException ex = (MgmUncheckedException) e;
				checkedEx = new MgmCheckedException(ex.getCode(),
						ex.getMessage(), ex);
			} else if (e instanceof MgmCheckedException) {
				checkedEx = (MgmCheckedException) e;
			} else {
				checkedEx = new MgmCheckedException(
						EMgmErrorCode.SYS_ERROR.getValue(),
						EMgmErrorCode.SYS_ERROR.getDisplayName()
								+ e.getMessage(), e);
			}
			throw checkedEx;
		}
	}

}
