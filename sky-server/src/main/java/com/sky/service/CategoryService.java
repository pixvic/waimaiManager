package com.sky.service;

import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {

    /**
     * 分页查询分类信息
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult getByPage(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根据类型查询分类信息
     * @param type
     * @return
     */
    List<Category> getByType(Integer type);
}
