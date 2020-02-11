package com.leyou.item.dto;

import lombok.Data;

/**
 * @author wangxin
 * @date 2020/2/11 17:08
 * @description: 为了符合页面数据需求，我们创建一个传递数据的数据转移对象DTO
 * GOOD LUCK！
 */
@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private Long parentId;
    private Boolean isParent;
    private Integer sort;
}
