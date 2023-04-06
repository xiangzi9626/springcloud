package com.example.menu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.commons.entity.SystemMenu;
import com.example.menu.mapper.SystemMenuMapper;
import com.example.menu.service.ISystemMenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author 丁祥
 * @since 2022-10-06
 */
@Service
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuMapper, SystemMenu> implements ISystemMenuService {
    @Override
    public List<SystemMenu> buildTreeMenu(List<SystemMenu> sysMenuList) {
        List<SystemMenu> resultMenuList = new ArrayList<>();
        for (SystemMenu sysMenu : sysMenuList) {

            // 寻找子节点
            for (SystemMenu e : sysMenuList) {
                if (e.getPid().equals(sysMenu.getId())) {
                    sysMenu.getChildren().add(e);
                }
            }
            if (sysMenu.getPid() == 0) {
                resultMenuList.add(sysMenu);
            }
        }
        return resultMenuList;
    }
}
