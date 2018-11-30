package com.mountainside.hydroppower.base.dao.user;

import com.mountainside.hydroppower.base.dao.BaseMapper;
import com.mountainside.hydroppower.base.po.user.PermissionPo;
import com.mountainside.hydroppower.base.po.user.RolePo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author : sxj
 * @Date : 2018/11/30 9:49
 * @Version : 1.0
 */
public interface RoleMapper extends BaseMapper<RolePo> {
    List<PermissionPo> getPermissionsByRoleId(@Param("roleId") Integer roleId);
}
