package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品ID查询菜品
     * @param id
     */
    @Select("select * from setmeal_dish where dish_id = #{id}")
    List<SetmealDish> getByDishId(Long id);
}
