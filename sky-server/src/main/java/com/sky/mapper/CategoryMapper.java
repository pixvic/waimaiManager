package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    /**
     * 分页查询分类信息
     * @param categoryDTO
     * @return
     */
    Page<CategoryDTO> getByNameOrType(CategoryDTO categoryDTO);
}
