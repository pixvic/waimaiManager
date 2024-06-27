package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    /**
     * 查询分类信息
     * @param categoryDTO
     * @return
     */
    Page<Category> getByNameOrType(Category categoryDTO);
}
