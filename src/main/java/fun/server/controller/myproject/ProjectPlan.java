package fun.server.controller.myproject;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.service.*;
import fun.tools.ParamTools;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 计划管理 相关业务处理
 */
@RestController
@RequestMapping("/s/projectPlan")
public class ProjectPlan {

    private final TProjectPlanService tProjectPlanService;
    private final TProjectService tProjectService;
    private final TUserService tUserService;
    private final TSubDataService tSubDataService;
    private final TProjectLogService tProjectLogService;
    private final TProjectExceedService tProjectExceedService;
    private final TProjectTeamService tProjectTeamService;
    private final TProjectPlanBGLogService tProjectPlanBGLogService;

    private final TLogActionService logActionService;
    public ProjectPlan(TProjectPlanService tProjectPlanService, TProjectService tProjectService, TUserService tUserService, TSubDataService tSubDataService, TProjectLogService tProjectLogService, TProjectExceedService tProjectExceedService, TProjectTeamService tProjectTeamService, TProjectPlanBGLogService tProjectPlanBGLogService, TLogActionService logActionService) {
        this.tProjectPlanService = tProjectPlanService;
        this.tProjectService = tProjectService;
        this.tUserService = tUserService;
        this.tSubDataService = tSubDataService;
        this.tProjectLogService = tProjectLogService;
        this.tProjectExceedService = tProjectExceedService;
        this.tProjectTeamService = tProjectTeamService;
        this.tProjectPlanBGLogService = tProjectPlanBGLogService;
        this.logActionService = logActionService;
    }


    /**
     * 获取计划信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryprojectPlan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryprojectPlan(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        String id = jsonParam.getString("id");
        try {
            // 获取数据库记录
            JSONArray projectPlan_Array = new JSONArray();
            // 查询条件
            TProjectPlanExample TProjectPlanExample = new TProjectPlanExample();
            TProjectPlanExample.Criteria criteria = TProjectPlanExample.createCriteria();
            if (id != null && !id.equals("")) {
                criteria.andFprojectidEqualTo(Long.parseLong(ParamTools.getdeParam(id)));
            }
            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }
            TProjectPlanExample.setOrderByClause("FCDATE desc");
            PageInfo<TProjectPlan> projectPlanPageInfo = tProjectPlanService.findByExampleMapper(TProjectPlanExample, (page - 1) * results, results);
            List<TProjectPlan> projectPlan_list = projectPlanPageInfo.getList();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int no = 1;
            for (TProjectPlan projectPlan : projectPlan_list) {
                JSONObject projectPlan_object = new JSONObject();
                projectPlan_object.put("no", no);
                projectPlan_object.put("key", ParamTools.getEnParam(projectPlan.getFkeyid().toString()));
                projectPlan_object.put("FEndState", projectPlan.getFendstate());
                switch (projectPlan.getFendstate()) {
                    case 0:
                        projectPlan_object.put("FEndStateName", "未完成");
                        break;
                    case 1:
                        projectPlan_object.put("FEndStateName", "按时完成");
                        break;
                    case 2:
                        projectPlan_object.put("FEndStateName", "逾期完成");
                        break;
                }
                projectPlan_object.put("FProjectID", ParamTools.getEnParam(projectPlan.getFprojectid().toString()));
                projectPlan_object.put("FName", projectPlan.getFname());
                projectPlan_object.put("FUserID", ParamTools.getEnParam(projectPlan.getFuserid().toString()));
                TUser tUser = tUserService.findById(projectPlan.getFuserid());
                projectPlan_object.put("FUserName", tUser.getfName() == null ? "" : tUser.getfName());
                projectPlan_object.put("FPSDate", projectPlan.getFpsdate() == null ? "" : sdf.format(projectPlan.getFpsdate()));
                projectPlan_object.put("FPEDate", projectPlan.getFpedate() == null ? "" : sdf.format(projectPlan.getFpedate()));
                projectPlan_object.put("FStartDate", projectPlan.getFstartdate() == null ? "" : sdf.format(projectPlan.getFstartdate()));
                projectPlan_object.put("FEndDate", projectPlan.getFenddate() == null ? "" : sdf.format(projectPlan.getFenddate()));
                projectPlan_object.put("FISMilepost", projectPlan.getFismilepost());
                projectPlan_object.put("FISMilepostName", projectPlan.getFismilepost() == 0 ? "否" : "是");
                projectPlan_object.put("FISSubData", projectPlan.getFissubdata());
                projectPlan_object.put("FISSubDataName", projectPlan.getFissubdata() == 0 ? "不需要" : "需要");
                projectPlan_object.put("FNote", projectPlan.getFnote());
                projectPlan_object.put("FCID", projectPlan.getFcid());
                projectPlan_object.put("FUID", projectPlan.getFuid());
                projectPlan_object.put("FCDATE", projectPlan.getFcdate() == null ? "" : sdf.format(projectPlan.getFcdate()));
                projectPlan_object.put("FUDATE", projectPlan.getFudate() == null ? "" : sdf.format(projectPlan.getFudate()));
                projectPlan_object.put("fstate", projectPlan.getFstate());
                switch (projectPlan.getFstate()) {
                    case 0:
                        projectPlan_object.put("FStateName", "未开");
                        break;
                    case 1:
                        projectPlan_object.put("FStateName", "开始");
                        break;
                    case 2:
                        projectPlan_object.put("FStateName", "完成");
                        break;
                }
                if (projectPlan.getFpsdate() != null && projectPlan.getFpedate() != null) {
                    projectPlan_object.put("PSPEDate", sdf.format(projectPlan.getFpsdate()) + "~" + sdf.format(projectPlan.getFpedate()));
                } else {
                    projectPlan_object.put("PSPEDate", "无");
                }
                int sum = 0;//总数
                int num = 0;//已提交数
                //查询交付物数量
                //全部数量
                TSubdataExample tSubdataExample = new TSubdataExample();
                TSubdataExample.Criteria criteria1 = tSubdataExample.createCriteria();
                criteria1.andFprojectidEqualTo(projectPlan.getFprojectid());
                criteria1.andFprojectplanidEqualTo(projectPlan.getFkeyid());
                criteria1.andFtypeEqualTo(1);
                List<TSubdata> subdataList = tSubDataService.findByExample(tSubdataExample);
                sum = subdataList.size();
                //已提交的数量
                tSubdataExample = new TSubdataExample();
                criteria1 = tSubdataExample.createCriteria();
                criteria1.andFprojectidEqualTo(projectPlan.getFprojectid());
                criteria1.andFprojectplanidEqualTo(projectPlan.getFkeyid());
                criteria1.andFtypeEqualTo(1);
                criteria1.andFstateEqualTo(1);
                subdataList = tSubDataService.findByExample(tSubdataExample);
                num = subdataList.size();
                projectPlan_object.put("FNum", num + "/" + sum);
                projectPlan_Array.add(projectPlan_object);
                no++;
            }
//            TProject tProject = tProjectService.findById(Long.parseLong(ParamTools.getdeParam(id)));
//            object.put("pfstate", tProject.getFstate());
            // 返回值
//            System.out.println(projectPlan_Array.toString());
            object.put("list", projectPlan_Array);
            object.put("total", projectPlanPageInfo.getTotal()); // 总行数
            object.put("page", projectPlanPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取甘特图数据
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryprojectPlanGantt", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryprojectPlanGantt(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            // 获取数据库记录
            JSONArray projectPlan_Array = new JSONArray();
            // 查询条件
            TProjectPlanExample TProjectPlanExample = new TProjectPlanExample();
            TProjectPlanExample.Criteria criteria = TProjectPlanExample.createCriteria();
            if (id != null && !id.equals("")) {
                criteria.andFprojectidEqualTo(Long.parseLong(ParamTools.getdeParam(id)));
            }
            TProjectPlanExample.setOrderByClause("FPSDate");
            List<TProjectPlan> projectPlan_list = tProjectPlanService.findByExample(TProjectPlanExample);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int no = 1;
            for (TProjectPlan projectPlan : projectPlan_list) {
                JSONObject projectPlan_object = new JSONObject();
                projectPlan_object.put("no", no);
                projectPlan_object.put("name", projectPlan.getFname());
                projectPlan_object.put("id", ParamTools.getEnParam(projectPlan.getFkeyid().toString()));
                projectPlan_object.put("status", (projectPlan.getFendstate() + 1));
                projectPlan_object.put("begin_date", projectPlan.getFpsdate() == null ? "" : sdf.format(projectPlan.getFpsdate()));
                projectPlan_object.put("end_date", projectPlan.getFpedate() == null ? "" : sdf.format(projectPlan.getFpedate()));
                projectPlan_object.put("finish_begin_date", projectPlan.getFstartdate() == null ? "" : sdf.format(projectPlan.getFstartdate()));
                projectPlan_object.put("finish_end_date", projectPlan.getFenddate() == null ? "" : sdf.format(projectPlan.getFenddate()));
                projectPlan_object.put("type", (projectPlan.getFismilepost() + 1));
//                if(projectPlan.getFismilepost() == 1){
//                    projectPlan_object.put("istype", (projectPlan.getFismilepost()+ 1));
//                }else if(projectPlan.getFismilepost() == 0){
//                    projectPlan_object.put("istype", (projectPlan.getFismilepost()+ 1));
//                }
                projectPlan_Array.add(projectPlan_object);
                no++;
            }
            // 返回值
            object.put("list", projectPlan_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取计划信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDataprojectPlanSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDataprojectPlanSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray projectPlan_Array = new JSONArray();
            TProjectPlanExample projectPlanExample = new TProjectPlanExample();
            TProjectPlanExample.Criteria criteria = projectPlanExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            projectPlanExample.setOrderByClause("fname asc");
            List<TProjectPlan> projectPlan_list = tProjectPlanService.findByExample(projectPlanExample);
            for (TProjectPlan projectPlan : projectPlan_list) {
                JSONObject projectPlan_object = new JSONObject();
                projectPlan_object.put("id", ParamTools.getEnParam(projectPlan.getFkeyid().toString()));
                projectPlan_object.put("text", projectPlan.getFname());
                projectPlan_Array.add(projectPlan_object);
            }
            // 返回值

            object.put("list", projectPlan_Array);
            object.put("result", "success");
            object.put("results", projectPlan_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取计划信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryprojectPlanInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryprojectPlanInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询计划信息
            TProjectPlan projectPlan = tProjectPlanService.findById(key);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            JSONObject projectPlan_object = new JSONObject();
            projectPlan_object.put("key", ParamTools.getEnParam(projectPlan.getFkeyid().toString()));
            projectPlan_object.put("FEndState", projectPlan.getFendstate());
            switch (projectPlan.getFendstate()) {
                case 0:
                    projectPlan_object.put("FEndStateName", "未完成");
                    break;
                case 1:
                    projectPlan_object.put("FEndStateName", "按时完成");
                    break;
                case 2:
                    projectPlan_object.put("FEndStateName", "逾期完成");
                    break;
            }
            projectPlan_object.put("FProjectID", ParamTools.getEnParam(projectPlan.getFprojectid().toString()));
            projectPlan_object.put("FName", projectPlan.getFname());
            projectPlan_object.put("FUserID", ParamTools.getEnParam(projectPlan.getFuserid().toString()));
            TUser tUser = tUserService.findById(projectPlan.getFuserid());
            projectPlan_object.put("FUserName", tUser == null ? "" : tUser.getfName());
            projectPlan_object.put("FPSDate", projectPlan.getFpsdate() == null ? "" : sdf.format(projectPlan.getFpsdate()));
            projectPlan_object.put("FPEDate", projectPlan.getFpedate() == null ? "" : sdf.format(projectPlan.getFpedate()));
            projectPlan_object.put("FStartDate", projectPlan.getFstartdate() == null ? "" : sdf.format(projectPlan.getFstartdate()));
            projectPlan_object.put("FEndDate", projectPlan.getFenddate() == null ? "" : sdf.format(projectPlan.getFenddate()));
            projectPlan_object.put("FISMilepost", projectPlan.getFismilepost());
            projectPlan_object.put("FISMilepostName", projectPlan.getFismilepost() == 0 ? "否" : "是");
            projectPlan_object.put("FISSubData", projectPlan.getFissubdata());
            projectPlan_object.put("FISSubDataName", projectPlan.getFissubdata() == 0 ? "不需要" : "需要");
            projectPlan_object.put("FNote", projectPlan.getFnote());
            projectPlan_object.put("FCID", projectPlan.getFcid());
            projectPlan_object.put("FUID", projectPlan.getFuid());
            projectPlan_object.put("FCDATE", projectPlan.getFcdate() == null ? "" : sdf.format(projectPlan.getFcdate()));
            projectPlan_object.put("FUDATE", projectPlan.getFudate() == null ? "" : sdf.format(projectPlan.getFudate()));
            projectPlan_object.put("FState", projectPlan.getFstate());
            switch (projectPlan.getFstate()) {
                case 0:
                    projectPlan_object.put("FStateName", "未开");
                    break;
                case 1:
                    projectPlan_object.put("FStateName", "开始");
                    break;
                case 2:
                    projectPlan_object.put("FStateName", "完成");
                    break;
            }

            // 返回值
            object.put("info", projectPlan_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加计划信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("添加项目计划信息")
    @ResponseBody
    @RequestMapping(value = "/addprojectPlan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addprojectPlan(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FProjectID = jsonParam.getString("FProjectID");
        String FName = jsonParam.getString("FName");
        String FUserID = jsonParam.getString("FUserID");
        String FPSDate = jsonParam.getString("FPSDate");
        String FPEDate = jsonParam.getString("FPEDate");
        int FISMilepost = jsonParam.getInteger("FISMilepost");
        int FISSubData = jsonParam.getInteger("FISSubData");
        String FNote = jsonParam.getString("FNote");
        try {
            FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);
            FUserID = FUserID == null ? "0" : ParamTools.getdeParam(FUserID);
            TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
            if (tProject != null) {
                if (tProject.getFstate() < 2) {
//                    if (repeaTProjectPlan(0L, FName, "1") == 0) {
                    String CookiesLoginprojectPlanID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                    String uid = ""; // 当前登录用户ID
                    if (CookiesLoginprojectPlanID != null && !CookiesLoginprojectPlanID.equals("")) {
                        uid = ParamTools.getdeParam(CookiesLoginprojectPlanID);
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    // 新建计划信息
                    TProjectPlan projectPlan = new TProjectPlan();
                    projectPlan.setFname(FName);
                    projectPlan.setFprojectid(Long.parseLong(FProjectID));
                    projectPlan.setFuserid(Long.parseLong(FUserID));
                    projectPlan.setFpsdate(sdf.parse(FPSDate));
                    projectPlan.setFpedate(sdf.parse(FPEDate));
                    projectPlan.setFismilepost(FISMilepost);
                    projectPlan.setFissubdata(FISSubData);
                    projectPlan.setFnote(FNote);
                    projectPlan.setFcid(Long.parseLong(uid));
                    projectPlan.setFcdate(new Date());
                    tProjectPlanService.save(projectPlan);


                    //添加日志
                    ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                    String fnote = "为项目【" + tProject.getFname() + "】，创建计划【" + FName + "】。";
                    projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "计划信息", "创建计划", "", uid);


                    TUser tUser = tUserService.findById(Long.parseLong(uid));
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserId(tUser.getfKeyId());
                    logAction.setfUserName(tUser.getfName());
                    logAction.setfType(3);
                    logAction.setfPath("projectPlan/addprojectPlan{" + projectPlan.getFkeyid() + "}");
                    logAction.setfUserType(1);

                    logAction.setfMemo(fnote);
                    logActionService.save(logAction);
                    // 返回值
                    object.put("result", "success");
//                    } else {
//                        // 返回值
//                        object.put("result", "fail");
//                    }
                } else {
                    object.put("result", "error");
                    object.put("error", "当前项目已完成，不能修改数据！");
                }
            } else {
                object.put("result", "error");
                object.put("error", "当前项目未找到，请刷新后再试！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 修改计划信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("修改项目计划信息")
    @ResponseBody
    @RequestMapping(value = "/updateprojectPlan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateprojectPlan(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FProjectID = jsonParam.getString("FProjectID");
        String FName = jsonParam.getString("FName");
        String FUserID = jsonParam.getString("FUserID");
        String FPSDate = jsonParam.getString("FPSDate");
        String FPEDate = jsonParam.getString("FPEDate");
        int FISMilepost = jsonParam.getInteger("FISMilepost");
        int FISSubData = jsonParam.getInteger("FISSubData");
        String FNote = jsonParam.getString("FNote");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);

            FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);
            FUserID = FUserID == null ? "0" : ParamTools.getdeParam(FUserID);
            TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
            if (tProject != null) {
                if (tProject.getFstate() < 2) {
//                    if (repeaTProjectPlan(key, FName, "2") == 0) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String CookiesLoginprojectPlanID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                    String uid = ""; // 当前登录用户ID
                    if (CookiesLoginprojectPlanID != null && !CookiesLoginprojectPlanID.equals("")) {
                        uid = ParamTools.getdeParam(CookiesLoginprojectPlanID);
                    }

                    TProjectPlan projectPlan1 = tProjectPlanService.findById(key);

                    // 更新计划信息
                    TProjectPlan projectPlan = new TProjectPlan();
                    projectPlan.setFkeyid(key);
                    projectPlan.setFname(FName);
                    projectPlan.setFprojectid(Long.parseLong(FProjectID));
                    projectPlan.setFuserid(Long.parseLong(FUserID));
                    projectPlan.setFpsdate(sdf.parse(FPSDate));
                    projectPlan.setFpedate(sdf.parse(FPEDate));
                    projectPlan.setFismilepost(FISMilepost);
                    projectPlan.setFissubdata(FISSubData);
                    projectPlan.setFnote(FNote);
                    projectPlan.setFuid(Long.parseLong(uid));
                    projectPlan.setFudate(new Date());
                    tProjectPlanService.update(projectPlan);

                    //添加日志
                    ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                    String fnote ="";
                    if(projectPlan1.getFname().equals(FName))
                        fnote = "项目【" + tProject.getFname() + "】，修改计划【" + FName + "】";
                    else
                        fnote = "项目【" + tProject.getFname() + "】，计划【" +projectPlan1.getFname()+ "】修改为【"+FName+"】";
                    projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "计划信息", "修改计划", "", uid);

                    TUser tUser = tUserService.findById(Long.parseLong(uid));
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserId(tUser.getfKeyId());
                    logAction.setfUserName(tUser.getfName());
                    logAction.setfType(3);
                    logAction.setfPath("projectPlan/updateprojectPlan{" + key + "}");
                    logAction.setfUserType(1);

                    logAction.setfMemo(fnote);
                    logActionService.save(logAction);


                    // 返回值
                    object.put("result", "success");
//                    } else {
//                        // 返回值
//                        object.put("result", "fail");
//                    }
                } else {
                    object.put("result", "error");
                    object.put("error", "当前项目已完成，不能修改数据！");
                }
            } else {
                object.put("result", "error");
                object.put("error", "当前项目未找到，请刷新后再试！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 删除计划信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("删除项目计划信息")
    @ResponseBody
    @RequestMapping(value = "/delprojectPlan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delprojectPlan(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLoginprojectPlanID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginprojectPlanID != null && !CookiesLoginprojectPlanID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            TProjectPlan projectPlan = tProjectPlanService.findById(Long.parseLong(id));
            if (projectPlan != null) {
                TProject tProject = tProjectService.findById(projectPlan.getFprojectid());
                if (tProject != null && tProject.getFstate() < 2) {

                    TProjectPlan projectPlan1 = tProjectPlanService.findById(Long.parseLong(id));
                    if(projectPlan1.getFstate() == 0){
                        tProjectPlanService.deleteById(Long.parseLong(id));
                        //添加日志
                        ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                        String fnote = "项目【" + tProject.getFname() + "】，删除计划【" + projectPlan.getFname() + "】";
                        projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "计划信息", "删除计划", "", uid);


                        TUser tUser = tUserService.findById(Long.parseLong(uid));
                        TLogAction logAction = new TLogAction();
                        logAction.setfUserId(tUser.getfKeyId());
                        logAction.setfUserName(tUser.getfName());
                        logAction.setfType(3);
                        logAction.setfPath("projectPlan/delprojectPlan{" + id + "}");
                        logAction.setfUserType(1);

                        logAction.setfMemo(fnote);
                        logActionService.save(logAction);

                        // 返回值
                        object.put("result", "success");
                    }else{
                        object.put("result", "error");
                        object.put("error", "计划已发出或者完成，不能删除数据！");
                    }

                } else {
                    object.put("result", "error");
                    object.put("error", "当前项目已完成，不能删除数据！");
                }
            } else {
                object.put("result", "error");
                object.put("error", "未找到该计划信息，请刷新后再试！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 变更计划信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/stateprojectPlan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stateprojectPlan(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLoginprojectPlanID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginprojectPlanID != null && !CookiesLoginprojectPlanID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TProjectPlan projectPlan = new TProjectPlan();
            projectPlan.setFkeyid(Long.parseLong(id));
            projectPlan.setFuid(Long.parseLong(uid));
            projectPlan.setFudate(new Date());
            projectPlan.setFstate(Integer.valueOf(state));
            tProjectPlanService.update(projectPlan);
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
     * 计划开始
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("开始项目计划")
    @ResponseBody
    @RequestMapping(value = "/kaishiprojectPlan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String kaishiprojectPlan(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");//计划ID
        String FProjectID = jsonParam.getString("FProjectID");//项目ID
        try {
            String CookiesLoginprojectPlanID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginprojectPlanID != null && !CookiesLoginprojectPlanID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            FProjectID = FProjectID == null ? "0" : (FProjectID.equals("0") ? "0" : ParamTools.getdeParam(FProjectID));


            //查询该计划是否需要交付物
            TProjectPlan tProjectPlan = tProjectPlanService.findById(Long.parseLong(id));
            int isupdate = -2;
            int sum = 0;//总数
            if (tProjectPlan.getFissubdata() == 1) {
                //需要交付物


                //查询交付物数量
                //全部数量
                TSubdataExample tSubdataExample = new TSubdataExample();
                TSubdataExample.Criteria criteria1 = tSubdataExample.createCriteria();
                criteria1.andFprojectidEqualTo(Long.parseLong(FProjectID));
                criteria1.andFprojectplanidEqualTo(Long.parseLong(id));
                criteria1.andFtypeEqualTo(1);
                List<TSubdata> subdataList = tSubDataService.findByExample(tSubdataExample);
                sum = subdataList.size();
                if (sum > 0) {
                    isupdate = 1;
                } else {
                    isupdate = 2;
                }
            } else {
                //不需要
                isupdate = 1;
            }

            if (isupdate == 1) {

                TProjectPlan projectPlan = new TProjectPlan();
                projectPlan.setFkeyid(Long.parseLong(id));
                projectPlan.setFuid(Long.parseLong(uid));
                projectPlan.setFudate(new Date());
                projectPlan.setFstate(1);
                projectPlan.setFstartdate(new Date());
                tProjectPlanService.update(projectPlan);

                //添加日志
                TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
                ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                String fnote = "项目【" + tProject.getFname() + "】，开始执行计划【" + tProjectPlan.getFname() + "】";
                projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "计划信息", "开始计划", "", uid);

                TUser tUser = tUserService.findById(Long.parseLong(uid));
                TLogAction logAction = new TLogAction();
                logAction.setfUserId(tUser.getfKeyId());
                logAction.setfUserName(tUser.getfName());
                logAction.setfType(3);
                logAction.setfPath("projectPlan/kaishiprojectPlan{" + projectPlan.getFkeyid() + "}");
                logAction.setfUserType(1);

                logAction.setfMemo(fnote);
                logActionService.save(logAction);


                // 返回值
                object.put("result", "success");
            } else {
                object.put("result", "error");
                object.put("error", "该计划未设置交付物，请设置后再试！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 完成计划
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("完成项目计划")
    @ResponseBody
    @RequestMapping(value = "/wanchengprojectPlan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String wanchengprojectPlan(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");//计划ID
        String FProjectID = jsonParam.getString("FProjectID");//项目ID
        try {
            String CookiesLoginprojectPlanID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginprojectPlanID != null && !CookiesLoginprojectPlanID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            FProjectID = FProjectID == null ? "0" : (FProjectID.equals("0") ? "0" : ParamTools.getdeParam(FProjectID));
            //查询该计划是否需要交付物
            TProjectPlan tProjectPlan = tProjectPlanService.findById(Long.parseLong(id));
            int isupdate = -2;
            int sum = 0;//总数
            int num = 0;//已提交数
            if (tProjectPlan.getFissubdata() == 1) {
                //需要交付物
                //查询交付物数量
                //全部数量
                TSubdataExample tSubdataExample = new TSubdataExample();
                TSubdataExample.Criteria criteria1 = tSubdataExample.createCriteria();
                criteria1.andFprojectidEqualTo(Long.parseLong(FProjectID));
                criteria1.andFprojectplanidEqualTo(Long.parseLong(id));
                criteria1.andFtypeEqualTo(1);
                List<TSubdata> subdataList = tSubDataService.findByExample(tSubdataExample);
                sum = subdataList.size();
                //已提交的数量
                tSubdataExample = new TSubdataExample();
                criteria1 = tSubdataExample.createCriteria();
                criteria1.andFprojectidEqualTo(Long.parseLong(FProjectID));
                criteria1.andFprojectplanidEqualTo(Long.parseLong(id));
                criteria1.andFtypeEqualTo(1);
                criteria1.andFstateEqualTo(1);
                subdataList = tSubDataService.findByExample(tSubdataExample);
                num = subdataList.size();

                if (num == sum) {
                    isupdate = 1;
                } else {
                    isupdate = 2;
                }
            } else {
                //不需要


                isupdate = 1;
            }
            if (isupdate == 1) {
                //判断提交的交付物是否审核通过， 通过后才可完成
                TSubdataExample tSubdataExample = new TSubdataExample();
                TSubdataExample.Criteria criteria1;
                criteria1 = tSubdataExample.createCriteria();
                criteria1.andFprojectidEqualTo(Long.parseLong(FProjectID));
                criteria1.andFprojectplanidEqualTo(Long.parseLong(id));
                criteria1.andFtypeEqualTo(1);
                criteria1.andFcheckstateEqualTo(2);
                List<TSubdata> subdataList = tSubDataService.findByExample(tSubdataExample);
                if (subdataList.size() == sum) {
                    TProjectPlan projectPlan = new TProjectPlan();
                    projectPlan.setFkeyid(Long.parseLong(id));
                    projectPlan.setFuid(Long.parseLong(uid));
                    projectPlan.setFudate(new Date());
                    projectPlan.setFstate(2);
                    Date nowTime = new Date();
                    //判断完成状态
                    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date startTime = tProjectPlan.getFpsdate();
                    Date endTime = tProjectPlan.getFpedate();

                    boolean effectiveDate = isEffectiveDate(nowTime, startTime, endTime);
                    if (effectiveDate) {
                        //当前时间在范围内
                        projectPlan.setFendstate(1);
                    } else {
                        //当前时间在不在范围内
                        projectPlan.setFendstate(2);
                    }
                    projectPlan.setFenddate(nowTime);
                    projectPlan.setFstartdate(new Date());
                    tProjectPlanService.update(projectPlan);

                    //添加日志
                    TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
                    ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                    String fnote = "项目【" + tProject.getFname() + "】，完成计划【" + tProjectPlan.getFname() + "】";
                    projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "计划信息", "完成计划", "", uid);

                    TUser tUser = tUserService.findById(Long.parseLong(uid));
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserId(tUser.getfKeyId());
                    logAction.setfUserName(tUser.getfName());
                    logAction.setfType(3);
                    logAction.setfPath("projectPlan/wanchengprojectPlan{" + projectPlan.getFkeyid() + "}");
                    logAction.setfUserType(1);

                    logAction.setfMemo(fnote);
                    logActionService.save(logAction);

                    // 返回值
                    object.put("result", "success");
                } else {
                    object.put("result", "error");
                    object.put("error", "该计划中有未完成审核的文件，不能完成计划！");
                }


            } else {
                object.put("result", "error");
                object.put("error", "该计划交付物未全部提交，请提交后再试！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 验证计划是否存在
     */
    private int repeaTProjectPlan(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TProjectPlanExample projectPlanExample = new TProjectPlanExample();
            TProjectPlanExample.Criteria criteria = projectPlanExample.createCriteria();
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
            List<TProjectPlan> rojectTypeList = tProjectPlanService.findByExample(projectPlanExample);
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


    //根据ID查询计划名称
    public String getName(Long id) {
        TProjectPlan byId = tProjectPlanService.findById(id);
        if (byId != null) {
            return byId.getFname();
        } else {
            return "";
        }

    }


    /**
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author sunran   判断当前时间在时间区间内
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 获取计划信息-预警 逾期
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryprojectPlanyuqi", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryprojectPlanyuqi(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String jihua = jsonParam.getString("jihua");//计划名称
        String xiangmu = jsonParam.getString("xiangmu");//项目名称
        try {
            // 获取数据库记录
            JSONArray projectPlan_Array = new JSONArray();
            List<Long> pid = new ArrayList<>();

            String CookiesLoginprojectPlanID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginprojectPlanID != null && !CookiesLoginprojectPlanID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            // 查询条件
            TProjectPlanExample TProjectPlanExample = new TProjectPlanExample();
            TProjectPlanExample.Criteria criteria = TProjectPlanExample.createCriteria();
            //查询 项目名称
            if (xiangmu != null && !xiangmu.equals("")) {
                //根据项目名称， 模糊查询出 项目ID
                TProjectExample tProjectExample = new TProjectExample();
                tProjectExample.or().andFnameLike("%" + xiangmu + "%");
                List<TProject> projectList = tProjectService.findByExample(tProjectExample);
                if (projectList != null && projectList.size() > 0) {
                    for (TProject t : projectList) {
                        pid.add(t.getFkeyid());
                    }
                }
            }
            //查询计划名称
            if (jihua != null && !jihua.equals("")) {
                criteria.andFnameLike("%" + jihua + "%");
            }
            if (pid.size() > 0) {
                criteria.andFprojectidIn(pid);
            }
            criteria.andFuseridEqualTo(Long.parseLong(uid));
            criteria.andFendstateEqualTo(0);
            TProjectPlanExample.setOrderByClause("FCDATE desc");
            PageInfo<TProjectPlan> projectPlanPageInfo = tProjectPlanService.findByExampleMapper(TProjectPlanExample, (page - 1) * results, results);
            List<TProjectPlan> projectPlan_list = projectPlanPageInfo.getList();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int no = 1;
            TProjectExceed projectExceed = null;
            int yuqiday = 0;
            Date newdate = null;
            Date fedate = null;
            int dayDiffer = -1;
            List<Long> yuqiid = new ArrayList<>();
            for (TProjectPlan projectPlan : projectPlan_list) {
                JSONObject projectPlan_object = new JSONObject();
                //检查是否逾期
                projectExceed = tProjectExceedService.findById(3l);
                //获取提前几天 提醒
                yuqiday = projectExceed.getFday() == null ? 0 : projectExceed.getFday();
                //获取当前时间
                newdate = new Date();
                //获取计划完成时间
                fedate = projectPlan.getFpedate();
                //判断 当前时间与计划完成时间的差值
                dayDiffer = getDayDiffer(newdate, fedate);
                if (dayDiffer <= yuqiday && dayDiffer >= 0) {
                    //预警
                    yuqiid.add(projectPlan.getFkeyid());

                } else if (dayDiffer < 0) {
                    //逾期
                    yuqiid.add(projectPlan.getFkeyid());
                }

            }
            if (yuqiid.size() <= 0) {
                yuqiid.add(0l);
            }
            TProjectPlanExample = new TProjectPlanExample();
            TProjectPlanExample.or().andFkeyidIn(yuqiid);
            TProjectPlanExample.setOrderByClause("FCDATE desc");
            projectPlanPageInfo = tProjectPlanService.findByExampleMapper(TProjectPlanExample, (page - 1) * results, results);
            projectPlan_list = projectPlanPageInfo.getList();
            for (TProjectPlan projectPlan : projectPlan_list) {
                JSONObject projectPlan_object = new JSONObject();
                //检查是否逾期
                projectExceed = tProjectExceedService.findById(3l);
                //获取提前几天 提醒
                yuqiday = projectExceed.getFday() == null ? 0 : projectExceed.getFday();
                //获取当前时间
                newdate = new Date();
                //获取计划完成时间
                fedate = projectPlan.getFpedate();
                //判断 当前时间与计划完成时间的差值
                dayDiffer = getDayDiffer(newdate, fedate);
                if (dayDiffer <= yuqiday && dayDiffer >= 0) {
                    //预警

                    projectPlan_object.put("FStates", 1);
                } else if (dayDiffer < 0) {
                    //逾期

                    projectPlan_object.put("FStates", 2);
                } else {
                    //正常
                    projectPlan_object.put("FStates", 3);
                }
                projectPlan_object.put("no", no);
                projectPlan_object.put("key", ParamTools.getEnParam(projectPlan.getFkeyid().toString()));
                projectPlan_object.put("FEndState", projectPlan.getFendstate());
                switch (projectPlan.getFendstate()) {
                    case 0:
                        projectPlan_object.put("FEndStateName", "未完成");
                        break;
                    case 1:
                        projectPlan_object.put("FEndStateName", "按时完成");
                        break;
                    case 2:
                        projectPlan_object.put("FEndStateName", "逾期完成");
                        break;
                }
                projectPlan_object.put("FProjectID", ParamTools.getEnParam(projectPlan.getFprojectid().toString()));
                TProject projectname = tProjectService.findById(projectPlan.getFprojectid());
                projectPlan_object.put("FProjectName", projectname.getFname() == null ? "" : projectname.getFname());
                if (projectname.getFmaterialid() == null)
                    projectPlan_object.put("isFMatertial", 0);
                else
                    projectPlan_object.put("isFMatertial", 1);
                projectPlan_object.put("FName", projectPlan.getFname());
                projectPlan_object.put("FUserID", ParamTools.getEnParam(projectPlan.getFuserid().toString()));
                TUser tUser = tUserService.findById(projectPlan.getFuserid());
                projectPlan_object.put("FUserName", tUser.getfName() == null ? "" : tUser.getfName());
                projectPlan_object.put("FPSDate", projectPlan.getFpsdate() == null ? "" : sdf.format(projectPlan.getFpsdate()));
                projectPlan_object.put("FPEDate", projectPlan.getFpedate() == null ? "" : sdf.format(projectPlan.getFpedate()));
                projectPlan_object.put("FStartDate", projectPlan.getFstartdate() == null ? "" : sdf.format(projectPlan.getFstartdate()));
                projectPlan_object.put("FEndDate", projectPlan.getFenddate() == null ? "" : sdf.format(projectPlan.getFenddate()));
                projectPlan_object.put("FISMilepost", projectPlan.getFismilepost());
                projectPlan_object.put("FISMilepostName", projectPlan.getFismilepost() == 0 ? "否" : "是");
                projectPlan_object.put("FISSubData", projectPlan.getFissubdata());
                projectPlan_object.put("FISSubDataName", projectPlan.getFissubdata() == 0 ? "不需要" : "需要");
                projectPlan_object.put("FNote", projectPlan.getFnote());
                projectPlan_object.put("FCID", projectPlan.getFcid());
                projectPlan_object.put("FUID", projectPlan.getFuid());
                projectPlan_object.put("FCDATE", projectPlan.getFcdate() == null ? "" : sdf.format(projectPlan.getFcdate()));
                projectPlan_object.put("FUDATE", projectPlan.getFudate() == null ? "" : sdf.format(projectPlan.getFudate()));
                projectPlan_object.put("fstate", projectPlan.getFstate());
                switch (projectPlan.getFstate()) {
                    case 0:
                        projectPlan_object.put("FStateName", "未开");
                        break;
                    case 1:
                        projectPlan_object.put("FStateName", "开始");
                        break;
                    case 2:
                        projectPlan_object.put("FStateName", "完成");
                        break;
                }
                if (projectPlan.getFpsdate() != null && projectPlan.getFpedate() != null) {
                    projectPlan_object.put("PSPEDate", sdf.format(projectPlan.getFpsdate()) + "~" + sdf.format(projectPlan.getFpedate()));
                } else {
                    projectPlan_object.put("PSPEDate", "无");
                }
                projectPlan_Array.add(projectPlan_object);
                no++;
            }

            // 返回值
            object.put("list", projectPlan_Array);
            object.put("total", projectPlanPageInfo.getTotal()); // 总行数
            object.put("page", projectPlanPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    public static int getDayDiffer(Date startDate, Date endDate) {
        long startDateTime = 0L;
        long endDateTime = 0L;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            startDateTime = dateFormat.parse(dateFormat.format(startDate)).getTime();
            endDateTime = dateFormat.parse(dateFormat.format(endDate)).getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int) ((endDateTime - startDateTime) / (1000 * 3600 * 24));
    }

    /**
     * 获取逾期项目计划总数
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryyuqiyProjectPlanCount", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryyuqiyProjectPlanCount(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            // 查询条件
            TProjectPlanExample TProjectPlanExample = new TProjectPlanExample();
            TProjectPlanExample.Criteria criteria = TProjectPlanExample.createCriteria();
            criteria.andFuseridEqualTo(Long.parseLong(uid));
            List<TProjectPlan> projectPlanList = tProjectPlanService.findByExample(TProjectPlanExample);
            TProjectExceed projectExceed = null;
            int yuqiday = 0;
            Date newdate = null;
            Date fedate = null;
            int dayDiffer = -1;
            List<Long> yuqiid = new ArrayList<>();
            for (TProjectPlan projectPlan : projectPlanList) {
                JSONObject projectPlan_object = new JSONObject();
                //检查是否逾期
                projectExceed = tProjectExceedService.findById(3l);
                //获取提前几天 提醒
                yuqiday = projectExceed.getFday() == null ? 0 : projectExceed.getFday();
                //获取当前时间
                newdate = new Date();
                //获取计划完成时间
                fedate = projectPlan.getFpedate();
                //判断 当前时间与计划完成时间的差值
                dayDiffer = getDayDiffer(newdate, fedate);
                if (dayDiffer <= yuqiday && dayDiffer >= 0) {
                    //预警
                    // yuqiid.add(projectPlan.getFkeyid());

                } else if (dayDiffer < 0) {
                    //逾期
                    yuqiid.add(projectPlan.getFkeyid());
                }

            }
            // 返回值
            object.put("total", yuqiid.size()); // 总行数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 变更计划时间信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/updateprojectPlanBGLog", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateprojectPlanBGLog(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FProjectID = jsonParam.getString("FProjectID");
        String FProjectPlanID = jsonParam.getString("FProjectPlanID");
        String FPEDate = jsonParam.getString("FPEDate");
        String FPSDate = jsonParam.getString("FPSDate");
        String FNote = jsonParam.getString("FNote");

        int FplanState =Integer.parseInt(jsonParam.getString("FplanState"));
        int sum = Integer.parseInt(jsonParam.getString("sum"));

        try {
            FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);
            FProjectPlanID = FProjectPlanID == null ? "0" : ParamTools.getdeParam(FProjectPlanID);
            TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));

            //根据项目计划查询名称
            TProjectPlan projectPlan1 = tProjectPlanService.findById(Long.parseLong(FProjectPlanID));

            if (projectPlan1 != null) {
                if (projectPlan1.getFstate() < 2) {
                    String CookiesLoginprojectPlanID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                    String uid = ""; // 当前登录用户ID
                    if (CookiesLoginprojectPlanID != null && !CookiesLoginprojectPlanID.equals("")) {
                        uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
                    }

                    //判断是否负责人
                    if(projectPlan1.getFuserid().longValue() == Long.parseLong(uid)){
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        //是

                        //修改计划时间
                        TProjectPlan tProjectPlan = new TProjectPlan();
                        if(FPSDate!=null){
                            tProjectPlan.setFpsdate(sdf.parse(FPSDate));
                        }else{
                            tProjectPlan.setFpsdate(projectPlan1.getFstartdate());
                        }
                        tProjectPlan.setFpedate(sdf.parse(FPEDate));
                        tProjectPlan.setFudate(new Date());
                        tProjectPlan.setFuid(Long.parseLong(uid));
                        tProjectPlan.setFkeyid(Long.parseLong(FProjectPlanID));
                        tProjectPlanService.update(tProjectPlan);

                        //新增变更计划日志表
                        TProjectPlanBglog tProjectPlanBglog = new TProjectPlanBglog();
                        tProjectPlanBglog.setFprojectid(Long.parseLong(FProjectID));
                        tProjectPlanBglog.setFprojectplanid(Long.parseLong(FProjectPlanID));
                        tProjectPlanBglog.setFqpsdate(projectPlan1.getFpsdate());
                        tProjectPlanBglog.setFqpedate(projectPlan1.getFpedate());
                        if(FPSDate!=null){
                            tProjectPlanBglog.setFpsdate(sdf.parse(FPSDate));
                        }else{
                            tProjectPlanBglog.setFpsdate(projectPlan1.getFpsdate());
                        }
                        tProjectPlanBglog.setFpedate(sdf.parse(FPEDate));
                        tProjectPlanBglog.setFnote(FNote);
                        tProjectPlanBglog.setFcid(Long.parseLong(uid));
                        tProjectPlanBglog.setFcdate(new Date());
                        tProjectPlanBGLogService.save(tProjectPlanBglog);

                        //添加日志
                        ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                        String fnote = "计划【" + projectPlan1.getFname()  + "】，变更计划时间,变更后时间为："+sdf.format(projectPlan1.getFpsdate()) +"~"+FPEDate;
                        projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "计划信息", "变更计划时间", "", uid);
                        // 返回值
                        object.put("result", "success");
                    }else{
                        //否
                        object.put("result", "error");
                        object.put("error", "您不是当前计划负责人，不能变更！");
                    }

                } else {
                    object.put("result", "error");
                    object.put("error", "当前计划已完成，不能变更！");
                }
            } else {
                object.put("result", "error");
                object.put("error", "当前计划未找到，请刷新后再试！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取计划变更信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryprojectPlanbglog", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryprojectPlanbglog(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");

        String id = jsonParam.getString("id");//项目ID
        try {
            id =id ==null? "0" : id.equals("0") ? "0" :  ParamTools.getdeParam(id);
            // 获取数据库记录
            JSONArray projectPlan_Array = new JSONArray();
            // 查询条件
            TProjectPlanBglogExample tProjectPlanBglogExample = new TProjectPlanBglogExample();
            TProjectPlanBglogExample.Criteria criteria = tProjectPlanBglogExample.createCriteria();
            TProjectPlan projectPlan = null;

            criteria.andFprojectidEqualTo(Long.parseLong( id));
            tProjectPlanBglogExample.setOrderByClause("FCDATE desc");
            PageInfo<TProjectPlanBglog> projectPlanbglogPageInfo = tProjectPlanBGLogService.findByExampleMapper(tProjectPlanBglogExample, (page - 1) * results, results);
            List<TProjectPlanBglog> projectPlanbglogPageInfoList = projectPlanbglogPageInfo.getList();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int no = 1;
            for (TProjectPlanBglog tProjectPlanBglog : projectPlanbglogPageInfoList) {
                projectPlan = tProjectPlanService.findById(tProjectPlanBglog.getFprojectplanid());
                JSONObject projectPlan_object = new JSONObject();
                projectPlan_object.put("no", no);
                projectPlan_object.put("FName", projectPlan == null ? "" : projectPlan.getFname() == null ? "" :projectPlan.getFname());
                projectPlan_object.put("key", ParamTools.getEnParam(tProjectPlanBglog.getFkeyid().toString()));
                if (tProjectPlanBglog.getFqpsdate() != null && tProjectPlanBglog.getFqpedate() != null) {
                    projectPlan_object.put("PSPEDate", sdf.format(tProjectPlanBglog.getFqpsdate()) + "~" + sdf.format(tProjectPlanBglog.getFqpedate()));
                } else {
                    projectPlan_object.put("PSPEDate", "无");
                }
                if (tProjectPlanBglog.getFpsdate() != null && tProjectPlanBglog.getFpedate() != null) {
                    projectPlan_object.put("PhSPEDate", sdf.format(tProjectPlanBglog.getFpsdate()) + "~" + sdf.format(tProjectPlanBglog.getFpedate()));
                } else {
                    projectPlan_object.put("PhSPEDate", "无");
                }
                TUser tUser = tUserService.findById(tProjectPlanBglog.getFcid());
                projectPlan_object.put("FCID", tUser == null ? "" : tUser.getfName() == null ? "" : tUser.getfName());
                projectPlan_object.put("FCDATE", sdf2.format(tProjectPlanBglog.getFcdate()));
                projectPlan_object.put("FNote", tProjectPlanBglog.getFnote() == null ? "" : tProjectPlanBglog.getFnote());
                projectPlan_Array.add(projectPlan_object);
                no++;
            }

            object.put("list", projectPlan_Array);
            object.put("total", projectPlanbglogPageInfo.getTotal()); // 总行数
            object.put("page", projectPlanbglogPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

}