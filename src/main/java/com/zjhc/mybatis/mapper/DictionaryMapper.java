package com.zjhc.mybatis.mapper;

import com.zjhc.mybatis.bean.Dictionary;
import com.zjhc.mybatis.util.MyMapper;

import java.util.List;

public interface DictionaryMapper extends MyMapper<Dictionary> {


    List<Dictionary> listByParentId(long parentId);

    List<Dictionary> listByName(String name);

    int deleteAllKids(long parentId);

    List<Dictionary> getParameter(String data);

}