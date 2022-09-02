package com.olrk.study.dao.impl;

import com.olrk.study.dao.EntityDao;
import com.olrk.study.entity.Entity;
import com.olrk.study.mapper.EntityMapper;
import org.apache.commons.collections4.ListUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * @author liaoruikai
 * @date 2022-08-31
 */
@Repository
public class EntityDaoImpl implements EntityDao {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void insert(Entity entity) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            EntityMapper mapper = sqlSession.getMapper(EntityMapper.class);
            mapper.insert(entity);
        }
    }

    @Override
    public void insertForeach(List<Entity> list) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            EntityMapper mapper = sqlSession.getMapper(EntityMapper.class);
            mapper.insertList(list);
        }
    }

    @Override
    public void insertBatch(Integer size, List<Entity> list) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false)) {
            EntityMapper mapper = sqlSession.getMapper(EntityMapper.class);
            if (Objects.isNull(size) || size == 0) {
                list.forEach(mapper::insert);
            } else {
                ListUtils.partition(list, size).forEach(mapper::insertList);
            }
            sqlSession.commit();
        }
    }

}
