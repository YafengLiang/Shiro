package com.voicecodes.mainsite.service;

import com.voicecodes.domain.common.ServiceException;
import com.voicecodes.domain.model.Role;
import com.voicecodes.domain.model.User;
import com.voicecodes.domain.model.UserExample;
import com.voicecodes.persitience.mapper.RoleMapper;
import com.voicecodes.persitience.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YafengLiang
 * @Description:
 * @Date: Created in  14:18 2018/6/20
 */
@Service
public class UserImpl implements IUser {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User getUserByLoginId(String username,String password) throws ServiceException {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.or();
        criteria.andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<User> users  =userMapper.selectByExample(example);
        if (users != null && users.size()>0){
            return users.get(0);
        }else {
            return null;
        }
    }

}
