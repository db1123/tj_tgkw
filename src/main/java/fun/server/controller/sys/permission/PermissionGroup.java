package fun.server.controller.sys.permission;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.server.model.*;
import fun.server.model.customQuery.TPermissionYZ.TPermissionUserCs;
import fun.server.model.customQuery.TPermissionYZ.TPermissionUserQX;
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
 * 权限组管理 相关业务处理
 */
@RestController
@RequestMapping("/s/tpermissionGroup")
public class PermissionGroup {

    private final TPermissionGRoupService tPermissionGRoupService;

    private final TLogActionService logActionService;
    private final TUserService tUserService;

    private final TPermissionService tPermissionService;
    private final TPermissionGRelationshipService tPermissionGRelationshipService;

    private final TPermissionTermService tPermissionTermService;

    public PermissionGroup(TPermissionGRoupService tPermissionGRoupService, TLogActionService logActionService, TUserService tUserService, TPermissionService tPermissionService, TPermissionGRelationshipService tPermissionGRelationshipService, TPermissionTermService tPermissionTermService) {
        this.tPermissionGRoupService = tPermissionGRoupService;
        this.logActionService = logActionService;
        this.tUserService = tUserService;
        this.tPermissionService = tPermissionService;
        this.tPermissionGRelationshipService = tPermissionGRelationshipService;
        this.tPermissionTermService = tPermissionTermService;
    }


    /**
     * 获取权限组信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytpermissionGroup", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytpermissionGroup(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        int dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray tpermissionGroup_Array = new JSONArray();
            // 查询条件
            TPermissiongroupExample TPermissiongroupExample = new TPermissiongroupExample();
            TPermissiongroupExample.Criteria criteria = TPermissiongroupExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                TPermissiongroupExample.setOrderByClause(orderSql.substring(1));
            } else {
                TPermissiongroupExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TPermissiongroup> tpermissionGroupPageInfo = tPermissionGRoupService.findByExampleMapper(TPermissiongroupExample, (page - 1) * results, results);
            List<TPermissiongroup> tpermissionGroup_list = tpermissionGroupPageInfo.getList();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (TPermissiongroup tpermissionGroup : tpermissionGroup_list) {
                JSONObject tpermissionGroup_object = new JSONObject();
                tpermissionGroup_object.put("key", ParamTools.getEnParam(tpermissionGroup.getFkeyid().toString()));
                if(dataall==1){
                    tpermissionGroup_object.put("FName", tpermissionGroup.getFname() == null ? "" : tpermissionGroup.getFname());
                    tpermissionGroup_object.put("FNote", tpermissionGroup.getFnote() == null ? "" : tpermissionGroup.getFnote());
                    tpermissionGroup_object.put("FCID", tpermissionGroup.getFcid() == null ? "":ParamTools.getEnParam(tpermissionGroup.getFcid().toString()));
                    tpermissionGroup_object.put("FUID", tpermissionGroup.getFuid() == null ? "" :ParamTools.getEnParam(tpermissionGroup.getFuid().toString()));
                    tpermissionGroup_object.put("FCDATE", tpermissionGroup.getFcdate() == null ? "" : sdf.format(tpermissionGroup.getFcdate()));
                    tpermissionGroup_object.put("FUDATE", tpermissionGroup.getFudate() == null ? "" : sdf.format(tpermissionGroup.getFudate()));
                }else{
                    tpermissionGroup_object.put("FName", "*****");
                    tpermissionGroup_object.put("FNote", "*****");
                    tpermissionGroup_object.put("FCID", "*****");
                    tpermissionGroup_object.put("FUID", "*****");
                    tpermissionGroup_object.put("FCDATE", "*****");
                    tpermissionGroup_object.put("FUDATE", "*****");
                }
                tpermissionGroup_object.put("FState", tpermissionGroup.getFstate());
                tpermissionGroup_Array.add(tpermissionGroup_object);
            }
            // 返回值
            object.put("list", tpermissionGroup_Array);
            object.put("total", tpermissionGroupPageInfo.getTotal()); // 总行数
            object.put("page", tpermissionGroupPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取权限组信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatatpermissionGroupSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatatpermissionGroupSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray tpermissionGroup_Array = new JSONArray();
            TPermissiongroupExample tpermissionGroupExample = new TPermissiongroupExample();
            TPermissiongroupExample.Criteria criteria = tpermissionGroupExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            tpermissionGroupExample.setOrderByClause("fname asc");
            List<TPermissiongroup> tpermissionGroup_list = tPermissionGRoupService.findByExample(tpermissionGroupExample);
            for (TPermissiongroup tpermissionGroup : tpermissionGroup_list) {
                JSONObject tpermissionGroup_object = new JSONObject();
                tpermissionGroup_object.put("id", ParamTools.getEnParam(tpermissionGroup.getFkeyid().toString()));
                tpermissionGroup_object.put("text", tpermissionGroup.getFname());
                tpermissionGroup_Array.add(tpermissionGroup_object);
            }
            // 返回值

            object.put("list", tpermissionGroup_Array);
            object.put("result", "success");
            object.put("results", tpermissionGroup_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取权限组信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytpermissionGroupInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytpermissionGroupInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询权限组信息
            TPermissiongroup tpermissionGroup = tPermissionGRoupService.findById(key);
            JSONObject tpermissionGroup_object = new JSONObject();
            tpermissionGroup_object.put("key", ParamTools.getEnParam(tpermissionGroup.getFkeyid().toString()));
            tpermissionGroup_object.put("FName", tpermissionGroup.getFname() == null ? "" : tpermissionGroup.getFname());
            tpermissionGroup_object.put("FNote", tpermissionGroup.getFnote() == null ? "" : tpermissionGroup.getFnote());
            tpermissionGroup_object.put("FCID", tpermissionGroup.getFcid());
            tpermissionGroup_object.put("FUID", tpermissionGroup.getFuid());
            tpermissionGroup_object.put("FCDATE", tpermissionGroup.getFcdate());
            tpermissionGroup_object.put("FUDATE", tpermissionGroup.getFudate());
            tpermissionGroup_object.put("FState", tpermissionGroup.getFstate());

            // 返回值
            object.put("info", tpermissionGroup_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加权限组信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("添加权限组信息")
    @ResponseBody
    @RequestMapping(value = "/addtpermissionGroup", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addtpermissionGroup(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String Fname = jsonParam.getString("FName");
        String FNote = jsonParam.getString("FNote");

        try {
            if (repeaTPermissiongroup(0L, Fname, "1") == 0) {
                String CookiesLogintpermissionGroupID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintpermissionGroupID != null && !CookiesLogintpermissionGroupID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintpermissionGroupID);
                }
                // 新建权限组信息
                TPermissiongroup tpermissionGroup = new TPermissiongroup();
                tpermissionGroup.setFname(Fname);
                tpermissionGroup.setFnote(FNote);
                tpermissionGroup.setFcid(Long.parseLong(uid));
                tpermissionGroup.setFcdate(new Date());
                tPermissionGRoupService.save(tpermissionGroup);

                TUser tUser = tUserService.findById(Long.parseLong(uid));
                TLogAction logAction = new TLogAction();
                logAction.setfUserId(tUser.getfKeyId());
                logAction.setfUserName(tUser.getfName());
                logAction.setfType(3);
                logAction.setfPath("tpermissionGroup/addtpermissionGroup{" + tpermissionGroup.getFkeyid() + "}");
                logAction.setfUserType(1);

                logAction.setfMemo("新增权限组【" + Fname + "】信息。");
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
     * 修改权限组信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("修改权限组信息")
    @ResponseBody
    @RequestMapping(value = "/updatetpermissionGroup", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatetpermissionGroup(HttpServletRequest request)
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
            if (repeaTPermissiongroup(key, FName, "2") == 0) {
                String CookiesLogintpermissionGroupID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintpermissionGroupID != null && !CookiesLogintpermissionGroupID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintpermissionGroupID);
                }
                TPermissiongroup tpermissionGroup1 = tPermissionGRoupService.findById(key);

                // 更新权限组信息
                TPermissiongroup tpermissionGroup = new TPermissiongroup();
                tpermissionGroup.setFkeyid(key);
                tpermissionGroup.setFname(FName);
                tpermissionGroup.setFnote(FNote);
                tpermissionGroup.setFuid(Long.parseLong(uid));
                tpermissionGroup.setFudate(new Date());


                TUser tUser = tUserService.findById(Long.parseLong(uid));
                TLogAction logAction = new TLogAction();
                logAction.setfUserId(tUser.getfKeyId());
                logAction.setfUserName(tUser.getfName());
                logAction.setfType(3);
                logAction.setfPath("tpermissionGroup/updatetpermissionGroup{" + key + "}");
                logAction.setfUserType(1);

                logAction.setfMemo("权限组名称【" + tpermissionGroup1.getFname() + "】修改为【" + FName + "】。");
                logActionService.save(logAction);
                tPermissionGRoupService.update(tpermissionGroup);


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
     * 删除权限组信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("删除权限组信息")
    @ResponseBody
    @RequestMapping(value = "/deltpermissionGroup", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deltpermissionGroup(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLogintpermissionGroupID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintpermissionGroupID != null && !CookiesLogintpermissionGroupID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            TPermissiongroup tpermissionGroup = tPermissionGRoupService.findById(Long.parseLong(id));
            TPermissionExample tPermissionExample =new TPermissionExample();
            TPermissionExample.Criteria criteria = tPermissionExample.createCriteria();
            criteria.andFpgidEqualTo(Long.parseLong(id));
            tPermissionService.deleteByByExample(tPermissionExample);

            tPermissionGRoupService.deleteById(Long.parseLong(id));

            TUser tUser = tUserService.findById(Long.parseLong(uid));
            TLogAction logAction = new TLogAction();
            logAction.setfUserId(tUser.getfKeyId());
            logAction.setfUserName(tUser.getfName());
            logAction.setfType(3);
            logAction.setfPath("tpermissionGroup/deltpermissionGroup{" + id + "}");
            logAction.setfUserType(1);

            logAction.setfMemo("删除权限组【" + tpermissionGroup.getFname() + "】信息。");
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
     * 变更权限组信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("变更权限组状态")
    @ResponseBody
    @RequestMapping(value = "/statetpermissionGroup", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statetpermissionGroup(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLogintpermissionGroupID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintpermissionGroupID != null && !CookiesLogintpermissionGroupID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";

            TPermissiongroup tpermissionGroup1 = tPermissionGRoupService.findById(Long.parseLong(id));
            TPermissiongroup tpermissionGroup = new TPermissiongroup();
            tpermissionGroup.setFkeyid(Long.parseLong(id));
            tpermissionGroup.setFuid(Long.parseLong(uid));
            tpermissionGroup.setFudate(new Date());
            tpermissionGroup.setFstate(Integer.valueOf(state));
            tPermissionGRoupService.update(tpermissionGroup);
            String statestr = "";
            if (Integer.valueOf(state) == 0) {
                statestr = "禁用";
            } else {
                statestr = "启用";
            }

            TUser tUser = tUserService.findById(Long.parseLong(uid));
            TLogAction logAction = new TLogAction();
            logAction.setfUserId(tUser.getfKeyId());
            logAction.setfUserName(tUser.getfName());
            logAction.setfType(3);
            logAction.setfPath("tpermissionGroup/statetpermissionGroup{" + id + "}");
            logAction.setfUserType(1);

            logAction.setfMemo("权限组【" + tpermissionGroup1.getFname() + "】状态变更为【" + statestr + "】。");
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
     * 验证权限组是否存在
     */
    private int repeaTPermissiongroup(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TPermissiongroupExample tpermissionGroupExample = new TPermissiongroupExample();
            TPermissiongroupExample.Criteria criteria = tpermissionGroupExample.createCriteria();
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
            List<TPermissiongroup> rojectTypeList = tPermissionGRoupService.findByExample(tpermissionGroupExample);
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
     * 添加权限组权限信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("添加权限组信息")
    @ResponseBody
    @RequestMapping(value = "/addtpermissionGroupuser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addtpermissionGroupuser(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String fmenids = jsonParam.getString("fmenids");//菜单
        String FPGID = jsonParam.getString("FPGID");//权限组id
        int FType = jsonParam.getInteger("FType");//类型
        String FTerm = jsonParam.getString("FTerm");//权限项
        String FNote = jsonParam.getString("FNote");//备注
        try {
            String CookiesLogintpermissionGroupID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintpermissionGroupID != null && !CookiesLogintpermissionGroupID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            object.put("FPGID", FPGID);
            FPGID = FPGID == null ? "0" : ParamTools.getdeParam(FPGID);
//            FMenID = FMenID == null ? "0" : ParamTools.getdeParam(FMenID);
            TPermissiongroup tPermissiongroup = tPermissionGRoupService.findById(Long.parseLong(FPGID));
            if (tPermissiongroup != null) {
                if (fmenids != null && !fmenids.equals("")) {
                    JSONArray powermenID_Array = JSONArray.parseArray(fmenids);
                    String FMenID = "";
                    String Fterm = "";
                    for (Object m : powermenID_Array) {
                        FMenID = m == null ? "0" : ParamTools.getdeParam(m.toString());
                        if (FType == -1 || FType == 1) {
                            if (FType == -1) {
                                TPermissionExample tPermissionExample1 = new TPermissionExample();
                                tPermissionExample1.createCriteria()
                                        .andFpgidEqualTo(Long.parseLong(FPGID))
                                        .andFmenidEqualTo(Long.parseLong(FMenID))
                                        .andFtermEqualTo(Long.parseLong("9999"));
                                List<TPermission> permissionList = tPermissionService.findByExample(tPermissionExample1);
                                if (permissionList.size() == 0) {
                                    TPermission tPermission = new TPermission();
                                    tPermission.setFpgid(Long.parseLong(FPGID));
                                    tPermission.setFtype(2);
                                    tPermission.setFmenid(Long.parseLong(FMenID));
                                    tPermission.setFterm(Long.parseLong("9999"));
                                    tPermission.setFnote(FNote);
                                    tPermission.setFcdate(new Date());
                                    tPermission.setFcid(Long.parseLong(uid));
                                    tPermissionService.save(tPermission);
                                }
                            }
                            if (FTerm != null && !FTerm.equals("")) {
                                JSONArray powerterm_Array = JSONArray.parseArray(FTerm);
                                for (Object term : powerterm_Array) {
                                    Fterm = term == null ? "0" : ParamTools.getdeParam(term.toString());
                                    TPermissionExample tPermissionExample1 = new TPermissionExample();
                                    tPermissionExample1.createCriteria()
                                            .andFpgidEqualTo(Long.parseLong(FPGID))
                                            .andFmenidEqualTo(Long.parseLong(FMenID))
                                            .andFtermEqualTo(Long.parseLong(Fterm));
                                    List<TPermission> permissionList = tPermissionService.findByExample(tPermissionExample1);
                                    if (permissionList.size() == 0) {
                                        TPermission tPermission = new TPermission();
                                        tPermission.setFpgid(Long.parseLong(FPGID));
                                        tPermission.setFtype(1);
                                        tPermission.setFmenid(Long.parseLong(FMenID));
                                        tPermission.setFterm(Long.parseLong(Fterm));
                                        tPermission.setFnote(FNote);
                                        tPermission.setFcdate(new Date());
                                        tPermission.setFcid(Long.parseLong(uid));
                                        tPermissionService.save(tPermission);
                                    }
//                                    else{
//                                        for (TPermission tPermission : permissionList){
//                                            TPermission t = new TPermission();
//                                            t.setFkeyid(tPermission.getFkeyid());
//                                            t.setFtype(1);
//                                            t.setFudate(new Date());
//                                            t.setFuid(Long.parseLong(uid));
//                                            tPermissionService.update(t);
//                                        }
//                                    }
                                }
                            }

                        } else {
                            TPermissionExample tPermissionExample1 = new TPermissionExample();
                            tPermissionExample1.createCriteria()
                                    .andFpgidEqualTo(Long.parseLong(FPGID))
                                    .andFmenidEqualTo(Long.parseLong(FMenID))
                                    .andFtermEqualTo(Long.parseLong("9999"));
                            List<TPermission> permissionList = tPermissionService.findByExample(tPermissionExample1);
                            if (permissionList.size() == 0) {
                                TPermission tPermission = new TPermission();
                                tPermission.setFpgid(Long.parseLong(FPGID));
                                tPermission.setFtype(2);
                                tPermission.setFmenid(Long.parseLong(FMenID));
                                tPermission.setFterm(Long.parseLong("9999"));
                                tPermission.setFnote(FNote);
                                tPermission.setFcdate(new Date());
                                tPermission.setFcid(Long.parseLong(uid));
                                tPermissionService.save(tPermission);
                            }
                        }
                    }

                }
                // 返回值
                object.put("result", "success");
            } else {
                object.put("result", "error");
                object.put("error", "未获取到权限组信息，请刷新后重试！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 访问菜单时 进行权限判断
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryaccesscontrol", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryaccesscontrol(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);

        String FMenID = jsonParam.getString("FMenID");//菜单ID
        try {
            // 获取数据库记录
            JSONArray accesscontrol_Array = new JSONArray();
            String CookiesLogintpermissionGroupID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintpermissionGroupID != null && !CookiesLogintpermissionGroupID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            FMenID = FMenID == null ? "0" : ParamTools.getdeParam(FMenID);
            TUser tUser = tUserService.findById(Long.parseLong(uid));
//            System.out.println(tUser.getfName());
            //先查询当前用户存不存在
            if (tUser != null) {
                JSONObject qx_object = new JSONObject();
                TPermissiontermExample tPermissiontermExample = new TPermissiontermExample();
                TPermissiontermExample.Criteria criteria = tPermissiontermExample.createCriteria();
                criteria.andFstateEqualTo(1);
                List<TPermissionterm> permissiontermList = tPermissionTermService.findByExample(tPermissiontermExample);
                if (tUser.getfIsAdmin() == 1) {
                    //查询是否是管理员 管理员拥有全部权限
                    qx_object = new JSONObject();
                    for (TPermissionterm tPermissionterm : permissiontermList) {
                        qx_object.put(tPermissionterm.getFalias().toString(), 1);
                    }
                    qx_object.put("dataall", 1);
//                    System.out.println("1:" + qx_object.toString());
                    object.put("accessInfo", qx_object);
                    object.put("result", "success");
                } else {
                    qx_object = new JSONObject();

                    List<Long> bczid = null ;
                    //不是管理员的话 进行正常查询
                    TPermissionUserCs tPermissionUserCs = new TPermissionUserCs();
                    tPermissionUserCs.setFUserID(Long.parseLong(uid));
                    tPermissionUserCs.setFMenID(Long.parseLong(FMenID));
                    List<TPermissionUserQX> userQXList = tPermissionGRoupService.getUserPermission(tPermissionUserCs);
                    if (userQXList.size() > 0) {
                        bczid = new ArrayList<>();
                        for (TPermissionterm tPermissionterm : permissiontermList) {
                            int isxt = 0;
                            for (TPermissionUserQX tPermissionUserQX : userQXList) {
                                if (tPermissionUserQX.getFTermID().toString().equals(tPermissionterm.getFkeyid().toString())) {
                                    isxt = 1;
                                    break;
                                }
                            }
                            if(isxt == 1){
                                qx_object.put(tPermissionterm.getFalias().toString(), 1);
                            }else{
                                qx_object.put(tPermissionterm.getFalias().toString(), 0);
                            }

                        }
//                        for (TPermissionUserQX tPermissionUserQX : userQXList) {
//                            if (tPermissionUserQX.getFTermID() == 9999) {
//                                qx_object.put("dataall", 1);
//                            } else {
//                                qx_object.put("dataall", 0);
//                            }
//                        }
//                        System.out.println("2:" + qx_object.toString());
                        object.put("accessInfo", qx_object);
                        object.put("result", "success");
                    }else{
                        object.put("result", "error");
                    }
                }
            }else{
                object.put("result", "error");
            }


        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 访问菜单时 进行权限判断
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryaccesscontroll", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryaccesscontroll(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FMenID = jsonParam.getString("FMenID");//菜单ID
        String FUserID = jsonParam.getString("FUserID");//用户ID
        try {
            // 获取数据库记录
            JSONArray accesscontrol_Array = new JSONArray();
//            FMenID = FMenID == null ? "0" : ParamTools.getdeParam(FMenID);
            FUserID = FUserID == null ? "0" : ParamTools.getdeParam(FUserID);
            TUser tUser = tUserService.findById(Long.parseLong(FUserID));
//            System.out.println(tUser.getfName());
            //先查询当前用户存不存在
            if (tUser != null) {
                JSONObject qx_object = new JSONObject();
                TPermissiontermExample tPermissiontermExample = new TPermissiontermExample();
                TPermissiontermExample.Criteria criteria = tPermissiontermExample.createCriteria();
                criteria.andFstateEqualTo(1);
                List<TPermissionterm> permissiontermList = tPermissionTermService.findByExample(tPermissiontermExample);
                if (tUser.getfIsAdmin() == 1) {
                    //查询是否是管理员 管理员拥有全部权限
                    qx_object = new JSONObject();
                    for (TPermissionterm tPermissionterm : permissiontermList) {
                        qx_object.put(tPermissionterm.getFalias().toString(), 1);
                    }
                    qx_object.put("dataall", 1);
//                    System.out.println("1:" + qx_object.toString());
                    object.put("accessInfo", ParamTools.getEnParam(qx_object.toString()));
                    object.put("result", "success");
                } else {
                    qx_object = new JSONObject();

                    List<Long> bczid = null ;
                    //不是管理员的话 进行正常查询
                    TPermissionUserCs tPermissionUserCs = new TPermissionUserCs();
                    tPermissionUserCs.setFUserID(Long.parseLong(FUserID));
                    tPermissionUserCs.setFMenID(Long.parseLong(FMenID));
                    List<TPermissionUserQX> userQXList = tPermissionGRoupService.getUserPermission(tPermissionUserCs);
                    if (userQXList.size() > 0) {
                        bczid = new ArrayList<>();
                        for (TPermissionterm tPermissionterm : permissiontermList) {
                            int isxt = 0;
                            for (TPermissionUserQX tPermissionUserQX : userQXList) {
                                if (tPermissionUserQX.getFTermID().toString().equals(tPermissionterm.getFkeyid().toString())) {
                                    isxt = 1;
                                    break;
                                }
                            }
                            if(isxt == 1){
                                qx_object.put(tPermissionterm.getFalias().toString(), 1);
                            }else{
                                qx_object.put(tPermissionterm.getFalias().toString(), 0);
                            }

                        }
//                        for (TPermissionUserQX tPermissionUserQX : userQXList) {
//                            if (tPermissionUserQX.getFTermID() == 9999) {
//                                qx_object.put("dataall", 1);
//                            } else {
//                                qx_object.put("dataall", 0);
//                            }
//                        }
//                        System.out.println("2:" + qx_object.toString());
                        object.put("accessInfo", ParamTools.getEnParam(qx_object.toString()));
                        object.put("result", "success");
                    }else{
                        object.put("result", "error");
                    }
                }
            }else{
                object.put("result", "error");
            }


        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


}