package com.mountainside.hydroppower.backendserver.response;

import com.mountainside.hydroppower.base.po.user.UserPo;
import lombok.Data;

import java.util.List;

/**
 * @Author : sxj
 * @Date : 2018/11/30 10:30
 * @Version : 1.0
 */
@Data
public class LoginSuccessVo {
    private UserPo userPo;
    private List<String> permissionPoList;
}
