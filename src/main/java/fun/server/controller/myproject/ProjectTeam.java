package fun.server.controller.myproject;

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
import java.util.Date;
import java.util.List;

/**
 * 团队成员管理 相关业务处理
 */
@RestController
@RequestMapping("/s/myprojectteam")
public class ProjectTeam {

    private final TProjectTeamService tProjectTeamService;
    private final TUserService tUserService;
    private final TDeptService tDeptService;
    private final TProjectService tProjectService;
    private final TProjectLogService tProjectLogService;
    private final TLogActionService logActionService;

    public ProjectTeam(TProjectTeamService tProjectTeamService, TUserService tUserService, TDeptService tDeptService, TProjectService tProjectService, TProjectLogService tProjectLogService, TLogActionService logActionService) {
        this.tProjectTeamService = tProjectTeamService;
        this.tUserService = tUserService;
        this.tDeptService = tDeptService;
        this.tProjectService = tProjectService;
        this.tProjectLogService = tProjectLogService;
        this.logActionService = logActionService;
    }


    /**
     * 获取团队成员信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querymyprojectteam", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querymyprojectteam(HttpServletRequest request)
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

            if (id != null) {
                if (!id.equals("0")) {
                    id = ParamTools.getdeParam(id);
                }
            } else {
                id = "0";
            }
            // 获取数据库记录
            JSONArray myprojectteam_Array = new JSONArray();

            List<TUser> userList = null;
            TUserExample tUserExample = new TUserExample();
            TUserExample.Criteria criteria1 = tUserExample.createCriteria();
            if (name != null && !name.equals("")) {
                criteria1.andFNameLike("%" + name + "%");
                userList = tUserService.findByExample(tUserExample);
            }
            List<Long> useridlist = null;
            // 查询条件
            TProjectTeamExample TProjectTeamExample = new TProjectTeamExample();
            TProjectTeamExample.Criteria criteria = TProjectTeamExample.createCriteria();
            if (userList != null && userList.size() > 0) {
                for (TUser tuser : userList) {
                    useridlist.add(tuser.getfKeyId());
                }
                criteria.andFuseridIn(useridlist);
            }
            String CookiesLoginmyprojectteamID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectteamID != null && !CookiesLoginmyprojectteamID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginmyprojectteamID);
            }
            criteria.andFprojectidEqualTo(Long.parseLong(id));
            TProjectTeamExample.setOrderByClause("FRole desc,FCDATE");
            PageInfo<TProjectTeam> myprojectteamPageInfo = tProjectTeamService.findByExampleMapper(TProjectTeamExample, (page - 1) * results, results);
            List<TProjectTeam> myprojectteam_list = myprojectteamPageInfo.getList();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int isrole = 3;
            int no = 1;
            String dquserid = "";
            String dqusername = "";
            for (TProjectTeam myprojectteam : myprojectteam_list) {
                JSONObject myprojectteam_object = new JSONObject();
                myprojectteam_object.put("no", no);
                myprojectteam_object.put("key", ParamTools.getEnParam(myprojectteam.getFkeyid().toString()));
                myprojectteam_object.put("FProjectID", ParamTools.getEnParam(myprojectteam.getFprojectid().toString()));
                TUser tUser = tUserService.findById(myprojectteam.getFuserid());
                myprojectteam_object.put("FUserID", myprojectteam.getFuserid() == null ? "" : ParamTools.getEnParam(myprojectteam.getFuserid().toString()));
                myprojectteam_object.put("FUserName", tUser == null ? "" : tUser.getfName());
                myprojectteam_object.put("FUserNo", tUser == null ? "" : tUser.getfUserno());
                if (tUser != null) {
                    TDept tDept = tDeptService.findById(tUser.getfDept());
                    myprojectteam_object.put("FUserDept", tDept == null ? "" : tDept.getFname());
                } else {
                    myprojectteam_object.put("FUserDept", "");
                }
                myprojectteam_object.put("FRole", myprojectteam.getFrole());
                if (myprojectteam.getFrole() == 0) {
                    myprojectteam_object.put("FRoleName", "参与人");
                } else if (myprojectteam.getFrole() == 1) {
                    myprojectteam_object.put("FRoleName", "负责人");
                }
                myprojectteam_object.put("FDuty", myprojectteam.getFduty() == null ? "" : myprojectteam.getFduty());
                myprojectteam_object.put("FNote", myprojectteam.getFnote() == null ? "" : myprojectteam.getFnote());
                myprojectteam_object.put("FCID", myprojectteam.getFcid() == null ? "" : myprojectteam.getFcid());
                myprojectteam_object.put("FUID", myprojectteam.getFuid() == null ? "" : myprojectteam.getFuid());
                myprojectteam_object.put("FCDATE", sdf.format(myprojectteam.getFcdate()));
                myprojectteam_object.put("FUDATE", myprojectteam.getFudate() == null ? "" : sdf.format(myprojectteam.getFudate()));
                myprojectteam_object.put("FState", myprojectteam.getFstate());
                if (myprojectteam.getFstate() == 1)
                    myprojectteam_object.put("FStateName", "活跃");
                else
                    myprojectteam_object.put("FStateName", "禁用");
                //添加验证是否负责人
//                if (Long.parseLong(uid) == myprojectteam.getFuserid().longValue() && myprojectteam.getFrole() == 1) {
//                    isrole = 2;
//                    dquserid = ParamTools.getEnParam(myprojectteam.getFkeyid().toString());
//                    dqusername = tUser == null ? "" : tUser.getfName();
//                }
//                //不验证是否负责人
                if (Long.parseLong(uid) == myprojectteam.getFuserid().longValue() && myprojectteam.getFrole() == 1) {
                    isrole = 2;
                    dquserid = ParamTools.getEnParam(myprojectteam.getFkeyid().toString());
                    dqusername = tUser == null ? "" : tUser.getfName();
                } else {
                    isrole = 2;
                    dquserid = ParamTools.getEnParam(myprojectteam.getFkeyid().toString());
                    dqusername = tUser == null ? "" : tUser.getfName();
                }
                myprojectteam_Array.add(myprojectteam_object);
                no++;
            }
            // 返回值
            object.put("list", myprojectteam_Array);
            object.put("isrole", isrole);
            object.put("dquserid", dquserid);
            object.put("dqusername", dqusername);
            object.put("total", myprojectteamPageInfo.getTotal()); // 总行数
            object.put("page", myprojectteamPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取团队成员信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatamyprojectteamSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatamyprojectteamSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray myprojectteam_Array = new JSONArray();
            List<TUser> userList = null;
            TUserExample tUserExample = new TUserExample();
            TUserExample.Criteria criteria1 = tUserExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria1.andFNameLike("%" + search + "%");
                userList = tUserService.findByExample(tUserExample);
            }
            List<Long> useridlist = null;
            // 查询条件
            TProjectTeamExample TProjectTeamExample = new TProjectTeamExample();
            TProjectTeamExample.Criteria criteria = TProjectTeamExample.createCriteria();
            if (userList != null && userList.size() > 0) {
                for (TUser tuser : userList) {
                    useridlist.add(tuser.getfKeyId());
                }
                criteria.andFuseridIn(useridlist);
            }
            criteria.andFstateEqualTo(1);
            TProjectTeamExample.setOrderByClause("FRole desc,FCDATE");
            List<TProjectTeam> myprojectteam_list = tProjectTeamService.findByExample(TProjectTeamExample);
            for (TProjectTeam myprojectteam : myprojectteam_list) {
                JSONObject myprojectteam_object = new JSONObject();
                myprojectteam_object.put("id", ParamTools.getEnParam(myprojectteam.getFkeyid().toString()));
                TUser tUser = tUserService.findById(myprojectteam.getFuserid());
                myprojectteam_object.put("text", tUser == null ? "" : tUser.getfName());
                myprojectteam_Array.add(myprojectteam_object);
            }
            // 返回值

            object.put("list", myprojectteam_Array);
            object.put("result", "success");
            object.put("results", myprojectteam_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取团队成员信息(下拉列表)  //只有参与者
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryTeamUserID", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryTeamUserID(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        String FProjectID = request.getParameter("FProjectID");
        try {
            FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);
            // 获取数据库记录
            JSONArray myprojectteam_Array = new JSONArray();
            List<TUser> userList = null;
            TUserExample tUserExample = new TUserExample();
            TUserExample.Criteria criteria1 = tUserExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria1.andFNameLike("%" + search + "%");
                userList = tUserService.findByExample(tUserExample);
            }
            List<Long> useridlist = null;
            // 查询条件
            TProjectTeamExample TProjectTeamExample = new TProjectTeamExample();
            TProjectTeamExample.Criteria criteria = TProjectTeamExample.createCriteria();
            if (userList != null && userList.size() > 0) {
                for (TUser tuser : userList) {
                    useridlist.add(tuser.getfKeyId());
                }
                criteria.andFuseridIn(useridlist);
            }
            criteria.andFprojectidEqualTo(Long.parseLong(FProjectID));
            criteria.andFstateEqualTo(1);
            criteria.andFroleEqualTo(0);
            TProjectTeamExample.setOrderByClause("FRole desc,FCDATE");
            List<TProjectTeam> myprojectteam_list = tProjectTeamService.findByExample(TProjectTeamExample);
            for (TProjectTeam myprojectteam : myprojectteam_list) {
                JSONObject myprojectteam_object = new JSONObject();
                myprojectteam_object.put("id", ParamTools.getEnParam(myprojectteam.getFkeyid().toString()));
                TUser tUser = tUserService.findById(myprojectteam.getFuserid());
                myprojectteam_object.put("text", tUser == null ? "" : tUser.getfName());
                myprojectteam_Array.add(myprojectteam_object);
            }
            // 返回值

            object.put("list", myprojectteam_Array);
            object.put("result", "success");
            object.put("results", myprojectteam_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取团队成员信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querymyprojectteamInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querymyprojectteamInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询团队成员信息
            TProjectTeam myprojectteam = tProjectTeamService.findById(key);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            JSONObject myprojectteam_object = new JSONObject();
            myprojectteam_object.put("key", ParamTools.getEnParam(myprojectteam.getFkeyid().toString()));
            myprojectteam_object.put("FProjectID", ParamTools.getEnParam(myprojectteam.getFprojectid().toString()));
            TUser tUser = tUserService.findById(myprojectteam.getFuserid());
            myprojectteam_object.put("FUserID", myprojectteam.getFuserid() == null ? "" : ParamTools.getEnParam(myprojectteam.getFuserid().toString()));
            myprojectteam_object.put("FUserName", tUser == null ? "" : tUser.getfName());
            myprojectteam_object.put("FUserNo", tUser == null ? "" : tUser.getfUserno());
            if (tUser != null) {
                TDept tDept = tDeptService.findById(tUser.getfDept());
                myprojectteam_object.put("FUserDept", tDept == null ? "" : tDept.getFname());
            } else {
                myprojectteam_object.put("FUserDept", "");
            }
            myprojectteam_object.put("FRole", myprojectteam.getFrole());
            if (myprojectteam.getFrole() == 0) {
                myprojectteam_object.put("FRoleName", "参与人");
            } else if (myprojectteam.getFrole() == 1) {
                myprojectteam_object.put("FRoleName", "负责人");
            }
            myprojectteam_object.put("FDuty", myprojectteam.getFduty() == null ? "" : myprojectteam.getFduty());
            myprojectteam_object.put("FNote", myprojectteam.getFnote() == null ? "" : myprojectteam.getFnote());
            myprojectteam_object.put("FCID", myprojectteam.getFcid() == null ? "" : myprojectteam.getFcid());
            myprojectteam_object.put("FUID", myprojectteam.getFuid() == null ? "" : myprojectteam.getFuid());
            myprojectteam_object.put("FCDATE", sdf.format(myprojectteam.getFcdate()));
            myprojectteam_object.put("FUDATE", myprojectteam.getFudate() == null ? "" : sdf.format(myprojectteam.getFudate()));
            myprojectteam_object.put("FState", myprojectteam.getFstate());
            if (myprojectteam.getFstate() == 1)
                myprojectteam_object.put("FStateName", "活跃");
            else
                myprojectteam_object.put("FStateName", "禁用");

            // 返回值
            object.put("info", myprojectteam_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加团队成员信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("添加项目团队成员信息")
    @ResponseBody
    @RequestMapping(value = "/addmyprojectteam", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addmyprojectteam(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FProjectID = jsonParam.getString("FProjectID");
        String FUserID = jsonParam.getString("FUserID");
        String FRole = jsonParam.getString("FRole");
        String FDuty = jsonParam.getString("FDuty");
        String FNote = jsonParam.getString("FNote");
        String userlist = jsonParam.getString("userlist");
        try {
            FUserID = FUserID == null ? "0" : ParamTools.getdeParam(FUserID);
            FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);

            String CookiesLoginmyprojectteamID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectteamID != null && !CookiesLoginmyprojectteamID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginmyprojectteamID);
            }


            //根据登录用户查询是否负责人身份
            //只有负责人身份才能添加
            TProjectTeamExample tProjectTeamExample = new TProjectTeamExample();
            TProjectTeamExample.Criteria criteria = tProjectTeamExample.createCriteria();
            criteria.andFuseridEqualTo(Long.parseLong(uid));
            criteria.andFprojectidEqualTo(Long.parseLong(FProjectID));
            List<TProjectTeam> teamList = tProjectTeamService.findByExample(tProjectTeamExample);
            if (teamList.size() > 0) {
//                if (teamList.get(0).getFrole() == 1) {
                if (userlist != null && !userlist.equals("")) {
                    JSONArray teamuser_Array = JSONArray.parseArray(userlist);
                    for (Object userId : teamuser_Array) {
                        Long teamuserid = Long.parseLong(ParamTools.getdeParam(userId.toString()));
                        tProjectTeamExample = new TProjectTeamExample();
                        tProjectTeamExample.or().andFuseridEqualTo(teamuserid)
                                .andFprojectidEqualTo(Long.parseLong(FProjectID));
                        teamList = tProjectTeamService.findByExample(tProjectTeamExample);
                        if (teamList.size() == 0) {
                            // 新建团队成员信息
                            TProjectTeam myprojectteam = new TProjectTeam();
                            myprojectteam.setFprojectid(Long.parseLong(FProjectID));
                            myprojectteam.setFuserid(teamuserid);
                            myprojectteam.setFrole(0);
                            myprojectteam.setFduty(FDuty);
                            myprojectteam.setFnote(FNote);
                            myprojectteam.setFcid(Long.parseLong(uid));
                            myprojectteam.setFcdate(new Date());
                            tProjectTeamService.save(myprojectteam);
                            TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
                            TUser tUser = tUserService.findById(teamuserid);
                            //添加日志
                            ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                            String fnote = "为项目【" + tProject.getFname() + "】，添加团队成员【" + tUser.getfName() + "】。";
                            projectLog.addprojectlog(fnote, FProjectID, "团队信息", "创建", "", uid);

                            TUser tUser2 = tUserService.findById(Long.parseLong(uid));
                            TLogAction logAction = new TLogAction();
                            logAction.setfUserId(tUser2.getfKeyId());
                            logAction.setfUserName(tUser2.getfName());
                            logAction.setfType(3);
                            logAction.setfPath("myprojectteam/addmyprojectteam{" + myprojectteam.getFkeyid() + "}");
                            logAction.setfUserType(1);

                            logAction.setfMemo(fnote);
                            logActionService.save(logAction);

                        } else {
                            // 返回值
                            object.put("result", "fail");
                        }

                    }
                }
                // 返回值
                object.put("result", "success");
//                } else {
//                    object.put("result", "error");
//                    object.put("error", "您不是负责人，不允许操作!");
//                }
            } else {
                object.put("result", "error");
                object.put("error", "您不是该项目团队成员，不允许操作!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 变更负责人
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("变更项目团队成员负责人")
    @ResponseBody
    @RequestMapping(value = "/changeUserId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String changeUserId(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FProjectID = jsonParam.getString("FProjectID");
        String FUserID = jsonParam.getString("FUserID");
        String FUserIDNew = jsonParam.getString("FUserIDNew"); //团队表的FKeydid

        try {
            FUserID = FUserID == null ? "0" : ParamTools.getdeParam(FUserID) == "0" ? "0" : ParamTools.getdeParam(FUserID);
            FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);
            FUserIDNew = FUserIDNew == null ? "0" : ParamTools.getdeParam(FUserIDNew);
            String CookiesLoginmyprojectteamID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectteamID != null && !CookiesLoginmyprojectteamID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginmyprojectteamID);
            }
            //根据登录用户查询是否负责人身份
            //只有负责人身份才能添加
            TProjectTeamExample tProjectTeamExample = new TProjectTeamExample();
            TProjectTeamExample.Criteria criteria = tProjectTeamExample.createCriteria();
            criteria.andFuseridEqualTo(Long.parseLong(uid)).andFprojectidEqualTo(Long.parseLong(FProjectID));
            List<TProjectTeam> teamList = tProjectTeamService.findByExample(tProjectTeamExample);
            if (teamList.size() > 0) {
//                if (teamList.get(0).getFrole() == 1) {
                //先修改当前负责人 再修改新选择的为负责人
                if(Long.parseLong(FUserID) !=0){

                    TProjectTeam tProjectTeam = new TProjectTeam();
                    tProjectTeam.setFkeyid(Long.parseLong(FUserID));
                    tProjectTeam.setFrole(0);
                    tProjectTeamService.update(tProjectTeam);

                    tProjectTeam = new TProjectTeam();
                    tProjectTeam.setFkeyid(Long.parseLong(FUserIDNew));
                    tProjectTeam.setFrole(1);
                    tProjectTeamService.update(tProjectTeam);


                    TProjectTeam projectTeam = tProjectTeamService.findById(Long.parseLong(FUserIDNew));
                    //变更项目表中负责人ID
                    TProject tProject = new TProject();
                    tProject.setFkeyid(Long.parseLong(FProjectID));
                    tProject.setFleaduserid(projectTeam.getFuserid());
                    tProjectService.update(tProject);

                    try {
                        TProjectTeam projectTeam1 = tProjectTeamService.findById(Long.parseLong(FUserID));
                        TUser oldtUser = tUserService.findById(projectTeam1.getFuserid());
                        TProject tProjectb = tProjectService.findById(Long.parseLong(FProjectID));
                        TUser tUser = tUserService.findById(projectTeam.getFuserid());
                        //添加日志
                        ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                        String fnote = "为项目【" + tProjectb.getFname() + "】，变更负责人，把负责人【" + oldtUser.getfName() + "】变更为【" + tUser.getfName() + "】。";
                        projectLog.addprojectlog(fnote, FProjectID, "团队信息", "变更负责人", "", uid);


                        TUser tUser2 = tUserService.findById(Long.parseLong(uid));
                        TLogAction logAction = new TLogAction();
                        logAction.setfUserId(tUser2.getfKeyId());
                        logAction.setfUserName(tUser2.getfName());
                        logAction.setfType(3);
                        logAction.setfPath("myprojectteam/changeUserId{" + tProjectTeam.getFkeyid() + "}");
                        logAction.setfUserType(1);

                        logAction.setfMemo(fnote);
                        logActionService.save(logAction);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }


                    // 返回值
                    object.put("result", "success");
                }else{
                    object.put("result", "error");
                    object.put("error", "未获取到原负责人信息，请刷新后重试!");
                }


//                } else {
//                    object.put("result", "error");
//                    object.put("error", "您不是负责人，不允许操作!");
//                }
            } else {
                object.put("result", "error");
                object.put("error", "您不是该项目团队成员，不允许操作!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 修改团队成员信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("修改项目团队成员信息")
    @ResponseBody
    @RequestMapping(value = "/updatemyprojectteam", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatemyprojectteam(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FProjectID = jsonParam.getString("FProjectID");
        String FUserID = jsonParam.getString("FUserID");
        int FRole = jsonParam.getInteger("FRole");
        String FDuty = jsonParam.getString("FDuty");
        String FNote = jsonParam.getString("FNote");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            FUserID = FUserID == null ? "0" : ParamTools.getdeParam(FUserID);
            FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);
            long key = Long.parseLong(id);

            String CookiesLoginmyprojectteamID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectteamID != null && !CookiesLoginmyprojectteamID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginmyprojectteamID);
            }

            TProjectTeamExample tProjectTeamExample = new TProjectTeamExample();
            tProjectTeamExample.or().andFuseridEqualTo(Long.parseLong(FUserID))
                    .andFprojectidEqualTo(Long.parseLong(FProjectID))
                    .andFkeyidNotEqualTo(key);
            List<TProjectTeam> teamList = tProjectTeamService.findByExample(tProjectTeamExample);
            if (teamList.size() == 0) {
                //根据登录用户查询是否负责人身份
                //只有负责人身份才能添加
                tProjectTeamExample = new TProjectTeamExample();
                TProjectTeamExample.Criteria criteria = tProjectTeamExample.createCriteria();
                criteria.andFuseridEqualTo(Long.parseLong(uid)).andFprojectidEqualTo(Long.parseLong(FProjectID));
                teamList = tProjectTeamService.findByExample(tProjectTeamExample);
                if (teamList.size() > 0) {
//                    if (teamList.get(0).getFrole() == 1) {
                    // 更新团队成员信息
                    TProjectTeam myprojectteam = new TProjectTeam();
                    myprojectteam.setFkeyid(key);
                    myprojectteam.setFprojectid(Long.parseLong(FProjectID));
                    myprojectteam.setFuserid(Long.parseLong(FUserID));
                    myprojectteam.setFrole(FRole);
                    myprojectteam.setFduty(FDuty);
                    myprojectteam.setFnote(FNote);
                    myprojectteam.setFuid(Long.parseLong(uid));
                    myprojectteam.setFudate(new Date());
                    tProjectTeamService.update(myprojectteam);


                    TProject tProjectb = tProjectService.findById(Long.parseLong(FProjectID));
                    TUser tUser = tUserService.findById(Long.parseLong(FUserID));
                    TUser oldtUser = tUserService.findById(teamList.get(0).getFuserid());
                    //添加日志
                    ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                    String fnote = "项目【" + tProjectb.getFname() + "】，修改团队成员，把团队成员【" + oldtUser.getfName() + "】修改为团队成员【" + tUser.getfName() + "】。";
                    projectLog.addprojectlog(fnote, FProjectID, "团队信息", "修改", "", uid);


                    TUser tUser2 = tUserService.findById(Long.parseLong(uid));
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserId(tUser2.getfKeyId());
                    logAction.setfUserName(tUser2.getfName());
                    logAction.setfType(3);
                    logAction.setfPath("myprojectteam/updatemyprojectteam{" + myprojectteam.getFkeyid() + "}");
                    logAction.setfUserType(1);

                    logAction.setfMemo(fnote);
                    logActionService.save(logAction);

                    // 返回值
                    object.put("result", "success");
//                    } else {
//                        object.put("result", "error");
//                        object.put("error", "您不是负责人，不允许操作!");
//                    }
                } else {
                    object.put("result", "error");
                    object.put("error", "您不是该项目团队成员，不允许操作!");
                }
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
     * 删除团队成员信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("删除项目团队成员信息")
    @ResponseBody
    @RequestMapping(value = "/delmyprojectteam", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delmyprojectteam(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String FProjectID = jsonParam.getString("FProjectID");
        try {
            String CookiesLoginmyprojectteamID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectteamID != null && !CookiesLoginmyprojectteamID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            FProjectID = FProjectID == null ? "0" : (FProjectID.equals("0") ? "0" : ParamTools.getdeParam(FProjectID));
            //根据登录用户查询是否负责人身份
            //只有负责人身份才能删除
            TProjectTeamExample tProjectTeamExample = new TProjectTeamExample();
            TProjectTeamExample.Criteria criteria = tProjectTeamExample.createCriteria();
            criteria.andFuseridEqualTo(Long.parseLong(uid)).andFprojectidEqualTo(Long.parseLong(FProjectID));
            List<TProjectTeam> teamList = tProjectTeamService.findByExample(tProjectTeamExample);
            if (teamList.size() > 0) {
//                if (teamList.get(0).getFrole() == 1) {
                TProjectTeam projectTeam = tProjectTeamService.findById(Long.parseLong(id));
                tProjectTeamService.deleteById(Long.parseLong(id));

                TProject tProjectb = tProjectService.findById(Long.parseLong(FProjectID));
                TUser tUser = tUserService.findById(projectTeam.getFuserid());
                //添加日志
                ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                String fnote = "项目【" + tProjectb.getFname() + "】，删除团队成员【" + tUser.getfName() + "】信息。";
                projectLog.addprojectlog(fnote, FProjectID, "团队信息", "删除", "", uid);

                TUser tUser2 = tUserService.findById(Long.parseLong(uid));
                TLogAction logAction = new TLogAction();
                logAction.setfUserId(tUser2.getfKeyId());
                logAction.setfUserName(tUser2.getfName());
                logAction.setfType(3);
                logAction.setfPath("myprojectteam/delmyprojectteam{" + id + "}");
                logAction.setfUserType(1);

                logAction.setfMemo(fnote);
                logActionService.save(logAction);

                // 返回值
                object.put("result", "success");
//                } else {
//                    object.put("result", "error");
//                    object.put("error", "您不是负责人，不允许操作!");
//                }
            } else {
                object.put("result", "error");
                object.put("error", "您不是该项目团队成员，不允许操作!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 查询当前项目团队负责人信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/selectprojectteamUserfz", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String selectprojectteamUserfz(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);

        String FProjectID = jsonParam.getString("FProjectID");
        try {
            String CookiesLoginmyprojectteamID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectteamID != null && !CookiesLoginmyprojectteamID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            FProjectID = FProjectID == null ? "0" : (FProjectID.equals("0") ? "0" : ParamTools.getdeParam(FProjectID));
            //查询 项目团队负责人信息
            TProjectTeamExample tProjectTeamExample = new TProjectTeamExample();
            tProjectTeamExample.createCriteria().andFprojectidEqualTo(Long.parseLong(FProjectID)).andFroleEqualTo(1);
            List<TProjectTeam> teamList = tProjectTeamService.findByExample(tProjectTeamExample);

            if(teamList.size() > 0){
                TProjectTeam tProjectTeam = teamList.get(0);
                object.put("fuserid", ParamTools.getEnParam(tProjectTeam.getFkeyid().toString()) );
                TUser tUser = tUserService.findById(tProjectTeam.getFuserid());
                object.put("fusername",tUser == null ? "" : tUser.getfName());
            }else{
                object.put("fuserid", ParamTools.getEnParam("0"));
                object.put("fusername","未获取到数据！");
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
     * 变更团队成员信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statemyprojectteam", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statemyprojectteam(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        String FProjectID = jsonParam.getString("FProjectID");

        try {
            String CookiesLoginmyprojectteamID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectteamID != null && !CookiesLoginmyprojectteamID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            FProjectID = FProjectID == null ? "0" : (FProjectID.equals("0") ? "0" : ParamTools.getdeParam(FProjectID));
            //根据登录用户查询是否负责人身份
            //只有负责人身份才能变更
            TProjectTeamExample tProjectTeamExample = new TProjectTeamExample();
            TProjectTeamExample.Criteria criteria = tProjectTeamExample.createCriteria();
            criteria.andFuseridEqualTo(Long.parseLong(uid)).andFprojectidEqualTo(Long.parseLong(FProjectID));
            List<TProjectTeam> teamList = tProjectTeamService.findByExample(tProjectTeamExample);
            if (teamList.size() > 0) {
//                if (teamList.get(0).getFrole() == 1) {
                state = state.equals("1") ? "0" : "1";
                TProjectTeam myprojectteam = new TProjectTeam();
                myprojectteam.setFkeyid(Long.parseLong(id));
                myprojectteam.setFuid(Long.parseLong(uid));
                myprojectteam.setFudate(new Date());
                myprojectteam.setFstate(Integer.valueOf(state));
                tProjectTeamService.update(myprojectteam);

                TProjectTeam projectTeam = tProjectTeamService.findById(Long.parseLong(id));
                TProject tProjectb = tProjectService.findById(Long.parseLong(FProjectID));
                TUser tUser = tUserService.findById(projectTeam.getFuserid());
                state = state.equals("1") ? "可用" : "禁用";
                //添加日志
                ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                String fnote = "项目【" + tProjectb.getFname() + "】，变更团队成员【" + tUser.getfName() + "】状态为【" + state + "】";
                projectLog.addprojectlog(fnote, FProjectID, "团队信息", "变更状态", "", uid);


                TUser tUser2 = tUserService.findById(Long.parseLong(uid));
                TLogAction logAction = new TLogAction();
                logAction.setfUserId(tUser2.getfKeyId());
                logAction.setfUserName(tUser2.getfName());
                logAction.setfType(3);
                logAction.setfPath("myprojectteam/statemyprojectteam{" + id + "}");
                logAction.setfUserType(1);

                logAction.setfMemo(fnote);
                logActionService.save(logAction);

                // 返回值
                object.put("result", "success");
//                } else {
//                    object.put("result", "error");
//                    object.put("error", "您不是负责人，不允许操作!");
//                }
            } else {
                object.put("result", "error");
                object.put("error", "您不是该项目团队成员，不允许操作!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 验证团队成员是否存在
     */
    private int repeaTProjectTeam(Long id, String userid, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TProjectTeamExample myprojectteamExample = new TProjectTeamExample();
            TProjectTeamExample.Criteria criteria = myprojectteamExample.createCriteria();
            if (ftype.equals("2")) { // 修改
                if (id != null) {
                    criteria.andFkeyidNotEqualTo(id);
                }
                if (userid != null && !userid.equals("")) {
                    criteria.andFuseridEqualTo(Long.parseLong(userid));
                }
            } else { // 新增
                if (userid != null && !userid.equals("")) {
                    criteria.andFuseridEqualTo(Long.parseLong(userid));
                }
            }
            List<TProjectTeam> rojectTypeList = tProjectTeamService.findByExample(myprojectteamExample);
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
}