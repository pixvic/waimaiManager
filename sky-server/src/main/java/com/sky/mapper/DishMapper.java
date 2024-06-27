package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DishMapper {

    /**
     * 根据分类ID查询该分类下的所有菜品
     * @param categoryId
     */
    @Select("select count(*) from dish where category_id = #{categoryId}")
    Long getByCategoryId(Long categoryId);
}
