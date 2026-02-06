package fun.server.controller.course;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.service.*;
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
 * 毕业要求指标点管理 相关业务处理
 */
@RestController
@RequestMapping("/s/coursegraduation")
public class CourseGraduation {

    private final TCourseGraduationService tCourseGraduationService;
    private final TUserService tUserService;
    private final TCourseTargetService tCourseTargetService;

    private final TTrainingProgramByyqService tTrainingProgramByyqService;

    private final TTrainingProgramService tTrainingProgramService;

    private final TCourseTargetTypeService tCourseTargetTypeService;

    public CourseGraduation(TCourseGraduationService tCourseGraduationService, TUserService tUserService, TCourseTargetService tCourseTargetService, TTrainingProgramByyqService tTrainingProgramByyqService, TTrainingProgramService tTrainingProgramService, TCourseTargetTypeService tCourseTargetTypeService) {
        this.tCourseGraduationService = tCourseGraduationService;
        this.tUserService = tUserService;
        this.tCourseTargetService = tCourseTargetService;
        this.tTrainingProgramByyqService = tTrainingProgramByyqService;
        this.tTrainingProgramService = tTrainingProgramService;
        this.tCourseTargetTypeService = tCourseTargetTypeService;
    }


    /**
     * 获取毕业要求指标点信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycoursegraduation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycoursegraduation(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String FCourseID = jsonParam.getString("FCourseID");
        String name = jsonParam.getString("name");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray coursegraduation_Array = new JSONArray();
            // 查询条件
            TCourseGraduationExample TCourseGraduationExample = new TCourseGraduationExample();
            TCourseGraduationExample.Criteria criteria = TCourseGraduationExample.createCriteria();
            if(name!=null && !name.equals("")){
                criteria.andFnameLike("%"+name+"%");
            }
            FCourseID = FCourseID == null ? "0" : FCourseID.equals("0") ? "" : ParamTools.getdeParam(FCourseID);
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
                TCourseGraduationExample.setOrderByClause(orderSql.substring(1));
            } else {
                TCourseGraduationExample.setOrderByClause("FNum asc");
            }
            PageInfo<TCourseGraduation> coursegraduationPageInfo = tCourseGraduationService.findByExampleMapper(TCourseGraduationExample, (page - 1) * results, results);
            List<TCourseGraduation> coursegraduation_list = coursegraduationPageInfo.getList();
            for (TCourseGraduation coursegraduation : coursegraduation_list) {
                JSONObject coursegraduation_object = new JSONObject();
                coursegraduation_object.put("key", ParamTools.getEnParam(coursegraduation.getFkeyid().toString()));
                coursegraduation_object.put("FCourseID", ParamTools.getEnParam(coursegraduation.getFcourseid().toString()));
                coursegraduation_object.put("FTBYYQID",coursegraduation.getFtbyyqid() == null ? ParamTools.getEnParam("0"): ParamTools.getEnParam(coursegraduation.getFtbyyqid().toString()));
                coursegraduation_object.put("FTPID", coursegraduation.getFtpid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(coursegraduation.getFtpid().toString()));
                TTrainingProgramByyq programByyq = tTrainingProgramByyqService.findById(coursegraduation.getFtbyyqid());
                coursegraduation_object.put("FTBName",programByyq == null ? "" : programByyq.getFname());


                TTrainingProgram programServiceById = tTrainingProgramService.findById(coursegraduation.getFtpid());
                coursegraduation_object.put("FTPName",programServiceById == null ? "" : programServiceById.getFname());

//                coursegraduation_object.put("FCourseCID", ParamTools.getEnParam(coursegraduation.getFcoursecid().toString()));
                if (dataall == 1) {
                    coursegraduation_object.put("FName", coursegraduation.getFname() == null ? "" : coursegraduation.getFname());
                    coursegraduation_object.put("FCourseCID", coursegraduation.getFcoursecid() == null ? "" : coursegraduation.getFcoursecid());
                    String[] split = coursegraduation.getFcoursecid().split(",");
                    String fcoursename= "";
                    TCourseTarget courseTarget = null;
                    for (int i = 0; i < split.length; i++) {
                        courseTarget = tCourseTargetService.findById(Long.valueOf(split[i]));
                        if(courseTarget.getFtype()!=null && !courseTarget.getFtype().equals("")) {
                            TCourseTargetType courseTargetType = tCourseTargetTypeService.findById(courseTarget.getFtype());
                            if(courseTargetType!=null)
                                fcoursename += "课程目标" + courseTarget.getFnum() + "("+courseTargetType.getFname()+"),";
                            else
                                fcoursename += "课程目标" + courseTarget.getFnum() + ",";

                        }
                        else{
                            fcoursename += "课程目标" + courseTarget.getFnum() + ",";
                        }
                    }


                    coursegraduation_object.put("FCourseCIDName",fcoursename);
                    coursegraduation_object.put("FCID", coursegraduation.getFcid());
                    coursegraduation_object.put("FUID", coursegraduation.getFuid());
                    coursegraduation_object.put("FCDATE", coursegraduation.getFcdate());
                    coursegraduation_object.put("FUDATE", coursegraduation.getFudate());
                } else {
                    coursegraduation_object.put("FTBName", "*****");
                    coursegraduation_object.put("FName", "*****");
                    coursegraduation_object.put("FNum", "*****");
                    coursegraduation_object.put("FCID", "*****");
                    coursegraduation_object.put("FUID", "*****");
                    coursegraduation_object.put("FCDATE", "*****");
                    coursegraduation_object.put("FUDATE", "*****");
                }

                coursegraduation_object.put("FState", coursegraduation.getFstate());
                coursegraduation_Array.add(coursegraduation_object);
            }
            // 返回值
            object.put("list", coursegraduation_Array);
            object.put("total", coursegraduationPageInfo.getTotal()); // 总行数
            object.put("page", coursegraduationPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取毕业要求指标点信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatacoursegraduationSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatacoursegraduationSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");


        try {
            // 获取数据库记录
            JSONArray coursegraduation_Array = new JSONArray();
            TCourseGraduationExample coursegraduationExample = new TCourseGraduationExample();
            TCourseGraduationExample.Criteria criteria = coursegraduationExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            coursegraduationExample.setOrderByClause("fname asc");
            List<TCourseGraduation> coursegraduation_list = tCourseGraduationService.findByExample(coursegraduationExample);
            for (TCourseGraduation coursegraduation : coursegraduation_list) {
                JSONObject coursegraduation_object = new JSONObject();
                coursegraduation_object.put("id", ParamTools.getEnParam(coursegraduation.getFkeyid().toString()));
                coursegraduation_object.put("text", coursegraduation.getFname());
                coursegraduation_Array.add(coursegraduation_object);
            }
            // 返回值

            object.put("list", coursegraduation_Array);
            object.put("result", "success");
            object.put("results", coursegraduation_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }




    /**
     * 根据ID获取毕业要求指标点信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycoursegraduationInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycoursegraduationInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询毕业要求指标点信息
            TCourseGraduation coursegraduation = tCourseGraduationService.findById(key);
            JSONObject coursegraduation_object = new JSONObject();
            coursegraduation_object.put("key", ParamTools.getEnParam(coursegraduation.getFkeyid().toString()));
            coursegraduation_object.put("FName", coursegraduation.getFname());
            coursegraduation_object.put("FCourseID", ParamTools.getEnParam(coursegraduation.getFcourseid().toString()));
            coursegraduation_object.put("FTBYYQID",coursegraduation.getFtbyyqid() == null ? ParamTools.getEnParam("0"): ParamTools.getEnParam(coursegraduation.getFtbyyqid().toString()));
            coursegraduation_object.put("FTPID", coursegraduation.getFtpid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(coursegraduation.getFtpid().toString()));
            TTrainingProgramByyq programByyq = tTrainingProgramByyqService.findById(coursegraduation.getFtbyyqid());
            coursegraduation_object.put("FTBName",programByyq == null ? "" : programByyq.getFname());
            TTrainingProgram programServiceById = tTrainingProgramService.findById(coursegraduation.getFtpid());
            coursegraduation_object.put("FTPName",programServiceById == null ? "" : programServiceById.getFname());
            coursegraduation_object.put("FTPEdition",programServiceById == null ? "" : "/" + programServiceById.getFedition());

//            coursegraduation_object.put("FCourseCID", ParamTools.getEnParam(coursegraduation.getFcoursecid().toString()));
            String FCourseCID = "";
            if(coursegraduation.getFcoursecid()!=null && !coursegraduation.getFcoursecid().equals("")){
                String[] split = coursegraduation.getFcoursecid().split(",");
                for (Object term : split) {
                    FCourseCID += ParamTools.getEnParam(term.toString()) + ",";
                }
            }
            coursegraduation_object.put("FCourseCID", FCourseCID);
            coursegraduation_object.put("FCID", coursegraduation.getFcid());
            coursegraduation_object.put("FUID", coursegraduation.getFuid());
            coursegraduation_object.put("FCDATE", coursegraduation.getFcdate());
            coursegraduation_object.put("FUDATE", coursegraduation.getFudate());
            coursegraduation_object.put("FState", coursegraduation.getFstate());
            // 返回值
            object.put("info", coursegraduation_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取毕业要求指标点信息xx
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycoursegraduationInfoxx", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycoursegraduationInfoxx(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询毕业要求指标点信息
            TCourseGraduation coursegraduation = tCourseGraduationService.findById(key);
            JSONObject coursegraduation_object = new JSONObject();
            JSONArray array = new JSONArray();
            coursegraduation_object.put("key", ParamTools.getEnParam(coursegraduation.getFkeyid().toString()));
            coursegraduation_object.put("FName", coursegraduation.getFname());
            coursegraduation_object.put("FCourseID", ParamTools.getEnParam(coursegraduation.getFcourseid().toString()));
            coursegraduation_object.put("FTBYYQID",coursegraduation.getFtbyyqid() == null ? ParamTools.getEnParam("0"): ParamTools.getEnParam(coursegraduation.getFtbyyqid().toString()));
            coursegraduation_object.put("FTPID", coursegraduation.getFtpid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(coursegraduation.getFtpid().toString()));
            TTrainingProgramByyq programByyq = tTrainingProgramByyqService.findById(coursegraduation.getFtbyyqid());
            coursegraduation_object.put("FTBName",programByyq == null ? "" : programByyq.getFname());
            TTrainingProgram programServiceById = tTrainingProgramService.findById(coursegraduation.getFtpid());
            coursegraduation_object.put("FTPName",programServiceById == null ? "" : programServiceById.getFname());
            String FCourseCID = "";
            if(coursegraduation.getFcoursecid()!=null && !coursegraduation.getFcoursecid().equals("")){
                JSONObject ttt_object = null;
                String[] split = coursegraduation.getFcoursecid().split(",");
                for (Object term : split) {
                    ttt_object = new JSONObject();
                    TCourseTarget tCourseTarget = tCourseTargetService.findById(Long.valueOf(term.toString()));
                    if(tCourseTarget.getFtype()!=null && !tCourseTarget.getFtype().equals("")) {
                        TCourseTargetType courseTargetType = tCourseTargetTypeService.findById(tCourseTarget.getFtype());
                        ttt_object.put("FTypeName", courseTargetType == null ? "" : courseTargetType.getFname());
                    }else{
                        ttt_object.put("FTypeName", "");
                    }
                    ttt_object.put("FName", tCourseTarget == null ? "" : tCourseTarget.getFname());
                    ttt_object.put("FNum", tCourseTarget == null ? 1 : tCourseTarget.getFnum());
                    array.add(ttt_object);
                    FCourseCID += ParamTools.getEnParam(term.toString()) + ",";
                }
            }
            coursegraduation_object.put("FCourseCID", FCourseCID);
            coursegraduation_object.put("FCID", coursegraduation.getFcid());
            coursegraduation_object.put("FUID", coursegraduation.getFuid());
            coursegraduation_object.put("FCDATE", coursegraduation.getFcdate());
            coursegraduation_object.put("FUDATE", coursegraduation.getFudate());
            coursegraduation_object.put("FState", coursegraduation.getFstate());
            coursegraduation_object.put("CoursecNamelist", array);
            // 返回值
            object.put("info", coursegraduation_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加毕业要求指标点信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加毕业要求指标点信息")
    @ResponseBody
    @RequestMapping(value = "/addcoursegraduation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addcoursegraduation(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
//        String FName = jsonParam.getString("FName");
        String FCourseID = jsonParam.getString("FCourseID");
        String FTBYYQID = jsonParam.getString("FTBYYQID");
        String FTPID = jsonParam.getString("FTPID");
        String FCourseCID = jsonParam.getString("fcoursecid");
        try {
            FCourseID = FCourseID == null ? "0" : ParamTools.getdeParam(FCourseID);
            FTBYYQID = FTBYYQID == null ? "0" : ParamTools.getdeParam(FTBYYQID);
            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);
            String fcid ="";
            if(FCourseCID!= null && !FCourseCID.equals("")){
                JSONArray powerterm_Array = JSONArray.parseArray(FCourseCID);
                for (Object term : powerterm_Array) {
                    fcid += ParamTools.getdeParam(term.toString()) + ",";
                }
            }
            TCourseGraduationExample coursegraduationExample = new TCourseGraduationExample();
            TCourseGraduationExample.Criteria criteria = coursegraduationExample.createCriteria();
//            criteria.andFnameEqualTo(FName);
            criteria.andFcourseidEqualTo(Long.parseLong(FCourseID));
            criteria.andFtbyyqidEqualTo(Long.parseLong(FTBYYQID));
            List<TCourseGraduation> levelList = tCourseGraduationService.findByExample(coursegraduationExample);
            if (levelList.size() == 0) {
                String CookiesLogincoursegraduationID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogincoursegraduationID != null && !CookiesLogincoursegraduationID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogincoursegraduationID);
                }
                // 新建毕业要求指标点信息
                TCourseGraduation coursegraduation = new TCourseGraduation();
//                coursegraduation.setFname(FName);
                coursegraduation.setFtbyyqid(Long.parseLong(FTBYYQID));
                coursegraduation.setFtpid(Long.parseLong(FTPID));
                coursegraduation.setFcoursecid(fcid);
                coursegraduation.setFcourseid(Long.parseLong(FCourseID));
                coursegraduation.setFcid(Long.parseLong(uid));
                coursegraduation.setFcdate(new Date());
                tCourseGraduationService.save(coursegraduation);
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
     * 修改毕业要求指标点信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改毕业要求指标点信息")
    @ResponseBody
    @RequestMapping(value = "/updatecoursegraduation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatecoursegraduation(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
//        String FName = jsonParam.getString("FName");
        String FCourseID = jsonParam.getString("FCourseID");
        String FCourseCID = jsonParam.getString("fcoursecid");
        String FTBYYQID = jsonParam.getString("FTBYYQID");
        String FTPID = jsonParam.getString("FTPID");
        try {
            FCourseID = FCourseID == null ? "0" : ParamTools.getdeParam(FCourseID);
            FTBYYQID = FTBYYQID == null ? "0" : ParamTools.getdeParam(FTBYYQID);
            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            String fcid ="";
            if(FCourseCID!= null && !FCourseCID.equals("")){
                JSONArray powerterm_Array = JSONArray.parseArray(FCourseCID);
                for (Object term : powerterm_Array) {
                    fcid += ParamTools.getdeParam(term.toString()) + ",";
                }
            }
            TCourseGraduationExample coursegraduationExample = new TCourseGraduationExample();
            TCourseGraduationExample.Criteria criteria = coursegraduationExample.createCriteria();
//            criteria.andFnameEqualTo(FName);
            criteria.andFcourseidEqualTo(Long.parseLong(FCourseID));
            criteria.andFtbyyqidEqualTo(Long.parseLong(FTBYYQID));
            criteria.andFkeyidNotEqualTo(key);
            List<TCourseGraduation> levelList = tCourseGraduationService.findByExample(coursegraduationExample);
            if (levelList.size() == 0) {
                String CookiesLogincoursegraduationID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogincoursegraduationID != null && !CookiesLogincoursegraduationID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogincoursegraduationID);
                }
                // 更新毕业要求指标点信息
                TCourseGraduation coursegraduation = new TCourseGraduation();
                coursegraduation.setFkeyid(key);
//                coursegraduation.setFname(FName);
                coursegraduation.setFtbyyqid(Long.parseLong(FTBYYQID));
                coursegraduation.setFtpid(Long.parseLong(FTPID));
                coursegraduation.setFcoursecid(fcid);
                coursegraduation.setFuid(Long.parseLong(uid));
                coursegraduation.setFudate(new Date());
                tCourseGraduationService.update(coursegraduation);
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
     * 删除毕业要求指标点信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除毕业要求指标点信息")
    @ResponseBody
    @RequestMapping(value = "/delcoursegraduation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delcoursegraduation(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            tCourseGraduationService.deleteById(Long.parseLong(id));
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