package com.test.tutorial.service;

/**
 * User service.
 *
 * @author Bert Lee
 * @version 2014-7-25
 */
public interface UserService {

    /**
     * Gets user name for specified user ID.
     *
     * @param userId user ID
     * @return user name
     */
    String getUserName(long userId);

    /**
     * Updates user name for specified user ID.
     *
     * @param userId   user ID
     * @param userName user name
     * @return <code>true</code> means success, <code>false</code> means fail
     */
    boolean updateUserName(long userId, String userName);

    /**
     * 查询用户所属的组织名称
     *
     * @param userId 用户身份
     * @return 组织名称
     */
    String getOrgName(long userId);

}
