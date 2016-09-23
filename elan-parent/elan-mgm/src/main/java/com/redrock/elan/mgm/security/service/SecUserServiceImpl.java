package com.redrock.elan.mgm.security.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ylinkpay.framework.core.model.PageData;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.redrock.elan.common.core.constant.ERoleType;
import com.redrock.elan.common.core.constant.EUserStatus;
import com.redrock.elan.common.core.util.PasswordUtil;
import com.redrock.elan.common.project.mgm.dto.security.SecUserDto;
import com.redrock.elan.common.project.mgm.exception.EMgmErrorCode;
import com.redrock.elan.common.project.mgm.exception.MgmUncheckedException;
import com.redrock.elan.mgm.common.MgmConstants;
import com.redrock.elan.mgm.security.dao.SecUserDtoMapper;

@Service("secUserService")
public class SecUserServiceImpl implements SecUserService {

	private static Logger _log = LoggerFactory
			.getLogger(SecUserServiceImpl.class);

	@Autowired
	SecUserDtoMapper secUserDtoMapper;

	@Autowired
	SecPermService secPermService;
	
	/**
	 * 
	 * @param name
	 * @return
	 * @author LS
	 * @date 2013-8-6
	 */
	@Override
	public SecUserDto getByLoginName(String loginName) {
		return secUserDtoMapper.getByLoginName(loginName);
	}

	/**
	 * 初始化系统管理员账户
	 * 
	 * @author LS
	 * @date 2013-7-29
	 */
	@Override
	@Transactional
	public void initSecUserDto() {
		SecUserDto root = secUserDtoMapper.getByLoginName(MgmConstants.ROOT_LOGIN_NAME);
		if (root == null) {
			root = new SecUserDto();
			root.setId(root.getIdentity());
			root.setLoginName(MgmConstants.ROOT_LOGIN_NAME);
			root.setRealName("管理员");
			root.setPassword(PasswordUtil.encryptPlainByMd5WithSalt(MgmConstants.DEFAULT_ROOT_PASSWORD));
			root.setRoleType(ERoleType.ADMIN);
			root.setStatus(EUserStatus.EFFECTIVE);
			root.setCreateTime(new Date());
			try {
				// 保存账户
				secUserDtoMapper.insert(root);
				_log.info("成功初始化系统账户！");
			} catch (Exception e) {
				String msg = "初始化系统账户异常：" + e.getMessage();
				_log.error(msg);
				throw new MgmUncheckedException(EMgmErrorCode.SYS_ERROR.getValue(), msg);
			}
		}
		secPermService.initAdminRole();
	}

	@Override
	public SecUserDto get(String id) {
		return secUserDtoMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageData<SecUserDto> listPage(PageData<SecUserDto> pageData,
			SecUserDto params) {
		PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize());
		List<SecUserDto> items = secUserDtoMapper.list(params);
		
		Page<SecUserDto> page = (Page<SecUserDto>)items;
		
		pageData.setRows(items);
		pageData.setTotal(page.getTotal());
		
		return pageData;
	}

	@Override
	@Transactional
	public void save(SecUserDto entity) {
		entity.setId(entity.getIdentity());
		entity.setStatus(EUserStatus.WAIT_ACTIVE);
		entity.setCreateTime(new Date());
		secUserDtoMapper.insert(entity);
	}

	@Override
	@Transactional
	public void update(SecUserDto entity) {
		secUserDtoMapper.updateByPrimaryKey(entity);
	}

	@Override
	public boolean checkLoginName(String id, String loginName) {
		return secUserDtoMapper.checkLoginName(id, loginName) > 0;
	}

	@Override
	public boolean checkEmail(String id, String email) {
		return secUserDtoMapper.checkEmail(id, email) > 0;
	}

	@Override
	@Transactional
	public void updateLockTime(String id) {
		SecUserDto entity = new SecUserDto();
		entity.setId(id);
		entity.setLastLoginLockTime(new Date());
		updateSelective(entity);
	}

	@Override
	@Transactional
	public void updateSelective(SecUserDto entity) {
		secUserDtoMapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	@Transactional
	public void delete(String id) {
		updateStatus(id, EUserStatus.DELETE);
	}

	@Override
	@Transactional
	public void unlock(String id) {
		updateStatus(id, EUserStatus.EFFECTIVE);
	}

	@Override
	@Transactional
	public void lock(String id) {
		updateStatus(id, EUserStatus.DISABLED);
	}
	
	private void updateStatus(String id, EUserStatus userStatus){
		SecUserDto entity = new SecUserDto();
		entity.setId(id);
		entity.setStatus(userStatus);
		updateSelective(entity);
	}

	@Override
	@Transactional
	public void changepwd(String id, String password) {
		SecUserDto entity = new SecUserDto();
		entity.setId(id);
		entity.setPassword(password);
		updateSelective(entity);
	}

	@Override
	public void lockUser(String id) {
		SecUserDto entity = new SecUserDto();
		entity.setId(id);
		entity.setLoginErrorTimes(0L);
		entity.setLastLoginLockTime(new Date());
		updateSelective(entity);
	}

	@Override
	public void loginSuccess(String id) {
		SecUserDto entity = new SecUserDto();
		entity.setId(id);
		entity.setLoginErrorTimes(0L);
		entity.setLastLoginLockTime(null);
		updateSelective(entity);
	}
}
