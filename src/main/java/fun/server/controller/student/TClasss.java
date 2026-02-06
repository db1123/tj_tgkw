package fun.server.controller.student;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.model.customQuery.tclass.tclassopenCS;
import fun.server.model.customQuery.tclass.tclassopenData;
import fun.server.service.TClassService;
import fun.server.service.TClassStudentService;
import fun.server.service.TGradeService;
import fun.server.service.TMajorService;
import fun.tools.ParamTools;
import fun.tools.SnowflakeIdWorker;
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
 * 班级管理 相关业务处理
 */
@RestController
@RequestMapping("/s/tclass")
public class TClasss {

    private final TClassService tClassService;

    private final TClassStudentService tClassStudentService;

    private final TMajorService tMajorService;

    private final TGradeService tGradeService;


    public TClasss(TClassService tClassService, TClassStudentService tClassStudentService, TMajorService tMajorService, TGradeService tGradeService) {
        this.tClassService = tClassService;
        this.tClassStudentService = tClassStudentService;
        this.tMajorService = tMajorService;
        this.tGradeService = tGradeService;
    }

    /**
     * 获取班级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytclass", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytclass(HttpServletRequest request)
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
        try {
            // 获取数据库记录
            JSONArray tclass_Array = new JSONArray();
            // 查询条件
            TClassExample TClassExample = new TClassExample();
            TClassExample.Criteria criteria = TClassExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFclassnameLike("%" + name + "%");

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
                TClassExample.setOrderByClause(orderSql.substring(1));
            } else {
                TClassExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TClass> tclassPageInfo = tClassService.findByExampleMapper(TClassExample, (page - 1) * results, results);
            List<TClass> tclass_list = tclassPageInfo.getList();

            for (TClass tclass : tclass_list) {
                String season ="";
                JSONObject tclass_object = new JSONObject();
                tclass_object.put("key", ParamTools.getEnParam(tclass.getFkeyid().toString()));
                tclass_object.put("FGradeID", ParamTools.getEnParam(tclass.getFkeyid().toString()));
                tclass_object.put("FMajorID ", ParamTools.getEnParam(tclass.getFmajorid().toString()));
                if (dataall == 1) {
                    tclass_object.put("FClassName", tclass.getFclassname() == null ? "" : tclass.getFclassname());
                    TMajor tMajor = tMajorService.findById(tclass.getFmajorid());
                    tclass_object.put("FMajorName", tMajor == null ? "" : tMajor.getFmajorname());
                    TGrade tGrade = tGradeService.findById(tclass.getFgradeid());
                    switch (tGrade.getFseason()) {
                        case 1:
                            season = "春";
                            break;
                        case 2:
                            season = "秋";
                            break;
                        default:
                            season = "";
                            break;
                    }
                    tclass_object.put("FGradeName", tGrade == null ? "" : tGrade.getFgradename() + season);
                    tclass_object.put("FCID", tclass.getFcid());
                    tclass_object.put("FUID", tclass.getFuid());
                    tclass_object.put("FCDATE", tclass.getFcdate());
                    tclass_object.put("FUDATE", tclass.getFudate());
                } else {
                    tclass_object.put("FName", "*****");
                    tclass_object.put("FCID", "*****");
                    tclass_object.put("FUID", "*****");
                    tclass_object.put("FCDATE", "*****");
                    tclass_object.put("FUDATE", "*****");
                }

                tclass_object.put("FState", tclass.getFstate());
                tclass_Array.add(tclass_object);
            }
            // 返回值
            object.put("list", tclass_Array);
            object.put("total", tclassPageInfo.getTotal()); // 总行数
            object.put("page", tclassPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取班级信息 _多条件
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytclass_dtj", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytclass_dtj(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String FCollegeName = jsonParam.getString("FCollegeName");
        String FMajorName = jsonParam.getString("FMajorName");
        String FGradeID = jsonParam.getString("FGradeID");
        String FClassName = jsonParam.getString("FClassName");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray tclass_Array = new JSONArray();
            // 查询条件
            tclassopenCS tclassopenCS = new tclassopenCS();
            tclassopenCS.setFCollegeName(FCollegeName);
            tclassopenCS.setFMajorName(FMajorName);
            if(FGradeID!= null && !FGradeID.equals("") && !FGradeID.equals("-1")){
                FGradeID = FGradeID== null? "0":ParamTools.getdeParam(FGradeID);
                tclassopenCS.setFGradeID(Long.parseLong(FGradeID));
            }
            tclassopenCS.setFClassName(FClassName);

            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                tclassopenCS.setOrderBy(orderSql.substring(1));
            } else {
                tclassopenCS.setOrderBy("FCDATE desc");
            }
            PageInfo<tclassopenData> tclassPageInfo = tClassService.getClassopenList(tclassopenCS, (page - 1) * results, results);
            List<tclassopenData> tclass_list = tclassPageInfo.getList();
            for (tclassopenData tclass : tclass_list) {
                JSONObject tclass_object = new JSONObject();
                tclass_object.put("key", ParamTools.getEnParam(tclass.getFKeyID().toString()));

                tclass_object.put("FGradeID", ParamTools.getEnParam(tclass.getFGradeID().toString()));
                tclass_object.put("FCollegeName", tclass.getFCollegeName() == null ? "" : tclass.getFCollegeName());
                tclass_object.put("FMajorName", tclass.getFMajorName() == null ? "" : tclass.getFMajorName());
                tclass_object.put("FClassName", tclass.getFClassName() == null ? "" : tclass.getFClassName());
                tclass_object.put("FClassStudentNum", tclass.getFClassStudentNum());
                tclass_Array.add(tclass_object);
            }
            
            // 返回值
            object.put("list", tclass_Array);
            object.put("total", tclassPageInfo.getTotal()); // 总行数
            object.put("page", tclassPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取班级信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatatclassSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatatclassSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray tclass_Array = new JSONArray();
            TClassExample tclassExample = new TClassExample();
            TClassExample.Criteria criteria = tclassExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFclassnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            tclassExample.setOrderByClause("FClassName asc");
            List<TClass> tclass_list = tClassService.findByExample(tclassExample);
            for (TClass tclass : tclass_list) {
                JSONObject tclass_object = new JSONObject();
                tclass_object.put("id", ParamTools.getEnParam(tclass.getFkeyid().toString()));
                tclass_object.put("text", tclass.getFclassname());
                tclass_Array.add(tclass_object);
            }
            // 返回值
            object.put("list", tclass_Array);
            object.put("result", "success");
            object.put("results", tclass_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取班级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytclassInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytclassInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
//            System.out.println(key);
            // 查询班级信息
            TClass tclas = tClassService.findById(key);
            JSONObject tclass_object = new JSONObject();
            tclass_object.put("key", ParamTools.getEnParam(tclas.getFkeyid().toString()));
            tclass_object.put("FMajorID", ParamTools.getEnParam(tclas.getFmajorid().toString()));
            tclass_object.put("FGradeID", ParamTools.getEnParam(tclas.getFgradeid().toString()));
            tclass_object.put("FClassName", tclas.getFclassname() == null ? "" : tclas.getFclassname());
            TMajor tMajor = tMajorService.findById(tclas.getFmajorid());
            tclass_object.put("FMajorName", tMajor == null ? "" : tMajor.getFmajorname());
            TGrade tGrade = tGradeService.findById(tclas.getFgradeid());
            tclass_object.put("FGradeName", tGrade == null ? "" : tGrade.getFgradename());
            tclass_object.put("FCID", tclas.getFcid());
            tclass_object.put("FUID", tclas.getFuid());
            tclass_object.put("FCDATE", tclas.getFcdate());
            tclass_object.put("FUDATE", tclas.getFudate());
            tclass_object.put("FState", tclas.getFstate());

            // 返回值
            object.put("info", tclass_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加班级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加班级信息")
    @ResponseBody
    @RequestMapping(value = "/addtclass", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addtclass(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FClassName = jsonParam.getString("FClassName");//班级名称
        String FGradeID = jsonParam.getString("FGradeID");//年级ID
//        String studentname = jsonParam.getString("studentname");//学生ID 可选
        String FMajorID = jsonParam.getString("FMajorID");//专业ID
        try {
            FGradeID = FGradeID == null ? "0" : ParamTools.getdeParam(FGradeID);
            FMajorID = FMajorID == null ? "0" : ParamTools.getdeParam(FMajorID);
            TClassExample tClassExample = new TClassExample();
            TClassExample.Criteria criteria = tClassExample.createCriteria();
            criteria.andFclassnameEqualTo(FClassName);
            criteria.andFgradeidEqualTo(Long.parseLong(FGradeID));
            criteria.andFmajoridEqualTo(Long.parseLong(FMajorID));
            List<TClass> classList = tClassService.findByExample(tClassExample);
            if(classList.size() == 0) {
                String CookiesLogintclassID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintclassID != null && !CookiesLogintclassID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintclassID);
                }
                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
                long key = idWorker.nextId();
                // 新建班级信息
                TClass tclass = new TClass();
                tclass.setFkeyid(key);
                tclass.setFclassname(FClassName);
                tclass.setFmajorid(Long.parseLong(FMajorID));
                tclass.setFgradeid(Long.parseLong(FGradeID));
                tclass.setFcid(Long.parseLong(uid));
                tclass.setFcdate(new Date());
                tClassService.save(tclass);

//                //如果班级选择了学生
//                if (studentname != null && !studentname.equals("")) {
//                    //增加新选的
//                    JSONArray powerRoles_Array = JSONArray.parseArray(studentname);
//                    for (Object userid : powerRoles_Array) {
//                        Long studentid = Long.parseLong(ParamTools.getdeParam(userid.toString()));
//                        TClassStudent tcLassStudent = new TClassStudent();
//                        tcLassStudent.setFclassid(key);
//                        tcLassStudent.setFstudentid(studentid);
//                        tcLassStudent.setFcid(Long.parseLong(uid));
//                        tcLassStudent.setFcdate(new Date());
//                        tClassStudentService.save(tcLassStudent);
//                    }
//                }
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
     * 添加班级学生信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加班级学生信息")
    @ResponseBody
    @RequestMapping(value = "/addtclassstudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addtclassstudent(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FKeyID = jsonParam.getString("FKeyID");//班级ID
        String studentname = jsonParam.getString("studentname");//学生ID 可选
        try {
            FKeyID = FKeyID == null ? "0" : ParamTools.getdeParam(FKeyID);
            //如果班级选择了学生
            if (studentname != null && !studentname.equals("")) {
                String CookiesLogintclassID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintclassID != null && !CookiesLogintclassID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintclassID);
                }
                //增加新选的
                JSONArray powerRoles_Array = JSONArray.parseArray(studentname);
                for (Object userid : powerRoles_Array) {
                    TClassStudentExample tClassStudentExample = new TClassStudentExample();
                    TClassStudentExample.Criteria criteria = tClassStudentExample.createCriteria();
                    criteria.andFclassidEqualTo(Long.parseLong(FKeyID));
                    criteria.andFstudentidEqualTo(Long.parseLong(uid));
                    List<TClassStudent> studentList = tClassStudentService.findByExample(tClassStudentExample);
                    if (studentList.size() == 0) {
                        Long studentid = Long.parseLong(ParamTools.getdeParam(userid.toString()));
                        TClassStudent tcLassStudent = new TClassStudent();
                        tcLassStudent.setFclassid(Long.parseLong(FKeyID));
                        tcLassStudent.setFstudentid(studentid);
                        tcLassStudent.setFcid(Long.parseLong(uid));
                        tcLassStudent.setFcdate(new Date());
                        tClassStudentService.save(tcLassStudent);
                    }
                }
                // 返回值
                object.put("result", "success");
            }else{
                object.put("result", "error");
                object.put("error", "未选择学生，请重新添加！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 修改班级信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改班级信息")
    @ResponseBody
    @RequestMapping(value = "/updatetclass", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatetclass(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FClassName = jsonParam.getString("FClassName");//班级名称
        String FGradeID = jsonParam.getString("FGradeID");//年级ID
        String FMajorID = jsonParam.getString("FMajorID");//专业ID
        String id = jsonParam.getString("FKeyID");//专业ID
        try {


            id = id == null ? "0" : ParamTools.getdeParam(id);
            FGradeID = FGradeID == null ? "0" : ParamTools.getdeParam(FGradeID);
            FMajorID = FMajorID == null ? "0" : ParamTools.getdeParam(FMajorID);
            TClassExample tClassExample = new TClassExample();
            TClassExample.Criteria criteria = tClassExample.createCriteria();
            criteria.andFclassnameEqualTo(FClassName);
            criteria.andFgradeidEqualTo(Long.parseLong(FGradeID));
            criteria.andFmajoridEqualTo(Long.parseLong(FMajorID));
            criteria.andFkeyidNotEqualTo(Long.parseLong(id));
            List<TClass> classList = tClassService.findByExample(tClassExample);
            if(classList.size() == 0) {
                String CookiesLogintclassID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintclassID != null && !CookiesLogintclassID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintclassID);
                }

                // 新建班级信息
                TClass tclass = new TClass();
                tclass.setFkeyid(Long.parseLong(id));
                tclass.setFclassname(FClassName);
//                tclass.setFmajorid(Long.parseLong(FMajorID));
                tclass.setFgradeid(Long.parseLong(FGradeID));
                tclass.setFcid(Long.parseLong(uid));
                tclass.setFcdate(new Date());
                tClassService.update(tclass);
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
     * 删除班级与学生的关系信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除班级中学生信息")
    @ResponseBody
    @RequestMapping(value = "/delclassstudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delclassstudent(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            tClassStudentService.deleteById(Long.parseLong(id));
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
     * 删除班级与学生的关系信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除班级及学生信息")
    @ResponseBody
    @RequestMapping(value = "/delclasssandtudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delclasssandtudent(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TClassStudentExample tClassStudentExample = new TClassStudentExample();
            tClassStudentExample.createCriteria().andFclassidEqualTo(Long.parseLong(id));
            tClassStudentService.deleteByByExample(tClassStudentExample);
            tClassService.deleteById(Long.parseLong(id));
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