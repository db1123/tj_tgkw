package fun.server.controller.myproject;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
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
 * 交付物类型管理 相关业务处理
 */
@RestController
@RequestMapping("/s/tflowcellfiletype")
public class SubDataType_cell {

    private final TFlowCellFileTypeService tFlowCellFileTypeService;
    private final TSubDataService tSubDataService;
    private final TFlowCellFileTypeGroupService tFlowCellFileTypeGroupService;
    private final TLogActionService logActionService;
    private final TUserService tUserService;
    public SubDataType_cell(TFlowCellFileTypeService tFlowCellFileTypeService, TSubDataService tSubDataService, TFlowCellFileTypeGroupService tFlowCellFileTypeGroupService, TLogActionService logActionService, TUserService tUserService) {
        this.tFlowCellFileTypeService = tFlowCellFileTypeService;
        this.tSubDataService = tSubDataService;
        this.tFlowCellFileTypeGroupService = tFlowCellFileTypeGroupService;
        this.logActionService = logActionService;
        this.tUserService = tUserService;
    }


    /**
     * 获取交付物类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytflowcellfiletype", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytflowcellfiletype(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");

        String fz = jsonParam.getString("fz");
        int lx = jsonParam.getInteger("lx");


        try {
            // 获取数据库记录
            JSONArray tflowcellfiletype_Array = new JSONArray();
            // 查询条件
            TFlowCellFileTypeExample TFlowCellFileTypeExample = new TFlowCellFileTypeExample();
            TFlowCellFileTypeExample.Criteria criteria = TFlowCellFileTypeExample.createCriteria();
            List<Long> fzid = new ArrayList<>();
            TFlowCellFileTypeGroupExample tFlowCellFileTypeGroupExample = new TFlowCellFileTypeGroupExample();
            TFlowCellFileTypeGroupExample.Criteria groupExampleCriteria = tFlowCellFileTypeGroupExample.createCriteria();
            if (fz != null && !fz.equals("")) {
                groupExampleCriteria.andFnameLike("%" + fz + "%");
                List<TFlowCellFileTypeGroup> groupList = tFlowCellFileTypeGroupService.findByExample(tFlowCellFileTypeGroupExample);

                if (groupList.size() > 0) {
                    for (TFlowCellFileTypeGroup fgroup : groupList) {
                        fzid.add(fgroup.getFkeyid());
                    }
                } else {
                    fzid.add(-1l);
                }
                criteria.andFFtypeIn(fzid);
            }
            if (lx != -1) {
                criteria.andFPormEqualTo(lx);
            }
            if (name != null && !name.equals("")) {
                criteria.andFNameLike("%" + name + "%");
            }
            TFlowCellFileTypeExample.setOrderByClause("f_cdate desc");
            PageInfo<TFlowCellFileType> tflowcellfiletypePageInfo = tFlowCellFileTypeService.findByExampleMapper(TFlowCellFileTypeExample, (page - 1) * results, results);
            List<TFlowCellFileType> tflowcellfiletype_list = tflowcellfiletypePageInfo.getList();

            for (TFlowCellFileType tflowcellfiletype : tflowcellfiletype_list) {
                JSONObject tflowcellfiletype_object = new JSONObject();
                tflowcellfiletype_object.put("key", ParamTools.getEnParam(tflowcellfiletype.getfKeyId().toString()));
                tflowcellfiletype_object.put("FName", tflowcellfiletype.getfName());
                tflowcellfiletype_object.put("Fporm", tflowcellfiletype.getfPorm());
                if (tflowcellfiletype.getfPorm() == null) {
                    tflowcellfiletype_object.put("FpormName", "");
                } else {
                    switch (tflowcellfiletype.getfPorm()) {
                        case 1:
                            tflowcellfiletype_object.put("FpormName", "项目");
                            break;
                        case 2:
                            tflowcellfiletype_object.put("FpormName", "任务");
                            break;
                        default:
                            tflowcellfiletype_object.put("FpormName", "");
                            break;
                    }
                }

                tflowcellfiletype_object.put("Ftype", tflowcellfiletype.getfFtype() == null ? "" : tflowcellfiletype.getfFtype());
                if (tflowcellfiletype.getfFtype() != null) {
                    TFlowCellFileTypeGroup typeGroup = tFlowCellFileTypeGroupService.findById(tflowcellfiletype.getfFtype());
                    tflowcellfiletype_object.put("FtypeName", typeGroup.getFname() == null ? "" : typeGroup.getFname());
                } else {
                    tflowcellfiletype_object.put("FtypeName", "");
                }

                tflowcellfiletype_object.put("FCID", tflowcellfiletype.getfCid());
                tflowcellfiletype_object.put("FUID", tflowcellfiletype.getfUid());
                tflowcellfiletype_object.put("FCDATE", tflowcellfiletype.getfCdate());
                tflowcellfiletype_object.put("FUDATE", tflowcellfiletype.getfUdate());
                tflowcellfiletype_object.put("FState", tflowcellfiletype.getfState());

                tflowcellfiletype_Array.add(tflowcellfiletype_object);
            }
            // 返回值
            object.put("list", tflowcellfiletype_Array);
            object.put("total", tflowcellfiletypePageInfo.getTotal()); // 总行数
            object.put("page", tflowcellfiletypePageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取交付物类型信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatatflowcellfiletypeSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatatflowcellfiletypeSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray tflowcellfiletype_Array = new JSONArray();
            TFlowCellFileTypeExample tflowcellfiletypeExample = new TFlowCellFileTypeExample();
            TFlowCellFileTypeExample.Criteria criteria = tflowcellfiletypeExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFNameLike("%" + search + "%");
            }

            tflowcellfiletypeExample.setOrderByClause("fname asc");
            List<TFlowCellFileType> tflowcellfiletype_list = tFlowCellFileTypeService.findByExample(tflowcellfiletypeExample);
            for (TFlowCellFileType tflowcellfiletype : tflowcellfiletype_list) {
                JSONObject tflowcellfiletype_object = new JSONObject();
                tflowcellfiletype_object.put("id", ParamTools.getEnParam(tflowcellfiletype.getfKeyId().toString()));
                tflowcellfiletype_object.put("text", tflowcellfiletype.getfName());
                tflowcellfiletype_Array.add(tflowcellfiletype_object);
            }
            // 返回值

            object.put("list", tflowcellfiletype_Array);
            object.put("result", "success");
            object.put("results", tflowcellfiletype_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取交付物类型信息(下拉列表) -- t_flow_cell_file_type
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDataflowcellfiletypeSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDataflowcellfiletypeSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray tFlowCellFileType_Array = new JSONArray();

            TFlowCellFileTypeExample tFlowCellFileTypeExample = new TFlowCellFileTypeExample();
            TFlowCellFileTypeExample.Criteria criteria = tFlowCellFileTypeExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFNameLike("%" + search + "%");
            }
            tFlowCellFileTypeExample.setOrderByClause("f_name asc");
            List<TFlowCellFileType> tFlowCellFileTypeList = tFlowCellFileTypeService.findByExample(tFlowCellFileTypeExample);
            for (TFlowCellFileType tflowcellfiletype : tFlowCellFileTypeList) {
                JSONObject tFlowCellFileType_object = new JSONObject();
                tFlowCellFileType_object.put("id", ParamTools.getEnParam(tflowcellfiletype.getfKeyId().toString()));
                tFlowCellFileType_object.put("text", tflowcellfiletype.getfName());
                tFlowCellFileType_Array.add(tFlowCellFileType_object);
            }
            // 返回值

            object.put("list", tFlowCellFileType_Array);
            object.put("result", "success");
            object.put("results", tFlowCellFileType_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取交付物类型分组信息(下拉列表) -- t_flow_cell_file_type_group
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDataflowcellfiletypegroupSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDataflowcellfiletypegroupSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        try {
            int ftype = 1;
            try {
                ftype = jsonParam.getInteger("ftype");
            } catch (Exception e) {
                ftype = 1;
            }
            // 获取数据库记录
            JSONArray tFlowCellFileTypeGroup_Array = new JSONArray();

            TFlowCellFileTypeGroupExample tFlowCellFileTypeGroupExample = new TFlowCellFileTypeGroupExample();
            TFlowCellFileTypeGroupExample.Criteria criteria = tFlowCellFileTypeGroupExample.createCriteria();
            criteria.andFtypeEqualTo(ftype);
            tFlowCellFileTypeGroupExample.setOrderByClause("fname asc");
            List<TFlowCellFileTypeGroup> tFlowCellFileTypeGroupsList = tFlowCellFileTypeGroupService.findByExample(tFlowCellFileTypeGroupExample);
            for (TFlowCellFileTypeGroup tFlowCellFileTypeGroup : tFlowCellFileTypeGroupsList) {
                JSONObject tFlowCellFileType_object = new JSONObject();
                tFlowCellFileType_object.put("id", ParamTools.getEnParam(tFlowCellFileTypeGroup.getFkeyid().toString()));
                tFlowCellFileType_object.put("text", tFlowCellFileTypeGroup.getFname());
                tFlowCellFileTypeGroup_Array.add(tFlowCellFileType_object);
            }
            // 返回值

            object.put("list", tFlowCellFileTypeGroup_Array);
            object.put("result", "success");
            object.put("results", tFlowCellFileTypeGroup_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 根据ID获取交付物类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytflowcellfiletypeInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytflowcellfiletypeInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询交付物类型信息
            TFlowCellFileType tflowcellfiletype = tFlowCellFileTypeService.findById(key);
            JSONObject tflowcellfiletype_object = new JSONObject();
            tflowcellfiletype_object.put("key", ParamTools.getEnParam(tflowcellfiletype.getfKeyId().toString()));
            tflowcellfiletype_object.put("FName", tflowcellfiletype.getfName());
            tflowcellfiletype_object.put("Fporm", tflowcellfiletype.getfPorm());
            if (tflowcellfiletype.getfPorm() == null) {
                tflowcellfiletype_object.put("FpormName", "");
            } else {
                switch (tflowcellfiletype.getfPorm()) {
                    case 1:
                        tflowcellfiletype_object.put("FpormName", "项目");
                        break;
                    case 2:
                        tflowcellfiletype_object.put("FpormName", "模具");
                        break;
                    default:
                        tflowcellfiletype_object.put("FpormName", "");
                        break;
                }
            }

            tflowcellfiletype_object.put("Ftype", tflowcellfiletype.getfFtype() == null ? "" : tflowcellfiletype.getfFtype());
            if (tflowcellfiletype.getfFtype() != null) {
                TFlowCellFileTypeGroup typeGroup = tFlowCellFileTypeGroupService.findById(tflowcellfiletype.getfFtype());
                tflowcellfiletype_object.put("FtypeName", typeGroup.getFname() == null ? "" : typeGroup.getFname());
            } else {
                tflowcellfiletype_object.put("FtypeName", "");
            }
            tflowcellfiletype_object.put("FCID", tflowcellfiletype.getfCid());
            tflowcellfiletype_object.put("FUID", tflowcellfiletype.getfUid());
            tflowcellfiletype_object.put("FCDATE", tflowcellfiletype.getfCdate());
            tflowcellfiletype_object.put("FUDATE", tflowcellfiletype.getfUdate());
            tflowcellfiletype_object.put("FState", tflowcellfiletype.getfState());
            // 返回值
            object.put("info", tflowcellfiletype_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加交付物类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("添加交付物类型信息")
    @ResponseBody
    @RequestMapping(value = "/addtflowcellfiletype", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addtflowcellfiletype(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String Fname = jsonParam.getString("Fname");
        String fporm = jsonParam.getString("fporm");
        String fftype = jsonParam.getString("fftype");

        try {
            if (repeaTFlowCellFileType(0L, Fname, "1") == 0) {
                String CookiesLogintflowcellfiletypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintflowcellfiletypeID != null && !CookiesLogintflowcellfiletypeID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintflowcellfiletypeID);
                }
                //查询分组是输入的还是选择的
                TFlowCellFileTypeGroupExample tFlowCellFileTypeGroupExample = new TFlowCellFileTypeGroupExample();
                TFlowCellFileTypeGroupExample.Criteria criteria = tFlowCellFileTypeGroupExample.createCriteria();
                criteria.andFnameEqualTo(fftype);
                List<TFlowCellFileTypeGroup> groupList = tFlowCellFileTypeGroupService.findByExample(tFlowCellFileTypeGroupExample);
                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
                Long groupID = 0l;
                if (groupList.size() > 0) {
                    groupID = groupList.get(0).getFkeyid();
                } else if (groupList.size() == 0) {
                    //新增输入的内容
                    groupID = idWorker.nextId();
                    TFlowCellFileTypeGroup tFlowCellFileTypeGroup = new TFlowCellFileTypeGroup();
                    tFlowCellFileTypeGroup.setFkeyid(groupID);
                    tFlowCellFileTypeGroup.setFcdate(new Date());
                    tFlowCellFileTypeGroup.setFcid(Long.parseLong(uid));
                    tFlowCellFileTypeGroup.setFname(fftype);
                    tFlowCellFileTypeGroup.setFtype(Integer.parseInt(fporm));
                    tFlowCellFileTypeGroupService.save(tFlowCellFileTypeGroup);
                }
                // 新建交付物类型信息
                TFlowCellFileType tflowcellfiletype = new TFlowCellFileType();
                tflowcellfiletype.setfName(Fname);
                tflowcellfiletype.setfFtype(groupID);
                tflowcellfiletype.setfPorm(Integer.parseInt(fporm));
                tflowcellfiletype.setfCid(Long.parseLong(uid));
                tflowcellfiletype.setfCdate(new Date());
                tFlowCellFileTypeService.save(tflowcellfiletype);

                try {
                    TUser tUser = tUserService.findById(Long.parseLong(uid));
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserId(tUser.getfKeyId());
                    logAction.setfUserName(tUser.getfName());
                    logAction.setfType(3);
                    logAction.setfPath("tflowcellfiletype/addtflowcellfiletype{" + tflowcellfiletype.getfKeyId() + "}");
                    logAction.setfUserType(1);

                    logAction.setfMemo("新增交付物类型【"+Fname+"】信息。");
                    logActionService.save(logAction);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

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
     * 修改交付物类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("修改交付物类型信息")
    @ResponseBody
    @RequestMapping(value = "/updatetflowcellfiletype", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatetflowcellfiletype(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String Fname = jsonParam.getString("Fname");
        String fporm = jsonParam.getString("fporm");
        String fftype = jsonParam.getString("fftype");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            if (repeaTFlowCellFileType(key, Fname, "2") == 0) {
                String CookiesLogintflowcellfiletypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintflowcellfiletypeID != null && !CookiesLogintflowcellfiletypeID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintflowcellfiletypeID);
                }
                //查询分组是输入的还是选择的
                TFlowCellFileTypeGroupExample tFlowCellFileTypeGroupExample = new TFlowCellFileTypeGroupExample();
                TFlowCellFileTypeGroupExample.Criteria criteria = tFlowCellFileTypeGroupExample.createCriteria();
                criteria.andFnameEqualTo(fftype);
                List<TFlowCellFileTypeGroup> groupList = tFlowCellFileTypeGroupService.findByExample(tFlowCellFileTypeGroupExample);
                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
                Long groupID = 0l;
                if (groupList.size() > 0) {
                    groupID = groupList.get(0).getFkeyid();
                } else if (groupList.size() == 0) {
                    //新增输入的内容
                    groupID = idWorker.nextId();
                    TFlowCellFileTypeGroup tFlowCellFileTypeGroup = new TFlowCellFileTypeGroup();
                    tFlowCellFileTypeGroup.setFkeyid(groupID);
                    tFlowCellFileTypeGroup.setFcdate(new Date());
                    tFlowCellFileTypeGroup.setFcid(Long.parseLong(uid));
                    tFlowCellFileTypeGroup.setFname(fftype);
                    tFlowCellFileTypeGroup.setFtype(Integer.parseInt(fporm));
                    tFlowCellFileTypeGroupService.save(tFlowCellFileTypeGroup);
                }

                TFlowCellFileType fileType = tFlowCellFileTypeService.findById(key);

                // 更新交付物类型信息
                TFlowCellFileType tflowcellfiletype = new TFlowCellFileType();
                tflowcellfiletype.setfKeyId(key);
                tflowcellfiletype.setfName(Fname);
                tflowcellfiletype.setfFtype(groupID);
                tflowcellfiletype.setfPorm(Integer.parseInt(fporm));
                tflowcellfiletype.setfUid(Long.parseLong(uid));
                tflowcellfiletype.setfUdate(new Date());
                tFlowCellFileTypeService.update(tflowcellfiletype);


                try {
                    TUser tUser = tUserService.findById(Long.parseLong(uid));
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserId(tUser.getfKeyId());
                    logAction.setfUserName(tUser.getfName());
                    logAction.setfType(3);
                    logAction.setfPath("tflowcellfiletype/updatetflowcellfiletype{" + key + "}");
                    logAction.setfUserType(1);
                    if(fileType.getfName().equals(Fname))
                        logAction.setfMemo("修改交付物类型【"+fileType.getfName()+"】信息。");
                    else
                        logAction.setfMemo("交付物类型名称【"+fileType.getfName()+"】修改为【"+Fname+"】。");
                    logActionService.save(logAction);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

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
     * 删除交付物类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("删除交付物类型信息")
    @ResponseBody
    @RequestMapping(value = "/deltflowcellfiletype", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deltflowcellfiletype(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLogintflowcellfiletypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintflowcellfiletypeID != null && !CookiesLogintflowcellfiletypeID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            TSubdataExample tMaterialExample = new TSubdataExample();
            tMaterialExample.createCriteria().andFtypeidEqualTo(Long.parseLong(id));
            List<TSubdata> projectList = tSubDataService.findByExample(tMaterialExample);

            int isdel = 0;
            String str = "";
            if (projectList.size() > 0) {
                isdel = 1;
                str += "项目参考资料、";
            }
            //
            if (isdel == 0) {

                TFlowCellFileType fileType = tFlowCellFileTypeService.findById(Long.parseLong(id));

                tFlowCellFileTypeService.deleteById(Long.parseLong(id));


                try {
                    TUser tUser = tUserService.findById(Long.parseLong(uid));
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserId(tUser.getfKeyId());
                    logAction.setfUserName(tUser.getfName());
                    logAction.setfType(3);
                    logAction.setfPath("tflowcellfiletype/deltflowcellfiletype{" + id + "}");
                    logAction.setfUserType(1);

                    logAction.setfMemo("删除交付物类型【"+fileType.getfName()+"】信息。");
                    logActionService.save(logAction);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                // 返回值
                object.put("result", "success");
            } else {
                object.put("result", "error");
                object.put("error", "当前类型在" + str + "等信息中被使用，不能删除！");
            }


        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 变更交付物类型信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statetflowcellfiletype", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statetflowcellfiletype(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLogintflowcellfiletypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintflowcellfiletypeID != null && !CookiesLogintflowcellfiletypeID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TFlowCellFileType tflowcellfiletype = new TFlowCellFileType();
            tflowcellfiletype.setfKeyId(Long.parseLong(id));
            tflowcellfiletype.setfUid(Long.parseLong(uid));
            tflowcellfiletype.setfUdate(new Date());
            tflowcellfiletype.setfState(Integer.valueOf(state));
            tFlowCellFileTypeService.update(tflowcellfiletype);
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
     * 验证交付物类型是否存在
     */
    private int repeaTFlowCellFileType(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TFlowCellFileTypeExample tflowcellfiletypeExample = new TFlowCellFileTypeExample();
            TFlowCellFileTypeExample.Criteria criteria = tflowcellfiletypeExample.createCriteria();
            if (ftype.equals("2")) { // 修改
                if (id != null) {
                    criteria.andFKeyIdNotEqualTo(id);
                }
                if (name != null && !name.equals("")) {
                    criteria.andFNameEqualTo(name);
                }
            } else { // 新增
                if (name != null && !name.equals("")) {
                    criteria.andFNameEqualTo(name);
                }
            }
            List<TFlowCellFileType> rojectTypeList = tFlowCellFileTypeService.findByExample(tflowcellfiletypeExample);
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


}