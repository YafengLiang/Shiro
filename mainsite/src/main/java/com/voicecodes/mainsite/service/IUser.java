package com.voicecodes.mainsite.service;

import com.voicecodes.domain.common.ServiceException;
import com.voicecodes.domain.model.Role;
import com.voicecodes.domain.model.User;

import java.util.List;

/**
 * @Author: YafengLiang
 * @Description:
 * @Date: Created in  11:38 2018/6/20
 */
public interface IUser {

    User getUserByLoginId(String username,String password) throws ServiceException;

}
