package fun.server.controller.course;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.model.customQuery.courseAbility.CourseabilityCS;
import fun.server.model.customQuery.courseAbility.CourseabilityData;
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
 * 课程管理 相关业务处理
 */
@RestController
@RequestMapping("/s/courseability")
public class CourseAbility {

    private final TCourseAbilityService tCourseAbilityService;

    private final TCourseService tCourseService;


    private final TAbilityService tAbilityService;

    private final TAbilityLevelService tAbilityLevelService;

    private final TAbilityTypeService tAbilityTypeService;

    private final TCourseStandardService tCourseStandardService;

    private final TCourseStandardSService tCourseStandardSService;

    private final TAbilityTreeService tAbilityTreeService;


    public CourseAbility(TCourseAbilityService tCourseAbilityService, TCourseService tCourseService, TAbilityService tAbilityService, TAbilityLevelService tAbilityLevelService, TAbilityTypeService tAbilityTypeService, TCourseStandardService tCourseStandardService, TCourseStandardSService tCourseStandardSService, TAbilityTreeService tAbilityTreeService) {
        this.tCourseAbilityService = tCourseAbilityService;
        this.tCourseService = tCourseService;
        this.tAbilityService = tAbilityService;
        this.tAbilityLevelService = tAbilityLevelService;
        this.tAbilityTypeService = tAbilityTypeService;
        this.tCourseStandardService = tCourseStandardService;
        this.tCourseStandardSService = tCourseStandardSService;
        this.tAbilityTreeService = tAbilityTreeService;
    }


//    /**
//     * 添加课程能力信息
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @LogOperation("添加课程能力信息")
//    @ResponseBody
//    @RequestMapping(value = "/addcourseability", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String addcourseability(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String abilityname = jsonParam.getString("abilityname");
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
//            TCourse tCourse = tCourseService.findById(Long.parseLong(FCourse));
//            String errorstr = "";
//            if (tCourse != null) {
//                if (abilityname != null && !abilityname.equals("")) {
//
//                    //增加新选的
//                    JSONArray powerRoles_Array = JSONArray.parseArray(abilityname);
//                    int forder =1;
//                    List<TCourseAbility> courseAbilityList = null;
//                    List<TCourseAbility> abilityList = null;
//                    for (Object userid : powerRoles_Array) {
//                        Long flevelid = Long.parseLong(ParamTools.getdeParam(userid.toString())); //能力等级ID
//                        TAbilityLevel abilityLevel = tAbilityLevelService.findById(flevelid);
//                        forder =1;
//
//                        TCourseAbilityExample tCourseAbilityExample =new TCourseAbilityExample();
//                        TCourseAbilityExample.Criteria criteria = tCourseAbilityExample.createCriteria();
//                        criteria.andFcourseidEqualTo(Long.parseLong(FCourse));
//                        criteria.andFabilitylevelidEqualTo(flevelid);
//                        abilityList = tCourseAbilityService.findByExample(tCourseAbilityExample);
//                        if (abilityList.size() ==  0) {
//                            tCourseAbilityExample =new TCourseAbilityExample();
//                            criteria = tCourseAbilityExample.createCriteria();
//                            criteria.andFcourseidEqualTo(Long.parseLong(FCourse));
//                            tCourseAbilityExample.setOrderByClause("FOrder desc");
//                            courseAbilityList = tCourseAbilityService.findByExample(tCourseAbilityExample);
//                            if(courseAbilityList.size() >0){
//                                forder = courseAbilityList.get(0).getForder() + 1;
//                            }
//
//                            TCourseAbility tCourseAbility = new TCourseAbility();
//                            tCourseAbility.setFcourseid(Long.parseLong(FCourse));
//                            tCourseAbility.setFabilitylevelid(flevelid);
//                            tCourseAbility.setFabilityid(abilityLevel.getFabilityid());
//                            tCourseAbility.setForder(forder);
//                            tCourseAbility.setFcid(Long.parseLong(uid));
//                            tCourseAbility.setFcdate(new Date());
//                            tCourseAbilityService.save(tCourseAbility);
//                        }else{
//
//                            TAbility serviceById = tAbilityService.findById(abilityLevel.getFabilityid());
//
//                            errorstr += serviceById.getFname() + "/" + abilityLevel.getFname() + "、";
//                        }
//                    }
//                }
//
//                if (!errorstr.equals("")) {
//                    errorstr = errorstr.substring(0, errorstr.length() - 1);
//                    object.put("errorstr", "能力【" + errorstr + "】已存在！");
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
                    int forder =1;
                    List<TCourseAbility> courseAbilityList = null;
                    List<TCourseAbility> abilityList = null;
                    for (Object userid : powerRoles_Array) {
                        Long flevelid = Long.parseLong(ParamTools.getdeParam(userid.toString())); //能力等级ID
//                        System.out.println(flevelid);
                        TAbilityTree abilityLevel = tAbilityTreeService.findById(flevelid);
                        forder =1;
                        TCourseAbilityExample tCourseAbilityExample =new TCourseAbilityExample();
                        TCourseAbilityExample.Criteria criteria = tCourseAbilityExample.createCriteria();
                        criteria.andFcourseidEqualTo(Long.parseLong(FCourse));
                        criteria.andFabilitylevelidEqualTo(flevelid);
                        abilityList = tCourseAbilityService.findByExample(tCourseAbilityExample);
                        if (abilityList.size() ==  0) {
                            tCourseAbilityExample =new TCourseAbilityExample();
                            criteria = tCourseAbilityExample.createCriteria();
                            criteria.andFcourseidEqualTo(Long.parseLong(FCourse));
                            tCourseAbilityExample.setOrderByClause("FOrder desc");
                            courseAbilityList = tCourseAbilityService.findByExample(tCourseAbilityExample);
                            if(courseAbilityList.size() >0){
                                forder = courseAbilityList.get(0).getForder() + 1;
                            }
                            TCourseAbility tCourseAbility = new TCourseAbility();
                            tCourseAbility.setFcourseid(Long.parseLong(FCourse));
                            tCourseAbility.setFabilitylevelid(flevelid);
                            tCourseAbility.setFabilityid(abilityLevel.getFpid());
                            tCourseAbility.setForder(forder);
                            tCourseAbility.setFcid(Long.parseLong(uid));
                            tCourseAbility.setFcdate(new Date());
                            tCourseAbilityService.save(tCourseAbility);
                            //绑定完能力 添加标准设置主表信息
                            insertCourseStandardData(tCourseAbility.getFkeyid(),Long.parseLong(uid));
                        }else{

                            TAbility serviceById = tAbilityService.findById(abilityLevel.getFpid());

                            errorstr += serviceById.getFname() + "/" + abilityLevel.getFname() + "、";
                        }
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

    private void insertCourseStandardData(Long FCourseAbilityID,Long uid) throws Exception {


        TCourseAbility courseAbility = tCourseAbilityService.findById(FCourseAbilityID);


        Long FCourseAbilityLevelID = courseAbility.getFabilitylevelid();
        if (courseAbility != null) {
            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
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
                        tCourseStandard.setFabilityid(courseAbility.getFabilityid());
                        tCourseStandard.setFabilitylevelid(courseAbility.getFabilitylevelid());
                        tCourseStandard.setFabilitycamid(condition.getFKeyID());
                        tCourseStandard.setFtype(-1);
                        tCourseStandard.setFcalctype(-1l);
                        tCourseStandard.setFszstate(-1);
                        tCourseStandard.setFcid(uid);
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
                            tCourseStandard.setFabilityid(courseAbility.getFabilityid());
                            tCourseStandard.setFabilitylevelid(courseAbility.getFabilitylevelid());
                            tCourseStandard.setFabilitycamid(condition.getFKeyID());
                            tCourseStandard.setFtype(-1);
                            tCourseStandard.setFcalctype(-1l);
                            tCourseStandard.setFszstate(-1);
                            tCourseStandard.setFcid(uid);
                            tCourseStandard.setFcdate(new Date());
                            tCourseStandard.setFcourseabilityid(courseAbility.getFkeyid());
                            tCourseStandard.setFcamtype(condition.getFType());
                            tCourseStandardService.save(tCourseStandard);
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取能力及能力等级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/getCourseAbility", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getCourseAbility(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        String abilitytype = jsonParam.getString("abilitytype");
        String courseID = jsonParam.getString("courseID");

        try {
            // 获取数据库记录
            JSONArray ability_Array = new JSONArray();
            // 查询条件
            CourseabilityCS courseabilityCS = new CourseabilityCS();

            if (name != null && !name.equals("")) {

                courseabilityCS.setName(name);
            }
            if (abilitytype != null && !abilitytype.equals("") && !abilitytype.equals("1")) {
                abilitytype = abilitytype == null ? "0" : ParamTools.getdeParam(abilitytype);
                courseabilityCS.setFTypeID(Long.parseLong(abilitytype));
            }
            courseID = courseID ==null ? "0" : ParamTools.getdeParam(courseID);
            courseabilityCS.setFCourseID(Long.parseLong(courseID));

            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                courseabilityCS.setOrderBy(orderSql.substring(1));
            } else {
                courseabilityCS.setOrderBy("tab.FTypeID desc , tab.FName desc");
            }
            PageInfo<CourseabilityData> abilityPageInfo = tCourseAbilityService.getCourseAbility(courseabilityCS, (page - 1) * results, results);
            List<CourseabilityData> ability_list = abilityPageInfo.getList();

            for (CourseabilityData ability : ability_list) {
                JSONObject ability_object = new JSONObject();
                ability_object.put("key", ParamTools.getEnParam(ability.getFLevelID().toString())); //能力等级ID
                ability_object.put("FTypeID", ParamTools.getEnParam(ability.getFTypeID().toString()));
                TAbilityType tAbilityType = tAbilityTypeService.findById(ability.getFTypeID());
                ability_object.put("FTypeName", getPName(tAbilityType.getFpid(),tAbilityType.getFname()));
                ability_object.put("FAbilityName", ability.getFAbilityName());
                ability_object.put("FLevelName", ability.getFLevelName());
                ability_object.put("FAbilityID",ParamTools.getEnParam(ability.getFAbilityID().toString()));
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

    private String getPName(Long pid,String oldname){
        String newname="";
        if(pid == 1){
            newname=oldname;
        }else{
            TAbilityType courseCategory = tAbilityTypeService.findById(pid);
            if(courseCategory!= null){
                newname = courseCategory.getFname() + "/"+ oldname ;
                getPName(courseCategory.getFpid(),newname);
            }

        }
        return newname;
    }

    /**
     * 获取能力及能力等级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/getCourseAbilityList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getCourseAbilityList(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");

        String courseID = jsonParam.getString("FCourseID");

        try {
            // 获取数据库记录
            JSONArray ability_Array = new JSONArray();
            // 查询条件
            CourseabilityCS courseabilityCS = new CourseabilityCS();

            courseID = courseID ==null ? "0" : ParamTools.getdeParam(courseID);

            courseabilityCS.setFCourseID(Long.parseLong(courseID));

//            // 排序
//            String orderSql = "";
//            for (Object order : order_JA) {
//                JSONObject order_Object = (JSONObject) order;
//                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
//                String colName = column_Object.getString("data");
//                orderSql += "," + colName + " " + order_Object.getString("dir");
//            }
//            if (orderSql.length() > 0) {
//                courseabilityCS.setOrderBy(orderSql.substring(1));
//            } else {
//                courseabilityCS.setOrderBy("tab.FTypeID desc , tab.FName desc");
//            }
            courseabilityCS.setOrderBy("tab.FTypeID desc , tab.FName desc");
            PageInfo<CourseabilityData> abilityPageInfo = tCourseAbilityService.getCourseAbilityList(courseabilityCS, (page - 1) * results, results);
            List<CourseabilityData> ability_list = abilityPageInfo.getList();

            for (CourseabilityData ability : ability_list) {
                JSONObject ability_object = new JSONObject();
                ability_object.put("key", ParamTools.getEnParam(ability.getFKeyID().toString())); //能力等级ID
                ability_object.put("FTypeID", ParamTools.getEnParam(ability.getFTypeID().toString()));
                TAbilityType tAbilityType = tAbilityTypeService.findById(ability.getFTypeID());
                ability_object.put("FTypeName", getPName(tAbilityType.getFpid(),tAbilityType.getFname()));
                ability_object.put("FAbilityName", ability.getFAbilityName());
                ability_object.put("FLevelName", ability.getFLevelName());
                ability_object.put("fdardnum", ability.getFdardnum());
                ability_object.put("fstatedardnum", ability.getFstatedardnum());
                ability_object.put("FMethodWeight", ability.getFMethodWeight());
                ability_object.put("FLevelID", ParamTools.getEnParam(ability.getFLevelID().toString()));
                ability_object.put("FAbilityID",ParamTools.getEnParam(ability.getFAbilityID().toString()));

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
     * 删除课程能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除课程能力信息")
    @ResponseBody
    @RequestMapping(value = "/delcourseability", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delcourseability(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            TCourseAbility tCourseAbility = tCourseAbilityService.findById(Long.parseLong(id));

            TCourseStandardExample tCourseStandardExample = new TCourseStandardExample();
            tCourseStandardExample.createCriteria().andFcourseabilityidEqualTo(Long.parseLong(id));
            List<TCourseStandard> courseStandardList = tCourseStandardService.findByExample(tCourseStandardExample);
            if (courseStandardList.size() > 0) {
                for (TCourseStandard courseStandard : courseStandardList) {
                    TCourseStandardSExample tCourseStandardSExample = new TCourseStandardSExample();
                    tCourseStandardSExample.createCriteria().andFcsidEqualTo(courseStandard.getFkeyid());
                    tCourseStandardSService.deleteByByExample(tCourseStandardSExample);
                    tCourseStandardService.deleteById(courseStandard.getFkeyid());
                }
            }
            tCourseAbilityService.deleteById(Long.parseLong(id));
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
     * 根据ID获取课程能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycourseabilityInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycourseabilityInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);

            TCourseAbility courseAbility = tCourseAbilityService.findById(key);

            JSONObject courseability_object = new JSONObject();
            courseability_object.put("key", ParamTools.getEnParam(courseAbility.getFkeyid().toString()));
            courseability_object.put("FMethodWeight", courseAbility.getFmethodweight());
            TAbilityTree tAbility = tAbilityTreeService.findById(courseAbility.getFabilityid());
            TAbilityTree tAbilityLevel = tAbilityTreeService.findById(courseAbility.getFabilitylevelid());
            courseability_object.put("FAbilityName", tAbility == null ? "" : tAbility.getFname());
            courseability_object.put("FAbilityLevelName", tAbilityLevel == null ? "" : tAbilityLevel.getFname());
            if (tAbility!=null){
                TAbilityType tAbilityType = tAbilityTypeService.findById(tAbility.getFtypeid());
                courseability_object.put("FAbilityType", tAbilityType == null ? "" : tAbilityType.getFname());
            }else{
                courseability_object.put("FAbilityType", "");
            }
            // 返回值
            object.put("info", courseability_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 修改课程能力权重
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改课程能力权重")
    @ResponseBody
    @RequestMapping(value = "/updatecourseabilityqz", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatecourseabilityqz(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FCourseID = jsonParam.getString("FCourseID");
        float FMethodWeight = jsonParam.getFloat("FMethodWeight");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            FCourseID = FCourseID == null ? "0" : ParamTools.getdeParam(FCourseID);
            long key = Long.parseLong(id);
            String CookiesLogindeptID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogindeptID != null && !CookiesLogindeptID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLogindeptID);
            }

            float FMethodWeight2 = 0;
            TCourseAbilityExample tCourseAbilityExample = new TCourseAbilityExample();
            tCourseAbilityExample.createCriteria().andFcourseidEqualTo(Long.parseLong(FCourseID)).andFkeyidNotEqualTo(key).andFstateEqualTo(1);
            List<TCourseAbility> courseAbilities = tCourseAbilityService.findByExample(tCourseAbilityExample);

//            System.out.println(courseAbilities.size());
//            System.out.println(FCourseID);
            if (courseAbilities.size() > 0) {
                for (TCourseAbility tCourseAbility : courseAbilities) {
                    FMethodWeight2 += tCourseAbility.getFmethodweight() == null ? 0 : tCourseAbility.getFmethodweight();
                }
            }
            if (100 - FMethodWeight2 - FMethodWeight >= 0) {
                TCourseAbility tCourseAbility = new TCourseAbility();
                tCourseAbility.setFkeyid(key);
                tCourseAbility.setFmethodweight(FMethodWeight);
                tCourseAbility.setFuid(Long.parseLong(uid));
                tCourseAbility.setFudate(new Date());
                tCourseAbilityService.update(tCourseAbility);
                // 返回值
                object.put("result", "success");
            }else {
                object.put("result", "error2");
                object.put("error", "总权重值超出了100的上限");
                object.put("FMethodWeight2", 100 - FMethodWeight2);
            }



        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

}