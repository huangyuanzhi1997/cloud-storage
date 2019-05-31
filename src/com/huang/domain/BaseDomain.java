/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.huang.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * domain的基类
 * @author Administrator
 *
 */
public class BaseDomain implements Serializable{
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
