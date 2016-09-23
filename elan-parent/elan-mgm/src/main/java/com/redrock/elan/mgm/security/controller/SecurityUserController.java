package com.redrock.elan.mgm.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.ylinkpay.framework.core.model.PageData;
import org.ylinkpay.framework.core.util.StringUtil;
import org.ylinkpay.framework.web.base.util.PageMessageUtil;
import org.ylinkpay.framework.web.base.util.TokenUtil;

import com.redrock.elan.common.core.constant.ERoleType;
import com.redrock.elan.common.core.constant.EUserStatus;
import com.redrock.elan.common.core.converter.ERoleTypeConverter;
import com.redrock.elan.common.core.converter.EUserStatusConverter;
import com.redrock.elan.common.core.util.PasswordUtil;
import com.redrock.elan.common.project.mgm.dto.security.SecUserDto;
import com.redrock.elan.mgm.common.BaseController;
import com.redrock.elan.mgm.common.MgmConstants;
import com.redrock.elan.mgm.security.common.EChangePwdError;
import com.redrock.elan.mgm.security.service.SecUserService;

@Controller
@RequestMapping("/security/user")
public class SecurityUserController extends BaseController {

	/**
	 * 部署到was上，用redirect进行跳转就必须用全路径
	 */
	public static final String REQUEST_BASE_PATH = "/security/user/";
	public static final String PAGE_BASE_PATH = "security/user/";

	public static final String LIST_PATH = PAGE_BASE_PATH + "securityUser_list";
	public static final String ADD_EDIT_PATH = PAGE_BASE_PATH
			+ "securityUser_edit";
	public static final String VIEW_PATH = PAGE_BASE_PATH + "securityUser_view";
	public static final String CHANGEPWD_PATH = PAGE_BASE_PATH
			+ "securityUser_changepwd";

	@Autowired
	protected SecUserService secUserService;

	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(ERoleType.class, new ERoleTypeConverter());
		binder.registerCustomEditor(EUserStatus.class,
				new EUserStatusConverter());
	}

	/**
	 * 显示用户列表
	 * 
	 * @param model
	 * @param pageData
	 * @param queryParam
	 * @return
	 * @author LS
	 * @date 2013-8-1
	 */
	@RequestMapping("/list")
	public String list(Model model, PageData<SecUserDto> pageData,
			SecUserDto queryParam, HttpServletRequest request) {
		try {
			pageData = secUserService.listPage(pageData, queryParam);
		} catch (Exception e) {
			_log.error("获取用户列表出错：" + e.getMessage(), e);
			PageMessageUtil.saveErrorMessage(request.getSession(), "获取用户列表出错!");
		}
		model.addAttribute("pageData", pageData);
		// 分页显示
		setPagination(model, pageData, request);

		// 状态枚举
		model.addAttribute("userStatuses", EUserStatus.values());
		model.addAttribute("roleTypes", ERoleType.values());

		return LIST_PATH;
	}

	/**
	 * 新增或修改用户
	 * 
	 * @param model
	 * @param id
	 *            用户ID
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(Model model,
			@RequestParam(value = "id", required = false) String userId,
			HttpServletRequest request) {
		if (userId != null) {
			// 修改
			try {
				model.addAttribute("obj", secUserService.get(userId));
			} catch (Exception e) {
				_log.error("获取用户信息出错：" + e.getMessage(), e);
				PageMessageUtil.saveErrorMessage(request.getSession(),
						"获取用户信息出错!");
				return redirectToList();
			}
		} else {
			// 新增
			model.addAttribute("obj", new SecUserDto());
		}

		model.addAttribute("roleTypes", ERoleType.getAllowRoleType());
		return ADD_EDIT_PATH;
	}

	/**
	 * 保存或更新
	 * 
	 * <p>
	 * BindingResult必须紧跟在@Valid标注的对象后面
	 * </p>
	 * 
	 * @param loginAccount
	 * @param roleIds
	 * @param error
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/save")
	public String saveOrUpdate(@Valid @ModelAttribute("obj") SecUserDto user,
			BindingResult error, HttpServletRequest request, HttpSession session) {
		if (error.hasErrors()) {
			return ADD_EDIT_PATH;
		}
		// 防止重复提交
		if (TokenUtil.isDuplicateSubmit(request)) {
			PageMessageUtil.saveErrorMessage(session, "重复提交！");
			return ADD_EDIT_PATH;
		}
		try {
			if (StringUtils.isNotBlank(user.getId())) {
				secUserService.updateSelective(user);

				PageMessageUtil.saveSuccessMessage(session,
						"修改用户[" + user.getLoginName() + "]成功");
			} else {
				user.setPassword(PasswordUtil
						.encryptPlainByMd5WithSalt(MgmConstants.DEFAULT_USER_PASSWORD));
				secUserService.save(user);
				PageMessageUtil.saveSuccessMessage(session,
						"新增用户[" + user.getLoginName()
								+ "]成功，初始密码为[<font color='red'>"
								+ MgmConstants.DEFAULT_USER_PASSWORD
								+ "</font>]");
			}
		} catch (Exception e) {
			_log.error("获取用户信息出错：" + e.getMessage(), e);
			PageMessageUtil.saveErrorMessage(request.getSession(),
					"新增或修改用户信息出错!");
		}
		return redirectToList();
	}

	@RequestMapping("/checkLoginName")
	@ResponseBody
	public boolean checkLoginName(@RequestParam("id") String id,
			@RequestParam("loginName") String loginName) {
		try {
			return !secUserService.checkLoginName(id, loginName);
		} catch (Exception e) {
			_log.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 冻结用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/lock")
	public String lock(@RequestParam("id") String id, HttpSession session,
			HttpServletRequest request) {
		try {
			secUserService.lock(id);
			PageMessageUtil.saveSuccessMessage(session, "冻结用户成功");
		} catch (Exception e) {
			_log.error("冻结用户失败", e);
			PageMessageUtil.saveErrorMessage(session, "冻结用户失败");
		}
		return redirectToList();
	}

	/**
	 * 启用用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/unlock")
	public String unlock(@RequestParam("id") String id, HttpSession session,
			HttpServletRequest request) {
		try {
			secUserService.unlock(id);
			PageMessageUtil.saveSuccessMessage(session, "解冻用户成功");
		} catch (Exception e) {
			_log.error("解冻用户失败", e);
			PageMessageUtil.saveErrorMessage(session, "解冻用户失败");
		}
		return redirectToList();
	}

	/**
	 * 启用用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/active")
	public String active(@RequestParam("id") String id, HttpSession session,
			HttpServletRequest request) {
		try {
			secUserService.unlock(id);
			PageMessageUtil.saveSuccessMessage(session, "激活用户成功");
		} catch (Exception e) {
			_log.error("解冻用户失败", e);
			PageMessageUtil.saveErrorMessage(session, "激活用户失败");
		}
		return redirectToList();
	}
	
	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id, HttpSession session,
			HttpServletRequest request) {
		try {
			secUserService.delete(id);
			PageMessageUtil.saveSuccessMessage(session, "删除用户成功");
		} catch (Exception e) {
			_log.error("注销用户失败", e);
			PageMessageUtil.saveErrorMessage(session, "删除用户失败");
		}
		return redirectToList();
	}

	/**
	 * 重置密码
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/resetpwd")
	public String resetpwd(@RequestParam("id") String id,HttpSession session,
			HttpServletRequest request) {
		try {
			String randomPass = RandomStringUtils.randomAlphanumeric(8);
			secUserService.changepwd(id,PasswordUtil.encryptPlainByMd5WithSalt(randomPass));
			PageMessageUtil.saveSuccessMessage(session,
					"重置密码成功，重置密码为：<font color='red'>" + randomPass + "</font>");
		} catch (Exception e) {
			_log.error("重置密码失败", e);
			PageMessageUtil.saveErrorMessage(session, "重置密码失败");
		}
		return redirectToList();
	}

	/**
	 * 跳转到修改密码页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/changepwd", method = RequestMethod.GET)
	public String tochangepwd(Model model) {
		model.addAttribute("fromPage", "user");
		return CHANGEPWD_PATH;
	}

	/**
	 * 修改密码
	 * 
	 * @param model
	 * @param loginAccountId
	 * @param newPwd
	 * @param confirmNewPwd
	 * @return
	 */
	@RequestMapping(value = "/changepwd", method = RequestMethod.POST)
	public String changepwd(Model model, @RequestParam("userId") String userId,
			@RequestParam("oldPwd") String oldPwd,
			@RequestParam("newPwd") String newPwd,
			@RequestParam("confirmNewPwd") String confirmNewPwd,
			@RequestParam("fromPage") String fromPage, HttpSession session,
			HttpServletRequest request) {

		EChangePwdError ecpe = validChangePwd(userId, oldPwd, newPwd,
				confirmNewPwd);

		if (ecpe == EChangePwdError.SUCCESS) {
			try {
				secUserService.changepwd(userId,PasswordUtil.encryptMd5ByMd5WithSalt(newPwd.trim()));
				PageMessageUtil.saveSuccessMessage(session, "更新密码成功");
			} catch (Exception e) {
				PageMessageUtil.saveErrorMessage(session, "更新密码失败");
				_log.error("更新密码失败", e);
			}
		} else {
			PageMessageUtil.saveErrorMessage(session, ecpe.getMsg());
		}
		if ("main".equals(fromPage)) {
			return "redirect:/main.html";
		}
		return CHANGEPWD_PATH;
	}

	/**
	 * 验证修改密码
	 * 
	 * @description
	 * @return
	 * @author LS
	 * @date 2014-2-17
	 */
	private EChangePwdError validChangePwd(String userId, String oldPwd,
			String newPwd, String confirmNewPwd) {
		EChangePwdError ecpe = EChangePwdError.SUCCESS;
		if (StringUtil.isBlank(oldPwd)) {
			ecpe = EChangePwdError.OLD_PWD_EMPTY;
		} else if (StringUtil.isBlank(newPwd)) {
			ecpe = EChangePwdError.NEW_PWD_EMPTY;
		} else if (StringUtil.isBlank(confirmNewPwd)) {
			ecpe = EChangePwdError.NEW_REPWD_EMPTY;
		} else if (!newPwd.trim().equals(confirmNewPwd.trim())) {
			ecpe = EChangePwdError.PWD_REPWD_NO_EQUAL;
		} else if (newPwd.trim().equals(oldPwd.trim())) {
			ecpe = EChangePwdError.OLDPWD_NEWPWD_EQUAL;
		} else {
			try {
				SecUserDto user = secUserService.get(userId);

				String oldPwdMd5Hex = PasswordUtil.encryptMd5ByMd5WithSalt(oldPwd.trim());
				if (!user.getPassword().trim().equals(oldPwdMd5Hex)) {
					ecpe = EChangePwdError.OLD_PWD_ERROR;
				}
			} catch (Exception e) {
				_log.error(e.getMessage(), e);
				ecpe = EChangePwdError.ERROR;
			}
		}
		return ecpe;
	}

	/**
	 * 查看用户信息
	 * 
	 * @param model
	 * @param id
	 * @return
	 * @author LS
	 * @date 2013-8-3
	 */
	@RequestMapping("/view")
	public String view(Model model, @RequestParam("id") String id,
			HttpSession session) {
		try {
			model.addAttribute("obj", secUserService.get(id));
		} catch (Exception e) {
			_log.error("查看用户信息失败", e);
			PageMessageUtil.saveErrorMessage(session, "查看用户信息失败");
			return redirectToList();
		}
		return VIEW_PATH;
	}

	/**
	 * 用户查看自己的信息,无须权限控制 no control view
	 * 
	 * @param model
	 * @param id
	 * @return
	 * @author LS
	 * @date 2013-8-3
	 */
	@RequestMapping("/ncview")
	public String ncview(Model model, @RequestParam("id") String id,
			HttpSession session) {
		try {
			model.addAttribute("obj", secUserService.get(id));
		} catch (Exception e) {
			_log.error("查看用户信息失败", e);
			PageMessageUtil.saveErrorMessage(session, "查看用户信息失败");
		}
		model.addAttribute("reqAction", "ncview");
		return VIEW_PATH;
	}

	private String redirectToList() {
		return "redirect:" + REQUEST_BASE_PATH + "list.html";
	}
}
