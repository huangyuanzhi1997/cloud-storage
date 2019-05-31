/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.huang.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javaswift.joss.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huang.bean.MessageBean;
import com.huang.cons.Constants;
import com.huang.domain.User;
import com.huang.service.UserService;
import com.huang.util.MD5;
import com.huang.util.UtilTools;

/**
 * 
 * 用户涉及到的所有控制action
 * @author Administrator
 *
 */
@Controller
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	
	/**
	 * 登陆
	 * @param request
	 * @param response
	 * @param email
	 * @param password
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Object login(HttpServletRequest request, HttpServletResponse response,
			String username, String password) {
		User user = userService.getUserByname(username);
		if (user == null) {
			return new MessageBean(false,Constants.ERROR_1);
		}
		MD5 md5 = new MD5();
		String inputstr = md5.getMD5ofStr(password);
		if (!inputstr.equals(user.getPassword())) {
			return new MessageBean(false,Constants.ERROR_2);
		}
		//测试swift链接配置是否成功----
		try
		{
			Account accout = UtilTools.getAccount();
			System.out.println("测试accout链接  ----- "+accout);
		}
		catch(Exception e)
		{
			return new MessageBean(false,Constants.ERROR_3);
		}
		setSessionUser(request, user);
		return new MessageBean(true,Constants.SUCCESS_1);
	}
	
	/**
	 * 跳转到成功页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/tosucess")
	public ModelAndView tosucess() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/sucess");
                view.getModel().put("path", "test");
		return view;
	}
	/**
	 * 跳转到成功页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/test")
	@ResponseBody
	public String test() {
            return "string";
	}

}
