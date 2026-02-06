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
 * 能力等级管理 相关业务处理
 */
@RestController
@RequestMapping("/s/abilitylevel")
public class AbilityLevel {

    private final TAbilityLevelService tAbilityLevelService;

    private final TStudentAbilityService tStudentAbilityService;
    private final TAbilityTypeService tAbilityTypeService;
    private final TUserService tUserService;

    private final TAbilityConditionService tAbilityConditionService;

    public AbilityLevel(TAbilityLevelService tAbilityLevelService, TStudentAbilityService tStudentAbilityService, TAbilityTypeService tAbilityTypeService, TUserService tUserService, TAbilityConditionService tAbilityConditionService) {
        this.tAbilityLevelService = tAbilityLevelService;
        this.tStudentAbilityService = tStudentAbilityService;
        this.tAbilityTypeService = tAbilityTypeService;
        this.tUserService = tUserService;
        this.tAbilityConditionService = tAbilityConditionService;
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
            // 查询条件
            TAbilityLevelExample TAbilityLevelExample = new TAbilityLevelExample();
            TAbilityLevelExample.Criteria criteria = TAbilityLevelExample.createCriteria();

            id = id == null ? "0" : id.equals("0") ? "" : ParamTools.getdeParam(id);
            criteria.andFabilityidEqualTo(Long.parseLong(id));
            TAbilityLevelExample.setOrderByClause("FPoints asc");
            PageInfo<TAbilityLevel> abilitylevelPageInfo = tAbilityLevelService.findByExampleMapper(TAbilityLevelExample, (page - 1) * results, results);
            List<TAbilityLevel> abilitylevel_list = abilitylevelPageInfo.getList();
            TAbilityConditionExample tAbilityConditionExample = null;
            float FConditionScore = 0;
            for (TAbilityLevel abilitylevel : abilitylevel_list) {
                FConditionScore = 0;
                JSONObject abilitylevel_object = new JSONObject();
                abilitylevel_object.put("key", ParamTools.getEnParam(abilitylevel.getFkeyid().toString()));
                if (dataall == 1) {
                    abilitylevel_object.put("FName", abilitylevel.getFname()== null ? "" : abilitylevel.getFname());
                    abilitylevel_object.put("FPoints", abilitylevel.getFpoints());
                    abilitylevel_object.put("FScoreMin", abilitylevel.getFscoremin());
                    abilitylevel_object.put("FScoreMax", abilitylevel.getFscoremax());
                    abilitylevel_object.put("FNote",  abilitylevel.getFnote()== null ? "" : abilitylevel.getFnote());
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
                    abilitylevel_object.put("FNote",  "*****");
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
     * 获取能力等级信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDataabilitylevelSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDataabilitylevelSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");


        try {
            // 获取数据库记录
            JSONArray abilitylevel_Array = new JSONArray();
            TAbilityLevelExample abilitylevelExample = new TAbilityLevelExample();
            TAbilityLevelExample.Criteria criteria = abilitylevelExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            abilitylevelExample.setOrderByClause("fname asc");
            List<TAbilityLevel> abilitylevel_list = tAbilityLevelService.findByExample(abilitylevelExample);
            for (TAbilityLevel abilitylevel : abilitylevel_list) {
                JSONObject abilitylevel_object = new JSONObject();
                abilitylevel_object.put("id", ParamTools.getEnParam(abilitylevel.getFkeyid().toString()));
                abilitylevel_object.put("text", abilitylevel.getFname());
                abilitylevel_Array.add(abilitylevel_object);
            }
            // 返回值

            object.put("list", abilitylevel_Array);
            object.put("result", "success");
            object.put("results", abilitylevel_Array);
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
    public String queryDataabilitylevelSelectld( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String abilityID = jsonParam.getString("abilityID");
        try {
            abilityID = abilityID == null || abilityID.equals("") ? "0" : ParamTools.getdeParam(abilityID);
            // 获取数据库记录
            JSONArray abilitylevel_Array = new JSONArray();
            TAbilityLevelExample abilitylevelExample = new TAbilityLevelExample();
            TAbilityLevelExample.Criteria criteria = abilitylevelExample.createCriteria();
            criteria.andFabilityidEqualTo(Long.parseLong(abilityID));
            criteria.andFstateEqualTo(1);
            abilitylevelExample.setOrderByClause("FPoints asc");
            List<TAbilityLevel> abilitylevel_list = tAbilityLevelService.findByExample(abilitylevelExample);
            for (TAbilityLevel abilitylevel : abilitylevel_list) {
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
            TAbilityLevel abilitylevel = tAbilityLevelService.findById(key);
            JSONObject abilitylevel_object = new JSONObject();
            abilitylevel_object.put("key", ParamTools.getEnParam(abilitylevel.getFkeyid().toString()));
            abilitylevel_object.put("FName", abilitylevel.getFname());
            abilitylevel_object.put("FPoints", abilitylevel.getFpoints());
            abilitylevel_object.put("FScoreMin", abilitylevel.getFscoremin());
            abilitylevel_object.put("FScoreMax", abilitylevel.getFscoremax());
            abilitylevel_object.put("FNote",  abilitylevel.getFnote()== null ? "" : abilitylevel.getFnote());
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
        int FPoints = jsonParam.getInteger("FPoints");
        String FAbilityID = jsonParam.getString("FAbilityID");
        int FScoreMin = jsonParam.getInteger("FScoreMin");
        int FScoreMax = jsonParam.getInteger("FScoreMax");

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
                // 新建能力等级信息
                TAbilityLevel abilitylevel = new TAbilityLevel();
                abilitylevel.setFname(FName);
                abilitylevel.setFabilityid(Long.parseLong(FAbilityID));
                abilitylevel.setFnote(FNote);
                abilitylevel.setFpoints(FPoints);
                abilitylevel.setFscoremax(FScoreMax);
                abilitylevel.setFscoremin(FScoreMin);
                abilitylevel.setFcid(Long.parseLong(uid));
                abilitylevel.setFcdate(new Date());
                tAbilityLevelService.save(abilitylevel);
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
        int FPoints = jsonParam.getInteger("FPoints");
        int FScoreMin = jsonParam.getInteger("FScoreMin");
        int FScoreMax = jsonParam.getInteger("FScoreMax");
        String FAbilityID = jsonParam.getString("FAbilityID");
        try {
            FAbilityID = FAbilityID == null ? "0" : ParamTools.getdeParam(FAbilityID);
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            TAbilityLevelExample abilitylevelExample = new TAbilityLevelExample();
            TAbilityLevelExample.Criteria criteria = abilitylevelExample.createCriteria();
            criteria.andFnameEqualTo(FName);
            criteria.andFabilityidEqualTo(Long.parseLong(FAbilityID));
            criteria.andFkeyidNotEqualTo(key);
            List<TAbilityLevel> levelList = tAbilityLevelService.findByExample(abilitylevelExample);
            if (levelList.size() == 0) {
                String CookiesLoginabilitylevelID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilitylevelID != null && !CookiesLoginabilitylevelID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilitylevelID);
                }
                // 更新能力等级信息
                TAbilityLevel abilitylevel = new TAbilityLevel();
                abilitylevel.setFkeyid(key);
                abilitylevel.setFname(FName);
                abilitylevel.setFnote(FNote);
                abilitylevel.setFpoints(FPoints);
                abilitylevel.setFscoremax(FScoreMax);
                abilitylevel.setFscoremin(FScoreMin);
                abilitylevel.setFuid(Long.parseLong(uid));
                abilitylevel.setFudate(new Date());
                tAbilityLevelService.update(abilitylevel);
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
            TStudentAbilityExample abilitylevelExample = new TStudentAbilityExample();
            abilitylevelExample.createCriteria().andFabilitylevelidEqualTo(Long.parseLong(id));
            List<TStudentAbility> abilityList = tStudentAbilityService.findByExample(abilitylevelExample);
            if (abilityList.size() == 0) {
                tAbilityLevelService.deleteById(Long.parseLong(id));
                // 返回值
                object.put("result", "success");
            }else{
                object.put("result", "能力等级已被使用，不能删除(不想使用可禁用)!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 变更能力等级信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/stateabilitylevel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stateabilitylevel(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLoginabilitylevelID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginabilitylevelID != null && !CookiesLoginabilitylevelID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TAbilityLevel abilitylevel = new TAbilityLevel();
            abilitylevel.setFkeyid(Long.parseLong(id));
            abilitylevel.setFuid(Long.parseLong(uid));
            abilitylevel.setFudate(new Date());
            abilitylevel.setFstate(Integer.valueOf(state));
            tAbilityLevelService.update(abilitylevel);
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
     * 验证能力等级是否存在
     */
    private int repeaTAbilityLevel(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TAbilityLevelExample abilitylevelExample = new TAbilityLevelExample();
            TAbilityLevelExample.Criteria criteria = abilitylevelExample.createCriteria();
            if (ftype.equals("2")) { // 修改
                if (id != null) {
//                    criteria.andFkeyidEqualTo((id));
                    criteria.andFkeyidNotEqualTo(id);
                }
                if (name != null && !name.equals("")) {
                    criteria.andFnameEqualTo(name);
                }
            } else { // 新增
                if (name != null && !name.equals("")) {
                    criteria.andFnameEqualTo(name);
                }
            }
            List<TAbilityLevel> abilitylevelList = tAbilityLevelService.findByExample(abilitylevelExample);
            if (abilitylevelList.size() == 0) {
                code = 0;
            } else {
                code = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }


    //根据ID查询能力等级名称
    public String getName(Long id) {
        TAbilityLevel byId = tAbilityLevelService.findById(id);
        if (byId != null) {
            return byId.getFname();
        } else {
            return "";
        }

    }
}