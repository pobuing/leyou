package com.leyou.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leyou.common.utils.BeanHelper;
import com.leyou.common.vo.PageResult;
import com.leyou.item.dto.BrandDTO;
import com.leyou.item.entity.TbBrand;
import com.leyou.item.entity.TbCategoryBrand;
import com.leyou.item.mapper.TbBrandMapper;
import com.leyou.item.service.TbBrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 服务实现类
 * </p>
 *
 * @author SYL
 * @since 2020-02-11
 */
@Service
public class TbBrandServiceImpl extends ServiceImpl<TbBrandMapper, TbBrand> implements TbBrandService {
    @Autowired
    private TbCategoryBrandServiceImpl categoryBrandService;

    @Override
    public void insertBrand(BrandDTO brandDTO, List<Long> cids) {
        //类型转换 将数据传输对象 转换为表实体类对象
        TbBrand tbBrand = BeanHelper.copyProperties(brandDTO, TbBrand.class);
        //存储brand对象
        this.save(tbBrand);
        //操作中间表
        for (Long cid : cids) {
            TbCategoryBrand categoryBrand = new TbCategoryBrand();
            categoryBrand.setBrandId(tbBrand.getId());
            categoryBrand.setCategoryId(cid);
            categoryBrandService.save(categoryBrand);
        }
    }


    @Override
    public void updateBrand(BrandDTO brandDTO, List<Long> cids) {
        //类型转换
        TbBrand tbBrand = BeanHelper.copyProperties(brandDTO, TbBrand.class);
        Long brandId = brandDTO.getId();
        this.updateById(tbBrand);
        //覆盖分类数据 先删除旧的数据 再添加新的数据
        QueryWrapper<TbCategoryBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TbCategoryBrand::getBrandId, brandId);
        categoryBrandService.remove(queryWrapper);
        //新增数据
        for (Long cid : cids) {
            TbCategoryBrand categoryBrand = new TbCategoryBrand();
            categoryBrand.setBrandId(brandId);
            categoryBrand.setCategoryId(cid);
            categoryBrandService.save(categoryBrand);
        }
    }

    @Override
    public PageResult<BrandDTO> findByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        //sql: select * from tb_brand where name like 'key' or letter = 'key'
        // order by name desc|asc
        QueryWrapper<TbBrand> queryWrapper = new QueryWrapper<>();
        //构建分页
        Page<TbBrand> page1 = new Page<>(page, rows);
        //判断是否有关键字查询
        if (StringUtils.isNotBlank(key)) {
            queryWrapper.lambda().or().like(TbBrand::getName, key).or().eq(TbBrand::getLetter, key);
        }
        //判断排序字段是否为空
        if (StringUtils.isNotBlank(sortBy)) {
            if (desc) {
                //设置降序排序
                page1.setDesc(sortBy);
            } else {
                //设置升序排序
                page1.setAsc(sortBy);
            }
        }
        IPage<TbBrand> iPage = this.page(page1, queryWrapper);
        return new PageResult(iPage.getTotal(), iPage.getRecords());
    }
}
