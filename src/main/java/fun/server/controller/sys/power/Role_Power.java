package fun.server.controller.sys.power;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fun.server.model.*;
import fun.server.service.*;
import fun.tools.ParamTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * 角色管理-右侧权限树 相关业务处理
 */
@RestController
@RequestMapping("/s/role")
public class Role_Power {

    private final TUserService userService;
    private final TMenuTypeService menuTypeService;
    private final TMenuOptionsService menuOptionsService;
    private final TPowerRoleMenuOptionService powerRoleMenuOptionService;
    private final TPowerRoleUserService powerRoleUserService;

    @Autowired
    public Role_Power(
      TUserService userService,
      TMenuTypeService menuTypeService,
      TMenuOptionsService menuOptionsService,
      TPowerRoleMenuOptionService powerRoleMenuOptionService,
      TPowerRoleUserService powerRoleUserService) {
        this.userService = userService;
        this.menuTypeService = menuTypeService;
        this.menuOptionsService = menuOptionsService;
        this.powerRoleMenuOptionService = powerRoleMenuOptionService;
        this.powerRoleUserService = powerRoleUserService;
    }

    /**
     * 获取功能菜单
     * @return 响应结果
     */
    @ResponseBody
    @RequestMapping(value = "/powerTree", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String powerTree( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        try {
            String uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request)); // 当前登录用户ID
            TUser user = userService.findById(Long.parseLong(uid));
            // 读取用户权限
            JSONArray menu_List = new JSONArray();
            List<Long> roleMenuOption_list = null; // 角色菜单项
            TMenuTypeExample menuTypeExample = new TMenuTypeExample();
            if (user.getfIsAdmin() == 1) { // 超级管理员
                menuTypeExample.or().andFStateEqualTo(1);
                menuTypeExample.setOrderByClause("f_order ASC");
            } else { // 普通用户
                // 获取用户角色
                TPowerRoleUserExample powerRoleUserExample = new TPowerRoleUserExample();
                powerRoleUserExample.or().andFUserIdEqualTo(user.getfKeyId());
                List<TPowerRoleUser> powerRoleUser_list = powerRoleUserService.findByExample(powerRoleUserExample);
                List<Long> role_list = new ArrayList<>();
                for (TPowerRoleUser powerRoleUser: powerRoleUser_list) {
                    role_list.add(powerRoleUser.getfRoleId());
                }
                if(role_list.size() > 0){
                    // 获取角色菜单项
                    TPowerRoleMenuOptionExample powerRoleMenuOptionExample = new TPowerRoleMenuOptionExample();
                    powerRoleMenuOptionExample.or().andFRoleIdIn(role_list);
                    List<TPowerRoleMenuOption> powerRoleMenuOption_list = powerRoleMenuOptionService.findByExample(powerRoleMenuOptionExample);
                    roleMenuOption_list = new ArrayList<>();
                    for (TPowerRoleMenuOption powerRoleMenuOption: powerRoleMenuOption_list) {
                        roleMenuOption_list.add(powerRoleMenuOption.getfMenuOptionId());
                    }
                    // 获取角色菜单类型
                    TMenuOptionsExample menuOptionsExample = new TMenuOptionsExample();
                    menuOptionsExample.or().andFKeyIdIn(roleMenuOption_list);
                    List<TMenuOptions> menuOptions_list = menuOptionsService.findByExample(menuOptionsExample);
                    List<Long> menuType_list = new ArrayList<>();
                    for (TMenuOptions menuOptions: menuOptions_list) {
                        menuType_list.add(menuOptions.getfTypeId());
                    }
                    // 菜单类型去重
                    LinkedHashSet<Long> set = new LinkedHashSet<>(menuType_list.size());
                    set.addAll(menuType_list);
                    menuType_list.clear();
                    menuType_list.addAll(set);
                    // 设置查询条件
                    menuTypeExample.or()
                            .andFStateEqualTo(1)
                            .andFKeyIdIn(menuType_list);
                    menuTypeExample.setOrderByClause("f_order ASC");
                }

            }
            List<TMenuType> menu_type_list = menuTypeService.findByExample(menuTypeExample);
            for (TMenuType menuType: menu_type_list) {
                JSONObject menuType_object = new JSONObject();
                menuType_object.put("key", ParamTools.getEnParam(menuType.getfKeyId().toString()));
                menuType_object.put("title", menuType.getfName());
                menuType_object.put("icon", menuType.getfIcon());
                JSONArray menu_list = recursionMenuOptions(menuType.getfKeyId(), 0L, roleMenuOption_list);
                if (menu_list.size() > 0) {
                    menuType_object.put("children", menu_list);
                }
                menu_List.add(menuType_object);
            }
            // 返回值
            object.put("menu", menu_List);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取功能菜单(递归)
     * @param typeid 菜单类型
     * @param pid 父节点
     * @return 响应结果
     * @throws Exception 顶层异常类
     */
    private JSONArray recursionMenuOptions(Long typeid, Long pid, List<Long> menuOption_list)
            throws Exception {
        JSONArray objectList = new JSONArray();
        TMenuOptionsExample menuOptionsExample = new TMenuOptionsExample();
        if (menuOption_list == null) {
            menuOptionsExample.or()
                    .andFTypeIdEqualTo(typeid)
                    .andFPIdEqualTo(pid)
                    .andFStateEqualTo(1);
        } else {
            menuOptionsExample.or()
                    .andFTypeIdEqualTo(typeid)
                    .andFPIdEqualTo(pid)
                    .andFStateEqualTo(1)
                    .andFKeyIdIn(menuOption_list);
        }
        menuOptionsExample.setOrderByClause("f_order ASC");
        List<TMenuOptions> list = menuOptionsService.findByExample(menuOptionsExample);
        for (TMenuOptions menuOptions : list) {
            JSONObject object = new JSONObject();
            object.put("key", ParamTools.getEnParam(menuOptions.getfKeyId().toString()));
            object.put("no", menuOptions.getfNo());
            object.put("title", menuOptions.getfName());
            object.put("icon", menuOptions.getfIcon());
            object.put("menuType", menuOptions.getfMenuType());
            if (menuOptions.getfIsLeaf() == 0) {
                object.put("children", recursionMenuOptions(typeid, menuOptions.getfKeyId(), menuOption_list));
            } else {
                object.put("url", menuOptions.getfUrl());
            }
            objectList.add(object);
        }
        return objectList;
    }

    /**
     * 获取权限树
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/showPowerTree", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String showPowerTree( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONArray menu_List = new JSONArray();
        try {
            TMenuTypeExample menuTypeExample = new TMenuTypeExample();
            menuTypeExample.or().andFStateEqualTo(1);
            menuTypeExample.setOrderByClause("f_order asc");
            List<TMenuType> menuType_list = menuTypeService.findByExample(menuTypeExample);
            for (TMenuType menuType: menuType_list) {
                JSONObject menuObject = new JSONObject();
                String typeId = ParamTools.getEnParam(menuType.getfKeyId().toString());
                menuObject.put("id", typeId);
                menuObject.put("pId", 0);
                menuObject.put("name", menuType.getfName());
                menu_List.add(menuObject);
                // 菜单项
                TMenuOptionsExample menuOptionsExample = new TMenuOptionsExample();
                menuOptionsExample.or().andFStateEqualTo(1).andFTypeIdEqualTo(menuType.getfKeyId());
                menuOptionsExample.setOrderByClause("f_order asc");
                List<TMenuOptions> menuOptions_list = menuOptionsService.findByExample(menuOptionsExample);
                for (TMenuOptions menuOptions: menuOptions_list) {
                    menuObject = new JSONObject();
                    menuObject.put("id", ParamTools.getEnParam(menuOptions.getfKeyId().toString()));
                    menuObject.put("pId", typeId);
                    menuObject.put("name", menuOptions.getfName());
                    menu_List.add(menuObject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menu_List.toJSONString();
    }
}