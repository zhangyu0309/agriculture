package com.agri.platform.authority.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.agri.core.base.action.BaseAction;
import com.agri.core.common.AuthorityCommon;
import com.agri.core.util.JsonUtils;
import com.agri.core.util.MD5;
import com.agri.platform.authority.bean.Admin;
import com.agri.platform.authority.service.AdminService;

/**
 * 管理员操作接口类
 *
 */
@Controller
public class AdminAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	@Resource
	private AdminService adminService;
	/**
	 * 用户Id参数
	 */
	private String userId;
	
	/**
	 * 用户密码参数
	 */
	private String passwd;
	/**
	 * 用户重复
	 */
	private String rePasswd;
	/**
	 * 登录成功与否的标记信息
	 */
	private String loginMsg;
	/**
	 * 管理员用户
	 */
	private Admin admin;
	/**
	 * 验证码
	 */
	private String randomCode;
	/**
     * 用户登录模块
     * @return
     */
	public String login(){
		//先判断当前是否已经登录
		Admin sessionAdmin = (Admin) this.getSession(AuthorityCommon.ADMIN_SESSION);
		
		try {
			if(sessionAdmin.getUserId()!=null){
				return AuthorityCommon.LOGIN_SUCCESS;
			}else{
				return AuthorityCommon.LOGIN_FAILED;
			}
		} catch (Exception e) {
			boolean captchaPassed = true;//SimpleImageCaptchaServlet.validateResponse(this.getRequest(), this.randomCode);
			if(captchaPassed){
				if(this.userId!=null&&this.passwd!=null){
					Map<String,Object> map = this.adminService.isLoginSuccess(userId, passwd);
					if((Boolean) map.get("flag")){
						//设置管理员用户Session
						Admin loginAdmin = (Admin) map.get("Admin");
//						this.admin = loginAdmin;
						this.getSession().setAttribute(AuthorityCommon.ADMIN_SESSION, loginAdmin);
						//查询所有管理员的权限（Map对象），并且把它放到Session中
						this.getSession().setAttribute(AuthorityCommon.ADMIN_AUTHORITY_SESSION, this.adminService.getOPAuthorityList(userId));
						System.out.println("CommonAuthoritySession :"+JsonUtils.getJAVABeanJSON(this.getSession(AuthorityCommon.ADMIN_AUTHORITY_SESSION)));
						return AuthorityCommon.LOGIN_SUCCESS;
					}else{
						//登录失败 返回失败原因
						this.loginMsg=(String) map.get("msg");
						return AuthorityCommon.LOGIN_FAILED;
					}
				}else{
					this.loginMsg = "用户名及其密码必填";
					return AuthorityCommon.LOGIN_FAILED;
				}
			}else{
				this.loginMsg = "验证码不正确";
				return AuthorityCommon.LOGIN_FAILED;
			}
			
		}
		
		
	}
	/**
	 * 用户退出系统模块
	 * @return
	 */
	public String loginOut(){
		//移除Session对象
		this.getSession().removeAttribute(AuthorityCommon.ADMIN_SESSION);
		//移除管理员权限Session
		return AuthorityCommon.LOGIN_OUT;
	}
	/**
	 * 密码修改
	 * @return
	 */
	public String passwdChange(){
		Map<String,Object> map = new HashMap<String,Object>();
		if(this.passwd!=null&&this.rePasswd!=null&&!this.passwd.trim().equals("")&&!this.rePasswd.trim().equals("")){
			String passReg = "^(?=.{8,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$";
			if (Pattern.matches(passReg, this.rePasswd)){
				Admin tempAdmin = (Admin) this.getSession(AuthorityCommon.ADMIN_SESSION);
				if(MD5.GetMD5Code(this.passwd).equals(tempAdmin.getPassword())){
					tempAdmin.setPassword(MD5.GetMD5Code(rePasswd));
					if(this.adminService.changePasswd(rePasswd, tempAdmin.getUserId())){
						map.put("flag", true);
						map.put("msg", "修改密码成功！");
						//更新用户Session    
						this.getSession().setAttribute(AuthorityCommon.ADMIN_SESSION, tempAdmin);
					}else{
						map.put("flag", false);
						map.put("msg", "修改密码失败！");
					}
				}else{
					map.put("flag", false);
					map.put("msg", "原密码输入不正确");
				}
			}else{
				map.put("flag", false);
				map.put("msg", "新密码强度不够，要求至少8为，且同时包含数字和字母");
			}
		}else{
			map.put("flag", false);
			map.put("msg", "修改密码传递的参数为空");
		}
		this.jsonString = JsonUtils.getJAVABeanJSON(map);
		try {
			this.responseWriter(jsonString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * 
	 * 一下方法只是提供给Struts2自己内部使用
	 * 
	 * 
	 */
	
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public void setRePasswd(String rePasswd) {
		this.rePasswd = rePasswd;
	}
    public String getLoginMsg() {
		return loginMsg;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public String getUserId() {
		return userId;
	}
	public void setRandomCode(String randomCode) {
		this.randomCode = randomCode;
	}
	
    
    
}
