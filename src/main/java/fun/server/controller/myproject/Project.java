package fun.server.controller.myproject;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.server.model.*;
import fun.server.model.customQuery.TProject_applyGroupID;
import fun.server.service.*;
import fun.tools.JavaMailTools;
import fun.tools.ParamTools;
import fun.tools.SnowflakeIdWorker;
import fun.websocket.MessageServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 项目管理 相关业务处理
 */
@RestController
@RequestMapping("/s/myproject")
public class Project {

    private final TProjectService tProjectService;
    private final TUserService tUserService;
    private final TLogActionService logActionService;
    private final TProjectTypeService tProjectTypeService;
    private final TProjectLevelService tProjectLevelService;
//    private final TCustomerService tCustomerService;
//    private final TMaterialService tMaterialService;
    private final TDeptService tDeptService;
    private final TProjectSecretService tProjectSecretService;
    private final TProjectTeamService tProjectTeamService;
    private final TProjectExceedService tProjectExceedService;
//    private final TBomMService tBomMService;
//    private final TBomSService tBomSService;
//    private final TMaterialFromService tMaterialFromService;
//    private final TMaterialUnitService tMaterialUnitService;
//    private final TProjectFlowsService tProjectFlowsService;
    private final TProjectPlanService tProjectPlanService;
    private final static String FORMAT_CODE = "0000";
    private final static String FAULT_PREFIX = "P";
//    private final TTaskService tTaskService;
    private final TSysNoService tSysNoService;
    private final TProjectLogService tProjectLogService;

//    private final TMaterialMoldTypeService tMaterialMoldTypeService;
//    private final TProcedureNoService tProcedureNoService;
//    private final TProcedureNameService tProcedureNameService;
//    private final TProjectMjPlanService tProjectMjPlanService;
//    private final TProjectMjPlanSService tProjectMjPlanSService;
//
//    private final TTaskTeamService tTaskTeamService;
//    private final TTaskLogService tTaskLogService;

    private final TProjectFilesService tProjectFilesService;


    private final TMajorService tMajorService;
    private final TCollegeService tCollegeService;

    @Resource
    private MessageServer messageServer;

    private static MessageServer messageServer_static;
    private static TMessageService messageService_static;
    private final TMessageService messageService;
    
    private final TProjectCourseService tProjectCourseService;

    private final TTrainingProgramCourseService tTrainingProgramCourseService;

    private final TCourseService tCourseService;

    private final TTrainingProgramService tTrainingProgramService;

    public Project(TProjectService tProjectService, TUserService tUserService, TLogActionService logActionService, TProjectTypeService tProjectTypeService, TProjectLevelService tProjectLevelService, TDeptService tDeptService, TProjectSecretService tProjectSecretService, TProjectTeamService tProjectTeamService, TProjectExceedService tProjectExceedService, TProjectPlanService tProjectPlanService, TSysNoService tSysNoService, TProjectLogService tProjectLogService, TProjectFilesService tProjectFilesService, TMajorService tMajorService, TCollegeService tCollegeService, TMessageService messageService, TProjectCourseService tProjectCourseService, TTrainingProgramCourseService tTrainingProgramCourseService, TCourseService tCourseService, TTrainingProgramService tTrainingProgramService) {
        this.tProjectService = tProjectService;
        this.tUserService = tUserService;
        this.logActionService = logActionService;
        this.tProjectTypeService = tProjectTypeService;
        this.tProjectLevelService = tProjectLevelService;
        this.tDeptService = tDeptService;
        this.tProjectSecretService = tProjectSecretService;
        this.tProjectTeamService = tProjectTeamService;
        this.tProjectExceedService = tProjectExceedService;
        this.tProjectPlanService = tProjectPlanService;
        this.tSysNoService = tSysNoService;
        this.tProjectLogService = tProjectLogService;
        this.tProjectFilesService = tProjectFilesService;
        this.tMajorService = tMajorService;
        this.tCollegeService = tCollegeService;
        this.messageService = messageService;
        this.tProjectCourseService = tProjectCourseService;
        this.tTrainingProgramCourseService = tTrainingProgramCourseService;
        this.tCourseService = tCourseService;
        this.tTrainingProgramService = tTrainingProgramService;
    }

    @PostConstruct
    public void init() {
        messageServer_static = this.messageServer;
        messageService_static = this.messageService;
    }





    /**
     * 获取项目信息-采购申请物料清单到货情况 项目列表
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querymyprojectcg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querymyprojectcg(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        int type = jsonParam.getInteger("type");//类型 1=只显示有物料清单的项目  2=显示全部项目

        String fno = jsonParam.getString("fno");
        try {
            // 查询条件
            TProjectExample TProjectExample = new TProjectExample();
            TProjectExample.Criteria criteria = TProjectExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }
            if (fno != null && !fno.equals("")) {
                criteria.andFnoLike("%" + fno + "%");
            }
            List<Long> projectid = new ArrayList<>();
            if (type == 2) {
                projectid = new ArrayList<>();
            } else if (type == 1) {
                //查询物料清单包含的项目ID
                List<TProject_applyGroupID> idList = tProjectService.getGroupID();
//                System.out.println(idList.size());
                if (idList.size() > 0) {
                    for (TProject_applyGroupID tProject_applyGroupID : idList) {
                        projectid.add(tProject_applyGroupID.getProjectID());
                    }
                } else {
                    projectid.add(0L);
                }
            }
            if (type == 1) {
                criteria.andFkeyidIn(projectid);
            }
//            System.out.println("projectid:" + projectid.toString());
//            System.out.println("type:" + type);

            PageInfo<TProject> myprojectPageInfo = tProjectService.findByExampleMapper(TProjectExample, (page - 1) * results, results);
            List<TProject> myproject_list = myprojectPageInfo.getList();
//            System.out.println("myproject_list:" + myproject_list.size());
            TProjectExceed projectExceed = null;
            // 获取数据库记录
            JSONArray myproject_Array = new JSONArray();
            int yuqiday = 0;
            Date newdate = null;
            Date fedate = null;
            int dayDiffer = -1;
            TUser tUser = null;
//            TMaterial tMaterial = null;
//            TCustomer tCustomer = null;
            TProjectType tProjectType = null;
            TProjectLevel tProjectLevel = null;
            TDept tDept = null;
            TProjectSecret projectSecret = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (TProject myproject : myproject_list) {
                JSONObject myproject_object = new JSONObject();
                //检查是否逾期
                projectExceed = tProjectExceedService.findById(1l);
                //获取提前几天 提醒
                yuqiday = projectExceed == null ? 0 : projectExceed.getFday();
                //获取当前时间
                newdate = new Date();
                //获取计划完成时间
                fedate = myproject.getFedate();
                //判断 当前时间与计划完成时间的差值
                dayDiffer = getDayDiffer(newdate, fedate);
                if (dayDiffer <= yuqiday && dayDiffer >= 0) {
                    //预警

                    myproject_object.put("FStates", 1);
                } else if (dayDiffer < 0) {
                    //逾期

                    myproject_object.put("FStates", 2);
                } else {
                    //正常
                    myproject_object.put("FStates", 3);
                }
                myproject_object.put("key", ParamTools.getEnParam(myproject.getFkeyid().toString()));
                myproject_object.put("FName", myproject.getFname());
                myproject_object.put("FUserID", ParamTools.getEnParam(myproject.getFuserid().toString()));
                tUser = tUserService.findById(myproject.getFuserid());
                myproject_object.put("FUserName", tUser == null ? "" : tUser.getfName());

                myproject_object.put("FDate", myproject.getFdate() == null ? "" : sdf.format(myproject.getFdate()));
                myproject_object.put("FNo", myproject.getFno());

                myproject_object.put("FCustomerID", ParamTools.getEnParam(myproject.getFcustomerid().toString()));
//                tCustomer = tCustomerService.findById(myproject.getFcustomerid());
//                myproject_object.put("FCustomerName", tCustomer == null ? "" : tCustomer.getFname());
                myproject_object.put("FCustomerName", "");

                myproject_object.put("FAsk", myproject.getFask());
                myproject_object.put("FNote", myproject.getFnote());

                myproject_object.put("FTypeID", ParamTools.getEnParam(myproject.getFtypeid().toString()));
                tProjectType = tProjectTypeService.findById(myproject.getFtypeid());
                myproject_object.put("FTypeName", tProjectType == null ? "" : tProjectType.getFname());

                myproject_object.put("FLevelID", ParamTools.getEnParam(myproject.getFlevelid().toString()));
                tProjectLevel = tProjectLevelService.findById(myproject.getFlevelid());
                myproject_object.put("FLevelName", tProjectLevel.getFname());

                myproject_object.put("FLeadUserID", ParamTools.getEnParam(myproject.getFleaduserid().toString()));
                tUser = tUserService.findById(myproject.getFleaduserid());
                myproject_object.put("FLeadUserName", tUser == null ? "" : tUser.getfName());

                myproject_object.put("FSDate", myproject.getFsdate() == null ? "" : sdf.format(myproject.getFsdate()));
                myproject_object.put("FEDate", myproject.getFedate() == null ? "" : sdf.format(myproject.getFedate()));
                myproject_object.put("FStartDate", myproject.getFstartdate() == null ? "" : sdf.format(myproject.getFstartdate()));
                myproject_object.put("FEndDate", myproject.getFenddate() == null ? "" : sdf.format(myproject.getFenddate()));
                if (myproject.getFmaterialid() == null)
                    myproject_object.put("isFMatertial", 0);
                else
                    myproject_object.put("isFMatertial", 1);
                myproject_object.put("FMaterialID", myproject.getFmaterialid() == null ? "" : ParamTools.getEnParam(myproject.getFmaterialid().toString()));
//                tMaterial = tMaterialService.findById(myproject.getFmaterialid());
//                myproject_object.put("FMaterialName", tMaterial == null ? "" : tMaterial.getFname());
                myproject_object.put("FMaterialName", "");

                myproject_object.put("FDeptID", ParamTools.getEnParam(myproject.getFdeptid().toString()));
//                tDept = tDeptService.findById(myproject.getFdeptid());
//                myproject_object.put("FDeptName", tDept == null ? "" : tDept.getFname());
                TMajor major = tMajorService.findById(myproject.getFdeptid());
                try {
                    TCollege tCollege = tCollegeService.findById(major.getFcollegeid());
                    myproject_object.put("TCollegeName", tCollege == null ? "" : tCollege.getFcollegename());
                    if(tCollege != null)
                        myproject_object.put("FDeptName", tCollege.getFcollegename() + "/" + major.getFmajorname());
                    else
                        myproject_object.put("FDeptName",  major.getFmajorname());
                } catch (Exception e) {
                    myproject_object.put("TCollegeName","");
                    myproject_object.put("FDeptName", major == null ? "" : major.getFmajorname());
                }

                myproject_object.put("FContractNo", myproject.getFcontractno());

                myproject_object.put("FSecret", ParamTools.getEnParam(myproject.getFsecret().toString()));
                projectSecret = tProjectSecretService.findById(myproject.getFsecret());
                myproject_object.put("FSecretName", projectSecret == null ? "" : projectSecret.getFname());

                myproject_object.put("FFrom", myproject.getFfrom());
                myproject_object.put("FFunds", myproject.getFfunds());
                myproject_object.put("FSysNo", myproject.getFsysno() == null ? "" : myproject.getFsysno());
                myproject_object.put("FSysNoNum", myproject.getFsysnonum() == null ? "" : myproject.getFsysnonum());
                myproject_object.put("FInsideNo", myproject.getFinsideno() == null ? "" : myproject.getFinsideno());


                myproject_object.put("FCID", myproject.getFcid());
                myproject_object.put("FUID", myproject.getFuid());
                myproject_object.put("FCDATE", myproject.getFcdate() == null ? "" : sdf.format(myproject.getFcdate()));
                myproject_object.put("FUDATE", myproject.getFudate() == null ? "" : sdf.format(myproject.getFudate()));
                myproject_object.put("FState", myproject.getFstate());
                myproject_Array.add(myproject_object);
            }
            // 返回值
            object.put("list", myproject_Array);
            object.put("total", myprojectPageInfo.getTotal()); // 总行数
            object.put("page", myprojectPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();

    }


    /**
     * 获取项目信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querymyproject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querymyproject(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        int showtype = jsonParam.getInteger("showtype");
        try {

            String CookiesLoginmyprojectteamID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectteamID != null && !CookiesLoginmyprojectteamID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginmyprojectteamID);
            }
            List<Long> projectid = new ArrayList<>();
            List<Long> yuqiid = new ArrayList<>();
            // 获取数据库记录
            JSONArray myproject_Array = new JSONArray();
            // 获取数据库记录
            JSONArray myproject_Array2 = new JSONArray();
            // 查询条件
            TProjectExample TProjectExample = new TProjectExample();
            TProjectExample.Criteria criteria = TProjectExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }
            if (showtype == 2) {
                criteria.andFstateNotEqualTo(0);
            } else if (showtype == 3) {
                criteria.andFstateEqualTo(1);
            }
            //判断当前用户 是否存在项目中
            TProjectTeamExample tProjectTeamExample = new TProjectTeamExample();
            TProjectTeamExample.Criteria teamExampleCriteria = tProjectTeamExample.createCriteria();
            teamExampleCriteria.andFuseridEqualTo(Long.parseLong(uid));
            teamExampleCriteria.andFstateEqualTo(1);
            List<TProjectTeam> teamList = tProjectTeamService.findByExample(tProjectTeamExample);
            if (teamList != null && teamList.size() > 0) {
                for (TProjectTeam tProjectTeam : teamList) {
                    projectid.add(tProjectTeam.getFprojectid());
                }
                criteria.andFkeyidIn(projectid);
                TProjectExample.setOrderByClause("FCDATE desc");
                PageInfo<TProject> myprojectPageInfo = tProjectService.findByExampleMapper(TProjectExample, (page - 1) * results, results);
                List<TProject> myproject_list = myprojectPageInfo.getList();
                TUser tUser = null;
//                TMaterial tMaterial = null;
//                TCustomer tCustomer = null;
                TProjectType tProjectType = null;
                TProjectLevel tProjectLevel = null;
                TDept tDept = null;
                TProjectSecret projectSecret = null;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                TProjectExceed projectExceed = null;
                int yuqiday = 0;
                Date newdate = null;
                Date fedate = null;
                int dayDiffer = -1;
                for (TProject myproject : myproject_list) {
                    JSONObject myproject_object = new JSONObject();
                    //检查是否逾期
                    projectExceed = tProjectExceedService.findById(1l);
                    //获取提前几天 提醒
                    yuqiday = projectExceed == null ? 0 : projectExceed.getFday();
                    //获取当前时间
                    newdate = new Date();
                    //获取计划完成时间
                    fedate = myproject.getFedate();
                    //判断 当前时间与计划完成时间的差值
                    dayDiffer = getDayDiffer(newdate, fedate);
                    if (dayDiffer <= yuqiday && dayDiffer >= 0) {
                        //预警
                        yuqiid.add(myproject.getFkeyid());
                        myproject_object.put("FStates", 1);
                    } else if (dayDiffer < 0) {
                        //逾期
                        yuqiid.add(myproject.getFkeyid());
                        myproject_object.put("FStates", 2);
                    } else {
                        //正常
                        myproject_object.put("FStates", 3);
                    }
                    if (showtype < 3) {
                        myproject_object.put("key", ParamTools.getEnParam(myproject.getFkeyid().toString()));
                        myproject_object.put("FName", myproject.getFname());
                        myproject_object.put("jmFName", ParamTools.getEnParam(myproject.getFname()));
                        myproject_object.put("FUserID", ParamTools.getEnParam(myproject.getFuserid().toString()));
                        tUser = tUserService.findById(myproject.getFuserid());
                        myproject_object.put("FUserName", tUser == null ? "" : tUser.getfName());

                        myproject_object.put("FDate", myproject.getFdate() == null ? "" : sdf.format(myproject.getFdate()));
                        myproject_object.put("FNo", myproject.getFno());

                        myproject_object.put("FCustomerID", ParamTools.getEnParam(myproject.getFcustomerid().toString()));
//                        tCustomer = tCustomerService.findById(myproject.getFcustomerid());
//                        myproject_object.put("FCustomerName", tCustomer == null ? "" : tCustomer.getFname());
                        myproject_object.put("FCustomerName", "");


                        myproject_object.put("FAsk", myproject.getFask());
                        myproject_object.put("FNote", myproject.getFnote());

                        myproject_object.put("FTypeID", ParamTools.getEnParam(myproject.getFtypeid().toString()));
                        tProjectType = tProjectTypeService.findById(myproject.getFtypeid());
                        myproject_object.put("FTypeName", tProjectType == null ? "" : tProjectType.getFname());

                        myproject_object.put("FLevelID", ParamTools.getEnParam(myproject.getFlevelid().toString()));
                        tProjectLevel = tProjectLevelService.findById(myproject.getFlevelid());
                        myproject_object.put("FLevelName", tProjectLevel.getFname());

                        myproject_object.put("FLeadUserID", ParamTools.getEnParam(myproject.getFleaduserid().toString()));
                        tUser = tUserService.findById(myproject.getFleaduserid());
                        myproject_object.put("FLeadUserName", tUser == null ? "" : tUser.getfName());

                        myproject_object.put("FSDate", myproject.getFsdate() == null ? "" : sdf.format(myproject.getFsdate()));
                        myproject_object.put("FEDate", myproject.getFedate() == null ? "" : sdf.format(myproject.getFedate()));
                        myproject_object.put("FStartDate", myproject.getFstartdate() == null ? "" : sdf.format(myproject.getFstartdate()));
                        myproject_object.put("FEndDate", myproject.getFenddate() == null ? "" : sdf.format(myproject.getFenddate()));
                        if (myproject.getFmaterialid() == null)
                            myproject_object.put("isFMatertial", 0);
                        else
                            myproject_object.put("isFMatertial", 1);
                        myproject_object.put("FMaterialID", myproject.getFmaterialid() == null ? "" : ParamTools.getEnParam(myproject.getFmaterialid().toString()));
//                        tMaterial = tMaterialService.findById(myproject.getFmaterialid());
//                        myproject_object.put("FMaterialName", tMaterial == null ? "" : tMaterial.getFname());
                        myproject_object.put("FMaterialName", "");

                        myproject_object.put("FDeptID", ParamTools.getEnParam(myproject.getFdeptid().toString()));
//                        tDept = tDeptService.findById(myproject.getFdeptid());
//                        myproject_object.put("FDeptName", tDept == null ? "" : tDept.getFname());
                        TMajor major = tMajorService.findById(myproject.getFdeptid());
                        try {
                            TCollege tCollege = tCollegeService.findById(major.getFcollegeid());
                            myproject_object.put("TCollegeName", tCollege == null ? "" : tCollege.getFcollegename());
                            if(tCollege != null)
                                myproject_object.put("FDeptName", tCollege.getFcollegename() + "/" + major.getFmajorname());
                            else
                                myproject_object.put("FDeptName",  major.getFmajorname());
                        } catch (Exception e) {
                            myproject_object.put("TCollegeName","");
                            myproject_object.put("FDeptName", major == null ? "" : major.getFmajorname());
                        }
                        

                        myproject_object.put("FContractNo", myproject.getFcontractno());

                        myproject_object.put("FSecret", ParamTools.getEnParam(myproject.getFsecret().toString()));
                        projectSecret = tProjectSecretService.findById(myproject.getFsecret());
                        myproject_object.put("FSecretName", projectSecret == null ? "" : projectSecret.getFname());

                        myproject_object.put("FFrom", myproject.getFfrom());
                        myproject_object.put("FFunds", myproject.getFfunds());
                        myproject_object.put("FSysNo", myproject.getFsysno() == null ? "" : myproject.getFsysno());
                        myproject_object.put("FSysNoNum", myproject.getFsysnonum() == null ? "" : myproject.getFsysnonum());
                        myproject_object.put("FInsideNo", myproject.getFinsideno() == null ? "" : myproject.getFinsideno());


                        myproject_object.put("FCID", myproject.getFcid());
                        myproject_object.put("FUID", myproject.getFuid());
                        myproject_object.put("FCDATE", myproject.getFcdate() == null ? "" : sdf.format(myproject.getFcdate()));
                        myproject_object.put("FUDATE", myproject.getFudate() == null ? "" : sdf.format(myproject.getFudate()));
                        myproject_object.put("FState", myproject.getFstate());
                    }
                    myproject_Array.add(myproject_object);
                }

                if (showtype == 3) {
                    if (yuqiid.size() <= 0) {
                        yuqiid.add(0l);
                    }
                    TProjectExample = new TProjectExample();
                    TProjectExample.or().andFkeyidIn(yuqiid);
                    myprojectPageInfo = tProjectService.findByExampleMapper(TProjectExample, (page - 1) * results, results);
                    myproject_list = myprojectPageInfo.getList();
                    for (TProject myproject : myproject_list) {
                        JSONObject myproject_object = new JSONObject();
                        //检查是否逾期
                        projectExceed = tProjectExceedService.findById(1l);
                        //获取提前几天 提醒
                        yuqiday = projectExceed == null ? 0 : projectExceed.getFday();
                        //获取当前时间
                        newdate = new Date();
                        //获取计划完成时间
                        fedate = myproject.getFedate();
                        //判断 当前时间与计划完成时间的差值
                        dayDiffer = getDayDiffer(newdate, fedate);
                        if (dayDiffer <= yuqiday && dayDiffer >= 0) {
                            //预警
//                            yuqiid.add(myproject.getFkeyid());
                            myproject_object.put("FStates", 1);
                        } else if (dayDiffer < 0) {
                            //逾期
//                            yuqiid.add(myproject.getFkeyid());
                            myproject_object.put("FStates", 2);
                        } else {
                            //正常
                            myproject_object.put("FStates", 3);
                        }

                        myproject_object.put("key", ParamTools.getEnParam(myproject.getFkeyid().toString()));
                        myproject_object.put("FName", myproject.getFname());
                        myproject_object.put("jmFName", ParamTools.getEnParam(myproject.getFname()));
                        myproject_object.put("FUserID", ParamTools.getEnParam(myproject.getFuserid().toString()));
                        tUser = tUserService.findById(myproject.getFuserid());
                        myproject_object.put("FUserName", tUser == null ? "" : tUser.getfName());

                        myproject_object.put("FDate", myproject.getFdate() == null ? "" : sdf.format(myproject.getFdate()));
                        myproject_object.put("FNo", myproject.getFno());

                        myproject_object.put("FCustomerID", ParamTools.getEnParam(myproject.getFcustomerid().toString()));
//                        tCustomer = tCustomerService.findById(myproject.getFcustomerid());
//                        myproject_object.put("FCustomerName", tCustomer == null ? "" : tCustomer.getFname());
                        myproject_object.put("FCustomerName", "");

                        myproject_object.put("FAsk", myproject.getFask());
                        myproject_object.put("FNote", myproject.getFnote());

                        myproject_object.put("FTypeID", ParamTools.getEnParam(myproject.getFtypeid().toString()));
                        tProjectType = tProjectTypeService.findById(myproject.getFtypeid());
                        myproject_object.put("FTypeName", tProjectType == null ? "" : tProjectType.getFname());

                        myproject_object.put("FLevelID", ParamTools.getEnParam(myproject.getFlevelid().toString()));
                        tProjectLevel = tProjectLevelService.findById(myproject.getFlevelid());
                        myproject_object.put("FLevelName", tProjectLevel.getFname());

                        myproject_object.put("FLeadUserID", ParamTools.getEnParam(myproject.getFleaduserid().toString()));
                        tUser = tUserService.findById(myproject.getFleaduserid());
                        myproject_object.put("FLeadUserName", tUser == null ? "" : tUser.getfName());

                        myproject_object.put("FSDate", myproject.getFsdate() == null ? "" : sdf.format(myproject.getFsdate()));
                        myproject_object.put("FEDate", myproject.getFedate() == null ? "" : sdf.format(myproject.getFedate()));
                        myproject_object.put("FStartDate", myproject.getFstartdate() == null ? "" : sdf.format(myproject.getFstartdate()));
                        myproject_object.put("FEndDate", myproject.getFenddate() == null ? "" : sdf.format(myproject.getFenddate()));
                        if (myproject.getFmaterialid() == null)
                            myproject_object.put("isFMatertial", 0);
                        else
                            myproject_object.put("isFMatertial", 1);
                        myproject_object.put("FMaterialID", myproject.getFmaterialid() == null ? "" : ParamTools.getEnParam(myproject.getFmaterialid().toString()));
//                        tMaterial = tMaterialService.findById(myproject.getFmaterialid());
//                        myproject_object.put("FMaterialName", tMaterial == null ? "" : tMaterial.getFname());
                        myproject_object.put("FMaterialName", "");

                        myproject_object.put("FDeptID", ParamTools.getEnParam(myproject.getFdeptid().toString()));
//                        tDept = tDeptService.findById(myproject.getFdeptid());
//                        myproject_object.put("FDeptName", tDept == null ? "" : tDept.getFname());
                        TMajor major = tMajorService.findById(myproject.getFdeptid());
                        myproject_object.put("FDeptName", major == null ? "" : major.getFmajorname());
                        myproject_object.put("FContractNo", myproject.getFcontractno());

                        myproject_object.put("FSecret", ParamTools.getEnParam(myproject.getFsecret().toString()));
                        projectSecret = tProjectSecretService.findById(myproject.getFsecret());
                        myproject_object.put("FSecretName", projectSecret == null ? "" : projectSecret.getFname());

                        myproject_object.put("FFrom", myproject.getFfrom());
                        myproject_object.put("FFunds", myproject.getFfunds());
                        myproject_object.put("FSysNo", myproject.getFsysno() == null ? "" : myproject.getFsysno());
                        myproject_object.put("FSysNoNum", myproject.getFsysnonum() == null ? "" : myproject.getFsysnonum());
                        myproject_object.put("FInsideNo", myproject.getFinsideno() == null ? "" : myproject.getFinsideno());


                        myproject_object.put("FCID", myproject.getFcid());
                        myproject_object.put("FUID", myproject.getFuid());
                        myproject_object.put("FCDATE", myproject.getFcdate() == null ? "" : sdf.format(myproject.getFcdate()));
                        myproject_object.put("FUDATE", myproject.getFudate() == null ? "" : sdf.format(myproject.getFudate()));
                        myproject_object.put("FState", myproject.getFstate());
                        myproject_Array2.add(myproject_object);
                    }
                    // 返回值
                    object.put("list", myproject_Array2);
                    object.put("total", myprojectPageInfo.getTotal()); // 总行数
                    object.put("page", myprojectPageInfo.getPageNum()); // 当前页数
                    object.put("result", "success");

                } else if (showtype < 3) {
                    // 返回值
                    object.put("list", myproject_Array);
                    object.put("total", myprojectPageInfo.getTotal()); // 总行数
                    object.put("page", myprojectPageInfo.getPageNum()); // 当前页数
                    object.put("result", "success");
                }
            } else {
                // 返回值
                object.put("list", myproject_Array);
                object.put("total", 0); // 总行数
                object.put("page", 0); // 当前页数
                object.put("result", "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
//        System.out.println(object.toString());
        return object.toString();
    }


    /**
     * 获取项目信息-检索
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querymyprojectjs", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querymyprojectjs(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        String xmtype = jsonParam.getString("xmtype");
        String khname = jsonParam.getString("khname");

        try {

            String CookiesLoginmyprojectteamID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectteamID != null && !CookiesLoginmyprojectteamID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginmyprojectteamID);
            }


            // 获取数据库记录
            JSONArray myproject_Array = new JSONArray();
            // 获取数据库记录
            JSONArray myproject_Array2 = new JSONArray();
            // 查询条件
            TProjectExample TProjectExample = new TProjectExample();
            TProjectExample.Criteria criteria = TProjectExample.createCriteria();
            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }
            List<Long> typeid = new ArrayList<>();
            List<Long> khid = new ArrayList<>();
            if (xmtype != null && !xmtype.equals("")) {
                TProjectTypeExample tProjectTypeExample = new TProjectTypeExample();
                tProjectTypeExample.or().andFnameLike("%" + xmtype + "%");
                List<TProjectType> typeList = tProjectTypeService.findByExample(tProjectTypeExample);
                if (typeList.size() > 0) {
                    for (TProjectType tProjectType : typeList) {
                        typeid.add(tProjectType.getFkeyid());
                    }
                    criteria.andFtypeidIn(typeid);
                } else {
                    typeid.add(0L);
                    criteria.andFtypeidIn(typeid);
                }
            }
//            if (khname != null && !khname.equals("")) {
//                TCustomerExample tCustomerExample = new TCustomerExample();
//                tCustomerExample.or().andFnameLike("%" + khname + "%");
//                List<TCustomer> customerList = tCustomerService.findByExample(tCustomerExample);
//                if (customerList.size() > 0) {
//                    for (TCustomer tCustomer : customerList) {
//                        khid.add(tCustomer.getFkeyid());
//                    }
//
//                } else {
//                    khid.add(0L);
//                    criteria.andFcustomeridIn(khid);
//                }
//            }
            TProjectExample.setOrderByClause("FCDATE desc");
            PageInfo<TProject> myprojectPageInfo = tProjectService.findByExampleMapper(TProjectExample, (page - 1) * results, results);
            List<TProject> myproject_list = myprojectPageInfo.getList();
            TUser tUser = null;
//            TMaterial tMaterial = null;
//            TCustomer tCustomer = null;
            TProjectType tProjectType = null;
            TProjectLevel tProjectLevel = null;
            TDept tDept = null;
            TProjectSecret projectSecret = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            TProjectExceed projectExceed = null;
            int yuqiday = 0;
            Date newdate = null;
            Date fedate = null;
            int dayDiffer = -1;
            for (TProject myproject : myproject_list) {
                JSONObject myproject_object = new JSONObject();
                //检查是否逾期
                projectExceed = tProjectExceedService.findById(1l);
                //获取提前几天 提醒
                yuqiday = projectExceed == null ? 0 : projectExceed.getFday();
                //获取当前时间
                newdate = new Date();
                //获取计划完成时间
                fedate = myproject.getFedate();
                //判断 当前时间与计划完成时间的差值
                dayDiffer = getDayDiffer(newdate, fedate);
                if (dayDiffer <= yuqiday && dayDiffer >= 0) {
                    //预警

                    myproject_object.put("FStates", 1);
                } else if (dayDiffer < 0) {
                    //逾期

                    myproject_object.put("FStates", 2);
                } else {
                    //正常
                    myproject_object.put("FStates", 3);
                }
                myproject_object.put("key", ParamTools.getEnParam(myproject.getFkeyid().toString()));
                myproject_object.put("FName", myproject.getFname());
                myproject_object.put("FUserID", ParamTools.getEnParam(myproject.getFuserid().toString()));
                tUser = tUserService.findById(myproject.getFuserid());
                myproject_object.put("FUserName", tUser == null ? "" : tUser.getfName());

                myproject_object.put("FDate", myproject.getFdate() == null ? "" : sdf.format(myproject.getFdate()));
                myproject_object.put("FNo", myproject.getFno());

                myproject_object.put("FCustomerID", ParamTools.getEnParam(myproject.getFcustomerid().toString()));
//                tCustomer = tCustomerService.findById(myproject.getFcustomerid());
//                myproject_object.put("FCustomerName", tCustomer == null ? "" : tCustomer.getFname());
                myproject_object.put("FCustomerName", "");

                myproject_object.put("FAsk", myproject.getFask());
                myproject_object.put("FNote", myproject.getFnote());

                myproject_object.put("FTypeID", ParamTools.getEnParam(myproject.getFtypeid().toString()));
                tProjectType = tProjectTypeService.findById(myproject.getFtypeid());
                myproject_object.put("FTypeName", tProjectType == null ? "" : tProjectType.getFname());

                myproject_object.put("FLevelID", ParamTools.getEnParam(myproject.getFlevelid().toString()));
                tProjectLevel = tProjectLevelService.findById(myproject.getFlevelid());
                myproject_object.put("FLevelName", tProjectLevel.getFname());

                myproject_object.put("FLeadUserID", ParamTools.getEnParam(myproject.getFleaduserid().toString()));
                tUser = tUserService.findById(myproject.getFleaduserid());
                myproject_object.put("FLeadUserName", tUser == null ? "" : tUser.getfName());

                myproject_object.put("FSDate", myproject.getFsdate() == null ? "" : sdf.format(myproject.getFsdate()));
                myproject_object.put("FEDate", myproject.getFedate() == null ? "" : sdf.format(myproject.getFedate()));
                myproject_object.put("FStartDate", myproject.getFstartdate() == null ? "" : sdf.format(myproject.getFstartdate()));
                myproject_object.put("FEndDate", myproject.getFenddate() == null ? "" : sdf.format(myproject.getFenddate()));
                if (myproject.getFmaterialid() == null)
                    myproject_object.put("isFMatertial", 0);
                else
                    myproject_object.put("isFMatertial", 1);
                myproject_object.put("FMaterialID", myproject.getFmaterialid() == null ? "" : ParamTools.getEnParam(myproject.getFmaterialid().toString()));
//                tMaterial = tMaterialService.findById(myproject.getFmaterialid());
//                myproject_object.put("FMaterialName", tMaterial == null ? "" : tMaterial.getFname());
                myproject_object.put("FMaterialName", "");

                myproject_object.put("FDeptID", ParamTools.getEnParam(myproject.getFdeptid().toString()));
//                tDept = tDeptService.findById(myproject.getFdeptid());
//                myproject_object.put("FDeptName", tDept == null ? "" : tDept.getFname());
                TMajor major = tMajorService.findById(myproject.getFdeptid());
                myproject_object.put("FDeptName", major == null ? "" : major.getFmajorname());
                myproject_object.put("FContractNo", myproject.getFcontractno());

                myproject_object.put("FSecret", ParamTools.getEnParam(myproject.getFsecret().toString()));
                projectSecret = tProjectSecretService.findById(myproject.getFsecret());
                myproject_object.put("FSecretName", projectSecret == null ? "" : projectSecret.getFname());

                myproject_object.put("FFrom", myproject.getFfrom());
                myproject_object.put("FFunds", myproject.getFfunds());
                myproject_object.put("FSysNo", myproject.getFsysno() == null ? "" : myproject.getFsysno());
                myproject_object.put("FSysNoNum", myproject.getFsysnonum() == null ? "" : myproject.getFsysnonum());
                myproject_object.put("FInsideNo", myproject.getFinsideno() == null ? "" : myproject.getFinsideno());


                myproject_object.put("FCID", myproject.getFcid());
                myproject_object.put("FUID", myproject.getFuid());
                myproject_object.put("FCDATE", myproject.getFcdate() == null ? "" : sdf.format(myproject.getFcdate()));
                myproject_object.put("FUDATE", myproject.getFudate() == null ? "" : sdf.format(myproject.getFudate()));
                myproject_object.put("FState", myproject.getFstate());
                myproject_Array.add(myproject_object);
            }
            // 返回值
            object.put("list", myproject_Array);
            object.put("total", myprojectPageInfo.getTotal()); // 总行数
            object.put("page", myprojectPageInfo.getPageNum()); // 当前页数
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
     * 获取项目信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatamyprojectSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatamyprojectSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {


            List<Integer> stateint = new ArrayList<>();
            stateint.add(0);
            stateint.add(1);

            // 获取数据库记录
            JSONArray myproject_Array = new JSONArray();
            TProjectExample myprojectExample = new TProjectExample();
            TProjectExample.Criteria criteria = myprojectExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            //criteria.andFstateEqualTo(0);
            criteria.andFstateIn(stateint);
            myprojectExample.setOrderByClause("fname asc");
            List<TProject> myproject_list = tProjectService.findByExample(myprojectExample);
            for (TProject myproject : myproject_list) {
                JSONObject myproject_object = new JSONObject();
                myproject_object.put("id", ParamTools.getEnParam(myproject.getFkeyid().toString()));
                myproject_object.put("text", myproject.getFname());
                myproject_Array.add(myproject_object);
            }
            // 返回值
            object.put("list", myproject_Array);
            object.put("result", "success");
            object.put("results", myproject_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取项目信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatamyprojectSelectbom", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatamyprojectSelectbom(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray myproject_Array = new JSONArray();
            TProjectExample myprojectExample = new TProjectExample();
            TProjectExample.Criteria criteria = myprojectExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            myprojectExample.setOrderByClause("fname asc");
            List<TProject> myproject_list = tProjectService.findByExample(myprojectExample);
            for (TProject myproject : myproject_list) {
                JSONObject myproject_object = new JSONObject();
                myproject_object.put("id", ParamTools.getEnParam(myproject.getFkeyid().toString()));
                myproject_object.put("text", myproject.getFname());
                myproject_Array.add(myproject_object);
            }
            // 返回值

            object.put("list", myproject_Array);
            object.put("result", "success");
            object.put("results", myproject_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取项目信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querymyprojectInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querymyprojectInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            String CookiesLoginmyprojectteamID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectteamID != null && !CookiesLoginmyprojectteamID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginmyprojectteamID);
            }

            // 查询项目信息
            TProject myproject = tProjectService.findById(key);
            int isrole = 3;
            TUser tUser = null;
//            TMaterial tMaterial = null;
//            TCustomer tCustomer = null;
            TProjectType tProjectType = null;
            TProjectLevel tProjectLevel = null;
            TDept tDept = null;
            TProjectSecret projectSecret = null;
            JSONObject myproject_object = new JSONObject();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            myproject_object.put("key", ParamTools.getEnParam(myproject.getFkeyid().toString()));
            myproject_object.put("FName", myproject.getFname());
            myproject_object.put("FUserID", ParamTools.getEnParam(myproject.getFuserid().toString()));
            tUser = tUserService.findById(myproject.getFuserid());
            myproject_object.put("FUserName", tUser == null ? "" : tUser.getfName());

            myproject_object.put("FDate", myproject.getFdate() == null ? "" : sdf.format(myproject.getFdate()));
            myproject_object.put("FNo", myproject.getFno());

            myproject_object.put("FCustomerID", ParamTools.getEnParam(myproject.getFcustomerid().toString()));
//            tCustomer = tCustomerService.findById(myproject.getFcustomerid());
//            myproject_object.put("FCustomerName", tCustomer == null ? "" : tCustomer.getFname());
            myproject_object.put("FCustomerName", "");

            myproject_object.put("FAsk", myproject.getFask());
            myproject_object.put("FNote", myproject.getFnote());

            myproject_object.put("FTypeID", ParamTools.getEnParam(myproject.getFtypeid().toString()));
            tProjectType = tProjectTypeService.findById(myproject.getFtypeid());
            myproject_object.put("FTypeName", tProjectType == null ? "" : tProjectType.getFname());

            myproject_object.put("FLevelID", ParamTools.getEnParam(myproject.getFlevelid().toString()));
            tProjectLevel = tProjectLevelService.findById(myproject.getFlevelid());
            myproject_object.put("FLevelName", tProjectLevel.getFname());

            myproject_object.put("FLeadUserID", ParamTools.getEnParam(myproject.getFleaduserid().toString()));
            tUser = tUserService.findById(myproject.getFleaduserid());
            myproject_object.put("FLeadUserName", tUser == null ? "" : tUser.getfName());

            myproject_object.put("FSDate", myproject.getFsdate() == null ? "" : sdf.format(myproject.getFsdate()));
            myproject_object.put("FEDate", myproject.getFedate() == null ? "" : sdf.format(myproject.getFedate()));
            myproject_object.put("FStartDate", myproject.getFstartdate() == null ? "" : sdf.format(myproject.getFstartdate()));
            myproject_object.put("FEndDate", myproject.getFenddate() == null ? "" : sdf.format(myproject.getFenddate()));
            if (myproject.getFmaterialid() == null)
                myproject_object.put("isFMatertial", 0);
            else
                myproject_object.put("isFMatertial", 1);
            myproject_object.put("FMaterialID", myproject.getFmaterialid() == null ? "" : ParamTools.getEnParam(myproject.getFmaterialid().toString()));
//            tMaterial = tMaterialService.findById(myproject.getFmaterialid());
//            myproject_object.put("FMaterialName", tMaterial == null ? "" : tMaterial.getFname());
            myproject_object.put("FMaterialName", "");

            myproject_object.put("FDeptID", ParamTools.getEnParam(myproject.getFdeptid().toString()));
//            tDept = tDeptService.findById(myproject.getFdeptid());
//            myproject_object.put("FDeptName", tDept == null ? "" : tDept.getFname());
            TMajor major = tMajorService.findById(myproject.getFdeptid());
            try {
                TCollege tCollege = tCollegeService.findById(major.getFcollegeid());
                myproject_object.put("TCollegeName", tCollege == null ? "" : tCollege.getFcollegename());
                if(tCollege != null)
                    myproject_object.put("FDeptName", tCollege.getFcollegename() + "/" + major.getFmajorname());
                else
                    myproject_object.put("FDeptName",  major.getFmajorname());
            } catch (Exception e) {
                myproject_object.put("TCollegeName","");
                myproject_object.put("FDeptName", major == null ? "" : major.getFmajorname());
            }

            myproject_object.put("FContractNo", myproject.getFcontractno());

            myproject_object.put("FSecret", ParamTools.getEnParam(myproject.getFsecret().toString()));
            projectSecret = tProjectSecretService.findById(myproject.getFsecret());
            myproject_object.put("FSecretName", projectSecret == null ? "" : projectSecret.getFname());

            myproject_object.put("FFrom", myproject.getFfrom());
            myproject_object.put("FFunds", myproject.getFfunds());
            myproject_object.put("FSysNo", myproject.getFsysno() == null ? "" : myproject.getFsysno());
            myproject_object.put("FSysNoNum", myproject.getFsysnonum() == null ? "" : myproject.getFsysnonum());
            myproject_object.put("FInsideNo", myproject.getFinsideno() == null ? "" : myproject.getFinsideno());


            myproject_object.put("FCID", myproject.getFcid());
            myproject_object.put("FUID", myproject.getFuid());
            myproject_object.put("FCDATE", myproject.getFcdate() == null ? "" : sdf.format(myproject.getFcdate()));
            myproject_object.put("FUDATE", myproject.getFudate() == null ? "" : sdf.format(myproject.getFudate()));
            myproject_object.put("FState", myproject.getFstate());
            if (Long.parseLong(uid) == myproject.getFleaduserid().longValue()) {
                isrole = 2;
            }


            JSONArray projectcourse_Array = null;
            try {
                projectcourse_Array = new JSONArray();
                TProjectCourseExample tProjectCourseExample = new TProjectCourseExample();

                tProjectCourseExample.createCriteria().andFprojectidEqualTo(key);
                List<TProjectCourse> list = tProjectCourseService.findByExample(tProjectCourseExample);
                for (TProjectCourse tCourseMajor: list) {
                    TMajor tMajor = tMajorService.findById(tCourseMajor.getFtmid());
                    TCourse course = tCourseService.findById(tCourseMajor.getFcourseid());
                    TTrainingProgram trainingProgramServiceById = tTrainingProgramService.findById(tCourseMajor.getFtpid());
                    JSONObject roleObject = new JSONObject();
                    roleObject.put("id", ParamTools.getEnParam(tCourseMajor.getFtpcid().toString()));
                    roleObject.put("name",trainingProgramServiceById.getFname()+"/" + tMajor.getFmajorname() + "/" + course.getFno() + "/" +course.getFname());
                    projectcourse_Array.add(roleObject);
                }
            } catch (Exception e) {

            }
            myproject_object.put("courselist", projectcourse_Array);


            // 返回值
            object.put("isrole", isrole);
            object.put("info", myproject_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加项目信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("添加项目信息")
    @ResponseBody
    @RequestMapping(value = "/addmyproject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addmyproject(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FCustomerID = jsonParam.getString("FCustomerID");
        String FNo = jsonParam.getString("FNo");
        String FName = jsonParam.getString("FName");
        String FTypeID = jsonParam.getString("FTypeID");
        String FLeadUserID = jsonParam.getString("FLeadUserID");
        String FAsk = jsonParam.getString("FAsk");
        String FSDate = jsonParam.getString("FSDate");
        String FEDate = jsonParam.getString("FEDate");
        String FLevelID = jsonParam.getString("FLevelID");
        String FDeptID = jsonParam.getString("FDeptID");
        String FContractNo = jsonParam.getString("FContractNo");
        String FFrom = jsonParam.getString("FFrom");
        String FFunds = jsonParam.getString("FFunds");
        String FNote = jsonParam.getString("FNote");
        String FSecret = jsonParam.getString("FSecret");
        String FInsideNo = jsonParam.getString("FInsideNo");

        String FCourselist = jsonParam.getString("FCourselist");

//        String FMName = jsonParam.getString("FMName");
        try {


            if (repeaTProject(0L, FNo, "1") == 0) {

                if (FCustomerID != null && !FCustomerID.equals("") && !FCustomerID.equals("0")) {
                    FCustomerID = ParamTools.getdeParam(FCustomerID);
                } else {
                    FCustomerID = "0";
                }
                if (FTypeID != null && !FTypeID.equals("") && !FTypeID.equals("0")) {
                    FTypeID = ParamTools.getdeParam(FTypeID);
                } else {
                    FTypeID = "0";
                }
                if (FLeadUserID != null && !FLeadUserID.equals("") && !FLeadUserID.equals("0")) {
                    FLeadUserID = ParamTools.getdeParam(FLeadUserID);
                } else {
                    FLeadUserID = "0";
                }
                if (FLevelID != null && !FLevelID.equals("") && !FLevelID.equals("0")) {
                    FLevelID = ParamTools.getdeParam(FLevelID);
                } else {
                    FLevelID = "0";
                }
                if (FDeptID != null && !FDeptID.equals("") && !FDeptID.equals("0")) {
                    FDeptID = ParamTools.getdeParam(FDeptID);
                } else {
                    FDeptID = "0";
                }

                if (FSecret != null && !FSecret.equals("") && !FSecret.equals("0")) {
                    FSecret = ParamTools.getdeParam(FSecret);
                } else {
                    FSecret = "0";
                }
                String CookiesLoginmyprojectID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginmyprojectID != null && !CookiesLoginmyprojectID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginmyprojectID);
                }
                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                //传入流水号的值
                int maxnum = 1;
                //查询当前年月
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMM");
                String yymm = sdf2.format(new Date());
                //查询当前几号
                int day = getDay();
                //查询项目中当前年月 最大月流水号
                TProjectExample tProjectExample = new TProjectExample();
                TProjectExample.Criteria criteria = tProjectExample.createCriteria();
                criteria.andFyymmnumEqualTo(yymm);
                tProjectExample.setOrderByClause("FSysNoNum desc limit 1 ");
                List<TProject> projectList = tProjectService.findByExample(tProjectExample);
                if (projectList.size() > 0) {
                    maxnum = (projectList.get(0).getFsysnonum() + 1);
                } else {
                    maxnum = 1;
                }
                //生成流水号
                String numberE = formatNumberE(maxnum, yymm);
                long key = idWorker.nextId();
                long key2 = idWorker.nextId();

                //******************************************************


//                //新建bomm信息
//                TMaterial material = new TMaterial();
//                material.setFkeyid(key2);
//                material.setFname(FName + "产品");
//                material.setFno("CP" + FNo);
//                material.setFmtype(1);
//                material.setFtype(2);
//                material.setFunitid(0l);
//                material.setFnote(FNote);
//                material.setFfrom(0l);
//                material.setFcid(Long.parseLong(uid));
//                material.setFcdate(new Date());
//                material.setFmodel("");
//                material.setFspecs("");
//                material.setFsize("");
//                material.setFtexture("");
//                material.setFoutside("");
//                tMaterialService.save(material);
//                TBomMExample tBomMExample = new TBomMExample();
//                tBomMExample.createCriteria().andFmaterialnoEqualTo("CP" + FNo).andFshowEqualTo(1);
//                List<TBomM> bomMList = tBomMService.findByExample(tBomMExample);
//                Long bomid = 0L;
//                if (bomMList.size() <= 0) {
//                    bomid = idWorker.nextId();
//                    TBomM tBomM = new TBomM();
//                    tBomM.setFkeyid(bomid);
//                    tBomM.setFcid(Long.parseLong(uid));
//                    tBomM.setFstate(0);
//                    tBomM.setFmaterialid(key2);
//                    tBomM.setFmaterialno("CP" + FNo);
//                    tBomM.setFeditionno(1);
//                    tBomM.setFedition("V01");
//                    tBomM.setFnote(FNote);
//                    tBomM.setFshow(1);
//                    tBomMService.save(tBomM);
//                } else {
//                    for (TBomM tbom : bomMList) {
//                        bomid = tbom.getFkeyid();
//                    }
//                }
                // 新建项目信息
                TProject myproject = new TProject();
                myproject.setFkeyid(key);
                myproject.setFcustomerid(Long.parseLong(FCustomerID));
                myproject.setFno(FNo);
                myproject.setFname(FName);
                myproject.setFtypeid(Long.parseLong(FTypeID));
                myproject.setFleaduserid(Long.parseLong(FLeadUserID));
                myproject.setFlevelid(Long.parseLong(FLevelID));
                myproject.setFask(FAsk);
                myproject.setFsdate(sdf.parse(FSDate));
                myproject.setFedate(sdf.parse(FEDate));
                myproject.setFcontractno(FContractNo);
                myproject.setFfrom(FFrom);
                myproject.setFfunds(FFunds);
                myproject.setFnote(FNote);
                myproject.setFuserid(Long.parseLong(uid));
                myproject.setFdeptid(Long.parseLong(FDeptID));
                myproject.setFsecret(Long.parseLong(FSecret));
                myproject.setFcid(Long.parseLong(uid));
                myproject.setFsysno(numberE);
                myproject.setFsysnonum(maxnum);
                myproject.setFyymmnum(yymm);
                myproject.setFinsideno(FInsideNo);
                myproject.setFcdate(new Date());
                myproject.setFmaterialid(key2);
                tProjectService.save(myproject);


                //添加项目团队中。负责人信息
                TProjectTeam tProjectTeam = new TProjectTeam();
                tProjectTeam.setFprojectid(key);
                tProjectTeam.setFuserid(Long.parseLong(FLeadUserID));
                tProjectTeam.setFrole(1);
                tProjectTeam.setFduty("");
                tProjectTeam.setFnote("");
                tProjectTeam.setFcid(Long.parseLong(uid));
                tProjectTeamService.save(tProjectTeam);


                if (FCourselist != null && !FCourselist.equals("")) {
                    //增加新选的
                    JSONArray course_Array = JSONArray.parseArray(FCourselist);
                    for (Object courseid: course_Array) {
                        Long courseId = Long.parseLong(ParamTools.getdeParam(courseid.toString()));
                        TTrainingProgramCourse tTrainingProgramCourse = tTrainingProgramCourseService.findById(courseId);

                        TProjectCourseExample tProjectCourseExample = new TProjectCourseExample();
                        TProjectCourseExample.Criteria criteria1 = tProjectCourseExample.createCriteria();
                        criteria1.andFprojectidEqualTo(key);
                        criteria1.andFtpcidEqualTo(courseId);
                        List<TProjectCourse> projectCourseList = tProjectCourseService.findByExample(tProjectCourseExample);
                        if(projectCourseList.size()<=0){
                            TProjectCourse tProjectCourse = new TProjectCourse();
                            tProjectCourse.setFprojectid(key);
                            tProjectCourse.setFcourseid(tTrainingProgramCourse.getFcourseid());
                            tProjectCourse.setFtmid(tTrainingProgramCourse.getFtmid());
                            tProjectCourse.setFtpid(tTrainingProgramCourse.getFtpid());
                            tProjectCourse.setFtpcid(tTrainingProgramCourse.getFkeyid());
                            tProjectCourse.setFcid(Long.parseLong(uid));
                            tProjectCourse.setFcdate(new Date());
                            tProjectCourseService.save(tProjectCourse);
                        }

                    }
                }

                //添加日志
                ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                projectLog.addprojectlog("创建了项目【" + FName + "】", String.valueOf(key), "项目信息", "创建项目", "", uid);


                TUser tUser = tUserService.findById(Long.parseLong(uid));
                TLogAction logAction = new TLogAction();
                logAction.setfUserId(tUser.getfKeyId());
                logAction.setfUserName(tUser.getfName());
                logAction.setfType(3);
                logAction.setfPath("myproject/addmyproject{" + myproject.getFkeyid() + "}");
                logAction.setfUserType(1);

                logAction.setfMemo("新增项目【" + FName + "】信息。");
                logActionService.save(logAction);


                // 返回值
                object.put("result", "success");
            } else {
                //编号重复后， 显示正在使用编号的信息 项目名称、编号、负责人
                TProjectExample myprojectExample = new TProjectExample();
                TProjectExample.Criteria criteria = myprojectExample.createCriteria();
                criteria.andFnoEqualTo(FNo);
                List<TProject> projectList = tProjectService.findByExample(myprojectExample);
                TUser user = tUserService.findById(projectList.get(0).getFuserid());
                JSONObject myproject_object = new JSONObject();
                myproject_object.put("FuserName", user.getfName() == null ? "" : user.getfName()); //项目发布人
                myproject_object.put("FName", projectList.get(0).getFname());
                myproject_object.put("FNo", projectList.get(0).getFno());
                object.put("faillist", myproject_object);
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


//    /**
//     * 根据选择的零部件 获取任务名称和任务编号
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/myprojectttaskNoName", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String myprojectttaskNoName(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String id = jsonParam.getString("id");//零部件ID  TMaterialID
//        try {
//            String CookiesLoginmyprojectID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//            String uid = ""; // 当前登录用户ID
//            if (CookiesLoginmyprojectID != null && !CookiesLoginmyprojectID.equals("")) {
//                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
//            }
//            id = id == null ? "0" : ParamTools.getdeParam(id);
//            TMaterial tMaterial = tMaterialService.findById(Long.parseLong(id));
//            if (tMaterial != null) {
//                //生成任务名称 + 任务编号
//                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                //传入流水号的值
//                int maxnum = 1;
//                //查询当前年月
//                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMM");
//                String yymm = sdf2.format(new Date());
//                //查询当前几号
//                int day = getDay();
//                //查询项目中当前年月 最大月流水号
//                TSysnoExample tSysnoExample = new TSysnoExample();
//                tSysnoExample.createCriteria().andFtypeEqualTo(1).andFyymmnumEqualTo(yymm);
//                tSysnoExample.setOrderByClause("FSysNoNum desc limit 1 ");
//                List<TSysno> tSysnos = tSysNoService.findByExample(tSysnoExample);
//                if (tSysnos.size() > 0) {
//                    maxnum = (tSysnos.get(0).getFsysnonum() + 1);
//                } else {
//                    maxnum = 1;
//                }
//                //生成流水号
//                String numberE = formatNumberE(maxnum, yymm, "RW");
//                TSysno tSysno = new TSysno();
//                tSysno.setFtype(1);
//                tSysno.setFsysno(numberE);
//                tSysno.setFsysnonum(maxnum);
//                tSysno.setFyymmnum(yymm);
//                tSysno.setFcdate(new Date());
//                tSysno.setFcid(Long.parseLong(uid));
//                tSysNoService.save(tSysno);
//
//                // 返回值
//                object.put("FRWNo", numberE);
//                object.put("FRWName", tMaterial.getFname() + "任务");
//                object.put("result", "success");
//            } else {
//                object.put("result", "error");
//                object.put("error", "请重新选择零部件后重试！");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }


    /**
     * 修改项目信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("修改项目信息")
    @ResponseBody
    @RequestMapping(value = "/updatemyproject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatemyproject(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FCustomerID = jsonParam.getString("FCustomerID");
        String FNo = jsonParam.getString("FNo");
        String FName = jsonParam.getString("FName");
        String FTypeID = jsonParam.getString("FTypeID");
        String FLeadUserID = jsonParam.getString("FLeadUserID");
        String FAsk = jsonParam.getString("FAsk");
        String FSDate = jsonParam.getString("FSDate");
        String FEDate = jsonParam.getString("FEDate");
        String FLevelID = jsonParam.getString("FLevelID");
        String FDeptID = jsonParam.getString("FDeptID");
        String FContractNo = jsonParam.getString("FContractNo");
        String FFrom = jsonParam.getString("FFrom");
        String FFunds = jsonParam.getString("FFunds");
        String FNote = jsonParam.getString("FNote");
        String FSecret = jsonParam.getString("FSecret");
        String FInsideNo = jsonParam.getString("FInsideNo");

        String FMName = jsonParam.getString("FMName");
        String FCourselist = jsonParam.getString("FCourselist");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            if (repeaTProject(key, FNo, "2") == 0) {

                if (FCustomerID != null && !FCustomerID.equals("") && !FCustomerID.equals("0")) {
                    FCustomerID = ParamTools.getdeParam(FCustomerID);
                } else {
                    FCustomerID = "0";
                }

                if (FTypeID != null && !FTypeID.equals("") && !FTypeID.equals("0")) {
                    FTypeID = ParamTools.getdeParam(FTypeID);
                } else {
                    FTypeID = "0";
                }
                if (FLeadUserID != null && !FLeadUserID.equals("") && !FLeadUserID.equals("0")) {
                    FLeadUserID = ParamTools.getdeParam(FLeadUserID);
                } else {
                    FLeadUserID = "0";
                }
                if (FLevelID != null && !FLevelID.equals("") && !FLevelID.equals("0")) {
                    FLevelID = ParamTools.getdeParam(FLevelID);
                } else {
                    FLevelID = "0";
                }
                if (FDeptID != null && !FDeptID.equals("") && !FDeptID.equals("0")) {
                    FDeptID = ParamTools.getdeParam(FDeptID);
                } else {
                    FDeptID = "0";
                }

                if (FSecret != null && !FSecret.equals("") && !FSecret.equals("0")) {
                    FSecret = ParamTools.getdeParam(FSecret);
                } else {
                    FSecret = "0";
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                String CookiesLoginmyprojectID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginmyprojectID != null && !CookiesLoginmyprojectID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginmyprojectID);
                }

                TProject tProject = tProjectService.findById(key);
                if (tProject.getFstate() < 2) {

//                    TMaterial tMaterial = new TMaterial();
//                    tMaterial.setFkeyid(tProject.getFmaterialid());
//                    tMaterial.setFname(FMName);
//                    tMaterial.setFuid(Long.parseLong(uid));
//                    tMaterial.setFudate(new Date());
//                    tMaterialService.update(tMaterial);

                    // 更新项目信息
                    TProject myproject = new TProject();
                    myproject.setFkeyid(key);
                    myproject.setFno(FNo);
                    myproject.setFname(FName);
                    myproject.setFtypeid(Long.parseLong(FTypeID));
                    myproject.setFleaduserid(Long.parseLong(FLeadUserID));
                    myproject.setFlevelid(Long.parseLong(FLevelID));
                    myproject.setFask(FAsk);
                    myproject.setFsdate(sdf.parse(FSDate));
                    myproject.setFedate(sdf.parse(FEDate));
                    myproject.setFcontractno(FContractNo);
                    myproject.setFfrom(FFrom);
                    myproject.setFcustomerid(Long.parseLong(FCustomerID));
                    myproject.setFfunds(FFunds);
                    myproject.setFnote(FNote);
                    myproject.setFinsideno(FInsideNo);
                    myproject.setFdeptid(Long.parseLong(FDeptID));
                    myproject.setFsecret(Long.parseLong(FSecret));
                    myproject.setFuid(Long.parseLong(uid));
                    myproject.setFudate(new Date());
                    tProjectService.update(myproject);

                    //修改团队表 负责人id
                    TProjectTeamExample tProjectTeamExample = new TProjectTeamExample();
                    TProjectTeamExample.Criteria criteria = tProjectTeamExample.createCriteria();
                    criteria.andFprojectidEqualTo(Long.parseLong(id));
                    criteria.andFroleEqualTo(1);
                    TProjectTeam tProjectTeam = new TProjectTeam();
                    tProjectTeam.setFuserid(Long.parseLong(FLeadUserID));
                    tProjectTeam.setFudate(new Date());
                    tProjectTeam.setFuid(Long.parseLong(uid));
                    tProjectTeamService.updateByExample(tProjectTeam, tProjectTeamExample);



                    if (FCourselist != null) {
                        if (FCourselist.equals("")) {
                            //删除全部
                            TProjectCourseExample tProjectCourseExample = new TProjectCourseExample();
                            tProjectCourseExample.createCriteria().andFcourseidEqualTo(key);
                            tProjectCourseService.deleteByByExample(tProjectCourseExample);
                        } else {
                            //增加新选的
                            JSONArray major_Array = JSONArray.parseArray(FCourselist);
                            List<Long> tempRoleID = new ArrayList<>();
                            for (Object courseid: major_Array) {

                                Long courseId = Long.parseLong(ParamTools.getdeParam(courseid.toString()));
                                TTrainingProgramCourse tTrainingProgramCourse = tTrainingProgramCourseService.findById(courseId);

                                TProjectCourseExample tProjectCourseExample = new TProjectCourseExample();
                                TProjectCourseExample.Criteria criteria1 = tProjectCourseExample.createCriteria();
                                criteria1.andFprojectidEqualTo(key);
                                criteria1.andFtpcidEqualTo(courseId);
                                List<TProjectCourse> projectCourseList = tProjectCourseService.findByExample(tProjectCourseExample);
                                if(projectCourseList.size()<=0){
                                    TProjectCourse tProjectCourse = new TProjectCourse();
                                    tProjectCourse.setFprojectid(key);
                                    tProjectCourse.setFcourseid(tTrainingProgramCourse.getFcourseid());
                                    tProjectCourse.setFtmid(tTrainingProgramCourse.getFtmid());
                                    tProjectCourse.setFtpid(tTrainingProgramCourse.getFtpid());
                                    tProjectCourse.setFtpcid(tTrainingProgramCourse.getFkeyid());
                                    tProjectCourse.setFcid(Long.parseLong(uid));
                                    tProjectCourse.setFcdate(new Date());
                                    tProjectCourseService.save(tProjectCourse);
                                }
                                tempRoleID.add(courseId);
                            }
                            //删除多余的
                            TProjectCourseExample tProjectCourseExample = new TProjectCourseExample();
                            if (tempRoleID.size() > 0) {
                                tProjectCourseExample.or()
                                        .andFprojectidEqualTo(key)
                                        .andFtpcidNotIn(tempRoleID);
                            } else {
                                tProjectCourseExample.or().andFprojectidEqualTo(key);
                            }
                            tProjectCourseService.deleteByByExample(tProjectCourseExample);
                        }
                    }

                    //添加日志
                    ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                    projectLog.addprojectlog("修改了项目【" + FName + "】", String.valueOf(key), "项目信息", "修改项目", "", uid);

                    TUser tUser = tUserService.findById(Long.parseLong(uid));
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserId(tUser.getfKeyId());
                    logAction.setfUserName(tUser.getfName());
                    logAction.setfType(3);
                    logAction.setfPath("myproject/updatemyproject{" + key + "}");
                    logAction.setfUserType(1);

                    logAction.setfMemo("修改项目【" + FName + "】信息。");
                    logActionService.save(logAction);

                    // 返回值
                    object.put("result", "success");
                } else {
                    object.put("result", "error");
                    object.put("error", "该项目已完成或关闭，不能修改！");
                }
            } else {
                //编号重复后， 显示正在使用编号的信息 项目名称、编号、负责人
                TProjectExample myprojectExample = new TProjectExample();
                TProjectExample.Criteria criteria = myprojectExample.createCriteria();
                criteria.andFnoEqualTo(FNo);
                List<TProject> projectList = tProjectService.findByExample(myprojectExample);
                TUser user = tUserService.findById(projectList.get(0).getFuserid());
                JSONObject myproject_object = new JSONObject();
                myproject_object.put("FuserName", user.getfName() == null ? "" : user.getfName()); //项目发布人
                myproject_object.put("FName", projectList.get(0).getFname());
                myproject_object.put("FNo", projectList.get(0).getFno());
                object.put("faillist", myproject_object);
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
     * 删除项目信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("删除项目信息")
    @ResponseBody
    @RequestMapping(value = "/delmyproject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delmyproject(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLoginmyprojectID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectID != null && !CookiesLoginmyprojectID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            TProject tProject = tProjectService.findById(Long.parseLong(id));
            if (tProject != null) {
                if (tProject.getFstate() != 0) {
                    object.put("result", "error");
                    object.put("error", "当前项目已发布，不能删除！");
                } else {
                    tProjectService.deleteById(Long.parseLong(id));

                    TProjectTeamExample tProjectTeamExample = new TProjectTeamExample();
                    TProjectTeamExample.Criteria criteria = tProjectTeamExample.createCriteria();
                    criteria.andFprojectidEqualTo(Long.parseLong(id));
                    tProjectTeamService.deleteByByExample(tProjectTeamExample);

                    //添加日志
                    ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                    projectLog.addprojectlog("删除项目【" + tProject.getFname() + "】", String.valueOf(tProject.getFkeyid()), "项目信息", "删除项目", "", uid);

                    TUser tUser = tUserService.findById(Long.parseLong(uid));
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserId(tUser.getfKeyId());
                    logAction.setfUserName(tUser.getfName());
                    logAction.setfType(3);
                    logAction.setfPath("myproject/delmyproject{" + tProject.getFkeyid() + "}");
                    logAction.setfUserType(1);

                    logAction.setfMemo("删除项目【" + tProject.getFname() + "】信息。");
                    logActionService.save(logAction);
                    // 返回值
                    object.put("result", "success");
                }
            } else {
                object.put("result", "error");
                object.put("error", "项目不存在，不能删除！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 变更项目信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */

    @ResponseBody
    @RequestMapping(value = "/statemyproject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statemyproject(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLoginmyprojectID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectID != null && !CookiesLoginmyprojectID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TProject myproject = new TProject();
            myproject.setFkeyid(Long.parseLong(id));
            myproject.setFuid(Long.parseLong(uid));
            myproject.setFudate(new Date());
            myproject.setFstate(Integer.valueOf(state));
            tProjectService.update(myproject);
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
     * 发布项目前进行判断
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/fabuemyprojectyz", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String fabuemyprojectyz(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLoginmyprojectID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectID != null && !CookiesLoginmyprojectID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            //判断当前项目是否挂了产品、判断是否挂了工作流 ，如果挂了工作流未启动，则询问是否跳转到该页面，，选否跳出,如果已启动则进行发布
            TProject tProject = tProjectService.findById(Long.parseLong(id));
            if (tProject.getFstate() == 0) {
//                TProjectFlowsExample tProjectFlowsExample = new TProjectFlowsExample();
//                tProjectFlowsExample.or().andFprojectidEqualTo(tProject.getFkeyid());
//                List<TProjectFlows> flowsList = tProjectFlowsService.findByExample(tProjectFlowsExample);
                int ismoju = -1;
                int bf = -1;
//                if (tProject.getFmaterialid() != null) {
                    //查询是否挂了零件 零件是否挂了模具
//                    TBomMExample tBomMExample = new TBomMExample();
//                    tBomMExample.createCriteria().andFmaterialidEqualTo(tProject.getFmaterialid()).andFshowEqualTo(1);
//                    List<TBomM> mList = tBomMService.findByExample(tBomMExample);
//                    if (mList.size() > 0) {
//                        TBomSExample tBomSExample = new TBomSExample();
//                        tBomSExample.createCriteria().andFbommidEqualTo(mList.get(0).getFkeyid()).andFfidEqualTo(-1l);
//                        List<TBomS> sList = tBomSService.findByExample(tBomSExample);
//                        if (sList.size() == 0) {
//                            ismoju = 1;
//                            //没挂零件
//                            object.put("mesg", 6);
//                            object.put("result", "success");
//
//                        } else {
//                            int znum = sList.size();
//                            int iii = 0;
//
//                            if (znum == 1) {
//                                tBomSExample = new TBomSExample();
//                                tBomSExample.createCriteria().andFbommidEqualTo(mList.get(0).getFkeyid()).andFfidEqualTo(sList.get(0).getFkeyid());
//                                List<TBomS> sList1 = tBomSService.findByExample(tBomSExample);
//                                if (sList1.size() == 0) {
//                                    //没挂模具
//                                    ismoju = 2;
//                                    object.put("mesg", 5);
//                                    object.put("result", "success");
//                                } else {
//                                    ismoju = 3;
//                                }
//                            } else {
//                                for (TBomS tBomS : sList) {
//                                    tBomSExample = new TBomSExample();
//                                    tBomSExample.createCriteria().andFbommidEqualTo(mList.get(0).getFkeyid()).andFfidEqualTo(tBomS.getFkeyid());
//                                    List<TBomS> sList1 = tBomSService.findByExample(tBomSExample);
//                                    if (sList1.size() == 0) {
//                                        //没挂模具
//                                        ismoju = 2;
//                                        object.put("mesg", 5);
//                                        object.put("result", "success");
//                                        iii = iii + 1;
//                                    }
//                                }
//
//                                if (iii == 0) {
//                                    ismoju = 3;
//                                    bf = 0;
//                                } else if (iii == znum) {
//                                    //没挂模具
//                                    ismoju = 2;
//                                    bf = 0;
//                                    object.put("mesg", 5);
//                                    object.put("result", "success");
//                                } else if (iii < znum) {
//                                    //部分没挂模具
//                                    ismoju = 3;
//                                    bf = 1;
//                                    object.put("mesg", 7);
//                                    object.put("result", "success");
//                                }
//                            }
//                        }
//
//
//                        if (ismoju == 3) {
//                            if (flowsList.size() <= 0) {
//                                //未挂工作流
//                                // 返回值
//                                if (bf == 1) {
//                                    object.put("mesg", 8);
//                                    object.put("result", "success");
//                                } else {
//                                    object.put("mesg", 2);
//                                    object.put("result", "success");
//                                }
//                            } else if (flowsList.size() > 0 && flowsList.get(0).getFstate() == 0) {
//                                //挂 工作流 但未启动
//                                // 返回值
//                                object.put("mesg", 3);
//                                object.put("result", "success");
//                            } else if ((flowsList.size() > 0 && flowsList.get(0).getFstate() >= 0)) {
//                                //所有条件通过
//                                // 返回值
//                                object.put("mesg", 4);
//                                object.put("result", "success");
//                            } else {
//                                object.put("result", "error");
//                                object.put("error", "未知错误，无法发布！");
//                            }
//                        }
////                        System.out.println(ismoju);
//
//                    }
//                } else {
//                    object.put("mesg", 1);
//                    object.put("result", "success");
//                }
//                if (flowsList.size() <= 0) {
//                    //未挂工作流
//                    // 返回值
//                    if (bf == 1) {
//                        object.put("mesg", 8);
//                        object.put("result", "success");
//                    } else {
//                        object.put("mesg", 2);
//                        object.put("result", "success");
//                    }
//                } else if (flowsList.size() > 0 && flowsList.get(0).getFstate() == 0) {
//                    //挂 工作流 但未启动
//                    // 返回值
//                    object.put("mesg", 3);
//                    object.put("result", "success");
//                } else if ((flowsList.size() > 0 && flowsList.get(0).getFstate() >= 0)) {
                    //所有条件通过
                    // 返回值
                    object.put("mesg", 4);
                    object.put("result", "success");
//                } else {
//                    object.put("result", "error");
//                    object.put("error", "未知错误，无法发布！");
//                }
            } else {
                object.put("result", "error");
                object.put("error", "该项目已发布，不能重复操作，请刷新页面！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 发布项目
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("发布项目")
    @ResponseBody
    @RequestMapping(value = "/fabuemyproject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String fabuemyproject(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        int state = jsonParam.getInteger("state");
        try {
            String CookiesLoginmyprojectID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectID != null && !CookiesLoginmyprojectID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TProject tProject = tProjectService.findById(Long.parseLong(id));
//            if (tProject.getFstate() == 0) {
//                TProjectTeamExample tProjectTeamExample = new TProjectTeamExample();
//                tProjectTeamExample.or().andFuseridEqualTo(Long.parseLong(uid)).andFroleEqualTo(1).andFprojectidEqualTo(Long.parseLong(id));
//                List<TProjectTeam> teamList = tProjectTeamService.findByExample(tProjectTeamExample);
//                if (teamList != null && teamList.size() > 0) {


//                    TProjectFlowsExample tProjectFlowsExample = new TProjectFlowsExample();
//                    tProjectFlowsExample.or().andFprojectidEqualTo(tProject.getFkeyid());
//                    List<TProjectFlows> flowsList = tProjectFlowsService.findByExample(tProjectFlowsExample);
//
//                    if (flowsList.size() <= 0) {
//                        object.put("result", "error");
//                        object.put("error", "该项目工作流未启动，不能发布项目！");
//                    } else{
//
//                    }
//                    if (tProject.getFmaterialid() == null || tProject.getFmaterialid() <= 0L) {
//                        object.put("result", "error");
//                        object.put("error", "该项目未选择产品信息，不能发布项目！");
//                    } else if (tProject.getFmaterialid() != null) {//flowsList.size() > 0 &&
            //检查工作流是否启动


            TProject myproject = new TProject();
            myproject.setFkeyid(Long.parseLong(id));
            myproject.setFuid(Long.parseLong(uid));
            myproject.setFudate(new Date());
            myproject.setFstate(state);
            myproject.setFstartdate(new Date());
            myproject.setFdate(new Date());
            myproject.setFuserid(Long.parseLong(uid));
            tProjectService.update(myproject);
            //添加日志
            ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
            projectLog.addprojectlog("发布项目【" + tProject.getFname() + "】", String.valueOf(tProject.getFkeyid()), "项目信息", "发布项目", "", uid);


            TUser tUser2 = tUserService.findById(Long.parseLong(uid));
            TLogAction logAction = new TLogAction();
            logAction.setfUserId(tUser2.getfKeyId());
            logAction.setfUserName(tUser2.getfName());
            logAction.setfType(3);
            logAction.setfPath("myproject/fabuemyproject{" + tProject.getFkeyid() + "}");
            logAction.setfUserType(1);

            logAction.setfMemo("发布项目【" + tProject.getFname() + "】信息。");
            logActionService.save(logAction);

            //查询当前项目的团队成员表 发送消息
            TProjectTeamExample tProjectTeamExample1 = new TProjectTeamExample();
            tProjectTeamExample1.createCriteria().andFprojectidEqualTo(tProject.getFkeyid());
            List<TProjectTeam> teamList1 = tProjectTeamService.findByExample(tProjectTeamExample1);
            JavaMailTools mailUtil = JavaMailTools.getMailUtil();

            for (TProjectTeam tProjectTeam : teamList1) {
                TUser tUser = tUserService.findById(tProjectTeam.getFuserid());
                // 添加消息
                TMessage message = new TMessage();
                message.setfCId(tProjectTeam.getFuserid());
                message.setfType(5);
                message.setfTitle("【" + tProject.getFname() + "】新项目发布通知");
                message.setfNote("您负责的项目【" + tProject.getFname() + "】已发布，请注意查看！");
                message.setfFromUserId(null);
                message.setfToUserId(tUser.getfKeyId());
                message.setfIsRead(0);
                message.setfState(1);
                messageService_static.save(message);
                // 通知客户端
                messageServer_static.sendInfo(tUser.getfLogin(), "{\"type\":5, \"message\":\"您负责的项目【" + tProject.getFname() + "】已发布，请注意查看！\"}");
                //发送邮件
                //判断当前用户是否有邮箱
                if (tUser.getfEmail() != null && !tUser.getfEmail().equals("")) {
                    try {
                        mailUtil.sendEmail(tUser.getfEmail(), "【" + tProject.getFname() + "】新项目发布通知", "您负责的项目【" + tProject.getFname() + "】已发布，请登录系统查看！");
                    } catch (Exception e) {
                        System.out.println("项目发布：");
                        System.out.println("给用户：" + tUser.getfName() + "发送邮件失败,用户邮箱为：【" + tUser.getfEmail() + "】");
                        System.out.println("错误信息：" + e.getMessage());
                    }
                }
                //

            }
            // 返回值
            object.put("result", "success");
//                    }
//                } else {
//                    object.put("result", "error");
//                    object.put("error", "您不是该项目的负责人，不允许发布项目！");
//                }
//            } else {
//                object.put("result", "error");
//                object.put("error", "该项目已发布，不能重复操作，请刷新页面！");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 完成项目
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("完成项目")
    @ResponseBody
    @RequestMapping(value = "/wanchengemyproject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String wanchengemyproject(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        int state = jsonParam.getInteger("state");
        try {
            String CookiesLoginmyprojectID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectID != null && !CookiesLoginmyprojectID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TProject tProject = tProjectService.findById(Long.parseLong(id));
            if (tProject.getFstate() == 1) {
                TProjectTeamExample tProjectTeamExample = new TProjectTeamExample();
                tProjectTeamExample.or().andFuseridEqualTo(Long.parseLong(uid)).andFroleEqualTo(1).andFprojectidEqualTo(Long.parseLong(id));
                List<TProjectTeam> teamList = tProjectTeamService.findByExample(tProjectTeamExample);
                int plansum = -1;//计划总数
                int plannum = -1;//计划完成数
                int flowsum = -1;//
                int flownum = -1;//工作流完成数
                int ttasksum = -1;
                int ttasknum = -1;
                boolean istrue = false;
                if (teamList != null && teamList.size() > 0) {
                    //判断所有计划是否完成
                    //查询项目下所有计划数
//                    TProjectPlanExample tProjectPlanExample = new TProjectPlanExample();
//                    TProjectPlanExample.Criteria planExampleCriteria = tProjectPlanExample.createCriteria();
//                    planExampleCriteria.andFprojectidEqualTo(Long.parseLong(id));
//                    List<TProjectPlan> planList = tProjectPlanService.findByExample(tProjectPlanExample);
//                    plansum = planList.size();
//                    //在查询已完成计划数
//                    tProjectPlanExample = new TProjectPlanExample();
//                    planExampleCriteria = tProjectPlanExample.createCriteria();
//                    planExampleCriteria.andFprojectidEqualTo(Long.parseLong(id)).andFstateEqualTo(2);
//                    planList = tProjectPlanService.findByExample(tProjectPlanExample);
//                    plannum = planList.size();

                    //判断项目工作流是否完成
//                    TProjectFlowsExample tProjectFlowsExample = new TProjectFlowsExample();
//                    TProjectFlowsExample.Criteria criteria = tProjectFlowsExample.createCriteria();
//                    criteria.andFprojectidEqualTo(Long.parseLong(id)).andFstateEqualTo(2);
//                    List<TProjectFlows> flowsList = tProjectFlowsService.findByExample(tProjectFlowsExample);
//                    flownum = flowsList.size();

                    //判断项目中的任务是否都已完成
//                    TTaskExample tTaskExample = new TTaskExample();
//                    tTaskExample.or().andFprojectidEqualTo(Long.parseLong(id));
//                    List<TTask> taskList = tTaskService.findByExample(tTaskExample);
//                    ttasksum = taskList.size();

//                    tTaskExample = new TTaskExample();
//                    tTaskExample.or().andFprojectidEqualTo(Long.parseLong(id)).andFstateEqualTo(2);
//                    taskList = tTaskService.findByExample(tTaskExample);
//                    ttasknum = taskList.size();


//                    if (plansum != -1 && plannum != -1 && plansum != plannum) {
//                        object.put("result", "error");
//                        object.put("error", "该项目中还有计划信息未完成，不能结束！");
//                    }
//                    else if (flownum <= 0) {
//                        object.put("result", "error");
//                        object.put("error", "该项目中工作流未完成，不能结束！");
//                    }
//                    else if (ttasksum != -1 && ttasknum != -1 && ttasksum != ttasknum) {
//                        object.put("result", "error");
//                        object.put("error", "该项目中任务未完成，不能结束！");
//                    }
//                    else if (plansum != -1 && plannum != -1 && plansum == plannum) {
                        TProject myproject = new TProject();
                        myproject.setFkeyid(Long.parseLong(id));
                        myproject.setFuid(Long.parseLong(uid));
                        myproject.setFudate(new Date());
                        myproject.setFstate(state);
                        myproject.setFenddate(new Date());
                        tProjectService.update(myproject);
                        try {
                            //添加日志
                            ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                            projectLog.addprojectlog("完成项目【" + tProject.getFname() + "】", String.valueOf(tProject.getFkeyid()), "项目信息", "完成项目", "", uid);

                            TUser tUser = tUserService.findById(Long.parseLong(uid));
                            TLogAction logAction = new TLogAction();
                            logAction.setfUserId(tUser.getfKeyId());
                            logAction.setfUserName(tUser.getfName());
                            logAction.setfType(3);
                            logAction.setfPath("myproject/wanchengemyproject{" + tProject.getFkeyid() + "}");
                            logAction.setfUserType(1);

                            logAction.setfMemo("完成项目【" + tProject.getFname() + "】。");
                            logActionService.save(logAction);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        // 返回值
                        object.put("result", "success");


                        TProjectTeamExample tProjectTeamExample1 = new TProjectTeamExample();
                        tProjectTeamExample1.createCriteria().andFprojectidEqualTo(tProject.getFkeyid());
                        List<TProjectTeam> teamList1 = tProjectTeamService.findByExample(tProjectTeamExample1);
                        JavaMailTools mailUtil = JavaMailTools.getMailUtil();

                        for (TProjectTeam tProjectTeam : teamList1) {
                            TUser tUser = tUserService.findById(tProjectTeam.getFuserid());
                            // 添加消息
                            TMessage message = new TMessage();
                            message.setfCId(tProjectTeam.getFuserid());
                            message.setfType(5);
                            message.setfTitle("【" + tProject.getFname() + "】项目完成通知");
                            message.setfNote("您负责的项目【" + tProject.getFname() + "】已完成！");
                            message.setfFromUserId(null);
                            message.setfToUserId(tUser.getfKeyId());
                            message.setfIsRead(0);
                            message.setfState(1);
                            messageService_static.save(message);
                            // 通知客户端
                            messageServer_static.sendInfo(tUser.getfLogin(), "{\"type\":5, \"message\":\"您负责的项目【" + tProject.getFname() + "】已完成！\"}");
                            //发送邮件
                            //判断当前用户是否有邮箱
                            if (tUser.getfEmail() != null && !tUser.getfEmail().equals("")) {
                                try {
                                    mailUtil.sendEmail(tUser.getfEmail(), "【" + tProject.getFname() + "】项目完成通知", "您负责的项目【" + tProject.getFname() + "】已完成，请登录系统查看！");
                                } catch (Exception e) {
                                    System.out.println("项目完成：");
                                    System.out.println("给用户：" + tUser.getfName() + "发送邮件失败,用户邮箱为：【" + tUser.getfEmail() + "】");
                                    System.out.println("错误信息：" + e.getMessage());
                                }
                            }
                            //

                        }

//                    } else {
//                        object.put("result", "error");
//                        object.put("error", "未知错误，请刷新页面后重试！");
//                    }
                } else {
                    object.put("result", "error");
                    object.put("error", "您不是该项目的负责人，不允许操作！");
                }
            } else {
                object.put("result", "error");
                object.put("error", "该项目未发布或已完成，请刷新页面后重试！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 关闭项目
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("关闭项目")
    @ResponseBody
    @RequestMapping(value = "/guanbiemyproject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String guanbiemyproject(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        int state = jsonParam.getInteger("state");
        try {
            String CookiesLoginmyprojectID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectID != null && !CookiesLoginmyprojectID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TProject tProject = tProjectService.findById(Long.parseLong(id));
            if (tProject.getFstate() != 2) {
                TProjectTeamExample tProjectTeamExample = new TProjectTeamExample();
                tProjectTeamExample.or().andFuseridEqualTo(Long.parseLong(uid)).andFroleEqualTo(1).andFprojectidEqualTo(Long.parseLong(id));
                List<TProjectTeam> teamList = tProjectTeamService.findByExample(tProjectTeamExample);
                if (teamList != null && teamList.size() > 0) {
                    TProject myproject = new TProject();
                    myproject.setFkeyid(Long.parseLong(id));
                    myproject.setFuid(Long.parseLong(uid));
                    myproject.setFudate(new Date());
                    myproject.setFstate(state);
                    //myproject.setFenddate(new Date());
                    tProjectService.update(myproject);

                    try {
                        //添加日志
                        ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                        projectLog.addprojectlog("关闭了项目【" + tProject.getFname() + "】", String.valueOf(tProject.getFkeyid()), "项目信息", "关闭项目", "", uid);

                        TUser tUser = tUserService.findById(Long.parseLong(uid));
                        TLogAction logAction = new TLogAction();
                        logAction.setfUserId(tUser.getfKeyId());
                        logAction.setfUserName(tUser.getfName());
                        logAction.setfType(3);
                        logAction.setfPath("myproject/guanbiemyproject{" + tProject.getFkeyid() + "}");
                        logAction.setfUserType(1);

                        logAction.setfMemo("关闭了项目【" + tProject.getFname() + "】信息。");
                        logActionService.save(logAction);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    // 返回值
                    object.put("result", "success");

                    TProjectTeamExample tProjectTeamExample1 = new TProjectTeamExample();
                    tProjectTeamExample1.createCriteria().andFprojectidEqualTo(tProject.getFkeyid());
                    List<TProjectTeam> teamList1 = tProjectTeamService.findByExample(tProjectTeamExample1);
                    JavaMailTools mailUtil = JavaMailTools.getMailUtil();

                    for (TProjectTeam tProjectTeam : teamList1) {
                        TUser tUser = tUserService.findById(tProjectTeam.getFuserid());
                        // 添加消息
                        TMessage message = new TMessage();
                        message.setfCId(tProjectTeam.getFuserid());
                        message.setfType(5);
                        message.setfTitle("【" + tProject.getFname() + "】项目关闭通知");
                        message.setfNote("您负责的项目【" + tProject.getFname() + "】已关闭！");
                        message.setfFromUserId(null);
                        message.setfToUserId(tUser.getfKeyId());
                        message.setfIsRead(0);
                        message.setfState(1);
                        messageService_static.save(message);
                        // 通知客户端
                        messageServer_static.sendInfo(tUser.getfLogin(), "{\"type\":5, \"message\":\"您负责的项目【" + tProject.getFname() + "】已关闭！\"}");
                        //发送邮件
                        //判断当前用户是否有邮箱
                        if (tUser.getfEmail() != null && !tUser.getfEmail().equals("")) {
                            try {
                                mailUtil.sendEmail(tUser.getfEmail(), "【" + tProject.getFname() + "】项目关闭通知", "您负责的项目【" + tProject.getFname() + "】已关闭，请登录系统查看！");
                            } catch (Exception e) {
                                System.out.println("项目关闭：");
                                System.out.println("给用户：" + tUser.getfName() + "发送邮件失败,用户邮箱为：【" + tUser.getfEmail() + "】");
                                System.out.println("错误信息：" + e.getMessage());
                            }
                        }
                        //

                    }


                } else {
                    object.put("result", "error");
                    object.put("error", "您不是该项目的负责人，不允许操作！");
                }
            } else {
                object.put("result", "error");
                object.put("error", "该项已完成，不能关闭！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

//    /**
//     * 添加项目对应产品信息
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
////    @LogOperation("添加项目产品信息")
//    @ResponseBody
//    @RequestMapping(value = "/addprojectmaterial", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String addprojectmaterial(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String FProjectID = jsonParam.getString("FProjectID");
//        String FMaterialID = jsonParam.getString("FMaterialID");
//        try {
//            String CookiesLoginmyprojectID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//            String uid = ""; // 当前登录用户ID
//            if (CookiesLoginmyprojectID != null && !CookiesLoginmyprojectID.equals("")) {
//                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
//            }
//            FProjectID = FProjectID == null ? "0" : (FProjectID.equals("0") ? "0" : ParamTools.getdeParam(FProjectID));
//            FMaterialID = FMaterialID == null ? "0" : (FMaterialID.equals("0") ? "0" : ParamTools.getdeParam(FMaterialID));
//            //先判断项目 是否已经发布
//            TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
//            if (tProject == null) {
//                object.put("result", "error");
//                object.put("error", "该项目不存在，请刷新后再试！");
//            } else {
//                if (tProject.getFstate() == 0) {
//                    TProject myproject = new TProject();
//                    myproject.setFkeyid(Long.parseLong(FProjectID));
//                    myproject.setFuid(Long.parseLong(uid));
//                    myproject.setFudate(new Date());
//                    myproject.setFmaterialid(Long.parseLong(FMaterialID));
//                    tProjectService.update(myproject);
//
//                    TMaterial tMaterial = tMaterialService.findById(Long.parseLong(FMaterialID));
//
//                    //添加日志
//                    ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
//                    String fname = tMaterial == null ? "" : tMaterial.getFname();
//                    String fnote = "为项目【" + tProject.getFname() + "】,添加了产品【" + fname + "】";
//                    projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "项目信息", "添加产品", "", uid);
//
//                    TUser tUser = tUserService.findById(Long.parseLong(uid));
//                    TLogAction logAction = new TLogAction();
//                    logAction.setfUserId(tUser.getfKeyId());
//                    logAction.setfUserName(tUser.getfName());
//                    logAction.setfType(3);
//                    logAction.setfPath("myproject/addprojectmaterial{" + tProject.getFkeyid() + "}");
//                    logAction.setfUserType(1);
//
//                    logAction.setfMemo("为项目【" + tProject.getFname() + "】,添加了产品【" + fname + "】信息。");
//                    logActionService.save(logAction);
//
//                    // 返回值
//                    object.put("result", "success");
//                } else {
//                    object.put("result", "error");
//                    object.put("error", "该项目已发布，不能修改！");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }


    /**
     * 验证项目是否存在
     */
    private int repeaTProject(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TProjectExample myprojectExample = new TProjectExample();
            TProjectExample.Criteria criteria = myprojectExample.createCriteria();
            if (ftype.equals("2")) { // 修改
                if (id != null) {
//                    criteria.andFkeyidEqualTo((id));
                    criteria.andFkeyidNotEqualTo(id);
                }
                if (name != null && !name.equals("")) {
                    criteria.andFnoEqualTo(name);
                }
            } else { // 新增
                if (name != null && !name.equals("")) {
                    criteria.andFnoEqualTo(name);
                }
            }
            List<TProject> rojectTypeList = tProjectService.findByExample(myprojectExample);
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


    //根据ID查询项目名称
    public String getName(Long id) {
        TProject byId = tProjectService.findById(id);
        if (byId != null) {
            return byId.getFname();
        } else {
            return "";
        }

    }

    /**
     * 生成流水号的方法
     *
     * @param faultNum
     * @return 格式为P202112001
     */
    public static String formatNumberE(int faultNum, String date) {
        // 对当前日期进行格式化
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//        String date = sdf.format(new Date());
        DecimalFormat dft = new DecimalFormat(FORMAT_CODE);
        String code;
        code = dft.format(faultNum);
        return FAULT_PREFIX + date + code;
    }

    public static String formatNumberE(int faultNum, String date, String PREFIX) {
        // 对当前日期进行格式化
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//        String date = sdf.format(new Date());
        DecimalFormat dft = new DecimalFormat(FORMAT_CODE);
        String code;
        code = dft.format(faultNum);
        return PREFIX + date + code;
    }

    public static int getDay() {
        SimpleDateFormat sdf22 = new SimpleDateFormat("dd");
        String s2 = sdf22.format(new Date());
        return Integer.parseInt(s2);
    }


//    /**
//     * 获取Bom信息
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/showBomTableTreeProject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String showBomTableTreeProject(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String FProjectID = jsonParam.getString("FProjectID");// 项目ID
//
//        JSONObject objall = new JSONObject();
//        JSONArray obj1Listarr = new JSONArray();
//        try {
//            if (FProjectID != null && !FProjectID.equals("") && !FProjectID.equals("0")) {
//                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
//                String CookiesLoginmaterialID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//                String uid = ""; // 当前登录用户ID
//                if (CookiesLoginmaterialID != null && !CookiesLoginmaterialID.equals("")) {
//                    uid = ParamTools.getdeParam(CookiesLoginmaterialID);
//                }
//                TMaterialUnit materialUnit = null;
//                TMaterialFrom materialFrom = null;
//
//                //根据项目ID查询 产品ID
//                TProject tProject = tProjectService.findById(Long.parseLong(ParamTools.getdeParam(FProjectID)));
//                long fmidd = 0L;
//                if (tProject != null) {
//                    object.put("pfstate", tProject.getFstate());
//                    fmidd = tProject.getFmaterialid() == null ? 0L : tProject.getFmaterialid();
//                }
//
//
//                objall.put("draw", 0);
//                objall.put("recordsTotal", 0);
//                objall.put("recordsFiltered", 0);
//                if (fmidd != 0L) {
//                    //根据bommid 查询 物料信息
//                    TBomMExample tBomMExample = null;
//
//
//                    tBomMExample = new TBomMExample();
//                    TBomMExample.Criteria criteria = tBomMExample.createCriteria();
//                    criteria.andFmaterialidEqualTo(fmidd);
//                    criteria.andFshowEqualTo(1);
////                    criteria.andFstateEqualTo(1);
//                    List<TBomM> tBomM = tBomMService.findByExample(tBomMExample);
//                    TMaterial tMaterial = null;
//                    tMaterial = tMaterialService.findById(fmidd);
//                    long bomid = 0l;
//                    if (tBomM.size() > 0) {
//                        bomid = tBomM.get(0).getFkeyid();
//                        if (tBomM.get(0).getFstate() == 0) {
//                            object.put("FBomState", "编辑");
//                        } else if (tBomM.get(0).getFstate() == 1) {
//                            object.put("FBomState", "可用版本");
//                        } else if (tBomM.get(0).getFstate() == 2) {
//                            object.put("FBomState", "历史版本");
//                        }
//                        object.put("FShow", tBomM.get(0).getFshow());
//                        object.put("FState", tBomM.get(0).getFstate());
//                        object.put("FEditionNo", tBomM.get(0).getFeditionno());
//                        object.put("FEdition", tBomM.get(0).getFedition());
//                    } else {
//                        bomid = idWorker.nextId();
//                        //没有添加主表信息
//                        TBomM tBomMb = new TBomM();
//                        tBomMb.setFkeyid(bomid);
//                        tBomMb.setFcid(Long.parseLong(uid));
//                        tBomMb.setFstate(0);
//                        tBomMb.setFmaterialid(fmidd);
//                        tBomMb.setFmaterialno(tMaterial.getFno());
//                        tBomMb.setFeditionno(1);
//                        tBomMb.setFshow(1);
//                        tBomMb.setFedition("V01");
//                        tBomMb.setFnote(tMaterial.getFnote());
//                        tBomMb.setFsyscode(tMaterial.getFsyscode());
//                        tBomMService.save(tBomMb);
//                        object.put("FBomState", "编辑");
//                        object.put("FEditionNo", 1);
//                        object.put("FEdition", "V01");
//                        object.put("FShow", 1);
//                        object.put("FState", 0);
//                    }
//                    object.put("MaterialID", ParamTools.getEnParam(String.valueOf(fmidd)));
//                    JSONObject obj1 = new JSONObject();//根节点内容
//                    if (tMaterial != null) {
//                        object.put("genid", ParamTools.getEnParam(String.valueOf(bomid)));
//                        obj1.put("key", ParamTools.getEnParam(String.valueOf(bomid)));
//                        obj1.put("FType", tMaterial.getFtype() == 1 ? "标准件" : "非标");
//                        materialUnit = tMaterialUnitService.findById(tMaterial.getFunitid());
//                        obj1.put("FUnitID", materialUnit == null ? "" : materialUnit.getFname());
//                        obj1.put("FMName", tMaterial.getFname());
//                        obj1.put("FMNo", tMaterial.getFno());
//                        obj1.put("FMID", ParamTools.getEnParam(tMaterial.getFkeyid().toString()));
//                        obj1.put("FModel", tMaterial.getFmodel() == null ? "" : tMaterial.getFmodel());
//                        obj1.put("FSpecs", tMaterial.getFspecs() == null ? "" : tMaterial.getFspecs());
//                        obj1.put("FTexture", tMaterial.getFtexture() == null ? "" : tMaterial.getFtexture());
//                        obj1.put("FSize", tMaterial.getFsize() == null ? "" : tMaterial.getFsize());
//                        obj1.put("FOutside", tMaterial.getFoutside() == null ? "" : tMaterial.getFoutside());
//                        switch (tMaterial.getFmtype()) {
//                            case 1:
//                                obj1.put("FMType", "产品");
//                                obj1.put("FName", "<i class=\"fa fa-cogs fa-lg \" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                                break;
//                            case 2:
//                                obj1.put("FMType", "制件");
//                                obj1.put("FName", "<i class=\"fa fa-cog fa-lg\" style=\"color: #00A7AA\" ></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                                break;
//                            case 3:
//                                obj1.put("FMType", "零件");
//                                obj1.put("FName", "<i class=\"fa fa-cog fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                                break;
//                            case 4:
//                                obj1.put("FMType", "模具");
//                                obj1.put("FName", "<i class=\"fa fa-share-alt-square fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//
//                                break;
//                            case 5:
//                                obj1.put("FMType", "标准件");
//                                obj1.put("FName", "<i class=\"fa fa-cog fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                                break;
//                            case 9:
//                                obj1.put("FMType", "其他");
//                                obj1.put("FName", "<i class=\"fa fa-gg fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                                break;
//                            default:
//                                obj1.put("FMType", "");
//                                obj1.put("FName", tMaterial.getFno() + "/" + tMaterial.getFname());
//                                break;
//                        }
//                        materialFrom = tMaterialFromService.findById(tMaterial.getFfrom());
//                        obj1.put("FFrom", materialFrom == null ? "" : materialFrom.getFname());
//                        obj1.put("num", "");
//                        object.put("FBomName", tMaterial.getFno() + "/" + tMaterial.getFname());
//
//                        obj1.put("edit", 0);
//                    } else {
//                        obj1.put("FType", "");
//                        obj1.put("FUnitID", "");
//                        obj1.put("FMType", "");
//                        obj1.put("FFrom", "");
//                        obj1.put("num", "");
//                        obj1.put("FName", "");
//                    }
//                    //根据bommid 查询子表
//                    TBomSExample tBomSExample = new TBomSExample();
//                    tBomSExample.createCriteria().andFbommidEqualTo(bomid).andFfidEqualTo(-1L);
//                    tBomSExample.setOrderByClause("fcdate ");
//                    List<TBomS> bomSList = tBomSService.findByExample(tBomSExample);
//                    JSONArray tbomsListarr = new JSONArray();
//                    if (bomSList.size() > 0) {
//                        for (TBomS tbs : bomSList) {
//                            tMaterial = tMaterialService.findById(tbs.getFmaterialid());
//                            JSONObject obj2 = new JSONObject();
//                            obj2.put("key", ParamTools.getEnParam(tbs.getFkeyid().toString()));
//                            obj2.put("FType", tMaterial.getFtype() == 1 ? "标准件" : "非标");
//                            materialUnit = tMaterialUnitService.findById(tMaterial.getFunitid());
//                            obj2.put("FUnitID", materialUnit == null ? "" : materialUnit.getFname());
//                            obj2.put("FMName", tMaterial.getFname());
//                            obj2.put("FMNo", tMaterial.getFno());
//                            obj2.put("FMID", ParamTools.getEnParam(tMaterial.getFkeyid().toString()));
//
//                            obj2.put("FModel", tMaterial.getFmodel() == null ? "" : tMaterial.getFmodel());
//                            obj2.put("FSpecs", tMaterial.getFspecs() == null ? "" : tMaterial.getFspecs());
//                            obj2.put("FTexture", tMaterial.getFtexture() == null ? "" : tMaterial.getFtexture());
//                            obj2.put("FSize", tMaterial.getFsize() == null ? "" : tMaterial.getFsize());
//                            obj2.put("FOutside", tMaterial.getFoutside() == null ? "" : tMaterial.getFoutside());
//
//                            switch (tMaterial.getFmtype()) {
//                                case 1:
//                                    obj2.put("FMType", "产品");
//                                    obj2.put("FName", "<i class=\"fa fa-cogs fa-lg \" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                                    break;
//                                case 2:
//                                    obj2.put("FMType", "制件");
//                                    obj2.put("FName", "<i class=\"fa fa-cog fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                                    break;
//                                case 3:
//                                    obj2.put("FMType", "零件");
//                                    obj2.put("FName", "<i class=\"fa fa-cog fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                                    break;
//                                case 4:
//                                    obj2.put("FMType", "模具");
//                                    obj2.put("FName", "<i class=\"fa fa-share-alt-square fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                                    break;
//                                case 5:
//                                    obj2.put("FMType", "标准件");
//                                    obj2.put("FName", "<i class=\"fa fa-cog fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                                    break;
//                                case 9:
//                                    obj2.put("FMType", "其他");
//                                    obj2.put("FName", "<i class=\"fa fa-gg fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                                    break;
//                                default:
//                                    obj2.put("FMType", "");
//                                    obj2.put("FName", tMaterial.getFno() + "/" + tMaterial.getFname());
//                                    break;
//                            }
//                            materialFrom = tMaterialFromService.findById(tMaterial.getFfrom());
//                            obj2.put("FFrom", materialFrom == null ? "" : materialFrom.getFname());
//                            obj2.put("num", tbs.getFnumU() + "/" + tbs.getFnumB());
//                            obj2.put("edit", 1);
//                            //递归下级
//                            this.getTBomsxjProject(obj2, tbs.getFkeyid(), bomid);
//                            tbomsListarr.add(obj2);
//                        }
//                        obj1.put("children", tbomsListarr);
//                    } else {
//                        obj1.put("children", null);
//                    }
//                    obj1Listarr.add(obj1);
//                    objall.put("data", obj1Listarr);
//                } else {
//                    objall.put("data", "");
//                }
//
//            } else {
//                objall.put("draw", 0);
//                objall.put("recordsTotal", 0);
//                objall.put("recordsFiltered", 0);
//                objall.put("data", "");
//                object.put("pfstate", -1);
//            }
////            System.out.println("Bom结构：" + objall.toString());
//            object.put("datalist2", objall);
//            object.put("result", "success");
////            System.out.println(object.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//
//        return object.toString();
//    }
//
//    public void getTBomsxjProject(JSONObject obj2, Long bomsid, Long bomid) {
//        JSONArray tbomsListarr = new JSONArray();
//        TMaterialUnit materialUnit = null;
//        TMaterialFrom materialFrom = null;
//        TMaterial tMaterial = null;
//        TBomSExample tBomSExample = new TBomSExample();
//        tBomSExample.createCriteria().andFbommidEqualTo(bomid).andFfidEqualTo(bomsid);
//        tBomSExample.setOrderByClause("fcdate ");
//        try {
//            List<TBomS> bomSList = tBomSService.findByExample(tBomSExample);
//            if (bomSList.size() > 0) {
//                for (TBomS tbs : bomSList) {
//                    tMaterial = tMaterialService.findById(tbs.getFmaterialid());
//                    JSONObject obj3 = new JSONObject();
//                    obj3.put("key", ParamTools.getEnParam(tbs.getFkeyid().toString()));
//                    obj3.put("FType", tMaterial.getFtype() == 1 ? "标准件" : "非标");
//                    materialUnit = tMaterialUnitService.findById(tMaterial.getFunitid());
//                    obj3.put("FUnitID", materialUnit == null ? "" : materialUnit.getFname());
//                    obj3.put("FMName", tMaterial.getFname());
//                    obj3.put("FMNo", tMaterial.getFno());
//                    obj3.put("FMID", ParamTools.getEnParam(tMaterial.getFkeyid().toString()));
//                    obj3.put("FModel", tMaterial.getFmodel() == null ? "" : tMaterial.getFmodel());
//                    obj3.put("FSpecs", tMaterial.getFspecs() == null ? "" : tMaterial.getFspecs());
//                    obj3.put("FTexture", tMaterial.getFtexture() == null ? "" : tMaterial.getFtexture());
//                    obj3.put("FSize", tMaterial.getFsize() == null ? "" : tMaterial.getFsize());
//                    obj3.put("FOutside", tMaterial.getFoutside() == null ? "" : tMaterial.getFoutside());
//
//                    switch (tMaterial.getFmtype()) {
//                        case 1:
//                            obj3.put("FMType", "产品");
//                            obj3.put("FName", "<i class=\"fa fa-cogs fa-lg \" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                            break;
//                        case 2:
//                            obj3.put("FMType", "制件");
//                            obj3.put("FName", "<i class=\"fa fa-cog fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                            break;
//                        case 3:
//                            obj3.put("FMType", "零件");
//                            obj3.put("FName", "<i class=\"fa fa-cog fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                            break;
//                        case 4:
//                            obj3.put("FMType", "模具");
//                            obj3.put("FName", "<i class=\"fa fa-share-alt-square fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                            break;
//                        case 5:
//                            obj3.put("FMType", "标准件");
//                            obj3.put("FName", "<i class=\"fa fa-cog fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                            break;
//                        case 9:
//                            obj3.put("FMType", "其他");
//                            obj3.put("FName", "<i class=\"fa fa-gg fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                            break;
//                        default:
//                            obj3.put("FMType", "");
//                            obj3.put("FName", tMaterial.getFno() + "/" + tMaterial.getFname());
//                            break;
//                    }
//                    materialFrom = tMaterialFromService.findById(tMaterial.getFfrom());
//                    obj3.put("FFrom", materialFrom == null ? "" : materialFrom.getFname());
//                    obj3.put("num", tbs.getFnumU() + "/" + tbs.getFnumB());
//                    obj3.put("edit", 1);
//                    //递归下级
//                    this.getTBomsxjProject(obj3, tbs.getFkeyid(), bomid);
//                    tbomsListarr.add(obj3);
//                }
//                obj2.put("children", tbomsListarr);
//            } else {
//                obj2.put("children", null);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }


//    /**
//     * 获取Bom信息-不显示根节点
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/showBomTableTreeProjectYC", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String showBomTableTreeProjectYC(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String FProjectID = jsonParam.getString("FProjectID");// 项目ID
//
//        JSONObject objall = new JSONObject();
//        JSONArray obj1Listarr = new JSONArray();
//        try {
//            if (FProjectID != null && !FProjectID.equals("") && !FProjectID.equals("0")) {
//                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
//                String CookiesLoginmaterialID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//                String uid = ""; // 当前登录用户ID
//                if (CookiesLoginmaterialID != null && !CookiesLoginmaterialID.equals("")) {
//                    uid = ParamTools.getdeParam(CookiesLoginmaterialID);
//                }
//                TMaterialUnit materialUnit = null;
//                TMaterialFrom materialFrom = null;
//
//                //根据项目ID查询 产品ID
//                TProject tProject = tProjectService.findById(Long.parseLong(ParamTools.getdeParam(FProjectID)));
//                long fmidd = 0L;
//                if (tProject != null) {
//                    object.put("pfstate", tProject.getFstate());
//                    fmidd = tProject.getFmaterialid() == null ? 0L : tProject.getFmaterialid();
//                }
//
//
//                objall.put("draw", 0);
//                objall.put("recordsTotal", 0);
//                objall.put("recordsFiltered", 0);
//                if (fmidd != 0L) {
//                    //根据bommid 查询 物料信息
//                    TBomMExample tBomMExample = null;
//
//
//                    tBomMExample = new TBomMExample();
//                    TBomMExample.Criteria criteria = tBomMExample.createCriteria();
//                    criteria.andFmaterialidEqualTo(fmidd);
//                    criteria.andFshowEqualTo(1);
////                    criteria.andFstateEqualTo(1);
//                    List<TBomM> tBomM = tBomMService.findByExample(tBomMExample);
//                    TMaterial tMaterial = null;
//                    tMaterial = tMaterialService.findById(fmidd);
//                    long bomid = 0l;
//                    if (tBomM.size() > 0) {
//                        bomid = tBomM.get(0).getFkeyid();
////                        if (tBomM.get(0).getFstate() == 0) {
////                            object.put("FBomState", "编辑");
////                        } else if (tBomM.get(0).getFstate() == 1) {
////                            object.put("FBomState", "可用版本");
////                        } else if (tBomM.get(0).getFstate() == 2) {
////                            object.put("FBomState", "历史版本");
////                        }
////                        object.put("FShow", tBomM.get(0).getFshow());
////                        object.put("FState", tBomM.get(0).getFstate());
////                        object.put("FEditionNo", tBomM.get(0).getFeditionno());
////                        object.put("FEdition", tBomM.get(0).getFedition());
//                    }
////                    else {
////                        bomid = idWorker.nextId();
////                        //没有添加主表信息
////                        TBomM tBomMb = new TBomM();
////                        tBomMb.setFkeyid(bomid);
////                        tBomMb.setFcid(Long.parseLong(uid));
////                        tBomMb.setFstate(0);
////                        tBomMb.setFmaterialid(fmidd);
////                        tBomMb.setFmaterialno(tMaterial.getFno());
////                        tBomMb.setFeditionno(1);
////                        tBomMb.setFshow(1);
////                        tBomMb.setFedition("V01");
////                        tBomMb.setFnote(tMaterial.getFnote());
////                        tBomMb.setFsyscode(tMaterial.getFsyscode());
////                        tBomMService.save(tBomMb);
////                        object.put("FBomState", "编辑");
////                        object.put("FEditionNo", 1);
////                        object.put("FEdition", "V01");
////                        object.put("FShow", 1);
////                        object.put("FState", 0);
////                    }
////                    object.put("MaterialID", ParamTools.getEnParam(String.valueOf(fmidd)));
////                    JSONObject obj1 = new JSONObject();//根节点内容
////                    if (tMaterial != null) {
////                        object.put("genid", ParamTools.getEnParam(String.valueOf(bomid)));
////                        obj1.put("key", ParamTools.getEnParam(String.valueOf(bomid)));
////                        obj1.put("FType", tMaterial.getFtype() == 1 ? "标准件" : "非标");
////                        materialUnit = tMaterialUnitService.findById(tMaterial.getFunitid());
////                        obj1.put("FUnitID", materialUnit == null ? "" : materialUnit.getFname());
////                        switch (tMaterial.getFmtype()) {
////                            case 1:
////                                obj1.put("FMType", "产品");
////                                obj1.put("FName", "<i class=\"fa fa-cogs fa-lg \" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
////                                break;
////                            case 2:
////                                obj1.put("FMType", "制件");
////                                obj1.put("FName", "<i class=\"fa fa-cog fa-lg\" style=\"color: #00A7AA\" ></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
////                                break;
////                            case 3:
////                                obj1.put("FMType", "零件");
////                                obj1.put("FName", "<i class=\"fa fa-cog fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
////                                break;
////                            case 4:
////                                obj1.put("FMType", "模具");
////                                obj1.put("FName", "<i class=\"fa fa-share-alt-square fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
////
////                                break;
////                            case 5:
////                                obj1.put("FMType", "标准件");
////                                obj1.put("FName", "<i class=\"fa fa-cog fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
////                                break;
////                            case 9:
////                                obj1.put("FMType", "其他");
////                                obj1.put("FName", "<i class=\"fa fa-gg fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
////                                break;
////                            default:
////                                obj1.put("FMType", "");
////                                obj1.put("FName", tMaterial.getFno() + "/" + tMaterial.getFname());
////                                break;
////                        }
////                        materialFrom = tMaterialFromService.findById(tMaterial.getFfrom());
////                        obj1.put("FFrom", materialFrom == null ? "" : materialFrom.getFname());
////                        obj1.put("num", "");
////                        object.put("FBomName", tMaterial.getFno() + "/" + tMaterial.getFname());
////                    } else {
////                        obj1.put("FType", "");
////                        obj1.put("FUnitID", "");
////                        obj1.put("FMType", "");
////                        obj1.put("FFrom", "");
////                        obj1.put("num", "");
////                        obj1.put("FName", "");
////                    }
//                    //根据bommid 查询子表
//                    TBomSExample tBomSExample = new TBomSExample();
//                    tBomSExample.createCriteria().andFbommidEqualTo(bomid).andFfidEqualTo(-1L);
//                    tBomSExample.setOrderByClause("fcdate ");
//                    List<TBomS> bomSList = tBomSService.findByExample(tBomSExample);
//                    JSONArray tbomsListarr = new JSONArray();
//                    if (bomSList.size() > 0) {
//                        for (TBomS tbs : bomSList) {
//                            tMaterial = tMaterialService.findById(tbs.getFmaterialid());
//                            JSONObject obj2 = new JSONObject();
//                            obj2.put("key", ParamTools.getEnParam(tbs.getFkeyid().toString()));
//                            obj2.put("FType", tMaterial.getFtype() == 1 ? "标准件" : "非标");
//                            materialUnit = tMaterialUnitService.findById(tMaterial.getFunitid());
//                            obj2.put("FUnitID", materialUnit == null ? "" : materialUnit.getFname());
//                            if (tbs.getFfid() == -1) {
//                                obj2.put("edit", 2);
//                            } else {
//                                obj2.put("edit", 1);
//                            }
//                            obj2.put("FMID", ParamTools.getEnParam(tMaterial.getFkeyid().toString()));
//                            obj2.put("FMName", tMaterial.getFname());
//                            obj2.put("FMNo", tMaterial.getFno());
//                            obj2.put("FModel", tMaterial.getFmodel() == null ? "" : tMaterial.getFmodel());
//                            obj2.put("FSpecs", tMaterial.getFspecs() == null ? "" : tMaterial.getFspecs());
//                            obj2.put("FTexture", tMaterial.getFtexture() == null ? "" : tMaterial.getFtexture());
//                            obj2.put("FSize", tMaterial.getFsize() == null ? "" : tMaterial.getFsize());
//                            obj2.put("FOutside", tMaterial.getFoutside() == null ? "" : tMaterial.getFoutside());
//                            switch (tMaterial.getFmtype()) {
//                                case 1:
//                                    obj2.put("FMType", "产品");
//                                    obj2.put("FName", "<i class=\"fa fa-cogs fa-lg \" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                                    break;
//                                case 2:
//                                    obj2.put("FMType", "制件");
//                                    obj2.put("FName", "<i class=\"fa fa-cog fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                                    break;
//                                case 3:
//                                    obj2.put("FMType", "零件");
//                                    obj2.put("FName", "<i class=\"fa fa-cog fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                                    break;
//                                case 4:
//                                    obj2.put("FMType", "模具");
//                                    obj2.put("FName", "<i class=\"fa fa-share-alt-square fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                                    break;
//                                case 5:
//                                    obj2.put("FMType", "标准件");
//                                    obj2.put("FName", "<i class=\"fa fa-cog fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                                    break;
//                                case 9:
//                                    obj2.put("FMType", "其他");
//                                    obj2.put("FName", "<i class=\"fa fa-gg fa-lg\" style=\"color: #00A7AA\"></i> " + tMaterial.getFno() + "/" + tMaterial.getFname());
//                                    break;
//                                default:
//                                    obj2.put("FMType", "");
//                                    obj2.put("FName", tMaterial.getFno() + "/" + tMaterial.getFname());
//                                    break;
//                            }
//                            materialFrom = tMaterialFromService.findById(tMaterial.getFfrom());
//                            obj2.put("FFrom", materialFrom == null ? "" : materialFrom.getFname());
//                            obj2.put("num", tbs.getFnumU() + "/" + tbs.getFnumB());
//
//                            //递归下级
//                            this.getTBomsxjProject(obj2, tbs.getFkeyid(), bomid);
//                            tbomsListarr.add(obj2);
//                        }
////                        obj1.put("children", tbomsListarr);
//                    } else {
////                        obj1.put("children", null);
//                    }
////                    obj1Listarr.add(obj1);
//                    objall.put("data", tbomsListarr);
//                } else {
//                    objall.put("data", "");
//                }
//
//            } else {
//                objall.put("draw", 0);
//                objall.put("recordsTotal", 0);
//                objall.put("recordsFiltered", 0);
//                objall.put("data", "");
//                object.put("pfstate", -1);
//            }
////            System.out.println("Bom结构："+objall.toString());
//            object.put("datalist2", objall);
//            object.put("result", "success");
////            System.out.println(object.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//
//        return object.toString();
//    }

    /**
     * 获取项目状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/getmyprojectstate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getmyprojectstate(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            TProject tProject = tProjectService.findById(Long.parseLong(id));
            // 返回值
            object.put("pmaterial", tProject == null ? -2 : tProject.getFmaterialid() == null ? -1 : 1);
            object.put("pfstate", tProject == null ? -2 : tProject.getFstate());
            object.put("result", "success");

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取我负责项目总数信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryMyProjectCount", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryMyProjectCount(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            // 查询条件
            TProjectTeamExample tProjectTeamExample = new TProjectTeamExample();
            tProjectTeamExample.or().andFuseridEqualTo(Long.parseLong(uid)); // 只读取自己负责的数据
            PageInfo<TProjectTeam> pageInfo = tProjectTeamService.findByExampleMapper(tProjectTeamExample, 0, 1);
            // 返回值
            object.put("total", pageInfo.getTotal()); // 总行数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取逾期项目总数
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryyuqiyProjectCount", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryyuqiyProjectCount(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            // 查询条件
            TProjectTeamExample tProjectTeamExample = new TProjectTeamExample();
            tProjectTeamExample.or().andFuseridEqualTo(Long.parseLong(uid)); // 只读取自己负责的数据

            List<TProjectTeam> teamList = tProjectTeamService.findByExample(tProjectTeamExample);
            List<Long> pid = new ArrayList<>();
            if (teamList != null && teamList.size() > 0) {
                for (TProjectTeam tProjectTeam : teamList) {
                    pid.add(tProjectTeam.getFprojectid());
                }
            } else {
                pid.add(0L);
            }

            List<Long> yuqiid = new ArrayList<>();
            TProjectExample tProjectExample = new TProjectExample();
            tProjectExample.or().andFkeyidIn(pid).andFstateEqualTo(1);//发布状态下
            List<TProject> projectList = tProjectService.findByExample(tProjectExample);
            TProjectExceed projectExceed = null;
            int yuqiday = 0;
            Date newdate = null;
            Date fedate = null;
            int dayDiffer = -1;
            for (TProject tProject : projectList) {
                //检查是否逾期
                projectExceed = tProjectExceedService.findById(1l);
                //获取提前几天 提醒
                yuqiday = projectExceed == null ? 0 : projectExceed.getFday();
                //获取当前时间
                newdate = new Date();
                //获取计划完成时间
                fedate = tProject.getFedate();
                //判断 当前时间与计划完成时间的差值
                dayDiffer = getDayDiffer(newdate, fedate);
                if (dayDiffer <= yuqiday && dayDiffer >= 0) {
                    //预警
//                    yuqiid.add(tProject.getFkeyid());

                } else if (dayDiffer < 0) {
                    //逾期
                    yuqiid.add(tProject.getFkeyid());

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
     * 获取项目信息--待办
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querymyprojectdaiban", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querymyprojectdaiban(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");

        try {

            String CookiesLoginmyprojectteamID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectteamID != null && !CookiesLoginmyprojectteamID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginmyprojectteamID);
            }
            List<Long> projectid = new ArrayList<>();

            // 获取数据库记录
            JSONArray myproject_Array = new JSONArray();

            // 查询条件
            TProjectExample TProjectExample = new TProjectExample();
            TProjectExample.Criteria criteria = TProjectExample.createCriteria();
            //判断当前用户 是否存在项目中
            TProjectTeamExample tProjectTeamExample = new TProjectTeamExample();
            TProjectTeamExample.Criteria teamExampleCriteria = tProjectTeamExample.createCriteria();
            teamExampleCriteria.andFuseridEqualTo(Long.parseLong(uid));
            teamExampleCriteria.andFstateEqualTo(1);
            List<TProjectTeam> teamList = tProjectTeamService.findByExample(tProjectTeamExample);
            if (teamList != null && teamList.size() > 0) {
                for (TProjectTeam tProjectTeam : teamList) {
                    projectid.add(tProjectTeam.getFprojectid());
                }
                criteria.andFkeyidIn(projectid);
                criteria.andFstateEqualTo(1);
                TProjectExample.setOrderByClause("FCDATE desc");
                PageInfo<TProject> myprojectPageInfo = tProjectService.findByExampleMapper(TProjectExample, (page - 1) * results, results);
                List<TProject> myproject_list = myprojectPageInfo.getList();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String fdate = "";
                for (TProject myproject : myproject_list) {
                    JSONObject myproject_object = new JSONObject();

                    myproject_object.put("key", ParamTools.getEnParam(myproject.getFkeyid().toString()));
                    myproject_object.put("FName", myproject.getFname());
                    myproject_object.put("FNo", myproject.getFno());
                    if (myproject.getFsdate() != null && myproject.getFedate() != null) {
                        fdate = sdf.format(myproject.getFsdate()) + "~" + sdf.format(myproject.getFedate());
                    } else {
                        fdate = "";
                    }
                    myproject_object.put("FDate", fdate);
                    myproject_object.put("FLeadUserID", ParamTools.getEnParam(myproject.getFleaduserid().toString()));
                    TUser userServiceById = tUserService.findById(myproject.getFleaduserid());
                    myproject_object.put("FLeadName", userServiceById == null ? "" : userServiceById.getfName());

                    myproject_Array.add(myproject_object);
                }


                // 返回值
                object.put("list", myproject_Array);
                object.put("total", myprojectPageInfo.getTotal()); // 总行数
                object.put("page", myprojectPageInfo.getPageNum()); // 当前页数
                object.put("result", "success");

            } else {
                // 返回值
                object.put("list", myproject_Array);
                object.put("total", 0); // 总行数
                object.put("page", 0); // 当前页数
                object.put("result", "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


//    /**
//     * 获取Bom信息-BOM索引查询文件
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/showBomTableTreeProjectsy", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String showBomTableTreeProjectsy(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String FProjectID = jsonParam.getString("FProjectID");// 项目ID
//
//        try {
//            JSONObject objall = new JSONObject();
//            JSONObject object1 = null;
//            JSONArray all_Array = new JSONArray();
//            long bommid = 0L;
//            FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);
//            System.out.println(FProjectID);
//            if (FProjectID != null && !FProjectID.equals("0")) {
//
//                TMaterial tMaterial = null;
//
//                TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
//                long fmid = 0L;
//                if (tProject != null) {
//                    fmid = tProject.getFmaterialid() == null ? 0L : tProject.getFmaterialid();
//                }
//                if (fmid != 0L) {
//                    TBomMExample tBomMExample = new TBomMExample();
//                    tBomMExample.or().andFmaterialidEqualTo(fmid).andFshowEqualTo(1);
//                    List<TBomM> mList = tBomMService.findByExample(tBomMExample);
//                    if (mList != null && mList.size() > 0) {
//                        object1 = new JSONObject();
//                        //根节点信息
//                        bommid = mList.get(0).getFkeyid();
//                        object1.put("id", ParamTools.getEnParam(String.valueOf(bommid)));
//                        object1.put("pId", -1);
//                        tMaterial = tMaterialService.findById(mList.get(0).getFmaterialid());
//                        if (tMaterial.getFno() != null && tMaterial.getFname() != null) {
//                            object1.put("name", tMaterial.getFno() + "/" + tMaterial.getFname());
//                        } else {
//                            object1.put("name", "");
//                        }
//
//                        object1.put("ftype", "测试");
//                        all_Array.add(object1);
//
//                        TBomSExample tBomSExample = new TBomSExample();
//                        tBomSExample.or().andFbommidEqualTo(mList.get(0).getFkeyid());
//                        List<TBomS> sList = tBomSService.findByExample(tBomSExample);
//                        if (sList != null && sList.size() > 0) {
//                            for (TBomS s : sList) {
//                                object1 = new JSONObject();
//                                object1.put("id", ParamTools.getEnParam(s.getFkeyid().toString()));
//                                if (s.getFfid() == -1) {
//                                    object1.put("pId", ParamTools.getEnParam(String.valueOf(bommid)));
//                                } else {
//                                    object1.put("pId", ParamTools.getEnParam(s.getFfid().toString()));
//                                }
//
//                                tMaterial = tMaterialService.findById(s.getFmaterialid());
//                                if (tMaterial.getFno() != null && tMaterial.getFname() != null) {
//                                    object1.put("name", tMaterial.getFno() + "/" + tMaterial.getFname());
//                                } else {
//                                    object1.put("name", "");
//                                }
//                                object1.put("ftype", "测试");
//                                all_Array.add(object1);
//                            }
//                        }
//
//                    }
//                    objall.put("files", all_Array);
//                }
//            }
////            System.out.println(all_Array.toString());
//            object.put("bommid", bommid);
//            object.put("dataall", objall);
//            object.put("json", all_Array.toJSONString());
//            object.put("result", "success");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return object.toString();
//    }


//    /**
//     * 模板导入模具信息
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
////    @LogOperation("模板上传模具任务物料清单明细信息")
//    @ResponseBody
//    @RequestMapping(value = "/projectmaterialmobanmj", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String projectmaterialmobanmj(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
//            throws Exception {
//        JSONObject object = new JSONObject();
//        String FKeyID = request.getParameter("FKeyID");//BOMSID
//        String FMID = request.getParameter("FMID");//物料ID
//        String FProjectID = request.getParameter("FProjectID");//项目ID
//        BufferedOutputStream stream = null;
//        FKeyID = FKeyID == null ? "0" : ParamTools.getdeParam(FKeyID);
//        FMID = FMID == null ? "0" : ParamTools.getdeParam(FMID);
//        FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);
//        String CookiesLogindesignpurchaseapplyID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//        String uid = ""; // 当前登录用户ID
//        if (CookiesLogindesignpurchaseapplyID != null && !CookiesLogindesignpurchaseapplyID.equals("")) {
//            uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
//        }
//        TMaterial tMaterial = tMaterialService.findById(Long.parseLong(FMID));
//        String errorstr = "";
//        TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
//        TBomS bomS = tBomSService.findById(Long.parseLong(FKeyID));
//        if (bomS == null) {
//            object.put("result", "error");
//            object.put("error", "未获取到零件信息，请刷新页面后重试！");
//        } else {
//            for (MultipartFile file : uploadFiles) {
//                try {
//                    // 获取文件名
//                    String filename = file.getOriginalFilename();
//                    if (filename == null || filename.equals("")) {
//                        object.put("result", "error");
//                        object.put("error", "请选择文件后再进行上传！");
//                    } else {
//                        // 获取文件的后缀名
//                        String suffixName = filename.substring(filename.lastIndexOf(".") + 1);
//                        //生成新的文件名
//                        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
//                        String realFileName = idWorker.nextId() + "." + suffixName;
//
//                        //读取excel
//                        // 2.创建工作区workbook
//                        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
//                        //生成任务名称 + 任务编号
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                        // 3.获取表sheet,这里sheet0代表获取下表为0的excel表,也就是第一个表
//                        XSSFSheet sheet = workbook.getSheetAt(0);
//                        boolean iserror = false;
//                        String FZHNo = "", FMJLX = "", FGXH = "", FGXMC = "", FFZR = "", FJHDate = "", FWCDate = "", FNote = "";
//                        // 4.获取数据
//                        // getLastRowNum() 获取一张sheet表中行的数量
//                        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
//                            // 获取第i行的数据
//                            XSSFRow row = sheet.getRow(i);
//                            FZHNo = "";
//                            FMJLX = "";
//                            FGXH = "";
//                            FGXMC = "";
//                            FFZR = "";
//                            FJHDate = "";
//                            FWCDate = "";
//                            FNote = "";
//                            // getLastCellNum() 获取这一行中单元格的数量
//                            int iii = 0;
//                            for (int j = 0; j < row.getLastCellNum(); j++) {
//                                //System.out.println(row.getLastCellNum());
//                                // 获取第i行第j列的单元格数据
//                                String cell = null;
//                                try {
//                                    cell = row.getCell(j).toString().trim() == null ? row.createCell(j).toString() : row.getCell(j).toString().trim();
//                                } catch (Exception e) {
//                                    continue;
//                                }
//
//                                if (cell == null && cell.equals("")) {
//                                    break;
//                                }
//                                if (i == 1 && j == 1 && !cell.equals("制号")) {
//                                    iserror = true;
//                                    break;
//                                }
//                                if (iserror == true) {
//                                    break;
//                                } else {
//
//                                    if (i < 2) {
//                                        break;
//                                    }
//                                    switch (j) {
//                                        case 0:
//                                            FZHNo = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
//                                            break;
//                                        case 1:
//                                            FMJLX = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
//                                            break;
//                                        case 2:
//                                            FGXH = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
//                                            break;
//                                        case 3:
//                                            FGXMC = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
//                                            break;
//                                        case 4:
//                                            FFZR = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
//                                            break;
//                                        case 5:
////                                            System.out.println(row.getCell(j).toString().trim());
//                                            FJHDate = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
//
//                                            break;
//                                        case 6:
////                                            System.out.println(row.getCell(j).toString().trim());
//                                            FWCDate = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
//                                            break;
//                                        case 7:
//                                            FNote = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
//                                            break;
//                                    }
//                                }
//                                // 5.打印单元格数据
////                                System.out.print(cell + " | ");
//                            }
//
//                            if (FZHNo.equals("") || FMJLX.equals("") || FGXH.equals("") || FGXMC.equals("") || FFZR.equals("") || FJHDate.equals("") || FWCDate.equals("")) {
//                                if (i >= 2)
//                                    errorstr += "第" + (i + 1) + "行，有空列；<br/>";
//                                continue;
//                            } else {
//                                Date date1 = sdf.parse(FJHDate);
//                                Date date2 = sdf.parse(FWCDate);
//                                if (date1.compareTo(date2) > 0) {
//                                    if (i >= 2)
//                                        errorstr += "第" + ((i + 1) - 2) + "行，计划完成时间大于计划开始时间；<br/>";
//                                    continue;
//                                } else {
//                                    String fname = "";
//                                    long ffuid = -1l;
//                                    long FMMType = -1l;
//                                    long FPurNo = -1l;
//                                    long FPurName = -1l;
//                                    String FPurNoName = "";
//                                    String FPurNameName = "";
//                                    long fmid = -1l;
//                                    //先判断 负责人是否存在系统
//                                    TUserExample tUserExample = new TUserExample();
//                                    tUserExample.createCriteria().andFNameEqualTo(FFZR);
//                                    List<TUser> userList = tUserService.findByExample(tUserExample);
//                                    if (userList.size() == 0) {
//                                        if (i >= 2)
//                                            errorstr += "第" + ((i + 1) - 2) + "行，系统未查到负责人信息，不能录入；<br/>";
//                                        continue;
//                                    } else {
//                                        ffuid = userList.get(0).getfKeyId();
//                                        //再判断 模具类型，工序号 ，工序名称 是否存在系统
//                                        TMaterialMoldTypeExample tMaterialMoldTypeExample = new TMaterialMoldTypeExample();
//                                        tMaterialMoldTypeExample.createCriteria().andFnameEqualTo(FMJLX);
//                                        List<TMaterialMoldType> typeList = tMaterialMoldTypeService.findByExample(tMaterialMoldTypeExample);
//                                        if (typeList.size() == 0) {
//                                            FMMType = idWorker.nextId();
//                                            TMaterialMoldType tMaterialMoldType = new TMaterialMoldType();
//                                            tMaterialMoldType.setFkeyid(FMMType);
//                                            tMaterialMoldType.setFname(FMJLX);
//                                            tMaterialMoldType.setFcdate(new Date());
//                                            tMaterialMoldType.setFcid(Long.parseLong(uid));
//                                            tMaterialMoldTypeService.save(tMaterialMoldType);
//                                        } else {
//                                            FMMType = typeList.get(0).getFkeyid();
//                                        }
//
//                                        TProcedureNoExample tProcedureNoExample = new TProcedureNoExample();
//                                        tProcedureNoExample.createCriteria().andFnameEqualTo(FGXH);
//                                        List<TProcedureNo> noList = tProcedureNoService.findByExample(tProcedureNoExample);
//                                        if (noList.size() == 0) {
//                                            FPurNo = idWorker.nextId();
//                                            TProcedureNo tProcedureNo = new TProcedureNo();
//                                            tProcedureNo.setFkeyid(FPurNo);
//                                            tProcedureNo.setFname(FGXH);
//                                            tProcedureNo.setFcdate(new Date());
//                                            tProcedureNo.setFcid(Long.parseLong(uid));
//                                            tProcedureNoService.save(tProcedureNo);
//                                            FPurNoName = FGXH;
//                                        } else {
//                                            FPurNo = noList.get(0).getFkeyid();
//                                            FPurNoName = noList.get(0).getFname();
//                                        }
//
//                                        TProcedureNameExample tProcedureNameExample = new TProcedureNameExample();
//                                        tProcedureNameExample.createCriteria().andFnameEqualTo(FGXMC);
//                                        List<TProcedureName> nameList = tProcedureNameService.findByExample(tProcedureNameExample);
//                                        if (nameList.size() == 0) {
//                                            FPurName = idWorker.nextId();
//                                            TProcedureName tProcedureName = new TProcedureName();
//                                            tProcedureName.setFkeyid(FPurName);
//                                            tProcedureName.setFname(FGXMC);
//                                            tProcedureName.setFcdate(new Date());
//                                            tProcedureName.setFcid(Long.parseLong(uid));
//                                            tProcedureNameService.save(tProcedureName);
//                                            FPurNameName = FGXMC;
//                                        } else {
//                                            FPurName = nameList.get(0).getFkeyid();
//                                            FPurNameName = nameList.get(0).getFname();
//                                        }
//
//                                        fname = FPurNoName + "/" + FPurNameName;
//
//
//                                        TMaterialExample tMaterialExample = new TMaterialExample();
//                                        tMaterialExample.createCriteria().andFnoEqualTo(FZHNo);
//                                        List<TMaterial> materialList = tMaterialService.findByExample(tMaterialExample);
//                                        if (materialList.size() == 0) {
//                                            fmid = idWorker.nextId();
//                                            // 新建物料信息
//                                            TMaterial material = new TMaterial();
//                                            material.setFkeyid(fmid);
//                                            material.setFname(fname);
//                                            material.setFno(FZHNo);
//                                            material.setFmtype(4);
//                                            material.setFtype(2);
//                                            material.setFunitid(0l);
//                                            material.setFnote(FNote);
//                                            material.setFfrom(0l);
//                                            material.setFcid(Long.parseLong(uid));
//                                            material.setFcdate(new Date());
//                                            material.setFmodel("");
//                                            material.setFspecs("");
//                                            material.setFsize("");
//                                            material.setFtexture("");
//                                            material.setFoutside("");
//                                            material.setFmmtype(FMMType);
//                                            material.setFpurno(FPurNo);
//                                            material.setFpurname(FPurName);
//                                            tMaterialService.save(material);
//
//                                        } else {
//                                            fmid = materialList.get(0).getFkeyid();
//                                        }
//                                        //最后插入系统
//                                        TBomSExample tBomSExample = new TBomSExample();
//                                        tBomSExample.createCriteria().andFfidEqualTo(bomS.getFkeyid()).andFmaterialnoEqualTo(FZHNo);
//                                        List<TBomS> bomSList = tBomSService.findByExample(tBomSExample);
//                                        if (bomSList.size() <= 0) {
//                                            long key2 = idWorker.nextId();
//                                            TBomS tBomS = new TBomS();
//                                            tBomS.setFkeyid(key2);
//                                            tBomS.setFbommid(bomS.getFbommid());
//                                            tBomS.setFmaterialid(fmid);
//                                            tBomS.setFmaterialno(FZHNo);
//                                            tBomS.setFfid(bomS.getFkeyid());
//                                            tBomS.setFnumU(1);
//                                            tBomS.setFnumB(1);
//                                            tBomS.setFnote(FNote);
//                                            tBomS.setFisleaf(1);
//                                            tBomS.setFdiv((bomS.getFdiv() + 1));
//                                            tBomS.setFpath(bomS.getFpath() + "_(" + key2 + ")");
//                                            tBomS.setFcid(Long.parseLong(uid));
//                                            tBomSService.save(tBomS);
//
//                                            //添加模具计划相关数据
//                                            long key3 = idWorker.nextId();
//                                            TProjectMjPlan tProjectMjPlan = new TProjectMjPlan();
//                                            tProjectMjPlan.setFkeyid(key3);
//                                            tProjectMjPlan.setFbomsid(key2);
//                                            tProjectMjPlan.setFprojectid(tProject.getFkeyid());
//                                            tProjectMjPlan.setFmid(fmid);
//                                            tProjectMjPlan.setFcid(Long.parseLong(uid));
//                                            tProjectMjPlan.setFcdate(new Date());
//                                            tProjectMjPlanService.save(tProjectMjPlan);
//                                            TProjectMjPlanS tProjectMjPlanS = null;
//                                            for (int plani = 1; plani < 30; plani++) {
//                                                tProjectMjPlanS = new TProjectMjPlanS();
//                                                tProjectMjPlanS.setFmplid(key3);
//                                                tProjectMjPlanS.setFmid(fmid);
//                                                tProjectMjPlanS.setFbomsid(key2);
//                                                tProjectMjPlanS.setFprojectid(tProject.getFkeyid());
//                                                tProjectMjPlanS.setFptype(Long.parseLong(String.valueOf(plani)));
//                                                tProjectMjPlanS.setFkhdate("");
//                                                tProjectMjPlanS.setFtzdate("");
//                                                tProjectMjPlanS.setFsjdate("");
//                                                tProjectMjPlanS.setFcid(Long.parseLong(uid));
//                                                tProjectMjPlanS.setFcdate(new Date());
//                                                tProjectMjPlanSService.save(tProjectMjPlanS);
//                                            }
//                                        }
//                                        try {
//                                            //同步添加任务
//
//
//                                            //传入流水号的值
//                                            int maxnum = 1;
//                                            //查询当前年月
//                                            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMM");
//                                            String yymm = sdf2.format(new Date());
//                                            //查询当前几号
//                                            int day = getDay();
//                                            //查询项目中当前年月 最大月流水号
//                                            TSysnoExample tSysnoExample = new TSysnoExample();
//                                            tSysnoExample.createCriteria().andFtypeEqualTo(1).andFyymmnumEqualTo(yymm);
//                                            tSysnoExample.setOrderByClause("FSysNoNum desc limit 1 ");
//                                            List<TSysno> tSysnos = tSysNoService.findByExample(tSysnoExample);
//                                            if (tSysnos.size() > 0) {
//                                                maxnum = (tSysnos.get(0).getFsysnonum() + 1);
//                                            } else {
//                                                maxnum = 1;
//                                            }
//                                            //生成流水号
//                                            String numberE = formatNumberE(maxnum, yymm, "RW");
//                                            TSysno tSysno = new TSysno();
//                                            tSysno.setFtype(1);
//                                            tSysno.setFsysno(numberE);
//                                            tSysno.setFsysnonum(maxnum);
//                                            tSysno.setFyymmnum(yymm);
//                                            tSysno.setFcdate(new Date());
//                                            tSysno.setFcid(Long.parseLong(uid));
//                                            tSysNoService.save(tSysno);
//
//
//                                            TTaskExample tTaskExample1 = new TTaskExample();
//                                            tTaskExample1.createCriteria().andFprojectidEqualTo(Long.parseLong(FProjectID)).andFnoEqualTo(numberE);
//                                            List<TTask> taskList = tTaskService.findByExample(tTaskExample1);
//                                            if (taskList.size() == 0) {
//                                                String fno = tProject.getFno();
//                                                maxnum = 1;
//                                                int maxnum2 = 1;
//                                                //查找当前项目 任务顺序
//                                                TTaskExample tTaskExample = new TTaskExample();
//                                                TTaskExample.Criteria criteria = tTaskExample.createCriteria();
//                                                criteria.andFprojectidEqualTo(Long.parseLong(FProjectID));
//                                                tTaskExample.setOrderByClause("FSysNoNum desc limit 1 ");
//                                                List<TTask> tTaskList = tTaskService.findByExample(tTaskExample);
//                                                if (tTaskList.size() > 0) {
//                                                    maxnum = (tTaskList.get(0).getFsysnonum() + 1);
//                                                } else {
//                                                    maxnum = 1;
//                                                }
//                                                SnowflakeIdWorker idWorker2 = new SnowflakeIdWorker(1, 1);
//                                                long takekey = idWorker2.nextId();
//                                                DecimalFormat dft = new DecimalFormat(FORMAT_CODE);
//                                                // 新建任务信息
//                                                TTask ttask = new TTask();
//                                                ttask.setFkeyid(takekey);
//                                                ttask.setFname(fname + "任务");
//                                                ttask.setFno(numberE);
//                                                ttask.setFprojectid(Long.parseLong(FProjectID));
//                                                ttask.setFask("");
//                                                ttask.setFnote("");
//                                                ttask.setFtypeid(1l);
//                                                ttask.setFleaduserid(ffuid);
//                                                ttask.setFsdate(sdf.parse(FJHDate));
//                                                ttask.setFedate(sdf.parse(FWCDate));
//                                                ttask.setFmaterialid(fmid);
//                                                ttask.setFstate(0);
//                                                ttask.setFismilepost(0);
//                                                ttask.setFsysnonum(maxnum);
//                                                ttask.setFsysno(fno + "/" + dft.format(maxnum));
//                                                ttask.setFmtype(Long.parseLong("3"));
//                                                ttask.setFcid(Long.parseLong(uid));
//                                                ttask.setFuserid(Long.parseLong(uid));
//                                                ttask.setFcdate(new Date());
//                                                ttask.setFfid(-1L);
//                                                ttask.setFpath("_(-1)");
//                                                tTaskService.save(ttask);
//
//
//                                                //添加任务团队中。负责人信息
//
//                                                TTaskTeam tTaskTeam = new TTaskTeam();
//                                                tTaskTeam.setFtaskid(takekey);
//                                                tTaskTeam.setFprojectid(Long.parseLong(FProjectID));
//                                                tTaskTeam.setFuserid(ffuid);
//                                                tTaskTeam.setFrole(1);
//                                                tTaskTeam.setFduty("");
//                                                tTaskTeam.setFnote("");
//                                                tTaskTeam.setFcid(Long.parseLong(uid));
//                                                tTaskTeamService.save(tTaskTeam);
//
//
//                                                //添加日志
//                                                ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
//                                                String fnote = "为项目【" + tProject.getFname() + "】，创建任务【" + fname + "任务" + "】。";
//                                                projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "任务信息", "创建任务", "", uid);
//
//
//                                                //添加日志
//                                                TaskLogc taskLogc = new TaskLogc(tTaskLogService, tUserService);
//                                                fnote = "创建任务【" + fname + "任务" + "】，所属项目【" + tProject.getFname() + "】。";
//                                                taskLogc.addttasklog(fnote, String.valueOf(tProject.getFkeyid()), String.valueOf(takekey), "任务信息", "创建任务", "", uid);
//
//
//                                                try {
//                                                    TUser tUser = tUserService.findById(Long.parseLong(uid));
//                                                    TLogAction logAction = new TLogAction();
//                                                    logAction.setfUserId(tUser.getfKeyId());
//                                                    logAction.setfUserName(tUser.getfName());
//                                                    logAction.setfType(3);
//                                                    logAction.setfPath("ttask/addttask{" + takekey + "}");
//                                                    logAction.setfUserType(1);
//
//                                                    logAction.setfMemo(fnote);
//                                                    logActionService.save(logAction);
//                                                } catch (NumberFormatException e) {
//                                                    e.printStackTrace();
//                                                }
//                                            }
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                        }
//
//                                    }
//                                }
//
//                            }
//
//                            // println换行
////                        System.out.println("");
//                        }
//                        if (iserror == true) {
//                            object.put("result", "error");
//                            object.put("error", "请选择正确的模板进行导入！");
//                        } else {
//
//                            try {
//                                TUser tUser = tUserService.findById(Long.parseLong(uid));
//                                TLogAction logAction = new TLogAction();
//                                logAction.setfUserId(tUser.getfKeyId());
//                                logAction.setfUserName(tUser.getfName());
//                                logAction.setfType(3);
//                                logAction.setfPath("myproject/projectmaterialmobanmj{" + tMaterial.getFkeyid() + "}");
//                                logAction.setfUserType(1);
//
//                                logAction.setfMemo("零件【" + tMaterial.getFno() + "/" + tMaterial.getFname() + "】模板导入模具信息。");
//                                logActionService.save(logAction);
//                            } catch (NumberFormatException e) {
//                                e.printStackTrace();
//                            }
//
//                            // 返回值
//                            object.put("result", "success");
//                            object.put("errorstr", errorstr);
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    object.put("result", "error");
//                    object.put("error", e.toString());
//                }
//            }
//        }
//
//        return object.toString();
//    }



    /**
     * 完成项目前进行判断
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/wanchengemyprojectyz", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String wanchengemyprojectyz(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLoginmyprojectID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectID != null && !CookiesLoginmyprojectID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            //判断当前项目是否上传了项目文件，如果数量大于0 则通过，如果等于0 给出提示
            TProject tProject = tProjectService.findById(Long.parseLong(id));
            if (tProject.getFstate() == 1) {
                //
                TProjectFilesExample tProjectFilesExample = new TProjectFilesExample();
                tProjectFilesExample.createCriteria().andFprojectidEqualTo(Long.parseLong(id)).andFvalidEqualTo(1);
                List<TProjectFiles> filesList = tProjectFilesService.findByExample(tProjectFilesExample);
                if(filesList.size() == 0){
                    object.put("mesg", 1);//给出提示
                }else{
                    object.put("mesg", 2);//可以完成
                }
                object.put("result", "success");
            } else {
                object.put("result", "error");
                object.put("error", "该项目已完成，不能重复操作，请刷新页面！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

}