package com.zjhc.web.service;

import com.zjhc.mybatis.bean.Organization;
import com.zjhc.mybatis.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 漏水亦凡
 * @date 2017/6/19
 */
@Service
public class OrganizationService {

    @Autowired
    OrganizationMapper organizationMapper;


    public List<Organization> listRoot(int lvl) {
        return organizationMapper.listByLvl(lvl);
    }

    public List<Organization> listNodes(Long id) {
        Organization org = organizationMapper.selectByPrimaryKey(id);
        String path = org.getParentIds();
        path = path == null ? id + "/%" : path + id + "/%";
        return organizationMapper.listNodesByPath(path);
    }

    public int add(Organization org) {
        return organizationMapper.insertSelective(org);
    }

    public int update(Organization org) {
        return organizationMapper.updateByPrimaryKeySelective(org);
    }

    public int delete(Long id, String path) {
        //TODO 检查是否被引用

        //先删除kids
        int num = organizationMapper.deleteAllKids(path);

        num += organizationMapper.deleteByPrimaryKey(id);
        return num;
    }
}
