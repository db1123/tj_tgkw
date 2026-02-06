package fun.server.controller.ability;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.service.TAbilityConditionAssessmentMethodService;
import fun.server.service.TAbilityConditionSService;
import fun.server.service.TAbilityConditionService;
import fun.server.service.TAbilityService;
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
import java.util.Objects;

/**
 * 等级条件层级管理 相关业务处理
 */
@RestController
@RequestMapping("/s/abilityconditions")
public class AbilityConditionS {

    private final TAbilityConditionSService tAbilityConditionSService;
    private final TAbilityService tAbilityService;
    private final TAbilityConditionService tAbilityConditionService;

    private final TAbilityConditionAssessmentMethodService tAbilityConditionAssessmentMethodService;

    public AbilityConditionS(TAbilityConditionSService tAbilityConditionSService, TAbilityService tAbilityService, TAbilityConditionService tAbilityConditionService, TAbilityConditionAssessmentMethodService tAbilityConditionAssessmentMethodService) {
        this.tAbilityConditionSService = tAbilityConditionSService;
        this.tAbilityService = tAbilityService;
        this.tAbilityConditionService = tAbilityConditionService;
        this.tAbilityConditionAssessmentMethodService = tAbilityConditionAssessmentMethodService;
    }


    /**
     * 获取类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilityConditions", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilityConditions(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FCID");//考核条件表ID

        JSONArray objectList = new JSONArray();
        try {
            String pid = id.equals("1") ? "1" : ParamTools.getdeParam(id);
            JSONObject object = new JSONObject();
            TAbilityCondition abilityCondition = tAbilityConditionService.findById(Long.parseLong(pid));


            object.put("id", ParamTools.getEnParam(abilityCondition.getFkeyid().toString()));
            object.put("pId", ParamTools.getEnParam("0"));
            TAbilityConditionSExample tAbilityConditionSExample = new TAbilityConditionSExample();
            TAbilityConditionSExample.Criteria criteria = tAbilityConditionSExample.createCriteria();
            criteria.andFpidEqualTo(abilityCondition.getFkeyid());
            List<TAbilityConditionS> list = tAbilityConditionSService.findByExample(tAbilityConditionSExample);
            if (list.size() > 0) {
                object.put("isParent", 1);
            } else {
                object.put("isParent", 0);
            }
            object.put("ftype", 1);
            object.put("FAbilityLevelID", ParamTools.getEnParam(abilityCondition.getFalid().toString()));
            object.put("iconSkin", "major-icon");
            object.put("name", abilityCondition.getFconditionname());
            objectList.add(object);


            tAbilityConditionSExample = new TAbilityConditionSExample();
            criteria = tAbilityConditionSExample.createCriteria();
            criteria.andFpidEqualTo(Long.parseLong(pid));
            list = tAbilityConditionSService.findByExample(tAbilityConditionSExample);
            for (TAbilityConditionS tAbilityConditionS : list) {
                object = new JSONObject();
                object.put("id", ParamTools.getEnParam(tAbilityConditionS.getFkeyid().toString()));
                object.put("pId", pid);
                object.put("name", tAbilityConditionS.getFname());
                object.put("ftype", 2);
                object.put("iconSkin", "major-icon");
                object.put("FAbilityLevelID", ParamTools.getEnParam(tAbilityConditionS.getFalid().toString()));
                if (tAbilityConditionS.getFisleaf() == 1) {
                    object.put("isParent", 0);
                } else {
                    object.put("isParent", 1);
                }
                objectList.add(object);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectList.toJSONString();
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
    @RequestMapping(value = "/queryabilityconditionsInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilityconditionsInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询能力等级信息
            TAbilityConditionS abilitylevel = tAbilityConditionSService.findById(key);
            JSONObject abilitylevel_object = new JSONObject();
            abilitylevel_object.put("key", ParamTools.getEnParam(abilitylevel.getFkeyid().toString()));
            abilitylevel_object.put("FALID", ParamTools.getEnParam(abilitylevel.getFalid().toString()));
            abilitylevel_object.put("FConditionName", abilitylevel.getFname() == null ? "" : abilitylevel.getFname());
//            abilitylevel_object.put("FConditionType", abilitylevel.getFconditiontype() == null ? -1 : abilitylevel.getFconditiontype());
//            abilitylevel_object.put("FConditionValue", abilitylevel.getFconditionvalue() == null ? "" : abilitylevel.getFconditionvalue());
            abilitylevel_object.put("FConditionScore", abilitylevel.getFconditionscore() == null ? 0 : abilitylevel.getFconditionscore());
            abilitylevel_object.put("FNote", abilitylevel.getFnote() == null ? "" : abilitylevel.getFnote());
            abilitylevel_object.put("FMethodWeight", abilitylevel.getFmethodweight() == null ? "" : abilitylevel.getFmethodweight());
            abilitylevel_object.put("FCID", abilitylevel.getFcid());
            abilitylevel_object.put("FUID", abilitylevel.getFuid());
            abilitylevel_object.put("FCDATE", abilitylevel.getFcdate());
            abilitylevel_object.put("FUDATE", abilitylevel.getFudate());
            abilitylevel_object.put("FState", abilitylevel.getFstate());


            if (!Objects.equals(abilitylevel.getFtacid(), abilitylevel.getFpid())) {
                TAbilityConditionS conditionS = tAbilityConditionSService.findById(abilitylevel.getFpid());
                abilitylevel_object.put("FFName", conditionS == null ? "" : conditionS.getFname());
            } else {
                TAbilityCondition abilityCondition = tAbilityConditionService.findById(abilitylevel.getFpid());
                abilitylevel_object.put("FFName", abilityCondition == null ? "" : abilityCondition.getFconditionname());
            }
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
     * 添加类型信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("添加等级条件层级信息")
    @ResponseBody
    @RequestMapping(value = "/addabilityConditions", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addabilityConditions(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String f_p_id = jsonParam.getString("FPID");
        String f_name = jsonParam.getString("FConditionName");
        String FNote = jsonParam.getString("FNote");
        String FConditionScore = jsonParam.getString("FConditionScore");
        String FAbilityLevelID = jsonParam.getString("FAbilityLevelID");
        String ftype = jsonParam.getString("ftype");
        float FMethodWeight = jsonParam.getFloat("FMethodWeight");

        try {
            FAbilityLevelID = FAbilityLevelID == null ? "0" : ParamTools.getdeParam(FAbilityLevelID);
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            Long pId = f_p_id.equals("1") ? 1L : Long.parseLong(ParamTools.getdeParam(f_p_id));

//            float maxscore =0;
//            long ftacid;
//            if(Integer.parseInt(ftype) == 1){
//                //条件主表
//                TAbilityCondition abilityCondition = tAbilityConditionService.findById(pId);
//                maxscore = abilityCondition.getFconditionscore();
//                ftacid = pId;
//            }else{
//                //条件子表
//                TAbilityConditionS conditionS = tAbilityConditionSService.findById(pId);
//                TAbilityCondition abilityCondition = tAbilityConditionService.findById(conditionS.getFtacid());
//                maxscore = abilityCondition.getFconditionscore();
//                ftacid =conditionS.getFtacid();
//            }
//            float sumscore=0;
//            TAbilityConditionSExample tAbilityConditionSExample2 =new TAbilityConditionSExample();
//            tAbilityConditionSExample2.createCriteria()
//                    .andFtacidEqualTo(ftacid)
//                    .andFstateEqualTo(1);
//            List<TAbilityConditionS> conditionSList = tAbilityConditionSService.findByExample(tAbilityConditionSExample2);
//            if (conditionSList.size() > 0) {
//                for (TAbilityConditionS conditionS : conditionSList) {
//                    sumscore += conditionS.getFconditionscore();
//                }
//            }
//            if(maxscore - sumscore - Integer.parseInt(FConditionScore) >=0 ){
            float FMethodWeight2 = 0;
            //子条件的权重
            TAbilityConditionSExample conditionExample = new TAbilityConditionSExample();
            conditionExample.createCriteria().andFpidEqualTo(pId).andFalidEqualTo(Long.parseLong(FAbilityLevelID));
            List<TAbilityConditionS> abilityConditionslist = tAbilityConditionSService.findByExample(conditionExample);
            if (abilityConditionslist.size() > 0) {
                for (TAbilityConditionS abilityConditionS : abilityConditionslist) {
                    FMethodWeight2 += abilityConditionS.getFmethodweight() == null ? 0 : abilityConditionS.getFmethodweight();
                }
            }

            // 还得加上 考核方式的
            TAbilityConditionAssessmentMethodExample tAbilityConditionAssessmentMethodExample = new TAbilityConditionAssessmentMethodExample();
            TAbilityConditionAssessmentMethodExample.Criteria criteria1 = tAbilityConditionAssessmentMethodExample.createCriteria();
            criteria1.andFalidEqualTo(Long.parseLong(FAbilityLevelID));
            criteria1.andFconditionidEqualTo(pId);
            List<TAbilityConditionAssessmentMethod> conditionAssessmentMethods = tAbilityConditionAssessmentMethodService.findByExample(tAbilityConditionAssessmentMethodExample);
            if (conditionAssessmentMethods.size() > 0) {
                for (TAbilityConditionAssessmentMethod conditionAssessmentMethod : conditionAssessmentMethods) {
                    FMethodWeight2 += conditionAssessmentMethod.getFmethodweight() == null ? 0 : conditionAssessmentMethod.getFmethodweight();
                }
            }
//            System.out.println("addFMethodWeight2:" + FMethodWeight2);

            if (100 - FMethodWeight2 - FMethodWeight >= 0) {
                TAbilityConditionSExample tAbilityConditionSExample = new TAbilityConditionSExample();
                TAbilityConditionSExample.Criteria criteria = tAbilityConditionSExample.createCriteria();
                criteria.andFpidEqualTo(pId);
                criteria.andFnameEqualTo(f_name);
                criteria.andFalidEqualTo(Long.parseLong(FAbilityLevelID));
                List<TAbilityConditionS> abilityConditionsList = tAbilityConditionSService.findByExample(tAbilityConditionSExample);
                if (abilityConditionsList.size() == 0) {
                    // 获取父节点路径
                    String path = "";
                    //父节点的层级
                    int fdiv=2;
                    TAbilityConditionS tAbilityConditionS = tAbilityConditionSService.findById(pId);
                    if (tAbilityConditionS == null) {
                        path = "|_1_|";
                        fdiv = 2;
                    } else {
                        path = tAbilityConditionS.getFpath();
                        fdiv = tAbilityConditionS.getFdiv() + 1;
                    }
                    // 添加数据
                    String id = "";
                    TAbilityConditionS abilityConditions = new TAbilityConditionS();
                    if (Integer.parseInt(ftype) == 1) {
                        abilityConditions.setFtacid(pId);
                    } else {
                        TAbilityConditionS conditionS = tAbilityConditionSService.findById(pId);
                        abilityConditions.setFtacid(conditionS.getFtacid());
                    }
                    abilityConditions.setFcid(Long.parseLong(uid));
                    abilityConditions.setFpid(pId);
                    abilityConditions.setFname(f_name);
                    abilityConditions.setFconditionscore(Float.parseFloat(FConditionScore));
                    abilityConditions.setFalid(Long.parseLong(FAbilityLevelID));
                    abilityConditions.setFisleaf(1);
                    abilityConditions.setFnote(FNote);
                    abilityConditions.setFmethodweight(FMethodWeight);
                    abilityConditions.setFdiv(fdiv);
                    tAbilityConditionSService.save(abilityConditions);
                    id = ParamTools.getEnParam(abilityConditions.getFkeyid().toString());
                    abilityConditions.setFpath(path + "|_" + abilityConditions.getFkeyid().toString() + "_|");
                    tAbilityConditionSService.update(abilityConditions);
                    // 刷新父节点
                    abilityConditions = new TAbilityConditionS();
                    abilityConditions.setFkeyid(pId);
                    abilityConditions.setFisleaf(0);
                    tAbilityConditionSService.update(abilityConditions);

                    // 返回值
                    object.put("id", id);
                    object.put("result", "success");
                } else {
                    object.put("result", "fail");
                }
            } else {
                object.put("result", "error2");
                object.put("error", "总权重值超出了100的上限");
                object.put("FMethodWeight2", 100 - FMethodWeight2);
            }

//            }else{
//                object.put("result", "error");
//                object.put("error", "子条件分数总和大于条件分数，请重新输入！");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 修改类型信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("修改等级条件层级信息")
    @ResponseBody
    @RequestMapping(value = "/updateabilityConditions", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateabilityConditions(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String f_name = jsonParam.getString("FConditionName");
        String FNote = jsonParam.getString("FNote");
        String FConditionScore = jsonParam.getString("FConditionScore");
        String FAbilityLevelID = jsonParam.getString("FAbilityLevelID");
        float FMethodWeight = jsonParam.getFloat("FMethodWeight");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
//            float maxscore = 0;
//            long ftacid;
//
//            //条件子表
            TAbilityConditionS conditionS = tAbilityConditionSService.findById(Long.parseLong(id));
//            TAbilityCondition abilityCondition = tAbilityConditionService.findById(conditionS.getFtacid());
//            maxscore = abilityCondition.getFconditionscore();
//            ftacid = conditionS.getFtacid();
//
//            float sumscore = 0;
//            TAbilityConditionSExample tAbilityConditionSExample2 = new TAbilityConditionSExample();
//            tAbilityConditionSExample2.createCriteria()
//                    .andFtacidEqualTo(ftacid)
//                    .andFstateEqualTo(1)
//                    .andFkeyidNotEqualTo(Long.parseLong(id));
//            List<TAbilityConditionS> conditionSList = tAbilityConditionSService.findByExample(tAbilityConditionSExample2);
//            if (conditionSList.size() > 0) {
//                for (TAbilityConditionS conditionS1 : conditionSList) {
//                    sumscore += conditionS1.getFconditionscore();
//                }
//            }

//            if (maxscore - sumscore - Integer.parseInt(FConditionScore) >= 0) {
            FAbilityLevelID = FAbilityLevelID == null ? "0" : ParamTools.getdeParam(FAbilityLevelID);


            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }

            float FMethodWeight2 = 0;
            TAbilityConditionSExample conditionExample = new TAbilityConditionSExample();
            conditionExample.createCriteria().andFpidEqualTo(conditionS.getFpid()).andFalidEqualTo(Long.parseLong(FAbilityLevelID)).andFkeyidNotEqualTo(Long.parseLong(id));
            List<TAbilityConditionS> abilityConditionslist = tAbilityConditionSService.findByExample(conditionExample);
            if (abilityConditionslist.size() > 0) {
                for (TAbilityConditionS abilityConditionS : abilityConditionslist) {
                    FMethodWeight2 += abilityConditionS.getFmethodweight() == null ? 0 : abilityConditionS.getFmethodweight();
                }
            }

            // 还得加上 考核方式的
            TAbilityConditionAssessmentMethodExample tAbilityConditionAssessmentMethodExample = new TAbilityConditionAssessmentMethodExample();
            TAbilityConditionAssessmentMethodExample.Criteria criteria1 = tAbilityConditionAssessmentMethodExample.createCriteria();
            criteria1.andFalidEqualTo(Long.parseLong(FAbilityLevelID));
            criteria1.andFconditionidEqualTo(conditionS.getFpid());
            List<TAbilityConditionAssessmentMethod> conditionAssessmentMethods = tAbilityConditionAssessmentMethodService.findByExample(tAbilityConditionAssessmentMethodExample);
            if (conditionAssessmentMethods.size() > 0) {
                for (TAbilityConditionAssessmentMethod conditionAssessmentMethod : conditionAssessmentMethods) {
                    FMethodWeight2 += conditionAssessmentMethod.getFmethodweight() == null ? 0 : conditionAssessmentMethod.getFmethodweight();
                }
            }
//            System.out.println("updateFMethodWeight2:" + FMethodWeight2);


            if (100 - FMethodWeight2 - FMethodWeight >= 0) {
                TAbilityConditionSExample tAbilityConditionSExample = new TAbilityConditionSExample();
                TAbilityConditionSExample.Criteria criteria = tAbilityConditionSExample.createCriteria();
                criteria.andFkeyidNotEqualTo(Long.parseLong(id));
                criteria.andFnameEqualTo(f_name);
                criteria.andFalidEqualTo(Long.parseLong(FAbilityLevelID));
                criteria.andFpidEqualTo(conditionS.getFpid());
                List<TAbilityConditionS> abilityConditionsList = tAbilityConditionSService.findByExample(tAbilityConditionSExample);
                if (abilityConditionsList.size() == 0) {
                    // 更新主记录
                    TAbilityConditionS abilityConditions1 = new TAbilityConditionS();
                    abilityConditions1.setFkeyid(Long.parseLong(id));
                    abilityConditions1.setFuid(Long.parseLong(uid));
                    abilityConditions1.setFudate(new Date());
                    abilityConditions1.setFname(f_name);
                    abilityConditions1.setFmethodweight(FMethodWeight);
                    abilityConditions1.setFconditionscore(Float.parseFloat(FConditionScore));
                    abilityConditions1.setFnote(FNote);
                    tAbilityConditionSService.update(abilityConditions1);
                    // 返回值
                    object.put("result", "success");
                } else {
                    object.put("result", "fail");
                }
            }else{
                object.put("result", "error2");
                object.put("error", "总权重值超出了100的上限");
                object.put("FMethodWeight2", 100 - FMethodWeight2);
            }


//            } else {
//                object.put("result", "error");
//                object.put("error", "子条件分数总和大于条件分数，请重新输入！");
//            }


        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 删除类型信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("删除等级条件层级信息")
    @ResponseBody
    @RequestMapping(value = "/delabilityConditions", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delabilityConditions(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }

            // 获取当前删除记录
            TAbilityConditionS abilityConditions = tAbilityConditionSService.findById(key);
            // 判断是否可删除
            if (abilityConditions.getFisleaf() == 0) {
                object.put("result", "error");
                object.put("error", "只能删除最下层节点");
            } else {
                // 删除记录
                tAbilityConditionSService.deleteById(key);
                // 判断是否需要修改父节点状态
                TAbilityConditionSExample paramTypeExample = new TAbilityConditionSExample();
                paramTypeExample.or().andFpidEqualTo(abilityConditions.getFpid());
                if (tAbilityConditionSService.findByExample(paramTypeExample).size() == 0) {
                    TAbilityConditionS SCTObject = new TAbilityConditionS();
                    SCTObject.setFkeyid(abilityConditions.getFpid());
                    SCTObject.setFisleaf(1);
                    tAbilityConditionSService.update(SCTObject);
                    object.put("PIsLeaf", 1);
                } else {
                    object.put("PIsLeaf", 0);
                }
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
     * 获取类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilityConditionsALL", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilityConditionsALL(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        try {
            // 返回值
            object.put("zNodes", recursionTypes(1L));
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取类型树(递归)
     * @param pid 父节点
     * @return 响应结果
     * @throws Exception 顶层异常类
     */
    private JSONArray recursionTypes(Long pid)
            throws Exception {
        JSONArray objectList = new JSONArray();
        TAbilityConditionSExample tAbilityConditionSExample = new TAbilityConditionSExample();
        tAbilityConditionSExample.or().andFpidEqualTo(pid);
        tAbilityConditionSExample.setOrderByClause("FCDATE asc");
        List<TAbilityConditionS> tAbilityConditionSList = tAbilityConditionSService.findByExample(tAbilityConditionSExample);

        for (TAbilityConditionS abilityConditions : tAbilityConditionSList) {
            JSONObject object = new JSONObject();
            object.put("id", ParamTools.getEnParam(abilityConditions.getFkeyid().toString()));
            object.put("pId", pid == 1 ? 1 : ParamTools.getEnParam(pid.toString()));
            object.put("name", abilityConditions.getFname());
            JSONArray next_list = recursionTypes(abilityConditions.getFkeyid());
            if (next_list.size() > 0) {
                object.put("children", next_list);
            }
            objectList.add(object);
        }
        return objectList;
    }


    /**
     * 获取考核条件树形信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/showTAbilityConditionTree", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String showBomTableTree(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();

        JSONObject jsonParam = ParamTools.getParameters(request);
        String FALID = jsonParam.getString("FALID");//等级ID

        JSONObject objall = new JSONObject();
        JSONArray obj1Listarr = new JSONArray();


        try {
            FALID = FALID == null ? "0" : ParamTools.getdeParam(FALID);

            JSONObject obj1 = new JSONObject();//根节点内容
            JSONArray zibiaoListarr = new JSONArray();
            TAbilityConditionExample tAbilityConditionExample = new TAbilityConditionExample();
            TAbilityConditionExample.Criteria criteria = tAbilityConditionExample.createCriteria();
            criteria.andFalidEqualTo(Long.parseLong(FALID));
            List<TAbilityCondition> abilityConditionList = tAbilityConditionService.findByExample(tAbilityConditionExample);
            if (abilityConditionList.size() > 0) {
                objall.put("draw", 0);
                objall.put("recordsTotal", 0);
                objall.put("recordsFiltered", 0);
                float FMethodWeight2 = 0;
                TAbilityConditionAssessmentMethodExample abilityConditionAssessmentMethodExample2;
                TAbilityConditionAssessmentMethodExample.Criteria criteria2;
                List<TAbilityConditionAssessmentMethod> abilityConditionAssessmentMethodList;
                JSONObject obj2 = new JSONObject();
                TAbilityConditionSExample tAbilityConditionSExample2;
                TAbilityConditionSExample.Criteria criteria1;
                List<TAbilityConditionS> conditionSList;
                for (TAbilityCondition abilityCondition : abilityConditionList) {
                    FMethodWeight2 = 0;
                    obj1 = new JSONObject();//根节点内容
                    obj1.put("key", ParamTools.getEnParam(abilityCondition.getFkeyid().toString()));
                    obj1.put("FConditionName", abilityCondition.getFconditionname() == null ? "" : abilityCondition.getFconditionname());
                    obj1.put("FConditionScore", abilityCondition.getFconditionscore() == null ? "" : abilityCondition.getFconditionscore());
                    obj1.put("FNote", abilityCondition.getFnote() == null ? "" : abilityCondition.getFnote());
                    abilityConditionAssessmentMethodExample2 = new TAbilityConditionAssessmentMethodExample();
                    criteria2 = abilityConditionAssessmentMethodExample2.createCriteria();
                    criteria2.andFconditionidEqualTo(abilityCondition.getFkeyid());
                    criteria2.andFtypeEqualTo(1);
                    abilityConditionAssessmentMethodList = tAbilityConditionAssessmentMethodService.findByExample(abilityConditionAssessmentMethodExample2);
                    if (abilityConditionAssessmentMethodList.size() > 0) {
                        for (TAbilityConditionAssessmentMethod abilityConditionAssessmentMethod : abilityConditionAssessmentMethodList) {
                            FMethodWeight2 += abilityConditionAssessmentMethod.getFmethodweight();
                        }
                    }
                    obj1.put("FMethodWeight", String.format("%.2f", FMethodWeight2));
                    obj1.put("FMethodWeight2", String.format("%.2f", abilityCondition.getFmethodweight() == null ? 0 : abilityCondition.getFmethodweight()));
                    obj1.put("FType", 1);

                    //查询子表
                    zibiaoListarr = new JSONArray();

                    tAbilityConditionSExample2 = new TAbilityConditionSExample();
                    criteria1 = tAbilityConditionSExample2.createCriteria();
                    criteria1.andFpidEqualTo(abilityCondition.getFkeyid());
                    criteria1.andFstateEqualTo(1);
                    tAbilityConditionSExample2.setOrderByClause("FCDATE asc");
                    conditionSList = tAbilityConditionSService.findByExample(tAbilityConditionSExample2);
                    if (conditionSList.size() > 0) {
                        for (TAbilityConditionS tAbilityConditionS : conditionSList) {
                            FMethodWeight2 = 0;
                            obj2 = new JSONObject();
                            obj2.put("key", ParamTools.getEnParam(tAbilityConditionS.getFkeyid().toString()));
                            obj2.put("FConditionName", tAbilityConditionS.getFname() == null ? "" : tAbilityConditionS.getFname());
                            obj2.put("FConditionScore", tAbilityConditionS.getFconditionscore() == null ? "" : tAbilityConditionS.getFconditionscore());
                            obj2.put("FNote", tAbilityConditionS.getFnote() == null ? "" : tAbilityConditionS.getFnote());
                            abilityConditionAssessmentMethodExample2 = new TAbilityConditionAssessmentMethodExample();
                            criteria2 = abilityConditionAssessmentMethodExample2.createCriteria();
                            criteria2.andFconditionidEqualTo(tAbilityConditionS.getFkeyid());
                            criteria2.andFtypeEqualTo(2);
                            abilityConditionAssessmentMethodList = tAbilityConditionAssessmentMethodService.findByExample(abilityConditionAssessmentMethodExample2);
                            if (abilityConditionAssessmentMethodList.size() > 0) {
                                for (TAbilityConditionAssessmentMethod abilityConditionAssessmentMethod : abilityConditionAssessmentMethodList) {
                                    FMethodWeight2 += abilityConditionAssessmentMethod.getFmethodweight();
                                }
                            }
                            obj2.put("FMethodWeight", String.format("%.2f", FMethodWeight2));
                            obj2.put("FMethodWeight2", String.format("%.2f", tAbilityConditionS.getFmethodweight() == null ? 0 : tAbilityConditionS.getFmethodweight()));
                            obj2.put("FType", 2);
                            if (tAbilityConditionS.getFisleaf() == 0)
                                this.getTAbilityConditionS(obj2, tAbilityConditionS.getFtacid(), tAbilityConditionS.getFalid(), tAbilityConditionS.getFkeyid());
                            zibiaoListarr.add(obj2);
                        }
                        obj1.put("children", zibiaoListarr);
                    } else {
                        obj1.put("children", null);
                    }
                    obj1Listarr.add(obj1);
                }

                objall.put("data", obj1Listarr);
//                objall.put("children", obj1);
//                System.out.println(objall.toString());
                object.put("datalist2", objall);
            }
//            else{
//                obj1.put("key", "");
//                obj1.put("FConditionName", "");
//                obj1.put("FConditionScore", "");
//                obj1.put("FNote", "");
//                obj1.put("FMethodWeight", "");
//
//            }

            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    private void getTAbilityConditionS(JSONObject obj2, Long FTACID, Long FALID, Long FPID) {

        JSONArray zibiaoListarr = new JSONArray();
        TAbilityConditionAssessmentMethodExample abilityConditionAssessmentMethodExample2;
        TAbilityConditionAssessmentMethodExample.Criteria criteria2;
        List<TAbilityConditionAssessmentMethod> abilityConditionAssessmentMethodList;
        TAbilityConditionSExample tAbilityConditionSExample2;
        TAbilityConditionSExample.Criteria criteria1;
        List<TAbilityConditionS> conditionSList;
        float FMethodWeight2 = 0;
        try {
            TAbilityConditionSExample tAbilityConditionSExample = new TAbilityConditionSExample();
            TAbilityConditionSExample.Criteria criteria = tAbilityConditionSExample.createCriteria();
//            criteria.andFtacidEqualTo(FTACID);
            criteria.andFalidEqualTo(FALID);
            criteria.andFpidEqualTo(FPID);
            criteria.andFstateEqualTo(1);
            tAbilityConditionSExample.setOrderByClause("FCDATE asc");
            conditionSList = tAbilityConditionSService.findByExample(tAbilityConditionSExample);
            if (conditionSList.size() > 0) {
                JSONObject obj3 = new JSONObject();
                for (TAbilityConditionS tAbilityConditionS : conditionSList) {
                    FMethodWeight2 = 0;
                    obj3 = new JSONObject();
                    obj3.put("key", ParamTools.getEnParam(tAbilityConditionS.getFkeyid().toString()));
                    obj3.put("FConditionName", tAbilityConditionS.getFname() == null ? "" : tAbilityConditionS.getFname());
                    obj3.put("FConditionScore", tAbilityConditionS.getFconditionscore() == null ? "" : tAbilityConditionS.getFconditionscore());
                    obj3.put("FNote", tAbilityConditionS.getFnote() == null ? "" : tAbilityConditionS.getFnote());
                    abilityConditionAssessmentMethodExample2 = new TAbilityConditionAssessmentMethodExample();
                    criteria2 = abilityConditionAssessmentMethodExample2.createCriteria();
                    criteria2.andFconditionidEqualTo(tAbilityConditionS.getFkeyid());
                    criteria2.andFtypeEqualTo(2);
                    abilityConditionAssessmentMethodList = tAbilityConditionAssessmentMethodService.findByExample(abilityConditionAssessmentMethodExample2);
                    if (abilityConditionAssessmentMethodList.size() > 0) {
                        for (TAbilityConditionAssessmentMethod abilityConditionAssessmentMethod : abilityConditionAssessmentMethodList) {
                            FMethodWeight2 += abilityConditionAssessmentMethod.getFmethodweight();
                        }
                    }
                    obj3.put("FMethodWeight", String.format("%.2f", FMethodWeight2));
                    obj3.put("FMethodWeight2", String.format("%.2f", tAbilityConditionS.getFmethodweight() == null ? 0 : tAbilityConditionS.getFmethodweight()));

                    obj3.put("FType", 2);
                    if (tAbilityConditionS.getFisleaf() == 0)
                        this.getTAbilityConditionS(obj3, tAbilityConditionS.getFtacid(), tAbilityConditionS.getFalid(), tAbilityConditionS.getFkeyid());
                    zibiaoListarr.add(obj3);
                }

                obj2.put("children", zibiaoListarr);
            } else {
                obj2.put("children", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取条件及子级的树形结构信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querabilitytiaojiantree", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querabilitytiaojiantree(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FCID = jsonParam.getString("FCID");
        int ftype = jsonParam.getInteger("ftype");
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

            FCID = FCID == null ? "0" : ParamTools.getdeParam(FCID);
            float FMethodWeight2 = 0;
            TAbilityConditionExample tAbilityConditionExample = null;
            float FConditionScore = 0;
            // 获取数据库记录
            JSONArray filetype_Array = new JSONArray();
            //查询文件分组
            JSONObject filetype_object = new JSONObject();
            TAbilityCondition abilityCondition = null;
            TAbilityConditionS conditionS = null;
            TAbilityConditionSExample tAbilityConditionSExample2 = new TAbilityConditionSExample();
            TAbilityConditionSExample.Criteria criteria = tAbilityConditionSExample2.createCriteria();
            float fenshu = 0;
            if (ftype == 1) {
                abilityCondition = tAbilityConditionService.findById(Long.parseLong(FCID));
                fenshu = 0;
                TAbilityConditionSExample conditionExample2 = new TAbilityConditionSExample();
                conditionExample2.createCriteria()
                        .andFpidEqualTo(abilityCondition.getFkeyid())
                        .andFstateEqualTo(1);
                List<TAbilityConditionS> conditionSList1 = tAbilityConditionSService.findByExample(conditionExample2);
                if (conditionSList1.size() > 0) {
                    for (TAbilityConditionS tAbilityConditionS : conditionSList1) {
//                        fenshu += tAbilityConditionS.getFconditionscore();
                        fenshu += tAbilityConditionS.getFmethodweight() == null ? 0 : tAbilityConditionS.getFmethodweight();
                    }
                }

                filetype_object = new JSONObject();
                filetype_object.put("id", ParamTools.getEnParam(abilityCondition.getFkeyid().toString()));
                filetype_object.put("pId", ParamTools.getEnParam("-1"));
                filetype_object.put("name", abilityCondition.getFconditionname() + "【当前条件达标分数：" + String.format("%.2f", abilityCondition.getFconditionscore()) + "】"+ "【当前条件权重：" + String.format("%.2f", abilityCondition.getFmethodweight() == null ? 0 : abilityCondition.getFmethodweight()) + "%】" +"【下级条件总权重：" + String.format("%.2f", fenshu) + "%】");
                filetype_object.put("FAbilityLevelID", ParamTools.getEnParam(abilityCondition.getFalid().toString()));
                filetype_object.put("ftype", 1);//  1=条件 2=子级
                filetype_object.put("open", true);
                filetype_object.put("iconSkin", "major-icon");
                filetype_Array.add(filetype_object);
                criteria.andFalidEqualTo(abilityCondition.getFalid());
                criteria.andFpidEqualTo(abilityCondition.getFkeyid());
            } else {
                conditionS = tAbilityConditionSService.findById(Long.parseLong(FCID));
                fenshu = 0;
                TAbilityConditionSExample conditionExample2 = new TAbilityConditionSExample();
                conditionExample2.createCriteria()
                        .andFpidEqualTo(conditionS.getFkeyid())
                        .andFstateEqualTo(1);
                List<TAbilityConditionS> conditionSList1 = tAbilityConditionSService.findByExample(conditionExample2);
                if (conditionSList1.size() > 0) {
                    for (TAbilityConditionS tAbilityConditionS : conditionSList1) {
//                        fenshu += tAbilityConditionS.getFconditionscore();
                        fenshu += tAbilityConditionS.getFmethodweight() == null ? 0 : tAbilityConditionS.getFmethodweight();
                    }
                }

                filetype_object = new JSONObject();
                filetype_object.put("id", ParamTools.getEnParam(conditionS.getFkeyid().toString()));
                filetype_object.put("pId", ParamTools.getEnParam(conditionS.getFpid().toString()));
                filetype_object.put("name", conditionS.getFname() + "【当前条件达标分数：】" + conditionS.getFconditionscore() + "【当前条件权重：" + String.format("%.2f", conditionS.getFmethodweight() == null ? 0 : conditionS.getFmethodweight()) + "%】" + "【下级条件总权重：" + String.format("%.2f", fenshu) + "%】");
                filetype_object.put("FAbilityLevelID", ParamTools.getEnParam(conditionS.getFalid().toString()));
                filetype_object.put("ftype", 2);//  1=条件 2=子级
                filetype_object.put("open", true);
                filetype_object.put("iconSkin", "major-icon");
                filetype_Array.add(filetype_object);
                criteria.andFalidEqualTo(conditionS.getFalid());
                criteria.andFpidEqualTo(conditionS.getFkeyid());
            }
            criteria.andFstateEqualTo(1);
            tAbilityConditionSExample2.setOrderByClause("FCDATE asc");
            List<TAbilityConditionS> conditionSList = tAbilityConditionSService.findByExample(tAbilityConditionSExample2);
            if (conditionSList.size() > 0) {
                for (TAbilityConditionS tAbilityConditionS : conditionSList) {
                    fenshu = 0;
                    TAbilityConditionSExample conditionExample2 = new TAbilityConditionSExample();
                    conditionExample2.createCriteria()
                            .andFpidEqualTo(tAbilityConditionS.getFkeyid())
                            .andFstateEqualTo(1);
                    List<TAbilityConditionS> conditionSList1 = tAbilityConditionSService.findByExample(conditionExample2);
                    if (conditionSList1.size() > 0) {
                        for (TAbilityConditionS tAbilityConditionS1 : conditionSList1) {
//                        fenshu += tAbilityConditionS.getFconditionscore();
                            fenshu += tAbilityConditionS1.getFmethodweight() == null ? 0 : tAbilityConditionS1.getFmethodweight();
                        }
                    }
                    filetype_object = new JSONObject();
                    filetype_object.put("id", ParamTools.getEnParam(tAbilityConditionS.getFkeyid().toString()));
                    filetype_object.put("pId", ParamTools.getEnParam(tAbilityConditionS.getFpid().toString()));
                    filetype_object.put("name", tAbilityConditionS.getFname() == null ? "" : tAbilityConditionS.getFname() + "【当前条件达标分数：" + String.format("%.2f", tAbilityConditionS.getFconditionscore()) + "】" + "【当前条件权重：" + String.format("%.2f", tAbilityConditionS.getFmethodweight() == null ? 0 : tAbilityConditionS.getFmethodweight()) + "%】"+ "【下级条件总权重：" + String.format("%.2f", fenshu) + "%】");
                    filetype_object.put("FAbilityLevelID", ParamTools.getEnParam(tAbilityConditionS.getFalid().toString()));
                    filetype_object.put("ftype", 2);//  1=条件 2=子级
                    filetype_object.put("open", true);
                    filetype_object.put("iconSkin", "major-icon");

                    filetype_Array.add(filetype_object);
                    if (tAbilityConditionS.getFisleaf() == 0)
                        this.getConditions(filetype_Array, tAbilityConditionS.getFkeyid(), tAbilityConditionS.getFalid());

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

    private void getConditions(JSONArray filetype_Array, Long pid, Long falid) throws Exception {
        JSONObject filetype_object = new JSONObject();
        float fenshu = 0;
        try {
            TAbilityConditionSExample tAbilityConditionSExample2 = new TAbilityConditionSExample();
            tAbilityConditionSExample2.createCriteria()
                    .andFalidEqualTo(falid)
                    .andFpidEqualTo(pid)
                    .andFstateEqualTo(1);
            tAbilityConditionSExample2.setOrderByClause("FCDATE asc");
            List<TAbilityConditionS> conditionSList = tAbilityConditionSService.findByExample(tAbilityConditionSExample2);
            if (conditionSList.size() > 0) {
                for (TAbilityConditionS tAbilityConditionS : conditionSList) {
                    fenshu = 0;
                    TAbilityConditionSExample conditionExample2 = new TAbilityConditionSExample();
                    conditionExample2.createCriteria()
                            .andFpidEqualTo(tAbilityConditionS.getFkeyid())
                            .andFstateEqualTo(1);
                    List<TAbilityConditionS> conditionSList1 = tAbilityConditionSService.findByExample(conditionExample2);
                    if (conditionSList1.size() > 0) {
                        for (TAbilityConditionS tAbilityConditionS1 : conditionSList1) {
//                        fenshu += tAbilityConditionS.getFconditionscore();
                            fenshu += tAbilityConditionS1.getFmethodweight() == null ? 0 : tAbilityConditionS1.getFmethodweight();
                        }
                    }
                    filetype_object = new JSONObject();
                    filetype_object.put("id", ParamTools.getEnParam(tAbilityConditionS.getFkeyid().toString()));
                    filetype_object.put("pId", ParamTools.getEnParam(tAbilityConditionS.getFpid().toString()));
                    filetype_object.put("name", tAbilityConditionS.getFname() == null ? "" : tAbilityConditionS.getFname() + "【当前条件达标分数：" + String.format("%.2f", tAbilityConditionS.getFconditionscore()) + "】" + "【当前条件权重：" + String.format("%.2f", tAbilityConditionS.getFmethodweight() == null ? 0 : tAbilityConditionS.getFmethodweight()) + "%】"+ "【下级条件总权重：" + String.format("%.2f", fenshu) + "%】");
                    filetype_object.put("FAbilityLevelID", ParamTools.getEnParam(tAbilityConditionS.getFalid().toString()));
                    filetype_object.put("ftype", 2);//  1=条件 2=子级
                    filetype_object.put("open", true);
                    filetype_object.put("iconSkin", "major-icon");
                    filetype_Array.add(filetype_object);
                    if (tAbilityConditionS.getFisleaf() == 0)
                        this.getConditions(filetype_Array, tAbilityConditionS.getFkeyid(), tAbilityConditionS.getFalid());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    @RequestMapping(value = "/queryabilityconditionsqz", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilityconditionsqz(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            float FMethodWeight = 0;
            TAbilityConditionSExample conditionExample = new TAbilityConditionSExample();
            conditionExample.createCriteria().andFpidEqualTo(key);
            List<TAbilityConditionS> abilityConditions = tAbilityConditionSService.findByExample(conditionExample);
            if (abilityConditions.size() > 0) {
                for (TAbilityConditionS abilityConditionS : abilityConditions) {
                    FMethodWeight += abilityConditionS.getFmethodweight() == null ? 0 : abilityConditionS.getFmethodweight();
                }
            }

            // 还得加上 考核方式的
            TAbilityConditionAssessmentMethodExample tAbilityConditionAssessmentMethodExample = new TAbilityConditionAssessmentMethodExample();
            TAbilityConditionAssessmentMethodExample.Criteria criteria1 = tAbilityConditionAssessmentMethodExample.createCriteria();
            criteria1.andFconditionidEqualTo(key);
            List<TAbilityConditionAssessmentMethod> conditionAssessmentMethods = tAbilityConditionAssessmentMethodService.findByExample(tAbilityConditionAssessmentMethodExample);
            if (conditionAssessmentMethods.size() > 0) {
                for (TAbilityConditionAssessmentMethod conditionAssessmentMethod : conditionAssessmentMethods) {
                    FMethodWeight += conditionAssessmentMethod.getFmethodweight() == null ? 0 : conditionAssessmentMethod.getFmethodweight();
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
}