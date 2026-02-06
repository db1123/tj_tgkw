package fun.server.controller.trainingprogram;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.model.customQuery.trainingprogram.TrainingprogramkcCS;
import fun.server.model.customQuery.trainingprogram.TrainingprogramkcData;
import fun.server.service.TCourseCategoryService;
import fun.server.service.TCourseService;
import fun.server.service.TTrainingProgramHxkcService;
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
 * 核心课程管理 相关业务处理
 */
@RestController
@RequestMapping("/s/trainingprogramhxkc")
public class TrainingProgramHXKC {

    private final TTrainingProgramHxkcService tTrainingProgramHxkcService;
    private final TCourseService tCourseService;
    private final TCourseCategoryService tCourseCategoryService;
    public TrainingProgramHXKC(TTrainingProgramHxkcService tTrainingProgramHxkcService, TCourseService tCourseService, TCourseCategoryService tCourseCategoryService) {
        this.tTrainingProgramHxkcService = tTrainingProgramHxkcService;
        this.tCourseService = tCourseService;
        this.tCourseCategoryService = tCourseCategoryService;
    }


    /**
     * 获取核心课程信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogramhxkc", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogramhxkc(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        String FTPID = jsonParam.getString("FTPID");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray trainingprogramhxkc_Array = new JSONArray();
            // 查询条件
            TTrainingProgramHxkcExample TTrainingProgramHxkcExample = new TTrainingProgramHxkcExample();
            TTrainingProgramHxkcExample.Criteria criteria = TTrainingProgramHxkcExample.createCriteria();
            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }
            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);
            criteria.andFtpidEqualTo(Long.parseLong(FTPID));
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                TTrainingProgramHxkcExample.setOrderByClause(orderSql.substring(1));
            } else {
                TTrainingProgramHxkcExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TTrainingProgramHxkc> trainingprogramhxkcPageInfo = tTrainingProgramHxkcService.findByExampleMapper(TTrainingProgramHxkcExample, (page - 1) * results, results);
            List<TTrainingProgramHxkc> trainingprogramhxkc_list = trainingprogramhxkcPageInfo.getList();
            for (TTrainingProgramHxkc trainingprogramhxkc : trainingprogramhxkc_list) {
                JSONObject trainingprogramhxkc_object = new JSONObject();
                trainingprogramhxkc_object.put("key", ParamTools.getEnParam(trainingprogramhxkc.getFkeyid().toString()));
                trainingprogramhxkc_object.put("FTMID", ParamTools.getEnParam(trainingprogramhxkc.getFtmid().toString()));
                trainingprogramhxkc_object.put("FTPID", ParamTools.getEnParam(trainingprogramhxkc.getFtpid().toString()));
                trainingprogramhxkc_object.put("FCourseID", ParamTools.getEnParam(trainingprogramhxkc.getFcourseid().toString()));
                if (dataall == 1) {
                    TCourse tCourseServiceById = tCourseService.findById(trainingprogramhxkc.getFcourseid());
                    trainingprogramhxkc_object.put("FName", tCourseServiceById == null ? "" : tCourseServiceById.getFname());
                    trainingprogramhxkc_object.put("FCID", trainingprogramhxkc.getFcid());
                    trainingprogramhxkc_object.put("FUID", trainingprogramhxkc.getFuid());
                    trainingprogramhxkc_object.put("FCDATE", trainingprogramhxkc.getFcdate());
                    trainingprogramhxkc_object.put("FUDATE", trainingprogramhxkc.getFudate());
                } else {
                    trainingprogramhxkc_object.put("FName", "*****");
                    trainingprogramhxkc_object.put("FCID", "*****");
                    trainingprogramhxkc_object.put("FUID", "*****");
                    trainingprogramhxkc_object.put("FCDATE", "*****");
                    trainingprogramhxkc_object.put("FUDATE", "*****");
                }

                trainingprogramhxkc_object.put("FState", trainingprogramhxkc.getFstate());
                trainingprogramhxkc_Array.add(trainingprogramhxkc_object);
            }
            // 返回值
            object.put("list", trainingprogramhxkc_Array);
            object.put("total", trainingprogramhxkcPageInfo.getTotal()); // 总行数
            object.put("page", trainingprogramhxkcPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取核心课程信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatatrainingprogramhxkcSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatatrainingprogramhxkcSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray trainingprogramhxkc_Array = new JSONArray();
            TTrainingProgramHxkcExample trainingprogramhxkcExample = new TTrainingProgramHxkcExample();
            TTrainingProgramHxkcExample.Criteria criteria = trainingprogramhxkcExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            trainingprogramhxkcExample.setOrderByClause("FName asc");
            List<TTrainingProgramHxkc> trainingprogramhxkc_list = tTrainingProgramHxkcService.findByExample(trainingprogramhxkcExample);
            for (TTrainingProgramHxkc trainingprogramhxkc : trainingprogramhxkc_list) {
                JSONObject trainingprogramhxkc_object = new JSONObject();
                trainingprogramhxkc_object.put("id", ParamTools.getEnParam(trainingprogramhxkc.getFkeyid().toString()));
                trainingprogramhxkc_object.put("text", trainingprogramhxkc.getFname());
                trainingprogramhxkc_Array.add(trainingprogramhxkc_object);
            }
            // 返回值

            object.put("list", trainingprogramhxkc_Array);
            object.put("result", "success");
            object.put("results", trainingprogramhxkc_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取核心课程信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogramhxkcInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogramhxkcInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询核心课程信息
            TTrainingProgramHxkc trainingprogramhxkc = tTrainingProgramHxkcService.findById(key);
            JSONObject trainingprogramhxkc_object = new JSONObject();
            trainingprogramhxkc_object.put("key", ParamTools.getEnParam(trainingprogramhxkc.getFkeyid().toString()));
            trainingprogramhxkc_object.put("FTMID", ParamTools.getEnParam(trainingprogramhxkc.getFtmid().toString()));
            trainingprogramhxkc_object.put("FTPID", ParamTools.getEnParam(trainingprogramhxkc.getFtpid().toString()));
            trainingprogramhxkc_object.put("FName", trainingprogramhxkc.getFname() == null ? "" : trainingprogramhxkc.getFname());
            trainingprogramhxkc_object.put("FCID", trainingprogramhxkc.getFcid());
            trainingprogramhxkc_object.put("FUID", trainingprogramhxkc.getFuid());
            trainingprogramhxkc_object.put("FCDATE", trainingprogramhxkc.getFcdate());
            trainingprogramhxkc_object.put("FUDATE", trainingprogramhxkc.getFudate());
            trainingprogramhxkc_object.put("FState", trainingprogramhxkc.getFstate());
            // 返回值
            object.put("info", trainingprogramhxkc_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加核心课程信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加核心课程信息")
    @ResponseBody
    @RequestMapping(value = "/addtrainingprogramhxkc", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addtrainingprogramhxkc(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);

//        String Fname = jsonParam.getString("FName");
        String FTMID = jsonParam.getString("FTMID");
        String FTPID = jsonParam.getString("FTPID");
        String courseids = jsonParam.getString("courseids");


        try {
            FTMID = FTMID == null ? "0" : ParamTools.getdeParam(FTMID);
            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);

            if (courseids != null && !courseids.equals("")) {
                TTrainingProgramHxkcExample tTrainingProgramHxkcExample = new TTrainingProgramHxkcExample();
                tTrainingProgramHxkcExample.createCriteria().andFtmidEqualTo(Long.parseLong(FTMID)).andFtpidEqualTo(Long.parseLong(FTPID));
                tTrainingProgramHxkcExample.setOrderByClause("FOrder desc ");
                int forder = 1;
                List<TTrainingProgramHxkc> hxkcList = tTrainingProgramHxkcService.findByExample(tTrainingProgramHxkcExample);

                if(hxkcList.size() > 0){
                    forder = hxkcList.get(0).getForder() + 1;
                }
                TCourse courseServiceById = null;
                Long fcourseid =0l;
                JSONArray powerRoles_Array = JSONArray.parseArray(courseids);
                for (Object courseid : powerRoles_Array) {
                    fcourseid = Long.parseLong(ParamTools.getdeParam(courseid.toString()));
                    courseServiceById = tCourseService.findById(fcourseid);
                    TTrainingProgramHxkcExample trainingprogramhxkcExample = new TTrainingProgramHxkcExample();
                    TTrainingProgramHxkcExample.Criteria criteria = trainingprogramhxkcExample.createCriteria();
                    criteria.andFcourseidEqualTo(fcourseid);
                    criteria.andFtmidEqualTo(Long.parseLong(FTMID));
                    criteria.andFtpidEqualTo(Long.parseLong(FTPID));
                    List<TTrainingProgramHxkc> programList = tTrainingProgramHxkcService.findByExample(trainingprogramhxkcExample);
                    if (programList.size() == 0) {
                        String CookiesLogintrainingprogramhxkcID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                        String uid = ""; // 当前登录用户ID
                        if (CookiesLogintrainingprogramhxkcID != null && !CookiesLogintrainingprogramhxkcID.equals("")) {
                            uid = ParamTools.getdeParam(CookiesLogintrainingprogramhxkcID);
                        }
                        // 新建核心课程信息
                        TTrainingProgramHxkc trainingprogramhxkc = new TTrainingProgramHxkc();
                        trainingprogramhxkc.setFname(courseServiceById.getFname());
                        trainingprogramhxkc.setFtmid(Long.parseLong(FTMID));
                        trainingprogramhxkc.setFtpid(Long.parseLong(FTPID));
                        trainingprogramhxkc.setFcourseid(fcourseid);
                        trainingprogramhxkc.setForder(forder);
                        trainingprogramhxkc.setFcid(Long.parseLong(uid));
                        trainingprogramhxkc.setFcdate(new Date());
                        tTrainingProgramHxkcService.save(trainingprogramhxkc);
                        forder = forder + 1;
                    }
//                    else {
//                        // 返回值
//                        object.put("result", "fail");
//                    }
                }
            }
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
     * 修改核心课程信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改核心课程信息")
    @ResponseBody
    @RequestMapping(value = "/updatetrainingprogramhxkc", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatetrainingprogramhxkc(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String Fname = jsonParam.getString("FName");
        String FTMID = jsonParam.getString("FTMID");
        String FTPID = jsonParam.getString("FTPID");
        try {
            FTMID = FTMID == null ? "0" : ParamTools.getdeParam(FTMID);
            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);

            TTrainingProgramHxkcExample trainingprogramhxkcExample = new TTrainingProgramHxkcExample();
            TTrainingProgramHxkcExample.Criteria criteria = trainingprogramhxkcExample.createCriteria();
            criteria.andFnameEqualTo(Fname);
            criteria.andFkeyidNotEqualTo(key);
            criteria.andFtmidEqualTo(Long.parseLong(FTMID));
            criteria.andFtpidEqualTo(Long.parseLong(FTPID));
            List<TTrainingProgramHxkc> programList = tTrainingProgramHxkcService.findByExample(trainingprogramhxkcExample);
            if (programList.size() == 0) {
                String CookiesLogintrainingprogramhxkcID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintrainingprogramhxkcID != null && !CookiesLogintrainingprogramhxkcID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintrainingprogramhxkcID);
                }
                // 更新核心课程信息
                TTrainingProgramHxkc trainingprogramhxkc = new TTrainingProgramHxkc();
                trainingprogramhxkc.setFkeyid(key);
                trainingprogramhxkc.setFname(Fname);
                trainingprogramhxkc.setFuid(Long.parseLong(uid));
                trainingprogramhxkc.setFudate(new Date());
                tTrainingProgramHxkcService.update(trainingprogramhxkc);
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
     * 删除核心课程信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除核心课程信息")
    @ResponseBody
    @RequestMapping(value = "/deltrainingprogramhxkc", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deltrainingprogramhxkc(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLogintrainingprogramhxkcID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintrainingprogramhxkcID != null && !CookiesLogintrainingprogramhxkcID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            tTrainingProgramHxkcService.deleteById(Long.parseLong(id));
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
     * 变更核心课程信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statetrainingprogramhxkc", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statetrainingprogramhxkc(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLogintrainingprogramhxkcID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintrainingprogramhxkcID != null && !CookiesLogintrainingprogramhxkcID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TTrainingProgramHxkc trainingprogramhxkc = new TTrainingProgramHxkc();
            trainingprogramhxkc.setFkeyid(Long.parseLong(id));
            trainingprogramhxkc.setFuid(Long.parseLong(uid));
            trainingprogramhxkc.setFudate(new Date());
            trainingprogramhxkc.setFstate(Integer.valueOf(state));
            tTrainingProgramHxkcService.update(trainingprogramhxkc);
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
     * 获取课程信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycourseopen", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycourseopen(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        String fno = jsonParam.getString("fno");
        String ftype = jsonParam.getString("ftype");
        int type = jsonParam.getInteger("type");
        String fnature = jsonParam.getString("fnature");
        Integer dataall = jsonParam.getInteger("dataall");

        String tpID = jsonParam.getString("tpID");
        try {

            // 获取数据库记录
            JSONArray course_Array = new JSONArray();
            // 查询条件

            TrainingprogramkcCS trainingprogramkcCS = new TrainingprogramkcCS();
            tpID = tpID == null ? "0" : ParamTools.getdeParam(tpID);
            trainingprogramkcCS.setFTPID(Long.parseLong(tpID));
            if (name != null && !name.equals("")) {
                trainingprogramkcCS.setFName(name);
            }
            if (fno != null && !fno.equals("")) {
                trainingprogramkcCS.setFNo(fno);
            }
            if (ftype != null && !ftype.equals("") && !ftype.equals("1")) {
                ftype = ftype == null ? "0" :ParamTools.getdeParam(ftype);
                trainingprogramkcCS.setFLTypeID(Long.parseLong(ftype));
            }
            if (fnature != null && !fnature.equals("")) {
                fnature = fnature == null ? "0" :ParamTools.getdeParam(fnature);
                if(!fnature.equals("-1"))
                    trainingprogramkcCS.setFNatureID(Long.parseLong(fnature));
            }
            trainingprogramkcCS.setFType(type);
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                trainingprogramkcCS.setOrderBy(orderSql.substring(1));
            } else {
                trainingprogramkcCS.setOrderBy("tc.FCDATE desc , tc.FNo desc");
            }
            PageInfo<TrainingprogramkcData> coursePageInfo = tTrainingProgramHxkcService.getCourseListopen(trainingprogramkcCS, (page - 1) * results, results);
            List<TrainingprogramkcData> course_list = coursePageInfo.getList();
            for (TrainingprogramkcData course : course_list) {
                JSONObject course_object = new JSONObject();
                course_object.put("key", ParamTools.getEnParam(course.getFKeyID().toString()));
                course_object.put("FType", course.getFTypeID() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(course.getFTypeID().toString()));
                course_object.put("FCNature", course.getFNatureID()  == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(course.getFNatureID().toString()));
                course_object.put("FName", course.getFName() == null ? "" : course.getFName());
                course_object.put("FYWName", course.getFYWName() == null ? "" : course.getFYWName());
                course_object.put("FNo", course.getFNo() == null ? "" : course.getFNo());
                course_object.put("FEdition", course.getFEdition() == null ? "" : course.getFEdition());
                course_object.put("FCDATE", course.getFCDATE());
                course_object.put("FUDATE", course.getFUDATE());
                TCourseCategory tCourseCategory = tCourseCategoryService.findById(course.getFTypeID());
                course_object.put("FTypeName",getPName(tCourseCategory.getFpid(),tCourseCategory.getFname()));
                course_object.put("FCNatureName", course.getFNatureName() == null ? "" : course.getFNatureName());
                course_Array.add(course_object);
            }
            // 返回值
            object.put("list", course_Array);
            object.put("total", coursePageInfo.getTotal()); // 总行数
            object.put("page", coursePageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
    private String getPName(Long pid,String oldname){
        String newname="";
        if(pid == 1){
            newname=oldname;
        }else{
            TCourseCategory courseCategory = tCourseCategoryService.findById(pid);
            if(courseCategory!= null){
                newname = courseCategory.getFname() + "/"+ oldname ;
                getPName(courseCategory.getFpid(),newname);
            }

        }
        return newname;
    }
 
}