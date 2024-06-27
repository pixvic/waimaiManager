package com.sky.service;

import com.sky.dto.CategoryDTO;
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

    /**
     * 新增分类
     * @param categoryDTO
     */
    void addCategory(CategoryDTO categoryDTO);

    /**
     * 修改分类
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     * 启用，禁用分类
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
}
