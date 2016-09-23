package com.redrock.elan.mgm.system.context;

import com.redrock.elan.common.project.mgm.dto.security.SecUserDto;

public class UserContextUtil {

	private static ThreadLocal<SecUserDto> threadLocal = new ThreadLocal<SecUserDto>();

	private static ThreadLocal<String> ipThreadLocal = new ThreadLocal<String>();

	public static void setSecUserDto(SecUserDto secUserDto) {
		threadLocal.set(secUserDto);
	}

	public static SecUserDto getSecUserDto() {
		SecUserDto user = threadLocal.get();
		if (user == null) {
			user = new SecUserDto();
		}
		return user;
	}

	public static void removeSecUserDto() {
		threadLocal.remove();
	}

	public static void setIP(String ip) {
		ipThreadLocal.set(ip);
	}

	public static String getIP() {
		return ipThreadLocal.get();
	}
}
