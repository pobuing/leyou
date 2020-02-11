package com.leyou.item.controller;

import com.leyou.item.dto.CategoryDTO;
import com.leyou.item.service.TbCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangxin
 * @date 2020/2/11 17:12
 * @description: 分类Controller
 * GOOD LUCK！
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private TbCategoryService categoryService;

    @GetMapping(value = "/of/parent", name = "根据父id查询分类列表")
    public ResponseEntity<List<CategoryDTO>> findByParentId(@RequestParam(name = "pid") Long pid) {
        List<CategoryDTO> categoryDTOS = categoryService.findCategoryListByParentId(pid);
        return ResponseEntity.ok(categoryDTOS);
    }

}
