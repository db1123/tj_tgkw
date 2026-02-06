package fun.server.controller.course;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.model.customQuery.courseAbility.CourseabilityCS;
import fun.server.model.customQuery.coursestandard.CourseStandardCS;
import fun.server.model.customQuery.coursestandard.CourseStandardData;
import fun.server.service.*;
import fun.tools.ParamTools;
import fun.tools.SnowflakeIdWorker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * 课程能力标准设置管理 相关业务处理
 */
@RestController
@RequestMapping("/s/coursestandard")
public class CourseStandard {

    private final TCourseStandardService tCourseStandardService;

    private final TCourseService tCourseService;

    private final TCourseStandardSService tCourseStandardSService;

    private final TAbilityService tAbilityService;

    private final TAbilityLevelService tAbilityLevelService;

    private final TUserService tUserService;

    private final TAbilityConditionService tAbilityConditionService;

    private final TCourseAbilityService tCourseAbilityService;

    private final TCourseStudentScoreTBTreeService tCourseStudentScoreTBTreeService;

    public CourseStandard(TCourseStandardService tCourseStandardService, TCourseService tCourseService, TCourseStandardSService tCourseStandardSService, TAbilityService tAbilityService, TAbilityLevelService tAbilityLevelService, TUserService tUserService, TAbilityConditionService tAbilityConditionService, TCourseAbilityService tCourseAbilityService, TCourseStudentScoreTBTreeService tCourseStudentScoreTBTreeService) {
        this.tCourseStandardService = tCourseStandardService;
        this.tCourseService = tCourseService;
        this.tCourseStandardSService = tCourseStandardSService;
        this.tAbilityService = tAbilityService;
        this.tAbilityLevelService = tAbilityLevelService;
        this.tUserService = tUserService;
        this.tAbilityConditionService = tAbilityConditionService;
        this.tCourseAbilityService = tCourseAbilityService;
        this.tCourseStudentScoreTBTreeService = tCourseStudentScoreTBTreeService;
    }


    /**
     * 查询课程能力是否有标准设置数据
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */

    @ResponseBody
    @RequestMapping(value = "/getCourseStandarddata", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getCourseStandarddata(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {

            id = id == null ? "0" : ParamTools.getdeParam(id);

            TCourse serviceById = tCourseService.findById(Long.parseLong(id));
            Long FCourseAbilityID = 0l;
            Long FCourseAbilityLevelID = 0l;
            if (serviceById != null) {
                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);

                String CookiesLogincourseID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogincourseID != null && !CookiesLogincourseID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogincourseID);
                }
                FCourseAbilityID = serviceById.getFabilityid();
                FCourseAbilityLevelID = serviceById.getFabilitylevelid();

                //查询标准设置中是否存在数据

                TCourseStandardExample tCourseStandardExample = new TCourseStandardExample();
                TCourseStandardExample.Criteria criteria = tCourseStandardExample.createCriteria();
                criteria.andFcourseidEqualTo(serviceById.getFkeyid());
                criteria.andFabilitylevelidEqualTo(FCourseAbilityLevelID);
                criteria.andFstateEqualTo(1);
                List<TCourseStandard> standardList = tCourseStandardService.findByExample(tCourseStandardExample);


                CourseStandardCS courseStandardCS = new CourseStandardCS();
                courseStandardCS.setFCourseID(FCourseAbilityLevelID);
                List<CourseStandardData> conditions = tCourseStandardService.getCourseStandardDatainsert(courseStandardCS);

                if (standardList.size() == 0) {
                    //插入数据
                    for (CourseStandardData condition : conditions) {
                        tCourseStandardExample = new TCourseStandardExample();
                        criteria = tCourseStandardExample.createCriteria();
                        criteria.andFcourseidEqualTo(serviceById.getFkeyid());
                        criteria.andFabilitylevelidEqualTo(FCourseAbilityLevelID);
                        criteria.andFabilitycamidEqualTo(condition.getFKeyID());
                        standardList = tCourseStandardService.findByExample(tCourseStandardExample);
                        if (standardList.size() == 0) {
                            long key = idWorker.nextId();
                            TCourseStandard tCourseStandard = new TCourseStandard();
                            tCourseStandard.setFkeyid(key);
                            tCourseStandard.setFcourseid(Long.parseLong(id));
                            tCourseStandard.setFabilityid(FCourseAbilityID);
                            tCourseStandard.setFabilitylevelid(FCourseAbilityLevelID);
                            tCourseStandard.setFabilitycamid(condition.getFKeyID());
                            tCourseStandard.setFtype(-1);
                            tCourseStandard.setFcalctype(-1l);
                            tCourseStandard.setFszstate(-1);
                            tCourseStandard.setFcid(Long.parseLong(uid));
                            tCourseStandard.setFcdate(new Date());
                            tCourseStandardService.save(tCourseStandard);


                        }
                    }

                } else {
                    //比较一下 是否与能力条件一致 不一致

                    if (standardList.size() < conditions.size()) {
                        for (CourseStandardData condition : conditions) {
                            tCourseStandardExample = new TCourseStandardExample();
                            criteria = tCourseStandardExample.createCriteria();
                            criteria.andFcourseidEqualTo(serviceById.getFkeyid());
                            criteria.andFabilitylevelidEqualTo(FCourseAbilityLevelID);
                            criteria.andFabilitycamidEqualTo(condition.getFKeyID());
                            standardList = tCourseStandardService.findByExample(tCourseStandardExample);
                            if (standardList.size() == 0) {
                                long key = idWorker.nextId();
                                TCourseStandard tCourseStandard = new TCourseStandard();
                                tCourseStandard.setFkeyid(key);
                                tCourseStandard.setFcourseid(Long.parseLong(id));
                                tCourseStandard.setFabilityid(FCourseAbilityID);
                                tCourseStandard.setFabilitylevelid(FCourseAbilityLevelID);
                                tCourseStandard.setFabilitycamid(condition.getFKeyID());
                                tCourseStandard.setFtype(-1);
                                tCourseStandard.setFcalctype(-1l);
                                tCourseStandard.setFszstate(-1);
                                tCourseStandard.setFcid(Long.parseLong(uid));
                                tCourseStandard.setFcdate(new Date());
                                tCourseStandardService.save(tCourseStandard);


                            }
                        }
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


//    /**
//     * 查询课程能力是否有标准设置数据——多个能力
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//
//    @ResponseBody
//    @RequestMapping(value = "/getCourseStandarddatacourse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String getCourseStandarddatacourse(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String id = jsonParam.getString("id");//课程能力表ID
//
//        try {
//
//            id = id == null ? "0" : ParamTools.getdeParam(id);
//            TCourseAbility courseAbility = tCourseAbilityService.findById(Long.parseLong(id));
//
//
//            Long FCourseAbilityID = courseAbility.getFabilityid();
//            Long FCourseAbilityLevelID = courseAbility.getFabilitylevelid();
//            if (courseAbility != null) {
//                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
//
//                String CookiesLogincourseID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//                String uid = ""; // 当前登录用户ID
//                if (CookiesLogincourseID != null && !CookiesLogincourseID.equals("")) {
//                    uid = ParamTools.getdeParam(CookiesLogincourseID);
//                }
////                FCourseAbilityID = serviceById.getFabilityid();
////                FCourseAbilityLevelID = serviceById.getFabilitylevelid();
//
//                //查询标准设置中是否存在数据
//                TCourse serviceById = tCourseService.findById(courseAbility.getFcourseid());
//                TCourseStandardExample tCourseStandardExample = new TCourseStandardExample();
//                TCourseStandardExample.Criteria criteria = tCourseStandardExample.createCriteria();
//                criteria.andFcourseabilityidEqualTo(courseAbility.getFkeyid());
//                criteria.andFabilitylevelidEqualTo(FCourseAbilityLevelID);
//                criteria.andFstateEqualTo(1);
//                List<TCourseStandard> standardList = tCourseStandardService.findByExample(tCourseStandardExample);
//
//
//                CourseStandardCS courseStandardCS = new CourseStandardCS();
//                courseStandardCS.setFCourseID(FCourseAbilityLevelID);
//                List<CourseStandardData> conditions = tCourseStandardService.getCourseStandardDatainsert(courseStandardCS);
//
//                CourseStandardCS courseStandardCS1 = new CourseStandardCS();
//                List<CourseStandardData> dataList = null;
//                long key = 0l;
//                long key2 = 0l;
//                if (standardList.size() == 0) {
//
//                    //插入数据
//                    for (CourseStandardData condition : conditions) {
//                        tCourseStandardExample = new TCourseStandardExample();
//                        criteria = tCourseStandardExample.createCriteria();
//                        criteria.andFcourseabilityidEqualTo(courseAbility.getFkeyid());
//                        criteria.andFabilitylevelidEqualTo(FCourseAbilityLevelID);
//                        criteria.andFabilitycamidEqualTo(condition.getFKeyID());
//                        standardList = tCourseStandardService.findByExample(tCourseStandardExample);
//                        if (standardList.size() == 0) {
//                            key = idWorker.nextId();
//                            TCourseStandard tCourseStandard = new TCourseStandard();
//                            tCourseStandard.setFkeyid(key);
//                            tCourseStandard.setFcourseid(serviceById.getFkeyid());
//                            tCourseStandard.setFabilityid(FCourseAbilityID);
//                            tCourseStandard.setFabilitylevelid(FCourseAbilityLevelID);
//                            tCourseStandard.setFabilitycamid(condition.getFKeyID());
//                            tCourseStandard.setFtype(-1);
//                            tCourseStandard.setFcalctype(-1l);
//                            tCourseStandard.setFszstate(-1);
//                            tCourseStandard.setFcid(Long.parseLong(uid));
//                            tCourseStandard.setFcdate(new Date());
//                            tCourseStandard.setFcourseabilityid(courseAbility.getFkeyid());
//                            tCourseStandard.setFcamtype(condition.getFType());
//                            tCourseStandardService.save(tCourseStandard);
//                        }
//                    }
//
//
//
//                } else {
//                    //比较一下 是否与能力条件一致 不一致
//
//                    if (standardList.size() < conditions.size()) {
//                        for (CourseStandardData condition : conditions) {
//                            tCourseStandardExample = new TCourseStandardExample();
//                            criteria = tCourseStandardExample.createCriteria();
//                            criteria.andFcourseabilityidEqualTo(courseAbility.getFkeyid());
//                            criteria.andFabilitylevelidEqualTo(FCourseAbilityLevelID);
//                            criteria.andFabilitycamidEqualTo(condition.getFKeyID());
//                            standardList = tCourseStandardService.findByExample(tCourseStandardExample);
//                            if (standardList.size() == 0) {
//                                key = idWorker.nextId();
//                                TCourseStandard tCourseStandard = new TCourseStandard();
//                                tCourseStandard.setFkeyid(key);
//                                tCourseStandard.setFcourseid(serviceById.getFkeyid());
//                                tCourseStandard.setFabilityid(FCourseAbilityID);
//                                tCourseStandard.setFabilitylevelid(FCourseAbilityLevelID);
//                                tCourseStandard.setFabilitycamid(condition.getFKeyID());
//                                tCourseStandard.setFtype(-1);
//                                tCourseStandard.setFcalctype(-1l);
//                                tCourseStandard.setFszstate(-1);
//                                tCourseStandard.setFcid(Long.parseLong(uid));
//                                tCourseStandard.setFcdate(new Date());
//                                tCourseStandard.setFcourseabilityid(courseAbility.getFkeyid());
//                                tCourseStandard.setFcamtype(condition.getFType());
//                                tCourseStandardService.save(tCourseStandard);
//                            }
//                        }
//                    }
//                }
//            }
//            // 返回值
//            object.put("result", "success");
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }

    /**
     * 查询课程能力是否有标准设置数据——多个能力
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */

    @ResponseBody
    @RequestMapping(value = "/getCourseStandarddatacourse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getCourseStandarddatacourse(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");//课程能力表ID

        try {

            id = id == null ? "0" : ParamTools.getdeParam(id);
            TCourseAbility courseAbility = tCourseAbilityService.findById(Long.parseLong(id));


            Long FCourseAbilityID = courseAbility.getFabilityid();
            Long FCourseAbilityLevelID = courseAbility.getFabilitylevelid();
            if (courseAbility != null) {
                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);

                String CookiesLogincourseID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogincourseID != null && !CookiesLogincourseID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogincourseID);
                }
//                FCourseAbilityID = serviceById.getFabilityid();
//                FCourseAbilityLevelID = serviceById.getFabilitylevelid();

                //查询标准设置中是否存在数据
                TCourse serviceById = tCourseService.findById(courseAbility.getFcourseid());
                TCourseStandardExample tCourseStandardExample = new TCourseStandardExample();
                TCourseStandardExample.Criteria criteria = tCourseStandardExample.createCriteria();
                criteria.andFcourseabilityidEqualTo(courseAbility.getFkeyid());
                criteria.andFabilitylevelidEqualTo(FCourseAbilityLevelID);
                criteria.andFstateEqualTo(1);
                List<TCourseStandard> standardList = tCourseStandardService.findByExample(tCourseStandardExample);


                CourseStandardCS courseStandardCS = new CourseStandardCS();
                courseStandardCS.setFCourseID(FCourseAbilityLevelID);
                List<CourseStandardData> conditions = tCourseStandardService.getCourseStandardDatainsert(courseStandardCS);

                CourseStandardCS courseStandardCS1 = new CourseStandardCS();
                List<CourseStandardData> dataList = null;
                long key = 0l;
                long key2 = 0l;
                if (standardList.size() == 0) {

                    //插入数据
                    for (CourseStandardData condition : conditions) {
                        tCourseStandardExample = new TCourseStandardExample();
                        criteria = tCourseStandardExample.createCriteria();
                        criteria.andFcourseabilityidEqualTo(courseAbility.getFkeyid());
                        criteria.andFabilitylevelidEqualTo(FCourseAbilityLevelID);
                        criteria.andFabilitycamidEqualTo(condition.getFKeyID());
                        standardList = tCourseStandardService.findByExample(tCourseStandardExample);
                        if (standardList.size() == 0) {
                            key = idWorker.nextId();
                            TCourseStandard tCourseStandard = new TCourseStandard();
                            tCourseStandard.setFkeyid(key);
                            tCourseStandard.setFcourseid(serviceById.getFkeyid());
                            tCourseStandard.setFabilityid(FCourseAbilityID);
                            tCourseStandard.setFabilitylevelid(FCourseAbilityLevelID);
                            tCourseStandard.setFabilitycamid(condition.getFKeyID());
                            tCourseStandard.setFtype(-1);
                            tCourseStandard.setFcalctype(-1l);
                            tCourseStandard.setFszstate(-1);
                            tCourseStandard.setFcid(Long.parseLong(uid));
                            tCourseStandard.setFcdate(new Date());
                            tCourseStandard.setFcourseabilityid(courseAbility.getFkeyid());
                            tCourseStandard.setFcamtype(condition.getFType());
                            tCourseStandardService.save(tCourseStandard);
                        }
                    }



                } else {
                    //比较一下 是否与能力条件一致 不一致

                    if (standardList.size() < conditions.size()) {
                        for (CourseStandardData condition : conditions) {
                            tCourseStandardExample = new TCourseStandardExample();
                            criteria = tCourseStandardExample.createCriteria();
                            criteria.andFcourseabilityidEqualTo(courseAbility.getFkeyid());
                            criteria.andFabilitylevelidEqualTo(FCourseAbilityLevelID);
                            criteria.andFabilitycamidEqualTo(condition.getFKeyID());
                            standardList = tCourseStandardService.findByExample(tCourseStandardExample);
                            if (standardList.size() == 0) {
                                key = idWorker.nextId();
                                TCourseStandard tCourseStandard = new TCourseStandard();
                                tCourseStandard.setFkeyid(key);
                                tCourseStandard.setFcourseid(serviceById.getFkeyid());
                                tCourseStandard.setFabilityid(FCourseAbilityID);
                                tCourseStandard.setFabilitylevelid(FCourseAbilityLevelID);
                                tCourseStandard.setFabilitycamid(condition.getFKeyID());
                                tCourseStandard.setFtype(-1);
                                tCourseStandard.setFcalctype(-1l);
                                tCourseStandard.setFszstate(-1);
                                tCourseStandard.setFcid(Long.parseLong(uid));
                                tCourseStandard.setFcdate(new Date());
                                tCourseStandard.setFcourseabilityid(courseAbility.getFkeyid());
                                tCourseStandard.setFcamtype(condition.getFType());
                                tCourseStandardService.save(tCourseStandard);
                            }
                        }
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
     * 获取课程能力标准设置信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycoursestandardlist", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycoursestandardlist(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String FTJName = jsonParam.getString("FTJName");
//        String FNLName = jsonParam.getString("FNLName");
//        String FNLDJName = jsonParam.getString("FNLDJName");
        String FSZState = jsonParam.getString("FSZState");
        String FType = jsonParam.getString("FType");
        String courseID = jsonParam.getString("courseID");
//        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray coursenature_Array = new JSONArray();
            // 查询条件
            CourseStandardCS courseStandardCS = new CourseStandardCS();

            if (FTJName != null && !FTJName.equals("")) {
                courseStandardCS.setFTJName(FTJName);
            }
//            if(FNLName!=null && !FNLName.equals("")){
//                courseStandardCS.setFNLName(FNLName);
//            }
//            if(FNLDJName!=null && !FNLDJName.equals("")){
//                courseStandardCS.setFNLDJName(FNLDJName);
//            }
            if (FSZState != null && !FSZState.equals("")) {
                courseStandardCS.setFSZState(Integer.parseInt(FSZState));
            }
//            System.out.println(FType);
            if (FType != null && !FType.equals("")) {
                courseStandardCS.setFType(Integer.parseInt(FType));
            }


            if (courseID != null && !courseID.equals("")) {
                courseStandardCS.setFCourseID(Long.parseLong(ParamTools.getdeParam(courseID)));
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
                courseStandardCS.setOrderBy(orderSql.substring(1));
            } else {
                courseStandardCS.setOrderBy("tac.FConditionName asc");
            }
            PageInfo<CourseStandardData> coursenaturePageInfo = tCourseStandardService.getCourseStandardDataList(courseStandardCS, (page - 1) * results, results);
            List<CourseStandardData> courseStandardDataList = coursenaturePageInfo.getList();
            for (CourseStandardData courseStandardData : courseStandardDataList) {
                JSONObject coursenature_object = new JSONObject();
                coursenature_object.put("key", ParamTools.getEnParam(courseStandardData.getFKeyID().toString()));
                coursenature_object.put("FCourseID", ParamTools.getEnParam(courseStandardData.getFCourseID().toString()));
                coursenature_object.put("FAbilityID", ParamTools.getEnParam(courseStandardData.getFAbilityID().toString()));
                coursenature_object.put("FAbilityLevelID", ParamTools.getEnParam(courseStandardData.getFAbilityLevelID().toString()));
                coursenature_object.put("FAbilityCAMID", ParamTools.getEnParam(courseStandardData.getFAbilityCAMID().toString()));
                coursenature_object.put("FName", courseStandardData.getFAbilityName() + "/" + courseStandardData.getFAbilityLevelName());
                coursenature_object.put("FAbilityName", courseStandardData.getFAbilityName());
                coursenature_object.put("FCourseName", courseStandardData.getFCourseName());
                coursenature_object.put("FAbilityLevelName", courseStandardData.getFAbilityLevelName());
                coursenature_object.put("FAbilityCAMName", courseStandardData.getFAbilityCAMName());
                coursenature_object.put("FcalcType", ParamTools.getEnParam(courseStandardData.getFcalcType().toString()));
                coursenature_object.put("FSZState", courseStandardData.getFSZState());
                coursenature_object.put("FType", courseStandardData.getFType());
                coursenature_object.put("FQZType", courseStandardData.getFQZType());
                coursenature_object.put("FConditionScore", courseStandardData.getFConditionScore());
                coursenature_Array.add(coursenature_object);
            }
            // 返回值
            object.put("list", coursenature_Array);
            object.put("total", coursenaturePageInfo.getTotal()); // 总行数
            object.put("page", coursenaturePageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }





    /**
     * 修改课程能力条件标准设置类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改课程能力条件标准设置类型信息")
    @ResponseBody
    @RequestMapping(value = "/updatecourseStandardFType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatecourseStandardFType(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        int FType = jsonParam.getInteger("FType");

        try {
            long fcalcType = 0;
            if (FType == 1) {
                String FcalcType = jsonParam.getString("FcalcType");
                FcalcType = FcalcType == null ? "0" : ParamTools.getdeParam(FcalcType);
                fcalcType = Long.parseLong(FcalcType);
            }
            String CookiesLogincoursenatureID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogincoursenatureID != null && !CookiesLogincoursenatureID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLogincoursenatureID);
            }
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            TCourseStandard tCoursestandard = new TCourseStandard();
            tCoursestandard.setFkeyid(key);
            tCoursestandard.setFtype(FType);
            if (FType == 1) {
                tCoursestandard.setFcalctype(fcalcType);
            }
            tCoursestandard.setFudate(new Date());
            tCoursestandard.setFuid(Long.parseLong(uid));
            tCoursestandard.setFszstate(1);
            tCourseStandardService.update(tCoursestandard);
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
     * 修改课程能力条件标准设置取值类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改课程能力条件标准设置取值类型信息")
    @ResponseBody
    @RequestMapping(value = "/updatecourseStandardFqzType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatecourseStandardFqzType(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        int fqztype = jsonParam.getInteger("fqztype");

        try {
            String CookiesLogincoursenatureID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogincoursenatureID != null && !CookiesLogincoursenatureID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLogincoursenatureID);
            }
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            TCourseStandard tCoursestandard = new TCourseStandard();
            tCoursestandard.setFkeyid(key);
            tCoursestandard.setFqztype(fqztype);
            tCoursestandard.setFudate(new Date());
            tCoursestandard.setFuid(Long.parseLong(uid));
            tCourseStandardService.update(tCoursestandard);
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
     * 检测课程绑定能力的考核条件是否已设置完标准
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */

    @ResponseBody
    @RequestMapping(value = "/coursestandardjc", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String coursestandardjc(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");//课程ID

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            //先查询是否存在课程能力
            CourseabilityCS courseabilityCS =new CourseabilityCS();
            courseabilityCS.setFCourseID(Long.parseLong(id));
            int issz = 0;
            int courseabiltyCount = tCourseStandardService.getCourseabiltyCount(courseabilityCS);
            if(courseabiltyCount > 0){
                TCourseStandardExample tCourseStandardExample = new TCourseStandardExample();
                TCourseStandardExample.Criteria criteria = tCourseStandardExample.createCriteria();
                criteria.andFcourseidEqualTo(Long.parseLong(id));
                List<TCourseStandard> tCourseStandardList = tCourseStandardService.findByExample(tCourseStandardExample);
                int znum = tCourseStandardList.size();
                tCourseStandardExample = new TCourseStandardExample();
                criteria = tCourseStandardExample.createCriteria();
                criteria.andFcourseidEqualTo(Long.parseLong(id));
                criteria.andFszstateEqualTo(2);
                List<TCourseStandard> standards = tCourseStandardService.findByExample(tCourseStandardExample);
                int sznum = standards.size();

                if (znum == 0)
                    issz =1;
                else if(znum < sznum)
                    issz = 2;
                //查询课程下能力是否设置了权重
                TCourseAbilityExample tCourseAbilityExample = new TCourseAbilityExample();
                tCourseAbilityExample.createCriteria().andFcourseidEqualTo(Long.parseLong(id)).andFmethodweightLessThanOrEqualTo((float) 0); //小于等于0
                List<TCourseAbility> tCourseAbilities = tCourseAbilityService.findByExample(tCourseAbilityExample);
                if (tCourseAbilities.size() > 0){
                    issz = 3;
                }
            }else{
                issz = 4;
            }
            int FGroupNum = 0;
            //查询一下 填报过程当前最新的是第几组
            //查询最新分组结果
            TCourseStudentScoreTbTreeExample groupbynumExample = new TCourseStudentScoreTbTreeExample();
            groupbynumExample.createCriteria().andFstateEqualTo(1);
            groupbynumExample.setOrderByClause("FGroypNum desc");
            List<TCourseStudentScoreTbTree> groupbynumlist = tCourseStudentScoreTBTreeService.findByExample(groupbynumExample);
            if(groupbynumlist.size() > 0) {
                FGroupNum = groupbynumlist.get(0).getFgroypnum();
            }else{
                FGroupNum = 1;
            }
            // 返回值
            object.put("FGroupNum", FGroupNum);
            object.put("issz", issz);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

}