package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.mapper.CategoryMapper;
import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findByParentId(Long parentId) {
        //獲取所有子類別
        List<Category> list = categoryMapper.findByParentId(parentId);
        //遍歷這個集合，查找每個子類別的子類別
        list.forEach( item -> {
            int subItemCount = categoryMapper.countSubItemsByParentId(item.getId());
            if (subItemCount>0) {
                item.setHasChildren(true);
            }else {
                item.setHasChildren(false);
            }
        });
        return list;

    }
}
