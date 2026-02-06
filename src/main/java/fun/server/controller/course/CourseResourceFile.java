package fun.server.controller.course;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.controller.solr.config.SolrFilesConfig;
import fun.server.model.*;
import fun.server.service.TCourseResourcefileService;
import fun.server.service.TFileFilterService;
import fun.server.service.TSolrProcessLogService;
import fun.server.service.TUserService;
import fun.tools.*;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * 课程资源管理 相关业务处理
 */
@RestController
@RequestMapping("/s/courseresourcefile")
@PropertySource("classpath:solr.properties")
public class CourseResourceFile {

    private final TCourseResourcefileService tCourseResourcefile;
    private final TUserService tUserService;


    private final TFileFilterService tFileFilterService;

    private final TSolrProcessLogService tSolrProcessLogService;

    public CourseResourceFile(TCourseResourcefileService tCourseResourcefile, TUserService tUserService, TFileFilterService tFileFilterService, TSolrProcessLogService tSolrProcessLogService) {
        this.tCourseResourcefile = tCourseResourcefile;
        this.tUserService = tUserService;
        this.tFileFilterService = tFileFilterService;
        this.tSolrProcessLogService = tSolrProcessLogService;
    }
    @Value("${courseresourcefile.path}")
    private String path;

    @Value("${solr.path}")
    private String solrpath;

    @Value("${solrcore.path}")
    private String solrcorepath;
    /**
     * 获取课程资源信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycourseresourcefile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycourseresourcefile(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");

        String FCourseID = jsonParam.getString("FCourseID");
        try {

            FCourseID = FCourseID == null ? "0" : ParamTools.getdeParam(FCourseID);
            // 获取数据库记录
            JSONArray courseresourcefile_Array = new JSONArray();
            // 查询条件

            TCourseResourcefileExample tBaseFileExample = new TCourseResourcefileExample();
            TCourseResourcefileExample.Criteria criteria = tBaseFileExample.createCriteria();

            criteria.andFcourseidEqualTo(Long.parseLong(FCourseID));
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                tBaseFileExample.setOrderByClause(orderSql.substring(1));
            } else {
                tBaseFileExample.setOrderByClause("FFName desc");
            }

            PageInfo<TCourseResourcefile> courseresourcefilePageInfo = tCourseResourcefile.findByExampleMapper(tBaseFileExample, (page - 1) * results, results);
            List<TCourseResourcefile> courseresourcefile_list = courseresourcefilePageInfo.getList();

            for (TCourseResourcefile courseresourcefile : courseresourcefile_list) {
                JSONObject courseresourcefile_object = new JSONObject();
                courseresourcefile_object.put("key", ParamTools.getEnParam(courseresourcefile.getFkeyid().toString()));
                courseresourcefile_object.put("FCourseID", courseresourcefile.getFcourseid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(courseresourcefile.getFcourseid().toString()));
                courseresourcefile_object.put("FFName", courseresourcefile.getFfname() == null ? "" : courseresourcefile.getFfname());
                courseresourcefile_object.put("FFUrl", courseresourcefile.getFfurl() == null ? "" : courseresourcefile.getFfurl());
                courseresourcefile_object.put("FHZ", courseresourcefile.getFhz() == null ? "" : courseresourcefile.getFhz());
                courseresourcefile_object.put("FYSFIleName", courseresourcefile.getFysfilename() == null ? "" : courseresourcefile.getFysfilename());
                courseresourcefile_object.put("FXTFIleName", courseresourcefile.getFxtfilename() == null ? "" : courseresourcefile.getFxtfilename());
                courseresourcefile_object.put("FRelativePath1", courseresourcefile.getFrelativepath1() == null ? "" : courseresourcefile.getFrelativepath1());
                courseresourcefile_object.put("FRelativePath2", courseresourcefile.getFrelativepath2() == null ? "" : courseresourcefile.getFrelativepath2());
                courseresourcefile_object.put("FCID", courseresourcefile.getFcid());
                courseresourcefile_object.put("FUID", courseresourcefile.getFuid());
                courseresourcefile_object.put("FCDATE", courseresourcefile.getFcdate());
                courseresourcefile_object.put("FUDATE", courseresourcefile.getFudate());
                courseresourcefile_object.put("FState", courseresourcefile.getFstate());
                courseresourcefile_Array.add(courseresourcefile_object);
            }
            // 返回值
            object.put("list", courseresourcefile_Array);
            object.put("total", courseresourcefilePageInfo.getTotal()); // 总行数
            object.put("page", courseresourcefilePageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 删除课程资源信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("课程资源数据删除")
    @ResponseBody
    @RequestMapping(value = "/deltcourseresourcefile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deltcourseresourcefile(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TCourseResourcefile file = tCourseResourcefile.findById(Long.parseLong(id));
            if(file!=null){
                File f = new File(file.getFfurl());
                if (f.exists())
                    f.delete();

                SolrClient solrClient = SolrFilesConfig.getSolrClient(solrcorepath);
                //t_course_resourcefile_1420381772193075200
                TSolrProcessLog tSolrProcessLog = new TSolrProcessLog();
                try {
                    // 按id删除
                    solrClient.deleteById("t_course_resourcefile_"+id);
                    // 提交删除
                    solrClient.commit();
                    tSolrProcessLog = new TSolrProcessLog();
                    tSolrProcessLog.setFsourcetable("t_course_resourcefile");
                    tSolrProcessLog.setFsourceid(Long.parseLong(id));
                    tSolrProcessLog.setFprocesstime(new Date());
                    tSolrProcessLog.setFstate(1);
                    tSolrProcessLog.setFmessage("数据删除成功！");
                    tSolrProcessLogService.save(tSolrProcessLog);
                }catch (SolrServerException e) {
//                        System.err.println("删除失败（服务器错误）：" + e.getMessage());
                    tSolrProcessLog = new TSolrProcessLog();
                    tSolrProcessLog.setFsourcetable("t_course_resourcefile");
                    tSolrProcessLog.setFsourceid(Long.parseLong(id));
                    tSolrProcessLog.setFprocesstime(new Date());
                    tSolrProcessLog.setFstate(9);
                    tSolrProcessLog.setFmessage("删除失败（服务器错误）：" + e.getMessage());
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
                    tSolrProcessLog.setFsourcetable("t_course_resourcefile");
                    tSolrProcessLog.setFsourceid(Long.parseLong(id));
                    tSolrProcessLog.setFprocesstime(new Date());
                    tSolrProcessLog.setFstate(9);
                    tSolrProcessLog.setFmessage("删除失败（网络/IO错误）：" + e.getMessage());
                    tSolrProcessLogService.save(tSolrProcessLog);
                } finally {
                    try {
                        solrClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            tCourseResourcefile.deleteById(Long.parseLong(id));

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
     * 获取文件url
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/getmyTbaseFielUrl", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getmyTbaseFielUrl(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);

        String id = jsonParam.getString("id");//图片ID
        String ip = jsonParam.getString("ip");
        try {
            // 查询条件
            id = id == null ? "0" : ParamTools.getdeParam(id);
            String url = "";
            String filename = "";
            TCourseResourcefile tBaseFile = tCourseResourcefile.findById(Long.parseLong(id));
            url = FileToUrlTools.convertPathToUrl(ip,tBaseFile.getFfurl());
            filename = tBaseFile.getFfname();
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
     * 上传文件 + 重新上传文件
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/uptabsefile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String uptabsefile(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);

        String FCourseID = request.getParameter("FCourseID");
        int scftype = Integer.parseInt(request.getParameter("scftype"));
        String FileID =null;
        try {
            FCourseID = FCourseID == null ? "0" : ParamTools.getdeParam(FCourseID);
//            System.out.println(scftype);

            if(scftype == 2){
                FileID = request.getParameter("FileID");
//                System.out.println(FileID);
                FileID = FileID == null ? "0" : ParamTools.getdeParam(FileID);
            }

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
                            istg = true;
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
                                JSONObject paramObject = null;
                                if (scftype == 1) {
                                    //插入数据
//                                    TCourseResourcefile tBaseFile = new TCourseResourcefile();
//                                    tBaseFile.setFfname(filename);
//                                    tBaseFile.setFfurl(savePath + realFileName);
//                                    tBaseFile.setFcid(Long.parseLong(uid));
//                                    tBaseFile.setFcdate(new Date());
//                                    tBaseFile.setFcourseid(Long.parseLong(FCourseID));
//                                    tCourseResourcefile.save(tBaseFile);

                                    TCourseResourcefile tBaseFile = new TCourseResourcefile();
                                    tBaseFile.setFfname(filename);
                                    tBaseFile.setFcourseid(Long.parseLong(FCourseID));
                                    tBaseFile.setFfurl(savePath + realFileName);
                                    tBaseFile.setFhz(suffixName);
                                    tBaseFile.setFysfilename(filename);
                                    tBaseFile.setFxtfilename(realFileName);
                                    tBaseFile.setFcdate(new Date());
                                    tBaseFile.setFcid(Long.parseLong(uid));
                                    tBaseFile.setFrelativepath1("//" + savePath.replace(path, "") + realFileName);
                                    tBaseFile.setFrelativepath2("//courseresourcefile" + tBaseFile.getFrelativepath1());
                                    tCourseResourcefile.save(tBaseFile);


                                    paramObject = new JSONObject();
                                    paramObject.put("source_table", "t_course_resourcefile");
                                    paramObject.put("source_id", tBaseFile.getFkeyid());
                                    paramObject.put("file_url", savePath + realFileName);
                                } else if (scftype == 2) {

                                    //先删除原文件 再修改新文件路径
                                    TCourseResourcefile tBaseFile = tCourseResourcefile.findById(Long.parseLong(FileID));
                                    if(tBaseFile!=null){
                                        File f = new File(tBaseFile.getFfurl());
                                        if(f.exists()){
                                            f.delete();
                                            //删除solr中的文件信息
                                            SolrClient solrClient = SolrFilesConfig.getSolrClient(solrcorepath);
                                            //t_course_resourcefile_1420381772193075200
                                            TSolrProcessLog tSolrProcessLog = new TSolrProcessLog();
                                            try {
                                                // 按id删除
                                                solrClient.deleteById("t_course_resourcefile_"+FileID);
                                                // 提交删除
                                                solrClient.commit();
                                                tSolrProcessLog = new TSolrProcessLog();
                                                tSolrProcessLog.setFsourcetable("t_course_resourcefile");
                                                tSolrProcessLog.setFsourceid(Long.parseLong(FileID));
                                                tSolrProcessLog.setFprocesstime(new Date());
                                                tSolrProcessLog.setFstate(1);
                                                tSolrProcessLog.setFmessage("t_course_resourcefile_"+Long.parseLong(FileID)+":旧数据删除成功！");
                                                tSolrProcessLogService.save(tSolrProcessLog);
                                            }catch (SolrServerException e) {
//                        System.err.println("删除失败（服务器错误）：" + e.getMessage());
                                                tSolrProcessLog = new TSolrProcessLog();
                                                tSolrProcessLog.setFsourcetable("t_course_resourcefile");
                                                tSolrProcessLog.setFsourceid(Long.parseLong(FileID));
                                                tSolrProcessLog.setFprocesstime(new Date());
                                                tSolrProcessLog.setFstate(9);
                                                tSolrProcessLog.setFmessage("t_course_resourcefile_"+Long.parseLong(FileID)+":删除失败（服务器错误）：" + e.getMessage());
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
                                                tSolrProcessLog.setFsourcetable("t_course_resourcefile");
                                                tSolrProcessLog.setFsourceid(Long.parseLong(FileID));
                                                tSolrProcessLog.setFprocesstime(new Date());
                                                tSolrProcessLog.setFstate(9);
                                                tSolrProcessLog.setFmessage("t_course_resourcefile_"+Long.parseLong(FileID)+":删除失败（网络/IO错误）：" + e.getMessage());
                                                tSolrProcessLogService.save(tSolrProcessLog);
                                            } finally {
                                                try {
                                                    solrClient.close();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                        tCourseResourcefile.deleteById(Long.parseLong(FileID));
                                    }

                                    //插入数据
                                    TCourseResourcefile tBaseFile1 = new TCourseResourcefile();
//                                    tBaseFile1.setFkeyid(Long.parseLong(FileID));
                                    tBaseFile1.setFfname(filename);
                                    tBaseFile1.setFcourseid(Long.parseLong(FCourseID));
                                    tBaseFile1.setFfurl(savePath + realFileName);
                                    tBaseFile1.setFhz(suffixName);
                                    tBaseFile1.setFysfilename(filename);
                                    tBaseFile1.setFxtfilename(realFileName);
                                    tBaseFile1.setFcid(Long.parseLong(uid));
                                    tBaseFile1.setFcdate(new Date());
                                    tBaseFile1.setFrelativepath1("//" + savePath.replace(path, "") + realFileName);
                                    tBaseFile1.setFrelativepath2("//courseresourcefile" + tBaseFile1.getFrelativepath1());
                                    tBaseFile1.setFsolrstate(0);
                                    tCourseResourcefile.save(tBaseFile1);

                                    paramObject = new JSONObject();
                                    paramObject.put("source_table", "t_course_resourcefile");
                                    paramObject.put("source_id", tBaseFile1.getFkeyid());
                                    paramObject.put("file_url", savePath + realFileName);
                                }
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

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }



    /**
     * 添加附件信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/addtabseFile")
    public String addtabseFile(@RequestParam("f_file") MultipartFile[] uploadFiles,
                                              @RequestParam("FCourseID") String fflid,
                                              HttpServletRequest request)
            throws Exception {
        JSONObject object = new JSONObject();
        // 判断是否存在上传文件
        if (uploadFiles.length < 1) {
            object.put("result", "error");
            object.put("error", "请至少选择一个文件上传");
        } else {


            fflid = fflid == null ? "0" : ParamTools.getdeParam(fflid);

            BufferedOutputStream stream = null;
            int maxforder =1;
            for (MultipartFile file : uploadFiles) {
                maxforder =1;
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


                    // 保存到数据库
                    TCourseResourcefile tBaseFile = new TCourseResourcefile();
                    tBaseFile.setFfname(filename);
                    tBaseFile.setFcourseid(Long.parseLong(fflid));
                    tBaseFile.setFfurl(savePath + realFileName);
                    tBaseFile.setFhz(suffixName);
                    tBaseFile.setFysfilename(filename);
                    tBaseFile.setFxtfilename(realFileName);
                    tBaseFile.setFcdate(new Date());
                    tBaseFile.setFcid(Long.parseLong(uid));
                    tBaseFile.setFrelativepath1("//" + savePath.replace(path, "") + realFileName);
                    tBaseFile.setFrelativepath2("//courseresourcefile" + tBaseFile.getFrelativepath1());
                    tCourseResourcefile.save(tBaseFile);

                    JSONObject paramObject = null;
                    paramObject = new JSONObject();
                    paramObject.put("source_table", "t_course_resourcefile");
                    paramObject.put("source_id", tBaseFile.getFkeyid());
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
}