package com.zjhc.mybatis.mapper;

import com.zjhc.mybatis.bean.Resource;
import com.zjhc.mybatis.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper extends MyMapper<Resource> {
    List<Resource> getResourceByType(String type);

    int countResourceByType(String type);

    int countResource();

    Resource getResourceByTypeAndId(@Param("type") String type,
                                    @Param("resourceId") long resourceId);

    int deleteResourceByTypeAndId(@Param("type") String type,
                                  @Param("resourceId") long resourceId);

    int deleteResourceKids(@Param("type") String type,
                           @Param("paths") String paths);

    int updateAllsByIds(@Param("ids") List<String> ids,
                        @Param("alls") int alls);

    List<String> getRoleByResourceId(@Param("resourceId") long resourceId,
                                     @Param("type") String type);
}