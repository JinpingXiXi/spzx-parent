package com.atguigu.spzx.utils;

import com.atguigu.spzx.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单的树节点处理
 */
public class MenuUtil {


    // 将菜单 转为 树节点列表
    public static List<SysMenu> toTreeList(List<SysMenu> allList) {
        //树节点列表
        List<SysMenu> treeList = new ArrayList<>();

        //提取所有的一级菜单 parent_id=0
        allList.forEach(sysMenu -> {
            if(sysMenu.getParentId() == 0){
                //组装一级菜单的子菜单
                buildChildren(sysMenu, allList);

                //添加一级菜单到>树节点列表
                treeList.add(sysMenu);
            }
        });

        //返回组装好的树节点
        return treeList;
    }

    //使用递归方法组装子菜单
    private static void buildChildren(SysMenu parentMenu, List<SysMenu> allList) {
        //遍历所有菜单，查找出parentMenu的所有子菜单
        allList.forEach(sysMenu -> {
            //子菜单parent_id  =  父菜单id
            if(sysMenu.getParentId() == parentMenu.getId()){
                //继续向下组装子菜单
                buildChildren(sysMenu, allList);

                //第一次添加子菜单时，实例化children属性
                if(parentMenu.getChildren() == null){
                    parentMenu.setChildren(new ArrayList<>());
                }
                //添加子菜单到children属性中
                parentMenu.getChildren().add(sysMenu);
            }
        });
    }
}
