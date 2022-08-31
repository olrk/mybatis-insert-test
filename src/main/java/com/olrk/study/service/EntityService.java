package com.olrk.study.service;

import com.olrk.study.entity.Entity;

import java.util.List;

/**
 * @author liaoruikai
 * @date 2022-08-31
 */
public interface EntityService {
    void insert(List<Entity> list);

    void insertForeach(List<Entity> byType);

    void insertBatch(List<Entity> byType);

    void insertSplitForeach(List<Entity> byType);

    void insertBatchSplitForeach(List<Entity> byType);
}
