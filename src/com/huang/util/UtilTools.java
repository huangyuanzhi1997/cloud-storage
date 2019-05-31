/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.huang.util;

import java.io.IOException;
import java.util.Properties;

import org.javaswift.joss.client.factory.AccountConfig;
import org.javaswift.joss.client.factory.AccountFactory;
import org.javaswift.joss.model.Account;
import org.javaswift.joss.model.StoredObject;

/**
 * 
 * 工具类，提供公共方法
 * @author Administrator
 *
 */
public class UtilTools {

    public static String COFING_FILE = "/config/xiandian.properties";
    public static Properties p;

    public static Properties getConfig() {
        if (p == null) {
            p = new Properties();
            try {
                p.load(Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream(COFING_FILE));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return p;
    }

    public static String formatPath(StoredObject so) {
        return so.getPublicURL();
    }

    private static Account account;

    public static Account getAccount() {
//		if (account == null) 
        {
            AccountConfig config = new AccountConfig();
            config.setUsername(UtilTools.getConfig().getProperty("USERNAME"));
            config.setPassword(UtilTools.getConfig().getProperty("PASSWORD"));
            config.setAuthUrl(UtilTools.getConfig().getProperty("AUTHURL"));
            config.setTenantId(UtilTools.getConfig().getProperty("TENANTID"));
            config.setTenantName(UtilTools.getConfig()
                    .getProperty("TENANTNAME"));
            account = new AccountFactory(config).createAccount();
        }
        return account;
    }

    public static Account getLocalAccount() {
//		if (account == null) 
        {
            AccountConfig config = new AccountConfig();
            config.setUsername("test0001");
            config.setPassword("111111");
            config.setAuthUrl("http://controller:35357/v2.0/tokens");
            config.setTenantId("affbb73548294c198002e48cc0f3cf87");
            config.setTenantName("test0002");
            account = new AccountFactory(config).createAccount();
        }
        return account;
    }

    public static void main(String[] args) {
        for(String s:"asdfasdf".split("/"))
            System.out.println(s);
    }
}
