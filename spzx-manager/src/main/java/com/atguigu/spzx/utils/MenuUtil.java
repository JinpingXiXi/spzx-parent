package com.atguigu.spzx.utils;

import com.atguigu.spzx.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单的树节点处理
 */
public class MenuUtil {


    // 将菜单 转为 树节点列表

    public static List<SysMenu> toTreeList(List<SysMenu> allMenusList) {
        ArrayList<SysMenu> treeList = new ArrayList<>();
        allMenusList.forEach(sysMenu -> {

            if (sysMenu.getParentId()==0){//SELF IS PARENT
                //pick only parent menus
                //build its submenu
                buildSubMenu(sysMenu, allMenusList);

                treeList.add(sysMenu);//add this to the tree

            }
        });
        return treeList;
    }

    //使用递归方法组装子菜单
    private static void buildSubMenu(SysMenu parentMenu, List<SysMenu> allList) {
        //iterate allList, check submenu for every sysMenu
        allList.forEach(sysMenu -> {
            if (sysMenu.getParentId() == parentMenu.getId()) {
                //recursion: search next submenu
                buildSubMenu(sysMenu,allList);
                //if this is the 1st time to add sub items, init children
                if (parentMenu.getChildren()==null){
                    parentMenu.setChildren(new ArrayList<>());
                }
                //add this menu as submenu for its parent
                //(children is a list)
                parentMenu.getChildren().add(sysMenu);
            }



        });


    }
}
