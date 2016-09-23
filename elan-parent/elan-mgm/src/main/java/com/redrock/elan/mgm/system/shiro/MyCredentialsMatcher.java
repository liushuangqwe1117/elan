package com.redrock.elan.mgm.system.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import com.redrock.elan.common.core.util.PasswordUtil;

public class MyCredentialsMatcher extends SimpleCredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		
		if (token == null || info == null)
			return false;
		
		CaptchaUsernamePasswordToken authcToken = (CaptchaUsernamePasswordToken)token;
		String tokenCredentials = String.valueOf(authcToken.getPassword());
		Object accountCredentials = this.getCredentials(info);
		return equals(PasswordUtil.encryptMd5ByMd5WithSalt(tokenCredentials), accountCredentials);
	}
}
