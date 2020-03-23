package com.tickets.service.impl;

import com.tickets.entity.User;
import com.tickets.mapper.UserMapper;
import com.tickets.service.UserService;
import com.tickets.utils.PwdUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean save(User user) {
        if (StringUtils.isEmpty(user.getUStartusing())) {
            user.setUStartusing(1);
        }
        user.setUPassword(PwdUtil.digest(user.getUPassword()));
        return userMapper.insert(user) == 1;
    }

    @Override
    public boolean saveAll(List<User> users) {
        return false;
    }

    @Override
    public boolean remove(String uId) {
        return userMapper.delete(uId) == 1;
    }

    @Override
    public boolean removeReal(String uId) {
        return userMapper.deleteReal(uId) == 1;
    }
}
