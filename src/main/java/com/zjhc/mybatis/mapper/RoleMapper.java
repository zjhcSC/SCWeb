package com.zjhc.mybatis.mapper;

import com.zjhc.mybatis.bean.Role;
import com.zjhc.mybatis.util.MyMapper;
import com.zjhc.web.bean.Menu4Role;
import com.zjhc.web.bean.Permission4Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends MyMapper<Role> {
    List<Role> selectByName(String name);

    List<Long> selectResourceByRole(long roleId);

    List<Menu4Role> selectAllMenus();

    List<Permission4Role> selectAllPermissions();

    List<Long> selectAllResources();


    int addRoleResources(
            @Param("roleId") Long roleId,
            @Param("resourceIds") List<Long> resourceIds);

    int deleteRoleResources(
            @Param("roleId") Long roleId,
            @Param("resourceIds") List<Long> resourceIds);


    int deleteAllRoleResources(long id);
}