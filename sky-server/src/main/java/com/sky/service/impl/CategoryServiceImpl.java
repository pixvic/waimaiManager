package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.CategoryMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealMapper setmealMapper;

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
        Category category = new Category();
        category.setStatus(0);
        // 复制信息
        BeanUtils.copyProperties(categoryDTO, category);
        categoryMapper.addCategory(category);
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        // 设置分类的基本信息
        Category category = new Category();
        // 复制信息
        BeanUtils.copyProperties(categoryDTO, category);
        categoryMapper.update(category);
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        // 设置需要更新的参数
        Category category = Category.builder()
                .id(id) // 设置查询需要的ID
                .status(status) // 设置需要改变的分类状态
                .build();
        categoryMapper.update(category);
    }

    @Override
    public void delete(Long id) {
        // 查询该分类下有没有菜品，有菜品则不允许删除分类
        Long dishCount = dishMapper.getByCategoryId(id);
        if (dishCount > 0) {
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
        }
        // 查询该分类下有没有套餐，有套餐则不允许删除分类
        Long setmealCount = setmealMapper.getByCategoryId(id);
        if (setmealCount > 0) {
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
        }
        // 删除分类
        categoryMapper.delete(id);

    }
}
