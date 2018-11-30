package com.mountainside.hydroppower.backendserver.request.user;

import lombok.Data;

/**
 * @Author : sxj
 * @Date : 2018/11/30 10:34
 * @Version : 1.0
 */
@Data
public class UserLoginRequest {
    private String userName;
    private String password;
}
