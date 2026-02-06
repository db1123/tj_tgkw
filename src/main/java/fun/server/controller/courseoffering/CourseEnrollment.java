package fun.server.controller.courseoffering;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.controller.interfaceD.AI_interface;
import fun.server.model.*;
import fun.server.model.customQuery.courseEnrollment.courseEnrollmentCS;
import fun.server.model.customQuery.courseEnrollment.courseEnrollmentCourseCS;
import fun.server.model.customQuery.courseEnrollment.courseEnrollmentCourseData;
import fun.server.model.customQuery.courseEnrollment.courseEnrollmentData;
import fun.server.model.customQuery.major.TCLassData;
import fun.server.model.customQuery.major.TClassCS;
import fun.server.service.*;
import fun.server.service.impl.TCourseAbilityConditionServiceImpl;
import fun.tools.ParamTools;
import fun.tools.SnowflakeIdWorker;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 学生选课管理 相关业务处理
 */
@RestController
@RequestMapping("/s/courseenrollment")
public class CourseEnrollment {

    private final TCourseEnrollmentService tCourseEnrollmentService;

    private final TCourseOfferingService tCourseOfferingService;

    private final TCourseService tCourseService;

    private final TStudentService tStudentService;

    private final TCourseNatureService tCourseNatureService;

    private final TCourseCategoryService tCourseCategoryService;

    private final TClassStudentService tClassStudentService;

    private final TTrainingProgramService tTrainingProgramService;

    private final TTrainingProgramCourseService tTrainingProgramCourseService;

    private final TMajorService tMajorService;

    private final TStudentAbilityService tStudentAbilityService;
    private final TCourseAbilityConditionServiceImpl tcourseabilityconditionService;

    private final TTrainingProgramService tTrainingprogramService;

    private final TInterfaceTypeService tInterfaceTypeService;
    private final TInterfaceService tInterfaceService;

    private final TCourseInterfaceService tCourseInterfaceService;


    public CourseEnrollment(TCourseEnrollmentService tCourseEnrollmentService, TCourseOfferingService tCourseOfferingService, TCourseService tCourseService, TStudentService tStudentService, TCourseNatureService tCourseNatureService, TCourseCategoryService tCourseCategoryService, TClassStudentService tClassStudentService, TTrainingProgramService tTrainingProgramService, TTrainingProgramCourseService tTrainingProgramCourseService, TMajorService tMajorService, TStudentAbilityService tStudentAbilityService, TCourseAbilityConditionServiceImpl tcourseabilityconditionService, TTrainingProgramService tTrainingprogramService, TInterfaceTypeService tInterfaceTypeService, TInterfaceService tInterfaceService, TCourseInterfaceService tCourseInterfaceService) {
        this.tCourseEnrollmentService = tCourseEnrollmentService;
        this.tCourseOfferingService = tCourseOfferingService;
        this.tCourseService = tCourseService;
        this.tStudentService = tStudentService;
        this.tCourseNatureService = tCourseNatureService;
        this.tCourseCategoryService = tCourseCategoryService;
        this.tClassStudentService = tClassStudentService;
        this.tTrainingProgramService = tTrainingProgramService;
        this.tTrainingProgramCourseService = tTrainingProgramCourseService;
        this.tMajorService = tMajorService;
        this.tStudentAbilityService = tStudentAbilityService;
        this.tcourseabilityconditionService = tcourseabilityconditionService;
        this.tTrainingprogramService = tTrainingprogramService;
        this.tInterfaceTypeService = tInterfaceTypeService;
        this.tInterfaceService = tInterfaceService;
        this.tCourseInterfaceService = tCourseInterfaceService;
    }

    @Value("${studentmb.path}")
    private String studentmb;


    @Value("${studentabilitymb.path}")
    private String studentabilitymb;

    /**
     * 获取学生选课信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycourseenrollment", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycourseenrollment(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String peiyang = jsonParam.getString("peiyang");
        String coursename = jsonParam.getString("coursename");
        String courseno = jsonParam.getString("courseno");
        String studentname = jsonParam.getString("studentname");
        String studentno = jsonParam.getString("studentno");
        String ftel = jsonParam.getString("ftel");
        String FLTypeID = jsonParam.getString("FLTypeID");
        String FNatureID = jsonParam.getString("FNatureID");
        String fipass = jsonParam.getString("fipass");
        int FSex = jsonParam.getInteger("FSex");

        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray courseenrollment_Array = new JSONArray();
            // 查询条件
            courseEnrollmentCS courseEnrollmentCS = new courseEnrollmentCS();
            courseEnrollmentCS.setFCourseName(coursename);
            courseEnrollmentCS.setFStudentNo(studentno);
            courseEnrollmentCS.setFTel(ftel);
            courseEnrollmentCS.setFStudentName(studentname);
            courseEnrollmentCS.setFCourseNo(courseno);
            courseEnrollmentCS.setFTPName(peiyang);
            if (FLTypeID != null && !FLTypeID.equals("") && !FLTypeID.equals("1")) {
                FLTypeID = FLTypeID == null ? "0" : ParamTools.getdeParam(FLTypeID);
                courseEnrollmentCS.setFLTypeID(Long.parseLong(FLTypeID));

            }
            if (FNatureID != null && !FNatureID.equals("")) {
                FNatureID = FNatureID == null ? "0" : ParamTools.getdeParam(FNatureID);
//                System.out.println(FNatureID);
                if (!FNatureID.equals("-1"))

                    courseEnrollmentCS.setFNatureID(Long.parseLong(FNatureID));
            }

            if (fipass != null && !fipass.equals(""))
                courseEnrollmentCS.setFIfPass(Integer.parseInt(fipass));


            courseEnrollmentCS.setFSex(FSex);

            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                courseEnrollmentCS.setOrderBy(orderSql.substring(1));
            } else {
                courseEnrollmentCS.setOrderBy("tco.FCDATE desc");
            }
            PageInfo<courseEnrollmentData> courseenrollmentPageInfo = tCourseEnrollmentService.getCourseEnrollmentDataList(courseEnrollmentCS, (page - 1) * results, results);
            List<courseEnrollmentData> courseenrollment_list = courseenrollmentPageInfo.getList();

            for (courseEnrollmentData courseenrollment : courseenrollment_list) {
                JSONObject courseenrollment_object = new JSONObject();
                courseenrollment_object.put("key", ParamTools.getEnParam(courseenrollment.getFKeyID().toString()));
                courseenrollment_object.put("FCOID", ParamTools.getEnParam(courseenrollment.getFCOID().toString()));
                TTrainingProgram tTrainingProgram = tTrainingprogramService.findById(courseenrollment.getFCOID());
                courseenrollment_object.put("FCOName", tTrainingProgram == null ? "" : tTrainingProgram.getFname());
                courseenrollment_object.put("FStudentID", ParamTools.getEnParam(courseenrollment.getFStudentID().toString()));
                courseenrollment_object.put("FCourseName", courseenrollment.getFCourseName() == null ? "" : courseenrollment.getFCourseName());
                courseenrollment_object.put("FYWName", courseenrollment.getFCourseYWName() == null ? "" : courseenrollment.getFCourseYWName());
                courseenrollment_object.put("FTPName", courseenrollment.getFTPName() == null ? "" : courseenrollment.getFTPName());
                courseenrollment_object.put("FCourseNo", courseenrollment.getFCourseNo() == null ? "" : courseenrollment.getFCourseNo());
                courseenrollment_object.put("FSemesterName", courseenrollment.getFSemesterName() == null ? "" : courseenrollment.getFSemesterName());
                courseenrollment_object.put("FTeacherName", courseenrollment.getFTeacherName() == null ? "" : courseenrollment.getFTeacherName());
                courseenrollment_object.put("FEnrollTime", courseenrollment.getFEnrollTime() == null ? "" : courseenrollment.getFEnrollTime());
                courseenrollment_object.put("FStatus", courseenrollment.getFStatus() == null ? -1 : courseenrollment.getFStatus());
                courseenrollment_object.put("FEdition", courseenrollment.getFCourseEdition() == null ? -1 : courseenrollment.getFCourseEdition());
                courseenrollment_object.put("FNote", courseenrollment.getFNote() == null ? "" : courseenrollment.getFNote());
                courseenrollment_object.put("FMode", courseenrollment.getFMode() == null ? -1 : courseenrollment.getFMode());
                courseenrollment_object.put("FIfPass", courseenrollment.getFIfPass() == null ? -1 : courseenrollment.getFIfPass());
                courseenrollment_object.put("FScore", courseenrollment.getFScore());
                courseenrollment_object.put("FTypeID", courseenrollment.getFLTypeID() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(courseenrollment.getFLTypeID().toString()));
                TCourseCategory tCourseCategory = tCourseCategoryService.findById(courseenrollment.getFLTypeID());
                courseenrollment_object.put("FTypeName", getPName(tCourseCategory.getFpid(), tCourseCategory.getFname()));
                courseenrollment_object.put("FCNature", courseenrollment.getFNatureID() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(courseenrollment.getFNatureID().toString()));
                TCourseNature courseNature = tCourseNatureService.findById(courseenrollment.getFNatureID());
                courseenrollment_object.put("FCNatureName", courseNature == null ? "" : courseNature.getFname());
                courseenrollment_object.put("FStudentNo", courseenrollment.getFStudentNo() == null ? "" : courseenrollment.getFStudentNo());
                courseenrollment_object.put("FStudentName", courseenrollment.getFStudentName() == null ? "" : courseenrollment.getFStudentName());
                courseenrollment_object.put("FTel", courseenrollment.getFTel() == null ? "" : courseenrollment.getFTel());
                courseenrollment_object.put("FStudentSex", courseenrollment.getFStudentSex() == null ? -1 : courseenrollment.getFStudentSex());
                courseenrollment_object.put("FCJType", courseenrollment.getFCJType() == null ? -1 : courseenrollment.getFCJType());

                TClassCS tClassCS = new TClassCS();
                tClassCS.setFStudentID(courseenrollment.getFStudentID());
                List<TCLassData> selectedClassList = tMajorService.selectClassInfo(tClassCS);
                if (selectedClassList.size() > 0) {
                    String classname = "";

                    for (TCLassData classData : selectedClassList) {
                        classname += " 【" + classData.getFClassName() + "】，";
                    }
                    if (classname.length() > 0) {
                        classname = classname.substring(0, classname.length() - 1);
                    }
                    courseenrollment_object.put("FClassName", classname);
                } else {
                    courseenrollment_object.put("FClassName", "");
                }

                courseenrollment_Array.add(courseenrollment_object);
            }
            // 返回值
            object.put("list", courseenrollment_Array);
            object.put("total", courseenrollmentPageInfo.getTotal()); // 总行数
            object.put("page", courseenrollmentPageInfo.getPageNum()); // 当前页数
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
     * 根据ID获取学生选课信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycourseenrollmentInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycourseenrollmentInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询学生选课信息
            TCourseEnrollment courseenrollment = tCourseEnrollmentService.findById(key);
            JSONObject courseenrollment_object = new JSONObject();
            courseenrollment_object.put("key", ParamTools.getEnParam(courseenrollment.getFkeyid().toString()));
            courseenrollment_object.put("FCID", courseenrollment.getFcid());
            courseenrollment_object.put("FUID", courseenrollment.getFuid());
            courseenrollment_object.put("FCDATE", courseenrollment.getFcdate());
            courseenrollment_object.put("FUDATE", courseenrollment.getFudate());
            courseenrollment_object.put("FState", courseenrollment.getFstate());
            // 返回值
            object.put("info", courseenrollment_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 删除学生选课信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除部门信息")
    @ResponseBody
    @RequestMapping(value = "/delcourseenrollment", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delcourseenrollment(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLogincourseenrollmentID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogincourseenrollmentID != null && !CookiesLogincourseenrollmentID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TCourseEnrollment courseEnrollment = tCourseEnrollmentService.findById(Long.parseLong(id));
            TCourseOffering service = tCourseOfferingService.findById(courseEnrollment.getFcoid());
            if (service != null) {
                TCourseOffering courseOffering = new TCourseOffering();
                courseOffering.setFkeyid(service.getFkeyid());
                courseOffering.setFcurrentenrollment(service.getFcurrentenrollment() - 1 < 0 ? 0 : service.getFcurrentenrollment() - 1);
                courseOffering.setFuid(Long.parseLong(uid));
                courseOffering.setFudate(new Date());
                tCourseOfferingService.update(courseOffering);
            }
            tCourseEnrollmentService.deleteById(Long.parseLong(id));
            // 返回值
            object.put("result", "success");

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

//    /**
//     * 添加课程报名学生信息
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @LogOperation("添加学生报名课程信息")
//    @ResponseBody
//    @RequestMapping(value = "/addcoursestudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String addcoursestudent(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String studentname = jsonParam.getString("studentname");
////        String FCourse = jsonParam.getString("FCourse");//课程ID
//        String FCourse = jsonParam.getString("FCourseID");//课程ID
//        try {
//            String CookiesLoginusergroupsID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//            String uid = ""; // 当前登录用户ID
//            if (CookiesLoginusergroupsID != null && !CookiesLoginusergroupsID.equals("")) {
//                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
//            }
//            object.put("FCourse", FCourse);
//            FCourse = FCourse == null ? "0" : ParamTools.getdeParam(FCourse);
//            TCourseOffering tCourse = tCourseOfferingService.findById(Long.parseLong(FCourse));
//            String errorstr = "";
//            Integer rollmentnum =  tCourse.getFcurrentenrollment() == null ? 0 :tCourse.getFcurrentenrollment();
//            Integer xknum = rollmentnum + 1;
//            if (tCourse != null) {
//                if (studentname != null && !studentname.equals("")) {
//                    //增加新选的
//                    JSONArray powerRoles_Array = JSONArray.parseArray(studentname);
//                    //查询一下 开课最大人数
//                    if (tCourse.getFmaxcapacity() - rollmentnum - powerRoles_Array.size() > 0) {
//                        for (Object userid : powerRoles_Array) {
//                            Long studentid = Long.parseLong(ParamTools.getdeParam(userid.toString()));
//                            TCourseEnrollmentExample tCourseEnrollmentExample = new TCourseEnrollmentExample();
//                            TCourseEnrollmentExample.Criteria criteria = tCourseEnrollmentExample.createCriteria();
//                            criteria.andFcoidEqualTo(Long.parseLong(FCourse));
//                            criteria.andFstudentidEqualTo(studentid);
//                            List<TCourseEnrollment> enrollmentList = tCourseEnrollmentService.findByExample(tCourseEnrollmentExample);
//                            if (enrollmentList.size() == 0) {
//                                TCourseEnrollment enrollment = new TCourseEnrollment();
//                                enrollment.setFcoid(Long.parseLong(FCourse));
//                                enrollment.setFstudentid(studentid);
//                                enrollment.setFmode(2);
//                                enrollment.setFifpass(-1);
//                                enrollment.setFstatus(1);
//                                enrollment.setFenrolltime(new Date());
//                                enrollment.setFcid(Long.parseLong(uid));
//                                enrollment.setFcdate(new Date());
//                                tCourseEnrollmentService.save(enrollment);
//                                //选课人数+1
//                                TCourseOffering tCourseOffering = new TCourseOffering();
//                                tCourseOffering.setFcurrentenrollment(xknum);
//                                tCourseOffering.setFkeyid(tCourse.getFkeyid());
//                                tCourseOffering.setFudate(new Date());
//                                tCourseOffering.setFuid(Long.parseLong(uid));
//                                tCourseOfferingService.update(tCourseOffering);
//                                xknum ++ ;
//                            } else {
//                                TStudent tStudent = tStudentService.findById(studentid);
//                                errorstr += tStudent.getFname() + "、";
//                            }
//
//                        }
//                    } else {
//                        object.put("result", "error");
//                        object.put("error", "选择的学生已超出该课程最大人数，请重新选择！");
//                    }
//
//                }
//
//                if (!errorstr.equals("")) {
//                    errorstr = errorstr.substring(0, errorstr.length() - 1);
//                    object.put("errorstr", "学生【" + errorstr + "】已报名该课程或已拥有该课程能力！");
//                    object.put("iserror", 1);
//                } else {
//                    object.put("iserror", 0);
//                }
//                // 返回值
//                object.put("result", "success");
//            } else {
//                object.put("result", "error");
//                object.put("error", "未获取到课程信息，请刷新后重试！");
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
     * 添加课程报名学生信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加学生报名课程信息")
    @ResponseBody
    @RequestMapping(value = "/addcoursestudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addcoursestudent(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String studentname = jsonParam.getString("studentname");
//        String FCourse = jsonParam.getString("FCourse");//课程ID
        String training_programID = jsonParam.getString("FCourseID");//培养方案ID
        try {
            String CookiesLoginusergroupsID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginusergroupsID != null && !CookiesLoginusergroupsID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
//            object.put("FCourse", FCourse);
            training_programID = training_programID == null ? "0" : ParamTools.getdeParam(training_programID);
            TTrainingProgramCourseExample tTrainingProgramCourseExample = new TTrainingProgramCourseExample();
            TTrainingProgramCourseExample.Criteria criteria2 = tTrainingProgramCourseExample.createCriteria();
            criteria2.andFtpidEqualTo(Long.parseLong(training_programID));
            List<TTrainingProgramCourse> programCourseList = tTrainingProgramCourseService.findByExample(tTrainingProgramCourseExample);
            String errorstr = "";

            if (studentname != null && !studentname.equals("") && programCourseList.size() > 0) {
                //增加新选的
                JSONArray powerRoles_Array = JSONArray.parseArray(studentname);
                for (Object userid : powerRoles_Array) {

                    Long studentid = Long.parseLong(ParamTools.getdeParam(userid.toString()));

                    for (TTrainingProgramCourse tTrainingProgramCourse : programCourseList) {
                        TCourseEnrollmentExample tCourseEnrollmentExample = new TCourseEnrollmentExample();
                        TCourseEnrollmentExample.Criteria criteria = tCourseEnrollmentExample.createCriteria();
                        criteria.andFcoidEqualTo(Long.parseLong(training_programID));
                        criteria.andFcourseidEqualTo(tTrainingProgramCourse.getFcourseid());
                        criteria.andFstudentidEqualTo(studentid);
                        List<TCourseEnrollment> enrollmentList = tCourseEnrollmentService.findByExample(tCourseEnrollmentExample);
                        if (enrollmentList.size() == 0) {
                            TCourseEnrollment enrollment = new TCourseEnrollment();
                            enrollment.setFcoid(Long.parseLong(training_programID));
                            enrollment.setFstudentid(studentid);
                            enrollment.setFcourseid(tTrainingProgramCourse.getFcourseid());
                            enrollment.setFmode(2);
                            enrollment.setFifpass(-1);
                            enrollment.setFstatus(1);
                            enrollment.setFenrolltime(new Date());
                            enrollment.setFcid(Long.parseLong(uid));
                            enrollment.setFcdate(new Date());
                            tCourseEnrollmentService.save(enrollment);
                        }
                    }
                }
            }
            object.put("iserror", 0);
            // 返回值
            object.put("result", "success");

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

//    /**
//     * 添加课程报名学生信息
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @LogOperation("添加学生报名课程信息")
//    @ResponseBody
//    @RequestMapping(value = "/addcoursestudentclass", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String addcoursestudentclass(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String studentname = jsonParam.getString("studentname");
////        String FCourse = jsonParam.getString("FCourse");//课程ID
//        String FCourse = jsonParam.getString("FCourseID");//课程ID
//        try {
//            String CookiesLoginusergroupsID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//            String uid = ""; // 当前登录用户ID
//            if (CookiesLoginusergroupsID != null && !CookiesLoginusergroupsID.equals("")) {
//                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
//            }
//            object.put("FCourse", FCourse);
//            FCourse = FCourse == null ? "0" : ParamTools.getdeParam(FCourse);
//            TCourseOffering tCourse = tCourseOfferingService.findById(Long.parseLong(FCourse));
//            String errorstr = "";
//            Integer rollmentnum =tCourse.getFcurrentenrollment() == null ? 0 :tCourse.getFcurrentenrollment();
//            Integer xknum = rollmentnum + 1;
//            if (tCourse != null) {
//                if (studentname != null && !studentname.equals("")) {
//                    //增加新选的
//                    JSONArray powerRoles_Array = JSONArray.parseArray(studentname);
//                    //查询一下 开课最大人数
//
//                    Integer yxmaxnum=0;
//                    for (Object userid : powerRoles_Array) {
//                        Long studentid = Long.parseLong(ParamTools.getdeParam(userid.toString()));//班级ID
//                        //根据班级查询学生人数
//                        TClassStudentExample tClassStudentExample = new TClassStudentExample();
//                        TClassStudentExample.Criteria criteria1 = tClassStudentExample.createCriteria();
//                        criteria1.andFclassidEqualTo(studentid);
//                        List<TClassStudent> tClassStudentList = tClassStudentService.findByExample(tClassStudentExample);
//                        yxmaxnum =yxmaxnum + tClassStudentList.size();
//                    }
//                    if (tCourse.getFmaxcapacity() - rollmentnum - yxmaxnum > 0) {
//                        for (Object userid : powerRoles_Array) {
//                            Long studentid = Long.parseLong(ParamTools.getdeParam(userid.toString()));//班级ID
//                            TClassStudentExample tClassStudentExample = new TClassStudentExample();
//                            TClassStudentExample.Criteria criteria1 = tClassStudentExample.createCriteria();
//                            criteria1.andFclassidEqualTo(studentid);
//                            List<TClassStudent> tClassStudentList = tClassStudentService.findByExample(tClassStudentExample);
//                            if(tClassStudentList.size() > 0){
//                                for (TClassStudent tClassStudent : tClassStudentList) {
//                                    TCourseEnrollmentExample tCourseEnrollmentExample = new TCourseEnrollmentExample();
//                                    TCourseEnrollmentExample.Criteria criteria = tCourseEnrollmentExample.createCriteria();
//                                    criteria.andFcoidEqualTo(Long.parseLong(FCourse));
//                                    criteria.andFstudentidEqualTo(tClassStudent.getFstudentid());
//                                    List<TCourseEnrollment> enrollmentList = tCourseEnrollmentService.findByExample(tCourseEnrollmentExample);
//                                    if (enrollmentList.size() == 0) {
//                                        TCourseEnrollment enrollment = new TCourseEnrollment();
//                                        enrollment.setFcoid(Long.parseLong(FCourse));
//                                        enrollment.setFstudentid(tClassStudent.getFstudentid());
//                                        enrollment.setFmode(2);
//                                        enrollment.setFifpass(-1);
//                                        enrollment.setFstatus(1);
//                                        enrollment.setFenrolltime(new Date());
//                                        enrollment.setFcid(Long.parseLong(uid));
//                                        enrollment.setFcdate(new Date());
//                                        tCourseEnrollmentService.save(enrollment);
//                                        //选课人数+1
//                                        TCourseOffering tCourseOffering = new TCourseOffering();
//                                        tCourseOffering.setFcurrentenrollment(xknum);
//                                        tCourseOffering.setFkeyid(tCourse.getFkeyid());
//                                        tCourseOffering.setFudate(new Date());
//                                        tCourseOffering.setFuid(Long.parseLong(uid));
//                                        tCourseOfferingService.update(tCourseOffering);
//                                        xknum ++ ;
//                                    } else {
//                                        TStudent tStudent = tStudentService.findById(tClassStudent.getFstudentid());
//                                        errorstr += tStudent.getFname() + "、";
//                                    }
//                                }
//
//                            }
//                        }
//                    } else {
//                        object.put("result", "error");
//                        object.put("error", "选择的学生已超出该课程最大人数，请重新选择！");
//                    }
//                }
//                if (!errorstr.equals("")) {
//                    errorstr = errorstr.substring(0, errorstr.length() - 1);
//                    object.put("errorstr", "学生【" + errorstr + "】已报名该课程或已拥有该课程能力！");
//                    object.put("iserror", 1);
//                } else {
//                    object.put("iserror", 0);
//                }
//                // 返回值
//                object.put("result", "success");
//            } else {
//                object.put("result", "error");
//                object.put("error", "未获取到课程信息，请刷新后重试！");
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
     * 添加课程报名学生信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加学生报名课程信息")
    @ResponseBody
    @RequestMapping(value = "/addcoursestudentclass", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addcoursestudentclass(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String studentname = jsonParam.getString("studentname");
//        String FCourse = jsonParam.getString("FCourse");//课程ID
        String training_programID = jsonParam.getString("FCourseID");//培养方案ID
        try {
            String CookiesLoginusergroupsID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginusergroupsID != null && !CookiesLoginusergroupsID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
//            object.put("FCourse", FCourse);
            training_programID = training_programID == null ? "0" : ParamTools.getdeParam(training_programID);
            TTrainingProgramCourseExample tTrainingProgramCourseExample = new TTrainingProgramCourseExample();
            TTrainingProgramCourseExample.Criteria criteria2 = tTrainingProgramCourseExample.createCriteria();
            criteria2.andFtpidEqualTo(Long.parseLong(training_programID));
            List<TTrainingProgramCourse> programCourseList = tTrainingProgramCourseService.findByExample(tTrainingProgramCourseExample);
            if (studentname != null && !studentname.equals("") && programCourseList.size() > 0) {
                String errorstr = "";
                JSONArray powerRoles_Array = JSONArray.parseArray(studentname);
                for (Object userid : powerRoles_Array) {
                    Long studentid = Long.parseLong(ParamTools.getdeParam(userid.toString()));//班级ID
                    TClassStudentExample tClassStudentExample = new TClassStudentExample();
                    TClassStudentExample.Criteria criteria1 = tClassStudentExample.createCriteria();
                    criteria1.andFclassidEqualTo(studentid);
                    List<TClassStudent> tClassStudentList = tClassStudentService.findByExample(tClassStudentExample);
                    if (tClassStudentList.size() > 0) {
                        for (TClassStudent tClassStudent : tClassStudentList) {
                            for (TTrainingProgramCourse tTrainingProgramCourse : programCourseList) {
                                TCourseEnrollmentExample tCourseEnrollmentExample = new TCourseEnrollmentExample();
                                TCourseEnrollmentExample.Criteria criteria = tCourseEnrollmentExample.createCriteria();
                                criteria.andFcoidEqualTo(Long.parseLong(training_programID));
                                criteria.andFcourseidEqualTo(tTrainingProgramCourse.getFcourseid());
                                criteria.andFstudentidEqualTo(tClassStudent.getFstudentid());
                                List<TCourseEnrollment> enrollmentList = tCourseEnrollmentService.findByExample(tCourseEnrollmentExample);
                                if (enrollmentList.size() == 0) {
                                    TCourseEnrollment enrollment = new TCourseEnrollment();
                                    enrollment.setFcoid(Long.parseLong(training_programID));
                                    enrollment.setFstudentid(tClassStudent.getFstudentid());
                                    enrollment.setFcourseid(tTrainingProgramCourse.getFcourseid());
                                    enrollment.setFmode(2);
                                    enrollment.setFifpass(-1);
                                    enrollment.setFstatus(1);
                                    enrollment.setFenrolltime(new Date());
                                    enrollment.setFcid(Long.parseLong(uid));
                                    enrollment.setFcdate(new Date());
                                    tCourseEnrollmentService.save(enrollment);
                                } else {
                                    TStudent tStudent = tStudentService.findById(tClassStudent.getFstudentid());
                                    errorstr += tStudent.getFname() + "、";
                                }
                            }
                        }
                    }
                }
//                if (!errorstr.equals("")) {
//                    errorstr = errorstr.substring(0, errorstr.length() - 1);
//                    object.put("errorstr", "学生【" + errorstr + "】已报名该课程或已拥有该课程能力！");
//                    object.put("iserror", 1);
//                } else {
//                    object.put("iserror", 0);
//                }
                object.put("iserror", 0);
                // 返回值
                object.put("result", "success");
            } else {
                object.put("result", "error");
                object.put("error", "未获取到培养方案信息或班级信息，刷新后重试！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 成绩采集Excel模版下载
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/courseenrollmentscoremoban", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String courseenrollmentscoremoban(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String ip = jsonParam.getString("ip");
        String filename = jsonParam.getString("filename");
        String paramString = jsonParam.getString("filenamestr");

        String coursename = jsonParam.getString("coursename");
        String courseno = jsonParam.getString("courseno");
        String studentname = jsonParam.getString("studentname");
        String studentno = jsonParam.getString("studentno");
        String ftel = jsonParam.getString("ftel");
        String FLTypeID = jsonParam.getString("FLTypeID");
        String FNatureID = jsonParam.getString("FNatureID");
        int FSex = jsonParam.getInteger("FSex");
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

            sheet.setColumnWidth(8, 0);
            sheet.setColumnWidth(9, 0);
            sheet.setColumnWidth(10, 0);
            // 从第二行开始插入数据
            int startRow = 1; // 第二行
            int col1 = 0; //

            courseEnrollmentCS courseEnrollmentCS = new courseEnrollmentCS();
            courseEnrollmentCS.setFCourseName(coursename);
            courseEnrollmentCS.setFStudentNo(studentno);
            courseEnrollmentCS.setFTel(ftel);
            courseEnrollmentCS.setFStudentName(studentname);
            courseEnrollmentCS.setFCourseNo(courseno);
            if (FLTypeID != null && !FLTypeID.equals("") && !FLTypeID.equals("1")) {
                FLTypeID = FLTypeID == null ? "0" : ParamTools.getdeParam(FLTypeID);
                courseEnrollmentCS.setFLTypeID(Long.parseLong(FLTypeID));

            }
            if (FNatureID != null && !FNatureID.equals("")) {
                FNatureID = FNatureID == null ? "0" : ParamTools.getdeParam(FNatureID);
                if (!FNatureID.equals("-1"))

                    courseEnrollmentCS.setFNatureID(Long.parseLong(FNatureID));
            }


            courseEnrollmentCS.setFSex(FSex);

            List<courseEnrollmentData> enrollmentDataList = tCourseEnrollmentService.getCourseEnrollmentDataListmb(courseEnrollmentCS);
            if (enrollmentDataList.size() > 0) {
                int i = 0;
                String sexname = "";
                for (courseEnrollmentData courseEnrollmentData : enrollmentDataList) {
                    sexname = "";
                    Row row = sheet.getRow(startRow + i);
                    if (row == null) {
                        row = sheet.createRow(startRow + i);
                    }
                    Cell cell1 = row.getCell(0);
                    if (cell1 == null) {
                        cell1 = row.createCell(0);
                    }
                    cell1.setCellValue(courseEnrollmentData.getFStudentNo());
                    cell1.setCellStyle(cellStyle); // 应用样式

                    Cell cell2 = row.getCell(1);
                    if (cell2 == null) {
                        cell2 = row.createCell(1);
                    }
                    cell2.setCellValue(courseEnrollmentData.getFStudentName());
                    cell2.setCellStyle(cellStyle); // 应用样式

                    Cell cell3 = row.getCell(2);
                    if (cell3 == null) {
                        cell3 = row.createCell(2);
                    }
                    switch (courseEnrollmentData.getFStudentSex()) {
                        case 0:
                            sexname = "男";
                            break;
                        case 1:
                            sexname = "女";
                            break;
                    }
                    cell3.setCellValue(sexname);
                    cell3.setCellStyle(cellStyle); // 应用样式

                    Cell cell4 = row.getCell(3);
                    if (cell4 == null) {
                        cell4 = row.createCell(3);
                    }
                    cell4.setCellValue(courseEnrollmentData.getFCourseNo());
                    cell4.setCellStyle(cellStyle); // 应用样式

                    Cell cell5 = row.getCell(4);
                    if (cell5 == null) {
                        cell5 = row.createCell(4);
                    }
                    cell5.setCellValue(courseEnrollmentData.getFCourseName());
                    cell5.setCellStyle(cellStyle); // 应用样式

                    Cell cell6 = row.getCell(5);
                    if (cell6 == null) {
                        cell6 = row.createCell(5);
                    }
                    cell6.setCellValue(courseEnrollmentData.getFCourseYWName() == null ? "" : courseEnrollmentData.getFCourseYWName());
                    cell6.setCellStyle(cellStyle); // 应用样式

                    Cell cell7 = row.getCell(8);
                    if (cell7 == null) {
                        cell7 = row.createCell(8);
                    }
                    cell7.setCellValue(ParamTools.getEnParam(courseEnrollmentData.getFCOID().toString()));
                    cell7.setCellStyle(cellStyle); // 应用样式

                    Cell cell8 = row.getCell(9);
                    if (cell8 == null) {
                        cell8 = row.createCell(9);
                    }
                    cell8.setCellValue(ParamTools.getEnParam(courseEnrollmentData.getFCourseID().toString()));
                    cell8.setCellStyle(cellStyle); // 应用样式

                    Cell cell9 = row.getCell(10);
                    if (cell9 == null) {
                        cell9 = row.createCell(10);
                    }
                    cell9.setCellValue(ParamTools.getEnParam(courseEnrollmentData.getFStudentID().toString()));
                    cell9.setCellStyle(cellStyle); // 应用样式
                    i++;
                }
            }

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
    @RequestMapping(value = "/enrollmentupmoban", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String enrollmentupmoban(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
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

                        String FSorce = "";
                        String FISHG = "";
                        String FStudentNo = "";
                        String FStudentName = "";
                        String FCourseNo = "";
                        String FCOD = "";
                        String FCourseID = "";
                        String FStudentID = "";

                        // 4.获取数据
                        // getLastRowNum() 获取一张sheet表中行的数量
                        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                            // 获取第i行的数据
                            XSSFRow row = sheet.getRow(i);

                            FSorce = "";
                            FISHG = "";
                            FStudentNo = "";
                            FCourseNo = "";
                            FCOD = "";
                            FStudentName = "";
                            FCourseID = "";
                            FStudentID = "";
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
                                if (i == 0 && j == 0 && !cell.equals("学生学号")) {
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
                                            //学生学号
                                            FStudentNo = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 1:
                                            //学生姓名
                                            FStudentName = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;

                                        case 3:
                                            //课程编号
                                            FCourseNo = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 8:
                                            //培养方案ID
                                            FCOD = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;

                                        case 9:
                                            //课程ID
                                            FCourseID = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;

                                        case 10:
                                            //学生ID
                                            FStudentID = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;

                                        case 6:
                                            //成绩
                                            FSorce = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;

                                        case 7:
                                            //是否合格
                                            FISHG = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;

                                    }
                                }
                                // 5.打印单元格数据
//                            System.out.print(cell + " | ");
                            }

                            JSONObject student_object = new JSONObject();
                            String error = "";
                            if (i > 0) {
                                Long fcoid = 0l;
                                Long fcourseid = 0l;
                                Long fstudentid = 0l;
                                Integer fistongg = -1;
                                if (FCOD.equals("") && FStudentNo.equals("") && FCourseNo.equals("") && FISHG.equals("")) {
                                    break;
                                }
                                if (FStudentNo.equals(""))
                                    error += "学生学号获取失败" + ";";
                                if (FCourseNo.equals(""))
                                    error += "课程编号获取失败" + ";";
                                if (FCOD.equals(""))
                                    error += "获取培养方案信息失败" + ";";
                                else
                                    fcoid = Long.parseLong(ParamTools.getdeParam(FCOD));

                                if (FCourseID.equals(""))
                                    error += "获取课程信息失败" + ";";
                                else
                                    fcourseid = Long.parseLong(ParamTools.getdeParam(FCourseID));

                                if (FStudentID.equals(""))
                                    error += "获取学生信息失败" + ";";
                                else
                                    fstudentid = Long.parseLong(ParamTools.getdeParam(FStudentID));


                                if (!FSorce.equals("")) {
                                    if (isNumeric(FSorce) == false) {
                                        error += "成绩填写错误" + ";";
                                    }
                                }

                                if (FISHG.equals("")) {
                                    error += "是否通过未填写" + ";";
                                } else {
                                    if (FISHG.contains("是") || FISHG.contains("否")) {
                                        if (FISHG.equals("是")) {
                                            fistongg = 1;
                                        } else {
                                            fistongg = 2;
                                        }

                                    } else {
                                        error += "是否通过填写错误" + ";";
                                    }
                                }
                                if (!error.equals("")) {
                                    student_object.put("FName", FStudentName == null ? "" : FStudentName);
                                    student_object.put("error", error);
                                    student_Array.add(student_object);
                                    continue;
                                } else {

                                    TCourseEnrollmentExample tCourseEnrollmentExample = new TCourseEnrollmentExample();
                                    TCourseEnrollmentExample.Criteria criteria = tCourseEnrollmentExample.createCriteria();
                                    criteria.andFcourseidEqualTo(fcourseid);
                                    criteria.andFcoidEqualTo(fcoid);
                                    criteria.andFstudentidEqualTo(fstudentid);
                                    List<TCourseEnrollment> courseEnrollmentList = tCourseEnrollmentService.findByExample(tCourseEnrollmentExample);
                                    if (courseEnrollmentList.size() > 0) {
                                        TCourseEnrollment tCourseEnrollment = new TCourseEnrollment();
                                        tCourseEnrollment.setFudate(new Date());
                                        tCourseEnrollment.setFuid(Long.parseLong(uid));
                                        tCourseEnrollment.setFscore(Float.valueOf(FSorce));
                                        tCourseEnrollment.setFifpass(fistongg);
                                        tCourseEnrollment.setFcjtype(1);
                                        tCourseEnrollmentService.updateByExample(tCourseEnrollment, tCourseEnrollmentExample);

                                        if (fistongg == 1) {

                                            //根据课程查询 课程绑定的条件
                                            TCourseAbilityConditionExample tCourseAbilityConditionExample = new TCourseAbilityConditionExample();
                                            TCourseAbilityConditionExample.Criteria criteria1 = tCourseAbilityConditionExample.createCriteria();
                                            criteria1.andFcourseidEqualTo(fcourseid);
                                            List<TCourseAbilityCondition> conditionList = tcourseabilityconditionService.findByExample(tCourseAbilityConditionExample);
                                            if (conditionList.size() > 0) {
                                                for (TCourseAbilityCondition condition : conditionList) {
                                                    TStudentAbilityExample studentabilityExample = new TStudentAbilityExample();
                                                    TStudentAbilityExample.Criteria criteria2 = studentabilityExample.createCriteria();
                                                    criteria2.andFstudentidEqualTo(fstudentid);
                                                    criteria2.andFconditionidEqualTo(condition.getFconditionid());
                                                    List<TStudentAbility> studentAbilityList = tStudentAbilityService.findByExample(studentabilityExample);
                                                    if (studentAbilityList.size() == 0) {
                                                        //往认定表里添加数据
                                                        TStudentAbility tstudentability = new TStudentAbility();
                                                        tstudentability.setFstudentid(fstudentid);
                                                        tstudentability.setFstate(1);
                                                        tstudentability.setFmode(1);
                                                        tstudentability.setFconditionid(condition.getFconditionid());
                                                        tstudentability.setFabilityinf("");
                                                        tstudentability.setFurl("");
                                                        tstudentability.setFdate(new Date());
                                                        tstudentability.setFcdate(new Date());
                                                        tstudentability.setFcid(Long.parseLong(uid));
                                                        tStudentAbilityService.save(tstudentability);
                                                    }
                                                }
                                                //查询当前学生 所拥有的能力 然后算分
                                                int studentFPoints = tStudentAbilityService.getStudentFPoints(fstudentid);
                                                //修改学生表 能力得分
                                                TStudent student = new TStudent();
                                                student.setFkeyid(fstudentid);
                                                student.setFpoints(studentFPoints);
                                                tStudentService.update(student);
                                            }
                                        }
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
     * 获取数据接口模版信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatainterfaceSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatainterfaceSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray courseoffering_Array = new JSONArray();

            TInterfaceExample tInterfaceExample = new TInterfaceExample();
            TInterfaceExample.Criteria criteria = tInterfaceExample.createCriteria();
            if (search != null && !search.equals(""))
                criteria.andFnameLike("%" + search + "%");
            criteria.andFstateEqualTo(1);
            tInterfaceExample.setOrderByClause("FCDATE desc,FName asc");
            List<TInterface> tInterfaceList = tInterfaceService.findByExample(tInterfaceExample);
            for (TInterface tInterface : tInterfaceList) {
                JSONObject tInterface_object = new JSONObject();
                tInterface_object.put("id", ParamTools.getEnParam(tInterface.getFkeyid().toString()));
                tInterface_object.put("text", tInterface.getFname());
                courseoffering_Array.add(tInterface_object);
            }
            // 返回值
            object.put("list", courseoffering_Array);
            object.put("results", courseoffering_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 添加课程报名学生信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("在线评价")
    @ResponseBody
    @RequestMapping(value = "/addcourseEnrollmentzaixian", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addcourseEnrollmentzaixian(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FCEID = jsonParam.getString("FCEID");
        String FRequire = jsonParam.getString("FRequire");
        String FReturnParameters = jsonParam.getString("FReturnParameters");
        String FInputParameters = jsonParam.getString("FInputParameters");
        String FPJContent = jsonParam.getString("FPJContent");
        String FMaturity = jsonParam.getString("FMaturity");
        String FName = jsonParam.getString("FName");
        String interfaces = jsonParam.getString("interface");
        try {
            String CookiesLoginusergroupsID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginusergroupsID != null && !CookiesLoginusergroupsID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            FCEID = FCEID == null ? "0" : ParamTools.getdeParam(FCEID);
            interfaces = interfaces == null ? "0" : ParamTools.getdeParam(interfaces);

            TCourseEnrollment tCourseEnrollment = tCourseEnrollmentService.findById(Long.parseLong(FCEID));
            if (tCourseEnrollment != null) {
                Long fcourseid = tCourseEnrollment.getFcourseid();
                Long fstudentid = tCourseEnrollment.getFstudentid();

                TStudent tStudent = tStudentService.findById(fstudentid);

                ///////////AI接口调用-发送大模型查询，并本地存储/////////////////

                JSONArray data2 = new JSONArray();
                JSONObject data2obj = new JSONObject();

                //学生信息
                JSONObject studetnobj = new JSONObject();
                studetnobj.put("id", ParamTools.getEnParam(tStudent.getFkeyid().toString()));
                studetnobj.put("no", tStudent.getFno());
                studetnobj.put("name", tStudent.getFname());
                data2obj.put("student", studetnobj);
                //评语
                JSONObject teacherobj = new JSONObject();
                teacherobj.put("text", FPJContent);
                data2obj.put("comments", teacherobj);

                //报名ID
                JSONObject peiyangobj = new JSONObject();
                peiyangobj.put("id", ParamTools.getEnParam(tCourseEnrollment.getFkeyid().toString()));
                data2obj.put("CourseEnrollment", peiyangobj);

                data2.add(data2obj);
                //获取前台的返回格式
                JSONObject FreturnP = JSONObject.parseObject(FReturnParameters);
                //添加培养方案ID信息
                FreturnP.put("CourseEnrollment_id", "课程报名ID");

                String question = "数据1：" + FInputParameters + "，数据2：" + data2 + "，" + FRequire + "返回格式如下：" + FreturnP+"，不要分析过程，按格式返回学生课程成绩总分！";
//                System.out.println(question);

                String answer_parameters = "[{\"name\": \"ai_result\", \"description\": \"" + FRequire + "\"}]";

                //调用AI接口
                String string = AI_interface.sendPost_json(question, FMaturity, answer_parameters);
                JSONObject jsonObject1 = JSONObject.parseObject(string);
                String code = jsonObject1.getString("code");
                String message = jsonObject1.getString("message");
                String ai_id = jsonObject1.getString("key_id");//大模型返回的AI查询id
                if (code.equals("200")) {
                    //添加数据-ai存储表
                    SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
                    long key = idWorker.nextId();
                    // 新建

                    TInterface tInterface = tInterfaceService.findById(Long.parseLong(interfaces));
                    TCourseInterfaceExample tCourseInterfaceExample = new TCourseInterfaceExample();
                    TCourseInterfaceExample.Criteria criteria = tCourseInterfaceExample.createCriteria();
                    criteria.andFifidEqualTo(tInterface.getFkeyid());
                    criteria.andFceidEqualTo(Long.parseLong(FCEID));
                    List<TCourseInterface> interfaceList = tCourseInterfaceService.findByExample(tCourseInterfaceExample);
                    TCourseInterface tCourseInterface = new TCourseInterface();
                    if (interfaceList.size() == 0) {
                        tCourseInterface.setFkeyid(key);
                        tCourseInterface.setFcdate(new Date());
                        tCourseInterface.setFcid(Long.parseLong(uid));
                        tCourseInterface.setFceid(Long.parseLong(FCEID));
                        tCourseInterface.setFinterfacellmid(ai_id);
                        tCourseInterface.setFcourseid(fcourseid);
                        tCourseInterface.setFstudentid(fstudentid);
                        tCourseInterface.setFname(FName);
                        tCourseInterface.setFmaturity(Float.valueOf(FMaturity));
                        tCourseInterface.setFinputparameters(FInputParameters);
                        tCourseInterface.setFreturnparameters(FReturnParameters);
                        tCourseInterface.setFrequire(FRequire);
                        tCourseInterface.setFifid(tInterface.getFkeyid());
                        tCourseInterface.setFtypeid(tInterface.getFtypeid());
                        tCourseInterface.setFpjcontent(FPJContent);
                        tCourseInterface.setFinterfacellmstate(1);
                        tCourseInterface.setFljparameters(question);
                        tCourseInterfaceService.save(tCourseInterface);

                    } else {
                        tCourseInterface = new TCourseInterface();
                        tCourseInterface.setFkeyid(interfaceList.get(0).getFkeyid());
                        tCourseInterface.setFinterfacellmid(ai_id);
                        tCourseInterface.setFmaturity(Float.valueOf(FMaturity));
                        tCourseInterface.setFinputparameters(FInputParameters);
                        tCourseInterface.setFreturnparameters(FReturnParameters);
                        tCourseInterface.setFrequire(FRequire);
                        tCourseInterface.setFifid(tInterface.getFkeyid());
                        tCourseInterface.setFtypeid(tInterface.getFtypeid());
                        tCourseInterface.setFpjcontent(FPJContent);
                        tCourseInterface.setFudate(new Date());
                        tCourseInterface.setFuid(Long.parseLong(uid));
                        tCourseInterface.setFljparameters(question);
                        tCourseInterfaceService.update(tCourseInterface);
                    }
                    // 返回值
                    object.put("result", "success");
                    object.put("FenrollmentscourseID", ParamTools.getEnParam(tCourseInterface.getFkeyid().toString()));
                } else {
                    // 返回值
                    object.put("result", "error");
                    object.put("ai_error", message);
                }
            } else {
                object.put("result", "error");
                object.put("error", "未获取到学生课程信息！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取在线评价结果
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */

    @ResponseBody
    @RequestMapping(value = "/selectzaixianjg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String selectzaixianjg(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");

        try {
            String CookiesLoginusergroupsID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginusergroupsID != null && !CookiesLoginusergroupsID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : ParamTools.getdeParam(id);//课程接口表ID

            TCourseInterface courseInterface = tCourseInterfaceService.findById(Long.parseLong(id));
            if (courseInterface != null) {
                String result = AI_interface.sendPost_json_search(courseInterface.getFinterfacellmid());
                JSONObject jsonObject1 = JSONObject.parseObject(result);
                String code = jsonObject1.getString("code");
                if (code.equals("200")) {
                    String info = jsonObject1.getString("info");
                    JSONObject jsonObject1_info = JSONObject.parseObject(info);
                    int state = jsonObject1_info.getInteger("state");
                    if (state == 2) {
                        String answer = jsonObject1_info.getString("answer");
                        JSONObject jsonObject1_answer = JSONObject.parseObject(answer);
                        String ai_result = jsonObject1_answer.getString("ai_result");
                        int courseEnrollmentFScore = UpdateCourseEnrollmentFScore(ai_result, uid);
                        if(courseEnrollmentFScore ==2){
                            object.put("result", "success");
                        }else{
                            object.put("result", "error");
                            object.put("error", "所写评语内容与评价模版不匹配，请重新输入！");
                        }
                        object.put("result", "success");
                    } else if (state < 2) {
                        //循环获取
                        try {
                            // 暂停5秒（5000毫秒）
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            // 处理中断异常
                            e.printStackTrace();
                        }
                        String zaixianJG = getZaixianJG(courseInterface.getFinterfacellmid());

                        if (zaixianJG != null && !zaixianJG.equals("")) {
                            JSONObject jsonObject1_zaixianJG = JSONObject.parseObject(zaixianJG);
                            String answer = jsonObject1_zaixianJG.getString("answer");
                            JSONObject jsonObject1_answer = JSONObject.parseObject(answer);
                            String ai_result = jsonObject1_answer.getString("ai_result");
                            int courseEnrollmentFScore = UpdateCourseEnrollmentFScore(ai_result, uid);
                            if(courseEnrollmentFScore ==2){
                                object.put("result", "success");
                            }else{
                                object.put("result", "error");
                                object.put("error", "所写评语内容与评价模版不匹配，请重新输入！");
                            }

                        } else {
                            object.put("result", "error");
                            object.put("error", "001评价过程中报错了，请检查参数重新操作！");
                        }
                    } else if (state == 3 || state == 9) {
                        object.put("result", "error");
                        object.put("error", "003评价过程中报错了，请检查参数重新操作！");
                    } else if (state == 9) {
                        object.put("result", "error");
                        object.put("error", "009评价过程中超时了，请检查网络重新操作！");
                    }
                }
            } else {
                object.put("result", "error");
                object.put("error", "评价结果获取失败，请重新操作！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
    //循环查询ai接口 结果
    private String getZaixianJG(String fmid) {
        String info2 = "";
        try {
            String result = AI_interface.sendPost_json_search(fmid);
            JSONObject jsonObject1 = JSONObject.parseObject(result);
            String code = jsonObject1.getString("code");
            if (code.equals("200")) {
                String info = jsonObject1.getString("info");
                JSONObject jsonObject1_info = JSONObject.parseObject(info);
                int state = jsonObject1_info.getInteger("state");
                if (state == 2) {
                    info2 = info;
                } else if (state < 2) {
                    //循环获取
                    try {
                        // 暂停5秒（5000毫秒）
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        // 处理中断异常
                        e.printStackTrace();
                    }
                    info2 = getZaixianJG(fmid);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return info2;
    }


    private int UpdateCourseEnrollmentFScore(String ai_result,String uid){
        int zhengque=2;
        try {
            JSONObject parsedObject = JSONObject.parseObject(ai_result);
            String courseEnrollmentId = parsedObject.getString("CourseEnrollment_id");//课程报名ID

            if(courseEnrollmentId!=null && !courseEnrollmentId.equals("")){
                Float finalScore = parsedObject.getFloat("final_score");

                int tg = 2;
                if (finalScore >= 60) {
                    tg = 1;
                } else {
                    tg = 2;
                }
                TCourseEnrollment tCourseEnrollment = new TCourseEnrollment();
                tCourseEnrollment.setFkeyid(Long.parseLong(ParamTools.getdeParam(courseEnrollmentId)));
                tCourseEnrollment.setFifpass(tg);
                tCourseEnrollment.setFscore(finalScore);
                tCourseEnrollment.setFuid(Long.parseLong(uid));
                tCourseEnrollment.setFudate(new Date());
                tCourseEnrollment.setFcjtype(3);
                tCourseEnrollmentService.update(tCourseEnrollment);

                if (tg == 1) {
                    TCourseEnrollment courseEnrollment = tCourseEnrollmentService.findById(Long.parseLong(ParamTools.getdeParam(courseEnrollmentId)));
                    //根据课程查询 课程绑定的条件
                    TCourseAbilityConditionExample tCourseAbilityConditionExample = new TCourseAbilityConditionExample();
                    TCourseAbilityConditionExample.Criteria criteria1 = tCourseAbilityConditionExample.createCriteria();
                    criteria1.andFcourseidEqualTo(courseEnrollment.getFcourseid());
                    List<TCourseAbilityCondition> conditionList = tcourseabilityconditionService.findByExample(tCourseAbilityConditionExample);
                    if (conditionList.size() > 0) {
                        for (TCourseAbilityCondition condition : conditionList) {
                            TStudentAbilityExample studentabilityExample = new TStudentAbilityExample();
                            TStudentAbilityExample.Criteria criteria2 = studentabilityExample.createCriteria();
                            criteria2.andFstudentidEqualTo(courseEnrollment.getFstudentid());
                            criteria2.andFconditionidEqualTo(condition.getFconditionid());
                            List<TStudentAbility> studentAbilityList = tStudentAbilityService.findByExample(studentabilityExample);
                            if (studentAbilityList.size() == 0) {
                                //往认定表里添加数据
                                TStudentAbility tstudentability = new TStudentAbility();
                                tstudentability.setFstudentid(courseEnrollment.getFstudentid());
                                tstudentability.setFstate(1);
                                tstudentability.setFmode(1);
                                tstudentability.setFconditionid(condition.getFconditionid());
                                tstudentability.setFabilityinf("");
                                tstudentability.setFurl("");
                                tstudentability.setFdate(new Date());
                                tstudentability.setFcdate(new Date());
                                tstudentability.setFcid(Long.parseLong(uid));
                                tStudentAbilityService.save(tstudentability);
                            }
                        }
                        //查询当前学生 所拥有的能力 然后算分
                        int studentFPoints = tStudentAbilityService.getStudentFPoints(courseEnrollment.getFstudentid());
                        //修改学生表 能力得分
                        TStudent student = new TStudent();
                        student.setFkeyid(courseEnrollment.getFstudentid());
                        student.setFpoints(studentFPoints);
                        tStudentService.update(student);
                    }
                }
            }else{
                zhengque=1;
            }
        } catch (Exception e) {
            zhengque=1;
        }
        return zhengque;
    }



    /**
     * 根据ID获取在线评价表信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryCourseInterfaceInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryCourseInterfaceInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            System.out.println(key);
            TCourseInterface tCourseInterface = tCourseInterfaceService.findById(key);
            JSONObject courseinterface_object = new JSONObject();
            courseinterface_object.put("key", ParamTools.getEnParam(tCourseInterface.getFkeyid().toString()));
            courseinterface_object.put("FIFID", ParamTools.getEnParam(tCourseInterface.getFifid().toString()));
            courseinterface_object.put("FCourseID", ParamTools.getEnParam(tCourseInterface.getFcourseid().toString()));
            courseinterface_object.put("FStudentID", ParamTools.getEnParam(tCourseInterface.getFstudentid().toString()));
            courseinterface_object.put("FTypeID", ParamTools.getEnParam(tCourseInterface.getFtypeid().toString()));
            courseinterface_object.put("FPJContent", tCourseInterface.getFpjcontent());
            courseinterface_object.put("FInterfacellm_Return", tCourseInterface.getFinterfacellmReturn());
            courseinterface_object.put("FInterfacellmState", tCourseInterface.getFinterfacellmstate());
            courseinterface_object.put("FInterfacellmID", tCourseInterface.getFinterfacellmid());
            courseinterface_object.put("FMaturity", tCourseInterface.getFmaturity());
            courseinterface_object.put("FRequire", tCourseInterface.getFrequire());
            courseinterface_object.put("FReturnParameters", tCourseInterface.getFreturnparameters());
            courseinterface_object.put("FInputParameters", tCourseInterface.getFinputparameters());
            courseinterface_object.put("FName", tCourseInterface.getFname());
            courseinterface_object.put("FCID", tCourseInterface.getFcid());
            courseinterface_object.put("FUID", tCourseInterface.getFuid());
            courseinterface_object.put("FCDATE", tCourseInterface.getFcdate());
            courseinterface_object.put("FUDATE", tCourseInterface.getFudate());
            courseinterface_object.put("FState", tCourseInterface.getFstate());

            
            // 返回值
            object.put("info", courseinterface_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取评语模版 课程学生人数
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryCourseEnrollmentStudentNum", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryCourseEnrollmentStudentNum(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FCOID = jsonParam.getString("FCOID");
        String FCourseID = jsonParam.getString("FCourseID");
        try {
            FCOID = FCOID == null? "0" : ParamTools.getdeParam(FCOID);
            FCourseID = FCourseID == null? "0" : ParamTools.getdeParam(FCourseID);
            courseEnrollmentCS courseEnrollmentCS = new courseEnrollmentCS();
            courseEnrollmentCS.setFCourseID(Long.parseLong(FCourseID));
            courseEnrollmentCS.setFCOID(Long.parseLong(FCOID));
            List<courseEnrollmentData> enrollmentDataList = tCourseEnrollmentService.getCourseEnrollmentDataListmb(courseEnrollmentCS);
            object.put("studentnum",enrollmentDataList.size());
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 在线评价 评语Excel模版下载
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/courseenrollmentscoremobapy", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String courseenrollmentscoremobapy(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String ip = jsonParam.getString("ip");
        String filename = jsonParam.getString("filename");
        String paramString = jsonParam.getString("filenamestr");

        String FCOID = jsonParam.getString("FCOID");
        String FCourseID = jsonParam.getString("FCourseID");

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

            sheet.setColumnWidth(8, 0);
            sheet.setColumnWidth(9, 0);
            sheet.setColumnWidth(10, 0);
            // 从第二行开始插入数据
            int startRow = 1; // 第二行
            int col1 = 0; //
            FCOID = FCOID == null? "0" : ParamTools.getdeParam(FCOID);
            FCourseID = FCourseID == null? "0" : ParamTools.getdeParam(FCourseID);
            courseEnrollmentCS courseEnrollmentCS = new courseEnrollmentCS();
            courseEnrollmentCS.setFCourseID(Long.parseLong(FCourseID));
            courseEnrollmentCS.setFCOID(Long.parseLong(FCOID));
            List<courseEnrollmentData> enrollmentDataList = tCourseEnrollmentService.getCourseEnrollmentDataListmb(courseEnrollmentCS);
            if (enrollmentDataList.size() > 0) {
                int i = 0;
                String sexname = "";
                for (courseEnrollmentData courseEnrollmentData : enrollmentDataList) {
                    sexname = "";
                    Row row = sheet.getRow(startRow + i);
                    if (row == null) {
                        row = sheet.createRow(startRow + i);
                    }
                    Cell cell1 = row.getCell(0);
                    if (cell1 == null) {
                        cell1 = row.createCell(0);
                    }
                    cell1.setCellValue(courseEnrollmentData.getFStudentNo());
                    cell1.setCellStyle(cellStyle); // 应用样式

                    Cell cell2 = row.getCell(1);
                    if (cell2 == null) {
                        cell2 = row.createCell(1);
                    }
                    cell2.setCellValue(courseEnrollmentData.getFStudentName());
                    cell2.setCellStyle(cellStyle); // 应用样式

                    Cell cell3 = row.getCell(2);
                    if (cell3 == null) {
                        cell3 = row.createCell(2);
                    }
                    switch (courseEnrollmentData.getFStudentSex()) {
                        case 0:
                            sexname = "男";
                            break;
                        case 1:
                            sexname = "女";
                            break;
                    }
                    cell3.setCellValue(sexname);
                    cell3.setCellStyle(cellStyle); // 应用样式

                    Cell cell4 = row.getCell(3);
                    if (cell4 == null) {
                        cell4 = row.createCell(3);
                    }
                    cell4.setCellValue(courseEnrollmentData.getFCourseNo());
                    cell4.setCellStyle(cellStyle); // 应用样式

                    Cell cell5 = row.getCell(4);
                    if (cell5 == null) {
                        cell5 = row.createCell(4);
                    }
                    cell5.setCellValue(courseEnrollmentData.getFCourseName());
                    cell5.setCellStyle(cellStyle); // 应用样式

                    Cell cell6 = row.getCell(5);
                    if (cell6 == null) {
                        cell6 = row.createCell(5);
                    }
                    cell6.setCellValue(courseEnrollmentData.getFCourseYWName() == null ? "" : courseEnrollmentData.getFCourseYWName());
                    cell6.setCellStyle(cellStyle); // 应用样式

                    Cell cell7 = row.getCell(7);
                    if (cell7 == null) {
                        cell7 = row.createCell(7);
                    }
                    cell7.setCellValue(ParamTools.getEnParam(courseEnrollmentData.getFCOID().toString()));
                    cell7.setCellStyle(cellStyle); // 应用样式

                    Cell cell8 = row.getCell(8);
                    if (cell8 == null) {
                        cell8 = row.createCell(8);
                    }
                    cell8.setCellValue(ParamTools.getEnParam(courseEnrollmentData.getFCourseID().toString()));
                    cell8.setCellStyle(cellStyle); // 应用样式

                    Cell cell9 = row.getCell(9);
                    if (cell9 == null) {
                        cell9 = row.createCell(9);
                    }
                    cell9.setCellValue(ParamTools.getEnParam(courseEnrollmentData.getFStudentID().toString()));
                    cell9.setCellStyle(cellStyle); // 应用样式
                    i++;
                }
            }

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
     * 获取课程报名中 未获得成绩的 课程信息(下拉列表)
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
            JSONArray courseEnrollmentCourse_Array = new JSONArray();
            courseEnrollmentCourseCS courseEnrollmentCourseCS = new courseEnrollmentCourseCS();
            courseEnrollmentCourseCS.setFOName(search);
            List<courseEnrollmentCourseData> getcoursedEnrollmentCourseSelect = tCourseEnrollmentService.getcourseEnrollmentCourseSelect(courseEnrollmentCourseCS);
            JSONObject courseEnrollmentCourse_object = null;
            for (courseEnrollmentCourseData courseEnrollmentCourseData : getcoursedEnrollmentCourseSelect) {
                courseEnrollmentCourse_object = new JSONObject();
                courseEnrollmentCourse_object.put("id", ParamTools.getEnParam(courseEnrollmentCourseData.getFCourseID()));
                courseEnrollmentCourse_object.put("text", courseEnrollmentCourseData.getFName());
                courseEnrollmentCourse_object.put("FCOID", ParamTools.getEnParam(courseEnrollmentCourseData.getFCOID()));
                courseEnrollmentCourse_Array.add(courseEnrollmentCourse_object);
            }



            // 返回值
            object.put("list", courseEnrollmentCourse_Array);
            object.put("results", courseEnrollmentCourse_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }



    /**
     * 模板导入_批量在线评价
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/enrollmentupmobanpl", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String enrollmentupmobanpl(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
            throws Exception {
        JSONObject object = new JSONObject();
        BufferedOutputStream stream = null;
        String CookiesLogindesignpurchaseapplyID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
        String uid = ""; // 当前登录用户ID
        if (CookiesLogindesignpurchaseapplyID != null && !CookiesLogindesignpurchaseapplyID.equals("")) {
            uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
        }
        String FJKID = request.getParameter("FJKID");
        String FReturnParameters = request.getParameter("FReturnParameters");
        String FInputParameters = request.getParameter("FInputParameters");
        String FRequire = request.getParameter("FRequire");
        String FMaturity = request.getParameter("FMaturity");
        String FName = request.getParameter("FName");
        List<Long> fceid = new ArrayList<>();
        List<Long> fcourseidlist = new ArrayList<>();
        List<Long> fstudentidlist = new ArrayList<>();
        List<String> fpystr = new ArrayList<>();
        for (MultipartFile file : uploadFiles) {
            try {
                // 获取文件名
                String filename = file.getOriginalFilename();
                if (filename == null || filename.equals("")) {
                    object.put("result", "error");
                    object.put("error", "请选择文件后再进行上传！");
                } else {
                    FJKID = FJKID == null? "0" : ParamTools.getdeParam(FJKID);//接口ID

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


                    JSONObject studetnxx_object = new JSONObject();//学生组信息

                    JSONArray studentxx_array = new JSONArray();//单条学生信息


                    if (suffixName.equals("xlsx")) {

                        String FPY = "";
                        String FISHG = "";
                        String FStudentNo = "";
                        String FStudentName = "";
                        String FCourseNo = "";
                        String FCOD = "";
                        String FCourseID = "";
                        String FStudentID = "";

                        // 4.获取数据
                        // getLastRowNum() 获取一张sheet表中行的数量
                        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                            // 获取第i行的数据
                            XSSFRow row = sheet.getRow(i);

                            FPY = "";
                            FISHG = "";
                            FStudentNo = "";
                            FCourseNo = "";
                            FCOD = "";
                            FStudentName = "";
                            FCourseID = "";
                            FStudentID = "";
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
                                if (i == 0 && j == 0 && !cell.equals("学生学号")) {
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
                                            //学生学号
                                            FStudentNo = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 1:
                                            //学生姓名
                                            FStudentName = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;

                                        case 3:
                                            //课程编号
                                            FCourseNo = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                        case 7:
                                            //培养方案ID
                                            FCOD = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;

                                        case 8:
                                            //课程ID
                                            FCourseID = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;

                                        case 9:
                                            //学生ID
                                            FStudentID = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;

                                        case 6:
                                            //评语
                                            FPY = row.getCell(j).getStringCellValue().toString().trim() == null ? "" : row.getCell(j).getStringCellValue().toString().trim();
                                            break;
                                    }
                                }
                                // 5.打印单元格数据
//                            System.out.print(cell + " | ");
                            }

                            JSONObject student_object = new JSONObject();
                            String error = "";
                            if (i > 0) {
                                Long fcoid = 0l;
                                Long fcourseid = 0l;
                                Long fstudentid = 0l;
                                Integer fistongg = -1;
                                if (FCOD.equals("") && FStudentNo.equals("") && FCourseNo.equals("") && FISHG.equals("") && FPY.equals("")) {
                                    break;
                                }
                                if (FStudentNo.equals(""))
                                    error += "学生学号获取失败" + ";";
                                if (FCourseNo.equals(""))
                                    error += "课程编号获取失败" + ";";
                                if (FCOD.equals(""))
                                    error += "获取培养方案信息失败" + ";";
                                else
                                    fcoid = Long.parseLong(ParamTools.getdeParam(FCOD));

                                if (FCourseID.equals(""))
                                    error += "获取课程信息失败" + ";";
                                else
                                    fcourseid = Long.parseLong(ParamTools.getdeParam(FCourseID));

                                if (FStudentID.equals(""))
                                    error += "获取学生信息失败" + ";";
                                else
                                    fstudentid = Long.parseLong(ParamTools.getdeParam(FStudentID));


                                if (FPY.equals("")) {
                                    error += "评语未填写" + ";";
                                }


                                if (!error.equals("")) {
                                    student_object.put("FName", FStudentName == null ? "" : FStudentName);
                                    student_object.put("error", error);
                                    student_Array.add(student_object);
                                    continue;
                                } else {
                                    TStudent tStudent = tStudentService.findById(fstudentid);

                                    ///////////AI接口调用-发送大模型查询，并本地存储/////////////////
                                    JSONObject data2obj = new JSONObject();
                                    //学生信息
                                    JSONObject studetnobj = new JSONObject();
                                    studetnobj.put("id", ParamTools.getEnParam(tStudent.getFkeyid().toString()));
                                    studetnobj.put("no", tStudent.getFno());
                                    studetnobj.put("name", tStudent.getFname());
                                    data2obj.put("student", studetnobj);
                                    //评语
                                    JSONObject teacherobj = new JSONObject();
                                    teacherobj.put("text", FPY);
                                    data2obj.put("comments", teacherobj);

                                    TCourseEnrollmentExample tCourseEnrollmentExample = new TCourseEnrollmentExample();
                                    tCourseEnrollmentExample.createCriteria().andFcourseidEqualTo(fcourseid).andFcoidEqualTo(Long.parseLong(ParamTools.getdeParam(FCOD))).andFstudentidEqualTo(fstudentid);
                                    List<TCourseEnrollment> courseEnrollmentList = tCourseEnrollmentService.findByExample(tCourseEnrollmentExample);
                                    TCourseEnrollment tCourseEnrollment = courseEnrollmentList.get(0);
                                    //报名ID
                                    JSONObject peiyangobj = new JSONObject();
                                    peiyangobj.put("id", ParamTools.getEnParam(tCourseEnrollment.getFkeyid().toString()));
                                    data2obj.put("CourseEnrollment", peiyangobj);

                                    studentxx_array.add(data2obj);
                                    studetnxx_object.put("studetlist",studentxx_array);
                                    fceid.add(Long.parseLong(ParamTools.getdeParam(FCOD)));
                                    fcourseidlist.add(fcourseid);
                                    fstudentidlist.add(fstudentid);
                                    fpystr.add(FPY);

                                }
                            }
                        }
                        //获取前台的返回格式
                        JSONObject FreturnP = JSONObject.parseObject(FReturnParameters);
                        //添加培养方案ID信息
                        FreturnP.put("CourseEnrollment_id", "课程报名ID");

                        String question = "数据1：" + FInputParameters + "，数据2：" + studetnxx_object + "，" + FRequire + "返回格式如下：" + FreturnP +"，不要分析过程，按格式返回多个学生课程成绩总分！";
                        System.out.println("question:" + question);
                        String answer_parameters = "[{\"name\": \"ai_result\", \"description\": \"" + FRequire + "\"}]";
                        //调用AI接口
                        String string = AI_interface.sendPost_json(question, FMaturity, answer_parameters);
                        JSONObject jsonObject1 = JSONObject.parseObject(string);
                        String code = jsonObject1.getString("code");
                        String message = jsonObject1.getString("message");
                        String ai_id = jsonObject1.getString("key_id");//大模型返回的AI查询id
                        if (code.equals("200")) {

                            if(fceid.size() > 0){
                                TInterface tInterface = tInterfaceService.findById(Long.parseLong(FJKID));
                                for (int i = 0; i < fceid.size(); i++) {
                                    long key = idWorker.nextId();
                                    TCourseEnrollmentExample tCourseEnrollmentExample = new TCourseEnrollmentExample();
                                    tCourseEnrollmentExample.createCriteria().andFcourseidEqualTo(fcourseidlist.get(i)).andFcoidEqualTo(fceid.get(i)).andFstudentidEqualTo(fstudentidlist.get(i));
                                    List<TCourseEnrollment> courseEnrollmentList = tCourseEnrollmentService.findByExample(tCourseEnrollmentExample);
                                    TCourseEnrollment tCourseEnrollment = courseEnrollmentList.get(0);
                                    TCourseInterfaceExample tCourseInterfaceExample = new TCourseInterfaceExample();
                                    tCourseInterfaceExample.createCriteria().andFceidEqualTo(tCourseEnrollment.getFkeyid()).andFifidEqualTo(Long.parseLong(FJKID));
                                    List<TCourseInterface> courseInterfaceList = tCourseInterfaceService.findByExample(tCourseInterfaceExample);
                                    TCourseInterface tCourseInterface = new TCourseInterface();
                                    if (courseInterfaceList.size() == 0) {
                                        tCourseInterface.setFkeyid(key);
                                        tCourseInterface.setFcdate(new Date());
                                        tCourseInterface.setFcid(Long.parseLong(uid));
                                        tCourseInterface.setFceid(fceid.get(i));
                                        tCourseInterface.setFinterfacellmid(ai_id);
                                        tCourseInterface.setFcourseid(tCourseEnrollment.getFcourseid());
                                        tCourseInterface.setFstudentid(tCourseEnrollment.getFstudentid());
                                        tCourseInterface.setFname(FName);
                                        tCourseInterface.setFmaturity(Float.valueOf(FMaturity));
                                        tCourseInterface.setFinputparameters(FInputParameters);
                                        tCourseInterface.setFreturnparameters(FReturnParameters);
                                        tCourseInterface.setFrequire(FRequire);
                                        tCourseInterface.setFifid(tInterface.getFkeyid());
                                        tCourseInterface.setFtypeid(tInterface.getFtypeid());
                                        tCourseInterface.setFpjcontent(fpystr.get(i));
                                        tCourseInterface.setFinterfacellmstate(1);
                                        tCourseInterface.setFljparameters(question);
                                        tCourseInterfaceService.save(tCourseInterface);
                                    } else {
                                        tCourseInterface = new TCourseInterface();
                                        tCourseInterface.setFkeyid(courseInterfaceList.get(0).getFkeyid());
                                        tCourseInterface.setFinterfacellmid(ai_id);
                                        tCourseInterface.setFmaturity(Float.valueOf(FMaturity));
                                        tCourseInterface.setFinputparameters(FInputParameters);
                                        tCourseInterface.setFreturnparameters(FReturnParameters);
                                        tCourseInterface.setFrequire(FRequire);
                                        tCourseInterface.setFifid(tInterface.getFkeyid());
                                        tCourseInterface.setFtypeid(tInterface.getFtypeid());
                                        tCourseInterface.setFpjcontent(fpystr.get(i));
                                        tCourseInterface.setFudate(new Date());
                                        tCourseInterface.setFuid(Long.parseLong(uid));
                                        tCourseInterface.setFljparameters(question);
                                        tCourseInterfaceService.update(tCourseInterface);
                                    }
                                }
                            }
                            System.out.println(string);
                            System.out.println(ai_id);
                            // 返回值
                            object.put("result", "success");
                            object.put("ai_id",ai_id);
                        } else {
                            // 返回值
                            object.put("result", "error");
                            object.put("ai_error", message);
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


    /**
     * 获取在线评价结果——批量
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */

    @ResponseBody
    @RequestMapping(value = "/selectzaixianjgpl", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String selectzaixianjgpl(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");//大数据模型ID

        try {
            String CookiesLoginusergroupsID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginusergroupsID != null && !CookiesLoginusergroupsID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }


            String result = AI_interface.sendPost_json_search(id);
            JSONObject jsonObject1 = JSONObject.parseObject(result);
            String code = jsonObject1.getString("code");
            if (code.equals("200")) {
                String info = jsonObject1.getString("info");
                JSONObject jsonObject1_info = JSONObject.parseObject(info);
                int state = jsonObject1_info.getInteger("state");
                if (state == 2) {
                    String answer = jsonObject1_info.getString("answer");
                    JSONObject jsonObject1_answer = JSONObject.parseObject(answer);
                    String ai_result = jsonObject1_answer.getString("ai_result");

                    Object parsed = JSON.parse(ai_result);
                    if (parsed instanceof JSONObject) {
                        JSONObject jsonObject = (JSONObject) parsed;
                        int courseEnrollmentFScore = UpdateCourseEnrollmentFScore(ai_result, uid);
                        if(courseEnrollmentFScore ==2){
                            object.put("result", "success");
                        }else{
                            object.put("result", "error");
                            object.put("error", "所写评语内容与评价模版不匹配，请重新输入！");
                        }
                    } else if (parsed instanceof JSONArray) {
                        JSONArray jsonArray = (JSONArray) parsed;
                        int courseEnrollmentFScore = UpdateCourseEnrollmentFScore2(ai_result, uid);
                        if(courseEnrollmentFScore ==2){
                            object.put("result", "success");
                        }else{
                            object.put("result", "error");
                            object.put("error", "所写评语内容与评价模版不匹配，请重新输入！");
                        }
                    } else {
                        object.put("result", "error");
                        object.put("error", "获取的结果无效，请重新输入！");
                    }

                } else if (state < 2) {
                    //循环获取
                    try {
                        // 暂停5秒（5000毫秒）
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        // 处理中断异常
                        e.printStackTrace();
                    }
                    String zaixianJG = getZaixianJG(id);

                    if (zaixianJG != null && !zaixianJG.equals("")) {
                        JSONObject jsonObject1_zaixianJG = JSONObject.parseObject(zaixianJG);
                        String answer = jsonObject1_zaixianJG.getString("answer");
                        JSONObject jsonObject1_answer = JSONObject.parseObject(answer);
                        String ai_result = jsonObject1_answer.getString("ai_result");

                        Object parsed = JSON.parse(ai_result);
                        if (parsed instanceof JSONObject) {
                            JSONObject jsonObject = (JSONObject) parsed;
                            int courseEnrollmentFScore = UpdateCourseEnrollmentFScore(ai_result, uid);
                            if(courseEnrollmentFScore ==2){
                                object.put("result", "success");
                            }else{
                                object.put("result", "error");
                                object.put("error", "所写评语内容与评价模版不匹配，请重新输入！");
                            }
                        } else if (parsed instanceof JSONArray) {
                            JSONArray jsonArray = (JSONArray) parsed;
                            int courseEnrollmentFScore = UpdateCourseEnrollmentFScore2(ai_result, uid);
                            if(courseEnrollmentFScore ==2){
                                object.put("result", "success");
                            }else{
                                object.put("result", "error");
                                object.put("error", "所写评语内容与评价模版不匹配，请重新输入！");
                            }
                        } else {
                            object.put("result", "error");
                            object.put("error", "获取的结果无效，请重新输入！");
                        }
                    } else {
                        object.put("result", "error");
                        object.put("error", "001评价过程中报错了，请检查参数重新操作！");
                    }
                } else if (state == 3 || state == 9) {
                    object.put("result", "error");
                    object.put("error", "003评价过程中报错了，请检查参数重新操作！");
                } else if (state == 9) {
                    object.put("result", "error");
                    object.put("error", "009评价过程中超时了，请检查网络重新操作！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    private int UpdateCourseEnrollmentFScore2(String ai_result,String uid){
        int zhengque=2;
        try {


            JSONArray jsonArray = JSONArray.parseArray(ai_result); // Correct for FastJSON

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String courseEnrollmentId = obj.getString("CourseEnrollment_id");
                Float finalScore = obj.getFloat("final_score");
                if(courseEnrollmentId!=null && !courseEnrollmentId.equals("")){
                    int tg = 2;
                    if (finalScore >= 60) {
                        tg = 1;
                    } else {
                        tg = 2;
                    }
                    TCourseEnrollment tCourseEnrollment = new TCourseEnrollment();
                    tCourseEnrollment.setFkeyid(Long.parseLong(ParamTools.getdeParam(courseEnrollmentId)));
                    tCourseEnrollment.setFifpass(tg);
                    tCourseEnrollment.setFscore(finalScore);
                    tCourseEnrollment.setFuid(Long.parseLong(uid));
                    tCourseEnrollment.setFudate(new Date());
                    tCourseEnrollment.setFcjtype(3);
                    tCourseEnrollmentService.update(tCourseEnrollment);
                    if (tg == 1) {
                        TCourseEnrollment courseEnrollment = tCourseEnrollmentService.findById(Long.parseLong(ParamTools.getdeParam(courseEnrollmentId)));
                        //根据课程查询 课程绑定的条件
                        TCourseAbilityConditionExample tCourseAbilityConditionExample = new TCourseAbilityConditionExample();
                        TCourseAbilityConditionExample.Criteria criteria1 = tCourseAbilityConditionExample.createCriteria();
                        criteria1.andFcourseidEqualTo(courseEnrollment.getFcourseid());
                        List<TCourseAbilityCondition> conditionList = tcourseabilityconditionService.findByExample(tCourseAbilityConditionExample);
                        if (conditionList.size() > 0) {
                            for (TCourseAbilityCondition condition : conditionList) {
                                TStudentAbilityExample studentabilityExample = new TStudentAbilityExample();
                                TStudentAbilityExample.Criteria criteria2 = studentabilityExample.createCriteria();
                                criteria2.andFstudentidEqualTo(courseEnrollment.getFstudentid());
                                criteria2.andFconditionidEqualTo(condition.getFconditionid());
                                List<TStudentAbility> studentAbilityList = tStudentAbilityService.findByExample(studentabilityExample);
                                if (studentAbilityList.size() == 0) {
                                    //往认定表里添加数据
                                    TStudentAbility tstudentability = new TStudentAbility();
                                    tstudentability.setFstudentid(courseEnrollment.getFstudentid());
                                    tstudentability.setFstate(1);
                                    tstudentability.setFmode(1);
                                    tstudentability.setFconditionid(condition.getFconditionid());
                                    tstudentability.setFabilityinf("");
                                    tstudentability.setFurl("");
                                    tstudentability.setFdate(new Date());
                                    tstudentability.setFcdate(new Date());
                                    tstudentability.setFcid(Long.parseLong(uid));
                                    tStudentAbilityService.save(tstudentability);
                                }
                            }
                            //查询当前学生 所拥有的能力 然后算分
                            int studentFPoints = tStudentAbilityService.getStudentFPoints(courseEnrollment.getFstudentid());
                            //修改学生表 能力得分
                            TStudent student = new TStudent();
                            student.setFkeyid(courseEnrollment.getFstudentid());
                            student.setFpoints(studentFPoints);
                            tStudentService.update(student);
                        }
                    }
                }else{
                    zhengque=1;
                }
//                System.out.println("CourseEnrollment_id: " + courseEnrollmentId);
//                System.out.println("final_score: " + finalScore);
//                System.out.println("----------------------");
            }
        } catch (Exception e) {
            zhengque=1;
        }
        return zhengque;
    }
}