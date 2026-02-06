package fun.server.controller.interfaceD;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TInterface;
import fun.server.model.TInterfaceExample;
import fun.server.model.TInterfaceType;
import fun.server.model.TInterfaceTypeExample;
import fun.server.service.TInterfaceService;
import fun.server.service.TInterfaceTypeService;
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
 * j接口类型管理 相关业务处理
 */
@RestController
@RequestMapping("/s/interfacetype")
public class InterfaceType {

    private final TInterfaceTypeService tInterfaceTypeService;
    private final TInterfaceService tInterfaceService;

    public InterfaceType(TInterfaceTypeService tInterfaceTypeService, TInterfaceService tInterfaceService) {
        this.tInterfaceTypeService = tInterfaceTypeService;
        this.tInterfaceService = tInterfaceService;
    }


    /**
     * 获取j接口类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryinterfacetypeList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryinterfacetypeList(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
    	
    	//System.out.println("----------------start------------");
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        //System.out.println(jsonParam);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray interfacetype_Array = new JSONArray();
            // 查询条件
            TInterfaceTypeExample TInterfaceTypeExample = new TInterfaceTypeExample();
            TInterfaceTypeExample.Criteria criteria = TInterfaceTypeExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");

            }
            TInterfaceTypeExample.setOrderByClause("FCDATE desc");
            PageInfo<TInterfaceType> InterfacetypePageInfo = tInterfaceTypeService.findByExampleMapper(TInterfaceTypeExample, (page - 1) * results, results);
            List<TInterfaceType> Interfacetype_list = InterfacetypePageInfo.getList();

            for (TInterfaceType abilitytype : Interfacetype_list) {
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
                interfacetype_Array.add(abilitytype_object);
            }
            // 返回值
            object.put("list", interfacetype_Array);
            object.put("total", InterfacetypePageInfo.getTotal()); // 总行数
            object.put("page", InterfacetypePageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取j接口类型信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatainterfacetypeSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatainterfacetypeSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray abilitytype_Array = new JSONArray();
            TInterfaceTypeExample abilitytypeExample = new TInterfaceTypeExample();
            TInterfaceTypeExample.Criteria criteria = abilitytypeExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            abilitytypeExample.setOrderByClause("fname asc");
            List<TInterfaceType> abilitytype_list = tInterfaceTypeService.findByExample(abilitytypeExample);
            for (TInterfaceType abilitytype : abilitytype_list) {
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
     * 获取j接口类型信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDataInterfacetypeSelectall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDataInterfacetypeSelectall(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray abilitytype_Array = new JSONArray();
            TInterfaceTypeExample abilitytypeExample = new TInterfaceTypeExample();
            TInterfaceTypeExample.Criteria criteria = abilitytypeExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            abilitytypeExample.setOrderByClause("fname asc");
            JSONObject abilitytype_object = new JSONObject();
            abilitytype_object.put("id", "-1");
            abilitytype_object.put("text","全部");
            abilitytype_Array.add(abilitytype_object);
            List<TInterfaceType> abilitytype_list = tInterfaceTypeService.findByExample(abilitytypeExample);
            for (TInterfaceType abilitytype : abilitytype_list) {
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
     * 根据ID获取j接口类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryInterfacetypeInfo2", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryInterfacetypeInfo2(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询j接口类型信息
            TInterfaceType abilitytype = tInterfaceTypeService.findById(key);
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
     * 添加j接口类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加接口类型信息")
    @ResponseBody
    @RequestMapping(value = "/addInterfacetype2", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addInterfacetype2(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String Fname = jsonParam.getString("Fname");

        try {
            if (repeaTInterfaceType(0L, Fname, "1") == 0) {
                String CookiesLoginabilitytypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilitytypeID != null && !CookiesLoginabilitytypeID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilitytypeID);
                }
                // 新建j接口类型信息
                TInterfaceType abilitytype = new TInterfaceType();
                abilitytype.setFname(Fname);
                abilitytype.setFcid(Long.parseLong(uid));
                abilitytype.setFcdate(new Date());
                tInterfaceTypeService.save(abilitytype);
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
     * 修改j接口类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改j接口类型信息")
    @ResponseBody
    @RequestMapping(value = "/updateInterfacetype2", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateInterfacetype2(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String Fname = jsonParam.getString("Fname");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            if (repeaTInterfaceType(key, Fname, "2") == 0) {
                String CookiesLoginabilitytypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginabilitytypeID != null && !CookiesLoginabilitytypeID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginabilitytypeID);
                }
                // 更新j接口类型信息
                TInterfaceType abilitytype = new TInterfaceType();
                abilitytype.setFkeyid(key);
                abilitytype.setFname(Fname);
                abilitytype.setFuid(Long.parseLong(uid));
                abilitytype.setFudate(new Date());
                tInterfaceTypeService.update(abilitytype);
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
     * 删除j接口类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除接口类型信息")
    @ResponseBody
    @RequestMapping(value = "/delInterfacetype2", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delInterfacetype2(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TInterfaceExample tAbilityExample = new TInterfaceExample();
            tAbilityExample.createCriteria().andFtypeidEqualTo(Long.parseLong(id));
            List<TInterface> abilityList = tInterfaceService.findByExample(tAbilityExample);
            if (abilityList.size() == 0) {
                tInterfaceTypeService.deleteById(Long.parseLong(id));
                // 返回值
                object.put("result", "success");
            }else{
                object.put("result", "该j接口类型已被使用，不能删除(不想使用可禁用)！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 变更j接口类型信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/stateInterfacetype", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stateInterfacetype(HttpServletRequest request)
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
            TInterfaceType abilitytype = new TInterfaceType();
            abilitytype.setFkeyid(Long.parseLong(id));
            abilitytype.setFuid(Long.parseLong(uid));
            abilitytype.setFudate(new Date());
            abilitytype.setFstate(Integer.valueOf(state));
            tInterfaceTypeService.update(abilitytype);
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
     * 验证j接口类型是否存在
     */
    private int repeaTInterfaceType(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TInterfaceTypeExample abilitytypeExample = new TInterfaceTypeExample();
            TInterfaceTypeExample.Criteria criteria = abilitytypeExample.createCriteria();
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
            List<TInterfaceType> abilitytypeList = tInterfaceTypeService.findByExample(abilitytypeExample);
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


    //根据ID查询j接口类型名称
    public String getName(Long id) {
        TInterfaceType byId = tInterfaceTypeService.findById(id);
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
    @RequestMapping(value = "/queryInterfaceTypeInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryInterfaceTypeInfo( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 获取数据库记录
            TInterfaceType paramType = tInterfaceTypeService.findById(key);
            JSONObject paramType_object = new JSONObject();
            paramType_object.put("key", ParamTools.getEnParam(paramType.getFkeyid().toString()));
            paramType_object.put("FName", paramType.getFname());
            paramType_object.put("FNote", paramType.getFnote());
            paramType_object.put("FPID", ParamTools.getEnParam(paramType.getFpid().toString()));
            if(paramType.getFpid() == 1 ){
                paramType_object.put("FPName", "根节点");
            }else{
                paramType = tInterfaceTypeService.findById(paramType.getFpid());
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
    @RequestMapping(value = "/queryinterfacetype", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryinterfacetype( HttpServletRequest request )
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
                TInterfaceTypeExample tAbilityTypeExample = new TInterfaceTypeExample();
                TInterfaceTypeExample.Criteria criteria = tAbilityTypeExample.createCriteria();
                criteria.andFpidEqualTo(1L);
                List<TInterfaceType> list = tInterfaceTypeService.findByExample(tAbilityTypeExample);
                if (list.size() > 0) {
                    object.put("isParent", 1);
                } else {
                    object.put("isParent", 0);
                }
                object.put("name", "根节点");
                objectList.add(object);
            } else {
                String pid = id.equals("1") ? "1" : ParamTools.getdeParam(id);
                TInterfaceTypeExample tAbilityTypeExample = new TInterfaceTypeExample();
                TInterfaceTypeExample.Criteria criteria = tAbilityTypeExample.createCriteria();
                criteria.andFpidEqualTo(Long.parseLong(pid));
                List<TInterfaceType> list = tInterfaceTypeService.findByExample(tAbilityTypeExample);
                for (TInterfaceType tAbilityType : list) {
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
    @LogOperation("添加j接口类型信息")
    @ResponseBody
    @RequestMapping(value = "/addInterfaceType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addInterfaceType( HttpServletRequest request )
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

            TInterfaceTypeExample tAbilityTypeExample = new TInterfaceTypeExample();
            TInterfaceTypeExample.Criteria criteria = tAbilityTypeExample.createCriteria();
            criteria.andFpidEqualTo(pId);
            criteria.andFnameEqualTo(f_name);
            List<TInterfaceType> abilityTypeList = tInterfaceTypeService.findByExample(tAbilityTypeExample);
            if(abilityTypeList.size() == 0){
                // 获取父节点路径
                String path = "";
                TInterfaceType tAbilityType = tInterfaceTypeService.findById(pId);
                if (tAbilityType == null) {
                    path = "|_1_|";
                } else {
                    path = tAbilityType.getFpath();
                }
                // 添加数据
                String id = "";
                TInterfaceType abilityType = new TInterfaceType();
                abilityType.setFcid(Long.parseLong(uid));
                abilityType.setFpid(pId);
                abilityType.setFname(f_name);
                abilityType.setFisleaf(1);
                abilityType.setFnote(FNote);
                tInterfaceTypeService.save(abilityType);
                id = ParamTools.getEnParam(abilityType.getFkeyid().toString());
                abilityType.setFpath(path + "|_" + abilityType.getFkeyid().toString() + "_|");
                tInterfaceTypeService.update(abilityType);
                // 刷新父节点
                abilityType = new TInterfaceType();
                abilityType.setFkeyid(pId);
                abilityType.setFisleaf(0);
                tInterfaceTypeService.update(abilityType);

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
    @LogOperation("修改j接口类型信息")
    @ResponseBody
    @RequestMapping(value = "/updateInterfaceType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateInterfaceType( HttpServletRequest request )
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
            TInterfaceTypeExample tAbilityTypeExample = new TInterfaceTypeExample();
            TInterfaceTypeExample.Criteria criteria = tAbilityTypeExample.createCriteria();
            criteria.andFkeyidNotEqualTo(Long.parseLong(id));
            criteria.andFnameEqualTo(f_name);
            List<TInterfaceType> abilityTypeList = tInterfaceTypeService.findByExample(tAbilityTypeExample);
            if(abilityTypeList.size() == 0){
                // 更新主记录
                TInterfaceType abilityType1 = new TInterfaceType();
                abilityType1.setFkeyid(Long.parseLong(id));
                abilityType1.setFuid(Long.parseLong(uid));
                abilityType1.setFudate(new Date());
                abilityType1.setFname(f_name);
                abilityType1.setFnote(FNote);
                tInterfaceTypeService.update(abilityType1);
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
    @LogOperation("删除接口类型信息")
    @ResponseBody
    @RequestMapping(value = "/delInterfaceType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delInterfaceType( HttpServletRequest request )
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
            TInterfaceType abilityType = tInterfaceTypeService.findById(key);
            // 判断是否可删除
            if (abilityType.getFisleaf() == 0) {
                object.put("result", "error");
                object.put("error", "只能删除最下层节点");
            } else {
                TInterfaceExample tAbilityExample = new TInterfaceExample();
                TInterfaceExample.Criteria criteria = tAbilityExample.createCriteria();
                criteria.andFtypeidEqualTo(key);
                if (tInterfaceService.findByExample(tAbilityExample).size() > 0) {
                    object.put("result", "error");
                    object.put("error", "此节点在使用中无法删除");
                } else {
                    // 删除记录
                    tInterfaceTypeService.deleteById(key);
                    // 判断是否需要修改父节点状态
                    TInterfaceTypeExample paramTypeExample = new TInterfaceTypeExample();
                    paramTypeExample.or().andFpidEqualTo(abilityType.getFpid());
                    if (tInterfaceTypeService.findByExample(paramTypeExample).size() == 0) {
                        TInterfaceType SCTObject = new TInterfaceType();
                        SCTObject.setFkeyid(abilityType.getFpid());
                        SCTObject.setFisleaf(1);
                        tInterfaceTypeService.update(SCTObject);
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
    @RequestMapping(value = "/queryInterfaceTypeALL", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryInterfaceTypeALL( HttpServletRequest request )
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
        TInterfaceTypeExample tAbilityTypeExample = new TInterfaceTypeExample();
        tAbilityTypeExample.or().andFpidEqualTo(pid);
        tAbilityTypeExample.setOrderByClause("FCDATE asc");
        List<TInterfaceType> tAbilityTypeList = tInterfaceTypeService.findByExample(tAbilityTypeExample);

        for (TInterfaceType abilityType : tAbilityTypeList) {
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