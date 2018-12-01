package com.mountainside.hydroppower.base.po.user;


import lombok.Data;

import java.io.Serializable;

/**
 * @Author : sxj
 * @Date : 2018/11/24 9:57
 * @Version : 1.0
 */
@Data
public class UserPo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String phone;
    private String loginName;
    private String realName;
    private String password;
    private Integer roleId;
    private Long createTime;
    private Long lastUpdateTime;
}
