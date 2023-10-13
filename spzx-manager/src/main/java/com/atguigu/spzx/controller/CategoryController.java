package com.atguigu.spzx.controller;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/admin/product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "根據parentId獲取子節點")
    @GetMapping("/findByParentId/{parentId}")
    public Result<List<Category>> findByParentId(@PathVariable Long parentId){
        List<Category> result = categoryService.findByParentId(parentId);
        return Result.ok(result);
    }

}
