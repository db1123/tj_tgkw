package fun.server.controller.myproject;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.service.TLogActionService;
import fun.server.service.TProjectDataService;
import fun.server.service.TDataTypeService;
import fun.server.service.TUserService;
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
 * 参考资料类型管理 相关业务处理
 */
@RestController
@RequestMapping("/s/dataType")
public class DataType {

    private final TDataTypeService tDataTypeService;
    private final TProjectDataService tProjectDataService;
    private final TLogActionService logActionService;
    private final TUserService tUserService;
    public DataType(TDataTypeService tDataTypeService, TProjectDataService tProjectDataService, TLogActionService logActionService, TUserService tUserService) {
        this.tDataTypeService = tDataTypeService;
        this.tProjectDataService = tProjectDataService;
        this.logActionService = logActionService;
        this.tUserService = tUserService;
    }


    /**
     * 获取参考资料类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querydataType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querydataType(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        try {
            // 获取数据库记录
            JSONArray dataType_Array = new JSONArray();
            // 查询条件
            TDataTypeExample TDataTypeExample = new TDataTypeExample();
            TDataTypeExample.Criteria criteria = TDataTypeExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");

            }
            TDataTypeExample.setOrderByClause("FCDATE desc");
            PageInfo<TDataType> dataTypePageInfo = tDataTypeService.findByExampleMapper(TDataTypeExample, (page - 1) * results, results);
            List<TDataType> dataType_list = dataTypePageInfo.getList();

            for (TDataType dataType : dataType_list) {
                JSONObject dataType_object = new JSONObject();
                dataType_object.put("key", ParamTools.getEnParam(dataType.getFkeyid().toString()));
                dataType_object.put("FName", dataType.getFname());
                dataType_object.put("FCID", dataType.getFcid());
                dataType_object.put("FUID", dataType.getFuid());
                dataType_object.put("FCDATE", dataType.getFcdate());
                dataType_object.put("FUDATE", dataType.getFudate());
                dataType_object.put("FState", dataType.getFstate());
                dataType_Array.add(dataType_object);
            }
            // 返回值
            object.put("list", dataType_Array);
            object.put("total", dataTypePageInfo.getTotal()); // 总行数
            object.put("page", dataTypePageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取参考资料类型信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatadataTypeSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatadataTypeSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray dataType_Array = new JSONArray();
            TDataTypeExample dataTypeExample = new TDataTypeExample();
            TDataTypeExample.Criteria criteria = dataTypeExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            dataTypeExample.setOrderByClause("fname asc");
            List<TDataType> dataType_list = tDataTypeService.findByExample(dataTypeExample);
            for (TDataType dataType : dataType_list) {
                JSONObject dataType_object = new JSONObject();
                dataType_object.put("id", ParamTools.getEnParam(dataType.getFkeyid().toString()));
                dataType_object.put("text", dataType.getFname());
                dataType_Array.add(dataType_object);
            }
            // 返回值

            object.put("list", dataType_Array);
            object.put("result", "success");
            object.put("results", dataType_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取参考资料类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querydataTypeInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querydataTypeInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询参考资料类型信息
            TDataType dataType = tDataTypeService.findById(key);
            JSONObject dataType_object = new JSONObject();
            dataType_object.put("key", ParamTools.getEnParam(dataType.getFkeyid().toString()));
            dataType_object.put("FName", dataType.getFname());
            dataType_object.put("FCID", dataType.getFcid());
            dataType_object.put("FUID", dataType.getFuid());
            dataType_object.put("FCDATE", dataType.getFcdate());
            dataType_object.put("FUDATE", dataType.getFudate());
            dataType_object.put("FState", dataType.getFstate());
            // 返回值
            object.put("info", dataType_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加参考资料类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("添加参考资料类型信息")
    @ResponseBody
    @RequestMapping(value = "/adddataType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String adddataType(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String Fname = jsonParam.getString("Fname");

        try {
            if (repeaTDataType(0L, Fname, "1") == 0) {
                String CookiesLogindataTypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogindataTypeID != null && !CookiesLogindataTypeID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogindataTypeID);
                }
                // 新建参考资料类型信息
                TDataType dataType = new TDataType();
                dataType.setFname(Fname);
                dataType.setFcid(Long.parseLong(uid));
                dataType.setFcdate(new Date());
                tDataTypeService.save(dataType);

                TUser tUser = tUserService.findById(Long.parseLong(uid));
                TLogAction logAction = new TLogAction();
                logAction.setfUserId(tUser.getfKeyId());
                logAction.setfUserName(tUser.getfName());
                logAction.setfType(3);
                logAction.setfPath("dataType/adddataType{" + dataType.getFkeyid() + "}");
                logAction.setfUserType(1);

                logAction.setfMemo("新增参考资料类型【"+Fname+"】信息。");
                logActionService.save(logAction);
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
     * 修改参考资料类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("修改参考资料类型信息")
    @ResponseBody
    @RequestMapping(value = "/updatedataType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatedataType(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String Fname = jsonParam.getString("Fname");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            if (repeaTDataType(key, Fname, "2") == 0) {
                String CookiesLogindataTypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogindataTypeID != null && !CookiesLogindataTypeID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogindataTypeID);
                }
                TDataType dataType1 = tDataTypeService.findById(key);

                // 更新参考资料类型信息
                TDataType dataType = new TDataType();
                dataType.setFkeyid(key);
                dataType.setFname(Fname);
                dataType.setFuid(Long.parseLong(uid));
                dataType.setFudate(new Date());
                tDataTypeService.update(dataType);

                TUser tUser = tUserService.findById(Long.parseLong(uid));
                TLogAction logAction = new TLogAction();
                logAction.setfUserId(tUser.getfKeyId());
                logAction.setfUserName(tUser.getfName());
                logAction.setfType(3);
                logAction.setfPath("dataType/updatedataType{" + key + "}");
                logAction.setfUserType(1);

                logAction.setfMemo("参考资料名称【"+dataType1.getFname()+"】修改为【"+Fname+"】。");
                logActionService.save(logAction);

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
     * 删除参考资料类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("删除参考资料类型信息")
    @ResponseBody
    @RequestMapping(value = "/deldataType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deldataType(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLogindataTypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogindataTypeID != null && !CookiesLogindataTypeID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            TProjectDataExample tMaterialExample = new TProjectDataExample();
            tMaterialExample.createCriteria().andFtypeidEqualTo(Long.parseLong(id));
            List<TProjectData> projectList = tProjectDataService.findByExample(tMaterialExample);
            if (projectList.size() > 0) {
                object.put("result", "error");
                object.put("error", "当前类型在项目参考资料信息中被使用，不能删除！");
            } else {
                TDataType dataType = tDataTypeService.findById(Long.parseLong(id));

                tDataTypeService.deleteById(Long.parseLong(id));

                TUser tUser = tUserService.findById(Long.parseLong(uid));
                TLogAction logAction = new TLogAction();
                logAction.setfUserId(tUser.getfKeyId());
                logAction.setfUserName(tUser.getfName());
                logAction.setfType(3);
                logAction.setfPath("dataType/deldataType{" + id + "}");
                logAction.setfUserType(1);

                logAction.setfMemo("删除参考资料类型【"+dataType.getFname()+"】信息。");
                logActionService.save(logAction);

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
     * 变更参考资料类型信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("变更参考资料类型状态")
    @ResponseBody
    @RequestMapping(value = "/statedataType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statedataType(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLogindataTypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogindataTypeID != null && !CookiesLogindataTypeID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";

            TDataType dataType1 = tDataTypeService.findById(Long.parseLong(id));
            TDataType dataType = new TDataType();
            dataType.setFkeyid(Long.parseLong(id));
            dataType.setFuid(Long.parseLong(uid));
            dataType.setFudate(new Date());
            dataType.setFstate(Integer.valueOf(state));
            tDataTypeService.update(dataType);
            String statestr = "";
            if(Integer.valueOf(state) ==0){
                statestr = "禁用";
            }else{
                statestr = "启用";
            }

            TUser tUser = tUserService.findById(Long.parseLong(uid));
            TLogAction logAction = new TLogAction();
            logAction.setfUserId(tUser.getfKeyId());
            logAction.setfUserName(tUser.getfName());
            logAction.setfType(3);
            logAction.setfPath("dataType/statedataType{" + id + "}");
            logAction.setfUserType(1);

            logAction.setfMemo("参考资料类型【"+dataType1.getFname()+"】状态变更为【"+statestr+"】。");
            logActionService.save(logAction);

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
     * 验证参考资料类型是否存在
     */
    private int repeaTDataType(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TDataTypeExample dataTypeExample = new TDataTypeExample();
            TDataTypeExample.Criteria criteria = dataTypeExample.createCriteria();
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
            List<TDataType> rojectTypeList = tDataTypeService.findByExample(dataTypeExample);
            if (rojectTypeList.size() == 0) {
                code = 0;
            } else {
                code = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }


    //根据ID查询参考资料类型名称
    public String getName(Long id) {
        TDataType byId = tDataTypeService.findById(id);
        if (byId != null) {
            return byId.getFname();
        } else {
            return "";
        }

    }
}