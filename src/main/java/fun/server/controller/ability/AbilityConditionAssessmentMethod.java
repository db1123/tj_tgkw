package fun.server.controller.ability;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
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
import java.util.Date;
import java.util.List;

/**
 * 能力等级条件与考核条件关系管理 相关业务处理
 */
@RestController
@RequestMapping("/s/abilityconditionassessmentmethod")
public class AbilityConditionAssessmentMethod {

    private final TAbilityConditionAssessmentMethodService tAbilityConditionAssessmentMethodService;
    private final TAbilityAssessmentMethodService tAbilityAssessmentMethodService;
    private final TAbilityConditionService tAbilityConditionService;

    private final TAbilityService tAbilityService;

    private final TAbilityLevelService tAbilityLevelService;

    private final  TAbilityTypeService tAbilityTypeService;
    
    private final TAbilityConditionSService tAbilityConditionSService;

    public AbilityConditionAssessmentMethod(TAbilityConditionAssessmentMethodService tAbilityConditionAssessmentMethodService, TAbilityAssessmentMethodService tAbilityAssessmentMethodService, TAbilityConditionService tAbilityConditionService, TAbilityService tAbilityService, TAbilityLevelService tAbilityLevelService, TAbilityTypeService tAbilityTypeService, TAbilityConditionSService tAbilityConditionSService) {
        this.tAbilityConditionAssessmentMethodService = tAbilityConditionAssessmentMethodService;
        this.tAbilityAssessmentMethodService = tAbilityAssessmentMethodService;
        this.tAbilityConditionService = tAbilityConditionService;
        this.tAbilityService = tAbilityService;
        this.tAbilityLevelService = tAbilityLevelService;
        this.tAbilityTypeService = tAbilityTypeService;
        this.tAbilityConditionSService = tAbilityConditionSService;
    }


    /**
     * 获取能力等级条件与考核条件关系信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilityconditionassessmentmethod", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilityconditionassessmentmethod(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String FALID = jsonParam.getString("FALID");
        String ConditionID = jsonParam.getString("ConditionID");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray abilityconditionassessmentmethod_Array = new JSONArray();
            // 查询条件
            TAbilityConditionAssessmentMethodExample tAbilityConditionAssessmentMethodExample = new TAbilityConditionAssessmentMethodExample();
            TAbilityConditionAssessmentMethodExample.Criteria criteria = tAbilityConditionAssessmentMethodExample.createCriteria();
            FALID = FALID == null ? "0" : ParamTools.getdeParam(FALID);
            ConditionID = ConditionID == null ? "0" : ParamTools.getdeParam(ConditionID);

            criteria.andFalidEqualTo(Long.parseLong(FALID));

//            if(!ConditionID.equals("0"))
            criteria.andFconditionidEqualTo(Long.parseLong(ConditionID));
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                tAbilityConditionAssessmentMethodExample.setOrderByClause(orderSql.substring(1));
            } else {
                tAbilityConditionAssessmentMethodExample.setOrderByClause("FCDATE asc , FMethodID asc");
            }
            PageInfo<TAbilityConditionAssessmentMethod> abilityconditionassessmentmethodPageInfo = tAbilityConditionAssessmentMethodService.findByExampleMapper(tAbilityConditionAssessmentMethodExample, (page - 1) * results, results);
            List<TAbilityConditionAssessmentMethod> abilityconditionassessmentmethod_list = abilityconditionassessmentmethodPageInfo.getList();

            for (TAbilityConditionAssessmentMethod abilityconditionassessmentmethod : abilityconditionassessmentmethod_list) {
                JSONObject abilityconditionassessmentmethod_object = new JSONObject();
                abilityconditionassessmentmethod_object.put("key", ParamTools.getEnParam(abilityconditionassessmentmethod.getFkeyid().toString()));
                abilityconditionassessmentmethod_object.put("FALID", ParamTools.getEnParam(abilityconditionassessmentmethod.getFalid().toString()));
                abilityconditionassessmentmethod_object.put("FConditionID", ParamTools.getEnParam(abilityconditionassessmentmethod.getFconditionid().toString()));
                abilityconditionassessmentmethod_object.put("FMethodID", ParamTools.getEnParam(abilityconditionassessmentmethod.getFmethodid().toString()));
                if (dataall == 1) {
                    TAbilityAssessmentMethod assessmentMethod = tAbilityAssessmentMethodService.findById(abilityconditionassessmentmethod.getFmethodid());
                    abilityconditionassessmentmethod_object.put("FMethodName", assessmentMethod == null ? "" : assessmentMethod.getFmethodname());
                    if(abilityconditionassessmentmethod.getFtype() ==1){
                        TAbilityCondition abilityCondition = tAbilityConditionService.findById(abilityconditionassessmentmethod.getFconditionid());
                        abilityconditionassessmentmethod_object.put("FConditionName", abilityCondition == null ? "" : abilityCondition.getFconditionname());
                    }else{
                        TAbilityConditionS conditionS = tAbilityConditionSService.findById(abilityconditionassessmentmethod.getFconditionid());
                        abilityconditionassessmentmethod_object.put("FConditionName", conditionS == null ? "" : conditionS.getFname());
                    }

                    abilityconditionassessmentmethod_object.put("FMethodWeight", abilityconditionassessmentmethod.getFmethodweight() == null ? "" : abilityconditionassessmentmethod.getFmethodweight());
                    abilityconditionassessmentmethod_object.put("FCID", abilityconditionassessmentmethod.getFcid());
                    abilityconditionassessmentmethod_object.put("FUID", abilityconditionassessmentmethod.getFuid());
                    abilityconditionassessmentmethod_object.put("FCDATE", abilityconditionassessmentmethod.getFcdate());
                    abilityconditionassessmentmethod_object.put("FUDATE", abilityconditionassessmentmethod.getFudate());
                } else {
                    abilityconditionassessmentmethod_object.put("FConditionName", "*****");
                    abilityconditionassessmentmethod_object.put("FMethodName","*****");
                    abilityconditionassessmentmethod_object.put("FMethodWeight", "*****");
                    abilityconditionassessmentmethod_object.put("FCID", "*****");
                    abilityconditionassessmentmethod_object.put("FUID", "*****");
                    abilityconditionassessmentmethod_object.put("FCDATE", "*****");
                    abilityconditionassessmentmethod_object.put("FUDATE", "*****");
                }

                abilityconditionassessmentmethod_object.put("FState", abilityconditionassessmentmethod.getFstate());
                abilityconditionassessmentmethod_Array.add(abilityconditionassessmentmethod_object);
            }
            // 返回值
            object.put("list", abilityconditionassessmentmethod_Array);
            object.put("total", abilityconditionassessmentmethodPageInfo.getTotal()); // 总行数
            object.put("page", abilityconditionassessmentmethodPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 删除能力等级考核方式信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除能力等级考核方式信息")
    @ResponseBody
    @RequestMapping(value = "/delabilityConditionAssessmentmethod", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delabilityConditionAssessmentmethod(HttpServletRequest request)
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
            tAbilityConditionAssessmentMethodService.deleteById(Long.parseLong(id));
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
     * 根据ID获取能力等级考核方式信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilityconditionAssessmentmethodInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilityconditionAssessmentmethodInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询能力等级信息
            TAbilityConditionAssessmentMethod abilityConditionAssessmentMethod = tAbilityConditionAssessmentMethodService.findById(key);
            JSONObject abilityConditionAssessmentMethod_object = new JSONObject();
            abilityConditionAssessmentMethod_object.put("key", ParamTools.getEnParam(abilityConditionAssessmentMethod.getFkeyid().toString()));
            abilityConditionAssessmentMethod_object.put("FALID", ParamTools.getEnParam(abilityConditionAssessmentMethod.getFalid().toString()));
            abilityConditionAssessmentMethod_object.put("FConditionID", ParamTools.getEnParam(abilityConditionAssessmentMethod.getFconditionid().toString()));
            abilityConditionAssessmentMethod_object.put("FMethodID", ParamTools.getEnParam(abilityConditionAssessmentMethod.getFmethodid().toString()));
            TAbilityAssessmentMethod tAbilityAssessmentMethod = tAbilityAssessmentMethodService.findById(abilityConditionAssessmentMethod.getFmethodid());
            abilityConditionAssessmentMethod_object.put("FMethodName", tAbilityAssessmentMethod == null ? "" : tAbilityAssessmentMethod.getFmethodname());
            abilityConditionAssessmentMethod_object.put("FMethodWeight", abilityConditionAssessmentMethod.getFmethodweight() == null ? "" : abilityConditionAssessmentMethod.getFmethodweight());
            abilityConditionAssessmentMethod_object.put("FCID", abilityConditionAssessmentMethod.getFcid());
            abilityConditionAssessmentMethod_object.put("FUID", abilityConditionAssessmentMethod.getFuid());
            abilityConditionAssessmentMethod_object.put("FCDATE", abilityConditionAssessmentMethod.getFcdate());
            abilityConditionAssessmentMethod_object.put("FUDATE", abilityConditionAssessmentMethod.getFudate());
            abilityConditionAssessmentMethod_object.put("FState", abilityConditionAssessmentMethod.getFstate());
            // 返回值
            object.put("info", abilityConditionAssessmentMethod_object);
            object.put("result", "success");
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
    @RequestMapping(value = "/queryabilityconditionAssessmentmethodqz", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilityconditionAssessmentmethodqz(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            float FMethodWeight=0;
            TAbilityConditionAssessmentMethodExample abilityConditionAssessmentMethodExample = new TAbilityConditionAssessmentMethodExample();
            TAbilityConditionAssessmentMethodExample.Criteria criteria = abilityConditionAssessmentMethodExample.createCriteria();
            criteria.andFconditionidEqualTo(key);
            List<TAbilityConditionAssessmentMethod> abilityConditionAssessmentMethodList = tAbilityConditionAssessmentMethodService.findByExample(abilityConditionAssessmentMethodExample);
            if(abilityConditionAssessmentMethodList.size() > 0){
                for (TAbilityConditionAssessmentMethod abilityConditionAssessmentMethod : abilityConditionAssessmentMethodList) {
                    FMethodWeight += abilityConditionAssessmentMethod.getFmethodweight();
                }
            }


            //子条件的权重
            TAbilityConditionSExample conditionExample = new TAbilityConditionSExample();
            conditionExample.createCriteria().andFpidEqualTo(key);
            List<TAbilityConditionS> abilityConditionslist = tAbilityConditionSService.findByExample(conditionExample);
            if (abilityConditionslist.size() > 0) {
                for (TAbilityConditionS abilityConditionS : abilityConditionslist) {
                    FMethodWeight += abilityConditionS.getFmethodweight() == null ? 0 : abilityConditionS.getFmethodweight();
                }
            }

            object.put("FMethodWeight", (100-FMethodWeight) < 0 ? 0 : (100-FMethodWeight));
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 新增能力等级考核条件与方式关系信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("新增能力等级考核条件与方式关系信息")
    @ResponseBody
    @RequestMapping(value = "/newaddabilitylevelConditionAsasessmentMethod", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String newaddabilitylevelConditionAsasessmentMethod(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);

        String FMethodID = jsonParam.getString("FMethodID");
        float FMethodWeight = jsonParam.getFloat("FMethodWeight");
        String FAbilityLevelID = jsonParam.getString("FAbilityLevelID");//能力等级id
        String FAbilityConditionID = jsonParam.getString("FAbilityConditionID");//能力等级考核条件id
        Integer ftype = jsonParam.getInteger("ftype");

        try {
            FMethodID = FMethodID == null ? "0" : ParamTools.getdeParam(FMethodID);
            FAbilityLevelID = FAbilityLevelID == null ? "0" : ParamTools.getdeParam(FAbilityLevelID);
            FAbilityConditionID = FAbilityConditionID == null ? "0" : ParamTools.getdeParam(FAbilityConditionID);
            float FMethodWeight2=0;
            TAbilityConditionAssessmentMethodExample abilityConditionAssessmentMethodExample2 = new TAbilityConditionAssessmentMethodExample();
            TAbilityConditionAssessmentMethodExample.Criteria criteria2 = abilityConditionAssessmentMethodExample2.createCriteria();
            criteria2.andFconditionidEqualTo(Long.parseLong(FAbilityConditionID));
            List<TAbilityConditionAssessmentMethod> abilityConditionAssessmentMethodList = tAbilityConditionAssessmentMethodService.findByExample(abilityConditionAssessmentMethodExample2);
            if(abilityConditionAssessmentMethodList.size() > 0){
                for (TAbilityConditionAssessmentMethod abilityConditionAssessmentMethod : abilityConditionAssessmentMethodList) {
                    FMethodWeight2 += abilityConditionAssessmentMethod.getFmethodweight();
                }
            }

            //子条件的权重
            TAbilityConditionSExample conditionExample = new TAbilityConditionSExample();
            conditionExample.createCriteria().andFpidEqualTo(Long.parseLong(FAbilityConditionID)).andFalidEqualTo(Long.parseLong(FAbilityLevelID));
            List<TAbilityConditionS> abilityConditionslist = tAbilityConditionSService.findByExample(conditionExample);
            if (abilityConditionslist.size() > 0) {
                for (TAbilityConditionS abilityConditionS : abilityConditionslist) {
                    FMethodWeight2 += abilityConditionS.getFmethodweight() == null ? 0 : abilityConditionS.getFmethodweight();
                }
            }

            if(100-FMethodWeight2 - FMethodWeight >= 0 ){

                TAbilityConditionAssessmentMethodExample abilityConditionAssessmentMethodExample = new TAbilityConditionAssessmentMethodExample();
                TAbilityConditionAssessmentMethodExample.Criteria criteria = abilityConditionAssessmentMethodExample.createCriteria();
                criteria.andFconditionidEqualTo(Long.parseLong(FAbilityConditionID));
                criteria.andFalidEqualTo(Long.parseLong(FAbilityLevelID));
                criteria.andFmethodidEqualTo(Long.parseLong(FMethodID));
                criteria.andFtypeEqualTo(ftype);
                List<TAbilityConditionAssessmentMethod> conditionList = tAbilityConditionAssessmentMethodService.findByExample(abilityConditionAssessmentMethodExample);
                if (conditionList.size() == 0) {
                    String CookiesLoginabilitylevelID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                    String uid = ""; // 当前登录用户ID
                    if (CookiesLoginabilitylevelID != null && !CookiesLoginabilitylevelID.equals("")) {
                        uid = ParamTools.getdeParam(CookiesLoginabilitylevelID);
                    }
                    TAbilityConditionAssessmentMethod condition = new TAbilityConditionAssessmentMethod();
                    condition.setFalid(Long.parseLong(FAbilityLevelID));
                    condition.setFconditionid(Long.parseLong(FAbilityConditionID));
                    condition.setFmethodid(Long.parseLong(FMethodID));
                    condition.setFmethodweight(FMethodWeight);
                    condition.setFcid(Long.parseLong(uid));
                    condition.setFtype(ftype);
                    condition.setFcdate(new Date());
                    tAbilityConditionAssessmentMethodService.save(condition);
                    // 返回值
                    object.put("result", "success");
                } else {
                    // 返回值
                    object.put("result", "fail");
                }
            }else{
                object.put("result", "error2");
                object.put("error", "总权重值超出了100的上限");
                object.put("FMethodWeight2", 100 - FMethodWeight2 );
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 修改能力等级考核条件与方式的关系信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改能力等级考核条件与方式关系信息")
    @ResponseBody
    @RequestMapping(value = "/updateabilitylevelConditionAsasessmentMethod", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateabilitylevelConditionAsasessmentMethod(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FMethodID = jsonParam.getString("FMethodID");
        float FMethodWeight = jsonParam.getFloat("FMethodWeight");
        String FAbilityLevelID = jsonParam.getString("FAbilityLevelID");//能力等级id
        String FAbilityConditionID = jsonParam.getString("FAbilityConditionID");//能力等级考核条件id

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            FMethodID = FMethodID == null ? "0" : ParamTools.getdeParam(FMethodID);
            FAbilityLevelID = FAbilityLevelID == null ? "0" : ParamTools.getdeParam(FAbilityLevelID);
            FAbilityConditionID = FAbilityConditionID == null ? "0" : ParamTools.getdeParam(FAbilityConditionID);

            float FMethodWeight2=0;
            TAbilityConditionAssessmentMethodExample abilityConditionAssessmentMethodExample2 = new TAbilityConditionAssessmentMethodExample();
            TAbilityConditionAssessmentMethodExample.Criteria criteria2 = abilityConditionAssessmentMethodExample2.createCriteria();
            criteria2.andFconditionidEqualTo(Long.parseLong(FAbilityConditionID));
            criteria2.andFkeyidNotEqualTo(Long.parseLong(id));
            List<TAbilityConditionAssessmentMethod> abilityConditionAssessmentMethodList = tAbilityConditionAssessmentMethodService.findByExample(abilityConditionAssessmentMethodExample2);
            if(abilityConditionAssessmentMethodList.size() > 0){
                for (TAbilityConditionAssessmentMethod abilityConditionAssessmentMethod : abilityConditionAssessmentMethodList) {
                    FMethodWeight2 += abilityConditionAssessmentMethod.getFmethodweight();
                }
            }

            //子条件的权重
            TAbilityConditionSExample conditionExample = new TAbilityConditionSExample();
            conditionExample.createCriteria().andFpidEqualTo(Long.parseLong(FAbilityConditionID)).andFalidEqualTo(Long.parseLong(FAbilityLevelID));
            List<TAbilityConditionS> abilityConditionslist = tAbilityConditionSService.findByExample(conditionExample);
            if (abilityConditionslist.size() > 0) {
                for (TAbilityConditionS abilityConditionS : abilityConditionslist) {
                    FMethodWeight2 += abilityConditionS.getFmethodweight() == null ? 0 : abilityConditionS.getFmethodweight();
                }
            }
            if(100- FMethodWeight2 - FMethodWeight >= 0 ){
                TAbilityConditionAssessmentMethodExample abilityConditionAssessmentMethodExample = new TAbilityConditionAssessmentMethodExample();
                TAbilityConditionAssessmentMethodExample.Criteria criteria = abilityConditionAssessmentMethodExample.createCriteria();
                criteria.andFconditionidEqualTo(Long.parseLong(FAbilityConditionID));
                criteria.andFalidEqualTo(Long.parseLong(FAbilityLevelID));
                criteria.andFmethodidEqualTo(Long.parseLong(FMethodID));
                criteria.andFkeyidNotEqualTo(Long.parseLong(id));
                List<TAbilityConditionAssessmentMethod> conditionList = tAbilityConditionAssessmentMethodService.findByExample(abilityConditionAssessmentMethodExample);
                if (conditionList.size() == 0) {
                    String CookiesLoginabilitylevelID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                    String uid = ""; // 当前登录用户ID
                    if (CookiesLoginabilitylevelID != null && !CookiesLoginabilitylevelID.equals("")) {
                        uid = ParamTools.getdeParam(CookiesLoginabilitylevelID);
                    }
                    TAbilityConditionAssessmentMethod condition = new TAbilityConditionAssessmentMethod();
                    condition.setFkeyid(Long.parseLong(id));
                    condition.setFmethodid(Long.parseLong(FMethodID));
                    condition.setFmethodweight(FMethodWeight);
                    condition.setFuid(Long.parseLong(uid));
                    condition.setFudate(new Date());
                    tAbilityConditionAssessmentMethodService.update(condition);
                    // 返回值
                    object.put("result", "success");
                } else {
                    // 返回值
                    object.put("result", "fail");
                }
            }else{
                object.put("result", "error2");
                object.put("error", "总权重值超出了100的上限");
                object.put("FMethodWeight2", 100 - FMethodWeight2 );
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 根据ID获取能力等级及条件 考核方式信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilityConditionAssessmentMethodInfohua", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilityConditionAssessmentMethodInfohua(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询能力信息
            TAbility ability = tAbilityService.findById(key);
            JSONObject ability_object = new JSONObject();
            ability_object.put("key", ParamTools.getEnParam(ability.getFkeyid().toString()));
            ability_object.put("FTypeID", ParamTools.getEnParam(ability.getFtypeid().toString()));
            TAbilityType tAbilityType = tAbilityTypeService.findById(ability.getFtypeid());
            ability_object.put("FTypeName", tAbilityType == null ? "" : tAbilityType.getFname());
            ability_object.put("FName", ability.getFname() == null ? "" : ability.getFname());
            ability_object.put("FNote", ability.getFnote() == null ? "" : ability.getFnote());

            TAbilityLevelExample tAbilityLevelExample = new TAbilityLevelExample();
            TAbilityLevelExample.Criteria criteria = tAbilityLevelExample.createCriteria();
            criteria.andFstateEqualTo(1);
            criteria.andFabilityidEqualTo(ability.getFkeyid());
            List<TAbilityLevel> abilityLevelList = tAbilityLevelService.findByExample(tAbilityLevelExample);
            JSONArray abilitylevel_Array = new JSONArray();
            TAbilityConditionExample abilityConditionExample = null;
            TAbilityConditionExample.Criteria criteria2 = null;
            List<TAbilityCondition> conditionList =null;
            TAbilityConditionAssessmentMethodExample abilityConditionAssessmentMethodExample =null;
            TAbilityConditionAssessmentMethodExample.Criteria criteria3 =null;
            List<TAbilityConditionAssessmentMethod> methodList =null;
            TAbilityAssessmentMethod tAbilityAssessmentMethod =null;
            String ftypename ="";
            JSONObject abilitylevel_object = new JSONObject();
            abilitylevel_object.put("id", ParamTools.getEnParam(ability.getFkeyid().toString()));
            abilitylevel_object.put("fid", 0);
            abilitylevel_object.put("value", ability.getFname() == null ? "" : ability.getFname());
            abilitylevel_object.put("FPoints", "");
            abilitylevel_object.put("FNote", "");
            abilitylevel_object.put("FScoreMin","");
            abilitylevel_object.put("FScoreMax", "");
            abilitylevel_object.put("level", 0);
            abilitylevel_object.put("FConditionType", "");
            abilitylevel_object.put("FConditionValue", "");
            abilitylevel_object.put("FConditionScore", "");
            abilitylevel_object.put("FMethodWeight", "");
            abilitylevel_Array.add(abilitylevel_object);
            if(abilityLevelList.size() > 0) {
                for (TAbilityLevel abilityLevel : abilityLevelList) {
                    abilitylevel_object = new JSONObject();
                    abilitylevel_object.put("id", ParamTools.getEnParam(abilityLevel.getFkeyid().toString()));
                    abilitylevel_object.put("fid", ParamTools.getEnParam(ability.getFkeyid().toString()));
                    abilitylevel_object.put("value", abilityLevel.getFname() == null ? "" : abilityLevel.getFname());
                    abilitylevel_object.put("FPoints", abilityLevel.getFpoints() == null ? "" : abilityLevel.getFpoints());
                    abilitylevel_object.put("FNote", abilityLevel.getFnote() == null ? "" : abilityLevel.getFnote());
                    abilitylevel_object.put("FScoreMin", abilityLevel.getFscoremin() == null ? 0 : abilityLevel.getFscoremin());
                    abilitylevel_object.put("FScoreMax", abilityLevel.getFscoremax() == null ? 0 : abilityLevel.getFscoremax());
                    abilitylevel_object.put("level", 1);
                    abilitylevel_object.put("FConditionType", "");
                    abilitylevel_object.put("FConditionValue", "");
                    abilitylevel_object.put("FConditionScore", "");
                    abilitylevel_object.put("FMethodWeight", "");
                    abilitylevel_Array.add(abilitylevel_object);
                    abilityConditionExample = new TAbilityConditionExample();
                    criteria2 = abilityConditionExample.createCriteria();
                    criteria2.andFstateEqualTo(1);
                    criteria2.andFalidEqualTo(abilityLevel.getFkeyid());
                    conditionList = tAbilityConditionService.findByExample(abilityConditionExample);
                    for (TAbilityCondition condition : conditionList) {
                        abilitylevel_object = new JSONObject();
                        abilitylevel_object.put("id", ParamTools.getEnParam(condition.getFkeyid().toString()));
                        abilitylevel_object.put("fid", ParamTools.getEnParam(abilityLevel.getFkeyid().toString()));
                        abilitylevel_object.put("value", condition.getFconditionname() == null ? "" : condition.getFconditionname());
                        abilitylevel_object.put("level", 2);
                        abilitylevel_object.put("FConditionType", "");
                        abilitylevel_object.put("FNote", condition.getFnote() == null ? "" : condition.getFnote());
                        abilitylevel_object.put("FConditionValue", condition.getFconditionvalue() == null ? "" : condition.getFconditionvalue());
                        abilitylevel_object.put("FConditionScore", condition.getFconditionscore() == null ? "" : condition.getFconditionscore());
                        abilitylevel_object.put("FMethodWeight", "");
                        abilitylevel_object.put("FPoints", "");
                        abilitylevel_object.put("FScoreMin", "");
                        abilitylevel_object.put("FScoreMax", "");
                        abilitylevel_Array.add(abilitylevel_object);

                        abilityConditionAssessmentMethodExample = new TAbilityConditionAssessmentMethodExample();
                        criteria3 = abilityConditionAssessmentMethodExample.createCriteria();
                        criteria3.andFstateEqualTo(1);
                        criteria3.andFalidEqualTo(abilityLevel.getFkeyid());
                        criteria3.andFconditionidEqualTo(condition.getFkeyid());
                        methodList = tAbilityConditionAssessmentMethodService.findByExample(abilityConditionAssessmentMethodExample);
                        if(methodList.size()>0) {
                            for (TAbilityConditionAssessmentMethod condition2 : methodList) {
                                abilitylevel_object = new JSONObject();
                                abilitylevel_object.put("id", ParamTools.getEnParam(condition2.getFkeyid().toString()));
                                abilitylevel_object.put("fid", ParamTools.getEnParam(condition.getFkeyid().toString()));
                                tAbilityAssessmentMethod = tAbilityAssessmentMethodService.findById(condition2.getFmethodid());
                                abilitylevel_object.put("level", 3);
                                abilitylevel_object.put("value", tAbilityAssessmentMethod == null ? "" : tAbilityAssessmentMethod.getFmethodname());
                                abilitylevel_object.put("FMethodWeight", condition2.getFmethodweight() == null ? 0 : condition2.getFmethodweight());
                                abilitylevel_object.put("FConditionType", "");
                                abilitylevel_object.put("FConditionValue", "");
                                abilitylevel_object.put("FConditionScore", "");
                                abilitylevel_object.put("FPoints", "");
                                abilitylevel_object.put("FNote", "");
                                abilitylevel_object.put("FScoreMin", "");
                                abilitylevel_object.put("FScoreMax", "");
                                abilitylevel_Array.add(abilitylevel_object);
                            }
                        }

                        //子条件
                        TAbilityConditionSExample tabilityConditionSExample = new TAbilityConditionSExample();
                        TAbilityConditionSExample.Criteria criteria1 = tabilityConditionSExample.createCriteria();
                        criteria1.andFpidEqualTo(condition.getFkeyid());
                        criteria1.andFstateEqualTo(1);
                        List<TAbilityConditionS> conditionSList = tAbilityConditionSService.findByExample(tabilityConditionSExample);
                        if(conditionSList.size() > 0) {
                            for (TAbilityConditionS tabilityConditionS : conditionSList) {
                                abilitylevel_object = new JSONObject();
                                abilitylevel_object.put("id", ParamTools.getEnParam(tabilityConditionS.getFkeyid().toString()));
                                abilitylevel_object.put("fid", ParamTools.getEnParam(condition.getFkeyid().toString()));
                                abilitylevel_object.put("value", tabilityConditionS.getFname() == null ? "" : tabilityConditionS.getFname());
                                abilitylevel_object.put("level", 4);
                                abilitylevel_object.put("FConditionType", "");
                                abilitylevel_object.put("FNote", tabilityConditionS.getFnote() == null ? "" : tabilityConditionS.getFnote());
                                abilitylevel_object.put("FConditionValue", "");
                                abilitylevel_object.put("FConditionScore", tabilityConditionS.getFconditionscore() == null ? "" : tabilityConditionS.getFconditionscore());
                                abilitylevel_object.put("FMethodWeight", "");
                                abilitylevel_object.put("FPoints", "");
                                abilitylevel_object.put("FScoreMin", "");
                                abilitylevel_object.put("FScoreMax", "");
                                abilitylevel_Array.add(abilitylevel_object);

                                abilityConditionAssessmentMethodExample = new TAbilityConditionAssessmentMethodExample();
                                criteria3 = abilityConditionAssessmentMethodExample.createCriteria();
                                criteria3.andFstateEqualTo(1);
                                criteria3.andFalidEqualTo(abilityLevel.getFkeyid());
                                criteria3.andFconditionidEqualTo(tabilityConditionS.getFkeyid());
                                methodList = tAbilityConditionAssessmentMethodService.findByExample(abilityConditionAssessmentMethodExample);
                                if(methodList.size()>0){
                                    for (TAbilityConditionAssessmentMethod condition2 : methodList) {
                                        abilitylevel_object = new JSONObject();
                                        abilitylevel_object.put("id", ParamTools.getEnParam(condition2.getFkeyid().toString()));
                                        abilitylevel_object.put("fid", ParamTools.getEnParam(tabilityConditionS.getFkeyid().toString()));
                                        tAbilityAssessmentMethod = tAbilityAssessmentMethodService.findById(condition2.getFmethodid());
                                        abilitylevel_object.put("level", 3);
                                        abilitylevel_object.put("value", tAbilityAssessmentMethod == null ? "" : tAbilityAssessmentMethod.getFmethodname());
                                        abilitylevel_object.put("FMethodWeight", condition2.getFmethodweight() == null ? 0 : condition2.getFmethodweight());
                                        abilitylevel_object.put("FConditionType", "");
                                        abilitylevel_object.put("FConditionValue", "");
                                        abilitylevel_object.put("FConditionScore", "");
                                        abilitylevel_object.put("FPoints", "");
                                        abilitylevel_object.put("FNote", "");
                                        abilitylevel_object.put("FScoreMin", "");
                                        abilitylevel_object.put("FScoreMax", "");
                                        abilitylevel_Array.add(abilitylevel_object);
                                    }
                                }

                                if(tabilityConditionS.getFisleaf() == 0){
                                    getConditions(abilitylevel_Array,tabilityConditionS.getFkeyid(),abilityLevel.getFkeyid());
                                }

                            }
                        }






                    }
                }
            }
//            System.out.println(abilitylevel_Array.toString());
            ability_object.put("abilityLevel", abilitylevel_Array);
            // 返回值
            object.put("info", ability_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    private void getConditions(JSONArray abilitylevel_Arra,Long pid,Long abilityLevelID) throws Exception {

        JSONObject abilitylevel_object = new JSONObject();
        TAbilityConditionAssessmentMethodExample abilityConditionAssessmentMethodExample =null;
        TAbilityConditionAssessmentMethodExample.Criteria criteria3 =null;
        List<TAbilityConditionAssessmentMethod> methodList =null;
        TAbilityAssessmentMethod tAbilityAssessmentMethod =null;
        try {
            TAbilityConditionSExample tabilityConditionSExample = new TAbilityConditionSExample();
            TAbilityConditionSExample.Criteria criteria1 = tabilityConditionSExample.createCriteria();
            criteria1.andFpidEqualTo(pid);
            criteria1.andFstateEqualTo(1);
            List<TAbilityConditionS> conditionSList = tAbilityConditionSService.findByExample(tabilityConditionSExample);
            if(conditionSList.size() > 0) {
                for (TAbilityConditionS tabilityConditionS : conditionSList) {
                    abilitylevel_object = new JSONObject();
                    abilitylevel_object.put("id", ParamTools.getEnParam(tabilityConditionS.getFkeyid().toString()));
                    abilitylevel_object.put("fid", ParamTools.getEnParam(pid.toString()));
                    abilitylevel_object.put("value", tabilityConditionS.getFname() == null ? "" : tabilityConditionS.getFname());
                    abilitylevel_object.put("level", 4);
                    abilitylevel_object.put("FConditionType", "");
                    abilitylevel_object.put("FNote", tabilityConditionS.getFnote() == null ? "" : tabilityConditionS.getFnote());
                    abilitylevel_object.put("FConditionValue", "");
                    abilitylevel_object.put("FConditionScore", tabilityConditionS.getFconditionscore() == null ? "" : tabilityConditionS.getFconditionscore());
                    abilitylevel_object.put("FMethodWeight", "");
                    abilitylevel_object.put("FPoints", "");
                    abilitylevel_object.put("FScoreMin", "");
                    abilitylevel_object.put("FScoreMax", "");
                    abilitylevel_Arra.add(abilitylevel_object);
                    abilityConditionAssessmentMethodExample = new TAbilityConditionAssessmentMethodExample();
                    criteria3 = abilityConditionAssessmentMethodExample.createCriteria();
                    criteria3.andFstateEqualTo(1);
                    criteria3.andFalidEqualTo(abilityLevelID);
                    criteria3.andFconditionidEqualTo(tabilityConditionS.getFkeyid());
                    methodList = tAbilityConditionAssessmentMethodService.findByExample(abilityConditionAssessmentMethodExample);
                    if(methodList.size()>0){
                        for (TAbilityConditionAssessmentMethod condition2 : methodList) {
                            abilitylevel_object = new JSONObject();
                            abilitylevel_object.put("id", ParamTools.getEnParam(condition2.getFkeyid().toString()));
                            abilitylevel_object.put("fid", ParamTools.getEnParam(tabilityConditionS.getFkeyid().toString()));
                            tAbilityAssessmentMethod = tAbilityAssessmentMethodService.findById(condition2.getFmethodid());
                            abilitylevel_object.put("level", 3);
                            abilitylevel_object.put("value", tAbilityAssessmentMethod == null ? "" : tAbilityAssessmentMethod.getFmethodname());
                            abilitylevel_object.put("FMethodWeight", condition2.getFmethodweight() == null ? 0 : condition2.getFmethodweight());
                            abilitylevel_object.put("FConditionType", "");
                            abilitylevel_object.put("FConditionValue", "");
                            abilitylevel_object.put("FConditionScore", "");
                            abilitylevel_object.put("FPoints", "");
                            abilitylevel_object.put("FNote", "");
                            abilitylevel_object.put("FScoreMin", "");
                            abilitylevel_object.put("FScoreMax", "");
                            abilitylevel_Arra.add(abilitylevel_object);
                        }
                    }
                    if(tabilityConditionS.getFisleaf() == 0){
                        getConditions(abilitylevel_Arra,tabilityConditionS.getFkeyid(),abilityLevelID);
                    }

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}