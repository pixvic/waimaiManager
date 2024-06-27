package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/admin/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    @ApiOperation("分页查询分类信息")
    public Result<PageResult> getByPage(CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("分页查询分类信息：{}", categoryPageQueryDTO);
        PageResult result = categoryService.getByPage(categoryPageQueryDTO);
        return Result.success(result);
    }

    @GetMapping("/list")
    @ApiOperation("根据类型查询分类")
    public Result<List<Category>> getByType(Integer type) {
        log.info("根据类型查询分类信息:{}", type);
        List<Category> category = categoryService.getByType(type);
        return Result.success(category);
    }
}
