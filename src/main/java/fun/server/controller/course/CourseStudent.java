package fun.server.controller.course;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.model.customQuery.coursestudent.CourseStudendata;
import fun.server.model.customQuery.coursestudent.CourseStudentCS;
import fun.server.model.customQuery.coursestudent.CoursestudentscoreCS;
import fun.server.model.customQuery.coursestudent.CoursestudentscoreData;
import fun.server.service.*;
import fun.server.service.impl.TCourseStandardServiceImpl;
import fun.tools.ParamTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 课程报名学生管理 相关业务处理
 */
@RestController
@RequestMapping("/s/coursestudent")
public class CourseStudent {

    private final TCourseService tCourseService;

    private final TStudentService tStudentService;

    private final TCourseStudentService tCourseStudentService;

    private final TStudentAbilityService tStudentAbilityService;

    private final TCourseStudentScoreService tCourseStudentScoreService;

    private final TCourseNatureService tCourseNatureService;

    private final TCourseCategoryService tCourseCategoryService;

    private final TCourseStandardService tCourseStandardService;

    private final TCourseStandardSService tCourseStandardSService;

    private final TCourseAbilityService tCourseAbilityService;

    private final TAbilityService tAbilityService;

    private final TAbilityLevelService tAbilityLevelService;

    private final TAbilityConditionService tAbilityConditionService;

    private final TAbilityConditionSService tAbilityConditionSService;

    private final TAbilityConditionAssessmentMethodService tAbilityConditionAssessmentMethodService;

    private final TAbilityAssessmentMethodService tAbilityAssessmentMethodService;
    private final TCourseStandardServiceImpl tcoursestandardService;

    private final TCourseStudentScoreTBService tCourseStudentScoreTBService;

    private final TAbilityTreeService tAbilityTreeService;

    private final TCourseStudentScoreTBTreeService tCourseStudentScoreTBTreeService;

    public CourseStudent(TCourseService tCourseService, TStudentService tStudentService, TCourseStudentService tCourseStudentService, TStudentAbilityService tStudentAbilityService, TCourseStudentScoreService tCourseStudentScoreService, TCourseNatureService tCourseNatureService, TCourseCategoryService tCourseCategoryService, TCourseStandardService tCourseStandardService, TCourseStandardSService tCourseStandardSService, TCourseAbilityService tCourseAbilityService, TAbilityService tAbilityService, TAbilityLevelService tAbilityLevelService, TAbilityConditionService tAbilityConditionService, TAbilityConditionSService tAbilityConditionSService, TAbilityConditionAssessmentMethodService tAbilityConditionAssessmentMethodService, TAbilityAssessmentMethodService tAbilityAssessmentMethodService, TCourseStandardServiceImpl tcoursestandardService, TCourseStudentScoreTBService tCourseStudentScoreTBService, TAbilityTreeService tAbilityTreeService, TCourseStudentScoreTBTreeService tCourseStudentScoreTBTreeService) {
        this.tCourseService = tCourseService;
        this.tStudentService = tStudentService;
        this.tCourseStudentService = tCourseStudentService;
        this.tStudentAbilityService = tStudentAbilityService;
        this.tCourseStudentScoreService = tCourseStudentScoreService;
        this.tCourseNatureService = tCourseNatureService;
        this.tCourseCategoryService = tCourseCategoryService;
        this.tCourseStandardService = tCourseStandardService;
        this.tCourseStandardSService = tCourseStandardSService;
        this.tCourseAbilityService = tCourseAbilityService;
        this.tAbilityService = tAbilityService;
        this.tAbilityLevelService = tAbilityLevelService;
        this.tAbilityConditionService = tAbilityConditionService;
        this.tAbilityConditionSService = tAbilityConditionSService;
        this.tAbilityConditionAssessmentMethodService = tAbilityConditionAssessmentMethodService;
        this.tAbilityAssessmentMethodService = tAbilityAssessmentMethodService;
        this.tcoursestandardService = tcoursestandardService;
        this.tCourseStudentScoreTBService = tCourseStudentScoreTBService;
        this.tAbilityTreeService = tAbilityTreeService;
        this.tCourseStudentScoreTBTreeService = tCourseStudentScoreTBTreeService;
    }


    /**
     * 获取课程报名学生信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycoursestudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycoursestudent(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String coursename = jsonParam.getString("coursename");
        String studentname = jsonParam.getString("studentname");
        String ftel = jsonParam.getString("ftel");
        String FLTypeID = jsonParam.getString("FLTypeID");
        String FNatureID = jsonParam.getString("FNatureID");
        int FSex = jsonParam.getInteger("FSex");
        String FStudentNo = jsonParam.getString("FStudentNo");

        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray coursestudent_Array = new JSONArray();
            // 查询条件
            CourseStudentCS courseStudentCS = new CourseStudentCS();
            courseStudentCS.setFCourseName(coursename);
            courseStudentCS.setFStudentName(studentname);
            courseStudentCS.setFTel(ftel);
            courseStudentCS.setFSex(FSex);
            courseStudentCS.setFStudentNo(FStudentNo);
            if (FLTypeID != null && !FLTypeID.equals("") && !FLTypeID.equals("1")) {
                FLTypeID = FLTypeID == null ? "0" : ParamTools.getdeParam(FLTypeID);
                courseStudentCS.setFLTypeID(Long.parseLong(FLTypeID));

            }
            if (FNatureID != null && !FNatureID.equals("")) {
                FNatureID = FNatureID == null ? "0" : ParamTools.getdeParam(FNatureID);
//                System.out.println(FNatureID);
                if (!FNatureID.equals("-1"))

                    courseStudentCS.setFNatureID(Long.parseLong(FNatureID));
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
                courseStudentCS.setOrderBy(orderSql.substring(1));
            } else {
                courseStudentCS.setOrderBy("tc.FCDATE desc");
            }
            PageInfo<CourseStudendata> coursestudentPageInfo = tCourseStudentService.getCourseStudentdata(courseStudentCS, (page - 1) * results, results);
            List<CourseStudendata> coursestudent_list = coursestudentPageInfo.getList();
            for (CourseStudendata coursestudent : coursestudent_list) {
                JSONObject coursestudent_object = new JSONObject();
                coursestudent_object.put("FCourseStudentID", ParamTools.getEnParam(coursestudent.getFCourseStudentID().toString()));
                coursestudent_object.put("FCourseID", ParamTools.getEnParam(coursestudent.getFCourseID().toString()));
                coursestudent_object.put("FStudentID", ParamTools.getEnParam(coursestudent.getFStudentID().toString()));
                TCourse courseServiceById = tCourseService.findById(Long.parseLong(coursestudent.getFCourseID().toString()));
                coursestudent_object.put("Fabilityid", courseServiceById == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(courseServiceById.getFabilityid().toString()));
                coursestudent_object.put("Fabilitylevelid", courseServiceById == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(courseServiceById.getFabilitylevelid().toString()));
                coursestudent_object.put("FCourseName", coursestudent.getFCourseName());
                coursestudent_object.put("FCourseNo", coursestudent.getFCourseNo());
                coursestudent_object.put("FStudentName", coursestudent.getFStudentName());
                coursestudent_object.put("FScore", coursestudent.getFScore());
                coursestudent_object.put("FIfPass", coursestudent.getFIfPass());
                coursestudent_object.put("FTel", coursestudent.getFTel());
                coursestudent_object.put("FEdition", coursestudent.getFEdition());
                coursestudent_object.put("FYWName", coursestudent.getFYWName());
                TCourseCategory tCourseCategory = tCourseCategoryService.findById(courseServiceById.getFtype());
                coursestudent_object.put("FTypeName", getPName(tCourseCategory.getFpid(), tCourseCategory.getFname()));
                TCourseNature courseNature = tCourseNatureService.findById(courseServiceById.getFcnature());
                coursestudent_object.put("FCNatureName", courseNature == null ? "" : courseNature.getFname());
                coursestudent_object.put("FClassName", coursestudent.getFClassName());
                coursestudent_object.put("FStudentNo", coursestudent.getFStudentNo());
                coursestudent_object.put("FStudentSex", coursestudent.getFStudentSex());

                coursestudent_Array.add(coursestudent_object);
            }
            // 返回值
            object.put("list", coursestudent_Array);
            object.put("total", coursestudentPageInfo.getTotal()); // 总行数
            object.put("page", coursestudentPageInfo.getPageNum()); // 当前页数
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
     * 变更课程信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/delcoursestudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delcoursestudent(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TCourseStudentExample tCourseStudentExample = new TCourseStudentExample();
            TCourseStudentExample.Criteria criteria = tCourseStudentExample.createCriteria();
            criteria.andFscoreIsNull();
            criteria.andFkeyidEqualTo(Long.parseLong(id));
            List<TCourseStudent> studentList = tCourseStudentService.findByExample(tCourseStudentExample);
            if (studentList.size() > 0) {
                tCourseStudentService.deleteById(Long.parseLong(id));
                // 返回值
                object.put("result", "success");
            } else {
                object.put("result", "error");
                object.put("error", "该学生报名的课程已有成绩，不能删除！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 进入填报页面 在循环判断一次 是否插入了填报过程数据
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */

    @ResponseBody
    @RequestMapping(value = "/addcoursestudentsoretbtree", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addcoursestudentsoretbtree(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FCourseStudent = jsonParam.getString("FCourseStudent");
//        String FCourse = jsonParam.getString("FCourse");//课程ID
        String FCourseID = jsonParam.getString("FCourseID");//课程ID
        int FGroupNum = jsonParam.getInteger("FGroupNum");//当前是第几组成绩
        try {
            FCourseID = FCourseID == null ? "0" : ParamTools.getdeParam(FCourseID);
            FCourseStudent = FCourseStudent == null ? "0" : ParamTools.getdeParam(FCourseStudent);
            String CookiesLoginusergroupsID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginusergroupsID != null && !CookiesLoginusergroupsID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            innsertCourabilityTBtree(Long.parseLong(FCourseStudent), Long.parseLong(FCourseID), Long.parseLong(uid),FGroupNum);
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
                if (studentname != null && !studentname.equals("")) {

                    //增加新选的
                    JSONArray powerRoles_Array = JSONArray.parseArray(studentname);
                    for (Object userid : powerRoles_Array) {
                        Long studentid = Long.parseLong(ParamTools.getdeParam(userid.toString()));
                        TCourseStudentExample tCourseStudentExample = new TCourseStudentExample();
                        TCourseStudentExample.Criteria criteria = tCourseStudentExample.createCriteria();
                        criteria.andFcourseidEqualTo(Long.parseLong(FCourse));
                        criteria.andFstudentidEqualTo(studentid);
                        List<TCourseStudent> studentList = tCourseStudentService.findByExample(tCourseStudentExample);

                        TCourse tCourse1 = tCourseService.findById(Long.parseLong(FCourse));

                        TStudentAbilityExample tStudentAbilityExample = new TStudentAbilityExample();
                        TStudentAbilityExample.Criteria criteria1 = tStudentAbilityExample.createCriteria();
                        criteria1.andFstudentidEqualTo(studentid);
                        criteria1.andFabilityidEqualTo(tCourse1.getFabilityid());
                        criteria1.andFabilitylevelidEqualTo(tCourse1.getFabilitylevelid());
                        criteria1.andFstateNotEqualTo(2); //不是注销状态的
                        List<TStudentAbility> tStudentAbilityList = tStudentAbilityService.findByExample(tStudentAbilityExample);
                        if (studentList.size() == 0 && tStudentAbilityList.size() == 0) {
                            TCourseStudent tCourseStudent = new TCourseStudent();
                            tCourseStudent.setFcourseid(Long.parseLong(FCourse));
                            tCourseStudent.setFstudentid(studentid);
                            tCourseStudent.setFmode(1);
                            tCourseStudent.setFifpass(0);
                            tCourseStudent.setFcid(Long.parseLong(uid));
                            tCourseStudent.setFcdate(new Date());
                            tCourseStudentService.save(tCourseStudent);


                            //添加 课程报名的填报过程，能力的树形数据、
                            innsertCourabilityTBtree(tCourseStudent.getFkeyid(), Long.parseLong(FCourse), Long.parseLong(uid),1);
                        } else {
                            TStudent tStudent = tStudentService.findById(studentid);
                            errorstr += tStudent.getFname() + "、";
                        }
                    }
                }

                if (!errorstr.equals("")) {
                    errorstr = errorstr.substring(0, errorstr.length() - 1);
                    object.put("errorstr", "学生【" + errorstr + "】已报名该课程或已拥有该课程能力！");
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

    private void innsertCourabilityTBtree(Long FCourseStudentID, Long FCourseID, Long uid,int FGropNum) {
        try {
            TCourseAbilityExample tCourseAbilityExample = new TCourseAbilityExample();
            TCourseAbilityExample.Criteria criteria = tCourseAbilityExample.createCriteria();
            criteria.andFcourseidEqualTo(FCourseID);
            criteria.andFstateEqualTo(1);
            List<TCourseAbility> tCourseAbilities = tCourseAbilityService.findByExample(tCourseAbilityExample);
            if (tCourseAbilities.size() > 0) {
                for (TCourseAbility tCourseAbility : tCourseAbilities) {
                    Long fabilitylevelid = tCourseAbility.getFabilitylevelid();
                    TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
                    TAbilityTreeExample.Criteria criteria1 = tAbilityTreeExample.createCriteria();
                    criteria1.andFpathLike("|_1_||_" + fabilitylevelid + "_|%");
                    criteria1.andFdelEqualTo(1);
                    criteria1.andFstateEqualTo(1);
                    criteria1.andFkeyidNotEqualTo(fabilitylevelid);
                    List<TAbilityTree> abilityTreeList = tAbilityTreeService.findByExample(tAbilityTreeExample);
                    if (abilityTreeList.size() > 0) {
                        TCourseStudentScoreTbTreeExample tCourseStudentScoreTbTreeExample = null;
                        TCourseStudentScoreTbTree tCourseStudentScoreTbTree = null;
                        TCourseStudentScoreTbTreeExample.Criteria criteria2 = null;
                        for (TAbilityTree tAbilityTree : abilityTreeList) {
                            tCourseStudentScoreTbTreeExample = new TCourseStudentScoreTbTreeExample();
                            criteria2 = tCourseStudentScoreTbTreeExample.createCriteria();
                            criteria2.andFcoursestudentidEqualTo(FCourseStudentID);
                            criteria2.andFabilitylevelidEqualTo(fabilitylevelid);
                            criteria2.andFabilitytreeidEqualTo(tAbilityTree.getFkeyid());
                            criteria2.andFgroypnumEqualTo(FGropNum);
                            List<TCourseStudentScoreTbTree> treeList = tCourseStudentScoreTBTreeService.findByExample(tCourseStudentScoreTbTreeExample);
                            if (treeList.size() == 0) {
                                tCourseStudentScoreTbTree = new TCourseStudentScoreTbTree();
                                tCourseStudentScoreTbTree.setFcourseid(FCourseID);
                                tCourseStudentScoreTbTree.setFcoursestudentid(FCourseStudentID);
                                tCourseStudentScoreTbTree.setFabilitylevelid(fabilitylevelid);
                                tCourseStudentScoreTbTree.setFabilitytreeid(tAbilityTree.getFkeyid());
                                tCourseStudentScoreTbTree.setFpid(tAbilityTree.getFpid());
                                tCourseStudentScoreTbTree.setFmethodweight(tAbilityTree.getFmethodweight());
                                tCourseStudentScoreTbTree.setFcontent("");
                                tCourseStudentScoreTbTree.setFcontentscore((float) 0);
                                tCourseStudentScoreTbTree.setFscore((float) 0);
                                tCourseStudentScoreTbTree.setFdiv(tAbilityTree.getFdiv());
                                tCourseStudentScoreTbTree.setFnodetype(tAbilityTree.getFnodetype());
                                tCourseStudentScoreTbTree.setFgroypnum(1);
                                tCourseStudentScoreTbTree.setFjsstate(1);
                                tCourseStudentScoreTbTree.setFjsscore((float) 0);
                                tCourseStudentScoreTbTree.setFgpid(tAbilityTree.getFgpid());
                                tCourseStudentScoreTbTree.setFcid(uid);
                                tCourseStudentScoreTbTree.setFcdate(new Date());
                                tCourseStudentScoreTbTree.setFgroypnum(FGropNum);
                                tCourseStudentScoreTBTreeService.save(tCourseStudentScoreTbTree);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void innsertCourabilityTBtree(Long FCourseStudentID, Long FCourseID, Long uid,int FGropNum) {
//        try {
//            TCourseAbilityExample tCourseAbilityExample = new TCourseAbilityExample();
//            TCourseAbilityExample.Criteria criteria = tCourseAbilityExample.createCriteria();
//            criteria.andFcourseidEqualTo(FCourseID);
//            criteria.andFstateEqualTo(1);
//            List<TCourseAbility> tCourseAbilities = tCourseAbilityService.findByExample(tCourseAbilityExample);
//            if (tCourseAbilities.size() > 0) {
//                for (TCourseAbility tCourseAbility : tCourseAbilities) {
//                    Long fabilitylevelid = tCourseAbility.getFabilitylevelid();
//                    TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
//                    tAbilityTreeExample.createCriteria().andFpidEqualTo(fabilitylevelid).andFdelEqualTo(1).andFstateEqualTo(1);
//                    List<TAbilityTree> abilityTreeList = tAbilityTreeService.findByExample(tAbilityTreeExample);
//                    if (abilityTreeList.size() > 0) {
//                        TCourseStudentScoreTbTreeExample tCourseStudentScoreTbTreeExample = null;
//                        TCourseStudentScoreTbTree tCourseStudentScoreTbTree = null;
//                        TCourseStudentScoreTbTreeExample.Criteria criteria2 = null;
//                        for (TAbilityTree tAbilityTree : abilityTreeList) {
//                            tCourseStudentScoreTbTreeExample = new TCourseStudentScoreTbTreeExample();
//                            criteria2 = tCourseStudentScoreTbTreeExample.createCriteria();
//                            criteria2.andFcoursestudentidEqualTo(FCourseStudentID);
//                            criteria2.andFabilitylevelidEqualTo(fabilitylevelid);
//                            criteria2.andFabilitytreeidEqualTo(tAbilityTree.getFkeyid());
//                            criteria2.andFgroypnumEqualTo(FGropNum);
//                            List<TCourseStudentScoreTbTree> treeList = tCourseStudentScoreTBTreeService.findByExample(tCourseStudentScoreTbTreeExample);
//                            if (treeList.size() == 0) {
//                                tCourseStudentScoreTbTree = new TCourseStudentScoreTbTree();
//                                tCourseStudentScoreTbTree.setFcourseid(FCourseID);
//                                tCourseStudentScoreTbTree.setFcoursestudentid(FCourseStudentID);
//                                tCourseStudentScoreTbTree.setFabilitylevelid(fabilitylevelid);
//                                tCourseStudentScoreTbTree.setFabilitytreeid(tAbilityTree.getFkeyid());
//                                tCourseStudentScoreTbTree.setFpid(tAbilityTree.getFpid());
//                                tCourseStudentScoreTbTree.setFmethodweight(tAbilityTree.getFmethodweight());
//                                tCourseStudentScoreTbTree.setFcontent("");
//                                tCourseStudentScoreTbTree.setFcontentscore((float) 0);
//                                tCourseStudentScoreTbTree.setFscore((float) 0);
//                                tCourseStudentScoreTbTree.setFdiv(tAbilityTree.getFdiv());
//                                tCourseStudentScoreTbTree.setFnodetype(tAbilityTree.getFnodetype());
//                                tCourseStudentScoreTbTree.setFgroypnum(1);
//                                tCourseStudentScoreTbTree.setFjsstate(1);
//                                tCourseStudentScoreTbTree.setFjsscore((float) 0);
//                                tCourseStudentScoreTbTree.setFgpid(tAbilityTree.getFgpid());
//                                tCourseStudentScoreTbTree.setFcid(uid);
//                                tCourseStudentScoreTbTree.setFcdate(new Date());
//                                tCourseStudentScoreTbTree.setFgroypnum(FGropNum);
//                                tCourseStudentScoreTBTreeService.save(tCourseStudentScoreTbTree);
//                                if (tAbilityTree.getFisleaf() == 0) {
//                                    innsertCourabilityTBtreeNode(FCourseStudentID, FCourseID, tAbilityTree.getFkeyid(), fabilitylevelid, uid,FGropNum);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void innsertCourabilityTBtreeNode(Long FCourseStudentID, Long FCourseID, Long FPID, Long fabilitylevelid, Long uid,int FGropNum) {
        try {
            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            tAbilityTreeExample.createCriteria().andFpidEqualTo(FPID).andFdelEqualTo(1).andFstateEqualTo(1);
            List<TAbilityTree> abilityTreeList = tAbilityTreeService.findByExample(tAbilityTreeExample);
            if (abilityTreeList.size() > 0) {
                TCourseStudentScoreTbTreeExample tCourseStudentScoreTbTreeExample = null;
                TCourseStudentScoreTbTree tCourseStudentScoreTbTree = null;
                TCourseStudentScoreTbTreeExample.Criteria criteria = null;
                for (TAbilityTree tAbilityTree : abilityTreeList) {
                    tCourseStudentScoreTbTreeExample = new TCourseStudentScoreTbTreeExample();
                    criteria = tCourseStudentScoreTbTreeExample.createCriteria();
                    criteria.andFcoursestudentidEqualTo(FCourseStudentID);
                    criteria.andFabilitylevelidEqualTo(fabilitylevelid);
                    criteria.andFabilitytreeidEqualTo(tAbilityTree.getFkeyid());
                    criteria.andFgroypnumEqualTo(FGropNum);
                    List<TCourseStudentScoreTbTree> treeList = tCourseStudentScoreTBTreeService.findByExample(tCourseStudentScoreTbTreeExample);
                    if (treeList.size() == 0) {
                        tCourseStudentScoreTbTree = new TCourseStudentScoreTbTree();
                        tCourseStudentScoreTbTree.setFcourseid(FCourseID);
                        tCourseStudentScoreTbTree.setFcoursestudentid(FCourseStudentID);
                        tCourseStudentScoreTbTree.setFabilitylevelid(fabilitylevelid);
                        tCourseStudentScoreTbTree.setFabilitytreeid(tAbilityTree.getFkeyid());
                        tCourseStudentScoreTbTree.setFpid(tAbilityTree.getFpid());
                        tCourseStudentScoreTbTree.setFmethodweight(tAbilityTree.getFmethodweight());
                        tCourseStudentScoreTbTree.setFcontent("");
                        tCourseStudentScoreTbTree.setFcontentscore((float) 0);
                        tCourseStudentScoreTbTree.setFscore((float) 0);
                        tCourseStudentScoreTbTree.setFdiv(tAbilityTree.getFdiv());
                        tCourseStudentScoreTbTree.setFnodetype(tAbilityTree.getFnodetype());
                        tCourseStudentScoreTbTree.setFgroypnum(1);
                        tCourseStudentScoreTbTree.setFjsstate(1);
                        tCourseStudentScoreTbTree.setFjsscore((float) 0);
                        tCourseStudentScoreTbTree.setFgpid(tAbilityTree.getFgpid());
                        tCourseStudentScoreTbTree.setFcid(uid);
                        tCourseStudentScoreTbTree.setFcdate(new Date());
                        tCourseStudentScoreTbTree.setFgroypnum(FGropNum);
                        tCourseStudentScoreTBTreeService.save(tCourseStudentScoreTbTree);
                        if (tAbilityTree.getFisleaf() == 0) {
                            innsertCourabilityTBtreeNode(FCourseStudentID, FCourseID, tAbilityTree.getFkeyid(), fabilitylevelid, uid,FGropNum);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 修改学生成绩
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改学生成绩")
    @ResponseBody
    @RequestMapping(value = "/updatecoursestudentcj", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatecoursestudentcj(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String fcourseStudentID = jsonParam.getString("fcourseStudentID");
        String FCourse = jsonParam.getString("FCourse");//课程ID

        float FScore = jsonParam.getFloat("FScore");
        try {
            String CookiesLoginusergroupsID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginusergroupsID != null && !CookiesLoginusergroupsID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            FCourse = FCourse == null ? "0" : ParamTools.getdeParam(FCourse);
            fcourseStudentID = fcourseStudentID == null ? "0" : ParamTools.getdeParam(fcourseStudentID);

            TCourse tCourse = tCourseService.findById(Long.parseLong(FCourse));
            TCourseStudent courseStudent = tCourseStudentService.findById(Long.parseLong(fcourseStudentID));
            //获取合格分数
            BigDecimal decimal = tCourse.getFpassscore();
            // 将 float 转换为 BigDecimal
            BigDecimal floatAsBigDecimal = new BigDecimal(Float.toString(FScore));
            // 比较大小
            int result = floatAsBigDecimal.compareTo(decimal);
            if (result == 0 || result > 0) {
                //学生成绩 等于或者大于 合格分数
                //添加完成绩后，添加学生对应能力
                TCourseStudent tCourseStudent = new TCourseStudent();
                tCourseStudent.setFkeyid(Long.parseLong(fcourseStudentID));
                tCourseStudent.setFscore(floatAsBigDecimal);
                tCourseStudent.setFudate(new Date());
                tCourseStudent.setFuid(Long.parseLong(uid));
                tCourseStudent.setFifpass(1); //通过
                tCourseStudentService.update(tCourseStudent);

                // 定义日期格式
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                TStudentAbility tstudentability = new TStudentAbility();
                tstudentability.setFstudentid(courseStudent.getFstudentid());
                tstudentability.setFstate(1);
                tstudentability.setFmode(1);
                tstudentability.setFabilityid(tCourse.getFabilityid());
                tstudentability.setFabilitylevelid(tCourse.getFabilitylevelid());
                tstudentability.setFabilityinf("");
                tstudentability.setFurl("");

                tstudentability.setFdate(dateFormat.parse(new Date().toString()));
                tstudentability.setFcdate(new Date());
                tstudentability.setFcid(Long.parseLong(uid));
                tStudentAbilityService.save(tstudentability);


                //查询当前学生 所拥有的能力 然后算分
                int studentFPoints = tStudentAbilityService.getStudentFPoints(courseStudent.getFstudentid());
                //修改学生表 能力得分
                TStudent student = new TStudent();
                student.setFkeyid(courseStudent.getFstudentid());
                student.setFpoints(studentFPoints);
                tStudentService.update(student);


            } else {
                //学生成绩 小于 合格分数
                //添加完成绩后，
                TCourseStudent tCourseStudent = new TCourseStudent();
                tCourseStudent.setFkeyid(Long.parseLong(fcourseStudentID));
                tCourseStudent.setFscore(floatAsBigDecimal);
                tCourseStudent.setFudate(new Date());
                tCourseStudent.setFuid(Long.parseLong(uid));
                tCourseStudent.setFifpass(2); //不通过
                tCourseStudentService.update(tCourseStudent);
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
     * 获取课程学生填报成绩信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/getCoursestudentscore", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getCoursestudentscore(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            // 获取数据库记录
            JSONArray coursestudent_Array = new JSONArray();
            // 查询条件
            CoursestudentscoreCS coursestudentscoreCS = new CoursestudentscoreCS();
            id = id == null ? "0" : ParamTools.getdeParam(id);
//            System.out.println(id);
            coursestudentscoreCS.setFStudentScoreID(Long.parseLong(id));
            List<CoursestudentscoreData> dataList = tCourseStudentService.getCoursestudentscore(coursestudentscoreCS);

            for (CoursestudentscoreData coursestudent : dataList) {
                JSONObject coursestudent_object = new JSONObject();
                coursestudent_object.put("FKeyID", ParamTools.getEnParam(coursestudent.getFKeyID().toString()));
                coursestudent_object.put("title", coursestudent.getFMethodName());
                coursestudent_object.put("total", coursestudent.getTotalScore());
//                System.out.println(coursestudent.getConditions());
                coursestudent_object.put("details", JSONArray.parse(coursestudent.getConditions()));
                coursestudent_object.put("contributionWeight", coursestudent.getFMethodWeight());
                coursestudent_object.put("ConditionIDs", ParamTools.getEnParam(coursestudent.getConditionIDs()));
                coursestudent_object.put("ConditionScores", coursestudent.getConditionScores());
                coursestudent_object.put("FScoreMin", coursestudent.getFScoreMin());
                coursestudent_Array.add(coursestudent_object);
            }
//            System.out.println(coursestudent_Array);
            // 返回值
            object.put("FScoreMin", dataList.get(0).getFScoreMin());
            object.put("examData", coursestudent_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 显示学生报名的课程考核条件及方式
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/coursesutdenttx", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String coursesutdenttx(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id"); //学生报名课程id
        try {
            JSONArray zongarray = new JSONArray();
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TCourseStudent courseStudent = tCourseStudentService.findById(Long.parseLong(id));
            if (courseStudent != null) {
                Long fcourseid = courseStudent.getFcourseid();//课程ID
                //课程能力
                TCourseAbilityExample tCourseAbilityExample = new TCourseAbilityExample();
                tCourseAbilityExample.createCriteria().andFcourseidEqualTo(fcourseid);
                tCourseAbilityExample.setOrderByClause("FOrder");
                List<TCourseAbility> tCourseAbilityList = tCourseAbilityService.findByExample(tCourseAbilityExample);
                String str1 = "填报内容";
                String str2 = "分值";
                String str3 = "描述";
                String str4 = "填报范围";
                if (tCourseAbilityList.size() > 0) {
                    TAbilityTree ability = null;

                    TCourseStandardExample tCourseStandardExample = null;
                    TCourseStandardExample.Criteria criteria = null;
                    List<TCourseStandard> courseStandards = null;
                    TAbilityTree abilityLevel = null;
                    String abname = "";
                    int i = 1;
                    Integer fqztype = -1;
                    for (TCourseAbility tCourseAbility : tCourseAbilityList) {
                        JSONObject ability_object = new JSONObject();
//                        ability = tAbilityTreeService.findById(tCourseAbility.getFabilityid());
//                        abilityLevel = tAbilityTreeService.findById(tCourseAbility.getFabilitylevelid());

                        TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
                        tAbilityTreeExample.createCriteria().andFdelEqualTo(1).andFkeyidEqualTo(tCourseAbility.getFabilityid());
                        List<TAbilityTree> abilityTrees = tAbilityTreeService.findByExample(tAbilityTreeExample);
                        if (abilityTrees.size() > 0) {
                            ability = abilityTrees.get(0);
                        }
                        tAbilityTreeExample = new TAbilityTreeExample();
                        tAbilityTreeExample.createCriteria().andFdelEqualTo(1).andFkeyidEqualTo(tCourseAbility.getFabilitylevelid());
                        abilityTrees = tAbilityTreeService.findByExample(tAbilityTreeExample);
                        if (abilityTrees.size() > 0) {
                            abilityLevel = abilityTrees.get(0);
                        }

                        if (ability != null && abilityLevel != null) {
                            abname = ability == null ? "" : ability.getFname();
                            abname += "/";
                            abname += abilityLevel == null ? "" : abilityLevel.getFname();

                            Serializable afmethodweight = tCourseAbility.getFmethodweight() == null ? "0.0" : tCourseAbility.getFmethodweight();
                            assert abilityLevel != null;
                            ability_object.put("groupTitle", "能力： " + abname + "【能力分值：" + abilityLevel.getFscoremin() + "~" + abilityLevel.getFscoremax() + "分】" + "【权重：" + afmethodweight + "%】");
                            tCourseStandardExample = new TCourseStandardExample();
                            criteria = tCourseStandardExample.createCriteria();
                            criteria.andFcourseidEqualTo(fcourseid);
                            criteria.andFabilityidEqualTo(tCourseAbility.getFabilityid());
                            criteria.andFabilitylevelidEqualTo(tCourseAbility.getFabilitylevelid());
                            courseStandards = tCourseStandardService.findByExample(tCourseStandardExample);
                            JSONArray coursetj_array = new JSONArray();
                            for (TCourseStandard tCourseStandard : courseStandards) {
                                JSONObject coursetj_object = new JSONObject();
                                //查询条件信息
                                String conditname = "";
                                String conditmeno = "";
                                JSONArray method_array = new JSONArray();

                                TAbilityTree serviceById = tAbilityTreeService.findById(tCourseStandard.getFabilitycamid());
                                Serializable fmethodweight = serviceById.getFmethodweight() == null ? "0.0" : serviceById.getFmethodweight();
                                conditname = serviceById == null ? "" : serviceById.getFname() + "【条件分值：" + serviceById.getFconditionscore() + "分】" + "【权重：" + fmethodweight + "%】 ";

                                //获取取值类型与取值标准
//                            Long coursestandardsID = 0l;
                                String qzfw = "";
                                TCourseStandardSExample tCourseStandardSExample = new TCourseStandardSExample();
                                tCourseStandardSExample.createCriteria().andFcsidEqualTo(tCourseStandard.getFkeyid());
                                List<TCourseStandardS> courseStandardSList = tCourseStandardSService.findByExample(tCourseStandardSExample);
                                if (courseStandardSList.size() > 0) {
                                    int ii = 1;

                                    for (TCourseStandardS tCourseStandardS : courseStandardSList) {
                                        fqztype = tCourseStandardS.getFqztype();
//                                    coursestandardsID = tCourseStandardS.getFkeyid();
                                        if (tCourseStandardS.getFqztype() == 0) {//布尔
                                            qzfw += ii + "、";
                                            qzfw += "" + str1 + "：<font color='bule' class='clickable'>" + tCourseStandardS.getFsvalue() + "</font>";
                                            qzfw += "，" + str2 + "：" + tCourseStandardS.getFvalueul();
                                            qzfw += "，" + str3 + "：" + tCourseStandardS.getFmemo();
                                            qzfw += "<br>";
                                            ii++;
                                        } else if (tCourseStandardS.getFqztype() == 1) {//数值
                                            qzfw += ii + "、";
                                            qzfw += str4 + "：";
                                            qzfw += "<font color='bule'>" + tCourseStandardS.getFvaluell() + " <= N < " + tCourseStandardS.getFvalueul() + "</font>";
                                            qzfw += "，" + str2 + "：" + tCourseStandardS.getFsvalue();
                                            qzfw += "，" + str3 + "：" + tCourseStandardS.getFmemo();
                                            qzfw += "<br>";
                                            ii++;
                                        } else if (tCourseStandardS.getFqztype() == 2) {//字符串
                                            qzfw += ii + "、";
                                            qzfw += "" + str1 + "：<font color='bule' class='clickable'>" + tCourseStandardS.getFsvalue() + "</font>";
                                            qzfw += "，" + str2 + "：" + tCourseStandardS.getFvalueul();
                                            qzfw += "，" + str3 + "：" + tCourseStandardS.getFmemo();
                                            qzfw += "<br>";
                                            ii++;
                                        } else if (tCourseStandardS.getFqztype() == 3) {//原值
                                            qzfw += ii + "、";
                                            qzfw += "" + str1 + "：<font color='bule'>直接获取原值</font>";
                                            qzfw += "<br>";
                                            ii++;
                                        }
                                    }
                                }
                                tAbilityTreeExample = new TAbilityTreeExample();
                                TAbilityTreeExample.Criteria criteria1 = tAbilityTreeExample.createCriteria();
                                criteria1.andFnodetypeEqualTo(4);
                                criteria1.andFdelEqualTo(1);
                                criteria1.andFpidEqualTo(tCourseStandard.getFabilitycamid());
                                List<TAbilityTree> tAbilityConditionAssessmentMethodList = tAbilityTreeService.findByExample(tAbilityTreeExample);
                                if (tAbilityConditionAssessmentMethodList.size() > 0) {
                                    for (TAbilityTree tAbilityConditionAssessmentMethod : tAbilityConditionAssessmentMethodList) {
                                        JSONObject amthod_object = new JSONObject();
                                        TAbilityAssessmentMethod assessmentMethodServiceById = tAbilityAssessmentMethodService.findById(tAbilityConditionAssessmentMethod.getFmethodid());
                                        amthod_object.put("id", "scheme-design_" + ParamTools.getEnParam(tAbilityConditionAssessmentMethod.getFkeyid().toString()));
                                        amthod_object.put("tjid", "tj_" + ParamTools.getEnParam(tAbilityConditionAssessmentMethod.getFpid().toString()));
                                        amthod_object.put("coursestandardsID", "standardID_" + ParamTools.getEnParam(tCourseStandard.getFkeyid().toString()));
                                        amthod_object.put("tjtype", "tjtype_" + 1);
                                        amthod_object.put("title", assessmentMethodServiceById == null ? "未知方式" : assessmentMethodServiceById.getFmethodname() + "(权重" + tAbilityConditionAssessmentMethod.getFmethodweight() + "%)");
                                        amthod_object.put("fqztype", fqztype);
                                        amthod_object.put("Fconditionid", tCourseStandard.getFabilitycamid());
                                        method_array.add(amthod_object);
                                    }
                                }
                                coursetj_object.put("title", conditname);
                                coursetj_object.put("description", qzfw);
                                coursetj_object.put("inputs", method_array);
                                coursetj_array.add(coursetj_object);
                            }
                            ability_object.put("items", coursetj_array);
                            zongarray.add(ability_object);
                        }
                        i++;
                    }

                }
                // 返回值
                object.put("jsonData", zongarray);
                object.put("result", "success");
            } else {
                object.put("result", "error");
                object.put("error", "获取数据失败，请刷新重试！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

//    /**
//     * 根据填报内容 获取分值
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException 输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/gettianbaofenzhi", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String gettianbaofenzhi(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String FCourseStudent = jsonParam.getString("FCourseStudent");
//        String fcamid = jsonParam.getString("fcamid").replace("scheme-design_", "");
//        String value = jsonParam.getString("value");
//        try {
//            String CookiesLoginStrucDID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//            String uid = ""; // 当前登录用户ID
//            if (CookiesLoginStrucDID != null && !CookiesLoginStrucDID.equals("")) {
//                uid = ParamTools.getdeParam(CookiesLoginStrucDID);
//            }
//            FCourseStudent = FCourseStudent == null ? "0" : ParamTools.getdeParam(FCourseStudent);
//            fcamid = fcamid == null ? "0" : ParamTools.getdeParam(fcamid);
//            long key = Long.parseLong(FCourseStudent);//学生课程报名ID
//            long camid = Long.parseLong(fcamid);//考核方式ID
//            TCourseStudent tCourseStudent = tCourseStudentService.findById(key);
//            TCourseAbilityExample tCourseAbilityExample = new TCourseAbilityExample();
//            tCourseAbilityExample.createCriteria().andFcourseidEqualTo(tCourseStudent.getFcourseid());
//            List<TCourseAbility> abilities = tCourseAbilityService.findByExample(tCourseAbilityExample);
//            TAbilityConditionAssessmentMethod conditionAssessmentMethod = tAbilityConditionAssessmentMethodService.findById(camid);
//            float weight = conditionAssessmentMethod.getFmethodweight() / 100; //权重
//            BigDecimal weightFloat = BigDecimal.valueOf(weight);
//            BigDecimal zongfen = null;
//            float zongfen2 = 0.0f;
//            Long coursestandardsid = 0l;
//            if (abilities.size() > 0) {
//                for (TCourseAbility tCourseAbility : abilities) {
//                    TCourseStandardExample tCourseStandardExample = new TCourseStandardExample();
//                    tCourseStandardExample.createCriteria()
//                            .andFcamtypeEqualTo(conditionAssessmentMethod.getFtype())
//                            .andFabilitycamidEqualTo(conditionAssessmentMethod.getFconditionid())
//                            .andFcourseidEqualTo(tCourseStudent.getFcourseid())
//                            .andFabilityidEqualTo(tCourseAbility.getFabilityid())
//                            .andFabilitylevelidEqualTo(tCourseAbility.getFabilitylevelid())
//                            .andFcourseabilityidEqualTo(tCourseAbility.getFkeyid());
//                    List<TCourseStandard> tCourseStandards = tcoursestandardService.findByExample(tCourseStandardExample);
//                    if (tCourseStandards.size() > 0) {
//                        for (TCourseStandard tCourseStandard : tCourseStandards) {
//                            TCourseStandardSExample tCourseStandardSExample = new TCourseStandardSExample();
//                            tCourseStandardSExample.createCriteria().andFcsidEqualTo(tCourseStandard.getFkeyid()).andFqztypeEqualTo(tCourseStandard.getFqztype());
//                            List<TCourseStandardS> standardS = tCourseStandardSService.findByExample(tCourseStandardSExample);
//                            if (standardS.size() > 0) {
//                                for (TCourseStandardS tCourseStandardS : standardS) {
////                            if (result < 0) {
////                                System.out.println("bigDecimalValue 小于 floatValue");
////                            } else if (result == 0) {
////                                System.out.println("bigDecimalValue 等于 floatValue");
////                            } else {
////                                System.out.println("bigDecimalValue 大于 floatValue");
////                            }
//
//                                    if (tCourseStandard.getFqztype() == 0 || tCourseStandard.getFqztype() == 2) {
//
//                                        if (tCourseStandardS.getFsvalue().equals(value)) {
//                                            zongfen = tCourseStandardS.getFvalueul().multiply(weightFloat);
//                                            object.put("value", tCourseStandardS.getFvalueul().multiply(weightFloat));
//                                            coursestandardsid = tCourseStandardS.getFkeyid();
//                                            break;
//                                        } else {
//                                            zongfen = BigDecimal.valueOf(0);
//                                            object.put("value", 0);
//                                        }
//                                    } else if (tCourseStandard.getFqztype() == 1) {
//                                        BigDecimal bigDecimalFromFloat = null;
//                                        float values = 0;
//                                        if (value != null && !value.equals("")) {
//                                            values = Float.parseFloat(value);
//                                            bigDecimalFromFloat = BigDecimal.valueOf(values);
//                                        } else {
//                                            value = "";
//                                        }
//                                        if (!value.equals("")) {
//                                            // 使用 compareTo 方法比较两个 BigDecimal 对象
//                                            int result = tCourseStandardS.getFvaluell().compareTo(bigDecimalFromFloat);
//                                            int result2 = tCourseStandardS.getFvalueul().compareTo(bigDecimalFromFloat);
//                                            if (result <= 0 && result2 > 0) {
//                                                zongfen = BigDecimal.valueOf(Double.parseDouble(tCourseStandardS.getFsvalue())).multiply(weightFloat);
//                                                object.put("value", zongfen);
//                                                coursestandardsid = tCourseStandardS.getFkeyid();
//                                                break;
//                                            } else {
//                                                zongfen = BigDecimal.valueOf(0);
//                                                object.put("value", 0);
//                                            }
//                                        } else {
//                                            zongfen = BigDecimal.valueOf(0);
//                                            object.put("value", 0);
//                                        }
//                                    } else if (tCourseStandard.getFqztype() == 3) {
//                                        coursestandardsid = tCourseStandardS.getFkeyid();
//                                        zongfen = BigDecimal.valueOf(Double.parseDouble(value)).multiply(weightFloat);
////                                        zongfen = BigDecimal.valueOf(Double.parseDouble(value));
//                                        object.put("value", zongfen);
//                                    }
//                                }
//                            }
//                        }
//                        TCourseStudentScoreTbExample tCourseStudentScoreTbExample = new TCourseStudentScoreTbExample();
//                        TCourseStudentScoreTbExample.Criteria criteria = tCourseStudentScoreTbExample.createCriteria();
//                        criteria.andFcoursestudentidEqualTo(key);
//                        criteria.andFacamidEqualTo(camid);
//                        if (coursestandardsid != 0) {
//                            List<TCourseStudentScoreTb> tbList = tCourseStudentScoreTBService.findByExample(tCourseStudentScoreTbExample);
//                            if (tbList.size() > 0) {
//                                //存在则修改 内容和得分
//                                for (TCourseStudentScoreTb tb : tbList) {
//                                    TCourseStudentScoreTb tCourseStudentScoreTb = new TCourseStudentScoreTb();
//                                    tCourseStudentScoreTb.setFkeyid(tb.getFkeyid());
//                                    tCourseStudentScoreTb.setFcontent(value);
//                                    assert zongfen != null;
//                                    tCourseStudentScoreTb.setFscore(Float.parseFloat(zongfen.toString()));
//                                    tCourseStudentScoreTb.setFudate(new Date());
//                                    tCourseStudentScoreTb.setFuid(Long.parseLong(uid));
//                                    tCourseStudentScoreTBService.update(tCourseStudentScoreTb);
//                                }
//                            } else {
//                                //不存在新增
//                                TCourseStudentScoreTb tCourseStudentScoreTb = new TCourseStudentScoreTb();
//                                tCourseStudentScoreTb.setFcourseid(tCourseStudent.getFcourseid());
//                                tCourseStudentScoreTb.setFcoursestudentid(key);
//                                tCourseStudentScoreTb.setFacamid(camid);
//                                tCourseStudentScoreTb.setFabilitylevelid(tCourseAbility.getFabilitylevelid());
//                                tCourseStudentScoreTb.setFconditionid(conditionAssessmentMethod.getFconditionid());
//                                tCourseStudentScoreTb.setFcoursestandardsid(coursestandardsid);
//                                tCourseStudentScoreTb.setFcontent(value);
//                                assert zongfen != null;
//                                tCourseStudentScoreTb.setFscore(Float.parseFloat(zongfen.toString()));
//                                tCourseStudentScoreTb.setFcdate(new Date());
//                                tCourseStudentScoreTb.setFcid(Long.parseLong(uid));
//                                tCourseStudentScoreTBService.save(tCourseStudentScoreTb);
//                            }
//                        }
//
//                        break;
//                    } else {
//                        object.put("value", 0);
//                    }
//                }
//
//
//            } else {
//                object.put("value", 0);
//            }
//
//            TCourseStudentScoreTbExample tCourseStudentScoreTbExample = new TCourseStudentScoreTbExample();
//            TCourseStudentScoreTbExample.Criteria criteria = tCourseStudentScoreTbExample.createCriteria();
//            criteria.andFcoursestudentidEqualTo(key);
//            criteria.andFconditionidEqualTo(conditionAssessmentMethod.getFconditionid());
//            zongfen2 = 0;
//            List<TCourseStudentScoreTb> scoreTbList = tCourseStudentScoreTBService.findByExample(tCourseStudentScoreTbExample);
//            if (scoreTbList.size() > 0) {
//                for (TCourseStudentScoreTb tCourseStudentScoreTb : scoreTbList) {
//                    zongfen2 += tCourseStudentScoreTb.getFscore();
//                }
//            }
//
////            System.out.println(object.toString());
//            // 返回值
//            object.put("zongfen2", String.format("%.2f", zongfen2));
//            object.put("result", "success");
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }

    /**
     * 根据填报内容 获取分值
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/gettianbaofenzhi", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String gettianbaofenzhi(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FCourseStudent = jsonParam.getString("FCourseStudent");
        String fcamid = jsonParam.getString("fcamid").replace("scheme-design_", "");
        String value = jsonParam.getString("value");
        try {
            String CookiesLoginStrucDID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginStrucDID != null && !CookiesLoginStrucDID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginStrucDID);
            }
            FCourseStudent = FCourseStudent == null ? "0" : ParamTools.getdeParam(FCourseStudent);
            fcamid = fcamid == null ? "0" : ParamTools.getdeParam(fcamid);
            long key = Long.parseLong(FCourseStudent);//学生课程报名ID
            long camid = Long.parseLong(fcamid);//考核方式ID
            TCourseStudent tCourseStudent = tCourseStudentService.findById(key);
            TCourseAbilityExample tCourseAbilityExample = new TCourseAbilityExample();
            tCourseAbilityExample.createCriteria().andFcourseidEqualTo(tCourseStudent.getFcourseid());
            List<TCourseAbility> abilities = tCourseAbilityService.findByExample(tCourseAbilityExample);
            TAbilityTree conditionAssessmentMethod = tAbilityTreeService.findById(camid);
            float weight = conditionAssessmentMethod.getFmethodweight() / 100; //权重
            BigDecimal weightFloat = BigDecimal.valueOf(weight);
            BigDecimal zongfen = null;
            float zongfen2 = 0.0f;
            Long coursestandardsid = 0l;

            BigDecimal FContentScore = BigDecimal.valueOf(0);
            if (abilities.size() > 0) {
                for (TCourseAbility tCourseAbility : abilities) {
                    TCourseStandardExample tCourseStandardExample = new TCourseStandardExample();
                    tCourseStandardExample.createCriteria()
                            .andFabilitycamidEqualTo(conditionAssessmentMethod.getFpid())
                            .andFcourseidEqualTo(tCourseStudent.getFcourseid())
                            .andFabilityidEqualTo(tCourseAbility.getFabilityid())
                            .andFabilitylevelidEqualTo(tCourseAbility.getFabilitylevelid())
                            .andFcourseabilityidEqualTo(tCourseAbility.getFkeyid());
                    List<TCourseStandard> tCourseStandards = tcoursestandardService.findByExample(tCourseStandardExample);
                    if (tCourseStandards.size() > 0) {
                        for (TCourseStandard tCourseStandard : tCourseStandards) {
                            TCourseStandardSExample tCourseStandardSExample = new TCourseStandardSExample();
                            tCourseStandardSExample.createCriteria().andFcsidEqualTo(tCourseStandard.getFkeyid()).andFqztypeEqualTo(tCourseStandard.getFqztype());
                            List<TCourseStandardS> standardS = tCourseStandardSService.findByExample(tCourseStandardSExample);
                            if (standardS.size() > 0) {
                                for (TCourseStandardS tCourseStandardS : standardS) {
//                            if (result < 0) {
//                                System.out.println("bigDecimalValue 小于 floatValue");
//                            } else if (result == 0) {
//                                System.out.println("bigDecimalValue 等于 floatValue");
//                            } else {
//                                System.out.println("bigDecimalValue 大于 floatValue");
//                            }

                                    if (tCourseStandard.getFqztype() == 0 || tCourseStandard.getFqztype() == 2) {

                                        if (tCourseStandardS.getFsvalue().equals(value)) {
                                            zongfen = tCourseStandardS.getFvalueul().multiply(weightFloat);
                                            object.put("value", tCourseStandardS.getFvalueul().multiply(weightFloat));
                                            coursestandardsid = tCourseStandardS.getFkeyid();
                                            FContentScore = tCourseStandardS.getFvalueul();
                                            break;
                                        } else {
                                            zongfen = BigDecimal.valueOf(0);
                                            FContentScore = BigDecimal.valueOf(0);
                                            object.put("value", 0);
                                        }
                                    } else if (tCourseStandard.getFqztype() == 1) {
                                        BigDecimal bigDecimalFromFloat = null;
                                        float values = 0;
                                        if (value != null && !value.equals("")) {
                                            values = Float.parseFloat(value);
                                            bigDecimalFromFloat = BigDecimal.valueOf(values);
                                        } else {
                                            value = "";
                                        }
                                        if (!value.equals("")) {
                                            // 使用 compareTo 方法比较两个 BigDecimal 对象
                                            int result = tCourseStandardS.getFvaluell().compareTo(bigDecimalFromFloat);
                                            int result2 = tCourseStandardS.getFvalueul().compareTo(bigDecimalFromFloat);
                                            if (result <= 0 && result2 > 0) {
                                                zongfen = BigDecimal.valueOf(Double.parseDouble(tCourseStandardS.getFsvalue())).multiply(weightFloat);
                                                object.put("value", zongfen);
                                                coursestandardsid = tCourseStandardS.getFkeyid();
                                                FContentScore = BigDecimal.valueOf(Double.parseDouble(tCourseStandardS.getFsvalue()));
                                                break;
                                            } else {
                                                zongfen = BigDecimal.valueOf(0);
                                                FContentScore = BigDecimal.valueOf(0);
                                                object.put("value", 0);
                                            }
                                        } else {
                                            zongfen = BigDecimal.valueOf(0);
                                            FContentScore = BigDecimal.valueOf(0);

                                            object.put("value", 0);
                                        }
                                    } else if (tCourseStandard.getFqztype() == 3) {
                                        coursestandardsid = tCourseStandardS.getFkeyid();
                                        zongfen = BigDecimal.valueOf(Double.parseDouble(value)).multiply(weightFloat);
                                        FContentScore = BigDecimal.valueOf(Double.parseDouble(value));

//                                        zongfen = BigDecimal.valueOf(Double.parseDouble(value));
                                        object.put("value", zongfen);
                                    }
                                }
                            }
                        }

                        TCourseStudentScoreTbTreeExample tCourseStudentScoreTbTreeExample = new TCourseStudentScoreTbTreeExample();
                        TCourseStudentScoreTbTreeExample.Criteria criteria = tCourseStudentScoreTbTreeExample.createCriteria();
                        criteria.andFcoursestudentidEqualTo(key);
                        criteria.andFabilitytreeidEqualTo(camid);
                        List<TCourseStudentScoreTbTree> studentScoreTbTrees = tCourseStudentScoreTBTreeService.findByExample(tCourseStudentScoreTbTreeExample);
                        if (studentScoreTbTrees.size() > 0) {
                            //存在则修改 内容和得分
                            for (TCourseStudentScoreTbTree tb : studentScoreTbTrees) {
                                TCourseStudentScoreTbTree tCourseStudentScoreTb = new TCourseStudentScoreTbTree();
                                tCourseStudentScoreTb.setFkeyid(tb.getFkeyid());
                                tCourseStudentScoreTb.setFcontent(value);
                                tCourseStudentScoreTb.setFcontentscore(FContentScore.floatValue());
                                assert zongfen != null;
                                tCourseStudentScoreTb.setFscore(zongfen.floatValue());
                                tCourseStudentScoreTb.setFjsscore(zongfen.floatValue());
                                tCourseStudentScoreTb.setFudate(new Date());
                                tCourseStudentScoreTb.setFuid(Long.parseLong(uid));
                                tCourseStudentScoreTBTreeService.update(tCourseStudentScoreTb);
                            }
                        }
                        break;
                    } else {
                        object.put("value", 0);
                    }
                }


            } else {
                object.put("value", 0);
            }

            TCourseStudentScoreTbTreeExample tCourseStudentScoreTbTreeExample = new TCourseStudentScoreTbTreeExample();
            TCourseStudentScoreTbTreeExample.Criteria criteria = tCourseStudentScoreTbTreeExample.createCriteria();
            criteria.andFcoursestudentidEqualTo(key);
            criteria.andFpidEqualTo(conditionAssessmentMethod.getFpid());
            List<TCourseStudentScoreTbTree> scoreTbTreeList = tCourseStudentScoreTBTreeService.findByExample(tCourseStudentScoreTbTreeExample);
            zongfen2 = 0;
            if (scoreTbTreeList.size() > 0) {
                for (TCourseStudentScoreTbTree tb : scoreTbTreeList) {
                    zongfen2 += tb.getFscore();
                }
            }
            // 返回值
            object.put("zongfen2", String.format("%.2f", zongfen2));
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 根据ID获取填报的信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycoursestudenttbInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycoursestudenttbInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");//课程报名ID
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询能力信息
            TCourseStudentScoreTbTreeExample tCourseStudentScoreTbTreeExample = new TCourseStudentScoreTbTreeExample();
            TCourseStudentScoreTbTreeExample.Criteria criteria = tCourseStudentScoreTbTreeExample.createCriteria();
            criteria.andFcoursestudentidEqualTo(key);
            List<TCourseStudentScoreTbTree> studentScoreTbTreeList = tCourseStudentScoreTBTreeService.findByExample(tCourseStudentScoreTbTreeExample);
            JSONArray tb_array = new JSONArray();
            for (TCourseStudentScoreTbTree tCourseStudentScoreTb : studentScoreTbTreeList) {
                JSONObject tb_object = new JSONObject();
                if (tCourseStudentScoreTb.getFnodetype() == 3) {
                    tb_object.put("key", ParamTools.getEnParam(tCourseStudentScoreTb.getFkeyid().toString()));
                    tb_object.put("FACAMID", ParamTools.getEnParam(tCourseStudentScoreTb.getFabilitytreeid().toString()));
                    tb_object.put("FConditionID", ParamTools.getEnParam(tCourseStudentScoreTb.getFabilitytreeid().toString()));
                    tb_object.put("FContent", tCourseStudentScoreTb.getFcontent() == null ? "" : tCourseStudentScoreTb.getFcontent());
                    tb_object.put("FScore", tCourseStudentScoreTb.getFscore() == null ? 0 : String.format("%.2f", tCourseStudentScoreTb.getFscore()));
                    tb_array.add(tb_object);
                } else {
                    tb_object.put("key", ParamTools.getEnParam(tCourseStudentScoreTb.getFkeyid().toString()));
                    tb_object.put("FACAMID", ParamTools.getEnParam(tCourseStudentScoreTb.getFabilitytreeid().toString()));
                    tb_object.put("FConditionID", ParamTools.getEnParam(tCourseStudentScoreTb.getFpid().toString()));
                    tb_object.put("FContent", tCourseStudentScoreTb.getFcontent() == null ? "" : tCourseStudentScoreTb.getFcontent());
                    tb_object.put("FScore", tCourseStudentScoreTb.getFscore() == null ? 0 : String.format("%.2f", tCourseStudentScoreTb.getFscore()));
                    tb_array.add(tb_object);
                }
            }
            // 返回值
            object.put("list", tb_array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


//    /**
//     * 提交填报
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/tianbao", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String tianbao(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String inputs = jsonParam.getString("inputs");//
//        String FCourseStudent = jsonParam.getString("FCourseStudent");//
//        try {
//            System.out.println(inputs);
//            System.out.println(FCourseStudent);
//            FCourseStudent = FCourseStudent == null ? "0" : ParamTools.getdeParam(FCourseStudent);
//            Map<Long,Integer> conditiontypemap = new HashMap<>();
//            TCourseStudent courseStudent = tCourseStudentService.findById(Long.valueOf(FCourseStudent));
//            if (courseStudent != null) {
//                Long fcourseid = courseStudent.getFcourseid();//课程ID
//                //课程能力
//                TCourseAbilityExample tCourseAbilityExample = new TCourseAbilityExample();
//                tCourseAbilityExample.createCriteria().andFcourseidEqualTo(fcourseid);
//                tCourseAbilityExample.setOrderByClause("FOrder");
//                List<TCourseAbility> tCourseAbilityList = tCourseAbilityService.findByExample(tCourseAbilityExample);
//                List<Long> ConditionIDList = new ArrayList<Long>();
//                List<Long> camIDList = new ArrayList<Long>();
//                List<Integer> typeIDList = new ArrayList<Integer>();
//                List<Long> coursestandardIDList = new ArrayList<>();
//                JSONArray input_Array = JSONArray.parseArray(inputs);
//                System.out.println(inputs);
//                for (int i = 0; i < input_Array.size(); i++) {
//                    JSONObject jsonObject = input_Array.getJSONObject(i);
//                    String tjid = jsonObject.getString("tjid").replace("tj_", "");//考评条件ID
//                    int tjtype = Integer.parseInt(jsonObject.getString("tjtype").replace("tjtype_", ""));//考核条件类型
//                    String id = jsonObject.getString("id").replace("scheme-design_", "");//考核方式ID
//                    String coursestandardsID = jsonObject.getString("coursestandardsID").replace("standardID_", "");//标准设置子表ID
//                    tjid = ParamTools.getdeParam(tjid);
//                    id = ParamTools.getdeParam(id);
//                    coursestandardsID = ParamTools.getdeParam(coursestandardsID);
//                    if (!ConditionIDList.contains(Long.parseLong(tjid))) {
//                        ConditionIDList.add(Long.parseLong(tjid));
//                        typeIDList.add(tjtype);
//                        conditiontypemap.put(Long.parseLong(tjid), tjtype);
//                        coursestandardIDList.add(Long.parseLong(coursestandardsID));
//                    }
//                    camIDList.add(Long.parseLong(id));
//                }
//                Map<Long, Float> abilitymap = new HashMap<Long, Float>();//存储每个能力的值或计算后的值
//                Map<Long, Float> conditionmap = new HashMap<Long, Float>();//存储每个条件的值或计算后的值
//                TAbilityCondition abilityCondition = null;
//                TAbilityConditionS tAbilityConditionS = null;
//                float tjqz = 0;
//                Long l = 0l;
//                Long conditionFkeyid = 0l;
//                float score = 0;
//                String conditionname = "";
//                for (TCourseAbility tCourseAbility : tCourseAbilityList) {
//                    float tFmethodweight = tCourseAbility.getFmethodweight() == null ? 0 : tCourseAbility.getFmethodweight() / 100; //课程能力的权重
//                    Long fabilityid = tCourseAbility.getFabilityid();
//                    Long fabilitylevelid = tCourseAbility.getFabilitylevelid();
//                    TAbility tAbility = tAbilityService.findById(fabilityid);
//                    TAbilityLevel tAbilityLevel = tAbilityLevelService.findById(fabilitylevelid);
//
//                    conditionmap = new HashMap<Long, Float>();//存储每个条件的值或计算后的值
//                    for (int i = 0; i < ConditionIDList.size(); i++) {
//                        tjqz = 0;
//                        l = ConditionIDList.get(i);
//                        conditionname = "";
//                        score = 0;
//                        //获取考核条件的权重
//                        if (typeIDList.get(i) == 1) {
//                            abilityCondition = tAbilityConditionService.findById(l);
//                            tjqz = abilityCondition.getFmethodweight() == null ? 0 : abilityCondition.getFmethodweight() / 100;
//                            conditionFkeyid = abilityCondition.getFkeyid();
//                            conditionname = abilityCondition.getFconditionname();
//
//                        } else {
//                            tAbilityConditionS = tAbilityConditionSService.findById(l);
//                            tjqz = tAbilityConditionS.getFmethodweight() == null ? 0 : tAbilityConditionS.getFmethodweight() / 100;
//                            conditionFkeyid = tAbilityConditionS.getFkeyid();
//                            conditionname = tAbilityConditionS.getFname();
//
//                        }
//                        TCourseStudentScoreTbExample tCourseStudentScoreTbExample = new TCourseStudentScoreTbExample();
//                        TCourseStudentScoreTbExample.Criteria criteria = tCourseStudentScoreTbExample.createCriteria();
//                        criteria.andFcourseidEqualTo(fcourseid);
//                        criteria.andFcoursestudentidEqualTo(Long.valueOf(FCourseStudent));
//                        criteria.andFabilitylevelidEqualTo(tCourseAbility.getFabilitylevelid());
//                        criteria.andFconditionidEqualTo(conditionFkeyid);
//                        List<TCourseStudentScoreTb> courseStudentScoreTbList = tCourseStudentScoreTBService.findByExample(tCourseStudentScoreTbExample);
//                        if (courseStudentScoreTbList.size() > 0) {
//                            for (TCourseStudentScoreTb tCourseStudentScoreTb : courseStudentScoreTbList) {
//                                score += tCourseStudentScoreTb.getFscore();
//                            }
////                            System.out.println("条件名称：" + conditionname + " :" + score + "权重：" + tjqz);
//                            System.out.println("条件名称：" + conditionname + " :" + score * tjqz + "，值：" + score + "权重：" + tjqz);
//                            conditionmap.put(conditionFkeyid, score * tjqz);
//                        }
//                    }
//                    if (!conditionmap.isEmpty()) {
//                        Float value = 0f;
////                        for(Map.Entry<Long,Float> entry:conditionmap.entrySet()){
////                            value += entry.getValue();
////                        }
////                        System.out.println("能力：" +tAbility.getFname() +"/" +tAbilityLevel.getFname() +" :" + (float) (value * tFmethodweight) +"权重：" + tFmethodweight);
////                        BigDecimal bd = BigDecimal.valueOf((float) (value * tFmethodweight));
////                        bd = bd.setScale(2, RoundingMode.HALF_UP);
////
////                        // 3. 将结果转回 float（注意：float 精度可能损失）
////                        float result = bd.floatValue();
////                        abilitymap.put(tCourseAbility.getFkeyid(), result);
//
//                         for (Map.Entry<Long, Float> entry : conditionmap.entrySet()) {
//                             if(conditiontypemap.get(entry.getKey()) == 1){
//                                 value += entry.getValue();
//                             }else{
//                                 Float value2= 0f ;
//                                 TAbilityConditionS conditionS = tAbilityConditionSService.findById(entry.getKey());
//                                 TAbilityConditionSExample tAbilityConditionSExample = new TAbilityConditionSExample();
//                                 TAbilityConditionSExample.Criteria criteria = tAbilityConditionSExample.createCriteria();
//                                 criteria.andFkeyidEqualTo(conditionS.getFpid());
//                                 List<TAbilityConditionS> conditionSList = tAbilityConditionSService.findByExample(tAbilityConditionSExample);
//                                 if (conditionSList.size() > 0) {
//                                     for (TAbilityConditionS tAbilityConditionS1 : conditionSList) {
//                                         System.out.println("entry.getValue():" + entry.getValue());
//                                         System.out.println("entry.getValue() * (tAbilityConditionS1.getFmethodweight()/ 100):" +entry.getValue() * (tAbilityConditionS1.getFmethodweight()/ 100));
//                                         System.out.println("(tAbilityConditionS1.getFmethodweight()/ 100):" + (tAbilityConditionS1.getFmethodweight()/ 100));
//
//                                         value += entry.getValue() * (tAbilityConditionS1.getFmethodweight()/ 100);
//                                         System.out.println("value():" + value);
//
////                                         if (tAbilityConditionS1.getFisleaf() == 1){
////
////                                         }
//                                     }
//                                 }
//                             }
//
//                         }
//                        System.out.println("能力：" +tAbility.getFname() +"/" +tAbilityLevel.getFname() +" :" + (float) (value * tFmethodweight) +"权重：" + tFmethodweight);
//                        BigDecimal bd = BigDecimal.valueOf((float) (value * tFmethodweight));
//                        bd = bd.setScale(2, RoundingMode.HALF_UP);
//                        // 3. 将结果转回 float（注意：float 精度可能损失）
//                        float result = bd.floatValue();
//                        abilitymap.put(tCourseAbility.getFkeyid(), result);
//                    }
//                }
//                if (!abilitymap.isEmpty()) {
//                    Float aFloat = 0f;
//
//                    for (Map.Entry<Long, Float> entry : abilitymap.entrySet()) {
//                        aFloat += entry.getValue();
//                    }
//                    System.out.println("学生最终成绩：" + aFloat);
//                }
//                object.put("result", "success");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }


    /**
     * 提交填报
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/tianbao", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String tianbao(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
//        String inputs = jsonParam.getString("inputs");//
        String FCourseStudent = jsonParam.getString("FCourseStudent");//
        try {
            int FGroupNum = 0;
            FCourseStudent = FCourseStudent == null ? "0" : ParamTools.getdeParam(FCourseStudent);
            TCourseStudent courseStudent = tCourseStudentService.findById(Long.valueOf(FCourseStudent));
            if (courseStudent != null) {
                String CookiesLoginStrucDID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginStrucDID != null && !CookiesLoginStrucDID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginStrucDID);
                }
                Long fcourseid = courseStudent.getFcourseid();//课程ID
                //课程能力
                TCourseAbilityExample tCourseAbilityExample = new TCourseAbilityExample();
                tCourseAbilityExample.createCriteria().andFcourseidEqualTo(fcourseid);
                tCourseAbilityExample.setOrderByClause("FOrder");
                List<TCourseAbility> tCourseAbilityList = tCourseAbilityService.findByExample(tCourseAbilityExample);
                Map<Long, Float> courseabilitymap = new HashMap<>(); //课程绑定能力，存储课程能力ID加权重
                if (tCourseAbilityList.size() > 0) {
                    for (TCourseAbility tCourseAbility : tCourseAbilityList) {
                        courseabilitymap.put(tCourseAbility.getFkeyid(), tCourseAbility.getFmethodweight() / 100);
                    }

                    //查询最新分组结果
                    TCourseStudentScoreTbTreeExample groupbynumExample = new TCourseStudentScoreTbTreeExample();
                    groupbynumExample.createCriteria().andFstateEqualTo(1);
                    groupbynumExample.setOrderByClause("FGroypNum desc ");
                    List<TCourseStudentScoreTbTree> groupbynumlist = tCourseStudentScoreTBTreeService.findByExample(groupbynumExample);
                    if(groupbynumlist.size() > 0){
                        FGroupNum = groupbynumlist.get(0).getFgroypnum();
                    }
                    Float fkcscore=0.0f;//学生课程最终成绩
                    for (Map.Entry<Long, Float> entry : courseabilitymap.entrySet()) {
                        Float courseabiliytv = entry.getValue();
                        Float fzscore=0.0f;//能力最终成绩
                        //循环出能力ID， 根据能力ID查找，下面所有的考核方式
                        TCourseAbility tCourseAbility = tCourseAbilityService.findById(entry.getKey());
                        Long fabilitylevelid = tCourseAbility.getFabilitylevelid();
                        Map<Long, Float> tiaojianmap = new HashMap<>();

                        //先找当前能力等级下的条件节点
                        TCourseStudentScoreTbTreeExample tCourseStudentScoreTbTreeExample = new TCourseStudentScoreTbTreeExample();
                        TCourseStudentScoreTbTreeExample.Criteria criteria = tCourseStudentScoreTbTreeExample.createCriteria();
                        criteria.andFcoursestudentidEqualTo(Long.parseLong(FCourseStudent));
                        criteria.andFpidEqualTo(fabilitylevelid);
                        criteria.andFgroypnumEqualTo(FGroupNum);
                        criteria.andFstateEqualTo(1);
                        List<TCourseStudentScoreTbTree> tCourseStudentScoreTbTreeList = tCourseStudentScoreTBTreeService.findByExample(tCourseStudentScoreTbTreeExample);
                        if (tCourseStudentScoreTbTreeList.size() > 0) {
                            for (TCourseStudentScoreTbTree tCourseStudentScoreTbTree : tCourseStudentScoreTbTreeList) {
                                tiaojianmap.put(tCourseStudentScoreTbTree.getFabilitytreeid(), tCourseStudentScoreTbTree.getFmethodweight() / 100);
                            }
                        }
                        if (tiaojianmap.size() > 0) {
                            //查找带有考核方式的节点， 往上推累加分数，如果上一级 不是map中的内容，则继续向上累加分数

                            //先查出所有考核方式
                            TCourseStudentScoreTbTreeExample tCourseStudentScoreTbTreeExample2 = new TCourseStudentScoreTbTreeExample();
                            TCourseStudentScoreTbTreeExample.Criteria criteria2 = tCourseStudentScoreTbTreeExample2.createCriteria();
                            criteria2.andFcoursestudentidEqualTo(Long.parseLong(FCourseStudent));
                            criteria2.andFabilitylevelidEqualTo(fabilitylevelid);
                            criteria2.andFnodetypeEqualTo(4);
                            criteria2.andFgroypnumEqualTo(FGroupNum);
                            criteria2.andFstateEqualTo(1);
                            List<TCourseStudentScoreTbTree> tCourseStudentScoreTbTreeList2 = tCourseStudentScoreTBTreeService.findByExample(tCourseStudentScoreTbTreeExample2);
                            for (int i = 0; i < tCourseStudentScoreTbTreeList2.size(); i++) {
                                if (!tiaojianmap.containsKey(tCourseStudentScoreTbTreeList2.get(i).getFpid())) {
                                    TCourseStudentScoreTbTreeExample tCourseStudentScoreTbTreeExample3 = new TCourseStudentScoreTbTreeExample();
                                    TCourseStudentScoreTbTreeExample.Criteria criteria3 = tCourseStudentScoreTbTreeExample3.createCriteria();
                                    criteria3.andFcoursestudentidEqualTo(Long.parseLong(FCourseStudent));
                                    criteria3.andFabilitylevelidEqualTo(fabilitylevelid);
                                    criteria3.andFabilitytreeidEqualTo(tCourseStudentScoreTbTreeList2.get(i).getFpid());
                                    criteria3.andFgroypnumEqualTo(FGroupNum);
                                    criteria3.andFstateEqualTo(1);
                                    List<TCourseStudentScoreTbTree> tCourseStudentScoreTbTreeList3 = tCourseStudentScoreTBTreeService.findByExample(tCourseStudentScoreTbTreeExample3);
                                    for (TCourseStudentScoreTbTree tCourseStudentScoreTbTree : tCourseStudentScoreTbTreeList3) {
                                        Float v = tCourseStudentScoreTbTree.getFmethodweight() / 100; //条件的权重
                                        Float fscore = v * tCourseStudentScoreTbTreeList2.get(i).getFscore();
                                        //给父级 修改得分
                                        TCourseStudentScoreTbTree tCourseStudentScoreTbTreein = new TCourseStudentScoreTbTree();
                                        tCourseStudentScoreTbTreein.setFkeyid(tCourseStudentScoreTbTree.getFkeyid());
                                        tCourseStudentScoreTbTreein.setFjsscore(tCourseStudentScoreTbTree.getFjsscore() + fscore);
                                        tCourseStudentScoreTbTreein.setFscore(tCourseStudentScoreTbTree.getFjsscore() + fscore);
                                        tCourseStudentScoreTbTreein.setFuid(Long.parseLong(uid));
                                        tCourseStudentScoreTbTreein.setFudate(new Date());
                                        tCourseStudentScoreTBTreeService.update(tCourseStudentScoreTbTreein);

                                        //继续向上查找 并赋值得分
                                        this.UpJqJSScore(tCourseStudentScoreTbTree.getFkeyid(), tCourseStudentScoreTbTree.getFcoursestudentid(), tCourseStudentScoreTbTree.getFabilitylevelid(), tiaojianmap, Long.parseLong(uid),FGroupNum);
                                    }
                                } else {
                                    //查出考核方式的父级
                                    TCourseStudentScoreTbTreeExample tCourseStudentScoreTbTreeExample3 = new TCourseStudentScoreTbTreeExample();
                                    TCourseStudentScoreTbTreeExample.Criteria criteria3 = tCourseStudentScoreTbTreeExample3.createCriteria();
                                    criteria3.andFcoursestudentidEqualTo(Long.parseLong(FCourseStudent));
                                    criteria3.andFabilitylevelidEqualTo(fabilitylevelid);
                                    criteria3.andFabilitytreeidEqualTo(tCourseStudentScoreTbTreeList2.get(i).getFpid());
                                    criteria3.andFgroypnumEqualTo(FGroupNum);
                                    criteria3.andFstateEqualTo(1);
                                    List<TCourseStudentScoreTbTree> tCourseStudentScoreTbTreeList3 = tCourseStudentScoreTBTreeService.findByExample(tCourseStudentScoreTbTreeExample3);
                                    Float v = tiaojianmap.get(tCourseStudentScoreTbTreeList2.get(i).getFpid());//条件的权重
                                    Float fscore = v * tCourseStudentScoreTbTreeList2.get(i).getFscore();
                                    for (TCourseStudentScoreTbTree tCourseStudentScoreTbTree : tCourseStudentScoreTbTreeList3) {
                                        //给父级 修改得分
                                        TCourseStudentScoreTbTree tCourseStudentScoreTbTreein = new TCourseStudentScoreTbTree();
                                        tCourseStudentScoreTbTreein.setFkeyid(tCourseStudentScoreTbTree.getFkeyid());
                                        tCourseStudentScoreTbTreein.setFjsscore((tCourseStudentScoreTbTree.getFjsscore() + fscore));
                                        tCourseStudentScoreTbTreein.setFscore((tCourseStudentScoreTbTree.getFjsscore() + fscore));
                                        tCourseStudentScoreTbTreein.setFuid(Long.parseLong(uid));
                                        tCourseStudentScoreTbTreein.setFudate(new Date());
                                        tCourseStudentScoreTBTreeService.update(tCourseStudentScoreTbTreein);


                                    }
                                }
                            }

                        }

                        TCourseStudentScoreTbTreeExample tCourseStudentScoreTbTreeExample1 = new TCourseStudentScoreTbTreeExample();
                        TCourseStudentScoreTbTreeExample.Criteria criteria1 = tCourseStudentScoreTbTreeExample1.createCriteria();
                        criteria1.andFcoursestudentidEqualTo(Long.parseLong(FCourseStudent));
                        criteria1.andFabilitylevelidEqualTo(fabilitylevelid);
                        criteria1.andFpidEqualTo(fabilitylevelid);
                        criteria1.andFstateEqualTo(1);
                        criteria1.andFgroypnumEqualTo(FGroupNum);
                        List<TCourseStudentScoreTbTree> tbTreeList = tCourseStudentScoreTBTreeService.findByExample(tCourseStudentScoreTbTreeExample1);
                        if(tbTreeList.size() > 0 ){
                            for (TCourseStudentScoreTbTree tCourseStudentScoreTbTree : tbTreeList) {
                                fzscore += tCourseStudentScoreTbTree.getFjsscore();
                            }
                        }
                        //打印能力的总成绩
                        TAbilityTree abilityTreelevel = tAbilityTreeService.findById(fabilitylevelid);
                        TAbilityTree abilityTreenl = tAbilityTreeService.findById(abilityTreelevel.getFpid());
                        System.out.println("能力：" +abilityTreenl.getFname() + "/" + abilityTreelevel.getFname() +"最终成绩：" +fzscore);
                        fkcscore += fzscore * courseabiliytv;
                    }
                    System.out.println("当前学生报名课程总成绩为：" + fkcscore);
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    //向上加权计算
    private void UpJqJSScore(Long FAbilitytreeID, Long FCourseStudent, Long fabilitylevelid, Map<Long, Float> tiaojianmap, Long uid,int FGroupNum) {
        try {
            TCourseStudentScoreTbTree scoreTbTree = tCourseStudentScoreTBTreeService.findById(FAbilitytreeID);//传过来的节点信息 ，需要分数
            Float fscore = scoreTbTree.getFjsscore();
            //查找它的父级
            TCourseStudentScoreTbTreeExample tCourseStudentScoreTbTreeExample3 = new TCourseStudentScoreTbTreeExample();
            TCourseStudentScoreTbTreeExample.Criteria criteria3 = tCourseStudentScoreTbTreeExample3.createCriteria();
            criteria3.andFcoursestudentidEqualTo(FCourseStudent);
            criteria3.andFabilitylevelidEqualTo(fabilitylevelid);
            criteria3.andFabilitytreeidEqualTo(scoreTbTree.getFpid());
            criteria3.andFgroypnumEqualTo(FGroupNum);
            criteria3.andFstateEqualTo(1);
            List<TCourseStudentScoreTbTree> tCourseStudentScoreTbTreeList3 = tCourseStudentScoreTBTreeService.findByExample(tCourseStudentScoreTbTreeExample3);
            if (tCourseStudentScoreTbTreeList3.size() > 0) {
                for (TCourseStudentScoreTbTree tCourseStudentScoreTbTree : tCourseStudentScoreTbTreeList3) {
                    if (tiaojianmap.containsKey(tCourseStudentScoreTbTree.getFpid())) {
                        //存在
                        TCourseStudentScoreTbTreeExample tCourseStudentScoreTbTreeExample4 = new TCourseStudentScoreTbTreeExample();
                        TCourseStudentScoreTbTreeExample.Criteria criteria4 = tCourseStudentScoreTbTreeExample4.createCriteria();
                        criteria4.andFcoursestudentidEqualTo(FCourseStudent);
                        criteria4.andFabilitylevelidEqualTo(fabilitylevelid);
                        criteria4.andFabilitytreeidEqualTo(tCourseStudentScoreTbTree.getFpid());
                        criteria4.andFgroypnumEqualTo(FGroupNum);
                        List<TCourseStudentScoreTbTree> tCourseStudentScoreTbTreeList4 = tCourseStudentScoreTBTreeService.findByExample(tCourseStudentScoreTbTreeExample4);
                        Float v = tiaojianmap.get(tCourseStudentScoreTbTree.getFpid());//条件的权重
                        Float fscore4 = v * fscore;
                        for (TCourseStudentScoreTbTree tCourseStudentScoreTbTree4 : tCourseStudentScoreTbTreeList4) {
                            //给父级 修改得分
                            TCourseStudentScoreTbTree tCourseStudentScoreTbTreein = new TCourseStudentScoreTbTree();
                            tCourseStudentScoreTbTreein.setFkeyid(tCourseStudentScoreTbTree4.getFkeyid());
                            tCourseStudentScoreTbTreein.setFjsscore(tCourseStudentScoreTbTree4.getFjsscore() + fscore4);
                            tCourseStudentScoreTbTreein.setFscore(tCourseStudentScoreTbTree4.getFjsscore() + fscore4);
                            tCourseStudentScoreTbTreein.setFuid(uid);
                            tCourseStudentScoreTbTreein.setFudate(new Date());
                            tCourseStudentScoreTBTreeService.update(tCourseStudentScoreTbTreein);


                        }
                    } else {
                        //不存在
                        tCourseStudentScoreTbTreeExample3 = new TCourseStudentScoreTbTreeExample();
                        criteria3 = tCourseStudentScoreTbTreeExample3.createCriteria();
                        criteria3.andFcoursestudentidEqualTo(FCourseStudent);
                        criteria3.andFabilitylevelidEqualTo(fabilitylevelid);
                        criteria3.andFabilitytreeidEqualTo(tCourseStudentScoreTbTree.getFpid());
                        criteria3.andFgroypnumEqualTo(FGroupNum);
                        criteria3.andFstateEqualTo(1);
                        tCourseStudentScoreTbTreeList3 = tCourseStudentScoreTBTreeService.findByExample(tCourseStudentScoreTbTreeExample3);
                        for (TCourseStudentScoreTbTree tCourseStudentScoreTbTree4 : tCourseStudentScoreTbTreeList3) {
                            Float v = tCourseStudentScoreTbTree4.getFmethodweight() / 100; //条件的权重
                            Float fscore4 = v * fscore;
                            //给父级 修改得分
                            TCourseStudentScoreTbTree tCourseStudentScoreTbTreein = new TCourseStudentScoreTbTree();
                            tCourseStudentScoreTbTreein.setFkeyid(tCourseStudentScoreTbTree4.getFkeyid());
                            tCourseStudentScoreTbTreein.setFjsscore(tCourseStudentScoreTbTree4.getFjsscore() + fscore4);
                            tCourseStudentScoreTbTreein.setFscore(tCourseStudentScoreTbTree4.getFjsscore() + fscore4);
                            tCourseStudentScoreTbTreein.setFuid(uid);
                            tCourseStudentScoreTbTreein.setFudate(new Date());
                            tCourseStudentScoreTBTreeService.update(tCourseStudentScoreTbTreein);


                            //继续向上查找 并赋值得分
                            this.UpJqJSScore(tCourseStudentScoreTbTree4.getFkeyid(), tCourseStudentScoreTbTree4.getFcoursestudentid(), tCourseStudentScoreTbTree4.getFabilitylevelid(), tiaojianmap, uid,FGroupNum);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}