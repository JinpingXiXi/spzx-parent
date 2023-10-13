package com.atguigu.spzx.util;

import com.atguigu.spzx.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuUtil {
    public static List<SysMenu> toTreeList(List<SysMenu> allList) {

        ArrayList<SysMenu> treeList = new ArrayList<>();

        //提取所有的一級菜單 parent_id=0
        allList.forEach(sysMenu -> {
            if (sysMenu.getParentId()==0){
                //是一級菜單（父），組裝子菜單
                buildChildren(sysMenu,allList);
                treeList.add(sysMenu);

            }
        });



        return null;
    }

    private static void buildChildren(SysMenu sysMenu, List<SysMenu> allList) {
        allList.forEach(sysMenu1 -> {

        });
    }
}
