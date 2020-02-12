package com.leyou.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wangxin
 * @date 2020/2/12 16:02
 * @description: 分页结果实体类
 * GOOD LUCK！
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    private Long total;//总条数
    private Long totalPage;//总页数
    private List<T> items;


    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }


}
