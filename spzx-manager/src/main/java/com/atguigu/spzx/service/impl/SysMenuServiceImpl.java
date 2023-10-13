package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.common.AuthContextUtil;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.mapper.SysMenuMapper;
import com.atguigu.spzx.mapper.SysRoleMenuMapper;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.service.SysMenuService;
import com.atguigu.spzx.utils.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-13
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     *
     * @return a tree List<SysMenu>
     */
    @Override
    public List<SysMenu> findTreeList() {
        List<SysMenu> allMenusList = sysMenuMapper.selectAll();
        return MenuUtil.toTreeList(allMenusList);
    }

    @Override
    public void add(SysMenu sysMenu) {
        sysMenuMapper.insert(sysMenu);
    }

    @Override
    public void update(SysMenu sysMenu) {
        sysMenuMapper.update(sysMenu);
    }

    @Override
    public void delete(Long id) {
        //判斷無子菜單才可以刪除
        if (sysMenuMapper.findAllSubMenusById()==0){
            sysMenuMapper.delete(id);
        }else {
            throw new GuiguException(ResultCodeEnum.NODE_ERROR);
        }


    }

    @Override
        public Map<String, Object> findAssignMenuList(Long roleId) {
            //總菜單樹：節點列表 from sys_menu
            List<SysMenu> treeList = this.findTreeList();

            List<Long> menuIdList = sysRoleMenuMapper.selectAssignMenuList(roleId);

            //封装响应给前端的Map
            Map<String, Object> map = new HashMap<>();
            map.put("treeList", treeList);
            map.put("menuIdList", menuIdList);
            return map;

    }

    @Override
    public List<SysMenu> findAccessMenuList() {
        //從線程變量獲取用戶ID
        Long userId = AuthContextUtil.get().getId();

        List<SysMenu> menuList = sysMenuMapper.selectByUserId(userId);

        return MenuUtil.toTreeList(menuList);
    }


}
