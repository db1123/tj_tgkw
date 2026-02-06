package fun.server.controller.student;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.model.customQuery.classStudent.ClassStudentCS;
import fun.server.model.customQuery.classStudent.ClassStudentData;
import fun.server.model.customQuery.major.TCLassData;
import fun.server.model.customQuery.major.TClassCS;
import fun.server.model.customQuery.studentability.StudentabilityLevelMax;
import fun.server.service.*;
import fun.tools.ParamTools;
import fun.tools.SnowflakeIdWorker;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 学生管理 相关业务处理
 */
@RestController
@RequestMapping("/s/tstudent")
public class Student {

    private final TStudentService tStudentService;
    private final TRoleService roleService;
    private final TUserService tUserService;
    private final TPowerRoleUserService powerRoleUserService;

    private final TAbilityService tAbilityService;
    private final TAbilityTypeService tAbilityTypeService;
    private final TAbilityLevelService tAbilityLevelService;

    private final TStudentAbilityService tStudentAbilityService;

    private final TClassStudentService tClassStudentService;

    private final TMajorService tMajorService;

    private final TAbilityTreeService tAbilityTreeService;
    private final StudentAbility studentAbility;

    public Student(TStudentService tStudentService, TRoleService roleService, TUserService tUserService, TPowerRoleUserService powerRoleUserService, TAbilityService tAbilityService, TAbilityTypeService tAbilityTypeService, TAbilityLevelService tAbilityLevelService, TStudentAbilityService tStudentAbilityService, TClassStudentService tClassStudentService, TMajorService tMajorService, TAbilityTreeService tAbilityTreeService, StudentAbility studentAbility) {
        this.tStudentService = tStudentService;
        this.roleService = roleService;
        this.tUserService = tUserService;
        this.powerRoleUserService = powerRoleUserService;
        this.tAbilityService = tAbilityService;
        this.tAbilityTypeService = tAbilityTypeService;
        this.tAbilityLevelService = tAbilityLevelService;
        this.tStudentAbilityService = tStudentAbilityService;
        this.tClassStudentService = tClassStudentService;
        this.tMajorService = tMajorService;
        this.tAbilityTreeService = tAbilityTreeService;
        this.studentAbility = studentAbility;
    }


    @Value("${studentmb.path}")
    private String studentmb;

    @Value("${studentfj.path}")
    private String studentfj;

    /**
     * 获取学生信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytstudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytstudent(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        String fno = jsonParam.getString("fno");
        String ftel = jsonParam.getString("ftel");
        String fsex = jsonParam.getString("fsex");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray tstudent_Array = new JSONArray();
            // 查询条件
            TStudentExample TStudentExample = new TStudentExample();
            TStudentExample.Criteria criteria = TStudentExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }
            if (fno != null && !fno.equals("")) {
                criteria.andFnoLike("%" + fno + "%");
            }
            if (ftel != null && !ftel.equals("")) {
                criteria.andFtelLike("%" + ftel + "%");
            }

            if (fsex != null && !fsex.equals("") && !fsex.equals("-1")) {
                criteria.andFsexEqualTo(Integer.parseInt(fsex));
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
                TStudentExample.setOrderByClause(orderSql.substring(1));
            } else {
                TStudentExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TStudent> tstudentPageInfo = tStudentService.findByExampleMapper(TStudentExample, (page - 1) * results, results);
            List<TStudent> tstudent_list = tstudentPageInfo.getList();

            for (TStudent tstudent : tstudent_list) {
                JSONObject tstudent_object = new JSONObject();
                tstudent_object.put("key", ParamTools.getEnParam(tstudent.getFkeyid().toString()));
                tstudent_object.put("FUserID", ParamTools.getEnParam(tstudent.getFuserid().toString()));
                if (dataall == 1) {
                    tstudent_object.put("FName", tstudent.getFname() == null ? "" : tstudent.getFname());
                    tstudent_object.put("FMode", tstudent.getFmode());
                    tstudent_object.put("FNo", tstudent.getFno() == null ? "" : tstudent.getFno());
                    tstudent_object.put("FSex", tstudent.getFsex());
                    tstudent_object.put("FBirthday", tstudent.getFbirthday() == null ? "" : tstudent.getFbirthday());
                    tstudent_object.put("FIDNumber", tstudent.getFidnumber() == null ? "" : tstudent.getFidnumber().substring(0, 3) + "****************");
                    tstudent_object.put("FTel", tstudent.getFtel() == null ? "" : tstudent.getFtel());
                    tstudent_object.put("FEmail", tstudent.getFemail() == null ? "" : tstudent.getFemail());
                    tstudent_object.put("FStartSchoolDate", tstudent.getFstartschooldate() == null ? "" : tstudent.getFstartschooldate());
                    tstudent_object.put("FEndSchoolDate", tstudent.getFendschooldate() == null ? "" : tstudent.getFendschooldate());
                    tstudent_object.put("FMajor", tstudent.getFmajor() == null ? "" : tstudent.getFmajor());
                    tstudent_object.put("FEducation", tstudent.getFeducation() == null ? "" : tstudent.getFeducation());
                    tstudent_object.put("FPolitical", tstudent.getFpolitical() == null ? "" : tstudent.getFpolitical());
                    tstudent_object.put("FAddr", tstudent.getFaddr() == null ? "" : tstudent.getFaddr());
                    tstudent_object.put("FHonor", tstudent.getFhonor() == null ? "" : tstudent.getFhonor());
                    tstudent_object.put("FPunish", tstudent.getFpunish() == null ? "" : tstudent.getFpunish());
                    tstudent_object.put("FHealth", tstudent.getFhealth() == null ? "" : tstudent.getFhealth());
                    tstudent_object.put("FNote", tstudent.getFnote() == null ? "" : tstudent.getFnote());
                    tstudent_object.put("FPoints", tstudent.getFpoints() == null ? "" : tstudent.getFpoints());
                    tstudent_object.put("FWorkState", tstudent.getFworkstate() == null ? "" : tstudent.getFworkstate());
                    tstudent_object.put("FGradeLevel", tstudent.getFgradelevel() == null ? 0 : tstudent.getFgradelevel());

                    TClassCS tClassCS = new TClassCS();
                    tClassCS.setFStudentID(tstudent.getFkeyid());
                    List<TCLassData> selectedClassList = tMajorService.selectClassInfo(tClassCS);
                    if (selectedClassList.size() > 0) {
                        String classname = "";

                        for (TCLassData classData : selectedClassList) {
                            classname += " 【" + classData.getFClassName() + "】，";
                        }
                        if (classname.length() > 0) {
                            classname = classname.substring(0, classname.length() - 1);
                        }
                        tstudent_object.put("FClassName", classname);
                    } else {
                        tstudent_object.put("FClassName", "");
                    }
                    tstudent_object.put("FCID", tstudent.getFcid());
                    tstudent_object.put("FUID", tstudent.getFuid());
                    tstudent_object.put("FCDATE", tstudent.getFcdate());
                    tstudent_object.put("FUDATE", tstudent.getFudate());
                } else {
                    tstudent_object.put("FClassName", "*****");
                    tstudent_object.put("FClassID", ParamTools.getEnParam("-1"));
                    tstudent_object.put("FName", "*****");
                    tstudent_object.put("FMode", "*****");
                    tstudent_object.put("FNo", "*****");
                    tstudent_object.put("FSex", "*****");
                    tstudent_object.put("FBirthday", "*****");
                    tstudent_object.put("FIDNumber", "*****");
                    tstudent_object.put("FTel", "*****");
                    tstudent_object.put("FEmail", "*****");
                    tstudent_object.put("FStartSchoolDate", "*****");
                    tstudent_object.put("FEndSchoolDate", "*****");
                    tstudent_object.put("FMajor", "*****");
                    tstudent_object.put("FEducation", "*****");
                    tstudent_object.put("FPolitical", "*****");
                    tstudent_object.put("FAddr", "*****");
                    tstudent_object.put("FHonor", "*****");
                    tstudent_object.put("FPunish", "*****");
                    tstudent_object.put("FHealth", "*****");
                    tstudent_object.put("FNote", "*****");
                    tstudent_object.put("FPoints", "*****");
                    tstudent_object.put("FWorkState", "*****");
                    tstudent_object.put("FGradeLevel", "*****");
                    tstudent_object.put("FClassName", "*****");
                    tstudent_object.put("FCID", "*****");
                    tstudent_object.put("FUID", "*****");
                    tstudent_object.put("FCDATE", "*****");
                    tstudent_object.put("FUDATE", "*****");
                }

                tstudent_object.put("FState", tstudent.getFstate());
                tstudent_Array.add(tstudent_object);
            }
            // 返回值
            object.put("list", tstudent_Array);
            object.put("total", tstudentPageInfo.getTotal()); // 总行数
            object.put("page", tstudentPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取学生信息_多条件_班级
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytstudent_tj", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytstudent_tj(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("FName");
        String FNo = jsonParam.getString("FNo");
        String FSex = jsonParam.getString("FSex");
        String FIDNumber = jsonParam.getString("FIDNumber");
        String FTel = jsonParam.getString("FTel");
        String classID = jsonParam.getString("classID");

        try {
            // 获取数据库记录
            JSONArray tstudent_Array = new JSONArray();
            // 查询条件
            ClassStudentCS ClassStudentCS = new ClassStudentCS();

            if (name != null && !name.equals("")) {
                ClassStudentCS.setFName(name);
            }
            if (FNo != null && !FNo.equals("")) {
                ClassStudentCS.setFNo(FNo);
            }
            if (FSex != null && !FSex.equals("") && !FSex.equals("-1")) {
                ClassStudentCS.setFSex(Integer.parseInt(FSex));
            }
            if (FIDNumber != null && !FIDNumber.equals("")) {
                ClassStudentCS.setFIDNumber(FIDNumber);
            }

            if (FTel != null && !FTel.equals("")) {
                ClassStudentCS.setFTel(FTel);
            }
            classID = classID == null ? "0" : ParamTools.getdeParam(classID);
            ClassStudentCS.setCLassID(Long.parseLong(classID));
//            TStudentExample.setOrderByClause("FCDATE desc");
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                ClassStudentCS.setOrderBy(orderSql.substring(1));
            } else {
                ClassStudentCS.setOrderBy("FName desc");
            }
            PageInfo<ClassStudentData> tstudentPageInfo = tClassStudentService.getCLassStudentlist(ClassStudentCS, (page - 1) * results, results);
            List<ClassStudentData> tstudent_list = tstudentPageInfo.getList();

            for (ClassStudentData tstudent : tstudent_list) {
                JSONObject tstudent_object = new JSONObject();
                tstudent_object.put("key", ParamTools.getEnParam(tstudent.getFKeyID().toString()));
                tstudent_object.put("FName", tstudent.getFName());
                tstudent_object.put("FNo", tstudent.getFNo());
                tstudent_object.put("FSex", tstudent.getFSex());
                tstudent_object.put("FIDNumber", tstudent.getFIDNumber());
                tstudent_object.put("FTel", tstudent.getFTel());
                tstudent_Array.add(tstudent_object);
            }
            // 返回值
            object.put("list", tstudent_Array);
            object.put("total", tstudentPageInfo.getTotal()); // 总行数
            object.put("page", tstudentPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取学生信息_多条件_课程
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytstudent_tjkc", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytstudent_tjkc(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("FName");
        String FNo = jsonParam.getString("FNo");
        String FSex = jsonParam.getString("FSex");
        String FIDNumber = jsonParam.getString("FIDNumber");
        String FTel = jsonParam.getString("FTel");
        String courseID = jsonParam.getString("courseID");

        try {
            // 获取数据库记录
            JSONArray tstudent_Array = new JSONArray();
            // 查询条件
            ClassStudentCS ClassStudentCS = new ClassStudentCS();

            if (name != null && !name.equals("")) {
                ClassStudentCS.setFName(name);
            }
            if (FNo != null && !FNo.equals("")) {
                ClassStudentCS.setFNo(FNo);
            }
            if (FSex != null && !FSex.equals("") && !FSex.equals("-1")) {
                ClassStudentCS.setFSex(Integer.parseInt(FSex));
            }
            if (FIDNumber != null && !FIDNumber.equals("")) {
                ClassStudentCS.setFIDNumber(FIDNumber);
            }

            if (FTel != null && !FTel.equals("")) {
                ClassStudentCS.setFTel(FTel);
            }
            courseID = courseID == null ? "0" : ParamTools.getdeParam(courseID);
            ClassStudentCS.setCLassID(Long.parseLong(courseID));
//            TStudentExample.setOrderByClause("FCDATE desc");
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                ClassStudentCS.setOrderBy(orderSql.substring(1));
            } else {
                ClassStudentCS.setOrderBy("FName desc");
            }
            PageInfo<ClassStudentData> tstudentPageInfo = tClassStudentService.getCourseStudentlist(ClassStudentCS, (page - 1) * results, results);
            List<ClassStudentData> tstudent_list = tstudentPageInfo.getList();

            for (ClassStudentData tstudent : tstudent_list) {
                JSONObject tstudent_object = new JSONObject();
                tstudent_object.put("key", ParamTools.getEnParam(tstudent.getFKeyID().toString()));
                tstudent_object.put("FName", tstudent.getFName());
                tstudent_object.put("FNo", tstudent.getFNo());
                tstudent_object.put("FSex", tstudent.getFSex());
                tstudent_object.put("FIDNumber", tstudent.getFIDNumber());
                tstudent_object.put("FTel", tstudent.getFTel());
                tstudent_Array.add(tstudent_object);
            }
            // 返回值
            object.put("list", tstudent_Array);
            object.put("total", tstudentPageInfo.getTotal()); // 总行数
            object.put("page", tstudentPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取学生信息_多条件_学生能力
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytstudent_studentability", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytstudent_studentability(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("FName");
        String FNo = jsonParam.getString("FNo");
        String FSex = jsonParam.getString("FSex");
        String FIDNumber = jsonParam.getString("FIDNumber");
        String FTel = jsonParam.getString("FTel");
        String FAbilityID = jsonParam.getString("FAbilityID");
        String FAbilityLevelID = jsonParam.getString("FAbilityLevelID");
        String FConditionID = jsonParam.getString("FConditionID");

        try {
            // 获取数据库记录
            JSONArray tstudent_Array = new JSONArray();
            // 查询条件
            ClassStudentCS ClassStudentCS = new ClassStudentCS();

            if (name != null && !name.equals("")) {
                ClassStudentCS.setFName(name);
            }
            if (FNo != null && !FNo.equals("")) {
                ClassStudentCS.setFNo(FNo);
            }
            if (FSex != null && !FSex.equals("") && !FSex.equals("-1")) {
                ClassStudentCS.setFSex(Integer.parseInt(FSex));
            }
            if (FIDNumber != null && !FIDNumber.equals("")) {
                ClassStudentCS.setFIDNumber(FIDNumber);
            }

            if (FTel != null && !FTel.equals("")) {
                ClassStudentCS.setFTel(FTel);
            }

            if (FAbilityID != null && !FAbilityID.equals("") && !FAbilityID.equals("0")) {
                FAbilityID = ParamTools.getdeParam(FAbilityID);
            } else {
                FAbilityID = "0";
            }
            if (FAbilityLevelID != null && !FAbilityLevelID.equals("") && !FAbilityLevelID.equals("0")) {
                FAbilityLevelID = ParamTools.getdeParam(FAbilityLevelID);
            } else {
                FAbilityLevelID = "0";
            }
            FConditionID = FConditionID == null ? "0" : ParamTools.getdeParam(FConditionID);
            ClassStudentCS.setFAbilityID(Long.parseLong(FAbilityID));
            ClassStudentCS.setFAbilityLevelID(Long.parseLong(FAbilityLevelID));
            ClassStudentCS.setFConditionID(Long.parseLong(FConditionID));
//            TStudentExample.setOrderByClause("FCDATE desc");
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                ClassStudentCS.setOrderBy(orderSql.substring(1));
            } else {
                ClassStudentCS.setOrderBy("FName desc");
            }

//            System.out.println(FAbilityID);
//            System.out.println(FAbilityLevelID);
            PageInfo<ClassStudentData> tstudentPageInfo = tClassStudentService.getStudentabilitylist(ClassStudentCS, (page - 1) * results, results);
            List<ClassStudentData> tstudent_list = tstudentPageInfo.getList();

            for (ClassStudentData tstudent : tstudent_list) {
                JSONObject tstudent_object = new JSONObject();
                tstudent_object.put("key", ParamTools.getEnParam(tstudent.getFKeyID().toString()));
                tstudent_object.put("FName", tstudent.getFName());
                tstudent_object.put("FNo", tstudent.getFNo());
                tstudent_object.put("FSex", tstudent.getFSex());
                tstudent_object.put("FIDNumber", tstudent.getFIDNumber());
                tstudent_object.put("FTel", tstudent.getFTel());
                tstudent_Array.add(tstudent_object);
            }
            // 返回值
            object.put("list", tstudent_Array);
            object.put("total", tstudentPageInfo.getTotal()); // 总行数
            object.put("page", tstudentPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取学生信息_多条件_学生能力
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytstudent_courseenrollment", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytstudent_courseenrollment(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("FName");
        String FNo = jsonParam.getString("FNo");
        String FSex = jsonParam.getString("FSex");
        String FIDNumber = jsonParam.getString("FIDNumber");
        String FTel = jsonParam.getString("FTel");
        String courseID = jsonParam.getString("courseID");


        try {
            // 获取数据库记录
            JSONArray tstudent_Array = new JSONArray();
            // 查询条件
            ClassStudentCS ClassStudentCS = new ClassStudentCS();

            if (name != null && !name.equals("")) {
                ClassStudentCS.setFName(name);
            }
            if (FNo != null && !FNo.equals("")) {
                ClassStudentCS.setFNo(FNo);
            }
            if (FSex != null && !FSex.equals("") && !FSex.equals("-1")) {
                ClassStudentCS.setFSex(Integer.parseInt(FSex));
            }
            if (FIDNumber != null && !FIDNumber.equals("")) {
                ClassStudentCS.setFIDNumber(FIDNumber);
            }

            if (FTel != null && !FTel.equals("")) {
                ClassStudentCS.setFTel(FTel);
            }
            courseID = courseID == null ? "0" : ParamTools.getdeParam(courseID);

            ClassStudentCS.setFAbilityID(Long.parseLong(courseID));//开课申请ID

//            TStudentExample.setOrderByClause("FCDATE desc");
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                ClassStudentCS.setOrderBy(orderSql.substring(1));
            } else {
                ClassStudentCS.setOrderBy("FName desc");
            }

//            System.out.println(FAbilityID);
//            System.out.println(FAbilityLevelID);
            PageInfo<ClassStudentData> tstudentPageInfo = tClassStudentService.getStudentenrollmentlist(ClassStudentCS, (page - 1) * results, results);
            List<ClassStudentData> tstudent_list = tstudentPageInfo.getList();

            for (ClassStudentData tstudent : tstudent_list) {
                JSONObject tstudent_object = new JSONObject();
                tstudent_object.put("key", ParamTools.getEnParam(tstudent.getFKeyID().toString()));
                tstudent_object.put("FName", tstudent.getFName());
                tstudent_object.put("FNo", tstudent.getFNo());
                tstudent_object.put("FSex", tstudent.getFSex());
                tstudent_object.put("FIDNumber", tstudent.getFIDNumber());
                tstudent_object.put("FTel", tstudent.getFTel());
                tstudent_Array.add(tstudent_object);
            }
            // 返回值
            object.put("list", tstudent_Array);
            object.put("total", tstudentPageInfo.getTotal()); // 总行数
            object.put("page", tstudentPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取学生信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatatstudentSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatatstudentSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray tstudent_Array = new JSONArray();
            TStudentExample tstudentExample = new TStudentExample();
            TStudentExample.Criteria criteria = tstudentExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            tstudentExample.setOrderByClause("FName asc");
            List<TStudent> tstudent_list = tStudentService.findByExample(tstudentExample);
            for (TStudent tstudent : tstudent_list) {
                JSONObject tstudent_object = new JSONObject();
                tstudent_object.put("id", ParamTools.getEnParam(tstudent.getFkeyid().toString()));
                tstudent_object.put("text", tstudent.getFname());
                tstudent_Array.add(tstudent_object);
            }
            // 返回值
            object.put("list", tstudent_Array);
            object.put("results", tstudent_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 根据ID获取学生信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytstudentInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytstudentInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询学生信息
            TStudent tstudent = tStudentService.findById(key);
            JSONObject tstudent_object = new JSONObject();
            tstudent_object.put("key", ParamTools.getEnParam(tstudent.getFkeyid().toString()));
            tstudent_object.put("FName", tstudent.getFname() == null ? "" : tstudent.getFname());
            tstudent_object.put("FMode", tstudent.getFmode());
            tstudent_object.put("FNo", tstudent.getFno() == null ? "" : tstudent.getFno());
            tstudent_object.put("FSex", tstudent.getFsex());
            tstudent_object.put("FBirthday", tstudent.getFbirthday() == null ? "" : tstudent.getFbirthday());
            tstudent_object.put("FIDNumber", tstudent.getFidnumber() == null ? "" : tstudent.getFidnumber());
            tstudent_object.put("FTel", tstudent.getFtel() == null ? "" : tstudent.getFtel());
            tstudent_object.put("FEmail", tstudent.getFemail() == null ? "" : tstudent.getFemail());
            tstudent_object.put("FStartSchoolDate", tstudent.getFstartschooldate() == null ? "" : tstudent.getFstartschooldate());
            tstudent_object.put("FEndSchoolDate", tstudent.getFendschooldate() == null ? "" : tstudent.getFendschooldate());
            tstudent_object.put("FMajor", tstudent.getFmajor() == null ? "" : tstudent.getFmajor());
            tstudent_object.put("FEducation", tstudent.getFeducation() == null ? "" : tstudent.getFeducation());
            tstudent_object.put("FPolitical", tstudent.getFpolitical() == null ? "" : tstudent.getFpolitical());
            tstudent_object.put("FAddr", tstudent.getFaddr() == null ? "" : tstudent.getFaddr());
            tstudent_object.put("FHonor", tstudent.getFhonor() == null ? "" : tstudent.getFhonor());
            tstudent_object.put("FPunish", tstudent.getFpunish() == null ? "" : tstudent.getFpunish());
            tstudent_object.put("FHealth", tstudent.getFhealth() == null ? "" : tstudent.getFhealth());
            tstudent_object.put("FNote", tstudent.getFnote() == null ? "" : tstudent.getFnote());
            tstudent_object.put("FPoints", tstudent.getFpoints() == null ? "" : tstudent.getFpoints());
            tstudent_object.put("FWorkState", tstudent.getFworkstate() == null ? "" : tstudent.getFworkstate());
            tstudent_object.put("FUserID", ParamTools.getEnParam(tstudent.getFuserid().toString()));
            tstudent_object.put("FGradeLevel", tstudent.getFgradelevel() == null ? 0 : tstudent.getFgradelevel());
            tstudent_object.put("FClassName", tstudent.getFclassname() == null ? "" : tstudent.getFclassname());
            TClassCS tClassCS = new TClassCS();
            tClassCS.setFStudentID(tstudent.getFkeyid());
            List<TCLassData> selectedClassList = tMajorService.selectClassInfo(tClassCS);
            if (selectedClassList.size() > 0) {
                String classname = "";

                for (TCLassData classData : selectedClassList) {
                    classname += "【" + classData.getFClassName() + "】，";
                }
                if (classname.length() > 0) {
                    classname = classname.substring(0, classname.length() - 1);
                }
                tstudent_object.put("FClassName", classname);
//                tstudent_object.put("FClassID", ParamTools.getEnParam(selectedClassInfo.getFClassId().toString()));
            } else {
                tstudent_object.put("FClassName", "");
//                tstudent_object.put("FClassID", ParamTools.getEnParam("-1"));
            }
            tstudent_object.put("FState", tstudent.getFstate());
            tstudent_object.put("FCID", tstudent.getFcid());
            tstudent_object.put("FUID", tstudent.getFuid());
            tstudent_object.put("FCDATE", tstudent.getFcdate());
            tstudent_object.put("FUDATE", tstudent.getFudate());
            tstudent_object.put("FState", tstudent.getFstate());

            JSONArray role_Array = new JSONArray();
            TPowerRoleUserExample powerRoleUserExample = new TPowerRoleUserExample();
            powerRoleUserExample.or().andFUserIdEqualTo(tstudent.getFuserid());
            List<TPowerRoleUser> list = powerRoleUserService.findByExample(powerRoleUserExample);
            for (TPowerRoleUser powerRoleUser : list) {
                TRole role = roleService.findById(powerRoleUser.getfRoleId());
                if (role != null) {
                    JSONObject roleObject = new JSONObject();
                    roleObject.put("id", ParamTools.getEnParam(role.getfKeyId().toString()));
                    roleObject.put("name", role.getfName());
                    role_Array.add(roleObject);
                }
            }
            tstudent_object.put("roles", role_Array);
            // 返回值
            object.put("info", tstudent_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 查询联系电话是否存在
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/getlxdh", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getlxdh(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FTel = jsonParam.getString("FTel");
        int ftype = jsonParam.getInteger("ftype");
        String fkeyid = jsonParam.getString("fkeyid");
        try {
            if (ftype == 2) {
                fkeyid = fkeyid == null ? "" : ParamTools.getdeParam(fkeyid);
            }
            TStudentExample tStudentExample = new TStudentExample();
            TStudentExample.Criteria criteria = tStudentExample.createCriteria();
            criteria.andFtelEqualTo(FTel);
            if (ftype == 2) {
                criteria.andFkeyidNotEqualTo(Long.parseLong(fkeyid));
            }
            List<TStudent> tStudentList = tStudentService.findByExample(tStudentExample);
            if (tStudentList.size() > 0) {
                object.put("iscz", true);
            } else {
                object.put("iscz", false);
            }
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加学生信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加学生信息")
    @ResponseBody
    @RequestMapping(value = "/addtstudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addtstudent(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FName = jsonParam.getString("FName"); //姓名
        String FNo = jsonParam.getString("FNo");//学号
        String FSex = jsonParam.getString("FSex");//性别
        String FBirthday = jsonParam.getString("FBirthday");//出生日期
        String FIDNumber = jsonParam.getString("FIDNumber");//身份证号
        String FTel = jsonParam.getString("FTel");//联系电话
        String FEmail = jsonParam.getString("FEmail");//电子邮箱
        String FStartSchoolDate = jsonParam.getString("FStartSchoolDate");//入学时间
        String FEndSchoolDate = jsonParam.getString("FEndSchoolDate");//毕业时间
        String FMajor = jsonParam.getString("FMajor");//专业
        String FEducation = jsonParam.getString("FEducation");//学历
        String FPolitical = jsonParam.getString("FPolitical");//政治面目
        String FAddr = jsonParam.getString("FAddr");//住址
        String FHonor = jsonParam.getString("FHonor");//奖励荣誉
        String FPunish = jsonParam.getString("FPunish");//惩罚记录
        String FHealth = jsonParam.getString("FHealth");//健康记录
        String FNote = jsonParam.getString("FNote");//备注
        String FWorkState = jsonParam.getString("FWorkState");//工作状态
//        int FGradeLevel = jsonParam.getInteger("FGradeLevel");//当前年级
//        String FClassName = jsonParam.getString("FClassName");//班级名称
        String roles = jsonParam.getString("roles");
        String FClassID = jsonParam.getString("FClassID");

        try {

            TStudentExample tstudentExample = new TStudentExample();
            TStudentExample.Criteria criteria = tstudentExample.createCriteria();
//            criteria.andFnameEqualTo(FName);
            criteria.andFtelEqualTo(FTel);
            criteria.andFidnumberEqualTo(FIDNumber);
            List<TStudent> studentList = tStudentService.findByExample(tstudentExample);
            if (studentList.size() == 0) {
                String CookiesLogintstudentID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintstudentID != null && !CookiesLogintstudentID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintstudentID);
                }

                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
                long key = idWorker.nextId();
                long stuednetkey = idWorker.nextId();
                // 新建学生信息
                TStudent tstudent = new TStudent();
                tstudent.setFkeyid(stuednetkey);
                tstudent.setFname(FName);
                tstudent.setFno(FNo);
                tstudent.setFsex(Integer.valueOf(FSex));
                tstudent.setFbirthday(FBirthday);
                tstudent.setFidnumber(FIDNumber);
                tstudent.setFtel(FTel);
                tstudent.setFemail(FEmail);
                tstudent.setFstartschooldate(FStartSchoolDate);
                tstudent.setFendschooldate(FEndSchoolDate);
                tstudent.setFmajor(FMajor);
                tstudent.setFeducation(FEducation);
                tstudent.setFpolitical(FPolitical);
                tstudent.setFaddr(FAddr);
                tstudent.setFhonor(FHonor);
                tstudent.setFpunish(FPunish);
                tstudent.setFhealth(FHealth);
                tstudent.setFnote(FNote);
                tstudent.setFworkstate(Integer.valueOf(FWorkState));
                tstudent.setFcid(Long.parseLong(uid));
                tstudent.setFcdate(new Date());
                tstudent.setFuserid(key);
//                tstudent.setFgradelevel(FGradeLevel);
//                tstudent.setFclassname(FClassName);
                tStudentService.save(tstudent);


                //用户表添加信息
                TUser tuser = new TUser();
                tuser.setfKeyId(key);
                tuser.setfLogin(FTel);
                tuser.setfPass(ParamTools.getEnParam(FIDNumber.substring(FIDNumber.length() - 6)));
                tuser.setfTel(FTel);
                tuser.setfEmail(FEmail);
                tuser.setfJoinId(stuednetkey);
                tuser.setfUserno(FNo);
                tuser.setfName(FName);
                tuser.setfType(3);
                tuser.setfState(1);
                tuser.setfIsAdmin(0);
                tUserService.save(tuser);

                // 新建角色信息
                if (roles != null && !roles.equals("")) {
                    //增加新选的
                    JSONArray powerRoles_Array = JSONArray.parseArray(roles);
                    for (Object roleId : powerRoles_Array) {
                        Long fRoleId = Long.parseLong(ParamTools.getdeParam(roleId.toString()));
                        TPowerRoleUserExample powerRoleUserExample = new TPowerRoleUserExample();
                        powerRoleUserExample.or()
                                .andFUserIdEqualTo(key)
                                .andFRoleIdEqualTo(fRoleId);
                        List<TPowerRoleUser> list = powerRoleUserService.findByExample(powerRoleUserExample);
                        if (list.size() <= 0) {
                            TPowerRoleUser powerRoleUser = new TPowerRoleUser();
                            powerRoleUser.setfCId(Long.parseLong(uid));
                            powerRoleUser.setfRoleId(fRoleId);
                            powerRoleUser.setfUserId(key);
                            powerRoleUserService.save(powerRoleUser);
                        }
                    }
                }
//                //如果选择了班级
//                if (FClassID != null && !FClassID.equals("") && !FClassID.equals("-1")) {
//                    FClassID = ParamTools.getdeParam(FClassID);
//                    //添加学生与班级关系数据
//                    TClassStudentExample tClassStudentExample = new TClassStudentExample();
//                    TClassStudentExample.Criteria criteria1 = tClassStudentExample.createCriteria();
//                    criteria1.andFclassidEqualTo(Long.parseLong(FClassID));
//                    criteria1.andFstudentidEqualTo(key);
//                    List<TClassStudent> classStudentList = tClassStudentService.findByExample(tClassStudentExample);
//                    if (classStudentList.size() == 0) {
//                        TClassStudent tClassStudent = new TClassStudent();
//                        tClassStudent.setFstudentid(key);
//                        tClassStudent.setFclassid(Long.parseLong(FClassID));
//                        tClassStudent.setFcdate(new Date());
//                        tClassStudent.setFcid(Long.parseLong(uid));
//                        tClassStudentService.save(tClassStudent);
//                    }
//                }
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
     * 修改学生信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改学生信息")
    @ResponseBody
    @RequestMapping(value = "/updatetstudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatetstudent(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FName = jsonParam.getString("FName"); //姓名
        String FNo = jsonParam.getString("FNo");//学号
        String FSex = jsonParam.getString("FSex");//性别
        String FBirthday = jsonParam.getString("FBirthday");//出生日期
        String FIDNumber = jsonParam.getString("FIDNumber");//身份证号
        String FTel = jsonParam.getString("FTel");//联系电话
        String FEmail = jsonParam.getString("FEmail");//电子邮箱
        String FStartSchoolDate = jsonParam.getString("FStartSchoolDate");//入学时间
        String FEndSchoolDate = jsonParam.getString("FEndSchoolDate");//毕业时间
        String FMajor = jsonParam.getString("FMajor");//专业
        String FEducation = jsonParam.getString("FEducation");//学历
        String FPolitical = jsonParam.getString("FPolitical");//政治面目
        String FAddr = jsonParam.getString("FAddr");//住址
        String FHonor = jsonParam.getString("FHonor");//奖励荣誉
        String FPunish = jsonParam.getString("FPunish");//惩罚记录
        String FHealth = jsonParam.getString("FHealth");//健康记录
        String FNote = jsonParam.getString("FNote");//备注
        String FWorkState = jsonParam.getString("FWorkState");//工作状态
//        int FGradeLevel = jsonParam.getInteger("FGradeLevel");//当前年级
//        String FClassName = jsonParam.getString("FClassName");//班级名称
        String roles = jsonParam.getString("roles");

        String FClassID = jsonParam.getString("FClassID");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            TStudentExample tstudentExample = new TStudentExample();
            TStudentExample.Criteria criteria = tstudentExample.createCriteria();
            criteria.andFnameEqualTo(FName);
            criteria.andFidnumberEqualTo(FIDNumber);
            criteria.andFkeyidNotEqualTo(key);
            List<TStudent> studentList = tStudentService.findByExample(tstudentExample);
            if (studentList.size() == 0) {
                String CookiesLogintstudentID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintstudentID != null && !CookiesLogintstudentID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintstudentID);
                }


                // 更新学生信息
                TStudent tstudent = new TStudent();
                tstudent.setFkeyid(key);
                tstudent.setFname(FName);
                tstudent.setFno(FNo);
                tstudent.setFsex(Integer.valueOf(FSex));
                tstudent.setFbirthday(FBirthday);
                tstudent.setFidnumber(FIDNumber);
                tstudent.setFtel(FTel);
                tstudent.setFemail(FEmail);
                tstudent.setFstartschooldate(FStartSchoolDate);
                tstudent.setFendschooldate(FEndSchoolDate);
                tstudent.setFmajor(FMajor);
                tstudent.setFeducation(FEducation);
                tstudent.setFpolitical(FPolitical);
                tstudent.setFaddr(FAddr);
                tstudent.setFhonor(FHonor);
                tstudent.setFpunish(FPunish);
                tstudent.setFhealth(FHealth);
                tstudent.setFnote(FNote);
                tstudent.setFworkstate(Integer.valueOf(FWorkState));
                tstudent.setFuid(Long.parseLong(uid));
                tstudent.setFudate(new Date());
//                tstudent.setFgradelevel(FGradeLevel);
//                tstudent.setFclassname(FClassName);
                tStudentService.update(tstudent);

                TStudent tStudent = tStudentService.findById(key);
                TUser tUser = new TUser();
                tUser.setfKeyId(tStudent.getFuserid());
                tUser.setfTel(FTel);
                tUser.setfLogin(FTel);
                tUser.setfEmail(FEmail);
                tUser.setfName(FName);
                tUser.setfUserno(FNo);
                if (!FIDNumber.equals(tStudent.getFidnumber())) {
                    tUser.setfPass(ParamTools.getEnParam(FIDNumber.substring(FIDNumber.length() - 6)));
                }
                tUserService.update(tUser);
                // 更新角色信息
                if (roles != null) {
                    if (roles.equals("")) {
                        //删除全部
                        TPowerRoleUserExample powerRoleUserExample = new TPowerRoleUserExample();
                        powerRoleUserExample.or().andFUserIdEqualTo(tStudent.getFuserid());
                        powerRoleUserService.deleteByByExample(powerRoleUserExample);
                    } else {
                        //增加新选的
                        JSONArray powerRoles_Array = JSONArray.parseArray(roles);
                        List<Long> tempRoleID = new ArrayList<>();
                        for (Object roleId : powerRoles_Array) {
                            Long fRoleId = Long.parseLong(ParamTools.getdeParam(roleId.toString()));
                            TPowerRoleUserExample powerRoleUserExample = new TPowerRoleUserExample();
                            powerRoleUserExample.or()
                                    .andFRoleIdEqualTo(fRoleId)
                                    .andFUserIdEqualTo(tStudent.getFuserid());
                            List<TPowerRoleUser> list = powerRoleUserService.findByExample(powerRoleUserExample);
                            if (list.size() <= 0) {
                                TPowerRoleUser powerRoleUser = new TPowerRoleUser();
                                powerRoleUser.setfCId(Long.parseLong(uid));
                                powerRoleUser.setfRoleId(fRoleId);
                                powerRoleUser.setfUserId(tStudent.getFuserid());
                                powerRoleUserService.save(powerRoleUser);
                            }
                            tempRoleID.add(fRoleId);
                        }
                        //删除多余的
                        TPowerRoleUserExample powerRoleUserExample = new TPowerRoleUserExample();
                        if (tempRoleID.size() > 0) {
                            powerRoleUserExample.or()
                                    .andFUserIdEqualTo(tStudent.getFuserid())
                                    .andFRoleIdNotIn(tempRoleID);
                        } else {
                            powerRoleUserExample.or().andFUserIdEqualTo(tStudent.getFuserid());
                        }
                        powerRoleUserService.deleteByByExample(powerRoleUserExample);
                    }
                }

//                //如果选择了班级
//                if (FClassID != null && !FClassID.equals("") && !FClassID.equals("-1")) {
//                    FClassID = ParamTools.getdeParam(FClassID);
//
//                    //添加学生与班级关系数据
//                    TClassStudentExample tClassStudentExample = new TClassStudentExample();
//                    TClassStudentExample.Criteria criteria1 = tClassStudentExample.createCriteria();
//                    criteria1.andFclassidEqualTo(Long.parseLong(FClassID));
//                    criteria1.andFstudentidEqualTo(key);
//                    List<TClassStudent> classStudentList = tClassStudentService.findByExample(tClassStudentExample);
//                    if (classStudentList.size() == 0) {
//                        //先删除之前的班级关系，再增加新的
//                        tClassStudentExample = new TClassStudentExample();
//                        criteria1 = tClassStudentExample.createCriteria();
//                        criteria1.andFstudentidEqualTo(key);
//                        tClassStudentService.deleteByByExample(tClassStudentExample);
//
//                        TClassStudent tClassStudent = new TClassStudent();
//                        tClassStudent.setFstudentid(key);
//                        tClassStudent.setFclassid(Long.parseLong(FClassID));
//                        tClassStudent.setFcdate(new Date());
//                        tClassStudent.setFcid(Long.parseLong(uid));
//                        tClassStudentService.save(tClassStudent);
//                    }
//                } else {
//                    //删除关系,即便没有关系
//                    TClassStudentExample tClassStudentExample = new TClassStudentExample();
//                    TClassStudentExample.Criteria criteria1 = tClassStudentExample.createCriteria();
//                    criteria1.andFstudentidEqualTo(key);
//                    tClassStudentService.deleteByByExample(tClassStudentExample);
//                }
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
     * 删除学生信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除学生信息")
    @ResponseBody
    @RequestMapping(value = "/deltstudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deltstudent(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLogintstudentID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintstudentID != null && !CookiesLogintstudentID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TStudent tStudent = tStudentService.findById(Long.parseLong(id));
            if (tStudent != null) {
                //是否删除用户表数据？
                TUserExample tUserExample = new TUserExample();
                TUserExample.Criteria criteria = tUserExample.createCriteria();
                criteria.andFKeyIdEqualTo(tStudent.getFuserid());
                tUserService.deleteByByExample(tUserExample);
                //删除班级与学生关系
                TClassStudentExample tClassStudentExample = new TClassStudentExample();
                TClassStudentExample.Criteria criteria1 = tClassStudentExample.createCriteria();
                criteria1.andFstudentidEqualTo(Long.parseLong(id));
                tClassStudentService.deleteByByExample(tClassStudentExample);
                //删除学生表数据·
                tStudentService.deleteById(Long.parseLong(id));
                // 返回值
                object.put("result", "success");
            } else {
                object.put("result", "获取学生数据失败，请重试！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 变更学生信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statetstudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statetstudent(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLogintstudentID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintstudentID != null && !CookiesLogintstudentID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TStudent tstudent = new TStudent();
            tstudent.setFkeyid(Long.parseLong(id));
            tstudent.setFuid(Long.parseLong(uid));
            tstudent.setFudate(new Date());
            tstudent.setFstate(Integer.valueOf(state));
            tStudentService.update(tstudent);
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
     * 验证学生是否存在
     */
    private int repeaTStudent(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TStudentExample tstudentExample = new TStudentExample();
            TStudentExample.Criteria criteria = tstudentExample.createCriteria();
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
            List<TStudent> tstudentList = tStudentService.findByExample(tstudentExample);
            if (tstudentList.size() == 0) {
                code = 0;
            } else {
                code = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }


    //根据ID查询学生名称
    public String getName(Long id) {
        TStudent byId = tStudentService.findById(id);
        if (byId != null) {
            return byId.getFname();
        } else {
            return "";
        }

    }


    /**
     * 模板下载
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/downloadMoban", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String downloadMoban(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        JSONObject jsonParam = ParamTools.getParameters(request);
        String ip = jsonParam.getString("ip");
        String filename = jsonParam.getString("filename");
        String filenamestr = jsonParam.getString("filenamestr");
        try {
            String url = "";
            url = studentmb.replace(studentmb, ip).replace("//", "\\");
            String file = "http://" + url + "/0/2024-11/" + filename;
//            System.out.println(file);
            // 返回值
            object.put("filename", filenamestr);//"采购申请物料清单导入模板"
            object.put("file", file);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    // 18位身份证号正则表达式
    private static final String REGEX_18_ID_CARD = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
    // 中国手机号正则表达式
    private static final String REGEX_MOBILE_NUMBER = "^1[3-9]\\d{9}$";

    /**
     * 模板导入
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/studentupmoban", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String studentupmoban(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
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
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    // 3.获取表sheet,这里sheet0代表获取下表为0的excel表,也就是第一个表
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    boolean iserror = false;
                    if (suffixName.equals("xlsx")) {
                        String FName = "";
                        String FNo = "";
                        String FSex = "";
                        String FBirthday = "";
                        String FIDNumber = "";
                        String FTel = "";
                        String FEmail = "";
                        String FStartSchoolDate = "";
                        String FEndSchoolDate = "";
                        String FMajor = "";
                        String FEducation = "";
                        String FPolitical = "";
                        String FAddr = "";
                        String FHonor = "";
                        String FPunish = "";
                        String FHealth = "";
                        String FNote = "";
                        String FWorkState = "";
//                        String FGradeLevel = "";
//                        String FClassName = "";
                        // 4.获取数据
                        // getLastRowNum() 获取一张sheet表中行的数量
                        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                            // 获取第i行的数据
                            XSSFRow row = sheet.getRow(i);
                            FName = "";
                            FNo = "";
                            FSex = "";
                            FBirthday = "";
                            FIDNumber = "";
                            FTel = "";
                            FEmail = "";
                            FStartSchoolDate = "";
                            FEndSchoolDate = "";
                            FMajor = "";
                            FEducation = "";
                            FPolitical = "";
                            FAddr = "";
                            FHonor = "";
                            FPunish = "";
                            FHealth = "";
                            FNote = "";
                            FWorkState = "";
//                            FGradeLevel = "";
//                            FClassName = "";
                            for (int j = 0; j < row.getLastCellNum(); j++) {
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
                                if (i == 0 && j == 0 && !cell.equals("*姓名")) {
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
                                            FName = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 1:
                                            FNo = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 2:
                                            FSex = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 3:
                                            try {
                                                if (DateUtil.isCellDateFormatted(row.getCell(j))) {
                                                    // 如果是日期格式，转换为日期对象
                                                    Date date = row.getCell(j).getDateCellValue();
                                                    FBirthday = dateFormat.format(date);
                                                } else {
                                                    FBirthday = "";
                                                }
                                            } catch (Exception e) {
                                                FBirthday = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            }
//                                            FBirthday = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 4:
                                            FIDNumber = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 5:
                                            row.getCell(j).setCellType(CellType.STRING);
                                            FTel = row.getCell(j).getStringCellValue();
//                                            FTel = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 6:
                                            FEmail = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 7:
                                            try {
                                                if (DateUtil.isCellDateFormatted(row.getCell(j))) {
                                                    // 如果是日期格式，转换为日期对象
                                                    Date date = row.getCell(j).getDateCellValue();
                                                    FStartSchoolDate = dateFormat.format(date);
                                                } else {
                                                    FStartSchoolDate = "";
                                                }
                                            } catch (Exception e) {
                                                FStartSchoolDate = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            }
//                                            FStartSchoolDate = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 8:
                                            try {
                                                if (DateUtil.isCellDateFormatted(row.getCell(j))) {
                                                    // 如果是日期格式，转换为日期对象
                                                    Date date = row.getCell(j).getDateCellValue();
                                                    FEndSchoolDate = dateFormat.format(date);
                                                } else {
                                                    FEndSchoolDate = "";
                                                }
                                            } catch (Exception e) {
                                                FEndSchoolDate = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            }
//                                            FEndSchoolDate = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
//                                        case 9:
//                                            FGradeLevel = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
//                                            break;
//                                        case 10:
//                                            FClassName = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
//                                            break;
                                        case 9:
                                            FMajor = row.getCell(j).toString().trim() == null ? "0" : row.getCell(j).toString().trim();
                                            break;
                                        case 10:
                                            FEducation = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 11:
                                            FPolitical = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 12:
                                            FAddr = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 13:
                                            FHonor = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 14:
                                            FPunish = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 15:
                                            FHealth = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 16:
                                            FWorkState = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 17:
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
                                if (FNo.equals(""))
                                    error += "学生学号未填写" + ";";
                                if (FBirthday.equals(""))
                                    error += "出生日期未填写" + ";";
                                if (FSex.equals(""))
                                    error += "性别未填写" + ";";
                                if (!FSex.equals("") && !(FSex.equals("0") || FSex.equals("1"))) {
                                    error += "性别填写错误" + ";";
                                }
                                if (FIDNumber.equals(""))
                                    error += "身份证号未填写" + ";";
                                if (!FIDNumber.equals("") && !Pattern.matches(REGEX_18_ID_CARD, FIDNumber)) {
                                    error += "身份证号填写错误" + ";";
                                }
                                if (FTel.equals(""))
                                    error += "联系电话未填写" + ";";
                                if (!FTel.equals("") && !Pattern.matches(REGEX_MOBILE_NUMBER, FTel)) {
                                    error += "联系电话填写错误" + ";";
                                }
                                TStudentExample tstudentExample = new TStudentExample();
                                TStudentExample.Criteria criteria = tstudentExample.createCriteria();
                                criteria.andFtelEqualTo(FTel);
                                List<TStudent> studentList = tStudentService.findByExample(tstudentExample);
                                if (studentList.size() > 0) {
                                    error += "联系电话已存在" + ";";
                                }
//                                if (FGradeLevel.equals(""))
//                                    error += "年级未填写" + ";";
//                                if (FClassName.equals(""))
//                                    error += "班级未填写" + ";";

                                if (FWorkState.equals(""))
                                    error += "工作状态未填写" + ";";
                                if (!FWorkState.equals("") && !(FWorkState.equals("0") || FWorkState.equals("1"))) {
                                    error += "工作状态填写错误" + ";";
                                }
                                if (!error.equals("")) {
                                    student_object.put("FName", FName == null ? "未填写" : FName);
                                    student_object.put("error", error);
                                    student_Array.add(student_object);
                                    continue;
                                } else {
                                    tstudentExample = new TStudentExample();
                                    criteria = tstudentExample.createCriteria();
//                                criteria.andFnameEqualTo(FName);
                                    criteria.andFidnumberEqualTo(FIDNumber);
                                    criteria.andFtelEqualTo(FTel);
                                    studentList = tStudentService.findByExample(tstudentExample);
                                    if (studentList.size() == 0) {
                                        long key = idWorker.nextId();
                                        long stuednetkey = idWorker.nextId();
                                        // 新建学生信息
                                        TStudent tstudent = new TStudent();
                                        tstudent.setFkeyid(stuednetkey);
                                        tstudent.setFname(FName);
                                        tstudent.setFno(FNo);
                                        tstudent.setFsex(Integer.valueOf(FSex));
                                        tstudent.setFbirthday(FBirthday);
                                        tstudent.setFidnumber(FIDNumber);
                                        tstudent.setFtel(FTel);
                                        tstudent.setFemail(FEmail);
                                        tstudent.setFstartschooldate(FStartSchoolDate);
                                        tstudent.setFendschooldate(FEndSchoolDate);
                                        tstudent.setFmajor(FMajor);
                                        tstudent.setFeducation(FEducation);
                                        tstudent.setFpolitical(FPolitical);
                                        tstudent.setFaddr(FAddr);
                                        tstudent.setFhonor(FHonor);
                                        tstudent.setFpunish(FPunish);
                                        tstudent.setFhealth(FHealth);
                                        tstudent.setFnote(FNote);
                                        tstudent.setFworkstate(Integer.valueOf(FWorkState));
                                        tstudent.setFcid(Long.parseLong(uid));
                                        tstudent.setFcdate(new Date());
                                        tstudent.setFuserid(key);
//                                        int parsedInt = 1;
//                                        try {
//                                            parsedInt = Integer.parseInt(FGradeLevel);
//                                        } catch (NumberFormatException e) {
//                                            parsedInt = 1;
//                                        }
//                                        tstudent.setFgradelevel(parsedInt);
//                                        tstudent.setFclassname(FClassName);
                                        tStudentService.save(tstudent);
                                        //用户表添加信息
                                        TUser tuser = new TUser();
                                        tuser.setfKeyId(key);
                                        tuser.setfLogin(FTel);
                                        tuser.setfPass(ParamTools.getEnParam(FIDNumber.substring(FIDNumber.length() - 6)));
                                        tuser.setfTel(FTel);
                                        tuser.setfEmail(FEmail);
                                        tuser.setfJoinId(stuednetkey);
                                        tuser.setfUserno(FNo);
                                        tuser.setfName(FName);
                                        tuser.setfType(3);
                                        tuser.setfState(1);
                                        tuser.setfIsAdmin(0);
                                        tUserService.save(tuser);
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


//    /**
//     * 获取学生能力画像
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/getstudentabilityhuaxiang", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String getstudentabilityhuaxiang(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        int ftype = jsonParam.getInteger("ftype"); //1 = 获取全部 2=获取个人
//        String userid = "0";
//        try {
//            if (ftype == 2) {
//                userid = jsonParam.getString("userid");
//                userid = userid == null ? "0" : ParamTools.getdeParam(userid);
//            }
//            //查询能力类型
//            TAbilityTypeExample abilityTypeExample = new TAbilityTypeExample();
//            TAbilityTypeExample.Criteria criteria = abilityTypeExample.createCriteria();
//            criteria.andFstateEqualTo(1);
//            List<TAbilityType> abilityTypeList = tAbilityTypeService.findByExample(abilityTypeExample);
//            if (abilityTypeList.size() > 0) {
//                JSONArray type_Array = new JSONArray();
//                JSONArray ability_Array = new JSONArray();
//                JSONArray studentability_Array = new JSONArray();
//                JSONArray openKeys_Array = new JSONArray();
//                TAbilityExample tAbilityExample = null;
//                int iii = 0;
//                for (TAbilityType abilityType : abilityTypeList) {
//                    JSONObject type_object = new JSONObject();
//                    type_object.put("key", ParamTools.getEnParam(abilityType.getFkeyid().toString()));
//                    type_object.put("FName", abilityType.getFname());
//                    type_object.put("FNote", abilityType.getFnote());
//                    type_object.put("i", iii);
//                    tAbilityExample = new TAbilityExample();
//                    TAbilityExample.Criteria criteria1 = tAbilityExample.createCriteria();
//                    criteria1.andFtypeidEqualTo(abilityType.getFkeyid());
//                    criteria1.andFstateEqualTo(1);
//
//                    List<TAbility> abilityList = tAbilityService.findByExample(tAbilityExample);
//                    if (abilityList.size() > 0) {
//                        for (TAbility ability : abilityList) {
//                            JSONObject ability_object = new JSONObject();
//                            ability_object.put("key", ParamTools.getEnParam(ability.getFkeyid().toString()));
//                            ability_object.put("FName", ability.getFname());
//                            ability_object.put("FNote", abilityType.getFnote());
//                            ability_object.put("i", iii);
//                            ability_Array.add(ability_object);
//                        }
//                    }
//
//                    type_Array.add(type_object);
//                    iii++;
//                }
////                TStudentAbilityExample tStudentAbilityExample = new TStudentAbilityExample();
////                TStudentAbilityExample.Criteria criteria2 = tStudentAbilityExample.createCriteria();
////                criteria2.andFstateEqualTo(1);
////                criteria2.andFstudentidEqualTo(Long.parseLong(userid));
////                List<TStudentAbility> tStudentAbilityList = tStudentAbilityService.findByExample(tStudentAbilityExample);
////                if (tStudentAbilityList.size() > 0) {
////                    for (TStudentAbility tStudentAbility : tStudentAbilityList) {
////                        JSONObject studentability_object = new JSONObject();
////                        studentability_object.put("key", ParamTools.getEnParam(tStudentAbility.getFkeyid().toString()));
////                        studentability_object.put("FAbilityID", ParamTools.getEnParam(tStudentAbility.getFabilityid().toString()));
////                        TAbilityLevel tAbilityLevel = tAbilityLevelService.findById(tStudentAbility.getFabilitylevelid());
////                        studentability_object.put("FAbilityLevelName", tAbilityLevel == null ? "" : tAbilityLevel.getFname());
////                        studentability_Array.add(studentability_object);
////                    }
////                }
//                List<StudentabilityLevelMax> levelMaxList = tStudentAbilityService.getStudentAbilityLevelMax(Long.parseLong(userid));
//                if (levelMaxList.size() > 0) {
//                    for (StudentabilityLevelMax studentabilityLevelMax : levelMaxList) {
//                        JSONObject studentability_object = new JSONObject();
//                        studentability_object.put("key", ParamTools.getEnParam(studentabilityLevelMax.getFKeyID().toString()));
//                        studentability_object.put("FAbilityID", ParamTools.getEnParam(studentabilityLevelMax.getFAbilityID().toString()));
//                        studentability_object.put("FAbilityLevelName", studentabilityLevelMax.getFAbilityLevelName());
//                        studentability_Array.add(studentability_object);
//                    }
//                }
//
//                for (int i = 0; i < abilityTypeList.size(); i++) {
//                    openKeys_Array.add(i);
//                }
////                System.out.println(openKeys_Array);
////                System.out.println(type_Array);
//                object.put("type_Array", type_Array);
//                object.put("ability_Array", ability_Array);
//                object.put("studentability_Array", studentability_Array);
//                object.put("openKeys", openKeys_Array);
//                // 返回值
//                object.put("result", "success");
//            } else {
//                object.put("result", "能力数据获取失败，请刷新后重试！");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }

    /**
     * 获取学生能力画像
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/getstudentabilityhuaxiang", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getstudentabilityhuaxiang(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int ftype = jsonParam.getInteger("ftype"); //1 = 获取全部 2=获取个人
        String userid = "0";
        try {
            if (ftype == 2) {
                userid = jsonParam.getString("userid");
                userid = userid == null ? "0" : ParamTools.getdeParam(userid);
            }
            //查询能力类型
            TAbilityTypeExample abilityTypeExample = new TAbilityTypeExample();
            TAbilityTypeExample.Criteria criteria = abilityTypeExample.createCriteria();
            criteria.andFstateEqualTo(1);
            List<TAbilityType> abilityTypeList = tAbilityTypeService.findByExample(abilityTypeExample);
            if (abilityTypeList.size() > 0) {
                JSONArray type_Array = new JSONArray();
                JSONArray ability_Array = new JSONArray();
                JSONArray studentability_Array = new JSONArray();
                JSONArray openKeys_Array = new JSONArray();
                TAbilityExample tAbilityExample = null;
                int iii = 0;
                for (TAbilityType abilityType : abilityTypeList) {
                    JSONObject type_object = new JSONObject();
                    type_object.put("key", ParamTools.getEnParam(abilityType.getFkeyid().toString()));
                    type_object.put("FName", abilityType.getFname());
                    type_object.put("FNote", abilityType.getFnote());
                    type_object.put("i", iii);

                    TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
                    TAbilityTreeExample.Criteria criteria1 = tAbilityTreeExample.createCriteria();
                    criteria1.andFnodetypeEqualTo(1);
                    criteria1.andFdelEqualTo(1);
                    criteria1.andFtypeidEqualTo(abilityType.getFkeyid());


                    List<TAbilityTree> abilityList = tAbilityTreeService.findByExample(tAbilityTreeExample);
                    if (abilityList.size() > 0) {
                        for (TAbilityTree ability : abilityList) {
                            JSONObject ability_object = new JSONObject();
                            ability_object.put("key", ParamTools.getEnParam(ability.getFkeyid().toString()));
                            ability_object.put("FName", ability.getFname());
                            ability_object.put("FNote", abilityType.getFnote());
                            ability_object.put("i", iii);
                            ability_Array.add(ability_object);
                        }
                    }
                    type_Array.add(type_object);
                    iii++;
                }
//                System.out.println(userid);
                List<StudentabilityLevelMax> levelMaxList = tStudentAbilityService.getStudentAbilityLevelMax(Long.parseLong(userid));
                if (levelMaxList.size() > 0) {
                    for (StudentabilityLevelMax studentabilityLevelMax : levelMaxList) {
                        JSONObject studentability_object = new JSONObject();
                        studentability_object.put("key", ParamTools.getEnParam(studentabilityLevelMax.getFKeyID().toString()));
                        studentability_object.put("FAbilityID", ParamTools.getEnParam(studentabilityLevelMax.getFAbilityID().toString()));
                        studentability_object.put("FAbilityLevelName", studentabilityLevelMax.getFAbilityLevelName());
                        studentability_Array.add(studentability_object);
                    }
                }


                for (int i = 0; i < abilityTypeList.size(); i++) {
                    openKeys_Array.add(i);
                }
//                System.out.println(openKeys_Array);
//                System.out.println(type_Array);
                object.put("type_Array", type_Array);
                object.put("ability_Array", ability_Array);
                object.put("studentability_Array", studentability_Array);
                object.put("openKeys", openKeys_Array);
                // 返回值
                object.put("result", "success");
            } else {
                object.put("result", "能力数据获取失败，请刷新后重试！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

//    /**
//     * 获取学生能力画像 _只显示获取到的能力？
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/getstudentabilityhuaxiang_d", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String getstudentabilityhuaxiang_d(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        int ftype = jsonParam.getInteger("ftype"); //1 = 获取全部 2=获取个人
//        String userid = "0";
//        try {
//            if (ftype == 2) {
//                userid = jsonParam.getString("userid");
//                userid = userid == null ? "0" : ParamTools.getdeParam(userid);
//            }
//
//            JSONArray type_Array = new JSONArray();
//            JSONArray ability_Array = new JSONArray();
//            JSONArray studentability_Array = new JSONArray();
//            JSONArray openKeys_Array = new JSONArray();
//            TAbilityExample tAbilityExample = null;
//            List<Long> typeidlist = new ArrayList<>();
//            List<Long> abilityIDlist = new ArrayList<>();
//            List<StudentabilityLevelMax> levelMaxList = tStudentAbilityService.getStudentAbilityLevelMax(Long.parseLong(userid));
//            if (levelMaxList.size() > 0) {
//                for (StudentabilityLevelMax studentabilityLevelMax : levelMaxList) {
//                    if(!typeidlist.contains(studentabilityLevelMax.getFTypeID()))
//                        typeidlist.add(studentabilityLevelMax.getFTypeID());
//                    abilityIDlist.add(studentabilityLevelMax.getFAbilityID());
//                }
//            }
//
//            if(typeidlist.size() > 0){
//                //查询能力类型
//                TAbilityTypeExample abilityTypeExample = new TAbilityTypeExample();
//                TAbilityTypeExample.Criteria criteria = abilityTypeExample.createCriteria();
//                criteria.andFstateEqualTo(1);
//                criteria.andFkeyidIn(typeidlist);
//                List<TAbilityType> abilityTypeList = tAbilityTypeService.findByExample(abilityTypeExample);
//                if (abilityTypeList.size() > 0) {
//
//                    int iii = 0;
//                    for (TAbilityType abilityType : abilityTypeList) {
//                        JSONObject type_object = new JSONObject();
//                        type_object.put("key", ParamTools.getEnParam(abilityType.getFkeyid().toString()));
//                        type_object.put("FName", abilityType.getFname());
//                        type_object.put("FNote", abilityType.getFnote());
//                        type_object.put("i", iii);
//
//                        TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
//                        TAbilityTreeExample.Criteria criteria1 = tAbilityTreeExample.createCriteria();
//                        criteria1.andFnodetypeEqualTo(1);
//                        criteria1.andFdelEqualTo(1);
//                        criteria1.andFtypeidEqualTo(abilityType.getFkeyid());
//                        criteria1.andFkeyidIn(abilityIDlist);
//                        List<TAbilityTree> abilityList = tAbilityTreeService.findByExample(tAbilityTreeExample);
//                        if (abilityList.size() > 0) {
//                            for (TAbilityTree ability : abilityList) {
//                                JSONObject ability_object = new JSONObject();
//                                ability_object.put("key", ParamTools.getEnParam(ability.getFkeyid().toString()));
//                                ability_object.put("FName", ability.getFname());
//                                ability_object.put("FNote", abilityType.getFnote());
//                                ability_object.put("i", iii);
//                                ability_Array.add(ability_object);
//                            }
//                        }
//                        type_Array.add(type_object);
//                        iii++;
//                    }
////                System.out.println(userid);
//
//                    for (StudentabilityLevelMax studentabilityLevelMax : levelMaxList) {
//                        JSONObject studentability_object = new JSONObject();
//                        studentability_object.put("key", ParamTools.getEnParam(studentabilityLevelMax.getFKeyID().toString()));
//                        studentability_object.put("FAbilityID", ParamTools.getEnParam(studentabilityLevelMax.getFAbilityID().toString()));
//                        studentability_object.put("FAbilityLevelName", studentabilityLevelMax.getFAbilityLevelName());
//                        studentability_Array.add(studentability_object);
//                    }
//
//
//
//                    for (int i = 0; i < abilityTypeList.size(); i++) {
//                        openKeys_Array.add(i);
//                    }
////                System.out.println(openKeys_Array);
////                System.out.println(type_Array);
//                    object.put("type_Array", type_Array);
//                    object.put("ability_Array", ability_Array);
//                    object.put("studentability_Array", studentability_Array);
//                    object.put("openKeys", openKeys_Array);
//                    // 返回值
//                    object.put("result", "success");
//                } else {
//                    object.put("result", "能力数据获取失败，请刷新后重试！");
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }

    /**
     * 获取学生能力画像 _只显示获取到的能力？
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/getstudentabilityhuaxiang_d", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getstudentabilityhuaxiang_d(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int ftype = jsonParam.getInteger("ftype"); //1 = 获取全部 2=获取个人
        String userid = "0";
        try {
            if (ftype == 2) {
                userid = jsonParam.getString("userid");
                userid = userid == null ? "0" : ParamTools.getdeParam(userid);
            }

            JSONArray type_Array = new JSONArray();
            JSONArray ability_Array = new JSONArray();
            JSONArray studentability_Array = new JSONArray();
            JSONArray openKeys_Array = new JSONArray();
            TAbilityExample tAbilityExample = null;
            List<Long> typeidlist = new ArrayList<>();
            List<Long> abilityIDlist = new ArrayList<>();
            List<Long> abilityConditionIDlist = new ArrayList<>();
            Map<Long,Long> abilityConditionIDMap = new HashMap<>();
            Map<Long,Long> studentabilityIDMap = new HashMap<>();

            TStudentAbilityExample tStudentAbilityExample = new TStudentAbilityExample();
            tStudentAbilityExample.createCriteria().andFstudentidEqualTo(Long.parseLong(userid));
            List<TStudentAbility> abilities = tStudentAbilityService.findByExample(tStudentAbilityExample);
            for (TStudentAbility ability : abilities) {
                TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
                TAbilityTreeExample.Criteria criteria1 = tAbilityTreeExample.createCriteria();
                criteria1.andFnodetypeEqualTo(3);
                criteria1.andFdelEqualTo(1);
                criteria1.andFalcidEqualTo(ability.getFconditionid());
                List<TAbilityTree> abilityList = tAbilityTreeService.findByExample(tAbilityTreeExample);
                if (abilityList.size() > 0) {
                    Long shuipingID = 0l;
                    for (TAbilityTree abilityTree : abilityList) {
                        //查找这个条件的 能力水平是什么
                        TAbilityTree abilityTree1 = tAbilityTreeService.findById(abilityTree.getFpid());
                        if (abilityTree1.getFnodetype() == 2) {
                            shuipingID = abilityTree1.getFkeyid();
                        } else {
                            shuipingID = getConditionPid(abilityTree1.getFpid());
                        }
                        TAbilityTreeExample tAbilityTreeExample2 = new TAbilityTreeExample();
                        TAbilityTreeExample.Criteria criteria2 = tAbilityTreeExample2.createCriteria();
                        criteria2.andFkeyidEqualTo(abilityTree.getFgpid());
                        List<TAbilityTree> trees = tAbilityTreeService.findByExample(tAbilityTreeExample2);
                        for (TAbilityTree tree : trees) {
                            if (!typeidlist.contains(tree.getFtypeid()))
                                typeidlist.add(tree.getFtypeid());
                        }
                        if (!abilityIDlist.contains(abilityTree.getFgpid())){
                            abilityIDlist.add(abilityTree.getFgpid());
                            abilityConditionIDMap.put(abilityTree.getFgpid(),shuipingID);
                            studentabilityIDMap.put(abilityTree.getFgpid(),ability.getFkeyid());
                        }
                    }
                }
            }


            if (typeidlist.size() > 0) {
                //查询能力类型
                TAbilityTypeExample abilityTypeExample = new TAbilityTypeExample();
                TAbilityTypeExample.Criteria criteria = abilityTypeExample.createCriteria();
                criteria.andFstateEqualTo(1);
                criteria.andFkeyidIn(typeidlist);
                List<TAbilityType> abilityTypeList = tAbilityTypeService.findByExample(abilityTypeExample);
                if (abilityTypeList.size() > 0) {

                    int iii = 0;
                    for (TAbilityType abilityType : abilityTypeList) {
                        JSONObject type_object = new JSONObject();
                        type_object.put("key", ParamTools.getEnParam(abilityType.getFkeyid().toString()));
                        type_object.put("FName", abilityType.getFname());
                        type_object.put("FNote", abilityType.getFnote());
                        type_object.put("i", iii);

                        TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
                        TAbilityTreeExample.Criteria criteria1 = tAbilityTreeExample.createCriteria();
                        criteria1.andFnodetypeEqualTo(1);
                        criteria1.andFdelEqualTo(1);
                        criteria1.andFtypeidEqualTo(abilityType.getFkeyid());
                        criteria1.andFkeyidIn(abilityIDlist);
                        List<TAbilityTree> abilityList = tAbilityTreeService.findByExample(tAbilityTreeExample);
                        if (abilityList.size() > 0) {
                            for (TAbilityTree ability : abilityList) {
                                TAbilityTree abilityTreeServiceById = tAbilityTreeService.findById(abilityConditionIDMap.get(ability.getFkeyid()));
                                JSONObject ability_object = new JSONObject();
                                ability_object.put("key", ParamTools.getEnParam(ability.getFkeyid().toString()));
                                ability_object.put("studentabilityID", ParamTools.getEnParam(studentabilityIDMap.get(ability.getFkeyid()).toString()));
                                ability_object.put("FName", ability.getFname() + "("+abilityTreeServiceById.getFname()+")");
                                ability_object.put("FNote", abilityType.getFnote());
                                ability_object.put("i", iii);
                                ability_Array.add(ability_object);
                            }
                        }
                        type_Array.add(type_object);
                        iii++;
                    }
//                System.out.println(userid);

//                    for (StudentabilityLevelMax studentabilityLevelMax : levelMaxList) {
//                        JSONObject studentability_object = new JSONObject();
//                        studentability_object.put("key", ParamTools.getEnParam(studentabilityLevelMax.getFKeyID().toString()));
//                        studentability_object.put("FAbilityID", ParamTools.getEnParam(studentabilityLevelMax.getFAbilityID().toString()));
//                        studentability_object.put("FAbilityLevelName", studentabilityLevelMax.getFAbilityLevelName());
//                        studentability_Array.add(studentability_object);
//                    }



                    for (int i = 0; i < abilityTypeList.size(); i++) {
                        openKeys_Array.add(i);
                    }
//                System.out.println(openKeys_Array);
//                System.out.println(type_Array);
                    object.put("type_Array", type_Array);
                    object.put("ability_Array", ability_Array);
                    object.put("studentability_Array", studentability_Array);
                    object.put("openKeys", openKeys_Array);
                    // 返回值
                    object.put("result", "success");
                } else {
                    object.put("result", "能力数据获取失败，请刷新后重试！");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    private Long getConditionPid(Long FPID) {
        Long fpid = 0l;


        TAbilityTree tree = tAbilityTreeService.findById(FPID);
        if (tree.getFnodetype() == 2) {
            fpid = tree.getFkeyid();
        } else {
            fpid = getConditionPid(tree.getFpid());
        }

        return fpid;
    }

    /**
     * 获取学生能力画像 _只显示获取到的能力？
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/getstudentabilityhuaxiang_dd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getstudentabilityhuaxiang_dd(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int ftype = jsonParam.getInteger("ftype"); //1 = 获取全部 2=获取个人
        String userid = "0";
        try {
            if (ftype == 2) {
                userid = jsonParam.getString("userid");
                userid = userid == null ? "0" : ParamTools.getdeParam(userid);
            }

            JSONArray type_Array = new JSONArray();
            JSONArray ability_Array = new JSONArray();
            JSONArray studentability_Array = new JSONArray();
            JSONArray openKeys_Array = new JSONArray();
            TAbilityExample tAbilityExample = null;
            List<Long> typeidlist = new ArrayList<>();
            List<Long> abilityIDlist = new ArrayList<>();
            List<Long> abilityConditionIDlist = new ArrayList<>();
            Map<Long,Long> abilityConditionIDMap = new HashMap<>();
            Map<Long,Long> studentabilityIDMap = new HashMap<>();

            TStudentAbilityExample tStudentAbilityExample = new TStudentAbilityExample();
            TStudentAbilityExample.Criteria criteria2 = tStudentAbilityExample.createCriteria();
            criteria2.andFstudentidEqualTo(Long.parseLong(userid));
            List<TStudentAbility> studentAbilities = tStudentAbilityService.findByExample(tStudentAbilityExample);
            if (studentAbilities.size() > 0) {
                Long shuipingID=0l;
                for (TStudentAbility studentAbility : studentAbilities) {
                    //存储条件ID
                    TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
                    tAbilityTreeExample.createCriteria().andFalcidEqualTo(studentAbility.getFconditionid());
                    List<TAbilityTree> abilityTrees = tAbilityTreeService.findByExample(tAbilityTreeExample);
                    for (TAbilityTree abilityTree : abilityTrees) {
                        //获取该条件的 能力水平ID
                        TAbilityTree tAbilityTree = tAbilityTreeService.findById(abilityTree.getFpid());
                        if(tAbilityTree.getFnodetype() == 2){
                            shuipingID = tAbilityTree.getFkeyid();
                        }else{
                            shuipingID = getConditionPid(tAbilityTree.getFpid());
                        }
                        //获取该条件的 能力ID
                        if(!typeidlist.contains(abilityTree.getFgpid())){
                            typeidlist.add(abilityTree.getFgpid());
                        }

                         if(!abilityConditionIDlist.contains(abilityTree.getFkeyid())){
                             abilityConditionIDlist.add(abilityTree.getFkeyid());
                        }
                        abilityConditionIDMap.put(abilityTree.getFkeyid(),shuipingID);
                        studentabilityIDMap.put(abilityTree.getFkeyid(),studentAbility.getFkeyid());
                    }
                }
            }
            System.out.println(abilityConditionIDlist);
            if (typeidlist.size() > 0) {
                //查询能力类型
                TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
                TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
                criteria.andFstateEqualTo(1);
                criteria.andFdelEqualTo(1);
                criteria.andFkeyidIn(typeidlist);
                List<TAbilityTree> abilityTypeList = tAbilityTreeService.findByExample(tAbilityTreeExample);
                if (abilityTypeList.size() > 0) {

                    int iii = 0;
                    for (TAbilityTree abilityType : abilityTypeList) {
                        JSONObject type_object = new JSONObject();
                        type_object.put("key", ParamTools.getEnParam(abilityType.getFkeyid().toString()));
                        type_object.put("FName", abilityType.getFname());
                        type_object.put("FNote", abilityType.getFnote() == null ? "" : abilityType.getFnote());
                        type_object.put("i", iii);

                        TAbilityTreeExample tAbilityTreeExample2 = new TAbilityTreeExample();
                        TAbilityTreeExample.Criteria criteria1 = tAbilityTreeExample2.createCriteria();
                        criteria1.andFdelEqualTo(1);
                        criteria1.andFgpidEqualTo(abilityType.getFkeyid());
                        criteria1.andFkeyidIn(abilityConditionIDlist);
                        List<TAbilityTree> abilityList = tAbilityTreeService.findByExample(tAbilityTreeExample2);
                        if (abilityList.size() > 0) {
                            for (TAbilityTree ability : abilityList) {
                                TAbilityTree abilityTreeServiceById = tAbilityTreeService.findById(abilityConditionIDMap.get(ability.getFkeyid()));
                                JSONObject ability_object = new JSONObject();
                                ability_object.put("key", ParamTools.getEnParam(ability.getFkeyid().toString()));
                                ability_object.put("levelConditID", ParamTools.getEnParam(ability.getFalcid().toString()));
                                ability_object.put("studentabilityID", ParamTools.getEnParam(studentabilityIDMap.get(ability.getFkeyid()).toString()));
//                                ability_object.put("FName", ability.getFname() + "("+abilityTreeServiceById.getFname()+")");
                                ability_object.put("FName", ability.getFname());
                                ability_object.put("FSPName", abilityTreeServiceById == null ? "" : abilityTreeServiceById.getFname());
                                ability_object.put("FNote", ability.getFnote() == null ? "" : ability.getFnote());
                                ability_object.put("i", iii);
                                ability_Array.add(ability_object);
                            }
                        }
                        type_Array.add(type_object);
                        iii++;
                    }


                    for (int i = 0; i < abilityTypeList.size(); i++) {
                        openKeys_Array.add(i);
                    }
                    object.put("type_Array", type_Array);
                    object.put("ability_Array", ability_Array);
                    object.put("studentability_Array", studentability_Array);
                    object.put("openKeys", openKeys_Array);
                    // 返回值
                    object.put("result", "success");
                } else {
                    object.put("result", "能力数据获取失败，请刷新后重试！");
                }
            }

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
//    @RequestMapping(value = "/queryabilityInfohua", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String queryabilityInfohua(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String id = jsonParam.getString("id");
//        String userid = jsonParam.getString("userid");
//        Integer type = jsonParam.getInteger("type");
//        String ip = jsonParam.getString("ip");
//
//        String studentabid = jsonParam.getString("studentabid");
//        try {
//            id = id == null ? "0" : ParamTools.getdeParam(id);
//            studentabid = studentabid == null ? "0" : ParamTools.getdeParam(studentabid);
//            userid = userid == null ? "0" : ParamTools.getdeParam(userid);
//            long key = Long.parseLong(id);
//            // 查询能力信息
//            TAbility ability = tAbilityService.findById(key);
//            JSONObject ability_object = new JSONObject();
//            ability_object.put("key", ParamTools.getEnParam(ability.getFkeyid().toString()));
//            ability_object.put("FTypeID", ParamTools.getEnParam(ability.getFtypeid().toString()));
//            TAbilityType tAbilityType = tAbilityTypeService.findById(ability.getFtypeid());
//            ability_object.put("FTypeName", tAbilityType == null ? "" : tAbilityType.getFname());
//            ability_object.put("FName", ability.getFname() == null ? "" : ability.getFname());
//            ability_object.put("FNote", ability.getFnote() == null ? "" : ability.getFnote());
////            TStudentAbilityExample tStudentAbilityExample = new TStudentAbilityExample();
////            TStudentAbilityExample.Criteria criteria = tStudentAbilityExample.createCriteria();
////            criteria.andFstateEqualTo(1);
////            criteria.andFstudentidEqualTo(Long.parseLong(userid));
////            criteria.andFabilityidEqualTo(key);
////            List<TStudentAbility> tStudentAbilityList = tStudentAbilityService.findByExample(tStudentAbilityExample);
//
////            for (TStudentAbility tStudentAbility : tStudentAbilityList) {
////            System.out.println(studentabid);
//            TStudentAbility tStudentAbility = tStudentAbilityService.findById(Long.parseLong(studentabid));
//            if (tStudentAbility != null) {
//                ability_object.put("studentwhq", 1);
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                switch (tStudentAbility.getFstate()) {
//                    case 0:
//                        ability_object.put("studentFState", "申请");
//                        break;
//                    case 1:
//                        ability_object.put("studentFState", "获取");
//                        break;
//                    case 2:
//                        ability_object.put("studentFState", "注销");
//                        break;
//                }
//                switch (tStudentAbility.getFmode()) {
//                    case 1:
//                        ability_object.put("studentFMode", "考核");
//                        break;
//                    case 2:
//                        ability_object.put("studentFMode", "材料认定");
//                        break;
//                    case 3:
//                        ability_object.put("studentFMode", "直接认定");
//                        break;
//                }
//                ability_object.put("studentFDate", tStudentAbility.getFdate() == null ? "" : dateFormat.format(tStudentAbility.getFdate()));
//                ability_object.put("studentFAbilityInf", tStudentAbility.getFabilityinf() == null ? "" : tStudentAbility.getFabilityinf());
//                String url = "";
//                if (tStudentAbility.getFurl() != null && !tStudentAbility.getFurl().equals("")) {
//                    if (type == 2) {
//                        url = tStudentAbility.getFurl().replace(studentfj.substring(0, studentfj.length() - 2), "").replace("//", "/");//本机测试
//                    } else
//                        //服务器
//                        url = tStudentAbility.getFurl().replace(studentfj.replace("//", "/").substring(0, studentfj.length() - 1), ip).replace("//", "/");
//
//                    ability_object.put("studentFUrl", url);
//                    ability_object.put("studentfj", 1);
//                } else {
//                    ability_object.put("studentFUrl", url);
//                    ability_object.put("studentfj", 0);
//                }
//
//            } else {
//                ability_object.put("studentwhq", 0);
//                ability_object.put("studentFState", "");
//                ability_object.put("studentFDate", "");
//                ability_object.put("studentFMode", "");
//                ability_object.put("studentFAbilityInf", "");
//                ability_object.put("studentfj", 0);
//                ability_object.put("studentFUrl", "");
//            }
////            }
//            // 返回值
//            object.put("info", ability_object);
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
    @RequestMapping(value = "/queryabilityInfohua", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilityInfohua(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String userid = jsonParam.getString("userid");
        Integer type = jsonParam.getInteger("type");
        String ip = jsonParam.getString("ip");

        String studentabid = jsonParam.getString("studentabid");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            studentabid = studentabid == null ? "0" : ParamTools.getdeParam(studentabid);
            userid = userid == null ? "0" : ParamTools.getdeParam(userid);
            long key = Long.parseLong(id);
            // 查询能力信息
            TAbilityTree ability = tAbilityTreeService.findById(key);
            JSONObject ability_object = new JSONObject();
            ability_object.put("key", ParamTools.getEnParam(ability.getFkeyid().toString()));
            ability_object.put("FTypeID",ability.getFtypeid() == null? ParamTools.getEnParam("0") :  ParamTools.getEnParam(ability.getFtypeid().toString()));
            TAbilityType tAbilityType = tAbilityTypeService.findById(ability.getFtypeid());
            ability_object.put("FTypeName", tAbilityType == null ? "" : tAbilityType.getFname());
            ability_object.put("FName", ability.getFname() == null ? "" : ability.getFname());
            ability_object.put("FNote", ability.getFnote() == null ? "" : ability.getFnote());

            TStudentAbility tStudentAbility = tStudentAbilityService.findById(Long.parseLong(studentabid));
            if (tStudentAbility != null) {
                ability_object.put("studentwhq", 1);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                switch (tStudentAbility.getFstate()) {
                    case 0:
                        ability_object.put("studentFState", "申请");
                        break;
                    case 1:
                        ability_object.put("studentFState", "获取");
                        break;
                    case 2:
                        ability_object.put("studentFState", "注销");
                        break;
                }
                switch (tStudentAbility.getFmode()) {
                    case 1:
                        ability_object.put("studentFMode", "考核");
                        break;
                    case 2:
                        ability_object.put("studentFMode", "材料认定");
                        break;
                    case 3:
                        ability_object.put("studentFMode", "直接认定");
                        break;
                }
                ability_object.put("studentFDate", tStudentAbility.getFdate() == null ? "" : dateFormat.format(tStudentAbility.getFdate()));
                ability_object.put("studentFAbilityInf", tStudentAbility.getFabilityinf() == null ? "" : tStudentAbility.getFabilityinf());
                String url = "";
                if (tStudentAbility.getFurl() != null && !tStudentAbility.getFurl().equals("")) {
                    if (type == 2) {
                        url = tStudentAbility.getFurl().replace(studentfj.substring(0, studentfj.length() - 2), "").replace("//", "/");//本机测试
                    } else
                        //服务器
                        url = tStudentAbility.getFurl().replace(studentfj.replace("//", "/").substring(0, studentfj.length() - 1), ip).replace("//", "/");

                    ability_object.put("studentFUrl", url);
                    ability_object.put("studentfj", 1);
                } else {
                    ability_object.put("studentFUrl", url);
                    ability_object.put("studentfj", 0);
                }

            } else {
                ability_object.put("studentwhq", 0);
                ability_object.put("studentFState", "");
                ability_object.put("studentFDate", "");
                ability_object.put("studentFMode", "");
                ability_object.put("studentFAbilityInf", "");
                ability_object.put("studentfj", 0);
                ability_object.put("studentFUrl", "");
            }
            
            



//            }
            // 返回值
            object.put("info", ability_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

}