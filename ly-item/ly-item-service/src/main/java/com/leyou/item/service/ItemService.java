package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exceptions.LyException;
import com.leyou.item.entity.Item;
import org.springframework.stereotype.Service;

/**
 * @author wangxin
 * @date 2020/2/11 16:03
 * @description: TODO
 * GOOD LUCK！
 */
@Service
public class ItemService {
    public Item saveItem(Item item) {
        if (item.getPrice() == null) {
            throw new LyException(ExceptionEnum.ITEM_PRICE_NOT_NULL);
        }
        item.setId(100);
        return item;
    }
}
