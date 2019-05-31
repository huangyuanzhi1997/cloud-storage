package com.huang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huang.dao.UserDao;
import com.huang.domain.User;

/**
 * 
 * 用户管理服务器，负责查询用户、注册用户等操作
 * @author Administrator
 *
 */
@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 根据用户名查找用户
	 * @param mail
	 * @return
	 */
	public User getUserByname(String username)
	{
		return userDao.getUserByname(username);
	}
}
