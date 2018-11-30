package com.mountainside.hydroppower.backendserver.service.user;

import com.mountainside.hydroppower.backendserver.common.util.MD5Util;
import com.mountainside.hydroppower.backendserver.request.user.UserAddOrUpdateRequest;
import com.mountainside.hydroppower.backendserver.request.user.UserLoginRequest;
import com.mountainside.hydroppower.backendserver.response.LoginSuccessVo;
import com.mountainside.hydroppower.base.common.api.ApiResponse;
import com.mountainside.hydroppower.base.common.constant.RetCode;
import com.mountainside.hydroppower.base.common.exception.BusinessException;
import com.mountainside.hydroppower.base.dao.user.UserMapper;
import com.mountainside.hydroppower.base.po.user.UserPo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author : sxj
 * @Date : 2018/11/24 10:29
 * @Version : 1.0
 */
@Service
@Slf4j
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Value("${saltEncrypt}")
    private String saltEncrypt;

    public ApiResponse<Integer> addNewOrUpdateUser(UserAddOrUpdateRequest userAddOrUpdateRequest){
        UserPo userPo = new UserPo();
        BeanUtils.copyProperties(userAddOrUpdateRequest,userPo,"password");
        //盐值的MD5加密,加密二次
        String encryptedPassword = MD5Util.md5(MD5Util.md5(userAddOrUpdateRequest.getPassword()) + saltEncrypt);
        userPo.setPassword(encryptedPassword);
        //todo 动态权限
        userPo.setRoleId(1);
        userPo.setCreateTime(System.currentTimeMillis());
        userPo.setLastUpdateTime(System.currentTimeMillis());
        this.userMapper.insert(userPo);
        return new ApiResponse<>(RetCode.OK,"新增或更新成功",userPo.getId());
    }

    public ApiResponse<LoginSuccessVo> login(UserLoginRequest userLoginRequest){
        if(StringUtils.isEmpty(userLoginRequest.getUserName()) || StringUtils.isEmpty(userLoginRequest.getUserName())){
            throw new BusinessException("登录参数错误");
        }
        String saltedPassword = MD5Util.md5(MD5Util.md5(userLoginRequest.getPassword()) + saltEncrypt);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userLoginRequest.getUserName(),saltedPassword);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);
            if(!subject.isAuthenticated()){
                throw new AccountException("用户没有权限");
            }
        }catch (IncorrectCredentialsException | AccountException e){
            throw new AccountException("用户名或密码错误");
        }
        LoginSuccessVo loginSuccessVo = new LoginSuccessVo();
        UserPo userPo = (UserPo) subject.getSession().getAttribute("user");
        userPo.setPassword(null);
        loginSuccessVo.setUserPo(userPo);
        loginSuccessVo.setPermissionPoList((List<String>)subject.getSession().getAttribute("permission"));
        return new ApiResponse<>(RetCode.OK,"登录成功",loginSuccessVo);
    }
}
