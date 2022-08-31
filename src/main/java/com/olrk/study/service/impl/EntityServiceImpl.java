package com.olrk.study.service.impl;

import com.olrk.study.dao.EntityDao;
import com.olrk.study.entity.Entity;
import com.olrk.study.service.EntityService;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liaoruikai
 * @date 2022-08-31
 */
@Service
public class EntityServiceImpl implements EntityService {
    @Autowired
    private EntityDao entityDao;

    @Override
    public void insert(List<Entity> list) {
        list.forEach(entityDao::insert);
    }

    @Override
    public void insertForeach(List<Entity> list) {
        entityDao.insertForeach(list);
    }

    @Override
    public void insertBatch(List<Entity> list) {
        entityDao.insertBatch(list);
    }

    @Override
    public void insertSplitForeach(List<Entity> list) {
        ListUtils.partition(list, 1000).forEach(entityDao::insertForeach);
    }

    @Override
    public void insertBatchSplitForeach(List<Entity> list) {
        entityDao.insertBatchForeach(list);
    }
}
