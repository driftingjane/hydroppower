package com.mountainside.hydroppower.backendserver.controller.user;

import com.mountainside.hydroppower.backendserver.request.user.UserAddOrUpdateRequest;
import com.mountainside.hydroppower.backendserver.request.user.UserLoginRequest;
import com.mountainside.hydroppower.backendserver.response.LoginSuccessVo;
import com.mountainside.hydroppower.backendserver.service.user.UserService;
import com.mountainside.hydroppower.base.common.api.ApiResponse;
import com.mountainside.hydroppower.base.common.constant.RetCode;
import com.mountainside.hydroppower.base.common.exception.BusinessException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : sxj
 * @Date : 2018/11/24 10:10
 * @Version : 1.0
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("新增用户")
    @PostMapping("/addNewUser")
    public ApiResponse<Integer> addNewUser(@RequestBody UserAddOrUpdateRequest userAddOrUpdateRequest){
        try{
            return this.userService.addNewOrUpdateUser(userAddOrUpdateRequest);
        }catch (Exception e){
            log.error("新增用户异常：{}",e);
            return new ApiResponse<>(RetCode.ERROR,"新增用户失败");
        }
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public ApiResponse<LoginSuccessVo> userLogin(@RequestBody UserLoginRequest userLoginRequest){
        try {
            return this.userService.login(userLoginRequest);
        }catch (BusinessException be){
            log.warn("用户登录异常：{}",be);
            return new ApiResponse<>(RetCode.PARAM_ERROR,be.getMessage());
        }catch (AccountException ae){
            log.warn("用户登录异常：{}",ae);
            return new ApiResponse<>(RetCode.PARAM_ERROR,ae.getMessage());
        }catch (Exception e){
            log.error("用户登录异常：{}",e);
            return new ApiResponse<>(RetCode.PARAM_ERROR,"用户登录失败");
        }
    }
}
