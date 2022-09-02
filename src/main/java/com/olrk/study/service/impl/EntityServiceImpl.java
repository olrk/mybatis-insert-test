package com.olrk.study.service.impl;

import com.olrk.study.dao.EntityDao;
import com.olrk.study.entity.Entity;
import com.olrk.study.service.EntityService;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public void insertForeach(Integer size, List<Entity> list) {
        if (Objects.isNull(size) || size == 0) {
            entityDao.insertForeach(list);
        } else {
            ListUtils.partition(list, size).forEach(entityDao::insertForeach);
        }
    }

    @Override
    public void insertBatch(Integer size, List<Entity> list) {
        entityDao.insertBatch(size, list);
    }

}
