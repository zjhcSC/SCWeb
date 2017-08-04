package com.zjhc.mybatis.mapper;

import com.zjhc.mybatis.bean.Menu;
import com.zjhc.mybatis.util.MyMapper;

import java.util.List;

public interface MenuMapper extends MyMapper<Menu> {


    List<Menu> findAllMenus();

    List<Menu> findMenus(String username);

    int deleteAllKids(String paths);

}