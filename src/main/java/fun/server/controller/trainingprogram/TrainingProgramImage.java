package fun.server.controller.trainingprogram;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TTrainingProgramImage;
import fun.server.model.TTrainingProgramImageExample;
import fun.server.service.TTrainingProgramImageService;
import fun.tools.FolderTools;
import fun.tools.ParamTools;
import fun.tools.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * 逻辑图管理 相关业务处理
 */
@RestController
@RequestMapping("/s/trainingprogramimage")
public class TrainingProgramImage {

    private final TTrainingProgramImageService tTrainingProgramImageService;

    public TrainingProgramImage(TTrainingProgramImageService tTrainingProgramImageService) {
        this.tTrainingProgramImageService = tTrainingProgramImageService;
    }
    @Value("${trainingprogramimage.path}")
    private String trainingprogramimagefj;

    /**
     * 获取培养目标信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogrammb", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogrammb(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String name = jsonParam.getString("name");

        String FTPID = jsonParam.getString("FTPID");

        try {
            FTPID = FTPID == null ? "" : ParamTools.getdeParam(FTPID);
            // 获取数据库记录
            JSONArray trainingprogrammb_Array = new JSONArray();
            // 查询条件

            TTrainingProgramImageExample TTrainingProgramImageExample = new TTrainingProgramImageExample();
            fun.server.model.TTrainingProgramImageExample.Criteria criteria = TTrainingProgramImageExample.createCriteria();
            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }
            criteria.andFtpidEqualTo(Long.parseLong(FTPID));
//            System.out.println(FTPID);
            List<TTrainingProgramImage> trainingprogramimage_list =  tTrainingProgramImageService.findByExample(TTrainingProgramImageExample);

            for (TTrainingProgramImage tTrainingProgramImage : trainingprogramimage_list) {
                JSONObject trainingprogrammb_object = new JSONObject();
                trainingprogrammb_object.put("key", ParamTools.getEnParam(tTrainingProgramImage.getFkeyid().toString()));
                trainingprogrammb_object.put("FName",tTrainingProgramImage.getFname());
                trainingprogrammb_object.put("FUrl",tTrainingProgramImage.getFurl());
                trainingprogrammb_object.put("FRelativePath1",tTrainingProgramImage.getFrelativepath1().replace("//","/"));
                trainingprogrammb_object.put("FRelativePath2",tTrainingProgramImage.getFrelativepath2().replace("//","/"));
                trainingprogrammb_object.put("FBase",tTrainingProgramImage.getFbase());
                trainingprogrammb_object.put("FHZ",tTrainingProgramImage.getFhz().replace(".",""));
                trainingprogrammb_object.put("FDHZ",tTrainingProgramImage.getFhz());
                trainingprogrammb_Array.add(trainingprogrammb_object);
            }

            // 返回值
            object.put("list", trainingprogrammb_Array.toString());
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 根据ID获取培养方案图片信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogramimageInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogramimageInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);

            // 查询年级信息
            TTrainingProgramImage tTrainingProgramImage = tTrainingProgramImageService.findById(key);
            JSONObject trainingprogrammb_object = new JSONObject();
            trainingprogrammb_object.put("key", ParamTools.getEnParam(tTrainingProgramImage.getFkeyid().toString()));
            trainingprogrammb_object.put("FName",tTrainingProgramImage.getFname());
            trainingprogrammb_object.put("FUrl",tTrainingProgramImage.getFurl());
            trainingprogrammb_object.put("FRelativePath1",tTrainingProgramImage.getFrelativepath1().replace("//","/"));
            trainingprogrammb_object.put("FRelativePath2",tTrainingProgramImage.getFrelativepath2().replace("//","/"));
            trainingprogrammb_object.put("FBase",tTrainingProgramImage.getFbase());
            trainingprogrammb_object.put("FHZ",tTrainingProgramImage.getFhz().replace(".",""));
            trainingprogrammb_object.put("FDHZ",tTrainingProgramImage.getFhz());

            // 返回值
            object.put("info", trainingprogrammb_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


//    /**
//     * 培养方案上传图片
//     *
//     * @param request 客户端请求
//     * @return 响应结果
//     * @throws UnsupportedEncodingException 未知编码异常
//     * @throws IOException                  输入输出异常
//     */
//    @LogOperation("培养方案上传图片")
//    @ResponseBody
//    @RequestMapping(value = "/upfiletrainingprogramimage")  //, method = RequestMethod.POST, produces = "application/json;charset=UTF-8"
//    public String upfiletrainingprogramimage(@RequestParam("f_file") MultipartFile[] uploadFiles, HttpServletRequest request)
//            throws UnsupportedEncodingException, IOException {
//        JSONObject object = new JSONObject();
//
//        // 获取请求参数
//        JSONObject jsonParam = ParamTools.getParameters(request);
////        String FName = request.getParameter("FName");
//        String FTMID = request.getParameter("FTMID");
//        String FTPID = request.getParameter("FTPID");
//
//        try {
//            FTMID = FTMID == null ? "0" : ParamTools.getdeParam(FTMID);
//            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);
//            String CookiesLogintaskdataID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//            String uid = ""; // 当前登录用户ID
//            if (CookiesLogintaskdataID != null && !CookiesLogintaskdataID.equals("")) {
//                uid = ParamTools.getdeParam(CookiesLogintaskdataID);
//            }
//            BufferedOutputStream stream = null;
//            for (MultipartFile file : uploadFiles) {
//                try {
//                    // 获取文件名
//                    String filename = file.getOriginalFilename();
//                    if (filename == null || filename.equals("")) {
//                        object.put("result", "error");
//                        object.put("error", "请至少选择一个文件上传");
//                    } else {
//                        // 获取文件的后缀名
//                        String suffixName = filename.substring(filename.lastIndexOf(".") + 1);
//
//                        //根据后缀名过滤上传文件
//                        boolean istg = true;
//                        if(suffixName.contains("jpg") || suffixName.contains("jpeg")|| suffixName.contains("png")|| suffixName.contains("bmp") || suffixName.contains("gif") || suffixName.contains("webp")|| suffixName.contains("JPG") || suffixName.contains("JPEG")|| suffixName.contains("PNG")|| suffixName.contains("BMP") || suffixName.contains("GIF") || suffixName.contains("WEBP")){
//                            istg = true;
//                        }else{
//                            istg = false;
//                        }
//                        if (istg == true) {
//
//                            //生成新的文件名
//                            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
//                            String realFileName = idWorker.nextId() + "." + suffixName;
//                            // 获取保存路径
//                            String savePath = FolderTools.CheckMonthFolder(trainingprogramimagefj, filename, "m");
//                            // 保存文件
//                            stream = new BufferedOutputStream(new FileOutputStream(new File(savePath + realFileName)));
//                            byte[] bytes = file.getBytes();
//
//                            stream.write(bytes, 0, bytes.length);
//                            // 定义日期格式
////                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
////                            String convertedImageToBase64 = this.convertImageToBase64(file.getInputStream());
////                            System.out.println(convertedImageToBase64.length());
//                            TTrainingProgramImage tTrainingProgramImage = new TTrainingProgramImage();
//                            tTrainingProgramImage.setFname(filename);
//                            tTrainingProgramImage.setFtmid(Long.parseLong(FTMID));
//                            tTrainingProgramImage.setFtpid(Long.parseLong(FTPID));
//                            tTrainingProgramImage.setFurl(savePath + realFileName);
//                            tTrainingProgramImage.setFhz(suffixName);
//                            tTrainingProgramImage.setFysfilename(file.getName());
//                            tTrainingProgramImage.setFxtfilename(realFileName);
//                            tTrainingProgramImage.setFcdate(new Date());
//                            tTrainingProgramImage.setFcid(Long.parseLong(uid));
//                            tTrainingProgramImage.setFrelativepath1("//" + savePath.replace(trainingprogramimagefj, "") + realFileName);
//                            tTrainingProgramImage.setFrelativepath2("//trainingprogramimage" + tTrainingProgramImage.getFrelativepath1());
//                            tTrainingProgramImageService.save(tTrainingProgramImage);
//                            // 返回值
//                            object.put("result", "success");
//
//                        } else {
//                            object.put("result", "error");
//                            object.put("error", "请上传正确的图片格式！");
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    object.put("result", "error");
//                    object.put("error", e.toString());
//                } finally {
//                    try {
//                        if (stream != null) {
//                            stream.close();
//                        }
//                    } catch (IOException e) {
//                        object.put("result", "error");
//                        object.put("error", e.toString());
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            object.put("result", "error");
//            object.put("error", e.toString());
//        }
//        return object.toString();
//    }

    /**
     * 添加附件信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/addtrainingprogramimageFile")
    public String addtrainingprogramimageFile(@RequestParam("f_file") MultipartFile[] uploadFiles,
                                              @RequestParam("FTMID") String ftmId,
                                              @RequestParam("FTPID") String ftpId,
                                              HttpServletRequest request)
            throws Exception {
        JSONObject object = new JSONObject();
        // 判断是否存在上传文件
        if (uploadFiles.length < 1) {
            object.put("result", "error");
            object.put("error", "请至少选择一个文件上传");
        } else {
            //传进来的ID 是反着的 ， 既FTPID 是FTMID ；FTMID 是FTPID 具体原因未知， 下放赋值 反着来  - -！
            ftmId = ftmId == null ? "0" : ParamTools.getdeParam(ftmId);
            ftpId = ftpId == null ? "0" : ParamTools.getdeParam(ftpId);
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
                    String savePath = FolderTools.CheckMonthFolder(trainingprogramimagefj, filename, "m");
                    // 保存文件
                    stream = new BufferedOutputStream(new FileOutputStream(new File(savePath + realFileName)));
                    byte[] bytes = file.getBytes();
                    stream.write(bytes, 0, bytes.length);
                    // 保存到数据库
                    TTrainingProgramImage tTrainingProgramImage = new TTrainingProgramImage();
                    tTrainingProgramImage.setFname(request.getParameter("originalFileName") == null ? "" : request.getParameter("originalFileName"));
                    tTrainingProgramImage.setFtmid(Long.parseLong(ftpId));
                    tTrainingProgramImage.setFtpid(Long.parseLong(ftmId));
                    tTrainingProgramImage.setFurl(savePath + realFileName);
                    tTrainingProgramImage.setFhz(suffixName);
                    tTrainingProgramImage.setFysfilename(file.getName());
                    tTrainingProgramImage.setFxtfilename(realFileName);
                    tTrainingProgramImage.setFcdate(new Date());
                    tTrainingProgramImage.setFcid(Long.parseLong(uid));
                    tTrainingProgramImage.setFrelativepath1("//" + savePath.replace(trainingprogramimagefj, "") + realFileName);
                    tTrainingProgramImage.setFrelativepath2("//trainingprogramimage" + tTrainingProgramImage.getFrelativepath1());
                    tTrainingProgramImageService.save(tTrainingProgramImage);
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
     * 删除培养方案中图片信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除图片信息")
    @ResponseBody
    @RequestMapping(value = "/deltrainingprogramimage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deltrainingprogramimage(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TTrainingProgramImage programImage = tTrainingProgramImageService.findById(Long.parseLong(id));
            try {
                File f = new File(programImage.getFurl());
                if(f.exists()){
                    f.delete();
                }
            } catch (Exception e) {

            }
            tTrainingProgramImageService.deleteById(Long.parseLong(id));
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
     * 修改培养方案图片名称
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改培养方案图片名称")
    @ResponseBody
    @RequestMapping(value = "/updatetrainingprogramimage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatetrainingprogramimage(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FName = jsonParam.getString("FName");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);

            String CookiesLogingradeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogingradeID != null && !CookiesLogingradeID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLogingradeID);
            }
            TTrainingProgramImage trainingProgramImage = new TTrainingProgramImage();
            trainingProgramImage.setFkeyid(key);
            trainingProgramImage.setFname(FName);
            trainingProgramImage.setFuid(Long.parseLong(uid));
            trainingProgramImage.setFudate(new Date());
            tTrainingProgramImageService.update(trainingProgramImage);

            // 返回值
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }



}