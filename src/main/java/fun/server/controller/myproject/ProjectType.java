package fun.server.controller.myproject;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.service.*;
import fun.server.service.TProjectService;
import fun.tools.ParamTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * 项目类型管理 相关业务处理
 */
@RestController
@RequestMapping("/s/projectType")
public class ProjectType {

    private final TProjectTypeService tProjectTypeService;
    private final TProjectService tProjectService;
    private final TLogActionService logActionService;
    private final TUserService tUserService;
    public ProjectType(TProjectTypeService tProjectTypeService, TProjectService tProjectService, TLogActionService logActionService, TUserService tUserService) {
        this.tProjectTypeService = tProjectTypeService;
        this.tProjectService = tProjectService;
        this.logActionService = logActionService;
        this.tUserService = tUserService;
    }


    /**
     * 获取项目类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryprojectType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryprojectType(HttpServletRequest request)
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
            JSONArray projectType_Array = new JSONArray();
            // 查询条件
            TProjectTypeExample TProjectTypeExample = new TProjectTypeExample();
            TProjectTypeExample.Criteria criteria = TProjectTypeExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");

            }
            TProjectTypeExample.setOrderByClause("FCDATE desc");
            PageInfo<TProjectType> projectTypePageInfo = tProjectTypeService.findByExampleMapper(TProjectTypeExample, (page - 1) * results, results);
            List<TProjectType> projectType_list = projectTypePageInfo.getList();

            for (TProjectType projectType : projectType_list) {
                JSONObject projectType_object = new JSONObject();
                projectType_object.put("key", ParamTools.getEnParam(projectType.getFkeyid().toString()));
                projectType_object.put("FName", projectType.getFname());
                projectType_object.put("FCID", projectType.getFcid());
                projectType_object.put("FUID", projectType.getFuid());
                projectType_object.put("FCDATE", projectType.getFcdate());
                projectType_object.put("FUDATE", projectType.getFudate());
                projectType_object.put("FState", projectType.getFstate());
                projectType_Array.add(projectType_object);
            }
            // 返回值
            object.put("list", projectType_Array);
            object.put("total", projectTypePageInfo.getTotal()); // 总行数
            object.put("page", projectTypePageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取项目类型信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDataprojectTypeSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDataprojectTypeSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray projectType_Array = new JSONArray();
            TProjectTypeExample projectTypeExample = new TProjectTypeExample();
            TProjectTypeExample.Criteria criteria = projectTypeExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            projectTypeExample.setOrderByClause("fname asc");
            List<TProjectType> projectType_list = tProjectTypeService.findByExample(projectTypeExample);
            for (TProjectType projectType : projectType_list) {
                JSONObject projectType_object = new JSONObject();
                projectType_object.put("id", ParamTools.getEnParam(projectType.getFkeyid().toString()));
                projectType_object.put("text", projectType.getFname());
                projectType_Array.add(projectType_object);
            }
            // 返回值

            object.put("list", projectType_Array);
            object.put("result", "success");
            object.put("results", projectType_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取项目类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryprojectTypeInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryprojectTypeInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询项目类型信息
            TProjectType projectType = tProjectTypeService.findById(key);
            JSONObject projectType_object = new JSONObject();
            projectType_object.put("key", ParamTools.getEnParam(projectType.getFkeyid().toString()));
            projectType_object.put("FName", projectType.getFname());
            projectType_object.put("FCID", projectType.getFcid());
            projectType_object.put("FUID", projectType.getFuid());
            projectType_object.put("FCDATE", projectType.getFcdate());
            projectType_object.put("FUDATE", projectType.getFudate());
            projectType_object.put("FState", projectType.getFstate());
            // 返回值
            object.put("info", projectType_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加项目类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("添加项目类型信息")
    @ResponseBody
    @RequestMapping(value = "/addprojectType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addprojectType(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String Fname = jsonParam.getString("Fname");

        try {
            if (repeaTProjectType(0L, Fname, "1") == 0) {
                String CookiesLoginprojectTypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginprojectTypeID != null && !CookiesLoginprojectTypeID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginprojectTypeID);
                }
                // 新建项目类型信息
                TProjectType projectType = new TProjectType();
                projectType.setFname(Fname);
                projectType.setFcid(Long.parseLong(uid));
                projectType.setFcdate(new Date());
                tProjectTypeService.save(projectType);

                try {
                    TUser tUser = tUserService.findById(Long.parseLong(uid));
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserId(tUser.getfKeyId());
                    logAction.setfUserName(tUser.getfName());
                    logAction.setfType(3);
                    logAction.setfPath("projectType/addprojectType{" + projectType.getFkeyid() + "}");
                    logAction.setfUserType(1);

                    logAction.setfMemo("新增项目类型【"+Fname+"】信息。");
                    logActionService.save(logAction);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

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
     * 修改项目类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("修改项目类型信息")
    @ResponseBody
    @RequestMapping(value = "/updateprojectType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateprojectType(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String Fname = jsonParam.getString("Fname");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            if (repeaTProjectType(key, Fname, "2") == 0) {
                String CookiesLoginprojectTypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginprojectTypeID != null && !CookiesLoginprojectTypeID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginprojectTypeID);
                }
                TProjectType projectType1 = tProjectTypeService.findById(key);

                // 更新项目类型信息
                TProjectType projectType = new TProjectType();
                projectType.setFkeyid(key);
                projectType.setFname(Fname);
                projectType.setFuid(Long.parseLong(uid));
                projectType.setFudate(new Date());
                tProjectTypeService.update(projectType);

                try {
                    TUser tUser = tUserService.findById(Long.parseLong(uid));
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserId(tUser.getfKeyId());
                    logAction.setfUserName(tUser.getfName());
                    logAction.setfType(3);
                    logAction.setfPath("projectType/updateprojectType{" + key + "}");
                    logAction.setfUserType(1);

                    logAction.setfMemo("项目类型名称【"+projectType1.getFname()+"】修改为【"+Fname+"】。");
                    logActionService.save(logAction);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }


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
     * 删除项目类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("删除项目类型信息")
    @ResponseBody
    @RequestMapping(value = "/delprojectType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delprojectType(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLoginprojectTypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginprojectTypeID != null && !CookiesLoginprojectTypeID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            TProjectExample tMaterialExample = new TProjectExample();
            tMaterialExample.createCriteria().andFtypeidEqualTo(Long.parseLong(id));
            List<TProject> projectList = tProjectService.findByExample(tMaterialExample);
            if (projectList.size() > 0) {
                object.put("result", "error");
                object.put("error", "当前类型在项目信息中被使用，不能删除！");
            } else {
                TProjectType projectType = tProjectTypeService.findById(Long.parseLong(id));

                tProjectTypeService.deleteById(Long.parseLong(id));


                try {
                    TUser tUser = tUserService.findById(Long.parseLong(uid));
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserId(tUser.getfKeyId());
                    logAction.setfUserName(tUser.getfName());
                    logAction.setfType(3);
                    logAction.setfPath("projectType/delprojectType{" + id + "}");
                    logAction.setfUserType(1);

                    logAction.setfMemo("删除项目类型【"+projectType.getFname()+"】信息。");
                    logActionService.save(logAction);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                // 返回值
                object.put("result", "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 变更项目类型信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/stateprojectType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stateprojectType(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLoginprojectTypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginprojectTypeID != null && !CookiesLoginprojectTypeID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";

            TProjectType projectType1 = tProjectTypeService.findById(Long.parseLong(id));


            TProjectType projectType = new TProjectType();
            projectType.setFkeyid(Long.parseLong(id));
            projectType.setFuid(Long.parseLong(uid));
            projectType.setFudate(new Date());
            projectType.setFstate(Integer.valueOf(state));
            tProjectTypeService.update(projectType);


            String statestr = "";
            if(Integer.valueOf(state) ==0){
                statestr = "禁用";
            }else{
                statestr = "启用";
            }

            try {
                TUser tUser = tUserService.findById(Long.parseLong(uid));
                TLogAction logAction = new TLogAction();
                logAction.setfUserId(tUser.getfKeyId());
                logAction.setfUserName(tUser.getfName());
                logAction.setfType(3);
                logAction.setfPath("projectType/stateprojectType{" + id + "}");
                logAction.setfUserType(1);

                logAction.setfMemo("项目类型【"+projectType1.getFname()+"】状态变更为【"+statestr+"】。");
                logActionService.save(logAction);
            } catch (NumberFormatException e) {
                e.printStackTrace();
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
     * 验证项目类型是否存在
     */
    private int repeaTProjectType(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TProjectTypeExample projectTypeExample = new TProjectTypeExample();
            TProjectTypeExample.Criteria criteria = projectTypeExample.createCriteria();
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
            List<TProjectType> rojectTypeList = tProjectTypeService.findByExample(projectTypeExample);
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


    //根据ID查询项目类型名称
    public String getName(Long id) {
        TProjectType byId = tProjectTypeService.findById(id);
        if (byId != null) {
            return byId.getFname();
        } else {
            return "";
        }

    }
}