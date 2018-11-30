package com.mountainside.hydroppower.base.dao.user;

import com.mountainside.hydroppower.base.dao.BaseMapper;
import com.mountainside.hydroppower.base.po.user.UserPo;
import org.apache.ibatis.annotations.Param;

/**
 * @Author : sxj
 * @Date : 2018/11/24 10:07
 * @Version : 1.0
 */
public interface UserMapper extends BaseMapper<UserPo> {
    UserPo selectByNameOrPhone(@Param("loginName") String loginName);
}
