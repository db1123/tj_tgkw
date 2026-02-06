package fun.server.controller.trainingprogram;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.model.customQuery.DataRange;
import fun.server.model.customQuery.trainingprogram.TrainingprogramkcCS;
import fun.server.model.customQuery.trainingprogram.TrainingprogramkcData;
import fun.server.service.*;
import fun.tools.ParamTools;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 课程安排管理 相关业务处理
 */
@RestController
@RequestMapping("/s/trainingprogramcourse")
public class TrainingProgramCourse {

    private final TTrainingProgramCourseService tTrainingProgramCourseService;

    private final TCourseCategoryService tCourseCategoryService;

    private final TCourseService tCourseService;

    private final TCourseSemesterService tCourseSemesterService;

    private final TTrainingProgramCourseXnxqService tTrainingProgramCourseXnxqService;

    public TrainingProgramCourse(TTrainingProgramCourseService tTrainingProgramCourseService, TCourseCategoryService tCourseCategoryService, TCourseService tCourseService, TCourseSemesterService tCourseSemesterService, TTrainingProgramCourseXnxqService tTrainingProgramCourseXnxqService) {
        this.tTrainingProgramCourseService = tTrainingProgramCourseService;
        this.tCourseCategoryService = tCourseCategoryService;
        this.tCourseService = tCourseService;
        this.tCourseSemesterService = tCourseSemesterService;
        this.tTrainingProgramCourseXnxqService = tTrainingProgramCourseXnxqService;
    }


    /**
     * 获取课程安排信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogramcourse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogramcourse(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray trainingprogramcourse_Array = new JSONArray();
            // 查询条件
            TTrainingProgramCourseExample TTrainingProgramCourseExample = new TTrainingProgramCourseExample();
            TTrainingProgramCourseExample.Criteria criteria = TTrainingProgramCourseExample.createCriteria();
//            if (name != null && !name.equals("")) {
//                criteria.andFnameLike("%" + name + "%");
//            }
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                TTrainingProgramCourseExample.setOrderByClause(orderSql.substring(1));
            } else {
                TTrainingProgramCourseExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TTrainingProgramCourse> trainingprogramcoursePageInfo = tTrainingProgramCourseService.findByExampleMapper(TTrainingProgramCourseExample, (page - 1) * results, results);
            List<TTrainingProgramCourse> trainingprogramcourse_list = trainingprogramcoursePageInfo.getList();
            for (TTrainingProgramCourse trainingprogramcourse : trainingprogramcourse_list) {
                JSONObject trainingprogramcourse_object = new JSONObject();
                trainingprogramcourse_object.put("key", ParamTools.getEnParam(trainingprogramcourse.getFkeyid().toString()));
                trainingprogramcourse_object.put("FTMID", ParamTools.getEnParam(trainingprogramcourse.getFtmid().toString()));
                trainingprogramcourse_object.put("FTPID", ParamTools.getEnParam(trainingprogramcourse.getFtpid().toString()));
                if (dataall == 1) {
//                    trainingprogramcourse_object.put("FName", trainingprogramcourse.getFname() == null ? "" : trainingprogramcourse.getFname());
                    trainingprogramcourse_object.put("FCID", trainingprogramcourse.getFcid());
                    trainingprogramcourse_object.put("FUID", trainingprogramcourse.getFuid());
                    trainingprogramcourse_object.put("FCDATE", trainingprogramcourse.getFcdate());
                    trainingprogramcourse_object.put("FUDATE", trainingprogramcourse.getFudate());
                } else {
                    trainingprogramcourse_object.put("FName", "*****");
                    trainingprogramcourse_object.put("FCID", "*****");
                    trainingprogramcourse_object.put("FUID", "*****");
                    trainingprogramcourse_object.put("FCDATE", "*****");
                    trainingprogramcourse_object.put("FUDATE", "*****");
                }

                trainingprogramcourse_object.put("FState", trainingprogramcourse.getFstate());
                trainingprogramcourse_Array.add(trainingprogramcourse_object);
            }
            // 返回值
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

    /**
     * 获取课程安排信息_自定义sql
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
            PageInfo<TrainingprogramkcData> trainingprogramcoursePageInfo = tTrainingProgramCourseService.getTrainingprogramCourseData(trainingprogramkcCS, (page - 1) * results, results);
            List<TrainingprogramkcData> trainingprogramcourse_list = trainingprogramcoursePageInfo.getList();
            for (TrainingprogramkcData course : trainingprogramcourse_list) {
                JSONObject trainingprogramcourse_object = new JSONObject();
                trainingprogramcourse_object.put("key", ParamTools.getEnParam(course.getFKeyID().toString()));
                trainingprogramcourse_object.put("FTrainingCourseID", ParamTools.getEnParam(course.getFTrainingCourseID().toString()));
                trainingprogramcourse_object.put("FType", course.getFTypeID() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(course.getFTypeID().toString()));
                trainingprogramcourse_object.put("FCNature", course.getFNatureID() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(course.getFNatureID().toString()));
                trainingprogramcourse_object.put("FName", course.getFName() == null ? "" : course.getFName());
                trainingprogramcourse_object.put("FYWName", course.getFYWName() == null ? "" : course.getFYWName());
                trainingprogramcourse_object.put("FNo", course.getFNo() == null ? "" : course.getFNo());
                trainingprogramcourse_object.put("FCredits", course.getFCredits() == null ? "" : course.getFCredits());
                trainingprogramcourse_object.put("FJYXNXQ", course.getFJYXNXQ() == null ? "" : course.getFJYXNXQ());
                trainingprogramcourse_object.put("FXN", course.getFXN() == null ? "" : course.getFXN());
                trainingprogramcourse_object.put("FXQ", course.getFXQ() == null ? "" : course.getFXQ());
                trainingprogramcourse_object.put("FXF", course.getFXF() == null ? "" : course.getFXF());
                trainingprogramcourse_object.put("FSJZS", course.getFSJZS() == null ? "" : course.getFSJZS());
                trainingprogramcourse_object.put("FIstk", course.getFIstk());
                trainingprogramcourse_object.put("FCDATE", course.getFCDATE());
                trainingprogramcourse_object.put("FUDATE", course.getFUDATE());
                TCourseCategory tCourseCategory = tCourseCategoryService.findById(course.getFTypeID());
                trainingprogramcourse_object.put("FTypeName", getPName(tCourseCategory.getFpid(), tCourseCategory.getFname()));
                trainingprogramcourse_object.put("FCNatureName", course.getFNatureName() == null ? "" : course.getFNatureName());

                if (course.getFTrainingCourseID() != null) {
                    TTrainingProgramCourse programCourse = tTrainingProgramCourseService.findById(course.getFTrainingCourseID());
                    if (programCourse != null) {
                        List<String> group1 = new ArrayList<>();
                        TTrainingProgramCourseXnxqExample tTrainingProgramCourseXnxqExample = new TTrainingProgramCourseXnxqExample();
                        tTrainingProgramCourseXnxqExample.createCriteria().andFtpidEqualTo(programCourse.getFtpid()).andFtmidEqualTo(programCourse.getFtmid()).andFcourseidEqualTo(programCourse.getFcourseid());
                        List<TTrainingProgramCourseXnxq> courseXnxqList = tTrainingProgramCourseXnxqService.findByExample(tTrainingProgramCourseXnxqExample);
                        if (courseXnxqList.size() > 0) {
                            for (TTrainingProgramCourseXnxq trainingProgramCourseXnxq : courseXnxqList) {
                                TCourseSemester courseSemester = tCourseSemesterService.findById(trainingProgramCourseXnxq.getFxnxqid());
                                group1.add(courseSemester.getFjname());
                            }
                        }

                        if (group1.size() > 0) {
                            if (group1.size() == 1) {
                                trainingprogramcourse_object.put("FSemesterName", group1.get(0).replaceAll("-", "年"));
                            } else {
                                trainingprogramcourse_object.put("FSemesterName", generateRange(group1));

                            }
                        }
                    } else {
                        trainingprogramcourse_object.put("FSemesterName", "未设置");
                    }

                } else {
                    trainingprogramcourse_object.put("FSemesterName", "未设置");
                }


                trainingprogramcourse_Array.add(trainingprogramcourse_object);
            }
            // 返回值
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
     * 获取培养方案中课程信息  联动
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatatrainingprogramcourseSelectld", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatatrainingprogramcourseSelectld(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String FTPID = jsonParam.getString("FTPID");

        String search = request.getParameter("search");
        String FTPID = request.getParameter("FTPID");
        try {
            FTPID = FTPID == null || FTPID.equals("") ? "0" : ParamTools.getdeParam(FTPID);
            // 获取数据库记录
            JSONArray trainingprogramcourse_Array = new JSONArray();
            TrainingprogramkcCS trainingprogramkcCS = new TrainingprogramkcCS();
            trainingprogramkcCS.setFTPID(Long.parseLong(FTPID));
            if (search != null && !search.equals("")) {
                trainingprogramkcCS.setFName(search);
            }
            List<TrainingprogramkcData> trainingprogramkcDataList = tTrainingProgramCourseService.getTrainingprogramCourseSelect(trainingprogramkcCS);

            for (TrainingprogramkcData trainingprogramkcData : trainingprogramkcDataList) {
                JSONObject abilitylevel_object = new JSONObject();
                abilitylevel_object.put("id", ParamTools.getEnParam(trainingprogramkcData.getFKeyID().toString()));
                abilitylevel_object.put("text", trainingprogramkcData.getFNo() + "/" + trainingprogramkcData.getFName() + "/" + trainingprogramkcData.getFYWName());
                trainingprogramcourse_Array.add(abilitylevel_object);
            }
            // 返回值
            object.put("list", trainingprogramcourse_Array);
            object.put("results", trainingprogramcourse_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取课程安排信息_自定义sql ——打印数据获取
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogramcoursesqldy", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogramcoursesqldy(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);

        String tpID = jsonParam.getString("tpID");
        try {
            // 获取数据库记录
            JSONArray trainingprogramcourse_Array = new JSONArray();
            // 查询条件
            TrainingprogramkcCS trainingprogramkcCS = new TrainingprogramkcCS();
            tpID = tpID == null ? "0" : ParamTools.getdeParam(tpID);
            trainingprogramkcCS.setFTPID(Long.parseLong(tpID));
            trainingprogramkcCS.setOrderBy("FCDATE desc");

            List<TrainingprogramkcData> trainingprogramcourse_list = tTrainingProgramCourseService.getTrainingprogramCourseDayin(trainingprogramkcCS);
//            {
//                "课程代码": "CS101",
//                    "中文课程名称": "计算机科学导论",
//                    "英文课程名称": "Introduction to Computer Science",
//                    "学分": 3,
//                    "必修学分": 3,
//                    "选修学分": 0,
//                    "总课时": 48,
//                    "授课课时": 32,
//                    "上机课时": 12,
//                    "实验课时": 4,
//                    "实践课时": 8,
//                    "实践周数": 2,
//                    "建议修读学期": "1",
//                    "是否必修": 1,
//                    "开课院系": "计算机学院",
//                    "备注": ""
//            },
            for (TrainingprogramkcData course : trainingprogramcourse_list) {
                JSONObject trainingprogramcourse_object = new JSONObject();
//                trainingprogramcourse_object.put("key", ParamTools.getEnParam(course.getFKeyID().toString()));
//                trainingprogramcourse_object.put("FTrainingCourseID", ParamTools.getEnParam(course.getFTrainingCourseID().toString()));
//
//                trainingprogramcourse_object.put("FType", course.getFTypeID() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(course.getFTypeID().toString()));
//                trainingprogramcourse_object.put("FCNature", course.getFNatureID()  == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(course.getFNatureID().toString()));
                trainingprogramcourse_object.put("课程名称", course.getFName() == null ? "" : course.getFName());
                trainingprogramcourse_object.put("课程英文名称", course.getFYWName() == null ? "" : course.getFYWName());
                trainingprogramcourse_object.put("课程编号", course.getFNo() == null ? "" : course.getFNo());
                trainingprogramcourse_object.put("学分", course.getFCredits() == null ? "" : course.getFCredits());
                trainingprogramcourse_object.put("学年学期", course.getFJYXNXQ() == null ? "" : course.getFJYXNXQ());
                trainingprogramcourse_object.put("实践周数", course.getFSJZS() == null ? "" : course.getFSJZS());
                trainingprogramcourse_object.put("是否停课", course.getFIstk());
                trainingprogramcourse_object.put("课程描述", course.getFCon());
                trainingprogramcourse_object.put("FPYName", course.getFTTPName() == null ? "" : course.getFTTPName());
                TCourseCategory tCourseCategory = tCourseCategoryService.findById(course.getFTypeID());
                trainingprogramcourse_object.put("课程类别", getPName(tCourseCategory.getFpid(), tCourseCategory.getFname()));
                trainingprogramcourse_object.put("课程性质", course.getFNatureName() == null ? "" : course.getFNatureName());
                trainingprogramcourse_Array.add(trainingprogramcourse_object);
            }
            // 返回值
            object.put("courseData", trainingprogramcourse_Array);

            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取课程安排信息
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
            // 查询课程安排信息
            TTrainingProgramCourse trainingprogramcourse = tTrainingProgramCourseService.findById(key);
            JSONObject trainingprogramcourse_object = new JSONObject();
            trainingprogramcourse_object.put("key", ParamTools.getEnParam(trainingprogramcourse.getFkeyid().toString()));
            trainingprogramcourse_object.put("FTMID", ParamTools.getEnParam(trainingprogramcourse.getFtmid().toString()));
            trainingprogramcourse_object.put("FTPID", ParamTools.getEnParam(trainingprogramcourse.getFtpid().toString()));
            trainingprogramcourse_object.put("FXF", trainingprogramcourse.getFxf() == null ? "" : trainingprogramcourse.getFxf());
            trainingprogramcourse_object.put("FCID", trainingprogramcourse.getFcid());
            trainingprogramcourse_object.put("FUID", trainingprogramcourse.getFuid());
            trainingprogramcourse_object.put("FCDATE", trainingprogramcourse.getFcdate());
            trainingprogramcourse_object.put("FUDATE", trainingprogramcourse.getFudate());
            trainingprogramcourse_object.put("FState", trainingprogramcourse.getFstate());


            //查询学年学期
            JSONArray prerequisite_Array = new JSONArray();
            TTrainingProgramCourseXnxqExample tTrainingProgramCourseXnxqExample = new TTrainingProgramCourseXnxqExample();
            tTrainingProgramCourseXnxqExample.createCriteria().andFtmidEqualTo(trainingprogramcourse.getFtmid()).andFtpidEqualTo(trainingprogramcourse.getFtpid()).andFcourseidEqualTo(trainingprogramcourse.getFcourseid());
            List<TTrainingProgramCourseXnxq> courseXnxqList = tTrainingProgramCourseXnxqService.findByExample(tTrainingProgramCourseXnxqExample);

            for (TTrainingProgramCourseXnxq tTrainingProgramCourseXnxq : courseXnxqList) {
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
                List<TTrainingProgramCourse> courseList = tTrainingProgramCourseService.findByExample(trainingprogramcourseExample);

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
                    List<TTrainingProgramCourse> programList = tTrainingProgramCourseService.findByExample(trainingprogramcourseExample);
                    if (programList.size() == 0) {
                        String CookiesLogintrainingprogramcourseID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                        String uid = ""; // 当前登录用户ID
                        if (CookiesLogintrainingprogramcourseID != null && !CookiesLogintrainingprogramcourseID.equals("")) {
                            uid = ParamTools.getdeParam(CookiesLogintrainingprogramcourseID);
                        }
                        TCourse course = tCourseService.findById(fcourseid);
                        // 新建课程安排信息
                        TTrainingProgramCourse trainingprogramcourse = new TTrainingProgramCourse();
                        trainingprogramcourse.setFcourseid(fcourseid);
                        trainingprogramcourse.setFtmid(Long.parseLong(FTMID));
                        trainingprogramcourse.setFtpid(Long.parseLong(FTPID));
                        trainingprogramcourse.setForder(forder);
                        trainingprogramcourse.setFcid(Long.parseLong(uid));
                        trainingprogramcourse.setFcdate(new Date());
                        trainingprogramcourse.setFxn(course.getFjyxnxq());
                        trainingprogramcourse.setFxq(course.getFjyxq());
                        trainingprogramcourse.setFxf(course.getFcredits());
                        tTrainingProgramCourseService.save(trainingprogramcourse);
                        try {


                            TCourseSemesterExample tCourseSemesterExample = new TCourseSemesterExample();
                            tCourseSemesterExample.createCriteria().andFjnameEqualTo(course.getFjyxnxq() + "-" + course.getFjyxq());
                            List<TCourseSemester> semesterList = tCourseSemesterService.findByExample(tCourseSemesterExample);
                            if (semesterList.size() > 0) {
                                TTrainingProgramCourseXnxq tTrainingProgramCourseXnxq = new TTrainingProgramCourseXnxq();
                                tTrainingProgramCourseXnxq.setFcourseid(fcourseid);
                                tTrainingProgramCourseXnxq.setFtmid(Long.parseLong(FTMID));
                                tTrainingProgramCourseXnxq.setFtpid(Long.parseLong(FTPID));
                                tTrainingProgramCourseXnxq.setFxnxqid(semesterList.get(0).getFkeyid());
                                tTrainingProgramCourseXnxq.setFcid(Long.parseLong(uid));
                                tTrainingProgramCourseXnxq.setFcdate(new Date());
                                tTrainingProgramCourseXnxqService.save(tTrainingProgramCourseXnxq);
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
     * 修改课程安排信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改课程安排信息")
    @ResponseBody
    @RequestMapping(value = "/updatetrainingprogramcourse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatetrainingprogramcourse(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FCourseID = jsonParam.getString("FCourseID");
        String FTMID = jsonParam.getString("FTMID");
        String FTPID = jsonParam.getString("FTPID");
        try {
            FTMID = FTMID == null ? "0" : ParamTools.getdeParam(FTMID);
            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);
            FCourseID = FCourseID == null ? "0" : ParamTools.getdeParam(FCourseID);
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            TTrainingProgramCourseExample trainingprogramcourseExample = new TTrainingProgramCourseExample();
            TTrainingProgramCourseExample.Criteria criteria = trainingprogramcourseExample.createCriteria();
            criteria.andFcourseidEqualTo(Long.parseLong(FCourseID));
            criteria.andFkeyidNotEqualTo(key);
            criteria.andFtmidEqualTo(Long.parseLong(FTMID));
            criteria.andFtpidEqualTo(Long.parseLong(FTPID));
            List<TTrainingProgramCourse> programList = tTrainingProgramCourseService.findByExample(trainingprogramcourseExample);
            if (programList.size() == 0) {
                String CookiesLogintrainingprogramcourseID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintrainingprogramcourseID != null && !CookiesLogintrainingprogramcourseID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintrainingprogramcourseID);
                }
                // 更新课程安排信息
                TTrainingProgramCourse trainingprogramcourse = new TTrainingProgramCourse();
                trainingprogramcourse.setFkeyid(key);
                trainingprogramcourse.setFcourseid(Long.parseLong(FCourseID));
                trainingprogramcourse.setFuid(Long.parseLong(uid));
                trainingprogramcourse.setFudate(new Date());
                tTrainingProgramCourseService.update(trainingprogramcourse);
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
     * 删除课程安排信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除课程安排信息")
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
            tTrainingProgramCourseService.deleteById(Long.parseLong(id));
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
     * 变更课程安排信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statetrainingprogramcourse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statetrainingprogramcourse(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLogintrainingprogramcourseID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintrainingprogramcourseID != null && !CookiesLogintrainingprogramcourseID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TTrainingProgramCourse trainingprogramcourse = new TTrainingProgramCourse();
            trainingprogramcourse.setFkeyid(Long.parseLong(id));
            trainingprogramcourse.setFuid(Long.parseLong(uid));
            trainingprogramcourse.setFudate(new Date());
            trainingprogramcourse.setFstate(Integer.valueOf(state));
            tTrainingProgramCourseService.update(trainingprogramcourse);
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
     * 获取专业信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDataxnxqSelectsql", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDataxnxqSelectsql(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray major_Array = new JSONArray();

            TCourseSemesterExample tCourseSemesterExample = new TCourseSemesterExample();
            TCourseSemesterExample.Criteria criteria = tCourseSemesterExample.createCriteria();


            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            tCourseSemesterExample.setOrderByClause("FName asc");
            List<TCourseSemester> tCourseSemesterList = tCourseSemesterService.findByExample(tCourseSemesterExample);
            for (TCourseSemester tCourseSemester : tCourseSemesterList) {
                JSONObject major_object = new JSONObject();
                major_object.put("id", ParamTools.getEnParam(tCourseSemester.getFkeyid().toString()));
                major_object.put("text", tCourseSemester.getFname());
                major_Array.add(major_object);
            }
            // 返回值

            object.put("list", major_Array);
            object.put("results", major_Array);
            object.put("result", "success");
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


        try {
            String CookiesLogincourseID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogincourseID != null && !CookiesLogincourseID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLogincourseID);
            }
            id = id == null ? "0" : ParamTools.getdeParam(id);
            TTrainingProgramCourse trainingProgramCourse = tTrainingProgramCourseService.findById(Long.parseLong(id));
            if(trainingProgramCourse!=null){
                TTrainingProgramCourse tTrainingProgramCourse = new TTrainingProgramCourse();
                tTrainingProgramCourse.setFkeyid(trainingProgramCourse.getFkeyid());
                tTrainingProgramCourse.setFxf(FXF);
                tTrainingProgramCourse.setFudate(new Date());
                tTrainingProgramCourse.setFuid(Long.parseLong(uid));
                tTrainingProgramCourseService.update(tTrainingProgramCourse);

                if (xnxqlist != null) {
                    if (xnxqlist.equals("")) {
                        //删除全部
                        TTrainingProgramCourseXnxqExample tTrainingProgramCourseXnxqExample = new TTrainingProgramCourseXnxqExample();
                        TTrainingProgramCourseXnxqExample.Criteria criteria = tTrainingProgramCourseXnxqExample.createCriteria();
                        criteria.andFtmidEqualTo(trainingProgramCourse.getFtmid());
                        criteria.andFtpidEqualTo(trainingProgramCourse.getFtpid());
                        criteria.andFcourseidEqualTo(trainingProgramCourse.getFcourseid());
                        tTrainingProgramCourseXnxqService.deleteByByExample(tTrainingProgramCourseXnxqExample);
                    } else {
                        //增加新选的
                        JSONArray major_Array = JSONArray.parseArray(xnxqlist);
                        List<Long> tempRoleID = new ArrayList<>();
                        for (Object majorId: major_Array) {
                            Long fMajorId = Long.parseLong(ParamTools.getdeParam(majorId.toString()));
                            TTrainingProgramCourseXnxqExample tTrainingProgramCourseXnxqExample = new TTrainingProgramCourseXnxqExample();
                            TTrainingProgramCourseXnxqExample.Criteria criteria = tTrainingProgramCourseXnxqExample.createCriteria();
                            criteria.andFtmidEqualTo(trainingProgramCourse.getFtmid());
                            criteria.andFtpidEqualTo(trainingProgramCourse.getFtpid());
                            criteria.andFcourseidEqualTo(trainingProgramCourse.getFcourseid());
                            criteria.andFxnxqidEqualTo(fMajorId);;
                            List<TTrainingProgramCourseXnxq> list = tTrainingProgramCourseXnxqService.findByExample(tTrainingProgramCourseXnxqExample);
                            if (list.size() <= 0) {
                                TTrainingProgramCourseXnxq tTrainingProgramCourseXnxq = new TTrainingProgramCourseXnxq();
                                tTrainingProgramCourseXnxq.setFtmid(trainingProgramCourse.getFtmid());
                                tTrainingProgramCourseXnxq.setFtpid(trainingProgramCourse.getFtpid());
                                tTrainingProgramCourseXnxq.setFcourseid(trainingProgramCourse.getFcourseid());
                                tTrainingProgramCourseXnxq.setFxnxqid(fMajorId);
                                tTrainingProgramCourseXnxq.setFcid(Long.parseLong(uid));
                                tTrainingProgramCourseXnxq.setFcdate(new Date());
                                tTrainingProgramCourseXnxqService.save(tTrainingProgramCourseXnxq);
                            }
                            tempRoleID.add(fMajorId);
                        }
                        //删除多余的
                        TTrainingProgramCourseXnxqExample tCourseMajorExample = new TTrainingProgramCourseXnxqExample();
                        if (tempRoleID.size() > 0) {
                            tCourseMajorExample.or()
                                    .andFtmidEqualTo(trainingProgramCourse.getFtmid())
                                    .andFtpidEqualTo(trainingProgramCourse.getFtpid())
                                    .andFcourseidEqualTo(trainingProgramCourse.getFcourseid())
                                    .andFxnxqidNotIn(tempRoleID);
                        } else {
                            tCourseMajorExample.or().andFtmidEqualTo(trainingProgramCourse.getFtmid())
                                    .andFtpidEqualTo(trainingProgramCourse.getFtpid())
                                    .andFcourseidEqualTo(trainingProgramCourse.getFcourseid());
                        }
                        tTrainingProgramCourseXnxqService.deleteByByExample(tCourseMajorExample);
                    }
                }
                // 返回值
                object.put("result", "success");
            }else{
                object.put("result", "error");
                object.put("error","课程信息获取失败，请刷新后重试！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

}