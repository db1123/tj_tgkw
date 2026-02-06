package fun.server.controller.myproject;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.service.TLogActionService;
import fun.server.service.TProjectService;
import fun.server.service.TProjectLevelService;
import fun.server.service.TUserService;
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
 * 项目等级管理 相关业务处理
 */
@RestController
@RequestMapping("/s/projectLevel")
public class ProjectLevel {

    private final TProjectLevelService tProjectLevelService;
    private final TProjectService tProjectService;
    private final TLogActionService logActionService;
    private final TUserService tUserService;
    public ProjectLevel(TProjectLevelService tProjectLevelService, TProjectService tProjectService, TLogActionService logActionService, TUserService tUserService) {
        this.tProjectLevelService = tProjectLevelService;
        this.tProjectService = tProjectService;
        this.logActionService = logActionService;
        this.tUserService = tUserService;
    }


    /**
     * 获取项目等级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryprojectLevel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryprojectLevel(HttpServletRequest request)
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
            JSONArray projectLevel_Array = new JSONArray();
            // 查询条件
            TProjectLevelExample TProjectLevelExample = new TProjectLevelExample();
            TProjectLevelExample.Criteria criteria = TProjectLevelExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");

            }
            TProjectLevelExample.setOrderByClause("FCDATE desc");
            PageInfo<TProjectLevel> projectLevelPageInfo = tProjectLevelService.findByExampleMapper(TProjectLevelExample, (page - 1) * results, results);
            List<TProjectLevel> projectLevel_list = projectLevelPageInfo.getList();

            for (TProjectLevel projectLevel : projectLevel_list) {
                JSONObject projectLevel_object = new JSONObject();
                projectLevel_object.put("key", ParamTools.getEnParam(projectLevel.getFkeyid().toString()));
                projectLevel_object.put("FName", projectLevel.getFname());
                projectLevel_object.put("FCID", projectLevel.getFcid());
                projectLevel_object.put("FUID", projectLevel.getFuid());
                projectLevel_object.put("FCDATE", projectLevel.getFcdate());
                projectLevel_object.put("FUDATE", projectLevel.getFudate());
                projectLevel_object.put("FState", projectLevel.getFstate());
                projectLevel_Array.add(projectLevel_object);
            }
            // 返回值
            object.put("list", projectLevel_Array);
            object.put("total", projectLevelPageInfo.getTotal()); // 总行数
            object.put("page", projectLevelPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取项目等级信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDataprojectLevelSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDataprojectLevelSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray projectLevel_Array = new JSONArray();
            TProjectLevelExample projectLevelExample = new TProjectLevelExample();
            TProjectLevelExample.Criteria criteria = projectLevelExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            projectLevelExample.setOrderByClause("fname asc");
            List<TProjectLevel> projectLevel_list = tProjectLevelService.findByExample(projectLevelExample);
            for (TProjectLevel projectLevel : projectLevel_list) {
                JSONObject projectLevel_object = new JSONObject();
                projectLevel_object.put("id", ParamTools.getEnParam(projectLevel.getFkeyid().toString()));
                projectLevel_object.put("text", projectLevel.getFname());
                projectLevel_Array.add(projectLevel_object);
            }
            // 返回值

            object.put("list", projectLevel_Array);
            object.put("result", "success");
            object.put("results", projectLevel_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取项目等级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryprojectLevelInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryprojectLevelInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询项目等级信息
            TProjectLevel projectLevel = tProjectLevelService.findById(key);
            JSONObject projectLevel_object = new JSONObject();
            projectLevel_object.put("key", ParamTools.getEnParam(projectLevel.getFkeyid().toString()));
            projectLevel_object.put("FName", projectLevel.getFname());
            projectLevel_object.put("FCID", projectLevel.getFcid());
            projectLevel_object.put("FUID", projectLevel.getFuid());
            projectLevel_object.put("FCDATE", projectLevel.getFcdate());
            projectLevel_object.put("FUDATE", projectLevel.getFudate());
            projectLevel_object.put("FState", projectLevel.getFstate());
            // 返回值
            object.put("info", projectLevel_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加项目等级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("添加项目等级信息")
    @ResponseBody
    @RequestMapping(value = "/addprojectLevel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addprojectLevel(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String Fname = jsonParam.getString("Fname");

        try {
            if (repeaTProjectLevel(0L, Fname, "1") == 0) {
                String CookiesLoginprojectLevelID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginprojectLevelID != null && !CookiesLoginprojectLevelID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginprojectLevelID);
                }
                // 新建项目等级信息
                TProjectLevel projectLevel = new TProjectLevel();
                projectLevel.setFname(Fname);
                projectLevel.setFcid(Long.parseLong(uid));
                projectLevel.setFcdate(new Date());
                tProjectLevelService.save(projectLevel);

                TUser tUser = tUserService.findById(Long.parseLong(uid));
                TLogAction logAction = new TLogAction();
                logAction.setfUserId(tUser.getfKeyId());
                logAction.setfUserName(tUser.getfName());
                logAction.setfType(3);
                logAction.setfPath("projectLevel/addprojectLevel{" + projectLevel.getFkeyid() + "}");
                logAction.setfUserType(1);

                logAction.setfMemo("新增项目等级【"+Fname+"】信息。");
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
     * 修改项目等级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("修改项目等级信息")
    @ResponseBody
    @RequestMapping(value = "/updateprojectLevel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateprojectLevel(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String Fname = jsonParam.getString("Fname");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            if (repeaTProjectLevel(key, Fname, "2") == 0) {
                String CookiesLoginprojectLevelID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginprojectLevelID != null && !CookiesLoginprojectLevelID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginprojectLevelID);
                }

                TProjectLevel projectLevel1 = tProjectLevelService.findById(key);

                // 更新项目等级信息
                TProjectLevel projectLevel = new TProjectLevel();
                projectLevel.setFkeyid(key);
                projectLevel.setFname(Fname);
                projectLevel.setFuid(Long.parseLong(uid));
                projectLevel.setFudate(new Date());
                tProjectLevelService.update(projectLevel);

                TUser tUser = tUserService.findById(Long.parseLong(uid));
                TLogAction logAction = new TLogAction();
                logAction.setfUserId(tUser.getfKeyId());
                logAction.setfUserName(tUser.getfName());
                logAction.setfType(3);
                logAction.setfPath("projectLevel/updateprojectLevel{" + key + "}");
                logAction.setfUserType(1);

                logAction.setfMemo("项目等级名称【"+projectLevel1.getFname()+"】修改为【"+Fname+"】。");
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
     * 删除项目等级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("删除项目等级信息")
    @ResponseBody
    @RequestMapping(value = "/delprojectLevel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delprojectLevel(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLoginprojectLevelID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginprojectLevelID != null && !CookiesLoginprojectLevelID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            TProjectExample tMaterialExample = new TProjectExample();
            tMaterialExample.createCriteria().andFlevelidEqualTo(Long.parseLong(id));
            List<TProject> projectList = tProjectService.findByExample(tMaterialExample);
            if (projectList.size() > 0) {
                object.put("result", "error");
                object.put("error", "当前等级在项目信息中被使用，不能删除！");
            } else {

                TProjectLevel projectLevel = tProjectLevelService.findById(Long.parseLong(id));

                tProjectLevelService.deleteById(Long.parseLong(id));

                TUser tUser = tUserService.findById(Long.parseLong(uid));
                TLogAction logAction = new TLogAction();
                logAction.setfUserId(tUser.getfKeyId());
                logAction.setfUserName(tUser.getfName());
                logAction.setfType(3);
                logAction.setfPath("projectLevel/delprojectLevel{" + id + "}");
                logAction.setfUserType(1);

                logAction.setfMemo("删除项目等级【"+projectLevel.getFname()+"】信息。");
                logActionService.save(logAction);

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
     * 变更项目等级信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/stateprojectLevel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stateprojectLevel(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLoginprojectLevelID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginprojectLevelID != null && !CookiesLoginprojectLevelID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";

            TProjectLevel projectLevel1 = tProjectLevelService.findById(Long.parseLong(id));

            TProjectLevel projectLevel = new TProjectLevel();
            projectLevel.setFkeyid(Long.parseLong(id));
            projectLevel.setFuid(Long.parseLong(uid));
            projectLevel.setFudate(new Date());
            projectLevel.setFstate(Integer.valueOf(state));
            tProjectLevelService.update(projectLevel);

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
            logAction.setfPath("projectLevel/stateprojectLevel{" + id + "}");
            logAction.setfUserType(1);

            logAction.setfMemo("项目等级【"+projectLevel1.getFname()+"】状态变更为【"+statestr+"】。");
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
     * 验证项目等级是否存在
     */
    private int repeaTProjectLevel(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TProjectLevelExample projectLevelExample = new TProjectLevelExample();
            TProjectLevelExample.Criteria criteria = projectLevelExample.createCriteria();
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
            List<TProjectLevel> rojectTypeList = tProjectLevelService.findByExample(projectLevelExample);
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


    //根据ID查询项目等级名称
    public String getName(Long id) {
        TProjectLevel byId = tProjectLevelService.findById(id);
        if (byId != null) {
            return byId.getFname();
        } else {
            return "";
        }

    }
}