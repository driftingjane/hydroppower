package com.mountainside.hydroppower.backendserver.request.user;

import lombok.Data;

/**
 * @Author : sxj
 * @Date : 2018/11/24 10:31
 * @Version : 1.0
 */
@Data
public class UserAddOrUpdateRequest {
    private Integer id;
    private String phone;
    private String loginName;
    private String realName;
    private String password;
}
