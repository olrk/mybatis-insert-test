package com.olrk.study.param;

import lombok.Builder;
import lombok.Data;

/**
 * @author liaoruikai
 * @date 2022-08-31
 */
@Data
@Builder
public class EntityInsertParam {
    /**
     * 数据总量类型，1：2万：2：5万；其他：0.5万
     */
    private Integer type;

    /**
     * 每批数量
     */
    private Integer size;
}
