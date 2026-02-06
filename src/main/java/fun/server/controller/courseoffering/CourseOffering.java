package fun.server.controller.courseoffering;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.model.customQuery.courseoffering.courseOfferingCS;
import fun.server.model.customQuery.courseoffering.courseOfferingData;
import fun.server.model.customQuery.courseoffering.courseOfferingopenCS;
import fun.server.service.TCourseCategoryService;
import fun.server.service.TCourseNatureService;
import fun.server.service.TCourseOfferingService;
import fun.server.service.TCourseScheduleService;
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
 * 开课管理 相关业务处理
 */
@RestController
@RequestMapping("/s/courseoffering")
public class CourseOffering {

    private final TCourseOfferingService tCourseOfferingService;
    private final TCourseCategoryService tCourseCategoryService;
    private final TCourseNatureService tCourseNatureService;
    private final TCourseScheduleService tCourseScheduleService;
    public CourseOffering(TCourseOfferingService tCourseOfferingService, TCourseCategoryService tCourseCategoryService, TCourseNatureService tCourseNatureService, TCourseScheduleService tCourseScheduleService) {
        this.tCourseOfferingService = tCourseOfferingService;
        this.tCourseCategoryService = tCourseCategoryService;
        this.tCourseNatureService = tCourseNatureService;
        this.tCourseScheduleService = tCourseScheduleService;
    }


    /**
     * 获取开课信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycourseoffering", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycourseoffering(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String FTPName = jsonParam.getString("FTPName");
        String FCourseName = jsonParam.getString("FCourseName");
        String FCourseNo = jsonParam.getString("FCourseNo");
        String FSemester = jsonParam.getString("FSemester");
        String FTeacher = jsonParam.getString("FTeacher");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray courseoffering_Array = new JSONArray();
            // 查询条件
            courseOfferingCS courseOfferingCS = new courseOfferingCS();
            courseOfferingCS.setFTPName(FTPName);
            courseOfferingCS.setFCourseName(FCourseName);
            courseOfferingCS.setFCourseNo(FCourseNo);
            courseOfferingCS.setFSemesterName(FSemester);
            courseOfferingCS.setFTeacherName(FTeacher);

            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                courseOfferingCS.setOrderBy(orderSql.substring(1));
            } else {
                courseOfferingCS.setOrderBy("tco.FCDATE desc");
            }
            PageInfo<courseOfferingData> courseofferingPageInfo = tCourseOfferingService.getTCourseOfferingList(courseOfferingCS, (page - 1) * results, results);
            List<courseOfferingData> courseoffering_list = courseofferingPageInfo.getList();

            for (courseOfferingData courseoffering : courseoffering_list) {
                JSONObject courseoffering_object = new JSONObject();
                courseoffering_object.put("key", ParamTools.getEnParam(courseoffering.getFKeyID().toString()));
                courseoffering_object.put("FTPID", ParamTools.getEnParam(courseoffering.getFTPID().toString()));
                courseoffering_object.put("FCourseID", ParamTools.getEnParam(courseoffering.getFCourseID().toString()));
                courseoffering_object.put("FSemester", ParamTools.getEnParam(courseoffering.getFSemester().toString()));
                courseoffering_object.put("FTeacher", ParamTools.getEnParam(courseoffering.getFTeacher().toString()));
                if (dataall == 1) {
                    courseoffering_object.put("FMaxCapacity", courseoffering.getFMaxCapacity());
                    courseoffering_object.put("FCurrentEnrollment", courseoffering.getFCurrentEnrollment());
                    courseoffering_object.put("FStatus", courseoffering.getFStatus());
                    courseoffering_object.put("FNote", courseoffering.getFNote());
                    courseoffering_object.put("FTPName", courseoffering.getFTPName());
                    courseoffering_object.put("FCourseName", courseoffering.getFCourseName());
                    courseoffering_object.put("FCourseNo", courseoffering.getFCourseNo());
                    courseoffering_object.put("FSemesterName", courseoffering.getFSemesterName());
                    courseoffering_object.put("FTeacherName", courseoffering.getFTeacherName());
                    courseoffering_object.put("FCID", courseoffering.getFCID());
                    courseoffering_object.put("FUID", courseoffering.getFUID());
                    courseoffering_object.put("FCDATE", courseoffering.getFCDATE());
                    courseoffering_object.put("FUDATE", courseoffering.getFUDATE());
                    courseoffering_object.put("FName", courseoffering.getFTPName() + "/" + courseoffering.getFCourseName());
                } else {
                    courseoffering_object.put("FName", "*****");
                    courseoffering_object.put("FCID", "*****");
                    courseoffering_object.put("FUID", "*****");
                    courseoffering_object.put("FCDATE", "*****");
                    courseoffering_object.put("FUDATE", "*****");
                }

                courseoffering_object.put("FState", courseoffering.getFState());
                courseoffering_Array.add(courseoffering_object);
            }
            // 返回值
            object.put("list", courseoffering_Array);
            object.put("total", courseofferingPageInfo.getTotal()); // 总行数
            object.put("page", courseofferingPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取开课信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycourseofferingopen", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycourseofferingopen(HttpServletRequest request)
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
        String fnature = jsonParam.getString("fnature");

        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray courseoffering_Array = new JSONArray();
            // 查询条件
            courseOfferingopenCS courseOfferingopenCS = new courseOfferingopenCS();
            courseOfferingopenCS.setFCourseName(name);
            courseOfferingopenCS.setFCourseNo(fno);
            if (ftype != null && !ftype.equals("") && !ftype.equals("1")) {
                ftype = ftype == null ? "0" :ParamTools.getdeParam(ftype);
                courseOfferingopenCS.setFLTypeID(Long.parseLong(ftype));
            }

            if (fnature != null && !fnature.equals("")) {
                fnature = fnature == null ? "0" :ParamTools.getdeParam(fnature);
                if(!fnature.equals("-1"))
                    courseOfferingopenCS.setFNatureID(Long.parseLong(fnature));
            }
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                courseOfferingopenCS.setOrderBy(orderSql.substring(1));
            } else {
                courseOfferingopenCS.setOrderBy("tco.FCDATE desc");
            }
            PageInfo<courseOfferingData> courseofferingPageInfo = tCourseOfferingService.getTCourseOfferingListopen(courseOfferingopenCS, (page - 1) * results, results);
            List<courseOfferingData> courseoffering_list = courseofferingPageInfo.getList();

            for (courseOfferingData courseoffering : courseoffering_list) {
                JSONObject courseoffering_object = new JSONObject();
                courseoffering_object.put("key", ParamTools.getEnParam(courseoffering.getFKeyID().toString()));
                courseoffering_object.put("FTPID", ParamTools.getEnParam(courseoffering.getFTPID().toString()));
                courseoffering_object.put("FCourseID", ParamTools.getEnParam(courseoffering.getFCourseID().toString()));
                courseoffering_object.put("FSemester", ParamTools.getEnParam(courseoffering.getFSemester().toString()));
                courseoffering_object.put("FTeacher", ParamTools.getEnParam(courseoffering.getFTeacher().toString()));
                courseoffering_object.put("FCourseFType", ParamTools.getEnParam(courseoffering.getFCourseFType().toString()));
                courseoffering_object.put("FCourseNatureID", ParamTools.getEnParam(courseoffering.getFCourseNatureID().toString()));
                if (dataall == 1) {
                    TCourseCategory tCourseCategory = tCourseCategoryService.findById(courseoffering.getFCourseFType());
                    courseoffering_object.put("FTypeName",getPName(tCourseCategory.getFpid(),tCourseCategory.getFname()));
                    TCourseNature courseNature = tCourseNatureService.findById(courseoffering.getFCourseNatureID());
                    courseoffering_object.put("FCNatureName", courseNature == null ? "" : courseNature.getFname());
                    courseoffering_object.put("FMaxCapacity", courseoffering.getFMaxCapacity());
                    courseoffering_object.put("FCurrentEnrollment", courseoffering.getFCurrentEnrollment());
                    courseoffering_object.put("FStatus", courseoffering.getFStatus());
                    courseoffering_object.put("FNote", courseoffering.getFNote());
                    courseoffering_object.put("FTPName", courseoffering.getFTPName());
                    courseoffering_object.put("FCourseName", courseoffering.getFCourseName());
                    courseoffering_object.put("FCourseNo", courseoffering.getFCourseNo());
                    courseoffering_object.put("FEdition", courseoffering.getFEdition());
                    courseoffering_object.put("FSemesterName", courseoffering.getFSemesterName());
                    courseoffering_object.put("FTeacherName", courseoffering.getFTeacherName());
                    courseoffering_object.put("FCID", courseoffering.getFCID());
                    courseoffering_object.put("FUID", courseoffering.getFUID());
                    courseoffering_object.put("FCDATE", courseoffering.getFCDATE());
                    courseoffering_object.put("FUDATE", courseoffering.getFUDATE());
                    courseoffering_object.put("FName", courseoffering.getFTPName() + "/" + courseoffering.getFCourseName());
                } else {
                    courseoffering_object.put("FName", "*****");
                    courseoffering_object.put("FCID", "*****");
                    courseoffering_object.put("FUID", "*****");
                    courseoffering_object.put("FCDATE", "*****");
                    courseoffering_object.put("FUDATE", "*****");
                }

                courseoffering_object.put("FState", courseoffering.getFState());
                courseoffering_Array.add(courseoffering_object);
            }
            // 返回值
            object.put("list", courseoffering_Array);
            object.put("total", courseofferingPageInfo.getTotal()); // 总行数
            object.put("page", courseofferingPageInfo.getPageNum()); // 当前页数
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

    /**
     * 获取开课信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatacourseofferingSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatacourseofferingSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray courseoffering_Array = new JSONArray();
            TCourseOfferingExample courseofferingExample = new TCourseOfferingExample();
            TCourseOfferingExample.Criteria criteria = courseofferingExample.createCriteria();

            criteria.andFstateEqualTo(1);
            courseofferingExample.setOrderByClause("FCDATE desc,FCollegeName asc");
            List<TCourseOffering> courseoffering_list = tCourseOfferingService.findByExample(courseofferingExample);
            for (TCourseOffering courseoffering : courseoffering_list) {
                JSONObject courseoffering_object = new JSONObject();
                courseoffering_object.put("id", ParamTools.getEnParam(courseoffering.getFkeyid().toString()));
//                courseoffering_object.put("text", courseoffering.getFcourseofferingname());
                courseoffering_Array.add(courseoffering_object);
            }
            // 返回值
            object.put("list", courseoffering_Array);
            object.put("results", courseoffering_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取开课信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatacourseofferingSelectall", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatacourseofferingSelectall(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray courseoffering_Array = new JSONArray();
            TCourseOfferingExample courseofferingExample = new TCourseOfferingExample();
            TCourseOfferingExample.Criteria criteria = courseofferingExample.createCriteria();

            criteria.andFstateEqualTo(1);
            courseofferingExample.setOrderByClause("FCDATE desc");
            JSONObject courseoffering_object = new JSONObject();
            courseoffering_object.put("id", "-1");
            courseoffering_object.put("text", "全部");
            courseoffering_Array.add(courseoffering_object);
            List<TCourseOffering> courseoffering_list = tCourseOfferingService.findByExample(courseofferingExample);
            for (TCourseOffering courseoffering : courseoffering_list) {
                courseoffering_object = new JSONObject();
                courseoffering_object.put("id", ParamTools.getEnParam(courseoffering.getFkeyid().toString()));
//                courseoffering_object.put("text", courseoffering.getFcourseofferingname());
                courseoffering_Array.add(courseoffering_object);
            }
            // 返回值

            object.put("list", courseoffering_Array);
            object.put("results", courseoffering_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取开课信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querycourseofferingInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querycourseofferingInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询开课信息
            courseOfferingCS courseOfferingCS = new courseOfferingCS();
            courseOfferingCS.setFKeyID(key);
            courseOfferingData courseoffering = tCourseOfferingService.getTCourseOfferingInfo(courseOfferingCS);
            JSONObject courseoffering_object = new JSONObject();
            courseoffering_object.put("key", ParamTools.getEnParam(courseoffering.getFKeyID().toString()));
            courseoffering_object.put("FTPID", ParamTools.getEnParam(courseoffering.getFTPID().toString()));
            courseoffering_object.put("FCourseID", ParamTools.getEnParam(courseoffering.getFCourseID().toString()));
            courseoffering_object.put("FSemester", ParamTools.getEnParam(courseoffering.getFSemester().toString()));
            courseoffering_object.put("FTeacher", ParamTools.getEnParam(courseoffering.getFTeacher().toString()));
            courseoffering_object.put("FMaxCapacity", courseoffering.getFMaxCapacity());
            courseoffering_object.put("FCurrentEnrollment", courseoffering.getFCurrentEnrollment());
            courseoffering_object.put("FStatus", courseoffering.getFStatus());
            courseoffering_object.put("FNote", courseoffering.getFNote());
            courseoffering_object.put("FTPName", courseoffering.getFTPName());
            courseoffering_object.put("FCourseName", courseoffering.getFCourseName());
            courseoffering_object.put("FSemesterName", courseoffering.getFSemesterName());
            courseoffering_object.put("FTeacherName", courseoffering.getFTeacherName());
            courseoffering_object.put("FCID", courseoffering.getFCID());
            courseoffering_object.put("FUID", courseoffering.getFUID());
            courseoffering_object.put("FCDATE", courseoffering.getFCDATE());
            courseoffering_object.put("FUDATE", courseoffering.getFUDATE());
            // 返回值
            object.put("info", courseoffering_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加开课信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加开课信息")
    @ResponseBody
    @RequestMapping(value = "/addcourseoffering", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addcourseoffering(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FTPID = jsonParam.getString("FTPID");
        String FCourseID = jsonParam.getString("FCourseID");
        String FSemester = jsonParam.getString("FSemester");
        String FTeacher = jsonParam.getString("FTeacher");
        Integer FMaxCapacity = jsonParam.getInteger("FMaxCapacity");
        String FNote = jsonParam.getString("FNote");

        try {

            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);
            FCourseID = FCourseID == null ? "0" : ParamTools.getdeParam(FCourseID);
            FSemester = FSemester == null ? "0" : ParamTools.getdeParam(FSemester);
            FTeacher = FTeacher == null ? "0" : ParamTools.getdeParam(FTeacher);

            TCourseOfferingExample tCourseOfferingExample = new TCourseOfferingExample();
            TCourseOfferingExample.Criteria criteria = tCourseOfferingExample.createCriteria();
            criteria.andFtpidEqualTo(Long.parseLong(FTPID));
            criteria.andFcourseidEqualTo(Long.parseLong(FCourseID));
            criteria.andFsemesterEqualTo(Long.parseLong(FSemester));
            criteria.andFstatusLessThan(3);//不是已结束的情况下
            List<TCourseOffering> tCourseOfferingList = tCourseOfferingService.findByExample(tCourseOfferingExample);
            if (tCourseOfferingList.size() > 0) {
                // 返回值
                object.put("result", "fail");
            } else {
                String CookiesLogincourseofferingID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogincourseofferingID != null && !CookiesLogincourseofferingID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogincourseofferingID);
                }
                // 新建开课信息
                TCourseOffering courseoffering = new TCourseOffering();
                courseoffering.setFtpid(Long.parseLong(FTPID));
                courseoffering.setFcourseid(Long.parseLong(FCourseID));
                courseoffering.setFsemester(Long.parseLong(FSemester));
                courseoffering.setFteacher(Long.parseLong(FTeacher));
                courseoffering.setFmaxcapacity(FMaxCapacity);
                courseoffering.setFstatus(0);
                courseoffering.setFnote(FNote);
                courseoffering.setFcid(Long.parseLong(uid));
                courseoffering.setFcdate(new Date());
                tCourseOfferingService.save(courseoffering);
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
     * 修改开课信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改开课信息")
    @ResponseBody
    @RequestMapping(value = "/updatecourseoffering", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatecourseoffering(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FTPID = jsonParam.getString("FTPID");
        String FCourseID = jsonParam.getString("FCourseID");
        String FSemester = jsonParam.getString("FSemester");
        String FTeacher = jsonParam.getString("FTeacher");
        Integer FMaxCapacity = jsonParam.getInteger("FMaxCapacity");
        String FNote = jsonParam.getString("FNote");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);
            FCourseID = FCourseID == null ? "0" : ParamTools.getdeParam(FCourseID);
            FSemester = FSemester == null ? "0" : ParamTools.getdeParam(FSemester);
            FTeacher = FTeacher == null ? "0" : ParamTools.getdeParam(FTeacher);
            TCourseOfferingExample tCourseOfferingExample = new TCourseOfferingExample();
            TCourseOfferingExample.Criteria criteria = tCourseOfferingExample.createCriteria();
            criteria.andFtpidEqualTo(Long.parseLong(FTPID));
            criteria.andFcourseidEqualTo(Long.parseLong(FCourseID));
            criteria.andFsemesterEqualTo(Long.parseLong(FSemester));
            criteria.andFkeyidNotEqualTo(key);
            criteria.andFstatusLessThan(3);//不是已结束的情况下
            List<TCourseOffering> tCourseOfferingList = tCourseOfferingService.findByExample(tCourseOfferingExample);
            if (tCourseOfferingList.size() > 0) {
                // 返回值
                object.put("result", "fail");
            } else {
                String CookiesLogincourseofferingID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogincourseofferingID != null && !CookiesLogincourseofferingID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogincourseofferingID);
                }
                // 更新开课信息
                TCourseOffering courseoffering = new TCourseOffering();
                courseoffering.setFkeyid(key);
                courseoffering.setFtpid(Long.parseLong(FTPID));
                courseoffering.setFcourseid(Long.parseLong(FCourseID));
                courseoffering.setFsemester(Long.parseLong(FSemester));
                courseoffering.setFteacher(Long.parseLong(FTeacher));
                courseoffering.setFmaxcapacity(FMaxCapacity);
                courseoffering.setFnote(FNote);
                courseoffering.setFuid(Long.parseLong(uid));
                courseoffering.setFudate(new Date());
                tCourseOfferingService.update(courseoffering);
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
     * 删除开课信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除开课信息")
    @ResponseBody
    @RequestMapping(value = "/delcourseoffering", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delcourseoffering(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            TCourseOffering courseOffering = tCourseOfferingService.findById(Long.parseLong(id));
            if (courseOffering.getFstatus() == 0) {
                tCourseOfferingService.deleteById(Long.parseLong(id));
                // 返回值
                object.put("result", "success");
            }else if (courseOffering.getFstatus() == 1) {
                object.put("result", "error");
                object.put("error", "开课申请审核中，不能删除！");
            }else if (courseOffering.getFstatus() > 1) {
                object.put("result", "error");
                object.put("error", "课程开放中，不能删除！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 变更开课信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statecourseoffering", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statecourseoffering(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLogincourseofferingID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogincourseofferingID != null && !CookiesLogincourseofferingID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TCourseOffering courseoffering = new TCourseOffering();
            courseoffering.setFkeyid(Long.parseLong(id));
            courseoffering.setFuid(Long.parseLong(uid));
            courseoffering.setFudate(new Date());
            courseoffering.setFstate(Integer.valueOf(state));
            tCourseOfferingService.update(courseoffering);
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
     * 开课提交
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/kaikecourseoffering", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String kaikecourseoffering(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLogincourseofferingID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogincourseofferingID != null && !CookiesLogincourseofferingID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            TCourseScheduleExample tCourseScheduleExample = new TCourseScheduleExample();
            tCourseScheduleExample.createCriteria().andFcoidEqualTo(Long.parseLong(id));
            List<TCourseSchedule> tCourseScheduleList = tCourseScheduleService.findByExample(tCourseScheduleExample);
            if ( tCourseScheduleList.size() > 0) {
                TCourseOffering courseoffering = new TCourseOffering();
                courseoffering.setFkeyid(Long.parseLong(id));
                courseoffering.setFuid(Long.parseLong(uid));
                courseoffering.setFudate(new Date());
                courseoffering.setFstatus(2);//已开放
                tCourseOfferingService.update(courseoffering);
                // 返回值
                object.put("result", "success");
            } else{
                object.put("result", "error");
                object.put("error", "请先设置开课时间！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


}