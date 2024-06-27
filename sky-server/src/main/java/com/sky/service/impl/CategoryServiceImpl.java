package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageResult getByPage(CategoryPageQueryDTO categoryPageQueryDTO) {
        Category category = new Category();
        category.setName(categoryPageQueryDTO.getName());
        category.setType(categoryPageQueryDTO.getType());
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        Page<Category> page = categoryMapper.getByNameOrType(category);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public List<Category> getByType(Integer type) {
        Category category = new Category();
        category.setType(type);
        List<Category> categoryList = categoryMapper.getByNameOrType(category).getResult();
        return categoryList;
    }
}
