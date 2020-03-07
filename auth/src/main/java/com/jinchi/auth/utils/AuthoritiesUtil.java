package com.jinchi.auth.utils;

import com.jinchi.auth.domain.Menu;
import com.jinchi.auth.domain.Operation;
import com.jinchi.auth.domain.RoleMenuOperation;
import com.jinchi.auth.domain.User;
import com.jinchi.auth.dto.LoginInitialDTO;
import com.jinchi.auth.dto.MenuDTO;
import com.jinchi.auth.dto.SimpleRoleDTO;
import com.jinchi.auth.dto.UserDTO;
import com.jinchi.auth.mapper.DepartmentMapper;
import com.jinchi.auth.mapper.MenuMapper;
import com.jinchi.auth.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author：XudongHu
 * @className:AuthoritiesUtil
 * @description: 权限生成工具类
 * @date:13:08 2019/3/29
 * @Modifer:
 */
@Component
public class AuthoritiesUtil {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    public LoginInitialDTO loginSuccess(Integer userId) {
        LoginInitialDTO loginInitial = userMapper.getLoginInitial(userId);
        UserDTO user = userMapper.find(userId);
        loginInitial.setDeptId(user.getDepartmentId());
        //该用户能使用的所有子菜单
        List<Menu> menuList = userMapper.subMenusByUserId(userId);


        if (menuList == null || menuList.size() == 0) return loginInitial.setMenuList(null);
        //1.子菜单查到子-父链=>
        //2.将所有链中的所有元素取出=>放入Set
        //3.遍历每个菜单,去Set中取出相应的子菜单
        //Set去重 重写MenuDTO的equals和hashCode方法
        Set<MenuDTO> set = new HashSet();
        menuList.forEach(e -> {
            MenuDTO menuDTO = menuMapper.recursiveQueryBySubMenuId(e.getId());
            while (menuDTO != null) {
                set.add(menuDTO);
                menuDTO = elementScatter(menuDTO);
            }
        });

        //将menuList清空防止循环引用
        set.forEach(e->e.getMenuList().clear());
        ArrayList<MenuDTO> listDTO = new ArrayList<>(set);

        operationSetting(listDTO,loginInitial.getRoleList());

        List<MenuDTO> menuTree = new ArrayList<>();
        for (int i = 0; i < listDTO.size(); i++) {
            MenuDTO father = listDTO.get(i);
            if (father.getParent() == null || father.getParent().equals(-1)) menuTree.add(father);
            for (int j = 0; j < listDTO.size(); j++) {
                MenuDTO sub = listDTO.get(j);
                if (father.getMenuId().equals(sub.getParent())) {
                    List<MenuDTO> list = father.getMenuList()==null?father.setMenuList(new ArrayList<>()).getMenuList():father.getMenuList();
                    list.add(sub);
                }
            }

        }

        menuTree.sort(Comparator.comparing(MenuDTO::getMenuId));

        loginInitial.setMenuList(menuTree);

        return loginInitial;
    }

    /**
     * 取出所有元素
     * 打散
     * @param menuDTO
     * @return
     */
    private MenuDTO elementScatter(MenuDTO menuDTO) {
        return menuDTO.getMenuList() == null || menuDTO.getMenuList().size() == 0 ? null : menuDTO.getMenuList().get(0);
    }

    /**
     * 设置角色的操作
     * @param menuDTOs 子菜单
     * @param roleDTOS 角色集合
     * @return
     */
    private void operationSetting(List<MenuDTO> menuDTOs, List<SimpleRoleDTO> roleDTOS){
        //==>操作设置
        List<Operation> listOperations = userMapper.allOperations();
        Map<Integer,Operation> mapOperations = new HashMap<>();
        listOperations.stream().forEach(e->mapOperations.put(e.getId(),e));


        for(MenuDTO menuDTO:menuDTOs){
            Integer menuId = menuDTO.getMenuId();

            List<Operation> operations = new ArrayList<>();
            Set<Integer> setOperationIds = new HashSet<>();
            for(SimpleRoleDTO role:roleDTOS){
                Integer roleId = role.getRoleId();
                List<RoleMenuOperation> roleMenuOperations = userMapper.operationsByRoleIdAndMenuId(roleId, menuId);
                roleMenuOperations.forEach(e->setOperationIds.add(e.getOperationId()));
            }
            setOperationIds.forEach(e->operations.add(mapOperations.getOrDefault(e,null)));
            menuDTO.setOperations(operations);
        }
    }

}
