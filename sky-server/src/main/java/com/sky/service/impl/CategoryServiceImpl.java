package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageResult getByPage(CategoryPageQueryDTO categoryPageQueryDTO) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(categoryPageQueryDTO.getName());
        categoryDTO.setType(categoryPageQueryDTO.getType());
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        Page<CategoryDTO> page = categoryMapper.getByNameOrType(categoryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
