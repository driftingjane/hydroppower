package com.mountainside.hydroppower.backendserver.common.configuration;

import com.mountainside.hydroppower.base.dao.user.RoleMapper;
import com.mountainside.hydroppower.base.dao.user.UserMapper;
import com.mountainside.hydroppower.base.po.user.PermissionPo;
import com.mountainside.hydroppower.base.po.user.UserPo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : sxj
 * @Date : 2018/11/30 8:23
 * @Version : 1.0
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException();
        }
        final SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        final List<String> permissions = (List<String>) SecurityUtils.getSubject().getSession().getAttribute("permission");
        info.addStringPermissions(permissions);
        return info;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token) throws AuthenticationException {

        final UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        final String username = usernamePasswordToken.getUsername();
        if (username == null) {
            throw new AccountException("用户名不能为空");
        }
        UserPo userPo = this.userMapper.selectByNameOrPhone(username);
        if (userPo == null) {
            throw new UnknownAccountException("用户不存在");
        }
        //session存入user相关信息
        SecurityUtils.getSubject().getSession().setAttribute("user", userPo);
        //session存入permission相关信息
        final List<PermissionPo> permissionList = roleMapper.getPermissionsByRoleId(userPo.getRoleId());
        final List<String> permissions = new ArrayList<>();
        permissionList.forEach(item -> {
            String authKey = null == item.getParent() ? item.getAuthKey() : item.getAuthKey() + ":" + item.getAction();
            permissions.add(authKey);
        });

        SecurityUtils.getSubject().getSession().setAttribute("permission", permissions);
        return new SimpleAuthenticationInfo(username, userPo.getPassword(), getName());
    }
}
