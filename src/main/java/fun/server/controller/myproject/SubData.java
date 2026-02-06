package fun.server.controller.myproject;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.server.model.*;
import fun.server.service.*;
import fun.tools.FileConvertTools;
import fun.tools.FolderTools;
import fun.tools.ParamTools;
import fun.tools.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * 交付物管理 相关业务处理
 */
@RestController
@RequestMapping("/s/subdata")
@PropertySource("classpath:upload.properties")
public class SubData {

//    private final TTaskTypeService tTaskTypeService;
    private final TSubDataService tSubDataService;
//    private final TSubDataTypeService tSubDataTypeService;
//    private final TTaskService tTaskService;
    private final TProjectService tProjectService;
    private final TUserService tUserService;
//    private final TFlowService tFlowService;
    private final TProjectLogService tProjectLogService;
    private final TFlowCellFileTypeService tFlowCellFileTypeService;
    private final TProjectPlanService tProjectPlanService;
//    private final TTaskLogService tTaskLogService;
//    private final TTaskTeamService tTaskTeamService;
//    private final TProjectFlowCellService tProjectFlowCellService;
    private final TProjectTypeService tProjectTypeService;
    private final TProjectLevelService tProjectLevelService;
//    private final TCustomerService tCustomerService;
    private final TDeptService tDeptService;
//    private final TFlowCellService tFlowCellService;
    private final TSubDataCheckService tSubDataCheckService;
//    private final TBomSService tBomSService;
//    private final TProjectFlowsService tProjectFlowsService;
    private final TFileFilterService tFileFilterService;
    private final TLogActionService logActionService;


    @Value("${projectplan.path}")
    private String path;

    @Value("${tasksubdata.path}")
    private String subdatapath;

    @Value("${flow.path}")
    private String flowpath;

    public SubData(TSubDataService tSubDataService, TProjectService tProjectService, TUserService tUserService, TProjectLogService tProjectLogService, TFlowCellFileTypeService tFlowCellFileTypeService, TProjectPlanService tProjectPlanService, TProjectTypeService tProjectTypeService, TProjectLevelService tProjectLevelService, TDeptService tDeptService, TSubDataCheckService tSubDataCheckService, TFileFilterService tFileFilterService, TLogActionService logActionService) {
        this.tSubDataService = tSubDataService;
        this.tProjectService = tProjectService;
        this.tUserService = tUserService;
        this.tProjectLogService = tProjectLogService;
        this.tFlowCellFileTypeService = tFlowCellFileTypeService;
        this.tProjectPlanService = tProjectPlanService;
        this.tProjectTypeService = tProjectTypeService;
        this.tProjectLevelService = tProjectLevelService;
        this.tDeptService = tDeptService;
        this.tSubDataCheckService = tSubDataCheckService;
        this.tFileFilterService = tFileFilterService;
        this.logActionService = logActionService;
    }

    /**
     * 获取交付物信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querysubdata", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querysubdata(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        String FProjectPlanID = "0";//jsonParam.getString("FProjectPlanID");
        int FType = -1;// jsonParam.getInteger("FType");
        String FTaskID = "0";// jsonParam.getString("FTaskID");
        String FNodeID = "0";// jsonParam.getString("fnodeid");
        String FProjectID = jsonParam.getString("FProjectID");
        String FWorkFlowID = "0";
        int classifytype = -1;//jsonParam.getInteger("classifytype");
        int executetype = -1;//jsonParam.getInteger("executetype");
        try {
            if (jsonParam.getString("FType") != null) {
                FType = jsonParam.getInteger("FType");
            }
            if (jsonParam.getString("classifytype") != null) {
                classifytype = jsonParam.getInteger("classifytype");
            }
            if (jsonParam.getString("executetype") != null) {
                executetype = jsonParam.getInteger("executetype");
            }
            // 获取数据库记录
            JSONArray subdata_Array = new JSONArray();
            // 查询条件
            TSubdataExample TSubdataExample = new TSubdataExample();
            TSubdataExample.Criteria criteria = TSubdataExample.createCriteria();
            if (jsonParam.getString("FProjectPlanID") != null) {
                FProjectPlanID = jsonParam.getString("FProjectPlanID");
                FProjectPlanID = FProjectPlanID == null ? "0" : (FProjectPlanID.equals("0") ? "0" : ParamTools.getdeParam(FProjectPlanID));
            } else
                FProjectPlanID = "0";
            if (jsonParam.getString("fnodeid") != null) {
                FNodeID = jsonParam.getString("fnodeid");
                FNodeID = FNodeID == null ? "0" : (FNodeID.equals("0") ? "0" : ParamTools.getdeParam(FNodeID));
            } else
                FNodeID = "0";

            if (jsonParam.getString("workflowid") != null) {
                FWorkFlowID = jsonParam.getString("workflowid");
//                System.out.println(FWorkFlowID);
                FWorkFlowID = FWorkFlowID == null ? "0" : (FWorkFlowID.equals("0") ? "0" : ParamTools.getdeParam(FWorkFlowID));
            } else
                FWorkFlowID = "0";
            if (jsonParam.getString("FTaskID") != null) {
                FTaskID = jsonParam.getString("FTaskID");
                FTaskID = FTaskID == null ? "0" : ParamTools.getdeParam(FTaskID);
            } else
                FTaskID = "0";
            FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);
            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }
            if (!FProjectPlanID.equals("0")) {
                criteria.andFprojectplanidEqualTo(Long.parseLong(FProjectPlanID));
            }
            List<Integer> statelist = new ArrayList<>();
            statelist.add(0);
            statelist.add(1);

//            System.out.println("FProjectID:" + FProjectID);
//            System.out.println("FWorkFlowID:" + FWorkFlowID);
//            System.out.println("classifytype:" + classifytype);
//            System.out.println("FType:" + FType);
//            System.out.println("FNodeID:" + FNodeID);
//            System.out.println("=====================================");
            //工作流访问
            if (executetype == 2) {

                if (FType == 1) {
                    //项目计划
                    if (classifytype == 3) {
                        criteria.andFhavetypeEqualTo(0);
                    } else {
                        criteria.andFhavetypeEqualTo(1);
                    }
                    criteria.andFprojectidEqualTo(Long.parseLong(FProjectID));
                    criteria.andFprojectplanidEqualTo(Long.parseLong(FProjectPlanID));
                } else if (FType == 2) {
                    //项目流程
                    if (classifytype == 3) {
                        criteria.andFhavetypeEqualTo(0);
                    } else if (classifytype == 4) {
                        criteria.andFhavetypeIn(statelist);

                    } else {
                        criteria.andFhavetypeEqualTo(1);
                    }
                    criteria.andFprojectidEqualTo(Long.parseLong(FProjectID));
                    criteria.andFtaskidIsNull();
                    criteria.andFprojectplanidIsNull();
                    if (classifytype == 4) {
                        if (!FWorkFlowID.equals("0")) {
                            criteria.andFworkflowidEqualTo(Long.parseLong(FWorkFlowID));
                        }
                        criteria.andFnodeidIsNotNull();
                    } else {
                        if (!FNodeID.equals("0")) {
                            criteria.andFnodeidEqualTo(Long.parseLong(FNodeID));
                        }
                    }
                } else {
                    //任务 、任务流程 、异常 等等
                    if (classifytype == 3) {
                        criteria.andFhavetypeEqualTo(0);
                    } else if (classifytype == 4) {
                        criteria.andFhavetypeIn(statelist);
                    } else {
                        criteria.andFhavetypeEqualTo(1);
                    }
                    if (classifytype == 4) {

                        if (!FWorkFlowID.equals("0")) {
                            criteria.andFworkflowidEqualTo(Long.parseLong(FWorkFlowID));
                        }
                        criteria.andFnodeidIsNotNull();
                    } else {
                        criteria.andFnodeidEqualTo(Long.parseLong(FNodeID));
                    }
                }

            } else {
                // 任务访问
                if (classifytype == 1) {
                    //显示需要的
                    if (!FTaskID.equals("0")) {
                        criteria.andFtaskidEqualTo(Long.parseLong(FTaskID));
                        criteria.andFtypeEqualTo(FType);
                        criteria.andFhavetypeEqualTo(1);
                    } else {
                        criteria.andFtaskidEqualTo(0L);
                    }
                }
                if (classifytype == 2) {
                    //显示流程的
                    if (!FTaskID.equals("0")) {
                        criteria.andFtaskidEqualTo(Long.parseLong(FTaskID));
                    } else {
                        criteria.andFtaskidEqualTo(0L);
                    }
                    criteria.andFnodeidIsNotNull();
                    //criteria.andFhavetypeEqualTo(1);
                }
                if (classifytype == 3) {
                    //显示其他交付物
                    if (!FTaskID.equals("0")) {
                        criteria.andFtaskidEqualTo(Long.parseLong(FTaskID));
                        criteria.andFtypeEqualTo(FType);
                        criteria.andFhavetypeEqualTo(0);
                    } else {
                        criteria.andFtaskidEqualTo(0L);
                    }
                }
            }
            criteria.andFvalidEqualTo(1);
            TSubdataExample.setOrderByClause("FCDATE desc");
            PageInfo<TSubdata> subdataPageInfo = tSubDataService.findByExampleMapper(TSubdataExample, (page - 1) * results, results);
            List<TSubdata> subdata_list = subdataPageInfo.getList();

            for (TSubdata subdata : subdata_list) {
                JSONObject subdata_object = new JSONObject();
                subdata_object.put("key", ParamTools.getEnParam(subdata.getFkeyid().toString()));
                subdata_object.put("FName", subdata.getFname() == null ? "" : subdata.getFname());
                subdata_object.put("FType", subdata.getFtype());
                switch (subdata.getFtype()) {
                    case 1:
                        subdata_object.put("FTypeName", "项目计划交付物");
                        break;
                    case 2:
                        subdata_object.put("FTypeName", "项目工作流");
                        break;
                    case 3:
                        subdata_object.put("FTypeName", "任务交付物");
                        break;
                    case 4:
                        subdata_object.put("FTypeName", "任务工作流");
                        break;
                }
                subdata_object.put("FProjectID", subdata.getFprojectid() == null ? "0" : ParamTools.getEnParam(subdata.getFprojectid().toString()));
                subdata_object.put("FProjectPlanID", subdata.getFprojectplanid() == null ? "0" : ParamTools.getEnParam(subdata.getFprojectplanid().toString()));
                subdata_object.put("FTaskID", subdata.getFtaskid() == null ? "0" : ParamTools.getEnParam(subdata.getFtaskid().toString()));
                subdata_object.put("FWorkFlowID", subdata.getFworkflowid() == null ? "0" : ParamTools.getEnParam(subdata.getFworkflowid().toString()));
//                TFlow tFlow = tFlowService.findById(subdata.getFworkflowid());
//                subdata_object.put("FWorkFlowName", tFlow == null ? "" : tFlow.getfName() == null ? "" : HtmlTools.delHTMLTag(tFlow.getfName()));
                subdata_object.put("FWorkFlowName", "");
                subdata_object.put("FFileName", subdata.getFfilename() == null ? "————" : subdata.getFfilename());
                subdata_object.put("FTool", subdata.getFtool() == null ? "" : subdata.getFtool());
                subdata_object.put("FTypeID", subdata.getFtypeid() == null ? "0" : ParamTools.getEnParam(subdata.getFtypeid().toString()));
                TFlowCellFileType fileType = tFlowCellFileTypeService.findById(subdata.getFtypeid());
                subdata_object.put("FTypeName", fileType == null ? "" : fileType.getfName() == null ? "" : fileType.getfName());
                subdata_object.put("FFileType", subdata.getFfiletype() == null ? "" : subdata.getFfiletype());
                subdata_object.put("FCheckFlowID", subdata.getFcheckflowid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(subdata.getFcheckflowid().toString()));
//                tFlow = tFlowService.findById(subdata.getFcheckflowid());
//                subdata_object.put("FCheckFlowName", tFlow == null ? "" : tFlow.getfName() == null ? "" : HtmlTools.delHTMLTag(tFlow.getfName()));
                subdata_object.put("FCheckFlowName", "");
                subdata_object.put("FNote", subdata.getFnote() == null ? "" : subdata.getFnote());
                subdata_object.put("FCID", subdata.getFcid());
                subdata_object.put("FUID", subdata.getFuid());
                subdata_object.put("FCDATE", subdata.getFcdate());
                subdata_object.put("FUDATE", subdata.getFudate());
                subdata_object.put("FState", subdata.getFstate());
                subdata_object.put("FEdition", subdata.getFedition() == null ? "" : subdata.getFedition());
                if (subdata.getFstate() == 0) {
                    subdata_object.put("FStateName", "未提交");
                    subdata_object.put("TaskFStateName", "否");
                } else if (subdata.getFstate() == 1) {
                    subdata_object.put("FStateName", "已提交");
                    subdata_object.put("TaskFStateName", "是");
                }

                subdata_object.put("FCheckState", subdata.getFcheckstate());
                if (subdata.getFcheckstate() == 0) {
                    subdata_object.put("FCheckStateName", "未审核");
                } else if (subdata.getFcheckstate() == 1) {
                    subdata_object.put("FCheckStateName", "审核中");
                } else if (subdata.getFcheckstate() == 2) {
                    subdata_object.put("FCheckStateName", "审核通过");
                } else if (subdata.getFcheckstate() == 3) {
                    subdata_object.put("FCheckStateName", "审核不通过");
                }
                subdata_object.put("FNodeID", subdata.getFnodeid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(subdata.getFnodeid().toString()));
//                TProjectFlowCell flowCell = tProjectFlowCellService.findById(subdata.getFnodeid());
//                subdata_object.put("FNodeName", flowCell == null ? "" : flowCell.getfValue() == null ? "" : HtmlTools.delHTMLTag(flowCell.getfValue()));
                subdata_object.put("FNodeName", "");


                if ((subdata.getFfilename() != null && !subdata.getFfilename().equals("")) && (subdata.getFurl() != null && !subdata.getFurl().equals(""))) {
                    subdata_object.put("isdisabled", 1);
                } else {
                    subdata_object.put("isdisabled", 0);
                }
                if (subdata.getFfiletype() != null) {
                    if (subdata.getFfiletype().toUpperCase().contains("PRT") || subdata.getFfiletype().toUpperCase().contains("STEP") || subdata.getFfiletype().toUpperCase().contains("EASM") || subdata.getFfiletype().toUpperCase().contains("SLD") || subdata.getFfiletype().toUpperCase().contains("DWG") || subdata.getFfiletype().toUpperCase().contains("DF") || subdata.getFfiletype().toUpperCase().contains("CATP")) {
                        subdata_object.put("isFFileType", 1);
                    } else {
                        subdata_object.put("isFFileType", 0);
                    }
                } else {
                    subdata_object.put("isFFileType", 0);
                }
                subdata_Array.add(subdata_object);
            }
            // 返回值
            object.put("list", subdata_Array);
            object.put("total", subdataPageInfo.getTotal()); // 总行数
            object.put("page", subdataPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取交付物信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatasubdataSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatasubdataSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray subdata_Array = new JSONArray();
            TSubdataExample subdataExample = new TSubdataExample();
            TSubdataExample.Criteria criteria = subdataExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            subdataExample.setOrderByClause("fname asc");
            List<TSubdata> subdata_list = tSubDataService.findByExample(subdataExample);
            for (TSubdata subdata : subdata_list) {
                JSONObject subdata_object = new JSONObject();
                subdata_object.put("id", ParamTools.getEnParam(subdata.getFkeyid().toString()));
                subdata_object.put("text", subdata.getFname());
                subdata_Array.add(subdata_object);
            }
            // 返回值

            object.put("list", subdata_Array);
            object.put("result", "success");
            object.put("results", subdata_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 任务-交付物(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytaskDatasubdataSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytaskDatasubdataSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        String FTaskID = request.getParameter("FTaskID");
        try {
            FTaskID = FTaskID == null ? "0" : ParamTools.getdeParam(FTaskID);
            // 获取数据库记录
            JSONArray subdata_Array = new JSONArray();
            TSubdataExample subdataExample = new TSubdataExample();
            TSubdataExample.Criteria criteria = subdataExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFtaskidEqualTo(Long.parseLong(FTaskID));
            criteria.andFstateEqualTo(0);
            criteria.andFnodeidIsNull();
            subdataExample.setOrderByClause("fname asc");
            List<TSubdata> subdata_list = tSubDataService.findByExample(subdataExample);
            for (TSubdata subdata : subdata_list) {
                JSONObject subdata_object = new JSONObject();
                subdata_object.put("id", ParamTools.getEnParam(subdata.getFkeyid().toString()));
                subdata_object.put("text", subdata.getFname());
                subdata_Array.add(subdata_object);
            }
            // 返回值

            object.put("list", subdata_Array);
            object.put("result", "success");
            object.put("results", subdata_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取交付物信息(下拉列表)--任务
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatasubdataTaskSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatasubdataTaskSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        // 获取请求参数
        String FTaskID = jsonParam.getString("FTaskID");

        try {
            FTaskID = FTaskID == null || FTaskID.equals("") ? "0" : ParamTools.getdeParam(FTaskID);
            // 获取数据库记录
            JSONArray subdata_Array = new JSONArray();
            TSubdataExample subdataExample = new TSubdataExample();
            subdataExample.or().andFtaskidEqualTo(Long.parseLong(FTaskID));
            subdataExample.setOrderByClause("fname asc");
            List<TSubdata> subdata_list = tSubDataService.findByExample(subdataExample);
            for (TSubdata subdata : subdata_list) {
                JSONObject subdata_object = new JSONObject();
                subdata_object.put("id", ParamTools.getEnParam(subdata.getFkeyid().toString()));
                subdata_object.put("text", subdata.getFname());
                subdata_Array.add(subdata_object);
            }
            // 返回值

            object.put("list", subdata_Array);
            object.put("result", "success");
            object.put("results", subdata_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


//    /**
//     * 获取工作流信息(下拉列表)
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/queryDataflowSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String queryDataflowSelect(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        String search = request.getParameter("search");
//        String FType = request.getParameter("FType");
//
//        try {
//            // 获取数据库记录
//            JSONArray tFlow_Array = new JSONArray();
//            List<Integer> typelist = new ArrayList<Integer>();
//            String[] strings = FType.split(",");
//            for (String v : strings) {
//                typelist.add(Integer.parseInt(v));
//            }
//            TFlowExample tFlowExample = new TFlowExample();
//            TFlowExample.Criteria criteria = tFlowExample.createCriteria();
//            if (search != null && !search.equals("")) {
//                criteria.andFNameLike("%" + search + "%");
//            }
//            criteria.andFStateEqualTo(1);
//            criteria.andFVersionActiveEqualTo(1);
//            criteria.andFTypeIn(typelist);
//            tFlowExample.setOrderByClause("f_name asc");
//            List<TFlow> tFlowList = tFlowService.findByExample(tFlowExample);
//            for (TFlow tFlow : tFlowList) {
//                JSONObject tFlow_object = new JSONObject();
//                tFlow_object.put("id", ParamTools.getEnParam(tFlow.getfKeyId().toString()));
//                tFlow_object.put("text", HtmlTools.delHTMLTag(tFlow.getfName()));
//                tFlow_Array.add(tFlow_object);
//            }
//            // 返回值
////            System.out.println(tFlow_Array.toString());
//            object.put("list", tFlow_Array);
//            object.put("result", "success");
//            object.put("results", tFlow_Array);
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }


    /**
     * 根据ID获取交付物信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querysubdataInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querysubdataInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询交付物信息
            TSubdata subdata = tSubDataService.findById(key);
            JSONObject subdata_object = new JSONObject();
            subdata_object.put("key", ParamTools.getEnParam(subdata.getFkeyid().toString()));
            subdata_object.put("FName", subdata.getFname() == null ? "" : subdata.getFname());
            subdata_object.put("FType", subdata.getFtype());
            switch (subdata.getFtype()) {
                case 1:
                    subdata_object.put("FTypesName", "项目计划");
                    break;
                case 2:
                    subdata_object.put("FTypesName", "项目工作流");
                    break;
                case 3:
                    subdata_object.put("FTypesName", "任务计划");
                    break;
                case 4:
                    subdata_object.put("FTypesName", "任务工作流");
                    break;
            }
            subdata_object.put("FProjectID", subdata.getFprojectid() == null ? "0" : ParamTools.getEnParam(subdata.getFprojectid().toString()));
            subdata_object.put("FProjectPlanID", subdata.getFprojectplanid() == null ? "0" : ParamTools.getEnParam(subdata.getFprojectplanid().toString()));
            subdata_object.put("FTaskID", subdata.getFtaskid() == null ? "0" : ParamTools.getEnParam(subdata.getFtaskid().toString()));
            subdata_object.put("FWorkFlowID", subdata.getFworkflowid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(subdata.getFworkflowid().toString()));
//            TFlow tFlow = tFlowService.findById(subdata.getFworkflowid());
//            subdata_object.put("FWorkFlowName", tFlow == null ? "" : tFlow.getfName() == null ? "" : HtmlTools.delHTMLTag(tFlow.getfName()));
            subdata_object.put("FWorkFlowName", "");
            if (subdata.getFcheckflowid() == null) {
                subdata_object.put("isFcheckflowid", 0);
            } else {
                subdata_object.put("isFcheckflowid", 1);
            }
            subdata_object.put("FNodeID", subdata.getFnodeid() == null ? "0" : ParamTools.getEnParam(subdata.getFnodeid().toString()));
            subdata_object.put("FTool", subdata.getFtool() == null ? "" : subdata.getFtool());
            subdata_object.put("FTypeID", subdata.getFtypeid() == null ? "0" : ParamTools.getEnParam(subdata.getFtypeid().toString()));
            TFlowCellFileType fileType = tFlowCellFileTypeService.findById(subdata.getFtypeid());
            subdata_object.put("FTypeName", fileType == null ? "" : fileType.getfName() == null ? "" : fileType.getfName());
            subdata_object.put("FFileType", subdata.getFfiletype() == null ? "" : subdata.getFfiletype());
            subdata_object.put("FCheckFlowID", subdata.getFcheckflowid() == null ? "0" : ParamTools.getEnParam(subdata.getFcheckflowid().toString()));
//            tFlow = tFlowService.findById(subdata.getFcheckflowid());
//            subdata_object.put("FCheckFlowName", tFlow == null ? "" : tFlow.getfName() == null ? "" : HtmlTools.delHTMLTag(tFlow.getfName()));
            subdata_object.put("FCheckFlowName", "");
            subdata_object.put("FNote", subdata.getFnote() == null ? "" : subdata.getFnote());
            subdata_object.put("FCID", subdata.getFcid());
            subdata_object.put("FUID", subdata.getFuid());
            subdata_object.put("FCDATE", subdata.getFcdate());
            subdata_object.put("FUDATE", subdata.getFudate());
            subdata_object.put("FState", subdata.getFstate());
            if (subdata.getFstate() == 0)
                subdata_object.put("FStateName", "未提交");
            else if (subdata.getFstate() == 1)
                subdata_object.put("FStateName", "已提交");
            // 返回值
            object.put("info", subdata_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加交付物信息-计划
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("添加计划交付物信息")
    @ResponseBody
    @RequestMapping(value = "/addsubdataplan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addsubdataplan(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FProjectID = jsonParam.getString("FProjectID");
        String FProjectPlanID = jsonParam.getString("FProjectPlanID");
        String FCheckFlowID = jsonParam.getString("FCheckFlowID");
        String FName = jsonParam.getString("FName");
        String FTypeID = jsonParam.getString("FTypeID");
        String FFileType = jsonParam.getString("FFileType");
        String FNote = jsonParam.getString("FNote");
//        System.out.println(FProjectID);
//        System.out.println(FProjectPlanID);
        try {
//            if (repeaTSubdata(0L, FName, "1") == 0) {
//
//
//            } else {
//                // 返回值
//                object.put("result", "fail");
//            }


            String CookiesLoginsubdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginsubdataID != null && !CookiesLoginsubdataID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginsubdataID);
            }
            FProjectPlanID = FProjectPlanID == null ? "0" : (FProjectPlanID.equals("0") ? "0" : ParamTools.getdeParam(FProjectPlanID));
            FCheckFlowID = FCheckFlowID == null ? "0" : (FCheckFlowID.equals("0") ? "0" : ParamTools.getdeParam(FCheckFlowID));
            FTypeID = FTypeID == null ? "0" : (FTypeID.equals("0") ? "0" : ParamTools.getdeParam(FTypeID));
            FProjectID = FProjectID == null ? "0" : (FProjectID.equals("0") ? "0" : ParamTools.getdeParam(FProjectID));
            //根据项目ID查询状态,已提交则不能添加

            TProjectPlan projectPlan = tProjectPlanService.findById(Long.parseLong(FProjectPlanID));

            if (projectPlan != null) {
                if (projectPlan.getFstate() == 0) {
//                    TSubdataExample tSubdataExample = new TSubdataExample();
//                    tSubdataExample.or().andFnameEqualTo(FName);
//                    List<TSubdata> subdataList = tSubDataService.findByExample(tSubdataExample);
//                    if (subdataList.size() == 0) {
//
//                    } else {
//                        object.put("result", "fail");
//
//                    }
                    // 新建交付物信息
                    TSubdata subdata = new TSubdata();
                    subdata.setFname(FName);
                    subdata.setFprojectid(Long.parseLong(FProjectID));
                    subdata.setFprojectplanid(Long.parseLong(FProjectPlanID));
                    if (!FCheckFlowID.equals("0"))
                        subdata.setFcheckflowid(Long.parseLong(FCheckFlowID));
                    subdata.setFtype(1);
                    subdata.setFstate(0);
                    subdata.setFtypeid(Long.parseLong(FTypeID));
                    subdata.setFfiletype(FFileType);
                    subdata.setFnote(FNote);
                    subdata.setFcid(Long.parseLong(uid));
                    subdata.setFcdate(new Date());
                    tSubDataService.save(subdata);

                    TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
                    //添加日志
                    ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                    String fnote = "为项目计划【" + projectPlan.getFname() + "】，创建交付物【" + FName + "】信息。";
                    projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "交付物信息", "创建", "", uid);
                    TUser tUser = tUserService.findById(Long.parseLong(uid));
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserId(tUser.getfKeyId());
                    logAction.setfUserName(tUser.getfName());
                    logAction.setfType(3);
                    logAction.setfPath("subdata/addsubdata{" + subdata.getFkeyid() + "}");
                    logAction.setfUserType(1);

                    logAction.setfMemo(fnote);
                    logActionService.save(logAction);
                    // 返回值
                    object.put("result", "success");

                } else {
                    object.put("result", "error");
                    object.put("error", "当前计划已开始，不能修改除数据！");
                }
            } else {
                object.put("result", "error");
                object.put("error", "未找到该计划信息，请刷新后再试！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 修改交付物信息 - 计划
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("修改计划交付物信息")
    @ResponseBody
    @RequestMapping(value = "/updatesubdataplan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatesubdataplan(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FProjectID = jsonParam.getString("FProjectID");
        String FProjectPlanID = jsonParam.getString("FProjectPlanID");
        String FCheckFlowID = jsonParam.getString("FCheckFlowID");
        String FName = jsonParam.getString("FName");
        String FTypeID = jsonParam.getString("FTypeID");
        String FFileType = jsonParam.getString("FFileType");
        String FNote = jsonParam.getString("FNote");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            String CookiesLoginsubdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginsubdataID != null && !CookiesLoginsubdataID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginsubdataID);
            }
            FProjectPlanID = FProjectPlanID == null ? "0" : ParamTools.getdeParam(FProjectPlanID);
            FCheckFlowID = FCheckFlowID == null ? "0" : ParamTools.getdeParam(FCheckFlowID);
            FTypeID = FTypeID == null ? "0" : ParamTools.getdeParam(FTypeID);
            FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);

            //根据计划ID查询状态,已提交则不能添加
            TProjectPlan projectPlan = tProjectPlanService.findById(Long.parseLong(FProjectPlanID));

            if (projectPlan != null) {
                if (projectPlan.getFstate() == 0) {
//                    TSubdataExample tSubdataExample = new TSubdataExample();
//                    tSubdataExample.or().andFnameEqualTo(FName).andFkeyidNotEqualTo(key);
//                    List<TSubdata> subdataList = tSubDataService.findByExample(tSubdataExample);
//                    if (subdataList.size() == 0) {
//
//                    } else {
//                        // 返回值
//                        object.put("result", "fail");
//                    }
                    // 新建交付物信息
                    TSubdata subdata = new TSubdata();
                    subdata.setFkeyid(key);
                    subdata.setFname(FName);
                    if (!FCheckFlowID.equals("0"))
                        subdata.setFcheckflowid(Long.parseLong(FCheckFlowID));
                    subdata.setFtypeid(Long.parseLong(FTypeID));
                    subdata.setFfiletype(FFileType);
                    subdata.setFnote(FNote);
                    subdata.setFuid(Long.parseLong(uid));
                    subdata.setFudate(new Date());
                    tSubDataService.update(subdata);

                    TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
                    //添加日志
                    ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                    String fnote = "项目计划【" + projectPlan.getFname() + "】，修改交付物【" + FName + "】信息。";
                    projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "交付物信息", "修改", "", uid);

                    TUser tUser = tUserService.findById(Long.parseLong(uid));
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserId(tUser.getfKeyId());
                    logAction.setfUserName(tUser.getfName());
                    logAction.setfType(3);
                    logAction.setfPath("subdata/updatesubdataplan{" + id + "}");
                    logAction.setfUserType(1);

                    logAction.setfMemo(fnote);
                    logActionService.save(logAction);


                    // 返回值
                    object.put("result", "success");
                } else {
                    object.put("result", "error");
                    object.put("error", "当前计划已开始，不能修改除数据！");
                }
            } else {
                object.put("result", "error");
                object.put("error", "未找到该计划信息，请刷新后再试！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 删除交付物信息 -计划
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/delsubdataplan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delsubdataplan(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String FProjectID = jsonParam.getString("FProjectID");
        try {
            String CookiesLoginsubdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginsubdataID != null && !CookiesLoginsubdataID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            FProjectID = FProjectID == null ? "0" : (FProjectID.equals("0") ? "0" : ParamTools.getdeParam(FProjectID));
            //根据项目ID查询状态,已提交则不能添加
            TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
            if (tProject != null) {
                if (tProject.getFstate() == 0) {
                    TSubdata tSubdata = tSubDataService.findById(Long.parseLong(id));

                    tSubDataService.deleteById(Long.parseLong(id));

                    //添加日志
                    ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                    String fnote = "项目【" + tProject.getFname() + "】，删除交付物【" + tSubdata.getFname() + "】信息。";
                    projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "交付物信息", "删除", "", uid);
                    TUser tUser = tUserService.findById(Long.parseLong(uid));
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserId(tUser.getfKeyId());
                    logAction.setfUserName(tUser.getfName());
                    logAction.setfType(3);
                    logAction.setfPath("subdata/delsubdataplan{" + id + "}");
                    logAction.setfUserType(1);

                    logAction.setfMemo(fnote);
                    logActionService.save(logAction);
                    // 返回值
                    object.put("result", "success");
                } else {
                    object.put("result", "error");
                    object.put("error", "当前项目已发布，不能修改除数据！");
                }
            } else {
                object.put("result", "error");
                object.put("error", "未找到该项目信息，请刷新后再试！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


//    /**
//     * 添加交付物信息-任务
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/addsubdatatask", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String addsubdatatask(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String FProjectID = jsonParam.getString("FProjectID");
//        String FTaskID = jsonParam.getString("FTaskID");
//        String FWorkFlowID = jsonParam.getString("FWorkFlowID");
//        String FName = jsonParam.getString("FName");
//        String FTypeID = jsonParam.getString("FTypeID");
//        String FFileType = jsonParam.getString("FFileType");
//        String FTool = jsonParam.getString("FTool");
//        String FNote = jsonParam.getString("FNote");
//
//        try {
//            String CookiesLoginsubdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//            String uid = ""; // 当前登录用户ID
//            if (CookiesLoginsubdataID != null && !CookiesLoginsubdataID.equals("")) {
//                uid = ParamTools.getdeParam(CookiesLoginsubdataID);
//            }
//            FTaskID = FTaskID == null ? "0" : (FTaskID.equals("0") ? "0" : ParamTools.getdeParam(FTaskID));
//            FWorkFlowID = FWorkFlowID == null ? "0" : (FWorkFlowID.equals("0") ? "0" : ParamTools.getdeParam(FWorkFlowID));
//            FTypeID = FTypeID == null ? "0" : (FTypeID.equals("0") ? "0" : ParamTools.getdeParam(FTypeID));
//            FProjectID = FProjectID == null ? "0" : (FProjectID.equals("0") ? "0" : ParamTools.getdeParam(FProjectID));
//            //根据任务ID查询状态,已提交则不能添加
//
//            TTask tTask = tTaskService.findById(Long.parseLong(FTaskID));
//
//            if (tTask != null) {
//                if (tTask.getFstate() == 0) {
//                    TTaskTeamExample tTaskTeamExample = new TTaskTeamExample();
//                    tTaskTeamExample.or().andFuseridEqualTo(Long.parseLong(uid)).andFstateEqualTo(1).andFtaskidEqualTo(Long.parseLong(FTaskID));
//                    List<TTaskTeam> teamList = tTaskTeamService.findByExample(tTaskTeamExample);
//                    if (teamList.size() <= 0) {
//                        object.put("result", "error");
//                        object.put("error", "您不是当前任务团队成员，不能操作！");
//                    } else {
//                        // 新建交付物信息
//                        TSubdata subdata = new TSubdata();
//                        subdata.setFname(FName);
//                        subdata.setFprojectid(Long.parseLong(FProjectID));
//                        subdata.setFtaskid(Long.parseLong(FTaskID));
//                        if (!FWorkFlowID.equals("0"))
//
//                            subdata.setFcheckflowid(Long.parseLong(FWorkFlowID));
//                        subdata.setFtype(3);
//                        subdata.setFstate(0);
//                        subdata.setFtool(FTool);
//                        subdata.setFtypeid(Long.parseLong(FTypeID));
//                        subdata.setFfiletype(FFileType);
//                        subdata.setFnote(FNote);
//                        subdata.setFcid(Long.parseLong(uid));
//                        subdata.setFcdate(new Date());
//                        tSubDataService.save(subdata);
//
//                        TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
//
//                        //添加日志
//
//                        TaskLogc taskLogc = new TaskLogc(tTaskLogService, tUserService);
//                        String fnote = "为任务【" + tTask.getFname() + "】，创建交付物【" + FName + "】信息。";
//                        taskLogc.addttasklog(fnote, String.valueOf(tProject.getFkeyid()), FTaskID, "交付物信息", "创建", "", uid);
//                        TUser tUser = tUserService.findById(Long.parseLong(uid));
//                        TLogAction logAction = new TLogAction();
//                        logAction.setfUserId(tUser.getfKeyId());
//                        logAction.setfUserName(tUser.getfName());
//                        logAction.setfType(3);
//                        logAction.setfPath("subdata/addsubdatatask{" + subdata.getFkeyid() + "}");
//                        logAction.setfUserType(1);
//
//                        logAction.setfMemo(fnote);
//                        logActionService.save(logAction);
//                        // 返回值
//                        object.put("result", "success");
//                    }
//                } else {
//                    object.put("result", "error");
//                    object.put("error", "当前任务已发布，不能修改除数据！");
//                }
//            } else {
//                object.put("result", "error");
//                object.put("error", "未找到该任务信息，请刷新后再试！");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }

//    /**
//     * 修改交付物信息 - 任务
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/updatesubdatatask", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String updatesubdatatask(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String id = jsonParam.getString("FKeyID");
//        String FProjectID = jsonParam.getString("FProjectID");
//        String FTaskID = jsonParam.getString("FTaskID");
//        String FWorkFlowID = jsonParam.getString("FWorkFlowID");
//        String FName = jsonParam.getString("FName");
//        String FTypeID = jsonParam.getString("FTypeID");
//        String FFileType = jsonParam.getString("FFileType");
//        String FTool = jsonParam.getString("FTool");
//        String FNote = jsonParam.getString("FNote");
//
//
//        try {
//            id = id == null ? "0" : ParamTools.getdeParam(id);
//            long key = Long.parseLong(id);
//            String CookiesLoginsubdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//            String uid = ""; // 当前登录用户ID
//            if (CookiesLoginsubdataID != null && !CookiesLoginsubdataID.equals("")) {
//                uid = ParamTools.getdeParam(CookiesLoginsubdataID);
//            }
//            FTaskID = FTaskID == null ? "0" : ParamTools.getdeParam(FTaskID);
//            FWorkFlowID = FWorkFlowID == null ? "0" : ParamTools.getdeParam(FWorkFlowID);
//            FTypeID = FTypeID == null ? "0" : ParamTools.getdeParam(FTypeID);
//            FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);
//
//            //根据任务ID查询状态,已提交则不能添加
//            TTask tTask = tTaskService.findById(Long.parseLong(FTaskID));
//
//            if (tTask != null) {
//                if (tTask.getFstate() == 0) {
//                    TTaskTeamExample tTaskTeamExample = new TTaskTeamExample();
//                    tTaskTeamExample.or().andFuseridEqualTo(Long.parseLong(uid)).andFstateEqualTo(1).andFtaskidEqualTo(Long.parseLong(FTaskID));
//                    List<TTaskTeam> teamList = tTaskTeamService.findByExample(tTaskTeamExample);
//                    if (teamList.size() <= 0) {
//                        object.put("result", "error");
//                        object.put("error", "您不是当前任务团队成员，不能操作！");
//                    } else {
//
//
//                        // 新建交付物信息
//                        TSubdata subdata = new TSubdata();
//                        subdata.setFkeyid(key);
//                        subdata.setFname(FName);
//                        if (!FWorkFlowID.equals("0"))
//                            subdata.setFcheckflowid(Long.parseLong(FWorkFlowID));
//
//                        subdata.setFtypeid(Long.parseLong(FTypeID));
//                        subdata.setFfiletype(FFileType);
//                        subdata.setFnote(FNote);
//                        subdata.setFtool(FTool);
//                        subdata.setFuid(Long.parseLong(uid));
//                        subdata.setFudate(new Date());
//                        tSubDataService.update(subdata);
//
//                        TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
//                        //添加日志
//
//                        TaskLogc taskLogc = new TaskLogc(tTaskLogService, tUserService);
//                        String fnote = "任务【" + tTask.getFname() + "】，修改交付物【" + FName + "】信息。";
//                        taskLogc.addttasklog(fnote, String.valueOf(tProject.getFkeyid()), FTaskID, "交付物信息", "修改", "", uid);
//
//                        TUser tUser = tUserService.findById(Long.parseLong(uid));
//                        TLogAction logAction = new TLogAction();
//                        logAction.setfUserId(tUser.getfKeyId());
//                        logAction.setfUserName(tUser.getfName());
//                        logAction.setfType(3);
//                        logAction.setfPath("subdata/updatesubdatatask{" + key + "}");
//                        logAction.setfUserType(1);
//
//                        logAction.setfMemo(fnote);
//                        logActionService.save(logAction);
//                        // 返回值
//                        object.put("result", "success");
//                    }
//                } else {
//                    object.put("result", "error");
//                    object.put("error", "当前任务已发布，不能修改除数据！");
//                }
//            } else {
//                object.put("result", "error");
//                object.put("error", "未找到该任务信息，请刷新后再试！");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }

//    /**
//     * 删除交付物信息 -任务
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
////    @LogOperation("删除任务交付物信息")
//    @ResponseBody
//    @RequestMapping(value = "/delsubdatatask", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String delsubdatatask(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String id = jsonParam.getString("id");
//        String FTaskID = jsonParam.getString("FTaskID");
//        String FProjectID = jsonParam.getString("FProjectID");
//        try {
//            String CookiesLoginsubdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//            String uid = ""; // 当前登录用户ID
//            if (CookiesLoginsubdataID != null && !CookiesLoginsubdataID.equals("")) {
//                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
//            }
//            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
//            FTaskID = FTaskID == null ? "0" : (FTaskID.equals("0") ? "0" : ParamTools.getdeParam(FTaskID));
//            FProjectID = FProjectID == null ? "0" : (FProjectID.equals("0") ? "0" : ParamTools.getdeParam(FProjectID));
//            //根据项目ID查询状态,已提交则不能添加
//            TTask tTask = tTaskService.findById(Long.parseLong(FTaskID));
//            if (tTask != null) {
//                if (tTask.getFstate() == 0) {
//                    TTaskTeamExample tTaskTeamExample = new TTaskTeamExample();
//                    tTaskTeamExample.or().andFuseridEqualTo(Long.parseLong(uid)).andFstateEqualTo(1).andFtaskidEqualTo(Long.parseLong(FTaskID));
//                    List<TTaskTeam> teamList = tTaskTeamService.findByExample(tTaskTeamExample);
//                    if (teamList.size() <= 0) {
//                        object.put("result", "error");
//                        object.put("error", "您不是当前任务团队成员，不能操作！");
//                    } else {
//                        TSubdata tSubdata = tSubDataService.findById(Long.parseLong(id));
//                        tSubDataService.deleteById(Long.parseLong(id));
//
//                        //添加日志
//                        TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
//                        TaskLogc taskLogc = new TaskLogc(tTaskLogService, tUserService);
//                        String fnote = "任务【" + tTask.getFname() + "】，删除交付物【" + tSubdata.getFname() + "】信息。";
//                        taskLogc.addttasklog(fnote, String.valueOf(tProject.getFkeyid()), FTaskID, "交付物信息", "删除", "", uid);
//                        TUser tUser = tUserService.findById(Long.parseLong(uid));
//                        TLogAction logAction = new TLogAction();
//                        logAction.setfUserId(tUser.getfKeyId());
//                        logAction.setfUserName(tUser.getfName());
//                        logAction.setfType(3);
//                        logAction.setfPath("subdata/delsubdatatask{" + id + "}");
//                        logAction.setfUserType(1);
//
//                        logAction.setfMemo(fnote);
//                        logActionService.save(logAction);
//                        // 返回值
//                        object.put("result", "success");
//                    }
//                } else {
//                    object.put("result", "error");
//                    object.put("error", "当前任务已发布，不能修改除数据！");
//                }
//            } else {
//                object.put("result", "error");
//                object.put("error", "未找到该任务信息，请刷新后再试！");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }

    /**
     * 变更交付物信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statesubdata", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statesubdata(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLoginsubdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginsubdataID != null && !CookiesLoginsubdataID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TSubdata subdata = new TSubdata();
            subdata.setFkeyid(Long.parseLong(id));
            subdata.setFuid(Long.parseLong(uid));
            subdata.setFudate(new Date());
            subdata.setFstate(Integer.valueOf(state));
            tSubDataService.update(subdata);
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
     * 验证交付物是否存在
     */
    private int repeaTSubdata(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TSubdataExample subdataExample = new TSubdataExample();
            TSubdataExample.Criteria criteria = subdataExample.createCriteria();
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
            List<TSubdata> rojectTypeList = tSubDataService.findByExample(subdataExample);
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


    //根据ID查询交付物名称
    public String getName(Long id) {
        TSubdata byId = tSubDataService.findById(id);
        if (byId != null) {
            return byId.getFname();
        } else {
            return "";
        }

    }


//    /**
//     * 添加交付物工作流信息-项目计划
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
////    @LogOperation("设置交付物工作流")
//    @ResponseBody
//    @RequestMapping(value = "/addsubdataplanflow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String addsubdataplanflow(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String FWorkFlowID = jsonParam.getString("FWorkFlowID");
//        String FKeyID = jsonParam.getString("FKeyID");
//        String FProjectID = jsonParam.getString("FProjectID");
////        System.out.println("FProjectID:"+FProjectID);
////        System.out.println("FKeyID:"+FKeyID);
//        try {
//            String CookiesLoginsubdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//            String uid = ""; // 当前登录用户ID
//            if (CookiesLoginsubdataID != null && !CookiesLoginsubdataID.equals("")) {
//                uid = ParamTools.getdeParam(CookiesLoginsubdataID);
//            }
//            FKeyID = FKeyID == null ? "0" : (FKeyID.equals("0") ? "0" : ParamTools.getdeParam(FKeyID));
//            FWorkFlowID = FWorkFlowID == null ? "0" : (FWorkFlowID.equals("0") ? "0" : ParamTools.getdeParam(FWorkFlowID));
//            FProjectID = FProjectID == null ? "0" : (FProjectID.equals("0") ? "0" : ParamTools.getdeParam(FProjectID));
//            //根据项目ID查询状态,已提交则不能添加
//            TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
//            if (tProject != null) {
//                if (tProject.getFstate() == 0) {
//                    // 新建交付物信息
//                    TSubdata subdata = new TSubdata();
//                    subdata.setFkeyid(Long.parseLong(FKeyID));
//                    //subdata.setFworkflowid(Long.parseLong(FWorkFlowID));
//                    subdata.setFcheckflowid(Long.parseLong(FWorkFlowID));
//                    subdata.setFuid(Long.parseLong(uid));
//                    subdata.setFudate(new Date());
//                    tSubDataService.update(subdata);
//                    TSubdata tSubdata = tSubDataService.findById(Long.parseLong(FKeyID));
//                    TFlow tFlow = tFlowService.findById(Long.parseLong(FWorkFlowID));
//                    //添加日志
//                    ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
//                    String fnote = "项目【" + tProject.getFname() + "】，为交付物【" + tSubdata.getFname() + "】设置工作流【" + HtmlTools.delHTMLTag(tFlow.getfName()) + "】。";
//                    projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "交付物信息", "设置", "", uid);
//                    TUser tUser = tUserService.findById(Long.parseLong(uid));
//                    TLogAction logAction = new TLogAction();
//                    logAction.setfUserId(tUser.getfKeyId());
//                    logAction.setfUserName(tUser.getfName());
//                    logAction.setfType(3);
//                    logAction.setfPath("subdata/addsubdataplanflow{" + FKeyID + "}");
//                    logAction.setfUserType(1);
//
//                    logAction.setfMemo(fnote);
//                    logActionService.save(logAction);
//
//                    // 返回值
//                    object.put("result", "success");
//                } else {
//                    object.put("result", "error");
//                    object.put("error", "当前项目已发布，不能修改除数据！");
//                }
//            } else {
//                object.put("result", "error");
//                object.put("error", "未找到该计划信息，请刷新后再试！");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }


    /**
     * 项目-计划信息-执行-提交交付物
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/upmyprojectplansub", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String upmyprojectplansub(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = request.getParameter("FKeyID");
        String FProjectID = request.getParameter("FProjectID");
        String planid = request.getParameter("planid");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);
            planid = planid == null ? "0" : ParamTools.getdeParam(planid);
            long key = Long.parseLong(id);
            String CookiesLoginmyprojectdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectdataID != null && !CookiesLoginmyprojectdataID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginmyprojectdataID);
            }
            TSubdata tSubdata = tSubDataService.findById(Long.parseLong(id));
            if (tSubdata == null) {
                object.put("result", "error");
                object.put("error", "交付物数据不存在，请刷新后再试！");
            } else {
                //判断计划是否是开始状态

                TProjectPlan projectPlan = tProjectPlanService.findById(Long.parseLong(planid));

                if (projectPlan.getFstate() == 1) {
                    // 判断是否存在上传文件
                    if (uploadFiles.length < 1) {
                        object.put("result", "error");
                        object.put("error", "请至少选择一个文件上传");
                    } else {
                        BufferedOutputStream stream = null;
                        for (MultipartFile file : uploadFiles) {
                            try {
                                // 获取文件名
                                String filename = file.getOriginalFilename();
//                                System.out.println("filename:" + filename);
                                if (filename == null || filename.equals("")) {
                                    object.put("result", "error");
                                    object.put("error", "请至少选择一个文件上传");
                                } else {
                                    // 获取文件的后缀名
                                    String suffixName = filename.substring(filename.lastIndexOf(".") + 1);
                                    //根据后缀名过滤上传文件
                                    boolean istg = false;
                                    TFileFilterExample tFileFilterExample = new TFileFilterExample();
                                    tFileFilterExample.createCriteria().andFstateEqualTo(1);
                                    List<TFileFilter> filterList = tFileFilterService.findByExample(tFileFilterExample);
                                    String filtername = "." + suffixName;
                                    for (TFileFilter tFileFilter : filterList) {
                                        if (filtername.equals(tFileFilter.getFname())) {
                                            istg = true;
                                            break;
                                        }
                                        istg = false;
                                    }

                                    if (istg == true) {
                                        //生成新的文件名
                                        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
                                        String realFileName = idWorker.nextId() + "." + suffixName;
                                        // 获取保存路径
                                        String savePath = FolderTools.CheckMonthFolder(path, filename, "m");
                                        // 保存文件
                                        stream = new BufferedOutputStream(new FileOutputStream(new File(savePath + realFileName)));
                                        byte[] bytes = file.getBytes();
                                        stream.write(bytes, 0, bytes.length);
                                        //先删除之前的文件，再新增新文件


                                        //获取审核流程ID
                                        TSubdata tSubdata2 = tSubDataService.findById(key);
//                                        Long subdata2Fcheckflowid = tSubdata2.getFcheckflowid() == null ? 0 : tSubdata2.getFcheckflowid();
//                                        TFlow tFlow = tFlowService.findById(subdata2Fcheckflowid);

                                        //插入数据
                                        TSubdata tSubdata1 = new TSubdata();
                                        tSubdata1.setFkeyid(key);
                                        tSubdata1.setFuid(Long.parseLong(uid));
                                        tSubdata1.setFudate(new Date());
                                        tSubdata1.setFurl(savePath + realFileName);
                                        tSubdata1.setFfilename(filename);
                                        tSubdata1.setFsysname(realFileName);
                                        tSubdata1.setFsubuserid(Long.parseLong(uid));
                                        tSubdata1.setFsubdate(new Date());
                                        tSubdata1.setFsubmode(1);
                                        tSubdata1.setFsubdatanum(1);
                                        tSubdata1.setFfiletype(suffixName);
//                                        if (tFlow != null) {
//                                            tSubdata1.setFcheckstate(1);
//                                        } else {
                                            tSubdata1.setFstate(1);
                                            tSubdata1.setFcheckstate(2);
//                                        }
//                                        tSubdata1.setFxml(tFlow == null ? "" : tFlow.getfXml());
                                        tSubdata1.setFxml("");
                                        tSubDataService.update(tSubdata1);

                                        TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
                                        //添加日志
                                        ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                                        String fnote = "项目计划【"+projectPlan.getFname()+"】上传交付物,文件名【" + filename + "】。";
                                        projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "计划信息", "上传交付物", "", uid);

                                        TUser tUser = tUserService.findById(Long.parseLong(uid));
                                        TLogAction logAction = new TLogAction();
                                        logAction.setfUserId(tUser.getfKeyId());
                                        logAction.setfUserName(tUser.getfName());
                                        logAction.setfType(3);
                                        logAction.setfPath("subdata/upmyprojectplansub{" + tSubdata1.getFkeyid() + "}");
                                        logAction.setfUserType(1);

                                        logAction.setfMemo(fnote);
                                        logActionService.save(logAction);


//                                        object.put("isqidong", tFlow == null ? 0 : 1);
                                        object.put("isqidong", 0);
                                        object.put("result", "success");
                                        object.put("path", savePath + realFileName);
                                        object.put("filename", filename);
                                        object.put("ext", suffixName);
                                    } else {
                                        object.put("result", "error");
                                        object.put("error", "请上传正确的文件格式！");
                                    }


                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                object.put("result", "error");
                                object.put("error", e.toString());
                            } finally {
                                try {
                                    if (stream != null) {
                                        stream.close();
                                    }
                                } catch (IOException e) {
                                    object.put("result", "error");
                                    object.put("error", e.toString());
                                }
                            }
                        }
                    }
                } else {
                    object.put("result", "error");
                    object.put("error", "该计划未开始或已完成，不能上传交付物！");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


//    /**
//     * 任务交付物-提交交付物
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/tasksubdatatj", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String tasksubdatatj(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String id = request.getParameter("FKeyID");
//        String FProjectID = request.getParameter("FProjectID");
//        String FTaskID = request.getParameter("FTaskID");
////        System.out.println(FProjectID);
////        System.out.println(FTaskID);
////        System.out.println(request.getParameter("executetype"));
//        int executetype = Integer.parseInt(request.getParameter("executetype"));
//
//        try {
//            id = id == null ? "0" : ParamTools.getdeParam(id);
//            FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);
//            FTaskID = FTaskID == null ? "0" : ParamTools.getdeParam(FTaskID);
//            long key = Long.parseLong(id);
//            String CookiesLogintaskdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//            String uid = ""; // 当前登录用户ID
//            if (CookiesLogintaskdataID != null && !CookiesLogintaskdataID.equals("")) {
//                uid = ParamTools.getdeParam(CookiesLogintaskdataID);
//            }
//            TSubdata tSubdata = tSubDataService.findById(Long.parseLong(id));
//            if (tSubdata == null) {
//                object.put("result", "error");
//                object.put("error", "交付物数据不存在，请刷新后再试！");
//            } else {
//
//                if (executetype == 1) {
//                    TTask tTask = tTaskService.findById(Long.parseLong(FTaskID));
//                    if (tTask.getFstate() != 1) {
//                        object.put("result", "error");
//                        object.put("error", "当前任务未开始或已完成，不能上传文件！");
//                    } else {
//                        // 判断是否存在上传文件
//                        if (uploadFiles.length < 1) {
//                            object.put("result", "error");
//                            object.put("error", "请至少选择一个文件上传");
//                        } else {
//                            BufferedOutputStream stream = null;
//                            for (MultipartFile file : uploadFiles) {
//                                try {
//                                    // 获取文件名
//                                    String filename = file.getOriginalFilename();
////                                System.out.println("filename:"+filename);
//                                    if (filename == null || filename.equals("")) {
//                                        object.put("result", "error");
//                                        object.put("error", "请至少选择一个文件上传");
//                                    } else {
//                                        // 获取文件的后缀名
//                                        String suffixName = filename.substring(filename.lastIndexOf(".") + 1);
//                                        //根据后缀名过滤上传文件
//                                        boolean istg = false;
//                                        TFileFilterExample tFileFilterExample = new TFileFilterExample();
//                                        tFileFilterExample.createCriteria().andFstateEqualTo(1);
//                                        List<TFileFilter> filterList = tFileFilterService.findByExample(tFileFilterExample);
//                                        String filtername = "." + suffixName;
//                                        for (TFileFilter tFileFilter : filterList) {
//                                            if (filtername.equals(tFileFilter.getFname())) {
//                                                istg = true;
//                                                break;
//                                            }
//                                            istg = false;
//                                        }
//                                        if (istg == true) {
//                                            //生成新的文件名
//                                            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
//                                            String realFileName = idWorker.nextId() + "." + suffixName;
//                                            // 获取保存路径
//                                            String savePath = FolderTools.CheckMonthFolder(subdatapath, filename, "m");
//                                            // 保存文件
//                                            stream = new BufferedOutputStream(new FileOutputStream(new File(savePath + realFileName)));
//                                            byte[] bytes = file.getBytes();
//                                            stream.write(bytes, 0, bytes.length);
//                                            //先删除之前的文件，再新增新文件
//
//
//                                            //获取审核流程ID
//                                            TSubdata tSubdata2 = tSubDataService.findById(key);
//                                            Long subdata2Fcheckflowid = tSubdata2.getFcheckflowid() == null ? 0 : tSubdata2.getFcheckflowid();
//                                            TFlow tFlow = tFlowService.findById(subdata2Fcheckflowid);
//
//                                            //插入数据
//                                            TSubdata tSubdata1 = new TSubdata();
//                                            tSubdata1.setFkeyid(key);
//                                            tSubdata1.setFuid(Long.parseLong(uid));
//                                            tSubdata1.setFudate(new Date());
//                                            tSubdata1.setFurl(savePath + realFileName);
//                                            tSubdata1.setFfilename(filename);
//                                            tSubdata1.setFsysname(realFileName);
//                                            tSubdata1.setFsubuserid(Long.parseLong(uid));
//                                            tSubdata1.setFsubdate(new Date());
//                                            tSubdata1.setFsubmode(1);
//                                            tSubdata1.setFsubdatanum(1);
//                                            tSubdata1.setFfiletype(suffixName);
//                                            if (tFlow != null) {
//                                                tSubdata1.setFstate(1);
//                                                tSubdata1.setFcheckstate(1);
//                                            } else {
//                                                tSubdata1.setFstate(1);
//                                                tSubdata1.setFcheckstate(2);
//                                            }
//                                            tSubdata1.setFxml(tFlow == null ? "" : tFlow.getfXml());
//                                            tSubDataService.update(tSubdata1);
//
//
//                                            //添加日志
//                                            TaskLogc taskLog = new TaskLogc(tTaskLogService, tUserService);
//                                            String fnote = "提交交付物【" + filename + "】。";
//                                            taskLog.addttasklog(fnote, FProjectID, FTaskID, "交付物", "提交交付物", "", uid);
//
//                                            TUser tUser = tUserService.findById(Long.parseLong(uid));
//                                            TLogAction logAction = new TLogAction();
//                                            logAction.setfUserId(tUser.getfKeyId());
//                                            logAction.setfUserName(tUser.getfName());
//                                            logAction.setfType(3);
//                                            logAction.setfPath("subdata/tasksubdatatj{" + tSubdata1.getFkeyid() + "}");
//                                            logAction.setfUserType(1);
//
//                                            logAction.setfMemo(fnote);
//                                            logActionService.save(logAction);
//
//
//                                            object.put("fcheckflowid", ParamTools.getEnParam(String.valueOf(key)));
//                                            object.put("isqidong", tFlow == null ? 0 : 1);
//                                            object.put("result", "success");
//                                            object.put("path", savePath + realFileName);
//                                            object.put("filename", filename);
//                                            object.put("ext", suffixName);
//                                        } else {
//                                            object.put("result", "error");
//                                            object.put("error", "请上传正确的文件格式！");
//                                        }
//                                    }
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                    object.put("result", "error");
//                                    object.put("error", e.toString());
//                                } finally {
//                                    try {
//                                        if (stream != null) {
//                                            stream.close();
//                                        }
//                                    } catch (IOException e) {
//                                        object.put("result", "error");
//                                        object.put("error", e.toString());
//                                    }
//                                }
//                            }
//                        }
//                    }
//                } else if (executetype == 2) {
//
//
//                    //获取审核流程ID
//                    TSubdata tSubdata2 = tSubDataService.findById(key);
//                    TProjectFlowCell flowCell = tProjectFlowCellService.findById(tSubdata2.getFnodeid());
//                    if (flowCell.getfState() == 1) {
//                        BufferedOutputStream stream = null;
//                        for (MultipartFile file : uploadFiles) {
//                            try {
//                                // 获取文件名
//                                String filename = file.getOriginalFilename();
////                                System.out.println("filename:"+filename);
//                                if (filename == null || filename.equals("")) {
//                                    object.put("result", "error");
//                                    object.put("error", "请至少选择一个文件上传");
//                                } else {
//                                    // 获取文件的后缀名
//                                    String suffixName = filename.substring(filename.lastIndexOf(".") + 1);
//
//                                    //根据后缀名过滤上传文件
//                                    boolean istg = false;
//                                    TFileFilterExample tFileFilterExample = new TFileFilterExample();
//                                    tFileFilterExample.createCriteria().andFstateEqualTo(1);
//                                    List<TFileFilter> filterList = tFileFilterService.findByExample(tFileFilterExample);
//                                    String filtername = "." + suffixName;
//                                    for (TFileFilter tFileFilter : filterList) {
//                                        if (filtername.equals(tFileFilter.getFname())) {
//                                            istg = true;
//                                            break;
//                                        }
//                                        istg = false;
//                                    }
//                                    if (istg == true) {
//                                        //生成新的文件名
//                                        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
//                                        String realFileName = idWorker.nextId() + "." + suffixName;
//                                        // 获取保存路径
//                                        String savePath = FolderTools.CheckMonthFolder(flowpath, filename, "m");
//                                        // 保存文件
//                                        stream = new BufferedOutputStream(new FileOutputStream(new File(savePath + realFileName)));
//                                        byte[] bytes = file.getBytes();
//                                        stream.write(bytes, 0, bytes.length);
//                                        //先删除之前的文件，再新增新文件
//
//
//                                        Long subdata2Fcheckflowid = tSubdata2.getFcheckflowid() == null ? 0 : tSubdata2.getFcheckflowid();
//                                        TFlow tFlow = tFlowService.findById(subdata2Fcheckflowid);
//
//                                        //插入数据
//                                        TSubdata tSubdata1 = new TSubdata();
//                                        tSubdata1.setFkeyid(key);
//                                        tSubdata1.setFuid(Long.parseLong(uid));
//                                        tSubdata1.setFudate(new Date());
//                                        tSubdata1.setFurl(savePath + realFileName);
//                                        tSubdata1.setFfilename(filename);
//                                        tSubdata1.setFsysname(realFileName);
//                                        tSubdata1.setFsubuserid(Long.parseLong(uid));
//                                        tSubdata1.setFsubdate(new Date());
//                                        tSubdata1.setFsubmode(2);
//                                        tSubdata1.setFsubdatanum(1);
//                                        tSubdata1.setFfiletype(suffixName);
//                                        if (tFlow != null) {
//                                            tSubdata1.setFstate(1);
//                                            tSubdata1.setFcheckstate(1);
//                                        } else {
//                                            tSubdata1.setFstate(1);
//                                            tSubdata1.setFcheckstate(2);
//                                        }
//                                        tSubdata1.setFxml(tFlow == null ? "" : tFlow.getfXml());
//                                        tSubDataService.update(tSubdata1);
//                                        object.put("fcheckflowid", ParamTools.getEnParam(String.valueOf(key)));
//                                        object.put("isqidong", tFlow == null ? 0 : 1);
//                                        object.put("result", "success");
//                                        object.put("path", savePath + realFileName);
//                                        object.put("filename", filename);
//                                        object.put("ext", suffixName);
//                                    } else {
//                                        object.put("result", "error");
//                                        object.put("error", "请上传正确的文件格式！");
//                                    }
//
//
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                object.put("result", "error");
//                                object.put("error", e.toString());
//                            } finally {
//                                try {
//                                    if (stream != null) {
//                                        stream.close();
//                                    }
//                                } catch (IOException e) {
//                                    object.put("result", "error");
//                                    object.put("error", e.toString());
//                                }
//                            }
//                        }
//                    } else {
//                        object.put("result", "error");
//                        object.put("error", "该节点数据已流转，请刷新后重试！");
//                    }
//
//                } else {
//                    object.put("result", "error");
//                    object.put("error", "数据获取失败，请刷新后重试!");
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }


//    /**
//     * 重新提交交付物
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
////    @LogOperation("重新提交交付物")
//    @ResponseBody
//    @RequestMapping(value = "/mytasksubdatajfreup", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String mytasksubdatajfreup(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        String id = request.getParameter("FKeyID");
//        int executetype = Integer.parseInt(request.getParameter("executetype"));
//        String FProjectID = request.getParameter("FProjectID");
//        String FTaskID = request.getParameter("FTaskID");
//        try {
//            id = id == null ? "0" : ParamTools.getdeParam(id);
//            FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);
//            FTaskID = FTaskID == null ? "0" : ParamTools.getdeParam(FTaskID);
//            long key = Long.parseLong(id);
//            String CookiesLogintaskdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//            String uid = ""; // 当前登录用户ID
//            if (CookiesLogintaskdataID != null && !CookiesLogintaskdataID.equals("")) {
//                uid = ParamTools.getdeParam(CookiesLogintaskdataID);
//            }
//            TSubdata tSubdata = tSubDataService.findById(Long.parseLong(id));
//            if (tSubdata == null) {
//                object.put("result", "error");
//                object.put("error", "交付物数据不存在，请刷新后再试！");
//            } else {
//                if (executetype == 1) {
//                    TTask tTask = tTaskService.findById(Long.parseLong(FTaskID));
//                    if (tTask.getFstate() != 1) {
//                        object.put("result", "error");
//                        object.put("error", "当前任务未开始或已完成，不能上传文件！");
//                    } else {
//                        // 判断是否存在上传文件
//                        if (uploadFiles.length < 1) {
//                            object.put("result", "error");
//                            object.put("error", "请至少选择一个文件上传");
//                        } else {
//                            BufferedOutputStream stream = null;
//                            for (MultipartFile file : uploadFiles) {
//                                try {
//                                    // 获取文件名
//                                    String filename = file.getOriginalFilename();
////                                System.out.println("filename:"+filename);
//                                    if (filename == null || filename.equals("")) {
//                                        object.put("result", "error");
//                                        object.put("error", "请至少选择一个文件上传");
//                                    } else {
//                                        // 获取文件的后缀名
//                                        String suffixName = filename.substring(filename.lastIndexOf(".") + 1);
//                                        //根据后缀名过滤上传文件
//                                        boolean istg = false;
//                                        TFileFilterExample tFileFilterExample = new TFileFilterExample();
//                                        tFileFilterExample.createCriteria().andFstateEqualTo(1);
//                                        List<TFileFilter> filterList = tFileFilterService.findByExample(tFileFilterExample);
//                                        String filtername = "." + suffixName;
//                                        for (TFileFilter tFileFilter : filterList) {
//                                            if (filtername.equals(tFileFilter.getFname())) {
//                                                istg = true;
//                                                break;
//                                            }
//                                            istg = false;
//                                        }
//                                        if (istg == true) {
//                                            //生成新的文件名
//                                            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
//                                            String realFileName = idWorker.nextId() + "." + suffixName;
//                                            // 获取保存路径
//                                            String savePath = FolderTools.CheckMonthFolder(subdatapath, filename, "m");
//                                            // 保存文件
//                                            stream = new BufferedOutputStream(new FileOutputStream(new File(savePath + realFileName)));
//                                            byte[] bytes = file.getBytes();
//                                            stream.write(bytes, 0, bytes.length);
//                                            //先删除之前的文件，再新增新文件
//
//
//                                            //获取审核流程ID
//                                            TSubdata tSubdata2 = tSubDataService.findById(key);
//                                            Long subdata2Fcheckflowid = tSubdata2.getFcheckflowid() == null ? 0 : tSubdata2.getFcheckflowid();
//                                            TFlow tFlow = tFlowService.findById(subdata2Fcheckflowid);
//
//                                            //插入数据
//                                            TSubdata tSubdata1 = new TSubdata();
//                                            tSubdata1.setFkeyid(key);
//                                            tSubdata1.setFuid(Long.parseLong(uid));
//                                            tSubdata1.setFudate(new Date());
//                                            tSubdata1.setFurl(savePath + realFileName);
//                                            tSubdata1.setFfilename(filename);
//                                            tSubdata1.setFsysname(realFileName);
//                                            tSubdata1.setFfiletype(suffixName);
//                                            tSubDataService.update(tSubdata1);
//
//                                            //添加日志
//                                            TaskLogc taskLog = new TaskLogc(tTaskLogService, tUserService);
//                                            String fnote = "重新提交交付物【" + filename + "】。";
//                                            taskLog.addttasklog(fnote, FProjectID, FTaskID, "交付物", "重新提交交付物", "", uid);
//
//                                            TUser tUser = tUserService.findById(Long.parseLong(uid));
//                                            TLogAction logAction = new TLogAction();
//                                            logAction.setfUserId(tUser.getfKeyId());
//                                            logAction.setfUserName(tUser.getfName());
//                                            logAction.setfType(3);
//                                            logAction.setfPath("subdata/mytasksubdatajfreup{" + tSubdata1.getFkeyid() + "}");
//                                            logAction.setfUserType(1);
//
//                                            logAction.setfMemo(fnote);
//                                            logActionService.save(logAction);
//
//
//                                            object.put("fcheckflowid", ParamTools.getEnParam(String.valueOf(key)));
//                                            object.put("isqidong", tFlow == null ? 0 : 1);
//                                            object.put("result", "success");
//                                            object.put("path", savePath + realFileName);
//                                            object.put("filename", filename);
//                                            object.put("ext", suffixName);
//                                        } else {
//                                            object.put("result", "error");
//                                            object.put("error", "请上传正确的文件格式！");
//                                        }
//
//                                    }
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                    object.put("result", "error");
//                                    object.put("error", e.toString());
//                                } finally {
//                                    try {
//                                        if (stream != null) {
//                                            stream.close();
//                                        }
//                                    } catch (IOException e) {
//                                        object.put("result", "error");
//                                        object.put("error", e.toString());
//                                    }
//                                }
//                            }
//                        }
//                    }
//                } else if (executetype == 2) {
//                    //获取审核流程ID
//                    TSubdata tSubdata2 = tSubDataService.findById(key);
//                    TProjectFlowCell flowCell = tProjectFlowCellService.findById(tSubdata2.getFnodeid());
//                    if (flowCell.getfState() == 1) {
//                        BufferedOutputStream stream = null;
//                        for (MultipartFile file : uploadFiles) {
//                            try {
//                                // 获取文件名
//                                String filename = file.getOriginalFilename();
////                                System.out.println("filename:"+filename);
//                                if (filename == null || filename.equals("")) {
//                                    object.put("result", "error");
//                                    object.put("error", "请至少选择一个文件上传");
//                                } else {
//                                    // 获取文件的后缀名
//                                    String suffixName = filename.substring(filename.lastIndexOf(".") + 1);
//                                    //根据后缀名过滤上传文件
//                                    boolean istg = false;
//                                    TFileFilterExample tFileFilterExample = new TFileFilterExample();
//                                    tFileFilterExample.createCriteria().andFstateEqualTo(1);
//                                    List<TFileFilter> filterList = tFileFilterService.findByExample(tFileFilterExample);
//                                    String filtername = "." + suffixName;
//                                    for (TFileFilter tFileFilter : filterList) {
//                                        if (filtername.equals(tFileFilter.getFname())) {
//                                            istg = true;
//                                            break;
//                                        }
//                                        istg = false;
//                                    }
//                                    if (istg == true) {
//                                        //生成新的文件名
//                                        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
//                                        String realFileName = idWorker.nextId() + "." + suffixName;
//                                        // 获取保存路径
//                                        String savePath = FolderTools.CheckMonthFolder(flowpath, filename, "m");
//                                        // 保存文件
//                                        stream = new BufferedOutputStream(new FileOutputStream(new File(savePath + realFileName)));
//                                        byte[] bytes = file.getBytes();
//                                        stream.write(bytes, 0, bytes.length);
//                                        //先删除之前的文件，再新增新文件
//
//
//                                        Long subdata2Fcheckflowid = tSubdata2.getFcheckflowid() == null ? 0 : tSubdata2.getFcheckflowid();
//                                        TFlow tFlow = tFlowService.findById(subdata2Fcheckflowid);
//
//                                        //插入数据
//                                        TSubdata tSubdata1 = new TSubdata();
//                                        tSubdata1.setFkeyid(key);
//                                        tSubdata1.setFuid(Long.parseLong(uid));
//                                        tSubdata1.setFudate(new Date());
//                                        tSubdata1.setFurl(savePath + realFileName);
//                                        tSubdata1.setFfilename(filename);
//                                        tSubdata1.setFsysname(realFileName);
//                                        tSubdata1.setFfiletype(suffixName);
//                                        tSubDataService.update(tSubdata1);
//
//                                        object.put("isqidong", tFlow == null ? 0 : 1);
//                                        object.put("result", "success");
//                                        object.put("path", savePath + realFileName);
//                                        object.put("filename", filename);
//                                        object.put("ext", suffixName);
//                                    } else {
//                                        object.put("result", "error");
//                                        object.put("error", "请上传正确的文件格式！");
//                                    }
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                object.put("result", "error");
//                                object.put("error", e.toString());
//                            } finally {
//                                try {
//                                    if (stream != null) {
//                                        stream.close();
//                                    }
//                                } catch (IOException e) {
//                                    object.put("result", "error");
//                                    object.put("error", e.toString());
//                                }
//                            }
//                        }
//                    } else {
//                        object.put("result", "error");
//                        object.put("error", "该节点数据已流转，请刷新后重试！");
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return object.toString();
//    }


//    /**
//     * 获取任务 交付物 文件url
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/getmytaskSubdataUrl", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String getmytaskSubdataUrl(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//
//        String id = jsonParam.getString("id");//交付物ID
//        String ip = jsonParam.getString("ip");
//        int ftype = jsonParam.getInteger("ftype");
//        String FProjectID = jsonParam.getString("FProjectID");
//        String FTaskID = jsonParam.getString("FTaskID");
//        int classifytype = jsonParam.getInteger("classifytype");
//        int executetype = jsonParam.getInteger("executetype");
//        try {
//            // 查询条件
//            id = id == null ? "0" : ParamTools.getdeParam(id);
//            FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);
//            FTaskID = FTaskID == null ? "0" : ParamTools.getdeParam(FTaskID);
//
//
//            TSubdata tSubdata = tSubDataService.findById(Long.parseLong(id));
//            String url = "";
//            String filename = "";
//            TTaskData tTaskData = null;
//
//            TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
//            String CookiesLogintaskdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//            String uid = ""; // 当前登录用户ID
//            if (CookiesLogintaskdataID != null && !CookiesLogintaskdataID.equals("")) {
//                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
//            }
//            if (tSubdata != null) {
//                filename = tSubdata.getFfilename();
//                tTaskData = new TTaskData();
//                tTaskData.setFkeyid(Long.parseLong(id));
//                if (tSubdata.getFsubmode() == 2 || tSubdata.getFsubmode() == 3) {//工作流
//                    if (ftype == 1) {
////                        if (tSubdata.getFurl().contains("TaskSubData")) {
//////                            url = tSubdata.getFurl().replace(subdatapath.substring(0, subdatapath.length() - 2), ip).replace("//", "/");//本机测试
////                            url = tSubdata.getFurl().replace(subdatapath.replace("//", "/").substring(0, subdatapath.length() - 1), ip).replace("//", "/");//服务器
////                            url = url.replace("http:/", "http://");
////                        } else if (tSubdata.getFurl().contains("Flow")) {
//////                            url = tSubdata.getFurl().replace(flowpath.substring(0, flowpath.length() - 2), ip).replace("//", "/");//本机测试
////                            url = tSubdata.getFurl().replace(flowpath.replace("//", "/").substring(0, flowpath.length() - 1), ip).replace("//", "/");//服务器
////                            url = url.replace("http:/", "http://");
////                        } else if (tSubdata.getFurl().contains("PlanSub")) {
//////                            url = tSubdata.getFurl().replace(path.substring(0, path.length() - 2), ip).replace("//", "/");//本机测试
////                            url = tSubdata.getFurl().replace(path.replace("//", "/").substring(0, flowpath.length() - 1), ip).replace("//", "/");//服务器
////                            url = url.replace("http:/", "http://");
////                        } else if (tSubdata.getFurl().contains("attachment")) {
//////                            url = tSubdata.getFurl().replace(path.substring(0, path.length() - 2), ip).replace("//", "/");//本机测试
////                            url = tSubdata.getFurl().replace(flowpath.replace("//", "/").substring(0, flowpath.length() - 1), ip).replace("//", "/");//服务器
////                            url = url.replace("http:/", "http://");
////                        }
//
//                        url = FileToUrlTools.convertPathToUrl(ip,tSubdata.getFurl());
//
//                        if (executetype == 1 && classifytype != 2) {
//                            //添加日志
//                            TaskLogc taskLog = new TaskLogc(tTaskLogService, tUserService);
//                            String fnote = "预览交付物【" + tSubdata.getFname() + "】";
//                            taskLog.addttasklog(fnote, FProjectID, FTaskID, "交付物", "预览", "", uid);
//                        }
//                    } else if (ftype == 2) {
////                        if (tSubdata.getFurl().contains("TaskSubData")) {
////                            url = tSubdata.getFurl().replace(subdatapath.replace("//", "/").substring(0, subdatapath.length() - 1), "").replace("//", "/");//本机测试
////                            //url = taskData.getFurl().replace(subdatapath.substring(0,subdatapath.length()-2),"").replace("//","/");//服务器
////                        } else if (tSubdata.getFurl().contains("Flow")) {
////                            url = tSubdata.getFurl().replace(flowpath.replace("//", "/").substring(0, flowpath.length() - 1), "").replace("//", "/");//本机测试
////                            //url = taskData.getFurl().replace(flowpath.substring(0,flowpath.length()-2),"").replace("//","/");//服务器
////                        } else if (tSubdata.getFurl().contains("PlanSub")) {
////                            url = tSubdata.getFurl().replace(path.replace("//", "/").substring(0, path.length() - 1), "").replace("//", "/");//本机测试
////                            //url = taskData.getFurl().replace(path.substring(0,path.length()-2),"").replace("//","/");//服务器
////                        } else if (tSubdata.getFurl().contains("attachment")) {
////                            url = tSubdata.getFurl().replace(flowpath.replace("//", "/").substring(0, flowpath.length() - 1), "").replace("//", "/");//本机测试
////                            //url = taskData.getFurl().replace(path.substring(0,path.length()-2),"").replace("//","/");//服务器
////                        }
//                        url = FileToUrlTools.convertPathToUrl(ip,tSubdata.getFurl());
//
//
//                        if (executetype == 1 && classifytype != 2) {
//                            //添加日志
//                            TaskLogc taskLog = new TaskLogc(tTaskLogService, tUserService);
//                            String fnote = "下载交付物【" + tSubdata.getFname() + "】";
//                            taskLog.addttasklog(fnote, FProjectID, FTaskID, "交付物", "下载", "", uid);
//                        }
//                    }
//                } else {
//                    if (ftype == 1) {
////                        if (tSubdata.getFurl().contains("TaskSubData")) {
////                            url = tSubdata.getFurl().replace(subdatapath.replace("//", "/").substring(0, subdatapath.length() - 1), ip).replace("//", "/");//本机测试
////                            url = url.replace("http:/", "http://");
////                            //url = tSubdata.getFurl().replace(subdatapath.substring(0,subdatapath.length()-2),ip).replace("//","/");//服务器
////                        } else if (tSubdata.getFurl().contains("Flow")) {
////                            url = tSubdata.getFurl().replace(flowpath.replace("//", "/").substring(0, flowpath.length() - 1), ip).replace("//", "/");//本机测试
////                            url = url.replace("http:/", "http://");
////                            //url = tSubdata.getFurl().replace(flowpath.substring(0,flowpath.length()-2),ip).replace("//","/");//服务器
////                        } else if (tSubdata.getFurl().contains("PlanSub")) {
////                            url = tSubdata.getFurl().replace(path.replace("//", "/").substring(0, path.length() - 1), ip).replace("//", "/");//本机测试
////                            url = url.replace("http:/", "http://");
////                            //url = tSubdata.getFurl().replace(path.substring(0,path.length()-2),ip).replace("//","/");//服务器
////                        } else if (tSubdata.getFurl().contains("attachment")) {
////                            url = tSubdata.getFurl().replace(flowpath.replace("//", "/").substring(0, flowpath.length() - 1), ip).replace("//", "/");//本机测试
////                            url = url.replace("http:/", "http://");
////                            //url = tSubdata.getFurl().replace(path.substring(0,path.length()-2),ip).replace("//","/");//服务器
////                        }
//                        url = FileToUrlTools.convertPathToUrl(ip,tSubdata.getFurl());
//
//
//                        if (executetype == 1 && classifytype != 2) {
//                            //添加日志
//                            TaskLogc taskLog = new TaskLogc(tTaskLogService, tUserService);
//                            String fnote = "预览交付物【" + tSubdata.getFname() + "】";
//                            taskLog.addttasklog(fnote, FProjectID, FTaskID, "交付物", "预览", "", uid);
//                        }
//                    } else {
////                        if (tSubdata.getFurl().contains("TaskSubData")) {
////                            url = tSubdata.getFurl().replace(subdatapath.replace("//", "/").substring(0, subdatapath.length() - 1), "").replace("//", "/");//本机测试
////                            //url = taskData.getFurl().replace(subdatapath.substring(0,subdatapath.length()-2),"").replace("//","/");//服务器
////                        } else if (tSubdata.getFurl().contains("Flow")) {
////                            url = tSubdata.getFurl().replace(flowpath.replace("//", "/").substring(0, flowpath.length() - 1), "").replace("//", "/");//本机测试
////                            //url = taskData.getFurl().replace(flowpath.substring(0,flowpath.length()-2),"").replace("//","/");//服务器
////                        } else if (tSubdata.getFurl().contains("PlanSub")) {
////                            url = tSubdata.getFurl().replace(path.replace("//", "/").substring(0, path.length() - 1), "").replace("//", "/");//本机测试
////                            //url = taskData.getFurl().replace(path.substring(0,path.length()-2),"").replace("//","/");//服务器
////                        } else if (tSubdata.getFurl().contains("attachment")) {
////                            url = tSubdata.getFurl().replace(flowpath.replace("//", "/").substring(0, flowpath.length() - 1), "").replace("//", "/");//本机测试
////                            //url = taskData.getFurl().replace(path.substring(0,path.length()-2),"").replace("//","/");//服务器
////                        }
//                        url = FileToUrlTools.convertPathToUrl(ip,tSubdata.getFurl());
//
//
//                        if (executetype == 1 && classifytype != 2) {
//                            //添加日志
//                            TaskLogc taskLog = new TaskLogc(tTaskLogService, tUserService);
//                            String fnote = "下载交付物【" + tSubdata.getFname() + "】";
//                            taskLog.addttasklog(fnote, FProjectID, FTaskID, "交付物", "下载", "", uid);
//                        }
//                    }
//
//                }
//
//            }
//            // 返回值
//            object.put("url", url);
//            object.put("filename", filename);
//            object.put("result", "success");
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }


    /**
     * 提交其他交付物
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("添加其他交付物信息")
    @ResponseBody
    @RequestMapping(value = "/mytasksubdataqtjf", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String mytasksubdataqtjf(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FTypeID = request.getParameter("FTypeID");
        String FProjectID = request.getParameter("FProjectID");
        String FTaskID = request.getParameter("FTaskID");
        int executetype = Integer.parseInt(request.getParameter("executetype"));
        String FName = request.getParameter("FName");
        String FTool = request.getParameter("FTool");
        String FWorkFlowID = request.getParameter("FWorkFlowID");
        String FNote = request.getParameter("FNote");
        String FNodeID = request.getParameter("FNodeID");
        int ftype = Integer.parseInt(request.getParameter("ftype"));
        String FProjectPlanID = "0";// request.getParameter("FProjectPlanID");
        System.out.println("ftype:" +ftype);
        try {
            FTypeID = FTypeID == null ? "0" : ParamTools.getdeParam(FTypeID);
            FWorkFlowID = FWorkFlowID == null ? "0" : ParamTools.getdeParam(FWorkFlowID);
            FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);
            FTaskID = FTaskID == null ? "0" : ParamTools.getdeParam(FTaskID);
            if (executetype == 2)
                FNodeID = FNodeID == null ? "0" : FNodeID.equals("0") ? "0" : ParamTools.getdeParam(FNodeID);
            if (request.getParameter("FProjectPlanID") != null) {
                FProjectPlanID = request.getParameter("FProjectPlanID");
                FProjectPlanID = FProjectPlanID == null ? "0" : FProjectPlanID.equals("0") ? "0" : ParamTools.getdeParam(FProjectPlanID);
            } else {
                FProjectPlanID = "0";
            }
            String CookiesLoginmyprojectdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectdataID != null && !CookiesLoginmyprojectdataID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginmyprojectdataID);
            }
            SnowflakeIdWorker idWorker2 = new SnowflakeIdWorker(1, 1);


            BufferedOutputStream stream = null;
            for (MultipartFile file : uploadFiles) {
                try {
                    // 获取文件名
                    String filename = file.getOriginalFilename();
//                    System.out.println("filename:" + filename);
                    if (filename == null || filename.equals("")) {
                        object.put("result", "error");
                        object.put("error", "请至少选择一个文件上传");
                    } else {
                        // 获取文件的后缀名
                        String suffixName = filename.substring(filename.lastIndexOf(".") + 1);
                        //根据后缀名过滤上传文件
                        boolean istg = false;
                        TFileFilterExample tFileFilterExample = new TFileFilterExample();
                        tFileFilterExample.createCriteria().andFstateEqualTo(1);
                        List<TFileFilter> filterList = tFileFilterService.findByExample(tFileFilterExample);
                        String filtername = "." + suffixName;
                        for (TFileFilter tFileFilter : filterList) {
                            if (filtername.equals(tFileFilter.getFname())) {
                                istg = true;
                                break;
                            }
                            istg = false;
                        }
                        if (istg == true) {
                            long key = idWorker2.nextId();

                            // 新建交付物信息
                            TSubdata subdata = new TSubdata();
                            subdata.setFkeyid(key);
                            subdata.setFname(FName);
                            subdata.setFprojectid(Long.parseLong(FProjectID));
//                        if (ftype != 1 && ftype != 2) {
                            if (executetype == 1) {
                                subdata.setFtaskid(Long.parseLong(FTaskID));
                            } else {
                                if (ftype != 1 && ftype != 2) {
                                    if(ftype != 6 && ftype != 5 && ftype != 7)  {
                                        subdata.setFtaskid(Long.parseLong(FTaskID));
                                    }
                                }
                                subdata.setFnodeid(Long.parseLong(FNodeID));
                            }
//                        }
                            if (ftype == 1) {
                                subdata.setFprojectplanid(Long.parseLong(FProjectPlanID));
                            }

                            if (!FWorkFlowID.equals("0")) {
                                subdata.setFcheckflowid(Long.parseLong(FWorkFlowID));
                                subdata.setFstate(1);
                                subdata.setFcheckstate(0);
                            } else {
                                subdata.setFstate(1);
                                subdata.setFcheckstate(2);
                            }
                            subdata.setFtype(ftype);
                            subdata.setFtypeid(Long.parseLong(FTypeID));
                            subdata.setFhavetype(0);
                            subdata.setFnote(FNote);
                            subdata.setFcid(Long.parseLong(uid));
                            subdata.setFcdate(new Date());
                            tSubDataService.save(subdata);


                            //生成新的文件名
                            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
                            String realFileName = idWorker.nextId() + "." + suffixName;
                            // 获取保存路径
                            String savePath = FolderTools.CheckMonthFolder(subdatapath, filename, "m");
                            // 保存文件
                            stream = new BufferedOutputStream(new FileOutputStream(new File(savePath + realFileName)));
                            byte[] bytes = file.getBytes();
                            stream.write(bytes, 0, bytes.length);


                            //获取审核流程ID
                            TSubdata tSubdata2 = tSubDataService.findById(key);
//                            Long subdata2Fcheckflowid = tSubdata2.getFcheckflowid() == null ? 0 : tSubdata2.getFcheckflowid();
//                            TFlow tFlow = tFlowService.findById(subdata2Fcheckflowid);

                            //插入数据
                            TSubdata tSubdata1 = new TSubdata();
                            tSubdata1.setFkeyid(key);
                            tSubdata1.setFuid(Long.parseLong(uid));
                            tSubdata1.setFudate(new Date());
                            tSubdata1.setFurl(savePath + realFileName);
                            tSubdata1.setFfilename(filename);
                            tSubdata1.setFsysname(realFileName);
                            tSubdata1.setFsubuserid(Long.parseLong(uid));
                            tSubdata1.setFsubdate(new Date());
                            tSubdata1.setFsubmode(1);
                            tSubdata1.setFsubdatanum(1);
                            tSubdata1.setFfiletype(suffixName);
//                            if (tFlow != null) {
//                                tSubdata1.setFstate(1);
//                                tSubdata1.setFcheckstate(0);
//                            } else {
                                tSubdata1.setFstate(1);
                                tSubdata1.setFcheckstate(2);
//                            }
//                            tSubdata1.setFxml(tFlow == null ? "" : tFlow.getfXml());
                            tSubdata1.setFxml("");
                            tSubDataService.update(tSubdata1);

                            TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
                            //添加日志
                            ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                            String fnote = "上传其他交付物,文件名【" + filename + "】。";
                            projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "计划信息", "上传交付物", "", uid);

                            TUser tUser = tUserService.findById(Long.parseLong(uid));
                            TLogAction logAction = new TLogAction();
                            logAction.setfUserId(tUser.getfKeyId());
                            logAction.setfUserName(tUser.getfName());
                            logAction.setfType(3);
                            logAction.setfPath("subdata/mytasksubdataqtjf{" + tSubdata1.getFkeyid() + "}");
                            logAction.setfUserType(1);

                            logAction.setfMemo(fnote);
                            logActionService.save(logAction);


                            object.put("fcheckflowiddddd", key);
                            object.put("fcheckflowid", ParamTools.getEnParam(String.valueOf(key)));
//                            object.put("isqidong", tFlow == null ? 0 : 1);
                            object.put("isqidong", 0);
                            object.put("result", "success");
                            object.put("path", savePath + realFileName);
                            object.put("filename", filename);
                            object.put("ext", suffixName);
                        } else {
                            object.put("result", "error");
                            object.put("error", "请上传正确的文件格式！");
                        }


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    object.put("result", "error");
                    object.put("error", e.toString());
                } finally {
                    try {
                        if (stream != null) {
                            stream.close();
                        }
                    } catch (IOException e) {
                        object.put("result", "error");
                        object.put("error", e.toString());
                    }
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
     * 修改其他交付物信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("修改其他交付物信息")
    @ResponseBody
    @RequestMapping(value = "/updatemytasksubdataqtjf", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatemytasksubdataqtjf(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FProjectID = jsonParam.getString("FProjectID");
        String FTaskID = jsonParam.getString("FTaskID");
        String FName = jsonParam.getString("FName");
        String FTypeID = jsonParam.getString("FTypeID");
        String FTool = jsonParam.getString("FTool");
        String FNote = jsonParam.getString("FNote");
        int executetype = jsonParam.getInteger("executetype");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);

            String CookiesLoginsubdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginsubdataID != null && !CookiesLoginsubdataID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginsubdataID);
            }
            FTaskID = FTaskID == null ? "0" : ParamTools.getdeParam(FTaskID);

            FTypeID = FTypeID == null ? "0" : ParamTools.getdeParam(FTypeID);
            FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);


            if (executetype == 1) {

//                TTask tTask = tTaskService.findById(Long.parseLong(FTaskID));
//                if (tTask != null) {
//                    if (tTask.getFstate() == 1) {
//
//                        TSubdata tSubdata = tSubDataService.findById(key);
//
//                        //修改其他交付物信息
//                        TSubdata subdata = new TSubdata();
//                        subdata.setFkeyid(key);
//                        subdata.setFname(FName);
//                        subdata.setFtypeid(Long.parseLong(FTypeID));
//                        subdata.setFtool(FTool);
//                        subdata.setFnote(FNote);
//                        subdata.setFuid(Long.parseLong(uid));
//                        subdata.setFudate(new Date());
//                        tSubDataService.update(subdata);
//
//                        TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
//                        //添加日志
//                        TaskLogc taskLogc = new TaskLogc(tTaskLogService, tUserService);
//                        String fnote ="";
//                        if(tSubdata.getFname().equals(FName))
//                            fnote = "任务【" + tTask.getFname() + "】，修改其他交付物【" + FName + "】信息。";
//                        else
//                            fnote = "任务【" + tTask.getFname() + "】，其他交付物【" + tSubdata.getFname() + "】修改为【"+FName+"】。";
//                        taskLogc.addttasklog(fnote, String.valueOf(tProject.getFkeyid()), FTaskID, "其他交付物信息", "修改", "", uid);
//
//
//                        TUser tUser = tUserService.findById(Long.parseLong(uid));
//                        TLogAction logAction = new TLogAction();
//                        logAction.setfUserId(tUser.getfKeyId());
//                        logAction.setfUserName(tUser.getfName());
//                        logAction.setfType(3);
//                        logAction.setfPath("subdata/updatemytasksubdataqtjf{" + id + "}");
//                        logAction.setfUserType(1);
//
//                        logAction.setfMemo(fnote);
//                        logActionService.save(logAction);
//                        // 返回值
//                        object.put("result", "success");
//                    } else {
//                        object.put("result", "error");
//                        object.put("error", "当前任务未开始或已完成，不能修改除数据！");
//                    }
//                } else {
//                    object.put("result", "error");
//                    object.put("error", "未找到该任务信息，请刷新后再试！");
//                }
            } else if (executetype == 2) {
                //修改其他交付物信息
                TSubdata subdata = new TSubdata();
                subdata.setFkeyid(key);
                subdata.setFname(FName);
                subdata.setFtypeid(Long.parseLong(FTypeID));
                subdata.setFtool(FTool);
                subdata.setFnote(FNote);
                subdata.setFuid(Long.parseLong(uid));
                subdata.setFudate(new Date());
                tSubDataService.update(subdata);
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
     * 删除任务其他交付物信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("删除其他交付物信息")
    @ResponseBody
    @RequestMapping(value = "/delmytasksubdataqt", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delmytasksubdataqt(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String FTaskID = jsonParam.getString("FTaskID");
        String FProjectID = jsonParam.getString("FProjectID");
        int executetype = jsonParam.getInteger("executetype");
        String fnodeid = "0";
        try {
            String CookiesLoginmyprojectdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectdataID != null && !CookiesLoginmyprojectdataID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            FTaskID = FTaskID == null ? "0" : (FTaskID.equals("0") ? "0" : ParamTools.getdeParam(FTaskID));
            FProjectID = FProjectID == null ? "0" : (FProjectID.equals("0") ? "0" : ParamTools.getdeParam(FProjectID));
            if (executetype == 1) {
//                TTask tTask = tTaskService.findById(Long.parseLong(FTaskID));
//                if (tTask == null) {
//                    object.put("result", "error");
//                    object.put("error", "交付物数据不存在，请刷新后再试！");
//                } else {
//                    TSubdata tSubdata = tSubDataService.findById(Long.parseLong(id));
//                    if (tSubdata.getFstate() == 1 && tSubdata.getFcheckstate() > 1) {
//                        object.put("result", "error");
//                        object.put("error", "该交付物数据已提交并审核，不能删除！");
//                    } else {
//                        if (tTask.getFstate() == 1) {
//                            tSubDataService.deleteById(Long.parseLong(id));
//                            //添加日志
//                            TaskLogc taskLogc = new TaskLogc(tTaskLogService, tUserService);
//                            String fnote = "任务【"+tTask.getFname()+"】删除其他交付物【" + tSubdata.getFname() + "】。";
//                            taskLogc.addttasklog(fnote, FProjectID, FTaskID, "其他交付物", "删除", "", uid);
//
//                            TUser tUser = tUserService.findById(Long.parseLong(uid));
//                            TLogAction logAction = new TLogAction();
//                            logAction.setfUserId(tUser.getfKeyId());
//                            logAction.setfUserName(tUser.getfName());
//                            logAction.setfType(3);
//                            logAction.setfPath("subdata/delmytasksubdataqt{" + id + "}");
//                            logAction.setfUserType(1);
//
//                            logAction.setfMemo(fnote);
//                            logActionService.save(logAction);
//
//                            // 返回值
//                            object.put("result", "success");
//                        }
//                    }
//                }
            } else if (executetype == 2) {
//                fnodeid = jsonParam.getString("fnodeid");
//                fnodeid = fnodeid == null ? "0" : (fnodeid.equals("0") ? "0" : ParamTools.getdeParam(fnodeid));
//                TProjectFlowCell flowCell = tProjectFlowCellService.findById(Long.parseLong(fnodeid));
//                if (flowCell.getfState() == 1) {
                    tSubDataService.deleteById(Long.parseLong(id));
                    // 返回值
                    object.put("result", "success");
//                } else {
//                    object.put("result", "error");
//                    object.put("error", "该节点数据已流转，不能删除！");
//                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


//    /**
//     * 流程节点交付物转为任务交付物
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
////    @LogOperation("流程节点交付物转为任务交付物")
//    @ResponseBody
//    @RequestMapping(value = "/liuzhuanjfw", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String liuzhuanjfw(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String FProjectID = jsonParam.getString("FProjectID");
//        String FTaskID = jsonParam.getString("FTaskID");
//        String FKeyID = jsonParam.getString("FKeyID");//工作流 -交付物ID
//        String FZSubDataID = jsonParam.getString("FZSubDataID");//任务 - 交付物ID
//        try {
//            String CookiesLoginsubdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//            String uid = ""; // 当前登录用户ID
//            if (CookiesLoginsubdataID != null && !CookiesLoginsubdataID.equals("")) {
//                uid = ParamTools.getdeParam(CookiesLoginsubdataID);
//            }
//            FZSubDataID = FZSubDataID == null ? "0" : (FZSubDataID.equals("0") ? "0" : ParamTools.getdeParam(FZSubDataID));
//            FTaskID = FTaskID == null ? "0" : (FTaskID.equals("0") ? "0" : ParamTools.getdeParam(FTaskID));
//            FKeyID = FKeyID == null ? "0" : (FKeyID.equals("0") ? "0" : ParamTools.getdeParam(FKeyID));
//            FProjectID = FProjectID == null ? "0" : (FProjectID.equals("0") ? "0" : ParamTools.getdeParam(FProjectID));
//
//            //先判断工作流交付物是否审核完成
//
//            //再判断任务交付物是否未提交状态
//
//            //最后判断任务是否已完成
//
//            TSubdata tSubdata = tSubDataService.findById(Long.parseLong(FKeyID));
//            if (tSubdata.getFstate() == 1 && tSubdata.getFcheckstate() > 1) {
//                TSubdata tSubdata1 = tSubDataService.findById(Long.parseLong(FZSubDataID));
//                if (tSubdata1.getFstate() == 0 && tSubdata1.getFcheckstate() < 1) {
//                    TTask tTask = tTaskService.findById(Long.parseLong(FTaskID));
//                    if (tTask.getFstate() == 1) {
//                        TFlow tFlow = null;
//                        if (tSubdata1.getFcheckflowid() != null) {
//                            tFlow = tFlowService.findById(tSubdata1.getFcheckflowid());
//                            // 返回值
//                            object.put("ischeckflowid", "1");
//                            object.put("fcheckflowid", ParamTools.getEnParam(FZSubDataID));
//                        } else {
//                            object.put("ischeckflowid", "0");
//                        }
//                        TSubdata tSubdata2 = new TSubdata();
//                        tSubdata2.setFkeyid(Long.parseLong(FZSubDataID));
//                        tSubdata2.setFzsubdataid(Long.parseLong(FKeyID));
//                        tSubdata2.setFurl(tSubdata.getFurl());
//                        tSubdata2.setFsysname(tSubdata.getFsysname());
//                        tSubdata2.setFfilename(tSubdata.getFfilename());
//                        tSubdata2.setFsubuserid(Long.parseLong(uid));
//                        tSubdata2.setFsubdate(new Date());
//                        tSubdata2.setFsubmode(3);
//                        tSubdata2.setFcheckstate(tFlow == null ? tSubdata.getFcheckstate() : 0);
//                        tSubdata2.setFstate(1);
//                        tSubdata2.setFhavetype(1);
//                        tSubdata2.setFxml(tFlow == null ? "" : tFlow.getfXml());
//                        tSubDataService.update(tSubdata2);
//                        TaskLogc taskLogc = new TaskLogc(tTaskLogService, tUserService);
//                        String fnote = "节点交付物【" + tSubdata.getFname() + "】转存到任务交付物【" + tSubdata1.getFname() + "】中。";
//                        taskLogc.addttasklog(fnote, FProjectID, FTaskID, "流程交付物", "转存", "", uid);
//
//                        TUser tUser = tUserService.findById(Long.parseLong(uid));
//                        TLogAction logAction = new TLogAction();
//                        logAction.setfUserId(tUser.getfKeyId());
//                        logAction.setfUserName(tUser.getfName());
//                        logAction.setfType(3);
//                        logAction.setfPath("subdata/liuzhuanjfw{" + tSubdata2.getFkeyid() + "}");
//                        logAction.setfUserType(1);
//
//                        logAction.setfMemo(fnote);
//                        logActionService.save(logAction);
//
//                        // 返回值
//                        object.put("result", "success");
//                    } else {
//                        object.put("result", "error");
//                        object.put("error", "当前任务未发布或已完成，不能转交！");
//                    }
//                } else {
//                    object.put("result", "error");
//                    object.put("error", "选择的任务需要已提交或正在审核中，不能转交！");
//                }
//            } else {
//                object.put("result", "error");
//                object.put("error", "节点中交付物审核未完成，不能转交！");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }


    /**
     * 我的交付物列表
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querymysubdata", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querymysubdata(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String fxm = jsonParam.getString("fxm");
        String frw = jsonParam.getString("frw");
        String fwj = jsonParam.getString("fwj");
        String ftype = jsonParam.getString("ftype");
        int jfstate = jsonParam.getInteger("jfstate");
        int showtype = jsonParam.getInteger("showtype");
        try {
            // 查询条件
            TSubdataExample TSubdataExample = new TSubdataExample();
            TSubdataExample.Criteria criteria = TSubdataExample.createCriteria();
            // 获取数据库记录
            JSONArray subdata_Array = new JSONArray();
            List<Long> xmid = new ArrayList<>();
            List<Long> rwid = new ArrayList<>();
            List<Long> typeid = new ArrayList<>();
            if (fxm != null && !fxm.equals("")) {
                TProjectExample tProjectExample = new TProjectExample();
                tProjectExample.or().andFnameLike("%" + fxm + "%");
                List<TProject> projectList = tProjectService.findByExample(tProjectExample);
                if (projectList.size() > 0) {
                    for (TProject tProject : projectList) {
                        xmid.add(tProject.getFkeyid());
                    }
                    criteria.andFprojectidIn(xmid);
                } else {
                    xmid.add(0L);
                    criteria.andFprojectidIn(xmid);
                }
            }
            if (jfstate != -1) {
                criteria.andFstateEqualTo(jfstate);
            }

//            if (frw != null && !frw.equals("")) {
//                TTaskExample tTaskExample = new TTaskExample();
//                tTaskExample.or().andFnameLike("%" + frw + "%");
//                List<TTask> taskList = tTaskService.findByExample(tTaskExample);
//                if (taskList.size() > 0) {
//                    for (TTask tTask : taskList) {
//                        rwid.add(tTask.getFkeyid());
//                    }
//                    criteria.andFtaskidIn(rwid);
//                } else {
//                    rwid.add(0L);
//                    criteria.andFtaskidIn(rwid);
//                }
//            }
            if (fwj != null && !fwj.equals("")) {
                criteria.andFnameLike("%" + fwj + "%");
            }
            if (ftype != null && !ftype.equals("")) {
                TFlowCellFileTypeExample tFlowCellFileTypeExample = new TFlowCellFileTypeExample();
                tFlowCellFileTypeExample.or().andFNameLike("%" + ftype + "%");
                List<TFlowCellFileType> fileTypeList = tFlowCellFileTypeService.findByExample(tFlowCellFileTypeExample);

                if (fileTypeList.size() > 0) {
                    for (TFlowCellFileType tFlowCellFileType : fileTypeList) {
                        typeid.add(tFlowCellFileType.getfKeyId());
                    }
                    criteria.andFtypeidIn(typeid);
                } else {
                    typeid.add(0L);
                    criteria.andFtypeidIn(typeid);
                }
            }
            String CookiesLogintaskdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintaskdataID != null && !CookiesLogintaskdataID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLogintaskdataID);
            }
            if (showtype == 1) {
                criteria.andFcidEqualTo(Long.parseLong(uid));
                criteria.andFvalidEqualTo(1);
            }
            TSubdataExample.setOrderByClause("FCDATE desc");
            PageInfo<TSubdata> subdataPageInfo = tSubDataService.findByExampleMapper(TSubdataExample, (page - 1) * results, results);
            List<TSubdata> subdata_list = subdataPageInfo.getList();
            String xmname = "";
            String rwname = "";
            String flowname = "";
            String fnodename = "";
            String ffilename = "";
            String ftitle = "";
            for (TSubdata subdata : subdata_list) {
                xmname = "";
                rwname = "";
                flowname = "";
                fnodename = "";
                ffilename = "";
                ftitle = "";
                JSONObject subdata_object = new JSONObject();
                subdata_object.put("key", ParamTools.getEnParam(subdata.getFkeyid().toString()));
                subdata_object.put("FName", subdata.getFname() == null ? "" : subdata.getFname());
                subdata_object.put("FType", subdata.getFtype());
                subdata_object.put("FProjectID", subdata.getFprojectid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(subdata.getFprojectid().toString()));
                subdata_object.put("FXml", subdata.getFxml() == null ? "" : subdata.getFxml());
                switch (subdata.getFtype()) {
                    case 1:
                        subdata_object.put("FTypeName", "项目计划交付物");
                        break;
                    case 2:
                        subdata_object.put("FTypeName", "项目工作流");
                        break;
                    case 3:
                        subdata_object.put("FTypeName", "任务交付物");
                        break;
                    case 4:
                        subdata_object.put("FTypeName", "任务工作流");
                        break;
                }
//                subdata_object.put("FProjectID", subdata.getFprojectid() == null ? "0" : ParamTools.getEnParam(subdata.getFprojectid().toString()));
                subdata_object.put("FProjectPlanID", subdata.getFprojectplanid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(subdata.getFprojectplanid().toString()));
                subdata_object.put("FTaskID", subdata.getFtaskid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(subdata.getFtaskid().toString()));
                subdata_object.put("FWorkFlowID", subdata.getFworkflowid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(subdata.getFworkflowid().toString()));
//                TFlow tFlow = tFlowService.findById(subdata.getFworkflowid());
//                subdata_object.put("FWorkFlowName", tFlow == null ? "" : tFlow.getfName() == null ? "" : HtmlTools.delHTMLTag(tFlow.getfName()));
                subdata_object.put("FWorkFlowName", "");
                ffilename = subdata.getFfilename() == null ? "————" : subdata.getFfilename();
                subdata_object.put("FFileName", subdata.getFfilename() == null ? "————" : subdata.getFfilename());
                subdata_object.put("FTool", subdata.getFtool() == null ? "" : subdata.getFtool());
                subdata_object.put("FTypeID", subdata.getFtypeid() == null ? "0" : ParamTools.getEnParam(subdata.getFtypeid().toString()));
                TFlowCellFileType fileType = tFlowCellFileTypeService.findById(subdata.getFtypeid());
                subdata_object.put("FTypeName", fileType == null ? "" : fileType.getfName() == null ? "" : fileType.getfName());
                subdata_object.put("FFileType", subdata.getFfiletype() == null ? "" : subdata.getFfiletype());
                if (subdata.getFfiletype() != null) {
                    if (subdata.getFfiletype().toUpperCase().contains("PRT") || subdata.getFfiletype().toUpperCase().contains("STEP") || subdata.getFfiletype().toUpperCase().contains("EASM") || subdata.getFfiletype().toUpperCase().contains("SLD") || subdata.getFfiletype().toUpperCase().contains("DWG") || subdata.getFfiletype().toUpperCase().contains("DF") || subdata.getFfiletype().toUpperCase().contains("CATP")) {
                        subdata_object.put("isFFileType", 1);
                    } else {
                        subdata_object.put("isFFileType", 0);
                    }
                } else {
                    subdata_object.put("isFFileType", 0);
                }
                subdata_object.put("FCheckFlowID", subdata.getFcheckflowid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(subdata.getFcheckflowid().toString()));
//                tFlow = tFlowService.findById(subdata.getFcheckflowid());
//                flowname = tFlow == null ? "" : tFlow.getfName() == null ? "" : HtmlTools.delHTMLTag(tFlow.getfName());
                flowname = "";
                subdata_object.put("FCheckFlowName", flowname);
//                TTask tTask = tTaskService.findById(subdata.getFtaskid());
//                rwname = tTask == null ? "" : tTask.getFname() == null ? "" : tTask.getFname();
                rwname = "";
                subdata_object.put("tTaskName", rwname);
                TProject tProject = tProjectService.findById(subdata.getFprojectid());
                xmname = tProject == null ? "" : tProject.getFname() == null ? "" : tProject.getFname();
                subdata_object.put("tProjectName", xmname);
                subdata_object.put("FNote", subdata.getFnote() == null ? "" : subdata.getFnote());
                subdata_object.put("FCID", subdata.getFcid());
                subdata_object.put("FUID", subdata.getFuid());
                subdata_object.put("FCDATE", subdata.getFcdate());
                subdata_object.put("FUDATE", subdata.getFudate());
                subdata_object.put("FState", subdata.getFstate());
                subdata_object.put("FValid", subdata.getFvalid());
                subdata_object.put("FEdition", subdata.getFedition() == null ? "" : subdata.getFedition());
                if (subdata.getFstate() == 0) {
                    subdata_object.put("FStateName", "未提交");
                    subdata_object.put("JFFStateName", "待交付");
                    subdata_object.put("TaskFStateName", "否");
                } else if (subdata.getFstate() == 1) {
                    subdata_object.put("FStateName", "已提交");
                    subdata_object.put("JFFStateName", "已交付");
                    subdata_object.put("TaskFStateName", "是");
                }

                subdata_object.put("FCheckState", subdata.getFcheckstate());
                if (subdata.getFcheckstate() == 0) {
                    subdata_object.put("FCheckStateName", "未审核");
                } else if (subdata.getFcheckstate() == 1) {
                    subdata_object.put("FCheckStateName", "审核中");
                } else if (subdata.getFcheckstate() == 2) {
                    subdata_object.put("FCheckStateName", "审核通过");
                } else if (subdata.getFcheckstate() == 3) {
                    subdata_object.put("FCheckStateName", "审核不通过");
                }
                subdata_object.put("FNodeID", subdata.getFnodeid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(subdata.getFnodeid().toString()));
//                TProjectFlowCell flowCell = tProjectFlowCellService.findById(subdata.getFnodeid());
//                fnodename = flowCell == null ? "" : flowCell.getfValue() == null ? "" : HtmlTools.delHTMLTag(flowCell.getfValue());
                fnodename = "";
                subdata_object.put("FNodeName", fnodename);
                if (!xmname.equals("")) {
                    ftitle = ftitle + xmname + "/";
                }
                if (!rwname.equals("")) {
                    ftitle = ftitle + rwname + "/";
                }
                if (!flowname.equals("")) {
                    ftitle = ftitle + flowname + "/";
                }
                if (!fnodename.equals("")) {
                    ftitle = ftitle + fnodename + "/";
                }
                if (!ffilename.equals("")) {
                    ftitle = ftitle + ffilename + "/";
                }
                if (ftitle.length() > 1) {
                    ftitle = ftitle.substring(0, ftitle.length() - 1);
                }
                //项目名称+ 任务名称+ 流程名称+ 节点名称 + 文件名称
                subdata_object.put("ftitle", ftitle);

                if ((subdata.getFfilename() != null && !subdata.getFfilename().equals("")) && (subdata.getFurl() != null && !subdata.getFurl().equals(""))) {
                    subdata_object.put("isdisabled", 1);
                } else {
                    subdata_object.put("isdisabled", 0);
                }
                subdata_Array.add(subdata_object);
            }
            // 返回值
            object.put("list", subdata_Array);
            object.put("total", subdataPageInfo.getTotal()); // 总行数
            object.put("page", subdataPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取我的交付物信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querymysubdataInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querymysubdataInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询交付物信息
            TSubdata subdata = tSubDataService.findById(key);
            JSONObject subdata_object = new JSONObject();
            subdata_object.put("key", ParamTools.getEnParam(subdata.getFkeyid().toString()));
            //项目信息
            //根据项目ID查询相关信息
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            TProject tProject = tProjectService.findById(subdata.getFprojectid());
            if (tProject != null) {
                subdata_object.put("xmFName", tProject.getFname() == null ? "" : tProject.getFname());
                subdata_object.put("xmFNo", tProject.getFno() == null ? "" : tProject.getFno());
//                TCustomer tCustomer = tCustomerService.findById(tProject.getFcustomerid());
//                subdata_object.put("xmFCustomerID", tCustomer == null ? "" : tCustomer.getFname() == null ? "" : tCustomer.getFname());
                subdata_object.put("xmFCustomerID", "");
                TUser tUser = tUserService.findById(tProject.getFleaduserid());
                subdata_object.put("xmFLeadUserID", tUser == null ? "" : tUser.getfName() == null ? "" : tUser.getfName());
                subdata_object.put("xmFSDate", tProject.getFsdate() == null ? "" : sdf.format(tProject.getFsdate()));
                subdata_object.put("xmFEDate", tProject.getFedate() == null ? "" : sdf.format(tProject.getFedate()));
                TProjectType projectType = tProjectTypeService.findById(tProject.getFtypeid());
                subdata_object.put("xmFTypeID", projectType == null ? "" : projectType.getFname() == null ? "" : projectType.getFname());
                TProjectLevel projectLevel = tProjectLevelService.findById(tProject.getFlevelid());
                subdata_object.put("xmFLevelID", projectLevel == null ? "" : projectLevel.getFname() == null ? "" : projectLevel.getFname());
            } else {
                subdata_object.put("xmFName", "");
                subdata_object.put("xmFNo", "");
                subdata_object.put("xmFCustomerID", "");
                subdata_object.put("xmFLeadUserID", "");
                subdata_object.put("xmFSDate", "");
                subdata_object.put("xmFEDate", "");
                subdata_object.put("xmFTypeID", "");
                subdata_object.put("xmFLevelID", "");
            }
            //任务信息
            //根据任务ID查询相关信息
            if (subdata.getFtaskid() == null) {
                subdata_object.put("istaksid", 1);
                subdata_object.put("rwFName", "");
                subdata_object.put("rwFNo", "");
                subdata_object.put("rwFLeadUserID", "");
                subdata_object.put("rwFSDate", "");
                subdata_object.put("rwFEDate", "");
                subdata_object.put("rwFTypeID", "");
                subdata_object.put("rwFAsk", "");
            } else {
                subdata_object.put("istaksid", 0);
//                TTask tTask = tTaskService.findById(subdata.getFtaskid());
//                if (tTask == null) {
                    subdata_object.put("rwFName", "");
                    subdata_object.put("rwFNo", "");
                    subdata_object.put("rwFLeadUserID", "");
                    subdata_object.put("rwFSDate", "");
                    subdata_object.put("rwFEDate", "");
                    subdata_object.put("rwFTypeID", "");
                    subdata_object.put("rwFAsk", "");

//                } else {
//                    subdata_object.put("rwFName", tTask.getFname() == null ? "" : tTask.getFname());
//                    subdata_object.put("rwFNo", tTask.getFno() == null ? "" : tTask.getFno());
//                    TUser tUser = tUserService.findById(tTask.getFleaduserid());
//                    subdata_object.put("rwFLeadUserID", tUser == null ? "" : tUser.getfName() == null ? "" : tUser.getfName());
//                    subdata_object.put("rwFSDate", tTask.getFsdate() == null ? "" : sdf.format(tTask.getFsdate()));
//                    subdata_object.put("rwFEDate", tTask.getFedate() == null ? "" : sdf.format(tTask.getFedate()));
//                    TTaskType taskType = tTaskTypeService.findById(tTask.getFtypeid());
//                    subdata_object.put("rwFTypeID", taskType == null ? "" : taskType.getFname() == null ? "" : taskType.getFname());
//                    subdata_object.put("rwFAsk", tTask.getFask() == null ? "" : tTask.getFask());
//                }
            }
            //文件信息
//            if (subdata.getFcheckflowid() != null) {
//                TFlow tFlow = tFlowService.findById(subdata.getFcheckflowid());
//                subdata_object.put("fflowid", tFlow == null ? "" : tFlow.getfName() == null ? "" : HtmlTools.delHTMLTag(tFlow.getfName()));
//            } else {

                subdata_object.put("fflowid", "");
//            }
            if (subdata.getFnodeid() == null) {

                subdata_object.put("ffliwjd", "");
            } else {
                subdata_object.put("ffliwjd", "");
//                TProjectFlowCell flowCell = tProjectFlowCellService.findById(subdata.getFnodeid());
//                subdata_object.put("ffliwjd", flowCell == null ? "" : flowCell.getfValue() == null ? "" : HtmlTools.delHTMLTag(flowCell.getfValue()));
            }

            subdata_object.put("FFileName", subdata.getFfilename() == null ? "" : subdata.getFfilename());
            TUser tUser = tUserService.findById(subdata.getFsubuserid());
            subdata_object.put("FSubUserID", tUser == null ? "" : tUser.getfName() == null ? "" : tUser.getfName());
            subdata_object.put("FSubDate", subdata.getFsubdate() == null ? "" : sdf.format(subdata.getFsubdate()));
            switch (subdata.getFcheckstate()) {
                case 0:
                    subdata_object.put("FCheckState", "未审核");
                    break;
                case 1:
                    subdata_object.put("FCheckState", "审核中");
                    break;
                case 2:
                    subdata_object.put("FCheckState", "通过审核");
                    break;
                case 3:
                    subdata_object.put("FCheckState", "不通过审核");
                    break;

            }
//            //            FCheckOpinion
//            TSubdataCheckExample tSubdataCheckExample = new TSubdataCheckExample();
//            tSubdataCheckExample.or().andFsubdataidEqualTo(subdata.getFkeyid());
//            List<TSubdataCheck> checkList = tSubDataCheckService.findByExample(tSubdataCheckExample);
//            if(checkList!=null && checkList.size() > 0){
//                subdata_object.put("FCheckOpinion", checkList.get(0).getFcheckopinion() == null ? "" : checkList.get(0).getFcheckopinion());
//            }else{
//                subdata_object.put("FCheckOpinion", "");
//            }

            // 返回值
            object.put("info", subdata_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 根据ID获取交付物审核信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querymysubdataprocess", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querymysubdataprocess(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("jfwid");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 获取数据库记录
            JSONArray subdata_Array = new JSONArray();
            // 查询交付物信息
            TSubdataCheckExample tSubdataCheckExample = new TSubdataCheckExample();
            tSubdataCheckExample.or().andFsubdataidEqualTo(Long.parseLong(id));
            tSubdataCheckExample.setOrderByClause("FCDATE DESC ");
            List<TSubdataCheck> checkList = tSubDataCheckService.findByExample(tSubdataCheckExample);
            TUser tUser = null;
            for (TSubdataCheck tSubdataCheck : checkList) {
                JSONObject subdata_object = new JSONObject();
                subdata_object.put("key", ParamTools.getEnParam(tSubdataCheck.getFkeyid().toString()));
                tUser = tUserService.findById(tSubdataCheck.getFuserid());
                subdata_object.put("FUserName", tUser == null ? "" : tUser.getfName() == null ? "" : tUser.getfName());
                subdata_object.put("FStateName", tSubdataCheck.getFstate() == 1 ? "已审核" : "未审核");
//                TFlow tFlow = tFlowService.findById(tSubdataCheck.getFcheckflowid());
//                subdata_object.put("FlowName", tFlow == null ? "" : tFlow.getfName() == null ? "" : HtmlTools.delHTMLTag(tFlow.getfName()));
                subdata_object.put("FlowName","");
//                TProjectFlowCell flowCell = tProjectFlowCellService.findById(tSubdataCheck.getFnodeid());
//                subdata_object.put("FNodeName", flowCell == null ? "" : flowCell.getfValue() == null ? "" : HtmlTools.delHTMLTag(flowCell.getfValue()));
                subdata_object.put("FNodeName", "");

                subdata_object.put("FResultName", tSubdataCheck.getFcheckopinion() == null ? "" : tSubdataCheck.getFcheckopinion());
                subdata_Array.add(subdata_object);
            }
            // 返回值
            object.put("list", subdata_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取我的交付物总数信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryMysubdataCount", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryMysubdataCount(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            // 查询条件
            TSubdataExample tSubdataExample = new TSubdataExample();
            tSubdataExample.or().andFcidEqualTo(Long.parseLong(uid)); // 只读取自己负责的数据
            PageInfo<TSubdata> pageInfo = tSubDataService.findByExampleMapper(tSubdataExample, 0, 1);
            // 返回值
            object.put("total", pageInfo.getTotal()); // 总行数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 根据ID获取交付物xml
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querymysubdatafxml", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querymysubdatafxml(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询交付物信息
            TSubdata subdata = tSubDataService.findById(key);
            JSONObject subdata_object = new JSONObject();
            subdata_object.put("key", ParamTools.getEnParam(subdata.getFkeyid().toString()));
            subdata_object.put("FXml", subdata.getFxml() == null ? "" : subdata.getFxml());
            // 返回值
            object.put("info", subdata_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

//    /**
//     * 根据ID获取交付物审核信息
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/querymysubdataprocess", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String querymysubdataprocess(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        String id = jsonParam.getString("jfwid");
//        try {
//            id = id == null ? "0" : ParamTools.getdeParam(id);
//            long key = Long.parseLong(id);
//            // 获取数据库记录
//            JSONArray subdata_Array = new JSONArray();
//            // 查询交付物信息
//            TSubdataCheckExample tSubdataCheckExample = new TSubdataCheckExample();
//            tSubdataCheckExample.or().andFsubdataidEqualTo(Long.parseLong(id));
//            tSubdataCheckExample.setOrderByClause("FCDATE DESC ");
//            List<TSubdataCheck> checkList = tSubDataCheckService.findByExample(tSubdataCheckExample);
//            TUser tUser = null;
//            for (TSubdataCheck tSubdataCheck : checkList){
//                JSONObject subdata_object = new JSONObject();
//                subdata_object.put("key", ParamTools.getEnParam(tSubdataCheck.getFkeyid().toString()));
//                tUser = tUserService.findById(tSubdataCheck.getFuserid());
//                subdata_object.put("FUserName", tUser == null ? "" : tUser.getfName() == null ? "" : tUser.getfName());
//                subdata_object.put("FStateName", tSubdataCheck.getFstate() == 1 ? "已审核" : "未审核");
//                TFlow tFlow = tFlowService.findById(tSubdataCheck.getFcheckflowid());
//                subdata_object.put("FlowName",tFlow == null ? "" : tFlow.getfName() == null ? "" :tFlow.getfName());
//                TProjectFlowCell flowCell = tProjectFlowCellService.findById(tSubdataCheck.getFnodeid());
//                subdata_object.put("FNodeName", flowCell == null ? "" : flowCell.getfValue() == null ? "" : flowCell.getfValue());
//                subdata_Array.add(subdata_object);
//            }
//            // 返回值
//            object.put("list", subdata_Array);
//            object.put("result", "success");
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }


    /**
     * 根据ID获取交付物url
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querymysubdatafurl", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querymysubdatafurl(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id").replace("%27", "").replace("'", "");
        try {
            String fileurl = "";
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询交付物信息
            TSubdata subdata = tSubDataService.findById(key);
            JSONObject subdata_object = new JSONObject();
            subdata_object.put("key", ParamTools.getEnParam(subdata.getFkeyid().toString()));
            String paths = "";
            if (subdata.getFurl() != null) {
                if (subdata.getFurl().contains("TaskSubData")) {
                    paths = subdatapath;
                } else if (subdata.getFurl().contains("Flow")) {
                    paths = flowpath;
                } else if (subdata.getFurl().contains("PlanSub")) {
                    paths = path;
                }
                fileurl = subdata.getFurl().replace(paths, "").replace("////", "//");
            }
//            System.out.println(fileurl);
            subdata_object.put("fileurl", fileurl);
            // 返回值
            object.put("info", subdata_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 文件不通过审核后升版
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */

//    @LogOperation("交付物文件升版")
    @ResponseBody
    @RequestMapping(value = "/upmyprojectplansubshengban", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String upmyprojectplansubshengban(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = request.getParameter("FKeyID");//原交付物id
        String FWorkFlowID = request.getParameter("FWorkFlowID"); //审核流程
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            FWorkFlowID = FWorkFlowID == null ? "0" : ParamTools.getdeParam(FWorkFlowID);
            long key = Long.parseLong(id);
            String CookiesLoginmyprojectdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectdataID != null && !CookiesLoginmyprojectdataID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginmyprojectdataID);
            }
            TSubdata tSubdata = tSubDataService.findById(Long.parseLong(id));
            int FSubDataNum = 1;
            int FEditionNo = 1;
            if (tSubdata == null) {
                object.put("result", "error");
                object.put("error", "交付物数据不存在，请刷新后再试！");
            } else {
                // 判断是否存在上传文件
                if (uploadFiles.length < 1) {
                    object.put("result", "error");
                    object.put("error", "请至少选择一个文件上传");
                } else {
                    BufferedOutputStream stream = null;
                    for (MultipartFile file : uploadFiles) {
                        try {
                            // 获取文件名
                            String filename = file.getOriginalFilename();
//                                System.out.println("filename:" + filename);
                            if (filename == null || filename.equals("")) {
                                object.put("result", "error");
                                object.put("error", "请至少选择一个文件上传");
                            } else {
                                // 获取文件的后缀名
                                String suffixName = filename.substring(filename.lastIndexOf(".") + 1);


                                //根据后缀名过滤上传文件
                                boolean istg = false;
                                TFileFilterExample tFileFilterExample = new TFileFilterExample();
                                tFileFilterExample.createCriteria().andFstateEqualTo(1);
                                List<TFileFilter> filterList = tFileFilterService.findByExample(tFileFilterExample);
                                String filtername = "." + suffixName;
                                for (TFileFilter tFileFilter : filterList) {
                                    if (filtername.equals(tFileFilter.getFname())) {
                                        istg = true;
                                        break;
                                    }
                                    istg = false;
                                }
                                if (istg == true) {
                                    //生成新的文件名
                                    SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
                                    String realFileName = idWorker.nextId() + "." + suffixName;
                                    // 获取保存路径
                                    String savePath = FolderTools.CheckMonthFolder(subdatapath, filename, "m");
                                    // 保存文件
                                    stream = new BufferedOutputStream(new FileOutputStream(new File(savePath + realFileName)));
                                    byte[] bytes = file.getBytes();
                                    stream.write(bytes, 0, bytes.length);

                                    if (tSubdata.getFisinit() == 0) {
                                        FSubDataNum = 1;
                                    } else {
                                        FSubDataNum = tSubdata.getFsubdatanum() + 1;
                                    }
                                    FEditionNo = tSubdata.getFeditionno() + 1;
                                    //获取审核流程ID
//                                    TFlow tFlow = tFlowService.findById(Long.parseLong(FWorkFlowID));
                                    SnowflakeIdWorker idWorker2 = new SnowflakeIdWorker(1, 1);
                                    long newkey = idWorker.nextId();
                                    //插入数据
                                    TSubdata tSubdata1 = new TSubdata();
                                    tSubdata1.setFkeyid(newkey);
                                    tSubdata1.setFsubdatanum(FSubDataNum);
                                    tSubdata1.setFhavetype(tSubdata.getFhavetype());
                                    tSubdata1.setFvalid(1);
                                    tSubdata1.setFeditionno(FEditionNo);
                                    tSubdata1.setFedition("V" + FEditionNo);
                                    if (tSubdata.getFisinit() == 0) {
                                        tSubdata1.setFinitfileid(key);
                                    } else {
                                        tSubdata1.setFinitfileid(tSubdata.getFkeyid());
                                    }
                                    tSubdata1.setFsubdataid(key);
                                    tSubdata1.setFisinit(1);
                                    tSubdata1.setFcheckflowid(Long.parseLong(FWorkFlowID));
                                    tSubdata1.setFprojectplanid(tSubdata.getFprojectplanid());
                                    tSubdata1.setFprojectid(tSubdata.getFprojectid());
                                    tSubdata1.setFtaskid(tSubdata.getFtaskid());
                                    tSubdata1.setFworkflowid(tSubdata.getFworkflowid());
                                    tSubdata1.setFnodeid(tSubdata.getFnodeid());
                                    tSubdata1.setFname(tSubdata.getFname());
                                    tSubdata1.setFtool(tSubdata.getFtool());
                                    tSubdata1.setFtypeid(tSubdata.getFtypeid());
                                    tSubdata1.setFtype(tSubdata.getFtype());
                                    tSubdata1.setFcid(Long.parseLong(uid));
                                    tSubdata1.setFcdate(new Date());
                                    tSubdata1.setFurl(savePath + realFileName);
                                    tSubdata1.setFfilename(filename);
                                    tSubdata1.setFsysname(realFileName);
                                    tSubdata1.setFsubuserid(Long.parseLong(uid));
                                    tSubdata1.setFsubdate(new Date());
                                    tSubdata1.setFsubmode(1);
                                    tSubdata1.setFsubdatanum(1);
                                    tSubdata1.setFfiletype(suffixName);
                                    tSubdata1.setFcheckstate(1);

//                                    tSubdata1.setFxml(tFlow == null ? "" : tFlow.getfXml());
                                    tSubdata1.setFxml("");
                                    tSubDataService.save(tSubdata1);

                                    //修改上一版本为无效
                                    tSubdata1 = new TSubdata();
                                    tSubdata1.setFkeyid(key);
                                    tSubdata1.setFuid(Long.parseLong(uid));
                                    tSubdata1.setFudate(new Date());
                                    tSubdata1.setFvalid(0);
                                    tSubDataService.update(tSubdata1);

                                    TUser tUser = tUserService.findById(Long.parseLong(uid));
                                    TLogAction logAction = new TLogAction();
                                    logAction.setfUserId(tUser.getfKeyId());
                                    logAction.setfUserName(tUser.getfName());
                                    logAction.setfType(3);
                                    logAction.setfPath("subdata/upmyprojectplansubshengban{" + newkey + "}");
                                    logAction.setfUserType(1);

                                    logAction.setfMemo("交付物【"+tSubdata.getFname()+"V"+tSubdata.getFeditionno()+"】升版为【"+tSubdata.getFname()+"V"+FEditionNo+"】。");
                                    logActionService.save(logAction);


                                    object.put("tsubdataid", ParamTools.getEnParam(String.valueOf(newkey)));
                                    object.put("result", "success");
                                } else {
                                    object.put("result", "error");
                                    object.put("error", "请上传正确的文件格式！");
                                }


                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            object.put("result", "error");
                            object.put("error", e.toString());
                        } finally {
                            try {
                                if (stream != null) {
                                    stream.close();
                                }
                            } catch (IOException e) {
                                object.put("result", "error");
                                object.put("error", e.toString());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


//    /**
//     * 根据项目ID。物料ID 获取任务下交付物所有文件
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @ResponseBody
//    @RequestMapping(value = "/queryttaskjfw", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String queryttaskjfw(HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
//        int results = jsonParam.getInteger("results");
//        int page = jsonParam.getInteger("page");
//        JSONArray columns_JA = jsonParam.getJSONArray("columns");
//        JSONArray order_JA = jsonParam.getJSONArray("order");
//        String fmid = jsonParam.getString("fmid");//bomsid
//        String xmid = jsonParam.getString("xmid");
//        try {
//            fmid = fmid == null ? "0" : fmid.equals("0") ? "0" : ParamTools.getdeParam(fmid);
//            xmid = xmid == null ? "0" : xmid.equals("0") ? "0" : ParamTools.getdeParam(xmid);
//            TBomS bomS = tBomSService.findById(Long.parseLong(fmid));
//            long ffmmid = bomS == null ? 0L : bomS.getFmaterialid() == null ? 0L : bomS.getFmaterialid();
//            List<Long> ttaskid = new ArrayList<>();
//            TTaskExample tTaskExample = new TTaskExample();
//            tTaskExample.or().andFprojectidEqualTo(Long.parseLong(xmid)).andFmaterialidEqualTo(ffmmid);
//            List<TTask> taskList = tTaskService.findByExample(tTaskExample);
//            if (taskList != null && taskList.size() > 0) {
//                for (TTask t : taskList
//                ) {
//                    ttaskid.add(t.getFkeyid());
//                }
//            } else {
//                ttaskid.add(0L);
//            }
//            // 获取数据库记录
//            JSONArray subdata_Array = new JSONArray();
//            TSubdataExample tSubdataExample = new TSubdataExample();
//            tSubdataExample.or().andFtaskidIn(ttaskid).andFvalidEqualTo(1);
//            PageInfo<TSubdata> pageInfo = tSubDataService.findByExampleMapper(tSubdataExample, (page - 1) * results, results);
//            List<TSubdata> tSubdataList = pageInfo.getList();
//            for (TSubdata tSubdata : tSubdataList) {
//                JSONObject subdata_object = new JSONObject();
//                subdata_object.put("key", ParamTools.getEnParam(tSubdata.getFkeyid().toString()));
//                subdata_object.put("FName", tSubdata.getFname() == null ? "" : tSubdata.getFname());
//                TSubdataType subdataType = tSubDataTypeService.findById(tSubdata.getFtypeid());
//                subdata_object.put("FTypeName", subdataType == null ? "" : subdataType.getFname() == null ? "" : subdataType.getFname());
//                subdata_object.put("FEdition", tSubdata.getFedition());
//                subdata_object.put("FState", tSubdata.getFstate());
//                if (tSubdata.getFstate() == 0) {
//                    subdata_object.put("FStateName", "未交付");
//                } else if (tSubdata.getFstate() == 1) {
//                    subdata_object.put("FStateName", "已交付");
//                } else {
//                    subdata_object.put("FStateName", "未知");
//                }
//                subdata_object.put("FFileName", tSubdata.getFfilename() == null ? "" : tSubdata.getFfilename());
//                if (tSubdata.getFfiletype() != null) {
//                    if (tSubdata.getFfiletype().toUpperCase().contains("PRT") || tSubdata.getFfiletype().toUpperCase().contains("STEP") || tSubdata.getFfiletype().toUpperCase().contains("EASM") || tSubdata.getFfiletype().toUpperCase().contains("SLD") || tSubdata.getFfiletype().toUpperCase().contains("DWG") || tSubdata.getFfiletype().toUpperCase().contains("DF") || tSubdata.getFfiletype().toUpperCase().contains("CATP")) {
//                        subdata_object.put("isFFileType", 1);
//                    } else {
//                        subdata_object.put("isFFileType", 0);
//                    }
//                } else {
//                    subdata_object.put("isFFileType", 0);
//                }
//                subdata_Array.add(subdata_object);
//            }
//
//            // 返回值
//            object.put("list", subdata_Array);
//            object.put("total", pageInfo.getTotal()); // 总行数
//            object.put("page", pageInfo.getPageNum()); // 当前页数
//            object.put("result", "success");
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }

    /**
     * 批量下载打包
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/testxiazai", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String testxiazai(HttpServletRequest request)//, HttpServletResponse response
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String xmid = jsonParam.getString("id");//项目ID
        String ip = jsonParam.getString("ip");//项目ID
        String[] paths = {};//{"http://39.102.232.225:9193/a//2022-04//968511814516080640.prt","http://39.102.232.225:9193/c//2022-04//968511508805844992.docx"};
        String[] filename = {};
        try {
            xmid = xmid == null ? "0" : ParamTools.getdeParam(xmid);
            //查询该工作流下所有附件与交付物的文件地址
            int i = 0;
//            TProjectFlowsExample tProjectFlowsExample = new TProjectFlowsExample();
//            tProjectFlowsExample.createCriteria().andFprojectidEqualTo(Long.parseLong(xmid));
//            List<TProjectFlows> flowsList = tProjectFlowsService.findByExample(tProjectFlowsExample);
//            long flowsid = 0;
//            if(flowsList.size()>0){
//                flowsid = flowsList.get(0).getFflowid();
//            }

            String furl = "";

            TSubdataExample tSubdataExample = new TSubdataExample();
            tSubdataExample.createCriteria().andFprojectidEqualTo(Long.parseLong(xmid)).andFstateEqualTo(1);//.andFworkflowidEqualTo(flowsid)
            List<TSubdata> subdataList = tSubDataService.findByExample(tSubdataExample);
            if (subdataList.size() > 0) {
                paths = new String[subdataList.size()];
                filename = new String[subdataList.size()];
                for (TSubdata tSubdata : subdataList) {
                    paths[i] = tSubdata.getFurl().replace(subdatapath, ip + "/").replace(flowpath, ip + "/");
                    filename[i] = tSubdata.getFfilename().substring(0, tSubdata.getFfilename().indexOf("."));
//                    System.out.println(paths[i]);
                    i++;
                }
                // 创建临时路径,存放压缩文件
                String zippath = subdatapath + "xiazai/";
                String myFileName = String.valueOf(new Date().getTime()) + ".zip";
                String zipFilePath = zippath + myFileName;

                File file = new File(zippath);
                //如果文件夹不存在则创建
                if (!file.exists() && !file.isDirectory()) {
                    file.mkdir();
                }
                // 压缩输出流,包装流,将临时文件输出流包装成压缩流,将所有文件输出到这里,打成zip包
                ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFilePath));
                // 循环调用压缩文件方法,将一个一个需要下载的文件打入压缩文件包
                int ii = 0;
                for (String path : paths) {
                    FileConvertTools.fileToZip(path, zipOut, filename[ii]);
                    ii++;
                }
                // 压缩完成后,关闭压缩流
                zipOut.close();

//                //拼接下载默认名称并转为ISO-8859-1格式
//                String fileName = new String((myFileName).getBytes(), "ISO-8859-1");
////                response.setHeader("Content-Disposition", "attchment;filename=" + fileName);
//
//                //该流不可以手动关闭,手动关闭下载会出问题,下载完成后会自动关闭
//                ServletOutputStream outputStream = response.getOutputStream();
//                FileInputStream inputStream = new FileInputStream(zipFilePath);
//                // 如果是SpringBoot框架,在这个路径
//                // org.apache.tomcat.util.http.fileupload.IOUtils产品
//                // 否则需要自主引入apache的 commons-io依赖
//                // copy方法为文件复制,在这里直接实现了下载效果
//                IOUtils.copy(inputStream, outputStream);
//
//                // 关闭输入流
//                inputStream.close();

                //            //下载完成之后，删掉这个zip包
//            File fileTempZip = new File(zipFilePath);
//            fileTempZip.delete();

                object.put("zipFilePath", zipFilePath.replace(subdatapath, ip + "/").replace(flowpath, ip + "/"));
                object.put("filename", myFileName);
//                System.out.println(zipFilePath);
                object.put("result", "success");
            } else {
                object.put("result", "error");
                object.put("error", "项目下未有交付物文件，无法打包");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 清理打包文件
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/delxiazai", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delxiazai(HttpServletRequest request)//, HttpServletResponse response
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        try {
            String zippath = subdatapath + "xiazai/";
            FileConvertTools.delFolder(zippath);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 查询打包文件数量
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/selectxiazai", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String selectxiazai(HttpServletRequest request)//, HttpServletResponse response
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();

        try {
            int i = numberOfFiles(subdatapath);
            object.put("num", i);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    public static int numberOfFiles(String subdatapath) {
        String zippath = subdatapath + "xiazai/";
        File folder = new File(zippath);
        File[] list = folder.listFiles();
        int fileCount = 0, folderCount = 0;
        long length = 0;
        for (File file : list) {
            if (file.isFile()) {
                fileCount++;
                length += file.length();
            } else {
                folderCount++;
            }
        }
//        System.out.println("文件夹的数目: " + folderCount + " 文件的数目: " + fileCount);
        return fileCount;
    }

    /**
     * 上传文件
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/subdataupfile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String subdataupfile(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FileName = request.getParameter("FileName");//文件名称
        String FileType = request.getParameter("FileType");//文件类型
        try {
            FileType = FileType == null ? "0" : ParamTools.getdeParam(FileType);
            String CookiesLoginmyprojectdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectdataID != null && !CookiesLoginmyprojectdataID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginmyprojectdataID);
            }
            // 判断是否存在上传文件
            if (uploadFiles.length < 1) {
                object.put("result", "error");
                object.put("error", "请至少选择一个文件上传");
            } else {
                BufferedOutputStream stream = null;
                for (MultipartFile file : uploadFiles) {
                    try {
                        // 获取文件名
                        String filename = file.getOriginalFilename();
//                                System.out.println("filename:" + filename);
                        if (filename == null || filename.equals("")) {
                            object.put("result", "error");
                            object.put("error", "请至少选择一个文件上传");
                        } else {
                            // 获取文件的后缀名
                            String suffixName = filename.substring(filename.lastIndexOf(".") + 1);
                            //根据后缀名过滤上传文件
                            boolean istg = false;
                            TFileFilterExample tFileFilterExample = new TFileFilterExample();
                            tFileFilterExample.createCriteria().andFstateEqualTo(1);
                            List<TFileFilter> filterList = tFileFilterService.findByExample(tFileFilterExample);
                            String filtername = "." + suffixName;
                            for (TFileFilter tFileFilter : filterList) {
                                if (filtername.equals(tFileFilter.getFname())) {
                                    istg = true;
                                    break;
                                }
                                istg = false;
                            }

                            if (istg == true) {
                                //生成新的文件名
                                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
                                String realFileName = idWorker.nextId() + "." + suffixName;
                                // 获取保存路径
                                String savePath = FolderTools.CheckMonthFolder(path, filename, "m");
                                // 保存文件
                                stream = new BufferedOutputStream(new FileOutputStream(new File(savePath + realFileName)));
                                byte[] bytes = file.getBytes();
                                stream.write(bytes, 0, bytes.length);
                                //先删除之前的文件，再新增新文件


                                //插入数据
                                TSubdata tSubdata1 = new TSubdata();
//                                tSubdata1.setFkeyid(key);
                                tSubdata1.setFcid(Long.parseLong(uid));
                                tSubdata1.setFcdate(new Date());
                                tSubdata1.setFurl(savePath + realFileName);
                                tSubdata1.setFfilename(filename);
                                tSubdata1.setFsysname(realFileName);
                                tSubdata1.setFsubuserid(Long.parseLong(uid));
                                tSubdata1.setFsubdate(new Date());
                                tSubdata1.setFsubmode(1);
                                tSubdata1.setFsubdatanum(1);
                                tSubdata1.setFfiletype(suffixName);
                                tSubdata1.setFtype(8);
                                tSubdata1.setFstate(1);
                                tSubdata1.setFcheckstate(2);
                                tSubdata1.setFprojectid(0l);
                                tSubdata1.setFxml("");
                                tSubdata1.setFtypeid(Long.parseLong(FileType));
                                tSubdata1.setFname(FileName);

                                tSubDataService.save(tSubdata1);


                                TUser tUser = tUserService.findById(Long.parseLong(uid));
                                TLogAction logAction = new TLogAction();
                                logAction.setfUserId(tUser.getfKeyId());
                                logAction.setfUserName(tUser.getfName());
                                logAction.setfType(3);
                                logAction.setfPath("subdata/subdataupfile{" + tSubdata1.getFkeyid() + "}");
                                logAction.setfUserType(1);

                                logAction.setfMemo("上传文件");
                                logActionService.save(logAction);


                                object.put("result", "success");
                                object.put("path", savePath + realFileName);
                                object.put("filename", filename);
                                object.put("ext", suffixName);
                            } else {
                                object.put("result", "error");
                                object.put("error", "请上传正确的文件格式！");
                            }


                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        object.put("result", "error");
                        object.put("error", e.toString());
                    } finally {
                        try {
                            if (stream != null) {
                                stream.close();
                            }
                        } catch (IOException e) {
                            object.put("result", "error");
                            object.put("error", e.toString());
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

}