package com.leyou.item.controller;

import com.leyou.item.entity.Item;
import com.leyou.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangxin
 * @date 2020/2/11 16:10
 * @description: TODO
 * GOOD LUCK！
 */
@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/save")
    public ResponseEntity<Item> save(Item item) {
        Item item1 = itemService.saveItem(item);
        return ResponseEntity.status(200).body(item1);
    }
}
