package com.sky.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类ID查询该分类下的所有的套餐
     * @param categoryId
     */
    @Select("select count(*) from setmeal where category_id = #{categoryId}")
    Long getByCategoryId(Long categoryId);
}
