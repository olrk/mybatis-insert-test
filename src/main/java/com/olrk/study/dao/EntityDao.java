package com.olrk.study.dao;

import com.olrk.study.entity.Entity;

import java.util.List;

/**
 * @author liaoruikai
 * @date 2022-08-31
 */
public interface EntityDao {
    void insert(Entity entity);

    void insertForeach(List<Entity> list);

    void insertBatch(Integer size, List<Entity> list);
}
