package com.atguigu.spzx.service;

import com.atguigu.spzx.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface CategoryService {
    List<Category> findByParentId(Long parentId);

    void exportData(HttpServletResponse response) throws IOException;
}
