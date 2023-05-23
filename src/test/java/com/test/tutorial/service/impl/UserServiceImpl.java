package com.test.tutorial.service.impl;

import com.test.tutorial.bean.User;
import com.test.tutorial.service.UserService;
import com.test.tutorial.dal.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * User service implementation.
 *
 * @version 2014-7-25
 * @author Bert Lee
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;

    @Override
    public String getUserName(long userId) {
        if (userId <= 0L) {
            return "";
        }

        User user = userDao.getUserInfo(userId);
        return user != null ? user.getName() : "";
    }

    @Override
    public boolean updateUserName(long userId, String userName) {
        if (userId <= 0L || !StringUtils.hasLength(userName)) {
            logger.warn("param of 'updateUserName' is invalid, userId: {}, userName: {}",
                    userId, userName);
            return false;
        }

        User user = new User(userId, userName);
        int updateResult = userDao.updateUserInfo(user);
        return updateResult > 0;
    }

}
