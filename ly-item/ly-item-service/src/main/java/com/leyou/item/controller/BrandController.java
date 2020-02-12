package com.leyou.item.controller;

import com.leyou.common.vo.PageResult;
import com.leyou.item.dto.BrandDTO;
import com.leyou.item.service.TbBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wangxin
 * @date 2020/2/12 16:44
 * @description: 品牌Controller
 * GOOD LUCK！
 */

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private TbBrandService brandService;

    @GetMapping(value = "/page", name = "分页查询品牌数据")
    public ResponseEntity<PageResult<BrandDTO>> findByPage(
            @RequestParam(value = "key", required = false) String key,//关键字
            @RequestParam(value = "page", defaultValue = "1") Integer page,//当前页码
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,//每页显示的条数
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc) {
        PageResult<BrandDTO> pageResult = brandService.findByPage(key, page, rows, sortBy, desc);
        return ResponseEntity.ok(pageResult);
    }

    /**
     * 新增商品类别
     *
     * @param brandDTO
     * @param cids
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> insertBrand(BrandDTO brandDTO,
                                            @RequestParam("cids") List<Long> cids) {
        brandService.insertBrand(brandDTO, cids);
        return ResponseEntity.ok().build();
    }

    /**
     * 商品类别修改
     *
     * @param brandDTO
     * @param cids
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateBrand(BrandDTO brandDTO,
                                            @RequestParam("cids") List<Long> cids) {
        brandService.updateBrand(brandDTO, cids);
        return ResponseEntity.ok().build();

    }


}
