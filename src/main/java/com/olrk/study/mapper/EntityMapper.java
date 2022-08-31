package com.olrk.study.mapper;

import com.olrk.study.entity.Entity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liaoruikai
 * @date 2022-08-31
 */
@Mapper
public interface EntityMapper {
    void insert(@Param("entity") Entity entity);

    void insertList(@Param("list") List<Entity> list);
}
