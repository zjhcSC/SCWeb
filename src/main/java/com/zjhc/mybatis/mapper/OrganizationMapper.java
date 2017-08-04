package com.zjhc.mybatis.mapper;

import com.zjhc.mybatis.bean.Organization;
import com.zjhc.mybatis.util.MyMapper;

import java.util.List;

public interface OrganizationMapper extends MyMapper<Organization> {


    List<Organization> listOrganizationByParentId(long parentId);

    List<Organization> listByLvl(int lvl);

    List<Organization> listNodesByPath(String path);

    int deleteAllKids(String path);
}