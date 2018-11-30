package com.mountainside.hydroppower.base.po.user;

import lombok.Data;

import java.util.Date;

/**
 * @Author : sxj
 * @Date : 2018/11/30 9:49
 * @Version : 1.0
 */
@Data
public class RolePo {

    private Integer id;

    private String roleName;

    private String roleDesc;

    private Integer userCount;

    private Date createTime;

    private Date lastUpdateTime;
}
