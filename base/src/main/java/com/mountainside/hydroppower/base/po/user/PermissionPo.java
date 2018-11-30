package com.mountainside.hydroppower.base.po.user;

import lombok.Data;

/**
 * @Author : sxj
 * @Date : 2018/11/30 9:41
 * @Version : 1.0
 */
@Data
public class PermissionPo {

    private Integer id;

    private String authKey;

    private String action;

    private String authName;

    private Integer parent;
}
