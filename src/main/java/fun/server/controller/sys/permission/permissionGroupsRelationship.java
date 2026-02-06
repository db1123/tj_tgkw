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
 * 权限组与权限关系管理 相关业务处理
 */
@RestController
@RequestMapping("/s/permissiongroupsrelationship")
public class permissionGroupsRelationship {

    private final TPermissionGRelationshipService tPermissionGRelationshipService;
    private final TLogActionService logActionService;
    private final TUserService tUserService;

    private final TMenuOptionsService tMenuOptionsService;
    private final TMenuTypeService tMenuTypeService;
    private final TPermissionService tPermissionService;
    
    private final TPermissionTermService tPermissionTermService;

    private final TPermissionGRoupService tPermissionGRoupService;


    public permissionGroupsRelationship(TPermissionGRelationshipService tPermissionGRelationshipService, TLogActionService logActionService, TUserService tUserService, TMenuOptionsService tMenuOptionsService, TMenuTypeService tMenuTypeService, TPermissionService tPermissionService, TPermissionTermService tPermissionTermService, TPermissionGRoupService tPermissionGRoupService) {
        this.tPermissionGRelationshipService = tPermissionGRelationshipService;
        this.logActionService = logActionService;
        this.tUserService = tUserService;
        this.tMenuOptionsService = tMenuOptionsService;
        this.tMenuTypeService = tMenuTypeService;
        this.tPermissionService = tPermissionService;
        this.tPermissionTermService = tPermissionTermService;
        this.tPermissionGRoupService = tPermissionGRoupService;
    }


    /**
     * 获取权限组与权限关系信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querypermissiongroupsrelationship", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querypermissiongroupsrelationship(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String id = jsonParam.getString("id"); //用户组ID
        String FMenID = jsonParam.getString("FMenID"); //菜单
        int FType = jsonParam.getInteger("FType"); //权限类型
        int dataall = jsonParam.getInteger("dataall"); //数据权限
        try {
            // 获取数据库记录
            JSONArray usergroups_Array = new JSONArray();
            // 查询条件

            TPermissionExample tPermissionExample  = new TPermissionExample();
            TPermissionExample.Criteria criteria = tPermissionExample.createCriteria();
            id = id == null ? "0" : ParamTools.getdeParam(id);

            criteria.andFpgidEqualTo(Long.parseLong(id));
            if(FMenID!=null && !FMenID.equals("")){
                FMenID = FMenID == null ? "-1" : ParamTools.getdeParam(FMenID);
                if(!FMenID.equals("-1")){
                    criteria.andFmenidEqualTo(Long.parseLong(FMenID));
                }
            }
            if(FType!=-2){
                criteria.andFtypeEqualTo(FType);
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
                tPermissionExample.setOrderByClause(orderSql.substring(1));
            } else {
                tPermissionExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TPermission> usergroupsPageInfo = tPermissionService.findByExampleMapper(tPermissionExample, (page - 1) * results, results);
            List<TPermission> usergroups_list = usergroupsPageInfo.getList();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (TPermission tPermission : usergroups_list) {
                JSONObject usergroups_object = new JSONObject();
                usergroups_object.put("key", ParamTools.getEnParam(tPermission.getFkeyid().toString()));
                usergroups_object.put("FPGID", tPermission.getFpgid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(tPermission.getFpgid().toString()));


                usergroups_object.put("FType", tPermission.getFtype() == null ? "" : tPermission.getFtype());
                usergroups_object.put("FMenID", tPermission.getFmenid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(tPermission.getFmenid().toString()));
                TMenuOptions menuOptions = tMenuOptionsService.findById(tPermission.getFmenid());
                if(dataall ==1 ){
                    if(tPermission.getFtype() ==1){
                        usergroups_object.put("FTypeName", "功能");
                    }else if(tPermission.getFtype() ==2){
                        usergroups_object.put("FTypeName", "数据");
                    }else{
                        usergroups_object.put("FTypeName", "无");
                    }
                    usergroups_object.put("FMenName", menuOptions == null ? "" : menuOptions.getfName());
                    if(tPermission.getFterm() != 9999){
                        usergroups_object.put("FTerm", tPermission.getFterm() == null ?  ParamTools.getEnParam("0") : ParamTools.getEnParam(tPermission.getFterm().toString()));
                        TPermissionterm tPermissionterm = tPermissionTermService.findById(tPermission.getFterm());
                        usergroups_object.put("FTermName", tPermissionterm == null ? "" : tPermissionterm.getFname());
                    }else{
                        usergroups_object.put("FTerm",  ParamTools.getEnParam("9999"));
                        usergroups_object.put("FTermName", "全部数据");
                    }
                    usergroups_object.put("FNote", tPermission.getFnote() == null ? "" : tPermission.getFnote());
                }else{
                    usergroups_object.put("FTypeName", "*****");
                    usergroups_object.put("FMenName", "*****");
                    if(tPermission.getFterm() != 9999){
                        usergroups_object.put("FTerm", tPermission.getFterm() == null ?  ParamTools.getEnParam("0") : ParamTools.getEnParam(tPermission.getFterm().toString()));
//                        TPermissionterm tPermissionterm = tPermissionTermService.findById(tPermission.getFterm());
                        usergroups_object.put("FTermName", "*****");
                    }else{
                        usergroups_object.put("FTerm",  ParamTools.getEnParam("9999"));
                        usergroups_object.put("FTermName", "*****");
                    }
                    usergroups_object.put("FNote", "******");
                }
                usergroups_object.put("FCID", tPermission.getFcid() == null ? "" : ParamTools.getEnParam(tPermission.getFcid().toString()));
                usergroups_object.put("FUID", tPermission.getFuid() == null ? "" : ParamTools.getEnParam(tPermission.getFuid().toString()));
                usergroups_object.put("FCDATE", tPermission.getFcdate() == null ? "" : sdf.format(tPermission.getFcdate()));
                usergroups_object.put("FUDATE", tPermission.getFudate() == null ? "" : sdf.format(tPermission.getFudate()));
                usergroups_object.put("FState", tPermission.getFstate());
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
     * 获取权限组与权限关系信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querypermissioninfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querypermissioninfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id"); //权限id
        try {
            // 获取数据库记录
            JSONArray usergroups_Array = new JSONArray();
            // 查询条件

            TPermissionExample tPermissionExample  = new TPermissionExample();
            TPermissionExample.Criteria criteria = tPermissionExample.createCriteria();
            id = id == null ? "0" : ParamTools.getdeParam(id);
            criteria.andFpgidEqualTo(Long.parseLong(id));


            TPermission  tPermission = tPermissionService.findById(Long.parseLong(id));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            JSONObject usergroups_object = new JSONObject();
            usergroups_object.put("key", ParamTools.getEnParam(tPermission.getFkeyid().toString()));
            usergroups_object.put("FPGID", tPermission.getFpgid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(tPermission.getFpgid().toString()));
            usergroups_object.put("FType", tPermission.getFtype() == null ? "" : tPermission.getFtype());
            usergroups_object.put("FMenID", tPermission.getFmenid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(tPermission.getFmenid().toString()));
            TMenuOptions menuOptions = tMenuOptionsService.findById(tPermission.getFmenid());
            usergroups_object.put("FMenName", menuOptions == null ? "" : menuOptions.getfName());
            if(tPermission.getFterm() != 9999){
                usergroups_object.put("FTerm", tPermission.getFterm() == null ?  ParamTools.getEnParam("0") : ParamTools.getEnParam(tPermission.getFterm().toString()));
                TPermissionterm tPermissionterm = tPermissionTermService.findById(tPermission.getFterm());
                usergroups_object.put("FTermName", tPermissionterm == null ? "" : tPermissionterm.getFname());
            }else{
                usergroups_object.put("FTerm",  ParamTools.getEnParam("9999"));
                usergroups_object.put("FTermName", "全部数据");
            }
            usergroups_object.put("FNote", tPermission.getFnote() == null ? "" : tPermission.getFnote());
            usergroups_object.put("FCID", tPermission.getFcid());
            usergroups_object.put("FUID", tPermission.getFuid());
            usergroups_object.put("FCDATE", tPermission.getFcdate() == null ? "" : sdf.format(tPermission.getFcdate()));
            usergroups_object.put("FUDATE", tPermission.getFudate() == null ? "" : sdf.format(tPermission.getFudate()));
            usergroups_object.put("FState", tPermission.getFstate());
            usergroups_Array.add(usergroups_object);
            // 返回值
            object.put("info", usergroups_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取菜单信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatatmenu_optionsSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatatmenu_optionsSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        String FType = request.getParameter("FType");
        try {
            // 获取数据库记录
            JSONArray tpermissionGroup_Array = new JSONArray();


            TMenuOptionsExample tMenuOptionsExample = new TMenuOptionsExample();
            TMenuOptionsExample.Criteria criteria = tMenuOptionsExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFNameLike("%" + search + "%");
            }

            if(FType!=null && !FType.equals("") && FType.equals("-1")){
                JSONObject tpermissionGroup_object = new JSONObject();
                tpermissionGroup_object.put("id", ParamTools.getEnParam("-1"));
                tpermissionGroup_object.put("text", "全部菜单");
                tpermissionGroup_Array.add(tpermissionGroup_object);
            }
            tMenuOptionsExample.setOrderByClause("f_name asc");
            criteria.andFStateEqualTo(1);
            criteria.andFMenuTypeEqualTo(1);
            List<TMenuOptions> tpermissionGroup_list = tMenuOptionsService.findByExample(tMenuOptionsExample);
            TMenuType menuType =null;
            for (TMenuOptions tMenuOptions : tpermissionGroup_list) {
                menuType = tMenuTypeService.findById(tMenuOptions.getfTypeId());
                if(menuType.getfState() == 1) {
                    JSONObject tpermissionGroup_object = new JSONObject();
                    tpermissionGroup_object.put("id", ParamTools.getEnParam(tMenuOptions.getfKeyId().toString()));
                    tpermissionGroup_object.put("text", tMenuOptions.getfName());
                    tpermissionGroup_Array.add(tpermissionGroup_object);
                }
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
     * 获取权限组 绑定的菜单信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatatmenuoptionsSelectqxz", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatatmenuoptionsSelectqxz(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");

        String FPGID = request.getParameter("FPGID");
        try {
            // 获取数据库记录
            JSONArray tpermissionGroup_Array = new JSONArray();

            FPGID = FPGID == null ? "0": ParamTools.getdeParam(FPGID);

            TMenuOptionsExample tMenuOptionsExample = new TMenuOptionsExample();
            TMenuOptionsExample.Criteria criteria = tMenuOptionsExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFNameLike("%" + search + "%");
            }
            tMenuOptionsExample.setOrderByClause("f_name asc");
            criteria.andFStateEqualTo(1);
            criteria.andFMenuTypeEqualTo(1);
            List<TMenuOptions> tpermissionGroup_list = tMenuOptionsService.findByExample(tMenuOptionsExample);
            List<Long> tmenids = new ArrayList<>();
            for (TMenuOptions tMenuOptions : tpermissionGroup_list) {
                tmenids.add(tMenuOptions.getfKeyId());

            }

            TPermissionExample tPermissionExample = new TPermissionExample();
            TPermissionExample.Criteria criteria1 = tPermissionExample.createCriteria();
            criteria1.andFpgidEqualTo(Long.parseLong(FPGID));
            if(tmenids.size()>0){
                criteria1.andFmenidIn(tmenids);
            }
            List<TPermission> permissionList = tPermissionService.findByExample(tPermissionExample);
            List<Long> permmenids = new ArrayList<>();
            for (TPermission tPermission :permissionList ){
                if(!permmenids.contains(tPermission.getFmenid())){
                    JSONObject tpermissionGroup_object = new JSONObject();
                    tpermissionGroup_object.put("id", ParamTools.getEnParam(tPermission.getFmenid().toString()));
                    TMenuOptions menuOptions = tMenuOptionsService.findById(tPermission.getFmenid());
                    tpermissionGroup_object.put("text", menuOptions == null ? "" : menuOptions.getfName());
                    tpermissionGroup_Array.add(tpermissionGroup_object);
                    permmenids.add(tPermission.getFmenid());
                }
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
     * 删除权限组中权限菜单的信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("删除权限组信息")
    @ResponseBody
    @RequestMapping(value = "/deltpermissionterm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deltpermissionterm(HttpServletRequest request)
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

            TPermission tPermission = tPermissionService.findById(Long.parseLong(id));
            Long fpgid = tPermission.getFpgid();
            Long fterm = tPermission.getFterm();
            TPermissiongroup tPermissiongroup = tPermissionGRoupService.findById(fpgid);
            TPermissionterm tPermissionterm = tPermissionTermService.findById(fterm);
            String ftermname="";
            if(tPermission.getFterm()!=9999){
                ftermname = tPermissionterm.getFname();
            }else{
                ftermname = "全部数据";
            }
            TMenuOptions menuOptions = tMenuOptionsService.findById(tPermission.getFmenid());

            TUser tUser = tUserService.findById(Long.parseLong(uid));
            TLogAction logAction = new TLogAction();
            logAction.setfUserId(tUser.getfKeyId());
            logAction.setfUserName(tUser.getfName());
            logAction.setfType(3);
            logAction.setfPath("permissiongroupsrelationship/deltpermissionterm{" + id + "}");
            logAction.setfUserType(1);
            logAction.setfMemo("删除权限组【" + tPermissiongroup.getFname() + "】-->菜单【"+menuOptions.getfName()+"】-->权限【"+ftermname+"】信息。");
            logActionService.save(logAction);
            tPermissionService.deleteById(Long.parseLong(id));
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
     * 一键清空权限菜单的信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("删除权限组信息")
    @ResponseBody
    @RequestMapping(value = "/delyijiantpermissionterm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delyijiantpermissionterm(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");//权限组ID
        try {
            String CookiesLogintpermissionGroupID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintpermissionGroupID != null && !CookiesLogintpermissionGroupID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            TPermissiongroup tPermissiongroup = tPermissionGRoupService.findById(Long.parseLong(id));
            TPermissionExample tPermissionExample = new TPermissionExample();
            tPermissionExample.createCriteria().andFpgidEqualTo(Long.parseLong(id));
            List<TPermission> permissionList = tPermissionService.findByExample(tPermissionExample);
            if(permissionList.size() > 0){
                TUser tUser = tUserService.findById(Long.parseLong(uid));
                TLogAction logAction = new TLogAction();
                logAction.setfUserId(tUser.getfKeyId());
                logAction.setfUserName(tUser.getfName());
                logAction.setfType(3);
                logAction.setfPath("permissiongroupsrelationship/delyijiantpermissionterm{" + id + "}");
                logAction.setfUserType(1);
                logAction.setfMemo("删除权限组【" + tPermissiongroup.getFname() + "】全部权限信息。");
                logActionService.save(logAction);
                //删除全部权限
                tPermissionService.deleteByByExample(tPermissionExample);
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
     * 按菜单清空权限菜单的信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("删除权限组信息")
    @ResponseBody
    @RequestMapping(value = "/delmentpermissionterm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delmentpermissionterm(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FPGID");//权限组ID
        String fmenids = jsonParam.getString("fmenids");//菜单ID集合
        try {
            String CookiesLogintpermissionGroupID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintpermissionGroupID != null && !CookiesLogintpermissionGroupID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            if (fmenids != null && !fmenids.equals("")) {
                TPermissiongroup tPermissiongroup = tPermissionGRoupService.findById(Long.parseLong(id));
                JSONArray powermenID_Array = JSONArray.parseArray(fmenids);
                String FMenID = "";
                for (Object m : powermenID_Array) {
                    FMenID = m == null ? "0" : ParamTools.getdeParam(m.toString());
                    TPermissionExample tPermissionExample = new TPermissionExample();
                    tPermissionExample.createCriteria().andFpgidEqualTo(Long.parseLong(id)).andFmenidEqualTo(Long.parseLong(FMenID));
                    List<TPermission> permissionList = tPermissionService.findByExample(tPermissionExample);
                    if(permissionList.size()>0){
                        TMenuOptions menuOptions = tMenuOptionsService.findById(Long.parseLong(FMenID));
                        TUser tUser = tUserService.findById(Long.parseLong(uid));
                        TLogAction logAction = new TLogAction();
                        logAction.setfUserId(tUser.getfKeyId());
                        logAction.setfUserName(tUser.getfName());
                        logAction.setfType(3);
                        logAction.setfPath("permissiongroupsrelationship/delmentpermissionterm{" + id + "}");
                        logAction.setfUserType(1);
                        logAction.setfMemo("删除权限组【" + tPermissiongroup.getFname() + "】-->菜单【"+menuOptions.getfName()+"】全部权限信息。");
                        logActionService.save(logAction);
                        //删除指定菜单全部权限
                        tPermissionService.deleteByByExample(tPermissionExample);
                    }
                }
            }
            // 返回值
            object.put("result", "success");
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 一键新增所有菜单权限
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("删除权限组信息")
    @ResponseBody
    @RequestMapping(value = "/addcaidanqx", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addcaidanqx(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");//权限组ID
        try {
            String CookiesLogintpermissionGroupID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintpermissionGroupID != null && !CookiesLogintpermissionGroupID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TMenuOptionsExample tMenuOptionsExample = new TMenuOptionsExample();
            TMenuOptionsExample.Criteria criteria = tMenuOptionsExample.createCriteria();
            tMenuOptionsExample.setOrderByClause("f_name asc");
            criteria.andFStateEqualTo(1);
            criteria.andFMenuTypeEqualTo(1);
            List<TMenuOptions> tpermissionGroup_list = tMenuOptionsService.findByExample(tMenuOptionsExample);
            TMenuType tMenuType = null;


            TPermissiontermExample tPermissiontermExample = new TPermissiontermExample();
            tPermissiontermExample.createCriteria();
            List<TPermissionterm> permissiontermList = null;
            for (TMenuOptions tMenuOptions : tpermissionGroup_list) {
                tMenuType = tMenuTypeService.findById(tMenuOptions.getfTypeId());
                if(tMenuType.getfState() == 1){
                    permissiontermList = tPermissionTermService.findByExample(tPermissiontermExample);
                    for (TPermissionterm tPermissionterm : permissiontermList) {
                        TPermissionExample tPermissionExample1 = new TPermissionExample();
                        tPermissionExample1.createCriteria()
                                .andFpgidEqualTo(Long.parseLong(id))
                                .andFmenidEqualTo(tMenuOptions.getfKeyId())
                                .andFtermEqualTo(tPermissionterm.getFkeyid());
                        List<TPermission> permissionList = tPermissionService.findByExample(tPermissionExample1);
                        if (permissionList.size() == 0) {
                            TPermission tPermission = new TPermission();
                            tPermission.setFpgid(Long.parseLong(id));
                            tPermission.setFtype(1);
                            tPermission.setFmenid(tMenuOptions.getfKeyId());
                            tPermission.setFterm(tPermissionterm.getFkeyid());
                            tPermission.setFnote("");
                            tPermission.setFcdate(new Date());
                            tPermission.setFcid(Long.parseLong(uid));
                            tPermissionService.save(tPermission);
                        }
                    }
                }
            }

            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
}