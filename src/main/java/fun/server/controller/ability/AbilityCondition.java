package fun.server.controller.ability;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
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
 * 能力等级条件管理 相关业务处理
 */
@RestController
@RequestMapping("/s/abilitycondition")
public class AbilityCondition {

    private final TAbilityConditionService tAbilityConditionService;

    private final TAbilityLevelService tAbilityLevelService;

    private final TAbilityService tAbilityService;

    private final TAbilityConditionAssessmentMethodService tAbilityConditionAssessmentMethodService;

    private final TAbilityAssessmentMethodService tAbilityAssessmentMethodService;

    private final TAbilityConditionSService tAbilityConditionSService;

    public AbilityCondition(TAbilityConditionService tAbilityConditionService, TAbilityLevelService tAbilityLevelService, TAbilityService tAbilityService, TAbilityConditionAssessmentMethodService tAbilityConditionAssessmentMethodService, TAbilityAssessmentMethodService tAbilityAssessmentMethodService, TAbilityConditionSService tAbilityConditionSService) {
        this.tAbilityConditionService = tAbilityConditionService;
        this.tAbilityLevelService = tAbilityLevelService;
        this.tAbilityService = tAbilityService;
        this.tAbilityConditionAssessmentMethodService = tAbilityConditionAssessmentMethodService;
        this.tAbilityAssessmentMethodService = tAbilityAssessmentMethodService;
        this.tAbilityConditionSService = tAbilityConditionSService;
    }


    /**
     * 获取能力等级条件信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilitycondition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilitycondition(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String FALID = jsonParam.getString("FALID");
        Integer dataall = jsonParam.getInteger("dataall");


        try {
            // 获取数据库记录
            JSONArray abilitycondition_Array = new JSONArray();
            // 查询条件
            TAbilityConditionExample tAbilityConditionExample = new TAbilityConditionExample();
            TAbilityConditionExample.Criteria criteria = tAbilityConditionExample.createCriteria();
            FALID = FALID == null ? "0" : ParamTools.getdeParam(FALID);
            criteria.andFalidEqualTo(Long.parseLong(FALID));
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                tAbilityConditionExample.setOrderByClause(orderSql.substring(1));
            } else {
                tAbilityConditionExample.setOrderByClause("FCDATE asc , FConditionName asc");
            }
            PageInfo<TAbilityCondition> abilityconditionPageInfo = tAbilityConditionService.findByExampleMapper(tAbilityConditionExample, (page - 1) * results, results);
            List<TAbilityCondition> abilitycondition_list = abilityconditionPageInfo.getList();
            float FMethodWeight2 = 0;
            for (TAbilityCondition abilitycondition : abilitycondition_list) {
                FMethodWeight2 = 0;
                JSONObject abilitycondition_object = new JSONObject();
                abilitycondition_object.put("key", ParamTools.getEnParam(abilitycondition.getFkeyid().toString()));
                if (dataall == 1) {

                    abilitycondition_object.put("FConditionName", abilitycondition.getFconditionname() == null ? "" : abilitycondition.getFconditionname());
                    abilitycondition_object.put("FConditionScore", abilitycondition.getFconditionscore() == null ? 0 : abilitycondition.getFconditionscore());
                    abilitycondition_object.put("FNote", abilitycondition.getFnote() == null ? "" : abilitycondition.getFnote());
                    abilitycondition_object.put("FMethodWeight", abilitycondition.getFmethodweight() == null ? 0 : abilitycondition.getFmethodweight());
//                    abilitycondition_object.put("FConditionType", abilitycondition.getFconditiontype() == null ? -1 : abilitycondition.getFconditiontype());
//                    abilitycondition_object.put("FConditionValue", abilitycondition.getFconditionvalue() == null ? "" : abilitycondition.getFconditionvalue());
                    abilitycondition_object.put("FCID", abilitycondition.getFcid());
                    abilitycondition_object.put("FUID", abilitycondition.getFuid());
                    abilitycondition_object.put("FCDATE", abilitycondition.getFcdate());
                    abilitycondition_object.put("FUDATE", abilitycondition.getFudate());

                    TAbilityConditionAssessmentMethodExample abilityConditionAssessmentMethodExample2 = new TAbilityConditionAssessmentMethodExample();
                    TAbilityConditionAssessmentMethodExample.Criteria criteria2 = abilityConditionAssessmentMethodExample2.createCriteria();
                    criteria2.andFconditionidEqualTo(abilitycondition.getFkeyid());
                    criteria2.andFtypeEqualTo(1);
                    List<TAbilityConditionAssessmentMethod> abilityConditionAssessmentMethodList = tAbilityConditionAssessmentMethodService.findByExample(abilityConditionAssessmentMethodExample2);
                    if (abilityConditionAssessmentMethodList.size() > 0) {
                        for (TAbilityConditionAssessmentMethod abilityConditionAssessmentMethod : abilityConditionAssessmentMethodList) {
                            FMethodWeight2 += abilityConditionAssessmentMethod.getFmethodweight();
                        }
                    }
                    abilitycondition_object.put("FMethodWeight", String.format("%.2f", FMethodWeight2));
                } else {
                    abilitycondition_object.put("FConditionName", "*****");
                    abilitycondition_object.put("FNote", "*****");
                    abilitycondition_object.put("FConditionScore", "*****");
                    abilitycondition_object.put("FConditionType", "*****");
                    abilitycondition_object.put("FConditionValue", "*****");
                    abilitycondition_object.put("FCID", "*****");
                    abilitycondition_object.put("FUID", "*****");
                    abilitycondition_object.put("FCDATE", "*****");
                    abilitycondition_object.put("FUDATE", "*****");
                }

                abilitycondition_object.put("FState", abilitycondition.getFstate());
                abilitycondition_Array.add(abilitycondition_object);
            }
            // 返回值
            object.put("list", abilitycondition_Array);
            object.put("total", abilityconditionPageInfo.getTotal()); // 总行数
            object.put("page", abilityconditionPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取能力等级考核条件信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilitycondition_xl", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilitycondition_xl(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray ability_Array = new JSONArray();
            TAbilityExample abilityExample = new TAbilityExample();
            TAbilityExample.Criteria criteria = abilityExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            abilityExample.setOrderByClause("FMethodName asc");
            List<TAbilityCondition> tAbilityAssessmentMethods = tAbilityConditionService.findByExample(abilityExample);
            for (TAbilityCondition ability : tAbilityAssessmentMethods) {
                JSONObject ability_object = new JSONObject();
                ability_object.put("id", ParamTools.getEnParam(ability.getFkeyid().toString()));
                ability_object.put("text", ability.getFconditionname());
                ability_Array.add(ability_object);
            }
            // 返回值
            object.put("list", ability_Array);
            object.put("result", "success");
            object.put("results", ability_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 根据ID获取能力等级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilityconditionInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilityconditionInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询能力等级信息
            TAbilityCondition abilitylevel = tAbilityConditionService.findById(key);
            JSONObject abilitylevel_object = new JSONObject();
            abilitylevel_object.put("key", ParamTools.getEnParam(abilitylevel.getFkeyid().toString()));
            abilitylevel_object.put("FALID", ParamTools.getEnParam(abilitylevel.getFalid().toString()));
            abilitylevel_object.put("FConditionName", abilitylevel.getFconditionname() == null ? "" : abilitylevel.getFconditionname());
//            abilitylevel_object.put("FConditionType", abilitylevel.getFconditiontype() == null ? -1 : abilitylevel.getFconditiontype());
//            abilitylevel_object.put("FConditionValue", abilitylevel.getFconditionvalue() == null ? "" : abilitylevel.getFconditionvalue());
            abilitylevel_object.put("FConditionScore", abilitylevel.getFconditionscore() == null ? 0 : abilitylevel.getFconditionscore());
            abilitylevel_object.put("FNote", abilitylevel.getFnote() == null ? "" : abilitylevel.getFnote());
            abilitylevel_object.put("FMethodWeight", abilitylevel.getFmethodweight() == null ? 0 : abilitylevel.getFmethodweight());
            abilitylevel_object.put("FCID", abilitylevel.getFcid());
            abilitylevel_object.put("FUID", abilitylevel.getFuid());
            abilitylevel_object.put("FCDATE", abilitylevel.getFcdate());
            abilitylevel_object.put("FUDATE", abilitylevel.getFudate());
            abilitylevel_object.put("FState", abilitylevel.getFstate());
            // 返回值
            object.put("info", abilitylevel_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加能力等级与等级条件信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加能力等级与等级条件信息")
    @ResponseBody
    @RequestMapping(value = "/addabilitylevelCondition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addabilitylevelCondition(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FNote = jsonParam.getString("FNote");
        String FName = jsonParam.getString("FName");
        int FScoreMin = jsonParam.getInteger("FScoreMin");
        int FPoints = jsonParam.getInteger("FPoints");
        int FScoreMax = jsonParam.getInteger("FScoreMax");
        String FAbilityID = jsonParam.getString("FAbilityID");
//        JSONArray tiaojian = jsonParam.getJSONArray("tiaojian");

        try {

            FAbilityID = FAbilityID == null ? "0" : ParamTools.getdeParam(FAbilityID);
            TAbilityLevelExample abilitylevelExample = new TAbilityLevelExample();
            TAbilityLevelExample.Criteria criteria = abilitylevelExample.createCriteria();
            criteria.andFnameEqualTo(FName);
            criteria.andFabilityidEqualTo(Long.parseLong(FAbilityID));
            List<TAbilityLevel> levelList = tAbilityLevelService.findByExample(abilitylevelExample);
            if (levelList.size() == 0) {
                String CookiesLoginabilitylevelID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilitylevelID != null && !CookiesLoginabilitylevelID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilitylevelID);
                }
                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
                long key = idWorker.nextId();
                // 新建能力等级信息
                TAbilityLevel abilitylevel = new TAbilityLevel();
                abilitylevel.setFkeyid(key);
                abilitylevel.setFname(FName);
                abilitylevel.setFabilityid(Long.parseLong(FAbilityID));
                abilitylevel.setFnote(FNote);
                abilitylevel.setFpoints(FPoints);
                abilitylevel.setFscoremax(FScoreMax);
                abilitylevel.setFscoremin(FScoreMin);
                abilitylevel.setFcid(Long.parseLong(uid));
                abilitylevel.setFcdate(new Date());
                tAbilityLevelService.save(abilitylevel);


//                for (Object o : tiaojian) {
//                    JSONObject tiaojian_Object = (JSONObject) o;
//                    String fType = tiaojian_Object.getString("FType");
//                    String fName = tiaojian_Object.getString("FName");
//                    String Value = tiaojian_Object.getString("Value");
//                    String Score = tiaojian_Object.getString("Score");
//                    String methodID = tiaojian_Object.getString("MethodID");
//                    String methodWeight = tiaojian_Object.getString("MethodWeight");
////                    System.out.println(fType);
////                    System.out.println(fName);
////                    System.out.println(Value);
////                    System.out.println(Score);
////                    System.out.println("-----------------------------");
//                    long key2 = idWorker.nextId();
//                    TAbilityCondition condition = new TAbilityCondition();
//                    condition.setFkeyid(key2);
//                    condition.setFalid(key);
//                    condition.setFconditionname(fName);
//                    condition.setFconditiontype(Integer.parseInt(fType));
//                    condition.setFconditionscore(Integer.parseInt(Score));
//                    condition.setFconditionvalue(Value);
//                    tAbilityConditionService.save(condition);
//
//                    TAbilityConditionAssessmentMethod tAbilityConditionAssessmentMethod = new TAbilityConditionAssessmentMethod();
//                    tAbilityConditionAssessmentMethod.setFalid(key);
//                    methodID = methodID == null ? "0" : ParamTools.getdeParam(methodID);
//                    tAbilityConditionAssessmentMethod.setFmethodid(Long.parseLong(methodID));
//                    tAbilityConditionAssessmentMethod.setFconditionid(key2);
//                    tAbilityConditionAssessmentMethod.setFmethodweight(Float.parseFloat(methodWeight));
//                    tAbilityConditionAssessmentMethodService.save(tAbilityConditionAssessmentMethod);
//                }
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
     * 新增能力等级考核条件信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("新增能力等级考核条件信息")
    @ResponseBody
    @RequestMapping(value = "/newaddabilitylevelCondition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String newaddabilitylevelCondition(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);

        String FConditionName = jsonParam.getString("FConditionName");
        String FNote = jsonParam.getString("FNote");
//        String FConditionValue = jsonParam.getString("FConditionValue");
//        int FConditionType = jsonParam.getInteger("FConditionType");
        String FConditionScore = jsonParam.getString("FConditionScore");

        String FAbilityLevelID = jsonParam.getString("FAbilityLevelID");
        float FMethodWeight = jsonParam.getFloat("FMethodWeight");
        try {

            FAbilityLevelID = FAbilityLevelID == null ? "0" : ParamTools.getdeParam(FAbilityLevelID);


//            //获取等级最高分数
//            TAbilityLevel tAbilityLevel = tAbilityLevelService.findById(Long.parseLong(FAbilityLevelID));
//            float maxscore = tAbilityLevel.getFscoremax();
//            TAbilityConditionExample conditionExample2 = new TAbilityConditionExample();
//            TAbilityConditionExample.Criteria criteria2 = conditionExample2.createCriteria();
//            criteria2.andFalidEqualTo(Long.parseLong(FAbilityLevelID));
//            List<TAbilityCondition> abilityConditionList = tAbilityConditionService.findByExample(conditionExample2);
//            float sumscore=0;
//            for (TAbilityCondition condition : abilityConditionList) {
//                sumscore += condition.getFconditionscore();
//            }


//            if(maxscore - sumscore - Integer.parseInt(FConditionScore) >= 0){
            float FMethodWeight2 = 0;
            TAbilityConditionExample conditionExample = new TAbilityConditionExample();
            conditionExample.createCriteria().andFalidEqualTo(Long.parseLong(FAbilityLevelID));
            List<TAbilityCondition> abilityConditions = tAbilityConditionService.findByExample(conditionExample);
            if (abilityConditions.size() > 0) {
                for (TAbilityCondition abilityCondition : abilityConditions) {
                    FMethodWeight2 += abilityCondition.getFmethodweight() == null ? 0 : abilityCondition.getFmethodweight();
                }
            }
            if (100 - FMethodWeight2 - FMethodWeight >= 0) {
                conditionExample = new TAbilityConditionExample();
                TAbilityConditionExample.Criteria criteria = conditionExample.createCriteria();
                criteria.andFconditionnameEqualTo(FConditionName);
                criteria.andFalidEqualTo(Long.parseLong(FAbilityLevelID));
                List<TAbilityCondition> conditionList = tAbilityConditionService.findByExample(conditionExample);
                if (conditionList.size() == 0) {
                    String CookiesLoginabilitylevelID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                    String uid = ""; // 当前登录用户ID
                    if (CookiesLoginabilitylevelID != null && !CookiesLoginabilitylevelID.equals("")) {
                        uid = ParamTools.getdeParam(CookiesLoginabilitylevelID);
                    }
                    TAbilityCondition condition = new TAbilityCondition();
                    condition.setFalid(Long.parseLong(FAbilityLevelID));
                    condition.setFconditionname(FConditionName);
//                condition.setFconditiontype(FConditionType);
                    condition.setFconditionscore(Float.parseFloat(FConditionScore));
//                condition.setFconditionvalue(FConditionValue);
                    condition.setFnote(FNote);
                    condition.setFmethodweight(FMethodWeight);
                    condition.setFcid(Long.parseLong(uid));
                    condition.setFcdate(new Date());
                    tAbilityConditionService.save(condition);
                    // 返回值
                    object.put("result", "success");
                } else {
                    // 返回值
                    object.put("result", "fail");
                }
            } else {
                object.put("result", "error2");
                object.put("error", "总权重值超出了100的上限");
                object.put("FMethodWeight2", 100 - FMethodWeight2);
            }

//            }else{
//
//                object.put("result", "error");
//                object.put("error", "条件分数总和大于能力等级最高分数，请重新输入！");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取能力等级考核方式信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilityconditionqz", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilityconditionqz(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            float FMethodWeight = 0;
            TAbilityConditionExample conditionExample = new TAbilityConditionExample();
            conditionExample.createCriteria().andFalidEqualTo(key);
            List<TAbilityCondition> abilityConditions = tAbilityConditionService.findByExample(conditionExample);
            if (abilityConditions.size() > 0) {
                for (TAbilityCondition abilityCondition : abilityConditions) {
                    FMethodWeight += abilityCondition.getFmethodweight() == null ? 0 : abilityCondition.getFmethodweight();
                }
            }
            object.put("FMethodWeight", (100 - FMethodWeight) < 0 ? 0 : (100 - FMethodWeight));
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 修改能力等级考核条件信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改能力等级考核条件信息")
    @ResponseBody
    @RequestMapping(value = "/updateabilitylevelCondition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateabilitylevelCondition(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FConditionName = jsonParam.getString("FConditionName");
//        String FConditionValue = jsonParam.getString("FConditionValue");
//        int FConditionType = jsonParam.getInteger("FConditionType");
        String FConditionScore = jsonParam.getString("FConditionScore");
        String FNote = jsonParam.getString("FNote");
        String FAbilityID = jsonParam.getString("FAbilityID");
        String FAbilityLevelID = jsonParam.getString("FAbilityLevelID");
        float FMethodWeight = jsonParam.getFloat("FMethodWeight");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            FAbilityID = FAbilityID == null ? "0" : ParamTools.getdeParam(FAbilityID);
            FAbilityLevelID = FAbilityLevelID == null ? "0" : ParamTools.getdeParam(FAbilityLevelID);
//            TAbilityLevel tAbilityLevel = tAbilityLevelService.findById(Long.parseLong(FAbilityLevelID));
//            float maxscore = tAbilityLevel.getFscoremax();
//
//            TAbilityConditionExample conditionExample2 = new TAbilityConditionExample();
//            TAbilityConditionExample.Criteria criteria2 = conditionExample2.createCriteria();
//            criteria2.andFalidEqualTo(Long.parseLong(FAbilityLevelID));
//            criteria2.andFkeyidNotEqualTo(Long.parseLong(id));
//            List<TAbilityCondition> abilityConditionList = tAbilityConditionService.findByExample(conditionExample2);
//            float sumscore = 0;
//            for (TAbilityCondition condition : abilityConditionList) {
//                sumscore += condition.getFconditionscore();
//            }
//            if (maxscore - sumscore - Integer.parseInt(FConditionScore) >= 0) {
            float FMethodWeight2 = 0;
            TAbilityConditionExample conditionExample = new TAbilityConditionExample();
            conditionExample.createCriteria().andFalidEqualTo(Long.parseLong(FAbilityLevelID)).andFkeyidNotEqualTo(Long.parseLong(id));
            List<TAbilityCondition> abilityConditions = tAbilityConditionService.findByExample(conditionExample);
            if (abilityConditions.size() > 0) {
                for (TAbilityCondition abilityCondition : abilityConditions) {
                    FMethodWeight2 += abilityCondition.getFmethodweight() == null ? 0 : abilityCondition.getFmethodweight();
                }
            }
            if (100 - FMethodWeight2 - FMethodWeight >= 0) {
                conditionExample = new TAbilityConditionExample();
                TAbilityConditionExample.Criteria criteria = conditionExample.createCriteria();
                criteria.andFkeyidNotEqualTo(Long.parseLong(id));
                criteria.andFconditionnameEqualTo(FConditionName);
                criteria.andFalidEqualTo(Long.parseLong(FAbilityLevelID));
                List<TAbilityCondition> conditionList = tAbilityConditionService.findByExample(conditionExample);
                if (conditionList.size() == 0) {
                    String CookiesLoginabilitylevelID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                    String uid = ""; // 当前登录用户ID
                    if (CookiesLoginabilitylevelID != null && !CookiesLoginabilitylevelID.equals("")) {
                        uid = ParamTools.getdeParam(CookiesLoginabilitylevelID);
                    }
                    TAbilityCondition condition = new TAbilityCondition();
                    condition.setFkeyid(Long.parseLong(id));
                    condition.setFconditionname(FConditionName);
//                condition.setFconditiontype(FConditionType);
                    condition.setFconditionscore(Float.parseFloat(FConditionScore));
//                condition.setFconditionvalue(FConditionValue);
                    condition.setFnote(FNote);
                    condition.setFmethodweight(FMethodWeight);
                    condition.setFuid(Long.parseLong(uid));
                    condition.setFudate(new Date());
                    tAbilityConditionService.update(condition);
                    // 返回值
                    object.put("result", "success");
                } else {
                    // 返回值
                    object.put("result", "fail");
                }
            } else {
                object.put("result", "error2");
                object.put("error", "总权重值超出了100的上限");
                object.put("FMethodWeight2", 100 - FMethodWeight2);
            }
//            } else {
//                object.put("result", "error");
//                object.put("error", "条件分数总和大于能力等级最高分数，请重新输入！");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 删除能力等级考核条件信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除能力等级考核条件信息")
    @ResponseBody
    @RequestMapping(value = "/delabilityCondition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delabilityCondition(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TAbilityConditionAssessmentMethodExample conditionExample = new TAbilityConditionAssessmentMethodExample();
            TAbilityConditionAssessmentMethodExample.Criteria criteria = conditionExample.createCriteria();
            criteria.andFconditionidEqualTo(Long.parseLong(id));
            tAbilityConditionAssessmentMethodService.deleteByByExample(conditionExample);
            tAbilityConditionService.deleteById(Long.parseLong(id));
            TAbilityConditionSExample conditionExample2 = new TAbilityConditionSExample();
            TAbilityConditionSExample.Criteria criteria2 = conditionExample2.createCriteria();
            criteria2.andFpidEqualTo(Long.parseLong(id));
            List<TAbilityConditionS> conditionSList = tAbilityConditionSService.findByExample(conditionExample2);
            if (conditionSList.size() > 0) {
                for (TAbilityConditionS condition : conditionSList) {
                    TAbilityConditionSExample conditionExample3 = new TAbilityConditionSExample();
                    conditionExample3.createCriteria().andFpathLike(condition.getFpath() + "%");
                    tAbilityConditionSService.deleteByByExample(conditionExample3);
                    tAbilityConditionSService.deleteById(condition.getFkeyid());
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
     * 获取能力、等级。条件考核的树形结构信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querabilitytree", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querabilitytree(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FAbilityID = jsonParam.getString("FAbilityID");
        String FAbilityLevelId = jsonParam.getString("FAbilityLevelId");
        Long fabilitylevelid = 1l;
        try {

//            { id:1, pId:0, name:"父节点1", open:true},
////            { id:11, pId:1, name:"父节点11"},
////            { id:111, pId:11, name:"叶子节点111"},
////            { id:112, pId:11, name:"叶子节点112"},
////            { id:113, pId:11, name:"叶子节点113"},
////            { id:114, pId:11, name:"叶子节点114"},
////            { id:12, pId:1, name:"父节点12"},
////            { id:121, pId:12, name:"叶子节点121"},
////            { id:122, pId:12, name:"叶子节点122"},
            if (FAbilityLevelId != null && !FAbilityLevelId.equals("")) {
                fabilitylevelid = Long.parseLong(ParamTools.getdeParam(FAbilityLevelId));
            }
            FAbilityID = FAbilityID == null ? "0" : ParamTools.getdeParam(FAbilityID);
            TAbility abilityServiceById = tAbilityService.findById(Long.parseLong(FAbilityID));
            float FMethodWeight2 = 0;
            float fenshu = 0;
            float ftjfenshu = 0;
            TAbilityConditionExample tAbilityConditionExample = null;
            float FConditionScore = 0;
            // 获取数据库记录
            JSONArray filetype_Array = new JSONArray();
            //查询文件分组
            JSONObject filetype_object = new JSONObject();
            filetype_object = new JSONObject();
            filetype_object.put("id", ParamTools.getEnParam(abilityServiceById.getFkeyid().toString()));
            filetype_object.put("pId", ParamTools.getEnParam("-1"));
            filetype_object.put("name", abilityServiceById.getFname());
            filetype_object.put("islevle", 1);//是否可以添加等级 1-可以 2-不可以
            filetype_object.put("istj", 2);//是否可以添加条件 1-可以 2-不可以
            filetype_object.put("iskh", 2);//是否可以添加考核 1-可以 2-不可以
            filetype_object.put("ftype", -1);// -1根节点 1=等级 2=条件 3=考核
            filetype_object.put("open", true);
            filetype_object.put("iconSkin", "school-icon");
            filetype_Array.add(filetype_object);
            TAbilityAssessmentMethod serviceById = null;
            //查询所有等级
            TAbilityLevelExample abilityLevelExample = new TAbilityLevelExample();
            TAbilityLevelExample.Criteria criteria = abilityLevelExample.createCriteria();
            criteria.andFabilityidEqualTo(abilityServiceById.getFkeyid());
            criteria.andFstateEqualTo(1);
            if (fabilitylevelid > 1) {
                criteria.andFkeyidEqualTo(fabilitylevelid);
            }
            List<TAbilityLevel> tAbilityLevelList = tAbilityLevelService.findByExample(abilityLevelExample);
            if (tAbilityLevelList.size() > 0) {
                for (TAbilityLevel tAbilityLevel : tAbilityLevelList) {
                    FConditionScore = 0;
                    tAbilityConditionExample = new TAbilityConditionExample();
                    tAbilityConditionExample.createCriteria().andFalidEqualTo(tAbilityLevel.getFkeyid());
                    List<TAbilityCondition> tAbilityConditionList = tAbilityConditionService.findByExample(tAbilityConditionExample);
                    for (TAbilityCondition condition : tAbilityConditionList) {
//                        FConditionScore += condition.getFconditionscore();
                        FConditionScore += condition.getFmethodweight();
                    }

                    filetype_object = new JSONObject();
                    filetype_object.put("id", ParamTools.getEnParam(tAbilityLevel.getFkeyid().toString()));
                    filetype_object.put("pId", ParamTools.getEnParam(abilityServiceById.getFkeyid().toString()));
//                    filetype_object.put("name", tAbilityLevel.getFname() == null ? "" : tAbilityLevel.getFname() + "【等级分数：" + tAbilityLevel.getFscoremin() + " ~ " + tAbilityLevel.getFscoremax() + "】" + "【条件总分：" + String.format("%.2f", FConditionScore) + "】");
                    filetype_object.put("name", tAbilityLevel.getFname() == null ? "" : tAbilityLevel.getFname() + "【等级分数：" + tAbilityLevel.getFscoremin() + " ~ " + tAbilityLevel.getFscoremax() + "】" + "【下级条件总权重：" + String.format("%.2f", FConditionScore) + "】");
                    filetype_object.put("AbilityName", abilityServiceById.getFname());
                    filetype_object.put("islevle", 2);//是否可以添加等级 1-可以 2-不可以
                    filetype_object.put("istj", 1);//是否可以添加条件 1-可以 2-不可以
                    filetype_object.put("iskh", 2);//是否可以添加考核 1-可以 2-不可以
                    filetype_object.put("ftype", 1);// -1根节点 1=等级 2=条件 3=考核
                    filetype_object.put("open", false);
                    filetype_object.put("iconSkin", "college-icon");
                    filetype_Array.add(filetype_object);

                    //查询所有条件
                    TAbilityConditionExample conditionExample = new TAbilityConditionExample();
                    TAbilityConditionExample.Criteria criteria1 = conditionExample.createCriteria();
                    criteria1.andFalidEqualTo(tAbilityLevel.getFkeyid());
                    criteria1.andFstateEqualTo(1);
                    conditionExample.setOrderByClause("FCDATE asc");
                    List<TAbilityCondition> abilityConditionList = tAbilityConditionService.findByExample(conditionExample);
                    if (abilityConditionList.size() > 0) {

                        for (TAbilityCondition tAbilityCondition : abilityConditionList) {
                            FMethodWeight2 = 0;
                            TAbilityConditionAssessmentMethodExample abilityConditionAssessmentMethodExample2 = new TAbilityConditionAssessmentMethodExample();
                            TAbilityConditionAssessmentMethodExample.Criteria criteria22 = abilityConditionAssessmentMethodExample2.createCriteria();
                            criteria22.andFconditionidEqualTo(tAbilityCondition.getFkeyid());
                            criteria22.andFtypeEqualTo(1);
                            List<TAbilityConditionAssessmentMethod> abilityConditionAssessmentMethodList = tAbilityConditionAssessmentMethodService.findByExample(abilityConditionAssessmentMethodExample2);
                            if (abilityConditionAssessmentMethodList.size() > 0) {
                                for (TAbilityConditionAssessmentMethod abilityConditionAssessmentMethod : abilityConditionAssessmentMethodList) {
                                    FMethodWeight2 += abilityConditionAssessmentMethod.getFmethodweight();
                                }
                            }
                            fenshu = 0;
                            TAbilityConditionSExample conditionExample2 = new TAbilityConditionSExample();
                            conditionExample2.createCriteria()
                                    .andFpidEqualTo(tAbilityCondition.getFkeyid())
                                    .andFstateEqualTo(1);
                            List<TAbilityConditionS> conditionSList1 = tAbilityConditionSService.findByExample(conditionExample2);
                            if (conditionSList1.size() > 0) {
                                for (TAbilityConditionS tAbilityConditionS : conditionSList1) {
//                                    fenshu += tAbilityConditionS.getFconditionscore();
                                    fenshu += tAbilityConditionS.getFmethodweight() == null ? 0 : tAbilityConditionS.getFmethodweight();
                                }
                            }

                            filetype_object = new JSONObject();
                            filetype_object.put("id", ParamTools.getEnParam(tAbilityCondition.getFkeyid().toString()));
                            filetype_object.put("pId", ParamTools.getEnParam(tAbilityLevel.getFkeyid().toString()));
//                            filetype_object.put("name", tAbilityCondition.getFconditionname() == null ? "" : tAbilityCondition.getFconditionname() + "【条件分数：" + String.format("%.2f", tAbilityCondition.getFconditionscore()) + "】" + "【子条件总分数：" + String.format("%.2f", fenshu) + "】" + "【总权重：" + String.format("%.2f", FMethodWeight2) + "%】");
//                            filetype_object.put("name", tAbilityCondition.getFconditionname() == null ? "" : tAbilityCondition.getFconditionname() + "【条件达标分数：" + String.format("%.2f", tAbilityCondition.getFconditionscore()) + "】" + "【当前条件总权重：" + String.format("%.2f", tAbilityCondition.getFmethodweight() == null ? 0 : tAbilityCondition.getFmethodweight()) + "%】"+ "【下级条件总权重：" + String.format("%.2f", fenshu) + "%】" + "【考核方式总权重：" + String.format("%.2f", FMethodWeight2) + "%】");
                            filetype_object.put("name", tAbilityCondition.getFconditionname() == null ? "" : tAbilityCondition.getFconditionname() + "【条件达标分数：" + String.format("%.2f", tAbilityCondition.getFconditionscore()) + "】" + "【当前条件总权重：" + String.format("%.2f", tAbilityCondition.getFmethodweight() == null ? 0 : tAbilityCondition.getFmethodweight()) + "%】"+ "【下级条件+考核方式总权重：" + String.format("%.2f", (fenshu + FMethodWeight2)) + "%】");
                            filetype_object.put("AbilityID", ParamTools.getEnParam(abilityServiceById.getFkeyid().toString()));
                            filetype_object.put("LevelName", tAbilityLevel.getFname());
                            filetype_object.put("AbilityName", abilityServiceById.getFname());
                            filetype_object.put("ConditionName", tAbilityCondition.getFconditionname() == null ? "" : tAbilityCondition.getFconditionname());
                            filetype_object.put("islevle", 2);//是否可以添加等级 1-可以 2-不可以
                            filetype_object.put("istj", 2);//是否可以添加条件 1-可以 2-不可以
                            filetype_object.put("iskh", 1);//是否可以添加考核 1-可以 2-不可以
                            filetype_object.put("ftype", 2);// -1根节点 1=等级 2=条件 3=考核
                            filetype_object.put("ftypes", 1);// -1根节点 1=等级 2=条件 3=考核
                            filetype_object.put("open", false);
                            filetype_object.put("iconSkin", "major-icon");
                            filetype_Array.add(filetype_object);


                            //每个条件下有哪考核方式
                            TAbilityConditionAssessmentMethodExample conditionAssessmentMethodExample = new TAbilityConditionAssessmentMethodExample();
                            TAbilityConditionAssessmentMethodExample.Criteria criteria2 = conditionAssessmentMethodExample.createCriteria();
                            criteria2.andFalidEqualTo(tAbilityLevel.getFkeyid());
                            criteria2.andFconditionidEqualTo(tAbilityCondition.getFkeyid());
                            criteria2.andFstateEqualTo(1);
                            List<TAbilityConditionAssessmentMethod> conditionAssessmentMethodList = tAbilityConditionAssessmentMethodService.findByExample(conditionAssessmentMethodExample);
                            if (conditionAssessmentMethodList.size() > 0) {
                                for (TAbilityConditionAssessmentMethod tAbilityConditionAssessmentMethod : conditionAssessmentMethodList) {
                                    serviceById = tAbilityAssessmentMethodService.findById(tAbilityConditionAssessmentMethod.getFmethodid());
                                    filetype_object = new JSONObject();
                                    filetype_object.put("id", ParamTools.getEnParam(tAbilityConditionAssessmentMethod.getFkeyid().toString()));
                                    filetype_object.put("pId", ParamTools.getEnParam(tAbilityCondition.getFkeyid().toString()));
                                    filetype_object.put("name", serviceById == null ? "" : serviceById.getFmethodname() + "【权重：" + String.format("%.2f", tAbilityConditionAssessmentMethod.getFmethodweight()) + "%】");
                                    filetype_object.put("LevelID", ParamTools.getEnParam(tAbilityLevel.getFkeyid().toString()));
                                    filetype_object.put("LevelName", tAbilityLevel.getFname());
                                    filetype_object.put("AbilityName", abilityServiceById.getFname());
                                    filetype_object.put("ConditionName", tAbilityCondition.getFconditionname() == null ? "" : tAbilityCondition.getFconditionname());
                                    filetype_object.put("MethodName", serviceById == null ? "" : serviceById.getFmethodname());
                                    filetype_object.put("islevle", 2);//是否可以添加等级 1-可以 2-不可以
                                    filetype_object.put("istj", 2);//是否可以添加条件 1-可以 2-不可以
                                    filetype_object.put("iskh", 1);//是否可以添加考核 1-可以 2-不可以
                                    filetype_object.put("ftype", 3);// -1根节点 1=等级 2=条件 3=考核
                                    filetype_object.put("open", false);
                                    filetype_object.put("iconSkin", "class-icon");
                                    filetype_Array.add(filetype_object);
                                }
                            }
                            //获取子条件信息


                            //子条件
                            TAbilityConditionSExample tabilityConditionSExample = new TAbilityConditionSExample();
                            TAbilityConditionSExample.Criteria criteria3 = tabilityConditionSExample.createCriteria();
                            criteria3.andFpidEqualTo(tAbilityCondition.getFkeyid());
                            criteria3.andFalidEqualTo(tAbilityLevel.getFkeyid());
                            criteria3.andFstateEqualTo(1);
                            List<TAbilityConditionS> conditionSList = tAbilityConditionSService.findByExample(tabilityConditionSExample);
                            if (conditionSList.size() > 0) {
                                for (TAbilityConditionS tabilityConditionS : conditionSList) {
                                    FMethodWeight2 = 0;
                                    abilityConditionAssessmentMethodExample2 = new TAbilityConditionAssessmentMethodExample();
                                    criteria22 = abilityConditionAssessmentMethodExample2.createCriteria();
                                    criteria22.andFconditionidEqualTo(tabilityConditionS.getFkeyid());
                                    criteria22.andFtypeEqualTo(2);
                                    abilityConditionAssessmentMethodList = tAbilityConditionAssessmentMethodService.findByExample(abilityConditionAssessmentMethodExample2);
                                    if (abilityConditionAssessmentMethodList.size() > 0) {
                                        for (TAbilityConditionAssessmentMethod abilityConditionAssessmentMethod : abilityConditionAssessmentMethodList) {
                                            FMethodWeight2 += abilityConditionAssessmentMethod.getFmethodweight();
                                        }
                                    }

                                    ftjfenshu = 0;
                                    TAbilityConditionSExample tabilityConditionSExample4 = new TAbilityConditionSExample();
                                    tabilityConditionSExample4.createCriteria()
                                            .andFpidEqualTo(tabilityConditionS.getFkeyid())
                                            .andFalidEqualTo(tAbilityLevel.getFkeyid())
                                            .andFstateEqualTo(1);
                                    List<TAbilityConditionS> abilityConditionSList = tAbilityConditionSService.findByExample(tabilityConditionSExample4);
                                    if (abilityConditionSList.size() > 0) {
                                        for (TAbilityConditionS tAbilityConditionS : abilityConditionSList) {
                                            ftjfenshu += tAbilityConditionS.getFmethodweight() == null ? 0 : tAbilityConditionS.getFmethodweight();
                                        }
                                    }

                                    filetype_object = new JSONObject();
                                    filetype_object.put("id", ParamTools.getEnParam(tabilityConditionS.getFkeyid().toString()));
                                    filetype_object.put("pId", ParamTools.getEnParam(tAbilityCondition.getFkeyid().toString()));
//                                    filetype_object.put("name", tabilityConditionS.getFname() == null ? "" : tabilityConditionS.getFname() + "【条件达标分数：" + String.format("%.2f", tabilityConditionS.getFconditionscore()) + "】"+ "【当前条件总权重：" + String.format("%.2f", tabilityConditionS.getFmethodweight() == null ? 0 : tabilityConditionS.getFmethodweight()) + "%】" + "【下级条件总权重：" + String.format("%.2f", ftjfenshu) + "%】" + "【考核方式总权重：" + String.format("%.2f", FMethodWeight2) + "%】");
                                    filetype_object.put("name", tabilityConditionS.getFname() == null ? "" : tabilityConditionS.getFname() + "【条件达标分数：" + String.format("%.2f", tabilityConditionS.getFconditionscore()) + "】"+ "【当前条件总权重：" + String.format("%.2f", tabilityConditionS.getFmethodweight() == null ? 0 : tabilityConditionS.getFmethodweight()) + "%】" + "【下级条件+考核方式总权重：" + String.format("%.2f", (ftjfenshu + FMethodWeight2)) + "%】");
                                    filetype_object.put("AbilityID", ParamTools.getEnParam(abilityServiceById.getFkeyid().toString()));
                                    filetype_object.put("LevelID", ParamTools.getEnParam(tAbilityLevel.getFkeyid().toString()));

                                    filetype_object.put("fjdname", tAbilityCondition.getFconditionname());
                                    filetype_object.put("LevelName", tAbilityLevel.getFname());
                                    filetype_object.put("AbilityName", abilityServiceById.getFname());
                                    filetype_object.put("ConditionName", tabilityConditionS.getFname() == null ? "" : tabilityConditionS.getFname());
                                    filetype_object.put("islevle", 2);//是否可以添加等级 1-可以 2-不可以
                                    filetype_object.put("istj", 2);//是否可以添加条件 1-可以 2-不可以
                                    filetype_object.put("iskh", 1);//是否可以添加考核 1-可以 2-不可以
                                    filetype_object.put("ftype", 4);// -1根节点 1=等级 2=条件 3=考核
                                    filetype_object.put("ftypes", 2);// -1根节点 1=等级 2=条件 3=考核
                                    filetype_object.put("open", false);
                                    filetype_object.put("iconSkin", "major-icon");
                                    filetype_Array.add(filetype_object);

                                    //每个条件下有哪考核方式
                                    conditionAssessmentMethodExample = new TAbilityConditionAssessmentMethodExample();
                                    criteria2 = conditionAssessmentMethodExample.createCriteria();
                                    criteria2.andFalidEqualTo(tAbilityLevel.getFkeyid());
                                    criteria2.andFconditionidEqualTo(tabilityConditionS.getFkeyid());
                                    criteria2.andFstateEqualTo(1);
                                    conditionAssessmentMethodList = tAbilityConditionAssessmentMethodService.findByExample(conditionAssessmentMethodExample);
                                    if (conditionAssessmentMethodList.size() > 0) {
                                        for (TAbilityConditionAssessmentMethod tAbilityConditionAssessmentMethod : conditionAssessmentMethodList) {
                                            serviceById = tAbilityAssessmentMethodService.findById(tAbilityConditionAssessmentMethod.getFmethodid());
                                            filetype_object = new JSONObject();
                                            filetype_object.put("id", ParamTools.getEnParam(tAbilityConditionAssessmentMethod.getFkeyid().toString()));
                                            filetype_object.put("pId", ParamTools.getEnParam(tabilityConditionS.getFkeyid().toString()));
                                            filetype_object.put("name", serviceById == null ? "" : serviceById.getFmethodname() + "【权重：" + String.format("%.2f", tAbilityConditionAssessmentMethod.getFmethodweight()) + "%】");
                                            filetype_object.put("LevelID", ParamTools.getEnParam(tAbilityLevel.getFkeyid().toString()));
                                            filetype_object.put("LevelName", tAbilityLevel.getFname());
                                            filetype_object.put("AbilityName", abilityServiceById.getFname());
                                            filetype_object.put("ConditionName", tabilityConditionS.getFname() == null ? "" : tabilityConditionS.getFname());
                                            filetype_object.put("MethodName", serviceById == null ? "" : serviceById.getFmethodname());
                                            filetype_object.put("islevle", 2);//是否可以添加等级 1-可以 2-不可以
                                            filetype_object.put("istj", 2);//是否可以添加条件 1-可以 2-不可以
                                            filetype_object.put("iskh", 1);//是否可以添加考核 1-可以 2-不可以
                                            filetype_object.put("ftype", 3);// -1根节点 1=等级 2=条件 3=考核
                                            filetype_object.put("open", false);
                                            filetype_object.put("iconSkin", "class-icon");
                                            filetype_Array.add(filetype_object);
                                        }
                                    }

                                    if (tabilityConditionS.getFisleaf() == 0) {
                                        getConditions(filetype_Array, tabilityConditionS.getFkeyid(), abilityServiceById.getFkeyid(), tAbilityLevel.getFname(), abilityServiceById.getFname(), tAbilityLevel.getFkeyid(), tabilityConditionS.getFname());
                                    }

                                }
                            }
                        }
                    }
                }
            }
//            System.out.println(filetype_Array);
            // 返回值
            object.put("zNodes", filetype_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    private void getConditions(JSONArray abilitylevel_Arra, Long pid, Long AbilityID, String LevelName, String AbilityName, Long LevelID, String FCName) throws Exception {

        JSONObject filetype_object = new JSONObject();

        try {
            float FMethodWeight2 = 0;
            float ftjfenshu = 0;
            TAbilityAssessmentMethod serviceById;

            TAbilityConditionSExample tabilityConditionSExample = new TAbilityConditionSExample();
            TAbilityConditionSExample.Criteria criteria1 = tabilityConditionSExample.createCriteria();
            criteria1.andFpidEqualTo(pid);
            criteria1.andFstateEqualTo(1);
            List<TAbilityConditionS> conditionSList = tAbilityConditionSService.findByExample(tabilityConditionSExample);
            if (conditionSList.size() > 0) {
                for (TAbilityConditionS tabilityConditionS : conditionSList) {
                    FMethodWeight2 = 0;
                    TAbilityConditionAssessmentMethodExample abilityConditionAssessmentMethodExample2 = new TAbilityConditionAssessmentMethodExample();
                    TAbilityConditionAssessmentMethodExample.Criteria criteria22 = abilityConditionAssessmentMethodExample2.createCriteria();
                    criteria22.andFconditionidEqualTo(tabilityConditionS.getFkeyid());
                    criteria22.andFtypeEqualTo(2);
                    List<TAbilityConditionAssessmentMethod> abilityConditionAssessmentMethodList = tAbilityConditionAssessmentMethodService.findByExample(abilityConditionAssessmentMethodExample2);
                    if (abilityConditionAssessmentMethodList.size() > 0) {
                        for (TAbilityConditionAssessmentMethod abilityConditionAssessmentMethod : abilityConditionAssessmentMethodList) {
                            FMethodWeight2 += abilityConditionAssessmentMethod.getFmethodweight();
                        }
                    }

                    ftjfenshu = 0;
                    TAbilityConditionSExample tAbilityConditionSExample2 = new TAbilityConditionSExample();
                    tAbilityConditionSExample2.createCriteria().andFpidEqualTo(tabilityConditionS.getFkeyid()).andFstateEqualTo(1);
                    List<TAbilityConditionS> sList = tAbilityConditionSService.findByExample(tAbilityConditionSExample2);
                    if (sList.size() > 0) {
                        for (TAbilityConditionS tAbilityConditionS : sList) {
                            ftjfenshu += tAbilityConditionS.getFmethodweight();
                        }
                    }
                    filetype_object = new JSONObject();
                    filetype_object.put("id", ParamTools.getEnParam(tabilityConditionS.getFkeyid().toString()));
                    filetype_object.put("pId", ParamTools.getEnParam(pid.toString()));
//                    filetype_object.put("name", tabilityConditionS.getFname() == null ? "" : tabilityConditionS.getFname() + "【条件达标分数：" + String.format("%.2f", tabilityConditionS.getFconditionscore()) + "】" + "【当前条件总权重：" + String.format("%.2f", tabilityConditionS.getFmethodweight() == null ? 0 : tabilityConditionS.getFmethodweight()) + "%】"+ "【下级条件总权重：" + String.format("%.2f", ftjfenshu) + "%】" + "【考核方式总权重：" + String.format("%.2f", FMethodWeight2) + "%】");
                    filetype_object.put("name", tabilityConditionS.getFname() == null ? "" : tabilityConditionS.getFname() + "【条件达标分数：" + String.format("%.2f", tabilityConditionS.getFconditionscore()) + "】" + "【当前条件总权重：" + String.format("%.2f", tabilityConditionS.getFmethodweight() == null ? 0 : tabilityConditionS.getFmethodweight()) + "%】"+ "【下级条件+考核方式总权重：" + String.format("%.2f", (ftjfenshu + FMethodWeight2) ) + "%】");
                    filetype_object.put("AbilityID", ParamTools.getEnParam(AbilityID.toString()));
                    filetype_object.put("LevelID", ParamTools.getEnParam(LevelID.toString()));

                    filetype_object.put("fjdname", FCName);
                    filetype_object.put("LevelName", LevelName);
                    filetype_object.put("AbilityName", AbilityName);
                    filetype_object.put("ConditionName", tabilityConditionS.getFname() == null ? "" : tabilityConditionS.getFname());
                    filetype_object.put("islevle", 2);//是否可以添加等级 1-可以 2-不可以
                    filetype_object.put("istj", 2);//是否可以添加条件 1-可以 2-不可以
                    filetype_object.put("iskh", 1);//是否可以添加考核 1-可以 2-不可以
                    filetype_object.put("ftype", 4);// -1根节点 1=等级 2=条件 3=考核
                    filetype_object.put("ftypes", 2);// -1根节点 1=等级 2=条件 3=考核
                    filetype_object.put("open", false);
                    filetype_object.put("iconSkin", "major-icon");
                    abilitylevel_Arra.add(filetype_object);
                    //每个条件下有哪考核方式
                    TAbilityConditionAssessmentMethodExample conditionAssessmentMethodExample = new TAbilityConditionAssessmentMethodExample();
                    TAbilityConditionAssessmentMethodExample.Criteria criteria2 = conditionAssessmentMethodExample.createCriteria();
                    criteria2.andFalidEqualTo(LevelID);
                    criteria2.andFconditionidEqualTo(tabilityConditionS.getFkeyid());
                    criteria2.andFstateEqualTo(1);
                    List<TAbilityConditionAssessmentMethod> conditionAssessmentMethodList = tAbilityConditionAssessmentMethodService.findByExample(conditionAssessmentMethodExample);
                    if (conditionAssessmentMethodList.size() > 0) {
                        for (TAbilityConditionAssessmentMethod tAbilityConditionAssessmentMethod : conditionAssessmentMethodList) {
                            serviceById = tAbilityAssessmentMethodService.findById(tAbilityConditionAssessmentMethod.getFmethodid());
                            filetype_object = new JSONObject();
                            filetype_object.put("id", ParamTools.getEnParam(tAbilityConditionAssessmentMethod.getFkeyid().toString()));
                            filetype_object.put("pId", ParamTools.getEnParam(tabilityConditionS.getFkeyid().toString()));
                            filetype_object.put("name", serviceById == null ? "" : serviceById.getFmethodname() + "【权重：" + String.format("%.2f", tAbilityConditionAssessmentMethod.getFmethodweight()) + "%】");
                            filetype_object.put("LevelID", ParamTools.getEnParam(LevelID.toString()));
                            filetype_object.put("LevelName", LevelName);
                            filetype_object.put("AbilityName", AbilityName);
                            filetype_object.put("ConditionName", tabilityConditionS.getFname() == null ? "" : tabilityConditionS.getFname());
                            filetype_object.put("MethodName", serviceById == null ? "" : serviceById.getFmethodname());
                            filetype_object.put("islevle", 2);//是否可以添加等级 1-可以 2-不可以
                            filetype_object.put("istj", 2);//是否可以添加条件 1-可以 2-不可以
                            filetype_object.put("iskh", 1);//是否可以添加考核 1-可以 2-不可以
                            filetype_object.put("ftype", 3);// -1根节点 1=等级 2=条件 3=考核
                            filetype_object.put("open", false);
                            filetype_object.put("iconSkin", "class-icon");
                            abilitylevel_Arra.add(filetype_object);
                        }
                    }
                    if (tabilityConditionS.getFisleaf() == 0) {
                        getConditions(abilitylevel_Arra, tabilityConditionS.getFkeyid(), AbilityID, LevelName, AbilityName, LevelID, tabilityConditionS.getFname());
                    }

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}