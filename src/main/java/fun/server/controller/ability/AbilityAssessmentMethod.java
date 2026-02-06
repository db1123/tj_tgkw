package fun.server.controller.ability;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.service.TAbilityAssessmentMethodService;
import fun.server.service.TAbilityConditionAssessmentMethodService;
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
 * 能力等级考核方式管理 相关业务处理
 */
@RestController
@RequestMapping("/s/abilityassessmentmethod")
public class AbilityAssessmentMethod {

    private final TAbilityAssessmentMethodService tAbilityAssessmentMethodService;
//    t_ability_condition_assessment_method
    private final TAbilityConditionAssessmentMethodService tAbilityConditionAssessmentMethodService;

    public AbilityAssessmentMethod(TAbilityAssessmentMethodService tAbilityAssessmentMethodService, TAbilityConditionAssessmentMethodService tAbilityConditionAssessmentMethodService) {
        this.tAbilityAssessmentMethodService = tAbilityAssessmentMethodService;
        this.tAbilityConditionAssessmentMethodService = tAbilityConditionAssessmentMethodService;
    }


    /**
     * 获取能力等级考核方式信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilityassessmentmethod", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilityassessmentmethod(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        Integer dataall = jsonParam.getInteger("dataall");
        String name = jsonParam.getString("name");

        try {
            // 获取数据库记录
            JSONArray abilityassessmentmethod_Array = new JSONArray();
            // 查询条件
            TAbilityAssessmentMethodExample tAbilityConditionExample = new TAbilityAssessmentMethodExample();
            TAbilityAssessmentMethodExample.Criteria criteria = tAbilityConditionExample.createCriteria();

            if(name!=null && !name.equals("")){
                criteria.andFmethodnameLike("%" + name + "%");
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
                tAbilityConditionExample.setOrderByClause(orderSql.substring(1));
            } else {
                tAbilityConditionExample.setOrderByClause("FCDATE asc , FConditionName asc");
            }
            PageInfo<TAbilityAssessmentMethod> abilityassessmentmethodPageInfo = tAbilityAssessmentMethodService.findByExampleMapper(tAbilityConditionExample, (page - 1) * results, results);
            List<TAbilityAssessmentMethod> abilityassessmentmethod_list = abilityassessmentmethodPageInfo.getList();

            for (TAbilityAssessmentMethod abilityassessmentmethod : abilityassessmentmethod_list) {
                JSONObject abilityassessmentmethod_object = new JSONObject();
                abilityassessmentmethod_object.put("key", ParamTools.getEnParam(abilityassessmentmethod.getFkeyid().toString()));
                if (dataall == 1) {

                    abilityassessmentmethod_object.put("FMethodName", abilityassessmentmethod.getFmethodname() == null ? "" : abilityassessmentmethod.getFmethodname());
                    abilityassessmentmethod_object.put("FMethodMaxScore", abilityassessmentmethod.getFmethodmaxscore() == null ? 100 : abilityassessmentmethod.getFmethodmaxscore());
                    abilityassessmentmethod_object.put("FDescription", abilityassessmentmethod.getFdescription() == null ? 0 : abilityassessmentmethod.getFdescription());
                    abilityassessmentmethod_object.put("FCID", abilityassessmentmethod.getFcid());
                    abilityassessmentmethod_object.put("FUID", abilityassessmentmethod.getFuid());
                    abilityassessmentmethod_object.put("FCDATE", abilityassessmentmethod.getFcdate());
                    abilityassessmentmethod_object.put("FUDATE", abilityassessmentmethod.getFudate());
                } else {
                    abilityassessmentmethod_object.put("FMethodName", "*****");
                    abilityassessmentmethod_object.put("FDescription", "*****");
                    abilityassessmentmethod_object.put("FMethodMaxScore", "*****");
                    abilityassessmentmethod_object.put("FCID", "*****");
                    abilityassessmentmethod_object.put("FUID", "*****");
                    abilityassessmentmethod_object.put("FCDATE", "*****");
                    abilityassessmentmethod_object.put("FUDATE", "*****");
                }

                abilityassessmentmethod_object.put("FState", abilityassessmentmethod.getFstate());
                abilityassessmentmethod_Array.add(abilityassessmentmethod_object);
            }
            // 返回值
            object.put("list", abilityassessmentmethod_Array);
            object.put("total", abilityassessmentmethodPageInfo.getTotal()); // 总行数
            object.put("page", abilityassessmentmethodPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取能力等级考核方式信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilityassessmentmethod_xl", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDataabilitySelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray ability_Array = new JSONArray();
            TAbilityAssessmentMethodExample tAbilityConditionExample = new TAbilityAssessmentMethodExample();
            TAbilityAssessmentMethodExample.Criteria criteria = tAbilityConditionExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFmethodnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            tAbilityConditionExample.setOrderByClause("FMethodName asc");
            List<TAbilityAssessmentMethod> tAbilityAssessmentMethods = tAbilityAssessmentMethodService.findByExample(tAbilityConditionExample);
            for (TAbilityAssessmentMethod ability : tAbilityAssessmentMethods) {
                JSONObject ability_object = new JSONObject();
                ability_object.put("id", ParamTools.getEnParam(ability.getFkeyid().toString()));
                ability_object.put("text", ability.getFmethodname());
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
     * 根据ID获取能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilityassessmentmethodInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilityInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询能力信息
            TAbilityAssessmentMethod abilityassessmentmethod = tAbilityAssessmentMethodService.findById(key);
            JSONObject abilityassessmentmethod_object = new JSONObject();
            abilityassessmentmethod_object.put("key", ParamTools.getEnParam(abilityassessmentmethod.getFkeyid().toString()));
            abilityassessmentmethod_object.put("FMethodName", abilityassessmentmethod.getFmethodname() == null ? "" : abilityassessmentmethod.getFmethodname());
            abilityassessmentmethod_object.put("FMethodMaxScore", abilityassessmentmethod.getFmethodmaxscore() == null ? 100 : abilityassessmentmethod.getFmethodmaxscore());
            abilityassessmentmethod_object.put("FDescription", abilityassessmentmethod.getFdescription() == null ? 0 : abilityassessmentmethod.getFdescription());
            abilityassessmentmethod_object.put("FCID", abilityassessmentmethod.getFcid());
            abilityassessmentmethod_object.put("FUID", abilityassessmentmethod.getFuid());
            abilityassessmentmethod_object.put("FCDATE", abilityassessmentmethod.getFcdate());
            abilityassessmentmethod_object.put("FUDATE", abilityassessmentmethod.getFudate());
            // 返回值
            object.put("info", abilityassessmentmethod_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加考核方式信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加考核方式信息")
    @ResponseBody
    @RequestMapping(value = "/addabilityAssessmentMethod", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addabilityAssessmentMethod(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FMethodName = jsonParam.getString("FMethodName");
        String FDescription = jsonParam.getString("FDescription");
        try {
            TAbilityAssessmentMethodExample tAbilityAssessmentMethodExample =new TAbilityAssessmentMethodExample();
            TAbilityAssessmentMethodExample.Criteria criteria = tAbilityAssessmentMethodExample.createCriteria();
            criteria.andFmethodnameEqualTo(FMethodName);
            List<TAbilityAssessmentMethod> methodList = tAbilityAssessmentMethodService.findByExample(tAbilityAssessmentMethodExample);
            if (methodList.size() == 0 ) {
                String CookiesLoginabilityAssessmentMethodID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilityAssessmentMethodID != null && !CookiesLoginabilityAssessmentMethodID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilityAssessmentMethodID);
                }
                // 新建考核方式信息
                TAbilityAssessmentMethod abilityAssessmentMethod = new TAbilityAssessmentMethod();
                abilityAssessmentMethod.setFmethodname(FMethodName);
                abilityAssessmentMethod.setFdescription(FDescription);
                abilityAssessmentMethod.setFcid(Long.parseLong(uid));
                abilityAssessmentMethod.setFcdate(new Date());
                tAbilityAssessmentMethodService.save(abilityAssessmentMethod);
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
     * 修改考核方式信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改考核方式信息")
    @ResponseBody
    @RequestMapping(value = "/updateabilityAssessmentMethod", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateabilityAssessmentMethod(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FMethodName = jsonParam.getString("FMethodName");
        String FDescription = jsonParam.getString("FDescription");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            TAbilityAssessmentMethodExample tAbilityAssessmentMethodExample =new TAbilityAssessmentMethodExample();
            TAbilityAssessmentMethodExample.Criteria criteria = tAbilityAssessmentMethodExample.createCriteria();
            criteria.andFmethodnameEqualTo(FMethodName);
            criteria.andFkeyidNotEqualTo(Long.parseLong(id));
            List<TAbilityAssessmentMethod> methodList = tAbilityAssessmentMethodService.findByExample(tAbilityAssessmentMethodExample);
            if (methodList.size() == 0) {
                String CookiesLoginabilityAssessmentMethodID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilityAssessmentMethodID != null && !CookiesLoginabilityAssessmentMethodID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilityAssessmentMethodID);
                }
                // 更新考核方式信息
                TAbilityAssessmentMethod abilityAssessmentMethod = new TAbilityAssessmentMethod();
                abilityAssessmentMethod.setFkeyid(key);
                abilityAssessmentMethod.setFmethodname(FMethodName);
                abilityAssessmentMethod.setFdescription(FDescription);
                abilityAssessmentMethod.setFuid(Long.parseLong(uid));
                abilityAssessmentMethod.setFudate(new Date());
                tAbilityAssessmentMethodService.update(abilityAssessmentMethod);
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
     * 删除考核方式信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除考核方式信息")
    @ResponseBody
    @RequestMapping(value = "/delabilityAssessmentMethod", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delabilityAssessmentMethod(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLoginabilityAssessmentMethodID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginabilityAssessmentMethodID != null && !CookiesLoginabilityAssessmentMethodID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TAbilityConditionAssessmentMethodExample tAbilityConditionAssessmentMethodExample =new TAbilityConditionAssessmentMethodExample();
            TAbilityConditionAssessmentMethodExample.Criteria criteria = tAbilityConditionAssessmentMethodExample.createCriteria();
            criteria.andFmethodidEqualTo(Long.parseLong(id));
            List<TAbilityConditionAssessmentMethod> assessmentMethodList = tAbilityConditionAssessmentMethodService.findByExample(tAbilityConditionAssessmentMethodExample);
            if (assessmentMethodList.size() > 0) {
                object.put("result", "error");
                object.put("error", "该考核方式在能力中使用，不能删除（可禁用）!");
            }else{
                tAbilityAssessmentMethodService.deleteById(Long.parseLong(id));
                // 返回值
                object.put("result", "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 变更考核方式信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/stateabilityAssessmentMethod", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stateabilityAssessmentMethod(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLoginabilityAssessmentMethodID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginabilityAssessmentMethodID != null && !CookiesLoginabilityAssessmentMethodID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TAbilityAssessmentMethod abilityAssessmentMethod = new TAbilityAssessmentMethod();
            abilityAssessmentMethod.setFkeyid(Long.parseLong(id));
            abilityAssessmentMethod.setFuid(Long.parseLong(uid));
            abilityAssessmentMethod.setFudate(new Date());
            abilityAssessmentMethod.setFstate(Integer.valueOf(state));
            tAbilityAssessmentMethodService.update(abilityAssessmentMethod);
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