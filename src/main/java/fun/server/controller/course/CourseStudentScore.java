package fun.server.controller.course;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fun.config.intercepors.actionlog.LogOperation;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 课程报名学生管理 相关业务处理
 */
@RestController
@RequestMapping("/s/coursestudentscore")
public class CourseStudentScore {

    private final TCourseStudentScoreService tCourseStudentScoreService;

    private final TStudentService tStudentService;

    private final TCourseStudentService tCourseStudentService;

    private final TAbilityLevelService tAbilityLevelService;

    private final TStudentAbilityService tStudentAbilityService;

    public CourseStudentScore(TCourseStudentScoreService tCourseStudentScoreService, TStudentService tStudentService, TCourseStudentService tCourseStudentService, TAbilityLevelService tAbilityLevelService, TStudentAbilityService tStudentAbilityService) {
        this.tCourseStudentScoreService = tCourseStudentScoreService;
        this.tStudentService = tStudentService;
        this.tCourseStudentService = tCourseStudentService;
        this.tAbilityLevelService = tAbilityLevelService;
        this.tStudentAbilityService = tStudentAbilityService;
    }


    /**
     * 填写学生成绩
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("填写学生成绩")
    @ResponseBody
    @RequestMapping(value = "/coursestudentscore", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String coursestudentscore(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);

        Integer indexs = 0;//循环次数
        if(jsonParam.getString("indexs") !=null && !jsonParam.getString("indexs").equals(""))
            indexs = jsonParam.getInteger("indexs");
        try {
            if (indexs > 0) {
                String fKeyID = jsonParam.getString("FKeyID");//课程报名ID
                String FCourseID = jsonParam.getString("FCourseID");//课程ID
                String FStudentID = jsonParam.getString("FStudentID");//学生ID
                float totalScores = jsonParam.getFloat("totalScores");//总分
                String Fabilitylevelid = jsonParam.getString("Fabilitylevelid");//课程能力等级ID

                String CookiesLoginusergroupsID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginusergroupsID != null && !CookiesLoginusergroupsID.equals("")) {
                    uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
                }

                fKeyID = fKeyID == null ? "" : ParamTools.getdeParam(fKeyID);
                FCourseID = FCourseID == null ? "" : ParamTools.getdeParam(FCourseID);
                FStudentID = FStudentID == null ? "" : ParamTools.getdeParam(FStudentID);
                Fabilitylevelid = Fabilitylevelid == null ? "" : ParamTools.getdeParam(Fabilitylevelid);

                for (int i = 0; i < indexs; i++) {
                    String abilityassessmentmethodFKeyID = ParamTools.getdeParam(jsonParam.getString("FKeyID" + i));//考核方式ID
                    String FConditionID = jsonParam.getString("FConditionID" + i);//各个条件的ID
                    String FConditionScores = jsonParam.getString("FConditionScores" + i);//各个条件的分数
                    float FContributionWeight = jsonParam.getFloat("FContributionWeight" + i);//考核方式 总权重
                    float Ftotal = jsonParam.getFloat("Ftotal" + i);//条件总分
                    float score = jsonParam.getFloat("score" + i);//输入的成绩

                    //插入成绩
                    TCourseStudentScore tCourseStudentScore = new TCourseStudentScore();
                    tCourseStudentScore.setFcourseid(Long.parseLong(FCourseID));
                    tCourseStudentScore.setFstudentid(Long.parseLong(FStudentID));
                    tCourseStudentScore.setFcoursestudentid(Long.parseLong(fKeyID));
                    tCourseStudentScore.setFcamid(Long.parseLong(abilityassessmentmethodFKeyID));
                    tCourseStudentScore.setFconditionid(FConditionID);
                    tCourseStudentScore.setFzscore(Ftotal);
                    tCourseStudentScore.setFcontributionweight(FContributionWeight);
                    tCourseStudentScore.setFstudentscore(score);
                    tCourseStudentScore.setFcid(Long.parseLong(uid));
                    tCourseStudentScore.setFcdate(new Date());
                    tCourseStudentScoreService.save(tCourseStudentScore);
                }

                //修改学生课程表成绩及状态
                TAbilityLevel tAbilityLevel = tAbilityLevelService.findById(Long.parseLong(Fabilitylevelid));
                //获取合格分数
                BigDecimal decimal = BigDecimal.valueOf(tAbilityLevel.getFscoremin());
                // 将 float 转换为 BigDecimal
                BigDecimal floatAsBigDecimal = new BigDecimal(totalScores);
                // 比较大小
                int result = floatAsBigDecimal.compareTo(decimal);
                TCourseStudent tCourseStudent = new TCourseStudent();
                tCourseStudent.setFkeyid(Long.parseLong(fKeyID));
                tCourseStudent.setFscore(BigDecimal.valueOf(totalScores));
                tCourseStudent.setFuid(Long.parseLong(uid));
                tCourseStudent.setFudate(new Date());
                if (result == 0 || result > 0) {
                    //学生成绩 等于或者大于 合格分数
                    tCourseStudent.setFifpass(1);
                    tCourseStudentService.update(tCourseStudent);
                    // 定义日期格式
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    TStudentAbility tstudentability = new TStudentAbility();
                    tstudentability.setFstudentid(Long.parseLong(FStudentID));
                    tstudentability.setFstate(1);
                    tstudentability.setFmode(1);
                    tstudentability.setFabilityid(tAbilityLevel.getFabilityid());
                    tstudentability.setFabilitylevelid(tAbilityLevel.getFkeyid());
                    tstudentability.setFabilityinf("");
                    tstudentability.setFurl("");
                    tstudentability.setFdate(dateFormat.parse(new Date().toString()));
                    tstudentability.setFcdate(new Date());
                    tstudentability.setFcid(Long.parseLong(uid));
                    tStudentAbilityService.save(tstudentability);


                    //查询当前学生 所拥有的能力 然后算分
                    int studentFPoints = tStudentAbilityService.getStudentFPoints(Long.parseLong(FStudentID));
                    //修改学生表 能力得分
                    TStudent student = new TStudent();
                    student.setFkeyid(Long.parseLong(FStudentID));
                    student.setFpoints(studentFPoints);
                    tStudentService.update(student);
                }else{
                    //学生成绩 小于 合格分数
                    tCourseStudent.setFifpass(2);
                    tCourseStudentService.update(tCourseStudent);
                }
                // 返回值
                object.put("result", "success");
            }else{
                object.put("result", "error");
                object.put("error", "未获取到有效成绩，请刷新后重试！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }



    /**
     *  查看成绩
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/selectCourseStudentScore", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String selectCourseStudentScore(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");//课程报名ID
        String FStudentID = jsonParam.getString("FStudentID");//学生ID
        String FCourseID = jsonParam.getString("FCourseID");//课程ID


        try {
            // 获取数据库记录
            JSONArray coursestudent_Array = new JSONArray();
            // 查询条件

            id= id == null ? "0" : ParamTools.getdeParam(id);
            FStudentID= FStudentID == null ? "0" : ParamTools.getdeParam(FStudentID);
            FCourseID= FCourseID == null ? "0" : ParamTools.getdeParam(FCourseID);
            TCourseStudentScoreExample example = new TCourseStudentScoreExample();
            TCourseStudentScoreExample.Criteria criteria = example.createCriteria();
            criteria.andFcourseidEqualTo(Long.parseLong(FCourseID));
            criteria.andFstudentidEqualTo(Long.parseLong(FStudentID));
            criteria.andFcoursestudentidEqualTo(Long.parseLong(id));
            List<TCourseStudentScore> scoreList = tCourseStudentScoreService.findByExample(example);
            for (TCourseStudentScore tCourseStudentScore : scoreList) {
                JSONObject coursestudent_object = new JSONObject();
                coursestudent_object.put("key",ParamTools.getEnParam(tCourseStudentScore.getFkeyid().toString()));
                coursestudent_object.put("FCAMID",ParamTools.getEnParam(tCourseStudentScore.getFcamid().toString()));
                coursestudent_object.put("FCourseID",ParamTools.getEnParam(tCourseStudentScore.getFcourseid().toString()));
                coursestudent_object.put("FCourseStudentID",ParamTools.getEnParam(tCourseStudentScore.getFcoursestudentid().toString()));
                coursestudent_object.put("FStudentID",ParamTools.getEnParam(tCourseStudentScore.getFstudentid().toString()));
                coursestudent_object.put("FConditionID",ParamTools.getEnParam(tCourseStudentScore.getFconditionid()));
                coursestudent_object.put("FZScore",tCourseStudentScore.getFzscore());
                coursestudent_object.put("FStudentScore",tCourseStudentScore.getFstudentscore());
                coursestudent_object.put("FContributionWeight",tCourseStudentScore.getFcontributionweight());
                coursestudent_Array.add(coursestudent_object);
            }
            // 返回值
            object.put("coursestudentscorelist", coursestudent_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


}