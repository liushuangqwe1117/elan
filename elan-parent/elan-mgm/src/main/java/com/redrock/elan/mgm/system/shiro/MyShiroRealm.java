package com.redrock.elan.mgm.system.shiro;


import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.ylinkpay.framework.core.util.StringUtil;

import com.redrock.elan.common.project.mgm.dto.security.SecUserDto;
import com.redrock.elan.mgm.security.service.SecPermService;
import com.redrock.elan.mgm.security.service.SecUserService;

/**
 * Shiro Realm 实现
 * 
 * @author LS
 * 
 */
public class MyShiroRealm extends AuthorizingRealm {
	
	private static Logger _log = LoggerFactory.getLogger(MyShiroRealm.class);

	@Autowired
	protected SecUserService secUserService;
	
	@Autowired
	protected SecPermService secPermService;
 
	/**
	 * 授权信息
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String username = (String) principals.fromRealm(getName()).iterator().next();

		if (username != null) {
			SecUserDto user;
			try {
				user = secUserService.getByLoginName(username);
			} catch (Exception e) {
				_log.error("获取用户角色信息失败："+e.getMessage());
				return null;
			}
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			List<String> perms = secPermService.findPermIDByRole(user.getRoleType().getValue());
			if(perms != null && !perms.isEmpty()) {
				info.addStringPermissions(perms);
			}
			return info;
		}
		return null;
	}

	/**
	 * 认证信息
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		if (StringUtil.isNotBlank(token.getUsername())) {
			SecUserDto user;
			user = secUserService.getByLoginName(token.getUsername());
			if (user != null) {
				return new SimpleAuthenticationInfo(user.getLoginName(),user.getPassword(), getName());
			}
		}
		return null;
	}

}
