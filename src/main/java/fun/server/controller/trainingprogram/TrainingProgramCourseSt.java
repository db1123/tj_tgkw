package fun.server.controller.trainingprogram;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.model.customQuery.DataRange;
import fun.server.model.customQuery.trainingprogram.*;
import fun.server.service.*;
import fun.tools.ChineseSemanticSimilarityTools;
import fun.tools.ParamTools;
import fun.tools.SimplePinyinTools;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 课程安排新管理 相关业务处理
 */
@RestController
@RequestMapping("/s/trainingprogramcoursest")
public class TrainingProgramCourseSt {

    private final TTrainingProgramCourseStService tTrainingProgramCourseStService;

    private final TCourseCategoryService tCourseCategoryService;

    private final TCourseService tCourseService;

    private final TCourseSemesterService tCourseSemesterService;

    private final TTrainingProgramCourseXnxqStService tTrainingProgramCourseXnxqStService;

    private final TTrainingProgramService tTrainingProgramService;

    private final TCoursePrerequisitesService tCoursePrerequisitesService;

    private final TCourseNatureService tCourseNatureService;



    public TrainingProgramCourseSt(TTrainingProgramCourseStService tTrainingProgramCourseStService, TCourseCategoryService tCourseCategoryService, TCourseService tCourseService, TCourseSemesterService tCourseSemesterService, TTrainingProgramCourseXnxqStService tTrainingProgramCourseXnxqStService, TTrainingProgramService tTrainingProgramService, TCoursePrerequisitesService tCoursePrerequisitesService, TCourseNatureService tCourseNatureService) {
        this.tTrainingProgramCourseStService = tTrainingProgramCourseStService;
        this.tCourseCategoryService = tCourseCategoryService;
        this.tCourseService = tCourseService;
        this.tCourseSemesterService = tCourseSemesterService;
        this.tTrainingProgramCourseXnxqStService = tTrainingProgramCourseXnxqStService;
        this.tTrainingProgramService = tTrainingProgramService;
        this.tCoursePrerequisitesService = tCoursePrerequisitesService;
        this.tCourseNatureService = tCourseNatureService;
    }


    /**
     * 获取课程安排新信息_自定义sql
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogramcoursesql", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogramcoursesql(HttpServletRequest request)
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
        String tpID = jsonParam.getString("tpID");
        try {
            // 获取数据库记录
            JSONArray trainingprogramcourse_Array = new JSONArray();
            // 查询条件
            TrainingprogramkcCS trainingprogramkcCS = new TrainingprogramkcCS();
            tpID = tpID == null ? "0" : ParamTools.getdeParam(tpID);
            trainingprogramkcCS.setFTPID(Long.parseLong(tpID));
            if (name != null && !name.equals("")) {
                trainingprogramkcCS.setFName(name);
            }
            if (fno != null && !fno.equals("")) {
                trainingprogramkcCS.setFNo(fno);
            }
            if (ftype != null && !ftype.equals("") && !ftype.equals("1")) {
                ftype = ftype == null ? "0" : ParamTools.getdeParam(ftype);
                trainingprogramkcCS.setFLTypeID(Long.parseLong(ftype));
            }
            if (fnature != null && !fnature.equals("")) {
                fnature = fnature == null ? "0" : ParamTools.getdeParam(fnature);
                if (!fnature.equals("-1"))
                    trainingprogramkcCS.setFNatureID(Long.parseLong(fnature));
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
                trainingprogramkcCS.setOrderBy(orderSql.substring(1));
            } else {
                trainingprogramkcCS.setOrderBy("FCDATE desc");
            }
            PageInfo<TrainingprogramkcstData> trainingprogramcoursePageInfo = tTrainingProgramCourseStService.getTrainingprogramCourseStData(trainingprogramkcCS, (page - 1) * results, results);
            List<TrainingprogramkcstData> trainingprogramcourse_list = trainingprogramcoursePageInfo.getList();
            for (TrainingprogramkcstData course : trainingprogramcourse_list) {
                JSONObject trainingprogramcourse_object = new JSONObject();
                trainingprogramcourse_object.put("key", ParamTools.getEnParam(course.getFKeyID().toString()));
                trainingprogramcourse_object.put("FCourseID", course.getFCourseID() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(course.getFCourseID().toString()));
                trainingprogramcourse_object.put("FName", course.getFCourseName() == null ? "" : course.getFCourseName());
                trainingprogramcourse_object.put("FYWName", course.getFYWName() == null ? "" : course.getFYWName());
                trainingprogramcourse_object.put("FNo", course.getFNo() == null ? "" : course.getFNo());
                trainingprogramcourse_object.put("FXF", course.getFXF());
                trainingprogramcourse_object.put("FTotalHours", course.getFTotalHours() == null ? "" : course.getFTotalHours());
                trainingprogramcourse_object.put("FWeeklyStudyHours", course.getFWeeklyStudyHours() == null ? "" : course.getFWeeklyStudyHours());
                trainingprogramcourse_object.put("FTheoreticalStudyHours", course.getFTheoreticalStudyHours() == null ? "" : course.getFTheoreticalStudyHours());
                trainingprogramcourse_object.put("FPracticalStudyHours", course.getFPracticalStudyHours() == null ? "" : course.getFPracticalStudyHours());
                trainingprogramcourse_object.put("FPPState", course.getFPPState());
                trainingprogramcourse_object.put("FYZState", course.getFYZState());
                trainingprogramcourse_object.put("FCourseState", course.getFCourseState());

                if (course.getFType() == null) {
                    trainingprogramcourse_object.put("FTypeName", "");
                } else {
                    TCourseCategory courseCategory = tCourseCategoryService.findById(course.getFType());
                    if (courseCategory != null) {
                        trainingprogramcourse_object.put("FTypeName", getPName(courseCategory.getFpid(), courseCategory.getFname()));
                    } else {
                        trainingprogramcourse_object.put("FTypeName", "");
                    }
                }


                trainingprogramcourse_object.put("FCNatureName", course.getFCNatureName() == null ? "" : course.getFCNatureName());
                if (course.getFCourseID() == null) {
                    trainingprogramcourse_object.put("FISCourse", 1);
                } else {
                    trainingprogramcourse_object.put("FISCourse", 2);
                }

                trainingprogramcourse_object.put("FSemesterName", course.getFSemesterName());
//                if (course.getFKeyID() != null) {
//                    TTrainingProgramCourseSt programCourse = tTrainingProgramCourseStService.findById(course.getFKeyID());
//                    if (programCourse != null) {
//                        List<String> group1 = new ArrayList<>();
//                        TTrainingProgramCourseXnxqStExample tTrainingProgramCourseXnxqExample = new TTrainingProgramCourseXnxqStExample();
//                        tTrainingProgramCourseXnxqExample.createCriteria().andFtpidEqualTo(programCourse.getFtpid()).andFtmidEqualTo(programCourse.getFtmid()).andFtpcstidEqualTo(programCourse.getFkeyid());
//                        List<TTrainingProgramCourseXnxqSt> courseXnxqList = tTrainingProgramCourseXnxqStService.findByExample(tTrainingProgramCourseXnxqExample);
//                        if (courseXnxqList.size() > 0) {
//                            for (TTrainingProgramCourseXnxqSt trainingProgramCourseXnxq : courseXnxqList) {
//                                TCourseSemester courseSemester = tCourseSemesterService.findById(trainingProgramCourseXnxq.getFxnxqid());
//                                group1.add(courseSemester.getFjname());
//                            }
//                        }
//
//                        if (group1.size() > 0) {
//                            if (group1.size() == 1) {
//                                trainingprogramcourse_object.put("FSemesterName", group1.get(0).replaceAll("-", "年"));
//                            } else {
//                                trainingprogramcourse_object.put("FSemesterName", generateRange(group1));
//
//                            }
//                        }
//                    } else {
//                        trainingprogramcourse_object.put("FSemesterName", "未设置");
//                    }
//
//                } else {
//                    trainingprogramcourse_object.put("FSemesterName", "未设置");
//                }


                trainingprogramcourse_Array.add(trainingprogramcourse_object);
            }
//            System.out.println(trainingprogramcourse_Array);
            //返回值
            object.put("list", trainingprogramcourse_Array);
            object.put("total", trainingprogramcoursePageInfo.getTotal()); // 总行数
            object.put("page", trainingprogramcoursePageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    public static String generateRange(List<String> values) {
        List<DataRange> ranges = values.stream()
                .map(DataRange::new)
                .sorted(Comparator.comparingInt(DataRange::getGroup)
                        .thenComparingInt(DataRange::getSubGroup))
                .collect(Collectors.toList());

        if (ranges.isEmpty()) return "";

        return ranges.get(0).toString().replaceAll("-", "年") + "~" + ranges.get(ranges.size() - 1).toString().replaceAll("-", "年");
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
     * 根据ID获取课程安排新信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogramcourseInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogramcourseInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询课程安排新信息
            TTrainingProgramCourseSt trainingprogramcourse = tTrainingProgramCourseStService.findById(key);
            JSONObject trainingprogramcourse_object = new JSONObject();
            trainingprogramcourse_object.put("key", ParamTools.getEnParam(trainingprogramcourse.getFkeyid().toString()));
            trainingprogramcourse_object.put("FTMID", ParamTools.getEnParam(trainingprogramcourse.getFtmid().toString()));
            trainingprogramcourse_object.put("FTPID", ParamTools.getEnParam(trainingprogramcourse.getFtpid().toString()));
            trainingprogramcourse_object.put("FCourseID", trainingprogramcourse.getFcourseid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(trainingprogramcourse.getFtpid().toString()));
            trainingprogramcourse_object.put("FXF", trainingprogramcourse.getFxf() == null ? "" : trainingprogramcourse.getFxf());
            trainingprogramcourse_object.put("FCID", trainingprogramcourse.getFcid());
            trainingprogramcourse_object.put("FUID", trainingprogramcourse.getFuid());
            trainingprogramcourse_object.put("FCDATE", trainingprogramcourse.getFcdate());
            trainingprogramcourse_object.put("FUDATE", trainingprogramcourse.getFudate());
            trainingprogramcourse_object.put("FCourseName", trainingprogramcourse.getFcoursename());
            trainingprogramcourse_object.put("FOrder", trainingprogramcourse.getForder());
            trainingprogramcourse_object.put("FTotalHours", trainingprogramcourse.getFtotalhours());
            trainingprogramcourse_object.put("FWeeklyStudyHours", trainingprogramcourse.getFweeklystudyhours());
            trainingprogramcourse_object.put("FTheoreticalStudyHours", trainingprogramcourse.getFtheoreticalstudyhours());
            trainingprogramcourse_object.put("FPracticalStudyHours", trainingprogramcourse.getFpracticalstudyhours());
            trainingprogramcourse_object.put("FPPState", trainingprogramcourse.getFppstate());
            trainingprogramcourse_object.put("FYZState", trainingprogramcourse.getFyzstate());


            //查询学年学期
            JSONArray prerequisite_Array = new JSONArray();
            TTrainingProgramCourseXnxqStExample tTrainingProgramCourseXnxqExample = new TTrainingProgramCourseXnxqStExample();
            tTrainingProgramCourseXnxqExample.createCriteria().andFtmidEqualTo(trainingprogramcourse.getFtmid()).andFtpidEqualTo(trainingprogramcourse.getFtpid()).andFtpcstidEqualTo(trainingprogramcourse.getFkeyid());
            List<TTrainingProgramCourseXnxqSt> courseXnxqList = tTrainingProgramCourseXnxqStService.findByExample(tTrainingProgramCourseXnxqExample);

            for (TTrainingProgramCourseXnxqSt tTrainingProgramCourseXnxq : courseXnxqList) {
                TCourseSemester tCourseSemester = tCourseSemesterService.findById(tTrainingProgramCourseXnxq.getFxnxqid());
                if (tCourseSemester != null) {
                    JSONObject roleObject = new JSONObject();
                    roleObject.put("id", ParamTools.getEnParam(tCourseSemester.getFkeyid().toString()));
                    roleObject.put("name", tCourseSemester.getFname());
                    prerequisite_Array.add(roleObject);
                }
            }

            trainingprogramcourse_object.put("xnxqlist", prerequisite_Array);


            // 返回值
            object.put("info", trainingprogramcourse_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加课程安排信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加课程安排信息")
    @ResponseBody
    @RequestMapping(value = "/addtrainingprogramcourse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addtrainingprogramcourse(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);

        String courseids = jsonParam.getString("courseids");
        String FTMID = jsonParam.getString("FTMID");
        String FTPID = jsonParam.getString("FTPID");

        try {
            FTMID = FTMID == null ? "0" : ParamTools.getdeParam(FTMID);
            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);
            if (courseids != null && !courseids.equals("")) {
                TTrainingProgramCourseExample trainingprogramcourseExample = new TTrainingProgramCourseExample();
                trainingprogramcourseExample.createCriteria().andFtmidEqualTo(Long.parseLong(FTMID)).andFtpidEqualTo(Long.parseLong(FTPID));
                trainingprogramcourseExample.setOrderByClause("FOrder desc ");
                int forder = 1;
                List<TTrainingProgramCourseSt> courseList = tTrainingProgramCourseStService.findByExample(trainingprogramcourseExample);

                if (courseList.size() > 0) {
                    forder = courseList.get(0).getForder() + 1;
                }
                Long fcourseid = 0l;
                JSONArray powerRoles_Array = JSONArray.parseArray(courseids);
                for (Object courseid : powerRoles_Array) {
                    fcourseid = Long.parseLong(ParamTools.getdeParam(courseid.toString()));
                    trainingprogramcourseExample = new TTrainingProgramCourseExample();
                    TTrainingProgramCourseExample.Criteria criteria = trainingprogramcourseExample.createCriteria();
                    criteria.andFcourseidEqualTo(fcourseid);
                    criteria.andFtmidEqualTo(Long.parseLong(FTMID));
                    criteria.andFtpidEqualTo(Long.parseLong(FTPID));
                    List<TTrainingProgramCourseSt> programList = tTrainingProgramCourseStService.findByExample(trainingprogramcourseExample);
                    if (programList.size() == 0) {
                        String CookiesLogintrainingprogramcourseID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                        String uid = ""; // 当前登录用户ID
                        if (CookiesLogintrainingprogramcourseID != null && !CookiesLogintrainingprogramcourseID.equals("")) {
                            uid = ParamTools.getdeParam(CookiesLogintrainingprogramcourseID);
                        }
                        TCourse course = tCourseService.findById(fcourseid);
                        // 新建课程安排信息
                        TTrainingProgramCourseSt trainingprogramcourse = new TTrainingProgramCourseSt();
                        trainingprogramcourse.setFcourseid(fcourseid);
                        trainingprogramcourse.setFcoursename(course.getFname());
                        trainingprogramcourse.setFtmid(Long.parseLong(FTMID));
                        trainingprogramcourse.setFtpid(Long.parseLong(FTPID));
                        trainingprogramcourse.setForder(forder);
                        trainingprogramcourse.setFcid(Long.parseLong(uid));
                        trainingprogramcourse.setFcdate(new Date());
                        trainingprogramcourse.setFppstate(2);
                        trainingprogramcourse.setFyzstate(1);
                        trainingprogramcourse.setFxf(course.getFcredits());
                        trainingprogramcourse.setFtotalhours(course.getFtotalhours());
                        trainingprogramcourse.setFweeklystudyhours(course.getFweeklystudyhours());
                        trainingprogramcourse.setFtheoreticalstudyhours(course.getFtheoreticalstudyhours());
                        trainingprogramcourse.setFpracticalstudyhours(course.getFpracticalstudyhours());
                        tTrainingProgramCourseStService.save(trainingprogramcourse);
                        try {


                            TCourseSemesterExample tCourseSemesterExample = new TCourseSemesterExample();
                            tCourseSemesterExample.createCriteria().andFjnameEqualTo(course.getFjyxnxq() + "-" + course.getFjyxq());
                            List<TCourseSemester> semesterList = tCourseSemesterService.findByExample(tCourseSemesterExample);
                            if (semesterList.size() > 0) {
                                TTrainingProgramCourseXnxqSt tTrainingProgramCourseXnxq = new TTrainingProgramCourseXnxqSt();
                                tTrainingProgramCourseXnxq.setFcourseid(fcourseid);
                                tTrainingProgramCourseXnxq.setFtmid(Long.parseLong(FTMID));
                                tTrainingProgramCourseXnxq.setFtpid(Long.parseLong(FTPID));
                                tTrainingProgramCourseXnxq.setFxnxqid(semesterList.get(0).getFkeyid());
                                tTrainingProgramCourseXnxq.setFcid(Long.parseLong(uid));
                                tTrainingProgramCourseXnxq.setFcdate(new Date());
                                tTrainingProgramCourseXnxq.setFtpcstid(trainingprogramcourse.getFkeyid());
                                tTrainingProgramCourseXnxqStService.save(tTrainingProgramCourseXnxq);
                            }
                        } catch (NumberFormatException e) {

                        }

                        forder = forder + 1;
                    }
                }
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
     * 删除课程安排新信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除课程安排新信息")
    @ResponseBody
    @RequestMapping(value = "/deltrainingprogramcourse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deltrainingprogramcourse(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLogintrainingprogramcourseID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintrainingprogramcourseID != null && !CookiesLogintrainingprogramcourseID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
//            System.out.println(id);

            tTrainingProgramCourseStService.deleteById(Long.parseLong(id));
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
     * 新增学期学年和学分、学时等信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/addxnxqxf", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addxnxqxf(HttpServletRequest request, Pageable pageable)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);

        String formData = jsonParam.getString("formData"); //每组信息
        String fmajorid = jsonParam.getString("fmajorid"); //专业ID
        String FTPID = jsonParam.getString("FTPID"); //培养方案ID
        try {
            String CookiesLogincourseID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogincourseID != null && !CookiesLogincourseID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLogincourseID);
            }
            fmajorid = fmajorid == null ? "0" : ParamTools.getdeParam(fmajorid);
            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);
            TTrainingProgram tTrainingProgram = tTrainingProgramService.findById(Long.parseLong(FTPID));
            if (tTrainingProgram != null) {
                if (formData != null) {
                    if (!formData.equals("")) {
                        JSONArray data_Array = JSONArray.parseArray(formData);
                        for (int i = 0; i < data_Array.size(); i++) {
                            JSONObject data_Object = data_Array.getJSONObject(i);
                            String FName = data_Object.getString("FName");
                            String FPracticalStudyHours = data_Object.getString("FPracticalStudyHours");
                            String FTheoreticalStudyHours = data_Object.getString("FTheoreticalStudyHours");
                            String FTotalHours = data_Object.getString("FTotalHours");
                            String FWeeklyStudyHours = data_Object.getString("FWeeklyStudyHours");
                            String FXNXQ = data_Object.getString("FXNXQ");
                            Float FXF = data_Object.getFloat("FXF");
                            System.out.println(FXNXQ);
                            //添加学年学期
//                            ObjectMapper objectMapper = new ObjectMapper();
//                            JsonNode jsonNode = objectMapper.readTree(FXNXQ);
                            FXNXQ = FXNXQ == null ? "0" : ParamTools.getdeParam(FXNXQ);
                            TTrainingProgramCourseStExample trainingprogramcourseStExample = new TTrainingProgramCourseStExample();
                            trainingprogramcourseStExample.createCriteria().andFtmidEqualTo(Long.parseLong(fmajorid)).andFtpidEqualTo(Long.parseLong(FTPID));
                            trainingprogramcourseStExample.setOrderByClause("FOrder desc ");
                            int forder = 1;
                            List<TTrainingProgramCourseSt> tTrainingProgramCourseStList = tTrainingProgramCourseStService.findByExample(trainingprogramcourseStExample);

                            if (tTrainingProgramCourseStList.size() > 0) {
                                forder = tTrainingProgramCourseStList.get(0).getForder() + 1;
                            }
                            String fxn="1";
                            Integer fxq=1;
                            TCourseSemester tCourseSemester = tCourseSemesterService.findById(Long.parseLong(FXNXQ));
                            if (tCourseSemester != null) {
                                fxq = tCourseSemester.getFxq();
                                fxn = tCourseSemester.getFxn()+"";
                            }


                            //根据名称、学分学时 先添加课程信息

                            Long courseID=0L;

                            TCourseExample courseExample = new TCourseExample();
                            courseExample.createCriteria().andFnameEqualTo(FName).andFvalidEqualTo(1);
                            List<TCourse> courseList = tCourseService.findByExample(courseExample);
                            if (courseList.size() > 0) {
                                courseID = courseList.get(0).getFkeyid();
                            }else{
                                TCourse tCourse = new TCourse();
                                tCourse.setFname(FName);
                                tCourse.setFtotalhours(FTotalHours);
                                tCourse.setFcredits(FXF);

                                tCourse.setFcid(Long.parseLong(uid));
                                tCourse.setFcdate(new Date());

                                tCourse.setFweeklystudyhours(FWeeklyStudyHours);
                                tCourse.setFtheoreticalstudyhours(FTheoreticalStudyHours);
                                tCourse.setFpracticalstudyhours(FPracticalStudyHours);
                                tCourse.setFjyxnxq(fxn);
                                tCourse.setFedition("V1.0");
                                tCourse.setFvalid(1);
                                tCourse.setFjyxq(fxq);
                                tCourseService.save(tCourse);
                                courseID = tCourse.getFkeyid();
                            }



                            TTrainingProgramCourseSt tTrainingProgramCourseSt = new TTrainingProgramCourseSt();
                            tTrainingProgramCourseSt.setFcourseid(courseID);
                            tTrainingProgramCourseSt.setFtmid(Long.parseLong(fmajorid));
                            tTrainingProgramCourseSt.setFtpid(Long.parseLong(FTPID));
                            tTrainingProgramCourseSt.setFcoursename(FName);
                            tTrainingProgramCourseSt.setForder(forder);
                            tTrainingProgramCourseSt.setFxf(FXF);
                            tTrainingProgramCourseSt.setFtotalhours(FTotalHours);
                            tTrainingProgramCourseSt.setFweeklystudyhours(FWeeklyStudyHours);
                            tTrainingProgramCourseSt.setFtheoreticalstudyhours(FTheoreticalStudyHours);
                            tTrainingProgramCourseSt.setFpracticalstudyhours(FPracticalStudyHours);
                            tTrainingProgramCourseSt.setFppstate(1);
                            tTrainingProgramCourseSt.setFyzstate(1);
                            tTrainingProgramCourseSt.setFcid(Long.parseLong(uid));
                            tTrainingProgramCourseSt.setFcdate(new Date());
                            tTrainingProgramCourseStService.save(tTrainingProgramCourseSt);


                            // 遍历JSON数组
//                            for (JsonNode node : jsonNode) {
//                                Long xnxqid = Long.parseLong(ParamTools.getdeParam(node.asText()));
//                                TTrainingProgramCourseXnxqSt tTrainingProgramCourseXnxqSt = new TTrainingProgramCourseXnxqSt();
//                                tTrainingProgramCourseXnxqSt.setFcourseid(courseID);
//                                tTrainingProgramCourseXnxqSt.setFtmid(Long.parseLong(fmajorid));
//                                tTrainingProgramCourseXnxqSt.setFtpid(Long.parseLong(FTPID));
//                                tTrainingProgramCourseXnxqSt.setFxnxqid(xnxqid);
//                                tTrainingProgramCourseXnxqSt.setFtpcstid(tTrainingProgramCourseSt.getFkeyid());
//                                tTrainingProgramCourseXnxqSt.setFcid(Long.parseLong(uid));
//                                tTrainingProgramCourseXnxqSt.setFcdate(new Date());
//                                tTrainingProgramCourseXnxqStService.save(tTrainingProgramCourseXnxqSt);
//                            }

                            TTrainingProgramCourseXnxqSt tTrainingProgramCourseXnxqSt = new TTrainingProgramCourseXnxqSt();
                            tTrainingProgramCourseXnxqSt.setFcourseid(courseID);
                            tTrainingProgramCourseXnxqSt.setFtmid(Long.parseLong(fmajorid));
                            tTrainingProgramCourseXnxqSt.setFtpid(Long.parseLong(FTPID));
                            tTrainingProgramCourseXnxqSt.setFxnxqid(Long.parseLong(FXNXQ));
                            tTrainingProgramCourseXnxqSt.setFtpcstid(tTrainingProgramCourseSt.getFkeyid());
                            tTrainingProgramCourseXnxqSt.setFcid(Long.parseLong(uid));
                            tTrainingProgramCourseXnxqSt.setFcdate(new Date());
                            tTrainingProgramCourseXnxqStService.save(tTrainingProgramCourseXnxqSt);
                        }
                    }
                }
                // 返回值
                object.put("result", "success");
            } else {
                object.put("result", "error");
                object.put("error", "未检测到培养方案信息，请刷新页面后重试！");
            }


        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 修改学期学年和学分信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/updatexnxqxf", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatexnxqxf(HttpServletRequest request, Pageable pageable)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID"); //课程安排ID
        Float FXF = jsonParam.getFloat("FXF");
        String xnxqlist = jsonParam.getString("xnxqlist");
        String FCourseName = jsonParam.getString("FCourseName");
        String FTotalHours = jsonParam.getString("FTotalHours");
        String FWeeklyStudyHours = jsonParam.getString("FWeeklyStudyHours");
        String FTheoreticalStudyHours = jsonParam.getString("FTheoreticalStudyHours");
        String FPracticalStudyHours = jsonParam.getString("FPracticalStudyHours");

        try {
            String CookiesLogincourseID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogincourseID != null && !CookiesLogincourseID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLogincourseID);
            }
            id = id == null ? "0" : ParamTools.getdeParam(id);
            TTrainingProgramCourseSt trainingProgramCourse = tTrainingProgramCourseStService.findById(Long.parseLong(id));
            if (trainingProgramCourse != null) {
                TTrainingProgramCourseSt tTrainingProgramCourse = new TTrainingProgramCourseSt();
                tTrainingProgramCourse.setFkeyid(trainingProgramCourse.getFkeyid());
                tTrainingProgramCourse.setFxf(FXF);
                if (trainingProgramCourse.getFppstate() == 1 && trainingProgramCourse.getFyzstate() == 1)
                    tTrainingProgramCourse.setFcoursename(FCourseName);
                tTrainingProgramCourse.setFtotalhours(FTotalHours);
                tTrainingProgramCourse.setFweeklystudyhours(FWeeklyStudyHours);
                tTrainingProgramCourse.setFtheoreticalstudyhours(FTheoreticalStudyHours);
                tTrainingProgramCourse.setFpracticalstudyhours(FPracticalStudyHours);
                tTrainingProgramCourse.setFudate(new Date());
                tTrainingProgramCourse.setFuid(Long.parseLong(uid));
                tTrainingProgramCourse.setFyzstate(1);
                tTrainingProgramCourseStService.update(tTrainingProgramCourse);

                if (xnxqlist != null) {
                    if (xnxqlist.equals("")) {
                        //删除全部
                        TTrainingProgramCourseXnxqStExample tTrainingProgramCourseXnxqExample = new TTrainingProgramCourseXnxqStExample();
                        TTrainingProgramCourseXnxqStExample.Criteria criteria = tTrainingProgramCourseXnxqExample.createCriteria();
                        criteria.andFtmidEqualTo(trainingProgramCourse.getFtmid());
                        criteria.andFtpidEqualTo(trainingProgramCourse.getFtpid());
                        criteria.andFtpcstidEqualTo(trainingProgramCourse.getFkeyid());
                        tTrainingProgramCourseXnxqStService.deleteByByExample(tTrainingProgramCourseXnxqExample);
                    } else {
                        //增加新选的
                        JSONArray major_Array = JSONArray.parseArray(xnxqlist);
                        List<Long> tempRoleID = new ArrayList<>();
                        for (Object majorId : major_Array) {
                            Long fMajorId = Long.parseLong(ParamTools.getdeParam(majorId.toString()));
                            TTrainingProgramCourseXnxqStExample tTrainingProgramCourseXnxqExample = new TTrainingProgramCourseXnxqStExample();
                            TTrainingProgramCourseXnxqStExample.Criteria criteria = tTrainingProgramCourseXnxqExample.createCriteria();
                            criteria.andFtmidEqualTo(trainingProgramCourse.getFtmid());
                            criteria.andFtpidEqualTo(trainingProgramCourse.getFtpid());
                            criteria.andFtpcstidEqualTo(trainingProgramCourse.getFkeyid());
                            criteria.andFxnxqidEqualTo(fMajorId);

                            List<TTrainingProgramCourseXnxqSt> list = tTrainingProgramCourseXnxqStService.findByExample(tTrainingProgramCourseXnxqExample);
                            if (list.size() <= 0) {
                                TTrainingProgramCourseXnxqSt tTrainingProgramCourseXnxq = new TTrainingProgramCourseXnxqSt();
                                tTrainingProgramCourseXnxq.setFtmid(trainingProgramCourse.getFtmid());
                                tTrainingProgramCourseXnxq.setFtpid(trainingProgramCourse.getFtpid());
                                tTrainingProgramCourseXnxq.setFtpcstid(trainingProgramCourse.getFkeyid());
                                tTrainingProgramCourseXnxq.setFxnxqid(fMajorId);
                                tTrainingProgramCourseXnxq.setFcid(Long.parseLong(uid));
                                tTrainingProgramCourseXnxq.setFcdate(new Date());
                                tTrainingProgramCourseXnxqStService.save(tTrainingProgramCourseXnxq);
                            }
                            tempRoleID.add(fMajorId);
                        }
                        //删除多余的
                        TTrainingProgramCourseXnxqStExample tCourseMajorExample = new TTrainingProgramCourseXnxqStExample();
                        if (tempRoleID.size() > 0) {
                            tCourseMajorExample.or()
                                    .andFtmidEqualTo(trainingProgramCourse.getFtmid())
                                    .andFtpidEqualTo(trainingProgramCourse.getFtpid())
                                    .andFtpcstidEqualTo(trainingProgramCourse.getFkeyid())
                                    .andFxnxqidNotIn(tempRoleID);
                        } else {
                            tCourseMajorExample.or().andFtmidEqualTo(trainingProgramCourse.getFtmid())
                                    .andFtpidEqualTo(trainingProgramCourse.getFtpid())
                                    .andFtpcstidEqualTo(trainingProgramCourse.getFkeyid());
                        }
                        tTrainingProgramCourseXnxqStService.deleteByByExample(tCourseMajorExample);
                    }
                }
                // 返回值
                object.put("result", "success");
            } else {
                object.put("result", "error");
                object.put("error", "课程信息获取失败，请刷新后重试！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 课程安排匹配课程信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("课程安排匹配课程信息")
    @ResponseBody
    @RequestMapping(value = "/pipei", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String pipei(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");//培养方案ID
        Integer coursestate = jsonParam.getInteger("coursestate");//是否要发布的课程，？  1=显示有效版本，包含编辑 发布状态  2=显示有效发布的
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            //查询当前培养方案下所有 没有匹配过的课程  取出填写名称
            TTrainingProgramCourseStExample tTrainingProgramCourseStExample = new TTrainingProgramCourseStExample();
            TTrainingProgramCourseStExample.Criteria stExampleCriteria = tTrainingProgramCourseStExample.createCriteria();
            stExampleCriteria.andFppstateEqualTo(1);
            List<TTrainingProgramCourseSt> courseStList = tTrainingProgramCourseStService.findByExample(tTrainingProgramCourseStExample);
            List<CourseAPBean> CourseAPlist = new ArrayList<CourseAPBean>();
            if (courseStList.size() > 0) {

                CourseAPBean courseAPBean = null;
                for (TTrainingProgramCourseSt courseSt : courseStList) {
                    courseAPBean = new CourseAPBean();
                    courseAPBean.setFKeyID(courseSt.getFkeyid());
                    courseAPBean.setFName(courseSt.getFcoursename());
                    CourseAPlist.add(courseAPBean);
                }

            }
            //查询全部课程信息  最新版本
            TCourseExample tCourseExample = new TCourseExample();
            TCourseExample.Criteria courseExampleCriteria = tCourseExample.createCriteria();
            courseExampleCriteria.andFvalidEqualTo(1);
            if (coursestate == 2)
                courseExampleCriteria.andFstateEqualTo(coursestate);

            List<TCourse> courseList = tCourseService.findByExample(tCourseExample);
            List<CourseBean> CourseBeanList = new ArrayList<CourseBean>();
            if (courseList.size() > 0) {
                for (TCourse course : courseList) {
                    CourseBean courseBean = new CourseBean();
                    courseBean.setFKeyID(course.getFkeyid());
                    courseBean.setFName(course.getFname());
                    courseBean.setFNo(course.getFno());
                    courseBean.setFEdition(course.getFedition());
                    courseBean.setFState(course.getFstate());
                    CourseBeanList.add(courseBean);
                }
            }


            if (CourseAPlist.size() > 0) {
                if (CourseBeanList.size() > 0) {
                    //循环比对 调用 语义相似度方法，生成json数据。
                    JSONArray xs_JSONArray = new JSONArray();
                    JSONArray xsz_JSONArray = null;
                    JSONObject xs_object = null;
                    JSONObject xsz_object = null;
                    for (CourseAPBean courseAPlist : CourseAPlist) {
                        xs_object = new JSONObject();
                        xs_object.put("id", ParamTools.getEnParam(courseAPlist.getFKeyID().toString()));
                        xs_object.put("name", courseAPlist.getFName());
                        xsz_JSONArray = new JSONArray();
                        for (CourseBean courseBean : CourseBeanList) {
                            boolean semanticallyEqual = ChineseSemanticSimilarityTools.isSemanticallyEqual(courseAPlist.getFName(), courseBean.getFName());
//                            System.out.println(courseAPlist.getFName());
//                            System.out.println(courseBean.getFName());
//                            System.out.println(semanticallyEqual);
//                            System.out.println("=====================================================");
                            if (semanticallyEqual) {
                                //如何为true 在获取一下得分
                                double score = ChineseSemanticSimilarityTools.calculateSemanticSimilarity(courseAPlist.getFName(), courseBean.getFName());
                                xsz_object = new JSONObject();
                                xsz_object.put("id", ParamTools.getEnParam(courseBean.getFKeyID().toString()));
                                xsz_object.put("name", courseBean.getFName());

                                xsz_object.put("matchRate", Math.round(score * 100) + "%");
                                xsz_object.put("courseCode", courseBean.getFNo());
                                xsz_object.put("FState", courseBean.getFState());
                                xsz_object.put("FEdition", courseBean.getFEdition());
                                xsz_JSONArray.add(xsz_object);
                            }
                        }
                        xs_object.put("similarCourses", xsz_JSONArray);
                        xs_object.put("hasManualInput", "true");
                        xs_JSONArray.add(xs_object);
                    }
//                    System.out.println(xs_JSONArray);
                    // 返回值
                    object.put("ppcourselist", xs_JSONArray);
                    object.put("result", "success");
                } else {
                    object.put("result", "error");
                    object.put("error", "未检测到课程信息，请跳转到课程维护添加！");
                }
            } else {
                object.put("result", "error");
                object.put("error", "未检测到需要匹配的课程安排信息！");
            }


            //数据格式：
            /*
            *
            * {
            id: 1,
            name: "大学物理（上册）",
            similarCourses: [
                {
                    id: 101,
                    名称: "大学物理（上册）- 理学院",
                    相似度: 98,
                    课程编号: "PHY101",
                    * 后面可以再自定义
                },
                {
                    id: 102,
                    name: "大学物理（上）- 工学院",
                    matchRate: 85,
                    courseCode: "PHY102",

                },
                {
                    id: 103,
                    name: "大学物理（上册）- 远程教育学院",
                    matchRate: 75,
                    courseCode: "PHY103",

                }
            ],
            hasManualInput: true  //全部为true   添加一个不做选择的项
        },
            *
            *
            *
            *
            * */


        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     *  确认课程绑定
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/coursebangding", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String coursebangding(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String selectedCoursesJson = jsonParam.getString("selectedCoursesJson");
        try {
            String CookiesLogintrainingprogrambyyqID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintrainingprogrambyyqID != null && !CookiesLogintrainingprogrambyyqID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            if (selectedCoursesJson != null && !selectedCoursesJson.equals("")) {
                ObjectMapper objectMapper = new ObjectMapper();
                // 解析为实体类列表
                List<CourseBDBean> courseList = objectMapper.readValue(
                        selectedCoursesJson, new TypeReference<List<CourseBDBean>>() {
                        }
                );
                for (CourseBDBean courseBDBean : courseList) {
                    if (courseBDBean.getIsxz() == 1) {
                        //选择课程的进行绑定
                        String courseStID = ParamTools.getdeParam(courseBDBean.getCourseStID());
                        String courseID = ParamTools.getdeParam(courseBDBean.getCourseID());
                        TTrainingProgramCourseSt ttrainingProgramCourseSt = new TTrainingProgramCourseSt();
                        ttrainingProgramCourseSt.setFkeyid(Long.parseLong(courseStID));
                        ttrainingProgramCourseSt.setFcourseid(Long.parseLong(courseID));
                        ttrainingProgramCourseSt.setFppstate(2);
                        ttrainingProgramCourseSt.setFuid(Long.parseLong(uid));
                        ttrainingProgramCourseSt.setFudate(new Date());
                        tTrainingProgramCourseStService.update(ttrainingProgramCourseSt);

                        TTrainingProgramCourseXnxqStExample tTrainingProgramCourseXnxqStExample = new TTrainingProgramCourseXnxqStExample();
                        tTrainingProgramCourseXnxqStExample.createCriteria().andFtpcstidEqualTo(Long.parseLong(courseStID));
                        TTrainingProgramCourseXnxqSt tTrainingProgramCourseXnxqSt = new TTrainingProgramCourseXnxqSt();
                        tTrainingProgramCourseXnxqSt.setFcourseid(Long.parseLong(courseID));
                        tTrainingProgramCourseXnxqStService.updateByExample(tTrainingProgramCourseXnxqSt,tTrainingProgramCourseXnxqStExample);
                    }
                }
                // 返回值
                object.put("result", "success");
            } else {
                object.put("result", "error");
                object.put("error", "数据拉取失败，请尝试重新匹配！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     *  验证学时学分前，查看是否还存在没有匹配的课程
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/selectpipeicourse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String selectpipeicourse(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id"); //培养方案ID
        try {

            id = id == null ? "0" : ParamTools.getdeParam(id);
            TTrainingProgramCourseStExample tTrainingProgramCourseStExample = new TTrainingProgramCourseStExample();
            tTrainingProgramCourseStExample.createCriteria().andFtpidEqualTo(Long.parseLong(id)).andFppstateEqualTo(1);
            List<TTrainingProgramCourseSt> programCourseStList = tTrainingProgramCourseStService.findByExample(tTrainingProgramCourseStExample);
            if (programCourseStList.size() == 0) {

                TTrainingProgramCourseStExample tTrainingProgramCourseStExample2 = new TTrainingProgramCourseStExample();
                tTrainingProgramCourseStExample2.createCriteria().andFtpidEqualTo(Long.parseLong(id)).andFppstateEqualTo(2);
                List<TTrainingProgramCourseSt> programCourseStList2 = tTrainingProgramCourseStService.findByExample(tTrainingProgramCourseStExample2);


                int error = 1;
                if(programCourseStList2.size() > 0){
                    //再检测一下，绑定的课程是否完善了信息
                    for (TTrainingProgramCourseSt trainingProgramCourseSt : programCourseStList2){
                        //查询课程信息是否填写完整
                        TCourse tCourseServiceById = tCourseService.findById(trainingProgramCourseSt.getFcourseid());
                        if (tCourseServiceById.getFno() == null || tCourseServiceById.getFno().equals("")) {
                            error += 1;
                        }

                        if (tCourseServiceById.getFtype() == null || tCourseServiceById.getFtype().toString().equals("0")) {
                            error += 1;
                        }
                        if (tCourseServiceById.getFcnature() == null || tCourseServiceById.getFcnature().toString().equals("0")) {
                            error += 1;
                        }
                    }
                }
                if(error == 1){
                    // 返回值
                    object.put("result", "success");
                }else{
                    object.put("result", "error");
                    object.put("error", "检测系统中绑定的课程信息未完善，请填写后重试！");
                }
            } else {
                object.put("result", "error");
                object.put("error", "检测系统中还有未匹配的课程，请匹配后重试！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     *  验证学时学分
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/yanzhengxueshixuefen", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String yanzhengxueshixuefen(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id"); //培养方案ID
        try {
            String CookiesLogintrainingprogrambyyqID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintrainingprogrambyyqID != null && !CookiesLogintrainingprogrambyyqID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : ParamTools.getdeParam(id);
            //查询课程安排中 已匹配的课程信息
            TTrainingProgramCourseStExample tTrainingProgramCourseStExample = new TTrainingProgramCourseStExample();
            //已匹配 and 未验证或者验证不通过的
            tTrainingProgramCourseStExample.createCriteria().andFtpidEqualTo(Long.parseLong(id)).andFppstateEqualTo(2).andFyzstateNotEqualTo(2);
            List<TTrainingProgramCourseSt> trainingProgramCourseStList = tTrainingProgramCourseStService.findByExample(tTrainingProgramCourseStExample);
            if (trainingProgramCourseStList.size() > 0) {
                Float FXF = 0f;
                String ftotalhours = "";
                String fweeklystudyhours = "";
                String ftheoreticalstudyhours = "";
                String fpracticalstudyhours = "";


                Float fcredits = 0f;
                String ftotalhours1 = "";
                String fweeklystudyhours1 = "";
                String ftheoreticalstudyhours1 = "";
                String fpracticalstudyhours1 = "";
                TCourse tCourse = null;
                String fcourseName = "";

                String leibiename="";

                //验证结果json
                JSONArray compareCardsData_array = new JSONArray();
                JSONObject compareCardsData_Object = new JSONObject();
                compareCardsData_Object.put("title", "培养方案课程安排验证结果");
                JSONArray pairs_array = null;
                pairs_array = new JSONArray();
                for (TTrainingProgramCourseSt trainingProgramCourseSt : trainingProgramCourseStList) {
                    FXF = 0f;
                    ftotalhours = "";
                    fweeklystudyhours = "";
                    ftheoreticalstudyhours = "";
                    fpracticalstudyhours = "";
                    fcredits = 0f;
                    ftotalhours1 = "";
                    fweeklystudyhours1 = "";
                    ftheoreticalstudyhours1 = "";
                    fpracticalstudyhours1 = "";
                    tCourse = null;
                    fcourseName = "";
                    leibiename = "";
                    //填写信息
                    FXF = trainingProgramCourseSt.getFxf();
                    ftotalhours = trainingProgramCourseSt.getFtotalhours();
                    fweeklystudyhours = trainingProgramCourseSt.getFweeklystudyhours();
                    ftheoreticalstudyhours = trainingProgramCourseSt.getFtheoreticalstudyhours();
                    fpracticalstudyhours = trainingProgramCourseSt.getFpracticalstudyhours();
                    //系统课程信息
                    tCourse = tCourseService.findById(trainingProgramCourseSt.getFcourseid());
                    if (tCourse != null) {
                        fcredits = tCourse.getFcredits();
                        ftotalhours1 = tCourse.getFtotalhours();
                        fweeklystudyhours1 = tCourse.getFweeklystudyhours();
                        ftheoreticalstudyhours1 = tCourse.getFtheoreticalstudyhours();
                        fpracticalstudyhours1 = tCourse.getFpracticalstudyhours();
                        fcourseName = tCourse.getFname();
                        TCourseCategory tCourseCategory = tCourseCategoryService.findById(tCourse.getFtype());
                        leibiename = tCourseCategory == null ? "" : tCourseCategory.getFname();
                    } else {
                        fcredits = 0f;
                        ftotalhours1 = "";
                        fweeklystudyhours1 = "";
                        ftheoreticalstudyhours1 = "";
                        fpracticalstudyhours1 = "";
                        fcourseName = "";
                        leibiename = "";
                    }

                    TTrainingProgramCourseSt tTrainingProgramCourseSt = new TTrainingProgramCourseSt();
                    tTrainingProgramCourseSt.setFkeyid(trainingProgramCourseSt.getFkeyid());
                    tTrainingProgramCourseSt.setFuid(Long.parseLong(uid));
                    tTrainingProgramCourseSt.setFudate(new Date());
                    if (equalsFloatWithBigDecimal(FXF, fcredits) && ftotalhours.equals(ftotalhours1) && fweeklystudyhours.equals(fweeklystudyhours1) && ftheoreticalstudyhours.equals(ftheoreticalstudyhours1) && fpracticalstudyhours.equals(fpracticalstudyhours1)) {
                        //如果相同则修改状态 已通过
                        tTrainingProgramCourseSt.setFyzstate(2);
                    } else {
                        //有不同，修改状态，不通过，生成json 传到结果页面。
                        tTrainingProgramCourseSt.setFyzstate(3);
                        JSONObject pairs_Object = new JSONObject();
                        pairs_Object.put("label", leibiename);
                        JSONArray pairs_Array2 = new JSONArray();

                        JSONObject courses_object = new JSONObject();
                        courses_object.put("name",  trainingProgramCourseSt.getFcoursename());
                        courses_object.put("FCredits", FXF);
                        courses_object.put("FWeeklyStudyHours", fweeklystudyhours);
                        courses_object.put("FTheoreticalStudyHours", ftheoreticalstudyhours);
                        courses_object.put("FPracticalStudyHours", fpracticalstudyhours);
                        courses_object.put("FTotalHours", ftotalhours);
                        pairs_Array2.add(courses_object);
                        courses_object = new JSONObject();
                        courses_object.put("name", fcourseName);
                        courses_object.put("FCredits", fcredits);
                        courses_object.put("FWeeklyStudyHours", fweeklystudyhours1);
                        courses_object.put("FTheoreticalStudyHours", ftheoreticalstudyhours1);
                        courses_object.put("FPracticalStudyHours", fpracticalstudyhours1);
                        courses_object.put("FTotalHours", ftotalhours1);
                        pairs_Array2.add(courses_object);
                        pairs_Object.put("courses", pairs_Array2);
                        pairs_array.add(pairs_Object);
                    }
                    tTrainingProgramCourseStService.update(tTrainingProgramCourseSt);

                }
                compareCardsData_Object.put("pairs", pairs_array);
                compareCardsData_array.add(compareCardsData_Object);


                //先获取课程性质分组
                List<TrainingprogramcourseStyData> getnaturedList = tTrainingProgramCourseStService.getnatureList();
                JSONArray table_array = new JSONArray();
                JSONObject table_object = new JSONObject();
                JSONObject table_object2 = new JSONObject();

                table_object.put("title", "填写课程学时学分分配");
                table_object2.put("title", "系统课程学时学分分配");

                JSONArray courseTypes_array = new JSONArray();
                for (TrainingprogramcourseStyData trainingprogramcourseStyData : getnaturedList) {
                    JSONObject courseTypes_object = new JSONObject();
                    courseTypes_object.put("name", trainingprogramcourseStyData.getFName());
                    courseTypes_object.put("key", SimplePinyinTools.chineseToPinyin(trainingprogramcourseStyData.getFName())); //通过名称转拼音的形式，动态生成对应英文字段
                    courseTypes_array.add(courseTypes_object);
                }

                JSONArray courseTypes_array2 = new JSONArray();
                for (TrainingprogramcourseStyData trainingprogramcourseStyData : getnaturedList) {
                    JSONObject courseTypes_object = new JSONObject();
                    courseTypes_object.put("name", trainingprogramcourseStyData.getFName());
                    courseTypes_object.put("key", SimplePinyinTools.chineseToPinyin(trainingprogramcourseStyData.getFName())); //通过名称转拼音的形式，动态生成对应英文字段
                    courseTypes_array2.add(courseTypes_object);
                }

                table_object.put("courseTypes", courseTypes_array);
                table_object2.put("courseTypes", courseTypes_array2);




                //查询填写课程表格信息
                List<SectionsBean> sectionsBeanList = new ArrayList<>();
                List<SectionsBean> sectionsBeanList2 = new ArrayList<>();
                for (TrainingprogramcourseStyData trainingprogramcourseStyData : getnaturedList) {
                    TrainingprogramcourseStyCS trainingprogramcourseStyCS = new TrainingprogramcourseStyCS();
                    trainingprogramcourseStyCS.setFCNatureID(trainingprogramcourseStyData.getFKeyID());
                    trainingprogramcourseStyCS.setOrderBy("tcc.FPID DESC ,tcc.FKeyID,tcc.FName");
                    List<TrainingprogramcourseStyData> trainingprogramcourseStyDataList = tTrainingProgramCourseStService.getCourseYanZhengst(trainingprogramcourseStyCS);
                    for (TrainingprogramcourseStyData trainingprogramcourseStyData1 : trainingprogramcourseStyDataList) {
                        SectionsBean sectionsBean = new SectionsBean();
                        sectionsBean.setFName(trainingprogramcourseStyData1.getFName());
                        sectionsBean.setFCNatureName(trainingprogramcourseStyData1.getFCNatureName());
                        sectionsBean.setKey(SimplePinyinTools.chineseToPinyin(trainingprogramcourseStyData1.getFCNatureName()));
                        sectionsBean.setFPID(trainingprogramcourseStyData1.getFPID());
                        sectionsBean.setValue(trainingprogramcourseStyData1.getFXF() + "/" + trainingprogramcourseStyData1.getTotalAllHours());
                        sectionsBean.setFXF(trainingprogramcourseStyData1.getFXF());
                        sectionsBean.setToalAllHous(trainingprogramcourseStyData1.getTotalAllHours());
                        sectionsBeanList.add(sectionsBean);
                    }
                    List<TrainingprogramcourseStyData> styDataList = tTrainingProgramCourseStService.getCourseYanZhengxt(trainingprogramcourseStyCS);
                    for (TrainingprogramcourseStyData trainingprogramcourseStyData1 : styDataList) {
                        SectionsBean sectionsBean = new SectionsBean();
                        sectionsBean.setFName(trainingprogramcourseStyData1.getFName());
                        sectionsBean.setFCNatureName(trainingprogramcourseStyData1.getFCNatureName());
                        sectionsBean.setKey(SimplePinyinTools.chineseToPinyin(trainingprogramcourseStyData1.getFCNatureName()));
                        sectionsBean.setFPID(trainingprogramcourseStyData1.getFPID());
                        sectionsBean.setValue(trainingprogramcourseStyData1.getFXF() + "/" + trainingprogramcourseStyData1.getTotalAllHours());
                        sectionsBean.setFXF(trainingprogramcourseStyData1.getFXF());
                        sectionsBean.setToalAllHous(trainingprogramcourseStyData1.getTotalAllHours());
                        sectionsBeanList2.add(sectionsBean);
                    }

                }

//                System.out.println(JSON.toJSONString(sectionsBeanList));
//                System.out.println(JSON.toJSONString(sectionsBeanList2));


                // 核心调用：传入参数，获取组装后的JSON结果
                JSONArray result = assembleCourseData(
                        sectionsBeanList,
                        getnaturedList,
                        tCourseCategoryService
                );

                JSONArray result2 = assembleCourseData(
                        sectionsBeanList2,
                        getnaturedList,
                        tCourseCategoryService
                );


                table_object.put("sections", result);
                table_object2.put("sections", result2);
                // 实际业务中可将result返回给前端/存入数据库等
                table_array.add(table_object);
                table_array.add(table_object2);

//                System.out.println(table_array);
                System.out.println(compareCardsData_array);


                //验证结果不合格的信息
                object.put("compareCardsData", compareCardsData_array);

                object.put("table_array", table_array);
                // 返回值
                object.put("result", "success");
            } else {
                object.put("result", "error");
                object.put("error", "未检测到可验证的信息，请刷新后重试！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    public static boolean equalsFloatWithBigDecimal(float a, float b) {
        // 注意：必须用 String 构造 BigDecimal，否则仍会有精度问题
        BigDecimal bd1 = new BigDecimal(Float.toString(a));
        BigDecimal bd2 = new BigDecimal(Float.toString(b));
        return bd1.compareTo(bd2) == 0;
    }


    private static final String CATEGORY_KEY = "category";
    private static final String ITEMS_KEY = "items";
    private static final String NAME_KEY = "name";
    private static final String DEFAULT_EMPTY_VALUE = "0/0";
    private static final Long SPECIAL_FPID = 1L;

    public JSONArray assembleCourseData(List<SectionsBean> sectionsBeanList,
                                         List<TrainingprogramcourseStyData> getnaturedList,
                                         TCourseCategoryService tCourseCategoryService) {

        JSONArray sectionsArray = new JSONArray();

        // 前置校验
        if (ObjectUtils.isEmpty(sectionsBeanList) || ObjectUtils.isEmpty(getnaturedList)) {

            return sectionsArray;
        }

        // ========== 新增：按fName分组排序 ==========
        List<SectionsBean> sortedSectionsList = sortByFPIDGroup(sectionsBeanList);
//        System.out.println("sortedSectionsList:" + JSON.toJSONString(sortedSectionsList));
        // 预缓存拼音映射
        Map<String, String> naturePinyinMap = preCacheNaturePinyin(getnaturedList);
        Map<Long, String> fpidCategoryMap = new HashMap<>();

        Long currentFpid = null;
        Map<String, JSONObject> currentItemsMap = null;
        JSONObject currentSectionObj = null;

        // ========== 遍历排序后的列表 ==========
        for (SectionsBean sectionsBean : sortedSectionsList) {
            Long fpid = sectionsBean.getFPID();
            if (fpid == null || StringUtils.isBlank(sectionsBean.getFName())) {
                continue;
            }

            // FPID判等逻辑（已修复）
            boolean isFpidChanged = false;
            if (currentFpid == null) {
                isFpidChanged = true;
            } else {
                isFpidChanged = !currentFpid.equals(fpid);
            }

            if (isFpidChanged) {
                // 保存上一分组
                if (currentSectionObj != null && currentItemsMap != null && !currentItemsMap.isEmpty()) {
                    JSONArray itemsArray = convertMapToJsonArray(currentItemsMap);
                    currentSectionObj.put(ITEMS_KEY, itemsArray);
                    sectionsArray.add(currentSectionObj);
                }

                // 重新初始化当前分组
                currentFpid = fpid;
                currentItemsMap = new LinkedHashMap<>();
                currentSectionObj = new JSONObject();

                // 设置分类名称
                String categoryName = getCategoryName(fpid, fpidCategoryMap, sectionsBean, tCourseCategoryService);
                currentSectionObj.put(CATEGORY_KEY, categoryName);
            }

            // 合并item
            buildOrMergeItemObject(sectionsBean, naturePinyinMap, currentItemsMap);
        }

        // 处理最后一个分组
        if (currentSectionObj != null && currentItemsMap != null && !currentItemsMap.isEmpty()) {
            JSONArray itemsArray = convertMapToJsonArray(currentItemsMap);
            currentSectionObj.put(ITEMS_KEY, itemsArray);
            sectionsArray.add(currentSectionObj);
        }


        return sectionsArray;
    }

    // 预缓存拼音映射
    private Map<String, String> preCacheNaturePinyin(List<TrainingprogramcourseStyData> getnaturedList) {
        Map<String, String> naturePinyinMap = new HashMap<>();
        for (TrainingprogramcourseStyData natureData : getnaturedList) {
            if (natureData == null || StringUtils.isBlank(natureData.getFName())) {
                continue;
            }

            String pinyin = SimplePinyinTools.chineseToPinyin(natureData.getFName());
            naturePinyinMap.put(natureData.getFName(), pinyin);
        }
        return naturePinyinMap;
    }

    // 获取分类名称
    private String getCategoryName(Long fpid,
                                   Map<Long, String> fpidCategoryMap,
                                   SectionsBean sectionsBean,
                                   TCourseCategoryService categoryService) {
        if (fpidCategoryMap.containsKey(fpid)) {
            return fpidCategoryMap.get(fpid);
        }

        String categoryName;
        if (SPECIAL_FPID.equals(fpid)) {
            categoryName = sectionsBean.getFName();
        } else {
            TCourseCategory category = categoryService.findById(fpid);
            categoryName = (category != null && StringUtils.isNotBlank(category.getFname()))
                    ? category.getFname() : "";
        }

        fpidCategoryMap.put(fpid, categoryName);
        return categoryName;
    }

    // 构建/合并item（核心：同名项合并不同性质的值）
    private void buildOrMergeItemObject(SectionsBean sectionsBean,
                                        Map<String, String> naturePinyinMap,
                                        Map<String, JSONObject> itemsMap) {
        String itemName = sectionsBean.getFName();
        String currentKey = sectionsBean.getKey(); // bixiu/xuanxiu
        String currentValue = StringUtils.defaultString(sectionsBean.getValue(), DEFAULT_EMPTY_VALUE);

        // 1. 不存在则新建item，初始化所有性质为0/0
        if (!itemsMap.containsKey(itemName)) {
            JSONObject newItem = new JSONObject();
            newItem.put(NAME_KEY, itemName);
            // 初始化所有拼音key为默认值
            for (String pinyinKey : naturePinyinMap.values()) {
                newItem.put(pinyinKey, DEFAULT_EMPTY_VALUE);
            }
            itemsMap.put(itemName, newItem);
        }

        // 2. 覆盖当前性质的值（核心：不管是必修还是选修，都合并到同一个item）
        JSONObject existItem = itemsMap.get(itemName);
        if (StringUtils.isNotBlank(currentKey)) {
            existItem.put(currentKey, currentValue);
        }
    }

    // Map转JSONArray（保持顺序）
    private JSONArray convertMapToJsonArray(Map<String, JSONObject> itemsMap) {
        JSONArray array = new JSONArray();
        for (JSONObject item : itemsMap.values()) {
            array.add(item);
        }
        return array;
    }
    /**
     * 按fPID分组排序，确保相同fPID的元素连续排列
     * @param sectionsBeanList 原始列表
     * @return 按fPID分组排序后的列表
     */
    private List<SectionsBean> sortByFPIDGroup(List<SectionsBean> sectionsBeanList) {
        // 空值校验
        if (ObjectUtils.isEmpty(sectionsBeanList)) {
            return new ArrayList<>();
        }

        // 1. 按fPID分组（key=fPID的字符串形式，value=该PID下的所有元素）
        // 使用LinkedHashMap保证分组的插入顺序
        Map<String, List<SectionsBean>> pidGroupMap = new LinkedHashMap<>();
        for (SectionsBean bean : sectionsBeanList) {
            // 获取fPID并转换为字符串，空值处理为"0"或空字符串
            String fPID = StringUtils.defaultString(
                    bean.getFPID() != null ? bean.getFPID().toString() : "",
                    "0"
            );
            // 初始化分组并添加元素
            pidGroupMap.computeIfAbsent(fPID, k -> new ArrayList<>()).add(bean);
        }

        // 2. 合并分组为有序列表（相同fPID的元素连续）
        List<SectionsBean> sortedList = new ArrayList<>();
        for (List<SectionsBean> group : pidGroupMap.values()) {
            sortedList.addAll(group);
        }

        return sortedList;
    }


    //填写课程实体类
    static class CourseAPBean {
        private Long FKeyID;
        private String FName;

        public Long getFKeyID() {
            return FKeyID;
        }

        public void setFKeyID(Long FKeyID) {
            this.FKeyID = FKeyID;
        }

        public String getFName() {
            return FName;
        }

        public void setFName(String FName) {
            this.FName = FName;
        }
    }

    //课程实体类
    static class CourseBean {
        private Long FKeyID;
        private String FName;
        private String FNo;
        private Integer FState;
        private String FEdition;

        public Long getFKeyID() {
            return FKeyID;
        }

        public void setFKeyID(Long FKeyID) {
            this.FKeyID = FKeyID;
        }

        public String getFName() {
            return FName;
        }

        public void setFName(String FName) {
            this.FName = FName;
        }

        public String getFNo() {
            return FNo;
        }

        public void setFNo(String FNo) {
            this.FNo = FNo;
        }

        public Integer getFState() {
            return FState;
        }

        public void setFState(Integer FState) {
            this.FState = FState;
        }

        public String getFEdition() {
            return FEdition;
        }

        public void setFEdition(String FEdition) {
            this.FEdition = FEdition;
        }
    }

    //课程绑定 实体类
    static class CourseBDBean {
        private String courseStID;
        private String courseStName;
        private Integer isxz;
        private String courseID;

        // 必须有默认构造函数
        public CourseBDBean() {
        }

        // Getter和Setter方法
        public String getCourseStID() {
            return courseStID;
        }

        public void setCourseStID(String courseStID) {
            this.courseStID = courseStID;
        }

        public String getCourseStName() {
            return courseStName;
        }

        public void setCourseStName(String courseStName) {
            this.courseStName = courseStName;
        }

        public Integer getIsxz() {
            return isxz;
        }

        public void setIsxz(Integer isxz) {
            this.isxz = isxz;
        }

        public String getCourseID() {
            return courseID;
        }

        public void setCourseID(String courseID) {
            this.courseID = courseID;
        }
    }


    static class SectionsBean {
        private String FName;
        private String key;
        private String value;
        private Long FPID;
        private String FCNatureName;
        private float FXF;
        private Integer ToalAllHous;

        public float getFXF() {
            return FXF;
        }

        public void setFXF(float FXF) {
            this.FXF = FXF;
        }

        public Integer getToalAllHous() {
            return ToalAllHous;
        }

        public void setToalAllHous(Integer toalAllHous) {
            ToalAllHous = toalAllHous;
        }

        public String getFCNatureName() {
            return FCNatureName;
        }

        public void setFCNatureName(String FCNatureName) {
            this.FCNatureName = FCNatureName;
        }

        public Long getFPID() {
            return FPID;
        }

        public void setFPID(Long FPID) {
            this.FPID = FPID;
        }

        public String getFName() {
            return FName;
        }

        public void setFName(String FName) {
            this.FName = FName;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }



    /**
     *  获取培养方案课程的学期、先修课程关系信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/getttrainingprogramCourseData", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getttrainingprogramCourseData(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FTPID = jsonParam.getString("FTPID");//培养方案ID
        try {
            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);

            TTrainingprogramcourseGxCS tTrainingprogramcourseGxCS = new TTrainingprogramcourseGxCS();
            tTrainingprogramcourseGxCS.setFTPID(Long.parseLong(FTPID));
            tTrainingprogramcourseGxCS.setOrderBy("FKKXQ ");
            List<TTrainingprogramcourseGxData> gxDataList = tTrainingProgramCourseStService.getTTrainingprogramCourseGx(tTrainingprogramcourseGxCS);
            JSONArray graph_nodesarray = new JSONArray();
            JSONArray graph_relationshipsarray = new JSONArray();
            JSONArray nature_relationshipsarray = new JSONArray();
            JSONArray zjsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            if (gxDataList.size() > 0) {
                for (TTrainingprogramcourseGxData gxData : gxDataList) {
                    JSONObject treeObject = new JSONObject();
                    treeObject.put("id", ParamTools.getEnParam(gxData.getFKeyID().toString()));
                    treeObject.put("FCourseID", ParamTools.getEnParam(gxData.getFCourseID().toString()));
                    treeObject.put("FName", gxData.getFName());
                    treeObject.put("FCNature",  ParamTools.getEnParam(gxData.getFCNature().toString()));
                    treeObject.put("group", gxData.getFKKXQ());
                    graph_nodesarray.add(treeObject);

                    TCoursePrerequisitesExample tCoursePrerequisitesExample = new TCoursePrerequisitesExample();
                    tCoursePrerequisitesExample.createCriteria().andFcourseidEqualTo(gxData.getFCourseID());
                    List<TCoursePrerequisites> coursePrerequisitesList = tCoursePrerequisitesService.findByExample(tCoursePrerequisitesExample);
                    if (coursePrerequisitesList.size() > 0) {
                        for (TCoursePrerequisites tCoursePrerequisites : coursePrerequisitesList) {
                            JSONObject relationshipsObject = new JSONObject();
                            relationshipsObject.put("to", ParamTools.getEnParam(tCoursePrerequisites.getFcourseid().toString()));
                            relationshipsObject.put("from", ParamTools.getEnParam(tCoursePrerequisites.getFprereqid().toString()));
//                            relationshipsObject.put("label", "拥有");
//                            relationshipsObject.put("arrows", "to");
                            graph_relationshipsarray.add(relationshipsObject);
                        }
                    }

                }
            }

            //获取课程性质信息列表
            TCourseNatureExample tCourseNatureExample = new TCourseNatureExample();
            tCourseNatureExample.createCriteria().andFstateEqualTo(1);
            List<TCourseNature> tCourseNatures = tCourseNatureService.findByExample(tCourseNatureExample);
            if (tCourseNatures.size() > 0) {
                for (TCourseNature tCourseNature : tCourseNatures) {
                    JSONObject relationshipsObject = new JSONObject();
                    relationshipsObject.put("id", ParamTools.getEnParam(tCourseNature.getFkeyid().toString()));
                    relationshipsObject.put("FName", tCourseNature.getFname());
                    nature_relationshipsarray.add(relationshipsObject);
                }
            }

            jsonObject.put("nature_list", nature_relationshipsarray);
            jsonObject.put("graph_nodes", graph_nodesarray);
            jsonObject.put("graph_relationships", graph_relationshipsarray);
            zjsonArray.add(jsonObject);
            object.put("zjsonArray", zjsonArray);
//            System.out.println(zjsonArray);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
}