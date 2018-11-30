package com.mountainside.hydroppower.base.dao;

/**
 * @Author : sxj
 * @Date : 2018/11/24 10:07
 * @Version : 1.0
 */
public interface BaseMapper<T> {
    Integer insert(T bean);
}
