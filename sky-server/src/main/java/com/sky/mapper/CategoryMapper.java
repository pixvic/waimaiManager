package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    /**
     * 查询分类信息
     * @param categoryDTO
     * @return
     */
    Page<Category> getByNameOrType(Category categoryDTO);

    /**
     * 新增分类新增分类
     * @param category
     */
    void addCategory(Category category);

    /**
     * 修改分类
     * @param category
     */
    void update(Category category);

    /**
     * 根据ID删除分类
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void delete(Long id);
}
