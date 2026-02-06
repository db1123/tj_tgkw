package fun.server.controller.trainingprogram;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.model.customQuery.trainingprogram.TrainingprogramCS;
import fun.server.model.customQuery.trainingprogram.TrainingprogramData;
import fun.server.model.customQuery.trainingprogram.TrainingprogramversionCS;
import fun.server.service.*;
import fun.tools.ParamTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 培养方案管理 相关业务处理
 */
@RestController
@RequestMapping("/s/trainingprogram")
public class TrainingProgram {

    private final TTrainingProgramService tTrainingProgramService;

    private final TMajorService tMajorService;


    private final TTrainingProgramMbService tTrainingProgramMbService;
    private final TTrainingProgramByyqService tTrainingProgramByyqService;
    private final TTrainingProgramZgxkService tTrainingProgramZgxkService;
    private final TTrainingProgramXzwfService tTrainingProgramXzwfService;
    private final TTrainingProgramHxkcService tTrainingProgramHxkcService;
    private final TTrainingProgramCourseService tTrainingProgramCourseService;
    private final TTrainingProgramQtService tTrainingProgramQtService;
    private final TTrainingProgramImageService tTrainingProgramImageService;

    private final TTrainingProgramCourseStService tTrainingProgramCourseStService;
    private final TTrainingProgramCourseXnxqStService tTrainingProgramCourseXnxqStService;

    public TrainingProgram(TTrainingProgramService tTrainingProgramService, TMajorService tMajorService, TTrainingProgramMbService tTrainingProgramMbService, TTrainingProgramByyqService tTrainingProgramByyqService, TTrainingProgramZgxkService tTrainingProgramZgxkService, TTrainingProgramXzwfService tTrainingProgramXzwfService, TTrainingProgramHxkcService tTrainingProgramHxkcService, TTrainingProgramCourseService tTrainingProgramCourseService, TTrainingProgramQtService tTrainingProgramQtService, TTrainingProgramImageService tTrainingProgramImageService, TTrainingProgramCourseStService tTrainingProgramCourseStService, TTrainingProgramCourseXnxqStService tTrainingProgramCourseXnxqStService) {
        this.tTrainingProgramService = tTrainingProgramService;
        this.tMajorService = tMajorService;
        this.tTrainingProgramMbService = tTrainingProgramMbService;
        this.tTrainingProgramByyqService = tTrainingProgramByyqService;
        this.tTrainingProgramZgxkService = tTrainingProgramZgxkService;
        this.tTrainingProgramXzwfService = tTrainingProgramXzwfService;
        this.tTrainingProgramHxkcService = tTrainingProgramHxkcService;
        this.tTrainingProgramCourseService = tTrainingProgramCourseService;
        this.tTrainingProgramQtService = tTrainingProgramQtService;
        this.tTrainingProgramImageService = tTrainingProgramImageService;
        this.tTrainingProgramCourseStService = tTrainingProgramCourseStService;
        this.tTrainingProgramCourseXnxqStService = tTrainingProgramCourseXnxqStService;
    }


    /**
     * 获取培养方案信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogram", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogram(HttpServletRequest request)
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
        String fmajorID = jsonParam.getString("fmajorID");

        try {
            fmajorID = fmajorID == null ? "" : ParamTools.getdeParam(fmajorID);
            // 获取数据库记录
            JSONArray trainingprogram_Array = new JSONArray();
            // 查询条件
            TTrainingProgramExample TTrainingProgramExample = new TTrainingProgramExample();
            TTrainingProgramExample.Criteria criteria = TTrainingProgramExample.createCriteria();
            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }
            criteria.andFtmidEqualTo(Long.parseLong(fmajorID));

            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                TTrainingProgramExample.setOrderByClause(orderSql.substring(1));
            } else {
                TTrainingProgramExample.setOrderByClause("FCDATE desc");
            }

            PageInfo<TTrainingProgram> trainingprogramPageInfo = tTrainingProgramService.findByExampleMapper(TTrainingProgramExample, (page - 1) * results, results);
            List<TTrainingProgram> trainingprogram_list = trainingprogramPageInfo.getList();
            for (TTrainingProgram trainingprogram : trainingprogram_list) {
                JSONObject trainingprogram_object = new JSONObject();
                trainingprogram_object.put("key", ParamTools.getEnParam(trainingprogram.getFkeyid().toString()));
                trainingprogram_object.put("FTMID", ParamTools.getEnParam(trainingprogram.getFtmid().toString()));
                trainingprogram_object.put("FParentId", ParamTools.getEnParam(trainingprogram.getFparentid().toString()));
                if (dataall == 1) {
                    trainingprogram_object.put("FName", trainingprogram.getFname() == null ? "" : trainingprogram.getFname());
                    trainingprogram_object.put("FJS", trainingprogram.getFjs() == null ? "" : trainingprogram.getFjs());
                    trainingprogram_object.put("FZYJS", trainingprogram.getFzyjs() == null ? "" : trainingprogram.getFzyjs());
                    trainingprogram_object.put("FEditionNo", trainingprogram.getFeditionno());
                    trainingprogram_object.put("FEdition", trainingprogram.getFedition());
                    trainingprogram_object.put("FCID", trainingprogram.getFcid());
                    trainingprogram_object.put("FUID", trainingprogram.getFuid());
                    trainingprogram_object.put("FCDATE", trainingprogram.getFcdate());
                    trainingprogram_object.put("FUDATE", trainingprogram.getFudate());
                } else {
                    trainingprogram_object.put("FName", "*****");
                    trainingprogram_object.put("FCID", "*****");
                    trainingprogram_object.put("FUID", "*****");
                    trainingprogram_object.put("FCDATE", "*****");
                    trainingprogram_object.put("FUDATE", "*****");
                    trainingprogram_object.put("FJS", "*****");
                    trainingprogram_object.put("FZYJS", "*****");
                    trainingprogram_object.put("FEditionNo", "*****");
                    trainingprogram_object.put("FEdition", "*****");
                }
                trainingprogram_object.put("FValid", trainingprogram.getFvalid());
                trainingprogram_object.put("FState", trainingprogram.getFstate());
                trainingprogram_Array.add(trainingprogram_object);
            }
            // 返回值
            object.put("list", trainingprogram_Array);
            object.put("total", trainingprogramPageInfo.getTotal()); // 总行数
            object.put("page", trainingprogramPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取培养方案历史版本信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogramVersion", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogramVersion(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        String parentId = jsonParam.getString("parentId");
        try {

            JSONArray trainingprogram_Array = new JSONArray();
            Long pid = Long.parseLong(ParamTools.getdeParam(parentId));
//            System.out.println(parentId);
            // 获取数据库记录
            JSONArray EntityFile_Array = new JSONArray();
            TrainingprogramversionCS trainingprogramversionCS = new TrainingprogramversionCS();
            trainingprogramversionCS.setFParentId(pid);
            trainingprogramversionCS.setOrderBy("FCDATE desc");
            PageInfo<TrainingprogramData> trainingprogramPageInfo = tTrainingProgramService.getTrainingVersionDataList(trainingprogramversionCS, (page - 1) * results, results);
            List<TrainingprogramData> trainingprogram_list = trainingprogramPageInfo.getList();
            for (TrainingprogramData trainingprogram : trainingprogram_list) {
                JSONObject trainingprogram_object = new JSONObject();
                trainingprogram_object.put("key", ParamTools.getEnParam(trainingprogram.getFKeyID().toString()));
                trainingprogram_object.put("FTMID", ParamTools.getEnParam(trainingprogram.getFMjorID().toString()));
                trainingprogram_object.put("FParentId", ParamTools.getEnParam(trainingprogram.getFParentId().toString()));
                trainingprogram_object.put("FMjorName", trainingprogram.getFMjorName() == null ? "" : trainingprogram.getFMjorName());
                trainingprogram_object.put("FCollegeName", trainingprogram.getFCollegeName() == null ? "" : trainingprogram.getFCollegeName());
                trainingprogram_object.put("FName", trainingprogram.getFName() == null ? "" : trainingprogram.getFName());
                trainingprogram_object.put("FJS", trainingprogram.getFJS() == null ? "" : trainingprogram.getFJS());
                trainingprogram_object.put("FZYJS", trainingprogram.getFZYJS() == null ? "" : trainingprogram.getFZYJS());
                trainingprogram_object.put("FEditionNo", trainingprogram.getFEditionNo());
                trainingprogram_object.put("FEdition", trainingprogram.getFEdition());
                trainingprogram_object.put("FCDATE", trainingprogram.getFCDATE());
                trainingprogram_object.put("FUDATE", trainingprogram.getFUDATE());
                trainingprogram_object.put("FValid", trainingprogram.getFValid());
                trainingprogram_object.put("FState", trainingprogram.getFState());
                trainingprogram_Array.add(trainingprogram_object);
            }
            // 返回值
            object.put("list", trainingprogram_Array);
            object.put("total", trainingprogramPageInfo.getTotal()); // 总行数
            object.put("page", trainingprogramPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取培养方案信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatatrainingprogramSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatatrainingprogramSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray trainingprogram_Array = new JSONArray();
            TTrainingProgramExample trainingprogramExample = new TTrainingProgramExample();
            TTrainingProgramExample.Criteria criteria = trainingprogramExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFvalidEqualTo(1);
            trainingprogramExample.setOrderByClause("FName asc");
            List<TTrainingProgram> trainingprogram_list = tTrainingProgramService.findByExample(trainingprogramExample);
            for (TTrainingProgram trainingprogram : trainingprogram_list) {
                JSONObject trainingprogram_object = new JSONObject();
                trainingprogram_object.put("id", ParamTools.getEnParam(trainingprogram.getFkeyid().toString()));
                trainingprogram_object.put("text", trainingprogram.getFname());
                trainingprogram_Array.add(trainingprogram_object);
            }
            // 返回值

            object.put("list", trainingprogram_Array);
            object.put("result", "success");
            object.put("results", trainingprogram_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取培养方案信息(下拉列表) _带版本
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatatrainingprogramSelectedition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatatrainingprogramSelectedition(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray trainingprogram_Array = new JSONArray();
            TTrainingProgramExample trainingprogramExample = new TTrainingProgramExample();
            TTrainingProgramExample.Criteria criteria = trainingprogramExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFvalidEqualTo(1);
            trainingprogramExample.setOrderByClause("FName asc");
            List<TTrainingProgram> trainingprogram_list = tTrainingProgramService.findByExample(trainingprogramExample);
            for (TTrainingProgram trainingprogram : trainingprogram_list) {
                JSONObject trainingprogram_object = new JSONObject();
                trainingprogram_object.put("id", ParamTools.getEnParam(trainingprogram.getFkeyid().toString()));
                trainingprogram_object.put("text", trainingprogram.getFname() + "/" + trainingprogram.getFedition());
                trainingprogram_Array.add(trainingprogram_object);
            }
            // 返回值

            object.put("list", trainingprogram_Array);
            object.put("result", "success");
            object.put("results", trainingprogram_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取培养方案信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogramInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogramInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询培养方案信息
            TTrainingProgram trainingprogram = tTrainingProgramService.findById(key);
            JSONObject trainingprogram_object = new JSONObject();
            trainingprogram_object.put("key", ParamTools.getEnParam(trainingprogram.getFkeyid().toString()));
            trainingprogram_object.put("FTMID", ParamTools.getEnParam(trainingprogram.getFtmid().toString()));
            trainingprogram_object.put("FParentId", ParamTools.getEnParam(trainingprogram.getFparentid().toString()));
            TMajor service = tMajorService.findById(trainingprogram.getFtmid());
            trainingprogram_object.put("FTMName", service == null ? "" : service.getFmajorname());
            trainingprogram_object.put("FName", trainingprogram.getFname() == null ? "" : trainingprogram.getFname());
            trainingprogram_object.put("FJS", trainingprogram.getFjs() == null ? "" : trainingprogram.getFjs());
            trainingprogram_object.put("FZYJS", trainingprogram.getFzyjs() == null ? "" : trainingprogram.getFzyjs());
            trainingprogram_object.put("FEditionNo", trainingprogram.getFeditionno());
            trainingprogram_object.put("FEdition", trainingprogram.getFedition());
            trainingprogram_object.put("FValid", trainingprogram.getFvalid());
            trainingprogram_object.put("FCID", trainingprogram.getFcid());
            trainingprogram_object.put("FUID", trainingprogram.getFuid());
            trainingprogram_object.put("FCDATE", trainingprogram.getFcdate());
            trainingprogram_object.put("FUDATE", trainingprogram.getFudate());
            trainingprogram_object.put("FState", trainingprogram.getFstate());
            // 返回值
            object.put("info", trainingprogram_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 查询是否存在培养方案
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogramcz", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogramcz(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");//专业ID
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            TTrainingProgramExample trainingprogramExample = new TTrainingProgramExample();
            TTrainingProgramExample.Criteria criteria = trainingprogramExample.createCriteria();
            criteria.andFtmidEqualTo(key);
            List<TTrainingProgram> trainingProgramList = tTrainingProgramService.findByExample(trainingprogramExample);
            // 返回值
            object.put("isadd", trainingProgramList.size());
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加培养方案信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加培养方案信息")
    @ResponseBody
    @RequestMapping(value = "/addtrainingprogram", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addtrainingprogram(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String Fname = jsonParam.getString("FName");
        String FJS = jsonParam.getString("FJS");
        String FZYJS = jsonParam.getString("FZYJS");
        String fmajorID = jsonParam.getString("fmajorID");
        try {
            fmajorID = fmajorID == null ? "0" : ParamTools.getdeParam(fmajorID);
            TTrainingProgramExample trainingprogramExample = new TTrainingProgramExample();
            TTrainingProgramExample.Criteria criteria = trainingprogramExample.createCriteria();
            criteria.andFnameEqualTo(Fname);
            criteria.andFtmidEqualTo(Long.parseLong(fmajorID));
            criteria.andFvalidEqualTo(1);
            List<TTrainingProgram> programList = tTrainingProgramService.findByExample(trainingprogramExample);
            if (programList.size() == 0) {
                String CookiesLogintrainingprogramID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintrainingprogramID != null && !CookiesLogintrainingprogramID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintrainingprogramID);
                }
                // 新建培养方案信息
                TTrainingProgram trainingprogram = new TTrainingProgram();
                trainingprogram.setFname(Fname);
                trainingprogram.setFjs(FJS);
                trainingprogram.setFzyjs(FZYJS);
                trainingprogram.setFedition("V1.0");
                trainingprogram.setFvalid(1);
                trainingprogram.setFtmid(Long.parseLong(fmajorID));
                trainingprogram.setFcid(Long.parseLong(uid));
                trainingprogram.setFcdate(new Date());
                tTrainingProgramService.save(trainingprogram);
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
     * 修改培养方案信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改培养方案信息")
    @ResponseBody
    @RequestMapping(value = "/updatetrainingprogram", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatetrainingprogram(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String Fname = jsonParam.getString("FName");
        String FJS = jsonParam.getString("FJS");
        String FZYJS = jsonParam.getString("FZYJS");
        String fmajorID = jsonParam.getString("fmajorID");
        try {
            fmajorID = fmajorID == null ? "0" : ParamTools.getdeParam(fmajorID);
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);

            TTrainingProgramExample trainingprogramExample = new TTrainingProgramExample();
            TTrainingProgramExample.Criteria criteria = trainingprogramExample.createCriteria();
            criteria.andFnameEqualTo(Fname);
            criteria.andFkeyidNotEqualTo(key);
            criteria.andFvalidEqualTo(1);
            criteria.andFtmidEqualTo(Long.parseLong(fmajorID));
            List<TTrainingProgram> programList = tTrainingProgramService.findByExample(trainingprogramExample);
            if (programList.size() == 0) {
                String CookiesLogintrainingprogramID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintrainingprogramID != null && !CookiesLogintrainingprogramID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintrainingprogramID);
                }
                // 更新培养方案信息
                TTrainingProgram trainingprogram = new TTrainingProgram();
                trainingprogram.setFkeyid(key);
                trainingprogram.setFname(Fname);
                trainingprogram.setFjs(FJS);
                trainingprogram.setFtmid(Long.parseLong(fmajorID));
                trainingprogram.setFzyjs(FZYJS);
                trainingprogram.setFuid(Long.parseLong(uid));
                trainingprogram.setFudate(new Date());
                tTrainingProgramService.update(trainingprogram);
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
     * 删除培养方案信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除培养方案信息")
    @ResponseBody
    @RequestMapping(value = "/deltrainingprogram", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deltrainingprogram(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLogintrainingprogramID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintrainingprogramID != null && !CookiesLogintrainingprogramID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

//            //查询是否有历史版本 有的话一并删除
//            TTrainingProgramExample trainingprogramExample = new TTrainingProgramExample();
//            trainingprogramExample.or().andFvalidEqualTo(0).andFkeyidEqualTo(Long.parseLong(id));
//            trainingprogramExample.or().andFvalidEqualTo(0).andFparentidEqualTo(Long.parseLong(id));
//            List<TTrainingProgram> trainingProgramList = tTrainingProgramService.findByExample(trainingprogramExample);
//            if(trainingProgramList.size() == 0){
//                TTrainingProgramMbExample tTrainingProgramMbExample = null;
//                List<TTrainingProgramMb> programMbList =null;
//
//                TTrainingProgramByyqExample tTrainingProgramByyqExample =null;
//                List<TTrainingProgramByyq> programByyqList =null;
//
//                TTrainingProgramZgxkExample tTrainingProgramZgxkExample =null;
//                List<TTrainingProgramZgxk> programZgxkList =null;
//
//                TTrainingProgramXzwfExample tTrainingProgramXzwfExample =null;
//                List<TTrainingProgramXzwf> xzwfList =null;
//
//                TTrainingProgramHxkcExample tTrainingProgramHxkcExample =null;
//                List<TTrainingProgramHxkc> programHxkcList =null;
//
//                TTrainingProgramCourseExample tTrainingProgramCourseExample =null;
//                List<TTrainingProgramCourse> courseList =null;
//
//                TTrainingProgramQtExample tTrainingProgramQtExample =null;
//                List<TTrainingProgramQt> programQtList = null;
//
//                TTrainingProgramImageExample tTrainingProgramImageExample = null;
//                List<TTrainingProgramImage> imageList = null;
//                for (TTrainingProgram tTrainingProgram : trainingProgramList) {
//                    tTrainingProgramMbExample = new TTrainingProgramMbExample();
//                    tTrainingProgramMbExample.createCriteria().andFtpidEqualTo(tTrainingProgram.getFkeyid());
//                    programMbList = tTrainingProgramMbService.findByExample(tTrainingProgramMbExample);
//                    if (programMbList.size() > 0) {
//                        tTrainingProgramMbService.deleteByByExample(tTrainingProgramMbExample);
//                    }
//
//                    tTrainingProgramByyqExample = new TTrainingProgramByyqExample();
//                    tTrainingProgramByyqExample.createCriteria().andFtpidEqualTo(tTrainingProgram.getFkeyid());
//
//                    programByyqList = tTrainingProgramByyqService.findByExample(tTrainingProgramByyqExample);
//                    if (programByyqList.size() > 0) {
//                        tTrainingProgramByyqService.deleteByByExample(tTrainingProgramByyqExample);
//                    }
//
//                    tTrainingProgramZgxkExample = new TTrainingProgramZgxkExample();
//                    tTrainingProgramZgxkExample.createCriteria().andFtpidEqualTo(tTrainingProgram.getFkeyid());
//
//                    programZgxkList = tTrainingProgramZgxkService.findByExample(tTrainingProgramZgxkExample);
//                    if (programZgxkList.size() > 0) {
//                        tTrainingProgramZgxkService.deleteByByExample(tTrainingProgramZgxkExample);
//                    }
//
//                    tTrainingProgramXzwfExample = new TTrainingProgramXzwfExample();
//                    tTrainingProgramXzwfExample.createCriteria().andFtpidEqualTo(tTrainingProgram.getFkeyid());
//
//                    xzwfList = tTrainingProgramXzwfService.findByExample(tTrainingProgramXzwfExample);
//                    if (xzwfList.size() > 0) {
//                        tTrainingProgramXzwfService.deleteByByExample(tTrainingProgramXzwfExample);
//                    }
//
//                    tTrainingProgramHxkcExample = new TTrainingProgramHxkcExample();
//                    tTrainingProgramHxkcExample.createCriteria().andFtpidEqualTo(tTrainingProgram.getFkeyid());
//
//                    programHxkcList = tTrainingProgramHxkcService.findByExample(tTrainingProgramHxkcExample);
//                    if (programHxkcList.size() > 0) {
//                        tTrainingProgramHxkcService.deleteByByExample(tTrainingProgramHxkcExample);
//                    }
//
//                    tTrainingProgramCourseExample = new TTrainingProgramCourseExample();
//                    tTrainingProgramCourseExample.createCriteria().andFtpidEqualTo(tTrainingProgram.getFkeyid());
//
//                    courseList = tTrainingProgramCourseService.findByExample(tTrainingProgramCourseExample);
//                    if (courseList.size() > 0) {
//
//                        tTrainingProgramCourseService.deleteByByExample(tTrainingProgramCourseExample);
//                    }
//
//                    tTrainingProgramQtExample = new TTrainingProgramQtExample();
//                    tTrainingProgramQtExample.createCriteria().andFtpidEqualTo(tTrainingProgram.getFkeyid());
//
//                    programQtList = tTrainingProgramQtService.findByExample(tTrainingProgramQtExample);
//                    if (programQtList.size() > 0) {
//                        tTrainingProgramQtService.deleteByByExample(tTrainingProgramQtExample);
//
//                    }
//
//                    tTrainingProgramImageExample = new TTrainingProgramImageExample();
//                    tTrainingProgramImageExample.createCriteria().andFtpidEqualTo(tTrainingProgram.getFkeyid());
//
//                    imageList = tTrainingProgramImageService.findByExample(tTrainingProgramImageExample);
//                    if (imageList.size() > 0) {
//                        tTrainingProgramImageService.deleteByByExample(tTrainingProgramImageExample);
//                    }
//                }
//                tTrainingProgramService.deleteByByExample(trainingprogramExample);
//            }
//            tTrainingProgramService.deleteById(Long.parseLong(id));

            TTrainingProgram trainingprogram = new TTrainingProgram();
            trainingprogram.setFkeyid(Long.parseLong(id));
            trainingprogram.setFvalid(0);
            trainingprogram.setFuid(Long.parseLong(uid));
            trainingprogram.setFcdate(new Date());
            tTrainingProgramService.update(trainingprogram);
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
     * 变更培养方案信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statetrainingprogram", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statetrainingprogram(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLogintrainingprogramID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintrainingprogramID != null && !CookiesLogintrainingprogramID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TTrainingProgram trainingprogram = new TTrainingProgram();
            trainingprogram.setFkeyid(Long.parseLong(id));
            trainingprogram.setFuid(Long.parseLong(uid));
            trainingprogram.setFudate(new Date());
            trainingprogram.setFstate(Integer.valueOf(state));
            tTrainingProgramService.update(trainingprogram);
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
     * 获取培养方案信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogramsql", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogramsql(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String fmjorname = jsonParam.getString("fmjorname");
        String fcollegename = jsonParam.getString("fcollegename");
        String name = jsonParam.getString("name");
        Integer dataall = jsonParam.getInteger("dataall");
//        Integer FValid = jsonParam.getInteger("FValid");
        try {

            // 获取数据库记录
            JSONArray trainingprogram_Array = new JSONArray();
            // 查询条件

            TrainingprogramCS trainingprogramCS = new TrainingprogramCS();
            if (name != null && !name.equals("")) {
                trainingprogramCS.setFName(name);
            }
            if (fmjorname != null && !fmjorname.equals("")) {
                trainingprogramCS.setFMjorName(fmjorname);
            }
            if (fcollegename != null && !fcollegename.equals("")) {
                trainingprogramCS.setFCollegeName(fcollegename);
            }


            trainingprogramCS.setFValid(1);

//            System.out.println(trainingprogramCS.getFValid());
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                trainingprogramCS.setOrderBy(orderSql.substring(1));
            } else {
                trainingprogramCS.setOrderBy("ttp.FCDATE desc");
            }
            PageInfo<TrainingprogramData> trainingprogramPageInfo = tTrainingProgramService.getTrainingProgramDataList(trainingprogramCS, (page - 1) * results, results);
            List<TrainingprogramData> trainingprogram_list = trainingprogramPageInfo.getList();
            for (TrainingprogramData trainingprogram : trainingprogram_list) {
                JSONObject trainingprogram_object = new JSONObject();
                trainingprogram_object.put("key", ParamTools.getEnParam(trainingprogram.getFKeyID().toString()));
                trainingprogram_object.put("FTMID", ParamTools.getEnParam(trainingprogram.getFMjorID().toString()));
                trainingprogram_object.put("FParentId", ParamTools.getEnParam(trainingprogram.getFParentId().toString()));
                if (dataall == 1) {
                    trainingprogram_object.put("FMjorName", trainingprogram.getFMjorName() == null ? "" : trainingprogram.getFMjorName());
                    trainingprogram_object.put("FCollegeName", trainingprogram.getFCollegeName() == null ? "" : trainingprogram.getFCollegeName());
                    trainingprogram_object.put("FName", trainingprogram.getFName() == null ? "" : trainingprogram.getFName());
                    trainingprogram_object.put("FJS", trainingprogram.getFJS() == null ? "" : trainingprogram.getFJS());
                    trainingprogram_object.put("FZYJS", trainingprogram.getFZYJS() == null ? "" : trainingprogram.getFZYJS());
                    trainingprogram_object.put("FEditionNo", trainingprogram.getFEditionNo());
                    trainingprogram_object.put("FEdition", trainingprogram.getFEdition());
                    trainingprogram_object.put("FCDATE", trainingprogram.getFCDATE());
                    trainingprogram_object.put("FUDATE", trainingprogram.getFUDATE());
                } else {
                    trainingprogram_object.put("FName", "*****");
                    trainingprogram_object.put("FCID", "*****");
                    trainingprogram_object.put("FUID", "*****");
                    trainingprogram_object.put("FCDATE", "*****");
                    trainingprogram_object.put("FUDATE", "*****");
                    trainingprogram_object.put("FJS", "*****");
                    trainingprogram_object.put("FZYJS", "*****");
                    trainingprogram_object.put("FEditionNo", "*****");
                    trainingprogram_object.put("FEdition", "*****");
                }
                trainingprogram_object.put("FValid", trainingprogram.getFValid());
                trainingprogram_object.put("FState", trainingprogram.getFState());
                trainingprogram_Array.add(trainingprogram_object);
            }
            // 返回值
            object.put("list", trainingprogram_Array);
            object.put("total", trainingprogramPageInfo.getTotal()); // 总行数
            object.put("page", trainingprogramPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取培养方案信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogramInfosql", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogramInfosql(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            TrainingprogramCS trainingprogramCS = new TrainingprogramCS();
            trainingprogramCS.setFKeyID(key);

            TrainingprogramData trainingprogram = tTrainingProgramService.getTrainingProgramDataInfo(trainingprogramCS);
            // 查询培养方案信息

            JSONObject trainingprogram_object = new JSONObject();
            trainingprogram_object.put("key", ParamTools.getEnParam(trainingprogram.getFKeyID().toString()));
            trainingprogram_object.put("FTMID", ParamTools.getEnParam(trainingprogram.getFMjorID().toString()));
            trainingprogram_object.put("FParentId", ParamTools.getEnParam(trainingprogram.getFParentId().toString()));
            trainingprogram_object.put("FCollegeName", trainingprogram.getFCollegeName());
            trainingprogram_object.put("FMjorName", trainingprogram.getFMjorName());
            trainingprogram_object.put("FName", trainingprogram.getFName());
            trainingprogram_object.put("FJS", trainingprogram.getFJS());
            trainingprogram_object.put("FZYJS", trainingprogram.getFZYJS());
            trainingprogram_object.put("FEditionNo", trainingprogram.getFEditionNo());
            trainingprogram_object.put("FEdition", trainingprogram.getFEdition());
            trainingprogram_object.put("FValid", trainingprogram.getFValid());
            trainingprogram_object.put("FCDATE", trainingprogram.getFCDATE());
            trainingprogram_object.put("FUDATE", trainingprogram.getFUDATE());
            trainingprogram_object.put("FState", trainingprogram.getFState());
            // 返回值
            object.put("info", trainingprogram_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 培养方案版本升级
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("培养方案版本升级")
    @ResponseBody
    @RequestMapping(value = "/upVersiontraniningprogram", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String upVersiontraniningprogram(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String type = jsonParam.getString("type");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }

            TTrainingProgram oldprogram = tTrainingProgramService.findById(key);
            // 获取当前版本信息

            if (oldprogram != null) {
                // 生成新版本
                String version = oldprogram.getFedition().replace("V", "");
                int big, small;
                big = Integer.parseInt(version.substring(0, version.indexOf(".")));
                small = Integer.parseInt(version.substring(version.indexOf(".") + 1, version.length()));
                if (type.equals("1")) { // 大版本
                    version = (big + 1) + ".0";
                } else { // 小版本
                    version = big + "." + (small + 1);
                }
                // 取消历史版本的激活状态

                TTrainingProgramExample tTrainingProgramExample = new TTrainingProgramExample();
                if (oldprogram.getFparentid() == 0) {
                    tTrainingProgramExample.or().andFkeyidEqualTo(oldprogram.getFkeyid());
                    tTrainingProgramExample.or().andFparentidEqualTo(oldprogram.getFkeyid());
                } else {
                    tTrainingProgramExample.or().andFkeyidEqualTo(oldprogram.getFparentid());
                    tTrainingProgramExample.or().andFparentidEqualTo(oldprogram.getFparentid());
                }
                TTrainingProgram VersionActivetrainingprogram = new TTrainingProgram();
                VersionActivetrainingprogram.setFvalid(0);
                tTrainingProgramService.updateByExample(VersionActivetrainingprogram, tTrainingProgramExample);
                // 获取基本信息
                TTrainingProgram tTrainingProgram = new TTrainingProgram();
                if (oldprogram.getFparentid() == 0) {
                    tTrainingProgram.setFparentid(oldprogram.getFkeyid());
                } else {
                    tTrainingProgram.setFparentid(oldprogram.getFparentid());
                }

                tTrainingProgram.setFname(oldprogram.getFname());
                tTrainingProgram.setFjs(oldprogram.getFjs());
                tTrainingProgram.setFzyjs(oldprogram.getFzyjs());
                tTrainingProgram.setFedition("V" + version);
                tTrainingProgram.setFvalid(1);
                tTrainingProgram.setFtmid(oldprogram.getFtmid());
                tTrainingProgram.setFcid(Long.parseLong(uid));
                tTrainingProgram.setFcdate(new Date());
                tTrainingProgramService.save(tTrainingProgram);
                Long newkeyid = tTrainingProgram.getFkeyid();
//                System.out.println("newkeyid：" + newkeyid);
                //把下面挂的内容全部添加进来
                if (newkeyid != null && newkeyid != 0) {
                    TTrainingProgramMbExample tTrainingProgramMbExample = new TTrainingProgramMbExample();
                    tTrainingProgramMbExample.createCriteria().andFtpidEqualTo(oldprogram.getFkeyid());
                    List<TTrainingProgramMb> programMbList = tTrainingProgramMbService.findByExample(tTrainingProgramMbExample);
                    if (programMbList.size() > 0) {

                        for (TTrainingProgramMb programMb : programMbList) {
                            TTrainingProgramMb trainingprogrammb = new TTrainingProgramMb();
                            trainingprogrammb.setFname(programMb.getFname());
                            trainingprogrammb.setFnum(programMb.getFnum());
                            trainingprogrammb.setFtmid(programMb.getFtmid());
                            trainingprogrammb.setFtpid(newkeyid);
                            trainingprogrammb.setFcid(Long.parseLong(uid));
                            trainingprogrammb.setFcdate(new Date());
                            tTrainingProgramMbService.save(trainingprogrammb);
                        }
                    }

                    TTrainingProgramByyqExample tTrainingProgramByyqExample = new TTrainingProgramByyqExample();
                    tTrainingProgramByyqExample.createCriteria().andFtpidEqualTo(oldprogram.getFkeyid());

                    List<TTrainingProgramByyq> programByyqList = tTrainingProgramByyqService.findByExample(tTrainingProgramByyqExample);
                    if (programByyqList.size() > 0) {
                        for (TTrainingProgramByyq tTrainingProgramByyq : programByyqList) {

                            TTrainingProgramByyq trainingprogrambyyq = new TTrainingProgramByyq();
                            trainingprogrambyyq.setFname(tTrainingProgramByyq.getFname());
                            trainingprogrambyyq.setForder(tTrainingProgramByyq.getForder());
                            trainingprogrambyyq.setFtmid(tTrainingProgramByyq.getFtmid());
                            trainingprogrambyyq.setFtpid(newkeyid);
                            trainingprogrambyyq.setFcid(Long.parseLong(uid));
                            trainingprogrambyyq.setFcdate(new Date());
                            tTrainingProgramByyqService.save(trainingprogrambyyq);
                        }
                    }

                    TTrainingProgramZgxkExample tTrainingProgramZgxkExample = new TTrainingProgramZgxkExample();
                    tTrainingProgramZgxkExample.createCriteria().andFtpidEqualTo(oldprogram.getFkeyid());

                    List<TTrainingProgramZgxk> programZgxkList = tTrainingProgramZgxkService.findByExample(tTrainingProgramZgxkExample);
                    if (programZgxkList.size() > 0) {
                        for (TTrainingProgramZgxk tTrainingProgramZgxk : programZgxkList) {
                            TTrainingProgramZgxk trainingprogramzgxk = new TTrainingProgramZgxk();
                            trainingprogramzgxk.setFname(tTrainingProgramZgxk.getFname());
                            trainingprogramzgxk.setForder(tTrainingProgramZgxk.getForder());
                            trainingprogramzgxk.setFtpid(newkeyid);
                            trainingprogramzgxk.setFtmid(tTrainingProgramZgxk.getFtmid());
                            trainingprogramzgxk.setFcid(Long.parseLong(uid));
                            trainingprogramzgxk.setFcdate(new Date());
                            tTrainingProgramZgxkService.save(trainingprogramzgxk);
                        }
                    }

                    TTrainingProgramXzwfExample tTrainingProgramXzwfExample = new TTrainingProgramXzwfExample();
                    tTrainingProgramXzwfExample.createCriteria().andFtpidEqualTo(oldprogram.getFkeyid());

                    List<TTrainingProgramXzwf> xzwfList = tTrainingProgramXzwfService.findByExample(tTrainingProgramXzwfExample);
                    if (xzwfList.size() > 0) {
                        for (TTrainingProgramXzwf xzwf : xzwfList) {
                            TTrainingProgramXzwf trainingprogramxzwf = new TTrainingProgramXzwf();
                            trainingprogramxzwf.setFtmid(xzwf.getFtmid());
                            trainingprogramxzwf.setFtpid(newkeyid);
                            trainingprogramxzwf.setFxz(xzwf.getFxz());
                            trainingprogramxzwf.setFxw(xzwf.getFxw());
                            trainingprogramxzwf.setFxf(xzwf.getFxf());
                            trainingprogramxzwf.setFcid(Long.parseLong(uid));
                            trainingprogramxzwf.setFcdate(new Date());
                            tTrainingProgramXzwfService.save(trainingprogramxzwf);
                        }
                    }

                    TTrainingProgramHxkcExample tTrainingProgramHxkcExample = new TTrainingProgramHxkcExample();
                    tTrainingProgramHxkcExample.createCriteria().andFtpidEqualTo(oldprogram.getFkeyid());

                    List<TTrainingProgramHxkc> programHxkcList = tTrainingProgramHxkcService.findByExample(tTrainingProgramHxkcExample);
                    if (programHxkcList.size() > 0) {
                        for (TTrainingProgramHxkc tTrainingProgramHxkc : programHxkcList) {
                            TTrainingProgramHxkc trainingprogramhxkc = new TTrainingProgramHxkc();
                            trainingprogramhxkc.setFname(tTrainingProgramHxkc.getFname());
                            trainingprogramhxkc.setFtmid(tTrainingProgramHxkc.getFtmid());
                            trainingprogramhxkc.setFtpid(newkeyid);
                            trainingprogramhxkc.setFcourseid(tTrainingProgramHxkc.getFcourseid());
                            trainingprogramhxkc.setForder(tTrainingProgramHxkc.getForder());
                            trainingprogramhxkc.setFcid(Long.parseLong(uid));
                            trainingprogramhxkc.setFcdate(new Date());
                            tTrainingProgramHxkcService.save(trainingprogramhxkc);
                        }
                    }

//                    TTrainingProgramCourseExample tTrainingProgramCourseExample = new TTrainingProgramCourseExample();
//                    tTrainingProgramCourseExample.createCriteria().andFtpidEqualTo(oldprogram.getFkeyid());
//
//                    List<TTrainingProgramCourse> courseList = tTrainingProgramCourseService.findByExample(tTrainingProgramCourseExample);
//                    if (courseList.size() > 0) {
//                        for (TTrainingProgramCourse tTrainingProgramCourse : courseList) {
//                            TTrainingProgramCourse trainingprogramcourse = new TTrainingProgramCourse();
//                            trainingprogramcourse.setFcourseid(tTrainingProgramCourse.getFcourseid());
//                            trainingprogramcourse.setFtmid(tTrainingProgramCourse.getFtmid());
//                            trainingprogramcourse.setFtpid(newkeyid);
//                            trainingprogramcourse.setForder(tTrainingProgramCourse.getForder());
//                            trainingprogramcourse.setFcid(Long.parseLong(uid));
//                            trainingprogramcourse.setFcdate(new Date());
//                            tTrainingProgramCourseService.save(trainingprogramcourse);
//                        }
//
//                    }

                    TTrainingProgramCourseStExample tTrainingProgramCourseStExample = new TTrainingProgramCourseStExample();
                    tTrainingProgramCourseStExample.createCriteria().andFtpidEqualTo(oldprogram.getFkeyid());
                    List<TTrainingProgramCourseSt> courseList = tTrainingProgramCourseStService.findByExample(tTrainingProgramCourseStExample);
                    if (courseList.size() > 0) {
                        for (TTrainingProgramCourseSt tTrainingProgramCourse : courseList) {
                            TTrainingProgramCourseSt trainingprogramcourse = new TTrainingProgramCourseSt();
                            trainingprogramcourse.setFcourseid(tTrainingProgramCourse.getFcourseid());
                            trainingprogramcourse.setFtmid(tTrainingProgramCourse.getFtmid());
                            trainingprogramcourse.setFtpid(newkeyid);
                            trainingprogramcourse.setForder(tTrainingProgramCourse.getForder());
                            trainingprogramcourse.setFxf(tTrainingProgramCourse.getFxf());
                            trainingprogramcourse.setFweeklystudyhours(tTrainingProgramCourse.getFweeklystudyhours());
                            trainingprogramcourse.setFtheoreticalstudyhours(tTrainingProgramCourse.getFtheoreticalstudyhours());
                            trainingprogramcourse.setFtotalhours(tTrainingProgramCourse.getFtotalhours());
                            trainingprogramcourse.setFpracticalstudyhours(tTrainingProgramCourse.getFpracticalstudyhours());
                            trainingprogramcourse.setFcid(Long.parseLong(uid));
                            trainingprogramcourse.setFcdate(new Date());
                            tTrainingProgramCourseStService.save(trainingprogramcourse);

                            TTrainingProgramCourseXnxqStExample tTrainingProgramCourseXnxqStExample = new TTrainingProgramCourseXnxqStExample();
                            TTrainingProgramCourseXnxqStExample.Criteria criteria = tTrainingProgramCourseXnxqStExample.createCriteria();
                            criteria.andFtpidEqualTo(oldprogram.getFkeyid());
                            criteria.andFtpcstidEqualTo(tTrainingProgramCourse.getFkeyid());
                            List<TTrainingProgramCourseXnxqSt> xnxqStList = tTrainingProgramCourseXnxqStService.findByExample(tTrainingProgramCourseXnxqStExample);
                            if (xnxqStList.size() > 0) {
                                for (TTrainingProgramCourseXnxqSt t : xnxqStList) {
                                    TTrainingProgramCourseXnxqSt tTrainingProgramCourseXnxqSt = new TTrainingProgramCourseXnxqSt();
                                    tTrainingProgramCourseXnxqSt.setFcourseid(t.getFcourseid());
                                    tTrainingProgramCourseXnxqSt.setFtpid(newkeyid);
                                    tTrainingProgramCourseXnxqSt.setFtmid(tTrainingProgramCourse.getFtmid());
                                    tTrainingProgramCourseXnxqSt.setFxnxqid(t.getFxnxqid());
                                    tTrainingProgramCourseXnxqSt.setFtpcstid(trainingprogramcourse.getFkeyid());
                                    tTrainingProgramCourseXnxqSt.setFcid(Long.parseLong(uid));
                                    tTrainingProgramCourseXnxqSt.setFcdate(new Date());
                                    tTrainingProgramCourseXnxqStService.save(tTrainingProgramCourseXnxqSt);
                                }
                            }

                        }

                    }

                    TTrainingProgramQtExample tTrainingProgramQtExample = new TTrainingProgramQtExample();
                    tTrainingProgramQtExample.createCriteria().andFtpidEqualTo(oldprogram.getFkeyid());

                    List<TTrainingProgramQt> programQtList = tTrainingProgramQtService.findByExample(tTrainingProgramQtExample);
                    if (programQtList.size() > 0) {
                        for (TTrainingProgramQt p : programQtList) {
                            TTrainingProgramQt trainingprogramqt = new TTrainingProgramQt();
                            trainingprogramqt.setFname(p.getFname());
                            trainingprogramqt.setFdw(p.getFdw());
                            trainingprogramqt.setFtype(p.getFtype());
                            trainingprogramqt.setFjs(p.getFjs());
                            trainingprogramqt.setForder(p.getForder());
                            trainingprogramqt.setFtmid(p.getFtmid());
                            trainingprogramqt.setFtpid(newkeyid);
                            trainingprogramqt.setFcid(Long.parseLong(uid));
                            trainingprogramqt.setFcdate(new Date());
                            tTrainingProgramQtService.save(trainingprogramqt);
                        }

                    }

//                    TTrainingProgramImageExample tTrainingProgramImageExample = new TTrainingProgramImageExample();
//                    tTrainingProgramImageExample.createCriteria().andFtpidEqualTo(oldprogram.getFkeyid());
//
//                    List<TTrainingProgramImage> imageList = tTrainingProgramImageService.findByExample(tTrainingProgramImageExample);
//                    if (imageList.size() > 0) {
//                        for (TTrainingProgramImage t : imageList) {
//                            TTrainingProgramImage tTrainingProgramImage = new TTrainingProgramImage();
//                            tTrainingProgramImage.setFname(t.getFname());
//                            tTrainingProgramImage.setFtmid(t.getFtmid());
//                            tTrainingProgramImage.setFtpid(newkeyid);
//                            tTrainingProgramImage.setFurl(t.getFurl());
//                            tTrainingProgramImage.setFhz(t.getFhz());
//                            tTrainingProgramImage.setFysfilename(t.getFysfilename());
//                            tTrainingProgramImage.setFxtfilename(t.getFxtfilename());
//                            tTrainingProgramImage.setFcdate(new Date());
//                            tTrainingProgramImage.setFcid(Long.parseLong(uid));
//                            tTrainingProgramImage.setFrelativepath1(t.getFrelativepath1());
//                            tTrainingProgramImage.setFrelativepath2(t.getFrelativepath2());
//                            tTrainingProgramImageService.save(tTrainingProgramImage);
//                        }
//                    }
                }
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
     * 培养方案发布
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/fabutrainingprogram", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String fabutrainingprogram(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLogindeptID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogindeptID != null && !CookiesLogindeptID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TTrainingProgram tTrainingProgramServiceById = tTrainingProgramService.findById(Long.parseLong(id));
            TTrainingProgramCourseStExample tTrainingProgramCourseStExample = new TTrainingProgramCourseStExample();
            TTrainingProgramCourseStExample.Criteria stExampleCriteria = tTrainingProgramCourseStExample.createCriteria();
            stExampleCriteria.andFtpidEqualTo(Long.parseLong(id));
            List<TTrainingProgramCourseSt> tTrainingProgramCourseStList = tTrainingProgramCourseStService.findByExample(tTrainingProgramCourseStExample);
            if (tTrainingProgramCourseStList.size() > 0) {
                JSONArray ttrainingprogram_array = new JSONArray();
                int error = 1;
                String courseName= "";
                String errorstr = "";
                for (TTrainingProgramCourseSt t : tTrainingProgramCourseStList) {
                    if (t.getFppstate() != 2 || t.getFyzstate() != 2) {
                        error += 1;
                        courseName += t.getFcoursename() + ",";
                    }
                }
                if (error == 1) {
                    TTrainingProgram tTrainingProgram = new TTrainingProgram();
                    tTrainingProgram.setFkeyid(Long.parseLong(id));
                    tTrainingProgram.setFstate(2);
                    tTrainingProgram.setFcid(Long.parseLong(uid));
                    tTrainingProgram.setFcdate(new Date());
                    tTrainingProgramService.update(tTrainingProgram);
                } else {
//                    object.put("result", "error");
//                    object.put("error", "检测到课程安排中【"+courseName+"】未匹配课程或者验证学分学时，不能发布！");
                    errorstr = "课程安排中【"+courseName+"】未匹配课程或者验证学分学时";
                }
                if(!errorstr.equals("")){
                    JSONObject ttrainingprogram_object = new JSONObject();
                    ttrainingprogram_object.put("FName", tTrainingProgramServiceById.getFname() == null ? "未填写" : tTrainingProgramServiceById.getFname());
                    ttrainingprogram_object.put("error", errorstr);
                    ttrainingprogram_array.add(ttrainingprogram_object);
                }
                // 返回值
                object.put("ttrainingprogramerror", ttrainingprogram_array);
                // 返回值
                object.put("result", "success");
            } else {
                object.put("result", "error");
                object.put("error", "检测到未设置课程安排，不能发布！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 培养方案发布
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/yijianfabutrainingprogram", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String yijianfabutrainingprogram(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);

        try {
            String CookiesLogindeptID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogindeptID != null && !CookiesLogindeptID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }

            TTrainingProgramExample tTrainingProgramExample = new TTrainingProgramExample();
            tTrainingProgramExample.createCriteria().andFstateEqualTo(1).andFvalidEqualTo(1);
            List<TTrainingProgram> tTrainingProgramList = tTrainingProgramService.findByExample(tTrainingProgramExample);
            if (tTrainingProgramList.size() > 0) {
                JSONArray ttrainingprogram_array = new JSONArray();
                for (TTrainingProgram tp : tTrainingProgramList) {
                    String errorstr="";
                    TTrainingProgramCourseStExample tTrainingProgramCourseStExample = new TTrainingProgramCourseStExample();
                    TTrainingProgramCourseStExample.Criteria stExampleCriteria = tTrainingProgramCourseStExample.createCriteria();
                    stExampleCriteria.andFtpidEqualTo(tp.getFkeyid());
                    List<TTrainingProgramCourseSt> tTrainingProgramCourseStList = tTrainingProgramCourseStService.findByExample(tTrainingProgramCourseStExample);
                    if (tTrainingProgramCourseStList.size() > 0) {
                        int error = 1;
                        String courseName = "";
                        for (TTrainingProgramCourseSt t : tTrainingProgramCourseStList) {
                            if (t.getFppstate() != 2 || t.getFyzstate() != 2) {
                                error += 1;
                                courseName +=t.getFcoursename() +",";
                            }
                        }
                        if (error == 1) {
                            TTrainingProgram tTrainingProgram = new TTrainingProgram();
                            tTrainingProgram.setFkeyid(tp.getFkeyid());
                            tTrainingProgram.setFstate(2);
                            tTrainingProgram.setFcid(Long.parseLong(uid));
                            tTrainingProgram.setFcdate(new Date());
                            tTrainingProgramService.update(tTrainingProgram);
                            // 返回值
                            object.put("result", "success");
                        } else {
                            errorstr +="课程安排中【"+courseName+"】未匹配课程或者验证学分学时。";

                        }
                    } else {
                        errorstr +="检测到未设置课程安排。";

                    }
                    if(!errorstr.equals("")){
                        JSONObject ttrainingprogram_object = new JSONObject();
                        ttrainingprogram_object.put("FName", tp.getFname() == null ? "未填写" : tp.getFname());
                        ttrainingprogram_object.put("error", errorstr);
                        ttrainingprogram_array.add(ttrainingprogram_object);
                    }
                }
                // 返回值
                object.put("ttrainingprogramerror", ttrainingprogram_array);
            }

//            TTrainingProgramExample tTrainingProgramExample = new TTrainingProgramExample();
//            tTrainingProgramExample.createCriteria().andFstateEqualTo(1).andFvalidEqualTo(1);
//            TTrainingProgram tTrainingProgram = new TTrainingProgram();
//            tTrainingProgram.setFstate(2);
//            tTrainingProgram.setFcid(Long.parseLong(uid));
//            tTrainingProgram.setFcdate(new Date());
//            tTrainingProgramService.updateByExample(tTrainingProgram, tTrainingProgramExample);
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