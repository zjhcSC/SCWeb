package com.zjhc.mybatis.mapper;

import com.zjhc.mybatis.bean.Permission;
import com.zjhc.mybatis.util.MyMapper;

import java.util.List;

public interface PermissionMapper extends MyMapper<Permission> {
    List<Permission> findAllPermissions();

    List<Permission> findPermissions(String username);

    int deleteAllKids(String parentIds);
}