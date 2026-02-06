package fun.server.controller.student;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.model.customQuery.studentability.StudentabilityCs;
import fun.server.model.customQuery.studentability.Studentabilitydata;
import fun.server.service.*;
import fun.tools.FolderTools;
import fun.tools.ParamTools;
import fun.tools.SnowflakeIdWorker;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 学生能力管理 相关业务处理
 */
@RestController
@RequestMapping("/s/studentability")
public class StudentAbility {

    private final TStudentAbilityService tStudentAbilityService;

    private final TStudentService tStudentService;

    private final TAbilityService tAbilityService;

    private final TAbilityLevelService tAbilityLevelService;

    private final TAbilityTreeService tAbilityTreeService;

    private final TAbilityLevelConditionService tAbilityLevelConditionService;


    public StudentAbility(TStudentAbilityService tStudentAbilityService, TStudentService tStudentService, TAbilityService tAbilityService, TAbilityLevelService tAbilityLevelService, TAbilityTreeService tAbilityTreeService, TAbilityLevelConditionService tAbilityLevelConditionService) {
        this.tStudentAbilityService = tStudentAbilityService;
        this.tStudentService = tStudentService;
        this.tAbilityService = tAbilityService;
        this.tAbilityLevelService = tAbilityLevelService;
        this.tAbilityTreeService = tAbilityTreeService;
        this.tAbilityLevelConditionService = tAbilityLevelConditionService;
    }

    @Value("${studentfj.path}")
    private String studentfj;

    @Value("${studentmb.path}")
    private String studentmb;


    @Value("${studentabilitymb.path}")
    private String studentabilitymb;


    /**
     * 获取学生能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querystudentability", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querystudentability(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String FStudentID = jsonParam.getString("id");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray studentability_Array = new JSONArray();
            // 查询条件
            TStudentAbilityExample TStudentAbilityExample = new TStudentAbilityExample();
            TStudentAbilityExample.Criteria criteria = TStudentAbilityExample.createCriteria();
            if (FStudentID != null && !FStudentID.equals("")) {
                FStudentID = FStudentID == null ? "" : ParamTools.getdeParam(FStudentID);
                criteria.andFstudentidEqualTo(Long.parseLong(FStudentID));
            } else {
                criteria.andFstudentidEqualTo(0l);
            }
            TStudentAbilityExample.setOrderByClause("FCDATE desc");
            PageInfo<TStudentAbility> studentabilityPageInfo = tStudentAbilityService.findByExampleMapper(TStudentAbilityExample, (page - 1) * results, results);
            List<TStudentAbility> studentability_list = studentabilityPageInfo.getList();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (TStudentAbility studentability : studentability_list) {
                JSONObject studentability_object = new JSONObject();
                studentability_object.put("key", ParamTools.getEnParam(studentability.getFkeyid().toString()));
                studentability_object.put("FStudentID", ParamTools.getEnParam(studentability.getFstudentid().toString()));
//                studentability_object.put("FAbilityID", ParamTools.getEnParam(studentability.getFabilityid().toString()));
//                studentability_object.put("FAbilityLevelID", ParamTools.getEnParam(studentability.getFabilitylevelid().toString()));
                studentability_object.put("FConditionID", ParamTools.getEnParam(studentability.getFconditionid().toString()));
                if (dataall == 1) {

                    TStudent student = tStudentService.findById(studentability.getFstudentid());
                    studentability_object.put("FStudentName", student == null ? "" : student.getFname());
//                    TAbilityTree tAbility = tAbilityTreeService.findById(studentability.getFabilityid());
//                    studentability_object.put("FAbilityName", tAbility == null ? "" : tAbility.getFname());
//                    TAbilityTree abilityLevel = tAbilityTreeService.findById(studentability.getFabilitylevelid());
//                    studentability_object.put("FAbilityLevelName", abilityLevel == null ? "" : abilityLevel.getFname());

                    TAbilityLevelCondition abilityLevelCondition = tAbilityLevelConditionService.findById(studentability.getFconditionid());
                    studentability_object.put("FConditionName", abilityLevelCondition == null ? "" : abilityLevelCondition.getFname());

                    studentability_object.put("FDate", studentability.getFdate() == null ? "" : dateFormat.format(studentability.getFdate()));
                    studentability_object.put("FMode", studentability.getFmode());
                    studentability_object.put("FAbilityInf", studentability.getFabilityinf() == null ? "" : studentability.getFabilityinf());
                    studentability_object.put("FUrl", studentability.getFurl() == null ? "" : studentability.getFurl());
                    studentability_object.put("FCID", studentability.getFcid());
                    studentability_object.put("FUID", studentability.getFuid());
                    studentability_object.put("FCDATE", studentability.getFcdate());
                    studentability_object.put("FUDATE", studentability.getFudate());
                } else {
                    studentability_object.put("FStudentName", "*****");
                    studentability_object.put("FAbilityName", "*****");
                    studentability_object.put("FAbilityLevelName", "*****");
                    studentability_object.put("FDate", "*****");
                    studentability_object.put("FMode", "*****");
                    studentability_object.put("FAbilityInf", "*****");
                    studentability_object.put("FUrl", "*****");
                    studentability_object.put("FCID", "*****");
                    studentability_object.put("FUID", "*****");
                    studentability_object.put("FCDATE", "*****");
                    studentability_object.put("FUDATE", "*****");
                }

                studentability_object.put("FState", studentability.getFstate());
                studentability_Array.add(studentability_object);
            }
            // 返回值
            object.put("list", studentability_Array);
            object.put("total", studentabilityPageInfo.getTotal()); // 总行数
            object.put("page", studentabilityPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 学生能力认定列表
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querystudentabilityrd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querystudentabilityrd(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String FName = jsonParam.getString("FName");
        String FTel = jsonParam.getString("FTel");
        String FAbilityName = jsonParam.getString("FAbilityName");
        int FState = jsonParam.getInteger("FState");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray studentability_Array = new JSONArray();
            // 查询条件
            StudentabilityCs studentabilityCs = new StudentabilityCs();
            studentabilityCs.setFAbilityName(FAbilityName);

            studentabilityCs.setFState(FState);

            studentabilityCs.setFTel(FTel);
            studentabilityCs.setFName(FName);
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                studentabilityCs.setOrderBy(orderSql.substring(1));
            } else {
                studentabilityCs.setOrderBy("FCDATE DESC");
            }
            PageInfo<Studentabilitydata> studentabilityPageInfo = tStudentAbilityService.getStudentAbilitylist(studentabilityCs, (page - 1) * results, results);
            List<Studentabilitydata> studentability_list = studentabilityPageInfo.getList();

            for (Studentabilitydata studentability : studentability_list) {
                JSONObject studentability_object = new JSONObject();
                studentability_object.put("key", ParamTools.getEnParam(studentability.getFKeyID().toString()));
                studentability_object.put("FStudentID", studentability.getFStudentID() == null ? ParamTools.getEnParam("0") :ParamTools.getEnParam(studentability.getFStudentID().toString()));
                studentability_object.put("FAbilityID", studentability.getFAbilityID() == null ? ParamTools.getEnParam("0") :ParamTools.getEnParam(studentability.getFAbilityID().toString()));
                studentability_object.put("FAbilityLevelID",studentability.getFAbilityLevelID() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(studentability.getFAbilityLevelID().toString()));
                studentability_object.put("FConditionID", studentability.getFConditionID() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(studentability.getFConditionID().toString()));
                if (dataall == 1) {
                    studentability_object.put("FSName", studentability.getFSName());
                    studentability_object.put("FSNo", studentability.getFSNo());
                    studentability_object.put("FSex", studentability.getFSex());
                    studentability_object.put("FBirthday", studentability.getFBirthday());
                    studentability_object.put("FTel", studentability.getFTel());
                    studentability_object.put("FDate", studentability.getFDate());
                    studentability_object.put("FMode", studentability.getFMode());
                    studentability_object.put("FAbilityInf", studentability.getFAbilityInf());
                    studentability_object.put("FUrl", studentability.getFUrl());
                    studentability_object.put("FTABName", studentability.getFTABName());
                    studentability_object.put("FTABLName", studentability.getFTABLName());
                    studentability_object.put("FTATBTName", studentability.getFTATBTName());
                    studentability_object.put("FConditionName", studentability.getFConditionName());
                } else {
                    studentability_object.put("FStudentName", "*****");
                    studentability_object.put("FSName", "*****");
                    studentability_object.put("FSNo", "*****");
                    studentability_object.put("FSex", "*****");
                    studentability_object.put("FBirthday", "*****");
                    studentability_object.put("FTel", "*****");
                    studentability_object.put("FDate", "*****");
                    studentability_object.put("FMode", "*****");
                    studentability_object.put("FAbilityInf", "*****");
                    studentability_object.put("FUrl", "*****");
                    studentability_object.put("FTABName", "*****");
                    studentability_object.put("FTABLName", "*****");
                    studentability_object.put("FTATBTName", "*****");
                }
                studentability_object.put("FMode", studentability.getFMode());
                studentability_object.put("FSState", studentability.getFSState());
                studentability_Array.add(studentability_object);
            }
            // 返回值
            object.put("list", studentability_Array);
            object.put("total", studentabilityPageInfo.getTotal()); // 总行数
            object.put("page", studentabilityPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


//    /**
//     * 根据ID获取学生能力信息
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/querystudentabilityInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String querystudentabilityInfo(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String id = jsonParam.getString("id");
//        try {
//            id = id == null ? "0" : ParamTools.getdeParam(id);
//            long key = Long.parseLong(id);
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            // 查询学生能力信息
//            TStudentAbility studentability = tStudentAbilityService.findById(key);
//            JSONObject studentability_object = new JSONObject();
//            studentability_object.put("key", ParamTools.getEnParam(studentability.getFkeyid().toString()));
//            studentability_object.put("FStudentID", ParamTools.getEnParam(studentability.getFstudentid().toString()));
//            studentability_object.put("FAbilityID", ParamTools.getEnParam(studentability.getFabilityid().toString()));
//            studentability_object.put("FAbilityLevelID", ParamTools.getEnParam(studentability.getFabilitylevelid().toString()));
//            studentability_object.put("FDate", studentability.getFdate() == null ? "" : dateFormat.format(studentability.getFdate()));
//            studentability_object.put("FMode", studentability.getFmode());
//            studentability_object.put("FAbilityInf", studentability.getFabilityinf() == null ? "" : studentability.getFabilityinf());
//            studentability_object.put("FUrl", studentability.getFurl() == null ? "" : studentability.getFurl());
//            TStudent student = tStudentService.findById(studentability.getFstudentid());
//            studentability_object.put("FStudentName", student == null ? "" : student.getFname());
//            TAbility tAbility = tAbilityService.findById(studentability.getFabilityid());
//            studentability_object.put("FAbilityName", tAbility == null ? "" : tAbility.getFname());
//            TAbilityLevel abilityLevel = tAbilityLevelService.findById(studentability.getFabilitylevelid());
//            studentability_object.put("FAbilityLevelName", abilityLevel == null ? "" : abilityLevel.getFname());
//            studentability_object.put("FCID", studentability.getFcid());
//            studentability_object.put("FUID", studentability.getFuid());
//            studentability_object.put("FCDATE", studentability.getFcdate());
//            studentability_object.put("FUDATE", studentability.getFudate());
//            studentability_object.put("FState", studentability.getFstate());
//            // 返回值
//            object.put("info", studentability_object);
//            object.put("result", "success");
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }

    /**
     * 根据ID获取学生能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querystudentabilityInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querystudentabilityInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // 查询学生能力信息
            TStudentAbility studentability = tStudentAbilityService.findById(key);
            JSONObject studentability_object = new JSONObject();
            studentability_object.put("key", ParamTools.getEnParam(studentability.getFkeyid().toString()));
            studentability_object.put("FStudentID", studentability.getFstudentid() == null ? ParamTools.getEnParam("0") :ParamTools.getEnParam(studentability.getFstudentid().toString()));
            studentability_object.put("FAbilityID", studentability.getFabilityid() == null ? ParamTools.getEnParam("0") :ParamTools.getEnParam(studentability.getFabilityid().toString()));
            studentability_object.put("FAbilityLevelID",studentability.getFabilitylevelid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(studentability.getFabilitylevelid().toString()));
            studentability_object.put("FConditionID", studentability.getFconditionid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(studentability.getFconditionid().toString()));
            studentability_object.put("FDate", studentability.getFdate() == null ? "" : dateFormat.format(studentability.getFdate()));
            studentability_object.put("FMode", studentability.getFmode());
            studentability_object.put("FAbilityInf", studentability.getFabilityinf() == null ? "" : studentability.getFabilityinf());
            studentability_object.put("FUrl", studentability.getFurl() == null ? "" : studentability.getFurl());
            TStudent student = tStudentService.findById(studentability.getFstudentid());
            studentability_object.put("FStudentName", student == null ? "" : student.getFname());
            TAbilityTree tAbility = tAbilityTreeService.findById(studentability.getFabilityid());
            studentability_object.put("FAbilityName", tAbility == null ? "" : tAbility.getFname());
            TAbilityTree abilityLevel = tAbilityTreeService.findById(studentability.getFabilitylevelid());
            studentability_object.put("FAbilityLevelName", abilityLevel == null ? "" : abilityLevel.getFname());

            TAbilityLevelCondition serviceById = tAbilityLevelConditionService.findById(studentability.getFconditionid());
            studentability_object.put("FConditionName", serviceById == null ? "" : serviceById.getFname());

            studentability_object.put("FCID", studentability.getFcid());
            studentability_object.put("FUID", studentability.getFuid());
            studentability_object.put("FCDATE", studentability.getFcdate());
            studentability_object.put("FUDATE", studentability.getFudate());
            studentability_object.put("FState", studentability.getFstate());
            // 返回值
            object.put("info", studentability_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 添加学生能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加学生能力信息")
    @ResponseBody
    @RequestMapping(value = "/addstudentAbility", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addstudentAbility(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FStudentID = jsonParam.getString("studentID");
        String studentname = jsonParam.getString("studentname");

        int FState = 1;
        int FMode = 3;
//        String FAbilityID = jsonParam.getString("FAbilityID");
//        String FAbilityLevelID = jsonParam.getString("FAbilityLevelID");
        String FConditionID = jsonParam.getString("FConditionID");
        String FAbilityInf = jsonParam.getString("FAbilityInf");
        String FDate = jsonParam.getString("FDate");

        try {

//            FStudentID = FStudentID == null ? "0" : FStudentID.equals("0") ? "0" : ParamTools.getdeParam(FStudentID);
//            FAbilityID = FAbilityID == null ? "0" : FAbilityID.equals("0") ? "0" : ParamTools.getdeParam(FAbilityID);
//            FAbilityLevelID = FAbilityLevelID == null ? "0" : FAbilityLevelID.equals("0") ? "0" : ParamTools.getdeParam(FAbilityLevelID);
            FConditionID = FConditionID == null ? "0" : FConditionID.equals("0") ? "0" : ParamTools.getdeParam(FConditionID);

            String CookiesLoginpostID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginpostID != null && !CookiesLoginpostID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginpostID);
            }

            if (studentname != null && !studentname.equals("")) {

                //增加新选的
                JSONArray powerRoles_Array = JSONArray.parseArray(studentname);
                for (Object userid : powerRoles_Array) {
                    Long studentid = Long.parseLong(ParamTools.getdeParam(userid.toString()));
                    TStudentAbilityExample studentabilityExample = new TStudentAbilityExample();
                    TStudentAbilityExample.Criteria criteria = studentabilityExample.createCriteria();
                    criteria.andFstudentidEqualTo(studentid);
                    criteria.andFconditionidEqualTo(Long.parseLong(FConditionID));
//                    criteria.andFabilityidEqualTo(Long.parseLong(FAbilityID));
//                    criteria.andFabilitylevelidEqualTo(Long.parseLong(FAbilityLevelID));
                    List<TStudentAbility> studentAbilityList = tStudentAbilityService.findByExample(studentabilityExample);
                    if (studentAbilityList.size() > 0) {
                        // 返回值
                        object.put("result", "fail");
                    } else {
                        // 定义日期格式
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        TStudentAbility tstudentability = new TStudentAbility();
                        tstudentability.setFstudentid(studentid);
                        tstudentability.setFstate(FState);
                        tstudentability.setFmode(FMode);
                        tstudentability.setFconditionid(Long.parseLong(FConditionID));
//                        tstudentability.setFabilityid(Long.parseLong(FAbilityID));
//                        tstudentability.setFabilitylevelid(Long.parseLong(FAbilityLevelID));
                        tstudentability.setFabilityinf(FAbilityInf);
                        tstudentability.setFurl("");

                        tstudentability.setFdate(dateFormat.parse(FDate));
                        tstudentability.setFcdate(new Date());
                        tstudentability.setFcid(Long.parseLong(uid));
                        tStudentAbilityService.save(tstudentability);


                        //查询当前学生 所拥有的能力 然后算分
                        int studentFPoints = tStudentAbilityService.getStudentFPoints(studentid);
                        //修改学生表 能力得分
                        TStudent student = new TStudent();
                        student.setFkeyid(studentid);
                        student.setFpoints(studentFPoints);
                        tStudentService.update(student);
                        // 返回值
                        object.put("result", "success");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 修改学生能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改学生能力信息")
    @ResponseBody
    @RequestMapping(value = "/updatestudentAbility", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatestudentAbility(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FKeyID = jsonParam.getString("FKeyID");
        String FStudentID = jsonParam.getString("studentID");
        int FState = jsonParam.getInteger("fstate");
        int FMode = 3;
//        String FAbilityID = jsonParam.getString("FAbilityID");
//        String FAbilityLevelID = jsonParam.getString("FAbilityLevelID");
        String FConditionID = jsonParam.getString("FConditionID");

        String FAbilityInf = jsonParam.getString("FAbilityInf");
        String FDate = jsonParam.getString("FDate");
        try {
            FKeyID = FKeyID == null ? "0" : FKeyID.equals("0") ? "0" : ParamTools.getdeParam(FKeyID);
            FStudentID = FStudentID == null ? "0" : FStudentID.equals("0") ? "0" : ParamTools.getdeParam(FStudentID);
//            FAbilityID = FAbilityID == null ? "0" : FAbilityID.equals("0") ? "0" : ParamTools.getdeParam(FAbilityID);
//            FAbilityLevelID = FAbilityLevelID == null ? "0" : FAbilityLevelID.equals("0") ? "0" : ParamTools.getdeParam(FAbilityLevelID);
            FConditionID = FConditionID == null ? "0" : FConditionID.equals("0") ? "0" : ParamTools.getdeParam(FConditionID);

            String CookiesLoginpostID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginpostID != null && !CookiesLoginpostID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginpostID);
            }
            TStudentAbilityExample studentabilityExample = new TStudentAbilityExample();
            TStudentAbilityExample.Criteria criteria = studentabilityExample.createCriteria();
            criteria.andFstudentidEqualTo(Long.parseLong(FStudentID));
            criteria.andFconditionidEqualTo(Long.parseLong(FConditionID));
//            criteria.andFabilityidEqualTo(Long.parseLong(FAbilityID));
//            criteria.andFabilitylevelidEqualTo(Long.parseLong(FAbilityLevelID));
            criteria.andFkeyidNotEqualTo(Long.parseLong(FKeyID));
            List<TStudentAbility> studentAbilityList = tStudentAbilityService.findByExample(studentabilityExample);
            if (studentAbilityList.size() > 0) {
                // 返回值
                object.put("result", "fail");
            } else {
                // 定义日期格式
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                TStudentAbility tstudentability = new TStudentAbility();
                tstudentability.setFkeyid(Long.parseLong(FKeyID));
                tstudentability.setFstudentid(Long.parseLong(FStudentID));
                tstudentability.setFstate(FState);
//                tstudentability.setFmode(FMode);
//                tstudentability.setFabilityid(Long.parseLong(FAbilityID));
//                tstudentability.setFabilitylevelid(Long.parseLong(FAbilityLevelID));
                tstudentability.setFconditionid(Long.parseLong(FConditionID));
                tstudentability.setFabilityinf(FAbilityInf);
                tstudentability.setFurl("");
                tstudentability.setFudate(new Date());
                tstudentability.setFdate(dateFormat.parse(FDate));
                tstudentability.setFuid(Long.parseLong(uid));
                tStudentAbilityService.update(tstudentability);

                //查询当前学生 所拥有的能力 然后算分
                int studentFPoints = tStudentAbilityService.getStudentFPoints(Long.parseLong(FStudentID));
                //修改学生表 能力得分
                TStudent student = new TStudent();
                student.setFkeyid(Long.parseLong(FStudentID));
                student.setFpoints(studentFPoints);
                tStudentService.update(student);


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
     * 删除学生能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除学生能力信息")
    @ResponseBody
    @RequestMapping(value = "/testudentAbility", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String testudentAbility(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLoginpostID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginpostID != null && !CookiesLoginpostID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TStudentAbility tStudentAbility = tStudentAbilityService.findById(Long.parseLong(id));
            Long fstudentid = tStudentAbility.getFstudentid();
            tStudentAbilityService.deleteById(Long.parseLong(id));
            File f = new File(tStudentAbility.getFurl());
            if (f.exists()) {
                f.delete();
            }
            //查询当前学生 所拥有的能力 然后算分
            int studentFPoints = tStudentAbilityService.getStudentFPoints(fstudentid);
            //修改学生表 能力得分
            TStudent student = new TStudent();
            student.setFkeyid(tStudentAbility.getFstudentid());
            student.setFpoints(studentFPoints);
            tStudentService.update(student);
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
     * 学生能力 材料认定
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("新增学生能力材料认定")
    @ResponseBody
    @RequestMapping(value = "/upfilestudentability", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String upfilestudentability(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();

        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String studentID = request.getParameter("studentID2");
//        String FAbilityID = request.getParameter("FAbilityID");
//        String FAbilityLevelID = request.getParameter("FAbilityLevelID");
        String FConditionID = jsonParam.getString("FConditionID");

        String FDate = request.getParameter("FDate");
        String FAbilityInf = request.getParameter("FAbilityInf");
        try {
            studentID = studentID == null ? "0" : ParamTools.getdeParam(studentID);
//            FAbilityID = FAbilityID == null ? "0" : ParamTools.getdeParam(FAbilityID);
//            FAbilityLevelID = FAbilityLevelID == null ? "0" : ParamTools.getdeParam(FAbilityLevelID);
            FConditionID = FConditionID == null ? "0" : ParamTools.getdeParam(FConditionID);

            String CookiesLogintaskdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintaskdataID != null && !CookiesLogintaskdataID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLogintaskdataID);
            }
            BufferedOutputStream stream = null;
            for (MultipartFile file : uploadFiles) {
                try {
                    // 获取文件名
                    String filename = file.getOriginalFilename();
                    if (filename == null || filename.equals("")) {
                        object.put("result", "error");
                        object.put("error", "请至少选择一个文件上传");
                    } else {
                        // 获取文件的后缀名
                        String suffixName = filename.substring(filename.lastIndexOf(".") + 1);
                        //根据后缀名过滤上传文件
                        boolean istg = true;
                        if (istg == true) {

                            //
                            TStudentAbilityExample studentabilityExample = new TStudentAbilityExample();
                            TStudentAbilityExample.Criteria criteria = studentabilityExample.createCriteria();
                            criteria.andFstudentidEqualTo(Long.parseLong(studentID));
//                            criteria.andFabilityidEqualTo(Long.parseLong(FAbilityID));
//                            criteria.andFabilitylevelidEqualTo(Long.parseLong(FAbilityLevelID));
                            criteria.andFconditionidEqualTo(Long.parseLong(FConditionID));
                            List<TStudentAbility> studentAbilityList = tStudentAbilityService.findByExample(studentabilityExample);
                            if (studentAbilityList.size() > 0) {
                                // 返回值
                                object.put("result", "fail");
                            } else {
                                int FState = 1;//状态 获取
                                int FMode = 2;//材料认定
                                //生成新的文件名
                                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
                                String realFileName = idWorker.nextId() + "." + suffixName;
                                // 获取保存路径
                                String savePath = FolderTools.CheckMonthFolder(studentfj, filename, "m");
                                // 保存文件
                                stream = new BufferedOutputStream(new FileOutputStream(new File(savePath + realFileName)));
                                byte[] bytes = file.getBytes();
                                stream.write(bytes, 0, bytes.length);
                                // 定义日期格式
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                TStudentAbility tstudentability = new TStudentAbility();
                                tstudentability.setFstudentid(Long.parseLong(studentID));
                                tstudentability.setFstate(FState);
                                tstudentability.setFmode(FMode);
//                                tstudentability.setFabilityid(Long.parseLong(FAbilityID));
//                                tstudentability.setFabilitylevelid(Long.parseLong(FAbilityLevelID));
                                tstudentability.setFconditionid(Long.parseLong(FConditionID));
                                tstudentability.setFabilityinf(FAbilityInf);
                                tstudentability.setFurl(savePath + realFileName);

                                tstudentability.setFdate(dateFormat.parse(FDate));
                                tstudentability.setFcdate(new Date());
                                tstudentability.setFcid(Long.parseLong(uid));
                                tStudentAbilityService.save(tstudentability);


                                //查询当前学生 所拥有的能力 然后算分
                                int studentFPoints = tStudentAbilityService.getStudentFPoints(Long.parseLong(studentID));
                                //修改学生表 能力得分
                                TStudent student = new TStudent();
                                student.setFkeyid(Long.parseLong(studentID));
                                student.setFpoints(studentFPoints);
                                tStudentService.update(student);
                                // 返回值
                                object.put("result", "success");
                            }

                        } else {
                            object.put("result", "error");
                            object.put("error", "请上传正确的文件格式！");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    object.put("result", "error");
                    object.put("error", e.toString());
                } finally {
                    try {
                        if (stream != null) {
                            stream.close();
                        }
                    } catch (IOException e) {
                        object.put("result", "error");
                        object.put("error", e.toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 学生能力 重新上传 证书附件
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("重新上传学生能力证书附件")
    @ResponseBody
    @RequestMapping(value = "/upfilestudentabilitycx", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String upfilestudentabilitycx(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();

        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FKeyID = request.getParameter("FKeyID");

        try {
            FKeyID = FKeyID == null ? "0" : ParamTools.getdeParam(FKeyID);
            String CookiesLogintaskdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintaskdataID != null && !CookiesLogintaskdataID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLogintaskdataID);
            }
            BufferedOutputStream stream = null;
            for (MultipartFile file : uploadFiles) {
                try {
                    // 获取文件名
                    String filename = file.getOriginalFilename();
                    if (filename == null || filename.equals("")) {
                        object.put("result", "error");
                        object.put("error", "请至少选择一个文件上传");
                    } else {
                        // 获取文件的后缀名
                        String suffixName = filename.substring(filename.lastIndexOf(".") + 1);
                        //根据后缀名过滤上传文件
                        boolean istg = true;
                        if (istg == true) {

                            //生成新的文件名
                            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
                            String realFileName = idWorker.nextId() + "." + suffixName;
                            // 获取保存路径
                            String savePath = FolderTools.CheckMonthFolder(studentfj, filename, "m");
                            // 保存文件
                            stream = new BufferedOutputStream(new FileOutputStream(new File(savePath + realFileName)));
                            byte[] bytes = file.getBytes();
                            stream.write(bytes, 0, bytes.length);
                            // 定义日期格式
//                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            TStudentAbility tstudentability = new TStudentAbility();
                            tstudentability.setFkeyid(Long.parseLong(FKeyID));
                            tstudentability.setFurl(savePath + realFileName);
                            tstudentability.setFudate(new Date());
                            tstudentability.setFuid(Long.parseLong(uid));
                            tStudentAbilityService.update(tstudentability);
                            // 返回值
                            object.put("result", "success");
                        } else {
                            object.put("result", "error");
                            object.put("error", "请上传正确的文件格式！");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    object.put("result", "error");
                    object.put("error", e.toString());
                } finally {
                    try {
                        if (stream != null) {
                            stream.close();
                        }
                    } catch (IOException e) {
                        object.put("result", "error");
                        object.put("error", e.toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 学生能力附件下载
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/getstudentabilityUrl", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getstudentabilityUrl(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String ip = jsonParam.getString("ip");
        Integer type = jsonParam.getInteger("FType"); //1-服务器 2-本地
        try {
            // 查询条件
            id = id == null ? "0" : ParamTools.getdeParam(id);
            TStudentAbility tStudentAbility = tStudentAbilityService.findById(Long.parseLong(id));
            String url = "";
            if (type == 2) {
                url = tStudentAbility.getFurl().replace(studentfj.substring(0, studentfj.length() - 2), "").replace("//", "/");//本机测试

            } else {
//                String uu ="/data/uploadFiles/student_capability_evaluation/studentfj/y//2025-01//1331641203891896320.docx";
                //服务器

                url = tStudentAbility.getFurl().replace(studentfj.replace("//", "/"), ip + "/").replace("//", "/");
            }

            TStudent tStudent = tStudentService.findById(tStudentAbility.getFstudentid());
            TAbilityTree tAbility = tAbilityTreeService.findById(tStudentAbility.getFabilityid());
            TAbilityTree tAbilityLevel = tAbilityTreeService.findById(tStudentAbility.getFabilitylevelid());
            String tabilityname = tAbility == null ? "" : tAbility.getFname();
            String tAbilityLevelname = tAbilityLevel == null ? "" : tAbilityLevel.getFname();
//            System.out.println("url :" + url);
            // 返回值
            object.put("url", url);
            object.put("filename", tStudent.getFname() + "_" + tabilityname + "_" + tAbilityLevelname);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 学生能力模版下载
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/studentabilitymoban", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String studentabilitymoban(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String ip = jsonParam.getString("ip");
        String filename = jsonParam.getString("filename");
        String paramString = jsonParam.getString("filenamestr");
        try {
            //生成新的文件名
            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
            String realFileName = idWorker.nextId() + ".xlsx";
            // 目标文件路径
            String outputFilePath = studentabilitymb + "/1/2024-11/" + realFileName;
            String url = "";
            url = studentmb.replace("//", "\\");
            String templateFilePath = url + "/0/2024-11/" + filename;
            // 加载Excel模板
            FileInputStream templateFile = new FileInputStream(new File(templateFilePath));
            Workbook workbook = new XSSFWorkbook(templateFile);
            Sheet sheet = workbook.getSheetAt(0);

            // 从第二行开始插入数据
            int startRow = 1; // 第二行
            int col1 = 8; //
            int col2 = 9; //
            TAbilityLevelConditionExample tAbilityLevelConditionExample = new TAbilityLevelConditionExample();
            TAbilityLevelConditionExample.Criteria criteria = tAbilityLevelConditionExample.createCriteria();
            criteria.andFstateEqualTo(1);
            List<TAbilityLevelCondition> levelConditionList = tAbilityLevelConditionService.findByExample(tAbilityLevelConditionExample);
            if (levelConditionList.size() > 0) {
                int i = 0;
                for (TAbilityLevelCondition mb : levelConditionList) {
                    Row row = sheet.getRow(startRow + i);
                    if (row == null) {
                        row = sheet.createRow(startRow + i);
                    }
                    Cell cell1 = row.getCell(col1);
                    if (cell1 == null) {
                        cell1 = row.createCell(col1);
                    }
                    cell1.setCellValue(ParamTools.getEnParam(mb.getFkeyid().toString()));

                    Cell cell2 = row.getCell(col2);
                    if (cell2 == null) {
                        cell2 = row.createCell(col2);
                    }
                    cell2.setCellValue(mb.getFname());
                    i++;
                }
            }
//            List<StudentabilityMB> mbList = tStudentAbilityService.getStudentAbilityMB();
//            if (mbList.size() > 0) {
//                int i = 0;
//                for (StudentabilityMB mb : mbList) {
//                    Row row = sheet.getRow(startRow + i);
//                    if (row == null) {
//                        row = sheet.createRow(startRow + i);
//                    }
//                    Cell cell1 = row.getCell(col1);
//                    if (cell1 == null) {
//                        cell1 = row.createCell(col1);
//                    }
//                    cell1.setCellValue(ParamTools.getEnParam(mb.getFTLevelID().toString()));
//
//                    Cell cell2 = row.getCell(col2);
//                    if (cell2 == null) {
//                        cell2 = row.createCell(col2);
//                    }
//                    cell2.setCellValue(mb.getFTLevelName());
//                    i++;
//                }
//            }
            // 将生成的Excel写入目标文件
            try (FileOutputStream outputStream = new FileOutputStream(new File(outputFilePath))) {
                workbook.write(outputStream);
            } catch (IllegalArgumentException e) {
                // 捕获并处理IllegalArgumentException异常，但不打印堆栈跟踪
                System.err.println("捕获到IllegalArgumentException异常: " + e.getMessage());
            } finally {
                workbook.close();
                templateFile.close();
            }

            String outurl = "http://" + studentabilitymb.replace(studentabilitymb, ip).replace("//", "\\") + "/1/2024-11/" + realFileName;
//            System.out.println(outurl);
            object.put("url", outurl);
            object.put("filename", paramString);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 模板导入
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/studentabilityupmoban", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String studentabilityupmoban(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
            throws Exception {
        JSONObject object = new JSONObject();
        BufferedOutputStream stream = null;
        String CookiesLogindesignpurchaseapplyID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
        String uid = ""; // 当前登录用户ID
        if (CookiesLogindesignpurchaseapplyID != null && !CookiesLogindesignpurchaseapplyID.equals("")) {
            uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
        }

        for (MultipartFile file : uploadFiles) {
            try {
                // 获取文件名
                String filename = file.getOriginalFilename();
                if (filename == null || filename.equals("")) {
                    object.put("result", "error");
                    object.put("error", "请选择文件后再进行上传！");
                } else {
                    JSONArray student_Array = new JSONArray();
                    // 获取文件的后缀名
                    String suffixName = filename.substring(filename.lastIndexOf(".") + 1);
                    //生成新的文件名
                    SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
                    String realFileName = idWorker.nextId() + "." + suffixName;

                    //读取excel
                    // 2.创建工作区workbook
                    XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());

                    // 3.获取表sheet,这里sheet0代表获取下表为0的excel表,也就是第一个表
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    boolean iserror = false;
                    if (suffixName.equals("xlsx")) {
                        String FNo = "";
                        String FTabName = "";//改存能力等级ID
                        String FlevelName = "";

                        String FNote = "";


                        // 4.获取数据
                        // getLastRowNum() 获取一张sheet表中行的数量
                        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                            // 获取第i行的数据
                            XSSFRow row = sheet.getRow(i);
                            FNo = "";
                            FTabName = "";
                            FlevelName = "";

                            FNote = "";
                            for (int j = 0; j < row.getLastCellNum(); j++) {

                                if (j > 3) {
                                    break;
                                }
                                // 获取第i行第j列的单元格数据
                                String cell = null;
                                try {
                                    cell = row.getCell(j).toString().trim() == null ? row.createCell(j).toString() : row.getCell(j).toString().trim();
                                } catch (Exception e) {
                                    continue;
                                }

                                if (cell == null && cell.equals("")) {
                                    break;
                                }
                                if (i == 0 && j == 0 && !cell.contains("*学生学号")) {
                                    iserror = true;
                                    break;
                                }
                                if (iserror == true) {
                                    break;
                                } else {

                                    if (i == 0) {
                                        break;
                                    }
                                    switch (j) {
                                        case 0:
                                            FNo = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 1:
                                            FTabName = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
//                                        case 2:
//                                            FlevelName = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
//                                            break;
                                        case 2:
                                            FNote = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                    }

                                }
                                // 5.打印单元格数据
//                            System.out.print(cell + " | ");
                            }

                            JSONObject student_object = new JSONObject();
                            String error = "";
                            if (i > 0) {
                                String FName = "";
                                Long ftabid = 0l;
                                Long ftablevelid = 0l;
                                Long fstudentid = 0l;
                                if (FNo.equals("") && FTabName.equals("")) {
                                    break;
                                }
                                if (FNo.equals(""))
                                    error += "学生学号未填写" + ";";
                                if (FTabName.equals(""))
                                    error += "能力条件未填写" + ";";
//                                if (FlevelName.equals(""))
//                                    error += "能力等级未填写" + ";";

                                TStudentExample tStudentExample = new TStudentExample();
                                TStudentExample.Criteria criteria = tStudentExample.createCriteria();
                                criteria.andFnoEqualTo(FNo);
                                criteria.andFstateEqualTo(1);
                                List<TStudent> tStudentList = tStudentService.findByExample(tStudentExample);
                                if (tStudentList.size() == 0) {
                                    error += "学生【" + FNo + "】不存在!;";
                                } else {
                                    fstudentid = tStudentList.get(0).getFkeyid();
                                    FName = tStudentList.get(0).getFname();
                                }
                                FTabName = ParamTools.getdeParam(FTabName);
//                                TAbilityTree abilityLevel = tAbilityTreeService.findById(Long.parseLong(FTabName));
//                                if (abilityLevel == null) {
//                                    error += "能力【" + ParamTools.getEnParam(FTabName) + "】不存在!;";
//                                } else {
//                                    ftabid = abilityLevel.getFkeyid();
//                                }
                                TAbilityLevelCondition conditionServiceById = tAbilityLevelConditionService.findById(Long.parseLong(FTabName));
                                if (conditionServiceById == null) {
                                    error += "能力条件【" + ParamTools.getEnParam(FTabName) + "】不存在!;";
                                } else {
                                    ftabid = conditionServiceById.getFkeyid();
                                }




//                                tAbilityTreeExample = new TAbilityTreeExample();
//                                tAbilityTreeExample.createCriteria().andFdelEqualTo(1).andFstateEqualTo(1).andFnodetypeEqualTo(2).andFnameEqualTo(FlevelName);
////                                TAbilityLevelExample tAbilityLevelExample = new TAbilityLevelExample();
////                                TAbilityLevelExample.Criteria criteria3 = tAbilityLevelExample.createCriteria();
////                                criteria3.andFnameEqualTo(FlevelName);
////                                criteria3.andFstateEqualTo(1);
////                                criteria3.andFabilityidEqualTo(ftabid);
////                                List<TAbilityLevel> abilityLevelList = tAbilityLevelService.findByExample(tAbilityLevelExample);
//                                List<TAbilityTree> abilityLevelList = tAbilityTreeService.findByExample(tAbilityTreeExample);
//                                if(abilityLevelList.size() == 0){
//                                    error += "能力等级【" + FTabName + "】不存在!;";
//                                }else{
//                                    ftablevelid = abilityLevelList.get(0).getFkeyid();
//                                }

                                if (!error.equals("")) {
                                    student_object.put("FName", FName == null ? "未填写" : FName);
                                    student_object.put("error", error);
                                    student_Array.add(student_object);
                                    continue;
                                } else {
                                    TStudentAbilityExample studentabilityExample = new TStudentAbilityExample();
                                    TStudentAbilityExample.Criteria criteria4 = studentabilityExample.createCriteria();
                                    criteria4.andFstudentidEqualTo(fstudentid);
                                    criteria4.andFconditionidEqualTo(conditionServiceById.getFkeyid());
//                                    criteria4.andFabilityidEqualTo(abilityLevel.getFpid());
//                                    criteria4.andFabilitylevelidEqualTo(abilityLevel.getFkeyid());
                                    List<TStudentAbility> studentAbilityList = tStudentAbilityService.findByExample(studentabilityExample);
                                    if (studentAbilityList.size() == 0) {
                                        // 定义日期格式
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                        TStudentAbility tstudentability = new TStudentAbility();
                                        tstudentability.setFstudentid(fstudentid);
                                        tstudentability.setFstate(1);
                                        tstudentability.setFmode(3);
                                        tstudentability.setFconditionid(conditionServiceById.getFkeyid());
//                                        tstudentability.setFabilityid(abilityLevel.getFpid());
//                                        tstudentability.setFabilitylevelid(abilityLevel.getFkeyid());
                                        tstudentability.setFabilityinf(FNote);
                                        tstudentability.setFurl("");
                                        tstudentability.setFdate(dateFormat.parse(dateFormat.format(new Date())));
                                        tstudentability.setFcdate(new Date());
                                        tstudentability.setFcid(Long.parseLong(uid));
                                        tStudentAbilityService.save(tstudentability);

                                        //查询当前学生 所拥有的能力 然后算分
                                        int studentFPoints = tStudentAbilityService.getStudentFPoints(fstudentid);
                                        //修改学生表 能力得分
                                        TStudent student = new TStudent();
                                        student.setFkeyid(fstudentid);
                                        student.setFpoints(studentFPoints);
                                        tStudentService.update(student);
                                    } else {
                                        error = "学生：" + FName + ",能力【" + ParamTools.getEnParam(FTabName) + "】重复；";
                                        student_object.put("FName", FName == null ? "未填写" : FName);
                                        student_object.put("error", error);
                                        student_Array.add(student_object);
                                    }
                                }
                            }
                        }
                    } else {
                        iserror = true;
                    }

                    if (iserror == true) {
                        object.put("result", "error");
                        object.put("error", "请选择正确的模板进行导入！");
                    } else {
                        // 返回值
                        object.put("studenterror", student_Array);
                        object.put("result", "success");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                object.put("result", "error");
                object.put("error", e.toString());
            }
        }
        return object.toString();
    }


}