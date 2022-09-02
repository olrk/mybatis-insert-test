package com.olrk.study.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author liaoruikai
 * @date 2022-08-31
 */
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Entity {
    Integer id;
    String date;
    String month;
    String name;
    String domain;
    String code;
    String number;
    Integer type;
    String province;
    Long count;
    Integer level;
    Integer model;
    String address;
    BigDecimal price;
    String remark;

    private static final String DATE = "2022-08-31";
    private static final String MONTH = "2022-08";
    private static final String NAME = "这是我的一个很长很长的用户名";
    private static final String DOMAIN = "thisIsMyDoMain@@";
    private static final String CODE = "nzmqti7ub0123";
    private static final String NUMBER = "08928382832832";
    private static final Integer TYPE = 1;
    private static final String PROVINCE = "台湾省";
    private static final Long COUNT = 59285923232L;
    private static final Integer LEVEL = 2;
    private static final Integer MODEL = 3;
    private static final String ADDRESS = "华盛顿西北 宾夕法尼亚大道 1600号";
    private static final BigDecimal PRICE = BigDecimal.valueOf(14981523.52);
    private static final String REMARK =
            "日本作家和大学教授们，也专门召开了主题研讨会。在会上，他们谴责岸田文雄为安倍晋三举行国葬，是在侮辱人人平等的理念。而他却要让民众为他的无聊买单。";

    static final List<Entity> _5000_ENTITY_LIST = new ArrayList<>();
    static final List<Entity> _20000_ENTITY_LIST = new ArrayList<>();
    static final List<Entity> _50000_ENTITY_LIST = new ArrayList<>();

    static {
        for (int i = 0; i < 5000; i++) {
            _5000_ENTITY_LIST.add(Entity.builder()
                    .date(DATE)
                    .month(MONTH)
                    .name(NAME)
                    .domain(DOMAIN)
                    .code(CODE)
                    .number(NUMBER)
                    .type(TYPE)
                    .province(PROVINCE)
                    .count(COUNT)
                    .level(LEVEL)
                    .model(MODEL)
                    .address(ADDRESS)
                    .price(PRICE)
                    .remark(REMARK)
                    .build());
        }

        for (int i = 0; i < 20000; i++) {
            _20000_ENTITY_LIST.add(Entity.builder()
                    .date(DATE)
                    .month(MONTH)
                    .name(NAME)
                    .domain(DOMAIN)
                    .code(CODE)
                    .number(NUMBER)
                    .type(TYPE)
                    .province(PROVINCE)
                    .count(COUNT)
                    .level(LEVEL)
                    .model(MODEL)
                    .address(ADDRESS)
                    .price(PRICE)
                    .remark(REMARK)
                    .build());
        }

        for (int i = 0; i < 50000; i++) {
            _50000_ENTITY_LIST.add(Entity.builder()
                    .date(DATE)
                    .month(MONTH)
                    .name(NAME)
                    .domain(DOMAIN)
                    .code(CODE)
                    .number(NUMBER)
                    .type(TYPE)
                    .province(PROVINCE)
                    .count(COUNT)
                    .level(LEVEL)
                    .model(MODEL)
                    .address(ADDRESS)
                    .price(PRICE)
                    .remark(REMARK)
                    .build());
        }

        System.out.println("初始化完成");
    }

    public static List<Entity> get5000EntityList() {
        return _5000_ENTITY_LIST;
    }

    public static List<Entity> get20000EntityList() {
        return _20000_ENTITY_LIST;
    }

    public static List<Entity> get50000EntityList() {
        return _50000_ENTITY_LIST;
    }

    public static List<Entity> getByType(Integer type) {
        if (Objects.isNull(type)) {
            return get5000EntityList();
        }
        if (1 == type) {
            return get20000EntityList();
        } else if (2 == type) {
            return get50000EntityList();
        } else {
            return get5000EntityList();
        }
    }

}
