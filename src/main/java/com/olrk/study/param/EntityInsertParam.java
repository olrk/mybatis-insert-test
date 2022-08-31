package com.olrk.study.param;

import lombok.Data;

/**
 * @author liaoruikai
 * @date 2022-08-31
 */
@Data
public class EntityInsertParam {
    /**
     * 数据总量类型，1：10万：2：50万；其他：5万
     */
    private int type;
}
