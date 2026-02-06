package fun.server.controller.course;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.model.customQuery.courseabilityCondition.CourseabilityCondtionCS;
import fun.server.model.customQuery.courseabilityCondition.CourseabilityCondtionData;
import fun.server.service.*;
import fun.tools.ParamTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 课程能力信息管理 相关业务处理
 */
@RestController
@RequestMapping("/s/courseabilitycondition")
public class CourseAbilityCondition {

    private final TCourseAbilityConditionService tCourseAbilityConditionService;

    private final TAbilityTreeService tAbilityTreeService;

    private final TCourseService tCourseService;

    private final TAbilityLevelConditionService tAbilityLevelConditionService;

    private final TCourseStandardService tCourseStandardService;

    private final TAbilityAssessmentMethodService tAbilityAssessmentMethodService;

    private final TCourseStandardSService tCourseStandardSService;
    public CourseAbilityCondition(TCourseAbilityConditionService tCourseAbilityConditionService, TAbilityTreeService tAbilityTreeService, TCourseService tCourseService, TAbilityLevelConditionService tAbilityLevelConditionService, TCourseStandardService tCourseStandardService, TAbilityAssessmentMethodService tAbilityAssessmentMethodService, TCourseStandardSService tCourseStandardSService) {
        this.tCourseAbilityConditionService = tCourseAbilityConditionService;
        this.tAbilityTreeService = tAbilityTreeService;
        this.tCourseService = tCourseService;
        this.tAbilityLevelConditionService = tAbilityLevelConditionService;
        this.tCourseStandardService = tCourseStandardService;
        this.tAbilityAssessmentMethodService = tAbilityAssessmentMethodService;
        this.tCourseStandardSService = tCourseStandardSService;
    }


    /**
     * 获取课程能力信息信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycourseabilitycondition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycourseabilitycondition(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String FCourseID = jsonParam.getString("FCourseID");
        Integer dataall = 1;
        try {
            // 获取数据库记录
            JSONArray courseabilitycondition_Array = new JSONArray();
            // 查询条件
            TCourseAbilityConditionExample TCourseAbilityConditionExample = new TCourseAbilityConditionExample();
            TCourseAbilityConditionExample.Criteria criteria = TCourseAbilityConditionExample.createCriteria();

            if (FCourseID != null && !FCourseID.equals("")) {
                FCourseID = ParamTools.getdeParam(FCourseID);
            } else {
                FCourseID = "0";
            }
            criteria.andFcourseidEqualTo(Long.parseLong(FCourseID));
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                TCourseAbilityConditionExample.setOrderByClause(orderSql.substring(1));
            } else {
                TCourseAbilityConditionExample.setOrderByClause("FOrder");
            }
            PageInfo<TCourseAbilityCondition> courseabilityconditionPageInfo = tCourseAbilityConditionService.findByExampleMapper(TCourseAbilityConditionExample, (page - 1) * results, results);
            List<TCourseAbilityCondition> courseabilitycondition_list = courseabilityconditionPageInfo.getList();

            for (TCourseAbilityCondition courseabilitycondition : courseabilitycondition_list) {
                JSONObject courseabilitycondition_object = new JSONObject();
                courseabilitycondition_object.put("key", ParamTools.getEnParam(courseabilitycondition.getFkeyid().toString()));
                courseabilitycondition_object.put("FCourseID", ParamTools.getEnParam(courseabilitycondition.getFcourseid().toString()));
                courseabilitycondition_object.put("FConditionID", ParamTools.getEnParam(courseabilitycondition.getFconditionid().toString()));
                TAbilityLevelCondition tAbilityLevelCondition = tAbilityLevelConditionService.findById(courseabilitycondition.getFconditionid());

                if (dataall == 1) {
                    courseabilitycondition_object.put("FConditionName", tAbilityLevelCondition.getFname());
                    courseabilitycondition_object.put("FOrder", courseabilitycondition.getForder());
                    courseabilitycondition_object.put("FCID", courseabilitycondition.getFcid());
                    courseabilitycondition_object.put("FUID", courseabilitycondition.getFuid());
                    courseabilitycondition_object.put("FCDATE", courseabilitycondition.getFcdate());
                    courseabilitycondition_object.put("FUDATE", courseabilitycondition.getFudate());
                } else {
                    courseabilitycondition_object.put("FName", "*****");
                    courseabilitycondition_object.put("FOrder", "*****");
                    courseabilitycondition_object.put("FCID", "*****");
                    courseabilitycondition_object.put("FUID", "*****");
                    courseabilitycondition_object.put("FCDATE", "*****");
                    courseabilitycondition_object.put("FUDATE", "*****");
                }
                courseabilitycondition_object.put("FState", courseabilitycondition.getFstate());


                TCourseStandardExample tCourseStandardExample = new TCourseStandardExample();
                tCourseStandardExample.createCriteria()
                        .andFcourseidEqualTo(courseabilitycondition.getFcourseid())
                        .andFcourseabilityidEqualTo(courseabilitycondition.getFkeyid())
                        .andFabilitycamidEqualTo(courseabilitycondition.getFconditionid());
                List<TCourseStandard> serviceByExample = tCourseStandardService.findByExample(tCourseStandardExample);
                //设置状态 默认-1 保存但未提交=1 提交=2
                if (serviceByExample.size() > 0) {
                    for (TCourseStandard tCourseStandard : serviceByExample) {
                        courseabilitycondition_object.put("FSZState", tCourseStandard.getFszstate());
                        courseabilitycondition_object.put("FType", tCourseStandard.getFtype());
                        courseabilitycondition_object.put("FQZType", tCourseStandard.getFqztype());
                        courseabilitycondition_object.put("FCourseStandardID",ParamTools.getEnParam(tCourseStandard.getFkeyid().toString()));
                    }
                }
                courseabilitycondition_Array.add(courseabilitycondition_object);
            }
            // 返回值
            object.put("list", courseabilitycondition_Array);
            object.put("total", courseabilityconditionPageInfo.getTotal()); // 总行数
            object.put("page", courseabilityconditionPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取考核条件信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/getCourseAbilityCondition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getCourseAbilityCondition(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        String courseID = jsonParam.getString("courseID");

        try {
            // 获取数据库记录
            JSONArray ability_Array = new JSONArray();
            // 查询条件
            CourseabilityCondtionCS courseabilityCondtionCS = new CourseabilityCondtionCS();

            if (name != null && !name.equals("")) {

                courseabilityCondtionCS.setName(name);
            }

            courseID = courseID == null ? "0" : ParamTools.getdeParam(courseID);
            courseabilityCondtionCS.setFCourseID(Long.parseLong(courseID));

            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                courseabilityCondtionCS.setOrderBy(orderSql.substring(1));
            } else {
                courseabilityCondtionCS.setOrderBy("talc.FName");
            }
            PageInfo<CourseabilityCondtionData> abilityPageInfo = tCourseAbilityConditionService.getCourseAbilityConditionList(courseabilityCondtionCS, (page - 1) * results, results);
            List<CourseabilityCondtionData> ability_list = abilityPageInfo.getList();

            for (CourseabilityCondtionData ability : ability_list) {
                JSONObject ability_object = new JSONObject();
                ability_object.put("key", ParamTools.getEnParam(ability.getFCourseAbilityConditionID().toString())); //考核条件ID
                ability_object.put("FCourseAbilityConditionName", ability.getFCourseAbilityConditionName());
                ability_object.put("FScore", ability.getFScore());
                ability_object.put("FNote", ability.getFNote());
                ability_Array.add(ability_object);
            }
            // 返回值
            object.put("list", ability_Array);
            object.put("total", abilityPageInfo.getTotal()); // 总行数
            object.put("page", abilityPageInfo.getPageNum()); // 当前页数
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
                    int forder = 1;
                    List<TCourseAbilityCondition> courseAbilityList = null;
                    List<TCourseAbility> abilityList = null;
                    for (Object conditionid : powerRoles_Array) {
                        Long fconditionid = Long.parseLong(ParamTools.getdeParam(conditionid.toString())); //考核条件ID
                        forder = 1;
                        TCourseAbilityConditionExample tCourseAbilityConditionExample = new TCourseAbilityConditionExample();
                        tCourseAbilityConditionExample.createCriteria().andFcourseidEqualTo(Long.parseLong(FCourse)).andFconditionidEqualTo(fconditionid);
                        courseAbilityList = tCourseAbilityConditionService.findByExample(tCourseAbilityConditionExample);
                        if (courseAbilityList.size() == 0) {
                            tCourseAbilityConditionExample = new TCourseAbilityConditionExample();
                            tCourseAbilityConditionExample.createCriteria().andFcourseidEqualTo(Long.parseLong(FCourse));
                            tCourseAbilityConditionExample.setOrderByClause("FOrder desc");
                            courseAbilityList = tCourseAbilityConditionService.findByExample(tCourseAbilityConditionExample);
                            if (courseAbilityList.size() > 0) {
                                forder = courseAbilityList.get(0).getForder() + 1;
                            }

                            TCourseAbilityCondition tCourseAbilityCondition = new TCourseAbilityCondition();
                            tCourseAbilityCondition.setFcid(Long.parseLong(uid));
                            tCourseAbilityCondition.setFcdate(new Date());
                            tCourseAbilityCondition.setForder(forder);
                            tCourseAbilityCondition.setFcourseid(Long.parseLong(FCourse));
                            tCourseAbilityCondition.setFconditionid(fconditionid);
                            tCourseAbilityConditionService.save(tCourseAbilityCondition);
//                            //绑定完能力 添加标准设置主表信息
                            insertCourseStandardData(tCourseAbilityCondition.getFkeyid(),Long.parseLong(uid),Long.parseLong(FCourse));
                        } else {

                            TAbilityLevelCondition condition = tAbilityLevelConditionService.findById(fconditionid);

                            errorstr += condition.getFname() + "、";
                        }
                    }
                }

                if (!errorstr.equals("")) {
                    errorstr = errorstr.substring(0, errorstr.length() - 1);
                    object.put("errorstr", "考核条件【" + errorstr + "】已存在！");
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
    private void insertCourseStandardData(Long FCourseAbilityConditionID,Long uid,Long FCourseID) throws Exception {
        TCourseAbilityCondition abilityCondition = tCourseAbilityConditionService.findById(FCourseAbilityConditionID);
        try {
            if(abilityCondition!=null){
                TCourseStandardExample tCourseStandardExample = new TCourseStandardExample();
                TCourseStandardExample.Criteria criteria = tCourseStandardExample.createCriteria();
                criteria.andFcourseidEqualTo(FCourseID);
                criteria.andFcourseabilityidEqualTo(FCourseAbilityConditionID);
                criteria.andFabilitycamidEqualTo(abilityCondition.getFconditionid());
                List<TCourseStandard> standardList = tCourseStandardService.findByExample(tCourseStandardExample);
                if (standardList.size() == 0) {
                    TCourseStandard tCourseStandard = new TCourseStandard();
                    tCourseStandard.setFcourseid(FCourseID);
                    tCourseStandard.setFcourseabilityid(FCourseAbilityConditionID);
                    tCourseStandard.setFabilitycamid(abilityCondition.getFconditionid());
                    tCourseStandard.setFcid(uid);
                    tCourseStandard.setFcdate(new Date());
                    tCourseStandard.setFszstate(-1);
                    tCourseStandardService.save(tCourseStandard);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 获取课程绑定能力涉及的能力树信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycourseabilityConditionTree", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycourseabilityConditionTree(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FlevelConditionID = jsonParam.getString("id");
        try {

            String searchInput ="";
            // 获取数据库记录
            JSONArray courseabilitycondition_Array = new JSONArray();
            FlevelConditionID = FlevelConditionID == null ? "0" : ParamTools.getdeParam(FlevelConditionID);
            TAbilityLevelCondition tAbilityLevelCondition = tAbilityLevelConditionService.findById(Long.parseLong(FlevelConditionID));


            CourseabilityCondtionCS courseabilityCondtionCS = new CourseabilityCondtionCS();
            courseabilityCondtionCS.setFCourseID(Long.parseLong(FlevelConditionID));
//            courseabilityCondtionCS.setFCourseID(1362433703590105089l);
            List<Long> levelAbilityConditionIDList = tCourseAbilityConditionService.getLevelAbilityConditionIDList(courseabilityCondtionCS);
            JSONArray treeData_Array = new JSONArray();
            if (levelAbilityConditionIDList.size() > 0) {
                float FConditionScore = 0;
                TAbilityTreeExample tAbilityTreeExample2 = new TAbilityTreeExample();
                TAbilityTreeExample.Criteria criteria = null;
                //获取到所有能力水平ID
                //拼装json数据
                for (Long levelAbilityConditionID : levelAbilityConditionIDList) {
                    TAbilityTree serviceById = tAbilityTreeService.findById(levelAbilityConditionID);
                    TAbilityTree abilityTree = tAbilityTreeService.findById(serviceById.getFpid());
                    TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
                    tAbilityTreeExample.createCriteria().andFpidEqualTo(serviceById.getFgpid()).andFkeyidEqualTo(levelAbilityConditionID);
                    List<TAbilityTree> serviceByExample = tAbilityTreeService.findByExample(tAbilityTreeExample);
                    JSONObject abilitytreeData = new JSONObject();
                    abilitytreeData.put("name", abilityTree.getFname());
                    JSONArray levelarray = new JSONArray();
                    if (serviceByExample.size() > 0) {
                        for (TAbilityTree tAbilityTree : serviceByExample) {

                            JSONObject leveltreeData = new JSONObject();
                            leveltreeData.put("id",ParamTools.getEnParam(tAbilityTree.getFkeyid().toString()));
                            leveltreeData.put("pId",ParamTools.getEnParam(tAbilityTree.getFpid().toString()));
                            leveltreeData.put("name",tAbilityTree.getFname() + "【当前水平积分："+tAbilityTree.getFpoints()+"分】");
                            levelarray.add(leveltreeData);
                            if(tAbilityTree.getFisleaf() == 0){
                                getCourseAbilityConditionTreez(tAbilityTree.getFkeyid(),levelarray);
                            }
                        }
                    }
                    abilitytreeData.put("nodes",levelarray);
                    treeData_Array.add(abilitytreeData);
                }
            }
            object.put("searchInput",tAbilityLevelCondition.getFname());
//            object.put("searchInput","相关知识要求");
            object.put("treeDataall",treeData_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    private void getCourseAbilityConditionTreez(Long FPID,JSONArray zdata_array){
        List<Integer> FNodetypeID = new ArrayList<Integer>();
        FNodetypeID.add(3);
        FNodetypeID.add(4);
        try {
            float FMethodWeight2 = 0;
            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            tAbilityTreeExample.createCriteria().andFpidEqualTo(FPID).andFdelEqualTo(1).andFstateEqualTo(1).andFnodetypeIn(FNodetypeID);
            List<TAbilityTree> treeList = tAbilityTreeService.findByExample(tAbilityTreeExample);
            if (treeList.size() > 0) {
                for (TAbilityTree tAbilityTree2 : treeList) {
                    FMethodWeight2 = 0;
                    JSONObject leveltreeData = new JSONObject();
                    leveltreeData.put("id",ParamTools.getEnParam(tAbilityTree2.getFkeyid().toString()));
                    leveltreeData.put("pId",ParamTools.getEnParam(tAbilityTree2.getFpid().toString()));
                    TAbilityTreeExample tAbilityTreeExample1 = new TAbilityTreeExample();
                    TAbilityTreeExample.Criteria criteria1 = tAbilityTreeExample1.createCriteria();
                    criteria1.andFdelEqualTo(1);
                    criteria1.andFpidEqualTo(tAbilityTree2.getFkeyid());
                    criteria1.andFnodetypeIn(FNodetypeID);
                    List<TAbilityTree> abilityConditionAssessmentMethodList = tAbilityTreeService.findByExample(tAbilityTreeExample1);
                    if (abilityConditionAssessmentMethodList.size() > 0) {
                        for (TAbilityTree abilityConditionAssessmentMethod : abilityConditionAssessmentMethodList) {
                            FMethodWeight2 += abilityConditionAssessmentMethod.getFmethodweight();
                        }
                    }
                    if(tAbilityTree2.getFnodetype() ==3){
                        leveltreeData.put("name",tAbilityTree2.getFname() + "【当前条件权重：" + String.format("%.2f", tAbilityTree2.getFmethodweight() == null ? 0 : tAbilityTree2.getFmethodweight()) + "%】" + "【下级条件+考核方式总权重：" + String.format("%.2f", (FMethodWeight2)) + "%】");
                    }else{
                        TAbilityAssessmentMethod tAbilityAssessmentMethod = tAbilityAssessmentMethodService.findById(tAbilityTree2.getFmethodid());
                        leveltreeData.put("name",tAbilityAssessmentMethod.getFmethodname() + "【当前条件权重：" + String.format("%.2f", tAbilityTree2.getFmethodweight() == null ? 0 : tAbilityTree2.getFmethodweight()) + "%】");
                    }

                    zdata_array.add(leveltreeData);
                    if(tAbilityTree2.getFisleaf() == 0 && tAbilityTree2.getFnodetype() == 3){
                        getCourseAbilityConditionTreez(tAbilityTree2.getFkeyid(),zdata_array);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除课程能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除课程能力信息")
    @ResponseBody
    @RequestMapping(value = "/delcourseabilityCondition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delcourseabilityCondition(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TCourseAbilityCondition courseAbilityCondition = tCourseAbilityConditionService.findById(Long.parseLong(id));

            TCourseStandardExample tCourseStandardExample = new TCourseStandardExample();
            tCourseStandardExample.createCriteria().andFcourseidEqualTo(courseAbilityCondition.getFcourseid()).andFcourseabilityidEqualTo(courseAbilityCondition.getFkeyid());
            List<TCourseStandard> courseStandardList = tCourseStandardService.findByExample(tCourseStandardExample);
            if (courseStandardList.size() > 0) {
                for (TCourseStandard courseStandard : courseStandardList) {
                    TCourseStandardSExample tCourseStandardSExample = new TCourseStandardSExample();
                    tCourseStandardSExample.createCriteria().andFcsidEqualTo(courseStandard.getFkeyid());
                    tCourseStandardSService.deleteByByExample(tCourseStandardSExample);
                    tCourseStandardService.deleteById(courseStandard.getFkeyid());
                }
            }
            tCourseAbilityConditionService.deleteById(Long.parseLong(id));
            // 返回值
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
}