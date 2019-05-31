/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.huang.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.huang.domain.User;

/**
 * 
 *  用户的dao层
 * @author Administrator
 *
 */
@Repository
public class UserDao extends BaseDao<User> {
	
	public User getUserByname(String username)
	{
		String hql = "from User where username = ?";
		List<User> users = find(hql,username);
	    if (users==null || users.size() == 0) {
			return null;
		}else{
			return users.get(0);
		}
	}
}
