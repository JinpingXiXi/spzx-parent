package com.atguigu.spzx.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.spzx.mapper.CategoryMapper;
import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;
import com.atguigu.spzx.service.CategoryService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
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
            if (subItemCount>0)
                item.setHasChildren(true);
            else
                item.setHasChildren(false);

        });

        return list;

    }

    @Override
    public void exportData(HttpServletResponse response) throws IOException {

        //set the return file info
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("分類數據","utf-8");
        response.setHeader("Content-disposition","attachment;filename=" + fileName + ".xlsx");

        //get all records in category -> as list
        List<Category> categoriesList = categoryMapper.selectAll();

        //create a new list of the output results -> write to browser
        ArrayList<CategoryExcelVo> categoryExcelVosList = new ArrayList<>(categoriesList.size());

        //convert category -> categoryExcelVo
        for (Category category : categoriesList) {
            CategoryExcelVo cExcelVo = new CategoryExcelVo();
            BeanUtils.copyProperties(category, cExcelVo);
            categoryExcelVosList.add(cExcelVo);
        }

        //write data to browser
        EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class)
                .sheet("分類數據")
                .doWrite(categoryExcelVosList);
    }
}
