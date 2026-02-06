package fun.server.controller.sys.power;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.service.TPowerRoleMenuOptionService;
import fun.server.service.TPowerRoleUserService;
import fun.server.service.TRoleService;
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
import java.util.Date;
import java.util.List;

/**
 * 角色管理 相关业务处理
 */
@RestController
@RequestMapping("/s/role")
public class Role {

    private final TRoleService roleService;
    private final TPowerRoleUserService powerRoleUserService;
    private final TPowerRoleMenuOptionService powerRoleMenuOptionService;

    @Autowired
    public Role(TRoleService roleService, TPowerRoleUserService powerRoleUserService, TPowerRoleMenuOptionService powerRoleMenuOptionService) {
        this.roleService = roleService;
        this.powerRoleUserService = powerRoleUserService;
        this.powerRoleMenuOptionService = powerRoleMenuOptionService;
    }

    /**
     * 获取角色信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryRole", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryRole( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        String sortField = jsonParam.getString("sortField");
        String sortOrder = jsonParam.getString("sortOrder");
        String name = jsonParam.getString("name");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray role_Array = new JSONArray();
            TRoleExample roleExample = new TRoleExample();
            TRoleExample.Criteria criteria = roleExample.createCriteria();
            if (name != null && !name.equals("")) {
                criteria.andFNameLike("%" + name + "%");
            }
            roleExample.setOrderByClause(sortOrder != null ? sortField + " " + sortOrder.substring(0, sortOrder.length()-3) : "f_key_id desc");
            PageInfo<TRole> rolePageInfo = roleService.findByExampleMapper(roleExample, (page-1)*results, results);
            List<TRole> role_list = rolePageInfo.getList();
            for (TRole role: role_list) {
                JSONObject role_object = new JSONObject();
                role_object.put("key", ParamTools.getEnParam(role.getfKeyId().toString()));
                if(dataall ==1 ){
                    role_object.put("f_name", role.getfName());
                }else{
                    role_object.put("f_name", "*****");
                }
                role_object.put("f_state", role.getfState());
                role_Array.add(role_object);
            }
            // 返回值
            object.put("list", role_Array);
            object.put("total", rolePageInfo.getTotal()); // 总行数
            object.put("page", rolePageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取角色信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryRoleSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryRoleSelect( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray role_Array = new JSONArray();
            TRoleExample roleExample = new TRoleExample();
            TRoleExample.Criteria criteria = roleExample.createCriteria();
            criteria.andFStateEqualTo(1);
            if (search != null && !search.equals("")) {
              criteria.andFNameLike("%" + search + "%");
            }
            List<TRole> role_list = roleService.findByExample(roleExample);
            for (TRole role: role_list) {
                JSONObject role_object = new JSONObject();
                role_object.put("id", ParamTools.getEnParam(role.getfKeyId().toString()));
                role_object.put("text", role.getfName());
                role_Array.add(role_object);
            }
            // 返回值
            object.put("list", role_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取角色信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryRoleInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryRoleInfo( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
          id = id == null ? "0" : ParamTools.getdeParam(id);
          long key = Long.parseLong(id);
            // 获取数据库记录
            JSONObject role_object = new JSONObject();
            TRole role = roleService.findById(key);
            role_object.put("key", ParamTools.getEnParam(role.getfKeyId().toString()));
            role_object.put("f_name", role.getfName());
            role_object.put("f_state", role.getfState());
            JSONArray menuOption_Array = new JSONArray();
            TPowerRoleMenuOptionExample powerRoleMenuOptionExample = new TPowerRoleMenuOptionExample();
            powerRoleMenuOptionExample.or().andFRoleIdEqualTo(key);
            List<TPowerRoleMenuOption> list = powerRoleMenuOptionService.findByExample(powerRoleMenuOptionExample);
            for (TPowerRoleMenuOption powerRoleMenuOption: list) {
                menuOption_Array.add(ParamTools.getEnParam(powerRoleMenuOption.getfMenuOptionId().toString()));
            }
            role_object.put("nodes", menuOption_Array);
            // 返回值
            object.put("info", role_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加角色信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("添加角色信息")
    @ResponseBody
    @RequestMapping(value = "/addRole", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addRole( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String f_name = jsonParam.getString("f_name");
        String nodes = jsonParam.getString("nodes");
        try {
            String uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request)); // 当前登录用户ID
            // 新建角色信息
            TRole role = new TRole();
            role.setfCId(Long.parseLong(uid));
            role.setfName(f_name);
            role.setfState(0);
            roleService.save(role);
            // 新建权限信息
            if (nodes != null && !nodes.equals("")) {
                //增加新选的
                JSONArray powerNodes_Array = JSONArray.parseArray(nodes);
                for (Object nodeId: powerNodes_Array) {
                    Long fMenuOptionId = Long.parseLong(ParamTools.getdeParam(nodeId.toString()));
                    TPowerRoleMenuOptionExample powerRoleMenuOptionExample = new TPowerRoleMenuOptionExample();
                    powerRoleMenuOptionExample.or()
                            .andFRoleIdEqualTo(role.getfKeyId())
                            .andFMenuOptionIdEqualTo(fMenuOptionId);
                    List<TPowerRoleMenuOption> list = powerRoleMenuOptionService.findByExample(powerRoleMenuOptionExample);
                    if (list.size() <= 0) {
                        TPowerRoleMenuOption powerRoleMenuOption = new TPowerRoleMenuOption();
                        powerRoleMenuOption.setfCId(Long.parseLong(uid));
                        powerRoleMenuOption.setfRoleId(role.getfKeyId());
                        powerRoleMenuOption.setfMenuOptionId(fMenuOptionId);
                        powerRoleMenuOptionService.save(powerRoleMenuOption);
                    }
                }
            }
            // 返回值
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 修改角色信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("修改角色信息")
    @ResponseBody
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateRole( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String f_key_id = jsonParam.getString("f_key_id");
        String f_name = jsonParam.getString("f_name");
        String nodes = jsonParam.getString("nodes");
        try {
            String uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request)); // 当前登录用户ID
            f_key_id = f_key_id == null ? "0" : ParamTools.getdeParam(f_key_id);
            Long key = Long.parseLong(f_key_id);
            // 更新角色信息
            TRole role = new TRole();
            role.setfKeyId(key);
            role.setfUId(Long.parseLong(uid));
            role.setfUDate(new Date());
            role.setfName(f_name);
            roleService.update(role);
            // 更新权限信息
            if (nodes != null) {
              if (nodes.equals("")) {
                //删除全部
                TPowerRoleMenuOptionExample powerRoleMenuOptionExample = new TPowerRoleMenuOptionExample();
                powerRoleMenuOptionExample.or().andFRoleIdEqualTo(key);
                powerRoleMenuOptionService.deleteByByExample(powerRoleMenuOptionExample);
              } else {
                  //增加新选的
                  JSONArray powerNodes_Array = JSONArray.parseArray(nodes);
                  List<Long> tempTreeID = new ArrayList<>();
                  for (Object nodeId: powerNodes_Array) {
                      Long fMenuOptionId = Long.parseLong(ParamTools.getdeParam(nodeId.toString()));
                      TPowerRoleMenuOptionExample powerRoleMenuOptionExample = new TPowerRoleMenuOptionExample();
                      powerRoleMenuOptionExample.or()
                              .andFRoleIdEqualTo(key)
                              .andFMenuOptionIdEqualTo(fMenuOptionId);
                      List<TPowerRoleMenuOption> list = powerRoleMenuOptionService.findByExample(powerRoleMenuOptionExample);
                      if (list.size() <= 0) {
                          TPowerRoleMenuOption powerRoleMenuOption = new TPowerRoleMenuOption();
                          powerRoleMenuOption.setfCId(Long.parseLong(uid));
                          powerRoleMenuOption.setfRoleId(key);
                          powerRoleMenuOption.setfMenuOptionId(fMenuOptionId);
                          powerRoleMenuOptionService.save(powerRoleMenuOption);
                      }
                      tempTreeID.add(fMenuOptionId);
                  }
                  //删除多余的
                  TPowerRoleMenuOptionExample powerRoleMenuOptionExample = new TPowerRoleMenuOptionExample();
                  if (tempTreeID.size() > 0) {
                      powerRoleMenuOptionExample.or()
                              .andFRoleIdEqualTo(key)
                              .andFMenuOptionIdNotIn(tempTreeID);
                  } else {
                      powerRoleMenuOptionExample.or().andFRoleIdEqualTo(key);
                  }
                  powerRoleMenuOptionService.deleteByByExample(powerRoleMenuOptionExample);
              }
            }
            // 返回值
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 删除角色信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("删除角色信息")
    @ResponseBody
    @RequestMapping(value = "/delRole", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delRole( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            Long key = Long.parseLong(id);
            // 判断是否存在用户关联记录
            TPowerRoleUserExample powerRoleUserExample = new TPowerRoleUserExample();
            powerRoleUserExample.or().andFRoleIdEqualTo(key);
            List<TPowerRoleUser> powerRoleUser_list = powerRoleUserService.findByExample(powerRoleUserExample);
            if (powerRoleUser_list.size() <= 0) {
                // 删除角色功能权限记录
                TPowerRoleMenuOptionExample powerRoleMenuOptionExample = new TPowerRoleMenuOptionExample();
                powerRoleMenuOptionExample.or().andFRoleIdEqualTo(key);
                powerRoleMenuOptionService.deleteByByExample(powerRoleMenuOptionExample);
                // 删除角色记录
                roleService.deleteById(key);
                // 返回值
                object.put("result", "success");
            } else {
                object.put("result", "error");
                object.put("error", "角色已被用户关联不能删除");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 变更角色信息状态
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("变更角色信息状态")
    @ResponseBody
    @RequestMapping(value = "/stateRole", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stateRole( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        int state = jsonParam.getInteger("state");
        try {
            String uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request)); // 当前登录用户ID
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            Long key = Long.parseLong(id);
            state = state == 1 ? 0 : 1;
            TRole role = new TRole();
            role.setfKeyId(key);
            role.setfUId(Long.parseLong(uid));
            role.setfUDate(new Date());
            role.setfState(state);
            roleService.update(role);
            // 返回值
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
}