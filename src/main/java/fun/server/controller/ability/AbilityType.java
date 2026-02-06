package fun.server.controller.ability;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TAbility;
import fun.server.model.TAbilityExample;
import fun.server.model.TAbilityType;
import fun.server.model.TAbilityTypeExample;
import fun.server.service.TAbilityService;
import fun.server.service.TAbilityTypeService;
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
 * 能力类型管理 相关业务处理
 */
@RestController
@RequestMapping("/s/abilitytype")
public class AbilityType {

    private final TAbilityTypeService tAbilityTypeService;
    private final TAbilityService tAbilityService;

    public AbilityType(TAbilityTypeService tAbilityTypeService, TAbilityService tAbilityService) {
        this.tAbilityTypeService = tAbilityTypeService;
        this.tAbilityService = tAbilityService;
    }


    /**
     * 获取能力类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilitytype", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilitytype(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray abilitytype_Array = new JSONArray();
            // 查询条件
            TAbilityTypeExample TAbilityTypeExample = new TAbilityTypeExample();
            TAbilityTypeExample.Criteria criteria = TAbilityTypeExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");

            }
            TAbilityTypeExample.setOrderByClause("FCDATE desc");
            PageInfo<TAbilityType> abilitytypePageInfo = tAbilityTypeService.findByExampleMapper(TAbilityTypeExample, (page - 1) * results, results);
            List<TAbilityType> abilitytype_list = abilitytypePageInfo.getList();

            for (TAbilityType abilitytype : abilitytype_list) {
                JSONObject abilitytype_object = new JSONObject();
                abilitytype_object.put("key", ParamTools.getEnParam(abilitytype.getFkeyid().toString()));
                if (dataall == 1) {
                    abilitytype_object.put("FName", abilitytype.getFname());
                    abilitytype_object.put("FCID", abilitytype.getFcid());
                    abilitytype_object.put("FUID", abilitytype.getFuid());
                    abilitytype_object.put("FCDATE", abilitytype.getFcdate());
                    abilitytype_object.put("FUDATE", abilitytype.getFudate());
                } else {
                    abilitytype_object.put("FName", "*****");
                    abilitytype_object.put("FCID", "*****");
                    abilitytype_object.put("FUID", "*****");
                    abilitytype_object.put("FCDATE", "*****");
                    abilitytype_object.put("FUDATE", "*****");
                }

                abilitytype_object.put("FState", abilitytype.getFstate());
                abilitytype_Array.add(abilitytype_object);
            }
            // 返回值
            object.put("list", abilitytype_Array);
            object.put("total", abilitytypePageInfo.getTotal()); // 总行数
            object.put("page", abilitytypePageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取能力类型信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDataabilitytypeSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDataabilitytypeSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray abilitytype_Array = new JSONArray();
            TAbilityTypeExample abilitytypeExample = new TAbilityTypeExample();
            TAbilityTypeExample.Criteria criteria = abilitytypeExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            abilitytypeExample.setOrderByClause("fname asc");
            List<TAbilityType> abilitytype_list = tAbilityTypeService.findByExample(abilitytypeExample);
            for (TAbilityType abilitytype : abilitytype_list) {
                JSONObject abilitytype_object = new JSONObject();
                abilitytype_object.put("id", ParamTools.getEnParam(abilitytype.getFkeyid().toString()));
                abilitytype_object.put("text", abilitytype.getFname());
                abilitytype_Array.add(abilitytype_object);
            }
            // 返回值

            object.put("list", abilitytype_Array);
            object.put("results", abilitytype_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取能力类型信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDataabilitytypeSelectall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDataabilitytypeSelectall(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray abilitytype_Array = new JSONArray();
            TAbilityTypeExample abilitytypeExample = new TAbilityTypeExample();
            TAbilityTypeExample.Criteria criteria = abilitytypeExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            abilitytypeExample.setOrderByClause("fname asc");
            JSONObject abilitytype_object = new JSONObject();
            abilitytype_object.put("id", "-1");
            abilitytype_object.put("text","全部");
            abilitytype_Array.add(abilitytype_object);
            List<TAbilityType> abilitytype_list = tAbilityTypeService.findByExample(abilitytypeExample);
            for (TAbilityType abilitytype : abilitytype_list) {
                abilitytype_object = new JSONObject();
                abilitytype_object.put("id", ParamTools.getEnParam(abilitytype.getFkeyid().toString()));
                abilitytype_object.put("text", abilitytype.getFname());
                abilitytype_Array.add(abilitytype_object);
            }
            // 返回值

            object.put("list", abilitytype_Array);
            object.put("results", abilitytype_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取能力类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilitytypeInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilitytypeInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询能力类型信息
            TAbilityType abilitytype = tAbilityTypeService.findById(key);
            JSONObject abilitytype_object = new JSONObject();
            abilitytype_object.put("key", ParamTools.getEnParam(abilitytype.getFkeyid().toString()));
            abilitytype_object.put("FName", abilitytype.getFname());
            abilitytype_object.put("FCID", abilitytype.getFcid());
            abilitytype_object.put("FUID", abilitytype.getFuid());
            abilitytype_object.put("FCDATE", abilitytype.getFcdate());
            abilitytype_object.put("FUDATE", abilitytype.getFudate());
            abilitytype_object.put("FState", abilitytype.getFstate());
            // 返回值
            object.put("info", abilitytype_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加能力类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加能力类型信息")
    @ResponseBody
    @RequestMapping(value = "/addabilitytype", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addabilitytype(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String Fname = jsonParam.getString("Fname");

        try {
            if (repeaTAbilityType(0L, Fname, "1") == 0) {
                String CookiesLoginabilitytypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilitytypeID != null && !CookiesLoginabilitytypeID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilitytypeID);
                }
                // 新建能力类型信息
                TAbilityType abilitytype = new TAbilityType();
                abilitytype.setFname(Fname);
                abilitytype.setFcid(Long.parseLong(uid));
                abilitytype.setFcdate(new Date());
                tAbilityTypeService.save(abilitytype);
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
     * 修改能力类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改能力类型信息")
    @ResponseBody
    @RequestMapping(value = "/updateabilitytype", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateabilitytype(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String Fname = jsonParam.getString("Fname");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            if (repeaTAbilityType(key, Fname, "2") == 0) {
                String CookiesLoginabilitytypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilitytypeID != null && !CookiesLoginabilitytypeID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilitytypeID);
                }
                // 更新能力类型信息
                TAbilityType abilitytype = new TAbilityType();
                abilitytype.setFkeyid(key);
                abilitytype.setFname(Fname);
                abilitytype.setFuid(Long.parseLong(uid));
                abilitytype.setFudate(new Date());
                tAbilityTypeService.update(abilitytype);
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
     * 删除能力类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除能力类型信息")
    @ResponseBody
    @RequestMapping(value = "/delabilitytype", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delabilitytype(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TAbilityExample tAbilityExample = new TAbilityExample();
            tAbilityExample.createCriteria().andFtypeidEqualTo(Long.parseLong(id));
            List<TAbility> abilityList = tAbilityService.findByExample(tAbilityExample);
            if (abilityList.size() == 0) {
                tAbilityTypeService.deleteById(Long.parseLong(id));
                // 返回值
                object.put("result", "success");
            }else{
                object.put("result", "该能力类型已被使用，不能删除(不想使用可禁用)！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 变更能力类型信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/stateabilitytype", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stateabilitytype(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLoginabilitytypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginabilitytypeID != null && !CookiesLoginabilitytypeID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TAbilityType abilitytype = new TAbilityType();
            abilitytype.setFkeyid(Long.parseLong(id));
            abilitytype.setFuid(Long.parseLong(uid));
            abilitytype.setFudate(new Date());
            abilitytype.setFstate(Integer.valueOf(state));
            tAbilityTypeService.update(abilitytype);
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
     * 验证能力类型是否存在
     */
    private int repeaTAbilityType(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TAbilityTypeExample abilitytypeExample = new TAbilityTypeExample();
            TAbilityTypeExample.Criteria criteria = abilitytypeExample.createCriteria();
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
            List<TAbilityType> abilitytypeList = tAbilityTypeService.findByExample(abilitytypeExample);
            if (abilitytypeList.size() == 0) {
                code = 0;
            } else {
                code = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }


    //根据ID查询能力类型名称
    public String getName(Long id) {
        TAbilityType byId = tAbilityTypeService.findById(id);
        if (byId != null) {
            return byId.getFname();
        } else {
            return "";
        }

    }


    /**
     * 获取类型信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilityTypeInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryParamTypeInfo( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 获取数据库记录
            TAbilityType paramType = tAbilityTypeService.findById(key);
            JSONObject paramType_object = new JSONObject();
            paramType_object.put("key", ParamTools.getEnParam(paramType.getFkeyid().toString()));
            paramType_object.put("FName", paramType.getFname());
            paramType_object.put("FPID", ParamTools.getEnParam(paramType.getFpid().toString()));
            if(paramType.getFpid() == 1 ){
                paramType_object.put("FPName", "根节点");
            }else{
                paramType = tAbilityTypeService.findById(paramType.getFpid());
                paramType_object.put("FPName", paramType.getFname());
            }
            // 返回值
            object.put("info", paramType_object);
            object.put("result", "success");
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
    @RequestMapping(value = "/queryabilityType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilityType( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        JSONArray objectList = new JSONArray();
        try {
            if (id == null) {
                JSONObject object = new JSONObject();
                object.put("id", 1);
                object.put("pId", 0);
                TAbilityTypeExample tAbilityTypeExample = new TAbilityTypeExample();
                TAbilityTypeExample.Criteria criteria = tAbilityTypeExample.createCriteria();
                criteria.andFpidEqualTo(1L);
                List<TAbilityType> list = tAbilityTypeService.findByExample(tAbilityTypeExample);
                if (list.size() > 0) {
                    object.put("isParent", 1);
                } else {
                    object.put("isParent", 0);
                }
                object.put("name", "根节点");
                objectList.add(object);
            } else {
                String pid = id.equals("1") ? "1" : ParamTools.getdeParam(id);
                TAbilityTypeExample tAbilityTypeExample = new TAbilityTypeExample();
                TAbilityTypeExample.Criteria criteria = tAbilityTypeExample.createCriteria();
                criteria.andFpidEqualTo(Long.parseLong(pid));
                List<TAbilityType> list = tAbilityTypeService.findByExample(tAbilityTypeExample);
                for (TAbilityType tAbilityType : list) {
                    JSONObject object = new JSONObject();
                    object.put("id", ParamTools.getEnParam(tAbilityType.getFkeyid().toString()));
                    object.put("pId", pid);
                    object.put("name", tAbilityType.getFname());
                    if (tAbilityType.getFisleaf() == 1) {
                        object.put("isParent", 0);
                    } else {
                        object.put("isParent", 1);
                    }
                    objectList.add(object);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectList.toJSONString();
    }

    /**
     * 添加类型信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("添加能力类型信息")
    @ResponseBody
    @RequestMapping(value = "/addabilityType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addabilityType( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String f_p_id = jsonParam.getString("FPID");
        String f_name = jsonParam.getString("FName");
        String FNote = jsonParam.getString("FNote");
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            Long pId = f_p_id.equals("1") ? 1L : Long.parseLong(ParamTools.getdeParam(f_p_id));

            TAbilityTypeExample tAbilityTypeExample = new TAbilityTypeExample();
            TAbilityTypeExample.Criteria criteria = tAbilityTypeExample.createCriteria();
            criteria.andFpidEqualTo(pId);
            criteria.andFnameEqualTo(f_name);
            List<TAbilityType> abilityTypeList = tAbilityTypeService.findByExample(tAbilityTypeExample);
            if(abilityTypeList.size() == 0){
                // 获取父节点路径
                String path = "";
                TAbilityType tAbilityType = tAbilityTypeService.findById(pId);
                if (tAbilityType == null) {
                    path = "|_1_|";
                } else {
                    path = tAbilityType.getFpath();
                }
                // 添加数据
                String id = "";
                TAbilityType abilityType = new TAbilityType();
                abilityType.setFcid(Long.parseLong(uid));
                abilityType.setFpid(pId);
                abilityType.setFname(f_name);
                abilityType.setFisleaf(1);
                abilityType.setFnote(FNote);
                tAbilityTypeService.save(abilityType);
                id = ParamTools.getEnParam(abilityType.getFkeyid().toString());
                abilityType.setFpath(path + "|_" + abilityType.getFkeyid().toString() + "_|");
                tAbilityTypeService.update(abilityType);
                // 刷新父节点
                abilityType = new TAbilityType();
                abilityType.setFkeyid(pId);
                abilityType.setFisleaf(0);
                tAbilityTypeService.update(abilityType);

                // 返回值
                object.put("id", id);
                object.put("result", "success");
            }else{
                object.put("result", "fial");
            }

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
    @LogOperation("修改能力类型信息")
    @ResponseBody
    @RequestMapping(value = "/updateabilityType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateabilityType( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String f_name = jsonParam.getString("FName");
        String FNote = jsonParam.getString("FNote");
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            id = id == null ? "0" : ParamTools.getdeParam(id);
            TAbilityTypeExample tAbilityTypeExample = new TAbilityTypeExample();
            TAbilityTypeExample.Criteria criteria = tAbilityTypeExample.createCriteria();
            criteria.andFkeyidNotEqualTo(Long.parseLong(id));
            criteria.andFnameEqualTo(f_name);
            List<TAbilityType> abilityTypeList = tAbilityTypeService.findByExample(tAbilityTypeExample);
            if(abilityTypeList.size() == 0){
                // 更新主记录
                TAbilityType abilityType1 = new TAbilityType();
                abilityType1.setFkeyid(Long.parseLong(id));
                abilityType1.setFuid(Long.parseLong(uid));
                abilityType1.setFudate(new Date());
                abilityType1.setFname(f_name);
                abilityType1.setFnote(FNote);
                tAbilityTypeService.update(abilityType1);
                // 返回值
                object.put("result", "success");
            }else{
                object.put("result", "fial");
            }

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
    @LogOperation("删除能力类型信息")
    @ResponseBody
    @RequestMapping(value = "/delabilityType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delabilityType( HttpServletRequest request )
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
            TAbilityType abilityType = tAbilityTypeService.findById(key);
            // 判断是否可删除
            if (abilityType.getFisleaf() == 0) {
                object.put("result", "error");
                object.put("error", "只能删除最下层节点");
            } else {
                TAbilityExample tAbilityExample = new TAbilityExample();
                TAbilityExample.Criteria criteria = tAbilityExample.createCriteria();
                criteria.andFtypeidEqualTo(key);
                if (tAbilityService.findByExample(tAbilityExample).size() > 0) {
                    object.put("result", "error");
                    object.put("error", "此节点在使用中无法删除");
                } else {
                    // 删除记录
                    tAbilityTypeService.deleteById(key);
                    // 判断是否需要修改父节点状态
                    TAbilityTypeExample paramTypeExample = new TAbilityTypeExample();
                    paramTypeExample.or().andFpidEqualTo(abilityType.getFpid());
                    if (tAbilityTypeService.findByExample(paramTypeExample).size() == 0) {
                        TAbilityType SCTObject = new TAbilityType();
                        SCTObject.setFkeyid(abilityType.getFpid());
                        SCTObject.setFisleaf(1);
                        tAbilityTypeService.update(SCTObject);
                        object.put("PIsLeaf", 1);
                    } else {
                        object.put("PIsLeaf", 0);
                    }
                    // 返回值
                    object.put("result", "success");
                }
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
    @RequestMapping(value = "/queryabilityTypeALL", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilityTypeALL( HttpServletRequest request )
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
        TAbilityTypeExample tAbilityTypeExample = new TAbilityTypeExample();
        tAbilityTypeExample.or().andFpidEqualTo(pid);
        tAbilityTypeExample.setOrderByClause("FCDATE asc");
        List<TAbilityType> tAbilityTypeList = tAbilityTypeService.findByExample(tAbilityTypeExample);

        for (TAbilityType abilityType : tAbilityTypeList) {
            JSONObject object = new JSONObject();
            object.put("id", ParamTools.getEnParam(abilityType.getFkeyid().toString()));
            object.put("pId", pid == 1 ? 1 : ParamTools.getEnParam(pid.toString()));
            object.put("name", abilityType.getFname());
            JSONArray next_list = recursionTypes(abilityType.getFkeyid());
            if (next_list.size() > 0) {
                object.put("children", next_list);
            }
            objectList.add(object);
        }
        return objectList;
    }
}