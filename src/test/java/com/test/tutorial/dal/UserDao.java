package com.test.tutorial.dal;

import com.test.tutorial.bean.User;

/**
 * User data access object.
 *
 * @author Bert Lee
 * @version 2014-7-25
 */
public interface UserDao {

    /**
     * Gets user info for specified user ID.
     *
     * @param id user ID
     * @return user info
     */
    User getUserInfo(long id);

    /**
     * Updates user info.
     *
     * @param user user info
     * @return 0 means fail, 1 means success
     */
    int updateUserInfo(User user);

}
