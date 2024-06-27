package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageResult getByPage(CategoryPageQueryDTO categoryPageQueryDTO) {
        // 将分类的分页查询类转为分类信息类
        Category category = new Category();
        category.setName(categoryPageQueryDTO.getName());
        category.setType(categoryPageQueryDTO.getType());
        // 进行分页查询
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        Page<Category> page = categoryMapper.getByNameOrType(category);
        // 获取总数与分类信息
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public List<Category> getByType(Integer type) {
        Category category = new Category();
        category.setType(type);
        // 获取分类信息
        List<Category> categoryList = categoryMapper.getByNameOrType(category).getResult();
        return categoryList;
    }

    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        // 设置分类的基本信息
        Category category = Category.builder()
                .createTime(LocalDateTime.now()) // 设置创建时间
                .createUser(BaseContext.getCurrentId()) // 设置创建分类的员工
                .updateTime(LocalDateTime.now()) // 设置更新时间
                .updateUser(BaseContext.getCurrentId()) // 设置执行更新时间的员工
                .status(1) // 设置分类是否启用
                .build();
        // 复制信息
        BeanUtils.copyProperties(categoryDTO, category);
        categoryMapper.addCategory(category);
    }
}
