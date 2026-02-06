package fun.server.controller.sys.permission;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.server.model.*;
import fun.server.service.*;
import fun.tools.ParamTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户组管理 相关业务处理
 */
@RestController
@RequestMapping("/s/usergroups")
public class UserGroups {

    private final TUserGroupsService tUserGroupsService;

    private final TUserPermissionGRelationshipService tUserPermissionGRelationshipService;
    private final TUserGroupsRelationshipService tUserGroupsRelationshipService;

    private final TPermissionGRoupService tPermissionGRoupService;
    private final TLogActionService logActionService;
    private final TUserService tUserService;


    public UserGroups(TUserGroupsService tUserGroupsService, TUserPermissionGRelationshipService tUserPermissionGRelationshipService, TUserGroupsRelationshipService tUserGroupsRelationshipService, TPermissionGRoupService tPermissionGRoupService, TLogActionService logActionService, TUserService tUserService) {
        this.tUserGroupsService = tUserGroupsService;
        this.tUserPermissionGRelationshipService = tUserPermissionGRelationshipService;
        this.tUserGroupsRelationshipService = tUserGroupsRelationshipService;
        this.tPermissionGRoupService = tPermissionGRoupService;
        this.logActionService = logActionService;
        this.tUserService = tUserService;
    }


    /**
     * 获取用户组信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryusergroups", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryusergroups(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        try {
            // 获取数据库记录
            JSONArray usergroups_Array = new JSONArray();
            // 查询条件
            TUsergroupsExample TUsergroupsExample = new TUsergroupsExample();
            TUsergroupsExample.Criteria criteria = TUsergroupsExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }
            // 排序
            String orderSql = "";
            for (Object order: order_JA) {
                JSONObject order_Object = (JSONObject)order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                TUsergroupsExample.setOrderByClause(orderSql.substring(1));
            } else {
                TUsergroupsExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TUsergroups> usergroupsPageInfo = tUserGroupsService.findByExampleMapper(TUsergroupsExample, (page - 1) * results, results);
            List<TUsergroups> usergroups_list = usergroupsPageInfo.getList();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (TUsergroups usergroups : usergroups_list) {
                JSONObject usergroups_object = new JSONObject();
                usergroups_object.put("key", ParamTools.getEnParam(usergroups.getFkeyid().toString()));
                usergroups_object.put("FName", usergroups.getFname() == null ? "" : usergroups.getFname());
                usergroups_object.put("FNote", usergroups.getFnote() == null ? "" : usergroups.getFnote());
                usergroups_object.put("FCID", usergroups.getFcid());
                usergroups_object.put("FUID", usergroups.getFuid());
                usergroups_object.put("FCDATE", usergroups.getFcdate() == null ? "" : sdf.format(usergroups.getFcdate()));
                usergroups_object.put("FUDATE", usergroups.getFudate() == null ? "" : sdf.format(usergroups.getFudate()));
                usergroups_object.put("FState", usergroups.getFstate());
                TUserpermissiongrelationshipExample tUserpermissiongrelationshipExample = new TUserpermissiongrelationshipExample();
                tUserpermissiongrelationshipExample.createCriteria().andFuseridEqualTo((usergroups.getFkeyid()));
                List<TUserpermissiongrelationship> userpermissiongrelationshipList = tUserPermissionGRelationshipService.findByExample(tUserpermissiongrelationshipExample);
                if(userpermissiongrelationshipList.size()>0){
                    JSONArray tUserpermissiongrelationship_Array = new JSONArray();
                    for (TUserpermissiongrelationship tUserpermissiongrelationship : userpermissiongrelationshipList){
                        TPermissiongroup tPermissiongroup = tPermissionGRoupService.findById(tUserpermissiongrelationship.getFpgid());
                        tUserpermissiongrelationship_Array.add(tPermissiongroup.getFname());
                    }
                    usergroups_object.put("FQXName", tUserpermissiongrelationship_Array.toString());
                }else{
                    usergroups_object.put("FQXName", "未设置");
                }
                usergroups_Array.add(usergroups_object);
            }
            // 返回值
            object.put("list", usergroups_Array);
            object.put("total", usergroupsPageInfo.getTotal()); // 总行数
            object.put("page", usergroupsPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取用户组信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatausergroupsSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatausergroupsSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray usergroups_Array = new JSONArray();
            TUsergroupsExample usergroupsExample = new TUsergroupsExample();
            TUsergroupsExample.Criteria criteria = usergroupsExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            usergroupsExample.setOrderByClause("fname asc");
            List<TUsergroups> usergroups_list = tUserGroupsService.findByExample(usergroupsExample);
            for (TUsergroups usergroups : usergroups_list) {
                JSONObject usergroups_object = new JSONObject();
                usergroups_object.put("id", ParamTools.getEnParam(usergroups.getFkeyid().toString()));
                usergroups_object.put("text", usergroups.getFname());
                usergroups_Array.add(usergroups_object);
            }
            // 返回值

            object.put("list", usergroups_Array);
            object.put("result", "success");
            object.put("results", usergroups_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取用户组信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryusergroupsInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryusergroupsInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询用户组信息
            TUsergroups usergroups = tUserGroupsService.findById(key);
            JSONObject usergroups_object = new JSONObject();
            usergroups_object.put("key", ParamTools.getEnParam(usergroups.getFkeyid().toString()));
            usergroups_object.put("FName", usergroups.getFname() == null ? "" : usergroups.getFname());
            usergroups_object.put("FNote", usergroups.getFnote() == null ? "" : usergroups.getFnote());
            usergroups_object.put("FCID", usergroups.getFcid());
            usergroups_object.put("FUID", usergroups.getFuid());
            usergroups_object.put("FCDATE", usergroups.getFcdate());
            usergroups_object.put("FUDATE", usergroups.getFudate());
            usergroups_object.put("FState", usergroups.getFstate());
            JSONArray role_Array = new JSONArray();
            TUsergroupsrelationshipExample tUsergroupsrelationshipExample = new TUsergroupsrelationshipExample();
            tUsergroupsrelationshipExample.createCriteria().andFugidEqualTo(usergroups.getFkeyid());
            List<TUsergroupsrelationship> usergroupsrelationshipList = tUserGroupsRelationshipService.findByExample(tUsergroupsrelationshipExample);
            if(usergroupsrelationshipList.size()>0){
                for (TUsergroupsrelationship tUsergroupsrelationship : usergroupsrelationshipList){
                    JSONObject roleObject = new JSONObject();
                    roleObject.put("id", ParamTools.getEnParam(tUsergroupsrelationship.getFkeyid().toString()));
                    TUser tUser = tUserService.findById(tUsergroupsrelationship.getFuserid());
                    roleObject.put("name", tUser == null ? "" : tUser.getfName());
                    role_Array.add(roleObject);
                }
            }
            JSONArray qxz_Array = new JSONArray();
            TUserpermissiongrelationshipExample tUserpermissiongrelationshipExample = new TUserpermissiongrelationshipExample();
            tUserpermissiongrelationshipExample.createCriteria().andFuseridEqualTo(usergroups.getFkeyid());
            List<TUserpermissiongrelationship> userpermissiongrelationshipList = tUserPermissionGRelationshipService.findByExample(tUserpermissiongrelationshipExample);
            if(userpermissiongrelationshipList.size() > 0){
                for (TUserpermissiongrelationship tUserpermissiongrelationship : userpermissiongrelationshipList){
                    JSONObject roleObject = new JSONObject();
                    TPermissiongroup tPermissiongroup = tPermissionGRoupService.findById(tUserpermissiongrelationship.getFpgid());
                    roleObject.put("id", ParamTools.getEnParam(tPermissiongroup.getFkeyid().toString()));
                    roleObject.put("name", tPermissiongroup == null ? "" : tPermissiongroup.getFname());
                    qxz_Array.add(roleObject);
                }
            }
            object.put("userz", role_Array);
            object.put("qxz", qxz_Array);
            // 返回值
            object.put("info", usergroups_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加用户组信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("添加用户组信息")
    @ResponseBody
    @RequestMapping(value = "/addusergroups", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addusergroups(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String Fname = jsonParam.getString("FName");
        String FNote = jsonParam.getString("FNote");

        try {
            if (repeaTUsergroups(0L, Fname, "1") == 0) {
                String CookiesLoginusergroupsID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginusergroupsID != null && !CookiesLoginusergroupsID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginusergroupsID);
                }
                // 新建用户组信息
                TUsergroups usergroups = new TUsergroups();
                usergroups.setFname(Fname);
                usergroups.setFnote(FNote);
                usergroups.setFcid(Long.parseLong(uid));
                usergroups.setFcdate(new Date());
                tUserGroupsService.save(usergroups);

                TUser tUser = tUserService.findById(Long.parseLong(uid));
                TLogAction logAction = new TLogAction();
                logAction.setfUserId(tUser.getfKeyId());
                logAction.setfUserName(tUser.getfName());
                logAction.setfType(3);
                logAction.setfPath("usergroups/addusergroups{" + usergroups.getFkeyid() + "}");
                logAction.setfUserType(1);

                logAction.setfMemo("新增用户组【"+Fname+"】信息。");
                logActionService.save(logAction);
                // 返回值
                object.put("result", "success");
            } else {
                // 返回值
                object.put("result", "fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 修改用户组信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("修改用户组信息")
    @ResponseBody
    @RequestMapping(value = "/updateusergroups", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateusergroups(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FName = jsonParam.getString("FName");
        String FNote = jsonParam.getString("FNote");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            if (repeaTUsergroups(key, FName, "2") == 0) {
                String CookiesLoginusergroupsID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginusergroupsID != null && !CookiesLoginusergroupsID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginusergroupsID);
                }
                TUsergroups usergroups1 = tUserGroupsService.findById(key);

                // 更新用户组信息
                TUsergroups usergroups = new TUsergroups();
                usergroups.setFkeyid(key);
                usergroups.setFname(FName);
                usergroups.setFnote(FNote);
                usergroups.setFuid(Long.parseLong(uid));
                usergroups.setFudate(new Date());


                TUser tUser = tUserService.findById(Long.parseLong(uid));
                TLogAction logAction = new TLogAction();
                logAction.setfUserId(tUser.getfKeyId());
                logAction.setfUserName(tUser.getfName());
                logAction.setfType(3);
                logAction.setfPath("usergroups/updateusergroups{" + key + "}");
                logAction.setfUserType(1);

                logAction.setfMemo("用户组名称【"+usergroups1.getFname()+"】修改为【"+FName+"】。");
                logActionService.save(logAction);
                tUserGroupsService.update(usergroups);


                // 返回值
                object.put("result", "success");
            } else {
                // 返回值
                object.put("result", "fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 删除用户组信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("删除用户组信息")
    @ResponseBody
    @RequestMapping(value = "/delusergroups", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delusergroups(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLoginusergroupsID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginusergroupsID != null && !CookiesLoginusergroupsID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            TUsergroups usergroups = tUserGroupsService.findById(Long.parseLong(id));

            TUsergroupsrelationshipExample tUsergroupsrelationshipExample = new TUsergroupsrelationshipExample();
            tUsergroupsrelationshipExample.createCriteria().andFugidEqualTo(Long.parseLong(id));
            tUserGroupsRelationshipService.deleteByByExample(tUsergroupsrelationshipExample);
            tUserGroupsService.deleteById(Long.parseLong(id));

            TUser tUser = tUserService.findById(Long.parseLong(uid));
            TLogAction logAction = new TLogAction();
            logAction.setfUserId(tUser.getfKeyId());
            logAction.setfUserName(tUser.getfName());
            logAction.setfType(3);
            logAction.setfPath("usergroups/delusergroups{" + id + "}");
            logAction.setfUserType(1);

            logAction.setfMemo("删除用户组【"+usergroups.getFname()+"】信息。");
            logActionService.save(logAction);

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
     * 变更用户组信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("变更用户组状态")
    @ResponseBody
    @RequestMapping(value = "/stateusergroups", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stateusergroups(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLoginusergroupsID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginusergroupsID != null && !CookiesLoginusergroupsID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
//            System.out.println("id:" + id);
            TUsergroups usergroups1 = tUserGroupsService.findById(Long.parseLong(id));
            TUsergroups usergroups = new TUsergroups();
            usergroups.setFkeyid(Long.parseLong(id));
            usergroups.setFuid(Long.parseLong(uid));
            usergroups.setFudate(new Date());
            usergroups.setFstate(Integer.valueOf(state));
            tUserGroupsService.update(usergroups);
            String statestr = "";
            if(Integer.valueOf(state) ==0){
                statestr = "禁用";
            }else{
                statestr = "启用";
            }

            TUser tUser = tUserService.findById(Long.parseLong(uid));
            TLogAction logAction = new TLogAction();
            logAction.setfUserId(tUser.getfKeyId());
            logAction.setfUserName(tUser.getfName());
            logAction.setfType(3);
            logAction.setfPath("usergroups/stateusergroups{" + id + "}");
            logAction.setfUserType(1);

            logAction.setfMemo("用户组【"+usergroups1.getFname()+"】状态变更为【"+statestr+"】。");
            logActionService.save(logAction);

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
     * 验证用户组是否存在
     */
    private int repeaTUsergroups(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TUsergroupsExample usergroupsExample = new TUsergroupsExample();
            TUsergroupsExample.Criteria criteria = usergroupsExample.createCriteria();
            if (ftype.equals("2")) { // 修改
                if (id != null) {
//                    criteria.andFkeyidEqualTo((id));
                    criteria.andFkeyidNotEqualTo(id);
                }
                if (name != null && !name.equals("")) {
                    criteria.andFnameEqualTo(name);
                }
            } else { // 新增
                if (name != null && !name.equals("")) {
                    criteria.andFnameEqualTo(name);
                }
            }
            List<TUsergroups> rojectTypeList = tUserGroupsService.findByExample(usergroupsExample);
            if (rojectTypeList.size() == 0) {
                code = 0;
            } else {
                code = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }



    /**
     * 添加用户组用户信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("添加用户组信息")
    @ResponseBody
    @RequestMapping(value = "/addusergroupsuser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addusergroupsuser(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String roles = jsonParam.getString("userzid");
        String FUGID = jsonParam.getString("FUGID");//用户组id
        try {
            String CookiesLoginusergroupsID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginusergroupsID != null && !CookiesLoginusergroupsID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            object.put("FUGID", FUGID);
            FUGID = FUGID == null ? "0" : ParamTools.getdeParam(FUGID);
            TUsergroups tUsergroups = tUserGroupsService.findById(Long.parseLong(FUGID));
            if(tUsergroups!=null) {
                if (roles != null && !roles.equals("")) {
                    //增加新选的
                    JSONArray powerRoles_Array = JSONArray.parseArray(roles);
                    for (Object userid: powerRoles_Array) {
                        Long fuserId = Long.parseLong(ParamTools.getdeParam(userid.toString()));
                        TUsergroupsrelationshipExample tUsergroupsrelationshipExample = new TUsergroupsrelationshipExample();
                        tUsergroupsrelationshipExample.createCriteria().andFugidEqualTo(Long.parseLong(FUGID)).andFuseridEqualTo(fuserId);
                        List<TUsergroupsrelationship> usergroupsrelationshipList = tUserGroupsRelationshipService.findByExample(tUsergroupsrelationshipExample);
                        if(usergroupsrelationshipList.size() == 0){
                            TUsergroupsrelationship tUsergroupsrelationship = new TUsergroupsrelationship();
                            tUsergroupsrelationship.setFugid(Long.parseLong(FUGID));
                            tUsergroupsrelationship.setFuserid(fuserId);
                            tUsergroupsrelationship.setFcid(Long.parseLong(uid));
                            tUsergroupsrelationship.setFcdate(new Date());
                            tUserGroupsRelationshipService.save(tUsergroupsrelationship);
                        }
                    }
                    // 返回值
                    object.put("result", "success");
                }
            }else{
                object.put("result", "error");
                object.put("error", "未获取到用户组信息，请刷新后重试！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 设置权限组
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("添加权限组信息")
    @ResponseBody
    @RequestMapping(value = "/addtuserpermissionGroupship", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addtuserpermissionGroupship(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FPGIDS = jsonParam.getString("FPGIDS");//权限组
        String FUserGroupID = jsonParam.getString("FUserGroupID");//权限组id

        try {
            String CookiesLogintpermissionGroupID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintpermissionGroupID != null && !CookiesLogintpermissionGroupID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            FUserGroupID = FUserGroupID == null ? "0" : ParamTools.getdeParam(FUserGroupID);
            if (FPGIDS != null ) {
                if(FPGIDS.equals("")){
                    TUserpermissiongrelationshipExample tUserpermissiongrelationshipExample = new TUserpermissiongrelationshipExample();
                    tUserpermissiongrelationshipExample.createCriteria().andFuseridEqualTo(Long.parseLong(FUserGroupID));
                    tUserPermissionGRelationshipService.deleteByByExample(tUserpermissiongrelationshipExample);
                }else{
                    JSONArray powermenID_Array = JSONArray.parseArray(FPGIDS);
                    String FPerm = "";
                    List<Long> tempmenID = new ArrayList<>();
                    for (Object m : powermenID_Array) {
                        FPerm = m == null ? "0" : ParamTools.getdeParam(m.toString());
                        TUserpermissiongrelationshipExample tUserpermissiongrelationshipExample = new TUserpermissiongrelationshipExample();
                        tUserpermissiongrelationshipExample.createCriteria().andFuseridEqualTo(Long.parseLong(FUserGroupID)).andFpgidEqualTo(Long.parseLong(FPerm));
                        List<TUserpermissiongrelationship> userpermissiongrelationshipList = tUserPermissionGRelationshipService.findByExample(tUserpermissiongrelationshipExample);
                        if(userpermissiongrelationshipList.size() == 0) {
                            TUserpermissiongrelationship  tUserpermissiongrelationship = new TUserpermissiongrelationship();
                            tUserpermissiongrelationship.setFpgid(Long.parseLong(FPerm));
                            tUserpermissiongrelationship.setFuserid(Long.parseLong(FUserGroupID));
                            tUserpermissiongrelationship.setFcdate(new Date());
                            tUserpermissiongrelationship.setFcid(Long.parseLong(uid));
                            tUserPermissionGRelationshipService.save(tUserpermissiongrelationship);
                        }
                        tempmenID.add(Long.parseLong(FPerm));
                    }
                    //删除多余的
                    TUserpermissiongrelationshipExample tUserpermissiongrelationshipExample = new TUserpermissiongrelationshipExample();
                    if (tempmenID.size() > 0) {
                        tUserpermissiongrelationshipExample.or()
                                .andFuseridEqualTo(Long.parseLong(FUserGroupID))
                                .andFpgidNotIn(tempmenID);
                    } else {
                        tUserpermissiongrelationshipExample.or().andFuseridEqualTo(Long.parseLong(FUserGroupID));
                    }
                    tUserPermissionGRelationshipService.deleteByByExample(tUserpermissiongrelationshipExample);
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
     * 清空用户组的权限组
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("添加权限组信息")
    @ResponseBody
    @RequestMapping(value = "/deltuserpermissionGroupship", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deltuserpermissionGroupship(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FUserGroupID = jsonParam.getString("FUserGroupID");//权限组id
        try {
            FUserGroupID = FUserGroupID == null ? "0" : ParamTools.getdeParam(FUserGroupID);
//            System.out.println(FUserGroupID);
            TUserpermissiongrelationshipExample tUserpermissiongrelationshipExample = new TUserpermissiongrelationshipExample();
            tUserpermissiongrelationshipExample.createCriteria().andFuseridEqualTo(Long.parseLong(FUserGroupID));
            tUserPermissionGRelationshipService.deleteByByExample(tUserpermissiongrelationshipExample);
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
     * 删除用户组人员信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("删除用户组信息")
    @ResponseBody
    @RequestMapping(value = "/delusergroupsuser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delusergroupsuser(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLoginusergroupsID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginusergroupsID != null && !CookiesLoginusergroupsID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            TUsergroupsrelationship tUsergroupsrelationship = tUserGroupsRelationshipService.findById(Long.parseLong(id));

            TUsergroups usergroups = tUserGroupsService.findById(tUsergroupsrelationship.getFugid());

            TUser tUser1 = tUserService.findById(tUsergroupsrelationship.getFuserid());

            TUser tUser = tUserService.findById(Long.parseLong(uid));
            TLogAction logAction = new TLogAction();
            logAction.setfUserId(tUser.getfKeyId());
            logAction.setfUserName(tUser.getfName());
            logAction.setfType(3);
            logAction.setfPath("usergroups/delusergroups{" + id + "}");
            logAction.setfUserType(1);
            logAction.setfMemo("删除用户组【"+usergroups.getFname()+"】，人员【"+tUser1.getfName()+"】信息。");
            logActionService.save(logAction);
            tUserGroupsRelationshipService.deleteById(Long.parseLong(id));
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