package com.test.tutorial.service.impl;

import com.test.tutorial.repository.entity.User;
import com.test.tutorial.service.UserService;
import com.test.tutorial.repository.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * User service implementation.
 *
 * @author Bert Lee
 * @version 2014-7-25
 */
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public String getUserName(long userId) {
        if (userId <= 0L) {
            return "";
        }

        User user = userMapper.selectById(userId);
        return user != null ? user.getUserName() : "";
    }

    @Override
    public boolean updateUserName(long userId, String userName) {
        if (userId <= 0L || !StringUtils.hasLength(userName)) {
            log.warn("param of 'updateUserName' is invalid, userId: {}, userName: {}",
                    userId, userName);
            return false;
        }

        User user = new User(userId, userName);
        int result = userMapper.updateById(user);
        return result > 0;
    }
}
