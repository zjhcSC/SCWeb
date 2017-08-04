package com.zjhc.mybatis.service;

import com.zjhc.mybatis.bean.User;
import com.zjhc.mybatis.util.IService;
import com.zjhc.web.bean.DataPage;
import com.zjhc.web.bean.UserVO;

import java.util.Map;
import java.util.Set;

/**
 * @author 漏水亦凡
 * @create 2017-02-08 11:26.
 */
public interface UserService extends IService<User> {


    /**
     * 根据用户名查找角色
     *
     * @param username
     * @return
     */
    Set<String> findRoles(String username);

    /**
     * 根据用户名查找权限
     *
     * @param username
     * @return
     */
    Set<String> findPermissions(String username);

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据用户名判断是否为管理员Admin
     *
     * @param username
     * @return
     */
    boolean isAdmin(String username);

    DataPage getUsers(int offset, int limit, String name);


    Map<String, Object> getUserInfo(String id);

    int updateUser(UserVO userUpdate);

    int delete(long id);

    int addUser(UserVO userUpdate) throws Exception;


}
