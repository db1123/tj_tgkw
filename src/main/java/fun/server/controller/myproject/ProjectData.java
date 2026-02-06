package fun.server.controller.myproject;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.service.*;
import fun.tools.FileToUrlTools;
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
import java.util.Date;
import java.util.List;

/**
 * 项目参考资料管理 相关业务处理
 */
@RestController
@RequestMapping("/s/myprojectdata")
@PropertySource("classpath:upload.properties")
public class ProjectData {

    private final TProjectDataService tProjectDataService;
    private final TProjectService tProjectService;
    private final TProjectTypeService tProjectTypeService;
    private final TDataTypeService tDataTypeService;
    private final TProjectLogService tProjectLogService;
    private final TUserService tUserService;
    private final TFileFilterService tFileFilterService;
    private final TLogActionService logActionService;
    public ProjectData(TProjectDataService tProjectDataService, TProjectService tProjectService, TProjectTypeService tProjectTypeService, TDataTypeService tDataTypeService, TProjectLogService tProjectLogService, TUserService tUserService, TFileFilterService tFileFilterService, TLogActionService logActionService) {
        this.tProjectDataService = tProjectDataService;
        this.tProjectService = tProjectService;
        this.tProjectTypeService = tProjectTypeService;
        this.tDataTypeService = tDataTypeService;
        this.tProjectLogService = tProjectLogService;
        this.tUserService = tUserService;
        this.tFileFilterService = tFileFilterService;
        this.logActionService = logActionService;
    }

    @Value("${projectdata.path}")
    private String path;

    /**
     * 获取项目参考资料信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querymyprojectdata", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querymyprojectdata(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        String id = jsonParam.getString("id");
        try {
            // 获取数据库记录
            JSONArray myprojectdata_Array = new JSONArray();
            // 查询条件
            TProjectDataExample TProjectDataExample = new TProjectDataExample();
            TProjectDataExample.Criteria criteria = TProjectDataExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }
            id = id == null ? "0" : ParamTools.getdeParam(id);
            criteria.andFprojectidEqualTo(Long.parseLong(id));
            TProjectDataExample.setOrderByClause("FCDATE desc");
            PageInfo<TProjectData> myprojectdataPageInfo = tProjectDataService.findByExampleMapper(TProjectDataExample, (page - 1) * results, results);
            List<TProjectData> myprojectdata_list = myprojectdataPageInfo.getList();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int num = 1;
            for (TProjectData myprojectdata : myprojectdata_list) {
                JSONObject myprojectdata_object = new JSONObject();
                myprojectdata_object.put("no", num);
                myprojectdata_object.put("key", ParamTools.getEnParam(myprojectdata.getFkeyid().toString()));
                myprojectdata_object.put("FProjectID", ParamTools.getEnParam(myprojectdata.getFprojectid().toString()));
                TProject tProject = tProjectService.findById(myprojectdata.getFprojectid());
                myprojectdata_object.put("FProjectName", tProject == null ? "" : tProject.getFname());
                myprojectdata_object.put("FName", myprojectdata.getFname());
                myprojectdata_object.put("FTypeID", ParamTools.getEnParam(myprojectdata.getFtypeid().toString()));
                TDataType tDataType = tDataTypeService.findById(myprojectdata.getFtypeid());
                myprojectdata_object.put("FTypeName", tDataType == null ? "" : tDataType.getFname());
                myprojectdata_object.put("FFrom", myprojectdata.getFfrom() == null ? "" : myprojectdata.getFfrom());
                myprojectdata_object.put("FType", myprojectdata.getFtype());
                if(myprojectdata.getFtype()!=null){
                    if (myprojectdata.getFtype().toUpperCase().contains("PRT") || myprojectdata.getFtype().toUpperCase().contains("STEP")|| myprojectdata.getFtype().toUpperCase().contains("EASM")|| myprojectdata.getFtype().toUpperCase().contains("SLD")|| myprojectdata.getFtype().toUpperCase().contains("DWG") || myprojectdata.getFtype().toUpperCase().contains("DF")|| myprojectdata.getFtype().toUpperCase().contains("CATP")) {
                        myprojectdata_object.put("isFFileType", 1);
                    } else {
                        myprojectdata_object.put("isFFileType", 0);
                    }
                }else{
                    myprojectdata_object.put("isFFileType", 0);
                }
                myprojectdata_object.put("FUrl", myprojectdata.getFurl());
                myprojectdata_object.put("FSysName", myprojectdata.getFsysname());
                myprojectdata_object.put("FFileName", myprojectdata.getFfilename());
                myprojectdata_object.put("FBrowseNum", myprojectdata.getFbrowsenum());
                myprojectdata_object.put("FDownloadNum", myprojectdata.getFdownloadnum());
                myprojectdata_object.put("FNote", myprojectdata.getFnote());
                myprojectdata_object.put("FCID", myprojectdata.getFcid());
                myprojectdata_object.put("FUID", myprojectdata.getFuid());
                myprojectdata_object.put("FCDATE", myprojectdata.getFcdate() == null ? "" : sdf.format(myprojectdata.getFcdate()));
                myprojectdata_object.put("FUDATE", myprojectdata.getFudate() == null ? "" : sdf.format(myprojectdata.getFudate()));
                myprojectdata_object.put("FState", myprojectdata.getFstate());
                myprojectdata_Array.add(myprojectdata_object);
                num++;
            }

//            TProject tProject = tProjectService.findById(Long.parseLong(id));
//            object.put("pfstate", tProject.getFstate());

            // 返回值
            object.put("list", myprojectdata_Array);
            object.put("total", myprojectdataPageInfo.getTotal()); // 总行数
            object.put("page", myprojectdataPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取项目参考资料信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatamyprojectdataSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatamyprojectdataSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray myprojectdata_Array = new JSONArray();
            TProjectDataExample myprojectdataExample = new TProjectDataExample();
            TProjectDataExample.Criteria criteria = myprojectdataExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            myprojectdataExample.setOrderByClause("fname asc");
            List<TProjectData> myprojectdata_list = tProjectDataService.findByExample(myprojectdataExample);
            for (TProjectData myprojectdata : myprojectdata_list) {
                JSONObject myprojectdata_object = new JSONObject();
                myprojectdata_object.put("id", ParamTools.getEnParam(myprojectdata.getFkeyid().toString()));
                myprojectdata_object.put("text", myprojectdata.getFname());
                myprojectdata_Array.add(myprojectdata_object);
            }
            // 返回值

            object.put("list", myprojectdata_Array);
            object.put("result", "success");
            object.put("results", myprojectdata_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取项目参考资料信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querymyprojectdataInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querymyprojectdataInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 查询项目参考资料信息
            TProjectData myprojectdata = tProjectDataService.findById(key);
            JSONObject myprojectdata_object = new JSONObject();
            myprojectdata_object.put("key", ParamTools.getEnParam(myprojectdata.getFkeyid().toString()));
            TProject tProject = tProjectService.findById(myprojectdata.getFprojectid());
            myprojectdata_object.put("FProjectName", tProject == null ? "" : tProject.getFname());
            myprojectdata_object.put("FName", myprojectdata.getFname());
            myprojectdata_object.put("FTypeID", ParamTools.getEnParam(myprojectdata.getFtypeid().toString()));
            TDataType tDataType = tDataTypeService.findById(myprojectdata.getFtypeid());
            myprojectdata_object.put("FTypeName", tDataType == null ? "" : tDataType.getFname());
            myprojectdata_object.put("FFrom", myprojectdata.getFfrom());
            myprojectdata_object.put("FType", myprojectdata.getFtype());
            myprojectdata_object.put("FUrl", myprojectdata.getFurl());
            myprojectdata_object.put("FSysName", myprojectdata.getFsysname());
            myprojectdata_object.put("FFileName", myprojectdata.getFfilename());
            myprojectdata_object.put("FBrowseNum", myprojectdata.getFbrowsenum());
            myprojectdata_object.put("FDownloadNum", myprojectdata.getFdownloadnum());
            myprojectdata_object.put("FNote", myprojectdata.getFnote());
            myprojectdata_object.put("FCID", myprojectdata.getFcid());
            myprojectdata_object.put("FUID", myprojectdata.getFuid());
            myprojectdata_object.put("FCDATE", myprojectdata.getFcdate() == null ? "" : sdf.format(myprojectdata.getFcdate()));
            myprojectdata_object.put("FUDATE", myprojectdata.getFudate() == null ? "" : sdf.format(myprojectdata.getFudate()));
            myprojectdata_object.put("FState", myprojectdata.getFstate());
            // 返回值
            object.put("info", myprojectdata_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加项目参考资料信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("添加项目参考资料信息")
    @ResponseBody
    @RequestMapping(value = "/addmyprojectdata", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addmyprojectdata(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String FProjectID = request.getParameter("FProjectID");
        String FName = request.getParameter("FName");
        String FTypeID = request.getParameter("FTypeID");
        String FFrom = request.getParameter("FFrom");
        String FNote = request.getParameter("FNote");


        try {
            //if (repeaTProjectData(0L, Fname, "1") == 0) {

            FProjectID = FProjectID == null ? "0" : ParamTools.getdeParam(FProjectID);
            FTypeID = FTypeID == null ? "0" : ParamTools.getdeParam(FTypeID);
            String CookiesLoginmyprojectdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectdataID != null && !CookiesLoginmyprojectdataID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginmyprojectdataID);
            }
            //System.out.println(uploadFiles.length);


            TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
            if (tProject.getFstate() > 1) {
                object.put("result", "error");
                object.put("error", "当前项目已完成，不能修改数据！");
            } else {
                BufferedOutputStream stream = null;
                for (MultipartFile file : uploadFiles) {
                    try {
                        // 获取文件名
                        String filename = file.getOriginalFilename();
                        // 判断是否存在上传文件
                        if (filename == null || filename.equals("")) {
                            object.put("result", "error");
                            object.put("error", "请至少选择一个文件上传");
                        } else {

                            // 获取文件的后缀名
                            String suffixName = filename.substring(filename.lastIndexOf(".") + 1);

                            //根据后缀名过滤上传文件
                            boolean istg=false;
                            TFileFilterExample tFileFilterExample = new TFileFilterExample();
                            tFileFilterExample.createCriteria().andFstateEqualTo(1);
                            List<TFileFilter> filterList = tFileFilterService.findByExample(tFileFilterExample);
                            String filtername = "."+suffixName;
                            for (TFileFilter tFileFilter : filterList){
                                if(filtername.equals(tFileFilter.getFname())){
                                    istg =true;
                                    break;
                                }
                                istg = false;
                            }
                            if(istg==true){
                                //生成新的文件名
                                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
                                String realFileName = idWorker.nextId() + "." + suffixName;
                                // 获取保存路径
                                String savePath = FolderTools.CheckMonthFolder(path, filename, "m");
                                // 保存文件
                                stream = new BufferedOutputStream(new FileOutputStream(new File(savePath + realFileName)));
                                byte[] bytes = file.getBytes();
                                stream.write(bytes, 0, bytes.length);

                                //插入数据
                                TProjectData tProjectData = new TProjectData();
                                tProjectData.setFcid(Long.parseLong(uid));
                                tProjectData.setFprojectid(Long.parseLong(FProjectID));
                                tProjectData.setFname(FName);
                                tProjectData.setFtypeid(Long.parseLong(FTypeID));
                                tProjectData.setFfrom(FFrom);
                                tProjectData.setFtype(suffixName);
                                tProjectData.setFurl(savePath + realFileName);
                                tProjectData.setFsysname(realFileName);
                                tProjectData.setFfilename(filename);
                                tProjectData.setFnote(FNote);
                                tProjectDataService.save(tProjectData);

                                //添加日志
                                ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                                String fnote = "为项目【" + tProject.getFname() + "】,上传了参考资料【" + FName + "】";
                                projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "参考资料", "上传", "", uid);
                                TUser tUser = tUserService.findById(Long.parseLong(uid));
                                TLogAction logAction = new TLogAction();
                                logAction.setfUserId(tUser.getfKeyId());
                                logAction.setfUserName(tUser.getfName());
                                logAction.setfType(3);
                                logAction.setfPath("myprojectdata/addmyprojectdata{" + tProjectData.getFkeyid() + "}");
                                logAction.setfUserType(1);

                                logAction.setfMemo("为项目【" + tProject.getFname() + "】,上传了参考资料【" + FName + "】。");
                                logActionService.save(logAction);
                                object.put("result", "success");
                                object.put("path", savePath + realFileName);
                                object.put("filename", filename);
                                object.put("ext", suffixName);
                            }else{
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

    /**
     * 修改项目参考资料信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("修改项目参考资料信息")
    @ResponseBody
    @RequestMapping(value = "/updatemyprojectdata", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatemyprojectdata(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = request.getParameter("FKeyID");
        String FName = request.getParameter("FName");
        String FTypeID = request.getParameter("FTypeID");
        String FFrom = request.getParameter("FFrom");
        String FNote = request.getParameter("FNote");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            FTypeID = FTypeID == null ? "0" : ParamTools.getdeParam(FTypeID);
            long key = Long.parseLong(id);
            //if (repeaTProjectData(key, Fname, "2") == 0) {
            String CookiesLoginmyprojectdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectdataID != null && !CookiesLoginmyprojectdataID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginmyprojectdataID);
            }
            TProjectData projectData = tProjectDataService.findById(Long.parseLong(id));
            if (projectData == null) {
                object.put("result", "error");
                object.put("error", "参考资料数据不存在，请刷新后再试！");
            } else {
                TProject tProject = tProjectService.findById(projectData.getFprojectid());
                if (tProject.getFstate() > 1) {
                    object.put("result", "error");
                    object.put("error", "当前项目已完成，不能修改数据！");
                } else {
                    //修改数据
                    TProjectData tProjectData = new TProjectData();
                    tProjectData.setFkeyid(key);
                    tProjectData.setFcid(Long.parseLong(uid));
                    tProjectData.setFname(FName);
                    tProjectData.setFtypeid(Long.parseLong(FTypeID));
                    tProjectData.setFfrom(FFrom);
                    tProjectData.setFnote(FNote);
                    tProjectData.setFudate(new Date());
                    tProjectDataService.update(tProjectData);

                    //添加日志
                    ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                    String fnote = "修改参考资料【" + FName + "】信息";
                    projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "参考资料", "修改", "", uid);

                    TUser tUser = tUserService.findById(Long.parseLong(uid));
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserId(tUser.getfKeyId());
                    logAction.setfUserName(tUser.getfName());
                    logAction.setfType(3);
                    logAction.setfPath("myprojectdata/updatemyprojectdata{" + tProjectData.getFkeyid() + "}");
                    logAction.setfUserType(1);

                    logAction.setfMemo(fnote + "。");
                    logActionService.save(logAction);
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
     * 重新上传文件
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("重新上传项目参考资料文件")
    @ResponseBody
    @RequestMapping(value = "/upmyprojectdata", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String upmyprojectdata(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = request.getParameter("FKeyID");
        String FName = request.getParameter("FName");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            String CookiesLoginmyprojectdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectdataID != null && !CookiesLoginmyprojectdataID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginmyprojectdataID);
            }
            TProjectData projectData = tProjectDataService.findById(Long.parseLong(id));
            if (projectData == null) {
                object.put("result", "error");
                object.put("error", "参考资料数据不存在，请刷新后再试！");
            } else {
                TProject tProject = tProjectService.findById(projectData.getFprojectid());
                if (tProject.getFstate() > 1) {
                    object.put("result", "error");
                    object.put("error", "当前项目已完成，不能上传文件！");
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
                                //System.out.println("filename:"+filename);
                                if (filename == null || filename.equals("")) {
                                    object.put("result", "error");
                                    object.put("error", "请至少选择一个文件上传");
                                } else {
                                    // 获取文件的后缀名
                                    String suffixName = filename.substring(filename.lastIndexOf(".") + 1);

                                    //根据后缀名过滤上传文件
                                    boolean istg=false;
                                    TFileFilterExample tFileFilterExample = new TFileFilterExample();
                                    tFileFilterExample.createCriteria().andFstateEqualTo(1);
                                    List<TFileFilter> filterList = tFileFilterService.findByExample(tFileFilterExample);
                                    String filtername = "."+suffixName;
                                    for (TFileFilter tFileFilter : filterList){
                                        if(filtername.equals(tFileFilter.getFname())){
                                            istg =true;
                                            break;
                                        }
                                        istg = false;
                                    }
                                    if(istg==true){
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

                                        File f = new File(projectData.getFurl());
                                        if (f.exists()) {
                                            f.delete();
                                        }
                                        //插入数据
                                        TProjectData tProjectData = new TProjectData();
                                        tProjectData.setFkeyid(key);
                                        tProjectData.setFuid(Long.parseLong(uid));
                                        tProjectData.setFtype(suffixName);
                                        tProjectData.setFname(FName);
                                        tProjectData.setFurl(savePath + realFileName);
                                        tProjectData.setFsysname(realFileName);
                                        tProjectData.setFfilename(filename);
                                        tProjectData.setFudate(new Date());
                                        tProjectDataService.update(tProjectData);


                                        //添加日志
                                        ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                                        String fnote = "为项目【"+tProject.getFname()+"】,重新上传参考资料【" + FName + "】";
                                        projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "参考资料", "重新上传", "", uid);
                                        TUser tUser = tUserService.findById(Long.parseLong(uid));
                                        TLogAction logAction = new TLogAction();
                                        logAction.setfUserId(tUser.getfKeyId());
                                        logAction.setfUserName(tUser.getfName());
                                        logAction.setfType(3);
                                        logAction.setfPath("myprojectdata/upmyprojectdata{" + tProjectData.getFkeyid() + "}");
                                        logAction.setfUserType(1);
                                        logAction.setfMemo(fnote + "。");
                                        logActionService.save(logAction);

                                        object.put("result", "success");
                                        object.put("path", savePath + realFileName);
                                        object.put("filename", filename);
                                        object.put("ext", suffixName);
                                    }else{
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
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 删除项目参考资料信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/delmyprojectdata", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delmyprojectdata(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLoginmyprojectdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectdataID != null && !CookiesLoginmyprojectdataID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TProjectData projectData = tProjectDataService.findById(Long.parseLong(id));
            if (projectData == null) {
                object.put("result", "error");
                object.put("error", "参考资料数据不存在，请刷新后再试！");
            } else {
                TProject tProject = tProjectService.findById(projectData.getFprojectid());
                if (tProject.getFstate() > 1) {
                    object.put("result", "error");
                    object.put("error", "当前项目已完成，不能删除！");
                } else {
                    String fname = projectData.getFname();
                    tProjectDataService.deleteById(Long.parseLong(id));
                    //添加日志
                    ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                    String fnote = "为项目【"+tProject.getFname()+"】，删除参考资料【" + fname + "】";
                    projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "参考资料", "删除", "", uid);
                    TUser tUser = tUserService.findById(Long.parseLong(uid));
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserId(tUser.getfKeyId());
                    logAction.setfUserName(tUser.getfName());
                    logAction.setfType(3);
                    logAction.setfPath("myprojectdata/delmyprojectdata{" + projectData.getFkeyid() + "}");
                    logAction.setfUserType(1);

                    logAction.setfMemo(fnote + "。");
                    logActionService.save(logAction);
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
     * 变更项目参考资料信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statemyprojectdata", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statemyprojectdata(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLoginmyprojectdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectdataID != null && !CookiesLoginmyprojectdataID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TProjectData myprojectdata = new TProjectData();
            myprojectdata.setFkeyid(Long.parseLong(id));
            myprojectdata.setFuid(Long.parseLong(uid));
            myprojectdata.setFudate(new Date());
            myprojectdata.setFstate(Integer.valueOf(state));
            tProjectDataService.update(myprojectdata);


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
     * 验证项目参考资料是否存在
     */
    private int repeaTProjectData(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TProjectDataExample myprojectdataExample = new TProjectDataExample();
            TProjectDataExample.Criteria criteria = myprojectdataExample.createCriteria();
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
            List<TProjectData> rojectTypeList = tProjectDataService.findByExample(myprojectdataExample);
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
            TProjectData projectData = tProjectDataService.findById(Long.parseLong(id));
            String url = "";
            String filename = "";
            TProjectData tProjectData = null;
            TProject tProject = tProjectService.findById(Long.parseLong(FProjectID));
            String CookiesLoginmyprojectdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginmyprojectdataID != null && !CookiesLoginmyprojectdataID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            if (projectData != null) {
                filename = projectData.getFfilename();
                tProjectData = new TProjectData();
                tProjectData.setFkeyid(Long.parseLong(id));
                if (ftype == 1) {
                    ///data/web_project/uploadFiles/pdm_server/ProjectData/
                    //url = projectData.getFurl().replace("D://MyDesk//uploadFiles//RiskSimulationServer//ProjectData",ip).replace("//","/");
//                    url = projectData.getFurl().replace(path.replace("//","/").substring(0,path.length()-1), ip).replace("//", "/");

                    url = FileToUrlTools.convertPathToUrl(ip,projectData.getFurl());
                    //增加预览数
                    tProjectData.setFbrowsenum((projectData.getFbrowsenum() + 1));
                    //添加日志
                    ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                    String fnote = "预览参考资料【" + projectData.getFname() + "】";
                    projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "参考资料", "预览", "", uid);
                } else if (ftype == 2) {
                    //url = projectData.getFurl().replace("D://MyDesk//uploadFiles//RiskSimulationServer//ProjectData","").replace("//","/");
//                    url = projectData.getFurl().replace(path.replace("//","/").substring(0,path.length()-1), "").replace("//", "/");
                    url = FileToUrlTools.convertPathToUrl(ip,projectData.getFurl());

                    //增加下载数
                    tProjectData.setFdownloadnum((projectData.getFdownloadnum() + 1));
                    //添加日志
                    ProjectLog projectLog = new ProjectLog(tProjectLogService, tUserService);
                    String fnote = "下载参考资料【" + projectData.getFname() + "】";
                    projectLog.addprojectlog(fnote, String.valueOf(tProject.getFkeyid()), "参考资料", "下载", "", uid);
                }
                tProjectDataService.update(tProjectData);
            }
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


    //根据ID查询项目参考资料名称
    public String getName(Long id) {
        TProjectData byId = tProjectDataService.findById(id);
        if (byId != null) {
            return byId.getFname();
        } else {
            return "";
        }

    }


    /**
     * 根据ID获取交付物url
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryprojectdatafurl", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryprojectdatafurl(HttpServletRequest request)
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
            TProjectData projectData = tProjectDataService.findById(key);
            JSONObject subdata_object = new JSONObject();
            subdata_object.put("key", ParamTools.getEnParam(projectData.getFkeyid().toString()));
            String paths="";
            if (projectData.getFurl() != null) {
                paths = path;
                fileurl = projectData.getFurl().replace(paths, "").replace("////", "//");
            }

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

}