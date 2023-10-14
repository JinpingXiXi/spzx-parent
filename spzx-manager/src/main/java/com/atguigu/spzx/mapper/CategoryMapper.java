package com.atguigu.spzx.mapper;

import com.atguigu.spzx.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> findByParentId(Long parentId);

    int countSubItemsByParentId(Long id);

    List<Category> selectAll();
}
