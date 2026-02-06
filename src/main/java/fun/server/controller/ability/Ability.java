package fun.server.controller.ability;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.model.customQuery.ability.abilityCS;
import fun.server.model.customQuery.ability.abilityData;
import fun.server.service.TAbilityLevelService;
import fun.server.service.TAbilityService;
import fun.server.service.TAbilityTypeService;
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
 * 能力管理 相关业务处理
 */
@RestController
@RequestMapping("/s/ability")
public class Ability {

    private final TAbilityService tAbilityService;
    private final TAbilityTypeService tAbilityTypeService;
    private final TAbilityLevelService tAbilityLevelService;

    public Ability(TAbilityService tAbilityService, TAbilityTypeService tAbilityTypeService, TAbilityLevelService tAbilityLevelService) {
        this.tAbilityService = tAbilityService;
        this.tAbilityTypeService = tAbilityTypeService;
        this.tAbilityLevelService = tAbilityLevelService;
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
            // 查询条件
            TAbilityExample TAbilityExample = new TAbilityExample();
            TAbilityExample.Criteria criteria = TAbilityExample.createCriteria();

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
                TAbilityExample.setOrderByClause(orderSql.substring(1));
            } else {
                TAbilityExample.setOrderByClause("FTypeID asc , FName asc");
            }
            PageInfo<TAbility> abilityPageInfo = tAbilityService.findByExampleMapper(TAbilityExample, (page - 1) * results, results);
            List<TAbility> ability_list = abilityPageInfo.getList();

            for (TAbility ability : ability_list) {
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

//    /**
//     * 获取能力信息(下拉列表)
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/queryDataabilitySelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String queryDataabilitySelect(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        String search = request.getParameter("search");
//        try {
//            // 获取数据库记录
//            JSONArray ability_Array = new JSONArray();
//            TAbilityExample abilityExample = new TAbilityExample();
//            TAbilityExample.Criteria criteria = abilityExample.createCriteria();
//            if (search != null && !search.equals("")) {
//                criteria.andFnameLike("%" + search + "%");
//            }
//            criteria.andFstateEqualTo(1);
//            abilityExample.setOrderByClause("fname asc");
//            List<TAbility> ability_list = tAbilityService.findByExample(abilityExample);
//            for (TAbility ability : ability_list) {
//                JSONObject ability_object = new JSONObject();
//                ability_object.put("id", ParamTools.getEnParam(ability.getFkeyid().toString()));
//                ability_object.put("text", ability.getFname());
//                ability_Array.add(ability_object);
//            }
//            // 返回值
//
//            object.put("list", ability_Array);
//            object.put("result", "success");
//            object.put("results", ability_Array);
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }

    /**
     * 获取能力信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDataabilitySelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDataabilitySelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray ability_Array = new JSONArray();
            TAbilityExample abilityExample = new TAbilityExample();
            TAbilityExample.Criteria criteria = abilityExample.createCriteria();

            abilityCS abilityCS = new abilityCS();

            if (search != null && !search.equals("")) {
                abilityCS.setName(search);
            }

            abilityCS.setOrderBy("a.FName asc");
            List<abilityData> ability_list = tAbilityService.getabilityselectList(abilityCS);
            for (abilityData ability : ability_list) {
                JSONObject ability_object = new JSONObject();
                ability_object.put("id", ParamTools.getEnParam(ability.getFKeyID().toString()));
                ability_object.put("text", ability.getFAbilityTypeName() +"——"+ability.getFAbilityName());
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
            TAbility ability = tAbilityService.findById(key);
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
            TAbilityExample abilityExample = new TAbilityExample();
            TAbilityExample.Criteria criteria = abilityExample.createCriteria();
            criteria.andFnameEqualTo(FName);
            criteria.andFtypeidEqualTo(Long.parseLong(FType));
            List<TAbility> abilityList = tAbilityService.findByExample(abilityExample);
            if (abilityList.size() == 0) {
                String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilityID);
                }
                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
                long key = idWorker.nextId();
                // 新建能力信息
                TAbility ability = new TAbility();
                ability.setFkeyid(key);
                ability.setFname(FName);
                ability.setFnote(FNote);
                ability.setFtypeid(Long.parseLong(FType));
                ability.setFcid(Long.parseLong(uid));
                ability.setFcdate(new Date());
                tAbilityService.save(ability);

//                if (fdj > 0) {
//                    TAbilityLevel taBilitylevel = null;
//                    for (int i = 1; i < (fdj + 1); i++) {
//                        taBilitylevel = new TAbilityLevel();
//                        taBilitylevel.setFabilityid(key);
//                        taBilitylevel.setFname(i + "级");
//                        taBilitylevel.setFpoints(i);
//                        tAbilityLevelService.save(taBilitylevel);
//                    }
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
            TAbilityExample abilityExample = new TAbilityExample();
            TAbilityExample.Criteria criteria = abilityExample.createCriteria();
            criteria.andFnameEqualTo(FName);
            criteria.andFtypeidEqualTo(Long.parseLong(FType));
            criteria.andFkeyidNotEqualTo(key);
            List<TAbility> abilityList = tAbilityService.findByExample(abilityExample);
            if (abilityList.size() == 0) {
                String CookiesLoginabilityID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilityID != null && !CookiesLoginabilityID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilityID);
                }
                // 更新能力信息
                TAbility ability = new TAbility();
                ability.setFkeyid(key);
                ability.setFname(FName);
                ability.setFnote(FNote);
                ability.setFtypeid(Long.parseLong(FType));
                ability.setFuid(Long.parseLong(uid));
                ability.setFudate(new Date());
                tAbilityService.update(ability);
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


            tAbilityService.deleteById(Long.parseLong(id));
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
            TAbility ability = new TAbility();
            ability.setFkeyid(Long.parseLong(id));
            ability.setFuid(Long.parseLong(uid));
            ability.setFudate(new Date());
            ability.setFstate(Integer.valueOf(state));
            tAbilityService.update(ability);
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
     * 验证能力是否存在
     */
    private int repeaTAbility(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TAbilityExample abilityExample = new TAbilityExample();
            TAbilityExample.Criteria criteria = abilityExample.createCriteria();
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
            List<TAbility> abilityList = tAbilityService.findByExample(abilityExample);
            if (abilityList.size() == 0) {
                code = 0;
            } else {
                code = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }


    //根据ID查询能力名称
    public String getName(Long id) {
        TAbility byId = tAbilityService.findById(id);
        if (byId != null) {
            return byId.getFname();
        } else {
            return "";
        }

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
                TAbilityExample tAbilityExample = null;
                int iii = 0;
                for (TAbilityType abilityType : abilityTypeList) {
                    JSONObject type_object = new JSONObject();
                    type_object.put("key", ParamTools.getEnParam(abilityType.getFkeyid().toString()));
                    type_object.put("FName", abilityType.getFname());
                    type_object.put("FNote", abilityType.getFnote());
                    type_object.put("i", iii);
                    tAbilityExample = new TAbilityExample();
                    TAbilityExample.Criteria criteria1 = tAbilityExample.createCriteria();
                    criteria1.andFtypeidEqualTo(abilityType.getFkeyid());
                    criteria1.andFstateEqualTo(1);

                    List<TAbility> abilityList = tAbilityService.findByExample(tAbilityExample);
                    if (abilityList.size() > 0) {
                        for (TAbility ability : abilityList) {
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
     * 根据ID获取能力信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilityInfohua", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilityInfohua(HttpServletRequest request)
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
            if(abilityLevelList.size() > 0) {
                for (TAbilityLevel abilityLevel : abilityLevelList) {
                    JSONObject abilitylevel_object = new JSONObject();
                    abilitylevel_object.put("key", ParamTools.getEnParam(abilityLevel.getFkeyid().toString()));
                    abilitylevel_object.put("FName", abilityLevel.getFname() == null ? "" : abilityLevel.getFname());
                    abilitylevel_object.put("FPoints", abilityLevel.getFpoints() == null ? "" : abilityLevel.getFpoints());
                    abilitylevel_object.put("FNote", abilityLevel.getFnote() == null ? "" : abilityLevel.getFnote());
                    abilitylevel_Array.add(abilitylevel_object);
                }
            }
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




}