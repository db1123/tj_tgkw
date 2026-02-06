package fun.server.controller.myproject;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.server.controller.solr.config.SolrFilesConfig;
import fun.server.model.*;
import fun.server.model.customQuery.TProjectFilesList;
import fun.server.model.customQuery.projectFile.projectFileNumCs;
import fun.server.service.*;
import fun.tools.*;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
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

/**
 * 项目文件 相关业务处理
 */
@RestController
@RequestMapping("/s/projectFiles")
@PropertySource("classpath:solr.properties")
public class ProjectFiles {

    private final TProjectService tProjectService;
    private final TUserService tUserService;
    private final TLogActionService logActionService;

    private final TFlowCellFileTypeService tFlowCellFileTypeService;

    private final TFlowCellFileTypeGroupService tFlowCellFileTypeGroupService;

    private final TProjectFilesService tProjectFilesService;

    private final TProjectLogService tProjectLogService;

    private final TFileFilterService tFileFilterService;


    private final TProjectTeamService tProjectTeamService;

    private final TSolrProcessLogService tSolrProcessLogService;


    @Value("${projectfiles.path}")
    private String path;

    @Value("${solr.path}")
    private String solrpath;

    @Value("${solrcore.path}")
    private String solrcorepath;

    public ProjectFiles(TProjectService tProjectService, TUserService tUserService, TLogActionService logActionService, TFlowCellFileTypeService tFlowCellFileTypeService, TFlowCellFileTypeGroupService tFlowCellFileTypeGroupService, TProjectFilesService tProjectFilesService, TProjectLogService tProjectLogService, TFileFilterService tFileFilterService, TProjectTeamService tProjectTeamService, TSolrProcessLogService tSolrProcessLogService) {
        this.tProjectService = tProjectService;
        this.tUserService = tUserService;
        this.logActionService = logActionService;
        this.tFlowCellFileTypeService = tFlowCellFileTypeService;
        this.tFlowCellFileTypeGroupService = tFlowCellFileTypeGroupService;
        this.tProjectFilesService = tProjectFilesService;
        this.tProjectLogService = tProjectLogService;
        this.tFileFilterService = tFileFilterService;
        this.tProjectTeamService = tProjectTeamService;
        this.tSolrProcessLogService = tSolrProcessLogService;
    }

    /**
     * 获取项目对应文件信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryprojectFiles", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryprojectFiles(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");

        String id = jsonParam.getString("id");//项目ID
        String ftypeid = jsonParam.getString("ftypeid");//类型ID
        int ftype = jsonParam.getInteger("ftype");//类型
        try {
            // 获取数据库记录
            JSONArray projectFiles_Array = new JSONArray();
            // 查询条件
            TProjectFilesExample tProjectFilesExample = new TProjectFilesExample();
            TProjectFilesExample.Criteria criteria = tProjectFilesExample.createCriteria();

            if (ftype == 1) {
                if (id != null && !id.equals("") && !id.equals("-1")) {
                    criteria.andFprojectidEqualTo(Long.parseLong(ParamTools.getdeParam(id)));
                } else {
                    criteria.andFprojectidEqualTo(-1l);
                }
            } else {
                if (id != null && !id.equals("") && !id.equals("-1")) {
                    criteria.andFtaskidEqualTo(Long.parseLong(ParamTools.getdeParam(id)));
                } else {
                    criteria.andFtaskidEqualTo(-1l);
                }
            }


            if (ftypeid != null && !ftypeid.equals("") && !ftypeid.equals("-1")) {
                criteria.andFtypeidEqualTo(Long.parseLong(ParamTools.getdeParam(ftypeid)));
            } else {
                criteria.andFtypeidEqualTo(-1l);
            }

            criteria.andFtypeEqualTo(ftype);//项目 1=项目 2=模具
            criteria.andFvalidEqualTo(1);
            tProjectFilesExample.setOrderByClause("FCDATE desc");

            PageInfo<TProjectFiles> projectFilesPageInfo = tProjectFilesService.findByExampleMapper(tProjectFilesExample, (page - 1) * results, results);
            List<TProjectFiles> projectFilesList = projectFilesPageInfo.getList();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int no = 1;
            for (TProjectFiles projectFiles : projectFilesList) {
                JSONObject projectFiles_object = new JSONObject();
                projectFiles_object.put("no", no);
                projectFiles_object.put("key", ParamTools.getEnParam(projectFiles.getFkeyid().toString()));


                projectFiles_object.put("FFileName", projectFiles.getFfilename() == null ? "" : projectFiles.getFfilename());
                projectFiles_object.put("FEditionNo", projectFiles.getFeditionno() == null ? "" : projectFiles.getFeditionno());
                projectFiles_object.put("FEdition", projectFiles.getFedition() == null ? "" : projectFiles.getFedition());
                projectFiles_object.put("FFileType", projectFiles.getFfiletype() == null ? "" : projectFiles.getFfiletype());
                projectFiles_object.put("FUrl", projectFiles.getFurl() == null ? "" : projectFiles.getFurl());
                projectFiles_object.put("FSysName", projectFiles.getFsysname() == null ? "" : projectFiles.getFsysname());

                projectFiles_object.put("FProjectID", projectFiles.getFprojectid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(projectFiles.getFprojectid().toString()));
                projectFiles_object.put("FTaskID", projectFiles.getFtaskid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(projectFiles.getFtaskid().toString()));
                projectFiles_object.put("FType", projectFiles.getFtype() == null ? "" : projectFiles.getFtype());
                projectFiles_object.put("FTypeID", projectFiles.getFtypeid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(projectFiles.getFtypeid().toString()));
                projectFiles_object.put("FFileID", projectFiles.getFfileid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(projectFiles.getFfileid().toString()));


                projectFiles_object.put("FNote", projectFiles.getFnote() == null ? "" : projectFiles.getFnote());
                projectFiles_object.put("FCID", projectFiles.getFcid());

                TUser serviceById = tUserService.findById(projectFiles.getFcid());
                projectFiles_object.put("FCIDName", serviceById == null ? "" : serviceById.getfName());

                projectFiles_object.put("FUID", projectFiles.getFuid());
                projectFiles_object.put("FCDATE", projectFiles.getFcdate() == null ? "" : sdf.format(projectFiles.getFcdate()));
                projectFiles_object.put("FUDATE", projectFiles.getFudate() == null ? "" : sdf.format(projectFiles.getFudate()));
                projectFiles_object.put("fstate", projectFiles.getFstate());
                projectFiles_Array.add(projectFiles_object);

            }

            // 返回值
//            System.out.println(projectFiles_Array.toString());
            object.put("list", projectFiles_Array);
            object.put("total", projectFilesPageInfo.getTotal()); // 总行数
            object.put("page", projectFilesPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取项目文件历史版本
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryprojectFilesls", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryprojectFilesls(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");

        String id = jsonParam.getString("id");//项目文件ID

        int isall = jsonParam.getInteger("isall");//是否显示有效的 1-否2-是

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 获取数据库记录
            JSONArray projectFiles_Array = new JSONArray();


            PageInfo<TProjectFilesList> projectFilesPageInfo = tProjectFilesService.getprojectfilesls(isall, Long.parseLong(ParamTools.getdeParam(id)), (page - 1) * results, results);
            List<TProjectFilesList> projectFilesList = projectFilesPageInfo.getList();


            for (TProjectFilesList projectFiles : projectFilesList) {
                JSONObject projectFiles_object = new JSONObject();

                projectFiles_object.put("key", ParamTools.getEnParam(projectFiles.getFkeyid().toString()));
                projectFiles_object.put("FFileName", projectFiles.getFFileName() == null ? "" : projectFiles.getFFileName());
                projectFiles_object.put("FEditionNo", projectFiles.getFEditionNo() == null ? "" : projectFiles.getFEditionNo());
                projectFiles_object.put("FEdition", projectFiles.getFEdition() == null ? "" : projectFiles.getFEdition());
                projectFiles_object.put("FUrl", projectFiles.getFUrl() == null ? "" : projectFiles.getFUrl());

                projectFiles_object.put("FProjectID", projectFiles.getFProjectID() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(projectFiles.getFProjectID().toString()));
                projectFiles_object.put("FTaskID", projectFiles.getFTaskID() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(projectFiles.getFTaskID().toString()));


                projectFiles_object.put("FFileID", projectFiles.getFFileID() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(projectFiles.getFFileID().toString()));
                projectFiles_object.put("FValid", projectFiles.getFValid() == null ? "-1" : projectFiles.getFValid());
                if (projectFiles.getFValid() == 1) {
                    projectFiles_object.put("FValidName", "有效版本");
                } else if (projectFiles.getFValid() == 0) {
                    projectFiles_object.put("FValidName", "无效版本");
                } else {
                    projectFiles_object.put("FValidName", "错误版本");
                }
                projectFiles_object.put("FNote", projectFiles.getFNote() == null ? "" : projectFiles.getFNote());
                projectFiles_Array.add(projectFiles_object);

            }

            // 返回值
//            System.out.println("456:"+projectFiles_Array.toString());
            object.put("list", projectFiles_Array);
            object.put("total", projectFilesPageInfo.getTotal()); // 总行数
            object.put("page", projectFilesPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取文件类型信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querapplyfiletype", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querapplyfiletype(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int ftype = jsonParam.getInteger("ftype");
        String projectid = jsonParam.getString("projectid");
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


            // 获取数据库记录
            JSONArray filetype_Array = new JSONArray();


            //查询文件分组
            JSONObject filetype_object = new JSONObject();

            TFlowCellFileTypeGroupExample tFlowCellFileTypeGroupExample = new TFlowCellFileTypeGroupExample();
            TFlowCellFileTypeGroupExample.Criteria criteria1 = tFlowCellFileTypeGroupExample.createCriteria();
            criteria1.andFtypeEqualTo(ftype);


            tFlowCellFileTypeGroupExample.setOrderByClause("FCDATE asc");
            List<TFlowCellFileTypeGroup> groupList = tFlowCellFileTypeGroupService.findByExample(tFlowCellFileTypeGroupExample);

            List<Long> groupLid = new ArrayList<>();
            for (TFlowCellFileTypeGroup tp : groupList) {
                groupLid.add(tp.getFkeyid());
                filetype_object = new JSONObject();
                filetype_object.put("id", ParamTools.getEnParam(tp.getFkeyid().toString()));
                filetype_object.put("pId", ParamTools.getEnParam("-1"));
                filetype_object.put("name", tp.getFname());
                filetype_object.put("issc", 2);//是否可以上传文件 1-可以 2-不可以
                filetype_Array.add(filetype_object);
            }
            if (groupLid.size() > 0) {
                //查询文件类型
                TFlowCellFileTypeExample tFlowCellFileTypeExample = new TFlowCellFileTypeExample();
                TFlowCellFileTypeExample.Criteria typeExampleCriteria = tFlowCellFileTypeExample.createCriteria();
                typeExampleCriteria.andFFtypeIn(groupLid);
                tFlowCellFileTypeExample.setOrderByClause("f_cdate asc");
                List<TFlowCellFileType> typeList = tFlowCellFileTypeService.findByExample(tFlowCellFileTypeExample);
                projectid = projectid == null ? "0" : ParamTools.getdeParam(projectid);


                projectFileNumCs projectFileNumCs;

                Integer projectFilesnum = 0;


                for (TFlowCellFileType tFlowCellFileType : typeList) {
                    //查询项目文件数量
                    projectFileNumCs = new projectFileNumCs();
                    projectFileNumCs.setFProjectID(Long.parseLong(projectid));
                    projectFileNumCs.setFTypeID(tFlowCellFileType.getfKeyId());
                    projectFilesnum = tProjectFilesService.getFProjectFilesNum(projectFileNumCs);
                    filetype_object = new JSONObject();
                    filetype_object.put("id", ParamTools.getEnParam(tFlowCellFileType.getfKeyId().toString()));
                    filetype_object.put("pId", ParamTools.getEnParam(tFlowCellFileType.getfFtype().toString()));
                    if (projectFilesnum > 0) {
                        filetype_object.put("name", tFlowCellFileType.getfName() + "<sup class='sxts'>" + projectFilesnum + "</sup>");
                    } else {
                        filetype_object.put("name", tFlowCellFileType.getfName());
                    }
                    filetype_object.put("titile", tFlowCellFileType.getfName());
                    filetype_object.put("issc", 1);//是否可以上传文件 1-可以 2-不可以
                    filetype_Array.add(filetype_object);
                }
            }

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


    /**
     * 项目-项目文件-执行-上传文件
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("上传项目文件")
    @ResponseBody
    @RequestMapping(value = "/upmyprojectfilessub", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String upmyprojectfilessub(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);

        String FProjectID = request.getParameter("FProjectID");
        String FTypeID = request.getParameter("FTypeID");
        String FNote = request.getParameter("FNote");
        try {
            FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);
            FTypeID = FTypeID == null ? "0" : ParamTools.getdeParam(FTypeID);

            String CookiesLoginmyprojectdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectdataID != null && !CookiesLoginmyprojectdataID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginmyprojectdataID);
            }
            TProject project = tProjectService.findById(Long.parseLong(FProjectID));

            if (project.getFstate() == 1) {
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
                                    TProjectFiles tProjectFiles = new TProjectFiles();
                                    tProjectFiles.setFfilename(filename);
                                    tProjectFiles.setFsysname(realFileName);
                                    tProjectFiles.setFurl(savePath + realFileName);
                                    tProjectFiles.setFcid(Long.parseLong(uid));
                                    tProjectFiles.setFcdate(new Date());
                                    tProjectFiles.setFprojectid(Long.parseLong(FProjectID));
                                    tProjectFiles.setFtypeid(Long.parseLong(FTypeID));
                                    tProjectFiles.setFfiletype(suffixName);
                                    tProjectFiles.setFnote(FNote);
                                    tProjectFiles.setFtype(1);
                                    tProjectFilesService.save(tProjectFiles);


                                    try {
                                        TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
                                        //添加日志
                                        ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                                        String fnote = "项目【" + tProject.getFname() + "】 ，上传项目文件,文件名【" + filename + "】";
                                        projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "项目文件", "上传项目文件", "", uid);

                                        TUser tUser = tUserService.findById(Long.parseLong(uid));
                                        TLogAction logAction = new TLogAction();
                                        logAction.setfUserId(tUser.getfKeyId());
                                        logAction.setfUserName(tUser.getfName());
                                        logAction.setfType(3);
                                        logAction.setfPath("projectFiles/upmyprojectfilessub{" + tProjectFiles.getFkeyid() + "}");
                                        logAction.setfUserType(1);

                                        logAction.setfMemo(fnote + "。");
                                        logActionService.save(logAction);
                                    } catch (NumberFormatException e) {

                                    }

                                    //文件进solr
                                    JSONObject paramObject = null;
                                    paramObject = new JSONObject();
                                    paramObject.put("source_table", "t_project_files");
                                    paramObject.put("source_id", tProjectFiles.getFkeyid());
                                    paramObject.put("file_url", savePath + realFileName);


                                    JSONObject returnDataObject = SolrFileTools.getsenPost(solrpath, paramObject);
                                    if (returnDataObject == null) {
                                        object.put("solrresult", "error");
                                        object.put("solrmessage", "文件索引失败");
                                    } else {
                                        String status = returnDataObject.getString("status");
                                        String message = returnDataObject.getString("message");
                                        object.put("solrresult", status);
                                        object.put("solrmessage", message);
                                    }
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
                object.put("error", "项目未发布或已完成，不能上传文件！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取文件url
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/getmyprojectUrl", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getmyprojectUrl(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);

        String id = jsonParam.getString("id");
        String ip = jsonParam.getString("ip");
        int ftype = jsonParam.getInteger("ftype");
        String FProjectID = jsonParam.getString("FProjectID");
        try {
            // 查询条件
            id = id == null ? "0" : ParamTools.getdeParam(id);
            FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);
            TProjectFiles projectFiles = tProjectFilesService.findById(Long.parseLong(id));

            String url = "";
            String filename = "";
            TProjectData tProjectData = null;
            TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
            String CookiesLoginmyprojectdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectdataID != null && !CookiesLoginmyprojectdataID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            if (projectFiles != null) {
                filename = projectFiles.getFfilename();

                if (ftype == 1) {
                    ///data/web_project/uploadFiles/pdm_server/ProjectData/
//                    url = projectFiles.getFurl().replace("D://MyDesk//uploadFiles//RiskSimulationServer//ProjectFiles", ip).replace("//", "/");
//                    url = projectFiles.getFurl().replace("D://app//uploadFiles//pdm_server//ProjectFiles", ip).replace("//", "/");//季华服务器
//                    url = projectFiles.getFurl().replace(path.replace("//","/").substring(0,path.length()-1), ip).replace("//", "/");
                    url = FileToUrlTools.convertPathToUrl(ip, projectFiles.getFurl());
                    //添加日志
                    ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                    String fnote = "预览项目文件【" + filename + "】";
                    projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "项目文件", "预览", "", uid);
                } else if (ftype == 2) {
//                    url = projectFiles.getFurl().replace("D://MyDesk//uploadFiles//RiskSimulationServer//ProjectFiles","").replace("//", "/");
//                    url = projectFiles.getFurl().replace("D://app//uploadFiles//pdm_server//ProjectFiles", "").replace("D:\\app\\uploadFiles\\pdm_server\\ProjectFiles", "").replace("//", "/").replace("file:///","");//季华服务器
//                    url = projectFiles.getFurl().replace(path.replace("//","/").substring(0,path.length()-1), "").replace("//", "/");
                    url = FileToUrlTools.convertPathToUrl(ip, projectFiles.getFurl());
                    //添加日志
                    ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                    String fnote = "下载项目文件【" + filename + "】";
                    projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "项目文件", "下载", "", uid);
                }

            }

//            System.out.println(url);
            // 返回值
            object.put("url", url);
            object.put("filename", filename);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 删除项目文件信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("删除项目文件")
    @ResponseBody
    @RequestMapping(value = "/delprojectfiles", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delprojectfiles(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String projectid = jsonParam.getString("projectid");//项目ID
        try {
            String CookiesLogincustomerID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogincustomerID != null && !CookiesLogincustomerID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            projectid = projectid == null ? "0" : (projectid.equals("0") ? "0" : ParamTools.getdeParam(projectid));
            TProject tProject = tProjectService.findById(Long.parseLong(projectid));
            if (tProject.getFstate() == 1) {

                //查询路径， 删除文件
                TProjectFiles projectFiles = tProjectFilesService.findById(Long.parseLong(id));
                String filename = projectFiles.getFfilename();
                File file = new File(projectFiles.getFurl());
                boolean delete = file.delete();
//                System.out.println("delete:" + delete);
                if (delete) {
                    TProject tProject1 = tProjectService.findById(projectFiles.getFprojectid());
                    //删除数据
                    tProjectFilesService.deleteById(Long.parseLong(id));

                    SolrClient solrClient = SolrFilesConfig.getSolrClient(solrcorepath);
                    //t_project_files_1420381772193075200
                    TSolrProcessLog tSolrProcessLog = new TSolrProcessLog();
                    try {
                        // 按id删除
                        solrClient.deleteById("t_project_files_"+id);
                        // 提交删除
                        solrClient.commit();
                        tSolrProcessLog = new TSolrProcessLog();
                        tSolrProcessLog.setFsourcetable("t_project_files");
                        tSolrProcessLog.setFsourceid(Long.parseLong(id));
                        tSolrProcessLog.setFprocesstime(new Date());
                        tSolrProcessLog.setFstate(1);
                        tSolrProcessLog.setFmessage("t_project_files_"+id+":旧数据删除成功！");
                        tSolrProcessLogService.save(tSolrProcessLog);
                    }catch (SolrServerException e) {
//                        System.err.println("删除失败（服务器错误）：" + e.getMessage());
                        tSolrProcessLog = new TSolrProcessLog();
                        tSolrProcessLog.setFsourcetable("t_project_files");
                        tSolrProcessLog.setFsourceid(Long.parseLong(id));
                        tSolrProcessLog.setFprocesstime(new Date());
                        tSolrProcessLog.setFstate(9);
                        tSolrProcessLog.setFmessage("t_project_files_"+id+":删除失败（服务器错误）：" + e.getMessage());
                        tSolrProcessLogService.save(tSolrProcessLog);
                        e.printStackTrace();
                        try {
                            solrClient.rollback();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    } catch (IOException e) {
//                        System.err.println("删除失败（网络/IO错误）：" + e.getMessage());
                        e.printStackTrace();
                        tSolrProcessLog = new TSolrProcessLog();
                        tSolrProcessLog.setFsourcetable("t_project_files");
                        tSolrProcessLog.setFsourceid(Long.parseLong(id));
                        tSolrProcessLog.setFprocesstime(new Date());
                        tSolrProcessLog.setFstate(9);
                        tSolrProcessLog.setFmessage("t_project_files_"+id+":删除失败（网络/IO错误）：" + e.getMessage());
                        tSolrProcessLogService.save(tSolrProcessLog);
                    } finally {
                        try {
                            solrClient.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }



                    //添加日志
                    ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                    String fnote = "项目【" + tProject.getFname() + "】 ，删除项目文件,文件名【" + filename + "】";
                    projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "项目文件", "删除项目文件", "", uid);


                    TUser tUser = tUserService.findById(Long.parseLong(uid));
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserId(tUser.getfKeyId());
                    logAction.setfUserName(tUser.getfName());
                    logAction.setfType(3);
                    logAction.setfPath("projectFiles/delprojectfiles{" + id + "}");
                    logAction.setfUserType(1);

                    logAction.setfMemo("删除项目【" + tProject1.getFname() + "】的项目文件【" + projectFiles.getFfilename() + "】。");
                    logActionService.save(logAction);

                    object.put("result", "success");
                } else {
                    object.put("result", "error");
                    object.put("error", "未找到项目文件，删除失败！");
                }

            } else {
                object.put("result", "error");
                object.put("error", "项目已完成，不能删除！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 项目文件升版
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("项目文件升版")
    @ResponseBody
    @RequestMapping(value = "/upmyprojectfilesshengban", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String upmyprojectfilesshengban(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = request.getParameter("FKeyID");//项目文件ID
        String FNote = request.getParameter("FNote");//备注
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            String CookiesLoginmyprojectdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectdataID != null && !CookiesLoginmyprojectdataID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginmyprojectdataID);
            }
            int FEditionNo = 1;
            TProjectFiles projectFiles = tProjectFilesService.findById(key);
            if (projectFiles != null) {
                BufferedOutputStream stream = null;
                for (MultipartFile file : uploadFiles) {
                    try {
                        // 获取文件名
                        String filename = file.getOriginalFilename();
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


                                FEditionNo = projectFiles.getFeditionno() + 1;

//                                SnowflakeIdWorker idWorker2 = new SnowflakeIdWorker(1, 1);
//                                long newkey = idWorker.nextId();
                                //插入数据
                                TProjectFiles tProjectFiles = new TProjectFiles();
                                tProjectFiles.setFfilename(filename);
                                tProjectFiles.setFsysname(realFileName);
                                tProjectFiles.setFurl(savePath + realFileName);
                                tProjectFiles.setFcid(Long.parseLong(uid));
                                tProjectFiles.setFcdate(new Date());
                                tProjectFiles.setFprojectid(projectFiles.getFprojectid());
                                tProjectFiles.setFtypeid(projectFiles.getFtypeid());
                                tProjectFiles.setFfiletype(suffixName);
                                tProjectFiles.setFnote(FNote);
                                tProjectFiles.setFtype(1);
                                if (FEditionNo == 2)
                                    tProjectFiles.setFfileid(key);
                                else
                                    tProjectFiles.setFfileid(projectFiles.getFfileid());
                                tProjectFiles.setFeditionno(FEditionNo);
                                tProjectFiles.setFedition("V" + FEditionNo);
                                tProjectFilesService.save(tProjectFiles);

                                try {
                                    //文件进solr
                                    JSONObject paramObject = null;
                                    paramObject = new JSONObject();
                                    paramObject.put("source_table", "t_project_files");
                                    paramObject.put("source_id", tProjectFiles.getFkeyid());
                                    paramObject.put("file_url", savePath + realFileName);


                                    JSONObject returnDataObject = SolrFileTools.getsenPost(solrpath, paramObject);
                                    if (returnDataObject == null) {
                                        object.put("solrresult", "error");
                                        object.put("solrmessage", "文件索引失败");
                                    } else {
                                        String status = returnDataObject.getString("status");
                                        String message = returnDataObject.getString("message");
                                        object.put("solrresult", status);
                                        object.put("solrmessage", message);
                                    }
                                } catch (Exception e) {

                                }


                                //修改上一版本为无效
                                tProjectFiles = new TProjectFiles();
                                tProjectFiles.setFkeyid(key);
                                tProjectFiles.setFvalid(0);
                                tProjectFiles.setFuid(Long.parseLong(uid));
                                tProjectFiles.setFudate(new Date());
                                tProjectFilesService.update(tProjectFiles);

                                TProject tProject = tProjectService.findById(projectFiles.getFprojectid());
                                TUser tUser = tUserService.findById(Long.parseLong(uid));
                                TLogAction logAction = new TLogAction();
                                logAction.setfUserId(tUser.getfKeyId());
                                logAction.setfUserName(tUser.getfName());
                                logAction.setfType(3);
                                logAction.setfPath("projectFiles/upmyprojectfilesshengban{" + projectFiles.getFkeyid() + "}");
                                logAction.setfUserType(1);

                                logAction.setfMemo("项目【" + tProject.getFname() + "】中的项目文件【" + projectFiles.getFfilename() + "】进行升版，版本【" + projectFiles.getFeditionno() + "-->" + FEditionNo + "】。");
                                logActionService.save(logAction);






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
            } else {
                object.put("result", "error");
                object.put("error", "项目文件不存在，请刷新后重试！");
            }
        } catch (
                Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();

    }


    /**
     * 获取未到货数据详情
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querapplyxm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querapplyxm(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int ftype = jsonParam.getInteger("ftype");//1-全部 2-登录用户
//        System.out.println("ftype:" + ftype);
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

            String CookiesLoginmyprojectteamID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectteamID != null && !CookiesLoginmyprojectteamID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginmyprojectteamID);
            }
            List<Long> projectids = new ArrayList<>();
            // 获取数据库记录
            JSONArray designpurchaseapply_Array = new JSONArray();


            //查询所有项目
            JSONObject designpurchaseapply_object = new JSONObject();
            designpurchaseapply_object.put("id", ParamTools.getEnParam("99635"));
            designpurchaseapply_object.put("pId", ParamTools.getEnParam("0"));
            designpurchaseapply_object.put("name", "项目名称");
            designpurchaseapply_object.put("issc", 2);
            designpurchaseapply_object.put("open", true);
            designpurchaseapply_Array.add(designpurchaseapply_object);
            //
            TProjectExample tProjectExample = new TProjectExample();
            TProjectExample.Criteria criteria = tProjectExample.createCriteria();
            if (ftype == 2) {
                //判断当前用户 是否存在项目中
                TProjectTeamExample tProjectTeamExample = new TProjectTeamExample();
                TProjectTeamExample.Criteria teamExampleCriteria = tProjectTeamExample.createCriteria();
                teamExampleCriteria.andFuseridEqualTo(Long.parseLong(uid));
                teamExampleCriteria.andFstateEqualTo(1);
                List<TProjectTeam> teamList = tProjectTeamService.findByExample(tProjectTeamExample);
                if (teamList != null && teamList.size() > 0) {
                    for (TProjectTeam tProjectTeam : teamList) {
                        projectids.add(tProjectTeam.getFprojectid());
                    }
                    criteria.andFkeyidIn(projectids);
                } else {

                    criteria.andFkeyidEqualTo(-1l);
                }
            }
            criteria.andFstateGreaterThan(-1);//状态大于0
            tProjectExample.setOrderByClause("FCDATE desc");
            List<TProject> projectList = tProjectService.findByExample(tProjectExample);
            List<Long> projectid = new ArrayList<>();
            projectFileNumCs projectFileNumCs;
            Integer tProjectFilesnum = 0;
            for (TProject tp : projectList) {
                projectFileNumCs = new projectFileNumCs();
                projectFileNumCs.setFProjectID(tp.getFkeyid());
                projectid.add(tp.getFkeyid());
                //查询该项目下有多少文件数量
                tProjectFilesnum = tProjectFilesService.getFProjectFilesNum(projectFileNumCs);
                designpurchaseapply_object = new JSONObject();
                designpurchaseapply_object.put("id", ParamTools.getEnParam(tp.getFkeyid().toString()));
                designpurchaseapply_object.put("pId", ParamTools.getEnParam("99635"));
                if (tProjectFilesnum > 0) {
                    designpurchaseapply_object.put("name", tp.getFno() + "/" + tp.getFname() + "<sup class='sxts'>" + tProjectFilesnum + "</sup>");

                } else {
                    designpurchaseapply_object.put("name", tp.getFno() + "/" + tp.getFname());

                }
                designpurchaseapply_object.put("title", tp.getFno() + "/" + tp.getFname());
                designpurchaseapply_object.put("projectid", ParamTools.getEnParam(tp.getFkeyid().toString()));
                designpurchaseapply_object.put("ftype", 1);
                designpurchaseapply_object.put("issc", 1);
                designpurchaseapply_Array.add(designpurchaseapply_object);
            }
//            if(projectid.size()>0){
//                TTaskExample tTaskExample = new TTaskExample();
//                TTaskExample.Criteria taskExampleCriteria = tTaskExample.createCriteria();
//                taskExampleCriteria.andFprojectidIn(projectid);
//                tTaskExample.setOrderByClause("FCDATE");
//                List<TTask> taskList = tTaskService.findByExample(tTaskExample);
//                List<Long> ttaskid = new ArrayList<>();
//                for (TTask t:taskList) {
//                    ttaskid.add(t.getFkeyid());
//                    designpurchaseapply_object = new JSONObject();
//                    designpurchaseapply_object.put("id",ParamTools.getEnParam(t.getFkeyid().toString()));
//                    designpurchaseapply_object.put("pId",ParamTools.getEnParam(t.getFprojectid().toString()));
//                    designpurchaseapply_object.put("name",t.getFno()+"/"+t.getFname());
//                    designpurchaseapply_object.put("projectid",ParamTools.getEnParam(t.getFprojectid().toString()));
//                    designpurchaseapply_object.put("ftype",2);
//                    designpurchaseapply_object.put("issc",1);
//                    designpurchaseapply_Array.add(designpurchaseapply_object);
//                }
//            }
            // 返回值
            object.put("zNodes", designpurchaseapply_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 添加批量添加项目文件
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/piliangshangchuan")
    public String piliangshangchuan(@RequestParam("f_file") MultipartFile[] uploadFiles,
                                    @RequestParam("FProject") String fprojcetid,
                                    @RequestParam("FTypeID") String ftypeid,
                                    HttpServletRequest request)
            throws Exception {
        JSONObject object = new JSONObject();
        // 判断是否存在上传文件
        if (uploadFiles.length < 1) {
            object.put("result", "error");
            object.put("error", "请至少选择一个文件上传");
        } else {


            fprojcetid = fprojcetid == null ? "0" : ParamTools.getdeParam(fprojcetid);
            ftypeid = ftypeid == null ? "0" : ParamTools.getdeParam(ftypeid);
            BufferedOutputStream stream = null;

            for (MultipartFile file : uploadFiles) {

                try {
                    String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                    String uid = ""; // 当前登录用户ID
                    if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                        uid = ParamTools.getdeParam(CookiesLoginUserID);
                    }
                    // 获取文件名
                    String filename = file.getOriginalFilename();
                    // 获取文件的后缀名
                    String suffixName = filename.substring(filename.lastIndexOf(".") + 1);
                    //生成新的文件名
                    SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
                    String realFileName = idWorker.nextId() + "." + suffixName;
                    // 获取保存路径
                    String savePath = FolderTools.CheckMonthFolder(path, filename, "m");
                    // 保存文件
                    stream = new BufferedOutputStream(new FileOutputStream(new File(savePath + realFileName)));
                    byte[] bytes = file.getBytes();
                    stream.write(bytes, 0, bytes.length);
                    //获取当前数据库 顺序


                    //插入数据
                    TProjectFiles tProjectFiles = new TProjectFiles();
                    tProjectFiles.setFfilename(filename);
                    tProjectFiles.setFsysname(realFileName);
                    tProjectFiles.setFurl(savePath + realFileName);
                    tProjectFiles.setFcid(Long.parseLong(uid));
                    tProjectFiles.setFcdate(new Date());
                    tProjectFiles.setFprojectid(Long.parseLong(fprojcetid));
                    tProjectFiles.setFtypeid(Long.parseLong(ftypeid));
                    tProjectFiles.setFfiletype(suffixName);
                    tProjectFiles.setFnote("");
                    tProjectFiles.setFtype(1);
                    tProjectFilesService.save(tProjectFiles);


                    try {
                        TProject tProject = tProjectService.findById(Long.parseLong(fprojcetid));
                        //添加日志
                        ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                        String fnote = "项目【" + tProject.getFname() + "】 ，上传项目文件,文件名【" + filename + "】";
                        projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "项目文件", "上传项目文件", "", uid);

                        TUser tUser = tUserService.findById(Long.parseLong(uid));
                        TLogAction logAction = new TLogAction();
                        logAction.setfUserId(tUser.getfKeyId());
                        logAction.setfUserName(tUser.getfName());
                        logAction.setfType(3);
                        logAction.setfPath("projectFiles/upmyprojectfilessub{" + tProjectFiles.getFkeyid() + "}");
                        logAction.setfUserType(1);

                        logAction.setfMemo(fnote + "。");
                        logActionService.save(logAction);
                    } catch (NumberFormatException e) {

                    }
                    //将文件进入solr
                    JSONObject paramObject = null;
                    paramObject = new JSONObject();
                    paramObject.put("source_table", "t_project_files");
                    paramObject.put("source_id", tProjectFiles.getFkeyid());
                    paramObject.put("file_url", savePath + realFileName);


                    JSONObject returnDataObject = SolrFileTools.getsenPost(solrpath, paramObject);
                    if (returnDataObject == null) {
                        object.put("solrresult", "error");
                        object.put("solrmessage", "文件索引失败");
                    } else {
                        String status = returnDataObject.getString("status");
                        String message = returnDataObject.getString("message");
                        object.put("solrresult", status);
                        object.put("solrmessage", message);
                    }


//
//
//                    // 保存到数据库
//                    TCourseResourcefile tBaseFile = new TCourseResourcefile();
//                    tBaseFile.setFfname(filename);
//                    tBaseFile.setFcourseid(Long.parseLong(fflid));
//                    tBaseFile.setFfurl(savePath + realFileName);
//                    tBaseFile.setFhz(suffixName);
//                    tBaseFile.setFysfilename(filename);
//                    tBaseFile.setFxtfilename(realFileName);
//                    tBaseFile.setFcdate(new Date());
//                    tBaseFile.setFcid(Long.parseLong(uid));
//                    tBaseFile.setFrelativepath1("//" + savePath.replace(path, "") + realFileName);
//                    tBaseFile.setFrelativepath2("//courseresourcefile" + tBaseFile.getFrelativepath1());
//
//                    tBaseFileService.save(tBaseFile);
                    // 返回参数
                    object.put("result", "success");
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
        return object.toString();
    }


    /**
     * 获取我的项目文件总数信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryMyProjectFileCount", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryMyProjectFileCount(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }

            TProjectFilesExample tProjectFilesExample = new TProjectFilesExample();
            tProjectFilesExample.createCriteria().andFcidEqualTo(Long.parseLong(uid));
            PageInfo<TProjectFiles> filesPageInfo = tProjectFilesService.findByExampleMapper(tProjectFilesExample, 0, 1);
            // 返回值
            object.put("total", filesPageInfo.getTotal()); // 总行数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

}