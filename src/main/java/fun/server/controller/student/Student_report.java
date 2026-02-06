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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 学生管理 相关业务处理
 */
@RestController
@RequestMapping("/s/tstudent_report")
public class Student_report {

    private final TStudentService tStudentService;
    private final TRoleService roleService;
    private final TUserService tUserService;
    private final TPowerRoleUserService powerRoleUserService;
    private final TAbilityService tAbilityService;
    private final TAbilityTypeService tAbilityTypeService;
    private final TAbilityLevelService tAbilityLevelService;
    private final TStudentAbilityService tStudentAbilityService;
    private final TClassStudentService tClassStudentService;
    private final TAbilityTreeService tAbilityTreeService;
    private final TMajorService tMajorService;
    private final TCourseStudentService tCourseStudentService;
    private final TCourseService tCourseService;

    public Student_report(TStudentService tStudentService, TRoleService roleService, TUserService tUserService, TPowerRoleUserService powerRoleUserService, TAbilityService tAbilityService, TAbilityTypeService tAbilityTypeService, TAbilityLevelService tAbilityLevelService, TStudentAbilityService tStudentAbilityService, TClassStudentService tClassStudentService, TMajorService tMajorService, TAbilityTreeService tAbilityTreeService,TCourseStudentService tCourseStudentService,TCourseService tCourseService) {
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
        this.tCourseStudentService = tCourseStudentService;
        this.tCourseService = tCourseService;
    }


    @Value("${studentmb.path}")
    private String studentmb;

    @Value("${studentfj.path}")
    private String studentfj;

   

    /**
     * 获取学生能力画像
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @ResponseBody
//    @RequestMapping(value = "/getstudentabilityreport", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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
//                
//                List<StudentabilityLevelMax> levelMaxList = tStudentAbilityService.getStudentAbilityLevelMax(Long.parseLong(userid));
//                if (levelMaxList.size() > 0) {
//                    for (StudentabilityLevelMax studentabilityLevelMax : levelMaxList) {
//                        JSONObject studentability_object = new JSONObject();
//                        studentability_object.put("key", ParamTools.getEnParam(studentabilityLevelMax.getFKeyID().toString()));
//                        studentability_object.put("FAbilityID", ParamTools.getEnParam(studentabilityLevelMax.getFAbilityID().toString()));
//                        studentability_object.put("FAbilityLevelName", studentabilityLevelMax.getFAbilityLevelName());
//                        studentability_object.put("value", studentabilityLevelMax.getFAbilityLevelName().replaceAll("[\\u4e00-\\u9fa5]","*"));
//                        
//                        studentability_Array.add(studentability_object);
//                    }
//                }
//
//                for (int i = 0; i < abilityTypeList.size(); i++) {
//                    openKeys_Array.add(i);
//                }
////                System.out.println(openKeys_Array);
////                System.out.println(type_Array);
//                object.put("abilityTypes", type_Array);
//                object.put("abilityDetails", ability_Array);
//                object.put("abilityDetails_value", studentability_Array);
//                //object.put("openKeys", openKeys_Array);
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
     * 获取学生能力
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/getstudentabilityreport", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getstudentabilityreport(HttpServletRequest request)
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
               // System.out.println(userid);
                List<StudentabilityLevelMax> levelMaxList = tStudentAbilityService.getStudentAbilityLevelMax(Long.parseLong(userid));
                if (levelMaxList.size() > 0) {
                    for (StudentabilityLevelMax studentabilityLevelMax : levelMaxList) {
                        JSONObject studentability_object = new JSONObject();
                        studentability_object.put("key", ParamTools.getEnParam(studentabilityLevelMax.getFKeyID().toString()));
                        studentability_object.put("FAbilityID", ParamTools.getEnParam(studentabilityLevelMax.getFAbilityID().toString()));
                        studentability_object.put("FAbilityLevelName", studentabilityLevelMax.getFAbilityLevelName());
                    
                        //studentability_object.put("value", studentabilityLevelMax.getFAbilityLevelName().replaceAll("[\\u4e00-\\u9fa5]",""));
                        //赋值
                        String abilityLevelName = studentabilityLevelMax.getFAbilityLevelName();
                        int abilityLevelValue;
                        if ("1级".equals(abilityLevelName) || "初级".equals(abilityLevelName)) {
                            abilityLevelValue = 1;
                        } else if ("2级".equals(abilityLevelName) || "中级".equals(abilityLevelName)) {
                            abilityLevelValue = 2;
                        } else if ("3级".equals(abilityLevelName) || "高级".equals(abilityLevelName)) {
                            abilityLevelValue = 3;
                        } else {
                            // 可以根据实际情况处理其他情况，这里简单设置为默认值
                            abilityLevelValue = 0;
                        }
                        studentability_object.put("value", abilityLevelValue);
                        
                        studentability_Array.add(studentability_object);
                    }
                }

                for (int i = 0; i < abilityTypeList.size(); i++) {
                    openKeys_Array.add(i);
                }
                
	              object.put("abilityTypes", type_Array);
	              object.put("abilityDetails", ability_Array);
	              object.put("abilityDetails_value", studentability_Array);
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
            if(selectedClassList.size() > 0){
                String classname ="";

                for (TCLassData classData : selectedClassList) {
                    classname  +="【" + classData.getFClassName() + "】，";
                }
                if(classname.length() > 0){
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
     * 根据ID获取学生课程信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytstudentInfo_kc", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytstudentInfo_kc(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询学生报名课程表
            TCourseStudentExample tCourseStudentExample = new TCourseStudentExample();
            TCourseStudentExample.Criteria criteria1 = tCourseStudentExample.createCriteria();
            criteria1.andFstudentidEqualTo(key);
            criteria1.andFstateEqualTo(1);
            
            Long Fcourseid = null;
            List<TCourseStudent> CourseStudentList = tCourseStudentService.findByExample(tCourseStudentExample);
            if (CourseStudentList.size() > 0) {
                for (TCourseStudent courseStudent : CourseStudentList) {
                	Fcourseid=courseStudent.getFcourseid();
                }
            }
            JSONObject tstudent_object = new JSONObject();
            tstudent_object.put("key", ParamTools.getEnParam(Fcourseid.toString()));
            //获取课程名称
            TCourse tcourse = tCourseService.findById(Fcourseid);
            tstudent_object.put("Fname",  tcourse.getFname());
        

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


}