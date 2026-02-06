package fun.server.controller.course;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.model.customQuery.projectcourse.ProjectCourseCs;
import fun.server.model.customQuery.projectcourse.ProjectCoursedata;
import fun.server.model.customQuery.trainingprogram.CouserVersionData;
import fun.server.model.customQuery.trainingprogram.TrainingprogramversionCS;
import fun.server.service.*;
import fun.tools.ExcelSelectTools;
import fun.tools.ParamTools;
import fun.tools.SnowflakeIdWorker;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 课程管理 相关业务处理
 */
@RestController
@RequestMapping("/s/course")
public class Course {

    private final TCourseService tCourseService;
    private final TUserService tUserService;

    private final TAbilityService tAbilityService;

    private final TAbilityLevelService tAbilityLevelService;

    private final TCourseStudentService tCourseStudentService;

    private final TStudentAbilityService tStudentAbilityService;

    private final TCourseNatureService tCourseNatureService;

    private final TCourseCategoryService tCourseCategoryService;

    private final TCourseTextbookService tCourseTextbookService;
    private final TCourseTargetService tCourseTargetService;
    private final TCourseGraduationService tCourseGraduationService;
    private final TCourseAssessmentMethodService tCourseAssessmentMethodService;

    private final TTrainingProgramService tTrainingProgramService;


    private final TCourseMajorService tCourseMajorService;

    private final TCoursePrerequisitesService tCoursePrerequisitesService;

    private final TMajorService tMajorService;

    private final TCollegeService tCollegeService;

    private final TCourseInstitutionService tCourseInstitutionService;


    private final TCourseResourcefileService tCourseResourcefileService;

    private final TProjectCourseService tProjectCourseService;
    private final TCourseSemesterService tCourseSemesterService;

    @Value("${studentmb.path}")
    private String studentmb;


    @Value("${studentabilitymb.path}")
    private String studentabilitymb;

    public Course(TCourseService tCourseService, TUserService tUserService, TAbilityService tAbilityService, TAbilityLevelService tAbilityLevelService, TCourseStudentService tCourseStudentService, TStudentAbilityService tStudentAbilityService, TCourseNatureService tCourseNatureService, TCourseCategoryService tCourseCategoryService, TCourseTextbookService tCourseTextbookService, TCourseTargetService tCourseTargetService, TCourseGraduationService tCourseGraduationService, TCourseAssessmentMethodService tCourseAssessmentMethodService, TTrainingProgramService tTrainingProgramService, TCourseMajorService tCourseMajorService, TCoursePrerequisitesService tCoursePrerequisitesService, TMajorService tMajorService, TCollegeService tCollegeService, TCourseInstitutionService tCourseInstitutionService, TCourseResourcefileService tCourseResourcefileService, TProjectCourseService tProjectCourseService, TCourseSemesterService tCourseSemesterService) {
        this.tCourseService = tCourseService;
        this.tUserService = tUserService;
        this.tAbilityService = tAbilityService;
        this.tAbilityLevelService = tAbilityLevelService;
        this.tCourseStudentService = tCourseStudentService;
        this.tStudentAbilityService = tStudentAbilityService;
        this.tCourseNatureService = tCourseNatureService;
        this.tCourseCategoryService = tCourseCategoryService;
        this.tCourseTextbookService = tCourseTextbookService;
        this.tCourseTargetService = tCourseTargetService;
        this.tCourseGraduationService = tCourseGraduationService;
        this.tCourseAssessmentMethodService = tCourseAssessmentMethodService;
        this.tTrainingProgramService = tTrainingProgramService;
        this.tCourseMajorService = tCourseMajorService;
        this.tCoursePrerequisitesService = tCoursePrerequisitesService;
        this.tMajorService = tMajorService;
        this.tCollegeService = tCollegeService;
        this.tCourseInstitutionService = tCourseInstitutionService;
        this.tCourseResourcefileService = tCourseResourcefileService;
        this.tProjectCourseService = tProjectCourseService;
        this.tCourseSemesterService = tCourseSemesterService;
    }

    /**
     * 获取课程信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycourse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycourse(HttpServletRequest request)
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
        String ftype = jsonParam.getString("ftype");
        String fnature = jsonParam.getString("fnature");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            // 获取数据库记录
            JSONArray course_Array = new JSONArray();
            // 查询条件
            TCourseExample TCourseExample = new TCourseExample();
            TCourseExample.Criteria criteria = TCourseExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }
            if (fno != null && !fno.equals("")) {
                criteria.andFnoLike("%" + fno + "%");
            }
            if (ftype != null && !ftype.equals("") && !ftype.equals("1")) {
                ftype = ftype == null ? "0" : ParamTools.getdeParam(ftype);
                criteria.andFtypeEqualTo(Long.parseLong(ftype));
            }
            if (fnature != null && !fnature.equals("")) {
                fnature = fnature == null ? "0" : ParamTools.getdeParam(fnature);
                if (!fnature.equals("-1"))
                    criteria.andFcnatureEqualTo(Long.parseLong(fnature));
            }

            if (jsonParam.getString("fstate") != null && !jsonParam.getString("fstate").equals("")) {
                criteria.andFstateEqualTo(Integer.parseInt(jsonParam.getString("fstate")));
            }
            criteria.andFvalidEqualTo(1);
//            System.out.println(name);
//            System.out.println(fno);
//            System.out.println(ftype);
//            System.out.println(fnature);
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                TCourseExample.setOrderByClause(orderSql.substring(1));
            } else {
                TCourseExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TCourse> coursePageInfo = tCourseService.findByExampleMapper(TCourseExample, (page - 1) * results, results);
            List<TCourse> course_list = coursePageInfo.getList();
//            System.out.println("course_list:" + course_list.size());
            for (TCourse course : course_list) {
                JSONObject course_object = new JSONObject();
                course_object.put("key", ParamTools.getEnParam(course.getFkeyid().toString()));
                course_object.put("FAbilityID", course.getFabilityid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(course.getFabilityid().toString()));
                course_object.put("FAbilityLevelID", course.getFabilitylevelid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(course.getFabilitylevelid().toString()));
                course_object.put("FType", course.getFtype() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(course.getFtype().toString()));
                course_object.put("FCNature", course.getFcnature() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(course.getFcnature().toString()));
                course_object.put("FParentId", course.getFparentid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(course.getFparentid().toString()));
                if (dataall == 1) {
                    course_object.put("FName", course.getFname() == null ? "" : course.getFname());
                    course_object.put("FNo", course.getFno() == null ? "" : course.getFno());
                    course_object.put("FCon", course.getFcon() == null ? "" : course.getFcon());
                    course_object.put("FPassScore", course.getFpassscore() == null ? "" : course.getFpassscore());
                    course_object.put("FFullScore", course.getFfullscore() == null ? "" : course.getFfullscore());
//                    TAbility ability = tAbilityService.findById(course.getFabilityid());
//                    course_object.put("abilityName", ability == null ? "" : ability.getFname());
//                    TAbilityLevel tAbilityLevel = tAbilityLevelService.findById(course.getFabilitylevelid());
//                    course_object.put("tAbilityLevelName", tAbilityLevel == null ? "" : tAbilityLevel.getFname());
                    course_object.put("FTotalHours", course.getFtotalhours() == null ? "" : course.getFtotalhours());
                    course_object.put("FCredits", course.getFcredits() == null ? "" : course.getFcredits());
                    course_object.put("FMajor", course.getFmajor() == null ? "" : course.getFmajor());
                    course_object.put("FPrerequisites", course.getFprerequisites() == null ? "" : course.getFprerequisites());
                    course_object.put("FTeachingRequirements", course.getFteachingrequirements() == null ? "" : course.getFteachingrequirements());
                    course_object.put("FCID", course.getFcid());
                    course_object.put("FUID", course.getFuid());
                    course_object.put("FCDATE", course.getFcdate() == null ? "" : dateFormat.format(course.getFcdate()));

                    course_object.put("FUDATE", course.getFudate() == null ? "" : dateFormat.format(course.getFudate()));

                    course_object.put("FWeeklyStudyHours", course.getFweeklystudyhours() == null ? "" : course.getFweeklystudyhours());
                    course_object.put("FTheoreticalStudyHours", course.getFtheoreticalstudyhours() == null ? "" : course.getFtheoreticalstudyhours());
                    course_object.put("FPracticalStudyHours", course.getFpracticalstudyhours() == null ? "" : course.getFpracticalstudyhours());
                    course_object.put("FKKQJ", course.getFkkqj());
                    course_object.put("FKKCJ", course.getFkkcj());
                    course_object.put("FDXQ", course.getFdxq());
                    course_object.put("FJYXNXQ", course.getFjyxnxq() == null ? "" : course.getFjyxnxq());

                    if (course.getFtype() == null) {
                        course_object.put("FTypeName", "");
                    } else {
                        TCourseCategory tCourseCategory = tCourseCategoryService.findById(course.getFtype());
                        course_object.put("FTypeName", getPName(tCourseCategory.getFpid(), tCourseCategory.getFname()));
                    }
                    if (course.getFcnature() == null) {
                        course_object.put("FCNatureName", "");
                    } else {
                        TCourseNature courseNature = tCourseNatureService.findById(course.getFcnature());
                        course_object.put("FCNatureName", courseNature == null ? "" : courseNature.getFname());
                    }
                    course_object.put("FEditionNo", course.getFeditionno());

//
//                    course_object.put("FKKDW", course.getFkkdw() == null ? ParamTools.getEnParam("-1") : ParamTools.getEnParam(course.getFkkdw().toString())); //开课单位 ，学院ID
//                    TCollege tCollegeServiceById = tCollegeService.findById(course.getFkkdw());
//                    course_object.put("FKKDWName", tCollegeServiceById ==null ? "" : tCollegeServiceById.getFcollegename());

                    course_object.put("FJYXQ", course.getFjyxq());
                    course_object.put("FJCZYK", course.getFjczyk());
                    course_object.put("FEdition", course.getFedition());
                    course_object.put("FValid", course.getFvalid());
                    course_object.put("FYWName", course.getFywname() == null ? "" : course.getFywname());
                    course_object.put("FSJZS", course.getFsjzs() == null ? "" : course.getFsjzs());
                    course_object.put("FIstk", course.getFistk());


                } else {
                    course_object.put("FName", "*****");
                    course_object.put("FNo", "*****");
                    course_object.put("FCon", "*****");
                    course_object.put("FPassScore", "*****");
                    course_object.put("FFullScore", "*****");
                    course_object.put("FTotalHours", "*****");
                    course_object.put("FCredits", "*****");
                    course_object.put("FMajor", "*****");
                    course_object.put("FPrerequisites", "*****");
                    course_object.put("FTeachingRequirements", "*****");
                    course_object.put("FCID", "*****");
                    course_object.put("FUID", "*****");
                    course_object.put("FCDATE", "*****");
                    course_object.put("FUDATE", "*****");
                    course_object.put("FWeeklyStudyHours", "*****");
                    course_object.put("FTheoreticalStudyHours", "*****");
                    course_object.put("FPracticalStudyHours", "*****");
                    course_object.put("FKKQJ", "*****");
                    course_object.put("FKKCJ", "*****");
                    course_object.put("FDXQ", "*****");
                    course_object.put("FJYXNXQ", "*****");

                    course_object.put("FEditionNo", "*****");
                    course_object.put("FEdition", "*****");
                    course_object.put("FValid", "*****");
                    course_object.put("FYWName", "*****");
                    course_object.put("FSJZS", "*****");
                    course_object.put("FIstk", "*****");
                }

                course_object.put("FState", course.getFstate());
                course_Array.add(course_object);
            }
            // 返回值
            object.put("list", course_Array);
            object.put("total", coursePageInfo.getTotal()); // 总行数
            object.put("page", coursePageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取课程信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatacourseSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatacourseSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray course_Array = new JSONArray();
            TCourseExample courseExample = new TCourseExample();
            TCourseExample.Criteria criteria = courseExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            courseExample.setOrderByClause("FName asc");
            List<TCourse> course_list = tCourseService.findByExample(courseExample);
            for (TCourse course : course_list) {
                JSONObject course_object = new JSONObject();
                course_object.put("id", ParamTools.getEnParam(course.getFkeyid().toString()));
                course_object.put("text", course.getFname());
                course_Array.add(course_object);
            }
            // 返回值

            object.put("list", course_Array);
            object.put("results", course_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取课程信息(下拉列表)——去掉自身
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatacourseSelectzs", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatacourseSelectzs(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        String FCourseID = request.getParameter("FCourseID");
        try {
            // 获取数据库记录
            JSONArray course_Array = new JSONArray();
            TCourseExample courseExample = new TCourseExample();
            TCourseExample.Criteria criteria = courseExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }


            if (FCourseID != null && !FCourseID.equals("")) {
                FCourseID = FCourseID == null ? "0" : ParamTools.getdeParam(FCourseID);
                criteria.andFkeyidNotEqualTo(Long.parseLong(FCourseID));
            }
            criteria.andFstateGreaterThan(0);
            criteria.andFvalidEqualTo(1);
            courseExample.setOrderByClause("FName asc");
            List<TCourse> course_list = tCourseService.findByExample(courseExample);
            for (TCourse course : course_list) {
                JSONObject course_object = new JSONObject();
                course_object.put("id", ParamTools.getEnParam(course.getFkeyid().toString()));
                course_object.put("text", course.getFname());
                course_Array.add(course_object);
            }
            // 返回值

            object.put("list", course_Array);
            object.put("results", course_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取课程信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycourseInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycourseInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询课程信息
            TCourse course = tCourseService.findById(key);
            JSONObject course_object = new JSONObject();
            course_object.put("key", ParamTools.getEnParam(course.getFkeyid().toString()));
            course_object.put("FAbilityID", course.getFabilityid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(course.getFabilityid().toString()));
            course_object.put("FAbilityLevelID", course.getFabilitylevelid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(course.getFabilitylevelid().toString()));
            course_object.put("FParentId", course.getFparentid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(course.getFparentid().toString()));
            course_object.put("FName", course.getFname() == null ? "" : course.getFname());
            course_object.put("FNo", course.getFno() == null ? "" : course.getFno());
            course_object.put("FCon", course.getFcon() == null ? "" : course.getFcon());
            course_object.put("FPassScore", course.getFpassscore() == null ? "" : course.getFpassscore());
            course_object.put("FFullScore", course.getFfullscore() == null ? "" : course.getFfullscore());
//            TAbility ability = tAbilityService.findById(course.getFabilityid());
//            course_object.put("FAbilityName", ability == null ? "" : ability.getFname());
//            TAbilityLevel tAbilityLevel = tAbilityLevelService.findById(course.getFabilitylevelid());
//            course_object.put("FAbilityLevelName", tAbilityLevel == null ? "" : tAbilityLevel.getFname());
            course_object.put("FTotalHours", course.getFtotalhours() == null ? "" : course.getFtotalhours());
            course_object.put("FCredits", course.getFcredits() == null ? "" : course.getFcredits());
            course_object.put("FMajor", course.getFmajor() == null ? "" : course.getFmajor());
            course_object.put("FPrerequisites", course.getFprerequisites() == null ? "" : course.getFprerequisites());
            course_object.put("FTeachingRequirements", course.getFteachingrequirements() == null ? "" : course.getFteachingrequirements());
            course_object.put("FWeeklyStudyHours", course.getFweeklystudyhours() == null ? "" : course.getFweeklystudyhours());
            course_object.put("FTheoreticalStudyHours", course.getFtheoreticalstudyhours() == null ? "" : course.getFtheoreticalstudyhours());
            course_object.put("FPracticalStudyHours", course.getFpracticalstudyhours() == null ? "" : course.getFpracticalstudyhours());
            course_object.put("FKKQJ", course.getFkkqj());
            course_object.put("FKKCJ", course.getFkkcj());
            course_object.put("FDXQ", course.getFdxq());
            course_object.put("FJYXNXQ", course.getFjyxnxq() == null ? "" : course.getFjyxnxq());
            course_object.put("FTypeID", course.getFtype() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(course.getFtype().toString()));
            course_object.put("FCNature", course.getFcnature() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(course.getFcnature().toString()));
            if(course.getFtype() == null) {
                course_object.put("FTypeName","");
            }else{
                TCourseCategory tCourseCategory = tCourseCategoryService.findById(course.getFtype());
                course_object.put("FTypeName", getPName(tCourseCategory.getFpid(), tCourseCategory.getFname()));
            }
            if(course.getFcnature() == null) {
                course_object.put("FCNatureName", "");
            }else{
                TCourseNature courseNature = tCourseNatureService.findById(course.getFcnature());
                course_object.put("FCNatureName", courseNature == null ? "" : courseNature.getFname());
            }

            course_object.put("FCID", course.getFcid());
            course_object.put("FUID", course.getFuid());
            course_object.put("FCDATE", course.getFcdate());
            course_object.put("FUDATE", course.getFudate());
            course_object.put("FState", course.getFstate());
            course_object.put("FKKDW", course.getFkkdw() == null ? ParamTools.getEnParam("-1") : ParamTools.getEnParam(course.getFkkdw().toString())); //开课单位 ，学院ID
            TCollege tCollegeServiceById = tCollegeService.findById(course.getFkkdw());
            course_object.put("FKKDWName", tCollegeServiceById == null ? "" : tCollegeServiceById.getFcollegename());

            course_object.put("FEditionNo", course.getFeditionno());
            course_object.put("FEdition", course.getFedition());
            course_object.put("FValid", course.getFvalid());
            course_object.put("FYWName", course.getFywname() == null ? "" : course.getFywname());
            course_object.put("FSJZS", course.getFsjzs() == null ? "" : course.getFsjzs());
            course_object.put("FIstk", course.getFistk());

            course_object.put("FJYXQ", course.getFjyxq());
            course_object.put("FJCZYK", course.getFjczyk());


            TCourseSemesterExample tCourseSemesterExample = new TCourseSemesterExample();
            tCourseSemesterExample.createCriteria().andFjnameEqualTo(course.getFjyxnxq() + "-" + course.getFjyxq());
            List<TCourseSemester> semesterList = tCourseSemesterService.findByExample(tCourseSemesterExample);
            if (semesterList.size() > 0) {
                course_object.put("FKKXQ", semesterList.get(0).getFkkxq());
            } else {
                course_object.put("FKKXQ", "");
            }

            //适用专业
            JSONArray major_Array = null;
            try {
                major_Array = new JSONArray();
                TCourseMajorExample tCourseMajorExample = new TCourseMajorExample();
                tCourseMajorExample.createCriteria().andFcourseidEqualTo(key);

                List<TCourseMajor> list = tCourseMajorService.findByExample(tCourseMajorExample);
                for (TCourseMajor tCourseMajor : list) {
                    TMajor tMajor = tMajorService.findById(tCourseMajor.getFmajorid());
                    TCollege college = tCollegeService.findById(tMajor.getFcollegeid());
                    if (tMajor != null) {
                        JSONObject roleObject = new JSONObject();
                        roleObject.put("id", ParamTools.getEnParam(tMajor.getFkeyid().toString()));
                        roleObject.put("name", college.getFcollegename() + "/" + tMajor.getFmajorname());
                        major_Array.add(roleObject);
                    }
                }
            } catch (Exception e) {

            }
            course_object.put("majorlist", major_Array);
            JSONArray prerequisite_Array = null;


            try {
                //先修课程
                prerequisite_Array = new JSONArray();
                TCoursePrerequisitesExample tCoursePrerequisitesExample = new TCoursePrerequisitesExample();
                tCoursePrerequisitesExample.createCriteria().andFcourseidEqualTo(key);
                List<TCoursePrerequisites> list2 = tCoursePrerequisitesService.findByExample(tCoursePrerequisitesExample);
                for (TCoursePrerequisites tCoursePrerequisites : list2) {
                    TCourse tCourse = tCourseService.findById(tCoursePrerequisites.getFprereqid());
                    if (tCourse != null) {
                        JSONObject roleObject = new JSONObject();
                        roleObject.put("id", ParamTools.getEnParam(tCourse.getFkeyid().toString()));
                        roleObject.put("name", tCourse.getFname());
                        prerequisite_Array.add(roleObject);
                    }
                }
            } catch (Exception e) {
            }
            course_object.put("prerequisitelist", prerequisite_Array);


            try {
                //开课单位
                prerequisite_Array = new JSONArray();
                TCourseInstitutionExample tCourseInstitutionExample = new TCourseInstitutionExample();
                tCourseInstitutionExample.createCriteria().andFcourseidEqualTo(key);
                List<TCourseInstitution> list = tCourseInstitutionService.findByExample(tCourseInstitutionExample);
                for (TCourseInstitution tCourseInstitution : list) {
                    TCollege college = tCollegeService.findById(tCourseInstitution.getFkkdw());
                    if (college != null) {
                        JSONObject roleObject = new JSONObject();
                        roleObject.put("id", ParamTools.getEnParam(college.getFkeyid().toString()));
                        roleObject.put("name", college.getFcollegename());
                        prerequisite_Array.add(roleObject);
                    }
                }
            } catch (Exception e) {
            }
            course_object.put("kkdwlist", prerequisite_Array);


            // 返回值
            object.put("info", course_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    private String getPName(Long pid, String oldname) {
        String newname = "";
        if (pid == 1) {
            newname = oldname;
        } else {
            TCourseCategory courseCategory = tCourseCategoryService.findById(pid);
            if (courseCategory != null) {
                newname = courseCategory.getFname() + "/" + oldname;
                getPName(courseCategory.getFpid(), newname);
            }

        }
        return newname;
    }

    /**
     * 添加课程信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加课程信息")
    @ResponseBody
    @RequestMapping(value = "/addcourse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addcourse(HttpServletRequest request, Pageable pageable)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FName = jsonParam.getString("FName");
        String FNo = jsonParam.getString("FNo");
        String FCon = jsonParam.getString("FCon");
        String FPassScore = jsonParam.getString("FPassScore");
        String FFullScore = jsonParam.getString("FFullScore");
//        String FAbilityID = jsonParam.getString("FAbilityID");
//        String FAbilityLevelID = jsonParam.getString("FAbilityLevelID");

        String FTotalHours = jsonParam.getString("FTotalHours");
        String FCredits = jsonParam.getString("FCredits");
        String FMajor = jsonParam.getString("FMajor");
        String FPrerequisites = jsonParam.getString("FPrerequisites");
        String FTeachingRequirements = jsonParam.getString("FTeachingRequirements");


        String FType = jsonParam.getString("FType");
        String FCNature = jsonParam.getString("FCNature");
        String FWeeklyStudyHours = jsonParam.getString("FWeeklyStudyHours");
        String FTheoreticalStudyHours = jsonParam.getString("FTheoreticalStudyHours");
        String FPracticalStudyHours = jsonParam.getString("FPracticalStudyHours");
        String FKKQJ = jsonParam.getString("FKKQJ");
        String FKKCJ = jsonParam.getString("FKKCJ");
        String FDXQ = jsonParam.getString("FDXQ");
        String FJYXNXQ = jsonParam.getString("FJYXNXQ");

        Integer FJYXQ = jsonParam.getInteger("FJYXQ");
        Integer FJCZYK = jsonParam.getInteger("FJCZYK");

        String FYWName = jsonParam.getString("FYWName");
        String FSJZS = jsonParam.getString("FSJZS");

        int FIstk = jsonParam.getInteger("FIstk");

        String FMajorlist = jsonParam.getString("FMajorlist");
        String FKKDWlist = jsonParam.getString("FKKDWlist");
        String FPrerequisiteslist = jsonParam.getString("FPrerequisiteslist");
//        String FKKDW = jsonParam.getString("FKKDW");


        try {
            String CookiesLogincourseID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogincourseID != null && !CookiesLogincourseID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLogincourseID);
            }

            if (FPassScore != null && !FPassScore.equals("")) {
                FPassScore = FPassScore;
            } else {
                FPassScore = "0";
            }
            if (FFullScore != null && !FFullScore.equals("")) {
                FFullScore = FFullScore;
            } else {
                FFullScore = "0";
            }

            if (FType != null && !FType.equals("")) {
                FType = ParamTools.getdeParam(FType);
            } else {
                FType = "0";
            }

            if (FCNature != null && !FCNature.equals("")) {
                FCNature = ParamTools.getdeParam(FCNature);
            } else {
                FCNature = "0";
            }
            int fkkqj = 0;
            if (FKKQJ != null && !FKKQJ.equals("")) {
                fkkqj = jsonParam.getInteger("FKKQJ");
            }
            int fkkcj = 0;
            if (FKKCJ != null && !FKKCJ.equals("")) {
                fkkcj = jsonParam.getInteger("FKKCJ");
            }
            int fdxq = 0;
            if (FDXQ != null && !FDXQ.equals("")) {
                fdxq = jsonParam.getInteger("FDXQ");
            }

//            FKKDW = FKKDW == null ? "-1" : ParamTools.getdeParam(FKKDW);

            TCourseExample courseExample = new TCourseExample();
            TCourseExample.Criteria criteria = courseExample.createCriteria();
            criteria.andFnoEqualTo(FNo);
            List<TCourse> courseList = tCourseService.findByExample(courseExample);
            if (courseList.size() == 0) {
//                FAbilityID = FAbilityID == null ? "0" : ParamTools.getdeParam(FAbilityID);
//                FAbilityLevelID = FAbilityLevelID == null ? "0" : ParamTools.getdeParam(FAbilityLevelID);
                // 新建课程信息
                TCourse course = new TCourse();
                course.setFname(FName);
                course.setFno(FNo);
                course.setFcon(FCon);
                course.setFpassscore(BigDecimal.valueOf(Float.parseFloat(FPassScore)));
                course.setFfullscore(BigDecimal.valueOf(Float.parseFloat(FFullScore)));
//                course.setFabilityid(Long.parseLong(FAbilityID));
//                course.setFabilitylevelid(Long.parseLong(FAbilityLevelID));
                course.setFtotalhours(FTotalHours);
                course.setFcredits(Float.parseFloat(FCredits));
                course.setFmajor(FMajor);
                course.setFprerequisites(FPrerequisites);
                course.setFteachingrequirements(FTeachingRequirements);
                course.setFcid(Long.parseLong(uid));
                course.setFcdate(new Date());
//                course.setFkkdw(Long.parseLong(FKKDW));
                course.setFtype(Long.parseLong(FType));
                course.setFcnature(Long.parseLong(FCNature));
                course.setFweeklystudyhours(FWeeklyStudyHours);
                course.setFtheoreticalstudyhours(FTheoreticalStudyHours);
                course.setFpracticalstudyhours(FPracticalStudyHours);
                course.setFkkqj(fkkqj);
                course.setFkkcj(fkkcj);
                course.setFdxq(fdxq);
                course.setFjyxnxq(FJYXNXQ);

                course.setFedition("V1.0");
                course.setFsjzs(FSJZS);
                course.setFistk(FIstk);
                course.setFvalid(1);
                course.setFywname(FYWName);
                course.setFjczyk(FJCZYK);
                course.setFjyxq(FJYXQ);
                tCourseService.save(course);


                //适用专业
                if (FMajorlist != null && !FMajorlist.equals("")) {
                    //增加新选的
                    JSONArray major_Array = JSONArray.parseArray(FMajorlist);
                    for (Object majorid : major_Array) {
                        Long fmajorId = Long.parseLong(ParamTools.getdeParam(majorid.toString()));

                        TCourseMajorExample tCourseMajorExample = new TCourseMajorExample();
                        TCourseMajorExample.Criteria criteria1 = tCourseMajorExample.createCriteria();
                        criteria1.andFcourseidEqualTo(course.getFkeyid());
                        criteria1.andFmajoridEqualTo(fmajorId);
                        List<TCourseMajor> majorList = tCourseMajorService.findByExample(tCourseMajorExample);

                        if (majorList.size() <= 0) {
                            TCourseMajor tCourseMajor = new TCourseMajor();
                            tCourseMajor.setFcid(Long.parseLong(uid));
                            tCourseMajor.setFcdate(new Date());
                            tCourseMajor.setFcourseid(course.getFkeyid());
                            tCourseMajor.setFmajorid(fmajorId);
                            tCourseMajorService.save(tCourseMajor);
                        }
                    }
                }

                if (FKKDWlist != null && !FKKDWlist.equals("")) {
                    //增加新选的
                    JSONArray kkdw_Array = JSONArray.parseArray(FKKDWlist);
                    for (Object kkdwid : kkdw_Array) {
                        Long fkkdwId = Long.parseLong(ParamTools.getdeParam(kkdwid.toString()));
                        TCourseInstitutionExample tCourseInstitutionExample = new TCourseInstitutionExample();
                        TCourseInstitutionExample.Criteria criteria1 = tCourseInstitutionExample.createCriteria();
                        criteria1.andFcourseidEqualTo(course.getFkeyid());
                        criteria1.andFkkdwEqualTo(fkkdwId);
                        List<TCourseInstitution> courseInstitutionList = tCourseInstitutionService.findByExample(tCourseInstitutionExample);
                        if (courseInstitutionList.size() <= 0) {
                            TCourseInstitution tCourseInstitution = new TCourseInstitution();
                            tCourseInstitution.setFcid(Long.parseLong(uid));
                            tCourseInstitution.setFcdate(new Date());
                            tCourseInstitution.setFcourseid(course.getFkeyid());
                            tCourseInstitution.setFkkdw(fkkdwId);
                            tCourseInstitutionService.save(tCourseInstitution);
                        }
                    }
                }

                //先修课程
                if (FPrerequisiteslist != null && !FPrerequisiteslist.equals("")) {
                    //增加新选的
                    JSONArray Prerequisites_Array = JSONArray.parseArray(FPrerequisiteslist);
                    for (Object prereqid : Prerequisites_Array) {
                        Long fprereqidId = Long.parseLong(ParamTools.getdeParam(prereqid.toString()));

                        TCoursePrerequisitesExample tCoursePrerequisitesExample = new TCoursePrerequisitesExample();
                        TCoursePrerequisitesExample.Criteria criteria1 = tCoursePrerequisitesExample.createCriteria();
                        criteria1.andFcourseidEqualTo(course.getFkeyid());
                        criteria1.andFprereqidEqualTo(fprereqidId);
                        List<TCoursePrerequisites> majorList = tCoursePrerequisitesService.findByExample(tCoursePrerequisitesExample);

                        if (majorList.size() <= 0) {
                            TCoursePrerequisites tCoursePrerequisites = new TCoursePrerequisites();
                            tCoursePrerequisites.setFcid(Long.parseLong(uid));
                            tCoursePrerequisites.setFcdate(new Date());
                            tCoursePrerequisites.setFcourseid(course.getFkeyid());
                            tCoursePrerequisites.setFprereqid(fprereqidId);
                            tCoursePrerequisitesService.save(tCoursePrerequisites);
                        }
                    }
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
     * 修改课程信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改课程信息")
    @ResponseBody
    @RequestMapping(value = "/updatecourse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatecourse(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FName = jsonParam.getString("FName");
        String FNo = jsonParam.getString("FNo");
        String FCon = jsonParam.getString("FCon");
        String FPassScore = jsonParam.getString("FPassScore");
        String FFullScore = jsonParam.getString("FFullScore");
//        String FAbilityID = jsonParam.getString("FAbilityID");
//        String FAbilityLevelID = jsonParam.getString("FAbilityLevelID");
        String FTotalHours = jsonParam.getString("FTotalHours");
        String FCredits = jsonParam.getString("FCredits");
        String FMajor = jsonParam.getString("FMajor");
        String FPrerequisites = jsonParam.getString("FPrerequisites");
        String FTeachingRequirements = jsonParam.getString("FTeachingRequirements");

        String FType = jsonParam.getString("FType");
        String FCNature = jsonParam.getString("FCNature");
        String FWeeklyStudyHours = jsonParam.getString("FWeeklyStudyHours");
        String FTheoreticalStudyHours = jsonParam.getString("FTheoreticalStudyHours");
        String FPracticalStudyHours = jsonParam.getString("FPracticalStudyHours");
        String FKKQJ = jsonParam.getString("FKKQJ");
        String FKKCJ = jsonParam.getString("FKKCJ");
        String FDXQ = jsonParam.getString("FDXQ");
        String FJYXNXQ = jsonParam.getString("FJYXNXQ");
        Integer FJYXQ = jsonParam.getInteger("FJYXQ");
        Integer FJCZYK = jsonParam.getInteger("FJCZYK");

        String FYWName = jsonParam.getString("FYWName");
        String FSJZS = jsonParam.getString("FSJZS");
//        String FKKDW = jsonParam.getString("FKKDW");
        int FIstk = jsonParam.getInteger("FIstk");

        String FMajorlist = jsonParam.getString("FMajorlist");
        String FPrerequisiteslist = jsonParam.getString("FPrerequisiteslist");
        String FKKDWlist = jsonParam.getString("FKKDWlist");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            TCourseExample courseExample = new TCourseExample();
            TCourseExample.Criteria criteria = courseExample.createCriteria();
            criteria.andFnoEqualTo(FNo);
            criteria.andFkeyidNotEqualTo(key);
            criteria.andFvalidEqualTo(1);
            List<TCourse> courseList = tCourseService.findByExample(courseExample);
            if (courseList.size() == 0) {

                if (FPassScore != null && !FPassScore.equals("")) {
                    FPassScore = FPassScore;
                } else {
                    FPassScore = "0";
                }
                if (FFullScore != null && !FFullScore.equals("")) {
                    FFullScore = FFullScore;
                } else {
                    FFullScore = "0";
                }
                if (FType != null && !FType.equals("")) {
                    FType = ParamTools.getdeParam(FType);
                } else {
                    FType = "0";
                }
                if (FCNature != null && !FCNature.equals("")) {
                    FCNature = ParamTools.getdeParam(FCNature);
                } else {
                    FCNature = "0";
                }
                int fkkqj = 0;
                if (FKKQJ != null && !FKKQJ.equals("")) {
                    fkkqj = jsonParam.getInteger("FKKQJ");
                }
                int fkkcj = 0;
                if (FKKCJ != null && !FKKCJ.equals("")) {
                    fkkcj = jsonParam.getInteger("FKKCJ");
                }
                int fdxq = 0;
                if (FDXQ != null && !FDXQ.equals("")) {
                    fdxq = jsonParam.getInteger("FDXQ");
                }
//                FAbilityID = FAbilityID == null ? "0" : ParamTools.getdeParam(FAbilityID);
//                FAbilityLevelID = FAbilityLevelID == null ? "0" : ParamTools.getdeParam(FAbilityLevelID);
                String CookiesLogincourseID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogincourseID != null && !CookiesLogincourseID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogincourseID);
                }

//                FKKDW = FKKDW == null ? "-1" : ParamTools.getdeParam(FKKDW);
                // 更新课程信息
                TCourse course = new TCourse();
                course.setFkeyid(key);
                course.setFname(FName);
                course.setFno(FNo);
                course.setFcon(FCon);
                course.setFpassscore(BigDecimal.valueOf(Float.parseFloat(FPassScore)));
                course.setFfullscore(BigDecimal.valueOf(Float.parseFloat(FFullScore)));
//                course.setFabilityid(Long.parseLong(FAbilityID));
//                course.setFabilitylevelid(Long.parseLong(FAbilityLevelID));
                course.setFtotalhours(FTotalHours);
                course.setFcredits(Float.parseFloat(FCredits));
                course.setFmajor(FMajor);
                course.setFprerequisites(FPrerequisites);
                course.setFteachingrequirements(FTeachingRequirements);
                course.setFuid(Long.parseLong(uid));
                course.setFudate(new Date());

                course.setFtype(Long.parseLong(FType));
                course.setFcnature(Long.parseLong(FCNature));
                course.setFweeklystudyhours(FWeeklyStudyHours);
                course.setFtheoreticalstudyhours(FTheoreticalStudyHours);
                course.setFpracticalstudyhours(FPracticalStudyHours);
                course.setFkkqj(fkkqj);
                course.setFkkcj(fkkcj);
                course.setFdxq(fdxq);
                course.setFjyxnxq(FJYXNXQ);
//                course.setFkkdw(Long.parseLong(FKKDW));
                course.setFsjzs(FSJZS);
                course.setFistk(FIstk);
                course.setFywname(FYWName);
                course.setFjczyk(FJCZYK);
                course.setFjyxq(FJYXQ);
                tCourseService.update(course);


                // 更新适用专业信息
                if (FMajorlist != null) {
                    if (FMajorlist.equals("")) {
                        //删除全部
                        TCourseMajorExample tCourseMajorExample = new TCourseMajorExample();
                        tCourseMajorExample.createCriteria().andFcourseidEqualTo(key);
                        tCourseMajorService.deleteByByExample(tCourseMajorExample);
                    } else {
                        //增加新选的
                        JSONArray major_Array = JSONArray.parseArray(FMajorlist);
                        List<Long> tempRoleID = new ArrayList<>();
                        for (Object majorId : major_Array) {
                            Long fMajorId = Long.parseLong(ParamTools.getdeParam(majorId.toString()));
                            TCourseMajorExample tCourseMajorExample = new TCourseMajorExample();
                            tCourseMajorExample.createCriteria().andFcourseidEqualTo(key).andFmajoridEqualTo(fMajorId);
                            List<TCourseMajor> list = tCourseMajorService.findByExample(tCourseMajorExample);
                            if (list.size() <= 0) {
                                TCourseMajor tCourseMajor = new TCourseMajor();
                                tCourseMajor.setFcid(Long.parseLong(uid));
                                tCourseMajor.setFcdate(new Date());
                                tCourseMajor.setFcourseid(key);
                                tCourseMajor.setFmajorid(fMajorId);
                                tCourseMajorService.save(tCourseMajor);
                            }
                            tempRoleID.add(fMajorId);
                        }
                        //删除多余的
                        TCourseMajorExample tCourseMajorExample = new TCourseMajorExample();
                        if (tempRoleID.size() > 0) {
                            tCourseMajorExample.or()
                                    .andFcourseidEqualTo(key)
                                    .andFmajoridNotIn(tempRoleID);
                        } else {
                            tCourseMajorExample.or().andFcourseidEqualTo(key);
                        }
                        tCourseMajorService.deleteByByExample(tCourseMajorExample);
                    }
                }


                // 更新开课专业
                if (FKKDWlist != null) {
                    if (FKKDWlist.equals("")) {
                        //删除全部
                        TCourseInstitutionExample tCourseInstitutionExample = new TCourseInstitutionExample();
                        tCourseInstitutionExample.createCriteria().andFcourseidEqualTo(key);
                        tCourseInstitutionService.deleteByByExample(tCourseInstitutionExample);
                    } else {
                        //增加新选的
                        JSONArray kkdw_Array = JSONArray.parseArray(FKKDWlist);
                        List<Long> tempRoleID = new ArrayList<>();
                        for (Object kkdwId : kkdw_Array) {
                            Long fkkdwId = Long.parseLong(ParamTools.getdeParam(kkdwId.toString()));
                            TCourseInstitutionExample tCourseInstitutionExample = new TCourseInstitutionExample();
                            tCourseInstitutionExample.createCriteria().andFcourseidEqualTo(key).andFkkdwEqualTo(fkkdwId);
                            List<TCourseInstitution> list = tCourseInstitutionService.findByExample(tCourseInstitutionExample);
                            if (list.size() <= 0) {

                                TCourseInstitution tCourseInstitution = new TCourseInstitution();
                                tCourseInstitution.setFcid(Long.parseLong(uid));
                                tCourseInstitution.setFcdate(new Date());
                                tCourseInstitution.setFcourseid(course.getFkeyid());
                                tCourseInstitution.setFkkdw(fkkdwId);
                                tCourseInstitutionService.save(tCourseInstitution);
                            }
                            tempRoleID.add(fkkdwId);
                        }
                        //删除多余的
                        TCourseInstitutionExample tCourseInstitutionExample = new TCourseInstitutionExample();
                        if (tempRoleID.size() > 0) {
                            tCourseInstitutionExample.or()
                                    .andFcourseidEqualTo(key)
                                    .andFkkdwNotIn(tempRoleID);
                        } else {
                            tCourseInstitutionExample.or().andFcourseidEqualTo(key);
                        }
                        tCourseInstitutionService.deleteByByExample(tCourseInstitutionExample);
                    }
                }

                // 更新先修课程信息
                if (FPrerequisiteslist != null) {
                    if (FPrerequisiteslist.equals("")) {
                        //删除全部
                        TCourseMajorExample tCourseMajorExample = new TCourseMajorExample();
                        tCourseMajorExample.createCriteria().andFcourseidEqualTo(key);
                        tCourseMajorService.deleteByByExample(tCourseMajorExample);
                    } else {
                        //增加新选的
                        JSONArray prerequisites_Array = JSONArray.parseArray(FPrerequisiteslist);
                        List<Long> tempRoleID = new ArrayList<>();
                        for (Object prerequisitesId : prerequisites_Array) {
                            Long fPrerequisitesId = Long.parseLong(ParamTools.getdeParam(prerequisitesId.toString()));
                            TCoursePrerequisitesExample tCoursePrerequisitesExample = new TCoursePrerequisitesExample();
                            tCoursePrerequisitesExample.createCriteria().andFcourseidEqualTo(key).andFprereqidEqualTo(fPrerequisitesId);
                            List<TCoursePrerequisites> list = tCoursePrerequisitesService.findByExample(tCoursePrerequisitesExample);
                            if (list.size() <= 0) {
                                TCoursePrerequisites tCoursePrerequisites = new TCoursePrerequisites();
                                tCoursePrerequisites.setFcid(Long.parseLong(uid));
                                tCoursePrerequisites.setFcdate(new Date());
                                tCoursePrerequisites.setFcourseid(key);
                                tCoursePrerequisites.setFprereqid(fPrerequisitesId);
                                tCoursePrerequisitesService.save(tCoursePrerequisites);
                            }
                            tempRoleID.add(fPrerequisitesId);
                        }
                        //删除多余的
                        TCoursePrerequisitesExample tCoursePrerequisitesExample = new TCoursePrerequisitesExample();
                        if (tempRoleID.size() > 0) {
                            tCoursePrerequisitesExample.or()
                                    .andFcourseidEqualTo(key)
                                    .andFprereqidNotIn(tempRoleID);
                        } else {
                            tCoursePrerequisitesExample.or().andFcourseidEqualTo(key);
                        }
                        tCoursePrerequisitesService.deleteByByExample(tCoursePrerequisitesExample);
                    }
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
     * 删除课程信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除课程信息")
    @ResponseBody
    @RequestMapping(value = "/delcourse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delcourse(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLogincourseID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogincourseID != null && !CookiesLogincourseID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));


            TCourseStudentExample tCourseStudentExample = new TCourseStudentExample();
            TCourseStudentExample.Criteria criteria = tCourseStudentExample.createCriteria();
            criteria.andFcourseidEqualTo(Long.parseLong(id));
            List<TCourseStudent> studentList = tCourseStudentService.findByExample(tCourseStudentExample);
            if (studentList.size() > 0) {
                object.put("result", "error");
                object.put("error", "当前课程有学生记录，不能删除！");
            } else {
                tCourseService.deleteById(Long.parseLong(id));
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
     * 变更课程信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statecourse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statecourse(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLogincourseID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogincourseID != null && !CookiesLogincourseID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            state = state.equals("1") ? "0" : "1";
            TCourse course = new TCourse();
//            course.setFkeyid(Long.parseLong(id));
            course.setFuid(Long.parseLong(uid));
            course.setFudate(new Date());
            course.setFstate(Integer.valueOf(state));
            TCourseExample tCourseExample = new TCourseExample();
            tCourseExample.createCriteria().andFkeyidEqualTo(Long.parseLong(id));
            tCourseService.updateByExample(course, tCourseExample);
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
     * 模板导入_新
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/courseupmoban_new", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String courseupmoban_new(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
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
                        String FName = "";
                        String FYWName = "";
                        String FNo = "";
                        String FCon = ""; //描述
                        String FCNature = "";//性质
                        String FType = "";//类别
                        String FWeeklyStudyHours = "";//周学时
                        String FTheoreticalStudyHours = "";//理论学时
                        String FPracticalStudyHours = "";//实践学时
                        String FTotalHours = ""; //总学时
                        String FCredits = "";//学分
                        String FJYXNXQ = ""; //建议学年
                        String FJYXQ = ""; //建议学期
                        String FJCZYK = "";//课程类型 1= 基础课  2= 专业课


                        // 4.获取数据
                        // getLastRowNum() 获取一张sheet表中行的数量
                        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                            // 获取第i行的数据
                            XSSFRow row = sheet.getRow(i);

                            FName = "";
                            FYWName = "";
                            FNo = "";
                            FCon = ""; //描述
                            FCNature = "";//性质
                            FType = "";//类别
                            FWeeklyStudyHours = "";//周学时
                            FTheoreticalStudyHours = "";//理论学时
                            FPracticalStudyHours = "";//实践学时
                            FTotalHours = ""; //总学时
                            FCredits = "";//学分
                            FJYXNXQ = ""; //建议学年
                            FJYXQ = ""; //建议学期
                            FJCZYK = "";//课程类型 1= 基础课  2= 专业课

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
                                if (i == 0 && j == 0 && !cell.equals("*课程编号")) {
                                    iserror = true;
                                    break;
                                }
                                if (iserror == true) {
                                    break;
                                } else {

                                    if (i < 1) {
                                        break;
                                    }
                                    row.getCell(j).setCellType(CellType.STRING); // 设置单元格类型为STRING
                                    switch (j) {
                                        case 0:
                                            //
                                            FNo = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 1:
                                            //
                                            FName = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 2:
                                            //
                                            FYWName = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 3:
                                            //
                                            FCNature = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 4:
                                            //
                                            FType = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 5:
                                            //
                                            FWeeklyStudyHours = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 6:
                                            //
                                            FTheoreticalStudyHours = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 7:
                                            //
                                            FPracticalStudyHours = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
//                                        case 8:
//                                            //
//                                            FTotalHours = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
//                                            break;
                                        case 8:
                                            //
                                            FCredits = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 9:
                                            //
                                            FJYXNXQ = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 10:
                                            //
                                            FJYXQ = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 11:
                                            //
                                            FJCZYK = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 12:
                                            //
                                            FCon = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                    }
                                }
                                // 5.打印单元格数据
//                            System.out.print(cell + " | ");
                            }
                            JSONObject student_object = new JSONObject();
                            String error = "";
                            if (i > 0) {
                                Long ftypeid = 0l;
                                Long fcnatureid = 0l;
                                Integer fjck = 1;
                                if (FName.equals("") && FNo.equals("")) {
                                    break;
                                }
                                if (FName.equals(""))
                                    error += "课程名称未填写" + ";";
                                if (FNo.equals(""))
                                    error += "课程编号未填写" + ";";

                                if (FType.equals(""))
                                    error += "课程类别未填写" + ";";
                                else {
                                    if (FType.contains("-")) {
                                        // 获取 "-" 的位置
                                        int index = FType.indexOf("-");
                                        // 获取 "-" 左边的内容
                                        String leftPart = FType.substring(0, index);
                                        ftypeid = Long.parseLong(leftPart);
                                    } else {
                                        error += "课程类别选择错误" + ";";
                                    }
                                }
                                if (FCNature.equals(""))
                                    error += "课程性质未填写" + ";";
                                else {
                                    if (FCNature.contains("-")) {
                                        // 获取 "-" 的位置
                                        int index = FCNature.indexOf("-");
                                        // 获取 "-" 左边的内容
                                        String leftPart = FCNature.substring(0, index);
                                        fcnatureid = Long.parseLong(leftPart);
                                    } else {
                                        error += "课程性质选择错误" + ";";
                                    }
                                }
                                if (FJYXNXQ.equals(""))
                                    error += "建议学年未选择" + ";";
                                else {
                                    FJYXNXQ = FJYXNXQ.replaceAll("年", "");
                                }
                                if (FJCZYK.equals(""))
                                    fjck = 1;
                                else {
                                    if (FJCZYK.contains("-")) {
                                        // 获取 "-" 的位置
                                        int index = FJCZYK.indexOf("-");
                                        // 获取 "-" 左边的内容
                                        String leftPart = FJCZYK.substring(0, index);
                                        fjck = Integer.parseInt(leftPart);
                                    } else {
                                        error += "课程性质选择错误" + ";";
                                    }
                                }
                                if (FJYXQ.equals(""))
                                    error += "建议学期未选择" + ";";
                                if (FWeeklyStudyHours.equals(""))
                                    error += "周学时未填写" + ";";
                                if (FTheoreticalStudyHours.equals(""))
                                    error += "理论学时未填写" + ";";
                                if (FPracticalStudyHours.equals(""))
                                    error += "实践学时未填写" + ";";
//                                if(!FTotalHours.equals("")){
//                                    if(isNumeric(FTotalHours) == false){
//                                        error += "学时填写错误" + ";";
//                                    }
//                                }
                                try {
                                    FTotalHours = (Integer.parseInt(FTheoreticalStudyHours) + Integer.parseInt(FPracticalStudyHours)) + "";
                                } catch (NumberFormatException e) {
                                    error += "学时填写错误" + ";";
                                }
                                if (!FCredits.equals("")) {
                                    if (isNumeric(FCredits) == false) {
                                        error += "学分填写错误" + ";";
                                    }
                                }
                                if (!error.equals("")) {
                                    student_object.put("FName", FName == null ? "未填写" : FName);
                                    student_object.put("FNo", FNo == null ? "未填写" : FNo);
                                    student_object.put("error", error);
                                    student_Array.add(student_object);
                                    continue;
                                } else {
                                    TCourseExample tCourseExample = new TCourseExample();
                                    TCourseExample.Criteria criteria4 = tCourseExample.createCriteria();
                                    criteria4.andFnoEqualTo(FNo);
                                    List<TCourse> tCourseList = tCourseService.findByExample(tCourseExample);
                                    if (tCourseList.size() == 0) {
                                        TCourse tCourse = new TCourse();
                                        tCourse.setFname(FName);
                                        tCourse.setFno(FNo);
                                        tCourse.setFywname(FYWName);
                                        tCourse.setFstate(1);
                                        tCourse.setFcon(FCon);
                                        tCourse.setFtotalhours(FTotalHours);
                                        tCourse.setFcredits(Float.parseFloat(FCredits));
                                        tCourse.setFcid(Long.parseLong(uid));
                                        tCourse.setFcdate(new Date());
                                        tCourse.setFtype(ftypeid);
                                        tCourse.setFcnature(fcnatureid);
                                        tCourse.setFweeklystudyhours(FWeeklyStudyHours);
                                        tCourse.setFtheoreticalstudyhours(FTheoreticalStudyHours);
                                        tCourse.setFpracticalstudyhours(FPracticalStudyHours);
                                        tCourse.setFjyxnxq(FJYXNXQ);
                                        tCourse.setFjyxq(Integer.parseInt(FJYXQ));
                                        tCourse.setFjczyk(fjck);
                                        if (Integer.parseInt(FJYXQ) == 1) {
                                            tCourse.setFkkqj(1);
                                        } else if (Integer.parseInt(FJYXQ) == 2) {
                                            tCourse.setFkkcj(1);
                                        }
                                        if (Integer.parseInt(FJYXQ) == 3) {
                                            tCourse.setFdxq(1);
                                        }
                                        tCourseService.save(tCourse);
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

    public static boolean isInteger(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches("-?\\d+"); // 匹配正负整数
    }

    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches("-?\\d+(\\.\\d+)?"); // 匹配正负整数和小数
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
    @RequestMapping(value = "/courseupmoban", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String courseupmoban(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
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
                        String FName = "";
                        String FNo = "";
                        String FCon = "";
                        String FPassScore = "";
                        String FFullScore = "";
                        String FAbilityID = "";
                        String FAbilityLevelID = "";
                        String FTotalHours = "";
                        String FCredits = "";
                        String FMajor = "";
                        String FPrerequisites = "";
                        String FTeachingRequirements = "";


                        String FType = "";
                        String FCNature = "";
                        String FWeeklyStudyHours = "";
                        String FTheoreticalStudyHours = "";
                        String FPracticalStudyHours = "";
                        String FKKQJ = "";
                        String FKKCJ = "";
                        String FDXQ = "";
                        String FJYXNXQ = "";

                        // 4.获取数据
                        // getLastRowNum() 获取一张sheet表中行的数量
                        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                            // 获取第i行的数据
                            XSSFRow row = sheet.getRow(i);
                            FName = "";
                            FNo = "";
                            FCon = "";
                            FPassScore = "";
                            FFullScore = "";
                            FAbilityID = "";
                            FAbilityLevelID = "";
                            FTotalHours = "";
                            FCredits = "";
                            FMajor = "";
                            FPrerequisites = "";
                            FTeachingRequirements = "";
                            FType = "";
                            FCNature = "";
                            FWeeklyStudyHours = "";
                            FTheoreticalStudyHours = "";
                            FPracticalStudyHours = "";
                            FKKQJ = "";
                            FKKCJ = "";
                            FDXQ = "";
                            FJYXNXQ = "";
                            for (int j = 3; j < row.getLastCellNum(); j++) {
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
                                if (i == 0 && j == 3 && !cell.equals("*课程名称")) {
                                    iserror = true;
                                    break;
                                }
                                if (iserror == true) {
                                    break;
                                } else {

                                    if (i < 1) {
                                        break;
                                    }
                                    row.getCell(j).setCellType(CellType.STRING); // 设置单元格类型为STRING
                                    switch (j) {
                                        case 3:
                                            //课程名称
                                            FName = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 4:
                                            //课程代码
                                            FNo = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 5:
                                            //课程类别
                                            FType = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 6:
                                            //课程性质
                                            FCNature = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 7:
                                            //周学时
                                            FWeeklyStudyHours = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 8:
                                            //理论学时
                                            FTheoreticalStudyHours = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 9:
                                            //实践学时
                                            FPracticalStudyHours = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 13:
                                            if (row.getCell(j).getCellType() == CellType.NUMERIC) {
                                                double numericValue = row.getCell(j).getNumericCellValue();
                                                // 检查是否为整数（即小数部分是否为0）
                                                if (numericValue == Math.floor(numericValue)) {
                                                    int intValue = (int) numericValue;
                                                    FKKQJ = intValue + "";
                                                    // 使用intValue进行后续处理
                                                } else {
                                                    FKKQJ = String.valueOf(numericValue);
                                                }
                                            } else {
                                                //开课秋季
                                                FKKQJ = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            }
                                            break;
                                        case 14:
                                            if (row.getCell(j).getCellType() == CellType.NUMERIC) {
                                                double numericValue = row.getCell(j).getNumericCellValue();
                                                // 检查是否为整数（即小数部分是否为0）
                                                if (numericValue == Math.floor(numericValue)) {
                                                    int intValue = (int) numericValue;
                                                    FKKCJ = intValue + "";
                                                    // 使用intValue进行后续处理
                                                } else {
                                                    FKKCJ = String.valueOf(numericValue);
                                                }
                                            } else {
                                                //开课春季
                                                FKKCJ = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            }

                                            break;
                                        case 15:
                                            if (row.getCell(j).getCellType() == CellType.NUMERIC) {
                                                double numericValue = row.getCell(j).getNumericCellValue();
                                                // 检查是否为整数（即小数部分是否为0）
                                                if (numericValue == Math.floor(numericValue)) {
                                                    int intValue = (int) numericValue;
                                                    FDXQ = intValue + "";
                                                    // 使用intValue进行后续处理
                                                } else {
                                                    FDXQ = String.valueOf(numericValue);
                                                }
                                            } else {
                                                //短学期
                                                FDXQ = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            }

                                            break;
                                        case 16:
                                            //建议学年学期
                                            FJYXNXQ = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
//                                        case 12:
//                                            //能力
//                                            FAbilityID = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
//                                            break;
                                        case 12:
                                            //能力等级
                                            FAbilityLevelID = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 10:
                                            //总学时
                                            FTotalHours = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 11:
                                            //学分
                                            FCredits = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 17:
                                            //适合专业
                                            FMajor = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 18:
                                            FPrerequisites = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 19:
                                            FTeachingRequirements = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 20:
                                            FPassScore = row.getCell(j).getStringCellValue().toString().trim() == null ? "0" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 21:
                                            FFullScore = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 22:
                                            FCon = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;


                                    }
                                }
                                // 5.打印单元格数据
//                            System.out.print(cell + " | ");
                            }

                            JSONObject student_object = new JSONObject();
                            String error = "";
                            if (i > 0) {
                                Long ftabid = 0l;
                                Long ftablevelid = 0l;
                                Long fstudentid = 0l;

                                Long ftypeid = 0l;
                                Long fcnatureid = 0l;


                                if (FName.equals("") && FNo.equals("") && FAbilityID.equals("") && FAbilityLevelID.equals("")) {
                                    break;
                                }
                                if (FName.equals(""))
                                    error += "课程姓名未填写" + ";";
                                if (FNo.equals(""))
                                    error += "课程编号未填写" + ";";
//                                if (FAbilityID.equals(""))
//                                    error += "获取能力未填写" + ";";
//                                else
//                                    ftabid = Long.parseLong(ParamTools.getdeParam(FAbilityID));
                                if (FAbilityLevelID.equals(""))
                                    error += "获取能力等级未填写" + ";";
                                else
                                    ftablevelid = Long.parseLong(ParamTools.getdeParam(FAbilityLevelID));


                                if (FType.equals(""))
                                    error += "课程类别未填写" + ";";
                                else
                                    ftypeid = Long.valueOf(ParamTools.getdeParam(FType));
                                if (FCNature.equals(""))
                                    error += "课程性质未填写" + ";";
                                else
                                    fcnatureid = Long.valueOf(ParamTools.getdeParam(FCNature));
                                if (FWeeklyStudyHours.equals(""))
                                    error += "周学时未填写" + ";";
                                if (FTheoreticalStudyHours.equals(""))
                                    error += "理论学时未填写" + ";";
                                if (FPracticalStudyHours.equals(""))
                                    error += "实践学时未填写" + ";";


//                                if(!FTotalHours.equals("")){
//                                    if(isNumeric(FTotalHours) == false){
//                                        error += "学时填写错误" + ";";
//                                    }
//                                }

                                if (!FCredits.equals("")) {
                                    if (isNumeric(FCredits) == false) {
                                        error += "学分填写错误" + ";";
                                    }
                                }
                                if (!FPassScore.equals("")) {
                                    if (isNumeric(FPassScore) == false) {
                                        error += "合格分数填写错误" + ";";
                                    }
                                }
                                if (!FFullScore.equals("")) {
                                    if (isNumeric(FFullScore) == false) {
                                        error += "合格分数填写错误" + ";";
                                    }
                                }

                                if (!FKKQJ.equals("")) {
                                    if (isNumeric(FKKQJ) == false) {
                                        error += "开课秋季填写错误" + ";";
                                    }
                                }
                                if (!FKKCJ.equals("")) {
                                    if (isNumeric(FKKCJ) == false) {
                                        error += "开课春季填写错误" + ";";
                                    }
                                }
                                if (!FDXQ.equals("")) {
                                    if (isNumeric(FDXQ) == false) {
                                        error += "短学期填写错误" + ";";
                                    }
                                }

//                                TAbility tAbility = tAbilityService.findById(ftabid);
//                                if(tAbility == null){
//                                    error += "能力【" + FAbilityID + "】不存在!;";
//                                }

                                TAbilityLevel tAbilityLevel = tAbilityLevelService.findById(ftablevelid);
                                if (tAbilityLevel == null) {
                                    error += "能力等级【" + FAbilityLevelID + "】不存在!;";
                                } else {
                                    ftabid = tAbilityLevel.getFabilityid();
                                }
                                TCourseCategory tCourseCategory = tCourseCategoryService.findById(ftypeid);
                                if (tCourseCategory == null) {
                                    error += "课程类别【" + FAbilityLevelID + "】不存在!;";
                                }

                                TCourseNature courseNature = tCourseNatureService.findById(fcnatureid);
                                if (courseNature == null) {
                                    error += "课程性质【" + FAbilityLevelID + "】不存在!;";
                                }

                                if (FPassScore != null && !FPassScore.equals("")) {
                                    FPassScore = FPassScore;
                                } else {
                                    FPassScore = "0";
                                }
                                if (FFullScore != null && !FFullScore.equals("")) {
                                    FType = FFullScore;
                                } else {
                                    FFullScore = "0";
                                }
                                if (!error.equals("")) {
                                    student_object.put("FName", FName == null ? "未填写" : FName);
                                    student_object.put("error", error);
                                    student_Array.add(student_object);
                                    continue;
                                } else {

                                    TCourseExample tCourseExample = new TCourseExample();
                                    TCourseExample.Criteria criteria4 = tCourseExample.createCriteria();
                                    criteria4.andFnoEqualTo(FNo);
                                    List<TCourse> tCourseList = tCourseService.findByExample(tCourseExample);
                                    if (tCourseList.size() == 0) {
                                        TCourse tCourse = new TCourse();
                                        tCourse.setFname(FName);
                                        tCourse.setFno(FNo);
                                        tCourse.setFstate(1);
                                        tCourse.setFabilityid(ftabid);
                                        tCourse.setFabilitylevelid(ftablevelid);
                                        tCourse.setFcon(FCon);
                                        tCourse.setFpassscore(BigDecimal.valueOf(Long.parseLong(FPassScore)));
                                        tCourse.setFfullscore(BigDecimal.valueOf(Long.parseLong(FFullScore)));
                                        tCourse.setFtotalhours(FTotalHours);
                                        tCourse.setFcredits(Float.parseFloat(FCredits));
                                        tCourse.setFmajor(FMajor);
                                        tCourse.setFprerequisites(FPrerequisites);
                                        tCourse.setFteachingrequirements(FTeachingRequirements);
                                        tCourse.setFcid(Long.parseLong(uid));
                                        tCourse.setFcdate(new Date());


                                        tCourse.setFtype(ftypeid);
                                        tCourse.setFcnature(fcnatureid);
                                        tCourse.setFweeklystudyhours(FWeeklyStudyHours);
                                        tCourse.setFtheoreticalstudyhours(FTheoreticalStudyHours);
                                        tCourse.setFpracticalstudyhours(FPracticalStudyHours);
                                        tCourse.setFkkqj(Integer.parseInt(FKKQJ));
                                        tCourse.setFkkcj(Integer.parseInt(FKKCJ));
                                        tCourse.setFdxq(Integer.parseInt(FDXQ));
                                        tCourse.setFjyxnxq(FJYXNXQ);
                                        tCourseService.save(tCourse);
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
//     * 课程能力模版下载
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/courseabilitymoban", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String courseabilitymoban(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String id = jsonParam.getString("id");
//        String ip = jsonParam.getString("ip");
//        String filename = jsonParam.getString("filename");
//        String paramString = jsonParam.getString("filenamestr");
//        try {
//            //生成新的文件名
//            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
//            String realFileName = idWorker.nextId() + ".xlsx";
//            // 目标文件路径
//            String outputFilePath = studentabilitymb + "/1/2024-11/" + realFileName;
//            String url = "";
//            url = studentmb.replace("//", "\\");
//            String templateFilePath = url + "/0/2024-11/" + filename;
//            // 加载Excel模板
//            FileInputStream templateFile = new FileInputStream(new File(templateFilePath));
//            Workbook workbook = new XSSFWorkbook(templateFile);
//            Sheet sheet = workbook.getSheetAt(0);
//
//            // 从第二行开始插入数据
//            int startRow = 1; // 第二行
//            int col1 = 0; //
//            int col2 = 1; //
//            int col3 = 2; //
//
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
//                    cell1.setCellValue(mb.getFTypeName());
//
//                    Cell cell2 = row.getCell(col2);
//                    if (cell2 == null) {
//                        cell2 = row.createCell(col2);
//                    }
//                    cell2.setCellValue(mb.getFTABName());
//
//                    Cell cell3 = row.getCell(col3);
//                    if (cell3 == null) {
//                        cell3 = row.createCell(col3);
//                    }
//                    cell3.setCellValue(mb.getFTLevelName());
//                    i++;
//                }
//            }
//            int col4 = 3; //
//
//
//            TCourseCategoryExample tCourseCategoryExample = new TCourseCategoryExample();
//            tCourseCategoryExample.createCriteria().andFstateEqualTo(1);
//            List<TCourseCategory> categoryList = tCourseCategoryService.findByExample(tCourseCategoryExample);
//            if (categoryList.size() > 0) {
//                int i = 0;
//                for (TCourseCategory category : categoryList) {
//                    Row row = sheet.getRow(startRow + i);
//                    if (row == null) {
//                        row = sheet.createRow(startRow + i);
//                    }
//                    Cell cell1 = row.getCell(col4);
//                    if (cell1 == null) {
//                        cell1 = row.createCell(col4);
//                    }
//                    cell1.setCellValue(category.getFname());
//                    i++;
//                }
//            }
//            int col5= 4; //
//            TCourseNatureExample tCourseNatureExample = new TCourseNatureExample();
//            tCourseNatureExample.createCriteria().andFstateEqualTo(1);
//            List<TCourseNature> natureList = tCourseNatureService.findByExample(tCourseNatureExample);
//            if (natureList.size() > 0) {
//                int i = 0;
//                for (TCourseNature nature : natureList) {
//                    Row row = sheet.getRow(startRow + i);
//                    if (row == null) {
//                        row = sheet.createRow(startRow + i);
//                    }
//                    Cell cell1 = row.getCell(col5);
//                    if (cell1 == null) {
//                        cell1 = row.createCell(col5);
//                    }
//                    cell1.setCellValue(nature.getFname());
//                    i++;
//                }
//            }
//
//
//            // 将生成的Excel写入目标文件
//            try (FileOutputStream outputStream = new FileOutputStream(new File(outputFilePath))) {
//                workbook.write(outputStream);
//            } catch (IllegalArgumentException e) {
//                // 捕获并处理IllegalArgumentException异常，但不打印堆栈跟踪
//                System.err.println("捕获到IllegalArgumentException异常: " + e.getMessage());
//            } finally {
//                workbook.close();
//                templateFile.close();
//            }
//
//            String outurl = "http://" + studentabilitymb.replace(studentabilitymb, ip).replace("//", "\\") + "/1/2024-11/" + realFileName;
////            System.out.println(outurl);
//            object.put("url", outurl);
//            object.put("filename", paramString);
//            object.put("result", "success");
//        }catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }

//    /**
//     * 课程能力模版下载
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/courseabilitymoban", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String courseabilitymoban(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String id = jsonParam.getString("id");
//        String ip = jsonParam.getString("ip");
//        String filename = jsonParam.getString("filename");
//        String paramString = jsonParam.getString("filenamestr");
//        try {
//            //生成新的文件名
//            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
//            String realFileName = idWorker.nextId() + ".xlsx";
//            // 目标文件路径
//            String outputFilePath = studentabilitymb + "/1/2024-11/" + realFileName;
//            String url = "";
//            url = studentmb.replace("//", "\\");
//            String templateFilePath = url + "/0/2024-11/" + filename;
//            // 加载Excel模板
//            FileInputStream templateFile = new FileInputStream(new File(templateFilePath));
//            Workbook workbook = new XSSFWorkbook(templateFile);
//            Sheet sheet = workbook.getSheetAt(0);
//            // 创建单元格样式
//            CellStyle cellStyle = workbook.createCellStyle();
//            cellStyle.setWrapText(true); // 设置自动换行
//            // 从第二行开始插入数据
//            int startRow = 1; // 第二行
//            int col1 = 0; //
//
//
//            List<StudentabilityMB> mbList = tStudentAbilityService.getStudentAbilityMB();
//            if (mbList.size() > 0) {
//                int i = 0;
//                String fnote="";
//                for (StudentabilityMB mb : mbList) {
//                    Row row = sheet.getRow(startRow + i);
//                    if (row == null) {
//                        row = sheet.createRow(startRow + i);
//                    }
//                    Cell cell1 = row.getCell(col1);
//                    if (cell1 == null) {
//                        cell1 = row.createCell(col1);
//                    }
//                    fnote = "名称："+mb.getFTLevelName()+" \n";
//                    fnote +="系统ID：" + ParamTools.getEnParam(mb.getFTLevelID().toString());
//                    cell1.setCellValue(fnote);
//                    cell1.setCellStyle(cellStyle); // 应用样式
//                    i++;
//                }
//            }
//            int col4 = 1; //
//            List<CourseMbData> dataList = tCourseNatureService.getCourseModelData();
//            if (dataList.size() > 0) {
//                int i = 0;
//                String fnote="";
//                for (CourseMbData courseMbData : dataList) {
//                    Row row = sheet.getRow(startRow + i);
//                    if (row == null) {
//                        row = sheet.createRow(startRow + i);
//                    }
//                    Cell cell1 = row.getCell(col4);
//                    if (cell1 == null) {
//                        cell1 = row.createCell(col4);
//                    }
//                    fnote = "名称："+courseMbData.getFMName()+" \n";
//                    fnote +="系统ID：" + ParamTools.getEnParam(courseMbData.getFKeyID().toString());
//                    cell1.setCellValue(fnote);
//                    cell1.setCellStyle(cellStyle); // 应用样式
//                    i++;
//                }
//            }
//            int col5= 2; //
//            TCourseNatureExample tCourseNatureExample = new TCourseNatureExample();
//            tCourseNatureExample.createCriteria().andFstateEqualTo(1);
//            List<TCourseNature> natureList = tCourseNatureService.findByExample(tCourseNatureExample);
//            if (natureList.size() > 0) {
//                int i = 0;
//                String fnote="";
//                for (TCourseNature nature : natureList) {
//                    Row row = sheet.getRow(startRow + i);
//                    if (row == null) {
//                        row = sheet.createRow(startRow + i);
//                    }
//                    Cell cell1 = row.getCell(col5);
//                    if (cell1 == null) {
//                        cell1 = row.createCell(col5);
//                    }
//                    fnote = "名称："+nature.getFname()+" \n";
//                    fnote +="系统ID：" + ParamTools.getEnParam(nature.getFkeyid().toString());
//                    cell1.setCellValue(fnote);
//                    cell1.setCellStyle(cellStyle); // 应用样式
//
//                    i++;
//                }
//            }
//
//
//            // 将生成的Excel写入目标文件
//            try (FileOutputStream outputStream = new FileOutputStream(new File(outputFilePath))) {
//                workbook.write(outputStream);
//            } catch (IllegalArgumentException e) {
//                // 捕获并处理IllegalArgumentException异常，但不打印堆栈跟踪
//                System.err.println("捕获到IllegalArgumentException异常: " + e.getMessage());
//            } finally {
//                workbook.close();
//                templateFile.close();
//            }
//
//            String outurl = "http://" + studentabilitymb.replace(studentabilitymb, ip).replace("//", "\\") + "/1/2024-11/" + realFileName;
////            System.out.println(outurl);
//            object.put("url", outurl);
//            object.put("filename", paramString);
//            object.put("result", "success");
//        }catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }

    /**
     * 课程能力模版下载
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/courseabilitymoban", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String courseabilitymoban(HttpServletRequest request)
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
            // 创建单元格样式
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setWrapText(true); // 设置自动换行
            int deffirstrow = 1;
            int deflastrow = 1048576;
            //重要：在写入文件前清理隐藏工作表
            cleanupHiddenSheets(workbook);
            //*******************************************************


            // 创建下拉框的选项列表
            // 设置下拉列表
            // 获取课程性质
            TCourseNatureExample tCourseNatureExample = new TCourseNatureExample();
            tCourseNatureExample.createCriteria().andFstateEqualTo(1);
            List<TCourseNature> tCourseNatureList = tCourseNatureService.findByExample(tCourseNatureExample);

            if (tCourseNatureList != null && tCourseNatureList.size() > 0) {
                //
                int celnature = 3; //

                String[] t_course_nature = new String[tCourseNatureList.size()];
                for (int i = 0; i < tCourseNatureList.size(); i++) {
                    TCourseNature nature = tCourseNatureList.get(i);
                    t_course_nature[i] = nature.getFkeyid() + "-" + nature.getFname();
                }


//
//                // 限制行数，避免Excel限制
                int actualLastRow = Math.min(deflastrow, 1000);


                //workbook 、 sheet 、第二行开始 、最大行数、数据列表、第几列、是否为空
                ExcelSelectTools.setupCourseNatureDropdown(workbook, sheet, 1, actualLastRow, t_course_nature, celnature, false);
            }


            //获取课程类别
            TCourseCategoryExample tCourseCategoryExample = new TCourseCategoryExample();
            tCourseCategoryExample.createCriteria().andFstateEqualTo(1);
            List<TCourseCategory> tCourseCategoryList = tCourseCategoryService.findByExample(tCourseCategoryExample);
            if (tCourseCategoryList != null && tCourseCategoryList.size() > 0) {
                int celnature = 4;
                String[] t_course_nature = new String[tCourseCategoryList.size()];
                for (int i = 0; i < tCourseCategoryList.size(); i++) {
                    TCourseCategory tCourseCategory = tCourseCategoryList.get(i);
                    t_course_nature[i] = tCourseCategory.getFkeyid() + "-" + getPName(tCourseCategory.getFpid(), tCourseCategory.getFname());
                }
                int actualLastRow = Math.min(deflastrow, 1000);

                //字符大于255时调用下面方法
                //workbook 、 sheet 、第二行开始 、最大行数、数据列表、第几列、是否为空
                ExcelSelectTools.setupCourseNatureDropdown(workbook, sheet, 1, actualLastRow, t_course_nature, celnature, false);
            }


            //建议学年
            if (1 == 1) {
                int celnature = 9;
                String[] t_course_nature = new String[4];
                for (int i = 0; i < 4; i++) {
                    t_course_nature[i] = (i + 1) + "年";
                }

                // 限制行数，避免Excel限制
                int actualLastRow = Math.min(deflastrow, 1000);

                ExcelSelectTools.setupCourseNatureDropdown(workbook, sheet, 1, actualLastRow, t_course_nature, celnature, false);

            }

            //建议学年
            if (1 == 1) {
                int celnature = 10;
                String[] t_course_nature = new String[2];
                for (int i = 0; i < 2; i++) {
                    t_course_nature[i] = (i + 1) + "";
                }
                // 限制行数，避免Excel限制
                int actualLastRow = Math.min(deflastrow, 1000);

                ExcelSelectTools.setupCourseNatureDropdown(workbook, sheet, 1, actualLastRow, t_course_nature, celnature, false);

            }


            //课程类型
            if (1 == 1) {
                int celnature = 11;
                String[] t_course_nature = new String[2];
                t_course_nature[0] = "1-基础课";
                t_course_nature[1] = "2-专业课";
                // 限制行数，避免Excel限制
                int actualLastRow = Math.min(deflastrow, 1000);
                ExcelSelectTools.setupCourseNatureDropdown(workbook, sheet, 1, actualLastRow, t_course_nature, celnature, true);
            }
            //*******************************************************
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
     * 清理隐藏的工作表（可选，在文件保存前调用）
     */
    public void cleanupHiddenSheets(Workbook workbook) {
        try {
            List<String> sheetsToRemove = new ArrayList<>();

            // 找出所有隐藏的验证数据工作表
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                if (sheet.getSheetName().startsWith("ValidationData_")) {
                    sheetsToRemove.add(sheet.getSheetName());
                }
            }

            // 移除这些工作表（如果需要）
            for (String sheetName : sheetsToRemove) {
                int sheetIndex = workbook.getSheetIndex(sheetName);
                if (sheetIndex != -1) {
                    workbook.removeSheetAt(sheetIndex);

                }
            }
        } catch (Exception e) {
            System.err.println("清理隐藏工作表失败: " + e.getMessage());
        }
    }


    /**
     * 课程发布
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/fabucourse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String fabucourse(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLogindeptID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogindeptID != null && !CookiesLogindeptID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            String error = "";
            //查询课程信息是否填写完整
            TCourse tCourseServiceById = tCourseService.findById(Long.parseLong(id));
            if (tCourseServiceById.getFno() == null || tCourseServiceById.getFno().equals("")) {
                error += "课程编号未填写;";
            }

            if (tCourseServiceById.getFtype() == null || tCourseServiceById.getFtype().toString().equals("0")) {
                error += "课程类别未选择;";
            }
            if (tCourseServiceById.getFcnature() == null || tCourseServiceById.getFcnature().toString().equals("0")) {
                error += "课程性质未选择;";
            }

            if (error.equals("")) {
                TCourse tCourse = new TCourse();
                tCourse.setFkeyid(Long.parseLong(id));
                tCourse.setFstate(2);
                tCourse.setFuid(Long.parseLong(uid));
                tCourse.setFudate(new Date());
                tCourseService.update(tCourse);
                // 返回值
                object.put("result", "success");
            } else {
                JSONArray course_array = new JSONArray();
                JSONObject course_object = new JSONObject();
                course_object.put("FName", tCourseServiceById.getFname() == null ? "未填写" : tCourseServiceById.getFname());
                course_object.put("error", error);
                course_array.add(course_object);

                // 返回值
                object.put("courseerror", course_array);
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
     * 课程一键发布
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/yijianfabucourse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String yijianfabucourse(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);

        try {
            String CookiesLogindeptID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogindeptID != null && !CookiesLogindeptID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }

            String error = "";
            //查询课程信息是否填写完整
            TCourseExample tCourseExample = new TCourseExample();
            tCourseExample.createCriteria().andFstateEqualTo(1).andFvalidEqualTo(1);
            List<TCourse> tCourses = tCourseService.findByExample(tCourseExample);
            JSONArray course_array = new JSONArray();
            for (TCourse tCourseServiceById : tCourses) {
                error = "";
                if (tCourseServiceById.getFno() == null || tCourseServiceById.getFno().equals("")) {
                    error += "课程编号未填写;";
                }

                if (tCourseServiceById.getFtype() == null) {
                    error += "课程类别未选择;";
                }
                if (tCourseServiceById.getFcnature() == null) {
                    error += "课程性质未选择;";
                }
                if(error.equals("")) {
                    TCourse tCourse = new TCourse();
                    tCourse.setFkeyid(tCourseServiceById.getFkeyid());
                    tCourse.setFstate(2);
                    tCourse.setFuid(Long.parseLong(uid));
                    tCourse.setFudate(new Date());
                    tCourseService.update(tCourse);
                }else{
                    JSONObject course_object = new JSONObject();
                    course_object.put("FName", tCourseServiceById.getFname() == null ? "未填写" : tCourseServiceById.getFname());
                    course_object.put("error", error);
                    course_array.add(course_object);
                }
            }
            // 返回值
            object.put("courseerror", course_array);
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
     * 课程版本升级
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("课程版本升级")
    @ResponseBody
    @RequestMapping(value = "/upVersioncourse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String upVersioncourse(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String type = jsonParam.getString("type");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }

            TCourse oldprogram = tCourseService.findById(key);
            // 获取当前版本信息

            if (oldprogram != null) {
                // 生成新版本
                String version = oldprogram.getFedition().replace("V", "");
                int big, small;
                big = Integer.parseInt(version.substring(0, version.indexOf(".")));
                small = Integer.parseInt(version.substring(version.indexOf(".") + 1, version.length()));
                if (type.equals("1")) { // 大版本
                    version = (big + 1) + ".0";
                } else { // 小版本
                    version = big + "." + (small + 1);
                }
                // 取消历史版本的激活状态

                TCourseExample tTrainingProgramExample = new TCourseExample();
                if (oldprogram.getFparentid() == 0) {
                    tTrainingProgramExample.or().andFkeyidEqualTo(oldprogram.getFkeyid());
                    tTrainingProgramExample.or().andFparentidEqualTo(oldprogram.getFkeyid());
                } else {
                    tTrainingProgramExample.or().andFkeyidEqualTo(oldprogram.getFparentid());
                    tTrainingProgramExample.or().andFparentidEqualTo(oldprogram.getFparentid());
                }
                TCourse VersionActivetrainingprogram = new TCourse();
                VersionActivetrainingprogram.setFvalid(0);
                tCourseService.updateByExample(VersionActivetrainingprogram, tTrainingProgramExample);
                // 获取基本信息
                TCourse course = new TCourse();
                if (oldprogram.getFparentid() == 0) {
                    course.setFparentid(oldprogram.getFkeyid());
                } else {
                    course.setFparentid(oldprogram.getFparentid());
                }


//                TCourse course = new TCourse();
                course.setFname(oldprogram.getFname());
                course.setFno(oldprogram.getFno());
                course.setFcon(oldprogram.getFcon());
                course.setFpassscore(oldprogram.getFpassscore());
                course.setFfullscore(oldprogram.getFfullscore());

                course.setFtotalhours(oldprogram.getFtotalhours());
                course.setFcredits(oldprogram.getFcredits());
                course.setFmajor(oldprogram.getFmajor());
                course.setFprerequisites(oldprogram.getFprerequisites());
                course.setFteachingrequirements(oldprogram.getFteachingrequirements());
                course.setFcid(Long.parseLong(uid));
                course.setFcdate(new Date());

                course.setFtype(oldprogram.getFtype());
                course.setFcnature(oldprogram.getFcnature());
                course.setFweeklystudyhours(oldprogram.getFweeklystudyhours());
                course.setFtheoreticalstudyhours(oldprogram.getFtheoreticalstudyhours());
                course.setFpracticalstudyhours(oldprogram.getFpracticalstudyhours());
                course.setFkkqj(oldprogram.getFkkqj());
                course.setFkkcj(oldprogram.getFkkcj());
                course.setFdxq(oldprogram.getFdxq());
                course.setFjyxnxq(oldprogram.getFjyxnxq());

                course.setFedition("V" + version);
                course.setFsjzs(oldprogram.getFsjzs());
                course.setFistk(oldprogram.getFistk());
                course.setFvalid(1);
                course.setFywname(oldprogram.getFywname());
                tCourseService.save(course);
                Long newkeyid = course.getFkeyid();
//                System.out.println("newkeyid：" + newkeyid);
                //把下面挂的内容全部添加进来
                if (newkeyid != null && newkeyid != 0) {
                    TCourseTextbookExample tCourseTextbookExample = new TCourseTextbookExample();
                    tCourseTextbookExample.createCriteria().andFcourseidEqualTo(oldprogram.getFkeyid());
                    List<TCourseTextbook> textbookList = tCourseTextbookService.findByExample(tCourseTextbookExample);
                    if (textbookList.size() > 0) {
                        for (TCourseTextbook textbook : textbookList) {
                            TCourseTextbook coursetextbook = new TCourseTextbook();
                            coursetextbook.setFname(textbook.getFname());
                            coursetextbook.setFtype(textbook.getFtype());
                            coursetextbook.setFcourseid(newkeyid);
                            coursetextbook.setFcid(Long.parseLong(uid));
                            coursetextbook.setFcdate(new Date());
                            tCourseTextbookService.save(coursetextbook);
                        }
                    }

                    HashMap<String, Long> newcid = new HashMap<>();
                    HashMap<Long, String> oldcid = new HashMap<>();
                    TCourseTargetExample tCourseTargetExample = new TCourseTargetExample();
                    tCourseTargetExample.createCriteria().andFcourseidEqualTo(oldprogram.getFkeyid());
                    List<TCourseTarget> targetList = tCourseTargetService.findByExample(tCourseTargetExample);
                    if (targetList.size() > 0) {
                        for (TCourseTarget target : targetList) {
                            TCourseTarget coursetarget = new TCourseTarget();
                            coursetarget.setFname(target.getFname());
                            coursetarget.setFnum(target.getFnum());
                            coursetarget.setFcourseid(newkeyid);
                            coursetarget.setFcid(Long.parseLong(uid));
                            coursetarget.setFcdate(new Date());
                            coursetarget.setFtype(target.getFtype());
                            tCourseTargetService.save(coursetarget);
                            newcid.put(target.getFname(), coursetarget.getFkeyid());
                            oldcid.put(target.getFkeyid(), target.getFname());
                        }
                    }

                    TCourseGraduationExample tCourseGraduationExample = new TCourseGraduationExample();
                    tCourseGraduationExample.createCriteria().andFcourseidEqualTo(oldprogram.getFkeyid());
                    List<TCourseGraduation> graduationList = tCourseGraduationService.findByExample(tCourseGraduationExample);
                    if (graduationList.size() > 0) {
                        for (TCourseGraduation tCourseGraduation : graduationList) {
                            String fcoursecid = tCourseGraduation.getFcoursecid();
                            if (fcoursecid != null && !fcoursecid.equals("")) {
                                String[] split = fcoursecid.split(",");
                                List<Long> newID = new ArrayList<>();
                                for (String s : split) {
                                    if (oldcid.get(Long.parseLong(s)) != null) {
                                        newID.add(newcid.get(oldcid.get(Long.parseLong(s))));
                                    }
                                }
                                String fcid = "";
                                for (Object term : newID) {
                                    fcid += term.toString() + ",";
                                }
                                TCourseGraduation coursegraduation = new TCourseGraduation();
                                coursegraduation.setFname(tCourseGraduation.getFname());
                                coursegraduation.setFcoursecid(fcid);
                                coursegraduation.setFcourseid(newkeyid);
                                coursegraduation.setFcid(Long.parseLong(uid));
                                coursegraduation.setFcdate(new Date());
                                coursegraduation.setFtbyyqid(tCourseGraduation.getFtbyyqid());
                                coursegraduation.setFtpid(tCourseGraduation.getFtpid());
                                tCourseGraduationService.save(coursegraduation);
                            }

                        }
                    }

                    TCourseAssessmentMethodExample tCourseAssessmentMethodExample = new TCourseAssessmentMethodExample();
                    tCourseAssessmentMethodExample.createCriteria().andFcourseidEqualTo(oldprogram.getFkeyid());
                    List<TCourseAssessmentMethod> methodList = tCourseAssessmentMethodService.findByExample(tCourseAssessmentMethodExample);
                    if (methodList.size() > 0) {
                        for (TCourseAssessmentMethod tCourseAssessmentMethod : methodList) {
                            TCourseAssessmentMethod courseassessmentmethod = new TCourseAssessmentMethod();
                            courseassessmentmethod.setFmethodname(tCourseAssessmentMethod.getFmethodname());
                            courseassessmentmethod.setFweight(tCourseAssessmentMethod.getFweight());
                            courseassessmentmethod.setFcourseid(newkeyid);
                            courseassessmentmethod.setFcid(Long.parseLong(uid));
                            courseassessmentmethod.setFcdate(new Date());
                            tCourseAssessmentMethodService.save(courseassessmentmethod);
                        }
                    }

                    //课程资源
                    TCourseResourcefileExample tCourseResourcefileExample = new TCourseResourcefileExample();
                    tCourseResourcefileExample.createCriteria().andFcourseidEqualTo(oldprogram.getFkeyid());
                    List<TCourseResourcefile> resourcefileList = tCourseResourcefileService.findByExample(tCourseResourcefileExample);
                    if (resourcefileList.size() > 0) {
                        for (TCourseResourcefile tCourseResourcefile : resourcefileList) {
                            TCourseResourcefile courseresourcefile = new TCourseResourcefile();
                            courseresourcefile.setFfname(tCourseResourcefile.getFfname());
                            courseresourcefile.setFcourseid(newkeyid);
                            courseresourcefile.setFfurl(tCourseResourcefile.getFfurl());
                            courseresourcefile.setFhz(tCourseResourcefile.getFhz());
                            courseresourcefile.setFysfilename(tCourseResourcefile.getFysfilename());
                            courseresourcefile.setFxtfilename(tCourseResourcefile.getFxtfilename());
                            courseresourcefile.setFrelativepath1(tCourseResourcefile.getFrelativepath1());
                            courseresourcefile.setFrelativepath2(tCourseResourcefile.getFrelativepath2());
                            courseresourcefile.setFsolrstate(tCourseResourcefile.getFsolrstate());
                            courseresourcefile.setFcid(Long.parseLong(uid));
                            courseresourcefile.setFcdate(new Date());
                            tCourseResourcefileService.save(courseresourcefile);
                        }
                    }
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
     * 获取课程历史版本信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryCourseVersion", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryCourseVersion(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        String parentId = jsonParam.getString("parentId");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        try {
            JSONArray trainingprogram_Array = new JSONArray();
            Long pid = Long.parseLong(ParamTools.getdeParam(parentId));
//            System.out.println(parentId);
            // 获取数据库记录
            JSONArray EntityFile_Array = new JSONArray();
            TrainingprogramversionCS trainingprogramversionCS = new TrainingprogramversionCS();
            trainingprogramversionCS.setFParentId(pid);
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                trainingprogramversionCS.setOrderBy(orderSql.substring(1));
            } else {
                trainingprogramversionCS.setOrderBy("tc.FCDATE desc");
            }

            PageInfo<CouserVersionData> couserVersionDataPageInfo = tTrainingProgramService.getCourseVersionDataList(trainingprogramversionCS, (page - 1) * results, results);
            List<CouserVersionData> couserVersionDataList = couserVersionDataPageInfo.getList();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (CouserVersionData couserVersionData : couserVersionDataList) {
                JSONObject course_object = new JSONObject();
                course_object.put("key", ParamTools.getEnParam(couserVersionData.getFKeyID().toString()));

                course_object.put("FParentId", ParamTools.getEnParam(couserVersionData.getFParentId().toString()));
                course_object.put("FNo", couserVersionData.getFNo());
                course_object.put("FName", couserVersionData.getFName());
                course_object.put("FYWName", couserVersionData.getFYWName());
                course_object.put("FTypeID", couserVersionData.getFTypeID());
//                course_object.put("FTypeName", couserVersionData.getFTypeName());
                course_object.put("FNatureID", couserVersionData.getFNatureID());
                course_object.put("FNatureName", couserVersionData.getFNatureName());

//                course_object.put("FCDATE", couserVersionData.getFCDATE() == null ? "" : dateFormat.format(couserVersionData.getFCDATE()));
//                course_object.put("FUDATE",  couserVersionData.getFUDATE() == null ? "" : dateFormat.format(couserVersionData.getFUDATE()));
                course_object.put("FCDATE", couserVersionData.getFCDATE() == null ? "" : couserVersionData.getFCDATE());
                course_object.put("FUDATE", couserVersionData.getFUDATE() == null ? "" : couserVersionData.getFUDATE());
                course_object.put("FCredits", couserVersionData.getFCredits());
                course_object.put("FSJZS", couserVersionData.getFSJZS());
                course_object.put("FJYXNXQ", couserVersionData.getFJYXNXQ());
                course_object.put("FIstk", couserVersionData.getFIstk());
                course_object.put("FEdition", couserVersionData.getFEdition());
                course_object.put("FValid", couserVersionData.getFValid());
                TCourseCategory tCourseCategory = tCourseCategoryService.findById(couserVersionData.getFTypeID());
                course_object.put("FTypeName", getPName(tCourseCategory.getFpid(), tCourseCategory.getFname()));
                trainingprogram_Array.add(course_object);
            }
            // 返回值
            object.put("list", trainingprogram_Array);
            object.put("total", couserVersionDataPageInfo.getTotal()); // 总行数
            object.put("page", couserVersionDataPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 添加课程能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加课程能力信息")
    @ResponseBody
    @RequestMapping(value = "/addcourseability", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addcourseability(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String abilityname = jsonParam.getString("abilityname");
//        String FCourse = jsonParam.getString("FCourse");//课程ID
        String FCourse = jsonParam.getString("FCourseID");//课程ID
        try {
            String CookiesLoginusergroupsID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginusergroupsID != null && !CookiesLoginusergroupsID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            object.put("FCourse", FCourse);
            FCourse = FCourse == null ? "0" : ParamTools.getdeParam(FCourse);
            TCourse tCourse = tCourseService.findById(Long.parseLong(FCourse));
            String errorstr = "";
            if (tCourse != null) {
                if (abilityname != null && !abilityname.equals("")) {

                    //增加新选的
                    JSONArray powerRoles_Array = JSONArray.parseArray(abilityname);
                    for (Object userid : powerRoles_Array) {
                        Long flevelid = Long.parseLong(ParamTools.getdeParam(userid.toString())); //能力等级ID
                        TAbilityLevel abilityLevel = tAbilityLevelService.findById(flevelid);
//
//                        TCourseStudentExample tCourseStudentExample = new TCourseStudentExample();
//                        TCourseStudentExample.Criteria criteria = tCourseStudentExample.createCriteria();
//                        criteria.andFcourseidEqualTo(Long.parseLong(FCourse));
//                        criteria.andFstudentidEqualTo(studentid);
//                        List<TCourseStudent> studentList = tCourseStudentService.findByExample(tCourseStudentExample);
//
//                        TCourse tCourse1 = tCourseService.findById(Long.parseLong(FCourse));
//
//                        TStudentAbilityExample tStudentAbilityExample = new TStudentAbilityExample();
//                        TStudentAbilityExample.Criteria criteria1 = tStudentAbilityExample.createCriteria();
//                        criteria1.andFstudentidEqualTo(studentid);
//                        criteria1.andFabilityidEqualTo(tCourse1.getFabilityid());
//                        criteria1.andFabilitylevelidEqualTo(tCourse1.getFabilitylevelid());
//                        criteria1.andFstateNotEqualTo(2); //不是注销状态的
//                        List<TStudentAbility> tStudentAbilityList = tStudentAbilityService.findByExample(tStudentAbilityExample);
//                        if (studentList.size() == 0 && tStudentAbilityList.size() == 0) {
//                            TCourseStudent tCourseStudent = new TCourseStudent();
//                            tCourseStudent.setFcourseid(Long.parseLong(FCourse));
//                            tCourseStudent.setFstudentid(studentid);
//                            tCourseStudent.setFmode(1);
//                            tCourseStudent.setFifpass(0);
//                            tCourseStudent.setFcid(Long.parseLong(uid));
//                            tCourseStudent.setFcdate(new Date());
//                            tCourseStudentService.save(tCourseStudent);
//                        } else {
//                            TStudent tStudent = tStudentService.findById(studentid);
//                            errorstr += tStudent.getFname() + "、";
//                        }
                    }
                }

                if (!errorstr.equals("")) {
                    errorstr = errorstr.substring(0, errorstr.length() - 1);
                    object.put("errorstr", "能力【" + errorstr + "】已存在！");
                    object.put("iserror", 1);
                } else {
                    object.put("iserror", 0);
                }
                // 返回值
                object.put("result", "success");
            } else {
                object.put("result", "error");
                object.put("error", "未获取到课程信息，请刷新后重试！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取课程信息(下拉列表)——根据学科（专业） 查找专业培养方案中课程安排的课程信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDataprojectCourseSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDataprojectCourseSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        String fmajorID = request.getParameter("fmajorID");//专业ID
        try {
            // 获取数据库记录
            JSONArray course_Array = new JSONArray();


            ProjectCourseCs projectCourseCs = new ProjectCourseCs();

            if (search != null && !search.equals("")) {
                projectCourseCs.setFName(search);

            }
            fmajorID = fmajorID == null ? "0" : ParamTools.getdeParam(fmajorID);
            projectCourseCs.setFMajorId(Long.parseLong(fmajorID));
            projectCourseCs.setOrderBy("tc.FName asc");

            List<ProjectCoursedata> coursedataList = tProjectCourseService.getProjectCourseSeelect(projectCourseCs);

            for (ProjectCoursedata course : coursedataList) {
                JSONObject course_object = new JSONObject();
                course_object.put("id", ParamTools.getEnParam(course.getFKeyID().toString()));
                course_object.put("text", course.getFName());
                course_Array.add(course_object);
            }
            // 返回值

            object.put("list", course_Array);
            object.put("results", course_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

}