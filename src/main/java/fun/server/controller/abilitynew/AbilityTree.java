package fun.server.controller.abilitynew;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 能力树形结构管理 相关业务处理
 */
@RestController
@RequestMapping("/s/abilitytree")
public class AbilityTree {

    private final TAbilityTreeService tAbilityTreeService;

    private final TAbilityTypeService tAbilityTypeService;

    private final TAbilityAssessmentMethodService tAbilityAssessmentMethodService;

    private final TCourseAbilityService tCourseAbilityService;

    private final TAbilityLevelConditionService tAbilityLevelConditionService;

    public AbilityTree(TAbilityTreeService tAbilityTreeService, TAbilityTypeService tAbilityTypeService, TAbilityAssessmentMethodService tAbilityAssessmentMethodService, TCourseAbilityService tCourseAbilityService, TAbilityLevelConditionService tAbilityLevelConditionService) {
        this.tAbilityTreeService = tAbilityTreeService;
        this.tAbilityTypeService = tAbilityTypeService;
        this.tAbilityAssessmentMethodService = tAbilityAssessmentMethodService;
        this.tCourseAbilityService = tCourseAbilityService;
        this.tAbilityLevelConditionService = tAbilityLevelConditionService;
    }


    /**
     * 获取能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryability", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryability(HttpServletRequest request)
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
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray ability_Array = new JSONArray();
            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
            // 查询条件
            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }
            if (abilitytype != null && !abilitytype.equals("") && !abilitytype.equals("1")) {
                abilitytype = abilitytype == null ? "0" : ParamTools.getdeParam(abilitytype);
                criteria.andFtypeidEqualTo(Long.parseLong(abilitytype));
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
                tAbilityTreeExample.setOrderByClause(orderSql.substring(1));
            } else {
                tAbilityTreeExample.setOrderByClause("FTypeID asc , FName asc");
            }
            criteria.andFnodetypeEqualTo(1);
            criteria.andFdelEqualTo(1);

            PageInfo<TAbilityTree> abilityPageInfo = tAbilityTreeService.findByExampleMapper(tAbilityTreeExample, (page - 1) * results, results);
            List<TAbilityTree> ability_list = abilityPageInfo.getList();

            for (TAbilityTree ability : ability_list) {
                JSONObject ability_object = new JSONObject();
                ability_object.put("key", ParamTools.getEnParam(ability.getFkeyid().toString()));
                ability_object.put("FTypeID", ParamTools.getEnParam(ability.getFtypeid().toString()));
                if (dataall == 1) {
                    TAbilityType tAbilityType = tAbilityTypeService.findById(ability.getFtypeid());
                    ability_object.put("FTypeName", tAbilityType == null ? "" : tAbilityType.getFname());
                    ability_object.put("FName", ability.getFname() == null ? "" : ability.getFname());
                    ability_object.put("FNote", ability.getFnote() == null ? "" : ability.getFnote());
                    ability_object.put("FCID", ability.getFcid());
                    ability_object.put("FUID", ability.getFuid());
                    ability_object.put("FCDATE", ability.getFcdate());
                    ability_object.put("FUDATE", ability.getFudate());
                } else {
                    ability_object.put("FTypeName", "*****");
                    ability_object.put("FName", "*****");
                    ability_object.put("FNote", "*****");
                    ability_object.put("FCID", "*****");
                    ability_object.put("FUID", "*****");
                    ability_object.put("FCDATE", "*****");
                    ability_object.put("FUDATE", "*****");
                }

                ability_object.put("FState", ability.getFstate());
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
     * 获取能力画像
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/getabilityhuaxiang", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getabilityhuaxiang(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int ftype = jsonParam.getInteger("ftype"); //1 = 获取全部 2=获取个人
//        String userid = "0";
        try {
//            if (ftype == 2) {
//                userid = jsonParam.getString("userid");
//                userid = userid == null ? "0" : ParamTools.getdeParam(userid);
//            }
            //查询能力类型
            TAbilityTypeExample abilityTypeExample = new TAbilityTypeExample();
            TAbilityTypeExample.Criteria criteria = abilityTypeExample.createCriteria();
            criteria.andFstateEqualTo(1);
            List<TAbilityType> abilityTypeList = tAbilityTypeService.findByExample(abilityTypeExample);
            if (abilityTypeList.size() > 0) {
                JSONArray type_Array = new JSONArray();
                JSONArray ability_Array = new JSONArray();
                JSONArray openKeys_Array = new JSONArray();
                TAbilityTreeExample tAbilityTreeExample = null;
                int iii = 0;
                for (TAbilityType abilityType : abilityTypeList) {
                    JSONObject type_object = new JSONObject();
                    type_object.put("key", ParamTools.getEnParam(abilityType.getFkeyid().toString()));
                    type_object.put("FName", abilityType.getFname());
                    type_object.put("FNote", abilityType.getFnote());
                    type_object.put("i", iii);


                    tAbilityTreeExample = new TAbilityTreeExample();
                    TAbilityTreeExample.Criteria criteria2 = tAbilityTreeExample.createCriteria();
                    criteria2.andFdelEqualTo(1);
                    criteria2.andFnodetypeEqualTo(1);
                    criteria2.andFtypeidEqualTo(abilityType.getFkeyid());
                    criteria2.andFstateEqualTo(1);
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
//                        type_object.put("FAbility", ability_Array);
                    }
                    type_Array.add(type_object);
                    iii++;
                }
                for (int i = 0; i < abilityTypeList.size(); i++) {
                    openKeys_Array.add(i);
                }
//                System.out.println(openKeys_Array);
//                System.out.println(type_Array);
                object.put("type_Array", type_Array);
                object.put("ability_Array", ability_Array);
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
            TAbilityTree ability = tAbilityTreeService.findById(key);
            JSONObject ability_object = new JSONObject();
            ability_object.put("key", ParamTools.getEnParam(ability.getFkeyid().toString()));
            ability_object.put("FTypeID", ParamTools.getEnParam(ability.getFtypeid().toString()));
            TAbilityType tAbilityType = tAbilityTypeService.findById(ability.getFtypeid());
            ability_object.put("FTypeName", tAbilityType == null ? "" : tAbilityType.getFname());
            ability_object.put("FName", ability.getFname() == null ? "" : ability.getFname());
            ability_object.put("FNote", ability.getFnote() == null ? "" : ability.getFnote());

            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
            criteria.andFpidEqualTo(ability.getFkeyid());
            criteria.andFdelEqualTo(1);
            criteria.andFnodetypeEqualTo(2);//等级

            List<TAbilityTree> abilityLevelList = tAbilityTreeService.findByExample(tAbilityTreeExample);
            JSONArray abilitylevel_Array = new JSONArray();

            List<TAbilityCondition> conditionList = null;
            TAbilityConditionAssessmentMethodExample abilityConditionAssessmentMethodExample = null;
            TAbilityConditionAssessmentMethodExample.Criteria criteria3 = null;
            List<TAbilityConditionAssessmentMethod> methodList = null;
            TAbilityAssessmentMethod tAbilityAssessmentMethod = null;
            String ftypename = "";
            JSONObject abilitylevel_object = new JSONObject();
            abilitylevel_object.put("id", ParamTools.getEnParam(ability.getFkeyid().toString()));
            abilitylevel_object.put("fid", 0);
            abilitylevel_object.put("value", ability.getFname() == null ? "" : ability.getFname());
            abilitylevel_object.put("FPoints", "");
            abilitylevel_object.put("FNote", "");
            abilitylevel_object.put("FScoreMin", "");
            abilitylevel_object.put("FScoreMax", "");
            abilitylevel_object.put("level", 0);
            abilitylevel_object.put("FConditionType", "");
            abilitylevel_object.put("FConditionValue", "");
            abilitylevel_object.put("FConditionScore", "");
            abilitylevel_object.put("FMethodWeight", "");
            abilitylevel_Array.add(abilitylevel_object);
            if (abilityLevelList.size() > 0) {
                for (TAbilityTree abilityLevel : abilityLevelList) {
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
                    tAbilityTreeExample = new TAbilityTreeExample();
                    criteria = tAbilityTreeExample.createCriteria();
                    criteria.andFpidEqualTo(abilityLevel.getFkeyid());
                    criteria.andFdelEqualTo(1);
                    criteria.andFnodetypeEqualTo(3);//条件
                    List<TAbilityTree> abilityTrees = tAbilityTreeService.findByExample(tAbilityTreeExample);
                    if (abilityTrees.size() > 0) {
                        for (TAbilityTree condition : abilityTrees) {
                            abilitylevel_object = new JSONObject();
                            abilitylevel_object.put("id", ParamTools.getEnParam(condition.getFkeyid().toString()));
                            abilitylevel_object.put("fid", ParamTools.getEnParam(abilityLevel.getFkeyid().toString()));
                            abilitylevel_object.put("value", condition.getFname() == null ? "" : condition.getFname());
                            abilitylevel_object.put("level", 2);
                            abilitylevel_object.put("FConditionType", "");
                            abilitylevel_object.put("FNote", condition.getFnote() == null ? "" : condition.getFnote());
                            abilitylevel_object.put("FConditionScore", condition.getFconditionscore() == null ? "" : condition.getFconditionscore());
                            abilitylevel_object.put("FMethodWeight", condition.getFmethodweight() == null ? 0 : condition.getFmethodweight());
                            abilitylevel_object.put("FPoints", "");
                            abilitylevel_object.put("FScoreMin", "");
                            abilitylevel_object.put("FScoreMax", "");
                            abilitylevel_Array.add(abilitylevel_object);
                            if (condition.getFisleaf() == 0) {
                                getNodeData(abilitylevel_Array, condition.getFkeyid());
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

    private void getNodeData(JSONArray abilitylevel_Array, Long FPID) throws Exception {

        JSONObject abilitylevel_object = null;
        try {
            List<Integer> nodetypelist = new ArrayList<>();
            nodetypelist.add(3);
            nodetypelist.add(4);
            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
            criteria.andFpidEqualTo(FPID);
            criteria.andFdelEqualTo(1);
            criteria.andFnodetypeIn(nodetypelist);//条件和方式
            List<TAbilityTree> abilityLevelList = tAbilityTreeService.findByExample(tAbilityTreeExample);
            if (abilityLevelList.size() > 0) {
                for (TAbilityTree condition : abilityLevelList) {
                    if (condition.getFnodetype() == 3) {
                        abilitylevel_object = new JSONObject();
                        abilitylevel_object.put("id", ParamTools.getEnParam(condition.getFkeyid().toString()));
                        abilitylevel_object.put("fid", ParamTools.getEnParam(FPID.toString()));
                        abilitylevel_object.put("value", condition.getFname() == null ? "" : condition.getFname());
                        abilitylevel_object.put("level", 4);
                        abilitylevel_object.put("FConditionType", "");
                        abilitylevel_object.put("FNote", condition.getFnote() == null ? "" : condition.getFnote());
                        abilitylevel_object.put("FConditionScore", condition.getFconditionscore() == null ? "" : condition.getFconditionscore());
                        abilitylevel_object.put("FMethodWeight", condition.getFmethodweight() == null ? 0 : condition.getFmethodweight());
                        abilitylevel_object.put("FPoints", "");
                        abilitylevel_object.put("FScoreMin", "");
                        abilitylevel_object.put("FScoreMax", "");
                        abilitylevel_Array.add(abilitylevel_object);
                        if (condition.getFisleaf() == 0) {
                            getNodeData(abilitylevel_Array, condition.getFkeyid());
                        }
                    } else if (condition.getFnodetype() == 4) {
                        abilitylevel_object = new JSONObject();
                        abilitylevel_object.put("id", ParamTools.getEnParam(condition.getFkeyid().toString()));
                        abilitylevel_object.put("fid", ParamTools.getEnParam(FPID.toString()));
                        TAbilityAssessmentMethod tAbilityAssessmentMethod = tAbilityAssessmentMethodService.findById(condition.getFmethodid());
                        abilitylevel_object.put("level", 3);
                        abilitylevel_object.put("value", tAbilityAssessmentMethod == null ? "" : tAbilityAssessmentMethod.getFmethodname());
                        abilitylevel_object.put("FMethodWeight", condition.getFmethodweight() == null ? 0 : condition.getFmethodweight());
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 添加能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加能力信息")
    @ResponseBody
    @RequestMapping(value = "/addability", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addability(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FName = jsonParam.getString("FName");
        String FType = jsonParam.getString("FType");
        String FNote = jsonParam.getString("FNote");
        Integer fdj = null;
        try {
            fdj = jsonParam.getInteger("FDJ");
        } catch (Exception e) {
            fdj = 0;
        }
        try {
            FType = FType == null ? "0" : ParamTools.getdeParam(FType);
            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
            criteria.andFnameEqualTo(FName);
            criteria.andFtypeidEqualTo(Long.parseLong(FType));
            criteria.andFnodetypeEqualTo(1);
            criteria.andFdelEqualTo(1);
            List<TAbilityTree> abilityList = tAbilityTreeService.findByExample(tAbilityTreeExample);
            if (abilityList.size() == 0) {
                String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilityID);
                }
                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
                long key = idWorker.nextId();
                // 新建能力信息
                TAbilityTree ability = new TAbilityTree();
                ability.setFkeyid(key);
                ability.setFname(FName);
                ability.setFnote(FNote);
                ability.setFtypeid(Long.parseLong(FType));
                ability.setFcid(Long.parseLong(uid));
                ability.setFcdate(new Date());
                ability.setFnodetype(1);
                ability.setFpid(-1l);
                ability.setFpath("|_1_|");
                ability.setFdiv(1);
                ability.setFisleaf(1);
                ability.setFgpid(key);
                tAbilityTreeService.save(ability);
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
     * 修改能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改能力信息")
    @ResponseBody
    @RequestMapping(value = "/updateability", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateability(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FName = jsonParam.getString("FName");
        String FType = jsonParam.getString("FType");
        String FNote = jsonParam.getString("FNote");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            FType = FType == null ? "0" : ParamTools.getdeParam(FType);
            long key = Long.parseLong(id);
            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
            criteria.andFnameEqualTo(FName);
            criteria.andFtypeidEqualTo(Long.parseLong(FType));
            criteria.andFkeyidNotEqualTo(key);
            criteria.andFnodetypeEqualTo(1);
            criteria.andFdelEqualTo(1);
            List<TAbilityTree> abilityList = tAbilityTreeService.findByExample(tAbilityTreeExample);
            if (abilityList.size() == 0) {
                String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilityID);
                }
                // 更新能力信息
                TAbilityTree ability = new TAbilityTree();
                ability.setFkeyid(key);
                ability.setFname(FName);
                ability.setFnote(FNote);
                ability.setFtypeid(Long.parseLong(FType));
                ability.setFuid(Long.parseLong(uid));
                ability.setFudate(new Date());
                tAbilityTreeService.update(ability);
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
     * 根据ID获取能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilityInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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
            TAbilityTree ability = tAbilityTreeService.findById(key);
            JSONObject ability_object = new JSONObject();
            ability_object.put("key", ParamTools.getEnParam(ability.getFkeyid().toString()));
            ability_object.put("FTypeID", ParamTools.getEnParam(ability.getFtypeid().toString()));
            TAbilityType tAbilityType = tAbilityTypeService.findById(ability.getFtypeid());
            ability_object.put("FTypeName", tAbilityType == null ? "" : tAbilityType.getFname());
            ability_object.put("FName", ability.getFname() == null ? "" : ability.getFname());
            ability_object.put("FNote", ability.getFnote() == null ? "" : ability.getFnote());
            ability_object.put("FCID", ability.getFcid());
            ability_object.put("FUID", ability.getFuid());
            ability_object.put("FCDATE", ability.getFcdate());
            ability_object.put("FUDATE", ability.getFudate());
            ability_object.put("FState", ability.getFstate());
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


    /**
     * 删除能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除能力信息")
    @ResponseBody
    @RequestMapping(value = "/delability", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delability(HttpServletRequest request)
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
            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            tAbilityTreeExample.createCriteria().andFgpidEqualTo(Long.parseLong(id));
            TAbilityTree tAbilityTree = new TAbilityTree();
            tAbilityTree.setFdel(2);//删除
            tAbilityTree.setFuid(Long.parseLong(uid));
            tAbilityTree.setFudate(new Date());
            tAbilityTreeService.updateByExample(tAbilityTree, tAbilityTreeExample);

            //修改 课程绑定能力 的状态为弃用
            TCourseAbilityExample tCourseAbilityExample = new TCourseAbilityExample();
            tCourseAbilityExample.createCriteria().andFabilityidEqualTo(Long.parseLong(id));
            TCourseAbility tCourseAbility = new TCourseAbility();
            tCourseAbility.setFstate(0);
            tCourseAbility.setFuid(Long.parseLong(uid));
            tCourseAbility.setFudate(new Date());
            tCourseAbilityService.updateByExample(tCourseAbility, tCourseAbilityExample);


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
     * 变更能力信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/stateability", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stateability(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TAbilityTree ability = new TAbilityTree();
            ability.setFkeyid(Long.parseLong(id));
            ability.setFuid(Long.parseLong(uid));
            ability.setFudate(new Date());
            ability.setFstate(Integer.valueOf(state));
            tAbilityTreeService.update(ability);
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
     * 获取能力等级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilitylevel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilitylevel(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String id = jsonParam.getString("id");

        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray abilitylevel_Array = new JSONArray();

            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
            // 查询条件
            id = id == null ? "0" : id.equals("0") ? "" : ParamTools.getdeParam(id);
            criteria.andFpidEqualTo(Long.parseLong(id));
            criteria.andFnodetypeEqualTo(2);//等级
            criteria.andFstateEqualTo(1);//可用状态
            criteria.andFdelEqualTo(1);//未删除
            tAbilityTreeExample.setOrderByClause("FCDATE asc");
            PageInfo<TAbilityTree> abilitylevelPageInfo = tAbilityTreeService.findByExampleMapper(tAbilityTreeExample, (page - 1) * results, results);
            List<TAbilityTree> abilitylevel_list = abilitylevelPageInfo.getList();
            TAbilityConditionExample tAbilityConditionExample = null;
            float FConditionScore = 0;
            for (TAbilityTree abilitylevel : abilitylevel_list) {
                FConditionScore = 0;
                JSONObject abilitylevel_object = new JSONObject();
                abilitylevel_object.put("key", ParamTools.getEnParam(abilitylevel.getFkeyid().toString()));
                if (dataall == 1) {
                    abilitylevel_object.put("FName", abilitylevel.getFname() == null ? "" : abilitylevel.getFname());
                    abilitylevel_object.put("FPoints", abilitylevel.getFpoints());
                    abilitylevel_object.put("FScoreMin", abilitylevel.getFscoremin());
                    abilitylevel_object.put("FScoreMax", abilitylevel.getFscoremax());
                    abilitylevel_object.put("FNote", abilitylevel.getFnote() == null ? "" : abilitylevel.getFnote());
                    abilitylevel_object.put("FCID", abilitylevel.getFcid());
                    abilitylevel_object.put("FUID", abilitylevel.getFuid());
                    abilitylevel_object.put("FCDATE", abilitylevel.getFcdate());
                    abilitylevel_object.put("FUDATE", abilitylevel.getFudate());

//                    tAbilityConditionExample = new TAbilityConditionExample();
//                    tAbilityConditionExample.createCriteria().andFalidEqualTo(abilitylevel.getFkeyid());
//                    List<TAbilityCondition> tAbilityConditionList = tAbilityConditionService.findByExample(tAbilityConditionExample);
//                    for (TAbilityCondition condition : tAbilityConditionList) {
//                        FConditionScore += condition.getFconditionscore();
//                    }
//                    abilitylevel_object.put("FConditionScore", FConditionScore);
                } else {
                    abilitylevel_object.put("FName", "*****");
                    abilitylevel_object.put("FPoints", "*****");
                    abilitylevel_object.put("FNote", "*****");
                    abilitylevel_object.put("FCID", "*****");
                    abilitylevel_object.put("FUID", "*****");
                    abilitylevel_object.put("FCDATE", "*****");
                    abilitylevel_object.put("FUDATE", "*****");
                }

                abilitylevel_object.put("FState", abilitylevel.getFstate());
                abilitylevel_Array.add(abilitylevel_object);
            }
            // 返回值
            object.put("list", abilitylevel_Array);
            object.put("total", abilitylevelPageInfo.getTotal()); // 总行数
            object.put("page", abilitylevelPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取能力等级  联动
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDataabilitylevelSelectld", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDataabilitylevelSelectld(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String abilityID = jsonParam.getString("abilityID");
        try {
            abilityID = abilityID == null || abilityID.equals("") ? "0" : ParamTools.getdeParam(abilityID);
            // 获取数据库记录
            JSONArray abilitylevel_Array = new JSONArray();
            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria1 = tAbilityTreeExample.createCriteria();
            criteria1.andFdelEqualTo(1);
            criteria1.andFpidEqualTo(Long.parseLong(abilityID));
            criteria1.andFnodetypeEqualTo(2);
            criteria1.andFstateEqualTo(1);
            tAbilityTreeExample.setOrderByClause("FCDATE asc");
            List<TAbilityTree> abilitylevel_list = tAbilityTreeService.findByExample(tAbilityTreeExample);
            for (TAbilityTree abilitylevel : abilitylevel_list) {
                JSONObject abilitylevel_object = new JSONObject();
                abilitylevel_object.put("id", ParamTools.getEnParam(abilitylevel.getFkeyid().toString()));
                abilitylevel_object.put("text", abilitylevel.getFname());
                abilitylevel_Array.add(abilitylevel_object);
            }
            // 返回值
            object.put("list", abilitylevel_Array);
            object.put("results", abilitylevel_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 删除能力等级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除能力等级信息")
    @ResponseBody
    @RequestMapping(value = "/delabilitylevel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delabilitylevel(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            TAbilityTree tAbilityTree = new TAbilityTree();
            tAbilityTree.setFkeyid(Long.parseLong(id));
            tAbilityTree.setFdel(2);//删除
            tAbilityTree.setFuid(Long.parseLong(uid));
            tAbilityTree.setFudate(new Date());
            tAbilityTreeService.update(tAbilityTree);


            //处理 课程绑定能力中 包含这个能力等级的所有项
            TCourseAbilityExample tCourseAbilityExample = new TCourseAbilityExample();
            tCourseAbilityExample.createCriteria().andFabilitylevelidEqualTo(Long.parseLong(id));
            TCourseAbility tCourseAbility = new TCourseAbility();
            tCourseAbility.setFstate(0);
            tCourseAbility.setFuid(Long.parseLong(uid));
            tCourseAbility.setFudate(new Date());
            tCourseAbilityService.updateByExample(tCourseAbility, tCourseAbilityExample);


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
     * 根据ID获取能力等级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilitylevelInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilitylevelInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询能力等级信息
            TAbilityTree abilitylevel = tAbilityTreeService.findById(key);
            JSONObject abilitylevel_object = new JSONObject();
            abilitylevel_object.put("key", ParamTools.getEnParam(abilitylevel.getFkeyid().toString()));
            abilitylevel_object.put("FName", abilitylevel.getFname());
            abilitylevel_object.put("FPoints", abilitylevel.getFpoints());
            abilitylevel_object.put("FScoreMin", abilitylevel.getFscoremin());
            abilitylevel_object.put("FScoreMax", abilitylevel.getFscoremax());
            abilitylevel_object.put("FNote", abilitylevel.getFnote() == null ? "" : abilitylevel.getFnote());
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
     * 添加能力等级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加能力等级信息")
    @ResponseBody
    @RequestMapping(value = "/addabilitylevel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addabilitylevel(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FNote = jsonParam.getString("FNote");
        String FName = jsonParam.getString("FName");
        float FPoints = jsonParam.getFloat("FPoints");
        String FAbilityID = jsonParam.getString("FAbilityID");
        int FScoreMin = jsonParam.getInteger("FScoreMin");
        int FScoreMax = jsonParam.getInteger("FScoreMax");

        try {
            FAbilityID = FAbilityID == null ? "0" : ParamTools.getdeParam(FAbilityID);


            TAbilityTree abilityTree = tAbilityTreeService.findById(Long.parseLong(FAbilityID));
            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
            criteria.andFnameEqualTo(FName);
            criteria.andFpidEqualTo(Long.parseLong(FAbilityID));
            criteria.andFnodetypeEqualTo(2);
            List<TAbilityTree> levelList = tAbilityTreeService.findByExample(tAbilityTreeExample);
            if (levelList.size() == 0) {
                String CookiesLoginabilitylevelID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilitylevelID != null && !CookiesLoginabilitylevelID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilitylevelID);
                }

                String fpath = abilityTree.getFpath();
                int fdiv = abilityTree.getFdiv() + 1;

                // 新建能力等级信息
                TAbilityTree abilitylevel = new TAbilityTree();
                abilitylevel.setFname(FName);
                abilitylevel.setFpid(Long.parseLong(FAbilityID));
                abilitylevel.setFnote(FNote);
                abilitylevel.setFpoints(FPoints);
                abilitylevel.setFscoremax(FScoreMax);
                abilitylevel.setFscoremin(FScoreMin);
                abilitylevel.setFcid(Long.parseLong(uid));
                abilitylevel.setFcdate(new Date());
                abilitylevel.setFgpid(abilityTree.getFgpid());
                abilitylevel.setFnodetype(2);
                abilitylevel.setFdiv(fdiv);
                tAbilityTreeService.save(abilitylevel);
                abilitylevel.setFpath(fpath + "|_" + abilitylevel.getFkeyid().toString() + "_|");
                tAbilityTreeService.update(abilitylevel);

                abilitylevel = new TAbilityTree();
                abilitylevel.setFkeyid(Long.parseLong(FAbilityID));
                abilitylevel.setFisleaf(0);
                tAbilityTreeService.update(abilitylevel);

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
     * 修改能力等级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改能力等级信息")
    @ResponseBody
    @RequestMapping(value = "/updateabilitylevel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateabilitylevel(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FNote = jsonParam.getString("FNote");
        String FName = jsonParam.getString("FName");
        float FPoints = jsonParam.getFloat("FPoints");
        int FScoreMin = jsonParam.getInteger("FScoreMin");
        int FScoreMax = jsonParam.getInteger("FScoreMax");
        String FAbilityID = jsonParam.getString("FAbilityID");
        try {
            FAbilityID = FAbilityID == null ? "0" : ParamTools.getdeParam(FAbilityID);
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
            criteria.andFnameEqualTo(FName);
            criteria.andFpidEqualTo(Long.parseLong(FAbilityID));
            criteria.andFkeyidNotEqualTo(key);
            criteria.andFnodetypeEqualTo(2);
            List<TAbilityTree> levelList = tAbilityTreeService.findByExample(tAbilityTreeExample);
            if (levelList.size() == 0) {
                String CookiesLoginabilitylevelID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilitylevelID != null && !CookiesLoginabilitylevelID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilitylevelID);
                }
                // 更新能力等级信息
                TAbilityTree abilitylevel = new TAbilityTree();
                abilitylevel.setFkeyid(key);
                abilitylevel.setFname(FName);
                abilitylevel.setFnote(FNote);
                abilitylevel.setFpoints(FPoints);
                abilitylevel.setFscoremax(FScoreMax);
                abilitylevel.setFscoremin(FScoreMin);
                abilitylevel.setFuid(Long.parseLong(uid));
                abilitylevel.setFudate(new Date());
                tAbilityTreeService.update(abilitylevel);
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

    /*
     *
     *     考核条件
     *
     * */


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
            List<Integer> nodetypelist = new ArrayList<>();//查询权重的 类型 条件和考核方式
            nodetypelist.add(3);
            nodetypelist.add(4);
            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            tAbilityTreeExample.createCriteria().andFpidEqualTo(Long.parseLong(FALID)).andFnodetypeEqualTo(3).andFdelEqualTo(1).andFdelEqualTo(1);//考核条件
            List<TAbilityTree> abilityConditionList = tAbilityTreeService.findByExample(tAbilityTreeExample);
            if (abilityConditionList.size() > 0) {
                objall.put("draw", 0);
                objall.put("recordsTotal", 0);
                objall.put("recordsFiltered", 0);
                float FMethodWeight2 = 0;
                TAbilityLevelCondition levelCondition = null;
                TAbilityConditionAssessmentMethodExample abilityConditionAssessmentMethodExample2;
                TAbilityConditionAssessmentMethodExample.Criteria criteria2;
                List<TAbilityTree> abilityConditionAssessmentMethodList;
                JSONObject obj2 = new JSONObject();
                TAbilityConditionSExample tAbilityConditionSExample2;
                TAbilityConditionSExample.Criteria criteria1;
                List<TAbilityTree> conditionSList;
                for (TAbilityTree abilityCondition : abilityConditionList) {
                    FMethodWeight2 = 0;
                    levelCondition = tAbilityLevelConditionService.findById(abilityCondition.getFalcid());
                    obj1 = new JSONObject();//根节点内容
                    obj1.put("key", ParamTools.getEnParam(abilityCondition.getFkeyid().toString()));
                    obj1.put("FPID", ParamTools.getEnParam(abilityCondition.getFpid().toString()));

//                    obj1.put("FConditionName", abilityCondition.getFname() == null ? "" : abilityCondition.getFname());
//                    obj1.put("FConditionScore", abilityCondition.getFconditionscore() == null ? "" : abilityCondition.getFconditionscore());
                    obj1.put("FConditionName", levelCondition == null ? "" : levelCondition.getFname());
                    obj1.put("FConditionScore", levelCondition == null ? "" : levelCondition.getFscore());
                    obj1.put("FNote", abilityCondition.getFnote() == null ? "" : abilityCondition.getFnote());
                    TAbilityTreeExample tAbilityTreeExample1 = new TAbilityTreeExample();
                    tAbilityTreeExample1.createCriteria().andFpidEqualTo(abilityCondition.getFkeyid()).andFnodetypeIn(nodetypelist).andFdelEqualTo(1).andFdelEqualTo(1);//考核方式
                    abilityConditionAssessmentMethodList = tAbilityTreeService.findByExample(tAbilityTreeExample1);
                    if (abilityConditionAssessmentMethodList.size() > 0) {
                        for (TAbilityTree abilityConditionAssessmentMethod : abilityConditionAssessmentMethodList) {
                            FMethodWeight2 += abilityConditionAssessmentMethod.getFmethodweight();
                        }
                    }
                    obj1.put("FMethodWeight", String.format("%.2f", FMethodWeight2));
                    obj1.put("FMethodWeight2", String.format("%.2f", abilityCondition.getFmethodweight() == null ? 0 : abilityCondition.getFmethodweight()));
                    obj1.put("FType", 1);
                    obj1.put("FNodeType", abilityCondition.getFnodetype());
                    if (abilityCondition.getFisleaf() == 0)
                        this.getTAbilityConditionS(obj1, abilityCondition.getFkeyid());
                    obj1Listarr.add(obj1);
                }
                objall.put("data", obj1Listarr);
                object.put("datalist2", objall);
            }
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    private void getTAbilityConditionS(JSONObject obj2, Long FPID) {

        JSONArray zibiaoListarr = new JSONArray();
        TAbilityConditionAssessmentMethodExample abilityConditionAssessmentMethodExample2;
        TAbilityConditionAssessmentMethodExample.Criteria criteria2;
        List<TAbilityTree> abilityConditionAssessmentMethodList;
        TAbilityConditionSExample tAbilityConditionSExample2;
        TAbilityConditionSExample.Criteria criteria1;
        List<TAbilityTree> conditionSList;
        float FMethodWeight2 = 0;
        List<Integer> nodetypelist = new ArrayList<>();//查询权重的 类型 条件和考核方式
        nodetypelist.add(3);
        nodetypelist.add(4);
        try {
            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria3 = tAbilityTreeExample.createCriteria();
            criteria3.andFpidEqualTo(FPID);
            criteria3.andFdelEqualTo(1);
            criteria3.andFnodetypeEqualTo(3);
            tAbilityTreeExample.setOrderByClause("FCDATE asc");
            conditionSList = tAbilityTreeService.findByExample(tAbilityTreeExample);
            if (conditionSList.size() > 0) {
                JSONObject obj3 = new JSONObject();
                TAbilityLevelCondition levelCondition = null;
                for (TAbilityTree tAbilityConditionS : conditionSList) {
                    levelCondition = tAbilityLevelConditionService.findById(tAbilityConditionS.getFalcid());
                    FMethodWeight2 = 0;
                    obj3 = new JSONObject();
                    obj3.put("key", ParamTools.getEnParam(tAbilityConditionS.getFkeyid().toString()));
                    obj3.put("FPID", ParamTools.getEnParam(tAbilityConditionS.getFpid().toString()));

//                    obj3.put("FConditionName", tAbilityConditionS.getFname() == null ? "" : tAbilityConditionS.getFname());
//                    obj3.put("FConditionScore", tAbilityConditionS.getFconditionscore() == null ? "" : tAbilityConditionS.getFconditionscore());
                    obj3.put("FConditionName", levelCondition == null ? "" : levelCondition.getFname());

                    obj3.put("FConditionScore", levelCondition == null ? "" : levelCondition.getFscore());
                    obj3.put("FNote", tAbilityConditionS.getFnote() == null ? "" : tAbilityConditionS.getFnote());
                    TAbilityTreeExample tAbilityTreeExample1 = new TAbilityTreeExample();
                    tAbilityTreeExample1.createCriteria().andFpidEqualTo(tAbilityConditionS.getFkeyid()).andFnodetypeIn(nodetypelist).andFdelEqualTo(1);//
                    abilityConditionAssessmentMethodList = tAbilityTreeService.findByExample(tAbilityTreeExample1);
                    if (abilityConditionAssessmentMethodList.size() > 0) {
                        for (TAbilityTree abilityConditionAssessmentMethod : abilityConditionAssessmentMethodList) {
                            FMethodWeight2 += abilityConditionAssessmentMethod.getFmethodweight();
                        }
                    }
                    obj3.put("FMethodWeight", String.format("%.2f", FMethodWeight2));
                    obj3.put("FMethodWeight2", String.format("%.2f", tAbilityConditionS.getFmethodweight() == null ? 0 : tAbilityConditionS.getFmethodweight()));

                    obj3.put("FType", 2);
                    obj3.put("FNodeType", tAbilityConditionS.getFnodetype());
                    if (tAbilityConditionS.getFisleaf() == 0)
                        this.getTAbilityConditionS(obj3, tAbilityConditionS.getFkeyid());
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
            TAbilityTree abilitylevel = tAbilityTreeService.findById(key);
            JSONObject abilitylevel_object = new JSONObject();
            abilitylevel_object.put("key", ParamTools.getEnParam(abilitylevel.getFkeyid().toString()));
            abilitylevel_object.put("FALCID", ParamTools.getEnParam(abilitylevel.getFalcid().toString()));
            abilitylevel_object.put("FConditionName", abilitylevel.getFname() == null ? "" : abilitylevel.getFname());
            abilitylevel_object.put("FConditionScore", abilitylevel.getFconditionscore() == null ? 0 : abilitylevel.getFconditionscore());
            abilitylevel_object.put("FNote", abilitylevel.getFnote() == null ? "" : abilitylevel.getFnote());
            abilitylevel_object.put("FMethodWeight", abilitylevel.getFmethodweight() == null ? 0 : abilitylevel.getFmethodweight());
            TAbilityLevelCondition levelCondition = tAbilityLevelConditionService.findById(abilitylevel.getFalcid());
            abilitylevel_object.put("FAbilitLevelConditionName", levelCondition == null ? 0 : levelCondition.getFname());
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
     * 根据ID获取条件下权重总数
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
            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
            criteria.andFpidEqualTo(key);
            criteria.andFdelEqualTo(1);
            criteria.andFnodetypeEqualTo(3);

            List<TAbilityTree> abilityConditions = tAbilityTreeService.findByExample(tAbilityTreeExample);
            if (abilityConditions.size() > 0) {
                for (TAbilityTree abilityCondition : abilityConditions) {
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

        String FConditionID = jsonParam.getString("FConditionID");
        String FNote = jsonParam.getString("FNote");
        String FConditionScore = jsonParam.getString("FConditionScore");
        String FAbilityLevelID = jsonParam.getString("FAbilityLevelID");//上级id
        float FMethodWeight = jsonParam.getFloat("FMethodWeight");
        try {

            FAbilityLevelID = FAbilityLevelID == null ? "0" : ParamTools.getdeParam(FAbilityLevelID);
            FConditionID = FConditionID == null ? "0" : ParamTools.getdeParam(FConditionID);
//            if(maxscore - sumscore - Integer.parseInt(FConditionScore) >= 0){
            float FMethodWeight2 = 0;

            //当前条件下考核方式和条件 累加的权重
            List<Integer> nodetypelist = new ArrayList<>();
            nodetypelist.add(3);
            nodetypelist.add(4);

            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
            criteria.andFnodetypeIn(nodetypelist);
            criteria.andFdelEqualTo(1);
            criteria.andFpidEqualTo(Long.parseLong(FAbilityLevelID));
            List<TAbilityTree> abilityConditions = tAbilityTreeService.findByExample(tAbilityTreeExample);
            if (abilityConditions.size() > 0) {
                for (TAbilityTree abilityCondition : abilityConditions) {
                    FMethodWeight2 += abilityCondition.getFmethodweight() == null ? 0 : abilityCondition.getFmethodweight();
                }
            }
            if (100 - FMethodWeight2 - FMethodWeight >= 0) {
                TAbilityTree tAbilityTree = tAbilityTreeService.findById(Long.parseLong(FAbilityLevelID));
                TAbilityTreeExample tAbilityTreeExample2 = new TAbilityTreeExample();
                TAbilityTreeExample.Criteria criteria1 = tAbilityTreeExample2.createCriteria();
                criteria1.andFnodetypeEqualTo(3);
                criteria1.andFdelEqualTo(1);
                criteria1.andFpidEqualTo(Long.parseLong(FAbilityLevelID));
                criteria1.andFalcidEqualTo(Long.parseLong(FConditionID));//改为考核条件ID
                List<TAbilityTree> conditionList = tAbilityTreeService.findByExample(tAbilityTreeExample2);
                if (conditionList.size() == 0) {
                    String CookiesLoginabilitylevelID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                    String uid = ""; // 当前登录用户ID
                    if (CookiesLoginabilitylevelID != null && !CookiesLoginabilitylevelID.equals("")) {
                        uid = ParamTools.getdeParam(CookiesLoginabilitylevelID);
                    }
                    String fpath = tAbilityTree.getFpath();
                    int fdiv = tAbilityTree.getFdiv() + 1;
                    TAbilityLevelCondition levelCondition = tAbilityLevelConditionService.findById(Long.parseLong(FConditionID));
                    // 新建考核条件信息
                    TAbilityTree abilitylevel = new TAbilityTree();
                    abilitylevel.setFname(levelCondition.getFname());
                    abilitylevel.setFpid(Long.parseLong(FAbilityLevelID));
                    abilitylevel.setFnote(FNote);
                    abilitylevel.setFmethodweight(FMethodWeight);
                    abilitylevel.setFconditionscore(levelCondition.getFscore());
                    abilitylevel.setFcid(Long.parseLong(uid));
                    abilitylevel.setFcdate(new Date());
                    abilitylevel.setFgpid(tAbilityTree.getFgpid());
                    abilitylevel.setFnodetype(3);
                    abilitylevel.setFdiv(fdiv);
                    abilitylevel.setFalcid(Long.parseLong(FConditionID));
                    tAbilityTreeService.save(abilitylevel);
                    abilitylevel.setFpath(fpath + "|_" + abilitylevel.getFkeyid().toString() + "_|");
                    tAbilityTreeService.update(abilitylevel);

                    abilitylevel = new TAbilityTree();
                    abilitylevel.setFkeyid(Long.parseLong(FAbilityLevelID));
                    abilitylevel.setFisleaf(0);
                    tAbilityTreeService.update(abilitylevel);
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
        String FConditionID = jsonParam.getString("FConditionID");
        String FConditionScore = jsonParam.getString("FConditionScore");
        String FNote = jsonParam.getString("FNote");
        String FAbilityID = jsonParam.getString("FAbilityID");
        String FAbilityLevelID = jsonParam.getString("FAbilityLevelID");
        String FPID = jsonParam.getString("FPID");
        float FMethodWeight = jsonParam.getFloat("FMethodWeight");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            FAbilityID = FAbilityID == null ? "0" : ParamTools.getdeParam(FAbilityID);
            FAbilityLevelID = FAbilityLevelID == null ? "0" : ParamTools.getdeParam(FAbilityLevelID);
            FConditionID = FConditionID == null ? "0" : ParamTools.getdeParam(FConditionID);
            FPID = FPID == null ? "0" : ParamTools.getdeParam(FPID);
            float FMethodWeight2 = 0;
            //当前条件下考核方式和条件 累加的权重
            List<Integer> nodetypelist = new ArrayList<>();
            nodetypelist.add(3);
            nodetypelist.add(4);

            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
            criteria.andFnodetypeIn(nodetypelist);
            criteria.andFdelEqualTo(1);
            criteria.andFpidEqualTo(Long.parseLong(FPID));
            criteria.andFkeyidNotEqualTo(Long.parseLong(id));
            List<TAbilityTree> abilityConditions = tAbilityTreeService.findByExample(tAbilityTreeExample);
            if (abilityConditions.size() > 0) {
                for (TAbilityTree abilityCondition : abilityConditions) {
                    FMethodWeight2 += abilityCondition.getFmethodweight() == null ? 0 : abilityCondition.getFmethodweight();
                }
            }
            if (100 - FMethodWeight2 - FMethodWeight >= 0) {
                tAbilityTreeExample = new TAbilityTreeExample();
                criteria = tAbilityTreeExample.createCriteria();
                criteria.andFkeyidNotEqualTo(Long.parseLong(id));
                criteria.andFalcidEqualTo(Long.parseLong(FConditionID));
                criteria.andFpidEqualTo(Long.parseLong(FPID));
                criteria.andFdelEqualTo(1);
                criteria.andFnodetypeEqualTo(3);
                List<TAbilityTree> conditionList = tAbilityTreeService.findByExample(tAbilityTreeExample);
                if (conditionList.size() == 0) {
                    String CookiesLoginabilitylevelID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                    String uid = ""; // 当前登录用户ID
                    if (CookiesLoginabilitylevelID != null && !CookiesLoginabilitylevelID.equals("")) {
                        uid = ParamTools.getdeParam(CookiesLoginabilitylevelID);
                    }
                    TAbilityLevelCondition abilityLevelCondition = tAbilityLevelConditionService.findById(Long.parseLong(FConditionID));
                    // 更新能力等级信息
                    TAbilityTree abilitylevel = new TAbilityTree();
                    abilitylevel.setFkeyid(Long.parseLong(id));
                    abilitylevel.setFname(abilityLevelCondition.getFname());
                    abilitylevel.setFnote(FNote);
                    abilitylevel.setFmethodweight(FMethodWeight);
                    abilitylevel.setFconditionscore(abilityLevelCondition.getFscore());
                    abilitylevel.setFuid(Long.parseLong(uid));
                    abilitylevel.setFudate(new Date());
                    abilitylevel.setFalcid(Long.parseLong(FConditionID));
                    tAbilityTreeService.update(abilitylevel);

                    TAbilityTreeExample tAbilityTreeExample1 = new TAbilityTreeExample();
                    TAbilityTreeExample.Criteria criteria1 = tAbilityTreeExample1.createCriteria();
                    criteria1.andFpidEqualTo(Long.parseLong(id));
                    criteria1.andFnodetypeEqualTo(4);
                    criteria1.andFdelEqualTo(1);

                    abilitylevel = new TAbilityTree();
                    abilitylevel.setFname(abilityLevelCondition.getFname());
                    abilitylevel.setFuid(Long.parseLong(uid));
                    abilitylevel.setFudate(new Date());
                    tAbilityTreeService.updateByExample(abilitylevel, tAbilityTreeExample1);

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
            TAbilityTree tAbilityTree = new TAbilityTree();
            tAbilityTree.setFkeyid(Long.parseLong(id));
            tAbilityTree.setFuid(Long.parseLong(uid));
            tAbilityTree.setFudate(new Date());
            tAbilityTree.setFdel(2);
            tAbilityTreeService.update(tAbilityTree);
            // 返回值
            object.put("result", "success");

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /*
     *
     *   考核方式
     * */


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
//        String FALID = jsonParam.getString("FALID");
        String ConditionID = jsonParam.getString("ConditionID");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray abilityconditionassessmentmethod_Array = new JSONArray();

            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
            // 查询条件

//            FALID = FALID == null ? "0" : ParamTools.getdeParam(FALID);
            ConditionID = ConditionID == null ? "0" : ParamTools.getdeParam(ConditionID);
            criteria.andFpidEqualTo(Long.parseLong(ConditionID));
            criteria.andFnodetypeEqualTo(4);
            criteria.andFdelEqualTo(1);


            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                tAbilityTreeExample.setOrderByClause(orderSql.substring(1));
            } else {
                tAbilityTreeExample.setOrderByClause("FCDATE asc , FMethodID asc");
            }
            PageInfo<TAbilityTree> abilityconditionassessmentmethodPageInfo = tAbilityTreeService.findByExampleMapper(tAbilityTreeExample, (page - 1) * results, results);
            List<TAbilityTree> abilityconditionassessmentmethod_list = abilityconditionassessmentmethodPageInfo.getList();

            for (TAbilityTree abilityconditionassessmentmethod : abilityconditionassessmentmethod_list) {
                JSONObject abilityconditionassessmentmethod_object = new JSONObject();
                abilityconditionassessmentmethod_object.put("key", ParamTools.getEnParam(abilityconditionassessmentmethod.getFkeyid().toString()));
                abilityconditionassessmentmethod_object.put("FConditionID", ParamTools.getEnParam(abilityconditionassessmentmethod.getFpid().toString()));
                abilityconditionassessmentmethod_object.put("FMethodID", ParamTools.getEnParam(abilityconditionassessmentmethod.getFmethodid().toString()));
                TAbilityAssessmentMethod assessmentMethod = tAbilityAssessmentMethodService.findById(abilityconditionassessmentmethod.getFmethodid());
                abilityconditionassessmentmethod_object.put("FMethodName", assessmentMethod == null ? "" : assessmentMethod.getFmethodname());
                abilityconditionassessmentmethod_object.put("FConditionName", abilityconditionassessmentmethod.getFname());
                abilityconditionassessmentmethod_object.put("FMethodWeight", abilityconditionassessmentmethod.getFmethodweight() == null ? "" : abilityconditionassessmentmethod.getFmethodweight());
                abilityconditionassessmentmethod_object.put("FCID", abilityconditionassessmentmethod.getFcid());
                abilityconditionassessmentmethod_object.put("FUID", abilityconditionassessmentmethod.getFuid());
                abilityconditionassessmentmethod_object.put("FCDATE", abilityconditionassessmentmethod.getFcdate());
                abilityconditionassessmentmethod_object.put("FUDATE", abilityconditionassessmentmethod.getFudate());

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
            TAbilityTree abilityConditionAssessmentMethod = tAbilityTreeService.findById(key);
            JSONObject abilityConditionAssessmentMethod_object = new JSONObject();
            abilityConditionAssessmentMethod_object.put("key", ParamTools.getEnParam(abilityConditionAssessmentMethod.getFkeyid().toString()));
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
            float FMethodWeight = 0;

            List<Integer> nodetypelist = new ArrayList<>();
            nodetypelist.add(3);
            nodetypelist.add(4);
            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
            criteria.andFpidEqualTo(Long.parseLong(id));
            criteria.andFnodetypeIn(nodetypelist);
            criteria.andFdelEqualTo(1);
            List<TAbilityTree> abilityConditionAssessmentMethodList = tAbilityTreeService.findByExample(tAbilityTreeExample);
            if (abilityConditionAssessmentMethodList.size() > 0) {
                for (TAbilityTree abilityConditionAssessmentMethod : abilityConditionAssessmentMethodList) {
                    FMethodWeight += abilityConditionAssessmentMethod.getFmethodweight();
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
     * 新增能力等级考核条件与方式关系信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("新增能力等级考核方式信息")
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
            float FMethodWeight2 = 0;
            List<Integer> nodetypelist = new ArrayList<>();
            nodetypelist.add(3);
            nodetypelist.add(4);
            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria1 = tAbilityTreeExample.createCriteria();
            criteria1.andFdelEqualTo(1);
            criteria1.andFnodetypeIn(nodetypelist);
            criteria1.andFpidEqualTo(Long.parseLong(FAbilityConditionID));
            List<TAbilityTree> abilityConditionAssessmentMethodList = tAbilityTreeService.findByExample(tAbilityTreeExample);
            if (abilityConditionAssessmentMethodList.size() > 0) {
                for (TAbilityTree abilityConditionAssessmentMethod : abilityConditionAssessmentMethodList) {
                    FMethodWeight2 += abilityConditionAssessmentMethod.getFmethodweight();
                }
            }

            if (100 - FMethodWeight2 - FMethodWeight >= 0) {
                tAbilityTreeExample = new TAbilityTreeExample();
                criteria1 = tAbilityTreeExample.createCriteria();
                criteria1.andFdelEqualTo(1);
                criteria1.andFnodetypeEqualTo(4);
                criteria1.andFpidEqualTo(Long.parseLong(FAbilityConditionID));
                criteria1.andFmethodidEqualTo(Long.parseLong(FMethodID));
                List<TAbilityTree> conditionList = tAbilityTreeService.findByExample(tAbilityTreeExample);
                if (conditionList.size() == 0) {
                    String CookiesLoginabilitylevelID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                    String uid = ""; // 当前登录用户ID
                    if (CookiesLoginabilitylevelID != null && !CookiesLoginabilitylevelID.equals("")) {
                        uid = ParamTools.getdeParam(CookiesLoginabilitylevelID);
                    }
                    TAbilityTree serviceById = tAbilityTreeService.findById(Long.parseLong(FAbilityConditionID));
                    String fpath = serviceById.getFpath();
                    int fdiv = serviceById.getFdiv() + 1;
                    // 新建考核方式信息
                    TAbilityTree abilitylevel = new TAbilityTree();
                    abilitylevel.setFname(serviceById.getFname());
                    abilitylevel.setFpid(Long.parseLong(FAbilityConditionID));
                    abilitylevel.setFnote("");
                    abilitylevel.setFcid(Long.parseLong(uid));
                    abilitylevel.setFcdate(new Date());
                    abilitylevel.setFgpid(serviceById.getFgpid());
                    abilitylevel.setFnodetype(4);
                    abilitylevel.setFdiv(fdiv);
                    abilitylevel.setFmethodweight(FMethodWeight);
                    abilitylevel.setFmethodid(Long.parseLong(FMethodID));
                    tAbilityTreeService.save(abilitylevel);
                    abilitylevel.setFpath(fpath + "|_" + abilitylevel.getFkeyid().toString() + "_|");
                    tAbilityTreeService.update(abilitylevel);

                    abilitylevel = new TAbilityTree();
                    abilitylevel.setFkeyid(Long.parseLong(FAbilityConditionID));
                    abilitylevel.setFisleaf(0);
                    tAbilityTreeService.update(abilitylevel);


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
    @LogOperation("修改能力等级考核方式信息")
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
            List<Integer> nodetypelist = new ArrayList<>();
            nodetypelist.add(3);
            nodetypelist.add(4);
            float FMethodWeight2 = 0;
            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria1 = tAbilityTreeExample.createCriteria();
            criteria1.andFdelEqualTo(1);
            criteria1.andFnodetypeIn(nodetypelist);
            criteria1.andFpidEqualTo(Long.parseLong(FAbilityConditionID));
            criteria1.andFkeyidNotEqualTo(Long.parseLong(id));
            List<TAbilityTree> abilityConditionAssessmentMethodList = tAbilityTreeService.findByExample(tAbilityTreeExample);
            if (abilityConditionAssessmentMethodList.size() > 0) {
                for (TAbilityTree abilityConditionAssessmentMethod : abilityConditionAssessmentMethodList) {
                    FMethodWeight2 += abilityConditionAssessmentMethod.getFmethodweight();
                }
            }


            if (100 - FMethodWeight2 - FMethodWeight >= 0) {
                tAbilityTreeExample = new TAbilityTreeExample();
                criteria1 = tAbilityTreeExample.createCriteria();
                criteria1.andFdelEqualTo(1);
                criteria1.andFnodetypeEqualTo(4);
                criteria1.andFpidEqualTo(Long.parseLong(FAbilityConditionID));
                criteria1.andFmethodidEqualTo(Long.parseLong(FMethodID));
                criteria1.andFkeyidNotEqualTo(Long.parseLong(id));
                List<TAbilityTree> conditionList = tAbilityTreeService.findByExample(tAbilityTreeExample);
                if (conditionList.size() == 0) {
                    String CookiesLoginabilitylevelID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                    String uid = ""; // 当前登录用户ID
                    if (CookiesLoginabilitylevelID != null && !CookiesLoginabilitylevelID.equals("")) {
                        uid = ParamTools.getdeParam(CookiesLoginabilitylevelID);
                    }

                    TAbilityTree abilitylevel = new TAbilityTree();
                    abilitylevel.setFkeyid(Long.parseLong(id));
                    abilitylevel.setFuid(Long.parseLong(uid));
                    abilitylevel.setFudate(new Date());
                    abilitylevel.setFmethodweight(FMethodWeight);
                    abilitylevel.setFmethodid(Long.parseLong(FMethodID));
                    tAbilityTreeService.update(abilitylevel);
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
            TAbilityTree abilityTree = new TAbilityTree();
            abilityTree.setFkeyid(Long.parseLong(id));
            abilityTree.setFuid(Long.parseLong(uid));
            abilityTree.setFudate(new Date());
            abilityTree.setFdel(2);
            tAbilityTreeService.update(abilityTree);
            // 返回值
            object.put("result", "success");

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /*
     *   考核子条件
     * */

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
            TAbilityTree abilitylevel = tAbilityTreeService.findById(key);
            JSONObject abilitylevel_object = new JSONObject();
            abilitylevel_object.put("key", ParamTools.getEnParam(abilitylevel.getFkeyid().toString()));
            abilitylevel_object.put("FPID", ParamTools.getEnParam(abilitylevel.getFpid().toString()));
            abilitylevel_object.put("FConditionName", abilitylevel.getFname() == null ? "" : abilitylevel.getFname());

            abilitylevel_object.put("FConditionScore", abilitylevel.getFconditionscore() == null ? 0 : abilitylevel.getFconditionscore());
            abilitylevel_object.put("FNote", abilitylevel.getFnote() == null ? "" : abilitylevel.getFnote());
            abilitylevel_object.put("FMethodWeight", abilitylevel.getFmethodweight() == null ? "" : abilitylevel.getFmethodweight());
            abilitylevel_object.put("FCID", abilitylevel.getFcid());
            abilitylevel_object.put("FUID", abilitylevel.getFuid());
            abilitylevel_object.put("FCDATE", abilitylevel.getFcdate());
            abilitylevel_object.put("FUDATE", abilitylevel.getFudate());
            abilitylevel_object.put("FState", abilitylevel.getFstate());


            TAbilityTree abilityTree = tAbilityTreeService.findById(abilitylevel.getFpid());
            abilitylevel_object.put("FFName", abilityTree == null ? "" : abilityTree.getFname());
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
     * 根据ID获取能力等级考核子条件权重信息
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
            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
            criteria.andFpidEqualTo(key);
            List<TAbilityTree> tAbilityTreeList = tAbilityTreeService.findByExample(tAbilityTreeExample);
            if (tAbilityTreeList.size() > 0) {
                for (TAbilityTree abilityConditionS : tAbilityTreeList) {
                    FMethodWeight += abilityConditionS.getFmethodweight() == null ? 0 : abilityConditionS.getFmethodweight();
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
     * 添加考核子条件信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("添加考核子条件信息")
    @ResponseBody
    @RequestMapping(value = "/addabilityConditions", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addabilityConditions(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String f_p_id = jsonParam.getString("FPID");
        String FConditionID = jsonParam.getString("FConditionID");
        String FNote = jsonParam.getString("FNote");
        String FConditionScore = jsonParam.getString("FConditionScore");
//        String FAbilityLevelID = jsonParam.getString("FAbilityLevelID");
        String ftype = jsonParam.getString("ftype");
        float FMethodWeight = jsonParam.getFloat("FMethodWeight");

        try {
//            FAbilityLevelID = FAbilityLevelID == null ? "0" : ParamTools.getdeParam(FAbilityLevelID);
            FConditionID = FConditionID == null ? "0" : ParamTools.getdeParam(FConditionID);

            Long pId = f_p_id.equals("1") ? 1L : Long.parseLong(ParamTools.getdeParam(f_p_id));


            float FMethodWeight2 = 0;
            //当前条件下考核方式和条件 累加的权重
            List<Integer> nodetypelist = new ArrayList<>();
            nodetypelist.add(3);
            nodetypelist.add(4);

            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
            criteria.andFnodetypeIn(nodetypelist);
            criteria.andFdelEqualTo(1);
            criteria.andFpidEqualTo(pId);
            List<TAbilityTree> abilityConditions = tAbilityTreeService.findByExample(tAbilityTreeExample);
            if (abilityConditions.size() > 0) {
                for (TAbilityTree abilityCondition : abilityConditions) {
                    FMethodWeight2 += abilityCondition.getFmethodweight() == null ? 0 : abilityCondition.getFmethodweight();
                }
            }
            if (100 - FMethodWeight2 - FMethodWeight >= 0) {
                TAbilityTree tAbilityTree = tAbilityTreeService.findById(pId);
                TAbilityTreeExample tAbilityTreeExample2 = new TAbilityTreeExample();
                TAbilityTreeExample.Criteria criteria1 = tAbilityTreeExample2.createCriteria();
                criteria1.andFnodetypeEqualTo(3);
                criteria1.andFdelEqualTo(1);
                criteria1.andFpidEqualTo(pId);
                criteria1.andFalcidEqualTo(Long.parseLong(FConditionID));
                List<TAbilityTree> conditionList = tAbilityTreeService.findByExample(tAbilityTreeExample2);
                if (conditionList.size() == 0) {
                    String CookiesLoginabilitylevelID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                    String uid = ""; // 当前登录用户ID
                    if (CookiesLoginabilitylevelID != null && !CookiesLoginabilitylevelID.equals("")) {
                        uid = ParamTools.getdeParam(CookiesLoginabilitylevelID);
                    }
                    String fpath = tAbilityTree.getFpath();
                    int fdiv = tAbilityTree.getFdiv() + 1;
                    TAbilityLevelCondition levelCondition = tAbilityLevelConditionService.findById(Long.parseLong(FConditionID));
                    // 新建考核条件信息
                    TAbilityTree abilitylevel = new TAbilityTree();
                    abilitylevel.setFname(levelCondition.getFname());
                    abilitylevel.setFpid(pId);
                    abilitylevel.setFnote(FNote);
                    abilitylevel.setFmethodweight(FMethodWeight);
                    abilitylevel.setFconditionscore(levelCondition.getFscore());
                    abilitylevel.setFcid(Long.parseLong(uid));
                    abilitylevel.setFcdate(new Date());
                    abilitylevel.setFgpid(tAbilityTree.getFgpid());
                    abilitylevel.setFnodetype(3);
                    abilitylevel.setFdiv(fdiv);
                    abilitylevel.setFalcid(Long.parseLong(FConditionID));
                    tAbilityTreeService.save(abilitylevel);
                    abilitylevel.setFpath(fpath + "|_" + abilitylevel.getFkeyid().toString() + "_|");
                    tAbilityTreeService.update(abilitylevel);

                    abilitylevel = new TAbilityTree();
                    abilitylevel.setFkeyid(pId);
                    abilitylevel.setFisleaf(0);
                    tAbilityTreeService.update(abilitylevel);
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
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }



    /*
     *
     *   能力树
     *
     * */

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
        String FAbilityID = jsonParam.getString("FAbilityID");//能力ID
//        String FAbilityLevelId = jsonParam.getString("FAbilityLevelId");
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

            FAbilityID = FAbilityID == null ? "0" : ParamTools.getdeParam(FAbilityID);

            TAbilityTree genabilityTree = tAbilityTreeService.findById(Long.parseLong(FAbilityID));//能力根节点


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
            filetype_object.put("id", ParamTools.getEnParam(genabilityTree.getFkeyid().toString()));
            filetype_object.put("pId", ParamTools.getEnParam("-1"));
            filetype_object.put("name", genabilityTree.getFname());
            filetype_object.put("showname", genabilityTree.getFname());
            filetype_object.put("islevle", 1);//是否可以添加等级 1-可以 2-不可以
            filetype_object.put("istj", 2);//是否可以添加条件 1-可以 2-不可以
            filetype_object.put("iskh", 2);//是否可以添加考核 1-可以 2-不可以
            filetype_object.put("ftype", -1);// -1根节点 1=等级 2=条件 3=考核
            filetype_object.put("open", true);
            filetype_object.put("iconSkin", "school-icon");
            filetype_Array.add(filetype_object);
            TAbilityAssessmentMethod serviceById = null;
            //查询所有等级

            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
            criteria.andFdelEqualTo(1);
            criteria.andFnodetypeEqualTo(2);
            criteria.andFpidEqualTo(genabilityTree.getFkeyid());
            List<Integer> nodetypelist = new ArrayList<>();
            nodetypelist.add(3);
            nodetypelist.add(4);
            List<TAbilityTree> tAbilityLevelList = tAbilityTreeService.findByExample(tAbilityTreeExample);
            if (tAbilityLevelList.size() > 0) {
                for (TAbilityTree tAbilityLevel : tAbilityLevelList) {
                    FConditionScore = 0;

                    tAbilityTreeExample = new TAbilityTreeExample();
                    criteria = tAbilityTreeExample.createCriteria();
                    criteria.andFdelEqualTo(1);
                    criteria.andFnodetypeEqualTo(3);
                    criteria.andFpidEqualTo(tAbilityLevel.getFkeyid());
                    List<TAbilityTree> tAbilityConditionList = tAbilityTreeService.findByExample(tAbilityTreeExample);
                    for (TAbilityTree condition : tAbilityConditionList) {
                        FConditionScore += condition.getFmethodweight();
                    }

                    filetype_object = new JSONObject();
                    filetype_object.put("id", ParamTools.getEnParam(tAbilityLevel.getFkeyid().toString()));
                    filetype_object.put("pId", ParamTools.getEnParam(genabilityTree.getFkeyid().toString()));
//                    filetype_object.put("name", tAbilityLevel.getFname() == null ? "" : tAbilityLevel.getFname() + "【等级分数：" + tAbilityLevel.getFscoremin() + " ~ " + tAbilityLevel.getFscoremax() + "】" + "【条件总分：" + String.format("%.2f", FConditionScore) + "】");
                    filetype_object.put("name", tAbilityLevel.getFname() == null ? "" : tAbilityLevel.getFname() + "【等级分数：" + tAbilityLevel.getFscoremin() + " ~ " + tAbilityLevel.getFscoremax() + "】" + "【下级条件总权重：" + String.format("%.2f", FConditionScore) + "%】");
                    filetype_object.put("showname", tAbilityLevel.getFname() == null ? "" : tAbilityLevel.getFname() + "【下级条件总权重：" + String.format("%.2f", FConditionScore) + "%】");
                    filetype_object.put("AbilityName", genabilityTree.getFname());
                    filetype_object.put("islevle", 2);//是否可以添加等级 1-可以 2-不可以
                    filetype_object.put("istj", 1);//是否可以添加条件 1-可以 2-不可以
                    filetype_object.put("iskh", 2);//是否可以添加考核 1-可以 2-不可以
                    filetype_object.put("ftype", 1);// -1根节点 1=等级 2=条件 3=考核
                    filetype_object.put("open", false);
                    filetype_object.put("iconSkin", "college-icon");
                    filetype_Array.add(filetype_object);

                    //查询所有条件
                    tAbilityTreeExample = new TAbilityTreeExample();
                    criteria = tAbilityTreeExample.createCriteria();
                    criteria.andFdelEqualTo(1);
                    criteria.andFnodetypeEqualTo(3);
                    criteria.andFpidEqualTo(tAbilityLevel.getFkeyid());
                    tAbilityTreeExample.setOrderByClause("FCDATE asc");
                    List<TAbilityTree> abilityConditionList = tAbilityTreeService.findByExample(tAbilityTreeExample);
                    if (abilityConditionList.size() > 0) {
                        for (TAbilityTree tAbilityCondition : abilityConditionList) {
                            FMethodWeight2 = 0;
                            TAbilityTreeExample tAbilityTreeExample1 = new TAbilityTreeExample();
                            TAbilityTreeExample.Criteria criteria1 = tAbilityTreeExample1.createCriteria();
                            criteria1.andFdelEqualTo(1);
                            criteria1.andFpidEqualTo(tAbilityCondition.getFkeyid());
                            criteria1.andFnodetypeIn(nodetypelist);
                            List<TAbilityTree> abilityConditionAssessmentMethodList = tAbilityTreeService.findByExample(tAbilityTreeExample1);
                            if (abilityConditionAssessmentMethodList.size() > 0) {
                                for (TAbilityTree abilityConditionAssessmentMethod : abilityConditionAssessmentMethodList) {
                                    FMethodWeight2 += abilityConditionAssessmentMethod.getFmethodweight();
                                }
                            }
                            filetype_object = new JSONObject();
                            filetype_object.put("id", ParamTools.getEnParam(tAbilityCondition.getFkeyid().toString()));
                            filetype_object.put("pId", ParamTools.getEnParam(tAbilityLevel.getFkeyid().toString()));
                            filetype_object.put("name", tAbilityCondition.getFname() == null ? "" : tAbilityCondition.getFname() + "【条件达标分数：" + tAbilityCondition.getFconditionscore() + "】" + "【当前条件权重：" + String.format("%.2f", tAbilityCondition.getFmethodweight() == null ? 0 : tAbilityCondition.getFmethodweight()) + "%】" + "【下级条件+考核方式总权重：" + String.format("%.2f", (FMethodWeight2)) + "%】");
                            filetype_object.put("showname", tAbilityCondition.getFname() == null ? "" : tAbilityCondition.getFname() + "【当前条件权重：" + String.format("%.2f", tAbilityCondition.getFmethodweight() == null ? 0 : tAbilityCondition.getFmethodweight()) + "%】" + "【下级条件+考核方式总权重：" + String.format("%.2f", (FMethodWeight2)) + "%】");

                            filetype_object.put("AbilityID", ParamTools.getEnParam(genabilityTree.getFkeyid().toString()));
                            filetype_object.put("LevelName", tAbilityLevel.getFname());
                            filetype_object.put("AbilityName", genabilityTree.getFname());
                            filetype_object.put("ConditionName", tAbilityCondition.getFname() == null ? "" : tAbilityCondition.getFname());
                            filetype_object.put("islevle", 2);//是否可以添加等级 1-可以 2-不可以
                            filetype_object.put("istj", 2);//是否可以添加条件 1-可以 2-不可以
                            filetype_object.put("iskh", 1);//是否可以添加考核 1-可以 2-不可以
                            filetype_object.put("ftype", 2);// -1根节点 1=等级 2=条件 3=考核
                            filetype_object.put("ftypes", 1);// -1根节点 1=等级 2=条件 3=考核
                            filetype_object.put("open", false);
                            filetype_object.put("iconSkin", "major-icon");
                            filetype_Array.add(filetype_object);
                            if (tAbilityCondition.getFisleaf() == 0) {
                                getNodeData(filetype_Array, tAbilityCondition.getFkeyid(), nodetypelist, genabilityTree, tAbilityLevel, tAbilityCondition.getFname());
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

    private void getNodeData(JSONArray filetype_Array, Long FPID, List<Integer> nodetypelist, TAbilityTree genabilityTree, TAbilityTree tAbilityLevel, String FFJName) throws Exception {

        JSONObject filetype_object = null;

        try {
            TAbilityTreeExample tAbilityTreeExample2 = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria3 = tAbilityTreeExample2.createCriteria();
            criteria3.andFdelEqualTo(1);
            criteria3.andFnodetypeIn(nodetypelist);//查询下面子条件与考核方式
            criteria3.andFpidEqualTo(FPID);
            List<TAbilityTree> zfsabilityTrees = tAbilityTreeService.findByExample(tAbilityTreeExample2);
            float FMethodWeight2 = 0;
            //每个条件下有哪考核方式
            if (zfsabilityTrees.size() > 0) {
                for (TAbilityTree tAbilityConditionAssessmentMethod : zfsabilityTrees) {
                    if (tAbilityConditionAssessmentMethod.getFnodetype() == 3) {
                        FMethodWeight2 = 0;
                        TAbilityTreeExample tAbilityTreeExample1 = new TAbilityTreeExample();
                        TAbilityTreeExample.Criteria criteria1 = tAbilityTreeExample1.createCriteria();
                        criteria1.andFdelEqualTo(1);
                        criteria1.andFpidEqualTo(tAbilityConditionAssessmentMethod.getFkeyid());
                        criteria1.andFnodetypeIn(nodetypelist);
                        List<TAbilityTree> abilityConditionAssessmentMethodList = tAbilityTreeService.findByExample(tAbilityTreeExample1);
                        if (abilityConditionAssessmentMethodList.size() > 0) {
                            for (TAbilityTree abilityConditionAssessmentMethod : abilityConditionAssessmentMethodList) {
                                FMethodWeight2 += abilityConditionAssessmentMethod.getFmethodweight();
                            }
                        }
                        filetype_object = new JSONObject();
                        filetype_object.put("id", ParamTools.getEnParam(tAbilityConditionAssessmentMethod.getFkeyid().toString()));
                        filetype_object.put("pId", ParamTools.getEnParam(FPID.toString()));
                        filetype_object.put("name", tAbilityConditionAssessmentMethod.getFname() == null ? "" : tAbilityConditionAssessmentMethod.getFname() + "【条件达标分数：" + tAbilityConditionAssessmentMethod.getFconditionscore() + "】" + "【当前条件权重：" + String.format("%.2f", tAbilityConditionAssessmentMethod.getFmethodweight() == null ? 0 : tAbilityConditionAssessmentMethod.getFmethodweight()) + "%】" + "【下级条件+考核方式总权重：" + String.format("%.2f", (FMethodWeight2)) + "%】");
                        filetype_object.put("showname", tAbilityConditionAssessmentMethod.getFname() == null ? "" : tAbilityConditionAssessmentMethod.getFname() + "【当前条件权重：" + String.format("%.2f", tAbilityConditionAssessmentMethod.getFmethodweight() == null ? 0 : tAbilityConditionAssessmentMethod.getFmethodweight()) + "%】" + "【下级条件+考核方式总权重：" + String.format("%.2f", (FMethodWeight2)) + "%】");
                        filetype_object.put("AbilityID", ParamTools.getEnParam(genabilityTree.getFkeyid().toString()));
                        filetype_object.put("LevelName", tAbilityLevel.getFname());
                        filetype_object.put("AbilityName", genabilityTree.getFname());
                        filetype_object.put("ConditionName", tAbilityConditionAssessmentMethod.getFname() == null ? "" : tAbilityConditionAssessmentMethod.getFname());
                        filetype_object.put("fjdname", FFJName);
                        filetype_object.put("islevle", 2);//是否可以添加等级 1-可以 2-不可以
                        filetype_object.put("istj", 2);//是否可以添加条件 1-可以 2-不可以
                        filetype_object.put("iskh", 1);//是否可以添加考核 1-可以 2-不可以
                        filetype_object.put("ftype", 4);// -1根节点 1=等级 2=条件 3=考核
                        filetype_object.put("ftypes", 1);// -1根节点 1=等级 2=条件 3=考核
                        filetype_object.put("open", false);
                        filetype_object.put("iconSkin", "major-icon");
                        filetype_Array.add(filetype_object);
                        if (tAbilityConditionAssessmentMethod.getFisleaf() == 0)
                            this.getNodeData(filetype_Array, tAbilityConditionAssessmentMethod.getFkeyid(), nodetypelist, genabilityTree, tAbilityLevel, tAbilityConditionAssessmentMethod.getFname());
                    } else if (tAbilityConditionAssessmentMethod.getFnodetype() == 4) {
                        TAbilityAssessmentMethod tAbilityAssessmentMethod = tAbilityAssessmentMethodService.findById(tAbilityConditionAssessmentMethod.getFmethodid());
                        filetype_object = new JSONObject();
                        filetype_object.put("id", ParamTools.getEnParam(tAbilityConditionAssessmentMethod.getFkeyid().toString()));
                        filetype_object.put("pId", ParamTools.getEnParam(FPID.toString()));
                        filetype_object.put("name", tAbilityAssessmentMethod.getFmethodname() + "【权重：" + String.format("%.2f", tAbilityConditionAssessmentMethod.getFmethodweight()) + "%】");
                        filetype_object.put("showname", tAbilityAssessmentMethod.getFmethodname() + "【权重：" + String.format("%.2f", tAbilityConditionAssessmentMethod.getFmethodweight()) + "%】");
                        filetype_object.put("LevelID", ParamTools.getEnParam(tAbilityLevel.getFkeyid().toString()));
                        filetype_object.put("LevelName", tAbilityLevel.getFname());
                        filetype_object.put("AbilityName", genabilityTree.getFname());
                        filetype_object.put("ConditionName", tAbilityConditionAssessmentMethod.getFname() == null ? "" : tAbilityConditionAssessmentMethod.getFname());
                        filetype_object.put("MethodName", tAbilityAssessmentMethod.getFmethodname());
                        filetype_object.put("islevle", 2);//是否可以添加等级 1-可以 2-不可以
                        filetype_object.put("istj", 2);//是否可以添加条件 1-可以 2-不可以
                        filetype_object.put("iskh", 1);//是否可以添加考核 1-可以 2-不可以
                        filetype_object.put("ftype", 3);// -1根节点 1=等级 2=条件 3=考核
                        filetype_object.put("open", false);
                        filetype_object.put("iconSkin", "class-icon");
                        filetype_Array.add(filetype_object);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



    /*
     *测试获取数据
     *
     * */

    /**
     *
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/getAbilitTreeData", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getAbilitTreeData(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String ftypeid = jsonParam.getString("ftypeid");//能力类型ID
        try {
            ftypeid = ftypeid == null ? "0" : ParamTools.getdeParam(ftypeid);
//            System.out.println(ftypeid);
            JSONArray zjsonArray = new JSONArray();
            TAbilityType abilityType = tAbilityTypeService.findById(Long.parseLong(ftypeid));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("filename", "");
            jsonObject.put("user_id", 1826863688028660086l);
            jsonObject.put("node_id", 1857238472583159808l);
            jsonObject.put("file_type", 2);
//            jsonObject.put("create_time",new Date());
            jsonObject.put("state", 2);
            jsonObject.put("title", abilityType == null ? "" : abilityType.getFname());
            jsonObject.put("file_ext", "");
//            jsonObject.put("chunkSize",0);
//            jsonObject.put("length",new JSONArray(Arrays.asList("$numberLong", "450")));
//            jsonObject.put("uploadDate",new JSONArray(Arrays.asList("$date", "2025-04-22T05:23:00.591Z")));

            JSONArray graph_groupsarray = new JSONArray();
            //1-能力 2-等级 3-条件 4-考核方式
            JSONObject graph_groupsObject = new JSONObject();
            graph_groupsObject.put("id", 0);
            graph_groupsObject.put("label", "能力类型");
            graph_groupsarray.add(graph_groupsObject);

            graph_groupsObject = new JSONObject();
            graph_groupsObject.put("id", 1);
            graph_groupsObject.put("label", "能力");
            graph_groupsarray.add(graph_groupsObject);

            graph_groupsObject = new JSONObject();
            graph_groupsObject.put("id", 2);
            graph_groupsObject.put("label", "能力水平");
            graph_groupsarray.add(graph_groupsObject);

            graph_groupsObject = new JSONObject();
            graph_groupsObject.put("id", 3);
            graph_groupsObject.put("label", "考核条件");
            graph_groupsarray.add(graph_groupsObject);

            graph_groupsObject = new JSONObject();
            graph_groupsObject.put("id", 4);
            graph_groupsObject.put("label", "考核方式");
            graph_groupsarray.add(graph_groupsObject);
            jsonObject.put("graph_groups", graph_groupsarray);

            //把类型加到节点中
            TAbilityTypeExample tAbilityTypeExample = new TAbilityTypeExample();
            tAbilityTypeExample.createCriteria().andFstateEqualTo(1).andFkeyidEqualTo(Long.parseLong(ftypeid));
            List<TAbilityType> tAbilityTypeList = tAbilityTypeService.findByExample(tAbilityTypeExample);
            JSONArray graph_nodesarray = new JSONArray();
            JSONArray graph_relationshipsarray = new JSONArray();
            for (TAbilityType tAbilityType : tAbilityTypeList) {
                JSONObject typeObject = new JSONObject();
                typeObject.put("id", ParamTools.getEnParam(tAbilityType.getFkeyid().toString()));
                typeObject.put("label", tAbilityType.getFname());
                typeObject.put("group", 0);
                graph_nodesarray.add(typeObject);

                //根据类型查询出所有节点

                TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
                TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
                criteria.andFtypeidEqualTo(tAbilityType.getFkeyid());
                criteria.andFdelEqualTo(1);
                criteria.andFstateEqualTo(1);
                criteria.andFnodetypeEqualTo(1);//能力
                List<TAbilityTree> tAbilityTrees = tAbilityTreeService.findByExample(tAbilityTreeExample);
                if (tAbilityTrees.size() > 0) {
                    for (TAbilityTree tAbilityTree : tAbilityTrees) {
                        JSONObject treeObject = new JSONObject();
                        treeObject.put("id", ParamTools.getEnParam(tAbilityTree.getFkeyid().toString()));
                        treeObject.put("label", tAbilityTree.getFname());
                        treeObject.put("group", tAbilityTree.getFnodetype());
                        graph_nodesarray.add(treeObject);


                        JSONObject relationshipsObject = new JSONObject();
                        relationshipsObject.put("from", ParamTools.getEnParam(tAbilityType.getFkeyid().toString()));
                        relationshipsObject.put("to", ParamTools.getEnParam(tAbilityTree.getFkeyid().toString()));
                        relationshipsObject.put("label", "拥有");
                        relationshipsObject.put("arrows", "to");

                        graph_relationshipsarray.add(relationshipsObject);
                        if (tAbilityTree.getFisleaf() == 0) {
                            getTreeNodeData(tAbilityTree.getFkeyid(), graph_nodesarray, graph_relationshipsarray);
                        }
                    }
                }
            }
            jsonObject.put("graph_nodes", graph_nodesarray);
            jsonObject.put("graph_relationships", graph_relationshipsarray);
            zjsonArray.add(jsonObject);
//            System.out.println(zjsonArray.toString());
            object.put("zjsonArray", zjsonArray);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    private void getTreeNodeData(Long FPID, JSONArray graph_nodesarray, JSONArray graph_relationshipsarray) {
        try {
            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
            criteria.andFpidEqualTo(FPID);
            criteria.andFdelEqualTo(1);
            criteria.andFstateEqualTo(1);
            List<TAbilityTree> tAbilityTrees = tAbilityTreeService.findByExample(tAbilityTreeExample);

            if (tAbilityTrees.size() > 0) {
                for (TAbilityTree tAbilityTree : tAbilityTrees) {
                    JSONObject treeObject = new JSONObject();
                    treeObject.put("id", ParamTools.getEnParam(tAbilityTree.getFkeyid().toString()));
                    treeObject.put("label", tAbilityTree.getFname());
                    treeObject.put("group", tAbilityTree.getFnodetype());
                    graph_nodesarray.add(treeObject);

                    JSONObject relationshipsObject = new JSONObject();
                    relationshipsObject.put("from", ParamTools.getEnParam(FPID.toString()));
                    relationshipsObject.put("to", ParamTools.getEnParam(tAbilityTree.getFkeyid().toString()));
                    relationshipsObject.put("label", "拥有");
                    relationshipsObject.put("arrows", "to");
                    graph_relationshipsarray.add(relationshipsObject);
                    if (tAbilityTree.getFisleaf() == 0) {
                        getTreeNodeData(tAbilityTree.getFkeyid(), graph_nodesarray, graph_relationshipsarray);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * 根据能力类型，获取其下面所有能力及条件
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/getAbilitTreeDataz", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getAbilitTreeDataz(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String abilityFTypeID = jsonParam.getString("abilityFTypeID");//能力类型ID
        try {
            abilityFTypeID = abilityFTypeID == null ? "0" : ParamTools.getdeParam(abilityFTypeID);
            TAbilityType tAbilityType = tAbilityTypeService.findById(Long.parseLong(abilityFTypeID));
            JSONArray filetype_Array = new JSONArray();
            JSONObject filetype_object = null;
            filetype_object = new JSONObject();
            filetype_object.put("id", ParamTools.getEnParam(abilityFTypeID.toString()));
            filetype_object.put("pId", ParamTools.getEnParam("-1"));
            filetype_object.put("name", tAbilityType.getFname());
            filetype_object.put("open", true);
            filetype_Array.add(filetype_object);


            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
            criteria.andFtypeidEqualTo(Long.parseLong(abilityFTypeID));
            criteria.andFdelEqualTo(1);
            criteria.andFstateEqualTo(1);
            criteria.andFnodetypeEqualTo(1);
            List<TAbilityTree> treeList = tAbilityTreeService.findByExample(tAbilityTreeExample);

            if (treeList.size() > 0) {
                List<Integer> nodetypelist = new ArrayList<>();
                nodetypelist.add(3);
                nodetypelist.add(4);
                for (TAbilityTree tAbilityTree : treeList) {
                    //查询能力
                    filetype_object = new JSONObject();
                    filetype_object.put("id", ParamTools.getEnParam(tAbilityTree.getFkeyid().toString()));
                    filetype_object.put("pId", ParamTools.getEnParam(abilityFTypeID));
                    filetype_object.put("name", tAbilityTree.getFname());
                    filetype_object.put("open", false);
                    filetype_Array.add(filetype_object);

                    TAbilityTreeExample tAbilityTreeExample2 = new TAbilityTreeExample();
                    TAbilityTreeExample.Criteria criteria1 = tAbilityTreeExample2.createCriteria();
                    criteria1.andFdelEqualTo(1);
                    criteria1.andFstateEqualTo(1);
                    criteria1.andFnodetypeEqualTo(2);
                    criteria1.andFpidEqualTo(tAbilityTree.getFkeyid());
                    List<TAbilityTree> treeList2 = tAbilityTreeService.findByExample(tAbilityTreeExample2);
                    for (TAbilityTree tAbilityTree1 : treeList2) {
                        filetype_object = new JSONObject();
                        filetype_object.put("id", ParamTools.getEnParam(tAbilityTree1.getFkeyid().toString()));
                        filetype_object.put("pId", ParamTools.getEnParam(tAbilityTree.getFkeyid().toString()));
                        filetype_object.put("name", tAbilityTree1.getFname());
                        filetype_object.put("open", false);
                        filetype_Array.add(filetype_object);
                        TAbilityTreeExample tAbilityTreeExample3 = new TAbilityTreeExample();
                        TAbilityTreeExample.Criteria criteria2 = tAbilityTreeExample3.createCriteria();
                        criteria2.andFdelEqualTo(1);
                        criteria2.andFstateEqualTo(1);
                        criteria2.andFnodetypeEqualTo(3);
                        criteria2.andFpidEqualTo(tAbilityTree1.getFkeyid());
                        List<TAbilityTree> treeList3 = tAbilityTreeService.findByExample(tAbilityTreeExample3);
                        for (TAbilityTree tAbilityTree2 : treeList3) {
                            filetype_object = new JSONObject();
                            filetype_object.put("id", ParamTools.getEnParam(tAbilityTree2.getFkeyid().toString()));
                            filetype_object.put("pId", ParamTools.getEnParam(tAbilityTree1.getFkeyid().toString()));
                            filetype_object.put("name", tAbilityTree2.getFname());
                            filetype_object.put("open", false);
                            filetype_Array.add(filetype_object);
                            if(tAbilityTree2.getFisleaf() == 0){
                                getAbilitTreeDatazNode(filetype_Array,tAbilityTree2.getFkeyid(),nodetypelist);
                            }
                        }

                    }

                }
            }

            object.put("zNodes", filetype_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    private void getAbilitTreeDatazNode(JSONArray filetype_Array,Long FPID,List<Integer> nodetypelist){
        JSONObject filetype_object = null;
        try{

            TAbilityTreeExample tAbilityTreeExample2 = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria1 = tAbilityTreeExample2.createCriteria();
            criteria1.andFdelEqualTo(1);
            criteria1.andFstateEqualTo(1);
            criteria1.andFnodetypeIn(nodetypelist);
            criteria1.andFpidEqualTo(FPID);
            List<TAbilityTree> treeList2 = tAbilityTreeService.findByExample(tAbilityTreeExample2);
            for (TAbilityTree tAbilityTree: treeList2) {
                filetype_object = new JSONObject();
                filetype_object.put("id", ParamTools.getEnParam(tAbilityTree.getFkeyid().toString()));
                filetype_object.put("pId", ParamTools.getEnParam(FPID.toString()));
                if(tAbilityTree.getFnodetype() == 3)
                    filetype_object.put("name", tAbilityTree.getFname());
                else{
                    TAbilityAssessmentMethod abilityAssessmentMethod = tAbilityAssessmentMethodService.findById(tAbilityTree.getFmethodid());
                    filetype_object.put("name", abilityAssessmentMethod == null ? "" : abilityAssessmentMethod.getFmethodname());
                } 
                filetype_object.put("open", false);
                filetype_Array.add(filetype_object);
                if(tAbilityTree.getFisleaf() == 0){
                    getAbilitTreeDatazNode(filetype_Array,tAbilityTree.getFkeyid(),nodetypelist);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}