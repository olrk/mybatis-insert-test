package com.olrk.study.controller;

import com.olrk.study.entity.Entity;
import com.olrk.study.param.EntityInsertParam;
import com.olrk.study.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaoruikai
 * @date 2022-08-31
 */
@RestController
@RequestMapping("entity")
public class EntityController {
    @Autowired
    private EntityService entityService;

    /**
     * 循环单条插入
     */
    @PostMapping("/insert")
    public int insert(@RequestBody EntityInsertParam param) {
        entityService.insert(Entity.getByType(param.getType()));
        return 0;
    }

    /**
     * foreach插入
     */
    @PostMapping("/insertForeach")
    public int insertForeach(@RequestBody EntityInsertParam param) {
        entityService.insertForeach(Entity.getByType(param.getType()));
        return 0;
    }

    /**
     * batch循环单条插入
     */
    @PostMapping("/insertBatch")
    public int insertBatch(@RequestBody EntityInsertParam param) {
        entityService.insertBatch(Entity.getByType(param.getType()));
        return 0;
    }

    /**
     * foreach分1000条一批插入
     */
    @PostMapping("/insertSplitForeach")
    public int insertSplitForeach(@RequestBody EntityInsertParam param) {
        entityService.insertSplitForeach(Entity.getByType(param.getType()));
        return 0;
    }

    /**
     * batch每1000条foreach插入
     */
    @PostMapping("/insertBatchSplitForeach")
    public int insertBatchSplitForeach(@RequestBody EntityInsertParam param) {
        entityService.insertBatchSplitForeach(Entity.getByType(param.getType()));
        return 0;
    }
}
